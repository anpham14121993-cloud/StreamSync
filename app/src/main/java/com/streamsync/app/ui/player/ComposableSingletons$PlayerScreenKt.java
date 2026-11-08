package com.streamsync.app.ui.player;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.media3.ui.DefaultTimeBar;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class ComposableSingletons$PlayerScreenKt {
    public static final ComposableSingletons$PlayerScreenKt INSTANCE = new ComposableSingletons$PlayerScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f85lambda1 = ComposableLambdaKt.composableLambdaInstance(1818649457, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2887@132761L94:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1818649457, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-1.<anonymous> (PlayerScreen.kt:2887)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f96lambda2 = ComposableLambdaKt.composableLambdaInstance(-2007242327, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C2919@134295L82:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2007242327, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-2.<anonymous> (PlayerScreen.kt:2919)");
            }
            IconKt.m1600Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), "Thông số phim", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f107lambda3 = ComposableLambdaKt.composableLambdaInstance(-1521188491, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C2944@135640L48:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1521188491, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-3.<anonymous> (PlayerScreen.kt:2944)");
            }
            TextKt.m2127Text4IGK_g("Dừng sớm và áp dụng", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-4, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f110lambda4 = ComposableLambdaKt.composableLambdaInstance(1607651103, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C3097@142445L36:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1607651103, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-4.<anonymous> (PlayerScreen.kt:3097)");
            }
            TextKt.m2127Text4IGK_g("Thử lại", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-5, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f111lambda5 = ComposableLambdaKt.composableLambdaInstance(988023153, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C3835@174162L14:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(988023153, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-5.<anonymous> (PlayerScreen.kt:3835)");
            }
            TextKt.m2127Text4IGK_g("Phụ đề", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-6, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f112lambda6 = ComposableLambdaKt.composableLambdaInstance(-990816550, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C3840@174338L15:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-990816550, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-6.<anonymous> (PlayerScreen.kt:3840)");
            }
            TextKt.m2127Text4IGK_g("Đồng bộ", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-7, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f113lambda7 = ComposableLambdaKt.composableLambdaInstance(2050765850, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C3843@174442L59:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2050765850, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-7.<anonymous> (PlayerScreen.kt:3843)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Đóng bảng", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-8, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f114lambda8 = ComposableLambdaKt.composableLambdaInstance(-916114260, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-8$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3910@177030L10:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-916114260, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-8.<anonymous> (PlayerScreen.kt:3910)");
            }
            TextKt.m2127Text4IGK_g("A-", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-9, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f115lambda9 = ComposableLambdaKt.composableLambdaInstance(-698194717, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-9$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3916@177365L10:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-698194717, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-9.<anonymous> (PlayerScreen.kt:3916)");
            }
            TextKt.m2127Text4IGK_g("A+", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-10, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f86lambda10 = ComposableLambdaKt.composableLambdaInstance(1140376611, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-10$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3930@177905L9:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1140376611, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-10.<anonymous> (PlayerScreen.kt:3930)");
            }
            TextKt.m2127Text4IGK_g("▼", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-11, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f87lambda11 = ComposableLambdaKt.composableLambdaInstance(107659930, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-11$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3936@178271L9:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(107659930, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-11.<anonymous> (PlayerScreen.kt:3936)");
            }
            TextKt.m2127Text4IGK_g("▲", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-12, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f88lambda12 = ComposableLambdaKt.composableLambdaInstance(732352996, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-12$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3946@178729L9:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(732352996, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-12.<anonymous> (PlayerScreen.kt:3946)");
            }
            TextKt.m2127Text4IGK_g("-", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-13, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f89lambda13 = ComposableLambdaKt.composableLambdaInstance(-300363685, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-13$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C3952@179065L9:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-300363685, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-13.<anonymous> (PlayerScreen.kt:3952)");
            }
            TextKt.m2127Text4IGK_g("+", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-14, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f90lambda14 = ComposableLambdaKt.composableLambdaInstance(-1998711367, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-14$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4066@183121L12:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1998711367, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-14.<anonymous> (PlayerScreen.kt:4066)");
            }
            TextKt.m2127Text4IGK_g("−100", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-15, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f91lambda15 = ComposableLambdaKt.composableLambdaInstance(1303143600, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-15$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4067@183196L12:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1303143600, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-15.<anonymous> (PlayerScreen.kt:4067)");
            }
            TextKt.m2127Text4IGK_g("+100", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-16, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f92lambda16 = ComposableLambdaKt.composableLambdaInstance(-852983823, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-16$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4068@183272L13:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-852983823, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-16.<anonymous> (PlayerScreen.kt:4068)");
            }
            TextKt.m2127Text4IGK_g("−0,5s", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-17, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f93lambda17 = ComposableLambdaKt.composableLambdaInstance(1285856050, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-17$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4069@183348L13:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1285856050, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-17.<anonymous> (PlayerScreen.kt:4069)");
            }
            TextKt.m2127Text4IGK_g("+0,5s", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-18, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f94lambda18 = ComposableLambdaKt.composableLambdaInstance(-1670251794, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-18$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C4070@183418L15:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1670251794, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-18.<anonymous> (PlayerScreen.kt:4070)");
            }
            TextKt.m2127Text4IGK_g("Đặt lại", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-19, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f95lambda19 = ComposableLambdaKt.composableLambdaInstance(-248969462, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-19$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4117@185884L23:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-248969462, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-19.<anonymous> (PlayerScreen.kt:4117)");
            }
            TextKt.m2127Text4IGK_g("Dừng và so khớp", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-20, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f97lambda20 = ComposableLambdaKt.composableLambdaInstance(-130106137, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-20$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope TextButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
            ComposerKt.sourceInformation($composer, "C4123@186108L22:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-130106137, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-20.<anonymous> (PlayerScreen.kt:4123)");
            }
            TextKt.m2127Text4IGK_g("Dừng Auto sync", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-21, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f98lambda21 = ComposableLambdaKt.composableLambdaInstance(842051565, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-21$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4338@194200L10,4335@193997L374:PlayerScreen.kt#nsz36g");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(842051565, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-21.<anonymous> (PlayerScreen.kt:4335)");
                }
                TextKt.m2127Text4IGK_g("Chưa đọc được track âm thanh. Thường là stream chưa bóc xong hoặc đang ở chế độ bitstream passthrough.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8)), ColorKt.Color(4289773253L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 438, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-22, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f99lambda22 = ComposableLambdaKt.composableLambdaInstance(1866981786, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-22$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4380@195761L10,4378@195672L261:PlayerScreen.kt#nsz36g");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1866981786, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-22.<anonymous> (PlayerScreen.kt:4378)");
                }
                TextKt.m2127Text4IGK_g("Đang hỏi addon...", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(12)), ColorKt.Color(4289773253L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 438, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-23, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f100lambda23 = ComposableLambdaKt.composableLambdaInstance(26976403, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-23$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4422@197334L10,4420@197213L293:PlayerScreen.kt#nsz36g");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(26976403, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-23.<anonymous> (PlayerScreen.kt:4420)");
                }
                TextKt.m2127Text4IGK_g("Addon không trả về danh sách tập cho phim bộ này.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(12)), ColorKt.Color(4289773253L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 438, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-24, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f101lambda24 = ComposableLambdaKt.composableLambdaInstance(-219590077, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-24$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4529@201674L29:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-219590077, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-24.<anonymous> (PlayerScreen.kt:4529)");
            }
            SpacerKt.Spacer(SizeKt.m599height3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(8)), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-25, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f102lambda25 = ComposableLambdaKt.composableLambdaInstance(-459851361, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-25$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C4558@202649L74:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-459851361, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-25.<anonymous> (PlayerScreen.kt:4558)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Đóng", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-26, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f103lambda26 = ComposableLambdaKt.composableLambdaInstance(1509082735, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-26$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C4649@205582L74:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1509082735, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-26.<anonymous> (PlayerScreen.kt:4649)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Đóng", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-27, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f104lambda27 = ComposableLambdaKt.composableLambdaInstance(-988666558, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-27$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C4759@210191L74:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-988666558, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-27.<anonymous> (PlayerScreen.kt:4759)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Đóng", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-28, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f105lambda28 = ComposableLambdaKt.composableLambdaInstance(1774866488, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-28$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4772@210804L10,4773@210878L11,4770@210701L316:PlayerScreen.kt#nsz36g");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1774866488, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-28.<anonymous> (PlayerScreen.kt:4770)");
                }
                TextKt.m2127Text4IGK_g("Trong file phim", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(6)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).m1376getPrimary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-29, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f106lambda29 = ComposableLambdaKt.composableLambdaInstance(-802075648, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-29$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            ComposerKt.sourceInformation($composer, "C4811@212811L181,4817@213120L10,4818@213194L11,4815@213017L316:PlayerScreen.kt#nsz36g");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-802075648, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-29.<anonymous> (PlayerScreen.kt:4811)");
                }
                DividerKt.m1527HorizontalDivider9IZ8Weo(PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(6)), 0.0f, ColorKt.Color(DefaultTimeBar.DEFAULT_UNPLAYED_COLOR), $composer, 390, 2);
                TextKt.m2127Text4IGK_g("Từ addon phụ đề", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(6)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).m1376getPrimary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 65528);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer.skipToGroupEnd();
        }
    });

    /* renamed from: lambda-30, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f108lambda30 = ComposableLambdaKt.composableLambdaInstance(1078403321, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-30$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope OutlinedButton, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
            ComposerKt.sourceInformation($composer, "C4885@216741L31:PlayerScreen.kt#nsz36g");
            if (($changed & 17) == 16 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1078403321, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-30.<anonymous> (PlayerScreen.kt:4885)");
            }
            TextKt.m2127Text4IGK_g("Tải lại từ addon phụ đề", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-31, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f109lambda31 = ComposableLambdaKt.composableLambdaInstance(1139074855, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt$lambda-31$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C4920@217871L83:PlayerScreen.kt#nsz36g");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1139074855, $changed, -1, "com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.lambda-31.<anonymous> (PlayerScreen.kt:4920)");
            }
            IconKt.m1600Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Đóng thông số", (Modifier) null, Color.INSTANCE.m3446getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6636getLambda1$app_mobileDebug() {
        return f85lambda1;
    }

    /* renamed from: getLambda-10$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6637getLambda10$app_mobileDebug() {
        return f86lambda10;
    }

    /* renamed from: getLambda-11$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6638getLambda11$app_mobileDebug() {
        return f87lambda11;
    }

    /* renamed from: getLambda-12$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6639getLambda12$app_mobileDebug() {
        return f88lambda12;
    }

    /* renamed from: getLambda-13$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6640getLambda13$app_mobileDebug() {
        return f89lambda13;
    }

    /* renamed from: getLambda-14$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6641getLambda14$app_mobileDebug() {
        return f90lambda14;
    }

    /* renamed from: getLambda-15$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6642getLambda15$app_mobileDebug() {
        return f91lambda15;
    }

    /* renamed from: getLambda-16$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6643getLambda16$app_mobileDebug() {
        return f92lambda16;
    }

    /* renamed from: getLambda-17$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6644getLambda17$app_mobileDebug() {
        return f93lambda17;
    }

    /* renamed from: getLambda-18$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6645getLambda18$app_mobileDebug() {
        return f94lambda18;
    }

    /* renamed from: getLambda-19$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6646getLambda19$app_mobileDebug() {
        return f95lambda19;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6647getLambda2$app_mobileDebug() {
        return f96lambda2;
    }

    /* renamed from: getLambda-20$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6648getLambda20$app_mobileDebug() {
        return f97lambda20;
    }

    /* renamed from: getLambda-21$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6649getLambda21$app_mobileDebug() {
        return f98lambda21;
    }

    /* renamed from: getLambda-22$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6650getLambda22$app_mobileDebug() {
        return f99lambda22;
    }

    /* renamed from: getLambda-23$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6651getLambda23$app_mobileDebug() {
        return f100lambda23;
    }

    /* renamed from: getLambda-24$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6652getLambda24$app_mobileDebug() {
        return f101lambda24;
    }

    /* renamed from: getLambda-25$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6653getLambda25$app_mobileDebug() {
        return f102lambda25;
    }

    /* renamed from: getLambda-26$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6654getLambda26$app_mobileDebug() {
        return f103lambda26;
    }

    /* renamed from: getLambda-27$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6655getLambda27$app_mobileDebug() {
        return f104lambda27;
    }

    /* renamed from: getLambda-28$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6656getLambda28$app_mobileDebug() {
        return f105lambda28;
    }

    /* renamed from: getLambda-29$app_mobileDebug, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m6657getLambda29$app_mobileDebug() {
        return f106lambda29;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6658getLambda3$app_mobileDebug() {
        return f107lambda3;
    }

    /* renamed from: getLambda-30$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6659getLambda30$app_mobileDebug() {
        return f108lambda30;
    }

    /* renamed from: getLambda-31$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6660getLambda31$app_mobileDebug() {
        return f109lambda31;
    }

    /* renamed from: getLambda-4$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6661getLambda4$app_mobileDebug() {
        return f110lambda4;
    }

    /* renamed from: getLambda-5$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6662getLambda5$app_mobileDebug() {
        return f111lambda5;
    }

    /* renamed from: getLambda-6$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6663getLambda6$app_mobileDebug() {
        return f112lambda6;
    }

    /* renamed from: getLambda-7$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6664getLambda7$app_mobileDebug() {
        return f113lambda7;
    }

    /* renamed from: getLambda-8$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6665getLambda8$app_mobileDebug() {
        return f114lambda8;
    }

    /* renamed from: getLambda-9$app_mobileDebug, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m6666getLambda9$app_mobileDebug() {
        return f115lambda9;
    }
}
