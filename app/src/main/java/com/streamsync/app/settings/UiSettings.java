package com.streamsync.app.settings;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiSettingsStore.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b8\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B¹\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\f\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\f\u0012\b\b\u0002\u0010\u0018\u001a\u00020\f¢\u0006\u0002\u0010\u0019J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u000fHÆ\u0003J\t\u00104\u001a\u00020\u000fHÆ\u0003J\t\u00105\u001a\u00020\fHÆ\u0003J\t\u00106\u001a\u00020\fHÆ\u0003J\t\u00107\u001a\u00020\fHÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\fHÆ\u0003J\t\u0010:\u001a\u00020\fHÆ\u0003J\t\u0010;\u001a\u00020\u0005HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\tHÆ\u0003J\t\u0010?\u001a\u00020\u0005HÆ\u0003J\t\u0010@\u001a\u00020\fHÆ\u0003J\t\u0010A\u001a\u00020\fHÆ\u0003J\t\u0010B\u001a\u00020\u000fHÆ\u0003J½\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\fHÆ\u0001J\u0013\u0010D\u001a\u00020\u00052\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010F\u001a\u00020\fHÖ\u0001J\t\u0010G\u001a\u00020HHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0015\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0011\u0010\u0017\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u0011\u0010\u0018\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u0011\u0010\u0014\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0011\u0010\u0013\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001b¨\u0006I"}, d2 = {"Lcom/streamsync/app/settings/UiSettings;", "", "themeMode", "Lcom/streamsync/app/settings/ThemeMode;", "dynamicColor", "", "amoledBlack", "motionEnabled", "deviceLayout", "Lcom/streamsync/app/settings/DeviceLayout;", "zoomOnHover", "posterCornerDp", "", "heroBlurDp", "heroScrimStrength", "", "cinematicEnabled", "cinematicBackdropStrength", "cinematicScrim", "rowSpacingDp", "postersPerRow", "heroHeightDp", "heroAutoSlide", "heroSlideMs", "homeTitleSp", "(Lcom/streamsync/app/settings/ThemeMode;ZZZLcom/streamsync/app/settings/DeviceLayout;ZIIFZFFIIIZII)V", "getAmoledBlack", "()Z", "getCinematicBackdropStrength", "()F", "getCinematicEnabled", "getCinematicScrim", "getDeviceLayout", "()Lcom/streamsync/app/settings/DeviceLayout;", "getDynamicColor", "getHeroAutoSlide", "getHeroBlurDp", "()I", "getHeroHeightDp", "getHeroScrimStrength", "getHeroSlideMs", "getHomeTitleSp", "getMotionEnabled", "getPosterCornerDp", "getPostersPerRow", "getRowSpacingDp", "getThemeMode", "()Lcom/streamsync/app/settings/ThemeMode;", "getZoomOnHover", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class UiSettings {
    public static final int $stable = 0;
    private final boolean amoledBlack;
    private final float cinematicBackdropStrength;
    private final boolean cinematicEnabled;
    private final float cinematicScrim;
    private final DeviceLayout deviceLayout;
    private final boolean dynamicColor;
    private final boolean heroAutoSlide;
    private final int heroBlurDp;
    private final int heroHeightDp;
    private final float heroScrimStrength;
    private final int heroSlideMs;
    private final int homeTitleSp;
    private final boolean motionEnabled;
    private final int posterCornerDp;
    private final int postersPerRow;
    private final int rowSpacingDp;
    private final ThemeMode themeMode;
    private final boolean zoomOnHover;

    public UiSettings() {
        this(null, false, false, false, null, false, 0, 0, 0.0f, false, 0.0f, 0.0f, 0, 0, 0, false, 0, 0, 262143, null);
    }

    /* renamed from: component1, reason: from getter */
    public final ThemeMode getThemeMode() {
        return this.themeMode;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getCinematicEnabled() {
        return this.cinematicEnabled;
    }

    /* renamed from: component11, reason: from getter */
    public final float getCinematicBackdropStrength() {
        return this.cinematicBackdropStrength;
    }

    /* renamed from: component12, reason: from getter */
    public final float getCinematicScrim() {
        return this.cinematicScrim;
    }

    /* renamed from: component13, reason: from getter */
    public final int getRowSpacingDp() {
        return this.rowSpacingDp;
    }

    /* renamed from: component14, reason: from getter */
    public final int getPostersPerRow() {
        return this.postersPerRow;
    }

    /* renamed from: component15, reason: from getter */
    public final int getHeroHeightDp() {
        return this.heroHeightDp;
    }

    /* renamed from: component16, reason: from getter */
    public final boolean getHeroAutoSlide() {
        return this.heroAutoSlide;
    }

    /* renamed from: component17, reason: from getter */
    public final int getHeroSlideMs() {
        return this.heroSlideMs;
    }

    /* renamed from: component18, reason: from getter */
    public final int getHomeTitleSp() {
        return this.homeTitleSp;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getDynamicColor() {
        return this.dynamicColor;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getAmoledBlack() {
        return this.amoledBlack;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getMotionEnabled() {
        return this.motionEnabled;
    }

    /* renamed from: component5, reason: from getter */
    public final DeviceLayout getDeviceLayout() {
        return this.deviceLayout;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getZoomOnHover() {
        return this.zoomOnHover;
    }

    /* renamed from: component7, reason: from getter */
    public final int getPosterCornerDp() {
        return this.posterCornerDp;
    }

    /* renamed from: component8, reason: from getter */
    public final int getHeroBlurDp() {
        return this.heroBlurDp;
    }

    /* renamed from: component9, reason: from getter */
    public final float getHeroScrimStrength() {
        return this.heroScrimStrength;
    }

    public final UiSettings copy(ThemeMode themeMode, boolean dynamicColor, boolean amoledBlack, boolean motionEnabled, DeviceLayout deviceLayout, boolean zoomOnHover, int posterCornerDp, int heroBlurDp, float heroScrimStrength, boolean cinematicEnabled, float cinematicBackdropStrength, float cinematicScrim, int rowSpacingDp, int postersPerRow, int heroHeightDp, boolean heroAutoSlide, int heroSlideMs, int homeTitleSp) {
        Intrinsics.checkNotNullParameter(themeMode, "themeMode");
        Intrinsics.checkNotNullParameter(deviceLayout, "deviceLayout");
        return new UiSettings(themeMode, dynamicColor, amoledBlack, motionEnabled, deviceLayout, zoomOnHover, posterCornerDp, heroBlurDp, heroScrimStrength, cinematicEnabled, cinematicBackdropStrength, cinematicScrim, rowSpacingDp, postersPerRow, heroHeightDp, heroAutoSlide, heroSlideMs, homeTitleSp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UiSettings)) {
            return false;
        }
        UiSettings uiSettings = (UiSettings) other;
        return this.themeMode == uiSettings.themeMode && this.dynamicColor == uiSettings.dynamicColor && this.amoledBlack == uiSettings.amoledBlack && this.motionEnabled == uiSettings.motionEnabled && this.deviceLayout == uiSettings.deviceLayout && this.zoomOnHover == uiSettings.zoomOnHover && this.posterCornerDp == uiSettings.posterCornerDp && this.heroBlurDp == uiSettings.heroBlurDp && Float.compare(this.heroScrimStrength, uiSettings.heroScrimStrength) == 0 && this.cinematicEnabled == uiSettings.cinematicEnabled && Float.compare(this.cinematicBackdropStrength, uiSettings.cinematicBackdropStrength) == 0 && Float.compare(this.cinematicScrim, uiSettings.cinematicScrim) == 0 && this.rowSpacingDp == uiSettings.rowSpacingDp && this.postersPerRow == uiSettings.postersPerRow && this.heroHeightDp == uiSettings.heroHeightDp && this.heroAutoSlide == uiSettings.heroAutoSlide && this.heroSlideMs == uiSettings.heroSlideMs && this.homeTitleSp == uiSettings.homeTitleSp;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.themeMode.hashCode() * 31) + Boolean.hashCode(this.dynamicColor)) * 31) + Boolean.hashCode(this.amoledBlack)) * 31) + Boolean.hashCode(this.motionEnabled)) * 31) + this.deviceLayout.hashCode()) * 31) + Boolean.hashCode(this.zoomOnHover)) * 31) + Integer.hashCode(this.posterCornerDp)) * 31) + Integer.hashCode(this.heroBlurDp)) * 31) + Float.hashCode(this.heroScrimStrength)) * 31) + Boolean.hashCode(this.cinematicEnabled)) * 31) + Float.hashCode(this.cinematicBackdropStrength)) * 31) + Float.hashCode(this.cinematicScrim)) * 31) + Integer.hashCode(this.rowSpacingDp)) * 31) + Integer.hashCode(this.postersPerRow)) * 31) + Integer.hashCode(this.heroHeightDp)) * 31) + Boolean.hashCode(this.heroAutoSlide)) * 31) + Integer.hashCode(this.heroSlideMs)) * 31) + Integer.hashCode(this.homeTitleSp);
    }

    public String toString() {
        return "UiSettings(themeMode=" + this.themeMode + ", dynamicColor=" + this.dynamicColor + ", amoledBlack=" + this.amoledBlack + ", motionEnabled=" + this.motionEnabled + ", deviceLayout=" + this.deviceLayout + ", zoomOnHover=" + this.zoomOnHover + ", posterCornerDp=" + this.posterCornerDp + ", heroBlurDp=" + this.heroBlurDp + ", heroScrimStrength=" + this.heroScrimStrength + ", cinematicEnabled=" + this.cinematicEnabled + ", cinematicBackdropStrength=" + this.cinematicBackdropStrength + ", cinematicScrim=" + this.cinematicScrim + ", rowSpacingDp=" + this.rowSpacingDp + ", postersPerRow=" + this.postersPerRow + ", heroHeightDp=" + this.heroHeightDp + ", heroAutoSlide=" + this.heroAutoSlide + ", heroSlideMs=" + this.heroSlideMs + ", homeTitleSp=" + this.homeTitleSp + ")";
    }

    public UiSettings(ThemeMode themeMode, boolean dynamicColor, boolean amoledBlack, boolean motionEnabled, DeviceLayout deviceLayout, boolean zoomOnHover, int posterCornerDp, int heroBlurDp, float heroScrimStrength, boolean cinematicEnabled, float cinematicBackdropStrength, float cinematicScrim, int rowSpacingDp, int postersPerRow, int heroHeightDp, boolean heroAutoSlide, int heroSlideMs, int homeTitleSp) {
        Intrinsics.checkNotNullParameter(themeMode, "themeMode");
        Intrinsics.checkNotNullParameter(deviceLayout, "deviceLayout");
        this.themeMode = themeMode;
        this.dynamicColor = dynamicColor;
        this.amoledBlack = amoledBlack;
        this.motionEnabled = motionEnabled;
        this.deviceLayout = deviceLayout;
        this.zoomOnHover = zoomOnHover;
        this.posterCornerDp = posterCornerDp;
        this.heroBlurDp = heroBlurDp;
        this.heroScrimStrength = heroScrimStrength;
        this.cinematicEnabled = cinematicEnabled;
        this.cinematicBackdropStrength = cinematicBackdropStrength;
        this.cinematicScrim = cinematicScrim;
        this.rowSpacingDp = rowSpacingDp;
        this.postersPerRow = postersPerRow;
        this.heroHeightDp = heroHeightDp;
        this.heroAutoSlide = heroAutoSlide;
        this.heroSlideMs = heroSlideMs;
        this.homeTitleSp = homeTitleSp;
    }

    public /* synthetic */ UiSettings(ThemeMode themeMode, boolean z, boolean z2, boolean z3, DeviceLayout deviceLayout, boolean z4, int i, int i2, float f, boolean z5, float f2, float f3, int i3, int i4, int i5, boolean z6, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? ThemeMode.SYSTEM : themeMode, (i8 & 2) != 0 ? true : z, (i8 & 4) != 0 ? true : z2, (i8 & 8) != 0 ? true : z3, (i8 & 16) != 0 ? DeviceLayout.PHONE : deviceLayout, (i8 & 32) != 0 ? false : z4, (i8 & 64) != 0 ? 12 : i, (i8 & 128) != 0 ? 28 : i2, (i8 & 256) != 0 ? 0.55f : f, (i8 & 512) != 0 ? true : z5, (i8 & 1024) != 0 ? 0.45f : f2, (i8 & 2048) != 0 ? 0.35f : f3, (i8 & 4096) != 0 ? 10 : i3, (i8 & 8192) != 0 ? 8 : i4, (i8 & 16384) != 0 ? 450 : i5, (i8 & 32768) != 0 ? true : z6, (i8 & 65536) != 0 ? 7000 : i6, (i8 & 131072) != 0 ? 18 : i7);
    }

    public final ThemeMode getThemeMode() {
        return this.themeMode;
    }

    public final boolean getDynamicColor() {
        return this.dynamicColor;
    }

    public final boolean getAmoledBlack() {
        return this.amoledBlack;
    }

    public final boolean getMotionEnabled() {
        return this.motionEnabled;
    }

    public final DeviceLayout getDeviceLayout() {
        return this.deviceLayout;
    }

    public final boolean getZoomOnHover() {
        return this.zoomOnHover;
    }

    public final int getPosterCornerDp() {
        return this.posterCornerDp;
    }

    public final int getHeroBlurDp() {
        return this.heroBlurDp;
    }

    public final float getHeroScrimStrength() {
        return this.heroScrimStrength;
    }

    public final boolean getCinematicEnabled() {
        return this.cinematicEnabled;
    }

    public final float getCinematicBackdropStrength() {
        return this.cinematicBackdropStrength;
    }

    public final float getCinematicScrim() {
        return this.cinematicScrim;
    }

    public final int getRowSpacingDp() {
        return this.rowSpacingDp;
    }

    public final int getPostersPerRow() {
        return this.postersPerRow;
    }

    public final int getHeroHeightDp() {
        return this.heroHeightDp;
    }

    public final boolean getHeroAutoSlide() {
        return this.heroAutoSlide;
    }

    public final int getHeroSlideMs() {
        return this.heroSlideMs;
    }

    public final int getHomeTitleSp() {
        return this.homeTitleSp;
    }
}
