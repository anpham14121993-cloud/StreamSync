package com.google.mlkit.nl.translate;

import com.google.android.gms.internal.mlkit_translate.zzt;
import com.google.android.gms.internal.mlkit_translate.zzw;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public class TranslateLanguage {
    public static final String AFRIKAANS = "af";
    public static final String ALBANIAN = "sq";
    public static final String ARABIC = "ar";
    public static final String BELARUSIAN = "be";
    public static final String BENGALI = "bn";
    public static final String BULGARIAN = "bg";
    public static final String CATALAN = "ca";
    public static final String CHINESE = "zh";
    public static final String CROATIAN = "hr";
    public static final String CZECH = "cs";
    public static final String DANISH = "da";
    public static final String DUTCH = "nl";
    public static final String ENGLISH = "en";
    public static final String ESPERANTO = "eo";
    public static final String ESTONIAN = "et";
    public static final String FINNISH = "fi";
    public static final String FRENCH = "fr";
    public static final String GALICIAN = "gl";
    public static final String GEORGIAN = "ka";
    public static final String GERMAN = "de";
    public static final String GREEK = "el";
    public static final String GUJARATI = "gu";
    public static final String HAITIAN_CREOLE = "ht";
    public static final String HINDI = "hi";
    public static final String HUNGARIAN = "hu";
    public static final String ICELANDIC = "is";
    public static final String INDONESIAN = "id";
    public static final String IRISH = "ga";
    public static final String ITALIAN = "it";
    public static final String JAPANESE = "ja";
    public static final String KANNADA = "kn";
    public static final String KOREAN = "ko";
    public static final String LATVIAN = "lv";
    public static final String LITHUANIAN = "lt";
    public static final String MACEDONIAN = "mk";
    public static final String MALAY = "ms";
    public static final String MALTESE = "mt";
    public static final String MARATHI = "mr";
    public static final String PERSIAN = "fa";
    public static final String POLISH = "pl";
    public static final String PORTUGUESE = "pt";
    public static final String ROMANIAN = "ro";
    public static final String RUSSIAN = "ru";
    public static final String SLOVAK = "sk";
    public static final String SLOVENIAN = "sl";
    public static final String SPANISH = "es";
    public static final String SWAHILI = "sw";
    public static final String SWEDISH = "sv";
    public static final String TAGALOG = "tl";
    public static final String TAMIL = "ta";
    public static final String TELUGU = "te";
    public static final String THAI = "th";
    public static final String TURKISH = "tr";
    public static final String UKRAINIAN = "uk";
    public static final String URDU = "ur";
    public static final String VIETNAMESE = "vi";
    public static final String WELSH = "cy";
    public static final String HEBREW = "he";
    public static final String NORWEGIAN = "no";
    private static final zzw zza = zzw.zze("iw", HEBREW, "in", "id", "nb", NORWEGIAN);

    /* compiled from: com.google.mlkit:translate@@17.0.3 */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes25.dex */
    public @interface Language {
    }

    private TranslateLanguage() {
    }

    public static String fromLanguageTag(String languageTag) {
        String lowerCase = languageTag.toLowerCase(Locale.US);
        if (zza.containsKey(lowerCase)) {
            return (String) zza.get(lowerCase);
        }
        if (getAllLanguages().contains(lowerCase)) {
            return lowerCase;
        }
        return null;
    }

    public static List<String> getAllLanguages() {
        return zzt.zzh(new String[]{AFRIKAANS, ALBANIAN, ARABIC, BELARUSIAN, BULGARIAN, BENGALI, CATALAN, CHINESE, CROATIAN, CZECH, DANISH, DUTCH, ENGLISH, ESPERANTO, ESTONIAN, FINNISH, FRENCH, GALICIAN, GEORGIAN, GERMAN, GREEK, GUJARATI, HAITIAN_CREOLE, HEBREW, HINDI, HUNGARIAN, ICELANDIC, "id", IRISH, ITALIAN, JAPANESE, KANNADA, KOREAN, LITHUANIAN, LATVIAN, MACEDONIAN, MARATHI, MALAY, MALTESE, NORWEGIAN, PERSIAN, POLISH, PORTUGUESE, ROMANIAN, RUSSIAN, SLOVAK, SLOVENIAN, SPANISH, SWEDISH, SWAHILI, TAGALOG, TAMIL, TELUGU, THAI, TURKISH, UKRAINIAN, URDU, "vi", WELSH});
    }

    public static String zza(String str) {
        return str.equals(HEBREW) ? "iw" : str;
    }
}
