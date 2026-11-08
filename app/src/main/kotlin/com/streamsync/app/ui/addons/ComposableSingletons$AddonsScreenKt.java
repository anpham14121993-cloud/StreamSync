package com.streamsync.app.ui.addons;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: AddonsScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes20.dex */
public final class ComposableSingletons$AddonsScreenKt {
    public static final ComposableSingletons$AddonsScreenKt INSTANCE = new ComposableSingletons$AddonsScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f64lambda1 = ComposableLambdaKt.composableLambdaInstance(-407551682, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C127@5440L13:AddonsScreen.kt#v1022u");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-407551682, $changed, -1, "com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt.lambda-1.<anonymous> (AddonsScreen.kt:127)");
            }
            TextKt.m2127Text4IGK_g("Addon", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f65lambda2 = ComposableLambdaKt.composableLambdaInstance(-1507623814, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C127@5420L36:AddonsScreen.kt#v1022u");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1507623814, $changed, -1, "com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt.lambda-2.<anonymous> (AddonsScreen.kt:127)");
            }
            AppBarKt.TopAppBar(ComposableSingletons$AddonsScreenKt.INSTANCE.m6609getLambda1$app_mobileDebug(), null, null, null, null, null, null, $composer, 6, WebSocketProtocol.PAYLOAD_SHORT);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f66lambda3 = ComposableLambdaKt.composableLambdaInstance(-1263080734, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C145@6368L42:AddonsScreen.kt#v1022u");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1263080734, $changed, -1, "com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt.lambda-3.<anonymous> (AddonsScreen.kt:145)");
            }
            TextKt.m2127Text4IGK_g("https://host/cfg_.../manifest.json", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f67lambda4 = ComposableLambdaKt.composableLambdaInstance(1950513813, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C260@12685L11,257@12483L257:AddonsScreen.kt#v1022u");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1950513813, $changed, -1, "com.streamsync.app.ui.addons.ComposableSingletons$AddonsScreenKt.lambda-4.<anonymous> (AddonsScreen.kt:257)");
                }
                IconKt.m1600Iconww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), "Gỡ addon", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).m1358getError0d7_KjU(), $composer, 48, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6609getLambda1$app_mobileDebug() {
        return f64lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6610getLambda2$app_mobileDebug() {
        return f65lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6611getLambda3$app_mobileDebug() {
        return f66lambda3;
    }

    /* renamed from: getLambda-4$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6612getLambda4$app_mobileDebug() {
        return f67lambda4;
    }
}
