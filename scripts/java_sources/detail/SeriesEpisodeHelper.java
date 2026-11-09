package com.streamsync.app.data.addon;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SeriesEpisodeHelper:
 * 1. Enriches series metadata with episodes, videos, trailers, cast, and genres from Cinemeta
 *    when catalog addons (like Cyberflix) do not provide them.
 * 2. Resolves trailer YouTube IDs for both series and movies.
 */
public class SeriesEpisodeHelper {
    private static final String TAG = "SeriesEpisodeHelper";
    private static final String CINEMETA_SERIES_URL = "https://v3-cinemeta.strem.io/meta/series/";
    private static final String CINEMETA_MOVIE_URL = "https://v3-cinemeta.strem.io/meta/movie/";
    private static final Pattern IMDB_PATTERN = Pattern.compile("tt\\d+");

    public static String extractImdbId(String rawId) {
        if (rawId == null) return null;
        Matcher m = IMDB_PATTERN.matcher(rawId);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }

    /**
     * Called when series metadata is loaded. If videos list is null or empty,
     * this queries Cinemeta to fetch full episode metadata, trailers, cast, and genres.
     */
    public static Object enrichSeriesIfEmpty(Object baseMeta, Object fullMeta) {
        if (baseMeta == null) return fullMeta;

        try {
            // Check if base has videos
            List<?> baseVideos = getListField(baseMeta, "getVideos");
            List<?> fullVideos = fullMeta != null ? getListField(fullMeta, "getVideos") : null;

            boolean hasVideos = (baseVideos != null && !baseVideos.isEmpty()) ||
                                (fullVideos != null && !fullVideos.isEmpty());

            List<?> baseTrailers = getListField(baseMeta, "getTrailers");
            List<?> fullTrailers = fullMeta != null ? getListField(fullMeta, "getTrailers") : null;
            boolean hasTrailers = (baseTrailers != null && !baseTrailers.isEmpty()) ||
                                  (fullTrailers != null && !fullTrailers.isEmpty());

            if (hasVideos && hasTrailers) {
                // If fullMeta has videos/trailers and base doesn't, merge them
                if (fullMeta != null) {
                    return mergeAddonMetas(baseMeta, fullMeta);
                }
                return baseMeta;
            }

            // Need to fetch from Cinemeta!
            String rawId = getStringField(baseMeta, "getId");
            String imdbId = extractImdbId(rawId);
            if (imdbId == null && fullMeta != null) {
                imdbId = extractImdbId(getStringField(fullMeta, "getId"));
            }
            if (imdbId == null) {
                // Return whatever we have
                return fullMeta != null ? fullMeta : baseMeta;
            }

            // Query Cinemeta
            JSONObject metaObj = fetchCinemetaMeta("series", imdbId);
            if (metaObj == null) {
                // Fallback to fullMeta or baseMeta
                return fullMeta != null ? fullMeta : baseMeta;
            }

            return applyCinemetaToMeta(baseMeta, fullMeta, metaObj);
        } catch (Throwable t) {
            Log.e(TAG, "enrichSeriesIfEmpty error", t);
            return fullMeta != null ? fullMeta : baseMeta;
        }
    }

    /**
     * Resolves trailer ID for any media item (series or movie) if it's currently missing.
     */
    public static String resolveTrailerId(String currentTrailerId, String itemId, String type) {
        if (currentTrailerId != null && !currentTrailerId.trim().isEmpty()) {
            return currentTrailerId;
        }
        String imdbId = extractImdbId(itemId);
        if (imdbId == null) return null;

        String mediaType = (type != null && type.contains("series")) ? "series" : "movie";
        try {
            JSONObject metaObj = fetchCinemetaMeta(mediaType, imdbId);
            if (metaObj != null && metaObj.has("trailers")) {
                JSONArray trailersArr = metaObj.optJSONArray("trailers");
                if (trailersArr != null && trailersArr.length() > 0) {
                    for (int i = 0; i < trailersArr.length(); i++) {
                        JSONObject tr = trailersArr.optJSONObject(i);
                        if (tr != null) {
                            String source = tr.optString("source", "");
                            if (!source.isEmpty()) return source;
                            String id = tr.optString("id", "");
                            if (!id.isEmpty()) return id;
                        }
                    }
                }
            }
        } catch (Throwable t) {
            Log.e(TAG, "resolveTrailerId error", t);
        }
        return null;
    }

