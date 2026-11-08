package com.streamsync.app.subtitle;

import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SubtitleTranslator.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.subtitle.SubtitleTranslator$translateAllInBackground$1", f = "SubtitleTranslator.kt", i = {}, l = {BufferCeiling.UI_RESERVE_MB}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
final class SubtitleTranslator$translateAllInBackground$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<SubtitleCue> $cues;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SubtitleTranslator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleTranslator$translateAllInBackground$1(List<SubtitleCue> list, SubtitleTranslator subtitleTranslator, Continuation<? super SubtitleTranslator$translateAllInBackground$1> continuation) {
        super(2, continuation);
        this.$cues = list;
        this.this$0 = subtitleTranslator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SubtitleTranslator$translateAllInBackground$1(this.$cues, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SubtitleTranslator$translateAllInBackground$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0062  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0051 -> B:7:0x0058). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            switch(r1) {
                case 0: goto L23;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L11:
            r1 = r9
            java.lang.Object r2 = r1.L$1
            com.streamsync.app.data.model.SubtitleCue r2 = (com.streamsync.app.data.model.SubtitleCue) r2
            java.lang.Object r3 = r1.L$0
            java.util.Iterator r3 = (java.util.Iterator) r3
            kotlin.ResultKt.throwOnFailure(r10)
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r10
            goto L58
        L23:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r9
            java.util.List<com.streamsync.app.data.model.SubtitleCue> r2 = r1.$cues
            java.util.Iterator r2 = r2.iterator()
            r3 = r2
        L2e:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L62
            java.lang.Object r2 = r3.next()
            com.streamsync.app.data.model.SubtitleCue r2 = (com.streamsync.app.data.model.SubtitleCue) r2
            com.streamsync.app.subtitle.SubtitleTranslator r4 = r1.this$0
            java.lang.String r5 = r2.getOriginalText()
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.L$0 = r3
            r1.L$1 = r2
            r7 = 1
            r1.label = r7
            java.lang.Object r4 = r4.translate(r5, r6)
            if (r4 != r0) goto L51
            return r0
        L51:
            r8 = r0
            r0 = r10
            r10 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r8
        L58:
            java.lang.String r10 = (java.lang.String) r10
            r3.setTranslatedText(r10)
            r10 = r0
            r0 = r1
            r1 = r2
            r3 = r4
            goto L2e
        L62:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.SubtitleTranslator$translateAllInBackground$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
