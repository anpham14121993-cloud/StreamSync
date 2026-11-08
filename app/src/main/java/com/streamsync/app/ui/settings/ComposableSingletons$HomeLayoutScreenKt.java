package com.streamsync.app.ui.settings;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.KeyboardArrowDownKt;
import androidx.compose.material.icons.filled.KeyboardArrowUpKt;
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
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeLayoutScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class ComposableSingletons$HomeLayoutScreenKt {
    public static final ComposableSingletons$HomeLayoutScreenKt INSTANCE = new ComposableSingletons$HomeLayoutScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f136lambda1 = ComposableLambdaKt.composableLambdaInstance(1026408320, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C78@3740L24:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1026408320, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-1.<anonymous> (HomeLayoutScreen.kt:78)");
            }
            TextKt.m2127Text4IGK_g("Bố cục trang chủ", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f137lambda2 = ComposableLambdaKt.composableLambdaInstance(253571649, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C81@3878L74:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(253571649, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-2.<anonymous> (HomeLayoutScreen.kt:81)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f138lambda3 = ComposableLambdaKt.composableLambdaInstance(681098866, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C85@4084L16:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(681098866, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-3.<anonymous> (HomeLayoutScreen.kt:85)");
            }
            TextKt.m2127Text4IGK_g("Mặc định", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f139lambda4 = ComposableLambdaKt.composableLambdaInstance(-1543227315, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C196@9637L30:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1543227315, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-4.<anonymous> (HomeLayoutScreen.kt:196)");
            }
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(24)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f140lambda5 = ComposableLambdaKt.composableLambdaInstance(1616204571, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C254@11733L63:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1616204571, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-5.<anonymous> (HomeLayoutScreen.kt:254)");
            }
            IconKt.m1600Iconww6aTOc(KeyboardArrowUpKt.getKeyboardArrowUp(Icons.INSTANCE.getDefault()), "Lên", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f141lambda6 = ComposableLambdaKt.composableLambdaInstance(1084099332, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C257@11937L67:HomeLayoutScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1084099332, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$HomeLayoutScreenKt.lambda-6.<anonymous> (HomeLayoutScreen.kt:257)");
            }
            IconKt.m1600Iconww6aTOc(KeyboardArrowDownKt.getKeyboardArrowDown(Icons.INSTANCE.getDefault()), "Xuống", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6691getLambda1$app_mobileDebug() {
        return f136lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6692getLambda2$app_mobileDebug() {
        return f137lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6693getLambda3$app_mobileDebug() {
        return f138lambda3;
    }

    /* renamed from: getLambda-4$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6694getLambda4$app_mobileDebug() {
        return f139lambda4;
    }

    /* renamed from: getLambda-5$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6695getLambda5$app_mobileDebug() {
        return f140lambda5;
    }

    /* renamed from: getLambda-6$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6696getLambda6$app_mobileDebug() {
        return f141lambda6;
    }
}
