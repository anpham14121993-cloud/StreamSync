package com.streamsync.app.data.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004¨\u0006\b"}, d2 = {"Lcom/streamsync/app/data/model/CatalogKey;", "", "()V", "of", "", "addonId", "type", "catalogId", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class CatalogKey {
    public static final int $stable = 0;
    public static final CatalogKey INSTANCE = new CatalogKey();

    private CatalogKey() {
    }

    public final String of(String addonId, String type, String catalogId) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (addonId == null ? "api" : addonId) + "|" + type + "|" + (catalogId == null ? TtmlNode.COMBINE_ALL : catalogId);
    }
}
