package com.streamsync.app.ui.player;

import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DecoderPreference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpticalPassthroughRenderersFactory.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/streamsync/app/ui/player/PreferenceMediaCodecSelector;", "Landroidx/media3/exoplayer/mediacodec/MediaCodecSelector;", "decoderPreference", "Lcom/streamsync/app/settings/DecoderPreference;", "(Lcom/streamsync/app/settings/DecoderPreference;)V", "delegate", "getDecoderInfos", "", "Landroidx/media3/exoplayer/mediacodec/MediaCodecInfo;", "mimeType", "", "requiresSecureDecoder", "", "tunneling", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
final class PreferenceMediaCodecSelector implements MediaCodecSelector {
    private static final Companion Companion = new Companion(null);
    private static final Set<String> SOFTWARE_ONLY_ON_MOBILE = SetsKt.setOf((Object[]) new String[]{MimeTypes.AUDIO_TRUEHD, MimeTypes.AUDIO_AC4});
    private final DecoderPreference decoderPreference;
    private final MediaCodecSelector delegate;

    /* compiled from: OpticalPassthroughRenderersFactory.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DecoderPreference.values().length];
            try {
                iArr[DecoderPreference.DEVICE_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DecoderPreference.PREFER_APP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DecoderPreference.PREFER_DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PreferenceMediaCodecSelector(DecoderPreference decoderPreference) {
        Intrinsics.checkNotNullParameter(decoderPreference, "decoderPreference");
        this.decoderPreference = decoderPreference;
        MediaCodecSelector DEFAULT = MediaCodecSelector.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
        this.delegate = DEFAULT;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecSelector
    public List<MediaCodecInfo> getDecoderInfos(String mimeType, boolean requiresSecureDecoder, boolean tunneling) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (SOFTWARE_ONLY_ON_MOBILE.contains(mimeType)) {
            return CollectionsKt.emptyList();
        }
        Iterable decoderInfos = this.delegate.getDecoderInfos(mimeType, requiresSecureDecoder, tunneling);
        Intrinsics.checkNotNullExpressionValue(decoderInfos, "getDecoderInfos(...)");
        switch (WhenMappings.$EnumSwitchMapping$0[this.decoderPreference.ordinal()]) {
            case 1:
                Iterable $this$filter$iv = decoderInfos;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    MediaCodecInfo it = (MediaCodecInfo) element$iv$iv;
                    if (it.hardwareAccelerated && !it.softwareOnly) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                return (List) destination$iv$iv;
            case 2:
                Iterable $this$sortedByDescending$iv = decoderInfos;
                return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.streamsync.app.ui.player.PreferenceMediaCodecSelector$getDecoderInfos$$inlined$sortedByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        MediaCodecInfo it2 = (MediaCodecInfo) t2;
                        MediaCodecInfo it3 = (MediaCodecInfo) t;
                        return ComparisonsKt.compareValues(Boolean.valueOf(it2.softwareOnly || !it2.hardwareAccelerated), Boolean.valueOf(it3.softwareOnly || !it3.hardwareAccelerated));
                    }
                });
            case 3:
                Iterable $this$sortedBy$iv = decoderInfos;
                return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.ui.player.PreferenceMediaCodecSelector$getDecoderInfos$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        MediaCodecInfo it2 = (MediaCodecInfo) t;
                        MediaCodecInfo it3 = (MediaCodecInfo) t2;
                        return ComparisonsKt.compareValues(Boolean.valueOf(it2.softwareOnly || !it2.hardwareAccelerated), Boolean.valueOf(it3.softwareOnly || !it3.hardwareAccelerated));
                    }
                });
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: OpticalPassthroughRenderersFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/streamsync/app/ui/player/PreferenceMediaCodecSelector$Companion;", "", "()V", "SOFTWARE_ONLY_ON_MOBILE", "", "", "getSOFTWARE_ONLY_ON_MOBILE", "()Ljava/util/Set;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getSOFTWARE_ONLY_ON_MOBILE() {
            return PreferenceMediaCodecSelector.SOFTWARE_ONLY_ON_MOBILE;
        }
    }
}
