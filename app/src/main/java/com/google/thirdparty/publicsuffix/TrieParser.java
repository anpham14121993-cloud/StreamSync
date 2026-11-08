package com.google.thirdparty.publicsuffix;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Queues;

/* loaded from: classes25.dex */
final class TrieParser {
    private static final Joiner DIRECT_JOINER = Joiner.on("");

    TrieParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence... encodedChunks) {
        String encoded = DIRECT_JOINER.join(encodedChunks);
        return parseFullString(encoded);
    }

    static ImmutableMap<String, PublicSuffixType> parseFullString(String encoded) {
        ImmutableMap.Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
        int encodedLen = encoded.length();
        int idx = 0;
        while (idx < encodedLen) {
            idx += doParseTrieToBuilder(Queues.newArrayDeque(), encoded, idx, builder);
        }
        return builder.buildOrThrow();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if (r2 != ',') goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0050, code lost:
    
        if (r1 >= r0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        r1 = r1 + doParseTrieToBuilder(r8, r9, r1, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        if (r9.charAt(r1) == '?') goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        if (r9.charAt(r1) != ',') goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
    
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int doParseTrieToBuilder(java.util.Deque<java.lang.CharSequence> r8, java.lang.CharSequence r9, int r10, com.google.common.collect.ImmutableMap.Builder<java.lang.String, com.google.thirdparty.publicsuffix.PublicSuffixType> r11) {
        /*
            int r0 = r9.length()
            r1 = r10
            r2 = 0
        L6:
            r3 = 58
            r4 = 33
            r5 = 44
            r6 = 63
            if (r1 >= r0) goto L24
            char r2 = r9.charAt(r1)
            r7 = 38
            if (r2 == r7) goto L24
            if (r2 == r6) goto L24
            if (r2 == r4) goto L24
            if (r2 == r3) goto L24
            if (r2 != r5) goto L21
            goto L24
        L21:
            int r1 = r1 + 1
            goto L6
        L24:
            java.lang.CharSequence r7 = r9.subSequence(r10, r1)
            java.lang.CharSequence r7 = reverse(r7)
            r8.push(r7)
            if (r2 == r4) goto L37
            if (r2 == r6) goto L37
            if (r2 == r3) goto L37
            if (r2 != r5) goto L4a
        L37:
            com.google.common.base.Joiner r3 = com.google.thirdparty.publicsuffix.TrieParser.DIRECT_JOINER
            java.lang.String r3 = r3.join(r8)
            int r4 = r3.length()
            if (r4 <= 0) goto L4a
            com.google.thirdparty.publicsuffix.PublicSuffixType r4 = com.google.thirdparty.publicsuffix.PublicSuffixType.fromCode(r2)
            r11.put(r3, r4)
        L4a:
            int r1 = r1 + 1
            if (r2 == r6) goto L66
            if (r2 == r5) goto L66
        L50:
            if (r1 >= r0) goto L66
            int r3 = doParseTrieToBuilder(r8, r9, r1, r11)
            int r1 = r1 + r3
            char r3 = r9.charAt(r1)
            if (r3 == r6) goto L63
            char r3 = r9.charAt(r1)
            if (r3 != r5) goto L50
        L63:
            int r1 = r1 + 1
        L66:
            r8.pop()
            int r3 = r1 - r10
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.thirdparty.publicsuffix.TrieParser.doParseTrieToBuilder(java.util.Deque, java.lang.CharSequence, int, com.google.common.collect.ImmutableMap$Builder):int");
    }

    private static CharSequence reverse(CharSequence s) {
        return new StringBuilder(s).reverse();
    }
}
