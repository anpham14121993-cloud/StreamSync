package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {

    @CheckForNull
    F function;

    @CheckForNull
    ListenableFuture<? extends I> inputFuture;

    @ParametricNullness
    abstract T doTransform(F function, @ParametricNullness I result) throws Exception;

    abstract void setResult(@ParametricNullness T result);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, AsyncFunction<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(executor);
        AsyncTransformFuture<I, O> output = new AsyncTransformFuture<>(input, function);
        input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
        return output;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        TransformFuture<I, O> output = new TransformFuture<>(input, function);
        input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
        return output;
    }

    AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.function = (F) Preconditions.checkNotNull(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        if (isCancelled() | (localInputFuture == null) | (localFunction == null)) {
            return;
        }
        this.inputFuture = null;
        if (localInputFuture.isCancelled()) {
            setFuture(localInputFuture);
            return;
        }
        try {
            try {
                Object doTransform = doTransform(localFunction, Futures.getDone(localInputFuture));
                this.function = null;
                setResult(doTransform);
            } catch (Throwable t) {
                try {
                    Platform.restoreInterruptIfIsInterruptedException(t);
                    setException(t);
                } finally {
                    this.function = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException e2) {
            cancel(false);
        } catch (ExecutionException e3) {
            setException(e3.getCause());
        } catch (Exception e4) {
            setException(e4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    @CheckForNull
    public String pendingToString() {
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            resultString = "inputFuture=[" + localInputFuture + "], ";
        }
        if (localFunction != null) {
            return resultString + "function=[" + localFunction + "]";
        }
        if (superString != null) {
            return resultString + superString;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        /* bridge */ /* synthetic */ Object doTransform(Object function, @ParametricNullness Object input) throws Exception {
            return doTransform((AsyncFunction<? super AsyncFunction<? super I, ? extends O>, ? extends O>) function, (AsyncFunction<? super I, ? extends O>) input);
        }

        AsyncTransformFuture(ListenableFuture<? extends I> inputFuture, AsyncFunction<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* JADX WARN: Multi-variable type inference failed */
        ListenableFuture<? extends O> doTransform(AsyncFunction<? super I, ? extends O> function, @ParametricNullness I input) throws Exception {
            ListenableFuture<? extends O> outputFuture = function.apply(input);
            Preconditions.checkNotNull(outputFuture, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", function);
            return outputFuture;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(ListenableFuture<? extends O> result) {
            setFuture(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        @ParametricNullness
        /* bridge */ /* synthetic */ Object doTransform(Object function, @ParametricNullness Object input) throws Exception {
            return doTransform((Function<? super Function<? super I, ? extends O>, ? extends O>) function, (Function<? super I, ? extends O>) input);
        }

        TransformFuture(ListenableFuture<? extends I> inputFuture, Function<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @ParametricNullness
        O doTransform(Function<? super I, ? extends O> function, @ParametricNullness I input) {
            return function.apply(input);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        void setResult(@ParametricNullness O result) {
            set(result);
        }
    }
}
