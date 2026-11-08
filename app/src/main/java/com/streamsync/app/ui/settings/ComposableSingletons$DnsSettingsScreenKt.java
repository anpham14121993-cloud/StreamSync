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

/* compiled from: DnsSettingsScreen.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class ComposableSingletons$DnsSettingsScreenKt {
    public static final ComposableSingletons$DnsSettingsScreenKt INSTANCE = new ComposableSingletons$DnsSettingsScreenKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f130lambda1 = ComposableLambdaKt.composableLambdaInstance(1137546952, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C52@2172L24:DnsSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1137546952, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt.lambda-1.<anonymous> (DnsSettingsScreen.kt:52)");
            }
            TextKt.m2127Text4IGK_g("DNS tích hợp sẵn", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f131lambda2 = ComposableLambdaKt.composableLambdaInstance(1470848329, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C55@2310L74:DnsSettingsScreen.kt#bv4dau");
            if (($changed & 3) == 2 && $composer.getSkipping()) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1470848329, $changed, -1, "com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt.lambda-2.<anonymous> (DnsSettingsScreen.kt:55)");
            }
            IconKt.m1600Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Quay lại", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f132lambda3 = ComposableLambdaKt.composableLambdaInstance(1701242441, false, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x020e  */
        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(androidx.compose.runtime.Composer r76, int r77) {
            /*
                Method dump skipped, instructions count: 530
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.ComposableSingletons$DnsSettingsScreenKt$lambda3$1.invoke(androidx.compose.runtime.Composer, int):void");
        }
    });

    /* renamed from: getLambda-1$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6685getLambda1$app_mobileDebug() {
        return f130lambda1;
    }

    /* renamed from: getLambda-2$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6686getLambda2$app_mobileDebug() {
        return f131lambda2;
    }

    /* renamed from: getLambda-3$app_mobileDebug, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6687getLambda3$app_mobileDebug() {
        return f132lambda3;
    }
}
