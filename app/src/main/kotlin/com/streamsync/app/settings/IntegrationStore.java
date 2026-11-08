package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: IntegrationStore.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0015J\u000e\u0010(\u001a\u00020)2\u0006\u0010'\u001a\u00020\u0015R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR$\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R0\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00182\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00150\u00188F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n \"*\u0004\u0018\u00010!0!X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010#\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\r¨\u0006+"}, d2 = {"Lcom/streamsync/app/settings/IntegrationStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "badgesActive", "", "getBadgesActive", "()Z", "value", "badgesEnabled", "getBadgesEnabled", "setBadgesEnabled", "(Z)V", "", "badgesIndex", "getBadgesIndex", "()I", "setBadgesIndex", "(I)V", "badgesUrl", "", "getBadgesUrl", "()Ljava/lang/String;", "", "badgesUrls", "getBadgesUrls", "()Ljava/util/List;", "setBadgesUrls", "(Ljava/util/List;)V", "gson", "Lcom/google/gson/Gson;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "showStreamProvider", "getShowStreamProvider", "setShowStreamProvider", "addBadgesUrl", "url", "removeBadgesUrl", "", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class IntegrationStore {
    private static final String KEY_BADGES_ENABLED = "badges_enabled";
    private static final String KEY_BADGES_INDEX = "badges_index";
    private static final String KEY_BADGES_URLS = "badges_urls_json";
    private static final String KEY_SHOW_PROVIDER = "show_stream_provider";
    public static final int MAX_BADGES_URL = 3;
    private final Gson gson;
    private final SharedPreferences prefs;
    public static final int $stable = 8;

    public IntegrationStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = context.getApplicationContext().getSharedPreferences("integrations", 0);
        this.gson = new Gson();
    }

    public final boolean getBadgesEnabled() {
        return this.prefs.getBoolean(KEY_BADGES_ENABLED, false);
    }

    public final void setBadgesEnabled(boolean value) {
        this.prefs.edit().putBoolean(KEY_BADGES_ENABLED, value).apply();
    }

    public final List<String> getBadgesUrls() {
        Object m6717constructorimpl;
        String raw = this.prefs.getString(KEY_BADGES_URLS, "");
        if (raw == null) {
            return CollectionsKt.emptyList();
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            IntegrationStore $this$_get_badgesUrls__u24lambda_u240 = this;
            Type type = new TypeToken<List<? extends String>>() { // from class: com.streamsync.app.settings.IntegrationStore$badgesUrls$1$type$1
            }.getType();
            List list = (List) $this$_get_badgesUrls__u24lambda_u240.gson.fromJson(raw, type);
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            m6717constructorimpl = Result.m6717constructorimpl(list);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        List emptyList = CollectionsKt.emptyList();
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = emptyList;
        }
        Iterable $this$map$iv = (Iterable) m6717constructorimpl;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return CollectionsKt.take(CollectionsKt.distinct((List) destination$iv$iv2), 3);
    }

    public final void setBadgesUrls(List<String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        List<String> $this$map$iv = value;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(StringsKt.trim((CharSequence) it).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            if (it2.length() > 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List clean = CollectionsKt.take(CollectionsKt.distinct((List) destination$iv$iv2), 3);
        this.prefs.edit().putString(KEY_BADGES_URLS, this.gson.toJson(clean)).apply();
    }

    public final int getBadgesIndex() {
        return this.prefs.getInt(KEY_BADGES_INDEX, 0);
    }

    public final void setBadgesIndex(int value) {
        this.prefs.edit().putInt(KEY_BADGES_INDEX, RangesKt.coerceIn(value, 0, 2)).apply();
    }

    public final String getBadgesUrl() {
        String str = (String) CollectionsKt.getOrNull(getBadgesUrls(), getBadgesIndex());
        return str == null ? "" : str;
    }

    public final boolean addBadgesUrl(String url) {
        Iterable $this$any$iv;
        Intrinsics.checkNotNullParameter(url, "url");
        String clean = StringsKt.trim((CharSequence) url).toString();
        if (clean.length() == 0) {
            return false;
        }
        List current = getBadgesUrls();
        List $this$any$iv2 = current;
        if (!($this$any$iv2 instanceof Collection) || !$this$any$iv2.isEmpty()) {
            Iterator it = $this$any$iv2.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    String it2 = (String) element$iv;
                    if (StringsKt.equals(it2, clean, true)) {
                        $this$any$iv = 1;
                        break;
                    }
                } else {
                    $this$any$iv = null;
                    break;
                }
            }
        } else {
            $this$any$iv = null;
        }
        if ($this$any$iv != null || current.size() >= 3) {
            return false;
        }
        setBadgesUrls(CollectionsKt.plus((Collection<? extends String>) current, clean));
        return true;
    }

    public final void removeBadgesUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Iterable current = getBadgesUrls();
        Iterable $this$filterNot$iv = current;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it = (String) element$iv$iv;
            if (!Intrinsics.areEqual(it, url)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List remaining = (List) destination$iv$iv;
        setBadgesUrls(remaining);
        if (getBadgesIndex() >= remaining.size()) {
            setBadgesIndex(RangesKt.coerceAtLeast(remaining.size() - 1, 0));
        }
    }

    public final boolean getBadgesActive() {
        return getBadgesEnabled() && (StringsKt.isBlank(getBadgesUrl()) ^ true);
    }

    public final boolean getShowStreamProvider() {
        return this.prefs.getBoolean(KEY_SHOW_PROVIDER, false);
    }

    public final void setShowStreamProvider(boolean value) {
        this.prefs.edit().putBoolean(KEY_SHOW_PROVIDER, value).apply();
    }
}
