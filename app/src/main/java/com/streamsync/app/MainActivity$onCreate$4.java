package com.streamsync.app;

import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.UiSettings;
import com.streamsync.app.settings.UiSettingsStore;
import com.streamsync.app.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MainActivity$onCreate$4 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$4(MainActivity mainActivity) {
        super(2);
        this.this$0 = mainActivity;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    private static final UiSettings invoke$lambda$0(State<UiSettings> state) {
        Object thisObj$iv = state.getValue();
        return (UiSettings) thisObj$iv;
    }

    public final void invoke(Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformation($composer, "C184@9055L29,189@9427L42,190@9503L98,190@9482L119,193@9614L2482:MainActivity.kt#b8997");
        if (($changed & 3) != 2 || !$composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(366846119, $changed, -1, "com.streamsync.app.MainActivity.onCreate.<anonymous> (MainActivity.kt:183)");
            }
            UiSettingsStore.Companion companion = UiSettingsStore.INSTANCE;
            Context applicationContext = this.this$0.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            State uiSettings$delegate = FlowExtKt.collectAsStateWithLifecycle(companion.get(applicationContext).getSettings(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer, 0, 7);
            $composer.startReplaceableGroup(-704371433);
            ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState lastExitReport$delegate = (MutableState) value$iv;
            $composer.endReplaceableGroup();
            Unit unit = Unit.INSTANCE;
            $composer.startReplaceableGroup(-704368945);
            ComposerKt.sourceInformation($composer, "CC(remember):MainActivity.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.this$0);
            MainActivity mainActivity = this.this$0;
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new MainActivity$onCreate$4$1$1(mainActivity, lastExitReport$delegate, null);
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer.endReplaceableGroup();
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, 6);
            ThemeKt.StreamSyncTheme(invoke$lambda$0(uiSettings$delegate), ComposableLambdaKt.composableLambda($composer, 1612044137, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    Object value$iv3;
                    ComposerKt.sourceInformation($composer2, "C199@9956L11,194@9671L913,*212@10692L7,214@10776L25,213@10720L1344:MainActivity.kt#b8997");
                    if (($changed2 & 3) != 2 || !$composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1612044137, $changed2, -1, "com.streamsync.app.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:194)");
                        }
                        SurfaceKt.m1979SurfaceT9BRK9s(BackgroundKt.m212backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getBackground(), null, 2, null), null, 0L, 0L, 0.0f, 0.0f, null, ComposableSingletons$MainActivityKt.INSTANCE.m6592getLambda1$app_mobileDebug(), $composer2, 12582912, WebSocketProtocol.PAYLOAD_SHORT);
                        final String report = MainActivity$onCreate$4.invoke$lambda$2(lastExitReport$delegate);
                        if (report != null) {
                            final MutableState<String> mutableState = lastExitReport$delegate;
                            ProvidableCompositionLocal<ClipboardManager> localClipboardManager = CompositionLocalsKt.getLocalClipboardManager();
                            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                            Object consume = $composer2.consume(localClipboardManager);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            final ClipboardManager clipboard = (ClipboardManager) consume;
                            $composer2.startReplaceableGroup(1889383460);
                            ComposerKt.sourceInformation($composer2, "CC(remember):MainActivity.kt#9igjgp");
                            Object it$iv3 = $composer2.rememberedValue();
                            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                                value$iv3 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$1$1
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
                                        mutableState.setValue(null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv3);
                            } else {
                                value$iv3 = it$iv3;
                            }
                            $composer2.endReplaceableGroup();
                            AndroidAlertDialog_androidKt.m1227AlertDialogOix01E0((Function0) value$iv3, ComposableLambdaKt.composableLambda($composer2, -1197605921, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$2
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

                                public final void invoke(Composer $composer3, int $changed3) {
                                    Object value$iv4;
                                    ComposerKt.sourceInformation($composer3, "C224@11386L25,224@11365L127:MainActivity.kt#b8997");
                                    if (($changed3 & 3) == 2 && $composer3.getSkipping()) {
                                        $composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1197605921, $changed3, -1, "com.streamsync.app.MainActivity.onCreate.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:224)");
                                    }
                                    $composer3.startReplaceableGroup(-939062813);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):MainActivity.kt#9igjgp");
                                    final MutableState<String> mutableState2 = mutableState;
                                    Object it$iv4 = $composer3.rememberedValue();
                                    if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                                        value$iv4 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$2$1$1
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
                                                mutableState2.setValue(null);
                                            }
                                        };
                                        $composer3.updateRememberedValue(value$iv4);
                                    } else {
                                        value$iv4 = it$iv4;
                                    }
                                    $composer3.endReplaceableGroup();
                                    ButtonKt.TextButton((Function0) value$iv4, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m6593getLambda2$app_mobileDebug(), $composer3, 805306374, 510);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }), null, ComposableLambdaKt.composableLambda($composer2, -1469660515, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$3
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

                                public final void invoke(Composer $composer3, int $changed3) {
                                    Object value$iv4;
                                    ComposerKt.sourceInformation($composer3, "C231@11774L160,231@11753L263:MainActivity.kt#b8997");
                                    if (($changed3 & 3) == 2 && $composer3.getSkipping()) {
                                        $composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1469660515, $changed3, -1, "com.streamsync.app.MainActivity.onCreate.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:231)");
                                    }
                                    $composer3.startReplaceableGroup(-939050262);
                                    ComposerKt.sourceInformation($composer3, "CC(remember):MainActivity.kt#9igjgp");
                                    boolean invalid$iv2 = $composer3.changedInstance(ClipboardManager.this) | $composer3.changed(report);
                                    final ClipboardManager clipboardManager = ClipboardManager.this;
                                    final String str = report;
                                    final MutableState<String> mutableState2 = mutableState;
                                    Object it$iv4 = $composer3.rememberedValue();
                                    if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                        value$iv4 = new Function0<Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$3$1$1
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
                                                ClipboardManager.this.setText(new AnnotatedString(str, null, null, 6, null));
                                                mutableState2.setValue(null);
                                            }
                                        };
                                        $composer3.updateRememberedValue(value$iv4);
                                    } else {
                                        value$iv4 = it$iv4;
                                    }
                                    $composer3.endReplaceableGroup();
                                    ButtonKt.TextButton((Function0) value$iv4, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m6594getLambda3$app_mobileDebug(), $composer3, 805306368, 510);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }), null, ComposableSingletons$MainActivityKt.INSTANCE.m6595getLambda4$app_mobileDebug(), ComposableLambdaKt.composableLambda($composer2, -1877742406, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.MainActivity$onCreate$4$2$1$4
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

                                /* JADX WARN: Removed duplicated region for block: B:24:0x01a3  */
                                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                    To view partially-correct add '--show-bad-code' argument
                                */
                                public final void invoke(androidx.compose.runtime.Composer r47, int r48) {
                                    /*
                                        Method dump skipped, instructions count: 423
                                        To view this dump add '--comments-level debug' option
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.MainActivity$onCreate$4$2$1$4.invoke(androidx.compose.runtime.Composer, int):void");
                                }
                            }), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, 1772598, 0, 16276);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer2.skipToGroupEnd();
                }
            }), $composer, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        $composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String invoke$lambda$2(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }
}
