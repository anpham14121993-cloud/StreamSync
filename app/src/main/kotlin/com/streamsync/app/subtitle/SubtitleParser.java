package com.streamsync.app.subtitle;

import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* compiled from: SubtitleParser.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0004H\u0002J\u0012\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020\u0004H\u0002J\u0010\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004H\u0002J\u0010\u00102\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0004H\u0002J&\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u0002052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00107\u001a\u0004\u0018\u00010\u0004J\u0010\u00108\u001a\u00020\u00042\u0006\u00104\u001a\u000205H\u0002J$\u00109\u001a\u00020\u00042\u0006\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u00042\b\u00107\u001a\u0004\u0018\u00010\u0004H\u0002J\u0018\u0010:\u001a\u00020\u00042\u0006\u00104\u001a\u0002052\u0006\u0010;\u001a\u00020<H\u0002J\u0012\u0010=\u001a\u0004\u0018\u0001052\u0006\u00101\u001a\u00020\u0004H\u0002J\u0010\u0010>\u001a\u00020<2\u0006\u00104\u001a\u000205H\u0002J\u0010\u0010?\u001a\u00020<2\u0006\u0010,\u001a\u00020\u0004H\u0002J\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010C\u001a\u00020\u0004J\u0016\u0010D\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u00101\u001a\u00020\u0004H\u0002J\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010C\u001a\u00020\u0004J\u001e\u0010F\u001a\u0010\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020H\u0018\u00010G2\u0006\u0010I\u001a\u00020\u0004H\u0002J\u0016\u0010J\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u00101\u001a\u00020\u0004H\u0002J\u0010\u0010K\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004H\u0002J\u001c\u0010L\u001a\u00020\u00072\u0006\u0010M\u001a\u00020\u00042\n\b\u0002\u0010N\u001a\u0004\u0018\u00010OH\u0002J\u0010\u0010P\u001a\u00020H2\u0006\u0010Q\u001a\u00020RH\u0002J(\u0010S\u001a\u00020H2\u0006\u0010T\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010V\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\r\u0010\tR\u001b\u0010\u000f\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0010\u0010\tR\u001b\u0010\u0012\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0013\u0010\tR\u001b\u0010\u0015\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0016\u0010\tR\u001b\u0010\u0018\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0019\u0010\tR\u001b\u0010\u001b\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001c\u0010\tR\u001b\u0010\u001e\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u000b\u001a\u0004\b\u001f\u0010\tR\u001b\u0010!\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b\"\u0010\tR\u001b\u0010$\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u000b\u001a\u0004\b%\u0010\tR\u001b\u0010'\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000b\u001a\u0004\b(\u0010\t¨\u0006W"}, d2 = {"Lcom/streamsync/app/subtitle/SubtitleParser;", "", "()V", "MOJIBAKE_LEADS", "", "TAG", "assDialogueRegex", "Lkotlin/text/Regex;", "getAssDialogueRegex", "()Lkotlin/text/Regex;", "assDialogueRegex$delegate", "Lkotlin/Lazy;", "assOverrideRegex", "getAssOverrideRegex", "assOverrideRegex$delegate", "assStyleRegex", "getAssStyleRegex", "assStyleRegex$delegate", "assTimeRegex", "getAssTimeRegex", "assTimeRegex$delegate", "blockSplitRegex", "getBlockSplitRegex", "blockSplitRegex$delegate", "extraBlankRegex", "getExtraBlankRegex", "extraBlankRegex$delegate", "fullTimeRegex", "getFullTimeRegex", "fullTimeRegex$delegate", "htmlTagRegex", "getHtmlTagRegex", "htmlTagRegex$delegate", "numericEntityRegex", "getNumericEntityRegex", "numericEntityRegex$delegate", "shortTimeRegex", "getShortTimeRegex", "shortTimeRegex$delegate", "softBreakRegex", "getSoftBreakRegex", "softBreakRegex$delegate", "badness", "", CmcdData.Factory.STREAMING_FORMAT_SS, "charsetOrNull", "Ljava/nio/charset/Charset;", HintConstants.AUTOFILL_HINT_NAME, "cleanText", "text", "countVietnameseMarks", "decode", "bytes", "", "contentType", "languageHint", "decodeCp1258", "decodeRaw", "decodeSingleByte", "isVietnamese", "", "encodeCp1252", "isValidUtf8", "looksLikeText", "parse", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "raw", "parseAss", "parseSrt", "parseTimeLine", "Lkotlin/Pair;", "", "line", "parseTimedText", "repairMojibake", "safeRegex", "pattern", "option", "Lkotlin/text/RegexOption;", "toAssMs", "match", "Lkotlin/text/MatchResult;", "toMs", CmcdData.Factory.STREAMING_FORMAT_HLS, "m", TranslateLanguage.MALAY, "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class SubtitleParser {
    private static final String TAG = "StreamSync";
    public static final SubtitleParser INSTANCE = new SubtitleParser();
    private static final String MOJIBAKE_LEADS = "ÃÄÂÆÐÑØÞßåæðþ";

    /* renamed from: fullTimeRegex$delegate, reason: from kotlin metadata */
    private static final Lazy fullTimeRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$fullTimeRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "(\\d{1,2}):(\\d{2}):(\\d{2})[,.](\\d{1,3})\\s*-->\\s*(\\d{1,2}):(\\d{2}):(\\d{2})[,.](\\d{1,3})", null, 2, null);
        }
    });

    /* renamed from: shortTimeRegex$delegate, reason: from kotlin metadata */
    private static final Lazy shortTimeRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$shortTimeRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "(\\d{1,3}):(\\d{2})[,.](\\d{1,3})\\s*-->\\s*(\\d{1,3}):(\\d{2})[,.](\\d{1,3})", null, 2, null);
        }
    });

    /* renamed from: assTimeRegex$delegate, reason: from kotlin metadata */
    private static final Lazy assTimeRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$assTimeRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "(\\d{1,2}):(\\d{2}):(\\d{2})[.,](\\d{1,3})", null, 2, null);
        }
    });

    /* renamed from: assOverrideRegex$delegate, reason: from kotlin metadata */
    private static final Lazy assOverrideRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$assOverrideRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "\\{[^}]*}", null, 2, null);
        }
    });

    /* renamed from: htmlTagRegex$delegate, reason: from kotlin metadata */
    private static final Lazy htmlTagRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$htmlTagRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "</?[a-zA-Z][^>]*>", null, 2, null);
        }
    });

    /* renamed from: assStyleRegex$delegate, reason: from kotlin metadata */
    private static final Lazy assStyleRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$assStyleRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            Regex safeRegex;
            safeRegex = SubtitleParser.INSTANCE.safeRegex("(?m)^\\[V4\\+? Styles]", RegexOption.IGNORE_CASE);
            return safeRegex;
        }
    });

    /* renamed from: assDialogueRegex$delegate, reason: from kotlin metadata */
    private static final Lazy assDialogueRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$assDialogueRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "(?m)^Dialogue:", null, 2, null);
        }
    });

    /* renamed from: blockSplitRegex$delegate, reason: from kotlin metadata */
    private static final Lazy blockSplitRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$blockSplitRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "\\n[ \\t]*\\n", null, 2, null);
        }
    });

    /* renamed from: softBreakRegex$delegate, reason: from kotlin metadata */
    private static final Lazy softBreakRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$softBreakRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "(?m)^[ \\t]*,[ \\t]*", null, 2, null);
        }
    });

    /* renamed from: extraBlankRegex$delegate, reason: from kotlin metadata */
    private static final Lazy extraBlankRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$extraBlankRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "\\n{3,}", null, 2, null);
        }
    });

    /* renamed from: numericEntityRegex$delegate, reason: from kotlin metadata */
    private static final Lazy numericEntityRegex = LazyKt.lazy(new Function0<Regex>() { // from class: com.streamsync.app.subtitle.SubtitleParser$numericEntityRegex$2
        @Override // kotlin.jvm.functions.Function0
        public final Regex invoke() {
            return SubtitleParser.safeRegex$default(SubtitleParser.INSTANCE, "&#(x?)([0-9A-Fa-f]+);", null, 2, null);
        }
    });
    public static final int $stable = 8;

    private SubtitleParser() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Regex safeRegex(String pattern, RegexOption option) {
        try {
            return option == null ? new Regex(pattern) : new Regex(pattern, option);
        } catch (Throwable e) {
            Log.e(TAG, "Regex hỏng, chuyển sang so khớp nguyên văn: " + pattern, e);
            return new Regex(Regex.INSTANCE.escape(pattern));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Regex safeRegex$default(SubtitleParser subtitleParser, String str, RegexOption regexOption, int i, Object obj) {
        if ((i & 2) != 0) {
            regexOption = null;
        }
        return subtitleParser.safeRegex(str, regexOption);
    }

    private final Regex getFullTimeRegex() {
        return (Regex) fullTimeRegex.getValue();
    }

    private final Regex getShortTimeRegex() {
        return (Regex) shortTimeRegex.getValue();
    }

    private final Regex getAssTimeRegex() {
        return (Regex) assTimeRegex.getValue();
    }

    private final Regex getAssOverrideRegex() {
        return (Regex) assOverrideRegex.getValue();
    }

    private final Regex getHtmlTagRegex() {
        return (Regex) htmlTagRegex.getValue();
    }

    private final Regex getAssStyleRegex() {
        return (Regex) assStyleRegex.getValue();
    }

    private final Regex getAssDialogueRegex() {
        return (Regex) assDialogueRegex.getValue();
    }

    private final Regex getBlockSplitRegex() {
        return (Regex) blockSplitRegex.getValue();
    }

    private final Regex getSoftBreakRegex() {
        return (Regex) softBreakRegex.getValue();
    }

    private final Regex getExtraBlankRegex() {
        return (Regex) extraBlankRegex.getValue();
    }

    private final Regex getNumericEntityRegex() {
        return (Regex) numericEntityRegex.getValue();
    }

    public static /* synthetic */ String decode$default(SubtitleParser subtitleParser, byte[] bArr, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return subtitleParser.decode(bArr, str, str2);
    }

    public final String decode(byte[] bytes, String contentType, String languageHint) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return repairMojibake(decodeRaw(bytes, contentType, languageHint));
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x013d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String decodeRaw(byte[] r10, java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.SubtitleParser.decodeRaw(byte[], java.lang.String, java.lang.String):java.lang.String");
    }

    private final String decodeCp1258(byte[] bytes) {
        Charset charsetOrNull = charsetOrNull("windows-1258");
        if (charsetOrNull == null) {
            charsetOrNull = Charsets.ISO_8859_1;
        }
        return new String(bytes, charsetOrNull);
    }

    private final String repairMojibake(String text) {
        byte[] bytes;
        String fixed;
        int after;
        if (text.length() == 0) {
            return text;
        }
        String str = text;
        for (int i = 0; i < 2; i++) {
            int before = INSTANCE.badness(str);
            if (before == 0 || (bytes = INSTANCE.encodeCp1252(str)) == null || !INSTANCE.isValidUtf8(bytes) || (after = INSTANCE.badness((fixed = new String(bytes, Charsets.UTF_8)))) >= before) {
                return str;
            }
            Log.i(TAG, "Sua mojibake: badness " + before + " -> " + after);
            str = fixed;
        }
        return str;
    }

    private final byte[] encodeCp1252(String text) {
        Charset charset = charsetOrNull("windows-1252");
        if (charset == null) {
            return null;
        }
        byte[] bytes = text.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        if (Intrinsics.areEqual(new String(bytes, charset), text)) {
            return bytes;
        }
        return null;
    }

    private final int badness(String s) {
        int n = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == 65533) {
                n += 5;
            } else if (128 <= c && c < 160) {
                n += 3;
            } else if (161 <= c && c < 192) {
                n++;
            } else if (StringsKt.contains$default((CharSequence) MOJIBAKE_LEADS, c, false, 2, (Object) null)) {
                n++;
            }
        }
        return n;
    }

    private final String decodeSingleByte(byte[] bytes, boolean isVietnamese) {
        String chosen;
        String cp1258 = decodeCp1258(bytes);
        Charset charsetOrNull = charsetOrNull("windows-1252");
        if (charsetOrNull == null) {
            charsetOrNull = Charsets.ISO_8859_1;
        }
        String cp1252 = new String(bytes, charsetOrNull);
        int marks1258 = countVietnameseMarks(cp1258);
        int marks1252 = countVietnameseMarks(cp1252);
        String $this$count$iv = cp1252;
        int count$iv = 0;
        int i = 0;
        while (true) {
            if (i >= $this$count$iv.length()) {
                break;
            }
            char element$iv = $this$count$iv.charAt(i);
            if (element$iv == 65533) {
                count$iv++;
            }
            i++;
        }
        int bad1252 = count$iv;
        String $this$count$iv2 = cp1258;
        int count$iv2 = 0;
        for (int i2 = 0; i2 < $this$count$iv2.length(); i2++) {
            char element$iv2 = $this$count$iv2.charAt(i2);
            char it = element$iv2 == 65533 ? (char) 1 : (char) 0;
            if (it != 0) {
                count$iv2++;
            }
        }
        int bad1258 = count$iv2;
        if (isVietnamese || marks1258 > marks1252) {
            chosen = cp1258;
        } else {
            chosen = cp1252;
        }
        String str = (chosen != cp1252 || bad1252 <= 0 || bad1252 >= bad1258) ? chosen : cp1258;
        Log.d(TAG, "Giai ma 1 byte: cp1258 marks=" + marks1258 + " cp1252 marks=" + marks1252 + " -> " + (str == cp1258 ? "CP1258" : "CP1252"));
        return str;
    }

    private final boolean looksLikeText(String s) {
        int suspicious = 0;
        int length = s.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                return suspicious * 20 < RangesKt.coerceAtLeast(s.length(), 1);
            }
            char c = s.charAt(i);
            if (c == 65533 || Character.isSurrogate(c)) {
                return false;
            }
            if (128 <= c && c < 160) {
                suspicious++;
            }
            i++;
        }
    }

    private final int countVietnameseMarks(String s) {
        int n = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (768 <= c && c < 880) {
                n++;
            }
        }
        return n;
    }

    private final Charset charsetOrNull(String name) {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            SubtitleParser subtitleParser = this;
            m6717constructorimpl = Result.m6717constructorimpl(Charset.forName(name));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = null;
        }
        return (Charset) m6717constructorimpl;
    }

    private final boolean isValidUtf8(byte[] bytes) {
        int k;
        int i = 0;
        while (true) {
            if (i >= bytes.length) {
                return true;
            }
            int b = bytes[i] & 255;
            if (b <= 127) {
                extra = 0;
            } else {
                if (!(194 <= b && b < 224)) {
                    if (224 <= b && b < 240) {
                        extra = 2;
                    } else {
                        extra = (240 > b || b >= 245) ? 0 : 1;
                        if (extra == 0) {
                            return false;
                        }
                        extra = 3;
                    }
                }
            }
            if (i + extra >= bytes.length) {
                return false;
            }
            if (1 <= extra) {
                while ((bytes[i + k] & 192) == 128) {
                    k = k != extra ? k + 1 : 1;
                }
                return false;
            }
            i += extra + 1;
        }
    }

    public final List<SubtitleCue> parseSrt(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        return parse(raw);
    }

    public final List<SubtitleCue> parse(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        if (StringsKt.isBlank(raw)) {
            return CollectionsKt.emptyList();
        }
        String text = StringsKt.replace$default(StringsKt.replace$default(raw, "\r\n", "\n", false, 4, (Object) null), '\r', '\n', false, 4, (Object) null);
        if (!StringsKt.startsWith(StringsKt.trimStart((CharSequence) text).toString(), "[Script Info]", true)) {
            if (!getAssStyleRegex().containsMatchIn(text)) {
                if (!getAssDialogueRegex().containsMatchIn(text)) {
                    return parseTimedText(text);
                }
            }
        }
        return parseAss(text);
    }

    private final List<SubtitleCue> parseTimedText(String text) {
        String obj;
        Integer intOrNull;
        List cues = new ArrayList();
        int timeLineIndex = 0;
        List body = getBlockSplitRegex().split(SequencesKt.joinToString$default(SequencesKt.dropWhile(StringsKt.lineSequence(text), new Function1<String, Boolean>() { // from class: com.streamsync.app.subtitle.SubtitleParser$parseTimedText$body$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(StringsKt.startsWith(StringsKt.trimStart((CharSequence) it).toString(), "WEBVTT", true));
            }
        }), "\n", null, null, 0, null, null, 62, null), 0);
        for (String block : body) {
            Iterable $this$filter$iv = StringsKt.lines(StringsKt.trim((CharSequence) block).toString());
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                if (!StringsKt.isBlank((String) element$iv$iv)) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List lines = (List) destination$iv$iv;
            if (lines.isEmpty()) {
                timeLineIndex = 0;
            } else {
                List $this$any$iv = lines;
                if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            Object element$iv = it.next();
                            String it2 = (String) element$iv;
                            if (StringsKt.startsWith(StringsKt.trimStart((CharSequence) it2).toString(), "NOTE", true) || StringsKt.startsWith(StringsKt.trimStart((CharSequence) it2).toString(), "STYLE", true) || StringsKt.startsWith(StringsKt.trimStart((CharSequence) it2).toString(), "REGION", true)) {
                                timeLineIndex = 1;
                                break;
                            }
                        } else {
                            timeLineIndex = 0;
                            break;
                        }
                    }
                }
                if (timeLineIndex != 0) {
                    timeLineIndex = 0;
                } else {
                    int index$iv = 0;
                    Iterator it3 = lines.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object item$iv = it3.next();
                            String it4 = (String) item$iv;
                            if (INSTANCE.getFullTimeRegex().containsMatchIn(it4) || INSTANCE.getShortTimeRegex().containsMatchIn(it4)) {
                                break;
                            }
                            index$iv++;
                        } else {
                            index$iv = -1;
                            break;
                        }
                    }
                    int timeLineIndex2 = index$iv;
                    if (timeLineIndex2 != -1) {
                        Pair startEnd = parseTimeLine((String) lines.get(timeLineIndex2));
                        if (startEnd != null) {
                            String rawText = CollectionsKt.joinToString$default(CollectionsKt.drop(lines, timeLineIndex2 + 1), "\n", null, null, 0, null, null, 62, null);
                            String cleaned = cleanText(rawText);
                            if (cleaned.length() == 0) {
                                timeLineIndex = 0;
                            } else {
                                String str = (String) CollectionsKt.firstOrNull(lines);
                                int index = (str == null || (obj = StringsKt.trim((CharSequence) str).toString()) == null || (intOrNull = StringsKt.toIntOrNull(obj)) == null) ? cues.size() + 1 : intOrNull.intValue();
                                cues.add(new SubtitleCue(index, startEnd.getFirst().longValue(), startEnd.getSecond().longValue(), cleaned, null, 16, null));
                                timeLineIndex = 0;
                            }
                        } else {
                            timeLineIndex = 0;
                        }
                    } else {
                        timeLineIndex = 0;
                    }
                }
            }
        }
        List $this$sortedBy$iv = cues;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.SubtitleParser$parseTimedText$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it5 = (SubtitleCue) t;
                SubtitleCue it6 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it5.getStartMs()), Long.valueOf(it6.getStartMs()));
            }
        });
    }

    private final List<SubtitleCue> parseAss(String text) {
        MatchResult it;
        List cues = new ArrayList();
        boolean inEvents = false;
        for (String rawLine : StringsKt.lineSequence(text)) {
            String line = StringsKt.trim((CharSequence) rawLine).toString();
            if (StringsKt.startsWith$default(line, "[", false, 2, (Object) null)) {
                inEvents = StringsKt.equals(line, "[Events]", true);
            } else if (inEvents && StringsKt.startsWith(line, "Dialogue:", true)) {
                String payload = StringsKt.trim((CharSequence) StringsKt.substringAfter(line, ":", "")).toString();
                List parts = StringsKt.split$default((CharSequence) payload, new String[]{","}, false, 10, 2, (Object) null);
                if (parts.size() >= 10 && (it = Regex.find$default(getAssTimeRegex(), (CharSequence) parts.get(1), 0, 2, null)) != null) {
                    long start = INSTANCE.toAssMs(it);
                    MatchResult it2 = Regex.find$default(getAssTimeRegex(), (CharSequence) parts.get(2), 0, 2, null);
                    if (it2 != null) {
                        long end = INSTANCE.toAssMs(it2);
                        if (end > start) {
                            String cleaned = cleanText(CollectionsKt.joinToString$default(CollectionsKt.drop(parts, 9), ",", null, null, 0, null, null, 62, null));
                            if (!(cleaned.length() == 0)) {
                                cues.add(new SubtitleCue(cues.size() + 1, start, end, cleaned, null, 16, null));
                            }
                        }
                    }
                }
            }
        }
        List $this$sortedBy$iv = cues;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.SubtitleParser$parseAss$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it3 = (SubtitleCue) t;
                SubtitleCue it4 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it3.getStartMs()), Long.valueOf(it4.getStartMs()));
            }
        });
    }

    private final Pair<Long, Long> parseTimeLine(String line) {
        MatchResult m = Regex.find$default(getFullTimeRegex(), line, 0, 2, null);
        if (m != null) {
            List g = m.getGroupValues();
            return TuplesKt.to(Long.valueOf(INSTANCE.toMs(g.get(1), g.get(2), g.get(3), g.get(4))), Long.valueOf(INSTANCE.toMs(g.get(5), g.get(6), g.get(7), g.get(8))));
        }
        MatchResult m2 = Regex.find$default(getShortTimeRegex(), line, 0, 2, null);
        if (m2 == null) {
            return null;
        }
        List g2 = m2.getGroupValues();
        return TuplesKt.to(Long.valueOf(INSTANCE.toMs("0", g2.get(1), g2.get(2), g2.get(3))), Long.valueOf(INSTANCE.toMs("0", g2.get(4), g2.get(5), g2.get(6))));
    }

    private final long toMs(String h, String m, String s, String ms) {
        Long longOrNull = StringsKt.toLongOrNull(h);
        if (longOrNull != null) {
            long hh = longOrNull.longValue();
            String fraction = StringsKt.padEnd(StringsKt.take(ms, 3), 3, '0');
            return (3600000 * hh) + (Long.parseLong(m) * 60000) + (Long.parseLong(s) * 1000) + Long.parseLong(fraction);
        }
        return 0L;
    }

    private final long toAssMs(MatchResult match) {
        List g = match.getGroupValues();
        return (Long.parseLong(g.get(1)) * 3600000) + (Long.parseLong(g.get(2)) * 60000) + (Long.parseLong(g.get(3)) * 1000) + Long.parseLong(StringsKt.padEnd(StringsKt.take(g.get(4), 2), 3, '0'));
    }

    private final String cleanText(String text) {
        return StringsKt.trim((CharSequence) getExtraBlankRegex().replace(CollectionsKt.joinToString$default(StringsKt.lines(getSoftBreakRegex().replace(getNumericEntityRegex().replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace(StringsKt.replace(StringsKt.replace(StringsKt.replace(getHtmlTagRegex().replace(getAssOverrideRegex().replace(text, ""), ""), "{\\an8}", "", true), "\\N", "\n", true), "\\n", "\n", true), "\\h", " ", true), "&nbsp;", " ", false, 4, (Object) null), "&amp;", "&", false, 4, (Object) null), "&lt;", "<", false, 4, (Object) null), "&gt;", ">", false, 4, (Object) null), "&quot;", "\"", false, 4, (Object) null), new Function1<MatchResult, CharSequence>() { // from class: com.streamsync.app.subtitle.SubtitleParser$cleanText$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult m) {
                Intrinsics.checkNotNullParameter(m, "m");
                int radix = m.getGroupValues().get(1).length() == 0 ? 10 : 16;
                Integer intOrNull = StringsKt.toIntOrNull(m.getGroupValues().get(2), radix);
                if (intOrNull == null) {
                    return m.getValue();
                }
                int code = intOrNull.intValue();
                if (!(1 <= code && code < 1114112)) {
                    return m.getValue();
                }
                char[] chars = Character.toChars(code);
                Intrinsics.checkNotNullExpressionValue(chars, "toChars(...)");
                return new String(chars);
            }
        }), "\n")), "\n", null, null, 0, null, new Function1<String, CharSequence>() { // from class: com.streamsync.app.subtitle.SubtitleParser$cleanText$2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.trim((CharSequence) it).toString();
            }
        }, 30, null), "\n\n")).toString();
    }
}
