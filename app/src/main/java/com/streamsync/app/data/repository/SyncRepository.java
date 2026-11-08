package com.streamsync.app.data.repository;

import android.content.Context;
import android.provider.Settings;
import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.data.local.AppDatabase;
import com.streamsync.app.data.local.WatchProgressDao;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.data.remote.RetrofitClient;
import com.streamsync.app.data.remote.SyncApi;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.SyncServerStore;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: SyncRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001a0\u0019J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@¢\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010!J2\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0086@¢\u0006\u0002\u0010'R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013¨\u0006("}, d2 = {"Lcom/streamsync/app/data/repository/SyncRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "api", "Lcom/streamsync/app/data/remote/SyncApi;", "getApi", "()Lcom/streamsync/app/data/remote/SyncApi;", "api$delegate", "Lkotlin/Lazy;", "appContext", "kotlin.jvm.PlatformType", "dao", "Lcom/streamsync/app/data/local/WatchProgressDao;", "deviceId", "", "isServerConfigured", "", "()Z", "getLocalProgress", "Lcom/streamsync/app/data/model/WatchProgress;", "mediaId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeProgress", "Lkotlinx/coroutines/flow/Flow;", "", "pullRemote", "", "sinceEpochMs", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pushPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordProgress", "positionMs", "durationMs", "stream", "Lcom/streamsync/app/data/model/StreamSource;", "(Ljava/lang/String;JJLcom/streamsync/app/data/model/StreamSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class SyncRepository {
    public static final int $stable = 8;

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private final Lazy api;
    private final Context appContext;
    private final WatchProgressDao dao;
    private final String deviceId;

    public SyncRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.dao = AppDatabase.INSTANCE.get(context).watchProgressDao();
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        this.deviceId = string == null ? "unknown-device" : string;
        this.api = LazyKt.lazy(new Function0<SyncApi>() { // from class: com.streamsync.app.data.repository.SyncRepository$api$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SyncApi invoke() {
                Context context2;
                RetrofitClient retrofitClient = RetrofitClient.INSTANCE;
                context2 = SyncRepository.this.appContext;
                Intrinsics.checkNotNullExpressionValue(context2, "access$getAppContext$p(...)");
                return retrofitClient.syncApi(new SyncServerStore(context2).getServerUrl());
            }
        });
    }

    private final SyncApi getApi() {
        return (SyncApi) this.api.getValue();
    }

    public final boolean isServerConfigured() {
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        return new SyncServerStore(appContext).isConfigured();
    }

    public final Flow<List<WatchProgress>> observeProgress() {
        final Flow $this$map$iv = this.dao.observeAll();
        return FlowKt.flowOn(new Flow<List<? extends WatchProgress>>() { // from class: com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            /* renamed from: com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes21.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2", f = "SyncRepository.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                /* renamed from: com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r12, kotlin.coroutines.Continuation r13) {
                    /*
                        r11 = this;
                        boolean r0 = r13 instanceof com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r13
                        com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2$1 r0 = (com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r13 = r0.label
                        int r13 = r13 - r2
                        r0.label = r13
                        goto L19
                    L14:
                        com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2$1 r0 = new com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1$2$1
                        r0.<init>(r13)
                    L19:
                        r13 = r0
                        java.lang.Object r0 = r13.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r13.label
                        switch(r2) {
                            case 0: goto L32;
                            case 1: goto L2d;
                            default: goto L25;
                        }
                    L25:
                        java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                        java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                        r12.<init>(r13)
                        throw r12
                    L2d:
                        r12 = 0
                        kotlin.ResultKt.throwOnFailure(r0)
                        goto L7b
                    L32:
                        kotlin.ResultKt.throwOnFailure(r0)
                        r2 = r11
                        kotlinx.coroutines.flow.FlowCollector r2 = r2.$this_unsafeFlow
                        r3 = 0
                        r4 = r13
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        java.util.List r12 = (java.util.List) r12
                        r4 = 0
                        java.lang.Iterable r12 = (java.lang.Iterable) r12
                        r5 = 0
                        java.util.ArrayList r6 = new java.util.ArrayList
                        r7 = 10
                        int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r12, r7)
                        r6.<init>(r7)
                        java.util.Collection r6 = (java.util.Collection) r6
                        r7 = 0
                        java.util.Iterator r8 = r12.iterator()
                    L54:
                        boolean r12 = r8.hasNext()
                        if (r12 == 0) goto L6a
                        java.lang.Object r12 = r8.next()
                        r9 = r12
                        com.streamsync.app.data.local.WatchProgressEntity r9 = (com.streamsync.app.data.local.WatchProgressEntity) r9
                        r10 = 0
                        com.streamsync.app.data.model.WatchProgress r9 = com.streamsync.app.data.repository.SyncRepositoryKt.access$toModel(r9)
                        r6.add(r9)
                        goto L54
                    L6a:
                        r12 = r6
                        java.util.List r12 = (java.util.List) r12
                        r4 = 1
                        r13.label = r4
                        java.lang.Object r12 = r2.emit(r12, r13)
                        if (r12 != r1) goto L7a
                        return r1
                    L7a:
                        r12 = r3
                    L7b:
                        kotlin.Unit r12 = kotlin.Unit.INSTANCE
                        return r12
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.SyncRepository$observeProgress$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends WatchProgress>> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        }, Dispatchers.getIO());
    }

    public static /* synthetic */ Object recordProgress$default(SyncRepository syncRepository, String str, long j, long j2, StreamSource streamSource, Continuation continuation, int i, Object obj) {
        StreamSource streamSource2;
        if ((i & 8) == 0) {
            streamSource2 = streamSource;
        } else {
            streamSource2 = null;
        }
        return syncRepository.recordProgress(str, j, j2, streamSource2, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object recordProgress(java.lang.String r26, long r27, long r29, com.streamsync.app.data.model.StreamSource r31, kotlin.coroutines.Continuation<? super kotlin.Unit> r32) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.SyncRepository.recordProgress(java.lang.String, long, long, com.streamsync.app.data.model.StreamSource, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004b A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLocalProgress(java.lang.String r6, kotlin.coroutines.Continuation<? super com.streamsync.app.data.model.WatchProgress> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.streamsync.app.data.repository.SyncRepository$getLocalProgress$1
            if (r0 == 0) goto L14
            r0 = r7
            com.streamsync.app.data.repository.SyncRepository$getLocalProgress$1 r0 = (com.streamsync.app.data.repository.SyncRepository$getLocalProgress$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.streamsync.app.data.repository.SyncRepository$getLocalProgress$1 r0 = new com.streamsync.app.data.repository.SyncRepository$getLocalProgress$1
            r0.<init>(r5, r7)
        L19:
            r7 = r0
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            switch(r2) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r0
            goto L42
        L32:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r5
            com.streamsync.app.data.local.WatchProgressDao r3 = r2.dao
            r4 = 1
            r7.label = r4
            java.lang.Object r6 = r3.get(r6, r7)
            if (r6 != r1) goto L42
            return r1
        L42:
            com.streamsync.app.data.local.WatchProgressEntity r6 = (com.streamsync.app.data.local.WatchProgressEntity) r6
            if (r6 == 0) goto L4b
            com.streamsync.app.data.model.WatchProgress r6 = com.streamsync.app.data.repository.SyncRepositoryKt.access$toModel(r6)
            goto L4c
        L4b:
            r6 = 0
        L4c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.SyncRepository.getLocalProgress(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00b5 -> B:12:0x0074). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00d1 -> B:12:0x0074). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pushPending(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.SyncRepository.pushPending(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00eb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00d0 -> B:12:0x0098). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00ec -> B:12:0x0098). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pullRemote(long r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.SyncRepository.pullRemote(long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
