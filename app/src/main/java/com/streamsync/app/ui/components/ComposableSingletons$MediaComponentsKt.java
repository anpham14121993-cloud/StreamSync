package com.streamsync.app.ui.components;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material3.IconKt;
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
import androidx.media3.extractor.WavUtil;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaComponents.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class ComposableSingletons$MediaComponentsKt {
    public static final ComposableSingletons$MediaComponentsKt INSTANCE = new ComposableSingletons$MediaComponentsKt();

    /* renamed from: lambda-1, reason: not valid java name */
    public static Function3<RowScope, Composer, Integer, Unit> f74lambda1 = ComposableLambdaKt.composableLambdaInstance(-73752459, false, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.ComposableSingletons$MediaComponentsKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
            invoke(rowScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(RowScope Button, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C647@28982L178,652@29181L28,655@29319L10,656@29402L10,653@29230L250:MediaComponents.kt#qvfdnh");
            if (($changed & 17) != 16 || !$composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-73752459, $changed, -1, "com.streamsync.app.ui.components.ComposableSingletons$MediaComponentsKt.lambda-1.<anonymous> (MediaComponents.kt:647)");
                }
                IconKt.m1600Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(19)), 0L, $composer, 432, 8);
                SpacerKt.Spacer(SizeKt.m618width3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(8)), $composer, 6);
                TextKt.m2127Text4IGK_g("Thông tin", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, TextStyle.m5248copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall().m5266getFontSizeXSAIIZE(), null, null, null, 0, 0, null, 16646143, null), $composer, 6, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
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
    public final Function3<RowScope, Composer, Integer, Unit> m6621getLambda1$app_mobileDebug() {
        return f74lambda1;
    }
}
