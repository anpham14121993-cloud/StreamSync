package com.streamsync.app.ui.components;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenu_androidKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SettingComponents.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\n\u001a}\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\u000b\u001a\u0002H\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00070\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0018\b\u0002\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u0002H\b\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aU\u0010\u001a\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\u0018\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00152\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00070\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u001f\u001av\u0010 \u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00070\r2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010'\u001a\u00020\u00152\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010)\u001a\u00020*H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a?\u0010-\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u00032\u0014\u0010/\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00070\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u00100\u001a\u001f\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u001f\u001aX\u00103\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00032\u0006\u00104\u001a\u0002052\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\u00070\r2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00107\u001a\u0002052\n\b\u0002\u00108\u001a\u0004\u0018\u00010*H\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a\u0013\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020\u0015¢\u0006\u0002\u0010=\"#\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006>²\u0006\n\u0010?\u001a\u000205X\u008a\u008e\u0002"}, d2 = {"LanguageOptions", "", "Lkotlin/Pair;", "", "getLanguageOptions", "()Ljava/util/List;", "ChoiceRowGroup", "", ExifInterface.GPS_DIRECTION_TRUE, "label", "choices", "selected", "onSelect", "Lkotlin/Function1;", "optionLabel", "modifier", "Landroidx/compose/ui/Modifier;", "optionDescription", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ColorPreviewDot", "argb", "", "size", "Landroidx/compose/ui/unit/Dp;", "ColorPreviewDot-rAjV9yQ", "(IFLandroidx/compose/runtime/Composer;II)V", "ColorSwatchRow", "colors", "(Ljava/lang/String;Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "EmphasizedText", "text", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "LabeledSlider", "value", "", "valueText", "onValueChange", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "steps", "description", "valueColor", "Landroidx/compose/ui/graphics/Color;", "LabeledSlider-VRxQTpk", "(Ljava/lang/String;FLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/ui/Modifier;ILjava/lang/String;JLandroidx/compose/runtime/Composer;II)V", "LanguageDropdown", "selectedCode", "onSelected", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SectionHeader", "title", "SwitchSettingRow", "checked", "", "onCheckedChange", "enabled", "titleColor", "SwitchSettingRow-FV1VA1c", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Ljava/lang/String;ZLandroidx/compose/ui/graphics/Color;Landroidx/compose/runtime/Composer;II)V", "contrastTextColor", "background", "(I)J", "app_mobileDebug", "expanded"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class SettingComponentsKt {
    private static final List<Pair<String, String>> LanguageOptions = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("vi", "Tiếng Việt"), TuplesKt.to(TranslateLanguage.ENGLISH, "English"), TuplesKt.to(TranslateLanguage.JAPANESE, "日本語 (Nhật)"), TuplesKt.to(TranslateLanguage.KOREAN, "한국어 (Hàn)"), TuplesKt.to(TranslateLanguage.CHINESE, "中文 (Trung)"), TuplesKt.to(TranslateLanguage.FRENCH, "Français"), TuplesKt.to(TranslateLanguage.SPANISH, "Español"), TuplesKt.to(TranslateLanguage.THAI, "ภษาไทย (Thái)")});

    public static final List<Pair<String, String>> getLanguageOptions() {
        return LanguageOptions;
    }

    public static final void SectionHeader(final String title, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(-1692411295);
        ComposerKt.sourceInformation($composer3, "C(SectionHeader)P(1)58@2304L10,59@2358L11,56@2253L205:SettingComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1692411295, $dirty2, -1, "com.streamsync.app.ui.components.SectionHeader (SettingComponents.kt:55)");
            }
            Modifier modifier4 = modifier3;
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(title, PaddingKt.m565paddingVpY3zN4(modifier3, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(12)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleMedium(), $composer2, $dirty2 & 14, 0, 65528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$SectionHeader$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SettingComponentsKt.SectionHeader(title, modifier2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: SwitchSettingRow-FV1VA1c, reason: not valid java name */
    public static final void m6625SwitchSettingRowFV1VA1c(final String title, final boolean checked, final Function1<? super Boolean, Unit> onCheckedChange, String description, boolean enabled, Color titleColor, Composer $composer, final int $changed, final int i) {
        String str;
        boolean z;
        Color color;
        String description2;
        boolean enabled2;
        Color titleColor2;
        Object value$iv;
        Composer $composer$iv;
        long m3419unboximpl;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(onCheckedChange, "onCheckedChange");
        Composer $composer3 = $composer.startRestartGroup(-941274419);
        ComposerKt.sourceInformation($composer3, "C(SwitchSettingRow)P(4!1,3!,5:c#ui.graphics.Color)77@2836L29,73@2677L962:SettingComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(title) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(checked) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changedInstance(onCheckedChange) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            str = description;
        } else if (($changed & 3072) == 0) {
            str = description;
            $dirty |= $composer3.changed(str) ? 2048 : 1024;
        } else {
            str = description;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            color = titleColor;
        } else if ((196608 & $changed) == 0) {
            color = titleColor;
            $dirty |= $composer3.changed(color) ? 131072 : 65536;
        } else {
            color = titleColor;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            description2 = str;
            enabled2 = z;
            titleColor2 = color;
            $composer2 = $composer3;
        } else {
            description2 = i2 != 0 ? null : str;
            enabled2 = i3 != 0 ? true : z;
            titleColor2 = i4 != 0 ? null : color;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-941274419, $dirty2, -1, "com.streamsync.app.ui.components.SwitchSettingRow (SettingComponents.kt:72)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            $composer3.startReplaceableGroup(-1030946132);
            ComposerKt.sourceInformation($composer3, "CC(remember):SettingComponents.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 896) == 256) | (($dirty2 & 112) == 32);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0<Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$SwitchSettingRow$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        onCheckedChange.invoke(Boolean.valueOf(!checked));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer3.endReplaceableGroup();
            Modifier m565paddingVpY3zN4 = PaddingKt.m565paddingVpY3zN4(ClickableKt.m246clickableXHw0xAI$default(fillMaxWidth$default, enabled2, null, null, (Function0) value$iv, 6, null), Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8));
            $composer3.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation($composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, centerVertically, $composer3, ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 14) | ((RendererCapabilities.DECODER_SUPPORT_MASK >> 3) & 112));
            int $changed$iv$iv = (RendererCapabilities.DECODER_SUPPORT_MASK << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv = LayoutKt.modifierMaterializerOf(m565paddingVpY3zN4);
            int $changed$iv$iv$iv = (($changed$iv$iv << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(factory$iv$iv$iv);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m2939constructorimpl($composer3);
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
            }
            skippableUpdate$iv$iv$iv.invoke(SkippableUpdater.m2930boximpl(SkippableUpdater.m2931constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i5 = ($changed$iv$iv$iv >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i6 = ((RendererCapabilities.DECODER_SUPPORT_MASK >> 6) & 112) | 6;
            RowScope $this$SwitchSettingRow_FV1VA1c_u24lambda_u242 = rowScope;
            ComposerKt.sourceInformationMarkerStart($composer3, 683868832, "C80@2940L569,95@3518L27,96@3554L79:SettingComponents.kt#qvfdnh");
            Modifier modifier$iv = RowScope.weight$default($this$SwitchSettingRow_FV1VA1c_u24lambda_u242, Modifier.INSTANCE, 1.0f, false, 2, null);
            $composer3.startReplaceableGroup(-483455358);
            ComposerKt.sourceInformation($composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv2 = (0 << 3) & 112;
            $composer3.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation($composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Function0 factory$iv$iv$iv2 = ComposeUiNode.INSTANCE.getConstructor();
            Function3 skippableUpdate$iv$iv$iv2 = LayoutKt.modifierMaterializerOf(modifier$iv);
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 9) & 7168) | 6;
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(factory$iv$iv$iv2);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m2939constructorimpl($composer3);
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2946setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv$iv2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), block$iv$iv$iv2);
            }
            skippableUpdate$iv$iv$iv2.invoke(SkippableUpdater.m2930boximpl(SkippableUpdater.m2931constructorimpl($composer3)), $composer3, Integer.valueOf(($changed$iv$iv$iv2 >> 3) & 112));
            $composer3.startReplaceableGroup(2058660585);
            int i7 = ($changed$iv$iv$iv2 >> 9) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i8 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1875170033, "C83@3049L10,81@2982L247:SettingComponents.kt#qvfdnh");
            TextStyle bodyLarge = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyLarge();
            $composer3.startReplaceableGroup(-1879170075);
            ComposerKt.sourceInformation($composer3, "");
            if (titleColor2 == null) {
                if (enabled2) {
                    $composer3.startReplaceableGroup(-1879168108);
                    ComposerKt.sourceInformation($composer3, "85@3160L7");
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    $composer$iv = $composer3;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    m3419unboximpl = ((Color) consume).m3419unboximpl();
                } else {
                    $composer$iv = $composer3;
                    $composer3.startReplaceableGroup(-1879166851);
                    ComposerKt.sourceInformation($composer3, "85@3187L11");
                    m3419unboximpl = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant();
                }
                $composer3.endReplaceableGroup();
            } else {
                $composer$iv = $composer3;
                m3419unboximpl = titleColor2.m3419unboximpl();
            }
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            Composer $composer$iv2 = $composer$iv;
            TextKt.m2127Text4IGK_g(title, (Modifier) null, m3419unboximpl, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodyLarge, $composer3, $dirty2 & 14, 0, 65530);
            $composer3.startReplaceableGroup(-1879165234);
            ComposerKt.sourceInformation($composer3, "90@3375L10,91@3439L11,88@3294L191");
            String str2 = description2;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                TextKt.m2127Text4IGK_g(description2, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodySmall(), $composer3, ($dirty2 >> 9) & 14, 0, 65530);
            }
            $composer3.endReplaceableGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            $composer3.endReplaceableGroup();
            $composer3.endNode();
            $composer3.endReplaceableGroup();
            $composer3.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(8)), $composer3, 6);
            SwitchKt.Switch(checked, onCheckedChange, null, null, enabled2, null, null, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | ($dirty2 & 57344), AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endReplaceableGroup();
            $composer2.endNode();
            $composer2.endReplaceableGroup();
            $composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final String str3 = description2;
            final boolean z2 = enabled2;
            final Color color2 = titleColor2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$SwitchSettingRow$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    SettingComponentsKt.m6625SwitchSettingRowFV1VA1c(title, checked, onCheckedChange, str3, z2, color2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x06cf  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x067f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x05fb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x05ac  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x06b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> void ChoiceRowGroup(final java.lang.String r104, final java.util.List<? extends T> r105, final T r106, final kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r107, final kotlin.jvm.functions.Function1<? super T, java.lang.String> r108, androidx.compose.ui.Modifier r109, kotlin.jvm.functions.Function1<? super T, java.lang.String> r110, androidx.compose.runtime.Composer r111, final int r112, final int r113) {
        /*
            Method dump skipped, instructions count: 1970
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.SettingComponentsKt.ChoiceRowGroup(java.lang.String, java.util.List, java.lang.Object, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x053f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x035b  */
    /* renamed from: LabeledSlider-VRxQTpk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6624LabeledSliderVRxQTpk(final java.lang.String r100, final float r101, final java.lang.String r102, final kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r103, final kotlin.ranges.ClosedFloatingPointRange<java.lang.Float> r104, androidx.compose.ui.Modifier r105, int r106, java.lang.String r107, long r108, androidx.compose.runtime.Composer r110, final int r111, final int r112) {
        /*
            Method dump skipped, instructions count: 1502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.SettingComponentsKt.m6624LabeledSliderVRxQTpk(java.lang.String, float, java.lang.String, kotlin.jvm.functions.Function1, kotlin.ranges.ClosedFloatingPointRange, androidx.compose.ui.Modifier, int, java.lang.String, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0546 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x05b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ColorSwatchRow(final java.lang.String r75, final java.util.List<kotlin.Pair<java.lang.Integer, java.lang.String>> r76, final int r77, final kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r78, androidx.compose.ui.Modifier r79, androidx.compose.runtime.Composer r80, final int r81, final int r82) {
        /*
            Method dump skipped, instructions count: 1498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.SettingComponentsKt.ColorSwatchRow(java.lang.String, java.util.List, int, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: ColorPreviewDot-rAjV9yQ, reason: not valid java name */
    public static final void m6623ColorPreviewDotrAjV9yQ(final int argb, final float size, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(1638711918);
        ComposerKt.sourceInformation($composer2, "C(ColorPreviewDot)P(!,1:c#ui.unit.Dp)265@9653L11,260@9505L187:SettingComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(argb) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(size) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (i2 != 0) {
                size = Dp.m5736constructorimpl(16);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1638711918, $dirty, -1, "com.streamsync.app.ui.components.ColorPreviewDot (SettingComponents.kt:258)");
            }
            long color = ColorKt.Color(argb);
            BoxKt.Box(BorderKt.m223borderxT4_qwU(BackgroundKt.m212backgroundbw27NRU$default(ClipKt.clip(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, size), RoundedCornerShapeKt.getCircleShape()), color, null, 2, null), Dp.m5736constructorimpl(1), MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getOutline(), RoundedCornerShapeKt.getCircleShape()), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$ColorPreviewDot$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SettingComponentsKt.m6623ColorPreviewDotrAjV9yQ(argb, size, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final long contrastTextColor(int background) {
        return ColorKt.m3461luminance8_81llA(ColorKt.Color(background)) > 0.5f ? Color.INSTANCE.m3435getBlack0d7_KjU() : Color.INSTANCE.m3446getWhite0d7_KjU();
    }

    public static final void EmphasizedText(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(396709338);
        ComposerKt.sourceInformation($composer3, "C(EmphasizedText)P(1)278@10091L10,276@10041L146:SettingComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer3.getSkipping()) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(396709338, $dirty2, -1, "com.streamsync.app.ui.components.EmphasizedText (SettingComponents.kt:275)");
            }
            $composer2 = $composer3;
            TextKt.m2127Text4IGK_g(text, modifier3, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getBodyMedium(), $composer2, ($dirty2 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty2 & 112), 0, 65500);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$EmphasizedText$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SettingComponentsKt.EmphasizedText(text, modifier2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void LanguageDropdown(final String label, final String selectedCode, final Function1<? super String, Unit> onSelected, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object value$iv;
        Object element$iv;
        final String selectedLabel;
        Object value$iv2;
        Modifier modifier3;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onSelected, "onSelected");
        Composer $composer2 = $composer.startRestartGroup(-1926744690);
        ComposerKt.sourceInformation($composer2, "C(LanguageDropdown)P(!1,3,2)292@10404L34,297@10628L17,295@10548L1073:SettingComponents.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(label) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selectedCode) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(onSelected) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1926744690, $dirty2, -1, "com.streamsync.app.ui.components.LanguageDropdown (SettingComponents.kt:291)");
            }
            $composer2.startReplaceableGroup(550919612);
            ComposerKt.sourceInformation($composer2, "CC(remember):SettingComponents.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState expanded$delegate = (MutableState) value$iv;
            $composer2.endReplaceableGroup();
            Iterable $this$firstOrNull$iv = LanguageOptions;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    element$iv = null;
                    break;
                }
                element$iv = it.next();
                Pair it2 = (Pair) element$iv;
                if (Intrinsics.areEqual(it2.getFirst(), selectedCode)) {
                    break;
                }
            }
            Pair pair = (Pair) element$iv;
            if (pair == null || (selectedLabel = (String) pair.getSecond()) == null) {
                selectedLabel = "Không đặt";
            }
            boolean LanguageDropdown$lambda$18 = LanguageDropdown$lambda$18(expanded$delegate);
            $composer2.startReplaceableGroup(550926763);
            ComposerKt.sourceInformation($composer2, "CC(remember):SettingComponents.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function1) new Function1<Boolean, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean it3) {
                        SettingComponentsKt.LanguageDropdown$lambda$19(expanded$delegate, it3);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer2.endReplaceableGroup();
            ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox(LanguageDropdown$lambda$18, (Function1) value$iv2, PaddingKt.m565paddingVpY3zN4(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), ComposableLambdaKt.composableLambda($composer2, -885116764, true, new Function3<ExposedDropdownMenuBoxScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Composer composer, Integer num) {
                    invoke(exposedDropdownMenuBoxScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer3, int $changed2) {
                    boolean LanguageDropdown$lambda$182;
                    Object value$iv3;
                    Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
                    ComposerKt.sourceInformation($composer3, "C302@10777L349,312@11195L20,312@11135L480:SettingComponents.kt#qvfdnh");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= ($changed2 & 8) == 0 ? $composer3.changed(ExposedDropdownMenuBox) : $composer3.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (($dirty4 & 19) == 18 && $composer3.getSkipping()) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-885116764, $dirty4, -1, "com.streamsync.app.ui.components.LanguageDropdown.<anonymous> (SettingComponents.kt:302)");
                    }
                    Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
                    String str = selectedLabel;
                    AnonymousClass1 anonymousClass1 = new Function1<String, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                            invoke2(str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String it3) {
                            Intrinsics.checkNotNullParameter(it3, "it");
                        }
                    };
                    final String str2 = label;
                    ComposableLambda composableLambda = ComposableLambdaKt.composableLambda($composer3, -1511939650, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C306@10914L11:SettingComponents.kt#qvfdnh");
                            if (($changed3 & 3) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1511939650, $changed3, -1, "com.streamsync.app.ui.components.LanguageDropdown.<anonymous>.<anonymous> (SettingComponents.kt:306)");
                            }
                            TextKt.m2127Text4IGK_g(str2, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer4, 0, 0, 131070);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    });
                    final MutableState<Boolean> mutableState = expanded$delegate;
                    OutlinedTextFieldKt.OutlinedTextField(str, (Function1<? super String, Unit>) anonymousClass1, fillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) composableLambda, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambda($composer3, 1191264155, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            boolean LanguageDropdown$lambda$183;
                            ComposerKt.sourceInformation($composer4, "C307@10986L33:SettingComponents.kt#qvfdnh");
                            if (($changed3 & 3) == 2 && $composer4.getSkipping()) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1191264155, $changed3, -1, "com.streamsync.app.ui.components.LanguageDropdown.<anonymous>.<anonymous> (SettingComponents.kt:307)");
                            }
                            ExposedDropdownMenuDefaults exposedDropdownMenuDefaults = ExposedDropdownMenuDefaults.INSTANCE;
                            LanguageDropdown$lambda$183 = SettingComponentsKt.LanguageDropdown$lambda$18(mutableState);
                            exposedDropdownMenuDefaults.TrailingIcon(LanguageDropdown$lambda$183, $composer4, ExposedDropdownMenuDefaults.$stable << 3);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 806903856, 0, 0, 8388008);
                    LanguageDropdown$lambda$182 = SettingComponentsKt.LanguageDropdown$lambda$18(expanded$delegate);
                    $composer3.startReplaceableGroup(1802054541);
                    ComposerKt.sourceInformation($composer3, "CC(remember):SettingComponents.kt#9igjgp");
                    final MutableState<Boolean> mutableState2 = expanded$delegate;
                    Object it$iv3 = $composer3.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2$4$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                SettingComponentsKt.LanguageDropdown$lambda$19(mutableState2, false);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    $composer3.endReplaceableGroup();
                    final Function1<String, Unit> function1 = onSelected;
                    final MutableState<Boolean> mutableState3 = expanded$delegate;
                    ExposedDropdownMenuBox.ExposedDropdownMenu(LanguageDropdown$lambda$182, (Function0) value$iv3, null, null, ComposableLambdaKt.composableLambda($composer3, -2014035211, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                            invoke(columnScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(ColumnScope ExposedDropdownMenu, Composer $composer4, int $changed3) {
                            Object value$iv4;
                            Object value$iv5;
                            Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
                            ComposerKt.sourceInformation($composer4, "C315@11321L38,313@11231L142,*320@11535L38,318@11444L147:SettingComponents.kt#qvfdnh");
                            if (($changed3 & 17) != 16 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2014035211, $changed3, -1, "com.streamsync.app.ui.components.LanguageDropdown.<anonymous>.<anonymous> (SettingComponents.kt:313)");
                                }
                                Function2<Composer, Integer, Unit> m6622getLambda1$app_mobileDebug = ComposableSingletons$SettingComponentsKt.INSTANCE.m6622getLambda1$app_mobileDebug();
                                $composer4.startReplaceableGroup(1076895710);
                                ComposerKt.sourceInformation($composer4, "CC(remember):SettingComponents.kt#9igjgp");
                                boolean invalid$iv = $composer4.changed(function1);
                                final Function1<String, Unit> function12 = function1;
                                final MutableState<Boolean> mutableState4 = mutableState3;
                                Object it$iv4 = $composer4.rememberedValue();
                                if (invalid$iv || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                    value$iv4 = new Function0<Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2$5$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        /* JADX WARN: Multi-variable type inference failed */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function12.invoke(null);
                                            SettingComponentsKt.LanguageDropdown$lambda$19(mutableState4, false);
                                        }
                                    };
                                    $composer4.updateRememberedValue(value$iv4);
                                } else {
                                    value$iv4 = it$iv4;
                                }
                                $composer4.endReplaceableGroup();
                                AndroidMenu_androidKt.DropdownMenuItem(m6622getLambda1$app_mobileDebug, (Function0) value$iv4, null, null, null, false, null, null, null, $composer4, 6, 508);
                                Iterable $this$forEach$iv = SettingComponentsKt.getLanguageOptions();
                                final Function1<String, Unit> function13 = function1;
                                final MutableState<Boolean> mutableState5 = mutableState3;
                                for (Object element$iv2 : $this$forEach$iv) {
                                    Pair pair2 = (Pair) element$iv2;
                                    final String code = (String) pair2.component1();
                                    final String name = (String) pair2.component2();
                                    ComposableLambda composableLambda2 = ComposableLambdaKt.composableLambda($composer4, -1573444916, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2$5$2$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer5, int $changed4) {
                                            ComposerKt.sourceInformation($composer5, "C319@11491L10:SettingComponents.kt#qvfdnh");
                                            if (($changed4 & 3) == 2 && $composer5.getSkipping()) {
                                                $composer5.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1573444916, $changed4, -1, "com.streamsync.app.ui.components.LanguageDropdown.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingComponents.kt:319)");
                                            }
                                            TextKt.m2127Text4IGK_g(name, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer5, 0, 0, 131070);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    });
                                    $composer4.startReplaceableGroup(549542045);
                                    ComposerKt.sourceInformation($composer4, "CC(remember):SettingComponents.kt#9igjgp");
                                    boolean invalid$iv2 = $composer4.changed(function13) | $composer4.changed(code);
                                    Object it$iv5 = $composer4.rememberedValue();
                                    if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                                        value$iv5 = new Function0<Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$2$5$2$2$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            /* JADX WARN: Multi-variable type inference failed */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                function13.invoke(code);
                                                SettingComponentsKt.LanguageDropdown$lambda$19(mutableState5, false);
                                            }
                                        };
                                        $composer4.updateRememberedValue(value$iv5);
                                    } else {
                                        value$iv5 = it$iv5;
                                    }
                                    $composer4.endReplaceableGroup();
                                    AndroidMenu_androidKt.DropdownMenuItem(composableLambda2, (Function0) value$iv5, null, null, null, false, null, null, null, $composer4, 6, 508);
                                    function13 = function13;
                                    mutableState5 = mutableState5;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }), $composer3, (ExposedDropdownMenuBoxScope.$stable << 15) | 24624 | (($dirty4 << 15) & 458752), 12);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), $composer2, 3120, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.SettingComponentsKt$LanguageDropdown$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    SettingComponentsKt.LanguageDropdown(label, selectedCode, onSelected, modifier5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LanguageDropdown$lambda$18(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LanguageDropdown$lambda$19(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }
}
