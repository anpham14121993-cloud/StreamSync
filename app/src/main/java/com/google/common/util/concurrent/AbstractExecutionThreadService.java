package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public abstract class AbstractExecutionThreadService implements Service {
    private static final LazyLogger logger = new LazyLogger(AbstractExecutionThreadService.class);
    private final Service delegate = new AnonymousClass1();

    protected abstract void run() throws Exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.AbstractExecutionThreadService$1, reason: invalid class name */
    /* loaded from: classes25.dex */
    public class AnonymousClass1 extends AbstractService {
        AnonymousClass1() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected final void doStart() {
            Executor executor = MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), (Supplier<String>) new Supplier() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$1$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return AbstractExecutionThreadService.AnonymousClass1.this.m6580xa0f821c5();
                }
            });
            executor.execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractExecutionThreadService.AnonymousClass1.this.m6581x3d661e24();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$doStart$0$com-google-common-util-concurrent-AbstractExecutionThreadService$1, reason: not valid java name */
        public /* synthetic */ String m6580xa0f821c5() {
            return AbstractExecutionThreadService.this.serviceName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$doStart$1$com-google-common-util-concurrent-AbstractExecutionThreadService$1, reason: not valid java name */
        public /* synthetic */ void m6581x3d661e24() {
            try {
                AbstractExecutionThreadService.this.startUp();
                notifyStarted();
                if (isRunning()) {
                    try {
                        AbstractExecutionThreadService.this.run();
                    } catch (Throwable t) {
                        Platform.restoreInterruptIfIsInterruptedException(t);
                        try {
                            AbstractExecutionThreadService.this.shutDown();
                        } catch (Exception ignored) {
                            Platform.restoreInterruptIfIsInterruptedException(ignored);
                            AbstractExecutionThreadService.logger.get().log(Level.WARNING, "Error while attempting to shut down the service after failure.", (Throwable) ignored);
                        }
                        notifyFailed(t);
                        return;
                    }
                }
                AbstractExecutionThreadService.this.shutDown();
                notifyStopped();
            } catch (Throwable t2) {
                Platform.restoreInterruptIfIsInterruptedException(t2);
                notifyFailed(t2);
            }
        }

        @Override // com.google.common.util.concurrent.AbstractService
        protected void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    }

    protected AbstractExecutionThreadService() {
    }

    protected void startUp() throws Exception {
    }

    protected void shutDown() throws Exception {
    }

    protected void triggerShutdown() {
    }

    protected Executor executor() {
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractExecutionThreadService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                AbstractExecutionThreadService.this.m6579xafeb5522(runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$executor$0$com-google-common-util-concurrent-AbstractExecutionThreadService, reason: not valid java name */
    public /* synthetic */ void m6579xafeb5522(Runnable command) {
        MoreExecutors.newThread(serviceName(), command).start();
    }

    public String toString() {
        return serviceName() + " [" + state() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    protected String serviceName() {
        return getClass().getSimpleName();
    }
}
