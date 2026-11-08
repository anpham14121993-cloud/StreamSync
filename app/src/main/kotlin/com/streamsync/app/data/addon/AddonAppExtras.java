package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003JK\u0010\u0014\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\bHÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/streamsync/app/data/addon/AddonAppExtras;", "", "cast", "", "Lcom/streamsync/app/data/addon/AddonPerson;", "directors", "writers", "certification", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getCast", "()Ljava/util/List;", "getCertification", "()Ljava/lang/String;", "getDirectors", "getWriters", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class AddonAppExtras {
    public static final int $stable = 8;
    private final List<AddonPerson> cast;
    private final String certification;
    private final List<AddonPerson> directors;
    private final List<AddonPerson> writers;

    public AddonAppExtras() {
        this(null, null, null, null, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonAppExtras copy$default(AddonAppExtras addonAppExtras, List list, List list2, List list3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = addonAppExtras.cast;
        }
        if ((i & 2) != 0) {
            list2 = addonAppExtras.directors;
        }
        if ((i & 4) != 0) {
            list3 = addonAppExtras.writers;
        }
        if ((i & 8) != 0) {
            str = addonAppExtras.certification;
        }
        return addonAppExtras.copy(list, list2, list3, str);
    }

    public final List<AddonPerson> component1() {
        return this.cast;
    }

    public final List<AddonPerson> component2() {
        return this.directors;
    }

    public final List<AddonPerson> component3() {
        return this.writers;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCertification() {
        return this.certification;
    }

    public final AddonAppExtras copy(List<AddonPerson> cast, List<AddonPerson> directors, List<AddonPerson> writers, String certification) {
        return new AddonAppExtras(cast, directors, writers, certification);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonAppExtras)) {
            return false;
        }
        AddonAppExtras addonAppExtras = (AddonAppExtras) other;
        return Intrinsics.areEqual(this.cast, addonAppExtras.cast) && Intrinsics.areEqual(this.directors, addonAppExtras.directors) && Intrinsics.areEqual(this.writers, addonAppExtras.writers) && Intrinsics.areEqual(this.certification, addonAppExtras.certification);
    }

    public int hashCode() {
        return ((((((this.cast == null ? 0 : this.cast.hashCode()) * 31) + (this.directors == null ? 0 : this.directors.hashCode())) * 31) + (this.writers == null ? 0 : this.writers.hashCode())) * 31) + (this.certification != null ? this.certification.hashCode() : 0);
    }

    public String toString() {
        return "AddonAppExtras(cast=" + this.cast + ", directors=" + this.directors + ", writers=" + this.writers + ", certification=" + this.certification + ")";
    }

    public AddonAppExtras(List<AddonPerson> list, List<AddonPerson> list2, List<AddonPerson> list3, String certification) {
        this.cast = list;
        this.directors = list2;
        this.writers = list3;
        this.certification = certification;
    }

    public /* synthetic */ AddonAppExtras(List list, List list2, List list3, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2, (i & 4) != 0 ? null : list3, (i & 8) != 0 ? null : str);
    }

    public final List<AddonPerson> getCast() {
        return this.cast;
    }

    public final List<AddonPerson> getDirectors() {
        return this.directors;
    }

    public final List<AddonPerson> getWriters() {
        return this.writers;
    }

    public final String getCertification() {
        return this.certification;
    }
}
