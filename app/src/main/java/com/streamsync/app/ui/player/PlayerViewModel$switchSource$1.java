package com.streamsync.app.ui.player;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.repository.AddonRepositoryKt;
import com.streamsync.app.data.repository.StreamSelectionStore;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$switchSource$1", f = "PlayerScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$switchSource$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $resumeAt;
    final /* synthetic */ StreamSource $source;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$switchSource$1(PlayerViewModel playerViewModel, StreamSource streamSource, long j, Continuation<? super PlayerViewModel$switchSource$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$source = streamSource;
        this.$resumeAt = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$switchSource$1(this.this$0, this.$source, this.$resumeAt, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$switchSource$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        Context context;
        MutableStateFlow mutableStateFlow3;
        MutableStateFlow mutableStateFlow4;
        MutableStateFlow mutableStateFlow5;
        MutableStateFlow mutableStateFlow6;
        MutableStateFlow mutableStateFlow7;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                mutableStateFlow = this.this$0._isBuffering;
                boolean z = true;
                mutableStateFlow.setValue(Boxing.boxBoolean(true));
                mutableStateFlow2 = this.this$0._playbackError;
                mutableStateFlow2.setValue(null);
                StreamSource resolved = AddonRepositoryKt.withMimeTypeFromHints(this.$source);
                StreamSelectionStore.INSTANCE.put(resolved);
                this.this$0.currentSource = resolved;
                StreamRequestHeaders.INSTANCE.set(resolved.getRequestHeaders());
                context = this.this$0.appContext;
                Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
                PlaybackPrewarmer.warm$default(context, resolved.getStreamUrl(), false, resolved.getRequestHeaders(), 4, null);
                this.this$0.resumePositionMs = this.$resumeAt;
                mutableStateFlow3 = this.this$0._streamUrl;
                mutableStateFlow3.setValue(resolved.getStreamUrl());
                mutableStateFlow4 = this.this$0._currentStreamSource;
                mutableStateFlow4.setValue(resolved);
                mutableStateFlow5 = this.this$0._streamMimeType;
                mutableStateFlow5.setValue(resolved.getMimeType());
                mutableStateFlow6 = this.this$0._reloadToken;
                mutableStateFlow7 = this.this$0._reloadToken;
                mutableStateFlow6.setValue(Boxing.boxInt(((Number) mutableStateFlow7.getValue()).intValue() + 1));
                String mimeType = resolved.getMimeType();
                if (mimeType != null && !StringsKt.isBlank(mimeType)) {
                    z = false;
                }
                if (z) {
                    BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this.this$0), null, null, new AnonymousClass1(this.this$0, this.$source, null), 3, null);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PlayerScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$switchSource$1$1", f = "PlayerScreen.kt", i = {}, l = {1142}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.player.PlayerViewModel$switchSource$1$1, reason: invalid class name */
    /* loaded from: classes23.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ StreamSource $source;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PlayerViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PlayerViewModel playerViewModel, StreamSource streamSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = playerViewModel;
            this.$source = streamSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$source, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                switch(r1) {
                    case 0: goto L1d;
                    case 1: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L12:
                r0 = r8
                r1 = 0
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1b
                r3 = r1
                r1 = r0
                r0 = r9
                goto L3b
            L1b:
                r1 = move-exception
                goto L4c
            L1d:
                kotlin.ResultKt.throwOnFailure(r9)
                r1 = r8
                java.lang.Object r3 = r1.L$0
                kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
                com.streamsync.app.ui.player.PlayerViewModel r4 = r1.this$0
                com.streamsync.app.data.model.StreamSource r5 = r1.$source
                kotlin.Result$Companion r3 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L48
                r3 = 0
                com.streamsync.app.data.repository.AddonRepository r4 = com.streamsync.app.ui.player.PlayerViewModel.access$getAddonRepo$p(r4)     // Catch: java.lang.Throwable -> L48
                r1.label = r2     // Catch: java.lang.Throwable -> L48
                java.lang.Object r4 = r4.probeStreamMimeType(r5, r1)     // Catch: java.lang.Throwable -> L48
                if (r4 != r0) goto L39
                return r0
            L39:
                r0 = r9
                r9 = r4
            L3b:
                com.streamsync.app.data.model.StreamSource r9 = (com.streamsync.app.data.model.StreamSource) r9     // Catch: java.lang.Throwable -> L42
                java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r9)     // Catch: java.lang.Throwable -> L42
                goto L5a
            L42:
                r9 = move-exception
                r7 = r1
                r1 = r9
                r9 = r0
                r0 = r7
                goto L4c
            L48:
                r0 = move-exception
                r7 = r1
                r1 = r0
                r0 = r7
            L4c:
                kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
                java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
                java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
                r7 = r0
                r0 = r9
                r9 = r1
                r1 = r7
            L5a:
                boolean r3 = kotlin.Result.m6723isFailureimpl(r9)
                r4 = 0
                if (r3 == 0) goto L62
                r9 = r4
            L62:
                com.streamsync.app.data.model.StreamSource r9 = (com.streamsync.app.data.model.StreamSource) r9
                if (r9 != 0) goto L69
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L69:
                java.lang.String r3 = r9.getMimeType()
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                if (r3 == 0) goto L79
                boolean r3 = kotlin.text.StringsKt.isBlank(r3)
                if (r3 == 0) goto L78
                goto L79
            L78:
                r2 = 0
            L79:
                if (r2 == 0) goto L7e
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            L7e:
                com.streamsync.app.data.repository.StreamSelectionStore r2 = com.streamsync.app.data.repository.StreamSelectionStore.INSTANCE
                r2.put(r9)
                com.streamsync.app.ui.player.PlayerViewModel r2 = r1.this$0
                kotlinx.coroutines.flow.MutableStateFlow r2 = com.streamsync.app.ui.player.PlayerViewModel.access$get_streamUrl$p(r2)
                java.lang.Object r2 = r2.getValue()
                java.lang.String r3 = r9.getStreamUrl()
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                if (r2 == 0) goto La4
                com.streamsync.app.ui.player.PlayerViewModel r2 = r1.this$0
                kotlinx.coroutines.flow.MutableStateFlow r2 = com.streamsync.app.ui.player.PlayerViewModel.access$get_streamMimeType$p(r2)
                java.lang.String r3 = r9.getMimeType()
                r2.setValue(r3)
            La4:
                com.streamsync.app.util.FileLog r2 = com.streamsync.app.util.FileLog.INSTANCE
                java.lang.String r3 = r9.getMimeType()
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "Hoi sau khi doi nguon ra kieu noi dung: "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r3 = r5.append(r3)
                java.lang.String r3 = r3.toString()
                r5 = 2
                com.streamsync.app.util.FileLog.d$default(r2, r3, r4, r5, r4)
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$switchSource$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
