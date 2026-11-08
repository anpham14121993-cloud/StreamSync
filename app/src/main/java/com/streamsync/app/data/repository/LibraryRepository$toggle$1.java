package com.streamsync.app.data.repository;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LibraryRepository.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.data.repository.LibraryRepository", f = "LibraryRepository.kt", i = {0, 0}, l = {LockFreeTaskQueueCore.CLOSED_SHIFT, 62, 65}, m = "toggle", n = {"this", "item"}, s = {"L$0", "L$1"})
/* loaded from: classes21.dex */
public final class LibraryRepository$toggle$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LibraryRepository this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryRepository$toggle$1(LibraryRepository libraryRepository, Continuation<? super LibraryRepository$toggle$1> continuation) {
        super(continuation);
        this.this$0 = libraryRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.toggle(null, this);
    }
}
