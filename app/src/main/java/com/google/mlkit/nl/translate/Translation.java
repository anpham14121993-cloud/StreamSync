package com.google.mlkit.nl.translate;

import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.nl.translate.internal.TranslatorImpl;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public class Translation {
    private Translation() {
    }

    public static Translator getClient(TranslatorOptions options) {
        return ((TranslatorImpl.Factory) MlKitContext.getInstance().get(TranslatorImpl.Factory.class)).zza(options);
    }
}
