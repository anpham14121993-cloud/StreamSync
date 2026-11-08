package com.streamsync.app.ui.catalog;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
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

/* compiled from: CatalogBrowseScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes22.dex */
public final class ComposableSingletons$CatalogBrowseScreenKt {
    public static final ComposableSingletons$CatalogBrowseScreenKt INSTANCE = new ComposableSingletons$CatalogBrowseScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f68lambda1 = ComposableLambdaKt.composableLambdaInstance(-2110696197, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C266@11969L15:CatalogBrowseScreen.kt#aadnbi");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2110696197, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt.lambda-1.<anonymous> (CatalogBrowseScreen.kt:266)");
            }
            TextKt.m2127Text4IGK_g("Thử lại", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f69lambda2 = ComposableLambdaKt.composableLambdaInstance(-1058348507, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C279@12634L21:CatalogBrowseScreen.kt#aadnbi");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1058348507, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt.lambda-2.<anonymous> (CatalogBrowseScreen.kt:279)");
            }
            TextKt.m2127Text4IGK_g("Tải thêm phim", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f70lambda3 = ComposableLambdaKt.composableLambdaInstance(-1208268509, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C300@13386L150:CatalogBrowseScreen.kt#aadnbi");
            if (($changed & 3) != 2 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1208268509, $changed, -1, "com.streamsync.app.ui.catalog.ComposableSingletons$CatalogBrowseScreenKt.lambda-3.<anonymous> (CatalogBrowseScreen.kt:300)");
                }
                IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), (String) null, (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
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
    public final Function3<RowScope, Composer, Integer, Unit> m6615getLambda1$app_mobileDebug() {
        return f68lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6616getLambda2$app_mobileDebug() {
        return f69lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6617getLambda3$app_mobileDebug() {
        return f70lambda3;
    }
}
