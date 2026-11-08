package com.streamsync.app.ui.detail;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
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
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MovieDetailScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class ComposableSingletons$MovieDetailScreenKt {
    public static final ComposableSingletons$MovieDetailScreenKt INSTANCE = new ComposableSingletons$MovieDetailScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f76lambda1 = ComposableLambdaKt.composableLambdaInstance(1387654842, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C647@29926L15:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1387654842, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-1.<anonymous> (MovieDetailScreen.kt:647)");
            }
            TextKt.m2127Text4IGK_g("Thử lại", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f77lambda2 = ComposableLambdaKt.composableLambdaInstance(-561666781, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C759@36508L10,760@36588L11,757@36372L380:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-561666781, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-2.<anonymous> (MovieDetailScreen.kt:757)");
                }
                TextKt.m2127Text4IGK_g("Đang lấy danh sách nguồn phát...", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).m1371getOnSurfaceVariant0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 54, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f78lambda3 = ComposableLambdaKt.composableLambdaInstance(1203495619, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C849@41799L26:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1203495619, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-3.<anonymous> (MovieDetailScreen.kt:849)");
            }
            TextKt.m2127Text4IGK_g("Tải lại nguồn phát", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f79lambda4 = ComposableLambdaKt.composableLambdaInstance(969535240, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C995@50231L10,996@50315L11,993@50076L381:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(969535240, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-4.<anonymous> (MovieDetailScreen.kt:993)");
                }
                TextKt.m2127Text4IGK_g("Addon không trả về tập nào cho phim bộ này.", PaddingKt.m564padding3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).m1371getOnSurfaceVariant0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 54, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f80lambda5 = ComposableLambdaKt.composableLambdaInstance(-1228935339, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C1182@63383L30:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1228935339, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-5.<anonymous> (MovieDetailScreen.kt:1182)");
            }
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(32)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f81lambda6 = ComposableLambdaKt.composableLambdaInstance(-739099216, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0182  */
        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(androidx.compose.runtime.Composer r34, int r35) {
            /*
                Method dump skipped, instructions count: 390
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda6$1.invoke(androidx.compose.runtime.Composer, int):void");
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f82lambda7 = ComposableLambdaKt.composableLambdaInstance(-447360750, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C1333@68438L25:MovieDetailScreen.kt#tl1h0o");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-447360750, $changed, -1, "com.streamsync.app.ui.detail.ComposableSingletons$MovieDetailScreenKt.lambda-7.<anonymous> (MovieDetailScreen.kt:1333)");
            }
            TextKt.m2127Text4IGK_g(StreamProviderGroupsKt.ALL_PROVIDERS_LABEL, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6626getLambda1$app_mobileDebug() {
        return f76lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6627getLambda2$app_mobileDebug() {
        return f77lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6628getLambda3$app_mobileDebug() {
        return f78lambda3;
    }

    /* renamed from: getLambda-4$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6629getLambda4$app_mobileDebug() {
        return f79lambda4;
    }

    /* renamed from: getLambda-5$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6630getLambda5$app_mobileDebug() {
        return f80lambda5;
    }

    /* renamed from: getLambda-6$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6631getLambda6$app_mobileDebug() {
        return f81lambda6;
    }

    /* renamed from: getLambda-7$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6632getLambda7$app_mobileDebug() {
        return f82lambda7;
    }
}