    private static JSONObject fetchCinemetaMeta(String type, String imdbId) {
        try {
            String urlStr = ("series".equals(type) ? CINEMETA_SERIES_URL : CINEMETA_MOVIE_URL) + imdbId + ".json";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 StreamSync");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            JSONObject root = new JSONObject(sb.toString());
            return root.optJSONObject("meta");
        } catch (Throwable t) {
            Log.e(TAG, "fetchCinemetaMeta error: " + imdbId, t);
            return null;
        }
    }

    private static Object applyCinemetaToMeta(Object baseMeta, Object fullMeta, JSONObject metaObj) {
        try {
            Class<?> addonVideoClass = Class.forName("com.streamsync.app.data.addon.AddonVideo");
            Constructor<?> videoCtor = addonVideoClass.getConstructor(
                String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class
            );

            Class<?> addonTrailerClass = Class.forName("com.streamsync.app.data.addon.AddonTrailer");
            Constructor<?> trailerCtor = addonTrailerClass.getConstructor(
                String.class, String.class, String.class
            );

            // 1. Videos
            List<Object> videosList = new ArrayList<>();
            JSONArray videosArr = metaObj.optJSONArray("videos");
            if (videosArr != null) {
                for (int i = 0; i < videosArr.length(); i++) {
                    JSONObject v = videosArr.optJSONObject(i);
                    if (v == null) continue;
                    String vidId = v.optString("id", "");
                    String title = v.optString("name", v.optString("title", "Tập " + (i + 1)));
                    int season = v.optInt("season", 1);
                    int ep = v.optInt("number", v.optInt("episode", i + 1));
                    String released = v.optString("released", v.optString("firstAired", null));
                    String thumbnail = v.optString("thumbnail", null);
                    String overview = v.optString("overview", v.optString("description", null));

                    Object videoObj = videoCtor.newInstance(
                        vidId, title, Integer.valueOf(season), Integer.valueOf(ep), released, thumbnail, overview
                    );
                    videosList.add(videoObj);
                }
            }

            // 2. Trailers
            List<Object> trailersList = new ArrayList<>();
            JSONArray trailersArr = metaObj.optJSONArray("trailers");
            if (trailersArr != null) {
                for (int i = 0; i < trailersArr.length(); i++) {
                    JSONObject tr = trailersArr.optJSONObject(i);
                    if (tr == null) continue;
                    String ytId = tr.optString("source", tr.optString("id", ""));
                    String type = tr.optString("type", "Trailer");
                    String title = tr.optString("name", "Trailer");
                    if (!ytId.isEmpty()) {
                        Object trailerObj = trailerCtor.newInstance(ytId, type, title);
                        trailersList.add(trailerObj);
                    }
                }
            }

            // 3. Cast
            List<String> castList = new ArrayList<>();
            JSONArray castArr = metaObj.optJSONArray("cast");
            if (castArr != null) {
                for (int i = 0; i < castArr.length(); i++) {
                    String actor = castArr.optString(i, "");
                    if (!actor.isEmpty()) castList.add(actor);
                }
            }

            // 4. Genres
            List<String> genresList = new ArrayList<>();
            JSONArray genresArr = metaObj.optJSONArray("genres");
            if (genresArr != null) {
                for (int i = 0; i < genresArr.length(); i++) {
                    String g = genresArr.optString(i, "");
                    if (!g.isEmpty()) genresList.add(g);
                }
            }

            String description = metaObj.optString("description", null);
            String background = metaObj.optString("background", null);
            String imdbRating = metaObj.optString("imdbRating", null);

            // Construct new AddonMeta with copy$default
            Object target = fullMeta != null ? fullMeta : baseMeta;
            return invokeCopyDefault(target, videosList, trailersList, castList, genresList, description, background, imdbRating);
        } catch (Throwable t) {
            Log.e(TAG, "applyCinemetaToMeta error", t);
            return fullMeta != null ? fullMeta : baseMeta;
        }
    }

