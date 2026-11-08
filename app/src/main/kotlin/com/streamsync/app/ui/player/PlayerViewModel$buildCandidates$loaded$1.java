package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$buildCandidates$loaded$1", f = "PlayerScreen.kt", i = {}, l = {1551}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$buildCandidates$loaded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends LocalAutoSyncEngine.SubtitleTrackCandidate>>, Object> {
    final /* synthetic */ Semaphore $gate;
    final /* synthetic */ List<SubtitleTrack> $others;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$buildCandidates$loaded$1(List<SubtitleTrack> list, PlayerViewModel playerViewModel, Semaphore semaphore, Continuation<? super PlayerViewModel$buildCandidates$loaded$1> continuation) {
        super(2, continuation);
        this.$others = list;
        this.this$0 = playerViewModel;
        this.$gate = semaphore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$buildCandidates$loaded$1(this.$others, this.this$0, this.$gate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends LocalAutoSyncEngine.SubtitleTrackCandidate>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<LocalAutoSyncEngine.SubtitleTrackCandidate>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<LocalAutoSyncEngine.SubtitleTrackCandidate>> continuation) {
        return ((PlayerViewModel$buildCandidates$loaded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PlayerScreen.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$buildCandidates$loaded$1$1", f = "PlayerScreen.kt", i = {}, l = {1568}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.player.PlayerViewModel$buildCandidates$loaded$1$1, reason: invalid class name */
    /* loaded from: classes23.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends LocalAutoSyncEngine.SubtitleTrackCandidate>>, Object> {
        final /* synthetic */ Semaphore $gate;
        final /* synthetic */ List<SubtitleTrack> $others;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PlayerViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<SubtitleTrack> list, PlayerViewModel playerViewModel, Semaphore semaphore, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$others = list;
            this.this$0 = playerViewModel;
            this.$gate = semaphore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$others, this.this$0, this.$gate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends LocalAutoSyncEngine.SubtitleTrackCandidate>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<LocalAutoSyncEngine.SubtitleTrackCandidate>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<LocalAutoSyncEngine.SubtitleTrackCandidate>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Deferred async$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    Iterable $this$map$iv = this.$others;
                    PlayerViewModel playerViewModel = this.this$0;
                    Semaphore semaphore = this.$gate;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        SubtitleTrack track = (SubtitleTrack) item$iv$iv;
                        async$default = BuildersKt__Builders_commonKt.async$default($this$coroutineScope, null, null, new PlayerViewModel$buildCandidates$loaded$1$1$1$1(track, playerViewModel, semaphore, null), 3, null);
                        destination$iv$iv.add(async$default);
                    }
                    this.label = 1;
                    Object awaitAll = AwaitKt.awaitAll((List) destination$iv$iv, this);
                    return awaitAll == coroutine_suspended ? coroutine_suspended : awaitAll;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object coroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.$others, this.this$0, this.$gate, null), this);
                return coroutineScope == coroutine_suspended ? coroutine_suspended : coroutineScope;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
