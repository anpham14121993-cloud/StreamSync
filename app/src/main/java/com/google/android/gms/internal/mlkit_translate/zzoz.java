package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public enum zzoz implements zzbq {
    TYPE_UNKNOWN(0),
    CUSTOM(1),
    AUTOML_IMAGE_LABELING(2),
    BASE_TRANSLATE(3),
    CUSTOM_OBJECT_DETECTION(4),
    CUSTOM_IMAGE_LABELING(5),
    BASE_ENTITY_EXTRACTION(6),
    BASE_DIGITAL_INK(7),
    TOXICITY_DETECTION(8),
    IMAGE_CAPTIONING(9),
    DIGITAL_INK_SEGMENTATION(10);

    private final int zzm;

    zzoz(int i) {
        this.zzm = i;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbq
    public final int zza() {
        return this.zzm;
    }
}
