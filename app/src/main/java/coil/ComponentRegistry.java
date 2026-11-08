package coil;

import androidx.exifinterface.media.ExifInterface;
import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.fetch.SourceResult;
import coil.intercept.Interceptor;
import coil.key.Keyer;
import coil.map.Mapper;
import coil.request.Options;
import coil.util.Collections;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ComponentRegistry.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001)B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B©\u0001\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00120\u0010\u0006\u001a,\u0012(\u0012&\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u00010\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004\u0012(\u0010\n\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004\u0012(\u0010\f\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\r\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001d\u001a\u00020\u001eJ8\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020!H\u0007J8\u0010'\u001a\u0010\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020!\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020!H\u0007R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R3\u0010\f\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\r\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R3\u0010\n\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R;\u0010\u0006\u001a,\u0012(\u0012&\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0006\b\u0001\u0012\u00020\u00010\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t0\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006*"}, d2 = {"Lcoil/ComponentRegistry;", "", "()V", "interceptors", "", "Lcoil/intercept/Interceptor;", "mappers", "Lkotlin/Pair;", "Lcoil/map/Mapper;", "Ljava/lang/Class;", "keyers", "Lcoil/key/Keyer;", "fetcherFactories", "Lcoil/fetch/Fetcher$Factory;", "decoderFactories", "Lcoil/decode/Decoder$Factory;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDecoderFactories", "()Ljava/util/List;", "getFetcherFactories", "getInterceptors", "getKeyers", "getMappers", "key", "", "data", "options", "Lcoil/request/Options;", "map", "newBuilder", "Lcoil/ComponentRegistry$Builder;", "newDecoder", "Lcoil/decode/Decoder;", "", "result", "Lcoil/fetch/SourceResult;", "imageLoader", "Lcoil/ImageLoader;", "startIndex", "newFetcher", "Lcoil/fetch/Fetcher;", "Builder", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ComponentRegistry {
    private final List<Decoder.Factory> decoderFactories;
    private final List<Pair<Fetcher.Factory<? extends Object>, Class<? extends Object>>> fetcherFactories;
    private final List<Interceptor> interceptors;
    private final List<Pair<Keyer<? extends Object>, Class<? extends Object>>> keyers;
    private final List<Pair<Mapper<? extends Object, ? extends Object>, Class<? extends Object>>> mappers;

    public /* synthetic */ ComponentRegistry(List list, List list2, List list3, List list4, List list5, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, list4, list5);
    }

    public final Pair<Decoder, Integer> newDecoder(SourceResult sourceResult, Options options, ImageLoader imageLoader) {
        return newDecoder$default(this, sourceResult, options, imageLoader, 0, 8, null);
    }

    public final Pair<Fetcher, Integer> newFetcher(Object obj, Options options, ImageLoader imageLoader) {
        return newFetcher$default(this, obj, options, imageLoader, 0, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ComponentRegistry(List<? extends Interceptor> list, List<? extends Pair<? extends Mapper<? extends Object, ? extends Object>, ? extends Class<? extends Object>>> list2, List<? extends Pair<? extends Keyer<? extends Object>, ? extends Class<? extends Object>>> list3, List<? extends Pair<? extends Fetcher.Factory<? extends Object>, ? extends Class<? extends Object>>> list4, List<? extends Decoder.Factory> list5) {
        this.interceptors = list;
        this.mappers = list2;
        this.keyers = list3;
        this.fetcherFactories = list4;
        this.decoderFactories = list5;
    }

    public final List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public final List<Pair<Mapper<? extends Object, ? extends Object>, Class<? extends Object>>> getMappers() {
        return this.mappers;
    }

    public final List<Pair<Keyer<? extends Object>, Class<? extends Object>>> getKeyers() {
        return this.keyers;
    }

    public final List<Pair<Fetcher.Factory<? extends Object>, Class<? extends Object>>> getFetcherFactories() {
        return this.fetcherFactories;
    }

    public final List<Decoder.Factory> getDecoderFactories() {
        return this.decoderFactories;
    }

    public ComponentRegistry() {
        this(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    public final Object map(Object data, Options options) {
        Object mappedData = data;
        List $this$forEachIndices$iv = this.mappers;
        int size = $this$forEachIndices$iv.size();
        for (int i$iv = 0; i$iv < size; i$iv++) {
            Pair<Mapper<? extends Object, ? extends Object>, Class<? extends Object>> pair = $this$forEachIndices$iv.get(i$iv);
            Mapper mapper = pair.component1();
            Class type = pair.component2();
            if (type.isAssignableFrom(mappedData.getClass())) {
                Intrinsics.checkNotNull(mapper, "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>");
                Object it = mapper.map(mappedData, options);
                if (it != null) {
                    mappedData = it;
                }
            }
        }
        return mappedData;
    }

    public final String key(Object data, Options options) {
        List $this$forEachIndices$iv = this.keyers;
        int size = $this$forEachIndices$iv.size();
        for (int i$iv = 0; i$iv < size; i$iv++) {
            Pair<Keyer<? extends Object>, Class<? extends Object>> pair = $this$forEachIndices$iv.get(i$iv);
            Keyer keyer = pair.component1();
            Class type = pair.component2();
            if (type.isAssignableFrom(data.getClass())) {
                Intrinsics.checkNotNull(keyer, "null cannot be cast to non-null type coil.key.Keyer<kotlin.Any>");
                String it = keyer.key(data, options);
                if (it != null) {
                    return it;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ Pair newFetcher$default(ComponentRegistry componentRegistry, Object obj, Options options, ImageLoader imageLoader, int i, int i2, Object obj2) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return componentRegistry.newFetcher(obj, options, imageLoader, i);
    }

    public final Pair<Fetcher, Integer> newFetcher(Object data, Options options, ImageLoader imageLoader, int startIndex) {
        int size = this.fetcherFactories.size();
        for (int index = startIndex; index < size; index++) {
            Pair<Fetcher.Factory<? extends Object>, Class<? extends Object>> pair = this.fetcherFactories.get(index);
            Fetcher.Factory factory = pair.component1();
            Class type = pair.component2();
            if (type.isAssignableFrom(data.getClass())) {
                Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type coil.fetch.Fetcher.Factory<kotlin.Any>");
                Fetcher fetcher = factory.create(data, options, imageLoader);
                if (fetcher != null) {
                    return TuplesKt.to(fetcher, Integer.valueOf(index));
                }
            }
        }
        return null;
    }

    public static /* synthetic */ Pair newDecoder$default(ComponentRegistry componentRegistry, SourceResult sourceResult, Options options, ImageLoader imageLoader, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return componentRegistry.newDecoder(sourceResult, options, imageLoader, i);
    }

    public final Pair<Decoder, Integer> newDecoder(SourceResult result, Options options, ImageLoader imageLoader, int startIndex) {
        int size = this.decoderFactories.size();
        for (int index = startIndex; index < size; index++) {
            Decoder.Factory factory = this.decoderFactories.get(index);
            Decoder decoder = factory.create(result, options, imageLoader);
            if (decoder != null) {
                return TuplesKt.to(decoder, Integer.valueOf(index));
            }
        }
        return null;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    /* compiled from: ComponentRegistry.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\bJ#\u0010\u0019\u001a\u00020\u0000\"\n\b\u0000\u0010\u001b\u0018\u0001*\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\rH\u0086\bJ,\u0010\u0019\u001a\u00020\u0000\"\b\b\u0000\u0010\u001b*\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\r2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u000eJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0011J#\u0010\u0019\u001a\u00020\u0000\"\n\b\u0000\u0010\u001b\u0018\u0001*\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0014H\u0086\bJ,\u0010\u0019\u001a\u00020\u0000\"\b\b\u0000\u0010\u001b*\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u00142\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u000eJ'\u0010\u0019\u001a\u00020\u0000\"\n\b\u0000\u0010\u001b\u0018\u0001*\u00020\u00012\u0010\u0010\u001f\u001a\f\u0012\u0004\u0012\u0002H\u001b\u0012\u0002\b\u00030\u0017H\u0086\bJ0\u0010\u0019\u001a\u00020\u0000\"\b\b\u0000\u0010\u001b*\u00020\u00012\u0010\u0010\u001f\u001a\f\u0012\u0004\u0012\u0002H\u001b\u0012\u0002\b\u00030\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u000eJ\u0006\u0010 \u001a\u00020\u0004R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR6\u0010\u000b\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\r\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e0\f0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR6\u0010\u0013\u001a$\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0014\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e0\f0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\nR:\u0010\u0016\u001a(\u0012$\u0012\"\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u0017\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e0\f0\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\n¨\u0006!"}, d2 = {"Lcoil/ComponentRegistry$Builder;", "", "()V", "registry", "Lcoil/ComponentRegistry;", "(Lcoil/ComponentRegistry;)V", "decoderFactories", "", "Lcoil/decode/Decoder$Factory;", "getDecoderFactories$coil_base_release", "()Ljava/util/List;", "fetcherFactories", "Lkotlin/Pair;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/lang/Class;", "getFetcherFactories$coil_base_release", "interceptors", "Lcoil/intercept/Interceptor;", "getInterceptors$coil_base_release", "keyers", "Lcoil/key/Keyer;", "getKeyers$coil_base_release", "mappers", "Lcoil/map/Mapper;", "getMappers$coil_base_release", "add", "factory", ExifInterface.GPS_DIRECTION_TRUE, "type", "interceptor", "keyer", "mapper", "build", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Builder {
        private final List<Decoder.Factory> decoderFactories;
        private final List<Pair<Fetcher.Factory<? extends Object>, Class<? extends Object>>> fetcherFactories;
        private final List<Interceptor> interceptors;
        private final List<Pair<Keyer<? extends Object>, Class<? extends Object>>> keyers;
        private final List<Pair<Mapper<? extends Object, ?>, Class<? extends Object>>> mappers;

        public final List<Interceptor> getInterceptors$coil_base_release() {
            return this.interceptors;
        }

        public final List<Pair<Mapper<? extends Object, ?>, Class<? extends Object>>> getMappers$coil_base_release() {
            return this.mappers;
        }

        public final List<Pair<Keyer<? extends Object>, Class<? extends Object>>> getKeyers$coil_base_release() {
            return this.keyers;
        }

        public final List<Pair<Fetcher.Factory<? extends Object>, Class<? extends Object>>> getFetcherFactories$coil_base_release() {
            return this.fetcherFactories;
        }

        public final List<Decoder.Factory> getDecoderFactories$coil_base_release() {
            return this.decoderFactories;
        }

        public Builder() {
            this.interceptors = new ArrayList();
            this.mappers = new ArrayList();
            this.keyers = new ArrayList();
            this.fetcherFactories = new ArrayList();
            this.decoderFactories = new ArrayList();
        }

        public Builder(ComponentRegistry registry) {
            this.interceptors = CollectionsKt.toMutableList((Collection) registry.getInterceptors());
            this.mappers = CollectionsKt.toMutableList((Collection) registry.getMappers());
            this.keyers = CollectionsKt.toMutableList((Collection) registry.getKeyers());
            this.fetcherFactories = CollectionsKt.toMutableList((Collection) registry.getFetcherFactories());
            this.decoderFactories = CollectionsKt.toMutableList((Collection) registry.getDecoderFactories());
        }

        public final Builder add(Interceptor interceptor) {
            Builder $this$add_u24lambda_u240 = this;
            $this$add_u24lambda_u240.interceptors.add(interceptor);
            return this;
        }

        public final /* synthetic */ <T> Builder add(Mapper<T, ?> mapper) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return add(mapper, Object.class);
        }

        public final <T> Builder add(Mapper<T, ?> mapper, Class<T> type) {
            Builder $this$add_u24lambda_u241 = this;
            $this$add_u24lambda_u241.mappers.add(TuplesKt.to(mapper, type));
            return this;
        }

        public final /* synthetic */ <T> Builder add(Keyer<T> keyer) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return add(keyer, Object.class);
        }

        public final <T> Builder add(Keyer<T> keyer, Class<T> type) {
            Builder $this$add_u24lambda_u242 = this;
            $this$add_u24lambda_u242.keyers.add(TuplesKt.to(keyer, type));
            return this;
        }

        public final /* synthetic */ <T> Builder add(Fetcher.Factory<T> factory) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return add(factory, Object.class);
        }

        public final <T> Builder add(Fetcher.Factory<T> factory, Class<T> type) {
            Builder $this$add_u24lambda_u243 = this;
            $this$add_u24lambda_u243.fetcherFactories.add(TuplesKt.to(factory, type));
            return this;
        }

        public final Builder add(Decoder.Factory factory) {
            Builder $this$add_u24lambda_u244 = this;
            $this$add_u24lambda_u244.decoderFactories.add(factory);
            return this;
        }

        public final ComponentRegistry build() {
            return new ComponentRegistry(Collections.toImmutableList(this.interceptors), Collections.toImmutableList(this.mappers), Collections.toImmutableList(this.keyers), Collections.toImmutableList(this.fetcherFactories), Collections.toImmutableList(this.decoderFactories), null);
        }
    }
}
