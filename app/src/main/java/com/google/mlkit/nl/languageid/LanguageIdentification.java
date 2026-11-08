package com.google.mlkit.nl.languageid;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierImpl;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes25.dex */
public class LanguageIdentification {
    private LanguageIdentification() {
    }

    public static LanguageIdentifier getClient() {
        return ((LanguageIdentifierImpl.Factory) MlKitContext.getInstance().get(LanguageIdentifierImpl.Factory.class)).create(LanguageIdentificationOptions.zza);
    }

    public static LanguageIdentifier getClient(LanguageIdentificationOptions options) {
        Preconditions.checkNotNull(options, "LanguageIdentificationOptions can not be null");
        return ((LanguageIdentifierImpl.Factory) MlKitContext.getInstance().get(LanguageIdentifierImpl.Factory.class)).create(options);
    }
}
