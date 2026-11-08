package com.streamsync.app.settings;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeLayoutStore.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0001!BI\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003JM\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/streamsync/app/settings/HomeLayoutSettings;", "", "order", "", "", "hidden", "", "featuredKeys", "featuredPosition", "Lcom/streamsync/app/settings/FeaturedPosition;", "showContinueWatching", "", "(Ljava/util/List;Ljava/util/Set;Ljava/util/List;Lcom/streamsync/app/settings/FeaturedPosition;Z)V", "getFeaturedKeys", "()Ljava/util/List;", "getFeaturedPosition", "()Lcom/streamsync/app/settings/FeaturedPosition;", "getHidden", "()Ljava/util/Set;", "getOrder", "getShowContinueWatching", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final /* data */ class HomeLayoutSettings {
    public static final int MAX_FEATURED = 2;
    private final List<String> featuredKeys;
    private final FeaturedPosition featuredPosition;
    private final Set<String> hidden;
    private final List<String> order;
    private final boolean showContinueWatching;
    public static final int $stable = 8;

    public HomeLayoutSettings() {
        this(null, null, null, null, false, 31, null);
    }

    public static /* synthetic */ HomeLayoutSettings copy$default(HomeLayoutSettings homeLayoutSettings, List list, Set set, List list2, FeaturedPosition featuredPosition, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = homeLayoutSettings.order;
        }
        if ((i & 2) != 0) {
            set = homeLayoutSettings.hidden;
        }
        Set set2 = set;
        if ((i & 4) != 0) {
            list2 = homeLayoutSettings.featuredKeys;
        }
        List list3 = list2;
        if ((i & 8) != 0) {
            featuredPosition = homeLayoutSettings.featuredPosition;
        }
        FeaturedPosition featuredPosition2 = featuredPosition;
        if ((i & 16) != 0) {
            z = homeLayoutSettings.showContinueWatching;
        }
        return homeLayoutSettings.copy(list, set2, list3, featuredPosition2, z);
    }

    public final List<String> component1() {
        return this.order;
    }

    public final Set<String> component2() {
        return this.hidden;
    }

    public final List<String> component3() {
        return this.featuredKeys;
    }

    /* renamed from: component4, reason: from getter */
    public final FeaturedPosition getFeaturedPosition() {
        return this.featuredPosition;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getShowContinueWatching() {
        return this.showContinueWatching;
    }

    public final HomeLayoutSettings copy(List<String> order, Set<String> hidden, List<String> featuredKeys, FeaturedPosition featuredPosition, boolean showContinueWatching) {
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(hidden, "hidden");
        Intrinsics.checkNotNullParameter(featuredKeys, "featuredKeys");
        Intrinsics.checkNotNullParameter(featuredPosition, "featuredPosition");
        return new HomeLayoutSettings(order, hidden, featuredKeys, featuredPosition, showContinueWatching);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeLayoutSettings)) {
            return false;
        }
        HomeLayoutSettings homeLayoutSettings = (HomeLayoutSettings) other;
        return Intrinsics.areEqual(this.order, homeLayoutSettings.order) && Intrinsics.areEqual(this.hidden, homeLayoutSettings.hidden) && Intrinsics.areEqual(this.featuredKeys, homeLayoutSettings.featuredKeys) && this.featuredPosition == homeLayoutSettings.featuredPosition && this.showContinueWatching == homeLayoutSettings.showContinueWatching;
    }

    public int hashCode() {
        return (((((((this.order.hashCode() * 31) + this.hidden.hashCode()) * 31) + this.featuredKeys.hashCode()) * 31) + this.featuredPosition.hashCode()) * 31) + Boolean.hashCode(this.showContinueWatching);
    }

    public String toString() {
        return "HomeLayoutSettings(order=" + this.order + ", hidden=" + this.hidden + ", featuredKeys=" + this.featuredKeys + ", featuredPosition=" + this.featuredPosition + ", showContinueWatching=" + this.showContinueWatching + ")";
    }

    public HomeLayoutSettings(List<String> order, Set<String> hidden, List<String> featuredKeys, FeaturedPosition featuredPosition, boolean showContinueWatching) {
        Intrinsics.checkNotNullParameter(order, "order");
        Intrinsics.checkNotNullParameter(hidden, "hidden");
        Intrinsics.checkNotNullParameter(featuredKeys, "featuredKeys");
        Intrinsics.checkNotNullParameter(featuredPosition, "featuredPosition");
        this.order = order;
        this.hidden = hidden;
        this.featuredKeys = featuredKeys;
        this.featuredPosition = featuredPosition;
        this.showContinueWatching = showContinueWatching;
    }

    public /* synthetic */ HomeLayoutSettings(List list, Set set, List list2, FeaturedPosition featuredPosition, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? SetsKt.emptySet() : set, (i & 4) != 0 ? CollectionsKt.emptyList() : list2, (i & 8) != 0 ? FeaturedPosition.TOP : featuredPosition, (i & 16) != 0 ? true : z);
    }

    public final List<String> getOrder() {
        return this.order;
    }

    public final Set<String> getHidden() {
        return this.hidden;
    }

    public final List<String> getFeaturedKeys() {
        return this.featuredKeys;
    }

    public final FeaturedPosition getFeaturedPosition() {
        return this.featuredPosition;
    }

    public final boolean getShowContinueWatching() {
        return this.showContinueWatching;
    }
}
