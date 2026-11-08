package com.streamsync.app.ui.player;

import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$load$1", f = "PlayerScreen.kt", i = {0, 1, 2, 3}, l = {492, 498, 500, 587}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch", "source"}, s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes23.dex */
public final class PlayerViewModel$load$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $loadStartedAtMs;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$load$1(PlayerViewModel playerViewModel, long j, Continuation<? super PlayerViewModel$load$1> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$loadStartedAtMs = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerViewModel$load$1 playerViewModel$load$1 = new PlayerViewModel$load$1(this.this$0, this.$loadStartedAtMs, continuation);
        playerViewModel$load$1.L$0 = obj;
        return playerViewModel$load$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerViewModel$load$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x02d5: MOVE (r1 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY] A[D('this' com.streamsync.app.ui.player.PlayerViewModel$load$1)]), block:B:114:0x02d5 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x02d6: MOVE (r2 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]), block:B:114:0x02d5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 800
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$load$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PlayerScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$load$1$1", f = "PlayerScreen.kt", i = {}, l = {555}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.player.PlayerViewModel$load$1$1, reason: invalid class name */
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
                java.lang.String r6 = "Hoi sau khi phat ra kieu noi dung: "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r3 = r5.append(r3)
                java.lang.String r3 = r3.toString()
                r5 = 2
                com.streamsync.app.util.FileLog.d$default(r2, r3, r4, r5, r4)
                kotlin.Unit r2 = kotlin.Unit.INSTANCE
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel$load$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
