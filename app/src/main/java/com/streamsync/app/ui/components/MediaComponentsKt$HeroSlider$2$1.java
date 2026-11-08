package com.streamsync.app.ui.components;

import androidx.compose.foundation.pager.PagerState;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.UiSettings;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MediaComponents.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.components.MediaComponentsKt$HeroSlider$2$1", f = "MediaComponents.kt", i = {}, l = {561}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
final class MediaComponentsKt$HeroSlider$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $autoSlide;
    final /* synthetic */ List<MediaItem> $items;
    final /* synthetic */ LifecycleOwner $lifecycleOwner;
    final /* synthetic */ PagerState $pagerState;
    final /* synthetic */ int $slideIntervalMs;
    final /* synthetic */ UiSettings $ui;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaComponentsKt$HeroSlider$2$1(boolean z, UiSettings uiSettings, List<MediaItem> list, LifecycleOwner lifecycleOwner, int i, PagerState pagerState, Continuation<? super MediaComponentsKt$HeroSlider$2$1> continuation) {
        super(2, continuation);
        this.$autoSlide = z;
        this.$ui = uiSettings;
        this.$items = list;
        this.$lifecycleOwner = lifecycleOwner;
        this.$slideIntervalMs = i;
        this.$pagerState = pagerState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaComponentsKt$HeroSlider$2$1(this.$autoSlide, this.$ui, this.$items, this.$lifecycleOwner, this.$slideIntervalMs, this.$pagerState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaComponentsKt$HeroSlider$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!this.$autoSlide || !this.$ui.getMotionEnabled() || this.$items.size() < 2) {
                    return Unit.INSTANCE;
                }
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(this.$lifecycleOwner, Lifecycle.State.RESUMED, new AnonymousClass1(this.$slideIntervalMs, this.$pagerState, this.$items, null), this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MediaComponents.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.components.MediaComponentsKt$HeroSlider$2$1$1", f = "MediaComponents.kt", i = {0, 1}, l = {563, 568}, m = "invokeSuspend", n = {"$this$repeatOnLifecycle", "$this$repeatOnLifecycle"}, s = {"L$0", "L$0"})
    /* renamed from: com.streamsync.app.ui.components.MediaComponentsKt$HeroSlider$2$1$1, reason: invalid class name */
    /* loaded from: classes15.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<MediaItem> $items;
        final /* synthetic */ PagerState $pagerState;
        final /* synthetic */ int $slideIntervalMs;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, PagerState pagerState, List<MediaItem> list, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$slideIntervalMs = i;
            this.$pagerState = pagerState;
            this.$items = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$slideIntervalMs, this.$pagerState, this.$items, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0084  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006f -> B:9:0x0071). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0084 -> B:11:0x0031). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                r2 = 1
                switch(r1) {
                    case 0: goto L29;
                    case 1: goto L1f;
                    case 2: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L12:
                r1 = r12
                r3 = 0
                java.lang.Object r4 = r1.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L1c
                goto L71
            L1c:
                r3 = move-exception
                r10 = r4
                goto L79
            L1f:
                r1 = r12
                java.lang.Object r3 = r1.L$0
                kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
                kotlin.ResultKt.throwOnFailure(r13)
                r10 = r3
                goto L44
            L29:
                kotlin.ResultKt.throwOnFailure(r13)
                r1 = r12
                java.lang.Object r3 = r1.L$0
                kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            L31:
                int r4 = r1.$slideIntervalMs
                long r4 = (long) r4
                r6 = r1
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r1.L$0 = r3
                r1.label = r2
                java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r6)
                if (r4 != r0) goto L43
                return r0
            L43:
                r10 = r3
            L44:
                androidx.compose.foundation.pager.PagerState r3 = r1.$pagerState
                boolean r3 = r3.isScrollInProgress()
                if (r3 != 0) goto L84
                androidx.compose.foundation.pager.PagerState r3 = r1.$pagerState
                java.util.List<com.streamsync.app.data.model.MediaItem> r4 = r1.$items
                kotlin.Result$Companion r5 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L78
                r11 = 0
                int r5 = r3.getCurrentPage()     // Catch: java.lang.Throwable -> L78
                int r5 = r5 + r2
                int r4 = r4.size()     // Catch: java.lang.Throwable -> L78
                int r4 = r5 % r4
                r1.L$0 = r10     // Catch: java.lang.Throwable -> L78
                r5 = 2
                r1.label = r5     // Catch: java.lang.Throwable -> L78
                r5 = 0
                r6 = 0
                r8 = 6
                r9 = 0
                r7 = r1
                java.lang.Object r3 = androidx.compose.foundation.pager.PagerState.animateScrollToPage$default(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L78
                if (r3 != r0) goto L6f
                return r0
            L6f:
                r4 = r10
                r3 = r11
            L71:
                kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1c
                kotlin.Result.m6717constructorimpl(r3)     // Catch: java.lang.Throwable -> L1c
                r3 = r4
                goto L31
            L78:
                r3 = move-exception
            L79:
                kotlin.Result$Companion r4 = kotlin.Result.INSTANCE
                java.lang.Object r3 = kotlin.ResultKt.createFailure(r3)
                kotlin.Result.m6717constructorimpl(r3)
                r3 = r10
                goto L31
            L84:
                r3 = r10
                goto L31
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.MediaComponentsKt$HeroSlider$2$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
