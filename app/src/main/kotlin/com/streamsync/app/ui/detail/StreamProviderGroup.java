package com.streamsync.app.ui.detail;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreamProviderGroups.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/streamsync/app/ui/detail/StreamProviderGroup;", "", "provider", "", "key", "sources", "", "Lcom/streamsync/app/data/model/StreamSource;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "isUnknown", "", "()Z", "getKey", "()Ljava/lang/String;", "getProvider", "getSources", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final /* data */ class StreamProviderGroup {
    public static final int $stable = 8;
    private final String key;
    private final String provider;
    private final List<StreamSource> sources;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StreamProviderGroup copy$default(StreamProviderGroup streamProviderGroup, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = streamProviderGroup.provider;
        }
        if ((i & 2) != 0) {
            str2 = streamProviderGroup.key;
        }
        if ((i & 4) != 0) {
            list = streamProviderGroup.sources;
        }
        return streamProviderGroup.copy(str, str2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* renamed from: component2, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    public final List<StreamSource> component3() {
        return this.sources;
    }

    public final StreamProviderGroup copy(String provider, String key, List<StreamSource> sources) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(sources, "sources");
        return new StreamProviderGroup(provider, key, sources);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamProviderGroup)) {
            return false;
        }
        StreamProviderGroup streamProviderGroup = (StreamProviderGroup) other;
        return Intrinsics.areEqual(this.provider, streamProviderGroup.provider) && Intrinsics.areEqual(this.key, streamProviderGroup.key) && Intrinsics.areEqual(this.sources, streamProviderGroup.sources);
    }

    public int hashCode() {
        return (((this.provider.hashCode() * 31) + this.key.hashCode()) * 31) + this.sources.hashCode();
    }

    public String toString() {
        return "StreamProviderGroup(provider=" + this.provider + ", key=" + this.key + ", sources=" + this.sources + ")";
    }

    public StreamProviderGroup(String provider, String key, List<StreamSource> sources) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(sources, "sources");
        this.provider = provider;
        this.key = key;
        this.sources = sources;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getKey() {
        return this.key;
    }

    public final List<StreamSource> getSources() {
        return this.sources;
    }

    public final boolean isUnknown() {
        return Intrinsics.areEqual(this.provider, StreamProviderGroupsKt.UNKNOWN_PROVIDER);
    }
}
