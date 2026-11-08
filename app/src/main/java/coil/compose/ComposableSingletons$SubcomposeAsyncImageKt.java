package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* compiled from: SubcomposeAsyncImage.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ComposableSingletons$SubcomposeAsyncImageKt {
    public static final ComposableSingletons$SubcomposeAsyncImageKt INSTANCE = new ComposableSingletons$SubcomposeAsyncImageKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<SubcomposeAsyncImageScope, Composer, Integer, Unit> f58lambda1 = ComposableLambdaKt.composableLambdaInstance(-1692951203, false, new Function3<SubcomposeAsyncImageScope, Composer, Integer, Unit>() { // from class: coil.compose.ComposableSingletons$SubcomposeAsyncImageKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SubcomposeAsyncImageScope subcomposeAsyncImageScope, Composer composer, Integer num) {
            invoke(subcomposeAsyncImageScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(SubcomposeAsyncImageScope $this$null, Composer $composer, int $changed) {
            int $dirty = $changed;
            if (($changed & 14) == 0) {
                $dirty |= $composer.changed($this$null) ? 4 : 2;
            }
            if (($dirty & 91) == 18 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1692951203, $dirty, -1, "coil.compose.ComposableSingletons$SubcomposeAsyncImageKt.lambda-1.<anonymous> (SubcomposeAsyncImage.kt:391)");
            }
            SubcomposeAsyncImageKt.SubcomposeAsyncImageContent($this$null, null, null, null, null, null, 0.0f, null, false, $composer, $dirty & 14, 255);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$coil_compose_base_release, reason: not valid java name */
    public final Function3<SubcomposeAsyncImageScope, Composer, Integer, Unit> m6527getLambda1$coil_compose_base_release() {
        return f58lambda1;
    }
}
