package retrofit2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/* loaded from: classes25.dex */
class BuiltInFactories {
    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
        return Collections.singletonList(new DefaultCallAdapterFactory(callbackExecutor));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends Converter.Factory> createDefaultConverterFactories() {
        return Collections.emptyList();
    }

    /* loaded from: classes25.dex */
    static final class Java8 extends BuiltInFactories {
        @Override // retrofit2.BuiltInFactories
        List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(@Nullable Executor callbackExecutor) {
            return Arrays.asList(new CompletableFutureCallAdapterFactory(), new DefaultCallAdapterFactory(callbackExecutor));
        }

        @Override // retrofit2.BuiltInFactories
        List<? extends Converter.Factory> createDefaultConverterFactories() {
            return Collections.singletonList(new OptionalConverterFactory());
        }
    }
}
