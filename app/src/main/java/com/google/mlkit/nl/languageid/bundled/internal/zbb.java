package com.google.mlkit.nl.languageid.bundled.internal;

import android.content.Context;
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate;
import com.google.mlkit.nl.languageid.internal.LanguageIdentifierDelegate;

/* compiled from: com.google.mlkit:language-id@@17.0.6 */
/* loaded from: classes25.dex */
public final class zbb implements LanguageIdentifierCreatorDelegate {
    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final LanguageIdentifierDelegate create(Context context, LanguageIdentificationOptions languageIdentificationOptions) {
        return new ThickLanguageIdentifier(context, languageIdentificationOptions);
    }

    @Override // com.google.mlkit.nl.languageid.internal.LanguageIdentifierCreatorDelegate
    public final int getPriority() {
        return 100;
    }
}
