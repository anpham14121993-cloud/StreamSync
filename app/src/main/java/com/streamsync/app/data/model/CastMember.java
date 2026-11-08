package com.streamsync.app.data.model;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/data/model/CastMember;", "", HintConstants.AUTOFILL_HINT_NAME, "", "role", "photoUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPhotoUrl", "getRole", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class CastMember {
    public static final int $stable = 0;
    private final String name;
    private final String photoUrl;
    private final String role;

    public static /* synthetic */ CastMember copy$default(CastMember castMember, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = castMember.name;
        }
        if ((i & 2) != 0) {
            str2 = castMember.role;
        }
        if ((i & 4) != 0) {
            str3 = castMember.photoUrl;
        }
        return castMember.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPhotoUrl() {
        return this.photoUrl;
    }

    public final CastMember copy(String name, String role, String photoUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new CastMember(name, role, photoUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CastMember)) {
            return false;
        }
        CastMember castMember = (CastMember) other;
        return Intrinsics.areEqual(this.name, castMember.name) && Intrinsics.areEqual(this.role, castMember.role) && Intrinsics.areEqual(this.photoUrl, castMember.photoUrl);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + (this.role == null ? 0 : this.role.hashCode())) * 31) + (this.photoUrl != null ? this.photoUrl.hashCode() : 0);
    }

    public String toString() {
        return "CastMember(name=" + this.name + ", role=" + this.role + ", photoUrl=" + this.photoUrl + ")";
    }

    public CastMember(String name, String role, String photoUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.role = role;
        this.photoUrl = photoUrl;
    }

    public /* synthetic */ CastMember(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getName() {
        return this.name;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getPhotoUrl() {
        return this.photoUrl;
    }
}
