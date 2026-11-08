package com.google.android.gms.internal.mlkit_translate;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public enum zzov implements zzbq {
    UNKNOWN_STATUS(0),
    EXPLICITLY_REQUESTED(1),
    IMPLICITLY_REQUESTED(2),
    MODEL_INFO_RETRIEVAL_SUCCEEDED(3),
    MODEL_INFO_RETRIEVAL_FAILED(4),
    SCHEDULED(5),
    DOWNLOADING(6),
    SUCCEEDED(7),
    FAILED(8),
    LIVE(9),
    UPDATE_AVAILABLE(10),
    DOWNLOADED(11),
    STARTED(12);

    private final int zzo;

    zzov(int i) {
        this.zzo = i;
    }

    @Override // com.google.android.gms.internal.mlkit_translate.zzbq
    public final int zza() {
        return this.zzo;
    }
}
