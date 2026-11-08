package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.hls.HlsTrackMetadataEntry;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes3.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_PLAYREADY = "com.microsoft.playready";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String LOG_TAG = "HlsPlaylistParser";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DEFINE = "#EXT-X-DEFINE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_IFRAME = "#EXT-X-I-FRAMES-ONLY";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_I_FRAME_STREAM_INF = "#EXT-X-I-FRAME-STREAM-INF";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PART = "#EXT-X-PART";
    private static final String TAG_PART_INF = "#EXT-X-PART-INF";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PRELOAD_HINT = "#EXT-X-PRELOAD-HINT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_RENDITION_REPORT = "#EXT-X-RENDITION-REPORT";
    private static final String TAG_SERVER_CONTROL = "#EXT-X-SERVER-CONTROL";
    private static final String TAG_SESSION_KEY = "#EXT-X-SESSION-KEY";
    private static final String TAG_SKIP = "#EXT-X-SKIP";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_MAP = "MAP";
    private static final String TYPE_PART = "PART";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private final HlsMultivariantPlaylist multivariantPlaylist;
    private final HlsMediaPlaylist previousMediaPlaylist;
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_VIDEO = Pattern.compile("VIDEO=\"(.+?)\"");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"(.+?)\"");
    private static final Pattern REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"(.+?)\"");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_ATTR_DURATION = Pattern.compile("DURATION=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PART_TARGET_DURATION = Pattern.compile("PART-TARGET=([\\d\\.]+)\\b");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_CAN_SKIP_UNTIL = Pattern.compile("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
    private static final Pattern REGEX_CAN_SKIP_DATE_RANGES = compileBooleanAttrPattern("CAN-SKIP-DATERANGES");
    private static final Pattern REGEX_SKIPPED_SEGMENTS = Pattern.compile("SKIPPED-SEGMENTS=(\\d+)\\b");
    private static final Pattern REGEX_HOLD_BACK = Pattern.compile("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PART_HOLD_BACK = Pattern.compile("PART-HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_CAN_BLOCK_RELOAD = compileBooleanAttrPattern("CAN-BLOCK-RELOAD");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_LAST_MSN = Pattern.compile("LAST-MSN=(\\d+)\\b");
    private static final Pattern REGEX_LAST_PART = Pattern.compile("LAST-PART=(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_BYTERANGE_START = Pattern.compile("BYTERANGE-START=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE_LENGTH = Pattern.compile("BYTERANGE-LENGTH=(\\d+)\\b");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_PRELOAD_HINT_TYPE = Pattern.compile("TYPE=(PART|MAP)");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Pattern REGEX_INDEPENDENT = compileBooleanAttrPattern("INDEPENDENT");
    private static final Pattern REGEX_GAP = compileBooleanAttrPattern("GAP");
    private static final Pattern REGEX_PRECISE = compileBooleanAttrPattern("PRECISE");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");

    /* loaded from: classes3.dex */
    public static final class DeltaUpdateException extends IOException {
    }

    public HlsPlaylistParser() {
        this(HlsMultivariantPlaylist.EMPTY, null);
    }

    public HlsPlaylistParser(HlsMultivariantPlaylist multivariantPlaylist, HlsMediaPlaylist previousMediaPlaylist) {
        this.multivariantPlaylist = multivariantPlaylist;
        this.previousMediaPlaylist = previousMediaPlaylist;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Queue<String> extraLines = new ArrayDeque<>();
        try {
            if (!checkPlaylistHeader(reader)) {
                throw ParserException.createForMalformedManifest("Input does not start with the #EXTM3U header.", null);
            }
            while (true) {
                String line2 = reader.readLine();
                if (line2 == null) {
                    Util.closeQuietly(reader);
                    throw ParserException.createForMalformedManifest("Failed to parse the playlist, could not identify any tags.", null);
                }
                line = line2.trim();
                if (!line.isEmpty()) {
                    if (!line.startsWith(TAG_STREAM_INF)) {
                        if (line.startsWith(TAG_TARGET_DURATION) || line.startsWith(TAG_MEDIA_SEQUENCE) || line.startsWith(TAG_MEDIA_DURATION) || line.startsWith(TAG_KEY) || line.startsWith(TAG_BYTERANGE) || line.equals(TAG_DISCONTINUITY) || line.equals(TAG_DISCONTINUITY_SEQUENCE) || line.equals(TAG_ENDLIST)) {
                            break;
                        }
                        extraLines.add(line);
                    } else {
                        extraLines.add(line);
                        return parseMultivariantPlaylist(new LineIterator(extraLines, reader), uri.toString());
                    }
                }
            }
            extraLines.add(line);
            return parseMediaPlaylist(this.multivariantPlaylist, this.previousMediaPlaylist, new LineIterator(extraLines, reader), uri.toString());
        } finally {
            Util.closeQuietly(reader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader reader) throws IOException {
        int last = reader.read();
        if (last == 239) {
            if (reader.read() != 187 || reader.read() != 191) {
                return false;
            }
            last = reader.read();
        }
        int last2 = skipIgnorableWhitespace(reader, true, last);
        int playlistHeaderLength = PLAYLIST_HEADER.length();
        for (int i = 0; i < playlistHeaderLength; i++) {
            if (last2 != PLAYLIST_HEADER.charAt(i)) {
                return false;
            }
            last2 = reader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(reader, false, last2));
    }

    private static int skipIgnorableWhitespace(BufferedReader reader, boolean skipLinebreaks, int c) throws IOException {
        while (c != -1 && Character.isWhitespace(c) && (skipLinebreaks || !Util.isLinebreak(c))) {
            c = reader.read();
        }
        return c;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:88:0x03b1. Please report as an issue. */
    private static HlsMultivariantPlaylist parseMultivariantPlaylist(LineIterator iterator, String baseUri) throws IOException {
        char c;
        List<Format> muxedCaptionFormats;
        ArrayList<HlsMultivariantPlaylist.Rendition> audios;
        ArrayList<HlsMultivariantPlaylist.Rendition> videos;
        String codecs;
        String sampleMimeType;
        int accessibilityChannel;
        List<Format> muxedCaptionFormats2;
        ArrayList<HlsMultivariantPlaylist.Rendition> closedCaptions;
        ArrayList<DrmInitData> sessionKeyDrmInitData;
        ArrayList<String> tags;
        ArrayList<HlsMultivariantPlaylist.Rendition> videos2;
        int width;
        int height;
        ArrayList<HlsMultivariantPlaylist.Rendition> audios2;
        float frameRate;
        ArrayList<HlsMultivariantPlaylist.Rendition> subtitles;
        ArrayList<String> mediaTags;
        Uri uri;
        HashMap<Uri, ArrayList<HlsTrackMetadataEntry.VariantInfo>> urlToVariantInfos;
        ArrayList<HlsTrackMetadataEntry.VariantInfo> variantInfosForUrl;
        String str = baseUri;
        HashMap<Uri, ArrayList<HlsTrackMetadataEntry.VariantInfo>> urlToVariantInfos2 = new HashMap<>();
        HashMap<String, String> variableDefinitions = new HashMap<>();
        ArrayList<HlsMultivariantPlaylist.Variant> variants = new ArrayList<>();
        ArrayList<HlsMultivariantPlaylist.Rendition> videos3 = new ArrayList<>();
        ArrayList<HlsMultivariantPlaylist.Rendition> audios3 = new ArrayList<>();
        ArrayList<HlsMultivariantPlaylist.Rendition> subtitles2 = new ArrayList<>();
        ArrayList<HlsMultivariantPlaylist.Rendition> closedCaptions2 = new ArrayList<>();
        ArrayList<String> mediaTags2 = new ArrayList<>();
        ArrayList<DrmInitData> sessionKeyDrmInitData2 = new ArrayList<>();
        ArrayList<String> tags2 = new ArrayList<>();
        Format muxedAudioFormat = null;
        List<Format> muxedCaptionFormats3 = null;
        boolean noClosedCaptions = false;
        boolean hasIndependentSegmentsTag = false;
        while (true) {
            boolean noClosedCaptions2 = iterator.hasNext();
            String str2 = MimeTypes.APPLICATION_M3U8;
            if (noClosedCaptions2) {
                String line = iterator.next();
                if (line.startsWith(TAG_PREFIX)) {
                    tags2.add(line);
                }
                boolean isIFrameOnlyVariant = line.startsWith(TAG_I_FRAME_STREAM_INF);
                Format muxedAudioFormat2 = muxedAudioFormat;
                if (line.startsWith(TAG_DEFINE)) {
                    variableDefinitions.put(parseStringAttr(line, REGEX_NAME, variableDefinitions), parseStringAttr(line, REGEX_VALUE, variableDefinitions));
                    muxedCaptionFormats2 = muxedCaptionFormats3;
                    closedCaptions = closedCaptions2;
                } else {
                    if (line.equals(TAG_INDEPENDENT_SEGMENTS)) {
                        hasIndependentSegmentsTag = true;
                        muxedCaptionFormats2 = muxedCaptionFormats3;
                        tags = tags2;
                        sessionKeyDrmInitData = sessionKeyDrmInitData2;
                        closedCaptions = closedCaptions2;
                        mediaTags = mediaTags2;
                        subtitles = subtitles2;
                        audios2 = audios3;
                        videos2 = videos3;
                        urlToVariantInfos = urlToVariantInfos2;
                    } else if (line.startsWith(TAG_MEDIA)) {
                        mediaTags2.add(line);
                        muxedCaptionFormats2 = muxedCaptionFormats3;
                        closedCaptions = closedCaptions2;
                    } else if (line.startsWith(TAG_SESSION_KEY)) {
                        String keyFormat = parseOptionalStringAttr(line, REGEX_KEYFORMAT, KEYFORMAT_IDENTITY, variableDefinitions);
                        DrmInitData.SchemeData schemeData = parseDrmSchemeData(line, keyFormat, variableDefinitions);
                        if (schemeData != null) {
                            String method = parseStringAttr(line, REGEX_METHOD, variableDefinitions);
                            muxedCaptionFormats2 = muxedCaptionFormats3;
                            String scheme = parseEncryptionScheme(method);
                            closedCaptions = closedCaptions2;
                            sessionKeyDrmInitData2.add(new DrmInitData(scheme, schemeData));
                        } else {
                            muxedCaptionFormats2 = muxedCaptionFormats3;
                            closedCaptions = closedCaptions2;
                        }
                    } else {
                        muxedCaptionFormats2 = muxedCaptionFormats3;
                        closedCaptions = closedCaptions2;
                        if (line.startsWith(TAG_STREAM_INF) || isIFrameOnlyVariant) {
                            boolean noClosedCaptions3 = noClosedCaptions | line.contains(ATTR_CLOSED_CAPTIONS_NONE);
                            int roleFlags = isIFrameOnlyVariant ? 16384 : 0;
                            int peakBitrate = parseIntAttr(line, REGEX_BANDWIDTH);
                            noClosedCaptions = noClosedCaptions3;
                            sessionKeyDrmInitData = sessionKeyDrmInitData2;
                            int averageBitrate = parseOptionalIntAttr(line, REGEX_AVERAGE_BANDWIDTH, -1);
                            String codecs2 = parseOptionalStringAttr(line, REGEX_CODECS, variableDefinitions);
                            tags = tags2;
                            String resolutionString = parseOptionalStringAttr(line, REGEX_RESOLUTION, variableDefinitions);
                            if (resolutionString != null) {
                                videos2 = videos3;
                                String[] widthAndHeight = Util.split(resolutionString, "x");
                                int width2 = Integer.parseInt(widthAndHeight[0]);
                                int height2 = Integer.parseInt(widthAndHeight[1]);
                                if (width2 <= 0 || height2 <= 0) {
                                    width2 = -1;
                                    height2 = -1;
                                }
                                width = width2;
                                height = height2;
                            } else {
                                videos2 = videos3;
                                width = -1;
                                height = -1;
                            }
                            audios2 = audios3;
                            String frameRateString = parseOptionalStringAttr(line, REGEX_FRAME_RATE, variableDefinitions);
                            if (frameRateString == null) {
                                frameRate = -1.0f;
                            } else {
                                float frameRate2 = Float.parseFloat(frameRateString);
                                frameRate = frameRate2;
                            }
                            subtitles = subtitles2;
                            String videoGroupId = parseOptionalStringAttr(line, REGEX_VIDEO, variableDefinitions);
                            mediaTags = mediaTags2;
                            String audioGroupId = parseOptionalStringAttr(line, REGEX_AUDIO, variableDefinitions);
                            HashMap<Uri, ArrayList<HlsTrackMetadataEntry.VariantInfo>> urlToVariantInfos3 = urlToVariantInfos2;
                            String subtitlesGroupId = parseOptionalStringAttr(line, REGEX_SUBTITLES, variableDefinitions);
                            String closedCaptionsGroupId = parseOptionalStringAttr(line, REGEX_CLOSED_CAPTIONS, variableDefinitions);
                            if (isIFrameOnlyVariant) {
                                uri = UriUtil.resolveToUri(str, parseStringAttr(line, REGEX_URI, variableDefinitions));
                            } else {
                                if (!iterator.hasNext()) {
                                    throw ParserException.createForMalformedManifest("#EXT-X-STREAM-INF must be followed by another line", null);
                                }
                                line = replaceVariableReferences(iterator.next(), variableDefinitions);
                                uri = UriUtil.resolveToUri(str, line);
                            }
                            Format format = new Format.Builder().setId(variants.size()).setContainerMimeType(MimeTypes.APPLICATION_M3U8).setCodecs(codecs2).setAverageBitrate(averageBitrate).setPeakBitrate(peakBitrate).setWidth(width).setHeight(height).setFrameRate(frameRate).setRoleFlags(roleFlags).build();
                            variants.add(new HlsMultivariantPlaylist.Variant(uri, format, videoGroupId, audioGroupId, subtitlesGroupId, closedCaptionsGroupId));
                            urlToVariantInfos = urlToVariantInfos3;
                            ArrayList<HlsTrackMetadataEntry.VariantInfo> variantInfosForUrl2 = urlToVariantInfos.get(uri);
                            if (variantInfosForUrl2 == null) {
                                variantInfosForUrl = new ArrayList<>();
                                urlToVariantInfos.put(uri, variantInfosForUrl);
                            } else {
                                variantInfosForUrl = variantInfosForUrl2;
                            }
                            variantInfosForUrl.add(new HlsTrackMetadataEntry.VariantInfo(averageBitrate, peakBitrate, videoGroupId, audioGroupId, subtitlesGroupId, closedCaptionsGroupId));
                        }
                    }
                    urlToVariantInfos2 = urlToVariantInfos;
                    tags2 = tags;
                    muxedAudioFormat = muxedAudioFormat2;
                    muxedCaptionFormats3 = muxedCaptionFormats2;
                    closedCaptions2 = closedCaptions;
                    sessionKeyDrmInitData2 = sessionKeyDrmInitData;
                    videos3 = videos2;
                    audios3 = audios2;
                    subtitles2 = subtitles;
                    mediaTags2 = mediaTags;
                    str = baseUri;
                }
                tags = tags2;
                sessionKeyDrmInitData = sessionKeyDrmInitData2;
                mediaTags = mediaTags2;
                subtitles = subtitles2;
                audios2 = audios3;
                videos2 = videos3;
                urlToVariantInfos = urlToVariantInfos2;
                urlToVariantInfos2 = urlToVariantInfos;
                tags2 = tags;
                muxedAudioFormat = muxedAudioFormat2;
                muxedCaptionFormats3 = muxedCaptionFormats2;
                closedCaptions2 = closedCaptions;
                sessionKeyDrmInitData2 = sessionKeyDrmInitData;
                videos3 = videos2;
                audios3 = audios2;
                subtitles2 = subtitles;
                mediaTags2 = mediaTags;
                str = baseUri;
            } else {
                Format muxedAudioFormat3 = muxedAudioFormat;
                List<Format> muxedCaptionFormats4 = muxedCaptionFormats3;
                ArrayList<String> tags3 = tags2;
                ArrayList<DrmInitData> sessionKeyDrmInitData3 = sessionKeyDrmInitData2;
                ArrayList<HlsMultivariantPlaylist.Rendition> closedCaptions3 = closedCaptions2;
                ArrayList<String> mediaTags3 = mediaTags2;
                ArrayList<HlsMultivariantPlaylist.Rendition> subtitles3 = subtitles2;
                ArrayList<HlsMultivariantPlaylist.Rendition> audios4 = audios3;
                ArrayList<HlsMultivariantPlaylist.Rendition> videos4 = videos3;
                HashMap<Uri, ArrayList<HlsTrackMetadataEntry.VariantInfo>> urlToVariantInfos4 = urlToVariantInfos2;
                ArrayList<HlsMultivariantPlaylist.Variant> deduplicatedVariants = new ArrayList<>();
                HashSet<Uri> urlsInDeduplicatedVariants = new HashSet<>();
                for (int i = 0; i < variants.size(); i++) {
                    HlsMultivariantPlaylist.Variant variant = variants.get(i);
                    if (urlsInDeduplicatedVariants.add(variant.url)) {
                        Assertions.checkState(variant.format.metadata == null);
                        HlsTrackMetadataEntry hlsMetadataEntry = new HlsTrackMetadataEntry(null, null, (List) Assertions.checkNotNull(urlToVariantInfos4.get(variant.url)));
                        Format format2 = variant.format.buildUpon().setMetadata(new Metadata(hlsMetadataEntry)).build();
                        deduplicatedVariants.add(variant.copyWithFormat(format2));
                    }
                }
                int i2 = 0;
                List<Format> muxedCaptionFormats5 = muxedCaptionFormats4;
                while (i2 < mediaTags3.size()) {
                    ArrayList<String> mediaTags4 = mediaTags3;
                    String line2 = mediaTags4.get(i2);
                    String groupId = parseStringAttr(line2, REGEX_GROUP_ID, variableDefinitions);
                    String name = parseStringAttr(line2, REGEX_NAME, variableDefinitions);
                    Format.Builder formatBuilder = new Format.Builder().setId(groupId + ":" + name).setLabel(name).setContainerMimeType(str2).setSelectionFlags(parseSelectionFlags(line2)).setRoleFlags(parseRoleFlags(line2, variableDefinitions)).setLanguage(parseOptionalStringAttr(line2, REGEX_LANGUAGE, variableDefinitions));
                    String referenceUri = parseOptionalStringAttr(line2, REGEX_URI, variableDefinitions);
                    Uri uri2 = referenceUri == null ? null : UriUtil.resolveToUri(baseUri, referenceUri);
                    String str3 = str2;
                    HashSet<Uri> urlsInDeduplicatedVariants2 = urlsInDeduplicatedVariants;
                    mediaTags3 = mediaTags4;
                    Metadata metadata = new Metadata(new HlsTrackMetadataEntry(groupId, name, Collections.emptyList()));
                    String parseStringAttr = parseStringAttr(line2, REGEX_TYPE, variableDefinitions);
                    switch (parseStringAttr.hashCode()) {
                        case -959297733:
                            if (parseStringAttr.equals(TYPE_SUBTITLES)) {
                                c = 2;
                                break;
                            }
                            break;
                        case -333210994:
                            if (parseStringAttr.equals(TYPE_CLOSED_CAPTIONS)) {
                                c = 3;
                                break;
                            }
                            break;
                        case 62628790:
                            if (parseStringAttr.equals(TYPE_AUDIO)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 81665115:
                            if (parseStringAttr.equals(TYPE_VIDEO)) {
                                c = 0;
                                break;
                            }
                            break;
                    }
                    c = 65535;
                    switch (c) {
                        case 0:
                            muxedCaptionFormats = muxedCaptionFormats5;
                            audios = audios4;
                            HlsMultivariantPlaylist.Variant variant2 = getVariantWithVideoGroup(variants, groupId);
                            if (variant2 != null) {
                                Format variantFormat = variant2.format;
                                String codecs3 = Util.getCodecsOfType(variantFormat.codecs, 2);
                                formatBuilder.setCodecs(codecs3).setSampleMimeType(MimeTypes.getMediaMimeType(codecs3)).setWidth(variantFormat.width).setHeight(variantFormat.height).setFrameRate(variantFormat.frameRate);
                            }
                            if (uri2 == null) {
                                videos = videos4;
                            } else {
                                formatBuilder.setMetadata(metadata);
                                videos = videos4;
                                videos.add(new HlsMultivariantPlaylist.Rendition(uri2, formatBuilder.build(), groupId, name));
                            }
                            muxedCaptionFormats5 = muxedCaptionFormats;
                            break;
                        case 1:
                            muxedCaptionFormats = muxedCaptionFormats5;
                            HlsMultivariantPlaylist.Variant variant3 = getVariantWithAudioGroup(variants, groupId);
                            if (variant3 == null) {
                                codecs = null;
                            } else {
                                String codecs4 = Util.getCodecsOfType(variant3.format.codecs, 1);
                                formatBuilder.setCodecs(codecs4);
                                codecs = MimeTypes.getMediaMimeType(codecs4);
                            }
                            String channelsString = parseOptionalStringAttr(line2, REGEX_CHANNELS, variableDefinitions);
                            if (channelsString != null) {
                                int channelCount = Integer.parseInt(Util.splitAtFirst(channelsString, "/")[0]);
                                formatBuilder.setChannelCount(channelCount);
                                if (MimeTypes.AUDIO_E_AC3.equals(codecs) && channelsString.endsWith("/JOC")) {
                                    codecs = MimeTypes.AUDIO_E_AC3_JOC;
                                    formatBuilder.setCodecs(MimeTypes.CODEC_E_AC3_JOC);
                                }
                            }
                            formatBuilder.setSampleMimeType(codecs);
                            if (uri2 == null) {
                                audios = audios4;
                                if (variant3 == null) {
                                    videos = videos4;
                                } else {
                                    muxedAudioFormat3 = formatBuilder.build();
                                    muxedCaptionFormats5 = muxedCaptionFormats;
                                    videos = videos4;
                                    break;
                                }
                            } else {
                                formatBuilder.setMetadata(metadata);
                                audios = audios4;
                                audios.add(new HlsMultivariantPlaylist.Rendition(uri2, formatBuilder.build(), groupId, name));
                                videos = videos4;
                            }
                            muxedCaptionFormats5 = muxedCaptionFormats;
                            break;
                        case 2:
                            String sampleMimeType2 = null;
                            HlsMultivariantPlaylist.Variant variant4 = getVariantWithSubtitleGroup(variants, groupId);
                            if (variant4 == null) {
                                muxedCaptionFormats = muxedCaptionFormats5;
                            } else {
                                muxedCaptionFormats = muxedCaptionFormats5;
                                String codecs5 = Util.getCodecsOfType(variant4.format.codecs, 3);
                                formatBuilder.setCodecs(codecs5);
                                sampleMimeType2 = MimeTypes.getMediaMimeType(codecs5);
                            }
                            if (sampleMimeType2 == null) {
                                sampleMimeType2 = MimeTypes.TEXT_VTT;
                            }
                            formatBuilder.setSampleMimeType(sampleMimeType2).setMetadata(metadata);
                            if (uri2 != null) {
                                subtitles3.add(new HlsMultivariantPlaylist.Rendition(uri2, formatBuilder.build(), groupId, name));
                                videos = videos4;
                                audios = audios4;
                            } else {
                                Log.w(LOG_TAG, "EXT-X-MEDIA tag with missing mandatory URI attribute: skipping");
                                videos = videos4;
                                audios = audios4;
                            }
                            muxedCaptionFormats5 = muxedCaptionFormats;
                            break;
                        case 3:
                            String instreamId = parseStringAttr(line2, REGEX_INSTREAM_ID, variableDefinitions);
                            if (instreamId.startsWith("CC")) {
                                sampleMimeType = MimeTypes.APPLICATION_CEA608;
                                accessibilityChannel = Integer.parseInt(instreamId.substring(2));
                            } else {
                                sampleMimeType = MimeTypes.APPLICATION_CEA708;
                                accessibilityChannel = Integer.parseInt(instreamId.substring(7));
                            }
                            if (muxedCaptionFormats5 == null) {
                                muxedCaptionFormats5 = new ArrayList<>();
                            }
                            formatBuilder.setSampleMimeType(sampleMimeType).setAccessibilityChannel(accessibilityChannel);
                            muxedCaptionFormats5.add(formatBuilder.build());
                            videos = videos4;
                            audios = audios4;
                            break;
                        default:
                            muxedCaptionFormats = muxedCaptionFormats5;
                            videos = videos4;
                            audios = audios4;
                            muxedCaptionFormats5 = muxedCaptionFormats;
                            break;
                    }
                    i2++;
                    audios4 = audios;
                    videos4 = videos;
                    str2 = str3;
                    urlsInDeduplicatedVariants = urlsInDeduplicatedVariants2;
                }
                List<Format> muxedCaptionFormats6 = muxedCaptionFormats5;
                ArrayList<HlsMultivariantPlaylist.Rendition> videos5 = videos4;
                ArrayList<HlsMultivariantPlaylist.Rendition> audios5 = audios4;
                if (noClosedCaptions) {
                    muxedCaptionFormats6 = Collections.emptyList();
                }
                return new HlsMultivariantPlaylist(baseUri, tags3, deduplicatedVariants, videos5, audios5, subtitles3, closedCaptions3, muxedAudioFormat3, muxedCaptionFormats6, hasIndependentSegmentsTag, variableDefinitions, sessionKeyDrmInitData3);
            }
        }
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMultivariantPlaylist.Variant> variants, String groupId) {
        for (int i = 0; i < variants.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = variants.get(i);
            if (groupId.equals(variant.audioGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMultivariantPlaylist.Variant> variants, String groupId) {
        for (int i = 0; i < variants.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = variants.get(i);
            if (groupId.equals(variant.videoGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithSubtitleGroup(ArrayList<HlsMultivariantPlaylist.Variant> variants, String groupId) {
        for (int i = 0; i < variants.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = variants.get(i);
            if (groupId.equals(variant.subtitleGroupId)) {
                return variant;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0376, code lost:
    
        throw new androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.DeltaUpdateException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist parseMediaPlaylist(androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist r86, androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist r87, androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.LineIterator r88, java.lang.String r89) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser.parseMediaPlaylist(androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist, androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist");
    }

    private static DrmInitData getPlaylistProtectionSchemes(String encryptionScheme, DrmInitData.SchemeData[] schemeDatas) {
        DrmInitData.SchemeData[] playlistSchemeDatas = new DrmInitData.SchemeData[schemeDatas.length];
        for (int i = 0; i < schemeDatas.length; i++) {
            playlistSchemeDatas[i] = schemeDatas[i].copyWithData(null);
        }
        return new DrmInitData(encryptionScheme, playlistSchemeDatas);
    }

    private static String getSegmentEncryptionIV(long segmentMediaSequence, String fullSegmentEncryptionKeyUri, String fullSegmentEncryptionIV) {
        if (fullSegmentEncryptionKeyUri == null) {
            return null;
        }
        if (fullSegmentEncryptionIV != null) {
            return fullSegmentEncryptionIV;
        }
        return Long.toHexString(segmentMediaSequence);
    }

    private static int parseSelectionFlags(String line) {
        int flags = 0;
        if (parseOptionalBooleanAttribute(line, REGEX_DEFAULT, false)) {
            flags = 0 | 1;
        }
        if (parseOptionalBooleanAttribute(line, REGEX_FORCED, false)) {
            flags |= 2;
        }
        if (parseOptionalBooleanAttribute(line, REGEX_AUTOSELECT, false)) {
            return flags | 4;
        }
        return flags;
    }

    private static int parseRoleFlags(String line, Map<String, String> variableDefinitions) {
        String concatenatedCharacteristics = parseOptionalStringAttr(line, REGEX_CHARACTERISTICS, variableDefinitions);
        if (TextUtils.isEmpty(concatenatedCharacteristics)) {
            return 0;
        }
        String[] characteristics = Util.split(concatenatedCharacteristics, ",");
        int roleFlags = 0;
        if (Util.contains(characteristics, "public.accessibility.describes-video")) {
            roleFlags = 0 | 512;
        }
        if (Util.contains(characteristics, "public.accessibility.transcribes-spoken-dialog")) {
            roleFlags |= 4096;
        }
        if (Util.contains(characteristics, "public.accessibility.describes-music-and-sound")) {
            roleFlags |= 1024;
        }
        if (Util.contains(characteristics, "public.easy-to-read")) {
            return roleFlags | 8192;
        }
        return roleFlags;
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String line, String keyFormat, Map<String, String> variableDefinitions) throws ParserException {
        String keyFormatVersions = parseOptionalStringAttr(line, REGEX_KEYFORMATVERSIONS, "1", variableDefinitions);
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(keyFormat)) {
            String uriString = parseStringAttr(line, REGEX_URI, variableDefinitions);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, MimeTypes.VIDEO_MP4, Base64.decode(uriString.substring(uriString.indexOf(44)), 0));
        }
        if (KEYFORMAT_WIDEVINE_PSSH_JSON.equals(keyFormat)) {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(line));
        }
        if (KEYFORMAT_PLAYREADY.equals(keyFormat) && "1".equals(keyFormatVersions)) {
            String uriString2 = parseStringAttr(line, REGEX_URI, variableDefinitions);
            byte[] data = Base64.decode(uriString2.substring(uriString2.indexOf(44)), 0);
            byte[] psshData = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, data);
            return new DrmInitData.SchemeData(C.PLAYREADY_UUID, MimeTypes.VIDEO_MP4, psshData);
        }
        return null;
    }

    private static HlsMediaPlaylist.ServerControl parseServerControl(String line) {
        long skipUntilUs;
        long holdBackUs;
        long partHoldBackUs;
        double skipUntilSeconds = parseOptionalDoubleAttr(line, REGEX_CAN_SKIP_UNTIL, -9.223372036854776E18d);
        if (skipUntilSeconds == -9.223372036854776E18d) {
            skipUntilUs = -9223372036854775807L;
        } else {
            skipUntilUs = (long) (skipUntilSeconds * 1000000.0d);
        }
        boolean canSkipDateRanges = parseOptionalBooleanAttribute(line, REGEX_CAN_SKIP_DATE_RANGES, false);
        double holdBackSeconds = parseOptionalDoubleAttr(line, REGEX_HOLD_BACK, -9.223372036854776E18d);
        if (holdBackSeconds == -9.223372036854776E18d) {
            holdBackUs = -9223372036854775807L;
        } else {
            holdBackUs = (long) (holdBackSeconds * 1000000.0d);
        }
        double partHoldBackSeconds = parseOptionalDoubleAttr(line, REGEX_PART_HOLD_BACK, -9.223372036854776E18d);
        if (partHoldBackSeconds == -9.223372036854776E18d) {
            partHoldBackUs = -9223372036854775807L;
        } else {
            partHoldBackUs = (long) (1000000.0d * partHoldBackSeconds);
        }
        boolean canBlockReload = parseOptionalBooleanAttribute(line, REGEX_CAN_BLOCK_RELOAD, false);
        return new HlsMediaPlaylist.ServerControl(skipUntilUs, canSkipDateRanges, holdBackUs, partHoldBackUs, canBlockReload);
    }

    private static String parseEncryptionScheme(String method) {
        if (METHOD_SAMPLE_AES_CENC.equals(method) || METHOD_SAMPLE_AES_CTR.equals(method)) {
            return C.CENC_TYPE_cenc;
        }
        return C.CENC_TYPE_cbcs;
    }

    private static int parseIntAttr(String line, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(line, pattern, Collections.emptyMap()));
    }

    private static int parseOptionalIntAttr(String line, Pattern pattern, int defaultValue) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)));
        }
        return defaultValue;
    }

    private static long parseLongAttr(String line, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(line, pattern, Collections.emptyMap()));
    }

    private static long parseOptionalLongAttr(String line, Pattern pattern, long defaultValue) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Long.parseLong((String) Assertions.checkNotNull(matcher.group(1)));
        }
        return defaultValue;
    }

    private static long parseTimeSecondsToUs(String line, Pattern pattern) throws ParserException {
        String timeValueSeconds = parseStringAttr(line, pattern, Collections.emptyMap());
        BigDecimal timeValue = new BigDecimal(timeValueSeconds);
        return timeValue.multiply(new BigDecimal(1000000L)).longValue();
    }

    private static double parseDoubleAttr(String line, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(line, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String line, Pattern pattern, Map<String, String> variableDefinitions) throws ParserException {
        String value = parseOptionalStringAttr(line, pattern, variableDefinitions);
        if (value != null) {
            return value;
        }
        throw ParserException.createForMalformedManifest("Couldn't match " + pattern.pattern() + " in " + line, null);
    }

    private static String parseOptionalStringAttr(String line, Pattern pattern, Map<String, String> variableDefinitions) {
        return parseOptionalStringAttr(line, pattern, null, variableDefinitions);
    }

    private static String parseOptionalStringAttr(String line, Pattern pattern, String defaultValue, Map<String, String> variableDefinitions) {
        Matcher matcher = pattern.matcher(line);
        String value = matcher.find() ? (String) Assertions.checkNotNull(matcher.group(1)) : defaultValue;
        if (variableDefinitions.isEmpty() || value == null) {
            return value;
        }
        return replaceVariableReferences(value, variableDefinitions);
    }

    private static double parseOptionalDoubleAttr(String line, Pattern pattern, double defaultValue) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Double.parseDouble((String) Assertions.checkNotNull(matcher.group(1)));
        }
        return defaultValue;
    }

    private static String replaceVariableReferences(String string, Map<String, String> variableDefinitions) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(string);
        StringBuffer stringWithReplacements = new StringBuffer();
        while (matcher.find()) {
            String groupName = matcher.group(1);
            if (variableDefinitions.containsKey(groupName)) {
                matcher.appendReplacement(stringWithReplacements, Matcher.quoteReplacement(variableDefinitions.get(groupName)));
            }
        }
        matcher.appendTail(stringWithReplacements);
        return stringWithReplacements.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String line, Pattern pattern, boolean defaultValue) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return BOOLEAN_TRUE.equals(matcher.group(1));
        }
        return defaultValue;
    }

    private static Pattern compileBooleanAttrPattern(String attribute) {
        return Pattern.compile(attribute + "=(" + BOOLEAN_FALSE + "|" + BOOLEAN_TRUE + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> extraLines, BufferedReader reader) {
            this.extraLines = extraLines;
            this.reader = reader;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() throws IOException {
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine != null) {
                    this.next = this.next.trim();
                } else {
                    return false;
                }
            } while (this.next.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (hasNext()) {
                String result = this.next;
                this.next = null;
                return result;
            }
            throw new NoSuchElementException();
        }
    }
}
