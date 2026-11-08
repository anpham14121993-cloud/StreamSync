package com.streamsync.app.ui.detail;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MovieDetailScreen.kt */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0006\u001a+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0003¢\u0006\u0002\u0010\n\u001a;\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0003¢\u0006\u0002\u0010\u000e\u001aM\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u00142\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0014H\u0007¢\u0006\u0002\u0010\u0017\u001aI\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00112\u0014\u0010\u001d\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u00030\u0014H\u0003¢\u0006\u0002\u0010\u001e\u001a\u001d\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010#\u001a#\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0003¢\u0006\u0002\u0010'\u001a-\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00152\b\b\u0002\u0010*\u001a\u00020&2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0003¢\u0006\u0002\u0010+\u001a\u0010\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0007H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006.²\u0006\f\u0010/\u001a\u0004\u0018\u000100X\u008a\u0084\u0002²\u0006\u0010\u00101\u001a\b\u0012\u0004\u0012\u00020\u00150\u001aX\u008a\u0084\u0002²\u0006\n\u0010%\u001a\u00020&X\u008a\u0084\u0002²\u0006\n\u00102\u001a\u00020&X\u008a\u0084\u0002²\u0006\f\u00103\u001a\u0004\u0018\u00010\u0011X\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\u0016\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000706X\u008a\u0084\u0002²\u0006\u0010\u00107\u001a\b\u0012\u0004\u0012\u00020\u00150\u001aX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u0004\u0018\u00010\u0011X\u008a\u0084\u0002²\u0006\n\u00109\u001a\u00020&X\u008a\u0084\u0002²\u0006\f\u0010:\u001a\u0004\u0018\u00010\u0001X\u008a\u008e\u0002²\u0006\f\u0010;\u001a\u0004\u0018\u00010\u0011X\u008a\u008e\u0002²\u0006\f\u0010<\u001a\u0004\u0018\u00010\u0011X\u008a\u008e\u0002"}, d2 = {"PREWARM_SOURCE_COUNT", "", "EpisodeRow", "", "episode", "Lcom/streamsync/app/data/model/SeriesVideo;", "progressMs", "", "onClick", "Lkotlin/Function0;", "(Lcom/streamsync/app/data/model/SeriesVideo;JLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EpisodeSlideCard", "index", "total", "(Lcom/streamsync/app/data/model/SeriesVideo;IIJLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MovieDetailScreen", "mediaId", "", "onBack", "onPlay", "Lkotlin/Function1;", "Lcom/streamsync/app/data/model/StreamSource;", "onOpenEpisode", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ProviderChipsRow", "sources", "", "options", "selected", "onSelect", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "StreamGroupHeaderContent", "group", "Lcom/streamsync/app/ui/detail/StreamProviderGroup;", "totalSources", "(Lcom/streamsync/app/ui/detail/StreamProviderGroup;ILandroidx/compose/runtime/Composer;I)V", "StreamRefreshButton", "loading", "", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "StreamRow", "source", "showAddon", "(Lcom/streamsync/app/data/model/StreamSource;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "formatMs", TranslateLanguage.MALAY, "app_mobileDebug", "item", "Lcom/streamsync/app/data/model/MediaItem;", "streams", "streamsLoading", "error", "resumePositionMs", "episodeProgress", "", "episodeStreams", "episodeStreamsFor", "episodeStreamsLoading", "selectedSeason", "selectedProvider", "episodeProvider"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes16.dex */
public final class MovieDetailScreenKt {
    private static final int PREWARM_SOURCE_COUNT = 3;

