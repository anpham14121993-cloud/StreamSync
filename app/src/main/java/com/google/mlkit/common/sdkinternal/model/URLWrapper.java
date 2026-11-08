package com.google.mlkit.common.sdkinternal.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class URLWrapper {
    private final URL zza;

    public URLWrapper(String spec) throws MalformedURLException {
        this.zza = new URL(spec);
    }

    public URLConnection openConnection() throws IOException {
        return this.zza.openConnection();
    }
}
