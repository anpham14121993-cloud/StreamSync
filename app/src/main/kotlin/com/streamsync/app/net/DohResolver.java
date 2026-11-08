package com.streamsync.app.net;

import android.content.Context;
import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DnsProvider;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: DohResolver.kt */
@Metadata(d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006*\u0001\f\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0015\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/net/DohResolver;", "Lokhttp3/Dns;", "context", "Landroid/content/Context;", "provider", "Lcom/streamsync/app/settings/DnsProvider;", "(Landroid/content/Context;Lcom/streamsync/app/settings/DnsProvider;)V", "app", "kotlin.jvm.PlatformType", "bootstrap", "Lokhttp3/OkHttpClient;", "cache", "com/streamsync/app/net/DohResolver$cache$1", "Lcom/streamsync/app/net/DohResolver$cache$1;", "systemDns", "lookup", "", "Ljava/net/InetAddress;", "hostname", "", "parseAddresses", TtmlNode.TAG_BODY, "parseAddresses$app_mobileDebug", "queryDoh", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class DohResolver implements Dns {
    private final Context app;
    private final OkHttpClient bootstrap;
    private final DohResolver$cache$1 cache;
    private final DnsProvider provider;
    private final Dns systemDns;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Regex IPV4_OR_V6 = new Regex("^[0-9.]+$|^[0-9a-fA-F:]+$");

    public DohResolver(Context context, DnsProvider provider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.provider = provider;
        this.app = context.getApplicationContext();
        this.systemDns = Dns.SYSTEM;
        this.bootstrap = new OkHttpClient.Builder().callTimeout(5L, TimeUnit.SECONDS).connectTimeout(5L, TimeUnit.SECONDS).dns(this.systemDns).build();
        this.cache = new DohResolver$cache$1();
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
    
        if (r2 != null) goto L30;
     */
    @Override // okhttp3.Dns
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<java.net.InetAddress> lookup(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "hostname"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = r6
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            kotlin.text.Regex r1 = com.streamsync.app.net.DohResolver.IPV4_OR_V6
            boolean r0 = r1.matches(r0)
            if (r0 == 0) goto L17
            okhttp3.Dns r0 = r5.systemDns
            java.util.List r0 = r0.lookup(r6)
            return r0
        L17:
            com.streamsync.app.net.DohResolver$cache$1 r0 = r5.cache
            monitor-enter(r0)
            r1 = 0
            com.streamsync.app.net.DohResolver$cache$1 r2 = r5.cache     // Catch: java.lang.Throwable -> L89
            java.lang.Object r2 = r2.get(r6)     // Catch: java.lang.Throwable -> L89
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L2b
            r3 = 0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Throwable -> L89
            monitor-exit(r0)
            return r2
        L2b:
            monitor-exit(r0)
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L3b
            r0 = r5
            com.streamsync.app.net.DohResolver r0 = (com.streamsync.app.net.DohResolver) r0     // Catch: java.lang.Throwable -> L3b
            r1 = 0
            java.util.List r2 = r0.queryDoh(r6)     // Catch: java.lang.Throwable -> L3b
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r2)     // Catch: java.lang.Throwable -> L3b
            goto L46
        L3b:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r0)
        L46:
            boolean r1 = kotlin.Result.m6723isFailureimpl(r0)
            r2 = 0
            if (r1 == 0) goto L4e
            r0 = r2
        L4e:
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L64
        L53:
            r1 = r0
            r3 = 0
            r4 = r1
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            r4 = r4 ^ 1
            if (r4 == 0) goto L61
            r2 = r0
        L61:
            if (r2 == 0) goto L64
            goto L6a
        L64:
            okhttp3.Dns r0 = r5.systemDns
            java.util.List r2 = r0.lookup(r6)
        L6a:
            r0 = r2
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ 1
            if (r1 == 0) goto L88
            com.streamsync.app.net.DohResolver$cache$1 r1 = r5.cache
            monitor-enter(r1)
            r2 = 0
            com.streamsync.app.net.DohResolver$cache$1 r3 = r5.cache     // Catch: java.lang.Throwable -> L85
            java.util.Map r3 = (java.util.Map) r3     // Catch: java.lang.Throwable -> L85
            r3.put(r6, r0)     // Catch: java.lang.Throwable -> L85
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L85
            monitor-exit(r1)
            goto L88
        L85:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L88:
            return r0
        L89:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.net.DohResolver.lookup(java.lang.String):java.util.List");
    }

    private final List<InetAddress> queryDoh(String hostname) {
        String body;
        HttpUrl url = this.provider.dohJsonUrlBuilder(hostname).addQueryParameter(HintConstants.AUTOFILL_HINT_NAME, hostname).build();
        Request request = new Request.Builder().url(url).header(HttpHeaders.ACCEPT, "application/dns-json").build();
        Response execute = this.bootstrap.newCall(request).execute();
        try {
            Response response = execute;
            if (!response.isSuccessful()) {
                List<InetAddress> emptyList = CollectionsKt.emptyList();
                CloseableKt.closeFinally(execute, null);
                return emptyList;
            }
            ResponseBody body2 = response.body();
            if (body2 != null && (body = body2.string()) != null) {
                List<InetAddress> parseAddresses$app_mobileDebug = parseAddresses$app_mobileDebug(body);
                CloseableKt.closeFinally(execute, null);
                return parseAddresses$app_mobileDebug;
            }
            List<InetAddress> emptyList2 = CollectionsKt.emptyList();
            CloseableKt.closeFinally(execute, null);
            return emptyList2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(execute, th);
                throw th2;
            }
        }
    }

    public final List<InetAddress> parseAddresses$app_mobileDebug(String body) {
        JSONArray answers;
        Intrinsics.checkNotNullParameter(body, "body");
        List out = new ArrayList();
        try {
            Result.Companion companion = Result.INSTANCE;
            DohResolver dohResolver = this;
            answers = new JSONObject(body).optJSONArray("Answer");
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (answers == null) {
            return CollectionsKt.emptyList();
        }
        Intrinsics.checkNotNull(answers);
        int length = answers.length();
        for (int i = 0; i < length; i++) {
            JSONObject entry = answers.optJSONObject(i);
            if (entry != null) {
                Intrinsics.checkNotNull(entry);
                boolean z = true;
                if (entry.optInt("type") == 1 || entry.optInt("type") == 28) {
                    String optString = entry.optString("data");
                    Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                    String data = StringsKt.trim((CharSequence) optString).toString();
                    if (!(data.length() == 0)) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            InetAddress it = InetAddress.getByName(data);
                            if (it.isLoopbackAddress() && !it.isLoopbackAddress()) {
                                z = false;
                            }
                            Unit unit = null;
                            if (!z) {
                                it = null;
                            }
                            if (it != null) {
                                out.add(it);
                                unit = Unit.INSTANCE;
                            }
                            Result.m6717constructorimpl(unit);
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            Result.m6717constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                }
            }
        }
        Result.m6717constructorimpl(Unit.INSTANCE);
        return out;
    }

    /* compiled from: DohResolver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/net/DohResolver$Companion;", "", "()V", "IPV4_OR_V6", "Lkotlin/text/Regex;", "getIPV4_OR_V6", "()Lkotlin/text/Regex;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Regex getIPV4_OR_V6() {
            return DohResolver.IPV4_OR_V6;
        }
    }
}
