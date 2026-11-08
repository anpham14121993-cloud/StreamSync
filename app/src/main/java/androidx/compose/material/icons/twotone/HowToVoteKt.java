package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HowToVote.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_howToVote", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HowToVote", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHowToVote", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class HowToVoteKt {
    private static ImageVector _howToVote;

    public static final ImageVector getHowToVote(Icons.TwoTone $this$HowToVote) {
        ImageVector.Builder m4044addPathoIyEayM;
        if (_howToVote != null) {
            ImageVector imageVector = _howToVote;
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder $this$_get_HowToVote__u24lambda_u244 = new ImageVector.Builder("TwoTone.HowToVote", Dp.m5736constructorimpl(24.0f), Dp.m5736constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int pathFillType$iv = VectorKt.getDefaultFillType();
        Brush fill$iv$iv = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv.moveTo(5.0f, 19.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineToRelative(14.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.verticalLineToRelative(1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.horizontalLineTo(5.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv.close();
        $this$_get_HowToVote__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv, (r30 & 16) != 0 ? 1.0f : 0.3f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 0.3f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv2 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv2 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv2 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv2 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv2 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(18.0f, 13.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(-0.68f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.0f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(1.91f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(19.0f, 17.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(5.0f, 17.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(1.78f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(2.05f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(6.0f, 13.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-3.0f, 3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(4.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 1.99f, 2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(19.0f, 22.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.curveToRelative(1.1f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(-4.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineToRelative(-3.0f, -3.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$PathData_u24lambda_u240$iv$iv$iv2.moveTo(19.0f, 20.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.lineTo(5.0f, 20.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(-1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.horizontalLineToRelative(14.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.verticalLineToRelative(1.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv2.close();
        $this$_get_HowToVote__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv2.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv2, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv2, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv2, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv2, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv3 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv3 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv3 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv3 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv3 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv3.moveTo(12.048f, 12.905f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineTo(8.505f, 9.362f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(4.95f, -4.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.lineToRelative(3.543f, 3.543f);
        $this$PathData_u24lambda_u240$iv$iv$iv3.close();
        $this$_get_HowToVote__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv3.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv3, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv3, (r30 & 16) != 0 ? 1.0f : 0.3f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 0.3f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv3, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv3, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        int pathFillType$iv4 = VectorKt.getDefaultFillType();
        Brush fill$iv$iv4 = new SolidColor(Color.INSTANCE.m3435getBlack0d7_KjU(), null);
        int strokeLineCap$iv$iv4 = StrokeCap.INSTANCE.m3762getButtKaPHkGw();
        int strokeLineJoin$iv$iv4 = StrokeJoin.INSTANCE.m3772getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv$iv4 = new PathBuilder();
        $this$PathData_u24lambda_u240$iv$iv$iv4.moveTo(19.11f, 7.25f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineTo(14.16f, 2.3f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.curveToRelative(-0.38f, -0.4f, -1.01f, -0.4f, -1.4f, -0.01f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineTo(6.39f, 8.66f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineToRelative(4.95f, 4.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineToRelative(6.36f, -6.36f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.close();
        $this$PathData_u24lambda_u240$iv$iv$iv4.moveTo(12.05f, 12.9f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineTo(8.51f, 9.36f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineToRelative(4.95f, -4.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineTo(17.0f, 7.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.lineToRelative(-4.95f, 4.95f);
        $this$PathData_u24lambda_u240$iv$iv$iv4.close();
        m4044addPathoIyEayM = $this$_get_HowToVote__u24lambda_u244.m4044addPathoIyEayM($this$PathData_u24lambda_u240$iv$iv$iv4.getNodes(), (r30 & 2) != 0 ? VectorKt.getDefaultFillType() : pathFillType$iv4, (r30 & 4) != 0 ? "" : "", (r30 & 8) != 0 ? null : fill$iv$iv4, (r30 & 16) != 0 ? 1.0f : 1.0f, (r30 & 32) == 0 ? null : null, (r30 & 64) != 0 ? 1.0f : 1.0f, (r30 & 128) != 0 ? 0.0f : 1.0f, (r30 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : strokeLineCap$iv$iv4, (r30 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : strokeLineJoin$iv$iv4, (r30 & 1024) != 0 ? 4.0f : 1.0f, (r30 & 2048) != 0 ? 0.0f : 0.0f, (r30 & 4096) == 0 ? 0.0f : 1.0f, (r30 & 8192) == 0 ? 0.0f : 0.0f);
        _howToVote = m4044addPathoIyEayM.build();
        ImageVector imageVector2 = _howToVote;
        Intrinsics.checkNotNull(imageVector2);
        return imageVector2;
    }
}
