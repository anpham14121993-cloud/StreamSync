package androidx.media3.extractor.text.ssa;

/* loaded from: classes24.dex */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int startTimeIndex, int endTimeIndex, int styleIndex, int textIndex, int length) {
        this.startTimeIndex = startTimeIndex;
        this.endTimeIndex = endTimeIndex;
        this.styleIndex = styleIndex;
        this.textIndex = textIndex;
        this.length = length;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.media3.extractor.text.ssa.SsaDialogueFormat fromFormatLine(java.lang.String r12) {
        /*
            r0 = -1
            r1 = -1
            r2 = -1
            r3 = -1
            java.lang.String r4 = "Format:"
            boolean r5 = r12.startsWith(r4)
            androidx.media3.common.util.Assertions.checkArgument(r5)
            int r4 = r4.length()
            java.lang.String r4 = r12.substring(r4)
            java.lang.String r5 = ","
            java.lang.String[] r4 = android.text.TextUtils.split(r4, r5)
            r5 = 0
        L1c:
            int r6 = r4.length
            r7 = -1
            if (r5 >= r6) goto L6b
            r6 = r4[r5]
            java.lang.String r6 = r6.trim()
            java.lang.String r6 = com.google.common.base.Ascii.toLowerCase(r6)
            int r8 = r6.hashCode()
            switch(r8) {
                case 100571: goto L53;
                case 3556653: goto L48;
                case 109757538: goto L3d;
                case 109780401: goto L32;
                default: goto L31;
            }
        L31:
            goto L5c
        L32:
            java.lang.String r8 = "style"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L31
            r7 = 2
            goto L5c
        L3d:
            java.lang.String r8 = "start"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L31
            r7 = 0
            goto L5c
        L48:
            java.lang.String r8 = "text"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L31
            r7 = 3
            goto L5c
        L53:
            java.lang.String r8 = "end"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L31
            r7 = 1
        L5c:
            switch(r7) {
                case 0: goto L66;
                case 1: goto L64;
                case 2: goto L62;
                case 3: goto L60;
                default: goto L5f;
            }
        L5f:
            goto L68
        L60:
            r3 = r5
            goto L68
        L62:
            r2 = r5
            goto L68
        L64:
            r1 = r5
            goto L68
        L66:
            r0 = r5
        L68:
            int r5 = r5 + 1
            goto L1c
        L6b:
            if (r0 == r7) goto L7d
            if (r1 == r7) goto L7d
            if (r3 == r7) goto L7d
            androidx.media3.extractor.text.ssa.SsaDialogueFormat r5 = new androidx.media3.extractor.text.ssa.SsaDialogueFormat
            int r11 = r4.length
            r6 = r5
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r6.<init>(r7, r8, r9, r10, r11)
            goto L7e
        L7d:
            r5 = 0
        L7e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaDialogueFormat.fromFormatLine(java.lang.String):androidx.media3.extractor.text.ssa.SsaDialogueFormat");
    }
}
