package com.streamsync.app.ui.detail;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.data.repository.ContentRepository;
import com.streamsync.app.data.repository.SyncRepository;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 K2\u00020\u0001:\u0001KB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u00109\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u0013H\u0002J\u0016\u0010;\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010<J\u0006\u0010=\u001a\u00020>J\u000e\u0010?\u001a\u00020>H\u0082@¢\u0006\u0002\u0010@J\u000e\u0010A\u001a\u00020>2\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020>J\u0018\u0010E\u001a\u0004\u0018\u00010\u00132\u0006\u0010F\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010GJ&\u0010H\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010F\u001a\u00020\u00102\b\u0010I\u001a\u0004\u0018\u00010\u0013H\u0082@¢\u0006\u0002\u0010JR \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n \u001b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\t0\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u001f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0019\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0019\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u001f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f¢\u0006\b\n\u0000\u001a\u0004\b/\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\n0\u001f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010!R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u001f¢\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0010\u00104\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/streamsync/app/ui/detail/MovieDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "mediaId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_episodeProgress", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_episodeStreams", "", "Lcom/streamsync/app/data/model/StreamSource;", "_episodeStreamsFor", "_episodeStreamsLoading", "", "_error", "_item", "Lcom/streamsync/app/data/model/MediaItem;", "_loading", "_resumePositionMs", "_streams", "_streamsLoading", "addonRepo", "Lcom/streamsync/app/data/repository/AddonRepository;", "appContext", "kotlin.jvm.PlatformType", "contentRepo", "Lcom/streamsync/app/data/repository/ContentRepository;", "episodeProgress", "Lkotlinx/coroutines/flow/StateFlow;", "getEpisodeProgress", "()Lkotlinx/coroutines/flow/StateFlow;", "episodeStreams", "getEpisodeStreams", "episodeStreamsFor", "getEpisodeStreamsFor", "episodeStreamsJob", "Lkotlinx/coroutines/Job;", "episodeStreamsLoading", "getEpisodeStreamsLoading", "error", "getError", "item", "getItem", "loading", "getLoading", "resumePositionMs", "getResumePositionMs", "streams", "getStreams", "streamsJob", "streamsLoading", "getStreamsLoading", "syncRepo", "Lcom/streamsync/app/data/repository/SyncRepository;", "episodeDisplayTitle", "parent", "fillEpisodeFromParent", "(Lcom/streamsync/app/data/model/MediaItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "", "loadEliteBadges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadEpisodeStreams", "episode", "Lcom/streamsync/app/data/model/SeriesVideo;", "refreshStreams", "resolveDetail", "fromAddon", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveSources", "cached", "(ZLcom/streamsync/app/data/model/MediaItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class MovieDetailViewModel extends ViewModel {
    private final MutableStateFlow<Map<String, Long>> _episodeProgress;
    private final MutableStateFlow<List<StreamSource>> _episodeStreams;
    private final MutableStateFlow<String> _episodeStreamsFor;
    private final MutableStateFlow<Boolean> _episodeStreamsLoading;
    private final MutableStateFlow<String> _error;
    private final MutableStateFlow<MediaItem> _item;
    private final MutableStateFlow<Boolean> _loading;
    private final MutableStateFlow<Long> _resumePositionMs;
    private final MutableStateFlow<List<StreamSource>> _streams;
    private final MutableStateFlow<Boolean> _streamsLoading;
    private final AddonRepository addonRepo;
    private final Context appContext;
    private final ContentRepository contentRepo;
    private final StateFlow<Map<String, Long>> episodeProgress;
    private final StateFlow<List<StreamSource>> episodeStreams;
    private final StateFlow<String> episodeStreamsFor;
    private Job episodeStreamsJob;
    private final StateFlow<Boolean> episodeStreamsLoading;
    private final StateFlow<String> error;
    private final StateFlow<MediaItem> item;
    private final StateFlow<Boolean> loading;
    private final String mediaId;
    private final StateFlow<Long> resumePositionMs;
    private final StateFlow<List<StreamSource>> streams;
    private Job streamsJob;
    private final StateFlow<Boolean> streamsLoading;
    private final SyncRepository syncRepo;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public MovieDetailViewModel(Context context, String mediaId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        this.mediaId = mediaId;
        this.appContext = context.getApplicationContext();
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.addonRepo = new AddonRepository(appContext);
        this.contentRepo = new ContentRepository();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.syncRepo = new SyncRepository(applicationContext);
        this._item = StateFlowKt.MutableStateFlow(MediaItemCache.INSTANCE.get(this.mediaId));
        this.item = FlowKt.asStateFlow(this._item);
        this._streams = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.streams = FlowKt.asStateFlow(this._streams);
        this._loading = StateFlowKt.MutableStateFlow(true);
        this.loading = FlowKt.asStateFlow(this._loading);
        this._streamsLoading = StateFlowKt.MutableStateFlow(false);
        this.streamsLoading = FlowKt.asStateFlow(this._streamsLoading);
        this._error = StateFlowKt.MutableStateFlow(null);
        this.error = FlowKt.asStateFlow(this._error);
        this._resumePositionMs = StateFlowKt.MutableStateFlow(0L);
        this.resumePositionMs = FlowKt.asStateFlow(this._resumePositionMs);
        this._episodeStreams = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.episodeStreams = FlowKt.asStateFlow(this._episodeStreams);
        this._episodeStreamsFor = StateFlowKt.MutableStateFlow(null);
        this.episodeStreamsFor = FlowKt.asStateFlow(this._episodeStreamsFor);
        this._episodeStreamsLoading = StateFlowKt.MutableStateFlow(false);
        this.episodeStreamsLoading = FlowKt.asStateFlow(this._episodeStreamsLoading);
        this._episodeProgress = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this.episodeProgress = FlowKt.asStateFlow(this._episodeProgress);
        load();
    }

    public final StateFlow<MediaItem> getItem() {
        return this.item;
    }

    public final StateFlow<List<StreamSource>> getStreams() {
        return this.streams;
    }

    public final StateFlow<Boolean> getLoading() {
        return this.loading;
    }

    public final StateFlow<Boolean> getStreamsLoading() {
        return this.streamsLoading;
    }

    public final StateFlow<String> getError() {
        return this.error;
    }

    public final StateFlow<Long> getResumePositionMs() {
        return this.resumePositionMs;
    }

    public final StateFlow<List<StreamSource>> getEpisodeStreams() {
        return this.episodeStreams;
    }

    public final StateFlow<String> getEpisodeStreamsFor() {
        return this.episodeStreamsFor;
    }

    public final StateFlow<Boolean> getEpisodeStreamsLoading() {
        return this.episodeStreamsLoading;
    }

    public final StateFlow<Map<String, Long>> getEpisodeProgress() {
        return this.episodeProgress;
    }

    public final void loadEpisodeStreams(SeriesVideo episode) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(episode, "episode");
        if (Intrinsics.areEqual(this._episodeStreamsFor.getValue(), episode.getId())) {
            return;
        }
        MediaItem value = this.item.getValue();
        String addonId = value != null ? value.getAddonId() : null;
        if (addonId == null) {
            addonId = "";
        }
        if (addonId.length() == 0) {
            return;
        }
        Job job = this.episodeStreamsJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MovieDetailViewModel$loadEpisodeStreams$1(this, episode, addonId, null), 3, null);
        this.episodeStreamsJob = launch$default;
    }

    public final void refreshStreams() {
        SeriesVideo seriesVideo;
        Job launch$default;
        Iterable videos;
        Object element$iv;
        String episodeId = this._episodeStreamsFor.getValue();
        MediaItem value = this.item.getValue();
        if (value == null || (videos = value.getVideos()) == null) {
            seriesVideo = null;
        } else {
            Iterable $this$firstOrNull$iv = videos;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    SeriesVideo it2 = (SeriesVideo) element$iv;
                    if (Intrinsics.areEqual(it2.getId(), episodeId)) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            seriesVideo = (SeriesVideo) element$iv;
        }
        SeriesVideo episode = seriesVideo;
        if (episode != null) {
            this._episodeStreamsFor.setValue(null);
            this._episodeStreams.setValue(CollectionsKt.emptyList());
            loadEpisodeStreams(episode);
        } else {
            Job job = this.streamsJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MovieDetailViewModel$refreshStreams$1(this, null), 3, null);
            this.streamsJob = launch$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(13:1|(2:3|(11:5|6|7|8|15|16|(1:18)|19|(1:21)|22|23))|35|6|7|8|15|16|(0)|19|(0)|22|23) */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0033, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0074, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r1 = kotlin.Result.m6717constructorimpl(kotlin.ResultKt.createFailure(r1));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadEliteBadges(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.streamsync.app.ui.detail.MovieDetailViewModel$loadEliteBadges$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.ui.detail.MovieDetailViewModel$loadEliteBadges$1 r0 = (com.streamsync.app.ui.detail.MovieDetailViewModel$loadEliteBadges$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.ui.detail.MovieDetailViewModel$loadEliteBadges$1 r0 = new com.streamsync.app.ui.detail.MovieDetailViewModel$loadEliteBadges$1
            r0.<init>(r7, r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2d:
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L33
            r4 = r0
            goto L65
        L33:
            r1 = move-exception
            goto L74
        L35:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r7
            com.streamsync.app.settings.IntegrationStore r3 = new com.streamsync.app.settings.IntegrationStore
            android.content.Context r4 = r2.appContext
            java.lang.String r5 = "appContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r3.<init>(r4)
            boolean r4 = r3.getBadgesActive()
            if (r4 != 0) goto L4e
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L4e:
            kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L33
            com.streamsync.app.ui.detail.MovieDetailViewModel r2 = (com.streamsync.app.ui.detail.MovieDetailViewModel) r2     // Catch: java.lang.Throwable -> L33
            r2 = 0
            com.streamsync.app.data.remote.EliteBadges r4 = com.streamsync.app.data.remote.EliteBadges.INSTANCE     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = r3.getBadgesUrl()     // Catch: java.lang.Throwable -> L33
            r6 = 1
            r8.label = r6     // Catch: java.lang.Throwable -> L33
            java.lang.Object r4 = r4.load(r5, r8)     // Catch: java.lang.Throwable -> L33
            if (r4 != r1) goto L64
            return r1
        L64:
            r1 = r2
        L65:
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch: java.lang.Throwable -> L33
            boolean r2 = r4.booleanValue()     // Catch: java.lang.Throwable -> L33
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)     // Catch: java.lang.Throwable -> L33
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)     // Catch: java.lang.Throwable -> L33
            goto L7e
        L74:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
        L7e:
            r2 = 0
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            boolean r3 = kotlin.Result.m6723isFailureimpl(r1)
            if (r3 == 0) goto L8a
            r1 = r2
        L8a:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L9c
            com.streamsync.app.util.FileLog r2 = com.streamsync.app.util.FileLog.INSTANCE
            java.lang.String r3 = "Không nạp được cấu hình badges từ URL"
            r4 = 2
            r5 = 0
            com.streamsync.app.util.FileLog.w$default(r2, r3, r5, r4, r5)
        L9c:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel.loadEliteBadges(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: MovieDetailScreen.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/streamsync/app/ui/detail/MovieDetailViewModel$Companion;", "", "()V", "releaseYearOf", "", "releaseInfo", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes16.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Integer releaseYearOf(String releaseInfo) {
            MatchResult find$default;
            String value;
            String str = releaseInfo;
            if ((str == null || StringsKt.isBlank(str)) || (find$default = Regex.find$default(new Regex("\\b(19|20)\\d{2}\\b"), releaseInfo, 0, 2, null)) == null || (value = find$default.getValue()) == null) {
                return null;
            }
            return StringsKt.toIntOrNull(value);
        }
    }

    public final void load() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MovieDetailViewModel$load$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveDetail(boolean r11, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.streamsync.app.ui.detail.MovieDetailViewModel$resolveDetail$1
            if (r0 == 0) goto L14
            r0 = r12
            com.streamsync.app.ui.detail.MovieDetailViewModel$resolveDetail$1 r0 = (com.streamsync.app.ui.detail.MovieDetailViewModel$resolveDetail$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            com.streamsync.app.ui.detail.MovieDetailViewModel$resolveDetail$1 r0 = new com.streamsync.app.ui.detail.MovieDetailViewModel$resolveDetail$1
            r0.<init>(r10, r12)
        L19:
            r12 = r0
            java.lang.Object r6 = r12.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.label
            r8 = 0
            switch(r0) {
                case 0: goto L4a;
                case 1: goto L40;
                case 2: goto L34;
                case 3: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2e:
            kotlin.ResultKt.throwOnFailure(r6)
            r11 = r6
            goto Lad
        L34:
            r11 = 0
            java.lang.Object r0 = r12.L$0
            com.streamsync.app.ui.detail.MovieDetailViewModel r0 = (com.streamsync.app.ui.detail.MovieDetailViewModel) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L3e
            r1 = r6
            goto L80
        L3e:
            r11 = move-exception
            goto L89
        L40:
            java.lang.Object r11 = r12.L$0
            com.streamsync.app.ui.detail.MovieDetailViewModel r11 = (com.streamsync.app.ui.detail.MovieDetailViewModel) r11
            kotlin.ResultKt.throwOnFailure(r6)
            r9 = r11
            r11 = r6
            goto L65
        L4a:
            kotlin.ResultKt.throwOnFailure(r6)
            r9 = r10
            if (r11 == 0) goto L68
            com.streamsync.app.data.repository.AddonRepository r0 = r9.addonRepo
            java.lang.String r1 = r9.mediaId
            r12.L$0 = r9
            r11 = 1
            r12.label = r11
            r2 = 0
            r4 = 2
            r5 = 0
            r3 = r12
            java.lang.Object r11 = com.streamsync.app.data.repository.AddonRepository.findItem$default(r0, r1, r2, r3, r4, r5)
            if (r11 != r7) goto L65
            return r7
        L65:
            com.streamsync.app.data.model.MediaItem r11 = (com.streamsync.app.data.model.MediaItem) r11
            goto L9d
        L68:
            kotlin.Result$Companion r11 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L87
            r11 = r9
            com.streamsync.app.ui.detail.MovieDetailViewModel r11 = (com.streamsync.app.ui.detail.MovieDetailViewModel) r11     // Catch: java.lang.Throwable -> L87
            r0 = 0
            com.streamsync.app.data.repository.ContentRepository r1 = r11.contentRepo     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = r11.mediaId     // Catch: java.lang.Throwable -> L87
            r12.L$0 = r9     // Catch: java.lang.Throwable -> L87
            r3 = 2
            r12.label = r3     // Catch: java.lang.Throwable -> L87
            java.lang.Object r1 = r1.getDetail(r2, r12)     // Catch: java.lang.Throwable -> L87
            if (r1 != r7) goto L7e
            return r7
        L7e:
            r11 = r0
            r0 = r9
        L80:
            com.streamsync.app.data.model.MediaItem r1 = (com.streamsync.app.data.model.MediaItem) r1     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r11 = kotlin.Result.m6717constructorimpl(r1)     // Catch: java.lang.Throwable -> L3e
            goto L93
        L87:
            r11 = move-exception
            r0 = r9
        L89:
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m6717constructorimpl(r11)
        L93:
            r9 = r0
            boolean r0 = kotlin.Result.m6723isFailureimpl(r11)
            if (r0 == 0) goto L9b
            r11 = r8
        L9b:
            com.streamsync.app.data.model.MediaItem r11 = (com.streamsync.app.data.model.MediaItem) r11
        L9d:
            if (r11 != 0) goto La1
            goto Lb0
        La1:
            r12.L$0 = r8
            r0 = 3
            r12.label = r0
            java.lang.Object r11 = r9.fillEpisodeFromParent(r11, r12)
            if (r11 != r7) goto Lad
            return r7
        Lad:
            r8 = r11
            com.streamsync.app.data.model.MediaItem r8 = (com.streamsync.app.data.model.MediaItem) r8
        Lb0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel.resolveDetail(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fillEpisodeFromParent(com.streamsync.app.data.model.MediaItem r49, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.MediaItem> r50) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel.fillEpisodeFromParent(com.streamsync.app.data.model.MediaItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String episodeDisplayTitle(MediaItem item, MediaItem parent) {
        Object element$iv;
        String title = parent.getTitle();
        Integer season = item.getSeason();
        Integer episode = item.getEpisode();
        String title2 = item.getTitle();
        Iterable $this$firstOrNull$iv = parent.getVideos();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                SeriesVideo it2 = (SeriesVideo) element$iv;
                if (Intrinsics.areEqual(it2.getSeason(), item.getSeason()) && Intrinsics.areEqual(it2.getEpisode(), item.getEpisode())) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        SeriesVideo seriesVideo = (SeriesVideo) element$iv;
        return SeasonLabelsKt.episodePageTitle(title, season, episode, title2, seriesVideo != null ? seriesVideo.getTitle() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveSources(boolean r13, com.streamsync.app.data.model.MediaItem r14, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.StreamSource>> r15) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailViewModel.resolveSources(boolean, com.streamsync.app.data.model.MediaItem, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
