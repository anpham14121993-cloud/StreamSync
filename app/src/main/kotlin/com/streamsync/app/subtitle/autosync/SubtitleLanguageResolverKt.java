package com.streamsync.app.subtitle.autosync;

import androidx.core.text.util.LocalePreferences;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SubtitleLanguageResolver.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"DEFAULT_LANGUAGE_CODE", "", "languageCodeFromLabel", "label", "normalizeLanguageCode", "value", "resolveTargetLanguageCode", "languageCode", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class SubtitleLanguageResolverKt {
    public static final String DEFAULT_LANGUAGE_CODE = "vi";

    public static final String resolveTargetLanguageCode(String languageCode, String label) {
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        Intrinsics.checkNotNullParameter(label, "label");
        String code = StringsKt.trim((CharSequence) languageCode).toString();
        return code.length() > 0 ? normalizeLanguageCode(code) : languageCodeFromLabel(label);
    }

    public static final String languageCodeFromLabel(String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        String lowerCase = label.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String text = StringsKt.trim((CharSequence) lowerCase).toString();
        if ((text.length() == 0) || Intrinsics.areEqual(text, "vi") || Intrinsics.areEqual(text, "vie")) {
            return "vi";
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.ENGLISH) || Intrinsics.areEqual(text, "eng")) {
            return TranslateLanguage.ENGLISH;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.CHINESE) || Intrinsics.areEqual(text, "chi") || Intrinsics.areEqual(text, "zho") || Intrinsics.areEqual(text, "cmn")) {
            return TranslateLanguage.CHINESE;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.JAPANESE) || Intrinsics.areEqual(text, "jpn")) {
            return TranslateLanguage.JAPANESE;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.KOREAN) || Intrinsics.areEqual(text, "kor")) {
            return TranslateLanguage.KOREAN;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.FRENCH) || Intrinsics.areEqual(text, "fra")) {
            return TranslateLanguage.FRENCH;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.GERMAN) || Intrinsics.areEqual(text, "deu") || Intrinsics.areEqual(text, "ger")) {
            return TranslateLanguage.GERMAN;
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.SPANISH) || Intrinsics.areEqual(text, "spa")) {
            return TranslateLanguage.SPANISH;
        }
        if (Intrinsics.areEqual(text, "id") || Intrinsics.areEqual(text, "ind")) {
            return "id";
        }
        if (Intrinsics.areEqual(text, TranslateLanguage.THAI) || Intrinsics.areEqual(text, "tha")) {
            return TranslateLanguage.THAI;
        }
        if (StringsKt.contains$default((CharSequence) text, (CharSequence) "viet", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng việt", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tieng viet", false, 2, (Object) null)) {
            return "vi";
        }
        if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "english", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng anh", false, 2, (Object) null)) {
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) LocalePreferences.CalendarType.CHINESE, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "mandarin", false, 2, (Object) null)) {
                return TranslateLanguage.CHINESE;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "中文", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng trung", false, 2, (Object) null)) {
                return TranslateLanguage.CHINESE;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "japanese", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng nhật", false, 2, (Object) null)) {
                return TranslateLanguage.JAPANESE;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "日本語", false, 2, (Object) null)) {
                return TranslateLanguage.JAPANESE;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "한국", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng hàn", false, 2, (Object) null)) {
                return TranslateLanguage.KOREAN;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "korean", false, 2, (Object) null)) {
                return TranslateLanguage.KOREAN;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "french", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng pháp", false, 2, (Object) null)) {
                return TranslateLanguage.FRENCH;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "german", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng đức", false, 2, (Object) null)) {
                return TranslateLanguage.GERMAN;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "spanish", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) text, (CharSequence) "tiếng tây ban nha", false, 2, (Object) null)) {
                return TranslateLanguage.SPANISH;
            }
            if (StringsKt.contains$default((CharSequence) text, (CharSequence) "indonesian", false, 2, (Object) null)) {
                return "id";
            }
            return StringsKt.contains$default((CharSequence) text, (CharSequence) "ไทย", false, 2, (Object) null) ? TranslateLanguage.THAI : "vi";
        }
        return TranslateLanguage.ENGLISH;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
    
        if (r0.equals("mandarin") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
    
        if (r0.equals("th-th") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00ad, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.THAI;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
    
        if (r0.equals("ko-kr") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0173, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.KOREAN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0061, code lost:
    
        if (r0.equals("ja-jp") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0159, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.JAPANESE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006a, code lost:
    
        if (r0.equals("id-id") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0073, code lost:
    
        if (r0.equals("fr-fr") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x018d, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.FRENCH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007c, code lost:
    
        if (r0.equals("es-es") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01a7, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.SPANISH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0085, code lost:
    
        if (r0.equals("de-de") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0180, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.GERMAN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008e, code lost:
    
        if (r0.equals("thai") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0097, code lost:
    
        if (r0.equals("zho") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a9, code lost:
    
        if (r0.equals("tha") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b7, code lost:
    
        if (r0.equals("spa") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c1, code lost:
    
        if (r0.equals("kor") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00cb, code lost:
    
        if (r0.equals("jpn") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x002b, code lost:
    
        if (r0.equals("indonesian") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d5, code lost:
    
        if (r0.equals("ind") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e3, code lost:
    
        if (r0.equals("ger") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00ed, code lost:
    
        if (r0.equals("fre") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f7, code lost:
    
        if (r0.equals("fra") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0101, code lost:
    
        if (r0.equals("eng") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x00d9, code lost:
    
        return "id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x019a, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.ENGLISH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010b, code lost:
    
        if (r0.equals("deu") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0115, code lost:
    
        if (r0.equals("cmn") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x011f, code lost:
    
        if (r0.equals("chi") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0129, code lost:
    
        if (r0.equals("tiếng anh") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0133, code lost:
    
        if (r0.equals("zh-hant") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x013d, code lost:
    
        if (r0.equals("zh-hans") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0155, code lost:
    
        if (r0.equals("japanese") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x016f, code lost:
    
        if (r0.equals("korean") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x017c, code lost:
    
        if (r0.equals("german") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0189, code lost:
    
        if (r0.equals("french") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0196, code lost:
    
        if (r0.equals("english") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
    
        if (r0.equals(androidx.core.text.util.LocalePreferences.CalendarType.CHINESE) == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01a3, code lost:
    
        if (r0.equals("spanish") == false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0141, code lost:
    
        return com.google.mlkit.nl.translate.TranslateLanguage.CHINESE;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0020. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String normalizeLanguageCode(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.SubtitleLanguageResolverKt.normalizeLanguageCode(java.lang.String):java.lang.String");
    }
}
