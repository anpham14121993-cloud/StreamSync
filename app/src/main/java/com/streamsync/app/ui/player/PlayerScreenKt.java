package com.streamsync.app.ui.player;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.View;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.common.PlaybackException;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.R;
import androidx.media3.ui.SubtitleView;
import androidx.profileinstaller.ProfileVerifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DeviceMemory;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.settings.RamTier;
import com.streamsync.app.settings.SubtitleStyle;
import com.streamsync.app.util.FileLog;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000ò\u0001\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aL\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001aQ\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u00042\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010%\u001a\u0087\u0002\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00042\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0#2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u00103\u001a\u0002042\u001e\u00105\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002040#\u0012\u0004\u0012\u00020\u000e0#2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u001f2\b\u00108\u001a\u0004\u0018\u00010\u00022\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000e0#2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010:\u001aU\u0010;\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00040\u001f2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001f2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010@\u001aW\u0010A\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u00022\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u001f2\u0006\u0010E\u001a\u00020\u00182\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010F\u001a\u001d\u0010G\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010H\u001a\u00020\u0002H\u0003¢\u0006\u0002\u0010I\u001a5\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u00022\b\u0010L\u001a\u0004\u0018\u00010\u00022\u0006\u0010M\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010N\u001aF\u0010O\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0006\u0010P\u001a\u00020\u00022\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0017\u0010Q\u001a\u0013\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020\u000e0#¢\u0006\u0002\bSH\u0003¢\u0006\u0002\u0010T\u001a7\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010D2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0003¢\u0006\u0002\u0010Y\u001a\u0097\u0002\u0010Z\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u00182\u0006\u0010]\u001a\u00020\u00182\b\u0010^\u001a\u0004\u0018\u00010\u00022\u0006\u0010_\u001a\u00020\u00042\u0006\u0010`\u001a\u00020\u00182\u0006\u0010a\u001a\u00020\u00182\u0006\u0010b\u001a\u00020\u00042\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0#2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010j\u001a\u00020\u00062\u000e\b\u0002\u0010k\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010l\u001a\u00020m2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00022\u000e\b\u0002\u0010o\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010p\u001aQ\u0010q\u001a\u00020\u000e2\u0006\u0010r\u001a\u00020\u00022\b\u0010P\u001a\u0004\u0018\u00010\u00022\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0014\b\u0002\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0#H\u0007¢\u0006\u0002\u0010u\u001a\u0017\u0010v\u001a\u00020\u000e2\b\u0010X\u001a\u0004\u0018\u00010DH\u0003¢\u0006\u0002\u0010w\u001aY\u0010x\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u001f2\b\u0010y\u001a\u0004\u0018\u00010\u00022\u0006\u0010E\u001a\u00020\u00182\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010z\u001a#\u0010{\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u00062\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0002\u0010}\u001a¿\u0001\u0010~\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002070\u001f2\b\u0010\u007f\u001a\u0004\u0018\u00010\u00022\u0006\u0010E\u001a\u00020\u00182\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000e0#2\r\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\r\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0010\b\u0002\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\u001f2\u000b\b\u0002\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00042\u0015\b\u0002\u0010\u0085\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0#2\u000f\b\u0002\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0003\u0010\u0087\u0001\u001ac\u0010\u0088\u0001\u001a\u00020\u000e2\u0006\u00103\u001a\u0002042\u001e\u00105\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u0002040#\u0012\u0004\u0012\u00020\u000e0#2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u001f2\b\u00108\u001a\u0004\u0018\u00010\u00022\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000e0#H\u0003¢\u0006\u0003\u0010\u0089\u0001\u001a\u0081\u0001\u0010\u008a\u0001\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0#2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0003\u0010\u008b\u0001\u001aN\u0010\u008c\u0001\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0007\u0010\u008d\u0001\u001a\u00020m2\b\u0010n\u001a\u0004\u0018\u00010\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020m\u0012\u0004\u0012\u00020\u000e0#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0003\u0010\u008e\u0001\u001a\u0012\u0010\u008f\u0001\u001a\u00020\u00022\u0007\u0010\u0090\u0001\u001a\u00020\u0002H\u0002\u001a\u0012\u0010\u0091\u0001\u001a\u00020\u00022\u0007\u0010\u0092\u0001\u001a\u00020\u0004H\u0002\u001a(\u0010\u0093\u0001\u001a\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\b\u0010\u0097\u0001\u001a\u00030\u0098\u00012\b\u0010\u0099\u0001\u001a\u00030\u009a\u0001H\u0002\u001a\u0012\u0010\u009b\u0001\u001a\u00020\u00022\u0007\u0010\u009c\u0001\u001a\u00020\u0004H\u0002\u001a\u001f\u0010\u009d\u0001\u001a\u00020\u00022\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00022\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0002H\u0002\u001a\u0013\u0010 \u0001\u001a\u00020\u00022\b\u0010¡\u0001\u001a\u00030¢\u0001H\u0002\u001a\u0011\u0010£\u0001\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u0006H\u0002\u001a\u0011\u0010¤\u0001\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u0006H\u0002\u001a\u001b\u0010¥\u0001\u001a\u00020\u00042\u0007\u0010¦\u0001\u001a\u00020\u00042\u0007\u0010§\u0001\u001a\u00020\u0004H\u0002\u001a\u0013\u0010¨\u0001\u001a\u00020\u000e2\b\u0010©\u0001\u001a\u00030ª\u0001H\u0002\u001a\u0013\u0010«\u0001\u001a\u00020\u00182\b\u0010¡\u0001\u001a\u00030¢\u0001H\u0002\u001a\u000f\u0010¬\u0001\u001a\u0005\u0018\u00010\u00ad\u0001*\u00030\u0096\u0001\u001a\u000e\u0010®\u0001\u001a\u00020\u0018*\u00030¯\u0001H\u0002\u001a\u000e\u0010°\u0001\u001a\u00030±\u0001*\u00020WH\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006²\u0001²\u0006\u000b\u0010³\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\r\u0010´\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\r\u0010µ\u0001\u001a\u0004\u0018\u00010DX\u008a\u0084\u0002²\u0006\r\u0010¶\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\r\u0010·\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\u000b\u0010¸\u0001\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\u000b\u0010¹\u0001\u001a\u00020\u0004X\u008a\u0084\u0002²\u0006\u000b\u0010º\u0001\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\u000b\u0010»\u0001\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u0010)\u001a\u00020\u0006X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020+X\u008a\u0084\u0002²\u0006\u000b\u0010¼\u0001\u001a\u000204X\u008a\u0084\u0002²\u0006\u0010\u00106\u001a\b\u0012\u0004\u0012\u0002070\u001fX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\r\u0010½\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\u000b\u0010¾\u0001\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\r\u0010¿\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u0084\u0002²\u0006\u000b\u0010À\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Á\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\n\u0010j\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\u000b\u0010Â\u0001\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\r\u0010Ã\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u008e\u0002²\u0006\u000b\u0010Ä\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Å\u0001\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\u000b\u0010Æ\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u0011\u0010Ç\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u008a\u0084\u0002²\u0006\u0012\u0010È\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\u001fX\u008a\u0084\u0002²\u0006\r\u0010É\u0001\u001a\u0004\u0018\u00010\u0004X\u008a\u0084\u0002²\u0006\r\u0010Ê\u0001\u001a\u0004\u0018\u00010\u0004X\u008a\u0084\u0002²\u0006\u0010\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u001fX\u008a\u0084\u0002²\u0006\n\u0010`\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\u0010\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u001fX\u008a\u0084\u0002²\u0006\r\u0010Ë\u0001\u001a\u0004\u0018\u00010>X\u008a\u0084\u0002²\u0006\u000e\u0010Ì\u0001\u001a\u0005\u0018\u00010Í\u0001X\u008a\u0084\u0002²\u0006\u0011\u0010Î\u0001\u001a\b\u0012\u0004\u0012\u00020D0\u001fX\u008a\u0084\u0002²\u0006\u000b\u0010Ï\u0001\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\u0010\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00040\u001fX\u008a\u0084\u0002²\u0006\u000b\u0010Ð\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Ñ\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Ò\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Ó\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Ô\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Õ\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\u000b\u0010Ö\u0001\u001a\u00020\u0018X\u008a\u008e\u0002²\u0006\n\u0010l\u001a\u00020mX\u008a\u008e\u0002²\u0006\f\u0010×\u0001\u001a\u00030Ø\u0001X\u008a\u0084\u0002²\u0006\r\u0010Ù\u0001\u001a\u0004\u0018\u00010\u0002X\u008a\u008e\u0002²\u0006\r\u0010Ú\u0001\u001a\u0004\u0018\u00010\u0004X\u008a\u008e\u0002²\u0006\f\u0010Û\u0001\u001a\u00030±\u0001X\u008a\u008e\u0002"}, d2 = {"AUDIO_LANGUAGE_NAMES", "", "", "BUFFER_ALLOCATOR_POOL_SIZE", "", "BUFFER_SAFETY_BITRATE_BPS", "", "CAPTURE_DISPLAY_MS", "PREWARM_SOURCE_COUNT", "PROGRESS_TICK_MS", "PROGRESS_WRITE_INTERVAL_MS", "PUSH_INTERVAL_MS", "RESTART_FROM_START_MIN_MS", "ActionChip", "", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "contentDescription", "onClick", "Lkotlin/Function0;", "tint", "Landroidx/compose/ui/graphics/Color;", "enabled", "", "ActionChip-FHprtrg", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Lkotlin/jvm/functions/Function0;JZLandroidx/compose/runtime/Composer;II)V", "AudioPicker", "modifier", "Landroidx/compose/ui/Modifier;", "tracks", "", "Lcom/streamsync/app/ui/player/AudioTrackOption;", "activeIndex", "onSelect", "Lkotlin/Function1;", "onClose", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "ControlPanel", "tab", "onTabChange", "offsetMs", "syncState", "Lcom/streamsync/app/ui/player/SubtitleSyncUiState;", "keyConfigured", "onAdjustOffset", "onResetOffset", "onStartAutoSync", "onStopAutoSync", "onCancelAutoSync", "onOpenSettings", TtmlNode.TAG_STYLE, "Lcom/streamsync/app/settings/SubtitleStyle;", "onStyleUpdate", "availableSubtitles", "Lcom/streamsync/app/data/model/SubtitleTrack;", "activeSubtitleLabel", "onSelectSubtitle", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;JLcom/streamsync/app/ui/player/SubtitleSyncUiState;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lcom/streamsync/app/settings/SubtitleStyle;Lkotlin/jvm/functions/Function1;Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "EpisodePicker", "seasons", "episodes", "Lcom/streamsync/app/data/model/SeriesVideo;", "onPick", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "EpisodeSourceSheet", "episodeLabel", "sources", "Lcom/streamsync/app/data/model/StreamSource;", "loading", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/util/List;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "InfoRow", "value", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "PickerRow", "primary", "secondary", "selected", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "PickerSheet", "title", "content", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "PlaybackInfoPanel", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "source", "(Landroidx/media3/exoplayer/ExoPlayer;Lcom/streamsync/app/data/model/StreamSource;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PlayerActionBar", "syncBusy", "hasSubtitleTrack", "hasAudioTracks", "activeAudioLabel", "sourceCount", "isLoadingSources", "hasSeasons", "episodeCount", "onNudge", "onAutoSync", "onSubtitleStyle", "onSubtitles", "onAudio", "onSources", "onEpisodes", "currentPositionMs", "onRestartFromBeginning", "scaleMode", "Lcom/streamsync/app/ui/player/VideoScaleMode;", "videoRatioLabel", "onOpenScalePicker", "(Landroidx/compose/ui/Modifier;JZZZLjava/lang/String;IZZILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLkotlin/jvm/functions/Function0;Lcom/streamsync/app/ui/player/VideoScaleMode;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;IIII)V", "PlayerScreen", "mediaId", "onBack", "onOpenEpisode", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "ProviderSection", "(Lcom/streamsync/app/data/model/StreamSource;Landroidx/compose/runtime/Composer;I)V", "SourcePicker", "currentUrl", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "SubtitleOffsetReadout", "onReset", "(JLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SubtitlePicker", "activeUrl", "onDisable", "onReload", "playerTracks", "Lcom/streamsync/app/ui/player/PlayerSubtitleTrack;", "activePlayerIndex", "onSelectPlayerTrack", "onDisablePlayerTrack", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Ljava/lang/String;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;III)V", "SubtitleStyleTab", "(Lcom/streamsync/app/settings/SubtitleStyle;Lkotlin/jvm/functions/Function1;Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "SyncTab", "(JLcom/streamsync/app/ui/player/SubtitleSyncUiState;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "VideoScalePicker", "current", "(Landroidx/compose/ui/Modifier;Lcom/streamsync/app/ui/player/VideoScaleMode;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "audioLanguageName", "code", "bitrateLabel", "bitsPerSecond", "buildLoadControl", "Lcom/streamsync/app/ui/player/AdjustableLoadControl;", "context", "Landroid/content/Context;", "store", "Lcom/streamsync/app/settings/PlaybackSettingsStore;", "tier", "Lcom/streamsync/app/settings/RamTier;", "channelLabel", "channels", "codecLabel", "mime", "fallback", "describePlaybackError", "e", "Landroidx/media3/common/PlaybackException;", "formatOffsetSeconds", "formatSigned", "gcd", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "hideBuiltInSubtitleView", "view", "Landroid/view/View;", "isDecoderInitFailure", "findActivityCompat", "Landroid/app/Activity;", "hasAudioCause", "", "snapshotInfo", "Lcom/streamsync/app/ui/player/PlaybackInfo;", "app_mobileDebug", "forcePortrait", "streamUrl", "currentSource", "streamMimeType", "playbackError", "isBuffering", "reloadToken", "audioDisabledNotice", "softwareAudioNotice", "subtitleStyle", "activeSubtitleTrackUrl", "isLoadingSubtitles", "subtitleError", "syncBannerVisible", "overlayVisible", "durationMs", "subtitleText", "showPanel", "panelTab", "showInfo", "audioTracks", "playerSubtitleTracks", "activePlayerSubtitleIndex", "activeAudioIndex", "pendingEpisode", "parentShow", "Lcom/streamsync/app/data/model/MediaItem;", "episodeSources", "isLoadingEpisodeSources", "showSubtitlePicker", "showAudioPicker", "showSourcePicker", "showEpisodePicker", "showScalePicker", "hasRenderedFirstFrame", "playingNow", "videoSize", "Lcom/streamsync/app/ui/player/VideoDimensions;", "scaleNotice", "pickedSeason", "info"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlayerScreenKt {
    private static final Map<String, String> AUDIO_LANGUAGE_NAMES = MapsKt.mapOf(TuplesKt.to("vie", "Tiếng Việt"), TuplesKt.to("eng", "Tiếng Anh"), TuplesKt.to("jpn", "Tiếng Nhật"), TuplesKt.to("kor", "Tiếng Hàn"), TuplesKt.to("zho", "Tiếng Trung"), TuplesKt.to("chi", "Tiếng Trung"), TuplesKt.to("fra", "Tiếng Pháp"), TuplesKt.to("fre", "Tiếng Pháp"), TuplesKt.to("deu", "Tiếng Đức"), TuplesKt.to("ger", "Tiếng Đức"), TuplesKt.to("spa", "Tiếng Tây Ban Nha"), TuplesKt.to("ita", "Tiếng Ý"), TuplesKt.to("rus", "Tiếng Nga"), TuplesKt.to("ind", "Tiếng Indonesia"), TuplesKt.to("tha", "Tiếng Thái"), TuplesKt.to("por", "Tiếng Bồ Đào Nha"), TuplesKt.to("tur", "Tiếng Thổ Nhĩ Kỳ"), TuplesKt.to("pol", "Tiếng Ba Lan"), TuplesKt.to("nld", "Tiếng Hà Lan"), TuplesKt.to("ara", "Tiếng Ả Rập"), TuplesKt.to("hin", "Tiếng Hindi"));
    private static final int BUFFER_ALLOCATOR_POOL_SIZE = 64;
    private static final long BUFFER_SAFETY_BITRATE_BPS = 100000000;
    private static final long CAPTURE_DISPLAY_MS = 20000;
    private static final int PREWARM_SOURCE_COUNT = 3;
    private static final long PROGRESS_TICK_MS = 200;
    private static final long PROGRESS_WRITE_INTERVAL_MS = 10000;
    private static final long PUSH_INTERVAL_MS = 30000;
    private static final long RESTART_FROM_START_MIN_MS = 10000;

    public static final Activity findActivityCompat(Context $this$findActivityCompat) {
        Intrinsics.checkNotNullParameter($this$findActivityCompat, "<this>");
        Context ctx = $this$findActivityCompat;
        while (ctx instanceof ContextWrapper) {
            if (ctx instanceof Activity) {
                return (Activity) ctx;
            }
            Context baseContext = ((ContextWrapper) ctx).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            ctx = baseContext;
        }
        return null;
    }

    public static final String formatSigned(long offsetMs) {
        return (offsetMs >= 0 ? new StringBuilder().append("+") : new StringBuilder()).append(offsetMs).append(TranslateLanguage.MALAY).toString();
    }

    public static final void hideBuiltInSubtitleView(View view) {
        SubtitleView subtitleView;
        try {
            Result.Companion companion = Result.INSTANCE;
            PlayerView playerView = view instanceof PlayerView ? (PlayerView) view : null;
            SubtitleView sub = (playerView == null || (subtitleView = playerView.getSubtitleView()) == null) ? view.findViewById(R.id.exo_subtitles) : subtitleView;
            if (sub != null) {
                sub.setVisibility(8);
            }
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
    }

    private static final AdjustableLoadControl buildLoadControl(Context context, PlaybackSettingsStore store, RamTier tier) {
        int requestedMb;
        boolean manual = store.isCustomBufferEnabled();
        if (!manual) {
            AdjustableLoadControl stock = AdjustableLoadControl.INSTANCE.stock();
            long j = 1048576;
            FileLog.d$default(FileLog.INSTANCE, "Bo dem: chinh tay TAT | heap toi da " + (Runtime.getRuntime().maxMemory() / j) + " MB | dung media3 mac dinh toi da " + (stock.getMaxBufferMs() / 1000) + "s giu phia sau " + stock.getBackBufferSec() + "s | tran byte tu media3 tu tinh", null, 2, null);
            Log.i("StreamSyncData", "Bo dem: chinh tay TAT | heap toi da " + (Runtime.getRuntime().maxMemory() / j) + " MB | dung media3 mac dinh toi da " + (stock.getMaxBufferMs() / 1000) + "s giu phia sau " + stock.getBackBufferSec() + "s | tran byte tu media3 tu tinh");
            return stock;
        }
        int minMs = (int) (store.getMinBufferSeconds() * 1000);
        int maxMs = (int) (store.getMaxBufferSeconds() * 1000);
        int ceilingMb = BufferCeiling.INSTANCE.forDevice(context, tier);
        if (store.isManagedBufferEnabled()) {
            requestedMb = ceilingMb;
        } else {
            requestedMb = store.getBufferSizeMb();
        }
        int targetMb = RangesKt.coerceAtMost(requestedMb, ceilingMb);
        int startMs = store.getStartBufferSeconds() * 1000;
        int seekMs = store.getSeekBufferSeconds() * 1000;
        int safeMin = RangesKt.coerceAtLeast(minMs, seekMs);
        long secondsFittableInTarget = (((targetMb * 1024) * 1024) * 8) / BUFFER_SAFETY_BITRATE_BPS;
        int floorSeconds = Math.max(startMs, seekMs);
        long j2 = 1000 * secondsFittableInTarget;
        long secondsFittableInTarget2 = floorSeconds;
        int cappedMin = RangesKt.coerceAtMost(safeMin, (int) RangesKt.coerceAtLeast(j2, secondsFittableInTarget2));
        int safeMax = RangesKt.coerceAtLeast(maxMs, cappedMin + 1000);
        int backBufferSec = store.getBackBufferSeconds();
        Runtime rt = Runtime.getRuntime();
        FileLog fileLog = FileLog.INSTANCE;
        String label = tier.getLabel();
        int floorSeconds2 = tier.getSafetyLimitMb();
        long j3 = 1048576;
        FileLog.d$default(fileLog, "Bo dem: chinh tay BAT | ho so " + label + " (tran mem " + floorSeconds2 + " MB) | heap toi da " + (rt.maxMemory() / j3) + " MB, dang dung " + (rt.totalMemory() / j3) + " MB | RAM con trong " + DeviceMemory.INSTANCE.read(context).getAvailableMb() + " MB | => tran thuc te " + ceilingMb + " MB | dung luong " + targetMb + " MB | toi thieu " + cappedMin + "s toi da " + safeMax + "s | bat dau " + startMs + "ms sau giat " + seekMs + "ms | giu phia sau " + backBufferSec + CmcdData.Factory.STREAMING_FORMAT_SS, null, 2, null);
        Log.i("StreamSyncData", "Bo dem: chinh tay BAT | ho so " + tier.getLabel() + " (tran mem " + tier.getSafetyLimitMb() + " MB) | heap toi da " + (rt.maxMemory() / j3) + " MB, dang dung " + (rt.totalMemory() / j3) + " MB | RAM con trong " + DeviceMemory.INSTANCE.read(context).getAvailableMb() + " MB | => tran thuc te " + ceilingMb + " MB | dung luong " + targetMb + " MB | toi thieu " + cappedMin + "s toi da " + safeMax + "s | bat dau " + startMs + "ms sau giat " + seekMs + "ms | giu phia sau " + backBufferSec + CmcdData.Factory.STREAMING_FORMAT_SS);
        return new AdjustableLoadControl(cappedMin, safeMax, startMs, seekMs, backBufferSec, true, false, (int) RangesKt.coerceAtMost(RangesKt.coerceIn(targetMb, 8L, PlaybackStateCompat.ACTION_PLAY_FROM_URI) * 1024 * 1024, 2147483647L), 64);
    }

    public static final String audioLanguageName(String code) {
        String lowerCase = code.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String key = StringsKt.substringBefore$default(StringsKt.substringBefore$default(lowerCase, "_", (String) null, 2, (Object) null), "-", (String) null, 2, (Object) null);
        String str = AUDIO_LANGUAGE_NAMES.get(key);
        return str == null ? code : str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final boolean hasAudioCause(java.lang.Throwable r7) {
        /*
            r0 = r7
            r1 = 0
        L2:
            r2 = 0
            if (r0 == 0) goto L71
            r3 = 10
            if (r1 >= r3) goto L71
            java.lang.String r3 = r0.getMessage()
            if (r3 != 0) goto L11
            java.lang.String r3 = ""
        L11:
            java.lang.String r4 = r0.getMessage()
            r5 = 1
            if (r4 == 0) goto L25
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "audio/"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 != r5) goto L25
            r2 = r5
        L25:
            if (r2 == 0) goto L69
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "true-hd"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 != 0) goto L68
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "truehd"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 != 0) goto L68
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "atmos"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 != 0) goto L68
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "eac3"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 != 0) goto L68
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r6 = "ac-4"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r4 = kotlin.text.StringsKt.contains(r4, r6, r5)
            if (r4 == 0) goto L69
        L68:
            return r5
        L69:
            java.lang.Throwable r0 = r0.getCause()
            int r1 = r1 + 1
            goto L2
        L71:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.hasAudioCause(java.lang.Throwable):boolean");
    }

    public static final String describePlaybackError(PlaybackException e) {
        String summary;
        String it;
        switch (e.errorCode) {
            case 2000:
                summary = "Lỗi mạng khi tải dữ liệu phát.";
                break;
            case PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED /* 2001 */:
            case PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT /* 2002 */:
                summary = "Không kết nối được tới máy chủ phát. Kiểm tra mạng rồi bấm Thử lại.";
                break;
            case PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE /* 2003 */:
            case PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED /* 3001 */:
            case PlaybackException.ERROR_CODE_PARSING_MANIFEST_MALFORMED /* 3002 */:
            case PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED /* 3003 */:
            case PlaybackException.ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED /* 3004 */:
                summary = "Không đọc được danh sách nguồn phát (định dạng HLS không hợp lệ).";
                break;
            case PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS /* 2004 */:
                summary = "Máy chủ phát trả lỗi HTTP (link hết hạn hoặc addon chặn truy cập). Thử nguồn khác.";
                break;
            case PlaybackException.ERROR_CODE_DECODER_INIT_FAILED /* 4001 */:
            case PlaybackException.ERROR_CODE_DECODING_FAILED /* 4003 */:
            case PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES /* 4004 */:
            case PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED /* 4005 */:
            case PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED /* 5001 */:
            case PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED /* 5002 */:
                if (hasAudioCause(e)) {
                    summary = "Máy này không giải mã được âm thanh của phim (thường là Dolby TrueHD/Atmos). Phim cần một máy có decoder TrueHD, hoặc nối qua TV/AVR nhận được TrueHD.";
                    break;
                } else {
                    summary = "Thiết bị này không giải mã được video (thường là HEVC 1080p). Thử nguồn 720p/SD hoặc xem trên máy khác.";
                    break;
                }
            default:
                summary = "Không phát được nguồn này.";
                break;
        }
        Throwable th = (Throwable) SequencesKt.lastOrNull(SequencesKt.generateSequence(e.getCause(), new Function1<Throwable, Throwable>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$describePlaybackError$detail$1
            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                return it2.getCause();
            }
        }));
        String str = null;
        if (th != null && (it = th.getMessage()) != null && (!StringsKt.isBlank(it))) {
            str = it;
        }
        String detail = str;
        String errorCodeName = e.getErrorCodeName();
        return (detail == null ? new StringBuilder().append(summary).append("\n").append(errorCodeName) : new StringBuilder().append(summary).append("\n").append(errorCodeName).append(": ").append(detail)).toString();
    }

    public static final boolean isDecoderInitFailure(PlaybackException e) {
        return e.errorCode == 4001 || e.errorCode == 4005 || e.errorCode == 4004 || e.errorCode == 5001 || e.errorCode == 5002;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0712  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0750  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x07c0  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x092f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x099f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0acd  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0b3f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0b87  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0d00  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0d4d  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0da1  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0e08  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0e52  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0e5f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0e99  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0eea  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0f3b  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0f80  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0faf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0fd6  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x1098  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0fe9  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0f8d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0f48 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0ef7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0ea6  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0e54  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0e15 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0c61  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0ca7  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0cd7  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0ba3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0b4e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0b11  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0a4f  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x09ab  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x09b0  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x098a  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0941  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x08b4  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x07a4  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x10c3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0543  */
    /* JADX WARN: Type inference failed for: r9v144, types: [androidx.lifecycle.viewmodel.CreationExtras] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerScreen(final java.lang.String r120, final java.lang.String r121, final kotlin.jvm.functions.Function0<kotlin.Unit> r122, final kotlin.jvm.functions.Function0<kotlin.Unit> r123, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r124, androidx.compose.runtime.Composer r125, final int r126, final int r127) {
        /*
            Method dump skipped, instructions count: 4308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.PlayerScreen(java.lang.String, java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final boolean PlayerScreen$lambda$4(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$5(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final String PlayerScreen$lambda$9(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final StreamSource PlayerScreen$lambda$10(State<StreamSource> state) {
        Object thisObj$iv = state.getValue();
        return (StreamSource) thisObj$iv;
    }

    public static final String PlayerScreen$lambda$11(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final String PlayerScreen$lambda$12(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$13(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final int PlayerScreen$lambda$14(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).intValue();
    }

    public static final boolean PlayerScreen$lambda$15(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final boolean PlayerScreen$lambda$16(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final long PlayerScreen$lambda$17(State<Long> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).longValue();
    }

    public static final SubtitleSyncUiState PlayerScreen$lambda$18(State<SubtitleSyncUiState> state) {
        Object thisObj$iv = state.getValue();
        return (SubtitleSyncUiState) thisObj$iv;
    }

    public static final SubtitleStyle PlayerScreen$lambda$19(State<SubtitleStyle> state) {
        Object thisObj$iv = state.getValue();
        return (SubtitleStyle) thisObj$iv;
    }

    public static final List<SubtitleTrack> PlayerScreen$lambda$20(State<? extends List<SubtitleTrack>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final String PlayerScreen$lambda$21(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final String PlayerScreen$lambda$22(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$23(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final String PlayerScreen$lambda$24(State<String> state) {
        Object thisObj$iv = state.getValue();
        return (String) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$26(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$27(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$29(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$30(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final long PlayerScreen$lambda$33(MutableState<Long> mutableState) {
        MutableState<Long> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().longValue();
    }

    public static final void PlayerScreen$lambda$34(MutableState<Long> mutableState, long value) {
        mutableState.setValue(Long.valueOf(value));
    }

    public static final long PlayerScreen$lambda$36(MutableState<Long> mutableState) {
        MutableState<Long> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().longValue();
    }

    public static final void PlayerScreen$lambda$37(MutableState<Long> mutableState, long value) {
        mutableState.setValue(Long.valueOf(value));
    }

    public static final String PlayerScreen$lambda$39(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final boolean PlayerScreen$lambda$42(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$43(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final int PlayerScreen$lambda$45(MutableIntState $panelTab$delegate) {
        MutableIntState $this$getValue$iv = $panelTab$delegate;
        return $this$getValue$iv.getIntValue();
    }

    public static final boolean PlayerScreen$lambda$48(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$49(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final List<AudioTrackOption> PlayerScreen$lambda$50(State<? extends List<AudioTrackOption>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final List<PlayerSubtitleTrack> PlayerScreen$lambda$51(State<? extends List<PlayerSubtitleTrack>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final Integer PlayerScreen$lambda$52(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return (Integer) thisObj$iv;
    }

    public static final Integer PlayerScreen$lambda$53(State<Integer> state) {
        Object thisObj$iv = state.getValue();
        return (Integer) thisObj$iv;
    }

    public static final List<StreamSource> PlayerScreen$lambda$54(State<? extends List<StreamSource>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$55(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final List<SeriesVideo> PlayerScreen$lambda$56(State<? extends List<SeriesVideo>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final SeriesVideo PlayerScreen$lambda$57(State<SeriesVideo> state) {
        Object thisObj$iv = state.getValue();
        return (SeriesVideo) thisObj$iv;
    }

    public static final MediaItem PlayerScreen$lambda$58(State<MediaItem> state) {
        Object thisObj$iv = state.getValue();
        return (MediaItem) thisObj$iv;
    }

    public static final List<StreamSource> PlayerScreen$lambda$59(State<? extends List<StreamSource>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$60(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    public static final List<Integer> PlayerScreen$lambda$61(State<? extends List<Integer>> state) {
        Object thisObj$iv = state.getValue();
        return (List) thisObj$iv;
    }

    public static final boolean PlayerScreen$lambda$65(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$66(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$68(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$69(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$71(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$72(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$74(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$75(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$77(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$78(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    private static final boolean PlayerScreen$lambda$80(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$81(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final boolean PlayerScreen$lambda$83(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    public static final void PlayerScreen$lambda$84(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    public static final VideoScaleMode PlayerScreen$lambda$87(MutableState<VideoScaleMode> mutableState) {
        MutableState<VideoScaleMode> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final VideoDimensions PlayerScreen$lambda$90(MutableState<VideoDimensions> mutableState) {
        MutableState<VideoDimensions> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final String PlayerScreen$lambda$92(MutableState<String> mutableState) {
        MutableState<String> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:189:0x09ca, code lost:
    
        if (r1 == null) goto L817;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x056c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x05b1  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0637  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x06ce  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x073e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0781  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x07d4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0817  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x087e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0938  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x099c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x09a7  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0a63  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x099f  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0983  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x08d2  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x085f  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0824 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x080a  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x078e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0706  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0729  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x05c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0ac1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x033b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerActionBar(androidx.compose.ui.Modifier r87, final long r88, final boolean r90, final boolean r91, final boolean r92, final java.lang.String r93, final int r94, final boolean r95, final boolean r96, final int r97, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r98, final kotlin.jvm.functions.Function0<kotlin.Unit> r99, final kotlin.jvm.functions.Function0<kotlin.Unit> r100, final kotlin.jvm.functions.Function0<kotlin.Unit> r101, final kotlin.jvm.functions.Function0<kotlin.Unit> r102, final kotlin.jvm.functions.Function0<kotlin.Unit> r103, final kotlin.jvm.functions.Function0<kotlin.Unit> r104, final kotlin.jvm.functions.Function0<kotlin.Unit> r105, long r106, kotlin.jvm.functions.Function0<kotlin.Unit> r108, com.streamsync.app.ui.player.VideoScaleMode r109, java.lang.String r110, kotlin.jvm.functions.Function0<kotlin.Unit> r111, androidx.compose.runtime.Composer r112, final int r113, final int r114, final int r115, final int r116) {
        /*
            Method dump skipped, instructions count: 2762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.PlayerActionBar(androidx.compose.ui.Modifier, long, boolean, boolean, boolean, java.lang.String, int, boolean, boolean, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, long, kotlin.jvm.functions.Function0, com.streamsync.app.ui.player.VideoScaleMode, java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x02c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SubtitleOffsetReadout(final long r77, final kotlin.jvm.functions.Function0<kotlin.Unit> r79, androidx.compose.runtime.Composer r80, final int r81) {
        /*
            Method dump skipped, instructions count: 731
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.SubtitleOffsetReadout(long, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    private static final String formatOffsetSeconds(long offsetMs) {
        String sign;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(Math.abs(offsetMs) / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        String body = StringsKt.replace$default(format, '.', ',', false, 4, (Object) null);
        if (offsetMs > 0) {
            sign = "+";
        } else {
            sign = offsetMs < 0 ? "-" : "";
        }
        return sign + body + " s";
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02ce  */
    /* renamed from: ActionChip-FHprtrg */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6667ActionChipFHprtrg(final java.lang.String r42, final androidx.compose.ui.graphics.vector.ImageVector r43, final java.lang.String r44, final kotlin.jvm.functions.Function0<kotlin.Unit> r45, long r46, boolean r48, androidx.compose.runtime.Composer r49, final int r50, final int r51) {
        /*
            Method dump skipped, instructions count: 760
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.m6667ActionChipFHprtrg(java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, kotlin.jvm.functions.Function0, long, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void ControlPanel(final int tab, final Function1<? super Integer, Unit> function1, final Function0<Unit> function0, final long offsetMs, final SubtitleSyncUiState syncState, final boolean keyConfigured, final Function1<? super Long, Unit> function12, final Function0<Unit> function02, final Function0<Unit> function03, final Function0<Unit> function04, final Function0<Unit> function05, final Function0<Unit> function06, final SubtitleStyle style, final Function1<? super Function1<? super SubtitleStyle, SubtitleStyle>, Unit> function13, final List<SubtitleTrack> list, final String activeSubtitleLabel, final Function1<? super SubtitleTrack, Unit> function14, Modifier modifier, Composer $composer, final int $changed, final int $changed1, final int i) {
        int $dirty;
        int $dirty2;
        long m3407copywmQWz5c;
        int $dirty3;
        Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(-153961078);
        ComposerKt.sourceInformation($composer2, "C(ControlPanel)P(17,14,7,4,16,2,5,9,11,12,6,8,15,13,1!1,10)3827@173880L11,3825@173812L1910:PlayerScreen.kt#nsz36g");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changed(tab) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty4 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty4 |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 = $dirty4 | 3072;
        } else {
            if (($changed & 3072) == 0) {
                $dirty = $dirty4 | ($composer2.changed(offsetMs) ? 2048 : 1024);
            } else {
                $dirty = $dirty4;
            }
            $dirty2 = $dirty;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(syncState) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changed(keyConfigured) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function12) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function02) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function03) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) != 0) {
            $dirty2 |= 805306368;
        } else if ((805306368 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function04) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function05) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function06) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed1 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty1 |= $composer2.changed(style) ? 256 : 128;
        }
        if ((i & 8192) != 0) {
            $dirty1 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer2.changedInstance(function13) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty1 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer2.changedInstance(list) ? 16384 : 8192;
        }
        if ((i & 32768) != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer2.changed(activeSubtitleLabel) ? 131072 : 65536;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer2.changedInstance(function14) ? 1048576 : 524288;
        }
        int i2 = i & 131072;
        if (i2 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer2.changed(modifier) ? 8388608 : 4194304;
        }
        if (($dirty2 & 306783379) == 306783378 && (4793491 & $dirty1) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            $dirty3 = $dirty2;
        } else {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-153961078, $dirty2, $dirty1, "com.streamsync.app.ui.player.ControlPanel (PlayerScreen.kt:3824)");
            }
            m3407copywmQWz5c = Color.m3407copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r16) : 0.97f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface()) : 0.0f);
            $dirty3 = $dirty2;
            SurfaceKt.m1979SurfaceT9BRK9s(modifier3, null, m3407copywmQWz5c, 0L, Dp.m5736constructorimpl(6), 0.0f, null, ComposableLambdaKt.composableLambda($composer2, 1683567973, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$ControlPanel$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0276  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0282  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0323  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x03b6  */
                /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:41:0x0350  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x0286  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r73, int r74) {
                    /*
                        Method dump skipped, instructions count: 954
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$ControlPanel$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, (($dirty1 >> 21) & 14) | 12607488, 106);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$ControlPanel$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.ControlPanel(tab, function1, function0, offsetMs, syncState, keyConfigured, function12, function02, function03, function04, function05, function06, style, function13, list, activeSubtitleLabel, function14, modifier4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x06ab  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x07bb  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0873  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0882  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0961  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x096d  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x09a6  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0a80  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0b27  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0b36  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0bad  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0bba  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0c00  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0c0d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0c55  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0cc5  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0d34  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0d41  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0e03  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0e0f  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0e48  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0edc  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0fbe  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x1165  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x1254  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x1232  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0e5e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0e15  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0d36  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0c02  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0baf  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0b43 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0b29  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0a8d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0a72  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x09bc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0973  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x088f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0875  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x07d8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:294:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0704 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x05d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0520 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0447 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x05ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SubtitleStyleTab(final com.streamsync.app.settings.SubtitleStyle r94, final kotlin.jvm.functions.Function1<? super kotlin.jvm.functions.Function1<? super com.streamsync.app.settings.SubtitleStyle, com.streamsync.app.settings.SubtitleStyle>, kotlin.Unit> r95, final java.util.List<com.streamsync.app.data.model.SubtitleTrack> r96, final java.lang.String r97, final kotlin.jvm.functions.Function1<? super com.streamsync.app.data.model.SubtitleTrack, kotlin.Unit> r98, androidx.compose.runtime.Composer r99, final int r100) {
        /*
            Method dump skipped, instructions count: 4725
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.SubtitleStyleTab(com.streamsync.app.settings.SubtitleStyle, kotlin.jvm.functions.Function1, java.util.List, java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0729  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0959  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0965  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x099e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0aea  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0b95  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0d16  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0d79  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0de8  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0e06  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0e23  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0ea3  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0d67  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0b20  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x09b4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0adb  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x05d1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x05b6  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x056c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0507 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x04ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SyncTab(final long r124, final com.streamsync.app.ui.player.SubtitleSyncUiState r126, final boolean r127, final kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r128, final kotlin.jvm.functions.Function0<kotlin.Unit> r129, final kotlin.jvm.functions.Function0<kotlin.Unit> r130, final kotlin.jvm.functions.Function0<kotlin.Unit> r131, final kotlin.jvm.functions.Function0<kotlin.Unit> r132, final kotlin.jvm.functions.Function0<kotlin.Unit> r133, androidx.compose.runtime.Composer r134, final int r135) {
        /*
            Method dump skipped, instructions count: 3797
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.SyncTab(long, com.streamsync.app.ui.player.SubtitleSyncUiState, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    public static final int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        if (r1.equals(androidx.media3.common.MimeTypes.AUDIO_MPEG) == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00c0, code lost:
    
        return "MP3";
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004d, code lost:
    
        if (r1.equals(androidx.media3.common.MimeTypes.AUDIO_FLAC) == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00ce, code lost:
    
        return "FLAC";
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0063, code lost:
    
        if (r1.equals("video/vp9") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        if (r1.equals(androidx.media3.common.MimeTypes.AUDIO_RAW) == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0096, code lost:
    
        return "PCM";
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0093, code lost:
    
        if (r1.equals("audio/pcm") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00bc, code lost:
    
        if (r1.equals("audio/mpeg-l2") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ca, code lost:
    
        if (r1.equals("audio/x-flac") == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        if (r1.equals(androidx.media3.common.MimeTypes.VIDEO_VP9) == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0066, code lost:
    
        return "VP9";
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0016. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.lang.String codecLabel(java.lang.String r5, java.lang.String r6) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.codecLabel(java.lang.String, java.lang.String):java.lang.String");
    }

    public static final String channelLabel(int channels) {
        switch (channels) {
            case 0:
                return "—";
            case 1:
                return "Mono";
            case 2:
                return "Stereo";
            case 3:
            case 4:
            case 5:
            case 7:
            default:
                return channels + " kênh";
            case 6:
                return "5.1";
            case 8:
                return "7.1";
        }
    }

    public static final String bitrateLabel(int bitsPerSecond) {
        if (bitsPerSecond <= 0) {
            return "—";
        }
        if (bitsPerSecond < 1000000) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.0f kbps", Arrays.copyOf(new Object[]{Double.valueOf(bitsPerSecond / 1000.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("%.2f Mbps", Arrays.copyOf(new Object[]{Double.valueOf(bitsPerSecond / 1000000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        return format2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0112, code lost:
    
        if (r8 != null) goto L257;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x017a, code lost:
    
        if (r8 != null) goto L292;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.streamsync.app.ui.player.PlaybackInfo snapshotInfo(androidx.media3.exoplayer.ExoPlayer r29) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.snapshotInfo(androidx.media3.exoplayer.ExoPlayer):com.streamsync.app.ui.player.PlaybackInfo");
    }

    public static final void AudioPicker(Modifier modifier, final List<AudioTrackOption> list, final Integer activeIndex, final Function1<? super Integer, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(942699904);
        ComposerKt.sourceInformation($composer2, "C(AudioPicker)P(1,4!1,3)4332@193913L1168,4332@193841L1240:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(activeIndex) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(942699904, $dirty2, -1, "com.streamsync.app.ui.player.AudioPicker (PlayerScreen.kt:4331)");
            }
            $composer2.startReplaceableGroup(921275470);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(list) | (($dirty2 & 896) == 256) | (($dirty2 & 7168) == 2048);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                        invoke2(lazyListScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LazyListScope PickerSheet) {
                        Function1<Integer, Object> function12;
                        Intrinsics.checkNotNullParameter(PickerSheet, "$this$PickerSheet");
                        if (list.isEmpty()) {
                            LazyListScope.item$default(PickerSheet, "empty", null, ComposableSingletons$PlayerScreenKt.INSTANCE.m6649getLambda21$app_mobileDebug(), 2, null);
                        }
                        final List items$iv = list;
                        final Function2 key$iv = new Function2<Integer, AudioTrackOption, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1.1
                            public final Object invoke(int i3, AudioTrackOption t) {
                                Intrinsics.checkNotNullParameter(t, "t");
                                return i3 + ":" + t.getGroupIndex();
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Integer num, AudioTrackOption audioTrackOption) {
                                return invoke(num.intValue(), audioTrackOption);
                            }
                        };
                        final Integer num = activeIndex;
                        final Function1<Integer, Unit> function13 = function1;
                        int size = items$iv.size();
                        if (key$iv != null) {
                            function12 = new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1$invoke$$inlined$itemsIndexed$default$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num2) {
                                    return invoke(num2.intValue());
                                }

                                public final Object invoke(int index) {
                                    return Function2.this.invoke(Integer.valueOf(index), items$iv.get(index));
                                }
                            };
                        } else {
                            function12 = null;
                        }
                        PickerSheet.items(size, function12, new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1$invoke$$inlined$itemsIndexed$default$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num2) {
                                return invoke(num2.intValue());
                            }

                            public final Object invoke(int index) {
                                items$iv.get(index);
                                return null;
                            }
                        }, ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1$invoke$$inlined$itemsIndexed$default$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num2, Composer composer, Integer num3) {
                                invoke(lazyItemScope, num2.intValue(), composer, num3.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LazyItemScope $this$items, int it, Composer $composer3, int $changed2) {
                                Object value$iv2;
                                ComposerKt.sourceInformation($composer3, "C183@8439L26:LazyDsl.kt#428nma");
                                int $dirty3 = $changed2;
                                if (($changed2 & 14) == 0) {
                                    $dirty3 |= $composer3.changed($this$items) ? 4 : 2;
                                }
                                if (($changed2 & 112) == 0) {
                                    $dirty3 |= $composer3.changed(it) ? 32 : 16;
                                }
                                if (($dirty3 & 731) == 146 && $composer3.getSkipping()) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1091073711, $dirty3, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
                                }
                                int $changed3 = ($dirty3 & 14) | ($dirty3 & 112);
                                final AudioTrackOption track = (AudioTrackOption) items$iv.get(it);
                                $composer3.startReplaceableGroup(-1875102385);
                                ComposerKt.sourceInformation($composer3, "C*4354@195021L30,4350@194843L222:PlayerScreen.kt#nsz36g");
                                int groupIndex = track.getGroupIndex();
                                Integer num2 = num;
                                boolean isActive = num2 != null && groupIndex == num2.intValue();
                                String label = track.getLabel();
                                String str = isActive ? "Đang dùng" : null;
                                $composer3.startReplaceableGroup(1879182809);
                                ComposerKt.sourceInformation($composer3, "CC(remember):PlayerScreen.kt#9igjgp");
                                boolean invalid$iv2 = (((($changed3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && $composer3.changed(track)) || ($changed3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | $composer3.changed(function13);
                                Object it$iv2 = $composer3.rememberedValue();
                                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    final Function1 function14 = function13;
                                    value$iv2 = (Function0) new Function0<Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$1$1$2$1$1
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
                                            function14.invoke(Integer.valueOf(track.getGroupIndex()));
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv2);
                                } else {
                                    value$iv2 = it$iv2;
                                }
                                $composer3.endReplaceableGroup();
                                PlayerScreenKt.PickerRow(label, str, isActive, (Function0) value$iv2, $composer3, 0);
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer2.endReplaceableGroup();
            PickerSheet(modifier3, "Âm thanh", function0, (Function1) value$iv, $composer2, ($dirty2 & 14) | 48 | (($dirty2 >> 6) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$AudioPicker$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.AudioPicker(Modifier.this, list, activeIndex, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void SourcePicker(Modifier modifier, final List<StreamSource> list, final String currentUrl, final boolean loading, final Function1<? super StreamSource, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(939844269);
        ComposerKt.sourceInformation($composer2, "C(SourcePicker)P(2,5!2,4)4375@195574L913,4375@195500L987:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(currentUrl) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(loading) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if ((74899 & $dirty2) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(939844269, $dirty2, -1, "com.streamsync.app.ui.player.SourcePicker (PlayerScreen.kt:4374)");
            }
            $composer2.startReplaceableGroup(1707696704);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 7168) == 2048) | $composer2.changedInstance(list) | (($dirty2 & 896) == 256) | ((57344 & $dirty2) == 16384);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                        invoke2(lazyListScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LazyListScope PickerSheet) {
                        Function1<Integer, Object> function12;
                        Intrinsics.checkNotNullParameter(PickerSheet, "$this$PickerSheet");
                        if (loading && list.isEmpty()) {
                            LazyListScope.item$default(PickerSheet, "loading", null, ComposableSingletons$PlayerScreenKt.INSTANCE.m6650getLambda22$app_mobileDebug(), 2, null);
                        }
                        final List items$iv = list;
                        final Function2 key$iv = new Function2<Integer, StreamSource, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1.1
                            public final Object invoke(int i3, StreamSource s) {
                                Intrinsics.checkNotNullParameter(s, "s");
                                return i3 + ":" + s.getStreamUrl();
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Integer num, StreamSource streamSource) {
                                return invoke(num.intValue(), streamSource);
                            }
                        };
                        final String str = currentUrl;
                        final Function1<StreamSource, Unit> function13 = function1;
                        int size = items$iv.size();
                        if (key$iv != null) {
                            function12 = new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1$invoke$$inlined$itemsIndexed$default$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Object invoke(int index) {
                                    return Function2.this.invoke(Integer.valueOf(index), items$iv.get(index));
                                }
                            };
                        } else {
                            function12 = null;
                        }
                        PickerSheet.items(size, function12, new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1$invoke$$inlined$itemsIndexed$default$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                return invoke(num.intValue());
                            }

                            public final Object invoke(int index) {
                                items$iv.get(index);
                                return null;
                            }
                        }, ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1$invoke$$inlined$itemsIndexed$default$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LazyItemScope $this$items, int it, Composer $composer3, int $changed2) {
                                Object value$iv2;
                                ComposerKt.sourceInformation($composer3, "C183@8439L26:LazyDsl.kt#428nma");
                                int $dirty3 = $changed2;
                                if (($changed2 & 14) == 0) {
                                    $dirty3 |= $composer3.changed($this$items) ? 4 : 2;
                                }
                                if (($changed2 & 112) == 0) {
                                    $dirty3 |= $composer3.changed(it) ? 32 : 16;
                                }
                                if (($dirty3 & 731) == 146 && $composer3.getSkipping()) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1091073711, $dirty3, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:183)");
                                }
                                int $changed3 = ($dirty3 & 14) | ($dirty3 & 112);
                                final StreamSource source = (StreamSource) items$iv.get(it);
                                $composer3.startReplaceableGroup(-1640208810);
                                ComposerKt.sourceInformation($composer3, "C*4394@196437L20,4387@196054L417:PlayerScreen.kt#nsz36g");
                                String name = source.getName();
                                if (name == null) {
                                    name = source.getStreamUrl();
                                }
                                String[] strArr = new String[2];
                                String it2 = source.getAddonName();
                                String str2 = null;
                                if (it2 == null || !(!StringsKt.isBlank(it2))) {
                                    it2 = null;
                                }
                                strArr[0] = it2;
                                String it3 = source.getQuality();
                                if (it3 == null || !(!StringsKt.isBlank(it3))) {
                                    it3 = null;
                                }
                                strArr[1] = it3;
                                String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) strArr), " • ", null, null, 0, null, null, 62, null);
                                if (!StringsKt.isBlank(joinToString$default)) {
                                    str2 = joinToString$default;
                                }
                                String str3 = str2;
                                boolean areEqual = Intrinsics.areEqual(source.getStreamUrl(), str);
                                $composer3.startReplaceableGroup(-1438371424);
                                ComposerKt.sourceInformation($composer3, "CC(remember):PlayerScreen.kt#9igjgp");
                                boolean invalid$iv2 = (((($changed3 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && $composer3.changed(source)) || ($changed3 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | $composer3.changed(function13);
                                Object it$iv2 = $composer3.rememberedValue();
                                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                    final Function1 function14 = function13;
                                    value$iv2 = (Function0) new Function0<Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$1$1$2$4$1
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
                                            function14.invoke(source);
                                        }
                                    };
                                    $composer3.updateRememberedValue(value$iv2);
                                } else {
                                    value$iv2 = it$iv2;
                                }
                                $composer3.endReplaceableGroup();
                                PlayerScreenKt.PickerRow(name, str3, areEqual, (Function0) value$iv2, $composer3, 0);
                                $composer3.endReplaceableGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer2.endReplaceableGroup();
            PickerSheet(modifier3, "Nguồn phát", function0, (Function1) value$iv, $composer2, ($dirty2 & 14) | 48 | (($dirty2 >> 9) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SourcePicker$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.SourcePicker(Modifier.this, list, currentUrl, loading, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void EpisodePicker(Modifier modifier, final List<Integer> list, final List<SeriesVideo> list2, final Function1<? super SeriesVideo, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(554470944);
        ComposerKt.sourceInformation($composer2, "C(EpisodePicker)P(1,4!1,3)4415@196999L48,4417@197127L2068,4417@197053L2142:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(list2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(554470944, $dirty2, -1, "com.streamsync.app.ui.player.EpisodePicker (PlayerScreen.kt:4413)");
            }
            $composer2.startReplaceableGroup(1421555795);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(list);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState pickedSeason$delegate = (MutableState) value$iv;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(1421561911);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(list2) | $composer2.changedInstance(list) | $composer2.changed(pickedSeason$delegate) | (($dirty2 & 7168) == 2048);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                        invoke2(lazyListScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x007d A[SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[SYNTHETIC] */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke2(androidx.compose.foundation.lazy.LazyListScope r14) {
                        /*
                            r13 = this;
                            java.lang.String r0 = "$this$PickerSheet"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
                            java.util.List<com.streamsync.app.data.model.SeriesVideo> r0 = r2
                            boolean r0 = r0.isEmpty()
                            if (r0 == 0) goto L1c
                            com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt r0 = com.streamsync.app.ui.player.ComposableSingletons$PlayerScreenKt.INSTANCE
                            kotlin.jvm.functions.Function3 r4 = r0.m6651getLambda23$app_mobileDebug()
                            r5 = 2
                            r6 = 0
                            java.lang.String r2 = "empty"
                            r3 = 0
                            r1 = r14
                            androidx.compose.foundation.lazy.LazyListScope.item$default(r1, r2, r3, r4, r5, r6)
                        L1c:
                            java.util.List<java.lang.Integer> r0 = r3
                            int r0 = r0.size()
                            r1 = 1
                            if (r0 <= r1) goto L41
                            com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$1 r0 = new com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$1
                            java.util.List<java.lang.Integer> r2 = r3
                            androidx.compose.runtime.MutableState<java.lang.Integer> r3 = r4
                            r0.<init>()
                            r2 = -1031889092(0xffffffffc27e9f3c, float:-63.655502)
                            androidx.compose.runtime.internal.ComposableLambda r0 = androidx.compose.runtime.internal.ComposableLambdaKt.composableLambdaInstance(r2, r1, r0)
                            r5 = r0
                            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
                            r6 = 2
                            r7 = 0
                            java.lang.String r3 = "seasons"
                            r4 = 0
                            r2 = r14
                            androidx.compose.foundation.lazy.LazyListScope.item$default(r2, r3, r4, r5, r6, r7)
                        L41:
                            java.util.List<com.streamsync.app.data.model.SeriesVideo> r0 = r2
                            java.lang.Iterable r0 = (java.lang.Iterable) r0
                            androidx.compose.runtime.MutableState<java.lang.Integer> r2 = r4
                            r3 = 0
                            java.util.ArrayList r4 = new java.util.ArrayList
                            r4.<init>()
                            java.util.Collection r4 = (java.util.Collection) r4
                            r5 = r0
                            r6 = 0
                            java.util.Iterator r7 = r5.iterator()
                        L55:
                            boolean r8 = r7.hasNext()
                            if (r8 == 0) goto L81
                            java.lang.Object r8 = r7.next()
                            r9 = r8
                            com.streamsync.app.data.model.SeriesVideo r9 = (com.streamsync.app.data.model.SeriesVideo) r9
                            r10 = 0
                            java.lang.Integer r11 = com.streamsync.app.ui.player.PlayerScreenKt.access$EpisodePicker$lambda$165(r2)
                            if (r11 == 0) goto L7a
                            java.lang.Integer r11 = r9.getSeason()
                            java.lang.Integer r12 = com.streamsync.app.ui.player.PlayerScreenKt.access$EpisodePicker$lambda$165(r2)
                            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r12)
                            if (r11 == 0) goto L78
                            goto L7a
                        L78:
                            r11 = 0
                            goto L7b
                        L7a:
                            r11 = r1
                        L7b:
                            if (r11 == 0) goto L55
                            r4.add(r8)
                            goto L55
                        L81:
                            r2 = r4
                            java.util.List r2 = (java.util.List) r2
                            r0 = r2
                            com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$2 r2 = new kotlin.jvm.functions.Function2<java.lang.Integer, com.streamsync.app.data.model.SeriesVideo, java.lang.Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.2
                                static {
                                    /*
                                        com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$2 r0 = new com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$2
                                        r0.<init>()
                                        
                                        // error: 0x0005: SPUT (r0 I:com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$2) com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.2.INSTANCE com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$2
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.AnonymousClass2.<clinit>():void");
                                }

                                {
                                    /*
                                        r1 = this;
                                        r0 = 2
                                        r1.<init>(r0)
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.AnonymousClass2.<init>():void");
                                }

                                public final java.lang.Object invoke(int r4, com.streamsync.app.data.model.SeriesVideo r5) {
                                    /*
                                        r3 = this;
                                        java.lang.String r0 = "e"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                                        java.lang.String r0 = r5.getId()
                                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                                        r1.<init>()
                                        java.lang.StringBuilder r1 = r1.append(r4)
                                        java.lang.String r2 = ":"
                                        java.lang.StringBuilder r1 = r1.append(r2)
                                        java.lang.StringBuilder r0 = r1.append(r0)
                                        java.lang.String r0 = r0.toString()
                                        return r0
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.AnonymousClass2.invoke(int, com.streamsync.app.data.model.SeriesVideo):java.lang.Object");
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ java.lang.Object invoke(java.lang.Integer r3, com.streamsync.app.data.model.SeriesVideo r4) {
                                    /*
                                        r2 = this;
                                        r0 = r3
                                        java.lang.Number r0 = (java.lang.Number) r0
                                        int r0 = r0.intValue()
                                        r1 = r4
                                        com.streamsync.app.data.model.SeriesVideo r1 = (com.streamsync.app.data.model.SeriesVideo) r1
                                        java.lang.Object r0 = r2.invoke(r0, r1)
                                        return r0
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.AnonymousClass2.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                                }
                            }
                            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                            kotlin.jvm.functions.Function1<com.streamsync.app.data.model.SeriesVideo, kotlin.Unit> r3 = r5
                            r4 = r14
                            r5 = 0
                            int r6 = r0.size()
                            if (r2 == 0) goto La0
                            com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$1 r7 = new com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$1
                            r7.<init>()
                            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
                            goto La1
                        La0:
                            r7 = 0
                        La1:
                            com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$2 r8 = new com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$2
                            r8.<init>()
                            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
                            com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$3 r9 = new com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1$invoke$$inlined$itemsIndexed$default$3
                            r9.<init>()
                            r3 = -1091073711(0xffffffffbef78951, float:-0.48346952)
                            androidx.compose.runtime.internal.ComposableLambda r1 = androidx.compose.runtime.internal.ComposableLambdaKt.composableLambdaInstance(r3, r1, r9)
                            kotlin.jvm.functions.Function4 r1 = (kotlin.jvm.functions.Function4) r1
                            r4.items(r6, r7, r8, r1)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$1$1.invoke2(androidx.compose.foundation.lazy.LazyListScope):void");
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer2.endReplaceableGroup();
            PickerSheet(modifier3, "Mùa và tập", function0, (Function1) value$iv2, $composer2, ($dirty2 & 14) | 48 | (($dirty2 >> 6) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$EpisodePicker$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.EpisodePicker(Modifier.this, list, list2, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final Integer EpisodePicker$lambda$165(MutableState<Integer> mutableState) {
        MutableState<Integer> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    public static final void VideoScalePicker(Modifier modifier, final VideoScaleMode current, final String videoRatioLabel, final Function1<? super VideoScaleMode, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(348016945);
        ComposerKt.sourceInformation($composer2, "C(VideoScalePicker)P(1!1,4,3)4482@199816L1895,4482@199738L1973:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(current) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changed(videoRatioLabel) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(348016945, $dirty2, -1, "com.streamsync.app.ui.player.VideoScalePicker (PlayerScreen.kt:4481)");
            }
            $composer2.startReplaceableGroup(-495471926);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 896) == 256) | (($dirty2 & 7168) == 2048) | (($dirty2 & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<LazyListScope, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                        invoke2(lazyListScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LazyListScope PickerSheet) {
                        Function1<Integer, Object> function12;
                        Intrinsics.checkNotNullParameter(PickerSheet, "$this$PickerSheet");
                        if (videoRatioLabel != null) {
                            final String str = videoRatioLabel;
                            LazyListScope.item$default(PickerSheet, "ratio", null, ComposableLambdaKt.composableLambdaInstance(-1167087010, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                                    invoke(lazyItemScope, composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LazyItemScope item, Composer $composer3, int $changed2) {
                                    long m3407copywmQWz5c;
                                    Intrinsics.checkNotNullParameter(item, "$this$item");
                                    ComposerKt.sourceInformation($composer3, "C4487@200012L10,4485@199907L290:PlayerScreen.kt#nsz36g");
                                    if (($changed2 & 17) != 16 || !$composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1167087010, $changed2, -1, "com.streamsync.app.ui.player.VideoScalePicker.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4485)");
                                        }
                                        String str2 = "Video đang phát: " + str;
                                        TextStyle labelSmall = MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getLabelSmall();
                                        m3407copywmQWz5c = Color.m3407copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m3411getAlphaimpl(r2) : 0.7f, (r12 & 2) != 0 ? Color.m3415getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m3414getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m3412getBlueimpl(Color.INSTANCE.m3446getWhite0d7_KjU()) : 0.0f);
                                        TextKt.m2127Text4IGK_g(str2, PaddingKt.m565paddingVpY3zN4(Modifier.INSTANCE, Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(6)), m3407copywmQWz5c, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, labelSmall, $composer3, 432, 0, 65528);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer3.skipToGroupEnd();
                                }
                            }), 2, null);
                        }
                        final List items$iv = CollectionsKt.toList(VideoScaleMode.getEntries());
                        final Function1 key$iv = new Function1<VideoScaleMode, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1.2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(VideoScaleMode it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                return it.name();
                            }
                        };
                        final Function1<VideoScaleMode, Unit> function13 = function1;
                        final VideoScaleMode videoScaleMode = current;
                        final Function1 contentType$iv = new Function1() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1$invoke$$inlined$items$default$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                                return invoke((VideoScaleMode) p1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Void invoke(VideoScaleMode videoScaleMode2) {
                                return null;
                            }
                        };
                        int size = items$iv.size();
                        if (key$iv != null) {
                            function12 = new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1$invoke$$inlined$items$default$2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Object invoke(int index) {
                                    return Function1.this.invoke(items$iv.get(index));
                                }
                            };
                        } else {
                            function12 = null;
                        }
                        PickerSheet.items(size, function12, new Function1<Integer, Object>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1$invoke$$inlined$items$default$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                return invoke(num.intValue());
                            }

                            public final Object invoke(int index) {
                                return Function1.this.invoke(items$iv.get(index));
                            }
                        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1$invoke$$inlined$items$default$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:49:0x02ba  */
                            /* JADX WARN: Removed duplicated region for block: B:52:0x02c6  */
                            /* JADX WARN: Removed duplicated region for block: B:60:0x038d  */
                            /* JADX WARN: Removed duplicated region for block: B:63:0x0427  */
                            /* JADX WARN: Removed duplicated region for block: B:66:0x046c  */
                            /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
                            /* JADX WARN: Removed duplicated region for block: B:69:0x0399  */
                            /* JADX WARN: Removed duplicated region for block: B:72:0x02cc  */
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final void invoke(androidx.compose.foundation.lazy.LazyItemScope r80, int r81, androidx.compose.runtime.Composer r82, int r83) {
                                /*
                                    Method dump skipped, instructions count: 1136
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$1$1$invoke$$inlined$items$default$4.invoke(androidx.compose.foundation.lazy.LazyItemScope, int, androidx.compose.runtime.Composer, int):void");
                            }
                        }));
                        LazyListScope.item$default(PickerSheet, "pad", null, ComposableSingletons$PlayerScreenKt.INSTANCE.m6652getLambda24$app_mobileDebug(), 2, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            $composer2.endReplaceableGroup();
            PickerSheet(modifier3, "Tỉ lệ hiển thị", function0, (Function1) value$iv, $composer2, ($dirty2 & 14) | 48 | (($dirty2 >> 6) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$VideoScalePicker$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.VideoScalePicker(Modifier.this, current, videoRatioLabel, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void PickerSheet(Modifier modifier, final String title, final Function0<Unit> function0, final Function1<? super LazyListScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-1522456541);
        ComposerKt.sourceInformation($composer2, "C(PickerSheet)P(1,3,2)4541@201988L853:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(title) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1522456541, $dirty2, -1, "com.streamsync.app.ui.player.PickerSheet (PlayerScreen.kt:4540)");
            }
            SurfaceKt.m1979SurfaceT9BRK9s(modifier4, RoundedCornerShapeKt.m836RoundedCornerShapea9UjIt4$default(Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(16), 0.0f, 0.0f, 12, null), ColorKt.Color(4061796890L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1104433688, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$PickerSheet$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:40:0x03b1  */
                /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r71, int r72) {
                    /*
                        Method dump skipped, instructions count: 949
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$PickerSheet$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, ($dirty2 & 14) | 12583296, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$PickerSheet$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.PickerSheet(Modifier.this, title, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0245  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PickerRow(final java.lang.String r77, final java.lang.String r78, final boolean r79, final kotlin.jvm.functions.Function0<kotlin.Unit> r80, androidx.compose.runtime.Composer r81, final int r82) {
        /*
            Method dump skipped, instructions count: 1142
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.PickerRow(java.lang.String, java.lang.String, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    public static final void EpisodeSourceSheet(Modifier modifier, final String episodeLabel, final List<StreamSource> list, final boolean loading, final Function1<? super StreamSource, Unit> function1, final Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-549958925);
        ComposerKt.sourceInformation($composer2, "C(EpisodeSourceSheet)P(2!1,5!1,4)4626@204676L4080:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(episodeLabel) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(loading) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-549958925, $dirty, -1, "com.streamsync.app.ui.player.EpisodeSourceSheet (PlayerScreen.kt:4625)");
            }
            SurfaceKt.m1979SurfaceT9BRK9s(modifier4, RoundedCornerShapeKt.m836RoundedCornerShapea9UjIt4$default(Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(16), 0.0f, 0.0f, 12, null), ColorKt.Color(4061796890L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -2006762440, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$EpisodeSourceSheet$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x01f4  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0200  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x032e  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x033a  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x0373  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x04c5  */
                /* JADX WARN: Removed duplicated region for block: B:49:0x07a8  */
                /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:52:0x0529  */
                /* JADX WARN: Removed duplicated region for block: B:77:0x0389  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x0340  */
                /* JADX WARN: Removed duplicated region for block: B:81:0x0206  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r119, int r120) {
                    /*
                        Method dump skipped, instructions count: 1964
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$EpisodeSourceSheet$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, ($dirty & 14) | 12583296, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$EpisodeSourceSheet$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    PlayerScreenKt.EpisodeSourceSheet(Modifier.this, episodeLabel, list, loading, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void SubtitlePicker(Modifier modifier, final List<SubtitleTrack> list, final String activeUrl, final boolean loading, final Function1<? super SubtitleTrack, Unit> function1, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, List<PlayerSubtitleTrack> list2, Integer activePlayerIndex, Function1<? super Integer, Unit> function12, Function0<Unit> function04, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        List playerTracks;
        Function1 onSelectPlayerTrack;
        Function0 onDisablePlayerTrack;
        Composer $composer2;
        Integer activePlayerIndex2;
        Composer $composer3 = $composer.startRestartGroup(-839096222);
        ComposerKt.sourceInformation($composer3, "C(SubtitlePicker)P(3,11,1,2,8,5,7,4,10!1,9)4738@209353L11,4736@209285L7585:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer3.changed(activeUrl) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(loading) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function03) ? 8388608 : 4194304;
        }
        int i3 = i & 256;
        if (i3 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(list2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i4 = i & 512;
        if (i4 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changed(activePlayerIndex) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i5 = i & 1024;
        if (i5 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function12) ? 4 : 2;
        }
        int i6 = i & 2048;
        if (i6 != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function04) ? 32 : 16;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty1 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            playerTracks = list2;
            activePlayerIndex2 = activePlayerIndex;
            onSelectPlayerTrack = function12;
            onDisablePlayerTrack = function04;
            $composer2 = $composer3;
        } else {
            modifier2 = i2 != 0 ? Modifier.INSTANCE : modifier;
            playerTracks = i3 != 0 ? CollectionsKt.emptyList() : list2;
            Integer activePlayerIndex3 = i4 != 0 ? null : activePlayerIndex;
            onSelectPlayerTrack = i5 != 0 ? new Function1<Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SubtitlePicker$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int it) {
                }
            } : function12;
            onDisablePlayerTrack = i6 != 0 ? new Function0<Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SubtitlePicker$2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            } : function04;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-839096222, $dirty, $dirty1, "com.streamsync.app.ui.player.SubtitlePicker (PlayerScreen.kt:4735)");
            }
            final List list3 = playerTracks;
            final Function0 function05 = onDisablePlayerTrack;
            final Integer num = activePlayerIndex3;
            final Function1 function13 = onSelectPlayerTrack;
            $composer2 = $composer3;
            SurfaceKt.m1979SurfaceT9BRK9s(modifier2, RoundedCornerShapeKt.m836RoundedCornerShapea9UjIt4$default(Dp.m5736constructorimpl(16), Dp.m5736constructorimpl(16), 0.0f, 0.0f, 12, null), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer3, -1969339587, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SubtitlePicker$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num2) {
                    invoke(composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0208  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0214  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0316  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x03f4  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x045f  */
                /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:47:0x0355  */
                /* JADX WARN: Removed duplicated region for block: B:50:0x021a  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r92, int r93) {
                    /*
                        Method dump skipped, instructions count: 1123
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$SubtitlePicker$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer3, ($dirty & 14) | 12582912, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            activePlayerIndex2 = activePlayerIndex3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final List list4 = playerTracks;
            final Integer num2 = activePlayerIndex2;
            final Function1 function14 = onSelectPlayerTrack;
            final Function0 function06 = onDisablePlayerTrack;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$SubtitlePicker$4
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num3) {
                    invoke(composer, num3.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    PlayerScreenKt.SubtitlePicker(Modifier.this, list, activeUrl, loading, function1, function0, function02, function03, list4, num2, function14, function06, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void PlaybackInfoPanel(final ExoPlayer player, final StreamSource source, final Function0<Unit> function0, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object value$iv;
        Object value$iv2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(447295403);
        ComposerKt.sourceInformation($composer2, "C(PlaybackInfoPanel)P(2,3,1)4901@217046L50,4903@217207L104,4903@217184L127,4910@217395L6,4910@217317L3221:PlayerScreen.kt#nsz36g");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(player) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(source) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if (($changed & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(447295403, $dirty2, -1, "com.streamsync.app.ui.player.PlaybackInfoPanel (PlayerScreen.kt:4900)");
            }
            $composer2.startReplaceableGroup(200286311);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(snapshotInfo(player), null, 2, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            final MutableState info$delegate = (MutableState) value$iv;
            $composer2.endReplaceableGroup();
            $composer2.startReplaceableGroup(200291517);
            ComposerKt.sourceInformation($composer2, "CC(remember):PlayerScreen.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(player);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new PlayerScreenKt$PlaybackInfoPanel$1$1(player, info$delegate, null);
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            $composer2.endReplaceableGroup();
            EffectsKt.LaunchedEffect(player, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer2, $dirty2 & 14);
            SurfaceKt.m1979SurfaceT9BRK9s(modifier4, MaterialTheme.INSTANCE.getShapes($composer2, MaterialTheme.$stable).getMedium(), ColorKt.Color(4028242458L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda($composer2, -1043843600, true, new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$PlaybackInfoPanel$2
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

                /* JADX WARN: Removed duplicated region for block: B:35:0x03d2  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x03de  */
                /* JADX WARN: Removed duplicated region for block: B:46:0x04ac  */
                /* JADX WARN: Removed duplicated region for block: B:51:0x0548  */
                /* JADX WARN: Removed duplicated region for block: B:54:0x0597  */
                /* JADX WARN: Removed duplicated region for block: B:59:0x05df  */
                /* JADX WARN: Removed duplicated region for block: B:62:0x063d  */
                /* JADX WARN: Removed duplicated region for block: B:65:0x0689  */
                /* JADX WARN: Removed duplicated region for block: B:68:0x0706  */
                /* JADX WARN: Removed duplicated region for block: B:71:0x07e6  */
                /* JADX WARN: Removed duplicated region for block: B:74:0x082f  */
                /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:77:0x07f3  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x0659  */
                /* JADX WARN: Removed duplicated region for block: B:79:0x0600  */
                /* JADX WARN: Removed duplicated region for block: B:80:0x056e  */
                /* JADX WARN: Removed duplicated region for block: B:84:0x03e4  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r92, int r93) {
                    /*
                        Method dump skipped, instructions count: 2099
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt$PlaybackInfoPanel$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }), $composer2, (($dirty2 >> 9) & 14) | 12583296, 120);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.streamsync.app.ui.player.PlayerScreenKt$PlaybackInfoPanel$3
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
                    PlayerScreenKt.PlaybackInfoPanel(ExoPlayer.this, source, function0, modifier5, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final PlaybackInfo PlaybackInfoPanel$lambda$172(MutableState<PlaybackInfo> mutableState) {
        MutableState<PlaybackInfo> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x009d, code lost:
    
        if (r2 == null) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b8, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b9, code lost:
    
        InfoRow("Provider", r4, r15, 6);
        r15.startReplaceableGroup(66860450);
        androidx.compose.runtime.ComposerKt.sourceInformation(r15, "4994@221370L35");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ce, code lost:
    
        if (r29.getAddonName() == null) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d0, code lost:
    
        r2 = r29.getAddonId();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d6, code lost:
    
        if (r2 == null) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00dc, code lost:
    
        if (kotlin.text.StringsKt.isBlank(r2) == false) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00df, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e2, code lost:
    
        if (r2 != false) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e4, code lost:
    
        InfoRow("Id addon", r29.getAddonId(), r15, 6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00e1, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ed, code lost:
    
        r15.endReplaceableGroup();
        r2 = r29.getQuality();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f4, code lost:
    
        if (r2 == null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0100, code lost:
    
        if ((!kotlin.text.StringsKt.isBlank(r2)) == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0104, code lost:
    
        r15.startReplaceableGroup(66865647);
        androidx.compose.runtime.ComposerKt.sourceInformation(r15, "*4996@221466L25");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x010f, code lost:
    
        if (r2 != null) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0112, code lost:
    
        InfoRow("Chất lượng", r2, r15, 6);
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x011a, code lost:
    
        r15.endReplaceableGroup();
        r2 = r29.getMimeType();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0121, code lost:
    
        if (r2 == null) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x012d, code lost:
    
        if ((!kotlin.text.StringsKt.isBlank(r2)) == false) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0131, code lost:
    
        r15.startReplaceableGroup(66868306);
        androidx.compose.runtime.ComposerKt.sourceInformation(r15, "*4997@221549L28");
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x013c, code lost:
    
        if (r2 != null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013f, code lost:
    
        InfoRow("Kiểu nội dung", r2, r15, 6);
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0147, code lost:
    
        r15.endReplaceableGroup();
        androidx.compose.material3.TextKt.m2127Text4IGK_g("URL stream", androidx.compose.foundation.layout.PaddingKt.m568paddingqDBjuR0$default(androidx.compose.ui.Modifier.INSTANCE, 0.0f, androidx.compose.ui.unit.Dp.m5736constructorimpl(6), 0.0f, 0.0f, 13, null), androidx.compose.ui.graphics.ColorKt.Color(4289773253L), 0L, (androidx.compose.ui.text.font.FontStyle) null, (androidx.compose.ui.text.font.FontWeight) null, (androidx.compose.ui.text.font.FontFamily) null, 0L, (androidx.compose.ui.text.style.TextDecoration) null, (androidx.compose.ui.text.style.TextAlign) null, 0L, 0, false, 0, 0, (kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit>) null, androidx.compose.material3.MaterialTheme.INSTANCE.getTypography(r15, androidx.compose.material3.MaterialTheme.$stable).getBodySmall(), r15, 438, 0, 65528);
        r5 = r15;
        androidx.compose.foundation.text.selection.SelectionContainerKt.SelectionContainer(null, androidx.compose.runtime.internal.ComposableLambdaKt.composableLambda(r5, 139063843, true, new com.streamsync.app.ui.player.PlayerScreenKt$ProviderSection$8()), r5, 48, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01c4, code lost:
    
        if (androidx.compose.runtime.ComposerKt.isTraceInProgress() == false) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01c6, code lost:
    
        androidx.compose.runtime.ComposerKt.traceEventEnd();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0130, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0103, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00b5, code lost:
    
        if (r2 == null) goto L134;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProviderSection(final com.streamsync.app.data.model.StreamSource r29, androidx.compose.runtime.Composer r30, final int r31) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.ProviderSection(com.streamsync.app.data.model.StreamSource, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x011c, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1.rememberedValue(), java.lang.Integer.valueOf(r55)) != false) goto L87;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void InfoRow(final java.lang.String r78, final java.lang.String r79, androidx.compose.runtime.Composer r80, final int r81) {
        /*
            Method dump skipped, instructions count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerScreenKt.InfoRow(java.lang.String, java.lang.String, androidx.compose.runtime.Composer, int):void");
    }
}
