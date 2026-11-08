package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bk\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jo\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006)"}, d2 = {"Lcom/streamsync/app/data/addon/AddonStream;", "", "url", "", "title", HintConstants.AUTOFILL_HINT_NAME, "description", "quality", "subtitles", "", "Lcom/streamsync/app/data/addon/AddonSubtitle;", "behaviorHints", "Lcom/streamsync/app/data/addon/AddonBehaviorHints;", "mimeType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/streamsync/app/data/addon/AddonBehaviorHints;Ljava/lang/String;)V", "getBehaviorHints", "()Lcom/streamsync/app/data/addon/AddonBehaviorHints;", "getDescription", "()Ljava/lang/String;", "getMimeType", "getName", "getQuality", "getSubtitles", "()Ljava/util/List;", "getTitle", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonStream {
    public static final int $stable = 8;
    private final AddonBehaviorHints behaviorHints;
    private final String description;
    private final String mimeType;
    private final String name;
    private final String quality;
    private final List<AddonSubtitle> subtitles;
    private final String title;
    private final String url;

    public AddonStream() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component5, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    public final List<AddonSubtitle> component6() {
        return this.subtitles;
    }

    /* renamed from: component7, reason: from getter */
    public final AddonBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    /* renamed from: component8, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    public final AddonStream copy(String url, String title, String name, String description, String quality, List<AddonSubtitle> subtitles, AddonBehaviorHints behaviorHints, String mimeType) {
        return new AddonStream(url, title, name, description, quality, subtitles, behaviorHints, mimeType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonStream)) {
            return false;
        }
        AddonStream addonStream = (AddonStream) other;
        return Intrinsics.areEqual(this.url, addonStream.url) && Intrinsics.areEqual(this.title, addonStream.title) && Intrinsics.areEqual(this.name, addonStream.name) && Intrinsics.areEqual(this.description, addonStream.description) && Intrinsics.areEqual(this.quality, addonStream.quality) && Intrinsics.areEqual(this.subtitles, addonStream.subtitles) && Intrinsics.areEqual(this.behaviorHints, addonStream.behaviorHints) && Intrinsics.areEqual(this.mimeType, addonStream.mimeType);
    }

    public int hashCode() {
        return ((((((((((((((this.url == null ? 0 : this.url.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.quality == null ? 0 : this.quality.hashCode())) * 31) + (this.subtitles == null ? 0 : this.subtitles.hashCode())) * 31) + (this.behaviorHints == null ? 0 : this.behaviorHints.hashCode())) * 31) + (this.mimeType != null ? this.mimeType.hashCode() : 0);
    }

    public String toString() {
        return "AddonStream(url=" + this.url + ", title=" + this.title + ", name=" + this.name + ", description=" + this.description + ", quality=" + this.quality + ", subtitles=" + this.subtitles + ", behaviorHints=" + this.behaviorHints + ", mimeType=" + this.mimeType + ")";
    }

    public AddonStream(String url, String title, String name, String description, String quality, List<AddonSubtitle> list, AddonBehaviorHints behaviorHints, String mimeType) {
        this.url = url;
        this.title = title;
        this.name = name;
        this.description = description;
        this.quality = quality;
        this.subtitles = list;
        this.behaviorHints = behaviorHints;
        this.mimeType = mimeType;
    }

    public /* synthetic */ AddonStream(String str, String str2, String str3, String str4, String str5, List list, AddonBehaviorHints addonBehaviorHints, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : list, (i & 64) != 0 ? null : addonBehaviorHints, (i & 128) == 0 ? str6 : null);
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final List<AddonSubtitle> getSubtitles() {
        return this.subtitles;
    }

    public final AddonBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final String getMimeType() {
        return this.mimeType;
    }
}
