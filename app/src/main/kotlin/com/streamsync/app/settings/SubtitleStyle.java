package com.streamsync.app.settings;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.ViewCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SubtitleStyleStore.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bHÆ\u0001J\u0013\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0006HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006*"}, d2 = {"Lcom/streamsync/app/settings/SubtitleStyle;", "", "fontSizeSp", "", "verticalOffsetPercent", "textColor", "", "backgroundEnabled", "", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "borderWidthDp", "borderColor", "bold", SubtitleStyleStore.KEY_SHADOW, "(FFIZIFIZZ)V", "getBackgroundColor", "()I", "getBackgroundEnabled", "()Z", "getBold", "getBorderColor", "getBorderWidthDp", "()F", "getFontSizeSp", "getShadow", "getTextColor", "getVerticalOffsetPercent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class SubtitleStyle {
    public static final int $stable = 0;
    private final int backgroundColor;
    private final boolean backgroundEnabled;
    private final boolean bold;
    private final int borderColor;
    private final float borderWidthDp;
    private final float fontSizeSp;
    private final boolean shadow;
    private final int textColor;
    private final float verticalOffsetPercent;

    public SubtitleStyle() {
        this(0.0f, 0.0f, 0, false, 0, 0.0f, 0, false, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final float getFontSizeSp() {
        return this.fontSizeSp;
    }

    /* renamed from: component2, reason: from getter */
    public final float getVerticalOffsetPercent() {
        return this.verticalOffsetPercent;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTextColor() {
        return this.textColor;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getBackgroundEnabled() {
        return this.backgroundEnabled;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component6, reason: from getter */
    public final float getBorderWidthDp() {
        return this.borderWidthDp;
    }

    /* renamed from: component7, reason: from getter */
    public final int getBorderColor() {
        return this.borderColor;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getBold() {
        return this.bold;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getShadow() {
        return this.shadow;
    }

    public final SubtitleStyle copy(float fontSizeSp, float verticalOffsetPercent, int textColor, boolean backgroundEnabled, int backgroundColor, float borderWidthDp, int borderColor, boolean bold, boolean shadow) {
        return new SubtitleStyle(fontSizeSp, verticalOffsetPercent, textColor, backgroundEnabled, backgroundColor, borderWidthDp, borderColor, bold, shadow);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleStyle)) {
            return false;
        }
        SubtitleStyle subtitleStyle = (SubtitleStyle) other;
        return Float.compare(this.fontSizeSp, subtitleStyle.fontSizeSp) == 0 && Float.compare(this.verticalOffsetPercent, subtitleStyle.verticalOffsetPercent) == 0 && this.textColor == subtitleStyle.textColor && this.backgroundEnabled == subtitleStyle.backgroundEnabled && this.backgroundColor == subtitleStyle.backgroundColor && Float.compare(this.borderWidthDp, subtitleStyle.borderWidthDp) == 0 && this.borderColor == subtitleStyle.borderColor && this.bold == subtitleStyle.bold && this.shadow == subtitleStyle.shadow;
    }

    public int hashCode() {
        return (((((((((((((((Float.hashCode(this.fontSizeSp) * 31) + Float.hashCode(this.verticalOffsetPercent)) * 31) + Integer.hashCode(this.textColor)) * 31) + Boolean.hashCode(this.backgroundEnabled)) * 31) + Integer.hashCode(this.backgroundColor)) * 31) + Float.hashCode(this.borderWidthDp)) * 31) + Integer.hashCode(this.borderColor)) * 31) + Boolean.hashCode(this.bold)) * 31) + Boolean.hashCode(this.shadow);
    }

    public String toString() {
        return "SubtitleStyle(fontSizeSp=" + this.fontSizeSp + ", verticalOffsetPercent=" + this.verticalOffsetPercent + ", textColor=" + this.textColor + ", backgroundEnabled=" + this.backgroundEnabled + ", backgroundColor=" + this.backgroundColor + ", borderWidthDp=" + this.borderWidthDp + ", borderColor=" + this.borderColor + ", bold=" + this.bold + ", shadow=" + this.shadow + ")";
    }

    public SubtitleStyle(float fontSizeSp, float verticalOffsetPercent, int textColor, boolean backgroundEnabled, int backgroundColor, float borderWidthDp, int borderColor, boolean bold, boolean shadow) {
        this.fontSizeSp = fontSizeSp;
        this.verticalOffsetPercent = verticalOffsetPercent;
        this.textColor = textColor;
        this.backgroundEnabled = backgroundEnabled;
        this.backgroundColor = backgroundColor;
        this.borderWidthDp = borderWidthDp;
        this.borderColor = borderColor;
        this.bold = bold;
        this.shadow = shadow;
    }

    public /* synthetic */ SubtitleStyle(float f, float f2, int i, boolean z, int i2, float f3, int i3, boolean z2, boolean z3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 20.0f : f, (i4 & 2) != 0 ? 0.0f : f2, (i4 & 4) != 0 ? -1 : i, (i4 & 8) != 0 ? false : z, (i4 & 16) != 0 ? -1728053248 : i2, (i4 & 32) == 0 ? f3 : 0.0f, (i4 & 64) != 0 ? ViewCompat.MEASURED_STATE_MASK : i3, (i4 & 128) != 0 ? false : z2, (i4 & 256) == 0 ? z3 : false);
    }

    public final float getFontSizeSp() {
        return this.fontSizeSp;
    }

    public final float getVerticalOffsetPercent() {
        return this.verticalOffsetPercent;
    }

    public final int getTextColor() {
        return this.textColor;
    }

    public final boolean getBackgroundEnabled() {
        return this.backgroundEnabled;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final float getBorderWidthDp() {
        return this.borderWidthDp;
    }

    public final int getBorderColor() {
        return this.borderColor;
    }

    public final boolean getBold() {
        return this.bold;
    }

    public final boolean getShadow() {
        return this.shadow;
    }
}
