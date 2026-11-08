package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.view.MotionEventCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes5.dex */
public final class MediaCodecUtil {
    private static final String CODEC_ID_AV01 = "av01";
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final String CODEC_ID_MP4A = "mp4a";
    private static final String CODEC_ID_VP09 = "vp09";
    private static final String TAG = "MediaCodecUtil";
    private static final Pattern PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<CodecKey, List<MediaCodecInfo>> decoderInfosCache = new HashMap<>();
    private static int maxH264DecodableFrameSize = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface MediaCodecListCompat {
        int getCodecCount();

        android.media.MediaCodecInfo getCodecInfoAt(int i);

        boolean isFeatureRequired(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean isFeatureSupported(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities);

        boolean secureDecodersExplicit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface ScoreProvider<T> {
        int getScore(T t);
    }

    /* loaded from: classes5.dex */
    public static class DecoderQueryException extends Exception {
        private DecoderQueryException(Throwable cause) {
            super("Failed to query underlying media codecs", cause);
        }
    }

    private MediaCodecUtil() {
    }

    public static void warmDecoderInfoCache(String mimeType, boolean secure, boolean tunneling) {
        try {
            getDecoderInfos(mimeType, secure, tunneling);
        } catch (DecoderQueryException e) {
            Log.e(TAG, "Codec warming failed", e);
        }
    }

    public static synchronized void clearDecoderInfoCache() {
        synchronized (MediaCodecUtil.class) {
            decoderInfosCache.clear();
        }
    }

    public static MediaCodecInfo getDecryptOnlyDecoderInfo() throws DecoderQueryException {
        return getDecoderInfo(MimeTypes.AUDIO_RAW, false, false);
    }

    public static MediaCodecInfo getDecoderInfo(String mimeType, boolean secure, boolean tunneling) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mimeType, secure, tunneling);
        if (decoderInfos.isEmpty()) {
            return null;
        }
        return decoderInfos.get(0);
    }

    public static synchronized List<MediaCodecInfo> getDecoderInfos(String mimeType, boolean secure, boolean tunneling) throws DecoderQueryException {
        MediaCodecListCompat mediaCodecList;
        synchronized (MediaCodecUtil.class) {
            CodecKey key = new CodecKey(mimeType, secure, tunneling);
            List<MediaCodecInfo> cachedDecoderInfos = decoderInfosCache.get(key);
            if (cachedDecoderInfos != null) {
                return cachedDecoderInfos;
            }
            if (Util.SDK_INT >= 21) {
                mediaCodecList = new MediaCodecListCompatV21(secure, tunneling);
            } else {
                mediaCodecList = new MediaCodecListCompatV16();
            }
            ArrayList<MediaCodecInfo> decoderInfos = getDecoderInfosInternal(key, mediaCodecList);
            if (secure && decoderInfos.isEmpty() && 21 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                MediaCodecListCompat mediaCodecList2 = new MediaCodecListCompatV16();
                decoderInfos = getDecoderInfosInternal(key, mediaCodecList2);
                if (!decoderInfos.isEmpty()) {
                    Log.w(TAG, "MediaCodecList API didn't list secure decoder for: " + mimeType + ". Assuming: " + decoderInfos.get(0).name);
                }
            }
            applyWorkarounds(mimeType, decoderInfos);
            ImmutableList<MediaCodecInfo> immutableDecoderInfos = ImmutableList.copyOf((Collection) decoderInfos);
            decoderInfosCache.put(key, immutableDecoderInfos);
            return immutableDecoderInfos;
        }
    }

