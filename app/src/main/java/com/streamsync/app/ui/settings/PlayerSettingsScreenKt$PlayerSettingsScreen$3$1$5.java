package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.BufferSteps;
import com.streamsync.app.settings.LastStream;
import com.streamsync.app.settings.LastStreamStore;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.settings.StreamThroughputProbe;
import com.streamsync.app.settings.ThroughputAdvice;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerSettingsScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ MutableState<Integer> $chunkSizeMb$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<Boolean> $customBuffer$delegate;
    final /* synthetic */ MutableState<Integer> $parallel$delegate;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ PlaybackSettingsStore $store;

    /* compiled from: PlayerSettingsScreen.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes17.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ThroughputAdvice.Advice.values().length];
            try {
                iArr[ThroughputAdvice.Advice.TURN_ON_PARALLEL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ThroughputAdvice.Advice.SOURCE_TOO_HEAVY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5(CoroutineScope coroutineScope, Context context, PlaybackSettingsStore playbackSettingsStore, MutableState<Boolean> mutableState, MutableState<Integer> mutableState2, MutableState<Integer> mutableState3) {
        super(2);
        this.$scope = coroutineScope;
        this.$context = context;
        this.$store = playbackSettingsStore;
        this.$customBuffer$delegate = mutableState;
        this.$parallel$delegate = mutableState2;
        this.$chunkSizeMb$delegate = mutableState3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        int i;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Object value$iv5;
        Object value$iv6;
        String str;
        long j;
        Object value$iv7;
        ComposerKt.sourceInformation($composer, "C310@15707L10,311@15771L11,305@15355L548,315@15943L60,317@16103L34,318@16166L68,319@16270L56,320@16358L59:PlayerSettingsScreen.kt#bv4dau");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1950275624, $changed, -1, "com.streamsync.app.ui.settings.PlayerSettingsScreen.<anonymous>.<anonymous>.<anonymous> (PlayerSettingsScreen.kt:305)");
            }
            TextKt.m2127Text4IGK_g("Đo trên đúng máy chủ của luồng bạn vừa xem, rồi so một kết nối với nhiều kết nối. Tốc độ mỗi CDN một khác nên đo chỗ khác rồi áp vào đây là đo sai. Mỗi lượt đo 8 giây, cả 5 lượt khoảng 40 giây.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 54, 0, 65528);
            $composer.startReplaceableGroup(-864264403);
            ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
            Context context = this.$context;
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                i = 2;
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new LastStreamStore(context).read(), null, 2, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                i = 2;
                value$iv = it$iv;
            }
            final MutableState lastStreamState = (MutableState) value$iv;
            $composer.endReplaceableGroup();
            LastStream lastStream = (LastStream) lastStreamState.getValue();
            $composer.startReplaceableGroup(-864259309);
            ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
            Object it$iv2 = $composer.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, i, null);
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState probing$delegate = (MutableState) value$iv2;
            $composer.endReplaceableGroup();
            $composer.startReplaceableGroup(-864257259);
            ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
            Object it$iv3 = $composer.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, i, null);
                $composer.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            final MutableState rows$delegate = (MutableState) value$iv3;
            $composer.endReplaceableGroup();
            $composer.startReplaceableGroup(-864253943);
            ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
            Object it$iv4 = $composer.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, i, null);
                $composer.updateRememberedValue(value$iv4);
            } else {
                value$iv4 = it$iv4;
            }
            final MutableState baselineRow$delegate = (MutableState) value$iv4;
            $composer.endReplaceableGroup();
            $composer.startReplaceableGroup(-864251124);
            ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
            Object it$iv5 = $composer.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, i, null);
                $composer.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            final MutableState verdict$delegate = (MutableState) value$iv5;
            $composer.endReplaceableGroup();
            if (lastStream == null) {
                $composer.startReplaceableGroup(-1021869435);
                ComposerKt.sourceInformation($composer, "326@16670L10,327@16740L11,323@16481L388");
                TextKt.m2127Text4IGK_g("Chưa có luồng nào được phát. Hãy mở một phim, xem một chút rồi quay lại đây.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 65528);
                $composer.endReplaceableGroup();
            } else {
                $composer.startReplaceableGroup(-1021315775);
                ComposerKt.sourceInformation($composer, "333@17001L936,331@16915L1234,*382@19492L10,380@19397L687");
                boolean z = !invoke$lambda$2(probing$delegate);
                Modifier m565paddingVpY3zN4 = PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4));
                $composer.startReplaceableGroup(-864229671);
                ComposerKt.sourceInformation($composer, "CC(remember):PlayerSettingsScreen.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(this.$scope);
                final CoroutineScope coroutineScope = this.$scope;
                Object it$iv6 = $composer.rememberedValue();
                if (invalid$iv || it$iv6 == Composer.INSTANCE.getEmpty()) {
                    value$iv6 = new Function0<Unit>() { // from class: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                            LastStream target = lastStreamState.getValue();
                            if (target == null) {
                                return;
                            }
                            PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5.invoke$lambda$3(probing$delegate, true);
                            rows$delegate.setValue(CollectionsKt.emptyList());
                            baselineRow$delegate.setValue(null);
                            verdict$delegate.setValue(null);
                            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(target, baselineRow$delegate, rows$delegate, verdict$delegate, probing$delegate, null), 3, null);
                        }

                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* compiled from: PlayerSettingsScreen.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
                        @DebugMetadata(c = "com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1", f = "PlayerSettingsScreen.kt", i = {}, l = {343}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1, reason: invalid class name */
                        /* loaded from: classes17.dex */
                        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ MutableState<ThroughputAdvice.Row> $baselineRow$delegate;
                            final /* synthetic */ MutableState<Boolean> $probing$delegate;
                            final /* synthetic */ MutableState<List<ThroughputAdvice.Row>> $rows$delegate;
                            final /* synthetic */ LastStream $target;
                            final /* synthetic */ MutableState<ThroughputAdvice.Result> $verdict$delegate;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(LastStream lastStream, MutableState<ThroughputAdvice.Row> mutableState, MutableState<List<ThroughputAdvice.Row>> mutableState2, MutableState<ThroughputAdvice.Result> mutableState3, MutableState<Boolean> mutableState4, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$target = lastStream;
                                this.$baselineRow$delegate = mutableState;
                                this.$rows$delegate = mutableState2;
                                this.$verdict$delegate = mutableState3;
                                this.$probing$delegate = mutableState4;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass1(this.$target, this.$baselineRow$delegate, this.$rows$delegate, this.$verdict$delegate, this.$probing$delegate, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                AnonymousClass1 anonymousClass1;
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        anonymousClass1 = this;
                                        StreamThroughputProbe streamThroughputProbe = StreamThroughputProbe.INSTANCE;
                                        LastStream lastStream = anonymousClass1.$target;
                                        final MutableState<ThroughputAdvice.Row> mutableState = anonymousClass1.$baselineRow$delegate;
                                        final MutableState<List<ThroughputAdvice.Row>> mutableState2 = anonymousClass1.$rows$delegate;
                                        anonymousClass1.label = 1;
                                        Object run = streamThroughputProbe.run(lastStream, 
                                        /*  JADX ERROR: Method code generation error
                                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0031: INVOKE (r2v1 'run' java.lang.Object) = 
                                              (r2v0 'streamThroughputProbe' com.streamsync.app.settings.StreamThroughputProbe)
                                              (r3v0 'lastStream' com.streamsync.app.settings.LastStream)
                                              (wrap:kotlin.jvm.functions.Function2<com.streamsync.app.settings.ThroughputAdvice$Row, java.lang.Boolean, kotlin.Unit>:0x0026: CONSTRUCTOR 
                                              (r5v0 'mutableState' androidx.compose.runtime.MutableState<com.streamsync.app.settings.ThroughputAdvice$Row> A[DONT_INLINE])
                                              (r6v0 'mutableState2' androidx.compose.runtime.MutableState<java.util.List<com.streamsync.app.settings.ThroughputAdvice$Row>> A[DONT_INLINE])
                                             A[MD:(androidx.compose.runtime.MutableState<com.streamsync.app.settings.ThroughputAdvice$Row>, androidx.compose.runtime.MutableState<java.util.List<com.streamsync.app.settings.ThroughputAdvice$Row>>):void (m), WRAPPED] call: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1$result$1.<init>(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState):void type: CONSTRUCTOR)
                                              (r1v1 'anonymousClass1' com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1 A[D('this' com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1)])
                                             VIRTUAL call: com.streamsync.app.settings.StreamThroughputProbe.run(com.streamsync.app.settings.LastStream, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object A[DECLARE_VAR, MD:(com.streamsync.app.settings.LastStream, kotlin.jvm.functions.Function2<? super com.streamsync.app.settings.ThroughputAdvice$Row, ? super java.lang.Boolean, kotlin.Unit>, kotlin.coroutines.Continuation<? super com.streamsync.app.settings.StreamThroughputProbe$ProbeResult>):java.lang.Object (m)] in method: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes17.dex
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                                            	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:84)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1$result$1, state: NOT_LOADED
                                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:304)
                                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:781)
                                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1117)
                                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:884)
                                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                            	... 21 more
                                            */
                                        /*
                                            this = this;
                                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                            int r1 = r7.label
                                            switch(r1) {
                                                case 0: goto L18;
                                                case 1: goto L11;
                                                default: goto L9;
                                            }
                                        L9:
                                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                            r8.<init>(r0)
                                            throw r8
                                        L11:
                                            r0 = r7
                                            kotlin.ResultKt.throwOnFailure(r8)
                                            r1 = r0
                                            r0 = r8
                                            goto L3a
                                        L18:
                                            kotlin.ResultKt.throwOnFailure(r8)
                                            r1 = r7
                                            com.streamsync.app.settings.StreamThroughputProbe r2 = com.streamsync.app.settings.StreamThroughputProbe.INSTANCE
                                            com.streamsync.app.settings.LastStream r3 = r1.$target
                                            com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1$result$1 r4 = new com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1$1$result$1
                                            androidx.compose.runtime.MutableState<com.streamsync.app.settings.ThroughputAdvice$Row> r5 = r1.$baselineRow$delegate
                                            androidx.compose.runtime.MutableState<java.util.List<com.streamsync.app.settings.ThroughputAdvice$Row>> r6 = r1.$rows$delegate
                                            r4.<init>(r5, r6)
                                            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                                            r5 = r1
                                            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                                            r6 = 1
                                            r1.label = r6
                                            java.lang.Object r2 = r2.run(r3, r4, r5)
                                            if (r2 != r0) goto L38
                                            return r0
                                        L38:
                                            r0 = r8
                                            r8 = r2
                                        L3a:
                                            com.streamsync.app.settings.StreamThroughputProbe$ProbeResult r8 = (com.streamsync.app.settings.StreamThroughputProbe.ProbeResult) r8
                                            androidx.compose.runtime.MutableState<com.streamsync.app.settings.ThroughputAdvice$Result> r2 = r1.$verdict$delegate
                                            com.streamsync.app.settings.LastStream r3 = r1.$target
                                            java.lang.Double r3 = r3.getAverageVideoBitrateMbps()
                                            com.streamsync.app.settings.ThroughputAdvice$Result r3 = r8.advice(r3)
                                            com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5.access$invoke$lambda$12(r2, r3)
                                            androidx.compose.runtime.MutableState<java.lang.Boolean> r2 = r1.$probing$delegate
                                            r3 = 0
                                            com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5.access$invoke$lambda$3(r2, r3)
                                            kotlin.Unit r2 = kotlin.Unit.INSTANCE
                                            return r2
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                                    }
                                }
                            };
                            $composer.updateRememberedValue(value$iv6);
                        } else {
                            value$iv6 = it$iv6;
                        }
                        $composer.endReplaceableGroup();
                        ButtonKt.Button((Function0) value$iv6, m565paddingVpY3zN4, z, null, null, null, null, null, null, ComposableLambdaKt.composableLambda($composer, 523494420, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                invoke(rowScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(RowScope Button, Composer $composer2, int $changed2) {
                                Intrinsics.checkNotNullParameter(Button, "$this$Button");
                                ComposerKt.sourceInformation($composer2, "C352@18076L51:PlayerSettingsScreen.kt#bv4dau");
                                if (($changed2 & 17) != 16 || !$composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(523494420, $changed2, -1, "com.streamsync.app.ui.settings.PlayerSettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerSettingsScreen.kt:352)");
                                    }
                                    TextKt.m2127Text4IGK_g(PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5.invoke$lambda$2(probing$delegate) ? "Đang đo..." : "Chạy phép đo", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer2.skipToGroupEnd();
                            }
                        }), $composer, 805306416, 504);
                        Double averageVideoBitrateMbps = lastStream.getAverageVideoBitrateMbps();
                        $composer.startReplaceableGroup(-864191413);
                        ComposerKt.sourceInformation($composer, "*360@18535L10,361@18608L11,356@18245L511");
                        if (averageVideoBitrateMbps == null) {
                            str = "CC(remember):PlayerSettingsScreen.kt#9igjgp";
                        } else {
                            double bps = averageVideoBitrateMbps.doubleValue();
                            str = "CC(remember):PlayerSettingsScreen.kt#9igjgp";
                            TextKt.m2127Text4IGK_g("Bitrate trung bình của luồng vừa xem: " + ThroughputAdvice.INSTANCE.fmt(bps) + " Mbps. Tốc độ tải phải vượt con số này thì phim mới chạy mượt.", PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 48, 0, 65528);
                            Unit unit = Unit.INSTANCE;
                        }
                        $composer.endReplaceableGroup();
                        ThroughputAdvice.Row row = invoke$lambda$8(baselineRow$delegate);
                        $composer.startReplaceableGroup(-864172376);
                        ComposerKt.sourceInformation($composer, "*367@18850L188");
                        if (row != null) {
                            PlayerSettingsScreenKt.ThroughputRowText("Một kết nối (mặc định khi tắt song song)", ThroughputAdvice.INSTANCE.fmt(row.getMegabitsPerSecond()), $composer, 6);
                            Unit unit2 = Unit.INSTANCE;
                        }
                        $composer.endReplaceableGroup();
                        $composer.startReplaceableGroup(-864163642);
                        ComposerKt.sourceInformation($composer, "*373@19127L182");
                        Iterable $this$forEach$iv = invoke$lambda$5(rows$delegate);
                        for (Object element$iv : $this$forEach$iv) {
                            ThroughputAdvice.Row row2 = (ThroughputAdvice.Row) element$iv;
                            PlayerSettingsScreenKt.ThroughputRowText("Song song, chunk " + row2.getChunkMb() + " MB", ThroughputAdvice.INSTANCE.fmt(row2.getMegabitsPerSecond()), $composer, 0);
                        }
                        $composer.endReplaceableGroup();
                        final ThroughputAdvice.Result r = invoke$lambda$11(verdict$delegate);
                        if (r != null) {
                            final PlaybackSettingsStore playbackSettingsStore = this.$store;
                            final MutableState<Boolean> mutableState = this.$customBuffer$delegate;
                            final MutableState<Integer> mutableState2 = this.$parallel$delegate;
                            final MutableState<Integer> mutableState3 = this.$chunkSizeMb$delegate;
                            String message = r.getMessage();
                            TextStyle bodyMedium = MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium();
                            switch (WhenMappings.$EnumSwitchMapping$0[r.getAdvice().ordinal()]) {
                                case 1:
                                    $composer.startReplaceableGroup(-1058413065);
                                    ComposerKt.sourceInformation($composer, "385@19695L11");
                                    long primary = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                                    $composer.endReplaceableGroup();
                                    j = primary;
                                    break;
                                case 2:
                                    $composer.startReplaceableGroup(-1058408395);
                                    ComposerKt.sourceInformation($composer, "387@19841L11");
                                    long error = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError();
                                    $composer.endReplaceableGroup();
                                    j = error;
                                    break;
                                default:
                                    $composer.startReplaceableGroup(-1058406087);
                                    ComposerKt.sourceInformation($composer, "388@19913L11");
                                    long onSurface = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurface();
                                    $composer.endReplaceableGroup();
                                    j = onSurface;
                                    break;
                            }
                            TextKt.m2127Text4IGK_g(message, PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(8)), j, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodyMedium, $composer, 48, 0, 65528);
                            $composer.startReplaceableGroup(-864130007);
                            ComposerKt.sourceInformation($composer, "394@20247L595,393@20197L1030");
                            if (r.getAdvice() == ThroughputAdvice.Advice.TURN_ON_PARALLEL) {
                                $composer.startReplaceableGroup(-1058395197);
                                ComposerKt.sourceInformation($composer, str);
                                boolean invalid$iv2 = $composer.changedInstance(playbackSettingsStore) | $composer.changed(r);
                                Object it$iv7 = $composer.rememberedValue();
                                if (invalid$iv2 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                                    value$iv7 = new Function0<Unit>() { // from class: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$6$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                                            PlaybackSettingsStore.this.setCustomBufferEnabled(true);
                                            PlaybackSettingsStore.this.setParallelConnections(r.getSuggestedConnections());
                                            PlaybackSettingsStore.this.setChunkSizeMb(BufferSteps.INSTANCE.normalizeChunkMb(r.getBestChunkMb()));
                                            PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$18(mutableState, true);
                                            PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$33(mutableState2, r.getSuggestedConnections());
                                            PlayerSettingsScreenKt.PlayerSettingsScreen$lambda$36(mutableState3, BufferSteps.INSTANCE.normalizeChunkMb(r.getBestChunkMb()));
                                        }
                                    };
                                    $composer.updateRememberedValue(value$iv7);
                                } else {
                                    value$iv7 = it$iv7;
                                }
                                $composer.endReplaceableGroup();
                                ButtonKt.Button((Function0) value$iv7, PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(4)), false, null, null, null, null, null, null, ComposableLambdaKt.composableLambda($composer, 885630345, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.settings.PlayerSettingsScreenKt$PlayerSettingsScreen$3$1$5$6$2
                                    /* JADX INFO: Access modifiers changed from: package-private */
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                                        invoke(rowScope, composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(RowScope Button, Composer $composer2, int $changed2) {
                                        Intrinsics.checkNotNullParameter(Button, "$this$Button");
                                        ComposerKt.sourceInformation($composer2, "C406@21005L192:PlayerSettingsScreen.kt#bv4dau");
                                        if (($changed2 & 17) != 16 || !$composer2.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(885630345, $changed2, -1, "com.streamsync.app.ui.settings.PlayerSettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerSettingsScreen.kt:406)");
                                            }
                                            TextKt.m2127Text4IGK_g("Áp dụng: " + ThroughputAdvice.Result.this.getSuggestedConnections() + " kết nối, chunk " + ThroughputAdvice.Result.this.getBestChunkMb() + " MB", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer2, 0, 0, 131070);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer2.skipToGroupEnd();
                                    }
                                }), $composer, 805306416, 508);
                            }
                            $composer.endReplaceableGroup();
                        }
                        $composer.endReplaceableGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean invoke$lambda$2(MutableState<Boolean> mutableState) {
                MutableState<Boolean> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue().booleanValue();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$3(MutableState<Boolean> mutableState, boolean value) {
                mutableState.setValue(Boolean.valueOf(value));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final List<ThroughputAdvice.Row> invoke$lambda$5(MutableState<List<ThroughputAdvice.Row>> mutableState) {
                MutableState<List<ThroughputAdvice.Row>> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue();
            }

            private static final ThroughputAdvice.Row invoke$lambda$8(MutableState<ThroughputAdvice.Row> mutableState) {
                MutableState<ThroughputAdvice.Row> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue();
            }

            private static final ThroughputAdvice.Result invoke$lambda$11(MutableState<ThroughputAdvice.Result> mutableState) {
                MutableState<ThroughputAdvice.Result> $this$getValue$iv = mutableState;
                return $this$getValue$iv.getValue();
            }
        }
