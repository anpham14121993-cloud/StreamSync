package com.streamsync.app.data.addon;

import android.content.Context;
import android.content.SharedPreferences;
import com.streamsync.app.settings.BufferCeiling;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* compiled from: ImdbIdResolver.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\rJ0\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b0\u00100\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002J/\u0010\u0015\u001a\u0004\u0018\u00010\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000f2\u0006\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\n\u001a\u00020\bH\u0002JD\u0010\u001a\u001a\u0004\u0018\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/streamsync/app/data/addon/ImdbIdResolver;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "cacheKey", "", "type", "title", "year", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "metaSearchCatalogs", "", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonManifest;", "addons", "normalize", "value", "pick", "metas", "Lcom/streamsync/app/data/addon/AddonMeta;", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "queryVariants", "resolve", "id", "releaseInfo", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yearOf", "(Ljava/lang/String;)Ljava/lang/Integer;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class ImdbIdResolver {

    @Deprecated
    public static final int SEARCH_PARALLEL = 4;
    private final SharedPreferences prefs;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Regex YEAR_RE = new Regex("\\b((?:19|20)\\d{2})\\b");
    private static final Regex WHITESPACE_RE = new Regex("\\s+");
    private static final Regex BracketNoiseRegex = new Regex("[\\[\\(](?:[^\\[\\]()]*\\bhd\\b[^\\[\\]()]*|[^\\[\\]()]*pđ[^\\[\\]()]*|[^\\[\\]()]*blu-?ray[^\\[\\()]*)|[\\(\\[]\\s*[\\)\\]]", RegexOption.IGNORE_CASE);

    public ImdbIdResolver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("imdb_id_cache", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.prefs = sharedPreferences;
    }

    public static /* synthetic */ Object resolve$default(ImdbIdResolver imdbIdResolver, List list, String str, String str2, String str3, String str4, Continuation continuation, int i, Object obj) {
        String str5;
        if ((i & 16) == 0) {
            str5 = str4;
        } else {
            str5 = null;
        }
        return imdbIdResolver.resolve(list, str, str2, str3, str5, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolve(java.util.List<com.streamsync.app.data.addon.AddonManifest> r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, kotlin.coroutines.Continuation<? super java.lang.String> r32) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.ImdbIdResolver.resolve(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List<Pair<AddonManifest, String>> metaSearchCatalogs(List<AddonManifest> addons, final String type) {
        return SequencesKt.toList(SequencesKt.flatMapIterable(SequencesKt.filter(SequencesKt.filter(CollectionsKt.asSequence(addons), new Function1<AddonManifest, Boolean>() { // from class: com.streamsync.app.data.addon.ImdbIdResolver$metaSearchCatalogs$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AddonManifest it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(AddonModelsKt.provides(it, "meta") || AddonModelsKt.provides(it, "catalog"));
            }
        }), new Function1<AddonManifest, Boolean>() { // from class: com.streamsync.app.data.addon.ImdbIdResolver$metaSearchCatalogs$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(AddonManifest it) {
                Intrinsics.checkNotNullParameter(it, "it");
                List<String> types = it.getTypes();
                if (types == null) {
                    types = CollectionsKt.emptyList();
                }
                return Boolean.valueOf(types.contains(type));
            }
        }), new Function1<AddonManifest, List<? extends Pair<? extends AddonManifest, ? extends String>>>() { // from class: com.streamsync.app.data.addon.ImdbIdResolver$metaSearchCatalogs$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<Pair<AddonManifest, String>> invoke(AddonManifest addon) {
                Iterable $this$filter$iv;
                boolean z;
                Intrinsics.checkNotNullParameter(addon, "addon");
                Iterable $this$filter$iv2 = addon.getCatalogs();
                if ($this$filter$iv2 == null) {
                    $this$filter$iv2 = CollectionsKt.emptyList();
                }
                String str = type;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv2) {
                    AddonCatalogDef def = (AddonCatalogDef) element$iv$iv;
                    if (Intrinsics.areEqual(def.getType(), str)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$filter$iv3 = (List) destination$iv$iv;
                Collection destination$iv$iv2 = new ArrayList();
                for (Object element$iv$iv2 : $this$filter$iv3) {
                    AddonCatalogDef def2 = (AddonCatalogDef) element$iv$iv2;
                    Iterable $this$any$iv = def2.getExtra();
                    if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                        Iterator it = $this$any$iv.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                Object element$iv = it.next();
                                AddonExtraDef it2 = (AddonExtraDef) element$iv;
                                $this$filter$iv = $this$filter$iv3;
                                if (Intrinsics.areEqual(it2.getName(), "search")) {
                                    z = true;
                                    break;
                                }
                                $this$filter$iv3 = $this$filter$iv;
                            } else {
                                $this$filter$iv = $this$filter$iv3;
                                z = false;
                                break;
                            }
                        }
                    } else {
                        $this$filter$iv = $this$filter$iv3;
                        z = false;
                    }
                    if (z) {
                        destination$iv$iv2.add(element$iv$iv2);
                    }
                    $this$filter$iv3 = $this$filter$iv;
                }
                Iterable $this$map$iv = (List) destination$iv$iv2;
                Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    AddonCatalogDef def3 = (AddonCatalogDef) item$iv$iv;
                    String id = def3.getId();
                    if (id == null) {
                        id = "";
                    }
                    destination$iv$iv3.add(TuplesKt.to(addon, id));
                }
                return (List) destination$iv$iv3;
            }
        }));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ce A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<java.lang.String> queryVariants(java.lang.String r31) {
        /*
            Method dump skipped, instructions count: 895
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.ImdbIdResolver.queryVariants(java.lang.String):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[LOOP:3: B:66:0x011e->B:88:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String pick(java.util.List<com.streamsync.app.data.addon.AddonMeta> r18, java.lang.String r19, java.lang.Integer r20) {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.ImdbIdResolver.pick(java.util.List, java.lang.String, java.lang.Integer):java.lang.String");
    }

    private final Integer yearOf(String value) {
        List<String> groupValues;
        if (value != null) {
            MatchResult find$default = Regex.find$default(YEAR_RE, value, 0, 2, null);
            String str = (find$default == null || (groupValues = find$default.getGroupValues()) == null) ? null : groupValues.get(0);
            if (str != null) {
                return StringsKt.toIntOrNull(str);
            }
            return null;
        }
        return null;
    }

    private final String cacheKey(String type, String title, Integer year) {
        return type + "|" + normalize(title) + "|" + (year == null ? "" : year);
    }

    private final String normalize(String value) {
        String normalize = Normalizer.normalize(value, Normalizer.Form.NFD);
        Intrinsics.checkNotNullExpressionValue(normalize, "normalize(...)");
        String noDiacritics = new Regex("\\p{InCombiningDiacriticalMarks}+").replace(normalize, "");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = noDiacritics.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("[^a-z0-9]+").replace(lowerCase, " ")).toString();
    }

    /* compiled from: ImdbIdResolver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/streamsync/app/data/addon/ImdbIdResolver$Companion;", "", "()V", "BracketNoiseRegex", "Lkotlin/text/Regex;", "getBracketNoiseRegex", "()Lkotlin/text/Regex;", "SEARCH_PARALLEL", "", "WHITESPACE_RE", "getWHITESPACE_RE", "YEAR_RE", "getYEAR_RE", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Regex getYEAR_RE() {
            return ImdbIdResolver.YEAR_RE;
        }

        public final Regex getWHITESPACE_RE() {
            return ImdbIdResolver.WHITESPACE_RE;
        }

        public final Regex getBracketNoiseRegex() {
            return ImdbIdResolver.BracketNoiseRegex;
        }
    }
}
