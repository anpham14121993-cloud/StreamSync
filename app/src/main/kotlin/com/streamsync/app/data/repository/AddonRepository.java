package com.streamsync.app.data.repository;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.streamsync.app.data.addon.AddonCatalogDef;
import com.streamsync.app.data.addon.AddonDao;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.data.addon.AddonResourceDef;
import com.streamsync.app.data.addon.InstalledAddonEntity;
import com.streamsync.app.data.local.AppDatabase;
import com.streamsync.app.data.model.CatalogDisplayNameKt;
import com.streamsync.app.data.model.CatalogEntry;
import com.streamsync.app.data.model.CatalogKey;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.MediaType;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u008d\u00012\u00020\u0001:\u0002\u008d\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JB\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000fH\u0002JE\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00172\u0018\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u001bJ<\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0#H\u0002J\u0010\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020&H\u0002J(\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020(2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010+J,\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f03H\u0086@¢\u0006\u0002\u00104J\u001e\u00105\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u00106\u001a\u000207H\u0082@¢\u0006\u0002\u00108J(\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010;J:\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010:0=2\u0006\u0010\u001e\u001a\u00020\u000e2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010?J\"\u0010@\u001a\u0004\u0018\u00010(2\u0006\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010*\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010AJ \u0010B\u001a\u0004\u0018\u00010(2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010AJ\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\fH\u0086@¢\u0006\u0002\u0010EJ8\u0010F\u001a\b\u0012\u0004\u0012\u00020/0\f2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u000f2\u0016\b\u0002\u0010G\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u001d\u0018\u00010HH\u0086@¢\u0006\u0002\u0010IJ$\u0010J\u001a\b\u0012\u0004\u0012\u00020\u000e0K2\u0006\u0010L\u001a\u00020\u000fH\u0086@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010NJ\u0010\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000fH\u0002J(\u0010R\u001a\u00020\u001d2\u0006\u0010S\u001a\u00020\u000f2\u0006\u0010T\u001a\u00020U2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020\u000fH\u0002J\u0012\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020D0\f0XJ\u0012\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\f0XJ\u0016\u0010Z\u001a\u00020\u00152\u0006\u0010[\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\\J\u001c\u0010]\u001a\u00020\u001d2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u0086@¢\u0006\u0002\u0010_J6\u0010`\u001a\u0004\u0018\u00010\u000f2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010a\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0002\u0010cJ\"\u0010d\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010*\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010AJt\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00150\f2\u0006\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010*\u001a\u00020\u000f2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010f\u001a\u0004\u0018\u0001012\n\b\u0002\u0010g\u001a\u0004\u0018\u0001012\u001c\b\u0002\u0010h\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\f\u0012\u0004\u0012\u00020\u001d\u0018\u00010HH\u0086@¢\u0006\u0002\u0010iJV\u0010j\u001a\b\u0012\u0004\u0012\u00020k0\f2\u0006\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010*\u001a\u00020\u000f2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010f\u001a\u0004\u0018\u0001012\n\b\u0002\u0010g\u001a\u0004\u0018\u000101H\u0086@¢\u0006\u0002\u0010lJ2\u0010m\u001a\u0004\u0018\u00010(2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000f2\b\b\u0002\u0010n\u001a\u00020PH\u0082@¢\u0006\u0002\u0010oJ&\u0010p\u001a\b\u0012\u0004\u0012\u00020(0\f2\u0006\u0010q\u001a\u00020\u000f2\b\b\u0002\u0010r\u001a\u000201H\u0086@¢\u0006\u0002\u0010sJ&\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\f2\u0006\u0010q\u001a\u00020\u000f2\b\b\u0002\u0010v\u001a\u000201H\u0086@¢\u0006\u0002\u0010sJ\u0016\u0010w\u001a\u00020\u001d2\u0006\u0010x\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010yJ\f\u0010z\u001a\u00020P*\u00020(H\u0002J\f\u0010{\u001a\u00020|*\u00020\u000eH\u0002J \u0010}\u001a\b\u0012\u0004\u0012\u00020D0\f*\b\u0012\u0004\u0012\u0002070\f2\u0006\u0010x\u001a\u00020\u000eH\u0002J\u0014\u0010~\u001a\u00020D*\u0002072\u0006\u0010x\u001a\u00020\u000eH\u0002J\f\u0010\u007f\u001a\u00020\u000e*\u00020|H\u0002J@\u0010\u0080\u0001\u001a\u00020(*\u00020:2\u0006\u0010\u001e\u001a\u00020\u000e2\u0007\u0010\u0081\u0001\u001a\u0002072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\t\b\u0002\u0010\u0082\u0001\u001a\u00020P2\t\b\u0002\u0010\u0083\u0001\u001a\u00020PH\u0002J\u001d\u0010\u0084\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010\f*\u000b\u0012\u0005\u0012\u00030\u0086\u0001\u0018\u00010\fH\u0002J\u0014\u0010\u0087\u0001\u001a\u00020(*\u00020(H\u0082@¢\u0006\u0003\u0010\u0088\u0001J2\u0010\u0089\u0001\u001a\u00020:*\u00020:2\u0006\u0010\u001e\u001a\u00020\u000e2\u0007\u0010\u008a\u0001\u001a\u00020\u000f2\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010:H\u0082@¢\u0006\u0003\u0010\u008c\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u008e\u0001"}, d2 = {"Lcom/streamsync/app/data/repository/AddonRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "catalogPermit", "Lkotlinx/coroutines/sync/Semaphore;", "dao", "Lcom/streamsync/app/data/addon/AddonDao;", "gson", "Lcom/google/gson/Gson;", "buildMetaCandidates", "", "Lkotlin/Pair;", "Lcom/streamsync/app/data/addon/AddonManifest;", "", "addons", "itemId", "requestedType", "ownerAddonId", "buildSources", "Lcom/streamsync/app/data/model/StreamSource;", "answers", "", "Lcom/streamsync/app/data/addon/StreamResponse;", "targets", "compositeId", "([Lcom/streamsync/app/data/addon/StreamResponse;Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "collectStreams", "", "addon", "response", "out", "", "seenUrls", "", "describe", "e", "", "enrichFromMeta", "Lcom/streamsync/app/data/model/MediaItem;", "item", "type", "(Lcom/streamsync/app/data/model/MediaItem;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCatalogPage", "Lcom/streamsync/app/ui/catalog/CatalogPage;", "row", "Lcom/streamsync/app/data/model/CatalogRow;", "skip", "", "seenIds", "", "(Lcom/streamsync/app/data/model/CatalogRow;ILjava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCatalogRow", "def", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "(Lcom/streamsync/app/data/addon/AddonManifest;Lcom/streamsync/app/data/addon/AddonCatalogDef;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMetaCached", "Lcom/streamsync/app/data/addon/AddonMeta;", "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMetaVariants", "", "types", "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findItem", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findItemRaw", "getCatalogEntries", "Lcom/streamsync/app/data/model/CatalogEntry;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCatalogRows", "onRow", "Lkotlin/Function1;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "install", "Lkotlin/Result;", "manifestUrl", "install-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSubtitleFile", "", "url", "logTiming", "what", "t0", "", "extra", "observeCatalogEntries", "Lkotlinx/coroutines/flow/Flow;", "observeInstalled", "probeStreamMimeType", "source", "(Lcom/streamsync/app/data/model/StreamSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reorderAddons", "ordered", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveImdbId", "title", "releaseInfo", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveStream", "resolveStreams", "season", "episode", "onPartial", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveSubtitles", "Lcom/streamsync/app/data/model/SubtitleTrack;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveViaMeta", "single", "(Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", SearchIntents.EXTRA_QUERY, "limit", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchRows", "Lcom/streamsync/app/data/repository/SearchResultRow;", "maxRows", "uninstall", "manifest", "(Lcom/streamsync/app/data/addon/AddonManifest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "needsSeriesMeta", "toEntity", "Lcom/streamsync/app/data/addon/InstalledAddonEntity;", "toEntries", "toEntry", "toManifest", "toMediaItem", "catalogDef", "fromCatalog", "fromMeta", "toSeriesVideos", "Lcom/streamsync/app/data/model/SeriesVideo;", "Lcom/streamsync/app/data/addon/AddonVideo;", "withEpisodeTitle", "(Lcom/streamsync/app/data/model/MediaItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withVideosIfSeries", "defType", "prefetchedSeries", "(Lcom/streamsync/app/data/addon/AddonMeta;Lcom/streamsync/app/data/addon/AddonManifest;Ljava/lang/String;Lcom/streamsync/app/data/addon/AddonMeta;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class AddonRepository {
    private final Semaphore catalogPermit;
    private final Context context;
    private final AddonDao dao;
    private final Gson gson;
    public static final int $stable = 8;
    private static final CoroutineScope backgroundScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));

    public AddonRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.dao = AppDatabase.INSTANCE.get(this.context).addonDao();
        this.gson = new Gson();
        this.catalogPermit = SemaphoreKt.Semaphore$default(8, 0, 2, null);
    }

    public final Flow<List<AddonManifest>> observeInstalled() {
        final Flow $this$map$iv = this.dao.observeAll();
        return (Flow) new Flow<List<? extends AddonManifest>>() { // from class: com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            /* renamed from: com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes21.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ AddonRepository this$0;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2", f = "AddonRepository.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                /* renamed from: com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2$1, reason: invalid class name */
                /* loaded from: classes21.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, AddonRepository addonRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = addonRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r14, kotlin.coroutines.Continuation r15) {
                    /*
                        r13 = this;
                        boolean r0 = r15 instanceof com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r15
                        com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2$1 r0 = (com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r15 = r0.label
                        int r15 = r15 - r2
                        r0.label = r15
                        goto L19
                    L14:
                        com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2$1 r0 = new com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1$2$1
                        r0.<init>(r15)
                    L19:
                        r15 = r0
                        java.lang.Object r0 = r15.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r15.label
                        switch(r2) {
                            case 0: goto L32;
                            case 1: goto L2d;
                            default: goto L25;
                        }
                    L25:
                        java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                        java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                        r14.<init>(r15)
                        throw r14
                    L2d:
                        r14 = 0
                        kotlin.ResultKt.throwOnFailure(r0)
                        goto L7d
                    L32:
                        kotlin.ResultKt.throwOnFailure(r0)
                        r2 = r13
                        kotlinx.coroutines.flow.FlowCollector r3 = r2.$this_unsafeFlow
                        r4 = 0
                        r5 = r15
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        java.util.List r14 = (java.util.List) r14
                        r5 = 0
                        java.lang.Iterable r14 = (java.lang.Iterable) r14
                        r6 = 0
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r8 = 10
                        int r8 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r14, r8)
                        r7.<init>(r8)
                        java.util.Collection r7 = (java.util.Collection) r7
                        r8 = 0
                        java.util.Iterator r9 = r14.iterator()
                    L54:
                        boolean r14 = r9.hasNext()
                        if (r14 == 0) goto L6c
                        java.lang.Object r14 = r9.next()
                        r10 = r14
                        com.streamsync.app.data.addon.InstalledAddonEntity r10 = (com.streamsync.app.data.addon.InstalledAddonEntity) r10
                        r11 = 0
                        com.streamsync.app.data.repository.AddonRepository r12 = r2.this$0
                        com.streamsync.app.data.addon.AddonManifest r10 = com.streamsync.app.data.repository.AddonRepository.access$toManifest(r12, r10)
                        r7.add(r10)
                        goto L54
                    L6c:
                        r14 = r7
                        java.util.List r14 = (java.util.List) r14
                        r2 = 1
                        r15.label = r2
                        java.lang.Object r14 = r3.emit(r14, r15)
                        if (r14 != r1) goto L7c
                        return r1
                    L7c:
                        r14 = r4
                    L7d:
                        kotlin.Unit r14 = kotlin.Unit.INSTANCE
                        return r14
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$observeInstalled$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends AddonManifest>> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* renamed from: install-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6607installgIAlus(java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Result<com.streamsync.app.data.addon.AddonManifest>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.streamsync.app.data.repository.AddonRepository$install$1
            if (r0 == 0) goto L14
            r0 = r9
            com.streamsync.app.data.repository.AddonRepository$install$1 r0 = (com.streamsync.app.data.repository.AddonRepository$install$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            com.streamsync.app.data.repository.AddonRepository$install$1 r0 = new com.streamsync.app.data.repository.AddonRepository$install$1
            r0.<init>(r7, r9)
        L19:
            r9 = r0
            java.lang.Object r0 = r9.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r9.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L36;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            r8 = 0
            java.lang.Object r1 = r9.L$0
            com.streamsync.app.data.addon.AddonManifest r1 = (com.streamsync.app.data.addon.AddonManifest) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L74
            goto L6e
        L36:
            r8 = 0
            java.lang.Object r2 = r9.L$0
            com.streamsync.app.data.repository.AddonRepository r2 = (com.streamsync.app.data.repository.AddonRepository) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L74
            r4 = r0
            goto L58
        L40:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r7
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L74
            com.streamsync.app.data.repository.AddonRepository r2 = (com.streamsync.app.data.repository.AddonRepository) r2     // Catch: java.lang.Throwable -> L74
            r3 = 0
            com.streamsync.app.data.addon.AddonClient r4 = com.streamsync.app.data.addon.AddonClient.INSTANCE     // Catch: java.lang.Throwable -> L74
            r9.L$0 = r2     // Catch: java.lang.Throwable -> L74
            r5 = 1
            r9.label = r5     // Catch: java.lang.Throwable -> L74
            java.lang.Object r4 = r4.fetchManifest(r8, r9)     // Catch: java.lang.Throwable -> L74
            if (r4 != r1) goto L57
            return r1
        L57:
            r8 = r3
        L58:
            com.streamsync.app.data.addon.AddonManifest r4 = (com.streamsync.app.data.addon.AddonManifest) r4     // Catch: java.lang.Throwable -> L74
            r3 = r4
            com.streamsync.app.data.addon.AddonDao r4 = r2.dao     // Catch: java.lang.Throwable -> L74
            com.streamsync.app.data.addon.InstalledAddonEntity r5 = r2.toEntity(r3)     // Catch: java.lang.Throwable -> L74
            r9.L$0 = r3     // Catch: java.lang.Throwable -> L74
            r6 = 2
            r9.label = r6     // Catch: java.lang.Throwable -> L74
            java.lang.Object r4 = r4.insert(r5, r9)     // Catch: java.lang.Throwable -> L74
            if (r4 != r1) goto L6d
            return r1
        L6d:
            r1 = r3
        L6e:
            java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r1)     // Catch: java.lang.Throwable -> L74
            goto L7f
        L74:
            r8 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r8 = kotlin.ResultKt.createFailure(r8)
            java.lang.Object r8 = kotlin.Result.m6717constructorimpl(r8)
        L7f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.m6607installgIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object uninstall(AddonManifest manifest, Continuation<? super Unit> continuation) {
        Object delete = this.dao.delete(toEntity(manifest), continuation);
        return delete == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? delete : Unit.INSTANCE;
    }

    public final Flow<List<CatalogEntry>> observeCatalogEntries() {
        final Flow $this$map$iv = this.dao.observeAll();
        return (Flow) new Flow<List<? extends CatalogEntry>>() { // from class: com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            /* renamed from: com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes21.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ AddonRepository this$0;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2", f = "AddonRepository.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                /* renamed from: com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2$1, reason: invalid class name */
                /* loaded from: classes21.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, AddonRepository addonRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = addonRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r14, kotlin.coroutines.Continuation r15) {
                    /*
                        r13 = this;
                        boolean r0 = r15 instanceof com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r15
                        com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2$1 r0 = (com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r15 = r0.label
                        int r15 = r15 - r2
                        r0.label = r15
                        goto L19
                    L14:
                        com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2$1 r0 = new com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1$2$1
                        r0.<init>(r15)
                    L19:
                        r15 = r0
                        java.lang.Object r0 = r15.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r15.label
                        switch(r2) {
                            case 0: goto L32;
                            case 1: goto L2d;
                            default: goto L25;
                        }
                    L25:
                        java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                        java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                        r14.<init>(r15)
                        throw r14
                    L2d:
                        r14 = 0
                        kotlin.ResultKt.throwOnFailure(r0)
                        goto L88
                    L32:
                        kotlin.ResultKt.throwOnFailure(r0)
                        r2 = r13
                        kotlinx.coroutines.flow.FlowCollector r3 = r2.$this_unsafeFlow
                        r4 = 0
                        r5 = r15
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        java.util.List r14 = (java.util.List) r14
                        r5 = 0
                        java.lang.Iterable r14 = (java.lang.Iterable) r14
                        r6 = 0
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Collection r7 = (java.util.Collection) r7
                        r8 = 0
                        java.util.Iterator r9 = r14.iterator()
                    L4e:
                        boolean r14 = r9.hasNext()
                        if (r14 == 0) goto L77
                        java.lang.Object r14 = r9.next()
                        com.streamsync.app.data.addon.InstalledAddonEntity r14 = (com.streamsync.app.data.addon.InstalledAddonEntity) r14
                        r10 = 0
                        com.streamsync.app.data.repository.AddonRepository r11 = r2.this$0
                        com.streamsync.app.data.addon.AddonManifest r14 = com.streamsync.app.data.repository.AddonRepository.access$toManifest(r11, r14)
                        com.streamsync.app.data.repository.AddonRepository r11 = r2.this$0
                        java.util.List r12 = r14.getCatalogs()
                        if (r12 != 0) goto L6d
                        java.util.List r12 = kotlin.collections.CollectionsKt.emptyList()
                    L6d:
                        java.util.List r14 = com.streamsync.app.data.repository.AddonRepository.access$toEntries(r11, r12, r14)
                        java.lang.Iterable r14 = (java.lang.Iterable) r14
                        kotlin.collections.CollectionsKt.addAll(r7, r14)
                        goto L4e
                    L77:
                        r14 = r7
                        java.util.List r14 = (java.util.List) r14
                        r2 = 1
                        r15.label = r2
                        java.lang.Object r14 = r3.emit(r14, r15)
                        if (r14 != r1) goto L87
                        return r1
                    L87:
                        r14 = r4
                    L88:
                        kotlin.Unit r14 = kotlin.Unit.INSTANCE
                        return r14
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository$observeCatalogEntries$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends CatalogEntry>> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCatalogEntries(kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.CatalogEntry>> r18) {
        /*
            r17 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.streamsync.app.data.repository.AddonRepository$getCatalogEntries$1
            if (r1 == 0) goto L19
            r1 = r0
            com.streamsync.app.data.repository.AddonRepository$getCatalogEntries$1 r1 = (com.streamsync.app.data.repository.AddonRepository$getCatalogEntries$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L19
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r0 = r1
            r2 = r17
            goto L21
        L19:
            com.streamsync.app.data.repository.AddonRepository$getCatalogEntries$1 r1 = new com.streamsync.app.data.repository.AddonRepository$getCatalogEntries$1
            r2 = r17
            r1.<init>(r2, r0)
            r0 = r1
        L21:
            java.lang.Object r1 = r0.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r0.label
            switch(r4) {
                case 0: goto L3d;
                case 1: goto L34;
                default: goto L2c;
            }
        L2c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L34:
            java.lang.Object r3 = r0.L$0
            com.streamsync.app.data.repository.AddonRepository r3 = (com.streamsync.app.data.repository.AddonRepository) r3
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = r1
            goto L51
        L3d:
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r17
            com.streamsync.app.data.addon.AddonDao r5 = r4.dao
            r0.L$0 = r4
            r6 = 1
            r0.label = r6
            java.lang.Object r5 = r5.getAll(r0)
            if (r5 != r3) goto L50
            return r3
        L50:
            r3 = r4
        L51:
            r4 = r5
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            r5 = 0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            r7 = 0
            java.util.Iterator r8 = r4.iterator()
        L61:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto Lb5
            java.lang.Object r4 = r8.next()
            com.streamsync.app.data.addon.InstalledAddonEntity r4 = (com.streamsync.app.data.addon.InstalledAddonEntity) r4
            r9 = 0
            com.streamsync.app.data.addon.AddonManifest r4 = r3.toManifest(r4)
            java.util.List r10 = r4.getCatalogs()
            if (r10 != 0) goto L7c
            java.util.List r10 = kotlin.collections.CollectionsKt.emptyList()
        L7c:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            r11 = 0
            java.util.ArrayList r12 = new java.util.ArrayList
            r13 = 10
            int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r10, r13)
            r12.<init>(r13)
            java.util.Collection r12 = (java.util.Collection) r12
            r13 = 0
            java.util.Iterator r14 = r10.iterator()
        L91:
            boolean r10 = r14.hasNext()
            if (r10 == 0) goto La8
            java.lang.Object r10 = r14.next()
            r15 = r10
            com.streamsync.app.data.addon.AddonCatalogDef r15 = (com.streamsync.app.data.addon.AddonCatalogDef) r15
            r16 = 0
            com.streamsync.app.data.model.CatalogEntry r15 = r3.toEntry(r15, r4)
            r12.add(r15)
            goto L91
        La8:
            r10 = r12
            java.util.List r10 = (java.util.List) r10
            r4 = r10
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            kotlin.collections.CollectionsKt.addAll(r6, r4)
            goto L61
        Lb5:
            r4 = r6
            java.util.List r4 = (java.util.List) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.getCatalogEntries(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object getCatalogRows$default(AddonRepository addonRepository, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        return addonRepository.getCatalogRows(str, function1, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008b A[LOOP:0: B:18:0x0085->B:20:0x008b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCatalogRows(java.lang.String r22, kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.CatalogRow, kotlin.Unit> r23, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.CatalogRow>> r24) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.getCatalogRows(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String describe(Throwable e) {
        Object m6717constructorimpl;
        Response<?> response;
        ResponseBody errorBody;
        String text = null;
        HttpException http = e instanceof HttpException ? (HttpException) e : null;
        try {
            Result.Companion companion = Result.INSTANCE;
            AddonRepository addonRepository = this;
            m6717constructorimpl = Result.m6717constructorimpl((http == null || (response = http.response()) == null || (errorBody = response.errorBody()) == null) ? null : errorBody.string());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = null;
        }
        String body = (String) m6717constructorimpl;
        if (body != null) {
            String str = StringsKt.isBlank(body) ^ true ? body : null;
            if (str != null) {
                text = StringsKt.take(str, 300);
            }
        }
        if (text != null) {
            return (http != null ? Integer.valueOf(http.code()) : "?") + " " + text;
        }
        String message = e.getMessage();
        return message == null ? "khong ro" : message;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchCatalogRow(com.streamsync.app.data.addon.AddonManifest r25, com.streamsync.app.data.addon.AddonCatalogDef r26, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.CatalogRow> r27) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.fetchCatalogRow(com.streamsync.app.data.addon.AddonManifest, com.streamsync.app.data.addon.AddonCatalogDef, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object search$default(AddonRepository addonRepository, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 60;
        }
        return addonRepository.search(str, i, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005f A[LOOP:0: B:13:0x0059->B:15:0x005f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object search(java.lang.String r9, int r10, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.MediaItem>> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.streamsync.app.data.repository.AddonRepository$search$1
            if (r0 == 0) goto L14
            r0 = r11
            com.streamsync.app.data.repository.AddonRepository$search$1 r0 = (com.streamsync.app.data.repository.AddonRepository$search$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.streamsync.app.data.repository.AddonRepository$search$1 r0 = new com.streamsync.app.data.repository.AddonRepository$search$1
            r0.<init>(r8, r11)
        L19:
            r11 = r0
            java.lang.Object r6 = r11.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r11.label
            switch(r0) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            int r9 = r11.I$0
            kotlin.ResultKt.throwOnFailure(r6)
            r10 = r9
            r9 = r6
            goto L4a
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r0 = r8
            r1 = r9
            r11.I$0 = r10
            r9 = 1
            r11.label = r9
            r2 = 0
            r4 = 2
            r5 = 0
            r3 = r11
            java.lang.Object r9 = searchRows$default(r0, r1, r2, r3, r4, r5)
            if (r9 != r7) goto L4a
            return r7
        L4a:
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            r2 = 0
            java.util.Iterator r3 = r9.iterator()
        L59:
            boolean r9 = r3.hasNext()
            if (r9 == 0) goto L71
            java.lang.Object r9 = r3.next()
            r4 = r9
            com.streamsync.app.data.repository.SearchResultRow r4 = (com.streamsync.app.data.repository.SearchResultRow) r4
            r5 = 0
            java.util.List r4 = r4.getItems()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            kotlin.collections.CollectionsKt.addAll(r1, r4)
            goto L59
        L71:
            r9 = r1
            java.util.List r9 = (java.util.List) r9
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.take(r9, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.search(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object searchRows$default(AddonRepository addonRepository, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8;
        }
        return addonRepository.searchRows(str, i, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b4 A[LOOP:2: B:58:0x00ae->B:60:0x00b4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchRows(java.lang.String r32, int r33, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.repository.SearchResultRow>> r34) {
        /*
            Method dump skipped, instructions count: 756
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.searchRows(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00a9 A[LOOP:3: B:80:0x00a3->B:82:0x00a9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchCatalogPage(com.streamsync.app.data.model.CatalogRow r30, int r31, java.util.Set<java.lang.String> r32, kotlin.coroutines.Continuation<? super com.streamsync.app.ui.catalog.CatalogPage> r33) {
        /*
            Method dump skipped, instructions count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.fetchCatalogPage(com.streamsync.app.data.model.CatalogRow, int, java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object resolveImdbId$default(AddonRepository addonRepository, String str, String str2, String str3, String str4, Continuation continuation, int i, Object obj) {
        String str5;
        if ((i & 8) == 0) {
            str5 = str4;
        } else {
            str5 = null;
        }
        return addonRepository.resolveImdbId(str, str2, str3, str5, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00af A[LOOP:0: B:15:0x00a9->B:17:0x00af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveImdbId(java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, kotlin.coroutines.Continuation<? super java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveImdbId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object resolveStreams$default(AddonRepository addonRepository, String str, String str2, String str3, String str4, Integer num, Integer num2, Function1 function1, Continuation continuation, int i, Object obj) {
        return addonRepository.resolveStreams(str, (i & 2) != 0 ? "movie" : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : function1, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x03f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0103 A[LOOP:1: B:31:0x00fd->B:33:0x0103, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0144 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveStreams(java.lang.String r55, java.lang.String r56, java.lang.String r57, java.lang.String r58, java.lang.Integer r59, java.lang.Integer r60, kotlin.jvm.functions.Function1<? super java.util.List<com.streamsync.app.data.model.StreamSource>, kotlin.Unit> r61, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.StreamSource>> r62) {
        /*
            Method dump skipped, instructions count: 1140
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveStreams(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object resolveStreams$ask(kotlinx.coroutines.sync.Semaphore r21, kotlin.jvm.functions.Function1<? super java.util.List<com.streamsync.app.data.model.StreamSource>, kotlin.Unit> r22, com.streamsync.app.data.repository.AddonRepository r23, java.lang.String r24, java.lang.String r25, int r26, com.streamsync.app.data.addon.StreamResponse[] r27, java.util.List<kotlin.Pair<com.streamsync.app.data.addon.AddonManifest, java.lang.String>> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r0 = r29
            boolean r1 = r0 instanceof com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$1
            if (r1 == 0) goto L16
            r1 = r0
            com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$1 r1 = (com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$1 r1 = new com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$1
            r1.<init>(r0)
        L1b:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L54;
                case 1: goto L31;
                default: goto L27;
            }
        L27:
            r29 = r1
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L31:
            int r2 = r0.I$0
            java.lang.Object r3 = r0.L$5
            com.streamsync.app.data.addon.StreamResponse[] r3 = (com.streamsync.app.data.addon.StreamResponse[]) r3
            java.lang.Object r4 = r0.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r0.L$3
            com.streamsync.app.data.addon.StreamResponse[] r5 = (com.streamsync.app.data.addon.StreamResponse[]) r5
            java.lang.Object r6 = r0.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r0.L$1
            com.streamsync.app.data.repository.AddonRepository r7 = (com.streamsync.app.data.repository.AddonRepository) r7
            java.lang.Object r8 = r0.L$0
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            kotlin.ResultKt.throwOnFailure(r1)
            r29 = r1
            r12 = r3
            r3 = r29
            goto La8
        L54:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r21
            r11 = r23
            r12 = r27
            r9 = r25
            r13 = r22
            r14 = r24
            r15 = r28
            r10 = r26
            java.lang.Object r4 = r15.get(r10)
            r16 = r4
            kotlin.Pair r16 = (kotlin.Pair) r16
            long r17 = android.os.SystemClock.elapsedRealtime()
            com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$2 r19 = new com.streamsync.app.data.repository.AddonRepository$resolveStreams$ask$2
            r20 = 0
            r4 = r19
            r5 = r11
            r6 = r17
            r8 = r16
            r29 = r1
            r1 = r10
            r10 = r20
            r4.<init>(r5, r6, r8, r9, r10)
            r4 = r19
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r0.L$0 = r13
            r0.L$1 = r11
            r0.L$2 = r14
            r0.L$3 = r12
            r0.L$4 = r15
            r0.L$5 = r12
            r0.I$0 = r1
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = com.streamsync.app.data.repository.StreamCacheKt.withPermit(r3, r4, r0)
            if (r3 != r2) goto La2
            return r2
        La2:
            r2 = r1
            r7 = r11
            r5 = r12
            r8 = r13
            r6 = r14
            r4 = r15
        La8:
            r12[r2] = r3
            if (r8 == 0) goto Lb4
            java.util.List r1 = r7.buildSources(r5, r4, r6)
            r8.invoke(r1)
        Lb4:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveStreams$ask(kotlinx.coroutines.sync.Semaphore, kotlin.jvm.functions.Function1, com.streamsync.app.data.repository.AddonRepository, java.lang.String, java.lang.String, int, com.streamsync.app.data.addon.StreamResponse[], java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
    
        if ((r2 == null || kotlin.text.StringsKt.isBlank(r2)) == false) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.streamsync.app.data.model.StreamSource> buildSources(com.streamsync.app.data.addon.StreamResponse[] r26, java.util.List<kotlin.Pair<com.streamsync.app.data.addon.AddonManifest, java.lang.String>> r27, java.lang.String r28) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.buildSources(com.streamsync.app.data.addon.StreamResponse[], java.util.List, java.lang.String):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void collectStreams(com.streamsync.app.data.addon.AddonManifest r34, com.streamsync.app.data.addon.StreamResponse r35, java.lang.String r36, java.util.List<com.streamsync.app.data.model.StreamSource> r37, java.util.Set<java.lang.String> r38) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.collectStreams(com.streamsync.app.data.addon.AddonManifest, com.streamsync.app.data.addon.StreamResponse, java.lang.String, java.util.List, java.util.Set):void");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0030. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0337 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0144 A[LOOP:2: B:88:0x013e->B:90:0x0144, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0175  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x028a -> B:15:0x0298). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x02b8 -> B:18:0x02cd). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0331 -> B:39:0x0240). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveSubtitles(java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.Integer r28, java.lang.Integer r29, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.SubtitleTrack>> r30) {
        /*
            Method dump skipped, instructions count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveSubtitles(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean isSubtitleFile(String url) {
        if (StringsKt.isBlank(url)) {
            return true;
        }
        String path = StringsKt.substringBefore$default(StringsKt.substringBefore$default(url, '?', (String) null, 2, (Object) null), '#', (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(path, "toLowerCase(...)");
        Iterable $this$any$iv = AddonRepositoryKt.access$getSUBTITLE_EXTENSIONS$p();
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.endsWith$default(path, it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object probeStreamMimeType(com.streamsync.app.data.model.StreamSource r26, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.StreamSource> r27) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.probeStreamMimeType(com.streamsync.app.data.model.StreamSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object resolveStream$default(AddonRepository addonRepository, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "movie";
        }
        return addonRepository.resolveStream(str, str2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveStream(java.lang.String r16, java.lang.String r17, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.StreamSource> r18) {
        /*
            r15 = this;
            r0 = r18
            boolean r1 = r0 instanceof com.streamsync.app.data.repository.AddonRepository$resolveStream$1
            if (r1 == 0) goto L18
            r1 = r0
            com.streamsync.app.data.repository.AddonRepository$resolveStream$1 r1 = (com.streamsync.app.data.repository.AddonRepository$resolveStream$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r0 = r1
            r13 = r15
            goto L1f
        L18:
            com.streamsync.app.data.repository.AddonRepository$resolveStream$1 r1 = new com.streamsync.app.data.repository.AddonRepository$resolveStream$1
            r13 = r15
            r1.<init>(r15, r0)
            r0 = r1
        L1f:
            java.lang.Object r1 = r0.result
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L32;
                default: goto L2a;
            }
        L2a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L32:
            kotlin.ResultKt.throwOnFailure(r1)
            r2 = r1
            goto L52
        L37:
            kotlin.ResultKt.throwOnFailure(r1)
            r2 = r15
            r4 = r17
            r3 = r16
            r5 = 1
            r0.label = r5
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 124(0x7c, float:1.74E-43)
            r12 = 0
            r10 = r0
            java.lang.Object r2 = resolveStreams$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            if (r2 != r14) goto L52
            return r14
        L52:
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveStream(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object findItem$default(AddonRepository addonRepository, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "movie";
        }
        return addonRepository.findItem(str, str2, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object findItem(java.lang.String r5, java.lang.String r6, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.streamsync.app.data.repository.AddonRepository$findItem$1
            if (r0 == 0) goto L14
            r0 = r7
            com.streamsync.app.data.repository.AddonRepository$findItem$1 r0 = (com.streamsync.app.data.repository.AddonRepository$findItem$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.streamsync.app.data.repository.AddonRepository$findItem$1 r0 = new com.streamsync.app.data.repository.AddonRepository$findItem$1
            r0.<init>(r4, r7)
        L19:
            r7 = r0
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L36;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            java.lang.Object r5 = r7.L$0
            com.streamsync.app.data.model.MediaItem r5 = (com.streamsync.app.data.model.MediaItem) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r0
            goto L62
        L36:
            java.lang.Object r5 = r7.L$0
            com.streamsync.app.data.repository.AddonRepository r5 = (com.streamsync.app.data.repository.AddonRepository) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r5
            r5 = r0
            goto L50
        L40:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r4
            r7.L$0 = r2
            r3 = 1
            r7.label = r3
            java.lang.Object r5 = r2.findItemRaw(r5, r6, r7)
            if (r5 != r1) goto L50
            return r1
        L50:
            com.streamsync.app.data.model.MediaItem r5 = (com.streamsync.app.data.model.MediaItem) r5
            if (r5 != 0) goto L56
            r5 = 0
            return r5
        L56:
            r7.L$0 = r5
            r6 = 2
            r7.label = r6
            java.lang.Object r6 = r2.withEpisodeTitle(r5, r7)
            if (r6 != r1) goto L62
            return r1
        L62:
            com.streamsync.app.data.model.MediaItem r6 = (com.streamsync.app.data.model.MediaItem) r6
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            if (r1 != 0) goto L6f
            com.streamsync.app.data.local.MediaItemCache r1 = com.streamsync.app.data.local.MediaItemCache.INSTANCE
            r1.put(r6)
        L6f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.findItem(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x028c, code lost:
    
        if (r3 != null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x02d5, code lost:
    
        if (r6 == null) goto L117;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02ef A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a7 A[LOOP:1: B:86:0x01a1->B:88:0x01a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object withEpisodeTitle(com.streamsync.app.data.model.MediaItem r57, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r58) {
        /*
            Method dump skipped, instructions count: 924
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.withEpisodeTitle(com.streamsync.app.data.model.MediaItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:1|(2:3|(4:5|6|7|8))|263|6|7|8) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:161|(1:162)|163|164|165|166|(1:168)(8:169|148|149|150|151|(1:153)|154|(2:156|157)(3:158|159|(4:177|178|179|(1:181)(8:182|92|93|94|95|(1:97)|98|(2:100|101)(9:102|(1:104)(1:137)|105|(1:107)|108|(7:111|(1:131)(1:115)|(4:117|(1:119)(1:129)|120|(3:122|(2:124|125)(1:127)|126))(1:130)|128|(0)(0)|126|109)|132|133|(1:135)(6:136|55|(3:58|(12:61|62|(1:64)|65|66|67|68|69|70|71|72|(1:74)(12:75|26|27|28|29|(1:31)|32|(1:(1:45)(1:46))|36|(1:38)(1:43)|39|(1:41)(6:42|13|(1:15)(1:21)|(1:17)(1:20)|18|19)))(1:60)|56)|85|86|87))))(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x03cc, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x03cd, code lost:
    
        r3 = r34;
        r19 = r2;
        r30 = r13;
        r13 = r5;
        r5 = r12;
        r12 = r8;
        r31 = r14;
        r14 = r7;
        r15 = r30;
        r7 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x014f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x0188, code lost:
    
        r14 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m6717constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x002f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x054c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0334 A[LOOP:2: B:193:0x032e->B:195:0x0334, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02af A[LOOP:3: B:200:0x02a9->B:202:0x02af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02f4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x061a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0620  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0661 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0662  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0631  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:164:0x03a9 -> B:143:0x03bb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:168:0x03ef -> B:146:0x03ff). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object findItemRaw(java.lang.String r34, java.lang.String r35, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r36) {
        /*
            Method dump skipped, instructions count: 1730
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.findItemRaw(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void logTiming(String what, long t0, String compositeId, String extra) {
        Log.i("StreamSyncData", what + " " + compositeId + " " + (SystemClock.elapsedRealtime() - t0) + "ms " + extra);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01b5 A[EDGE_INSN: B:116:0x01b5->B:117:0x01b5 BREAK  A[LOOP:6: B:104:0x0189->B:142:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020c A[LOOP:7: B:122:0x01d4->B:133:0x020c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[LOOP:6: B:104:0x0189->B:142:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.Pair<com.streamsync.app.data.addon.AddonManifest, java.lang.String>> buildMetaCandidates(java.util.List<com.streamsync.app.data.addon.AddonManifest> r23, java.lang.String r24, java.lang.String r25, java.lang.String r26) {
        /*
            Method dump skipped, instructions count: 549
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.buildMetaCandidates(java.util.List, java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    private static final void buildMetaCandidates$offer(LinkedHashMap<String, Pair<AddonManifest, String>> linkedHashMap, AddonManifest addon, String type) {
        linkedHashMap.putIfAbsent(addon.getId() + "|" + type, TuplesKt.to(addon, type));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0121 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveViaMeta(com.streamsync.app.data.addon.AddonManifest r33, java.lang.String r34, java.lang.String r35, boolean r36, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r37) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.resolveViaMeta(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object resolveViaMeta$default(AddonRepository addonRepository, AddonManifest addonManifest, String str, String str2, boolean z, Continuation continuation, int i, Object obj) {
        boolean z2;
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return addonRepository.resolveViaMeta(addonManifest, str, str2, z2, continuation);
    }

    private final boolean needsSeriesMeta(MediaItem $this$needsSeriesMeta) {
        return $this$needsSeriesMeta.getType() == MediaType.SERIES && $this$needsSeriesMeta.getVideos().isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchMetaCached(com.streamsync.app.data.addon.AddonManifest r18, java.lang.String r19, java.lang.String r20, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.AddonMeta> r21) {
        /*
            r17 = this;
            r0 = r21
            boolean r1 = r0 instanceof com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$1
            if (r1 == 0) goto L19
            r1 = r0
            com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$1 r1 = (com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L19
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r0 = r1
            r2 = r17
            goto L21
        L19:
            com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$1 r1 = new com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$1
            r2 = r17
            r1.<init>(r2, r0)
            r0 = r1
        L21:
            java.lang.Object r1 = r0.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r0.label
            r5 = 0
            switch(r4) {
                case 0: goto L3e;
                case 1: goto L35;
                default: goto L2d;
            }
        L2d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            java.lang.Object r3 = r0.L$0
            java.lang.String r3 = (java.lang.String) r3
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r1
            goto L85
        L3e:
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r19
            r6 = r18
            r7 = r20
            com.streamsync.app.data.repository.MetaCache r8 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            java.lang.String r9 = r6.getId()
            java.lang.String r8 = r8.key(r9, r4, r7)
            com.streamsync.app.data.repository.MetaCache r10 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            r14 = 2
            r15 = 0
            r12 = 0
            r11 = r8
            com.streamsync.app.data.addon.AddonMeta r9 = com.streamsync.app.data.repository.MetaCache.get$default(r10, r11, r12, r14, r15)
            if (r9 == 0) goto L61
            r3 = r9
            r4 = 0
            return r3
        L61:
            com.streamsync.app.data.repository.MetaCache r10 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            r14 = 2
            r15 = 0
            r12 = 0
            r11 = r8
            boolean r9 = com.streamsync.app.data.repository.MetaCache.wasMissed$default(r10, r11, r12, r14, r15)
            if (r9 == 0) goto L6f
            return r5
        L6f:
            com.streamsync.app.data.repository.MetaCache r9 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$3 r10 = new com.streamsync.app.data.repository.AddonRepository$fetchMetaCached$3
            r10.<init>(r6, r4, r7, r5)
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            r0.L$0 = r8
            r11 = 1
            r0.label = r11
            java.lang.Object r4 = r9.coalesce(r8, r10, r0)
            if (r4 != r3) goto L84
            return r3
        L84:
            r3 = r8
        L85:
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r6 = r4.component1()
            com.streamsync.app.data.addon.AddonMeta r6 = (com.streamsync.app.data.addon.AddonMeta) r6
            java.lang.Object r4 = r4.component2()
            com.streamsync.app.data.addon.CacheDirective r4 = (com.streamsync.app.data.addon.CacheDirective) r4
            if (r6 == 0) goto La5
            com.streamsync.app.data.repository.MetaCache r9 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            r15 = 8
            r16 = 0
            r13 = 0
            r10 = r3
            r11 = r6
            r12 = r4
            com.streamsync.app.data.repository.MetaCache.put$default(r9, r10, r11, r12, r13, r15, r16)
            r5 = r6
            goto Lb0
        La5:
            com.streamsync.app.data.repository.MetaCache r9 = com.streamsync.app.data.repository.MetaCache.INSTANCE
            r13 = 2
            r14 = 0
            r11 = 0
            r10 = r3
            com.streamsync.app.data.repository.MetaCache.markMissed$default(r9, r10, r11, r13, r14)
        Lb0:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.fetchMetaCached(com.streamsync.app.data.addon.AddonManifest, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchMetaVariants(com.streamsync.app.data.addon.AddonManifest r11, java.util.List<java.lang.String> r12, java.lang.String r13, kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, com.streamsync.app.data.addon.AddonMeta>> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$1
            if (r0 == 0) goto L14
            r0 = r14
            com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$1 r0 = (com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$1 r0 = new com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$1
            r0.<init>(r10, r14)
        L19:
            r14 = r0
            java.lang.Object r0 = r14.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r14.label
            switch(r2) {
                case 0: goto L39;
                case 1: goto L32;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2d:
            kotlin.ResultKt.throwOnFailure(r0)
            r11 = r0
            goto L8d
        L32:
            java.lang.Object r11 = r14.L$0
            kotlin.ResultKt.throwOnFailure(r0)
            r12 = r0
            goto L6d
        L39:
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r10
            r2 = r12
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r12 = kotlin.collections.CollectionsKt.distinct(r2)
            boolean r2 = r12.isEmpty()
            if (r2 == 0) goto L4f
            java.util.Map r1 = kotlin.collections.MapsKt.emptyMap()
            return r1
        L4f:
            int r2 = r12.size()
            r3 = 1
            if (r2 != r3) goto L76
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r12)
            java.lang.Object r4 = kotlin.collections.CollectionsKt.first(r12)
            java.lang.String r4 = (java.lang.String) r4
            r14.L$0 = r2
            r14.label = r3
            java.lang.Object r11 = r8.fetchMetaCached(r11, r4, r13, r14)
            if (r11 != r1) goto L6b
            return r1
        L6b:
            r12 = r11
            r11 = r2
        L6d:
            kotlin.Pair r11 = kotlin.TuplesKt.to(r11, r12)
            java.util.Map r11 = kotlin.collections.MapsKt.mapOf(r11)
            return r11
        L76:
            com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$2 r9 = new com.streamsync.app.data.repository.AddonRepository$fetchMetaVariants$2
            r7 = 0
            r2 = r9
            r3 = r12
            r4 = r8
            r5 = r11
            r6 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r2 = 2
            r14.label = r2
            java.lang.Object r11 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r9, r14)
            if (r11 != r1) goto L8d
            return r1
        L8d:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.fetchMetaVariants(com.streamsync.app.data.addon.AddonManifest, java.util.List, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0031. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01cc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00fa A[LOOP:0: B:32:0x00f4->B:34:0x00fa, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0139 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0134 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enrichFromMeta(com.streamsync.app.data.model.MediaItem r32, java.lang.String r33, java.lang.String r34, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r35) {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.enrichFromMeta(com.streamsync.app.data.model.MediaItem, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object withVideosIfSeries(com.streamsync.app.data.addon.AddonMeta r5, com.streamsync.app.data.addon.AddonManifest r6, java.lang.String r7, com.streamsync.app.data.addon.AddonMeta r8, kotlin.coroutines.Continuation<? super com.streamsync.app.data.addon.AddonMeta> r9) {
        /*
            r4 = this;
            boolean r7 = r9 instanceof com.streamsync.app.data.repository.AddonRepository$withVideosIfSeries$1
            if (r7 == 0) goto L14
            r7 = r9
            com.streamsync.app.data.repository.AddonRepository$withVideosIfSeries$1 r7 = (com.streamsync.app.data.repository.AddonRepository$withVideosIfSeries$1) r7
            int r0 = r7.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L14
            int r9 = r7.label
            int r9 = r9 - r1
            r7.label = r9
            goto L19
        L14:
            com.streamsync.app.data.repository.AddonRepository$withVideosIfSeries$1 r7 = new com.streamsync.app.data.repository.AddonRepository$withVideosIfSeries$1
            r7.<init>(r4, r9)
        L19:
            java.lang.Object r9 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            java.lang.Object r5 = r7.L$0
            com.streamsync.app.data.addon.AddonMeta r5 = (com.streamsync.app.data.addon.AddonMeta) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r9
            goto L67
        L35:
            kotlin.ResultKt.throwOnFailure(r9)
            r1 = r4
            java.util.List r2 = r5.getVideos()
            java.util.Collection r2 = (java.util.Collection) r2
            r3 = 1
            if (r2 == 0) goto L4b
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L49
            goto L4b
        L49:
            r2 = 0
            goto L4c
        L4b:
            r2 = r3
        L4c:
            if (r2 != 0) goto L4f
            return r5
        L4f:
            if (r8 != 0) goto L6d
        L52:
            java.lang.String r8 = r5.getId()
            if (r8 != 0) goto L5a
            java.lang.String r8 = ""
        L5a:
            r7.L$0 = r5
            r7.label = r3
            java.lang.String r2 = "series"
            java.lang.Object r6 = r1.fetchMetaCached(r6, r2, r8, r7)
            if (r6 != r0) goto L67
            return r0
        L67:
            r8 = r6
            com.streamsync.app.data.addon.AddonMeta r8 = (com.streamsync.app.data.addon.AddonMeta) r8
            if (r8 != 0) goto L6d
            return r5
        L6d:
            r6 = r8
            com.streamsync.app.data.addon.AddonMeta r8 = com.streamsync.app.data.repository.AddonRepositoryKt.mergeSeriesEpisodesFrom(r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.withVideosIfSeries(com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.AddonManifest, java.lang.String, com.streamsync.app.data.addon.AddonMeta, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object withVideosIfSeries$default(AddonRepository addonRepository, AddonMeta addonMeta, AddonManifest addonManifest, String str, AddonMeta addonMeta2, Continuation continuation, int i, Object obj) {
        AddonMeta addonMeta3;
        if ((i & 4) == 0) {
            addonMeta3 = addonMeta2;
        } else {
            addonMeta3 = null;
        }
        return addonRepository.withVideosIfSeries(addonMeta, addonManifest, str, addonMeta3, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CatalogEntry> toEntries(List<AddonCatalogDef> list, AddonManifest manifest) {
        List<AddonCatalogDef> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            AddonCatalogDef def = (AddonCatalogDef) item$iv$iv;
            destination$iv$iv.add(toEntry(def, manifest));
        }
        return (List) destination$iv$iv;
    }

    private final CatalogEntry toEntry(AddonCatalogDef $this$toEntry, AddonManifest manifest) {
        CatalogKey catalogKey = CatalogKey.INSTANCE;
        String id = manifest.getId();
        String type = $this$toEntry.getType();
        if (type == null) {
            type = "";
        }
        String of = catalogKey.of(id, type, $this$toEntry.getId());
        String id2 = manifest.getId();
        String name = manifest.getName();
        String type2 = $this$toEntry.getType();
        String str = type2 == null ? "" : type2;
        String id3 = $this$toEntry.getId();
        String str2 = id3 == null ? "" : id3;
        String name2 = $this$toEntry.getName();
        return new CatalogEntry(of, id2, name, str, str2, name2 == null ? "" : name2);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.streamsync.app.data.model.SeriesVideo> toSeriesVideos(java.util.List<com.streamsync.app.data.addon.AddonVideo> r30) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.toSeriesVideos(java.util.List):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MediaItem toMediaItem$default(AddonRepository addonRepository, AddonMeta addonMeta, AddonManifest addonManifest, AddonCatalogDef addonCatalogDef, String str, boolean z, boolean z2, int i, Object obj) {
        String str2 = (i & 4) != 0 ? null : str;
        boolean z3 = (i & 8) != 0 ? false : z;
        return addonRepository.toMediaItem(addonMeta, addonManifest, addonCatalogDef, str2, z3, (i & 16) != 0 ? !z3 : z2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:160:0x041a, code lost:
    
        if ((!kotlin.text.StringsKt.isBlank(r43)) != false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009b, code lost:
    
        if ((r7.length() > 0) != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x018c, code lost:
    
        if ((r29.length() > 0 ? 1 : null) != null) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0200, code lost:
    
        if ((r30.length() > 0 ? r3 : null) != null) goto L106;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x029a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0340 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0239  */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.streamsync.app.data.model.MediaItem toMediaItem(com.streamsync.app.data.addon.AddonMeta r48, com.streamsync.app.data.addon.AddonManifest r49, com.streamsync.app.data.addon.AddonCatalogDef r50, java.lang.String r51, boolean r52, boolean r53) {
        /*
            Method dump skipped, instructions count: 1211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.toMediaItem(com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.AddonManifest, com.streamsync.app.data.addon.AddonCatalogDef, java.lang.String, boolean, boolean):com.streamsync.app.data.model.MediaItem");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonManifest toManifest(InstalledAddonEntity $this$toManifest) {
        String id = $this$toManifest.getId();
        String name = $this$toManifest.getName();
        String version = $this$toManifest.getVersion();
        String description = $this$toManifest.getDescription();
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) $this$toManifest.getTypesCsv(), new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        ArrayList arrayList = (List) destination$iv$iv;
        Iterable iterable = (List) this.gson.fromJson($this$toManifest.getCatalogsJson(), new TypeToken<List<? extends AddonCatalogDef>>() { // from class: com.streamsync.app.data.repository.AddonRepository$toManifest$2
        }.getType());
        if (iterable == null) {
            iterable = CollectionsKt.emptyList();
        }
        Iterable $this$filter$iv2 = iterable;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            AddonCatalogDef it2 = (AddonCatalogDef) element$iv$iv2;
            String id2 = it2.getId();
            if (!(id2 == null || StringsKt.isBlank(id2))) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List<AddonCatalogDef> withDistinctCatalogNames = CatalogDisplayNameKt.withDistinctCatalogNames((List) destination$iv$iv2);
        Iterable iterable2 = (List) this.gson.fromJson($this$toManifest.getResourcesJson(), new TypeToken<List<? extends AddonResourceDef>>() { // from class: com.streamsync.app.data.repository.AddonRepository$toManifest$4
        }.getType());
        if (iterable2 == null) {
            iterable2 = CollectionsKt.emptyList();
        }
        Iterable $this$filter$iv3 = iterable2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv3 : $this$filter$iv3) {
            AddonResourceDef it3 = (AddonResourceDef) element$iv$iv3;
            if (!StringsKt.isBlank(it3.getName())) {
                destination$iv$iv3.add(element$iv$iv3);
            }
        }
        ArrayList arrayList2 = (List) destination$iv$iv3;
        Iterable $this$filter$iv4 = StringsKt.split$default((CharSequence) $this$toManifest.getIdPrefixesCsv(), new String[]{","}, false, 0, 6, (Object) null);
        Collection destination$iv$iv4 = new ArrayList();
        for (Object element$iv$iv4 : $this$filter$iv4) {
            String it4 = (String) element$iv$iv4;
            if (!StringsKt.isBlank(it4)) {
                destination$iv$iv4.add(element$iv$iv4);
            }
        }
        return new AddonManifest(id, name, version, description, arrayList, withDistinctCatalogNames, arrayList2, (List) destination$iv$iv4, $this$toManifest.getLogoUrl(), $this$toManifest.getSortOrder(), $this$toManifest.getBaseUrl());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0122 -> B:12:0x0128). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object reorderAddons(java.util.List<com.streamsync.app.data.addon.AddonManifest> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepository.reorderAddons(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final InstalledAddonEntity toEntity(AddonManifest $this$toEntity) {
        Gson gson = this.gson;
        List<AddonResourceDef> resources = $this$toEntity.getResources();
        if (resources == null) {
            resources = CollectionsKt.emptyList();
        }
        String json = gson.toJson(resources);
        List<String> idPrefixes = $this$toEntity.getIdPrefixes();
        if (idPrefixes == null) {
            idPrefixes = CollectionsKt.emptyList();
        }
        String joinToString$default = CollectionsKt.joinToString$default(idPrefixes, ",", null, null, 0, null, null, 62, null);
        String baseUrl = $this$toEntity.getBaseUrl();
        String id = $this$toEntity.getId();
        String name = $this$toEntity.getName();
        String version = $this$toEntity.getVersion();
        String description = $this$toEntity.getDescription();
        List<String> types = $this$toEntity.getTypes();
        if (types == null) {
            types = CollectionsKt.emptyList();
        }
        String joinToString$default2 = CollectionsKt.joinToString$default(types, ",", null, null, 0, null, null, 62, null);
        String json2 = this.gson.toJson($this$toEntity.getCatalogs());
        String it = $this$toEntity.getLogo();
        String str = (it == null || !(StringsKt.isBlank(it) ^ true)) ? null : it;
        int sortOrder = $this$toEntity.getSortOrder();
        Intrinsics.checkNotNull(json2);
        Intrinsics.checkNotNull(json);
        return new InstalledAddonEntity(baseUrl, id, name, version, description, joinToString$default2, json2, json, joinToString$default, str, sortOrder);
    }
}
