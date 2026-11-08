package com.streamsync.app.ui.player;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.container.NalUnitUtil;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DolbyVisionFallback.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rJ\u0017\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u0010J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionFallback;", "", "()V", "HDR10_COLOR_INFO", "Landroidx/media3/common/ColorInfo;", "HEVC_PREFIX", "", "NAL_UNIT_TYPE_SPS", "", "baseLayerMimeFor", "dvCodecs", "describe", "format", "Landroidx/media3/common/Format;", "hevcCodecsString", "needsRewrite", "", "profileOf", "codecs", "(Ljava/lang/String;)Ljava/lang/Integer;", "rewrite", "fallbackMime", "injectHdrColorInfo", "shouldInjectHdrColorInfo", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class DolbyVisionFallback {
    public static final int $stable;
    private static final ColorInfo HDR10_COLOR_INFO;
    private static final String HEVC_PREFIX = "hvc1";
    public static final DolbyVisionFallback INSTANCE = new DolbyVisionFallback();
    private static final int NAL_UNIT_TYPE_SPS = 33;

    private DolbyVisionFallback() {
    }

    public final String baseLayerMimeFor(String dvCodecs) {
        String str = dvCodecs;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String c = dvCodecs.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(c, "toLowerCase(...)");
        if (StringsKt.startsWith$default(c, "dvhe.", false, 2, (Object) null) || StringsKt.startsWith$default(c, "dvh1.", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_H265;
        }
        if (StringsKt.startsWith$default(c, "dvav.", false, 2, (Object) null) || StringsKt.startsWith$default(c, "dva1.", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_H264;
        }
        if (StringsKt.startsWith$default(c, "hev1.", false, 2, (Object) null) || StringsKt.startsWith$default(c, "hvc1.", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_H265;
        }
        if (StringsKt.startsWith$default(c, "avc3.", false, 2, (Object) null) || StringsKt.startsWith$default(c, "av01.", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_H264;
        }
        return null;
    }

    public final Integer profileOf(String codecs) {
        String str;
        String str2 = codecs;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        String lowerCase = StringsKt.trim((CharSequence) codecs).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List parts = StringsKt.split$default((CharSequence) lowerCase, new char[]{'.'}, false, 0, 6, (Object) null);
        String head = (String) CollectionsKt.firstOrNull(parts);
        if (head == null) {
            return null;
        }
        if ((StringsKt.startsWith$default(head, "dvhe", false, 2, (Object) null) || StringsKt.startsWith$default(head, "dvh1", false, 2, (Object) null) || StringsKt.startsWith$default(head, "dvav", false, 2, (Object) null) || StringsKt.startsWith$default(head, "dva1", false, 2, (Object) null) || StringsKt.startsWith$default(head, "hev1", false, 2, (Object) null) || StringsKt.startsWith$default(head, "avc3", false, 2, (Object) null)) && (str = (String) CollectionsKt.getOrNull(parts, 1)) != null) {
            return StringsKt.toIntOrNull(str);
        }
        return null;
    }

    public final boolean needsRewrite(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        return StringsKt.equals(MimeTypes.VIDEO_DOLBY_VISION, format.sampleMimeType, true);
    }

    public static /* synthetic */ Format rewrite$default(DolbyVisionFallback dolbyVisionFallback, Format format, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return dolbyVisionFallback.rewrite(format, str, z);
    }

    public final Format rewrite(Format format, String fallbackMime, boolean injectHdrColorInfo) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(fallbackMime, "fallbackMime");
        String codecs = hevcCodecsString(format);
        Format.Builder builder = format.buildUpon().setSampleMimeType(fallbackMime).setCodecs(codecs).setSelectionFlags(format.selectionFlags & (-2));
        Intrinsics.checkNotNullExpressionValue(builder, "setSelectionFlags(...)");
        String containerMime = format.containerMimeType;
        if (containerMime != null && StringsKt.equals(MimeTypes.VIDEO_DOLBY_VISION, containerMime, true)) {
            builder.setContainerMimeType(fallbackMime);
        }
        if (injectHdrColorInfo && shouldInjectHdrColorInfo(format)) {
            builder.setColorInfo(HDR10_COLOR_INFO);
        }
        Format build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final boolean shouldInjectHdrColorInfo(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        ColorInfo colorInfo = format.colorInfo;
        if (colorInfo == null) {
            return true;
        }
        int transfer = colorInfo.colorTransfer;
        return transfer != 6;
    }

    static {
        ColorInfo build = new ColorInfo.Builder().setColorSpace(6).setColorRange(2).setColorTransfer(6).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        HDR10_COLOR_INFO = build;
        $stable = 8;
    }

    private final String hevcCodecsString(Format format) {
        List initData;
        List initializationData = format.initializationData;
        Intrinsics.checkNotNullExpressionValue(initializationData, "initializationData");
        String str = null;
        if (initializationData.isEmpty()) {
            return null;
        }
        byte[] bArr = initializationData.get(0);
        Intrinsics.checkNotNullExpressionValue(bArr, "get(...)");
        byte[] buffer = bArr;
        int pos = 22;
        int numArrays = 0;
        try {
            if (buffer.length >= 22 + 1) {
                try {
                    numArrays = buffer[22] & 255;
                    pos = 22 + 1;
                } catch (IllegalArgumentException e) {
                    return null;
                } catch (IndexOutOfBoundsException e2) {
                    return null;
                }
            }
            int i = 0;
            while (i < numArrays) {
                if (pos + 3 > buffer.length) {
                    initData = initializationData;
                } else {
                    int nalType = buffer[pos] & 63;
                    int numNalus = ((buffer[pos + 1] & 255) << 8) | (buffer[pos + 2] & 255);
                    pos += 3;
                    if (nalType != 33 || numNalus <= 0) {
                        initData = initializationData;
                        for (int i2 = 0; i2 < numNalus; i2++) {
                            try {
                                if (pos + 2 <= buffer.length) {
                                    int len = ((buffer[pos] & 255) << 8) | (buffer[pos + 1] & 255);
                                    pos += len + 2;
                                }
                            } catch (IllegalArgumentException e3) {
                                return null;
                            } catch (IndexOutOfBoundsException e4) {
                                return null;
                            }
                        }
                    } else {
                        if (pos + 2 > buffer.length) {
                            return str;
                        }
                        int nalLength = ((buffer[pos] & 255) << 8) | (buffer[pos + 1] & 255);
                        int start = pos + 2;
                        int end = start + nalLength;
                        if (nalLength > 0 && end <= buffer.length) {
                            NalUnitUtil.H265SpsData sps = NalUnitUtil.parseH265SpsNalUnit(buffer, start, end);
                            if (sps == null) {
                                return str;
                            }
                            Intrinsics.checkNotNull(sps);
                            String tier = sps.generalTierFlag ? "H" : "L";
                            String head = "hvc1." + sps.generalProfileSpace + "." + sps.generalProfileIdc + "." + tier;
                            return head + "." + sps.generalLevelIdc;
                        }
                        return null;
                    }
                }
                i++;
                initializationData = initData;
                str = null;
            }
            return null;
        } catch (IllegalArgumentException e5) {
            return null;
        } catch (IndexOutOfBoundsException e6) {
            return null;
        }
    }

    public final String describe(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        Integer p = profileOf(format.codecs);
        String base = baseLayerMimeFor(format.codecs);
        return "DV profile=" + (p == null ? "?" : p) + " base=" + (base == null ? "khong ro" : base);
    }
}
