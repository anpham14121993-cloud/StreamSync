package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import coil.util.Emoji;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* compiled from: StreamLabelParser.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0012\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/data/addon/StreamLabelParser;", "", "()V", "DECORATION", "Lkotlin/text/Regex;", "FILENAME_PIPE", "", "QUALITY_RANKS", "", "Lkotlin/Pair;", "", "SIZE_LINE", "cleanFilename", "raw", "cleanName", "providerOf", HintConstants.AUTOFILL_HINT_NAME, "qualityOf", "qualityValueOf", "sizeOf", "description", "specOf", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class StreamLabelParser {
    private static final String FILENAME_PIPE = "|^|";
    public static final StreamLabelParser INSTANCE = new StreamLabelParser();
    private static final List<Pair<String, Integer>> QUALITY_RANKS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("2160p", 2160), TuplesKt.to("4K", 2160), TuplesKt.to("UHD", 2160), TuplesKt.to("1440p", 1440), TuplesKt.to("1080p", 1080), TuplesKt.to("FHD", 1080), TuplesKt.to("720p", 720), TuplesKt.to("HD", 720), TuplesKt.to("480p", 480), TuplesKt.to("360p", 360)});
    private static final Regex DECORATION = new Regex("[\\uD83C-\\uDBFF][\\uDC00-\\uDFFF]|\\p{So}|\\uFE0F|\\u200D");
    private static final Regex SIZE_LINE = new Regex("(\\d+(?:[.,]\\d+)?)\\s*(GB|MB|GiB|MiB)(?![A-Za-z])", RegexOption.IGNORE_CASE);
    public static final int $stable = 8;

    private StreamLabelParser() {
    }

    public final int qualityValueOf(String raw) {
        if (raw != null) {
            String text = raw.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(text, "toLowerCase(...)");
            if (text != null) {
                for (Pair<String, Integer> pair : QUALITY_RANKS) {
                    String token = pair.component1();
                    int value = pair.component2().intValue();
                    String lowerCase = token.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    if (StringsKt.contains$default((CharSequence) text, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        return value;
                    }
                }
                return 0;
            }
        }
        return 0;
    }

    public final String cleanFilename(String raw) {
        String substringBefore$default;
        String it;
        if (raw == null || (substringBefore$default = StringsKt.substringBefore$default(raw, FILENAME_PIPE, (String) null, 2, (Object) null)) == null || (it = StringsKt.trim((CharSequence) substringBefore$default).toString()) == null) {
            return null;
        }
        if (it.length() > 0) {
            return it;
        }
        return null;
    }

    public final String cleanName(String raw) {
        String it;
        if (raw == null) {
            return null;
        }
        String replace = DECORATION.replace(raw, " ");
        if (replace == null) {
            return null;
        }
        String replace2 = new Regex("\\s+").replace(replace, " ");
        if (replace2 == null || (it = StringsKt.trim((CharSequence) replace2).toString()) == null) {
            return null;
        }
        if (it.length() > 0) {
            return it;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        if ((r5.length() > 0) != false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String providerOf(java.lang.String r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 == 0) goto Lf
            r1 = r10
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim(r1)
            java.lang.String r1 = r1.toString()
            goto L10
        Lf:
            r1 = r0
        L10:
            if (r1 != 0) goto L14
            java.lang.String r1 = ""
        L14:
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L21
            r2 = r3
            goto L22
        L21:
            r2 = r4
        L22:
            if (r2 == 0) goto L25
            return r0
        L25:
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r5 = 8226(0x2022, float:1.1527E-41)
            r6 = 2
            boolean r2 = kotlin.text.StringsKt.contains$default(r2, r5, r4, r6, r0)
            if (r2 != 0) goto L32
            return r0
        L32:
            java.lang.String r2 = kotlin.text.StringsKt.substringBeforeLast$default(r1, r5, r0, r6, r0)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.CharSequence r2 = kotlin.text.StringsKt.trim(r2)
            java.lang.String r2 = r2.toString()
            java.lang.String r5 = kotlin.text.StringsKt.substringAfterLast$default(r1, r5, r0, r6, r0)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.CharSequence r5 = kotlin.text.StringsKt.trim(r5)
            java.lang.String r5 = r5.toString()
            r6 = r5
            r7 = 0
            r8 = r2
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            int r8 = r8.length()
            if (r8 <= 0) goto L5b
            r8 = r3
            goto L5c
        L5b:
            r8 = r4
        L5c:
            if (r8 == 0) goto L6d
            r8 = r6
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            int r8 = r8.length()
            if (r8 <= 0) goto L69
            r8 = r3
            goto L6a
        L69:
            r8 = r4
        L6a:
            if (r8 == 0) goto L6d
            goto L6e
        L6d:
            r3 = r4
        L6e:
            if (r3 == 0) goto L71
            r0 = r5
        L71:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.StreamLabelParser.providerOf(java.lang.String):java.lang.String");
    }

    public final String qualityOf(String name) {
        String text = name != null ? StringsKt.trim((CharSequence) name).toString() : null;
        if (text == null) {
            text = "";
        }
        if (text.length() == 0) {
            return null;
        }
        List $this$qualityOf_u24lambda_u243 = CollectionsKt.createListBuilder();
        if (StringsKt.contains$default((CharSequence) text, Typography.bullet, false, 2, (Object) null)) {
            $this$qualityOf_u24lambda_u243.add(StringsKt.substringAfterLast$default(StringsKt.substringBeforeLast$default(text, Typography.bullet, (String) null, 2, (Object) null), Typography.bullet, (String) null, 2, (Object) null));
        }
        $this$qualityOf_u24lambda_u243.add(text);
        List<String> candidates = CollectionsKt.build($this$qualityOf_u24lambda_u243);
        for (String candidate : candidates) {
            Iterator<Pair<String, Integer>> it = QUALITY_RANKS.iterator();
            while (it.hasNext()) {
                String token = it.next().component1();
                if (StringsKt.contains((CharSequence) candidate, (CharSequence) token, true)) {
                    return token;
                }
            }
        }
        return null;
    }

    public final String specOf(String description) {
        Object element$iv;
        String substringAfter$default;
        String it;
        String text = description != null ? StringsKt.trim((CharSequence) description).toString() : null;
        if (text == null) {
            text = "";
        }
        if (text.length() == 0) {
            return null;
        }
        Iterable $this$map$iv = StringsKt.lines(text);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(StringsKt.trim((CharSequence) item$iv$iv).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (((String) element$iv$iv).length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable lines = (List) destination$iv$iv2;
        Iterable $this$firstOrNull$iv = lines;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            if (StringsKt.startsWith$default((String) element$iv, "🎞", false, 2, (Object) null)) {
                break;
            }
        }
        String str = (String) element$iv;
        if (str != null && (substringAfter$default = StringsKt.substringAfter$default(str, ' ', (String) null, 2, (Object) null)) != null && (it = StringsKt.trim((CharSequence) substringAfter$default).toString()) != null) {
            String it3 = it.length() > 0 ? it : null;
            if (it3 != null) {
                return it3;
            }
        }
        return (String) CollectionsKt.firstOrNull(CollectionsKt.drop(lines, 1));
    }

    public final String sizeOf(String description) {
        Object element$iv;
        if (description == null) {
            return null;
        }
        Iterable $this$map$iv = StringsKt.lines(description);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it2 = $this$filter$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object element$iv$iv = it2.next();
            String it3 = (String) element$iv$iv;
            if (it3.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        Iterable candidates = (List) destination$iv$iv2;
        Iterable $this$firstOrNull$iv = candidates;
        Iterator it4 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it4.hasNext()) {
                element$iv = it4.next();
                String it5 = (String) element$iv;
                if (StringsKt.startsWith$default(it5, Emoji.FLOPPY, false, 2, (Object) null)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        String str = (String) element$iv;
        if (str == null) {
            str = description;
        }
        String sizeLine = str;
        MatchResult m = Regex.find$default(SIZE_LINE, sizeLine, 0, 2, null);
        if (m == null) {
            return null;
        }
        return ((Object) m.getGroupValues().get(1)) + " " + ((Object) m.getGroupValues().get(2));
    }
}
