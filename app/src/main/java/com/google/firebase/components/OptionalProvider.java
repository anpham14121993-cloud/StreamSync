package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes25.dex */
public class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private static final Provider<Object> EMPTY_PROVIDER;
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = OptionalProvider$$Lambda$4.lambdaFactory$();
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;

    static {
        Deferred.DeferredHandler<Object> deferredHandler;
        Provider<Object> provider;
        deferredHandler = OptionalProvider$$Lambda$4.instance;
        NOOP_HANDLER = deferredHandler;
        provider = OptionalProvider$$Lambda$5.instance;
        EMPTY_PROVIDER = provider;
    }

    public static /* synthetic */ void lambda$static$0(Provider p) {
    }

    public static /* synthetic */ Object lambda$static$1() {
        return null;
    }

    private OptionalProvider(Deferred.DeferredHandler<T> handler, Provider<T> provider) {
        this.handler = handler;
        this.delegate = provider;
    }

    public static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    public static <T> OptionalProvider<T> of(Provider<T> provider) {
        return new OptionalProvider<>(null, provider);
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        return this.delegate.get();
    }

    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> localHandler;
        if (this.delegate != EMPTY_PROVIDER) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (this) {
            localHandler = this.handler;
            this.handler = null;
            this.delegate = provider;
        }
        localHandler.handle(provider);
    }

    @Override // com.google.firebase.inject.Deferred
    public void whenAvailable(Deferred.DeferredHandler<T> handler) {
        Provider<T> provider;
        Provider<T> provider2 = this.delegate;
        if (provider2 != EMPTY_PROVIDER) {
            handler.handle(provider2);
            return;
        }
        Provider<T> toRun = null;
        synchronized (this) {
            provider = this.delegate;
            if (provider != EMPTY_PROVIDER) {
                toRun = provider;
            } else {
                Deferred.DeferredHandler<T> existingHandler = this.handler;
                this.handler = OptionalProvider$$Lambda$1.lambdaFactory$(existingHandler, handler);
            }
        }
        if (toRun != null) {
            handler.handle(provider);
        }
    }

    public static /* synthetic */ void lambda$whenAvailable$2(Deferred.DeferredHandler existingHandler, Deferred.DeferredHandler handler, Provider p) {
        existingHandler.handle(p);
        handler.handle(p);
    }
}
