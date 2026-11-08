package com.streamsync.app.settings;

import androidx.media3.exoplayer.RendererCapabilities;
import androidx.recyclerview.widget.ItemTouchHelper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DeviceProfile.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\b\u0086\u0081\u0002\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001eBG\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000ej\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001f"}, d2 = {"Lcom/streamsync/app/settings/RamTier;", "", "label", "", "minBufferSeconds", "", "maxBufferSeconds", "backBufferSeconds", "parallelConnections", "chunkSizeMb", "safetyLimitMb", "warningMb", "(Ljava/lang/String;ILjava/lang/String;IIIIIII)V", "getBackBufferSeconds", "()I", "getChunkSizeMb", "getLabel", "()Ljava/lang/String;", "getMaxBufferSeconds", "getMinBufferSeconds", "getParallelConnections", "getSafetyLimitMb", "getWarningMb", "RAM_1", "RAM_15", "RAM_2", "RAM_3", "RAM_4", "RAM_6", "RAM_8", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public enum RamTier {
    RAM_1("1 GB", 20, 40, 8, 1, 1, 96, 120),
    RAM_15("1,5 GB", 20, 40, 8, 1, 1, 112, 140),
    RAM_2("2 GB", 20, 40, 8, 1, 1, 128, 160),
    RAM_3("3 GB", 30, 50, 10, 2, 4, 160, 200),
    RAM_4("4 GB", 40, 60, 10, 2, 4, 200, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION),
    RAM_6("6 GB", 50, 70, 10, 3, 8, 256, 320),
    RAM_8("8 GB và trên", 60, 90, 10, 4, 16, 320, 400);

    private final int backBufferSeconds;
    private final int chunkSizeMb;
    private final String label;
    private final int maxBufferSeconds;
    private final int minBufferSeconds;
    private final int parallelConnections;
    private final int safetyLimitMb;
    private final int warningMb;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<RamTier> getEntries() {
        return $ENTRIES;
    }

    RamTier(String label, int minBufferSeconds, int maxBufferSeconds, int backBufferSeconds, int parallelConnections, int chunkSizeMb, int safetyLimitMb, int warningMb) {
        this.label = label;
        this.minBufferSeconds = minBufferSeconds;
        this.maxBufferSeconds = maxBufferSeconds;
        this.backBufferSeconds = backBufferSeconds;
        this.parallelConnections = parallelConnections;
        this.chunkSizeMb = chunkSizeMb;
        this.safetyLimitMb = safetyLimitMb;
        this.warningMb = warningMb;
    }

    public final String getLabel() {
        return this.label;
    }

    public final int getMinBufferSeconds() {
        return this.minBufferSeconds;
    }

    public final int getMaxBufferSeconds() {
        return this.maxBufferSeconds;
    }

    public final int getBackBufferSeconds() {
        return this.backBufferSeconds;
    }

    public final int getParallelConnections() {
        return this.parallelConnections;
    }

    public final int getChunkSizeMb() {
        return this.chunkSizeMb;
    }

    public final int getSafetyLimitMb() {
        return this.safetyLimitMb;
    }

    public final int getWarningMb() {
        return this.warningMb;
    }

    /* compiled from: DeviceProfile.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\f\u0010\b\u001a\u00020\u0006*\u00020\u0004H\u0002¨\u0006\t"}, d2 = {"Lcom/streamsync/app/settings/RamTier$Companion;", "", "()V", "forRamMb", "Lcom/streamsync/app/settings/RamTier;", "ramMb", "", "fromRamMb", "maxRamMbInclusive", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {

        /* compiled from: DeviceProfile.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes14.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[RamTier.values().length];
                try {
                    iArr[RamTier.RAM_1.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[RamTier.RAM_15.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[RamTier.RAM_2.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[RamTier.RAM_3.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[RamTier.RAM_4.ordinal()] = 5;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[RamTier.RAM_6.ordinal()] = 6;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[RamTier.RAM_8.ordinal()] = 7;
                } catch (NoSuchFieldError e7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RamTier fromRamMb(int ramMb) {
            RamTier ramTier;
            RamTier[] values = RamTier.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    ramTier = values[i];
                    if (ramMb <= RamTier.INSTANCE.maxRamMbInclusive(ramTier)) {
                        break;
                    }
                    i++;
                } else {
                    ramTier = null;
                    break;
                }
            }
            return ramTier == null ? RamTier.RAM_8 : ramTier;
        }

        private final int maxRamMbInclusive(RamTier $this$maxRamMbInclusive) {
            switch (WhenMappings.$EnumSwitchMapping$0[$this$maxRamMbInclusive.ordinal()]) {
                case 1:
                    return 1280;
                case 2:
                    return 1792;
                case 3:
                    return 2560;
                case 4:
                    return RendererCapabilities.AUDIO_OFFLOAD_SUPPORT_MASK;
                case 5:
                    return 4608;
                case 6:
                    return 7168;
                case 7:
                    return Integer.MAX_VALUE;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        public final RamTier forRamMb(int ramMb) {
            return fromRamMb(ramMb);
        }
    }
}
