package com.streamsync.app.net;

import android.content.Context;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DnsProvider;
import com.streamsync.app.settings.PlaybackSettingsStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DohResolver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/streamsync/app/net/DnsResolverFactory;", "", "()V", "cached", "Lcom/streamsync/app/net/DohResolver;", "cachedProvider", "Lcom/streamsync/app/settings/DnsProvider;", "forApp", "context", "Landroid/content/Context;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class DnsResolverFactory {
    private static volatile DohResolver cached;
    private static volatile DnsProvider cachedProvider;
    public static final DnsResolverFactory INSTANCE = new DnsResolverFactory();
    public static final int $stable = 8;

    private DnsResolverFactory() {
    }

    public final DohResolver forApp(Context context) {
        DohResolver dohResolver;
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        DnsProvider provider = new PlaybackSettingsStore(applicationContext).getDnsProvider();
        if (provider == DnsProvider.SYSTEM) {
            return null;
        }
        DohResolver existing = cached;
        if (existing != null && cachedProvider == provider) {
            return existing;
        }
        synchronized (this) {
            if (cached == null || cachedProvider != provider) {
                Context applicationContext2 = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
                cached = new DohResolver(applicationContext2, provider);
                cachedProvider = provider;
            }
            dohResolver = cached;
        }
        return dohResolver;
    }
}
