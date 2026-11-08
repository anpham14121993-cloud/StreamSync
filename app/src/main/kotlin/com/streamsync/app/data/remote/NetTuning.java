package com.streamsync.app.data.remote;

import android.content.Context;
import com.streamsync.app.net.DnsResolverFactory;
import com.streamsync.app.net.DohResolver;
import com.streamsync.app.net.DpiBypassSocketFactory;
import com.streamsync.app.net.Ipv4FirstDns;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.PlaybackSettingsStore;
import javax.net.SocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;
import okhttp3.OkHttpClient;

/* compiled from: NetTuning.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/streamsync/app/data/remote/NetTuning;", "", "()V", "apply", "", "context", "Landroid/content/Context;", "builder", "Lokhttp3/OkHttpClient$Builder;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class NetTuning {
    public static final int $stable = 0;
    public static final NetTuning INSTANCE = new NetTuning();

    private NetTuning() {
    }

    public final void apply(Context context, OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        PlaybackSettingsStore store = new PlaybackSettingsStore(applicationContext);
        DohResolver forApp = DnsResolverFactory.INSTANCE.forApp(context);
        DohResolver base = forApp != null ? forApp : Dns.SYSTEM;
        builder.dns(new Ipv4FirstDns(base));
        if (store.isDpiBypassEnabled()) {
            SocketFactory socketFactory = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "getDefault(...)");
            builder.socketFactory(new DpiBypassSocketFactory(socketFactory, store.getDpiFragmentSize(), store.getDpiDelayMs()));
        }
    }
}
