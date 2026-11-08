package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.AppCacheCleaner;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CacheSettingsScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.settings.CacheSettingsScreenKt$CacheSettingsScreen$1$1", f = "CacheSettingsScreen.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes17.dex */
public final class CacheSettingsScreenKt$CacheSettingsScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<AppCacheCleaner.CacheReport> $report$delegate;
    Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CacheSettingsScreenKt$CacheSettingsScreen$1$1(Context context, MutableState<AppCacheCleaner.CacheReport> mutableState, Continuation<? super CacheSettingsScreenKt$CacheSettingsScreen$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$report$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CacheSettingsScreenKt$CacheSettingsScreen$1$1(this.$context, this.$report$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CacheSettingsScreenKt$CacheSettingsScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CacheSettingsScreen.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/streamsync/app/util/AppCacheCleaner$CacheReport;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    @DebugMetadata(c = "com.streamsync.app.ui.settings.CacheSettingsScreenKt$CacheSettingsScreen$1$1$1", f = "CacheSettingsScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.streamsync.app.ui.settings.CacheSettingsScreenKt$CacheSettingsScreen$1$1$1, reason: invalid class name */
    /* loaded from: classes17.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AppCacheCleaner.CacheReport>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AppCacheCleaner.CacheReport> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return AppCacheCleaner.INSTANCE.inspect(this.$context);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableState<AppCacheCleaner.CacheReport> mutableState;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableState = this.$report$delegate;
                this.L$0 = mutableState;
                this.label = 1;
                Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.$context, null), this);
                if (withContext != coroutine_suspended) {
                    $result = withContext;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                MutableState<AppCacheCleaner.CacheReport> mutableState2 = (MutableState) this.L$0;
                ResultKt.throwOnFailure($result);
                mutableState = mutableState2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableState.setValue((AppCacheCleaner.CacheReport) $result);
        return Unit.INSTANCE;
    }
}
