package com.streamsync.app.ui.addons;

import android.content.Context;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import com.streamsync.app.data.addon.AddonManifest;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: AddonsScreen.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003²\u0006\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u008a\u0084\u0002²\u0006\f\u0010\u0007\u001a\u0004\u0018\u00010\bX\u008a\u0084\u0002²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002²\u0006\f\u0010\u000b\u001a\u0004\u0018\u00010\bX\u008a\u0084\u0002²\u0006\n\u0010\f\u001a\u00020\bX\u008a\u008e\u0002"}, d2 = {"AddonsScreen", "", "(Landroidx/compose/runtime/Composer;I)V", "app_mobileDebug", "installed", "", "Lcom/streamsync/app/data/addon/AddonManifest;", "error", "", "installing", "", "lastInstalledUrl", "urlInput"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes20.dex */
public final class AddonsScreenKt {
    public static final void AddonsScreen(Composer $composer, final int $changed) {
        Object value$iv;
        Object value$iv2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-56372290);
        ComposerKt.sourceInformation($composer3, "C(AddonsScreen)118@4984L7,119@5012L37,120@5091L29,121@5154L29,122@5227L29,123@5312L29,125@5363L31,127@5400L7540:AddonsScreen.kt#v1022u");
        if ($changed != 0 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-56372290, $changed, -1, "com.streamsync.app.ui.addons.AddonsScreen (AddonsScreen.kt:117)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context context = (Context) consume;
            $composer3.startReplaceableGroup(703780728);
            ComposerKt.sourceInformation($composer3, "CC(remember):AddonsScreen.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AddonsViewModel(context);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final AddonsViewModel viewModel = (AddonsViewModel) value$iv;
            $composer3.endReplaceableGroup();
            final State installed$delegate = FlowExtKt.collectAsStateWithLifecycle(viewModel.getInstalled(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            final State error$delegate = FlowExtKt.collectAsStateWithLifecycle(viewModel.getError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            final State installing$delegate = FlowExtKt.collectAsStateWithLifecycle(viewModel.getInstalling(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            final State lastInstalledUrl$delegate = FlowExtKt.collectAsStateWithLifecycle(viewModel.getLastInstalledUrl(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, $composer3, 0, 7);
            $composer3.startReplaceableGroup(703791954);
            ComposerKt.sourceInformation($composer3, "CC(remember):AddonsScreen.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            final MutableState urlInput$delegate = (MutableState) value$iv2;
            $composer3.endReplaceableGroup();
            $composer2 = $composer3;
            ScaffoldKt.m1782ScaffoldTvnljyQ(null, ComposableSingletons$AddonsScreenKt.INSTANCE.m6610getLambda2$app_mobileDebug(), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda($composer3, -1049810737, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.AddonsScreenKt$AddonsScreen$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer, Integer num) {
                    invoke(paddingValues, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:31:0x024e  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x025a  */
                /* JADX WARN: Removed duplicated region for block: B:45:0x037f  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x040a  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x043f  */
                /* JADX WARN: Removed duplicated region for block: B:58:0x049a  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x0517  */
                /* JADX WARN: Removed duplicated region for block: B:64:0x0601  */
                /* JADX WARN: Removed duplicated region for block: B:69:0x0656  */
                /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:75:0x0417 A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:76:0x0391  */
                /* JADX WARN: Removed duplicated region for block: B:77:0x0323 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:87:0x0262  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.PaddingValues r94, androidx.compose.runtime.Composer r95, int r96) {
                    /*
                        Method dump skipped, instructions count: 1626
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.addons.AddonsScreenKt$AddonsScreen$1.invoke(androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int):void");
                }
            }), $composer3, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.addons.AddonsScreenKt$AddonsScreen$2
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

                public final void invoke(Composer composer, int i) {
                    AddonsScreenKt.AddonsScreen(composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AddonManifest> AddonsScreen$lambda$1(State<? extends List<AddonManifest>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AddonsScreen$lambda$2(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean AddonsScreen$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AddonsScreen$lambda$4(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AddonsScreen$lambda$6(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }
}
