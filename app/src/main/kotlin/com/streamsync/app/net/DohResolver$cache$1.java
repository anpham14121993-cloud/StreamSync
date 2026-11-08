package com.streamsync.app.net;

import com.streamsync.app.settings.BufferCeiling;
import java.net.InetAddress;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DohResolver.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010'\n\u0000*\u0001\u0000\b\n\u0018\u00002*\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001j\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003`\u0005J\"\u0010\u0006\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\tH\u0014¨\u0006\n"}, d2 = {"com/streamsync/app/net/DohResolver$cache$1", "Ljava/util/LinkedHashMap;", "", "", "Ljava/net/InetAddress;", "Lkotlin/collections/LinkedHashMap;", "removeEldestEntry", "", "eldest", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class DohResolver$cache$1 extends LinkedHashMap<String, List<? extends InetAddress>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DohResolver$cache$1() {
        super(32, 0.75f, true);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object key) {
        if (key instanceof String) {
            return containsKey((String) key);
        }
        return false;
    }

    public /* bridge */ boolean containsKey(String key) {
        return super.containsKey((Object) key);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object value) {
        if (value instanceof List) {
            return containsValue((List<? extends InetAddress>) value);
        }
        return false;
    }

    public /* bridge */ boolean containsValue(List<? extends InetAddress> list) {
        return super.containsValue((Object) list);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, List<InetAddress>>> entrySet() {
        return getEntries();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ List<InetAddress> get(Object key) {
        if (key instanceof String) {
            return get((String) key);
        }
        return null;
    }

    public /* bridge */ List<InetAddress> get(String key) {
        return (List) super.get((Object) key);
    }

    public /* bridge */ Set<Map.Entry<String, List<InetAddress>>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object getOrDefault(Object key, Object defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, (List<? extends InetAddress>) defaultValue);
    }

    public final /* bridge */ List getOrDefault(Object key, List defaultValue) {
        return !(key instanceof String) ? defaultValue : getOrDefault((String) key, (List<? extends InetAddress>) defaultValue);
    }

    public /* bridge */ List<InetAddress> getOrDefault(String key, List<? extends InetAddress> list) {
        return (List) super.getOrDefault((Object) key, (String) list);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<List<InetAddress>> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ List<InetAddress> remove(Object key) {
        if (key instanceof String) {
            return remove((String) key);
        }
        return null;
    }

    public /* bridge */ List<InetAddress> remove(String key) {
        return (List) super.remove((Object) key);
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object key, Object value) {
        if ((key instanceof String) && (value instanceof List)) {
            return remove((String) key, (List<? extends InetAddress>) value);
        }
        return false;
    }

    public /* bridge */ boolean remove(String key, List<? extends InetAddress> list) {
        return super.remove((Object) key, (Object) list);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<List<InetAddress>> values() {
        return getValues();
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<String, List<? extends InetAddress>> eldest) {
        Intrinsics.checkNotNullParameter(eldest, "eldest");
        return size() > 64;
    }
}
