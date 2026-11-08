package com.streamsync.app.ui.settings;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
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

/* compiled from: DpiBypassSettingsScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class ComposableSingletons$DpiBypassSettingsScreenKt {
    public static final ComposableSingletons$DpiBypassSettingsScreenKt INSTANCE = new ComposableSingletons$DpiBypassSettingsScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f133lambda1 = ComposableLambdaKt.composableLambdaInstance(-1462733304, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C50@2085L17:DpiBypassSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1462733304, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt.lambda-1.<anonymous> (DpiBypassSettingsScreen.kt:50)");
            }
            TextKt.m2127Text4IGK_g("BypassDPI", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f134lambda2 = ComposableLambdaKt.composableLambdaInstance(1614512905, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C53@2216L74:DpiBypassSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1614512905, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt.lambda-2.<anonymous> (DpiBypassSettingsScreen.kt:53)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f135lambda3 = ComposableLambdaKt.composableLambdaInstance(-873362423, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x01e7  */
        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(androidx.compose.runtime.Composer r52, int r53) {
            /*
                Method dump skipped, instructions count: 491
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.ComposableSingletons$DpiBypassSettingsScreenKt$lambda3$1.invoke(androidx.compose.runtime.Composer, int):void");
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6688getLambda1$app_mobileDebug() {
        return f133lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6689getLambda2$app_mobileDebug() {
        return f134lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6690getLambda3$app_mobileDebug() {
        return f135lambda3;
    }
}
