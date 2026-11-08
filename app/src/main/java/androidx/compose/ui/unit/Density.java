package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* compiled from: Density.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\b\u001a\u00020\t*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\n*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0010\u001a\u00020\n*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0017ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0012J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0017J\u0016\u0010 \u001a\u00020\u0016*\u00020\u0015H\u0017ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018J\u0019\u0010\"\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0019\u0010\"\u001a\u00020\r*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010%R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "(J)F", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(F)J", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public interface Density extends FontScaling {
    float getDensity();

    /* compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5724toDpGaN1DYA(Density $this, long $receiver) {
            return Density.super.mo310toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5731toSp0xMU5do(Density $this, float $receiver) {
            return Density.super.mo317toSp0xMU5do($receiver);
        }

        @Deprecated
        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5729toPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo315toPx0680j_4($receiver);
        }

        @Deprecated
        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5723roundToPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo309roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5728toPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo314toPxR2X_6o($receiver);
        }

        @Deprecated
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5722roundToPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo308roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5726toDpu2uoSUM(Density $this, int $receiver) {
            return Density.super.mo312toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5733toSpkPz2Gy4(Density $this, int $receiver) {
            return Density.super.mo319toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5725toDpu2uoSUM(Density $this, float $receiver) {
            return Density.super.mo311toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5732toSpkPz2Gy4(Density $this, float $receiver) {
            return Density.super.mo318toSpkPz2Gy4($receiver);
        }

        @Deprecated
        public static Rect toRect(Density $this, DpRect $receiver) {
            return Density.super.toRect($receiver);
        }

        @Deprecated
        /* renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5730toSizeXkaWNTQ(Density $this, long $receiver) {
            return Density.super.mo316toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5727toDpSizekrfVVM(Density $this, long $receiver) {
            return Density.super.mo313toDpSizekrfVVM($receiver);
        }
    }

    /* renamed from: toPx-0680j_4 */
    default float mo315toPx0680j_4(float $this$toPx_u2d0680j_4) {
        return getDensity() * $this$toPx_u2d0680j_4;
    }

    /* renamed from: roundToPx-0680j_4 */
    default int mo309roundToPx0680j_4(float $this$roundToPx_u2d0680j_4) {
        float px = mo315toPx0680j_4($this$roundToPx_u2d0680j_4);
        if (Float.isInfinite(px)) {
            return Integer.MAX_VALUE;
        }
        return MathKt.roundToInt(px);
    }

    /* renamed from: toPx--R2X_6o */
    default float mo314toPxR2X_6o(long $this$toPx_u2d_u2dR2X_6o) {
        if (TextUnitType.m5955equalsimpl0(TextUnit.m5926getTypeUIouoOA($this$toPx_u2d_u2dR2X_6o), TextUnitType.INSTANCE.m5960getSpUIouoOA())) {
            return mo315toPx0680j_4(mo310toDpGaN1DYA($this$toPx_u2d_u2dR2X_6o));
        }
        throw new IllegalStateException("Only Sp can convert to Px".toString());
    }

    /* renamed from: roundToPx--R2X_6o */
    default int mo308roundToPxR2X_6o(long $this$roundToPx_u2d_u2dR2X_6o) {
        return MathKt.roundToInt(mo314toPxR2X_6o($this$roundToPx_u2d_u2dR2X_6o));
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo312toDpu2uoSUM(int $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m5736constructorimpl($this$dp$iv);
    }

    /* renamed from: toSp-kPz2Gy4 */
    default long mo319toSpkPz2Gy4(int $this$toSp_u2dkPz2Gy4) {
        return mo317toSp0xMU5do(mo312toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo311toDpu2uoSUM(float $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m5736constructorimpl($this$dp$iv);
    }

    /* renamed from: toSp-kPz2Gy4 */
    default long mo318toSpkPz2Gy4(float $this$toSp_u2dkPz2Gy4) {
        return mo317toSp0xMU5do(mo311toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    default Rect toRect(DpRect $this$toRect) {
        return new Rect(mo315toPx0680j_4($this$toRect.m5819getLeftD9Ej5fM()), mo315toPx0680j_4($this$toRect.m5821getTopD9Ej5fM()), mo315toPx0680j_4($this$toRect.m5820getRightD9Ej5fM()), mo315toPx0680j_4($this$toRect.m5818getBottomD9Ej5fM()));
    }

    /* renamed from: toSize-XkaWNTQ */
    default long mo316toSizeXkaWNTQ(long $this$toSize_u2dXkaWNTQ) {
        if ($this$toSize_u2dXkaWNTQ != DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ()) {
            return SizeKt.Size(mo315toPx0680j_4(DpSize.m5834getWidthD9Ej5fM($this$toSize_u2dXkaWNTQ)), mo315toPx0680j_4(DpSize.m5832getHeightD9Ej5fM($this$toSize_u2dXkaWNTQ)));
        }
        return Size.INSTANCE.m3245getUnspecifiedNHjbRc();
    }

    /* renamed from: toDpSize-k-rfVVM */
    default long mo313toDpSizekrfVVM(long $this$toDpSize_u2dk_u2drfVVM) {
        if ($this$toDpSize_u2dk_u2drfVVM != Size.INSTANCE.m3245getUnspecifiedNHjbRc()) {
            return DpKt.m5758DpSizeYgX7TsA(mo311toDpu2uoSUM(Size.m3237getWidthimpl($this$toDpSize_u2dk_u2drfVVM)), mo311toDpu2uoSUM(Size.m3234getHeightimpl($this$toDpSize_u2dk_u2drfVVM)));
        }
        return DpSize.INSTANCE.m5843getUnspecifiedMYxV2XQ();
    }
}
