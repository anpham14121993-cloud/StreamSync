package com.streamsync.app.data.repository;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: StreamSelectionStore.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006J*\u0010\r\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/repository/StreamSelectionStore;", "", "()V", "selections", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/streamsync/app/data/model/StreamSource;", "clear", "", "get", "mediaId", "put", "source", "rememberSourceFor", "streamUrl", "addonId", "addonName", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class StreamSelectionStore {
    public static final StreamSelectionStore INSTANCE = new StreamSelectionStore();
    private static final ConcurrentHashMap<String, StreamSource> selections = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private StreamSelectionStore() {
    }

    public final void put(StreamSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (!StringsKt.isBlank(source.getMediaId())) {
            selections.put(source.getMediaId(), source);
        }
    }

    public final StreamSource get(String mediaId) {
        if (mediaId != null) {
            return selections.get(mediaId);
        }
        return null;
    }

    public final void rememberSourceFor(String mediaId, String streamUrl, String addonId, String addonName) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(streamUrl, "streamUrl");
        if (!StringsKt.isBlank(mediaId) && !StringsKt.isBlank(streamUrl)) {
            String id = StringsKt.substringBefore$default(mediaId, ':', (String) null, 2, (Object) null);
            selections.put(mediaId, new StreamSource(mediaId, streamUrl, null, null, null, null, null, null, null, 0, addonId == null ? id : addonId, addonName, null, null, null, 0, 62460, null));
        }
    }

    public final void clear() {
        selections.clear();
    }
}
