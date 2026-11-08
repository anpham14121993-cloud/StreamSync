package com.streamsync.app.data.remote;

import androidx.autofill.HintConstants;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.streamsync.app.data.remote.EliteBadges;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.OkHttpClient;

/* compiled from: EliteBadges.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u0004\u001d\u001e\u001f B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/streamsync/app/data/remote/EliteBadges;", "", "()V", "cachedUrl", "", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "client$delegate", "Lkotlin/Lazy;", "compiled", "", "Lcom/streamsync/app/data/remote/EliteBadges$CompiledFilter;", "groupNames", "", "gson", "Lcom/google/gson/Gson;", "lock", "Lkotlinx/coroutines/sync/Mutex;", "filterCount", "", "load", "", "url", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "match", "Lcom/streamsync/app/data/remote/Badge;", "streamName", "BadgeFilter", "BadgeGroup", "CompiledFilter", "Config", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class EliteBadges {
    private static volatile String cachedUrl;
    public static final EliteBadges INSTANCE = new EliteBadges();
    private static final Gson gson = new Gson();
    private static final Mutex lock = MutexKt.Mutex$default(false, 1, null);
    private static volatile List<CompiledFilter> compiled = CollectionsKt.emptyList();
    private static volatile Map<String, String> groupNames = MapsKt.emptyMap();

    /* renamed from: client$delegate, reason: from kotlin metadata */
    private static final Lazy client = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.streamsync.app.data.remote.EliteBadges$client$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            return new OkHttpClient.Builder().connectTimeout(8L, TimeUnit.SECONDS).readTimeout(12L, TimeUnit.SECONDS).callTimeout(15L, TimeUnit.SECONDS).build();
        }
    });
    public static final int $stable = 8;

    private EliteBadges() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EliteBadges.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\r\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/data/remote/EliteBadges$Config;", "", "filters", "", "Lcom/streamsync/app/data/remote/EliteBadges$BadgeFilter;", "groups", "Lcom/streamsync/app/data/remote/EliteBadges$BadgeGroup;", "(Ljava/util/List;Ljava/util/List;)V", "getFilters", "()Ljava/util/List;", "getGroups", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final /* data */ class Config {

        @SerializedName("filters")
        private final List<BadgeFilter> filters;

        @SerializedName("groups")
        private final List<BadgeGroup> groups;

        /* JADX WARN: Multi-variable type inference failed */
        public Config() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Config copy$default(Config config, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = config.filters;
            }
            if ((i & 2) != 0) {
                list2 = config.groups;
            }
            return config.copy(list, list2);
        }

        public final List<BadgeFilter> component1() {
            return this.filters;
        }

        public final List<BadgeGroup> component2() {
            return this.groups;
        }

        public final Config copy(List<BadgeFilter> filters, List<BadgeGroup> groups) {
            return new Config(filters, groups);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.filters, config.filters) && Intrinsics.areEqual(this.groups, config.groups);
        }

        public int hashCode() {
            return ((this.filters == null ? 0 : this.filters.hashCode()) * 31) + (this.groups != null ? this.groups.hashCode() : 0);
        }

        public String toString() {
            return "Config(filters=" + this.filters + ", groups=" + this.groups + ")";
        }

        public Config(List<BadgeFilter> list, List<BadgeGroup> list2) {
            this.filters = list;
            this.groups = list2;
        }

        public /* synthetic */ Config(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
        }

        public final List<BadgeFilter> getFilters() {
            return this.filters;
        }

        public final List<BadgeGroup> getGroups() {
            return this.groups;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EliteBadges.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u000fJV\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\b\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/streamsync/app/data/remote/EliteBadges$BadgeFilter;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "groupId", "pattern", "imageUrl", "isEnabled", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getGroupId", "()Ljava/lang/String;", "getId", "getImageUrl", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "getPattern", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/streamsync/app/data/remote/EliteBadges$BadgeFilter;", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final /* data */ class BadgeFilter {

        @SerializedName("groupId")
        private final String groupId;

        @SerializedName("id")
        private final String id;

        @SerializedName("imageURL")
        private final String imageUrl;

        @SerializedName("isEnabled")
        private final Boolean isEnabled;

        @SerializedName(HintConstants.AUTOFILL_HINT_NAME)
        private final String name;

        @SerializedName("pattern")
        private final String pattern;

        public BadgeFilter() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ BadgeFilter copy$default(BadgeFilter badgeFilter, String str, String str2, String str3, String str4, String str5, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                str = badgeFilter.id;
            }
            if ((i & 2) != 0) {
                str2 = badgeFilter.name;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = badgeFilter.groupId;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = badgeFilter.pattern;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = badgeFilter.imageUrl;
            }
            String str9 = str5;
            if ((i & 32) != 0) {
                bool = badgeFilter.isEnabled;
            }
            return badgeFilter.copy(str, str6, str7, str8, str9, bool);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component3, reason: from getter */
        public final String getGroupId() {
            return this.groupId;
        }

        /* renamed from: component4, reason: from getter */
        public final String getPattern() {
            return this.pattern;
        }

        /* renamed from: component5, reason: from getter */
        public final String getImageUrl() {
            return this.imageUrl;
        }

        /* renamed from: component6, reason: from getter */
        public final Boolean getIsEnabled() {
            return this.isEnabled;
        }

        public final BadgeFilter copy(String id, String name, String groupId, String pattern, String imageUrl, Boolean isEnabled) {
            return new BadgeFilter(id, name, groupId, pattern, imageUrl, isEnabled);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BadgeFilter)) {
                return false;
            }
            BadgeFilter badgeFilter = (BadgeFilter) other;
            return Intrinsics.areEqual(this.id, badgeFilter.id) && Intrinsics.areEqual(this.name, badgeFilter.name) && Intrinsics.areEqual(this.groupId, badgeFilter.groupId) && Intrinsics.areEqual(this.pattern, badgeFilter.pattern) && Intrinsics.areEqual(this.imageUrl, badgeFilter.imageUrl) && Intrinsics.areEqual(this.isEnabled, badgeFilter.isEnabled);
        }

        public int hashCode() {
            return ((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.groupId == null ? 0 : this.groupId.hashCode())) * 31) + (this.pattern == null ? 0 : this.pattern.hashCode())) * 31) + (this.imageUrl == null ? 0 : this.imageUrl.hashCode())) * 31) + (this.isEnabled != null ? this.isEnabled.hashCode() : 0);
        }

        public String toString() {
            return "BadgeFilter(id=" + this.id + ", name=" + this.name + ", groupId=" + this.groupId + ", pattern=" + this.pattern + ", imageUrl=" + this.imageUrl + ", isEnabled=" + this.isEnabled + ")";
        }

        public BadgeFilter(String id, String name, String groupId, String pattern, String imageUrl, Boolean isEnabled) {
            this.id = id;
            this.name = name;
            this.groupId = groupId;
            this.pattern = pattern;
            this.imageUrl = imageUrl;
            this.isEnabled = isEnabled;
        }

        public /* synthetic */ BadgeFilter(String str, String str2, String str3, String str4, String str5, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : bool);
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getGroupId() {
            return this.groupId;
        }

        public final String getPattern() {
            return this.pattern;
        }

        public final String getImageUrl() {
            return this.imageUrl;
        }

        public final Boolean isEnabled() {
            return this.isEnabled;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EliteBadges.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/streamsync/app/data/remote/EliteBadges$BadgeGroup;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final /* data */ class BadgeGroup {

        @SerializedName("id")
        private final String id;

        @SerializedName(HintConstants.AUTOFILL_HINT_NAME)
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public BadgeGroup() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ BadgeGroup copy$default(BadgeGroup badgeGroup, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = badgeGroup.id;
            }
            if ((i & 2) != 0) {
                str2 = badgeGroup.name;
            }
            return badgeGroup.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final BadgeGroup copy(String id, String name) {
            return new BadgeGroup(id, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BadgeGroup)) {
                return false;
            }
            BadgeGroup badgeGroup = (BadgeGroup) other;
            return Intrinsics.areEqual(this.id, badgeGroup.id) && Intrinsics.areEqual(this.name, badgeGroup.name);
        }

        public int hashCode() {
            return ((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name != null ? this.name.hashCode() : 0);
        }

        public String toString() {
            return "BadgeGroup(id=" + this.id + ", name=" + this.name + ")";
        }

        public BadgeGroup(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public /* synthetic */ BadgeGroup(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EliteBadges.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/remote/EliteBadges$CompiledFilter;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "groupId", "imageUrl", "regex", "Lkotlin/text/Regex;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/text/Regex;)V", "getGroupId", "()Ljava/lang/String;", "getId", "getImageUrl", "getName", "getRegex", "()Lkotlin/text/Regex;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final class CompiledFilter {
        private final String groupId;
        private final String id;
        private final String imageUrl;
        private final String name;
        private final Regex regex;

        public CompiledFilter(String id, String name, String groupId, String imageUrl, Regex regex) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(groupId, "groupId");
            Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
            Intrinsics.checkNotNullParameter(regex, "regex");
            this.id = id;
            this.name = name;
            this.groupId = groupId;
            this.imageUrl = imageUrl;
            this.regex = regex;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getGroupId() {
            return this.groupId;
        }

        public final String getImageUrl() {
            return this.imageUrl;
        }

        public final Regex getRegex() {
            return this.regex;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getClient() {
        return (OkHttpClient) client.getValue();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00d6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0107 A[Catch: all -> 0x00e2, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x00e2, blocks: (B:16:0x00df, B:18:0x0107, B:24:0x012d, B:27:0x0134, B:29:0x0138, B:32:0x0140, B:34:0x0146, B:35:0x014a, B:36:0x015c, B:38:0x0162, B:42:0x018d, B:46:0x0178, B:49:0x0185, B:52:0x0193, B:54:0x01a7, B:55:0x01ab, B:56:0x01bd, B:58:0x01c3, B:62:0x0273, B:66:0x01e0, B:68:0x01e6, B:75:0x01fe, B:77:0x0204, B:84:0x0221, B:87:0x0229, B:92:0x0249, B:95:0x0250, B:98:0x0258, B:101:0x0264, B:104:0x023f, B:112:0x027b, B:115:0x0292, B:121:0x0123, B:23:0x0110, B:91:0x0231), top: B:15:0x00df, inners: #2, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object load(java.lang.String r30, kotlin.coroutines.Continuation<? super java.lang.Boolean> r31) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.remote.EliteBadges.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<Badge> match(String streamName) {
        List filters = compiled;
        if (!filters.isEmpty()) {
            String str = streamName;
            if (!(str == null || StringsKt.isBlank(str))) {
                List $this$filter$iv = filters;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    if (((CompiledFilter) element$iv$iv).getRegex().containsMatchIn(streamName)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                List hit = (List) destination$iv$iv;
                if (hit.isEmpty()) {
                    return CollectionsKt.emptyList();
                }
                final List order = CollectionsKt.toList(groupNames.keySet());
                List $this$sortedBy$iv = hit;
                Iterable $this$map$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.data.remote.EliteBadges$match$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        EliteBadges.CompiledFilter it = (EliteBadges.CompiledFilter) t;
                        int i = order.indexOf(it.getGroupId());
                        if (i < 0) {
                            i = Integer.MAX_VALUE;
                        }
                        Integer valueOf = Integer.valueOf(i);
                        EliteBadges.CompiledFilter it2 = (EliteBadges.CompiledFilter) t2;
                        int i2 = order.indexOf(it2.getGroupId());
                        return ComparisonsKt.compareValues(valueOf, Integer.valueOf(i2 >= 0 ? i2 : Integer.MAX_VALUE));
                    }
                });
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    CompiledFilter it = (CompiledFilter) item$iv$iv;
                    destination$iv$iv2.add(new Badge(it.getId(), it.getName(), groupNames.get(it.getGroupId()), it.getImageUrl()));
                    filters = filters;
                }
                return (List) destination$iv$iv2;
            }
        }
        return CollectionsKt.emptyList();
    }

    public final int filterCount() {
        return compiled.size();
    }
}
