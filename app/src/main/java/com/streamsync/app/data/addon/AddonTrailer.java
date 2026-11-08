package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/data/addon/AddonTrailer;", "", "youtubeId", "", "type", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getType", "getYoutubeId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonTrailer {
    public static final int $stable = 0;
    private final String title;
    private final String type;
    private final String youtubeId;

    public AddonTrailer() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ AddonTrailer copy$default(AddonTrailer addonTrailer, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonTrailer.youtubeId;
        }
        if ((i & 2) != 0) {
            str2 = addonTrailer.type;
        }
        if ((i & 4) != 0) {
            str3 = addonTrailer.title;
        }
        return addonTrailer.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getYoutubeId() {
        return this.youtubeId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final AddonTrailer copy(String youtubeId, String type, String title) {
        Intrinsics.checkNotNullParameter(youtubeId, "youtubeId");
        return new AddonTrailer(youtubeId, type, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonTrailer)) {
            return false;
        }
        AddonTrailer addonTrailer = (AddonTrailer) other;
        return Intrinsics.areEqual(this.youtubeId, addonTrailer.youtubeId) && Intrinsics.areEqual(this.type, addonTrailer.type) && Intrinsics.areEqual(this.title, addonTrailer.title);
    }

    public int hashCode() {
        return (((this.youtubeId.hashCode() * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.title != null ? this.title.hashCode() : 0);
    }

    public String toString() {
        return "AddonTrailer(youtubeId=" + this.youtubeId + ", type=" + this.type + ", title=" + this.title + ")";
    }

    public AddonTrailer(String youtubeId, String type, String title) {
        Intrinsics.checkNotNullParameter(youtubeId, "youtubeId");
        this.youtubeId = youtubeId;
        this.type = type;
        this.title = title;
    }

    public /* synthetic */ AddonTrailer(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getYoutubeId() {
        return this.youtubeId;
    }

    public final String getType() {
        return this.type;
    }

    public final String getTitle() {
        return this.title;
    }
}
