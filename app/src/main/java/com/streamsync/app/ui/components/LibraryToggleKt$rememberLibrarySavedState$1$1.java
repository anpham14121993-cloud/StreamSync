package com.streamsync.app.ui.components;

import androidx.compose.runtime.MutableState;
import com.streamsync.app.data.repository.LibraryRepository;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LibraryToggle.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$1$1", f = "LibraryToggle.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes15.dex */
public final class LibraryToggleKt$rememberLibrarySavedState$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mediaId;
    final /* synthetic */ LibraryRepository $repo;
    final /* synthetic */ MutableState<Boolean> $saved$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryToggleKt$rememberLibrarySavedState$1$1(LibraryRepository libraryRepository, String str, MutableState<Boolean> mutableState, Continuation<? super LibraryToggleKt$rememberLibrarySavedState$1$1> continuation) {
        super(2, continuation);
        this.$repo = libraryRepository;
        this.$mediaId = str;
        this.$saved$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LibraryToggleKt$rememberLibrarySavedState$1$1 libraryToggleKt$rememberLibrarySavedState$1$1 = new LibraryToggleKt$rememberLibrarySavedState$1$1(this.$repo, this.$mediaId, this.$saved$delegate, continuation);
        libraryToggleKt$rememberLibrarySavedState$1$1.L$0 = obj;
        return libraryToggleKt$rememberLibrarySavedState$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryToggleKt$rememberLibrarySavedState$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0075  */
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
            switch(r1) {
                case 0: goto L21;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L11:
            r0 = r8
            r1 = 0
            java.lang.Object r2 = r0.L$0
            androidx.compose.runtime.MutableState r2 = (androidx.compose.runtime.MutableState) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1f
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r9
            goto L40
        L1f:
            r1 = move-exception
            goto L5b
        L21:
            kotlin.ResultKt.throwOnFailure(r9)
            r1 = r8
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            androidx.compose.runtime.MutableState<java.lang.Boolean> r3 = r1.$saved$delegate
            com.streamsync.app.data.repository.LibraryRepository r4 = r1.$repo
            java.lang.String r5 = r1.$mediaId
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L56
            r2 = 0
            r1.L$0 = r3     // Catch: java.lang.Throwable -> L56
            r6 = 1
            r1.label = r6     // Catch: java.lang.Throwable -> L56
            java.lang.Object r4 = r4.isSaved(r5, r1)     // Catch: java.lang.Throwable -> L56
            if (r4 != r0) goto L3e
            return r0
        L3e:
            r0 = r9
            r9 = r4
        L40:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L4f
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L4f
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)     // Catch: java.lang.Throwable -> L4f
            java.lang.Object r9 = kotlin.Result.m6717constructorimpl(r9)     // Catch: java.lang.Throwable -> L4f
            goto L6a
        L4f:
            r9 = move-exception
            r2 = r3
            r7 = r1
            r1 = r9
            r9 = r0
            r0 = r7
            goto L5b
        L56:
            r0 = move-exception
            r2 = r3
            r7 = r1
            r1 = r0
            r0 = r7
        L5b:
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
            r3 = r2
            r7 = r0
            r0 = r9
            r9 = r1
            r1 = r7
        L6a:
            r2 = 0
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            boolean r4 = kotlin.Result.m6723isFailureimpl(r9)
            if (r4 == 0) goto L76
            r9 = r2
        L76:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            com.streamsync.app.ui.components.LibraryToggleKt.access$rememberLibrarySavedState$lambda$2(r3, r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
