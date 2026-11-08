package com.streamsync.app.net;

import com.streamsync.app.settings.BufferCeiling;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;

/* compiled from: Ipv4FirstDns.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/streamsync/app/net/Ipv4FirstDns;", "Lokhttp3/Dns;", "delegate", "(Lokhttp3/Dns;)V", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class Ipv4FirstDns implements Dns {
    public static final int $stable = 8;
    private final Dns delegate;

    public Ipv4FirstDns(Dns delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String hostname) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        try {
            Result.Companion companion = Result.INSTANCE;
            Ipv4FirstDns $this$lookup_u24lambda_u240 = this;
            m6717constructorimpl = Result.m6717constructorimpl($this$lookup_u24lambda_u240.delegate.lookup(hostname));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = null;
        }
        List resolved = (List) m6717constructorimpl;
        if (resolved == null) {
            resolved = CollectionsKt.emptyList();
        }
        if (resolved.size() < 2) {
            return resolved;
        }
        Iterable $this$sortedBy$iv = resolved;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.net.Ipv4FirstDns$lookup$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(!(((InetAddress) t) instanceof Inet4Address) ? 1 : 0), Integer.valueOf(!(((InetAddress) t2) instanceof Inet4Address) ? 1 : 0));
            }
        });
    }
}
