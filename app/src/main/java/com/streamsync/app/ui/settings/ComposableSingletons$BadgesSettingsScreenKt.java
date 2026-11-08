package com.streamsync.app.ui.settings;

import androidx.compose.foundation.layout.RowScope;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BadgesSettingsScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class ComposableSingletons$BadgesSettingsScreenKt {
    public static final ComposableSingletons$BadgesSettingsScreenKt INSTANCE = new ComposableSingletons$BadgesSettingsScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f124lambda1 = ComposableLambdaKt.composableLambdaInstance(809393132, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C94@4109L14:BadgesSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(809393132, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt.lambda-1.<anonymous> (BadgesSettingsScreen.kt:94)");
            }
            TextKt.m2127Text4IGK_g("Badges", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f125lambda2 = ComposableLambdaKt.composableLambdaInstance(-466387539, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C97@4237L74:BadgesSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-466387539, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt.lambda-2.<anonymous> (BadgesSettingsScreen.kt:97)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f126lambda3 = ComposableLambdaKt.composableLambdaInstance(1044321231, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C266@11743L27:BadgesSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1044321231, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt.lambda-3.<anonymous> (BadgesSettingsScreen.kt:266)");
            }
            TextKt.m2127Text4IGK_g("Thêm URL file .json", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f127lambda4 = ComposableLambdaKt.composableLambdaInstance(-1795477831, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C296@13343L12:BadgesSettingsScreen.kt#bv4dau");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1795477831, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$BadgesSettingsScreenKt.lambda-4.<anonymous> (BadgesSettingsScreen.kt:296)");
            }
            TextKt.m2127Text4IGK_g("Thêm", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6679getLambda1$app_mobileDebug() {
        return f124lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6680getLambda2$app_mobileDebug() {
        return f125lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6681getLambda3$app_mobileDebug() {
        return f126lambda3;
    }

    /* renamed from: getLambda-4$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6682getLambda4$app_mobileDebug() {
        return f127lambda4;
    }
}
