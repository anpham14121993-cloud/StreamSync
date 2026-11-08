package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoScaleMode.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\t\u001a\u00020\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/streamsync/app/ui/player/VideoScaleMode;", "", "label", "", "hint", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getHint", "()Ljava/lang/String;", "getLabel", "next", "FIT", "CROP", "STRETCH", "ORIGINAL", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public enum VideoScaleMode {
    FIT("Vừa vặn", "Giữ nguyên tỉ lệ, xem hết khung hình, có thể có viền đen"),
    CROP("Khít", "Phóng cho kín màn hình, phần thừa bị cắt mất"),
    STRETCH("Kéo giãn", "Ép vừa khung màn hình, hình bị méo"),
    ORIGINAL("Gốc", "Đúng kích thước gốc của video, không phóng to");

    private final String hint;
    private final String label;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<VideoScaleMode> getEntries() {
        return $ENTRIES;
    }

    VideoScaleMode(String label, String hint) {
        this.label = label;
        this.hint = hint;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getHint() {
        return this.hint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final VideoScaleMode next() {
        return (VideoScaleMode) getEntries().get((ordinal() + 1) % getEntries().size());
    }

    /* compiled from: VideoScaleMode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/ui/player/VideoScaleMode$Companion;", "", "()V", "resizeModeOrNull", "", "mode", "Lcom/streamsync/app/ui/player/VideoScaleMode;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class Companion {

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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int resizeModeOrNull(VideoScaleMode mode) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
                case 1:
                case 4:
                    return 0;
                case 2:
                    return 4;
                case 3:
                    return 3;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }
}
