package com.streamsync.app.ui.theme;

import android.content.res.Configuration;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowWidthClass.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\r\u0010\r\u001a\u00020\u0002H\u0007¢\u0006\u0002\u0010\u000e\u001a\r\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a\u000e\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\n\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"LocalWindowWidthClass", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/streamsync/app/ui/theme/WindowWidthClass;", "getLocalWindowWidthClass", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "contentMaxWidthDp", "Landroidx/compose/ui/unit/Dp;", "getContentMaxWidthDp", "(Lcom/streamsync/app/ui/theme/WindowWidthClass;)Landroidx/compose/ui/unit/Dp;", "posterColumns", "", "getPosterColumns", "(Lcom/streamsync/app/ui/theme/WindowWidthClass;)I", "currentWindowWidthClass", "(Landroidx/compose/runtime/Composer;I)Lcom/streamsync/app/ui/theme/WindowWidthClass;", "isTablet", "", "(Landroidx/compose/runtime/Composer;I)Z", "widthClassOf", "widthDp", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class WindowWidthClassKt {
    private static final ProvidableCompositionLocal<WindowWidthClass> LocalWindowWidthClass = CompositionLocalKt.staticCompositionLocalOf(new Function0<WindowWidthClass>() { // from class: com.streamsync.app.ui.theme.WindowWidthClassKt$LocalWindowWidthClass$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WindowWidthClass invoke() {
            return WindowWidthClass.COMPACT;
        }
    });

    /* compiled from: WindowWidthClass.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WindowWidthClass.values().length];
            try {
                iArr[WindowWidthClass.EXPANDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[WindowWidthClass.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[WindowWidthClass.COMPACT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Dp getContentMaxWidthDp(WindowWidthClass $this$contentMaxWidthDp) {
        Intrinsics.checkNotNullParameter($this$contentMaxWidthDp, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$contentMaxWidthDp.ordinal()]) {
            case 1:
                return Dp.m5734boximpl(Dp.m5736constructorimpl(840));
            case 2:
                return Dp.m5734boximpl(Dp.m5736constructorimpl(720));
            case 3:
                return null;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final int getPosterColumns(WindowWidthClass $this$posterColumns) {
        Intrinsics.checkNotNullParameter($this$posterColumns, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$posterColumns.ordinal()]) {
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 3;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final ProvidableCompositionLocal<WindowWidthClass> getLocalWindowWidthClass() {
        return LocalWindowWidthClass;
    }

    public static final WindowWidthClass widthClassOf(int widthDp) {
        return widthDp < 600 ? WindowWidthClass.COMPACT : widthDp < 840 ? WindowWidthClass.MEDIUM : WindowWidthClass.EXPANDED;
    }

    public static final WindowWidthClass currentWindowWidthClass(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1037659377, "C(currentWindowWidthClass)88@2996L7:WindowWidthClass.kt#d1esv6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1037659377, $changed, -1, "com.streamsync.app.ui.theme.currentWindowWidthClass (WindowWidthClass.kt:88)");
        }
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        WindowWidthClass widthClassOf = widthClassOf(((Configuration) consume).screenWidthDp);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return widthClassOf;
    }

    public static final boolean isTablet(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1349543912, "C(isTablet)93@3125L25:WindowWidthClass.kt#d1esv6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1349543912, $changed, -1, "com.streamsync.app.ui.theme.isTablet (WindowWidthClass.kt:93)");
        }
        boolean z = currentWindowWidthClass($composer, 0) != WindowWidthClass.COMPACT;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return z;
    }
}
