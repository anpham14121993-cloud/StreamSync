package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.navigation.NavBackStackEntry;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* compiled from: DialogNavigator.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ComposableSingletons$DialogNavigatorKt {
    public static final ComposableSingletons$DialogNavigatorKt INSTANCE = new ComposableSingletons$DialogNavigatorKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<NavBackStackEntry, Composer, Integer, Unit> f57lambda1 = ComposableLambdaKt.composableLambdaInstance(-1092249270, false, new Function3<NavBackStackEntry, Composer, Integer, Unit>() { // from class: androidx.navigation.compose.ComposableSingletons$DialogNavigatorKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
            invoke(navBackStackEntry, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(NavBackStackEntry it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C:DialogNavigator.kt#opm8kd");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1092249270, $changed, -1, "androidx.navigation.compose.ComposableSingletons$DialogNavigatorKt.lambda-1.<anonymous> (DialogNavigator.kt:64)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$navigation_compose_release, reason: not valid java name */
    public final Function3<NavBackStackEntry, Composer, Integer, Unit> m6498getLambda1$navigation_compose_release() {
        return f57lambda1;
    }
}