    @RequiresNonNull({"#2.sampleMimeType"})
    public static List<MediaCodecInfo> getDecoderInfosSoftMatch(MediaCodecSelector mediaCodecSelector, Format format, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, requiresSecureDecoder, requiresTunnelingDecoder);
        List<MediaCodecInfo> alternativeDecoderInfos = getAlternativeDecoderInfos(mediaCodecSelector, format, requiresSecureDecoder, requiresTunnelingDecoder);
        return ImmutableList.builder().addAll((Iterable) decoderInfos).addAll((Iterable) alternativeDecoderInfos).build();
    }

    public static List<MediaCodecInfo> getAlternativeDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean requiresSecureDecoder, boolean requiresTunnelingDecoder) throws DecoderQueryException {
        String alternativeMimeType = getAlternativeCodecMimeType(format);
        if (alternativeMimeType == null) {
            return ImmutableList.of();
        }
        return mediaCodecSelector.getDecoderInfos(alternativeMimeType, requiresSecureDecoder, requiresTunnelingDecoder);
    }

    public static List<MediaCodecInfo> getDecoderInfosSortedByFormatSupport(List<MediaCodecInfo> decoderInfos, final Format format) {
        List<MediaCodecInfo> decoderInfos2 = new ArrayList<>(decoderInfos);
        sortByScore(decoderInfos2, new ScoreProvider() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda3
            @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
            public final int getScore(Object obj) {
                return MediaCodecUtil.lambda$getDecoderInfosSortedByFormatSupport$0(Format.this, (MediaCodecInfo) obj);
            }
        });
        return decoderInfos2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDecoderInfosSortedByFormatSupport$0(Format format, MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.isFormatFunctionallySupported(format) ? 1 : 0;
    }

    public static int maxH264DecodableFrameSize() throws DecoderQueryException {
        if (maxH264DecodableFrameSize == -1) {
            int result = 0;
            MediaCodecInfo decoderInfo = getDecoderInfo(MimeTypes.VIDEO_H264, false, false);
            if (decoderInfo != null) {
                for (MediaCodecInfo.CodecProfileLevel profileLevel : decoderInfo.getProfileLevels()) {
                    result = Math.max(avcLevelToMaxFrameSize(profileLevel.level), result);
                }
                result = Math.max(result, Util.SDK_INT >= 21 ? 345600 : 172800);
            }
            maxH264DecodableFrameSize = result;
        }
        return maxH264DecodableFrameSize;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0062, code lost:
    
        if (r3.equals(androidx.media3.exoplayer.mediacodec.MediaCodecUtil.CODEC_ID_AVC1) != false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> getCodecProfileAndLevel(androidx.media3.common.Format r5) {
        /*
            java.lang.String r0 = r5.codecs
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            java.lang.String r0 = r5.codecs
            java.lang.String r2 = "\\."
            java.lang.String[] r0 = r0.split(r2)
            java.lang.String r2 = "video/dolby-vision"
            java.lang.String r3 = r5.sampleMimeType
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L1f
            java.lang.String r1 = r5.codecs
            android.util.Pair r1 = getDolbyVisionProfileAndLevel(r1, r0)
            return r1
        L1f:
            r2 = 0
            r3 = r0[r2]
            int r4 = r3.hashCode()
            switch(r4) {
                case 3004662: goto L65;
                case 3006243: goto L5c;
                case 3006244: goto L52;
                case 3199032: goto L48;
                case 3214780: goto L3e;
                case 3356560: goto L34;
                case 3624515: goto L2a;
                default: goto L29;
            }
        L29:
            goto L6f
        L2a:
            java.lang.String r2 = "vp09"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 2
            goto L70
        L34:
            java.lang.String r2 = "mp4a"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 6
            goto L70
        L3e:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 4
            goto L70
        L48:
            java.lang.String r2 = "hev1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 3
            goto L70
        L52:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 1
            goto L70
        L5c:
            java.lang.String r4 = "avc1"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L29
            goto L70
        L65:
            java.lang.String r2 = "av01"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L29
            r2 = 5
            goto L70
        L6f:
            r2 = -1
        L70:
            switch(r2) {
                case 0: goto L94;
                case 1: goto L94;
                case 2: goto L8d;
                case 3: goto L84;
                case 4: goto L84;
                case 5: goto L7b;
                case 6: goto L74;
                default: goto L73;
            }
        L73:
            return r1
        L74:
            java.lang.String r1 = r5.codecs
            android.util.Pair r1 = getAacCodecProfileAndLevel(r1, r0)
            return r1
        L7b:
            java.lang.String r1 = r5.codecs
            androidx.media3.common.ColorInfo r2 = r5.colorInfo
            android.util.Pair r1 = getAv1ProfileAndLevel(r1, r0, r2)
            return r1
        L84:
            java.lang.String r1 = r5.codecs
            androidx.media3.common.ColorInfo r2 = r5.colorInfo
            android.util.Pair r1 = getHevcProfileAndLevel(r1, r0, r2)
            return r1
        L8d:
            java.lang.String r1 = r5.codecs
            android.util.Pair r1 = getVp9ProfileAndLevel(r1, r0)
            return r1
        L94:
            java.lang.String r1 = r5.codecs
            android.util.Pair r1 = getAvcProfileAndLevel(r1, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(androidx.media3.common.Format):android.util.Pair");
    }

    public static String getAlternativeCodecMimeType(Format format) {
        Pair<Integer, Integer> codecProfileAndLevel;
        if (MimeTypes.AUDIO_E_AC3_JOC.equals(format.sampleMimeType)) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = getCodecProfileAndLevel(format)) != null) {
            int profile = ((Integer) codecProfileAndLevel.first).intValue();
            if (profile == 16 || profile == 256) {
                return MimeTypes.VIDEO_H265;
            }
            if (profile == 512) {
                return MimeTypes.VIDEO_H264;
            }
            if (profile == 1024) {
                return MimeTypes.VIDEO_AV1;
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0169, code lost:
    
        androidx.media3.common.util.Log.e(androidx.media3.exoplayer.mediacodec.MediaCodecUtil.TAG, "Skipping codec " + r7 + " (failed to query capabilities)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00c5, code lost:
    
        if (r31.secure == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00c8, code lost:
    
        r25 = r0;
        r28 = r9;
        r29 = r13;
        r2 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00e1, code lost:
    
        r0.add(androidx.media3.exoplayer.mediacodec.MediaCodecInfo.newInstance(r7, r15, r25, r0, r10, r11, r16, false, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00e8, code lost:
    
        r6 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00ed, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00ee, code lost:
    
        r6 = r15;
        r7 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00b0, code lost:
    
        if (r31.secure != r10) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0163 A[Catch: Exception -> 0x01ba, TRY_ENTER, TryCatch #2 {Exception -> 0x01ba, blocks: (B:3:0x0008, B:5:0x001f, B:9:0x0185, B:10:0x0032, B:14:0x0045, B:56:0x015b, B:59:0x0163, B:61:0x0169, B:64:0x018f, B:65:0x01b8), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018f A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.ArrayList<androidx.media3.exoplayer.mediacodec.MediaCodecInfo> getDecoderInfosInternal(androidx.media3.exoplayer.mediacodec.MediaCodecUtil.CodecKey r31, androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat r32) throws androidx.media3.exoplayer.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            Method dump skipped, instructions count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getDecoderInfosInternal(androidx.media3.exoplayer.mediacodec.MediaCodecUtil$CodecKey, androidx.media3.exoplayer.mediacodec.MediaCodecUtil$MediaCodecListCompat):java.util.ArrayList");
    }

    private static String getCodecMimeType(android.media.MediaCodecInfo info, String name, String mimeType) {
        String[] supportedTypes = info.getSupportedTypes();
        for (String supportedType : supportedTypes) {
            if (supportedType.equalsIgnoreCase(mimeType)) {
                return supportedType;
            }
        }
        if (mimeType.equals(MimeTypes.VIDEO_DOLBY_VISION)) {
            if ("OMX.MS.HEVCDV.Decoder".equals(name)) {
                return "video/hevcdv";
            }
            if ("OMX.RTK.video.decoder".equals(name) || "OMX.realtek.video.decoder.tunneled".equals(name)) {
                return "video/dv_hevc";
            }
            return null;
        }
        if (mimeType.equals(MimeTypes.AUDIO_ALAC) && "OMX.lge.alac.decoder".equals(name)) {
            return "audio/x-lg-alac";
        }
        if (mimeType.equals(MimeTypes.AUDIO_FLAC) && "OMX.lge.flac.decoder".equals(name)) {
            return "audio/x-lg-flac";
        }
        if (mimeType.equals(MimeTypes.AUDIO_AC3) && "OMX.lge.ac3.decoder".equals(name)) {
            return "audio/lg-ac3";
        }
        return null;
    }

    private static boolean isCodecUsableDecoder(android.media.MediaCodecInfo info, String name, boolean secureDecodersExplicit, String mimeType) {
        if (info.isEncoder() || (!secureDecodersExplicit && name.endsWith(".secure"))) {
            return false;
        }
        if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(name) || "CIPMP3Decoder".equals(name) || "CIPVorbisDecoder".equals(name) || "CIPAMRNBDecoder".equals(name) || "AACDecoder".equals(name) || "MP3Decoder".equals(name))) {
            return false;
        }
        if (Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(name) || "OMX.Exynos.AAC.Decoder".equals(name)) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || "SC-05G".equals(Util.DEVICE) || "marinelteatt".equals(Util.DEVICE) || "404SC".equals(Util.DEVICE) || "SC-04G".equals(Util.DEVICE) || "SCV31".equals(Util.DEVICE)))) {
            return false;
        }
        if (Util.SDK_INT == 19 && "OMX.SEC.vp8.dec".equals(name) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
            return false;
        }
        if (Util.SDK_INT == 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(name)) {
            return false;
        }
        return (Util.SDK_INT <= 23 && MimeTypes.AUDIO_E_AC3_JOC.equals(mimeType) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(name)) ? false : true;
    }

    private static void applyWorkarounds(String mimeType, List<MediaCodecInfo> decoderInfos) {
        if (MimeTypes.AUDIO_RAW.equals(mimeType)) {
            if (Util.SDK_INT < 26 && Util.DEVICE.equals("R9") && decoderInfos.size() == 1 && decoderInfos.get(0).name.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                decoderInfos.add(MediaCodecInfo.newInstance("OMX.google.raw.decoder", MimeTypes.AUDIO_RAW, MimeTypes.AUDIO_RAW, null, false, true, false, false, false));
            }
            sortByScore(decoderInfos, new ScoreProvider() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda1
                @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
                public final int getScore(Object obj) {
                    return MediaCodecUtil.lambda$applyWorkarounds$1((MediaCodecInfo) obj);
                }
            });
        }
        if (Util.SDK_INT < 21 && decoderInfos.size() > 1) {
            String firstCodecName = decoderInfos.get(0).name;
            if ("OMX.SEC.mp3.dec".equals(firstCodecName) || "OMX.SEC.MP3.Decoder".equals(firstCodecName) || "OMX.brcm.audio.mp3.decoder".equals(firstCodecName)) {
                sortByScore(decoderInfos, new ScoreProvider() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda2
                    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.ScoreProvider
                    public final int getScore(Object obj) {
                        return MediaCodecUtil.lambda$applyWorkarounds$2((MediaCodecInfo) obj);
                    }
                });
            }
        }
        if (Util.SDK_INT < 32 && decoderInfos.size() > 1 && "OMX.qti.audio.decoder.flac".equals(decoderInfos.get(0).name)) {
            decoderInfos.add(decoderInfos.remove(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$applyWorkarounds$1(MediaCodecInfo decoderInfo) {
        String name = decoderInfo.name;
        if (name.startsWith("OMX.google") || name.startsWith("c2.android")) {
            return 1;
        }
        if (Util.SDK_INT < 26 && name.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
            return -1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$applyWorkarounds$2(MediaCodecInfo mediaCodecInfo) {
        return mediaCodecInfo.name.startsWith("OMX.google") ? 1 : 0;
    }

    private static boolean isAlias(android.media.MediaCodecInfo info) {
        return Util.SDK_INT >= 29 && isAliasV29(info);
    }

    private static boolean isAliasV29(android.media.MediaCodecInfo info) {
        return info.isAlias();
    }

    private static boolean isHardwareAccelerated(android.media.MediaCodecInfo codecInfo, String mimeType) {
        if (Util.SDK_INT >= 29) {
            return isHardwareAcceleratedV29(codecInfo);
        }
        return !isSoftwareOnly(codecInfo, mimeType);
    }

    private static boolean isHardwareAcceleratedV29(android.media.MediaCodecInfo codecInfo) {
        return codecInfo.isHardwareAccelerated();
    }

    private static boolean isSoftwareOnly(android.media.MediaCodecInfo codecInfo, String mimeType) {
        if (Util.SDK_INT >= 29) {
            return isSoftwareOnlyV29(codecInfo);
        }
        if (MimeTypes.isAudio(mimeType)) {
            return true;
        }
        String codecName = Ascii.toLowerCase(codecInfo.getName());
        if (codecName.startsWith("arc.")) {
            return false;
        }
        if (codecName.startsWith("omx.google.") || codecName.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((codecName.startsWith("omx.sec.") && codecName.contains(".sw.")) || codecName.equals("omx.qcom.video.decoder.hevcswvdec") || codecName.startsWith("c2.android.") || codecName.startsWith("c2.google.")) {
            return true;
        }
        return (codecName.startsWith("omx.") || codecName.startsWith("c2.")) ? false : true;
    }

    private static boolean isSoftwareOnlyV29(android.media.MediaCodecInfo codecInfo) {
        return codecInfo.isSoftwareOnly();
    }

    private static boolean isVendor(android.media.MediaCodecInfo codecInfo) {
        if (Util.SDK_INT >= 29) {
            return isVendorV29(codecInfo);
        }
        String codecName = Ascii.toLowerCase(codecInfo.getName());
        return (codecName.startsWith("omx.google.") || codecName.startsWith("c2.android.") || codecName.startsWith("c2.google.")) ? false : true;
    }

    private static boolean isVendorV29(android.media.MediaCodecInfo codecInfo) {
        return codecInfo.isVendor();
    }

    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String codec, String[] parts) {
        if (parts.length < 3) {
            Log.w(TAG, "Ignoring malformed Dolby Vision codec string: " + codec);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(parts[1]);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed Dolby Vision codec string: " + codec);
            return null;
        }
        String profileString = matcher.group(1);
        Integer profile = dolbyVisionStringToProfile(profileString);
        if (profile == null) {
            Log.w(TAG, "Unknown Dolby Vision profile string: " + profileString);
            return null;
        }
        String levelString = parts[2];
        Integer level = dolbyVisionStringToLevel(levelString);
        if (level == null) {
            Log.w(TAG, "Unknown Dolby Vision level string: " + levelString);
            return null;
        }
        return new Pair<>(profile, level);
    }

    private static Pair<Integer, Integer> getHevcProfileAndLevel(String codec, String[] parts, ColorInfo colorInfo) {
        int profile;
        if (parts.length < 4) {
            Log.w(TAG, "Ignoring malformed HEVC codec string: " + codec);
            return null;
        }
        Matcher matcher = PROFILE_PATTERN.matcher(parts[1]);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed HEVC codec string: " + codec);
            return null;
        }
        String profileString = matcher.group(1);
        if ("1".equals(profileString)) {
            profile = 1;
        } else if (ExifInterface.GPS_MEASUREMENT_2D.equals(profileString)) {
            if (colorInfo != null && colorInfo.colorTransfer == 6) {
                profile = 4096;
            } else {
                profile = 2;
            }
        } else {
            Log.w(TAG, "Unknown HEVC profile string: " + profileString);
            return null;
        }
        String levelString = parts[3];
        Integer level = hevcCodecStringToProfileLevel(levelString);
        if (level == null) {
            Log.w(TAG, "Unknown HEVC level string: " + levelString);
            return null;
        }
        return new Pair<>(Integer.valueOf(profile), level);
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String codec, String[] parts) {
        int profileInteger;
        int profileInteger2;
        if (parts.length < 2) {
            Log.w(TAG, "Ignoring malformed AVC codec string: " + codec);
            return null;
        }
        try {
            if (parts[1].length() == 6) {
                profileInteger = Integer.parseInt(parts[1].substring(0, 2), 16);
                profileInteger2 = Integer.parseInt(parts[1].substring(4), 16);
            } else if (parts.length >= 3) {
                int profileInteger3 = Integer.parseInt(parts[1]);
                profileInteger = profileInteger3;
                profileInteger2 = Integer.parseInt(parts[2]);
            } else {
                Log.w(TAG, "Ignoring malformed AVC codec string: " + codec);
                return null;
            }
            int profile = avcProfileNumberToConst(profileInteger);
            if (profile == -1) {
                Log.w(TAG, "Unknown AVC profile: " + profileInteger);
                return null;
            }
            int level = avcLevelNumberToConst(profileInteger2);
            if (level == -1) {
                Log.w(TAG, "Unknown AVC level: " + profileInteger2);
                return null;
            }
            return new Pair<>(Integer.valueOf(profile), Integer.valueOf(level));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed AVC codec string: " + codec);
            return null;
        }
    }

    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String codec, String[] parts) {
        if (parts.length < 3) {
            Log.w(TAG, "Ignoring malformed VP9 codec string: " + codec);
            return null;
        }
        try {
            int profileInteger = Integer.parseInt(parts[1]);
            int levelInteger = Integer.parseInt(parts[2]);
            int profile = vp9ProfileNumberToConst(profileInteger);
            if (profile == -1) {
                Log.w(TAG, "Unknown VP9 profile: " + profileInteger);
                return null;
            }
            int level = vp9LevelNumberToConst(levelInteger);
            if (level == -1) {
                Log.w(TAG, "Unknown VP9 level: " + levelInteger);
                return null;
            }
            return new Pair<>(Integer.valueOf(profile), Integer.valueOf(level));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed VP9 codec string: " + codec);
            return null;
        }
    }

    private static Pair<Integer, Integer> getAv1ProfileAndLevel(String codec, String[] parts, ColorInfo colorInfo) {
        int profile;
        if (parts.length < 4) {
            Log.w(TAG, "Ignoring malformed AV1 codec string: " + codec);
            return null;
        }
        try {
            int profileInteger = Integer.parseInt(parts[1]);
            int levelInteger = Integer.parseInt(parts[2].substring(0, 2));
            int bitDepthInteger = Integer.parseInt(parts[3]);
            if (profileInteger != 0) {
                Log.w(TAG, "Unknown AV1 profile: " + profileInteger);
                return null;
            }
            if (bitDepthInteger != 8 && bitDepthInteger != 10) {
                Log.w(TAG, "Unknown AV1 bit depth: " + bitDepthInteger);
                return null;
            }
            if (bitDepthInteger == 8) {
                profile = 1;
            } else if (colorInfo != null && (colorInfo.hdrStaticInfo != null || colorInfo.colorTransfer == 7 || colorInfo.colorTransfer == 6)) {
                profile = 4096;
            } else {
                profile = 2;
            }
            int level = av1LevelNumberToConst(levelInteger);
            if (level == -1) {
                Log.w(TAG, "Unknown AV1 level: " + levelInteger);
                return null;
            }
            return new Pair<>(Integer.valueOf(profile), Integer.valueOf(level));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed AV1 codec string: " + codec);
            return null;
        }
    }

    private static int avcLevelToMaxFrameSize(int avcLevel) {
        switch (avcLevel) {
            case 1:
            case 2:
                return 25344;
            case 8:
            case 16:
            case 32:
                return 101376;
            case 64:
                return 202752;
            case 128:
            case 256:
                return 414720;
            case 512:
                return 921600;
            case 1024:
                return 1310720;
            case 2048:
            case 4096:
                return 2097152;
            case 8192:
                return 2228224;
            case 16384:
                return 5652480;
            case 32768:
            case 65536:
                return 9437184;
            case 131072:
            case 262144:
            case 524288:
                return 35651584;
            default:
                return -1;
        }
    }

    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String codec, String[] parts) {
        if (parts.length != 3) {
            Log.w(TAG, "Ignoring malformed MP4A codec string: " + codec);
            return null;
        }
        try {
            int objectTypeIndication = Integer.parseInt(parts[1], 16);
            String mimeType = MimeTypes.getMimeTypeFromMp4ObjectType(objectTypeIndication);
            if (MimeTypes.AUDIO_AAC.equals(mimeType)) {
                int audioObjectTypeIndication = Integer.parseInt(parts[2]);
                int profile = mp4aAudioObjectTypeToProfile(audioObjectTypeIndication);
                if (profile != -1) {
                    return new Pair<>(Integer.valueOf(profile), 0);
                }
            }
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed MP4A codec string: " + codec);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$sortByScore$3(ScoreProvider scoreProvider, Object a, Object b) {
        return scoreProvider.getScore(b) - scoreProvider.getScore(a);
    }

    private static <T> void sortByScore(List<T> list, final ScoreProvider<T> scoreProvider) {
        Collections.sort(list, new Comparator() { // from class: androidx.media3.exoplayer.mediacodec.MediaCodecUtil$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MediaCodecUtil.lambda$sortByScore$3(MediaCodecUtil.ScoreProvider.this, obj, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private android.media.MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean includeSecure, boolean includeTunneling) {
            int i;
            if (includeSecure || includeTunneling) {
                i = 1;
            } else {
                i = 0;
            }
            this.codecKind = i;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public android.media.MediaCodecInfo getCodecInfoAt(int index) {
            ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[index];
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return true;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureSupported(String feature, String mimeType, MediaCodecInfo.CodecCapabilities capabilities) {
            return capabilities.isFeatureSupported(feature);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureRequired(String feature, String mimeType, MediaCodecInfo.CodecCapabilities capabilities) {
            return capabilities.isFeatureRequired(feature);
        }

        @EnsuresNonNull({"mediaCodecInfos"})
        private void ensureMediaCodecInfosInitialized() {
            if (this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        private MediaCodecListCompatV16() {
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public android.media.MediaCodecInfo getCodecInfoAt(int index) {
            return MediaCodecList.getCodecInfoAt(index);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean secureDecodersExplicit() {
            return false;
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureSupported(String feature, String mimeType, MediaCodecInfo.CodecCapabilities capabilities) {
            return "secure-playback".equals(feature) && MimeTypes.VIDEO_H264.equals(mimeType);
        }

        @Override // androidx.media3.exoplayer.mediacodec.MediaCodecUtil.MediaCodecListCompat
        public boolean isFeatureRequired(String feature, String mimeType, MediaCodecInfo.CodecCapabilities capabilities) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class CodecKey {
        public final String mimeType;
        public final boolean secure;
        public final boolean tunneling;

        public CodecKey(String mimeType, boolean secure, boolean tunneling) {
            this.mimeType = mimeType;
            this.secure = secure;
            this.tunneling = tunneling;
        }

        public int hashCode() {
            int result = (1 * 31) + this.mimeType.hashCode();
            return (((result * 31) + (this.secure ? 1231 : 1237)) * 31) + (this.tunneling ? 1231 : 1237);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != CodecKey.class) {
                return false;
            }
            CodecKey other = (CodecKey) obj;
            if (TextUtils.equals(this.mimeType, other.mimeType) && this.secure == other.secure && this.tunneling == other.tunneling) {
                return true;
            }
            return false;
        }
    }

    private static int avcProfileNumberToConst(int profileNumber) {
        switch (profileNumber) {
            case 66:
                return 1;
            case 77:
                return 2;
            case 88:
                return 4;
            case 100:
                return 8;
            case 110:
                return 16;
            case 122:
                return 32;
            case 244:
                return 64;
            default:
                return -1;
        }
    }

    private static int avcLevelNumberToConst(int levelNumber) {
        switch (levelNumber) {
            case 10:
                return 1;
            case 11:
                return 4;
            case 12:
                return 8;
            case 13:
                return 16;
            case 20:
                return 32;
            case 21:
                return 64;
            case 22:
                return 128;
            case 30:
                return 256;
            case 31:
                return 512;
            case 32:
                return 1024;
            case 40:
                return 2048;
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                return 4096;
            case 42:
                return 8192;
            case 50:
                return 16384;
            case 51:
                return 32768;
            case 52:
                return 65536;
            default:
                return -1;
        }
    }

    private static int vp9ProfileNumberToConst(int profileNumber) {
        switch (profileNumber) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            default:
                return -1;
        }
    }

    private static int vp9LevelNumberToConst(int levelNumber) {
        switch (levelNumber) {
            case 10:
                return 1;
            case 11:
                return 2;
            case 20:
                return 4;
            case 21:
                return 8;
            case 30:
                return 16;
            case 31:
                return 32;
            case 40:
                return 64;
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                return 128;
            case 50:
                return 256;
            case 51:
                return 512;
            case 60:
                return 2048;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                return 4096;
            case 62:
                return 8192;
            default:
                return -1;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Integer hevcCodecStringToProfileLevel(String codecString) {
        char c;
        if (codecString == null) {
            return null;
        }
        switch (codecString.hashCode()) {
            case 70821:
                if (codecString.equals("H30")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 70914:
                if (codecString.equals("H60")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 70917:
                if (codecString.equals("H63")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 71007:
                if (codecString.equals("H90")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 71010:
                if (codecString.equals("H93")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 74665:
                if (codecString.equals("L30")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 74758:
                if (codecString.equals("L60")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 74761:
                if (codecString.equals("L63")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 74851:
                if (codecString.equals("L90")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 74854:
                if (codecString.equals("L93")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2193639:
                if (codecString.equals("H120")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 2193642:
                if (codecString.equals("H123")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2193732:
                if (codecString.equals("H150")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 2193735:
                if (codecString.equals("H153")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 2193738:
                if (codecString.equals("H156")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 2193825:
                if (codecString.equals("H180")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 2193828:
                if (codecString.equals("H183")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 2193831:
                if (codecString.equals("H186")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case 2312803:
                if (codecString.equals("L120")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2312806:
                if (codecString.equals("L123")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2312896:
                if (codecString.equals("L150")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2312899:
                if (codecString.equals("L153")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2312902:
                if (codecString.equals("L156")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2312989:
                if (codecString.equals("L180")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 2312992:
                if (codecString.equals("L183")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 2312995:
                if (codecString.equals("L186")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 4;
            case 2:
                return 16;
            case 3:
                return 64;
            case 4:
                return 256;
            case 5:
                return 1024;
            case 6:
                return 4096;
            case 7:
                return 16384;
            case '\b':
                return 65536;
            case '\t':
                return 262144;
            case '\n':
                return 1048576;
            case 11:
                return 4194304;
            case '\f':
                return 16777216;
            case '\r':
                return 2;
            case 14:
                return 8;
            case 15:
                return 32;
            case 16:
                return 128;
            case 17:
                return 512;
            case 18:
                return 2048;
            case 19:
                return 8192;
            case 20:
                return 32768;
            case 21:
                return 131072;
            case 22:
                return 524288;
            case 23:
                return 2097152;
            case 24:
                return 8388608;
            case 25:
                return 33554432;
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Integer dolbyVisionStringToProfile(String profileString) {
        char c;
        if (profileString == null) {
            return null;
        }
        switch (profileString.hashCode()) {
            case 1536:
                if (profileString.equals("00")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1537:
                if (profileString.equals("01")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1538:
                if (profileString.equals("02")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1539:
                if (profileString.equals("03")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1540:
                if (profileString.equals("04")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1541:
                if (profileString.equals("05")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1542:
                if (profileString.equals("06")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1543:
                if (profileString.equals("07")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1544:
                if (profileString.equals("08")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1545:
                if (profileString.equals("09")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1567:
                if (profileString.equals("10")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case '\b':
                return 256;
            case '\t':
                return 512;
            case '\n':
                return 1024;
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Integer dolbyVisionStringToLevel(String levelString) {
        char c;
        if (levelString == null) {
            return null;
        }
        switch (levelString.hashCode()) {
            case 1537:
                if (levelString.equals("01")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1538:
                if (levelString.equals("02")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1539:
                if (levelString.equals("03")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1540:
                if (levelString.equals("04")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1541:
                if (levelString.equals("05")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1542:
                if (levelString.equals("06")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1543:
                if (levelString.equals("07")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1544:
                if (levelString.equals("08")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1545:
                if (levelString.equals("09")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1567:
                if (levelString.equals("10")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1568:
                if (levelString.equals("11")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1569:
                if (levelString.equals("12")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1570:
                if (levelString.equals("13")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case '\b':
                return 256;
            case '\t':
                return 512;
            case '\n':
                return 1024;
            case 11:
                return 2048;
            case '\f':
                return 4096;
            default:
                return null;
        }
    }

    private static int av1LevelNumberToConst(int levelNumber) {
        switch (levelNumber) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 1024;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return 8192;
            case 14:
                return 16384;
            case 15:
                return 32768;
            case 16:
                return 65536;
            case 17:
                return 131072;
            case 18:
                return 262144;
            case 19:
                return 524288;
            case 20:
                return 1048576;
            case 21:
                return 2097152;
            case 22:
                return 4194304;
            case 23:
                return 8388608;
            default:
                return -1;
        }
    }

    private static int mp4aAudioObjectTypeToProfile(int profileNumber) {
        switch (profileNumber) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 17:
                return 17;
            case 20:
                return 20;
            case 23:
                return 23;
            case 29:
                return 29;
            case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                return 39;
            case 42:
                return 42;
            default:
                return -1;
        }
    }
}
