package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003JI\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f¨\u0006!"}, d2 = {"Lcom/streamsync/app/data/model/SubtitleTrack;", "", "languageCode", "", "label", "url", "fullName", "addonName", "fromStream", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAddonName", "()Ljava/lang/String;", "getFromStream", "()Z", "getFullName", "getLabel", "getLanguageCode", "shortLabel", "getShortLabel", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class SubtitleTrack {
    public static final int $stable = 0;
    private final String addonName;
    private final boolean fromStream;
    private final String fullName;
    private final String label;
    private final String languageCode;
    private final String url;

    public static /* synthetic */ SubtitleTrack copy$default(SubtitleTrack subtitleTrack, String str, String str2, String str3, String str4, String str5, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleTrack.languageCode;
        }
        if ((i & 2) != 0) {
            str2 = subtitleTrack.label;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = subtitleTrack.url;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = subtitleTrack.fullName;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = subtitleTrack.addonName;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            z = subtitleTrack.fromStream;
        }
        return subtitleTrack.copy(str, str6, str7, str8, str9, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLanguageCode() {
        return this.languageCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFullName() {
        return this.fullName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getFromStream() {
        return this.fromStream;
    }

    public final SubtitleTrack copy(String languageCode, String label, String url, String fullName, String addonName, boolean fromStream) {
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(url, "url");
        return new SubtitleTrack(languageCode, label, url, fullName, addonName, fromStream);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleTrack)) {
            return false;
        }
        SubtitleTrack subtitleTrack = (SubtitleTrack) other;
        return Intrinsics.areEqual(this.languageCode, subtitleTrack.languageCode) && Intrinsics.areEqual(this.label, subtitleTrack.label) && Intrinsics.areEqual(this.url, subtitleTrack.url) && Intrinsics.areEqual(this.fullName, subtitleTrack.fullName) && Intrinsics.areEqual(this.addonName, subtitleTrack.addonName) && this.fromStream == subtitleTrack.fromStream;
    }

    public int hashCode() {
        return (((((((((this.languageCode.hashCode() * 31) + this.label.hashCode()) * 31) + this.url.hashCode()) * 31) + (this.fullName == null ? 0 : this.fullName.hashCode())) * 31) + (this.addonName != null ? this.addonName.hashCode() : 0)) * 31) + Boolean.hashCode(this.fromStream);
    }

    public String toString() {
        return "SubtitleTrack(languageCode=" + this.languageCode + ", label=" + this.label + ", url=" + this.url + ", fullName=" + this.fullName + ", addonName=" + this.addonName + ", fromStream=" + this.fromStream + ")";
    }

    public SubtitleTrack(String languageCode, String label, String url, String fullName, String addonName, boolean fromStream) {
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(url, "url");
        this.languageCode = languageCode;
        this.label = label;
        this.url = url;
        this.fullName = fullName;
        this.addonName = addonName;
        this.fromStream = fromStream;
    }

    public /* synthetic */ SubtitleTrack(String str, String str2, String str3, String str4, String str5, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? false : z);
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final boolean getFromStream() {
        return this.fromStream;
    }

    public final String getShortLabel() {
        return this.label;
    }
}
