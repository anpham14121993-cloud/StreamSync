package com.streamsync.app.ui.player;

import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: StreamRequestHeaders.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005J(\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005J\u001c\u0010\f\u001a\u00020\b2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/streamsync/app/ui/player/StreamRequestHeaders;", "", "()V", "current", "Ljava/util/concurrent/atomic/AtomicReference;", "", "", "clear", "", "get", "sanitize", LastStreamStore.KEY_HEADERS, "set", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class StreamRequestHeaders {
    public static final StreamRequestHeaders INSTANCE = new StreamRequestHeaders();
    private static final AtomicReference<Map<String, String>> current = new AtomicReference<>(MapsKt.emptyMap());
    public static final int $stable = 8;

    private StreamRequestHeaders() {
    }

    public final Map<String, String> get() {
        Map<String, String> map = current.get();
        Intrinsics.checkNotNullExpressionValue(map, "get(...)");
        return map;
    }

    public final void set(Map<String, String> headers) {
        current.set(sanitize(headers));
    }

    public final void clear() {
        current.set(MapsKt.emptyMap());
    }

    public final Map<String, String> sanitize(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap out = new LinkedHashMap(headers.size());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String rawKey = entry.getKey();
            String rawValue = entry.getValue();
            String key = StringsKt.trim((CharSequence) rawKey).toString();
            String value = StringsKt.trim((CharSequence) rawValue).toString();
            if (!(key.length() == 0)) {
                if (!(value.length() == 0) && !StringsKt.equals(key, HttpHeaders.RANGE, true) && !StringsKt.equals(key, HttpHeaders.HOST, true) && !StringsKt.equals(key, HttpHeaders.CONTENT_LENGTH, true) && !StringsKt.contains$default((CharSequence) key, '\n', false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) key, '\r', false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) value, '\n', false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) value, '\r', false, 2, (Object) null)) {
                    out.put(key, value);
                }
            }
        }
        return out;
    }
}
