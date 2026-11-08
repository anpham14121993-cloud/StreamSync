package com.google.mlkit.common.sdkinternal;

import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class ExecutorSelector {
    private final Provider zza;

    public ExecutorSelector(Provider provider) {
        this.zza = provider;
    }

    public Executor getExecutorToUse(Executor customExecutor) {
        return customExecutor != null ? customExecutor : (Executor) this.zza.get();
    }
}