    /* JADX WARN: Removed duplicated region for block: B:100:0x0cd2  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0d04  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0e1c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0d1a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0cd6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x08ab  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0a40  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0a59  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0b60  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0b9e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0bbe  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0be0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0ba1  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0a98  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0a43  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x09dc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x047b A[LOOP:0: B:198:0x0475->B:200:0x047b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0453 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03ba A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x036b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0328 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0522  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x05d4  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0cc6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MovieDetailScreen(final java.lang.String r102, final kotlin.jvm.functions.Function0<kotlin.Unit> r103, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.StreamSource, kotlin.Unit> r104, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r105, androidx.compose.runtime.Composer r106, final int r107, final int r108) {
        /*
            Method dump skipped, instructions count: 3653
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt.MovieDetailScreen(java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final MediaItem MovieDetailScreen$lambda$1(State<MediaItem> state) {
        Object thisObj$iv = state.getValue();
        return (MediaItem) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<StreamSource> MovieDetailScreen$lambda$2(State<? extends List<StreamSource>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MovieDetailScreen$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MovieDetailScreen$lambda$4(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MovieDetailScreen$lambda$5(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long MovieDetailScreen$lambda$6(State<Long> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, Long> MovieDetailScreen$lambda$7(State<? extends Map<String, Long>> state) {
        Object thisObj$iv = state.getValue();
        return (Map) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<StreamSource> MovieDetailScreen$lambda$8(State<? extends List<StreamSource>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MovieDetailScreen$lambda$9(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MovieDetailScreen$lambda$10(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final Integer MovieDetailScreen$lambda$15(MutableState<Integer> mutableState) {
        MutableState<Integer> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String MovieDetailScreen$lambda$18(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0244  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EpisodeRow(final com.streamsync.app.data.model.SeriesVideo r76, final long r77, final kotlin.jvm.functions.Function0<kotlin.Unit> r79, androidx.compose.runtime.Composer r80, final int r81) {
        /*
            Method dump skipped, instructions count: 1291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt.EpisodeRow(com.streamsync.app.data.model.SeriesVideo, long, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void StreamRefreshButton(final boolean loading, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-690342830);
        ComposerKt.sourceInformation($composer2, "C(StreamRefreshButton)1282@66686L649:MovieDetailScreen.kt#tl1h0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(loading) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-690342830, $dirty2, -1, "com.streamsync.app.ui.detail.StreamRefreshButton (MovieDetailScreen.kt:1281)");
            }
            IconButtonKt.IconButton(function0, SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(32)), !loading, null, null, ComposableLambdaKt.composableLambda($composer2, -34364497, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$StreamRefreshButton$1
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
                    ComposerKt.sourceInformation($composer3, "C:MovieDetailScreen.kt#tl1h0o");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-34364497, $changed2, -1, "com.streamsync.app.ui.detail.StreamRefreshButton.<anonymous> (MovieDetailScreen.kt:1288)");
                        }
                        if (loading) {
                            $composer3.startReplaceableGroup(1169017341);
                            ComposerKt.sourceInformation($composer3, "1289@66940L124");
                            ProgressIndicatorKt.m1746CircularProgressIndicatorLxG7B9w(SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(16)), 0L, Dp.m5736constructorimpl(2), 0L, 0, $composer3, 390, 26);
                            $composer3.endReplaceableGroup();
                        } else {
                            $composer3.startReplaceableGroup(1169199187);
                            ComposerKt.sourceInformation($composer3, "1297@67237L11,1294@67094L225");
                            IconKt.m1600Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), "Làm mới tất cả luồng", SizeKt.m613size3ABfNKs(Modifier.INSTANCE, Dp.m5736constructorimpl(20)), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary(), $composer3, 432, 0);
                            $composer3.endReplaceableGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }), $composer2, (($dirty2 >> 3) & 14) | 196656, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$StreamRefreshButton$2
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
                    MovieDetailScreenKt.StreamRefreshButton(loading, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProviderChipsRow(final java.util.List<com.streamsync.app.data.model.StreamSource> r56, final java.util.List<java.lang.String> r57, final java.lang.String r58, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r59, androidx.compose.runtime.Composer r60, final int r61) {
        /*
            Method dump skipped, instructions count: 844
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt.ProviderChipsRow(java.util.List, java.util.List, java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
    }

    private static final String ProviderChipsRow$labelFor(List<StreamSource> list, String key) {
        Object element$iv;
        String addonName;
        String it;
        List<StreamSource> $this$firstOrNull$iv = list;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it2.next();
            if (Intrinsics.areEqual(((StreamSource) element$iv).getAddonId(), key)) {
                break;
            }
        }
        StreamSource streamSource = (StreamSource) element$iv;
        if (streamSource != null && (addonName = streamSource.getAddonName()) != null && (it = StringsKt.trim((CharSequence) addonName).toString()) != null) {
            String str = it.length() > 0 ? it : null;
            if (str != null) {
                return str;
            }
        }
        return key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StreamGroupHeaderContent(final com.streamsync.app.ui.detail.StreamProviderGroup r68, final int r69, androidx.compose.runtime.Composer r70, final int r71) {
        /*
            Method dump skipped, instructions count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt.StreamGroupHeaderContent(com.streamsync.app.ui.detail.StreamProviderGroup, int, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a9, code lost:
    
        if (r3 == null) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StreamRow(final com.streamsync.app.data.model.StreamSource r21, boolean r22, final kotlin.jvm.functions.Function0<kotlin.Unit> r23, androidx.compose.runtime.Composer r24, final int r25, final int r26) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt.StreamRow(com.streamsync.app.data.model.StreamSource, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatMs(long ms) {
        long totalSeconds = ms / 1000;
        long j = 3600;
        long h = totalSeconds / j;
        long j2 = 60;
        long m = (totalSeconds % j) / j2;
        long s = totalSeconds % j2;
        String format = h > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(h), Long.valueOf(m), Long.valueOf(s)}, 3)) : String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(m), Long.valueOf(s)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void EpisodeSlideCard(final SeriesVideo episode, final int index, final int total, final long progressMs, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-269604027);
        ComposerKt.sourceInformation($composer2, "C(EpisodeSlideCard)P(!2,4,3)1573@77956L11,1572@77901L91,1569@77795L2195:MovieDetailScreen.kt#tl1h0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(episode) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(index) ? 32 : 16;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(progressMs) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9235) != 9234 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-269604027, $dirty2, -1, "com.streamsync.app.ui.detail.EpisodeSlideCard (MovieDetailScreen.kt:1568)");
            }
            CardKt.Card(function0, SizeKt.m599height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5736constructorimpl(190)), false, RoundedCornerShapeKt.m834RoundedCornerShape0680j_4(Dp.m5736constructorimpl(14)), CardDefaults.INSTANCE.m1290cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, null, ComposableLambdaKt.composableLambda($composer2, -775862662, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$EpisodeSlideCard$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                    invoke(columnScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x017c  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x029c  */
                /* JADX WARN: Removed duplicated region for block: B:30:0x02a8  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x0360  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x03dc  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0429  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x0449  */
                /* JADX WARN: Removed duplicated region for block: B:52:0x04cf  */
                /* JADX WARN: Removed duplicated region for block: B:55:0x0560  */
                /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:59:0x0365  */
                /* JADX WARN: Removed duplicated region for block: B:62:0x02ae  */
                /* JADX WARN: Removed duplicated region for block: B:63:0x0195  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.ColumnScope r78, androidx.compose.runtime.Composer r79, int r80) {
                    /*
                        Method dump skipped, instructions count: 1380
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.detail.MovieDetailScreenKt$EpisodeSlideCard$1.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, (($dirty2 >> 12) & 14) | 100663344, 228);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.detail.MovieDetailScreenKt$EpisodeSlideCard$2
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
                    MovieDetailScreenKt.EpisodeSlideCard(SeriesVideo.this, index, total, progressMs, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
