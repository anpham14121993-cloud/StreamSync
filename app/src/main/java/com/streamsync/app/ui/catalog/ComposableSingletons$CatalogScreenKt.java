package com.streamsync.app.ui.catalog;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
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
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.components.MediaComponentsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CatalogScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class ComposableSingletons$CatalogScreenKt {
    public static final ComposableSingletons$CatalogScreenKt INSTANCE = new ComposableSingletons$CatalogScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f71lambda1 = ComposableLambdaKt.composableLambdaInstance(530311311, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C591@28020L25:CatalogScreen.kt#aadnbi");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(530311311, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt.lambda-1.<anonymous> (CatalogScreen.kt:591)");
            }
            MediaComponentsKt.RowHeader("Tiếp tục xem", null, null, $composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f72lambda2 = ComposableLambdaKt.composableLambdaInstance(1249246497, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C634@30277L30:CatalogScreen.kt#aadnbi");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1249246497, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt.lambda-2.<anonymous> (CatalogScreen.kt:634)");
            }
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(24)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f73lambda3 = ComposableLambdaKt.composableLambdaInstance(-772044857, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C671@31544L25:CatalogScreen.kt#aadnbi");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-772044857, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogScreenKt.lambda-3.<anonymous> (CatalogScreen.kt:671)");
            }
            TextKt.m2127Text4IGK_g("Cài addon của tôi", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6618getLambda1$app_mobileDebug() {
        return f71lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6619getLambda2$app_mobileDebug() {
        return f72lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6620getLambda3$app_mobileDebug() {
        return f73lambda3;
    }
}
