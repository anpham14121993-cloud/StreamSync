package com.google.mlkit.nl.translate;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;
import java.io.Closeable;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public interface Translator extends Closeable, LifecycleObserver {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    Task<Void> downloadModelIfNeeded();

    Task<Void> downloadModelIfNeeded(DownloadConditions downloadConditions);

    Task<String> translate(String str);
}
