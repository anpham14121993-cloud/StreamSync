package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: UiSettingsStore.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/settings/UiSettingsStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_settings", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/streamsync/app/settings/UiSettings;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "read", "reset", "", "update", "transform", "Lkotlin/Function1;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class UiSettingsStore {
    public static final String KEY_AMOLED_BLACK = "amoled_black";
    public static final String KEY_CINEMATIC = "cinematic_enabled";
    public static final String KEY_CINEMATIC_BACKDROP = "cinematic_backdrop";
    public static final String KEY_CINEMATIC_SCRIM = "cinematic_scrim";
    public static final String KEY_DEVICE_LAYOUT = "device_layout";
    public static final String KEY_DYNAMIC_COLOR = "dynamic_color";
    public static final String KEY_HERO_AUTO_SLIDE = "hero_auto_slide";
    public static final String KEY_HERO_BLUR = "hero_blur_dp";
    public static final String KEY_HERO_SCRIM = "hero_scrim_strength";
    public static final String KEY_HERO_SLIDE_MS = "hero_slide_ms";
    public static final String KEY_HOME_TITLE_SP = "home_title_sp";
    public static final String KEY_MOTION = "motion_enabled";
    public static final String KEY_POSTERS_PER_ROW = "posters_per_row";
    public static final String KEY_POSTER_CORNER = "poster_corner_dp";
    public static final String KEY_ROW_SPACING = "row_spacing_dp";
    public static final String KEY_THEME_MODE = "theme_mode";
    public static final String KEY_ZOOM = "zoom_on_hover";
    private static volatile UiSettingsStore instance;
    private final MutableStateFlow<UiSettings> _settings;
    private final SharedPreferences prefs;
    private final StateFlow<UiSettings> settings;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ UiSettingsStore(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private UiSettingsStore(Context context) {
        this.prefs = context.getApplicationContext().getSharedPreferences("ui_settings", 0);
        this._settings = StateFlowKt.MutableStateFlow(read());
        this.settings = FlowKt.asStateFlow(this._settings);
    }

    public final StateFlow<UiSettings> getSettings() {
        return this.settings;
    }

    private final UiSettings read() {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            UiSettingsStore $this$read_u24lambda_u240 = this;
            String string = $this$read_u24lambda_u240.prefs.getString(KEY_THEME_MODE, null);
            if (string == null) {
                string = "SYSTEM";
            }
            Intrinsics.checkNotNull(string);
            m6717constructorimpl = Result.m6717constructorimpl(ThemeMode.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        ThemeMode themeMode = ThemeMode.SYSTEM;
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = themeMode;
        }
        ThemeMode mode = (ThemeMode) m6717constructorimpl;
        boolean z = this.prefs.getBoolean(KEY_DYNAMIC_COLOR, true);
        boolean z2 = this.prefs.getBoolean(KEY_AMOLED_BLACK, true);
        boolean z3 = this.prefs.getBoolean(KEY_MOTION, true);
        String string2 = this.prefs.getString(KEY_DEVICE_LAYOUT, null);
        if (string2 == null) {
            string2 = "PHONE";
        }
        return new UiSettings(mode, z, z2, z3, DeviceLayout.valueOf(string2), this.prefs.getBoolean(KEY_ZOOM, false), this.prefs.getInt(KEY_POSTER_CORNER, 12), this.prefs.getInt(KEY_HERO_BLUR, 28), this.prefs.getFloat(KEY_HERO_SCRIM, 0.55f), this.prefs.getBoolean(KEY_CINEMATIC, true), this.prefs.getFloat(KEY_CINEMATIC_BACKDROP, 0.45f), this.prefs.getFloat(KEY_CINEMATIC_SCRIM, 0.35f), this.prefs.getInt(KEY_ROW_SPACING, 10), this.prefs.getInt(KEY_POSTERS_PER_ROW, 8), 450, this.prefs.getBoolean(KEY_HERO_AUTO_SLIDE, true), this.prefs.getInt(KEY_HERO_SLIDE_MS, 7000), this.prefs.getInt(KEY_HOME_TITLE_SP, 18));
    }

    public final void update(Function1<? super UiSettings, UiSettings> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        UiSettings next = transform.invoke(this._settings.getValue());
        this.prefs.edit().putString(KEY_THEME_MODE, next.getThemeMode().name()).putBoolean(KEY_DYNAMIC_COLOR, next.getDynamicColor()).putBoolean(KEY_AMOLED_BLACK, next.getAmoledBlack()).putBoolean(KEY_MOTION, next.getMotionEnabled()).putString(KEY_DEVICE_LAYOUT, next.getDeviceLayout().name()).putBoolean(KEY_ZOOM, next.getZoomOnHover()).putInt(KEY_POSTER_CORNER, next.getPosterCornerDp()).putInt(KEY_HERO_BLUR, next.getHeroBlurDp()).putFloat(KEY_HERO_SCRIM, next.getHeroScrimStrength()).putBoolean(KEY_CINEMATIC, next.getCinematicEnabled()).putFloat(KEY_CINEMATIC_BACKDROP, next.getCinematicBackdropStrength()).putFloat(KEY_CINEMATIC_SCRIM, next.getCinematicScrim()).putInt(KEY_ROW_SPACING, next.getRowSpacingDp()).putInt(KEY_POSTERS_PER_ROW, next.getPostersPerRow()).putBoolean(KEY_HERO_AUTO_SLIDE, next.getHeroAutoSlide()).putInt(KEY_HERO_SLIDE_MS, next.getHeroSlideMs()).putInt(KEY_HOME_TITLE_SP, next.getHomeTitleSp()).apply();
        this._settings.setValue(next);
    }

    public final void reset() {
        update(new Function1<UiSettings, UiSettings>() { // from class: com.streamsync.app.settings.UiSettingsStore$reset$1
            @Override // kotlin.jvm.functions.Function1
            public final UiSettings invoke(UiSettings it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new UiSettings(null, false, false, false, null, false, 0, 0, 0.0f, false, 0.0f, 0.0f, 0, 0, 0, false, 0, 0, 262143, null);
            }
        });
    }

    /* compiled from: UiSettingsStore.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/streamsync/app/settings/UiSettingsStore$Companion;", "", "()V", "KEY_AMOLED_BLACK", "", "KEY_CINEMATIC", "KEY_CINEMATIC_BACKDROP", "KEY_CINEMATIC_SCRIM", "KEY_DEVICE_LAYOUT", "KEY_DYNAMIC_COLOR", "KEY_HERO_AUTO_SLIDE", "KEY_HERO_BLUR", "KEY_HERO_SCRIM", "KEY_HERO_SLIDE_MS", "KEY_HOME_TITLE_SP", "KEY_MOTION", "KEY_POSTERS_PER_ROW", "KEY_POSTER_CORNER", "KEY_ROW_SPACING", "KEY_THEME_MODE", "KEY_ZOOM", "instance", "Lcom/streamsync/app/settings/UiSettingsStore;", "get", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UiSettingsStore get(Context context) {
            UiSettingsStore it;
            Intrinsics.checkNotNullParameter(context, "context");
            UiSettingsStore uiSettingsStore = UiSettingsStore.instance;
            if (uiSettingsStore == null) {
                synchronized (this) {
                    it = UiSettingsStore.instance;
                    if (it == null) {
                        it = new UiSettingsStore(context, null);
                        Companion companion = UiSettingsStore.INSTANCE;
                        UiSettingsStore.instance = it;
                    }
                }
                return it;
            }
            return uiSettingsStore;
        }
    }
}
