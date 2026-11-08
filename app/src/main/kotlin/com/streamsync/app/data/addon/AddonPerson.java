package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/streamsync/app/data/addon/AddonPerson;", "", HintConstants.AUTOFILL_HINT_NAME, "", "character", "photo", "tmdbId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getCharacter", "()Ljava/lang/String;", "getName", "getPhoto", "getTmdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/streamsync/app/data/addon/AddonPerson;", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonPerson {
    public static final int $stable = 0;
    private final String character;
    private final String name;
    private final String photo;
    private final Integer tmdbId;

    public AddonPerson() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ AddonPerson copy$default(AddonPerson addonPerson, String str, String str2, String str3, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addonPerson.name;
        }
        if ((i & 2) != 0) {
            str2 = addonPerson.character;
        }
        if ((i & 4) != 0) {
            str3 = addonPerson.photo;
        }
        if ((i & 8) != 0) {
            num = addonPerson.tmdbId;
        }
        return addonPerson.copy(str, str2, str3, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCharacter() {
        return this.character;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPhoto() {
        return this.photo;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getTmdbId() {
        return this.tmdbId;
    }

    public final AddonPerson copy(String name, String character, String photo, Integer tmdbId) {
        return new AddonPerson(name, character, photo, tmdbId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonPerson)) {
            return false;
        }
        AddonPerson addonPerson = (AddonPerson) other;
        return Intrinsics.areEqual(this.name, addonPerson.name) && Intrinsics.areEqual(this.character, addonPerson.character) && Intrinsics.areEqual(this.photo, addonPerson.photo) && Intrinsics.areEqual(this.tmdbId, addonPerson.tmdbId);
    }

    public int hashCode() {
        return ((((((this.name == null ? 0 : this.name.hashCode()) * 31) + (this.character == null ? 0 : this.character.hashCode())) * 31) + (this.photo == null ? 0 : this.photo.hashCode())) * 31) + (this.tmdbId != null ? this.tmdbId.hashCode() : 0);
    }

    public String toString() {
        return "AddonPerson(name=" + this.name + ", character=" + this.character + ", photo=" + this.photo + ", tmdbId=" + this.tmdbId + ")";
    }

    public AddonPerson(String name, String character, String photo, Integer tmdbId) {
        this.name = name;
        this.character = character;
        this.photo = photo;
        this.tmdbId = tmdbId;
    }

    public /* synthetic */ AddonPerson(String str, String str2, String str3, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num);
    }

    public final String getName() {
        return this.name;
    }

    public final String getCharacter() {
        return this.character;
    }

    public final String getPhoto() {
        return this.photo;
    }

    public final Integer getTmdbId() {
        return this.tmdbId;
    }
}
