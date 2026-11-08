package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
final class zzvs {
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0061, code lost:
    
        if (r3.equals("value") != false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map zza(android.content.Context r8, int r9) {
        /*
            java.lang.String r0 = "DefaultsXmlParser"
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            android.content.res.Resources r8 = r8.getResources()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            if (r8 != 0) goto L14
            java.lang.String r8 = "Could not find the resources of the current context while trying to set defaults from an XML."
            android.util.Log.e(r0, r8)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            goto L8f
        L14:
            android.content.res.XmlResourceParser r8 = r8.getXml(r9)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            int r9 = r8.getEventType()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            r2 = 0
            r3 = r2
            r4 = r3
            r5 = r4
        L20:
            r6 = 1
            if (r9 == r6) goto L8f
            r7 = 2
            if (r9 != r7) goto L2b
            java.lang.String r3 = r8.getName()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            goto L82
        L2b:
            r7 = 3
            if (r9 != r7) goto L4d
            java.lang.String r9 = r8.getName()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            java.lang.String r3 = "entry"
            boolean r9 = r9.equals(r3)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            if (r9 == 0) goto L4b
            if (r4 == 0) goto L44
            if (r5 == 0) goto L44
            r1.put(r4, r5)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            r4 = r2
            r5 = r4
            goto L4b
        L44:
            java.lang.String r9 = "An entry in the defaults XML has an invalid key and/or value tag."
            android.util.Log.w(r0, r9)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            r4 = r2
            r5 = r4
        L4b:
            r3 = r2
            goto L82
        L4d:
            r7 = 4
            if (r9 != r7) goto L82
            if (r3 == 0) goto L82
            int r9 = r3.hashCode()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            switch(r9) {
                case 106079: goto L64;
                case 111972721: goto L5a;
                default: goto L59;
            }
        L59:
            goto L6e
        L5a:
            java.lang.String r9 = "value"
            boolean r9 = r3.equals(r9)
            if (r9 == 0) goto L6e
            goto L6f
        L64:
            java.lang.String r9 = "key"
            boolean r9 = r3.equals(r9)
            if (r9 == 0) goto L6e
            r6 = 0
            goto L6f
        L6e:
            r6 = -1
        L6f:
            switch(r6) {
                case 0: goto L78;
                case 1: goto L73;
                default: goto L72;
            }
        L72:
            goto L7d
        L73:
            java.lang.String r5 = r8.getText()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            goto L82
        L78:
            java.lang.String r4 = r8.getText()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            goto L82
        L7d:
            java.lang.String r9 = "Encountered an unexpected tag while parsing the defaults XML."
            android.util.Log.w(r0, r9)     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
        L82:
            int r9 = r8.next()     // Catch: java.io.IOException -> L87 org.xmlpull.v1.XmlPullParserException -> L89
            goto L20
        L87:
            r8 = move-exception
            goto L8a
        L89:
            r8 = move-exception
        L8a:
            java.lang.String r9 = "Encountered an error while parsing the defaults XML file."
            android.util.Log.e(r0, r9, r8)
        L8f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzvs.zza(android.content.Context, int):java.util.Map");
    }
}