    private static Object mergeAddonMetas(Object base, Object full) {
        try {
            List<?> fullVideos = getListField(full, "getVideos");
            List<?> fullTrailers = getListField(full, "getTrailers");
            List<?> fullCast = getListField(full, "getCast");
            List<?> fullGenres = getListField(full, "getGenres");
            String fullDesc = getStringField(full, "getDescription");
            String fullBg = getStringField(full, "getBackground");
            String fullRating = getStringField(full, "getImdbRating");

            return invokeCopyDefault(base, fullVideos, fullTrailers, fullCast, fullGenres, fullDesc, fullBg, fullRating);
        } catch (Throwable t) {
            return base;
        }
    }

    private static Object invokeCopyDefault(
        Object target,
        List<?> videos,
        List<?> trailers,
        List<?> cast,
        List<?> genres,
        String description,
        String background,
        String imdbRating
    ) throws Exception {
        Class<?> metaClass = target.getClass();

        // Signature: (AddonMeta, String id, String type, String name, String poster, String posterShape,
        //             String description, String background, String logo, String icon, String releaseInfo,
        //             String released, String imdbRating, List videos, List genres, List director, List cast,
        //             String runtime, String language, String country, String awards, String website,
        //             List trailers, List links, AddonMetaBehaviorHints, AddonAppExtras, int mask, Object marker)
        for (Method m : metaClass.getMethods()) {
            if ("copy$default".equals(m.getName()) && m.getParameterTypes().length == 28) {
                Object[] args = new Object[28];
                args[0] = target;

                // Mask: starts with all bits 1 (keep original)
                int mask = 0x1ffffff;

                // 6: description (bit 0x20 = 32)
                if (description != null) {
                    args[6] = description;
                    mask &= ~0x20;
                }
                // 7: background (bit 0x40 = 64)
                if (background != null) {
                    args[7] = background;
                    mask &= ~0x40;
                }
                // 12: imdbRating (bit 0x800 = 2048)
                if (imdbRating != null) {
                    args[12] = imdbRating;
                    mask &= ~0x800;
                }
                // 13: videos (bit 0x1000 = 4096)
                if (videos != null && !videos.isEmpty()) {
                    args[13] = videos;
                    mask &= ~0x1000;
                }
                // 14: genres (bit 0x2000 = 8192)
                if (genres != null && !genres.isEmpty()) {
                    args[14] = genres;
                    mask &= ~0x2000;
                }
                // 16: cast (bit 0x8000 = 32768)
                if (cast != null && !cast.isEmpty()) {
                    args[16] = cast;
                    mask &= ~0x8000;
                }
                // 22: trailers (bit 0x200000 = 2097152)
                if (trailers != null && !trailers.isEmpty()) {
                    args[22] = trailers;
                    mask &= ~0x200000;
                }

                args[26] = Integer.valueOf(mask);
                args[27] = null;

                return m.invoke(null, args);
            }
        }
        return target;
    }

    private static List<?> getListField(Object obj, String methodName) {
        if (obj == null) return null;
        try {
            Method m = obj.getClass().getMethod(methodName);
            Object res = m.invoke(obj);
            if (res instanceof List) {
                return (List<?>) res;
            }
        } catch (Throwable ignored) {}
        return null;
    }

    private static String getStringField(Object obj, String methodName) {
        if (obj == null) return null;
        try {
            Method m = obj.getClass().getMethod(methodName);
            Object res = m.invoke(obj);
            if (res instanceof String) {
                return (String) res;
            }
        } catch (Throwable ignored) {}
        return null;
    }
}
