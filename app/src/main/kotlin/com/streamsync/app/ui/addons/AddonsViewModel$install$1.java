package com.streamsync.app.ui.addons;

import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AddonsScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.addons.AddonsViewModel$install$1", f = "AddonsScreen.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes20.dex */
public final class AddonsViewModel$install$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $manifestUrl;
    int label;
    final /* synthetic */ AddonsViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddonsViewModel$install$1(AddonsViewModel addonsViewModel, String str, Continuation<? super AddonsViewModel$install$1> continuation) {
        super(2, continuation);
        this.this$0 = addonsViewModel;
        this.$manifestUrl = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AddonsViewModel$install$1(this.this$0, this.$manifestUrl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AddonsViewModel$install$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        AddonsViewModel$install$1 addonsViewModel$install$1;
        Object obj;
        MutableStateFlow mutableStateFlow3;
        MutableStateFlow mutableStateFlow4;
        String explain;
        MutableStateFlow mutableStateFlow5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableStateFlow = this.this$0._installing;
                mutableStateFlow.setValue(Boxing.boxBoolean(true));
                mutableStateFlow2 = this.this$0._error;
                mutableStateFlow2.setValue(null);
                this.label = 1;
                Object m6607installgIAlus = this.this$0.repo.m6607installgIAlus(this.$manifestUrl, this);
                if (m6607installgIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
                addonsViewModel$install$1 = this;
                obj = m6607installgIAlus;
                break;
            case 1:
                addonsViewModel$install$1 = this;
                ResultKt.throwOnFailure($result);
                obj = ((Result) $result).getValue();
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AddonsViewModel addonsViewModel = addonsViewModel$install$1.this$0;
        if (Result.m6724isSuccessimpl(obj)) {
            mutableStateFlow5 = addonsViewModel._lastInstalledUrl;
            mutableStateFlow5.setValue(((AddonManifest) obj).getBaseUrl());
        }
        AddonsViewModel addonsViewModel2 = addonsViewModel$install$1.this$0;
        String str = addonsViewModel$install$1.$manifestUrl;
        Throwable it = Result.m6720exceptionOrNullimpl(obj);
        if (it != null) {
            mutableStateFlow4 = addonsViewModel2._error;
            explain = addonsViewModel2.explain(it, str);
            mutableStateFlow4.setValue(explain);
        }
        mutableStateFlow3 = addonsViewModel$install$1.this$0._installing;
        mutableStateFlow3.setValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }
}
