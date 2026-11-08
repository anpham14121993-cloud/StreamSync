package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/addon/MetaResponse;", "", "meta", "Lcom/streamsync/app/data/addon/AddonMeta;", "videos", "", "Lcom/streamsync/app/data/addon/AddonVideo;", "(Lcom/streamsync/app/data/addon/AddonMeta;Ljava/util/List;)V", "getMeta", "()Lcom/streamsync/app/data/addon/AddonMeta;", "getVideos", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "mergedMeta", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class MetaResponse {
    public static final int $stable = 8;
    private final AddonMeta meta;
    private final List<AddonVideo> videos;

    /* JADX WARN: Multi-variable type inference failed */
    public MetaResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MetaResponse copy$default(MetaResponse metaResponse, AddonMeta addonMeta, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            addonMeta = metaResponse.meta;
        }
        if ((i & 2) != 0) {
            list = metaResponse.videos;
        }
        return metaResponse.copy(addonMeta, list);
    }

    /* renamed from: component1, reason: from getter */
    public final AddonMeta getMeta() {
        return this.meta;
    }

    public final List<AddonVideo> component2() {
        return this.videos;
    }

    public final MetaResponse copy(AddonMeta meta, List<AddonVideo> videos) {
        return new MetaResponse(meta, videos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetaResponse)) {
            return false;
        }
        MetaResponse metaResponse = (MetaResponse) other;
        return Intrinsics.areEqual(this.meta, metaResponse.meta) && Intrinsics.areEqual(this.videos, metaResponse.videos);
    }

    public int hashCode() {
        return ((this.meta == null ? 0 : this.meta.hashCode()) * 31) + (this.videos != null ? this.videos.hashCode() : 0);
    }

    public String toString() {
        return "MetaResponse(meta=" + this.meta + ", videos=" + this.videos + ")";
    }

    public MetaResponse(AddonMeta meta, List<AddonVideo> list) {
        this.meta = meta;
        this.videos = list;
    }

    public /* synthetic */ MetaResponse(AddonMeta addonMeta, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : addonMeta, (i & 2) != 0 ? null : list);
    }

    public final AddonMeta getMeta() {
        return this.meta;
    }

    public final List<AddonVideo> getVideos() {
        return this.videos;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
    
        if (r2 == null) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.streamsync.app.data.addon.AddonMeta mergedMeta() {
        /*
            r30 = this;
            r0 = r30
            com.streamsync.app.data.addon.AddonMeta r1 = r0.meta
            r2 = 0
            if (r1 != 0) goto L8
            return r2
        L8:
            java.util.List r3 = r1.getVideos()
            if (r3 == 0) goto L1e
            r4 = r3
            r5 = 0
            r6 = r4
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ 1
            if (r6 == 0) goto L1c
            r2 = r3
        L1c:
            if (r2 != 0) goto L20
        L1e:
            java.util.List<com.streamsync.app.data.addon.AddonVideo> r2 = r0.videos
        L20:
            r29 = r2
            if (r29 != 0) goto L26
            r2 = r1
            goto L54
        L26:
            r27 = 33550335(0x1ffefff, float:9.4016584E-38)
            r28 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r14 = r29
            com.streamsync.app.data.addon.AddonMeta r2 = com.streamsync.app.data.addon.AddonMeta.copy$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.MetaResponse.mergedMeta():com.streamsync.app.data.addon.AddonMeta");
    }
}
