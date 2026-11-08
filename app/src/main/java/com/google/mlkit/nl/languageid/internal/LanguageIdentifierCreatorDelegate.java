package com.google.mlkit.nl.languageid.internal;

import android.content.Context;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;

/* compiled from: com.google.mlkit:language-id-common@@16.1.0 */
/* loaded from: classes25.dex */
public interface LanguageIdentifierCreatorDelegate {
    LanguageIdentifierDelegate create(Context context, LanguageIdentificationOptions languageIdentificationOptions);

    int getPriority();
}
