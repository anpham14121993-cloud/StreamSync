package com.streamsync.app.ui.player;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.media3.common.MimeTypes;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: VideoScaleMode.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002\u001a4\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002\u001a\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"originalPaddingPx", "Lkotlin/Pair;", "", "mode", "Lcom/streamsync/app/ui/player/VideoScaleMode;", MimeTypes.BASE_TYPE_VIDEO, "Lcom/streamsync/app/ui/player/VideoDimensions;", "viewportWidth", "viewportHeight", "videoDisplaySize", "videoSurfaceModifier", "Landroidx/compose/ui/Modifier;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class VideoScaleModeKt {

    /* compiled from: VideoScaleMode.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoScaleMode.values().length];
            try {
                iArr[VideoScaleMode.FIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VideoScaleMode.CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VideoScaleMode.STRETCH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VideoScaleMode.ORIGINAL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Pair<Integer, Integer> videoDisplaySize(VideoScaleMode mode, VideoDimensions video, int viewportWidth, int viewportHeight) {
        Float aspectRatio;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(video, "video");
        if (viewportWidth <= 0 || viewportHeight <= 0 || (aspectRatio = video.getAspectRatio()) == null) {
            return null;
        }
        aspectRatio.floatValue();
        switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1:
                float scale = Math.min(viewportWidth / video.getWidth(), viewportHeight / video.getHeight());
                return TuplesKt.to(Integer.valueOf((int) (video.getWidth() * scale)), Integer.valueOf((int) (video.getHeight() * scale)));
            case 2:
                float scale2 = Math.max(viewportWidth / video.getWidth(), viewportHeight / video.getHeight());
                return TuplesKt.to(Integer.valueOf((int) (video.getWidth() * scale2)), Integer.valueOf((int) (video.getHeight() * scale2)));
            case 3:
                return null;
            case 4:
                boolean fits = video.getWidth() <= viewportWidth && video.getHeight() <= viewportHeight;
                if (fits) {
                    return TuplesKt.to(Integer.valueOf(video.getWidth()), Integer.valueOf(video.getHeight()));
                }
                float scale3 = Math.min(viewportWidth / video.getWidth(), viewportHeight / video.getHeight());
                return TuplesKt.to(Integer.valueOf((int) (video.getWidth() * scale3)), Integer.valueOf((int) (video.getHeight() * scale3)));
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final Pair<Integer, Integer> originalPaddingPx(VideoScaleMode mode, VideoDimensions video, int viewportWidth, int viewportHeight) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(video, "video");
        if (mode != VideoScaleMode.ORIGINAL) {
            return TuplesKt.to(0, 0);
        }
        if (video.getWidth() <= 0 || video.getHeight() <= 0) {
            return TuplesKt.to(0, 0);
        }
        if (viewportWidth <= 0 || viewportHeight <= 0) {
            return TuplesKt.to(0, 0);
        }
        int h = RangesKt.coerceAtLeast((viewportWidth - video.getWidth()) / 2, 0);
        int v = RangesKt.coerceAtLeast((viewportHeight - video.getHeight()) / 2, 0);
        return TuplesKt.to(Integer.valueOf(h), Integer.valueOf(v));
    }

    public static final Modifier videoSurfaceModifier() {
        return BackgroundKt.m212backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m3435getBlack0d7_KjU(), null, 2, null);
    }
}
