package com.streamsync.app.ui.search;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.SearchKt;
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

/* compiled from: SearchScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class ComposableSingletons$SearchScreenKt {
    public static final ComposableSingletons$SearchScreenKt INSTANCE = new ComposableSingletons$SearchScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f116lambda1 = ComposableLambdaKt.composableLambdaInstance(1975552174, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C198@7809L24:SearchScreen.kt#mhoxe9");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1975552174, $changed, -1, "com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt.lambda-1.<anonymous> (SearchScreen.kt:198)");
            }
            TextKt.m2127Text4IGK_g("Nhập tên phim...", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f117lambda2 = ComposableLambdaKt.composableLambdaInstance(-2070136721, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C199@7865L53:SearchScreen.kt#mhoxe9");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2070136721, $changed, -1, "com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt.lambda-2.<anonymous> (SearchScreen.kt:199)");
            }
            IconKt.m1600Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f118lambda3 = ComposableLambdaKt.composableLambdaInstance(147467304, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C206@8174L61:SearchScreen.kt#mhoxe9");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(147467304, $changed, -1, "com.streamsync.app.ui.search.ComposableSingletons$SearchScreenKt.lambda-3.<anonymous> (SearchScreen.kt:206)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Xoá từ khoá", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6671getLambda1$app_mobileDebug() {
        return f116lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6672getLambda2$app_mobileDebug() {
        return f117lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6673getLambda3$app_mobileDebug() {
        return f118lambda3;
    }
}
