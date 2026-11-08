package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.SearchBarTokens;
import androidx.compose.material3.tokens.SearchViewTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* compiled from: SearchBar.android.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020!H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u008a\u0001\u0010 \u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020\u001e2\b\b\u0002\u0010%\u001a\u00020\u001e2\b\b\u0002\u0010&\u001a\u00020\u001e2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020\u001e2\b\b\u0002\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\u001e2\b\b\u0002\u0010,\u001a\u00020\u001e2\b\b\u0002\u0010-\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u001e2\b\b\u0002\u0010/\u001a\u00020\u001e2\b\b\u0002\u00100\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u009e\u0001\u0010 \u001a\u00020!2\b\b\u0002\u00103\u001a\u00020\u001e2\b\b\u0002\u00104\u001a\u00020\u001e2\b\b\u0002\u0010%\u001a\u00020\u001e2\b\b\u0002\u0010&\u001a\u00020\u001e2\b\b\u0002\u0010'\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020\u001e2\b\b\u0002\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\u001e2\b\b\u0002\u0010,\u001a\u00020\u001e2\b\b\u0002\u0010-\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u001e2\b\b\u0002\u00105\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u00020\u001e2\b\b\u0002\u00100\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b7\u00108R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00069"}, d2 = {"Landroidx/compose/material3/SearchBarDefaults;", "", "()V", "Elevation", "Landroidx/compose/ui/unit/Dp;", "getElevation-D9Ej5fM$annotations", "getElevation-D9Ej5fM", "()F", "F", "InputFieldHeight", "getInputFieldHeight-D9Ej5fM", "ShadowElevation", "getShadowElevation-D9Ej5fM", "TonalElevation", "getTonalElevation-D9Ej5fM", "dockedShape", "Landroidx/compose/ui/graphics/Shape;", "getDockedShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "fullScreenShape", "getFullScreenShape", "inputFieldShape", "getInputFieldShape", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "colors", "Landroidx/compose/material3/SearchBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "dividerColor", "inputFieldColors", "Landroidx/compose/material3/TextFieldColors;", "colors-Klgx-Pg", "(JJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "textColor", "disabledTextColor", "cursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "placeholderColor", "disabledPlaceholderColor", "inputFieldColors--u-KgnY", "(JJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "unfocusedTextColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "inputFieldColors-ITpI4ow", "(JJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class SearchBarDefaults {
    public static final int $stable = 0;
    public static final SearchBarDefaults INSTANCE = new SearchBarDefaults();
    private static final float TonalElevation = SearchBarTokens.INSTANCE.m2774getContainerElevationD9Ej5fM();
    private static final float ShadowElevation = ElevationTokens.INSTANCE.m2474getLevel0D9Ej5fM();
    private static final float Elevation = TonalElevation;
    private static final float InputFieldHeight = SearchBarTokens.INSTANCE.m2775getContainerHeightD9Ej5fM();

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to TonalElevation. Not to be confused with ShadowElevation.", replaceWith = @ReplaceWith(expression = "TonalElevation", imports = {}))
    /* renamed from: getElevation-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m1792getElevationD9Ej5fM$annotations() {
    }

    private SearchBarDefaults() {
    }

    /* renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m1797getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    /* renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m1796getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    /* renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m1794getElevationD9Ej5fM() {
        return Elevation;
    }

    /* renamed from: getInputFieldHeight-D9Ej5fM, reason: not valid java name */
    public final float m1795getInputFieldHeightD9Ej5fM() {
        return InputFieldHeight;
    }

    public final Shape getInputFieldShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(-971556142);
        ComposerKt.sourceInformation($composer, "C523@24182L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-971556142, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-inputFieldShape> (SearchBar.android.kt:523)");
        }
        Shape value = ShapesKt.getValue(SearchBarTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    public final Shape getFullScreenShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1665502056);
        ComposerKt.sourceInformation($composer, "C527@24354L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1665502056, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-fullScreenShape> (SearchBar.android.kt:527)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getFullScreenContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    public final Shape getDockedShape(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(1006952150);
        ComposerKt.sourceInformation($composer, "C530@24496L5:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1006952150, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-dockedShape> (SearchBar.android.kt:530)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getDockedContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return value;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        $composer.startReplaceableGroup(2112270157);
        ComposerKt.sourceInformation($composer, "C533@24623L10:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2112270157, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-windowInsets> (SearchBar.android.kt:533)");
        }
        WindowInsets statusBars = WindowInsets_androidKt.getStatusBars(WindowInsets.INSTANCE, $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return statusBars;
    }

    /* renamed from: colors-Klgx-Pg, reason: not valid java name */
    public final SearchBarColors m1793colorsKlgxPg(long containerColor, long dividerColor, TextFieldColors inputFieldColors, Composer $composer, int $changed, int i) {
        long dividerColor2;
        TextFieldColors inputFieldColors2;
        $composer.startReplaceableGroup(-1216168196);
        ComposerKt.sourceInformation($composer, "C(colors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color)545@25112L5,546@25179L5,547@25230L18:SearchBar.android.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        if ((i & 2) == 0) {
            dividerColor2 = dividerColor;
        } else {
            dividerColor2 = ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), $composer, 6);
        }
        if ((i & 4) == 0) {
            inputFieldColors2 = inputFieldColors;
        } else {
            inputFieldColors2 = m1799inputFieldColorsITpI4ow(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 0, ($changed << 3) & 57344, 16383);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1216168196, $changed, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.android.kt:548)");
        }
        SearchBarColors searchBarColors = new SearchBarColors(containerColor2, dividerColor2, inputFieldColors2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return searchBarColors;
    }

    /* renamed from: inputFieldColors-ITpI4ow, reason: not valid java name */
    public final TextFieldColors m1799inputFieldColorsITpI4ow(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long cursorColor, SelectionColors selectionColors, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int i) {
        long unfocusedTextColor2;
        long disabledTextColor2;
        long cursorColor2;
        SelectionColors selectionColors2;
        long focusedLeadingIconColor2;
        long unfocusedLeadingIconColor2;
        long disabledLeadingIconColor2;
        long focusedTrailingIconColor2;
        long unfocusedTrailingIconColor2;
        long disabledTrailingIconColor2;
        long focusedPlaceholderColor2;
        long unfocusedPlaceholderColor2;
        long disabledPlaceholderColor2;
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        $composer.startReplaceableGroup(-602148837);
        ComposerKt.sourceInformation($composer, "C(inputFieldColors)P(7:c#ui.graphics.Color,12:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,9,5:c#ui.graphics.Color,10:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,4:c#ui.graphics.Color,6:c#ui.graphics.Color,11:c#ui.graphics.Color,2:c#ui.graphics.Color)580@27197L5,581@27271L5,582@27354L5,584@27493L5,585@27572L7,586@27655L5,587@27738L5,589@27847L5,590@27993L5,591@28078L5,593@28189L5,594@28337L5,595@28423L5,596@28513L5,599@28641L847:SearchBar.android.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : focusedTextColor;
        if ((i & 2) == 0) {
            unfocusedTextColor2 = unfocusedTextColor;
        } else {
            unfocusedTextColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6);
        }
        if ((i & 4) != 0) {
            m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6)) : 0.0f);
            disabledTextColor2 = m3407copywmQWz5c4;
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 8) == 0) {
            cursorColor2 = cursorColor;
        } else {
            cursorColor2 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6);
        }
        if ((i & 16) == 0) {
            selectionColors2 = selectionColors;
        } else {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        }
        if ((i & 32) != 0) {
            focusedLeadingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6);
        } else {
            focusedLeadingIconColor2 = focusedLeadingIconColor;
        }
        if ((i & 64) == 0) {
            unfocusedLeadingIconColor2 = unfocusedLeadingIconColor;
        } else {
            unfocusedLeadingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6);
        }
        if ((i & 128) != 0) {
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6)) : 0.0f);
            disabledLeadingIconColor2 = m3407copywmQWz5c3;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((i & 256) == 0) {
            focusedTrailingIconColor2 = focusedTrailingIconColor;
        } else {
            focusedTrailingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6);
        }
        if ((i & 512) == 0) {
            unfocusedTrailingIconColor2 = unfocusedTrailingIconColor;
        } else {
            unfocusedTrailingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6);
        }
        if ((i & 1024) != 0) {
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6)) : 0.0f);
            disabledTrailingIconColor2 = m3407copywmQWz5c2;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        if ((i & 2048) == 0) {
            focusedPlaceholderColor2 = focusedPlaceholderColor;
        } else {
            focusedPlaceholderColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6);
        }
        if ((i & 4096) == 0) {
            unfocusedPlaceholderColor2 = unfocusedPlaceholderColor;
        } else {
            unfocusedPlaceholderColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6);
        }
        if ((i & 8192) != 0) {
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r7, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r7) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r7) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r7) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6)) : 0.0f);
            disabledPlaceholderColor2 = m3407copywmQWz5c;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-602148837, $changed, $changed1, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:599)");
        }
        TextFieldColors m2097colors0hiis_0 = TextFieldDefaults.INSTANCE.m2097colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, 0L, 0L, 0L, 0L, 0L, cursorColor2, 0L, selectionColors2, 0L, 0L, 0L, 0L, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, 0L, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, 0L, 0L, 0L, 0L, 0L, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | (($changed << 15) & 234881024), (($changed >> 12) & 14) | (458752 & $changed) | (3670016 & $changed) | ($changed & 29360128) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (29360128 & ($changed1 << 18)) | (($changed1 << 18) & 234881024) | (($changed1 << 18) & 1879048192), 0, 3072, 1204058872, 4095);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m2097colors0hiis_0;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* renamed from: inputFieldColors--u-KgnY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m1798inputFieldColorsuKgnY(long textColor, long disabledTextColor, long cursorColor, SelectionColors selectionColors, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledTextColor2;
        long cursorColor2;
        SelectionColors selectionColors2;
        long focusedLeadingIconColor2;
        long unfocusedLeadingIconColor2;
        long disabledLeadingIconColor2;
        long focusedTrailingIconColor2;
        long unfocusedTrailingIconColor2;
        long disabledTrailingIconColor2;
        long placeholderColor2;
        long disabledPlaceholderColor2;
        long m3407copywmQWz5c;
        long m3407copywmQWz5c2;
        long m3407copywmQWz5c3;
        long m3407copywmQWz5c4;
        $composer.startReplaceableGroup(355927049);
        ComposerKt.sourceInformation($composer, "C(inputFieldColors)P(9:c#ui.graphics.Color,3:c#ui.graphics.Color,0:c#ui.graphics.Color,8,5:c#ui.graphics.Color,10:c#ui.graphics.Color,1:c#ui.graphics.Color,6:c#ui.graphics.Color,11:c#ui.graphics.Color,4:c#ui.graphics.Color,7:c#ui.graphics.Color,2:c#ui.graphics.Color)619@29678L5,620@29761L5,622@29900L5,623@29979L7,624@30062L5,625@30145L5,627@30254L5,628@30400L5,629@30485L5,631@30596L5,632@30737L5,633@30827L5,635@30912L765:SearchBar.android.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : textColor;
        if ((i & 2) != 0) {
            m3407copywmQWz5c4 = Color.m3407copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r4) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6)) : 0.0f);
            disabledTextColor2 = m3407copywmQWz5c4;
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 4) == 0) {
            cursorColor2 = cursorColor;
        } else {
            cursorColor2 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6);
        }
        if ((i & 8) == 0) {
            selectionColors2 = selectionColors;
        } else {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) consume;
        }
        if ((i & 16) != 0) {
            focusedLeadingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6);
        } else {
            focusedLeadingIconColor2 = focusedLeadingIconColor;
        }
        if ((i & 32) == 0) {
            unfocusedLeadingIconColor2 = unfocusedLeadingIconColor;
        } else {
            unfocusedLeadingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6);
        }
        if ((i & 64) != 0) {
            m3407copywmQWz5c3 = Color.m3407copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r4) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6)) : 0.0f);
            disabledLeadingIconColor2 = m3407copywmQWz5c3;
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        if ((i & 128) == 0) {
            focusedTrailingIconColor2 = focusedTrailingIconColor;
        } else {
            focusedTrailingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6);
        }
        if ((i & 256) == 0) {
            unfocusedTrailingIconColor2 = unfocusedTrailingIconColor;
        } else {
            unfocusedTrailingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6);
        }
        if ((i & 512) != 0) {
            m3407copywmQWz5c2 = Color.m3407copywmQWz5c(r4, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r4) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r4) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r4) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6)) : 0.0f);
            disabledTrailingIconColor2 = m3407copywmQWz5c2;
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        if ((i & 1024) == 0) {
            placeholderColor2 = placeholderColor;
        } else {
            placeholderColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6);
        }
        if ((i & 2048) != 0) {
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r2) : 0.38f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6)) : 0.0f);
            disabledPlaceholderColor2 = m3407copywmQWz5c;
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(355927049, $changed, $changed1, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.android.kt:635)");
        }
        TextFieldColors m1799inputFieldColorsITpI4ow = m1799inputFieldColorsITpI4ow(textColor2, textColor2, disabledTextColor2, cursorColor2, selectionColors2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, placeholderColor2, placeholderColor2, disabledPlaceholderColor2, $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 6) & 896) | (($changed1 << 6) & 7168) | (($changed1 << 6) & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return m1799inputFieldColorsITpI4ow;
    }
}
