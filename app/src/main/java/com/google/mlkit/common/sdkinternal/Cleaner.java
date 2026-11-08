package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class Cleaner {
    private final ReferenceQueue zza = new ReferenceQueue();
    private final Set zzb = Collections.synchronizedSet(new HashSet());

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    /* loaded from: classes25.dex */
    public interface Cleanable {
        void clean();
    }

    private Cleaner() {
    }

    public static Cleaner create() {
        Cleaner cleaner = new Cleaner();
        cleaner.register(cleaner, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zza
            @Override // java.lang.Runnable
            public final void run() {
            }
        });
        final ReferenceQueue referenceQueue = cleaner.zza;
        final Set set = cleaner.zzb;
        Thread thread = new Thread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzb
            @Override // java.lang.Runnable
            public final void run() {
                ReferenceQueue referenceQueue2 = referenceQueue;
                while (!set.isEmpty()) {
                    try {
                        ((zzd) referenceQueue2.remove()).clean();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }, "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return cleaner;
    }

    public Cleanable register(Object obj, Runnable cleaningAction) {
        zzd zzdVar = new zzd(obj, this.zza, this.zzb, cleaningAction, null);
        this.zzb.add(zzdVar);
        return zzdVar;
    }
}
