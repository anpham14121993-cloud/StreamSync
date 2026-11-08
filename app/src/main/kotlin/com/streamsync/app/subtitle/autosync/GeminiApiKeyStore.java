package com.streamsync.app.subtitle.autosync;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.text.util.LocalePreferences;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GeminiApiKeyStore.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u000bR$\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "apiKey", "getApiKey", "()Ljava/lang/String;", "setApiKey", "(Ljava/lang/String;)V", "isConfigured", "", "()Z", "prefs", "Landroid/content/SharedPreferences;", "targetLanguageCode", "getTargetLanguageCode", "setTargetLanguageCode", "useAiArbiter", "getUseAiArbiter", "setUseAiArbiter", "(Z)V", "clear", "", "masked", "normalize", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class GeminiApiKeyStore {

    @Deprecated
    public static final String KEY = "api_key";

    @Deprecated
    public static final String KEY_AI_ARBITER = "use_ai_arbiter";

    @Deprecated
    public static final String KEY_LANG = "target_language_code";

    @Deprecated
    public static final String PREFS = "gemini_api_key";
    private final SharedPreferences prefs;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Map<String, String> LEGACY_NAMES = MapsKt.mapOf(TuplesKt.to("vietnamese", "vi"), TuplesKt.to("english", TranslateLanguage.ENGLISH), TuplesKt.to(LocalePreferences.CalendarType.CHINESE, TranslateLanguage.CHINESE), TuplesKt.to("japanese", TranslateLanguage.JAPANESE), TuplesKt.to("korean", TranslateLanguage.KOREAN), TuplesKt.to("french", TranslateLanguage.FRENCH), TuplesKt.to("german", TranslateLanguage.GERMAN), TuplesKt.to("spanish", TranslateLanguage.SPANISH), TuplesKt.to("indonesian", "id"));

    public GeminiApiKeyStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.prefs = sharedPreferences;
    }

    public final String getApiKey() {
        String string = this.prefs.getString(KEY, "");
        return StringsKt.trim((CharSequence) (string != null ? string : "")).toString();
    }

    public final void setApiKey(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.prefs.edit().putString(KEY, StringsKt.trim((CharSequence) value).toString()).apply();
    }

    public final boolean isConfigured() {
        return getApiKey().length() > 0;
    }

    public final boolean getUseAiArbiter() {
        return this.prefs.getBoolean(KEY_AI_ARBITER, false);
    }

    public final void setUseAiArbiter(boolean value) {
        this.prefs.edit().putBoolean(KEY_AI_ARBITER, value).apply();
    }

    public final String getTargetLanguageCode() {
        String string = this.prefs.getString(KEY_LANG, "vi");
        if (string == null) {
            string = "";
        }
        return normalize(string);
    }

    public final void setTargetLanguageCode(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.prefs.edit().putString(KEY_LANG, normalize(value)).apply();
    }

    public final String masked() {
        String key = getApiKey();
        if (key.length() == 0) {
            return "";
        }
        if (key.length() <= 8) {
            return StringsKt.repeat("*", key.length());
        }
        return StringsKt.repeat("*", key.length() - 4) + StringsKt.takeLast(key, 4);
    }

    public final void clear() {
        this.prefs.edit().remove(KEY).apply();
    }

    /* compiled from: GeminiApiKeyStore.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore$Companion;", "", "()V", "KEY", "", "KEY_AI_ARBITER", "KEY_LANG", "LEGACY_NAMES", "", "getLEGACY_NAMES", "()Ljava/util/Map;", "PREFS", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, String> getLEGACY_NAMES() {
            return GeminiApiKeyStore.LEGACY_NAMES;
        }
    }

    public final String normalize(String value) {
        String str;
        Intrinsics.checkNotNullParameter(value, "value");
        String v = StringsKt.trim((CharSequence) value).toString();
        boolean z = true;
        if (v.length() == 0) {
            return "vi";
        }
        String lower = v.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        String it = LEGACY_NAMES.get(lower);
        if (it != null) {
            return it;
        }
        int length = lower.length();
        if (2 <= length && length < 7) {
            String $this$all$iv = lower;
            int i = 0;
            while (true) {
                if (i >= $this$all$iv.length()) {
                    break;
                }
                char element$iv = $this$all$iv.charAt(i);
                if (((Character.isLetter(element$iv) || element$iv == '-') ? (char) 1 : (char) 0) == 0) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                return StringsKt.substringBefore$default(lower, '-', (String) null, 2, (Object) null);
            }
        }
        switch (lower.hashCode()) {
            case -767328380:
                return !lower.equals("tiếng pháp") ? "vi" : TranslateLanguage.FRENCH;
            case -767151490:
                return !lower.equals("tiếng nhật") ? "vi" : TranslateLanguage.JAPANESE;
            case -766911395:
                str = "tiếng việt";
                break;
            case -371571175:
                str = "tieng viet";
                break;
            case -370578428:
                return !lower.equals("tiếng tây ban nha") ? "vi" : TranslateLanguage.SPANISH;
            case -24766882:
                return !lower.equals("tiếng anh") ? "vi" : TranslateLanguage.ENGLISH;
            case -24756615:
                return !lower.equals("tiếng hàn") ? "vi" : TranslateLanguage.KOREAN;
            case -24355858:
                return !lower.equals("tiếng đức") ? "vi" : TranslateLanguage.GERMAN;
            case 1238994823:
                return !lower.equals("tiếng indonesia") ? "vi" : "id";
            case 1986512243:
                return !lower.equals("tiếng trung") ? "vi" : TranslateLanguage.CHINESE;
            default:
                return "vi";
        }
        lower.equals(str);
        return "vi";
    }
}
