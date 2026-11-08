package com.streamsync.app.data.repository;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.streamsync.app.data.local.AppDatabase;
import com.streamsync.app.data.local.LibraryDao;
import com.streamsync.app.data.local.LibraryItemEntity;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: LibraryRepository.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00130\u000fJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J\f\u0010\u001a\u001a\u00020\u0011*\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/streamsync/app/data/repository/LibraryRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dao", "Lcom/streamsync/app/data/local/LibraryDao;", "gson", "Lcom/google/gson/Gson;", "isSaved", "", "mediaId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/streamsync/app/data/model/MediaItem;", "observeIds", "", "remove", "", "save", "item", "(Lcom/streamsync/app/data/model/MediaItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggle", "toMediaItem", "Lcom/streamsync/app/data/local/LibraryItemEntity;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class LibraryRepository {
    public static final int $stable = 8;
    private final LibraryDao dao;
    private final Gson gson;

    public LibraryRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.dao = AppDatabase.INSTANCE.get(context).libraryDao();
        this.gson = new Gson();
    }

    public final Flow<List<MediaItem>> observeAll() {
        final Flow $this$map$iv = this.dao.observeAll();
        return (Flow) new Flow<List<? extends MediaItem>>() { // from class: com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            /* renamed from: com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes21.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ LibraryRepository this$0;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2", f = "LibraryRepository.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                /* renamed from: com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, LibraryRepository libraryRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = libraryRepository;
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
                        boolean r0 = r15 instanceof com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r15
                        com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2$1 r0 = (com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r15 = r0.label
                        int r15 = r15 - r2
                        r0.label = r15
                        goto L19
                    L14:
                        com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2$1 r0 = new com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1$2$1
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
                        com.streamsync.app.data.local.LibraryItemEntity r10 = (com.streamsync.app.data.local.LibraryItemEntity) r10
                        r11 = 0
                        com.streamsync.app.data.repository.LibraryRepository r12 = r2.this$0
                        com.streamsync.app.data.model.MediaItem r10 = com.streamsync.app.data.repository.LibraryRepository.access$toMediaItem(r12, r10)
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
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.LibraryRepository$observeAll$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends MediaItem>> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    public final Flow<Set<String>> observeIds() {
        final Flow $this$map$iv = this.dao.observeIds();
        return (Flow) new Flow<Set<? extends String>>() { // from class: com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            /* renamed from: com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes21.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2", f = "LibraryRepository.kt", i = {}, l = {219}, m = "emit", n = {}, s = {})
                /* renamed from: com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r8
                        com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2$1 r0 = (com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r8 = r0.label
                        int r8 = r8 - r2
                        r0.label = r8
                        goto L19
                    L14:
                        com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2$1 r0 = new com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L19:
                        r8 = r0
                        java.lang.Object r0 = r8.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r8.label
                        switch(r2) {
                            case 0: goto L32;
                            case 1: goto L2d;
                            default: goto L25;
                        }
                    L25:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L2d:
                        r7 = 0
                        kotlin.ResultKt.throwOnFailure(r0)
                        goto L51
                    L32:
                        kotlin.ResultKt.throwOnFailure(r0)
                        r2 = r6
                        kotlinx.coroutines.flow.FlowCollector r2 = r2.$this_unsafeFlow
                        r3 = 0
                        r4 = r8
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        java.util.List r7 = (java.util.List) r7
                        r4 = 0
                        r5 = r7
                        java.lang.Iterable r5 = (java.lang.Iterable) r5
                        java.util.Set r7 = kotlin.collections.CollectionsKt.toSet(r5)
                        r4 = 1
                        r8.label = r4
                        java.lang.Object r7 = r2.emit(r7, r8)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r7 = r3
                    L51:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.LibraryRepository$observeIds$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super Set<? extends String>> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isSaved(java.lang.String r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.streamsync.app.data.repository.LibraryRepository$isSaved$1
            if (r0 == 0) goto L14
            r0 = r7
            com.streamsync.app.data.repository.LibraryRepository$isSaved$1 r0 = (com.streamsync.app.data.repository.LibraryRepository$isSaved$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            com.streamsync.app.data.repository.LibraryRepository$isSaved$1 r0 = new com.streamsync.app.data.repository.LibraryRepository$isSaved$1
            r0.<init>(r5, r7)
        L19:
            r7 = r0
            java.lang.Object r0 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r3 = 1
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2e:
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r0
            goto L42
        L33:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r5
            com.streamsync.app.data.local.LibraryDao r4 = r2.dao
            r7.label = r3
            java.lang.Object r6 = r4.findId(r6, r7)
            if (r6 != r1) goto L42
            return r1
        L42:
            if (r6 == 0) goto L45
            goto L46
        L45:
            r3 = 0
        L46:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.LibraryRepository.isSaved(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object save(MediaItem item, Continuation<? super Unit> continuation) {
        LibraryDao libraryDao = this.dao;
        String id = item.getId();
        String title = item.getTitle();
        String posterUrl = item.getPosterUrl();
        String backgroundUrl = item.getBackgroundUrl();
        String description = item.getDescription();
        String name = item.getType().name();
        String releaseInfo = item.getReleaseInfo();
        String imdbRating = item.getImdbRating();
        String json = this.gson.toJson(item.getGenres());
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        Object upsert = libraryDao.upsert(new LibraryItemEntity(id, title, posterUrl, backgroundUrl, description, name, releaseInfo, imdbRating, json, item.getSeason(), item.getEpisode(), System.currentTimeMillis()), continuation);
        return upsert == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? upsert : Unit.INSTANCE;
    }

    public final Object remove(String mediaId, Continuation<? super Unit> continuation) {
        Object deleteById = this.dao.deleteById(mediaId, continuation);
        return deleteById == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? deleteById : Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toggle(com.streamsync.app.data.model.MediaItem r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.streamsync.app.data.repository.LibraryRepository$toggle$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.data.repository.LibraryRepository$toggle$1 r0 = (com.streamsync.app.data.repository.LibraryRepository$toggle$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.data.repository.LibraryRepository$toggle$1 r0 = new com.streamsync.app.data.repository.LibraryRepository$toggle$1
            r0.<init>(r6, r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            r3 = 1
            switch(r2) {
                case 0: goto L43;
                case 1: goto L36;
                case 2: goto L32;
                case 3: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L83
        L32:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L73
        L36:
            java.lang.Object r7 = r8.L$1
            com.streamsync.app.data.model.MediaItem r7 = (com.streamsync.app.data.model.MediaItem) r7
            java.lang.Object r2 = r8.L$0
            com.streamsync.app.data.repository.LibraryRepository r2 = (com.streamsync.app.data.repository.LibraryRepository) r2
            kotlin.ResultKt.throwOnFailure(r0)
            r4 = r0
            goto L58
        L43:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r6
            java.lang.String r4 = r7.getId()
            r8.L$0 = r2
            r8.L$1 = r7
            r8.label = r3
            java.lang.Object r4 = r2.isSaved(r4, r8)
            if (r4 != r1) goto L58
            return r1
        L58:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            r5 = 0
            if (r4 == 0) goto L75
            java.lang.String r3 = r7.getId()
            r8.L$0 = r5
            r8.L$1 = r5
            r4 = 2
            r8.label = r4
            java.lang.Object r7 = r2.remove(r3, r8)
            if (r7 != r1) goto L73
            return r1
        L73:
            r3 = 0
            goto L84
        L75:
            r8.L$0 = r5
            r8.L$1 = r5
            r4 = 3
            r8.label = r4
            java.lang.Object r7 = r2.save(r7, r8)
            if (r7 != r1) goto L83
            return r1
        L83:
        L84:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.LibraryRepository.toggle(com.streamsync.app.data.model.MediaItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.streamsync.app.data.model.MediaItem toMediaItem(com.streamsync.app.data.local.LibraryItemEntity r39) {
        /*
            r38 = this;
            java.lang.String r2 = r39.getMediaId()
            java.lang.String r3 = r39.getTitle()
            java.lang.String r4 = r39.getPosterUrl()
            java.lang.String r5 = r39.getDescription()
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L23
            r0 = r39
            r1 = 0
            java.lang.String r6 = r0.getType()     // Catch: java.lang.Throwable -> L23
            com.streamsync.app.data.model.MediaType r6 = com.streamsync.app.data.model.MediaType.valueOf(r6)     // Catch: java.lang.Throwable -> L23
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r6)     // Catch: java.lang.Throwable -> L23
            goto L2e
        L23:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r0)
        L2e:
            com.streamsync.app.data.model.MediaType r1 = com.streamsync.app.data.model.MediaType.MOVIE
            boolean r6 = kotlin.Result.m6723isFailureimpl(r0)
            if (r6 == 0) goto L37
            r0 = r1
        L37:
            r6 = r0
            com.streamsync.app.data.model.MediaType r6 = (com.streamsync.app.data.model.MediaType) r6
            java.lang.String r9 = r39.getBackgroundUrl()
            java.lang.String r10 = r39.getReleaseInfo()
            java.lang.String r11 = r39.getImdbRating()
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L74
            r0 = r39
            r1 = 0
            r15 = r38
            com.google.gson.Gson r7 = r15.gson     // Catch: java.lang.Throwable -> L72
            java.lang.String r8 = r0.getGenresJson()     // Catch: java.lang.Throwable -> L72
            java.lang.Class<java.lang.String[]> r12 = java.lang.String[].class
            java.lang.Object r7 = r7.fromJson(r8, r12)     // Catch: java.lang.Throwable -> L72
            java.lang.String[] r7 = (java.lang.String[]) r7     // Catch: java.lang.Throwable -> L72
            if (r7 == 0) goto L66
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch: java.lang.Throwable -> L72
            java.util.List r7 = kotlin.collections.ArraysKt.toList(r7)     // Catch: java.lang.Throwable -> L72
            goto L67
        L66:
            r7 = 0
        L67:
            if (r7 != 0) goto L6d
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()     // Catch: java.lang.Throwable -> L72
        L6d:
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r7)     // Catch: java.lang.Throwable -> L72
            goto L81
        L72:
            r0 = move-exception
            goto L77
        L74:
            r0 = move-exception
            r15 = r38
        L77:
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m6717constructorimpl(r0)
        L81:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            boolean r7 = kotlin.Result.m6723isFailureimpl(r0)
            if (r7 == 0) goto L8c
            r0 = r1
        L8c:
            r12 = r0
            java.util.List r12 = (java.util.List) r12
            java.lang.Integer r32 = r39.getSeason()
            java.lang.Integer r33 = r39.getEpisode()
            com.streamsync.app.data.model.MediaItem r0 = new com.streamsync.app.data.model.MediaItem
            r1 = r0
            r7 = 0
            r8 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            r15 = r16
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r34 = 0
            r35 = 1073739872(0x3ffff860, float:1.9997673)
            r36 = 1
            r37 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.LibraryRepository.toMediaItem(com.streamsync.app.data.local.LibraryItemEntity):com.streamsync.app.data.model.MediaItem");
    }
}
