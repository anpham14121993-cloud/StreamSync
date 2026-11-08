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

/* compiled from: SettingsScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class ComposableSingletons$SettingsScreenKt {
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f145lambda1 = ComposableLambdaKt.composableLambdaInstance(1923048520, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$SettingsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C110@5078L15:SettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1923048520, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$SettingsScreenKt.lambda-1.<anonymous> (SettingsScreen.kt:110)");
            }
            TextKt.m2127Text4IGK_g("Cài đặt", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f146lambda2 = ComposableLambdaKt.composableLambdaInstance(-320320055, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$SettingsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C113@5207L74:SettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-320320055, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$SettingsScreenKt.lambda-2.<anonymous> (SettingsScreen.kt:113)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6700getLambda1$app_mobileDebug() {
        return f145lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6701getLambda2$app_mobileDebug() {
        return f146lambda2;
    }
}
