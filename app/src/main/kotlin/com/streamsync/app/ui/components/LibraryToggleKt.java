package com.streamsync.app.ui.components;

import android.content.Context;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.profileinstaller.ProfileVerifier;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.repository.LibraryRepository;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LibraryToggle.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\n0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\n\u0010\u000f\u001a\u00020\tX\u008a\u008e\u0002"}, d2 = {"LibraryAddButton", "", "item", "Lcom/streamsync/app/data/model/MediaItem;", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/streamsync/app/data/model/MediaItem;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "rememberLibrarySavedState", "Lkotlin/Pair;", "", "Lkotlin/Function1;", "mediaId", "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Lkotlin/Pair;", "app_mobileDebug", "saved"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes15.dex */
public final class LibraryToggleKt {
    public static final Pair<Boolean, Function1<MediaItem, Unit>> rememberLibrarySavedState(String mediaId, Composer $composer, int $changed) {
        Object value$iv$iv$iv;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        $composer.startReplaceableGroup(1611912451);
        ComposerKt.sourceInformation($composer, "C(rememberLibrarySavedState)33@1239L7,34@1263L24,37@1436L43,38@1495L67,40@1592L81,40@1568L105,44@1702L314:LibraryToggle.kt#qvfdnh");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1611912451, $changed, -1, "com.streamsync.app.ui.components.rememberLibrarySavedState (LibraryToggle.kt:32)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        $composer.startReplaceableGroup(773894976);
        ComposerKt.sourceInformation($composer, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
        $composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation($composer, "CC(remember):Composables.kt#9igjgp");
        Object it$iv$iv$iv = $composer.rememberedValue();
        if (it$iv$iv$iv == Composer.INSTANCE.getEmpty()) {
            value$iv$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer));
            $composer.updateRememberedValue(value$iv$iv$iv);
        } else {
            value$iv$iv$iv = it$iv$iv$iv;
        }
        $composer.endReplaceableGroup();
        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv$iv;
        final CoroutineScope scope = wrapper$iv.getCoroutineScope();
        $composer.endReplaceableGroup();
        $composer.startReplaceableGroup(-991803534);
        ComposerKt.sourceInformation($composer, "CC(remember):LibraryToggle.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(mediaId)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        final MutableState saved$delegate = (MutableState) value$iv;
        $composer.endReplaceableGroup();
        $composer.startReplaceableGroup(-991801622);
        ComposerKt.sourceInformation($composer, "CC(remember):LibraryToggle.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(context);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            value$iv2 = new LibraryRepository(applicationContext);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        final LibraryRepository repo = (LibraryRepository) value$iv2;
        $composer.endReplaceableGroup();
        $composer.startReplaceableGroup(-991798504);
        ComposerKt.sourceInformation($composer, "CC(remember):LibraryToggle.kt#9igjgp");
        boolean invalid$iv3 = $composer.changed(saved$delegate) | $composer.changedInstance(repo) | (((($changed & 14) ^ 6) > 4 && $composer.changed(mediaId)) || ($changed & 6) == 4);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = new LibraryToggleKt$rememberLibrarySavedState$1$1(repo, mediaId, saved$delegate, null);
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        $composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(mediaId, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3, $composer, $changed & 14);
        Boolean valueOf = Boolean.valueOf(rememberLibrarySavedState$lambda$1(saved$delegate));
        $composer.startReplaceableGroup(-991794751);
        ComposerKt.sourceInformation($composer, "CC(remember):LibraryToggle.kt#9igjgp");
        boolean invalid$iv4 = $composer.changedInstance(scope) | $composer.changed(saved$delegate) | $composer.changedInstance(repo);
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            value$iv4 = new Function1<MediaItem, Unit>() { // from class: com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MediaItem mediaItem) {
                    invoke2(mediaItem);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: LibraryToggle.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                @DebugMetadata(c = "com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$2$1$1", f = "LibraryToggle.kt", i = {}, l = {50}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$2$1$1, reason: invalid class name */
                /* loaded from: classes15.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ LibraryRepository $repo;
                    final /* synthetic */ MutableState<Boolean> $saved$delegate;
                    final /* synthetic */ MediaItem $target;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(LibraryRepository libraryRepository, MediaItem mediaItem, MutableState<Boolean> mutableState, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$repo = libraryRepository;
                        this.$target = mediaItem;
                        this.$saved$delegate = mutableState;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$repo, this.$target, this.$saved$delegate, continuation);
                        anonymousClass1.L$0 = obj;
                        return anonymousClass1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
                    /* JADX WARN: Removed duplicated region for block: B:14:0x007a  */
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
                            com.streamsync.app.data.model.MediaItem r5 = r1.$target
                            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L56
                            r2 = 0
                            r1.L$0 = r3     // Catch: java.lang.Throwable -> L56
                            r6 = 1
                            r1.label = r6     // Catch: java.lang.Throwable -> L56
                            java.lang.Object r4 = r4.toggle(r5, r1)     // Catch: java.lang.Throwable -> L56
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
                            androidx.compose.runtime.MutableState<java.lang.Boolean> r2 = r1.$saved$delegate
                            boolean r2 = com.streamsync.app.ui.components.LibraryToggleKt.access$rememberLibrarySavedState$lambda$1(r2)
                            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                            boolean r4 = kotlin.Result.m6723isFailureimpl(r9)
                            if (r4 == 0) goto L7b
                            r9 = r2
                        L7b:
                            java.lang.Boolean r9 = (java.lang.Boolean) r9
                            boolean r9 = r9.booleanValue()
                            com.streamsync.app.ui.components.LibraryToggleKt.access$rememberLibrarySavedState$lambda$2(r3, r9)
                            kotlin.Unit r9 = kotlin.Unit.INSTANCE
                            return r9
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.components.LibraryToggleKt$rememberLibrarySavedState$2$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MediaItem item) {
                    if (item == null) {
                        return;
                    }
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScope.this, null, null, new AnonymousClass1(repo, item, saved$delegate, null), 3, null);
                }
            };
            $composer.updateRememberedValue(value$iv4);
        } else {
            value$iv4 = it$iv4;
        }
        $composer.endReplaceableGroup();
        Pair<Boolean, Function1<MediaItem, Unit>> pair = TuplesKt.to(valueOf, (Function1) value$iv4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceableGroup();
        return pair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rememberLibrarySavedState$lambda$1(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void rememberLibrarySavedState$lambda$2(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final void LibraryAddButton(final MediaItem item, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-1061112424);
        ComposerKt.sourceInformation($composer2, "C(LibraryAddButton)63@2299L34,64@2359L16,64@2338L260:LibraryToggle.kt#qvfdnh");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(item) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1061112424, $dirty2, -1, "com.streamsync.app.ui.components.LibraryAddButton (LibraryToggle.kt:61)");
            }
            if (item == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.LibraryToggleKt$LibraryAddButton$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer, int i3) {
                            LibraryToggleKt.LibraryAddButton(MediaItem.this, modifier3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                        }
                    });
                    return;
                }
                return;
            }
            Pair<Boolean, Function1<MediaItem, Unit>> rememberLibrarySavedState = rememberLibrarySavedState(item.getId(), $composer2, 0);
            final boolean saved = rememberLibrarySavedState.component1().booleanValue();
            final Function1 toggle = rememberLibrarySavedState.component2();
            $composer2.startReplaceableGroup(173500793);
            ComposerKt.sourceInformation($composer2, "CC(remember):LibraryToggle.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 14) == 4) | $composer2.changed(toggle);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0<Unit>() { // from class: com.streamsync.app.ui.components.LibraryToggleKt$LibraryAddButton$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        toggle.invoke(item);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer2.endReplaceableGroup();
            IconButtonKt.IconButton((Function0) value$iv, modifier3, false, null, null, ComposableLambdaKt.composableLambda($composer2, -1489479627, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.LibraryToggleKt$LibraryAddButton$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C65@2408L184:LibraryToggle.kt#qvfdnh");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1489479627, $changed2, -1, "com.streamsync.app.ui.components.LibraryAddButton.<anonymous> (LibraryToggle.kt:65)");
                        }
                        IconKt.m1600Iconww6aTOc(saved ? CheckKt.getCheck(Icons.INSTANCE.getDefault()) : AddKt.getAdd(Icons.INSTANCE.getDefault()), saved ? "Bỏ khỏi thư viện" : "Thêm vào thư viện", (Modifier) null, 0L, $composer3, 0, 12);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, ($dirty2 & 112) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 28);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.components.LibraryToggleKt$LibraryAddButton$4
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    LibraryToggleKt.LibraryAddButton(MediaItem.this, modifier3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
