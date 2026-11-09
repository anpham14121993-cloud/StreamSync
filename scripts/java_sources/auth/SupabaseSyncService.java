package com.streamsync.app.auth;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SupabaseSyncService {

    public static final String DEFAULT_SERVER_URL = "https://api.nuvio.tv";
    public static final String DEFAULT_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiYW5vbiIsImlzcyI6InN1cGFiYXNlIiwiaWF0IjoxNzgxNTIxMzQ2LCJleHAiOjE5MzkyMDEzNDZ9.tmQaj682pwzehpqlgCDMnySOqiUvpgRbrE43T4VJpDI";

    public interface SyncCallback {
        void onSuccess(int addonsCount, int progressCount);
        void onError(String message);
    }

    public interface AuthCallback {
        void onSuccess(String email, String token);
        void onError(String message);
    }

    public interface DeviceLinkStartCallback {
        void onSuccess(String userCode, String deviceCode, String verificationUrl, String nonce, int pollInterval);
        void onError(String message);
    }

    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static void runOnMain(Runnable r) {
        sMainHandler.post(r);
    }

    public static void showToast(final Context context, final String msg) {
        runOnMain(new Runnable() {
            @Override
            public void run() {
                if (context != null) {
                    Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static String normalizeUrl(String url) {
        if (url == null) return "";
        url = url.trim();
        while (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (!url.isEmpty() && !url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        return url;
    }

    public static String fetchDiscoveryKey(String serverUrl) {
        String base = normalizeUrl(serverUrl);
        if (base.isEmpty()) return DEFAULT_ANON_KEY;
        try {
            URL url = new URL(base + "/.well-known/nuvio");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(6000);
            int code = conn.getResponseCode();
            if (code == 200) {
                String resp = readStream(conn.getInputStream());
                JSONObject json = new JSONObject(resp);
                String key = json.optString("publishable_key", "");
                if (!key.isEmpty()) {
                    return key;
                }
            }
        } catch (Throwable ignored) {}
        return DEFAULT_ANON_KEY;
    }

    public static void login(final Context context, final String serverUrl, final String email, final String password, final AuthCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String base = normalizeUrl(serverUrl);
                    if (base.isEmpty()) base = DEFAULT_SERVER_URL;

                    String anonKey = fetchDiscoveryKey(base);

                    URL url = new URL(base + "/auth/v1/token?grant_type=password");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("apikey", anonKey);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    conn.setDoOutput(true);

                    JSONObject body = new JSONObject();
                    body.put("email", email.trim());
                    body.put("password", password);

                    byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(data);
                    }

                    int code = conn.getResponseCode();
                    InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
                    String resp = (is != null) ? readStream(is) : "";

                    if (code == 200) {
                        JSONObject json = new JSONObject(resp);
                        String token = json.optString("access_token", "");
                        String refreshToken = json.optString("refresh_token", "");
                        JSONObject user = json.optJSONObject("user");
                        String userId = (user != null) ? user.optString("id", "") : "";

                        SharedPreferences prefs = context.getSharedPreferences("nuvio_supabase_auth", Context.MODE_PRIVATE);
                        prefs.edit()
                                .putString("server_url", base)
                                .putString("anon_key", anonKey)
                                .putString("auth_token", token)
                                .putString("refresh_token", refreshToken)
                                .putString("auth_email", email.trim())
                                .putString("auth_user_id", userId)
                                .putBoolean("is_anonymous", false)
                                .putLong("last_login_time", System.currentTimeMillis())
                                .apply();

                        if (callback != null) {
                            final String finalToken = token;
                            runOnMain(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(email.trim(), finalToken);
                                }
                            });
                        }
                    } else {
                        String errMsg = "Đăng nhập thất bại (" + code + ")";
                        try {
                            JSONObject json = new JSONObject(resp);
                            if (json.has("msg")) errMsg = json.getString("msg");
                            else if (json.has("error_description")) errMsg = json.getString("error_description");
                            else if (json.has("message")) errMsg = json.getString("message");
                        } catch (Throwable ignored) {}

                        final String finalErrMsg = errMsg;
                        if (callback != null) {
                            runOnMain(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onError(finalErrMsg);
                                }
                            });
                        }
                    }
                } catch (final Exception e) {
                    if (callback != null) {
                        runOnMain(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError("Lỗi kết nối: " + e.getMessage());
                            }
                        });
                    }
                }
            }
        }).start();
    }

    public static void startDeviceLink(final Context context, final DeviceLinkStartCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String nonce = UUID.randomUUID().toString();
                    String base = DEFAULT_SERVER_URL;
                    String anonKey = DEFAULT_ANON_KEY;

                    URL url = new URL(base + "/rest/v1/rpc/start_device_login_session");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("apikey", anonKey);
                    conn.setRequestProperty("Authorization", "Bearer " + anonKey);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);
                    conn.setDoOutput(true);

                    JSONObject body = new JSONObject();
                    body.put("p_device_nonce", nonce);
                    body.put("p_redirect_base_url", "https://nuvio.tv/link");
                    body.put("p_device_name", "StreamSync Mobile");
                    body.put("p_device_type", "mobile");

                    byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(data);
                    }

                    int code = conn.getResponseCode();
                    InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();
                    String resp = (is != null) ? readStream(is) : "";

                    if (code == 200) {
                        JSONArray arr = new JSONArray(resp);
                        if (arr.length() > 0) {
                            JSONObject item = arr.getJSONObject(0);
                            final String deviceCode = item.optString("device_code", "");
                            final String userCode = item.optString("user_code", "");
                            final String verifUrl = item.optString("verification_uri_complete", "https://nuvio.tv/link");
                            final int pollInterval = item.optInt("poll_interval_seconds", 3);

                            runOnMain(new Runnable() {
                                @Override
                                public void run() {
                                    if (callback != null) {
                                        callback.onSuccess(userCode, deviceCode, verifUrl, nonce, pollInterval);
                                    }
                                }
                            });
                            return;
                        }
                    }

                    final String err = "Không thể tạo phiên liên kết (" + code + ")";
                    runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) callback.onError(err);
                        }
                    });
                } catch (final Exception e) {
                    runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) callback.onError("Lỗi kết nối: " + e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    public static String pollDeviceStatus(String deviceCode, String nonce) {
        try {
            URL url = new URL(DEFAULT_SERVER_URL + "/rest/v1/rpc/poll_tv_login_session");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("apikey", DEFAULT_ANON_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + DEFAULT_ANON_KEY);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("p_code", deviceCode);
            body.put("p_device_nonce", nonce);

            byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data);
            }

            int code = conn.getResponseCode();
            if (code == 200) {
                String resp = readStream(conn.getInputStream());
                JSONArray arr = new JSONArray(resp);
                if (arr.length() > 0) {
                    return arr.getJSONObject(0).optString("status", "").trim().toLowerCase();
                }
            }
        } catch (Throwable ignored) {}
        return "error";
    }

    public static boolean exchangeAndSaveSession(Context context, String deviceCode, String nonce) {
        try {
            URL url = new URL(DEFAULT_SERVER_URL + "/functions/v1/tv-logins-exchange");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("apikey", DEFAULT_ANON_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + DEFAULT_ANON_KEY);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("code", deviceCode);
            body.put("device_nonce", nonce);

            byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data);
            }

            int code = conn.getResponseCode();
            if (code == 200) {
                String resp = readStream(conn.getInputStream());
                JSONObject json = new JSONObject(resp);
                String accessToken = json.optString("access_token", "");
                String refreshToken = json.optString("refresh_token", "");
                String email = "";
                String userId = "";

                if (json.has("user")) {
                    JSONObject user = json.getJSONObject("user");
                    email = user.optString("email", "");
                    userId = user.optString("id", "");
                }

                if (email.isEmpty() && !accessToken.isEmpty()) {
                    try {
                        URL userUrl = new URL(DEFAULT_SERVER_URL + "/auth/v1/user");
                        HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
                        userConn.setRequestMethod("GET");
                        userConn.setRequestProperty("apikey", DEFAULT_ANON_KEY);
                        userConn.setRequestProperty("Authorization", "Bearer " + accessToken);
                        if (userConn.getResponseCode() == 200) {
                            JSONObject uJson = new JSONObject(readStream(userConn.getInputStream()));
                            email = uJson.optString("email", "");
                            userId = uJson.optString("id", "");
                        }
                    } catch (Throwable ignored) {}
                }

                if (email.isEmpty()) email = "Tài khoản Nuvio";

                SharedPreferences prefs = context.getSharedPreferences("nuvio_supabase_auth", Context.MODE_PRIVATE);
                prefs.edit()
                        .putString("server_url", DEFAULT_SERVER_URL)
                        .putString("anon_key", DEFAULT_ANON_KEY)
                        .putString("auth_token", accessToken)
                        .putString("refresh_token", refreshToken)
                        .putString("auth_email", email)
                        .putString("auth_user_id", userId)
                        .putBoolean("is_anonymous", false)
                        .putLong("last_login_time", System.currentTimeMillis())
                        .apply();
                return true;
            }
        } catch (Throwable ignored) {}
        return false;
    }

    public static void syncLive(final Context context, final SyncCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences prefs = context.getSharedPreferences("nuvio_supabase_auth", Context.MODE_PRIVATE);
                    String serverUrl = prefs.getString("server_url", DEFAULT_SERVER_URL);
                    String token = prefs.getString("auth_token", "");
                    String anonKey = prefs.getString("anon_key", DEFAULT_ANON_KEY);

                    if (token.isEmpty()) {
                        if (callback != null) {
                            runOnMain(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onError("Chưa đăng nhập tài khoản");
                                }
                            });
                        }
                        return;
                    }

                    showToast(context, "Đang tự động đồng bộ tiện ích và lịch sử...");

                    int addonsCount = pullAddonsFromSupabase(context, serverUrl, token, anonKey);
                    int progressCount = pullWatchProgressFromSupabase(context, serverUrl, token, anonKey);

                    prefs.edit()
                            .putLong("last_sync_time", System.currentTimeMillis())
                            .putInt("synced_addons", addonsCount)
                            .putInt("synced_progress", progressCount)
                            .apply();

                    refreshRoom(context);

                    final int finalAddons = addonsCount;
                    final int finalProgress = progressCount;
                    runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onSuccess(finalAddons, finalProgress);
                            }
                        }
                    });
                } catch (final Exception e) {
                    if (callback != null) {
                        runOnMain(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError("Lỗi đồng bộ: " + e.getMessage());
                            }
                        });
                    }
                }
            }
        }).start();
    }

    public static int pullAddonsFromSupabase(Context context, String serverUrl, String token, String anonKey) {
        int count = 0;
        try {
            String base = normalizeUrl(serverUrl);
            List<String> urlsToInstall = new ArrayList<>();

            // 1. Query addons table from Supabase
            try {
                URL url = new URL(base + "/rest/v1/addons?select=*&order=sort_order.asc");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("apikey", anonKey);
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);

                if (conn.getResponseCode() == 200) {
                    String resp = readStream(conn.getInputStream());
                    JSONArray arr = new JSONArray(resp);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        String u = obj.optString("url", "").trim();
                        boolean enabled = obj.optBoolean("enabled", true);
                        if (enabled && !u.isEmpty() && !urlsToInstall.contains(u)) {
                            urlsToInstall.add(u);
                        }
                    }
                }
            } catch (Throwable ignored) {}

            // 2. Query plugins table as complement
            try {
                URL url = new URL(base + "/rest/v1/plugins?select=*&order=sort_order.asc");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("apikey", anonKey);
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);

                if (conn.getResponseCode() == 200) {
                    String resp = readStream(conn.getInputStream());
                    JSONArray arr = new JSONArray(resp);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        String u = obj.optString("url", "").trim();
                        boolean enabled = obj.optBoolean("enabled", true);
                        if (enabled && !u.isEmpty() && !urlsToInstall.contains(u)) {
                            urlsToInstall.add(u);
                        }
                    }
                }
            } catch (Throwable ignored) {}

            if (!urlsToInstall.isEmpty()) {
                count = installAddonsDirectly(context, urlsToInstall);
            }
        } catch (Throwable ignored) {}
        return count;
    }

    public static int pullWatchProgressFromSupabase(Context context, String serverUrl, String token, String anonKey) {
        int count = 0;
        try {
            String base = normalizeUrl(serverUrl);
            List<JSONObject> progressList = new ArrayList<>();
            Set<String> seenMediaIds = new HashSet<>();

            // 1. RPC sync_pull_watch_progress
            try {
                URL url = new URL(base + "/rest/v1/rpc/sync_pull_watch_progress");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("apikey", anonKey);
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                conn.setDoOutput(true);

                JSONObject body = new JSONObject();
                body.put("p_profile_id", 1);
                byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(data);
                }

                if (conn.getResponseCode() == 200) {
                    String resp = readStream(conn.getInputStream());
                    JSONArray arr = new JSONArray(resp);
                    for (int i = 0; i < arr.length(); i++) {
                        progressList.add(arr.getJSONObject(i));
                    }
                }
            } catch (Throwable ignored) {}

            // 2. RPC sync_pull_watched_items
            try {
                URL url = new URL(base + "/rest/v1/rpc/sync_pull_watched_items");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("apikey", anonKey);
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);
                conn.setDoOutput(true);

                JSONObject body = new JSONObject();
                body.put("p_profile_id", 1);
                body.put("p_page", 1);
                body.put("p_page_size", 100);
                byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(data);
                }

                if (conn.getResponseCode() == 200) {
                    String resp = readStream(conn.getInputStream());
                    JSONArray arr = new JSONArray(resp);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject w = arr.getJSONObject(i);
                        JSONObject converted = new JSONObject();
                        converted.put("content_id", w.optString("content_id", ""));
                        converted.put("content_type", w.optString("content_type", ""));
                        converted.put("season", w.optInt("season", 0));
                        converted.put("episode", w.optInt("episode", 0));
                        converted.put("position", 1000);
                        converted.put("duration", 1000);
                        converted.put("last_watched", w.optLong("watched_at", System.currentTimeMillis()));
                        progressList.add(converted);
                    }
                }
            } catch (Throwable ignored) {}

            // 3. Fallback GET watch_progress table
            if (progressList.isEmpty()) {
                try {
                    URL url = new URL(base + "/rest/v1/watch_progress?select=*");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("apikey", anonKey);
                    conn.setRequestProperty("Authorization", "Bearer " + token);
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    if (conn.getResponseCode() == 200) {
                        String resp = readStream(conn.getInputStream());
                        JSONArray arr = new JSONArray(resp);
                        for (int i = 0; i < arr.length(); i++) {
                            progressList.add(arr.getJSONObject(i));
                        }
                    }
                } catch (Throwable ignored) {}
            }

            // 4. Pull Library items (sync_pull_library) into library_items table
            pullLibraryFromSupabase(context, base, token, anonKey);

            if (progressList.isEmpty()) {
                return 0;
            }

            File dbFile = context.getDatabasePath("streamsync.db");
            if (!dbFile.getParentFile().exists()) {
                dbFile.getParentFile().mkdirs();
            }

            ensureRoomDatabase(context);

            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4201b4cf7d52e2b03ff5e210c14b7a60')");
                db.execSQL("PRAGMA user_version = 7");

                db.execSQL("CREATE TABLE IF NOT EXISTS `watch_progress` (`mediaId` TEXT NOT NULL, `positionMs` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, `updatedAtEpochMs` INTEGER NOT NULL, `deviceId` TEXT NOT NULL, `pendingSync` INTEGER NOT NULL, `streamUrl` TEXT, `addonId` TEXT, `addonName` TEXT, PRIMARY KEY(`mediaId`));");

                db.beginTransaction();
                for (JSONObject obj : progressList) {
                    String contentId = obj.optString("content_id", "");
                    if (contentId.isEmpty()) contentId = obj.optString("contentId", "");

                    String videoId = obj.optString("video_id", "");
                    if (videoId.isEmpty()) videoId = obj.optString("videoId", "");

                    int season = obj.optInt("season", 0);
                    int episode = obj.optInt("episode", 0);

                    String mediaId = videoId;
                    if (mediaId.isEmpty()) mediaId = obj.optString("progress_key", "");
                    if (mediaId.isEmpty()) mediaId = contentId;
                    if (mediaId.isEmpty()) mediaId = obj.optString("mediaId", "");

                    if (season > 0 && episode > 0 && !mediaId.contains(":")) {
                        mediaId = contentId + ":" + season + ":" + episode;
                    }

                    if (mediaId.isEmpty() || seenMediaIds.contains(mediaId)) {
                        continue;
                    }
                    seenMediaIds.add(mediaId);

                    long pos = obj.optLong("position", 0);
                    if (pos == 0) pos = obj.optLong("positionMs", 0);
                    if (pos > 0 && pos < 10000) pos = pos * 1000L;
                    if (pos <= 0) pos = 60000L;

                    long dur = obj.optLong("duration", 0);
                    if (dur == 0) dur = obj.optLong("durationMs", 0);
                    if (dur > 0 && dur < 10000) dur = dur * 1000L;
                    if (dur <= pos || pos >= (long)(dur * 0.97)) {
                        dur = Math.max(pos * 2L, pos + 300000L);
                    }

                    long lastWatched = obj.optLong("last_watched", 0);
                    if (lastWatched == 0) lastWatched = obj.optLong("lastWatched", 0);
                    if (lastWatched == 0) lastWatched = obj.optLong("watched_at", 0);
                    if (lastWatched == 0) lastWatched = obj.optLong("updatedAtEpochMs", System.currentTimeMillis());

                    ContentValues cv = new ContentValues();
                    cv.put("mediaId", mediaId);
                    cv.put("positionMs", pos);
                    cv.put("durationMs", dur);
                    cv.put("updatedAtEpochMs", lastWatched);
                    cv.put("deviceId", "streamsync");
                    cv.put("pendingSync", 0);
                    cv.putNull("streamUrl");
                    cv.putNull("addonId");
                    cv.putNull("addonName");

                    db.insertWithOnConflict("watch_progress", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                    count++;
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
                db.close();
            }
        } catch (Throwable ignored) {}
        return count;
    }

    private static void pullLibraryFromSupabase(Context context, String base, String token, String anonKey) {
        try {
            URL url = new URL(base + "/rest/v1/rpc/sync_pull_library");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("apikey", anonKey);
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setDoOutput(true);

            JSONObject body = new JSONObject();
            body.put("p_profile_id", 1);
            body.put("p_limit", 100);
            body.put("p_offset", 0);
            byte[] data = body.toString().getBytes(StandardCharsets.UTF_8);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(data);
            }

            if (conn.getResponseCode() == 200) {
                String resp = readStream(conn.getInputStream());
                JSONArray arr = new JSONArray(resp);
                if (arr.length() == 0) return;

                File dbFile = context.getDatabasePath("streamsync.db");
                if (!dbFile.exists()) return;

                ensureRoomDatabase(context);

                SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
                try {
                    db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                    db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4201b4cf7d52e2b03ff5e210c14b7a60')");
                    db.execSQL("PRAGMA user_version = 7");

                    db.execSQL("CREATE TABLE IF NOT EXISTS `library_items` (`mediaId` TEXT NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `backgroundUrl` TEXT, `description` TEXT, `type` TEXT NOT NULL, `releaseInfo` TEXT, `imdbRating` TEXT, `genresJson` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `addedAtEpochMs` INTEGER NOT NULL, PRIMARY KEY(`mediaId`));");

                    db.beginTransaction();
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject item = arr.getJSONObject(i);
                        String mediaId = item.optString("content_id", "");
                        if (mediaId.isEmpty()) continue;

                        ContentValues cv = new ContentValues();
                        cv.put("mediaId", mediaId);
                        cv.put("title", item.optString("name", ""));
                        cv.put("posterUrl", item.optString("poster", null));
                        cv.put("backgroundUrl", item.optString("background", null));
                        cv.put("description", item.optString("description", null));
                        cv.put("type", item.optString("content_type", "movie"));
                        cv.put("releaseInfo", item.optString("release_info", null));
                        cv.put("imdbRating", item.has("imdb_rating") ? String.valueOf(item.opt("imdb_rating")) : null);

                        JSONArray genresArr = item.optJSONArray("genres");
                        cv.put("genresJson", (genresArr != null) ? genresArr.toString() : "[]");

                        cv.putNull("season");
                        cv.putNull("episode");
                        cv.put("addedAtEpochMs", item.optLong("added_at", System.currentTimeMillis()));

                        db.insertWithOnConflict("library_items", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                    db.close();
                }
            }
        } catch (Throwable ignored) {}
    }

    public static int installAddonsDirectly(Context context, List<String> urls) {
        int installed = 0;
        File dbFile = context.getDatabasePath("streamsync.db");
        if (!dbFile.getParentFile().exists()) {
            dbFile.getParentFile().mkdirs();
        }
        if (urls == null || urls.isEmpty()) return 0;

        ensureRoomDatabase(context);

        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4201b4cf7d52e2b03ff5e210c14b7a60')");
            db.execSQL("PRAGMA user_version = 7");

            db.execSQL("CREATE TABLE IF NOT EXISTS `installed_addons` (`baseUrl` TEXT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `description` TEXT, `typesCsv` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `resourcesJson` TEXT NOT NULL, `idPrefixesCsv` TEXT NOT NULL, `logoUrl` TEXT, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`baseUrl`));");

            db.beginTransaction();
            for (String rawUrl : urls) {
                try {
                    String manifestUrl = rawUrl.trim();
                    if (manifestUrl.isEmpty()) continue;

                    String fetchUrl = manifestUrl.endsWith("/manifest.json") ? manifestUrl : (manifestUrl.replaceAll("/+$", "") + "/manifest.json");
                    String baseUrl = fetchUrl;
                    int mIdx = baseUrl.indexOf("/manifest.json");
                    if (mIdx != -1) {
                        baseUrl = baseUrl.substring(0, mIdx);
                    }
                    baseUrl = baseUrl.replaceAll("/+$", "");

                    JSONObject manifest = null;
                    try {
                        URL u = new URL(fetchUrl);
                        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 StreamSync");
                        conn.setConnectTimeout(8000);
                        conn.setReadTimeout(8000);
                        if (conn.getResponseCode() == 200) {
                            String jsonStr = readStream(conn.getInputStream());
                            manifest = new JSONObject(jsonStr);
                        }
                    } catch (Throwable ignored) {}

                    if (manifest == null) {
                        manifest = getDefaultManifestFallback(baseUrl);
                    }

                    if (manifest == null) continue;

                    String id = manifest.optString("id", "");
                    if (id.isEmpty()) continue;

                    String name = manifest.optString("name", id);
                    String version = manifest.optString("version", "1.0.0");
                    String description = manifest.optString("description", "");
                    String logoUrl = manifest.optString("logo", manifest.optString("icon", ""));

                    String typesCsv = "movie,series";
                    JSONArray defaultTypesArr = new JSONArray("[\"movie\",\"series\"]");
                    if (manifest.has("types")) {
                        JSONArray tArr = manifest.optJSONArray("types");
                        if (tArr != null && tArr.length() > 0) {
                            defaultTypesArr = tArr;
                            StringBuilder sb = new StringBuilder();
                            for (int j = 0; j < tArr.length(); j++) {
                                if (j > 0) sb.append(",");
                                sb.append(tArr.optString(j));
                            }
                            typesCsv = sb.toString();
                        }
                    }

                    JSONArray defaultPrefixesArr = new JSONArray("[\"tt\"]");
                    String idPrefixesCsv = "tt";
                    if (manifest.has("idPrefixes")) {
                        JSONArray pArr = manifest.optJSONArray("idPrefixes");
                        if (pArr != null && pArr.length() > 0) {
                            defaultPrefixesArr = pArr;
                            StringBuilder sb = new StringBuilder();
                            for (int j = 0; j < pArr.length(); j++) {
                                if (j > 0) sb.append(",");
                                sb.append(pArr.optString(j));
                            }
                            idPrefixesCsv = sb.toString();
                        }
                    }

                    // 1. Normalize catalogs to valid AddonCatalogDef JSONObjects
                    JSONArray normCatalogs = new JSONArray();
                    if (manifest.has("catalogs")) {
                        Object cObj = manifest.opt("catalogs");
                        if (cObj instanceof JSONArray) {
                            JSONArray cArr = (JSONArray) cObj;
                            for (int ci = 0; ci < cArr.length(); ci++) {
                                Object cItem = cArr.opt(ci);
                                if (cItem instanceof JSONObject) {
                                    JSONObject cDef = (JSONObject) cItem;
                                    String cId = cDef.optString("id", "").trim();
                                    String cType = cDef.optString("type", "").trim();
                                    if (!cId.isEmpty()) {
                                        if (cType.isEmpty()) cDef.put("type", "movie");
                                        if (!cDef.has("name") || cDef.optString("name", "").isEmpty()) {
                                            cDef.put("name", cId);
                                        }
                                        if (!cDef.has("extra") || !(cDef.opt("extra") instanceof JSONArray)) {
                                            cDef.put("extra", new JSONArray("[]"));
                                        }
                                        if (!cDef.has("extraSupported") || !(cDef.opt("extraSupported") instanceof JSONArray)) {
                                            cDef.put("extraSupported", new JSONArray("[]"));
                                        }
                                        if (!cDef.has("extraRequired") || !(cDef.opt("extraRequired") instanceof JSONArray)) {
                                            cDef.put("extraRequired", new JSONArray("[]"));
                                        }
                                        normCatalogs.put(cDef);
                                    }
                                }
                            }
                        }
                    }
                    String catalogsJson = normCatalogs.toString();

                    // 2. Normalize resources to valid AddonResourceDef JSONObjects
                    // Prevents ClassCastException: String cannot be cast to AddonResourceDef
                    JSONArray normResources = new JSONArray();
                    if (manifest.has("resources")) {
                        Object rObj = manifest.opt("resources");
                        if (rObj instanceof JSONArray) {
                            JSONArray rArr = (JSONArray) rObj;
                            for (int ri = 0; ri < rArr.length(); ri++) {
                                Object rItem = rArr.opt(ri);
                                if (rItem instanceof String) {
                                    String rName = (String) rItem;
                                    if (!rName.trim().isEmpty()) {
                                        JSONObject rDef = new JSONObject();
                                        rDef.put("name", rName.trim());
                                        rDef.put("types", defaultTypesArr);
                                        rDef.put("idPrefixes", defaultPrefixesArr);
                                        normResources.put(rDef);
                                    }
                                } else if (rItem instanceof JSONObject) {
                                    JSONObject rDef = (JSONObject) rItem;
                                    String rName = rDef.optString("name", "").trim();
                                    if (!rName.isEmpty()) {
                                        if (!rDef.has("types") || !(rDef.opt("types") instanceof JSONArray)) {
                                            rDef.put("types", defaultTypesArr);
                                        }
                                        if (!rDef.has("idPrefixes") || !(rDef.opt("idPrefixes") instanceof JSONArray)) {
                                            rDef.put("idPrefixes", defaultPrefixesArr);
                                        }
                                        normResources.put(rDef);
                                    }
                                }
                            }
                        }
                    }
                    if (normResources.length() == 0) {
                        normResources = new JSONArray("[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]");
                    }
                    String resourcesJson = normResources.toString();

                    ContentValues cv = new ContentValues();
                    cv.put("baseUrl", baseUrl);
                    cv.put("id", id);
                    cv.put("name", name);
                    cv.put("version", version);
                    cv.put("description", description);
                    cv.put("typesCsv", typesCsv);
                    cv.put("catalogsJson", catalogsJson);
                    cv.put("resourcesJson", resourcesJson);
                    cv.put("idPrefixesCsv", idPrefixesCsv);
                    if (!logoUrl.isEmpty()) {
                        cv.put("logoUrl", logoUrl);
                    } else {
                        cv.putNull("logoUrl");
                    }
                    cv.put("sortOrder", installed);

                    db.insertWithOnConflict("installed_addons", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
                    installed++;
                } catch (Throwable ignored) {}
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return installed;
    }

    private static JSONObject getDefaultManifestFallback(String baseUrl) {
        try {
            JSONObject obj = new JSONObject();
            if (baseUrl.contains("cinemeta")) {
                obj.put("id", "community.cinemeta");
                obj.put("name", "Cinemeta");
                obj.put("version", "3.0.12");
                obj.put("description", "Cung cấp danh mục và siêu dữ liệu chính thức cho phim & TV series");
                obj.put("types", new JSONArray("[\"movie\",\"series\"]"));
                obj.put("catalogs", new JSONArray("[{\"type\":\"movie\",\"id\":\"top\",\"name\":\"Top Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"top\",\"name\":\"Top Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"));
                obj.put("resources", new JSONArray("[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]},{\"name\":\"meta\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"));
                obj.put("idPrefixes", new JSONArray("[\"tt\"]"));
                obj.put("logo", "https://v3-cinemeta.strem.io/logo.png");
                return obj;
            } else if (baseUrl.contains("opensubtitles")) {
                obj.put("id", "org.stremio.opensubtitles-v3");
                obj.put("name", "OpenSubtitles v3");
                obj.put("version", "1.0.0");
                obj.put("description", "Phụ đề đa ngôn ngữ chính thức từ OpenSubtitles");
                obj.put("types", new JSONArray("[\"movie\",\"series\"]"));
                obj.put("catalogs", new JSONArray("[]"));
                obj.put("resources", new JSONArray("[{\"name\":\"subtitles\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"));
                obj.put("idPrefixes", new JSONArray("[\"tt\"]"));
                obj.put("logo", "https://opensubtitles-v3.strem.io/logo.png");
                return obj;
            } else if (baseUrl.contains("cyberflix")) {
                obj.put("id", "tv.cyberflix.catalog");
                obj.put("name", "Cyberflix Catalog");
                obj.put("version", "1.5.0");
                obj.put("description", "Danh mục phim và series từ các nền tảng trực tuyến phổ biến");
                obj.put("types", new JSONArray("[\"movie\",\"series\"]"));
                obj.put("catalogs", new JSONArray("[{\"type\":\"movie\",\"id\":\"cyberflix_netflix_movies\",\"name\":\"Netflix Phim\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]},{\"type\":\"series\",\"id\":\"cyberflix_netflix_series\",\"name\":\"Netflix Series\",\"extra\":[],\"extraSupported\":[],\"extraRequired\":[]}]"));
                obj.put("resources", new JSONArray("[{\"name\":\"catalog\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"));
                obj.put("idPrefixes", new JSONArray("[\"tt\"]"));
                obj.put("logo", "");
                return obj;
            } else if (baseUrl.contains("torrentio")) {
                obj.put("id", "com.stremio.torrentio");
                obj.put("name", "Torrentio");
                obj.put("version", "1.0.13");
                obj.put("description", "Nguồn phát torrent và debrid đa dạng");
                obj.put("types", new JSONArray("[\"movie\",\"series\"]"));
                obj.put("catalogs", new JSONArray("[]"));
                obj.put("resources", new JSONArray("[{\"name\":\"stream\",\"types\":[\"movie\",\"series\"],\"idPrefixes\":[\"tt\"]}]"));
                obj.put("idPrefixes", new JSONArray("[\"tt\"]"));
                obj.put("logo", "https://torrentio.strem.fun/logo.png");
                return obj;
            }
        } catch (Throwable ignored) {}
        return null;
    }

    public static int installDefaultAddonsFallback(Context context) {
        List<String> list = new ArrayList<>();
        list.add("https://v3-cinemeta.strem.io/manifest.json");
        list.add("https://opensubtitles-v3.strem.io/manifest.json");
        list.add("https://catalog.cyberflix.tv/manifest.json");
        list.add("https://torrentio.strem.fun/manifest.json");
        return installAddonsDirectly(context, list);
    }

    public static void ensureRoomDatabase(Context context) {
        if (context == null) return;
        try {
            Class<?> appDbClass = Class.forName("com.streamsync.app.data.local.AppDatabase");
            Object companion = appDbClass.getField("Companion").get(null);
            java.lang.reflect.Method getMethod = companion.getClass().getMethod("get", Context.class);
            getMethod.invoke(companion, context.getApplicationContext());
        } catch (Throwable ignored) {}
    }

    public static void refreshRoom(Context context) {
        try {
            Class<?> appDbClass = Class.forName("com.streamsync.app.data.local.AppDatabase");
            Object companion = appDbClass.getField("Companion").get(null);
            java.lang.reflect.Method getMethod = companion.getClass().getMethod("get", Context.class);
            Object dbInstance = getMethod.invoke(companion, context.getApplicationContext());
            if (dbInstance != null) {
                java.lang.reflect.Method getTrackerMethod = dbInstance.getClass().getMethod("getInvalidationTracker");
                Object tracker = getTrackerMethod.invoke(dbInstance);
                if (tracker != null) {
                    java.lang.reflect.Method refreshMethod = tracker.getClass().getMethod("refreshVersionsAsync");
                    refreshMethod.invoke(tracker);
                }
            }
        } catch (Throwable ignored) {}
    }

    private static String readStream(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();
        return sb.toString().trim();
    }
}
