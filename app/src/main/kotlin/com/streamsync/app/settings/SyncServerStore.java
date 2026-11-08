package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SyncServerStore.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\b\u0010\f\u001a\u0004\u0018\u00010\nJ\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/streamsync/app/settings/SyncServerStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "prefs", "Landroid/content/SharedPreferences;", "deviceId", "", "getConfigId", "getServerUrl", "isConfigured", "", "setServerUrl", "", "url", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class SyncServerStore {
    private static final String KEY_CONFIG_ID = "server_config_id";
    private static final String KEY_URL = "server_url";
    private final Context appContext;
    private final SharedPreferences prefs;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Regex CONFIG_ID_RE = new Regex("cfg_[A-Za-z0-9_-]{20,120}");
    private static final List<String> FILE_SUFFIXES = CollectionsKt.listOf((Object[]) new String[]{".json", ".js", ".m3u8", ".mpd"});

    public SyncServerStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.prefs = this.appContext.getSharedPreferences("sync_server", 0);
    }

    public final String getServerUrl() {
        String it = this.prefs.getString(KEY_URL, null);
        if (it == null || !(!StringsKt.isBlank(it))) {
            return null;
        }
        return it;
    }

    public final void setServerUrl(String url) {
        Pair<String, String> splitConfigId = INSTANCE.splitConfigId(url);
        String base = splitConfigId.component1();
        String configId = splitConfigId.component2();
        this.prefs.edit().putString(KEY_URL, base).putString(KEY_CONFIG_ID, configId == null ? "" : configId).apply();
    }

    public final boolean isConfigured() {
        return getServerUrl() != null;
    }

    public final String getConfigId() {
        String it = this.prefs.getString(KEY_CONFIG_ID, null);
        if (it == null || !(!StringsKt.isBlank(it))) {
            return null;
        }
        return it;
    }

    public final String deviceId() {
        String it = Settings.Secure.getString(this.appContext.getContentResolver(), "android_id");
        if (it != null) {
            if (!(!StringsKt.isBlank(it))) {
                it = null;
            }
            if (it != null) {
                return it;
            }
        }
        return "unknown-device";
    }

    /* compiled from: SyncServerStore.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007J \u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\r2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/streamsync/app/settings/SyncServerStore$Companion;", "", "()V", "CONFIG_ID_RE", "Lkotlin/text/Regex;", "FILE_SUFFIXES", "", "", "KEY_CONFIG_ID", "KEY_URL", "normalize", "url", "splitConfigId", "Lkotlin/Pair;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<String, String> splitConfigId(String url) {
            String withScheme;
            boolean z;
            StringBuilder append;
            String trimmed = url != null ? StringsKt.trim((CharSequence) url).toString() : null;
            if (trimmed == null) {
                trimmed = "";
            }
            if (trimmed.length() == 0) {
                return TuplesKt.to(null, null);
            }
            if (StringsKt.startsWith$default(trimmed, "http://", false, 2, (Object) null) || StringsKt.startsWith$default(trimmed, "https://", false, 2, (Object) null)) {
                withScheme = trimmed;
            } else {
                withScheme = "https://" + trimmed;
            }
            String scheme = StringsKt.substringBefore$default(withScheme, "://", (String) null, 2, (Object) null);
            String afterScheme = StringsKt.substringAfter$default(withScheme, "://", (String) null, 2, (Object) null);
            String host = StringsKt.substringBefore$default(afterScheme, '/', (String) null, 2, (Object) null);
            String path = StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter(afterScheme, '/', ""), '?', (String) null, 2, (Object) null), '#', (String) null, 2, (Object) null);
            Iterable $this$filter$iv = StringsKt.split$default((CharSequence) path, new char[]{'/'}, false, 0, 6, (Object) null);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                if (it.length() > 0) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            List segments = (List) destination$iv$iv;
            int index$iv = 0;
            Iterator it2 = segments.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object item$iv = it2.next();
                    String it3 = (String) item$iv;
                    if (SyncServerStore.CONFIG_ID_RE.matches(it3)) {
                        break;
                    }
                    index$iv++;
                } else {
                    index$iv = -1;
                    break;
                }
            }
            int configIndex = index$iv;
            if (configIndex >= 0) {
                List prefix = CollectionsKt.take(segments, configIndex);
                String base = prefix.isEmpty() ? scheme + "://" + host + "/" : scheme + "://" + host + "/" + CollectionsKt.joinToString$default(prefix, "/", null, null, 0, null, null, 62, null) + "/";
                return TuplesKt.to(base, segments.get(configIndex));
            }
            String str = (String) CollectionsKt.lastOrNull(segments);
            String last = str != null ? str : "";
            Iterable $this$any$iv = SyncServerStore.FILE_SUFFIXES;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                z = false;
            } else {
                Iterator it4 = $this$any$iv.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        z = false;
                        break;
                    }
                    Object element$iv = it4.next();
                    String it5 = (String) element$iv;
                    int configIndex2 = configIndex;
                    if (StringsKt.endsWith(last, it5, true)) {
                        z = true;
                        break;
                    }
                    configIndex = configIndex2;
                }
            }
            boolean isFile = z;
            List dir = isFile ? CollectionsKt.dropLast(segments, 1) : segments;
            if (dir.isEmpty()) {
                append = new StringBuilder().append(scheme).append("://").append(host);
            } else {
                append = new StringBuilder().append(scheme).append("://").append(host).append("/").append(CollectionsKt.joinToString$default(dir, "/", null, null, 0, null, null, 62, null));
            }
            String base2 = append.append("/").toString();
            return TuplesKt.to(base2, null);
        }

        public final String normalize(String url) {
            String withScheme;
            String trimmed = url != null ? StringsKt.trim((CharSequence) url).toString() : null;
            if (trimmed == null) {
                trimmed = "";
            }
            if (trimmed.length() == 0) {
                return null;
            }
            if (StringsKt.startsWith$default(trimmed, "http://", false, 2, (Object) null) || StringsKt.startsWith$default(trimmed, "https://", false, 2, (Object) null)) {
                withScheme = trimmed;
            } else {
                withScheme = "https://" + trimmed;
            }
            return StringsKt.endsWith$default(withScheme, "/", false, 2, (Object) null) ? withScheme : withScheme + "/";
        }
    }
}
