package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.ViewCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: SubtitleStyleStore.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0014R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/settings/SubtitleStyleStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_style", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/streamsync/app/settings/SubtitleStyle;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", TtmlNode.TAG_STYLE, "Lkotlinx/coroutines/flow/StateFlow;", "getStyle", "()Lkotlinx/coroutines/flow/StateFlow;", "read", "reset", "", "update", "transform", "Lkotlin/Function1;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class SubtitleStyleStore {
    public static final String KEY_BG_COLOR = "background_color";
    public static final String KEY_BG_ENABLED = "background_enabled";
    public static final String KEY_BOLD = "bold";
    public static final String KEY_BORDER_COLOR = "border_color";
    public static final String KEY_BORDER_WIDTH = "border_width_dp";
    public static final String KEY_FONT_SIZE = "font_size_sp";
    public static final String KEY_SHADOW = "shadow";
    public static final String KEY_TEXT_COLOR = "text_color";
    public static final String KEY_V_OFFSET = "vertical_offset_percent";
    private static volatile SubtitleStyleStore instance;
    private final MutableStateFlow<SubtitleStyle> _style;
    private final SharedPreferences prefs;
    private final StateFlow<SubtitleStyle> style;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ SubtitleStyleStore(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private SubtitleStyleStore(Context context) {
        this.prefs = context.getApplicationContext().getSharedPreferences("subtitle_style", 0);
        this._style = StateFlowKt.MutableStateFlow(read());
        this.style = FlowKt.asStateFlow(this._style);
    }

    public final StateFlow<SubtitleStyle> getStyle() {
        return this.style;
    }

    private final SubtitleStyle read() {
        return new SubtitleStyle(this.prefs.getFloat(KEY_FONT_SIZE, 20.0f), this.prefs.getFloat(KEY_V_OFFSET, 0.0f), this.prefs.getInt(KEY_TEXT_COLOR, -1), this.prefs.getBoolean(KEY_BG_ENABLED, false), this.prefs.getInt(KEY_BG_COLOR, -1728053248), this.prefs.getFloat(KEY_BORDER_WIDTH, 0.0f), this.prefs.getInt(KEY_BORDER_COLOR, ViewCompat.MEASURED_STATE_MASK), this.prefs.getBoolean("bold", false), this.prefs.getBoolean(KEY_SHADOW, false));
    }

    public final void update(Function1<? super SubtitleStyle, SubtitleStyle> transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        SubtitleStyle next = transform.invoke(this._style.getValue());
        this.prefs.edit().putFloat(KEY_FONT_SIZE, next.getFontSizeSp()).putFloat(KEY_V_OFFSET, next.getVerticalOffsetPercent()).putInt(KEY_TEXT_COLOR, next.getTextColor()).putBoolean(KEY_BG_ENABLED, next.getBackgroundEnabled()).putInt(KEY_BG_COLOR, next.getBackgroundColor()).putFloat(KEY_BORDER_WIDTH, next.getBorderWidthDp()).putInt(KEY_BORDER_COLOR, next.getBorderColor()).putBoolean("bold", next.getBold()).putBoolean(KEY_SHADOW, next.getShadow()).apply();
        this._style.setValue(next);
    }

    public final void reset() {
        update(new Function1<SubtitleStyle, SubtitleStyle>() { // from class: com.streamsync.app.settings.SubtitleStyleStore$reset$1
            @Override // kotlin.jvm.functions.Function1
            public final SubtitleStyle invoke(SubtitleStyle it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new SubtitleStyle(0.0f, 0.0f, 0, false, 0, 0.0f, 0, false, false, FrameMetricsAggregator.EVERY_DURATION, null);
            }
        });
    }

    /* compiled from: SubtitleStyleStore.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/streamsync/app/settings/SubtitleStyleStore$Companion;", "", "()V", "KEY_BG_COLOR", "", "KEY_BG_ENABLED", "KEY_BOLD", "KEY_BORDER_COLOR", "KEY_BORDER_WIDTH", "KEY_FONT_SIZE", "KEY_SHADOW", "KEY_TEXT_COLOR", "KEY_V_OFFSET", "instance", "Lcom/streamsync/app/settings/SubtitleStyleStore;", "get", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubtitleStyleStore get(Context context) {
            SubtitleStyleStore it;
            Intrinsics.checkNotNullParameter(context, "context");
            SubtitleStyleStore subtitleStyleStore = SubtitleStyleStore.instance;
            if (subtitleStyleStore == null) {
                synchronized (this) {
                    it = SubtitleStyleStore.instance;
                    if (it == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        it = new SubtitleStyleStore(applicationContext, null);
                        Companion companion = SubtitleStyleStore.INSTANCE;
                        SubtitleStyleStore.instance = it;
                    }
                }
                return it;
            }
            return subtitleStyleStore;
        }
    }
}
