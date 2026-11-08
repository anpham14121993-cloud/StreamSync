package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ2\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0004\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/data/addon/AddonMetaBehaviorHints;", "", "defaultVideoId", "", "isLive", "", "hasScheduledVideos", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getDefaultVideoId", "()Ljava/lang/String;", "getHasScheduledVideos", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/streamsync/app/data/addon/AddonMetaBehaviorHints;", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonMetaBehaviorHints {
    public static final int $stable = 0;
    private final String defaultVideoId;
    private final Boolean hasScheduledVideos;
    private final Boolean isLive;

    public AddonMetaBehaviorHints() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ AddonMetaBehaviorHints copy$default(AddonMetaBehaviorHints addonMetaBehaviorHints, String str, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonMetaBehaviorHints.defaultVideoId;
        }
        if ((i & 2) != 0) {
            bool = addonMetaBehaviorHints.isLive;
        }
        if ((i & 4) != 0) {
            bool2 = addonMetaBehaviorHints.hasScheduledVideos;
        }
        return addonMetaBehaviorHints.copy(str, bool, bool2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDefaultVideoId() {
        return this.defaultVideoId;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getIsLive() {
        return this.isLive;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getHasScheduledVideos() {
        return this.hasScheduledVideos;
    }

    public final AddonMetaBehaviorHints copy(String defaultVideoId, Boolean isLive, Boolean hasScheduledVideos) {
        return new AddonMetaBehaviorHints(defaultVideoId, isLive, hasScheduledVideos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonMetaBehaviorHints)) {
            return false;
        }
        AddonMetaBehaviorHints addonMetaBehaviorHints = (AddonMetaBehaviorHints) other;
        return Intrinsics.areEqual(this.defaultVideoId, addonMetaBehaviorHints.defaultVideoId) && Intrinsics.areEqual(this.isLive, addonMetaBehaviorHints.isLive) && Intrinsics.areEqual(this.hasScheduledVideos, addonMetaBehaviorHints.hasScheduledVideos);
    }

    public int hashCode() {
        return ((((this.defaultVideoId == null ? 0 : this.defaultVideoId.hashCode()) * 31) + (this.isLive == null ? 0 : this.isLive.hashCode())) * 31) + (this.hasScheduledVideos != null ? this.hasScheduledVideos.hashCode() : 0);
    }

    public String toString() {
        return "AddonMetaBehaviorHints(defaultVideoId=" + this.defaultVideoId + ", isLive=" + this.isLive + ", hasScheduledVideos=" + this.hasScheduledVideos + ")";
    }

    public AddonMetaBehaviorHints(String defaultVideoId, Boolean isLive, Boolean hasScheduledVideos) {
        this.defaultVideoId = defaultVideoId;
        this.isLive = isLive;
        this.hasScheduledVideos = hasScheduledVideos;
    }

    public /* synthetic */ AddonMetaBehaviorHints(String str, Boolean bool, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? null : bool2);
    }

    public final String getDefaultVideoId() {
        return this.defaultVideoId;
    }

    public final Boolean isLive() {
        return this.isLive;
    }

    public final Boolean getHasScheduledVideos() {
        return this.hasScheduledVideos;
    }
}
