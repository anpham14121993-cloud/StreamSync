package com.streamsync.app.ui.player;

import android.content.Context;
import android.os.SystemClock;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.media3.common.Player;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.Tracks;
import com.google.common.collect.ImmutableList;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.model.EpisodeItemKt;
import com.streamsync.app.data.model.MediaItem;
import com.streamsync.app.data.model.MediaType;
import com.streamsync.app.data.model.SeriesVideo;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.data.remote.RetrofitClient;
import com.streamsync.app.data.repository.AddonRepository;
import com.streamsync.app.data.repository.ContentRepository;
import com.streamsync.app.data.repository.StreamSelectionStore;
import com.streamsync.app.data.repository.SyncRepository;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.PlaybackSettingsStore;
import com.streamsync.app.settings.SubtitleStyleStore;
import com.streamsync.app.subtitle.SubtitleOffsetStore;
import com.streamsync.app.subtitle.SubtitleTranslator;
import com.streamsync.app.subtitle.audio.PlayerAudioCapture;
import com.streamsync.app.subtitle.autosync.GeminiApiKeyStore;
import com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine;
import com.streamsync.app.util.FileLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.OkHttpClient;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\u008c\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b)\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 õ\u00012\u00020\u0001:\u0002õ\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010£\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010¤\u0001\u001a\u00020!J\u0011\u0010¥\u0001\u001a\u00030¦\u00012\u0007\u0010§\u0001\u001a\u00020!J\u001b\u0010¨\u0001\u001a\u00030¦\u00012\u0006\u0010h\u001a\u00020!2\u0007\u0010©\u0001\u001a\u00020\u000eH\u0002J\u0012\u0010ª\u0001\u001a\u00030¦\u00012\u0006\u0010h\u001a\u00020!H\u0002J\u001e\u0010«\u0001\u001a\u00030¦\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\u0017\u0010°\u0001\u001a\t\u0012\u0005\u0012\u00030±\u00010\u0010H\u0082@¢\u0006\u0003\u0010²\u0001J\b\u0010³\u0001\u001a\u00030¦\u0001J\b\u0010´\u0001\u001a\u00030¦\u0001J\u0013\u0010µ\u0001\u001a\u00020\u00052\b\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u0012\u0010¸\u0001\u001a\u00030¦\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u0001J\b\u0010¹\u0001\u001a\u00030¦\u0001J\u0012\u0010º\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010»\u0001\u001a\u00020\u001aJ&\u0010¼\u0001\u001a\u00020\u00052\u0007\u0010½\u0001\u001a\u00020\u00052\u000b\b\u0002\u0010¾\u0001\u001a\u0004\u0018\u00010\u0005H\u0082@¢\u0006\u0003\u0010¿\u0001J\n\u0010À\u0001\u001a\u00030¦\u0001H\u0002J\u0011\u0010Á\u0001\u001a\u00030¦\u0001H\u0082@¢\u0006\u0003\u0010²\u0001J\u001a\u0010Â\u0001\u001a\u00030¦\u00012\u0007\u0010¤\u0001\u001a\u00020!2\u0007\u0010Ã\u0001\u001a\u00020!J\b\u0010Ä\u0001\u001a\u00030¦\u0001J \u0010Å\u0001\u001a\u00030¦\u00012\u000b\b\u0002\u0010Æ\u0001\u001a\u0004\u0018\u00010\u00172\t\b\u0002\u0010Ç\u0001\u001a\u00020\u000eJ\b\u0010È\u0001\u001a\u00030¦\u0001J\u0011\u0010É\u0001\u001a\u00030¦\u00012\u0007\u0010»\u0001\u001a\u00020\u001aJ#\u0010Ê\u0001\u001a\u00030¦\u00012\u0007\u0010Ë\u0001\u001a\u00020\u00132\u0007\u0010Ì\u0001\u001a\u00020\u0005H\u0082@¢\u0006\u0003\u0010Í\u0001J\u0012\u0010Î\u0001\u001a\u00020\u000e2\u0007\u0010Ï\u0001\u001a\u00020\u0005H\u0002J-\u0010Ð\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\r\u0010Ñ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130\u00102\r\u0010Ò\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J\u000b\u0010Ó\u0001\u001a\u0004\u0018\u00010\u0005H\u0002J\u0007\u0010Ô\u0001\u001a\u00020\u000eJ\u0011\u0010Õ\u0001\u001a\u00030¦\u00012\u0007\u0010Ö\u0001\u001a\u00020\u000eJ\n\u0010×\u0001\u001a\u00030¦\u0001H\u0014J\u0011\u0010Ø\u0001\u001a\u00030¦\u00012\u0007\u0010Ù\u0001\u001a\u00020\u000eJ\u001c\u0010Ú\u0001\u001a\u00030¦\u00012\u0007\u0010Û\u0001\u001a\u00020\u00052\t\b\u0002\u0010Ü\u0001\u001a\u00020\u000eJ\u001a\u0010Ý\u0001\u001a\u00030¦\u00012\u0007\u0010¤\u0001\u001a\u00020!2\u0007\u0010Ã\u0001\u001a\u00020!J\u0012\u0010Þ\u0001\u001a\u00030¦\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u0001J\u0013\u0010ß\u0001\u001a\u00020\u00052\b\u0010à\u0001\u001a\u00030á\u0001H\u0002J\u0011\u0010â\u0001\u001a\u00030¦\u00012\u0007\u0010Æ\u0001\u001a\u00020\u0017J\n\u0010ã\u0001\u001a\u00030¦\u0001H\u0002J\u001c\u0010ä\u0001\u001a\u00030¦\u00012\u0007\u0010¤\u0001\u001a\u00020!2\u0007\u0010Ã\u0001\u001a\u00020!H\u0002J\b\u0010å\u0001\u001a\u00030¦\u0001J\b\u0010æ\u0001\u001a\u00030¦\u0001J\b\u0010ç\u0001\u001a\u00030¦\u0001J\u001b\u0010è\u0001\u001a\u00030¦\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010é\u0001\u001a\u00020\tJ\u001b\u0010ê\u0001\u001a\u00030¦\u00012\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010é\u0001\u001a\u00020\tJ\u0011\u0010ë\u0001\u001a\u00030¦\u00012\u0007\u0010Ë\u0001\u001a\u00020\u0013J\u0014\u0010ì\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010Ï\u0001\u001a\u00020\u0005H\u0002J\u0011\u0010í\u0001\u001a\u00030¦\u00012\u0007\u0010¤\u0001\u001a\u00020!J\n\u0010î\u0001\u001a\u00030¦\u0001H\u0002J\u0012\u0010ï\u0001\u001a\u00030¦\u00012\u0006\u0010h\u001a\u00020!H\u0002J\b\u0010ð\u0001\u001a\u00030¦\u0001J\u001a\u0010ñ\u0001\u001a\u00030¦\u00012\u0007\u0010Æ\u0001\u001a\u00020\u00172\u0007\u0010ò\u0001\u001a\u00020!J\u000e\u0010ó\u0001\u001a\u000200*\u00030ô\u0001H\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00100\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t02¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0019\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t02¢\u0006\b\n\u0000\u001a\u0004\b6\u00104R\u0019\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\b\n\u0000\u001a\u0004\b8\u00104R\u0010\u00109\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\b\n\u0000\u001a\u0004\b<\u00104R\u0010\u0010=\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010@\u001a\n A*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\b\n\u0000\u001a\u0004\bG\u00104R\u001d\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001002¢\u0006\b\n\u0000\u001a\u0004\bI\u00104R\u0011\u0010J\u001a\u00020K¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u001d\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001002¢\u0006\b\n\u0000\u001a\u0004\bO\u00104R\u0010\u0010P\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u001002¢\u0006\b\n\u0000\u001a\u0004\bU\u00104R\u0010\u0010V\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001702¢\u0006\b\n\u0000\u001a\u0004\bX\u00104R\u001d\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001002¢\u0006\b\n\u0000\u001a\u0004\bZ\u00104R\u001d\u0010[\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001002¢\u0006\b\n\u0000\u001a\u0004\b\\\u00104R\u0011\u0010]\u001a\u00020^¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`R\u0017\u0010a\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\b\n\u0000\u001a\u0004\ba\u00104R\u0017\u0010b\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\b\n\u0000\u001a\u0004\bb\u00104R\u0017\u0010c\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\b\n\u0000\u001a\u0004\bc\u00104R\u0017\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\b\n\u0000\u001a\u0004\bd\u00104R\u000e\u0010e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010h\u001a\b\u0012\u0004\u0012\u00020!02¢\u0006\b\n\u0000\u001a\u0004\bi\u00104R\u000e\u0010j\u001a\u00020kX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010l\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#02¢\u0006\b\n\u0000\u001a\u0004\bm\u00104R\u0019\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a02¢\u0006\b\n\u0000\u001a\u0004\bo\u00104R\u0019\u0010p\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\b\n\u0000\u001a\u0004\bq\u00104R\u001d\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u001002¢\u0006\b\n\u0000\u001a\u0004\bs\u00104R\u0017\u0010t\u001a\b\u0012\u0004\u0012\u00020\t02¢\u0006\b\n\u0000\u001a\u0004\bu\u00104R\u000e\u0010v\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010w\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020!X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020!X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010|\u001a\u00020!2\u0006\u0010{\u001a\u00020!@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b}\u0010~R\u001e\u0010\u007f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u001002¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u00104R\u0010\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e02¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u00104R\u001f\u0010\u0085\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001002¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u00104R\u001b\u0010\u0087\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u00104R\u001b\u0010\u0089\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u00104R\u001b\u0010\u008b\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000502¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u00104R!\u0010\u008d\u0001\u001a\u00030\u008e\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0015\u0010\u0093\u0001\u001a\u00030\u0094\u0001¢\u0006\n\n\u0000\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0011\u0010\u0097\u0001\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020002¢\u0006\t\n\u0000\u001a\u0005\b\u009b\u0001\u00104R\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u009f\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010¡\u0001\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006ö\u0001"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "mediaId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_activeAudioIndex", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_activePlayerSubtitleIndex", "_activeSubtitleLabel", "_activeSubtitleTrackUrl", "_audioDisabledNotice", "", "_audioTracks", "", "Lcom/streamsync/app/ui/player/AudioTrackOption;", "_availableSubtitles", "Lcom/streamsync/app/data/model/SubtitleTrack;", "_cues", "Lcom/streamsync/app/data/model/SubtitleCue;", "_currentStreamSource", "Lcom/streamsync/app/data/model/StreamSource;", "_episodeSources", "_episodes", "Lcom/streamsync/app/data/model/SeriesVideo;", "_isBuffering", "_isLoadingEpisodeSources", "_isLoadingSources", "_isLoadingSubtitles", "_isPlaying", "_offsetMs", "", "_parentShow", "Lcom/streamsync/app/data/model/MediaItem;", "_pendingEpisode", "_playbackError", "_playerSubtitleTracks", "Lcom/streamsync/app/ui/player/PlayerSubtitleTrack;", "_reloadToken", "_seasons", "_softwareAudioNotice", "_sources", "_streamMimeType", "_streamUrl", "_subtitleError", "_syncState", "Lcom/streamsync/app/ui/player/SubtitleSyncUiState;", "activeAudioIndex", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveAudioIndex", "()Lkotlinx/coroutines/flow/StateFlow;", "activePlayerSubtitleIndex", "getActivePlayerSubtitleIndex", "activeSubtitleLabel", "getActiveSubtitleLabel", "activeSubtitleLang", "activeSubtitleRawSrt", "activeSubtitleTrackUrl", "getActiveSubtitleTrackUrl", "activeSubtitleUrl", "addonRepo", "Lcom/streamsync/app/data/repository/AddonRepository;", "appContext", "kotlin.jvm.PlatformType", "audioCapture", "Lcom/streamsync/app/subtitle/audio/PlayerAudioCapture;", "getAudioCapture", "()Lcom/streamsync/app/subtitle/audio/PlayerAudioCapture;", "audioDisabledNotice", "getAudioDisabledNotice", "audioTracks", "getAudioTracks", "autoSyncEngine", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine;", "getAutoSyncEngine", "()Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine;", "availableSubtitles", "getAvailableSubtitles", "captureWatchdog", "Lkotlinx/coroutines/Job;", "contentRepo", "Lcom/streamsync/app/data/repository/ContentRepository;", "cues", "getCues", "currentSource", "currentStreamSource", "getCurrentStreamSource", "episodeSources", "getEpisodeSources", "episodes", "getEpisodes", "geminiKeyStore", "Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore;", "getGeminiKeyStore", "()Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore;", "isBuffering", "isLoadingEpisodeSources", "isLoadingSources", "isLoadingSubtitles", "itemType", "lastProgressWriteAtMs", "lastPushAtMs", "offsetMs", "getOffsetMs", "offsetStore", "Lcom/streamsync/app/subtitle/SubtitleOffsetStore;", "parentShow", "getParentShow", "pendingEpisode", "getPendingEpisode", "playbackError", "getPlaybackError", "playerSubtitleTracks", "getPlayerSubtitleTracks", "reloadToken", "getReloadToken", "rememberAtMs", "rememberDwellJob", "rememberDwellMs", "rememberTickMs", "requestedAtMs", "<set-?>", "resumePositionMs", "getResumePositionMs", "()J", "seasons", "getSeasons", "settingsStore", "Lcom/streamsync/app/settings/PlaybackSettingsStore;", "softwareAudioNotice", "getSoftwareAudioNotice", "sources", "getSources", "streamMimeType", "getStreamMimeType", "streamUrl", "getStreamUrl", "subtitleError", "getSubtitleError", "subtitleHttpClient", "Lokhttp3/OkHttpClient;", "getSubtitleHttpClient", "()Lokhttp3/OkHttpClient;", "subtitleHttpClient$delegate", "Lkotlin/Lazy;", "subtitleStyleStore", "Lcom/streamsync/app/settings/SubtitleStyleStore;", "getSubtitleStyleStore", "()Lcom/streamsync/app/settings/SubtitleStyleStore;", "syncJob", "syncRepo", "Lcom/streamsync/app/data/repository/SyncRepository;", "syncState", "getSyncState", "translationJob", "translator", "Lcom/streamsync/app/subtitle/SubtitleTranslator;", "triedContainerFallbacks", "", "triedMuteFallback", "triedSoftwareFallback", "activeCueText", "positionMs", "adjustOffset", "", "deltaMs", "applyAutoSyncOffset", "aiConfirmed", "applyOffset", "applyTrackOverride", "player", "Landroidx/media3/common/Player;", "group", "Landroidx/media3/common/TrackGroup;", "buildCandidates", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAutoSync", "cancelPendingEpisode", "describe", "e", "", "disablePlayerSubtitle", "disableSubtitles", "episodeRouteFor", "episode", "fetchSubtitleText", "url", "languageHint", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "finishAutoSync", "finishAutoSyncInternal", "flushProgressNow", "durationMs", "load", "loadAddonSubtitles", "source", "force", "loadSourcesAndEpisodes", "loadSourcesForEpisode", "loadSubtitleTrack", "track", "translateTarget", "(Lcom/streamsync/app/data/model/SubtitleTrack;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "looksLikeEpisodeId", "id", "mergeSubtitles", "fromStream", "fromAddons", "nextContainerFallback", "onAudioDecoderFailed", "onBufferingChanged", "buffering", "onCleared", "onIsPlayingChanged", "playing", "onPlaybackError", "message", "retryAsHls", "onTick", "onTracksChanged", "pct", "score", "", "pickSourceForPendingEpisode", "pushPendingThrottled", "recordProgressThrottled", "resetAudioFallbacks", "resetOffset", "retry", "selectAudioTrack", "index", "selectPlayerSubtitleTrack", "selectSubtitleTrack", "seriesShowIdOf", "startAutoSyncCapture", "startCaptureWatchdog", "startDwellBeforeRemembering", "stopAutoSyncCapture", "switchSource", "currentPositionMs", "toUiState", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlayerViewModel extends ViewModel {

    @Deprecated
    public static final long CAPTURE_LIMIT_MS = 20000;

    @Deprecated
    public static final int PEER_TRACK_LIMIT = 8;
    private final MutableStateFlow<Integer> _activeAudioIndex;
    private final MutableStateFlow<Integer> _activePlayerSubtitleIndex;
    private final MutableStateFlow<String> _activeSubtitleLabel;
    private final MutableStateFlow<String> _activeSubtitleTrackUrl;
    private final MutableStateFlow<Boolean> _audioDisabledNotice;
    private final MutableStateFlow<List<AudioTrackOption>> _audioTracks;
    private final MutableStateFlow<List<SubtitleTrack>> _availableSubtitles;
    private final MutableStateFlow<List<SubtitleCue>> _cues;
    private final MutableStateFlow<StreamSource> _currentStreamSource;
    private final MutableStateFlow<List<StreamSource>> _episodeSources;
    private final MutableStateFlow<List<SeriesVideo>> _episodes;
    private final MutableStateFlow<Boolean> _isBuffering;
    private final MutableStateFlow<Boolean> _isLoadingEpisodeSources;
    private final MutableStateFlow<Boolean> _isLoadingSources;
    private final MutableStateFlow<Boolean> _isLoadingSubtitles;
    private final MutableStateFlow<Boolean> _isPlaying;
    private final MutableStateFlow<Long> _offsetMs;
    private final MutableStateFlow<MediaItem> _parentShow;
    private final MutableStateFlow<SeriesVideo> _pendingEpisode;
    private final MutableStateFlow<String> _playbackError;
    private final MutableStateFlow<List<PlayerSubtitleTrack>> _playerSubtitleTracks;
    private final MutableStateFlow<Integer> _reloadToken;
    private final MutableStateFlow<List<Integer>> _seasons;
    private final MutableStateFlow<Boolean> _softwareAudioNotice;
    private final MutableStateFlow<List<StreamSource>> _sources;
    private final MutableStateFlow<String> _streamMimeType;
    private final MutableStateFlow<String> _streamUrl;
    private final MutableStateFlow<String> _subtitleError;
    private final MutableStateFlow<SubtitleSyncUiState> _syncState;
    private final StateFlow<Integer> activeAudioIndex;
    private final StateFlow<Integer> activePlayerSubtitleIndex;
    private final StateFlow<String> activeSubtitleLabel;
    private String activeSubtitleLang;
    private String activeSubtitleRawSrt;
    private final StateFlow<String> activeSubtitleTrackUrl;
    private String activeSubtitleUrl;
    private final AddonRepository addonRepo;
    private final Context appContext;
    private final PlayerAudioCapture audioCapture;
    private final StateFlow<Boolean> audioDisabledNotice;
    private final StateFlow<List<AudioTrackOption>> audioTracks;
    private final LocalAutoSyncEngine autoSyncEngine;
    private final StateFlow<List<SubtitleTrack>> availableSubtitles;
    private Job captureWatchdog;
    private final ContentRepository contentRepo;
    private final StateFlow<List<SubtitleCue>> cues;
    private StreamSource currentSource;
    private final StateFlow<StreamSource> currentStreamSource;
    private final StateFlow<List<StreamSource>> episodeSources;
    private final StateFlow<List<SeriesVideo>> episodes;
    private final GeminiApiKeyStore geminiKeyStore;
    private final StateFlow<Boolean> isBuffering;
    private final StateFlow<Boolean> isLoadingEpisodeSources;
    private final StateFlow<Boolean> isLoadingSources;
    private final StateFlow<Boolean> isLoadingSubtitles;
    private final String itemType;
    private long lastProgressWriteAtMs;
    private long lastPushAtMs;
    private final String mediaId;
    private final StateFlow<Long> offsetMs;
    private final SubtitleOffsetStore offsetStore;
    private final StateFlow<MediaItem> parentShow;
    private final StateFlow<SeriesVideo> pendingEpisode;
    private final StateFlow<String> playbackError;
    private final StateFlow<List<PlayerSubtitleTrack>> playerSubtitleTracks;
    private final StateFlow<Integer> reloadToken;
    private long rememberAtMs;
    private Job rememberDwellJob;
    private final long rememberDwellMs;
    private final long rememberTickMs;
    private long requestedAtMs;
    private long resumePositionMs;
    private final StateFlow<List<Integer>> seasons;
    private final PlaybackSettingsStore settingsStore;
    private final StateFlow<Boolean> softwareAudioNotice;
    private final StateFlow<List<StreamSource>> sources;
    private final StateFlow<String> streamMimeType;
    private final StateFlow<String> streamUrl;
    private final StateFlow<String> subtitleError;

    /* renamed from: subtitleHttpClient$delegate, reason: from kotlin metadata */
    private final Lazy subtitleHttpClient;
    private final SubtitleStyleStore subtitleStyleStore;
    private Job syncJob;
    private final SyncRepository syncRepo;
    private final StateFlow<SubtitleSyncUiState> syncState;
    private Job translationJob;
    private SubtitleTranslator translator;
    private final List<String> triedContainerFallbacks;
    private boolean triedMuteFallback;
    private boolean triedSoftwareFallback;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: PlayerScreen.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.SERIES_EPISODE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MediaType.SERIES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PlayerViewModel(Context context, String mediaId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        this.mediaId = mediaId;
        this.appContext = context.getApplicationContext();
        this.contentRepo = new ContentRepository();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.addonRepo = new AddonRepository(applicationContext);
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
        this.syncRepo = new SyncRepository(applicationContext2);
        Context applicationContext3 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "getApplicationContext(...)");
        this.offsetStore = new SubtitleOffsetStore(applicationContext3);
        Context applicationContext4 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "getApplicationContext(...)");
        this.settingsStore = new PlaybackSettingsStore(applicationContext4);
        SubtitleStyleStore.Companion companion = SubtitleStyleStore.INSTANCE;
        Context applicationContext5 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext5, "getApplicationContext(...)");
        this.subtitleStyleStore = companion.get(applicationContext5);
        Context applicationContext6 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext6, "getApplicationContext(...)");
        this.geminiKeyStore = new GeminiApiKeyStore(applicationContext6);
        this.autoSyncEngine = new LocalAutoSyncEngine(this.geminiKeyStore, new Function1<LocalAutoSyncEngine.State, Unit>() { // from class: com.streamsync.app.ui.player.PlayerViewModel$autoSyncEngine$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LocalAutoSyncEngine.State state) {
                invoke2(state);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LocalAutoSyncEngine.State state) {
                MutableStateFlow mutableStateFlow;
                SubtitleSyncUiState uiState;
                Intrinsics.checkNotNullParameter(state, "state");
                if (state instanceof LocalAutoSyncEngine.State.Done) {
                    BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(PlayerViewModel.this), null, null, new AnonymousClass1(state, PlayerViewModel.this, null), 3, null);
                }
                mutableStateFlow = PlayerViewModel.this._syncState;
                uiState = PlayerViewModel.this.toUiState(state);
                mutableStateFlow.setValue(uiState);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: PlayerScreen.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
            @DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$autoSyncEngine$1$1", f = "PlayerScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.streamsync.app.ui.player.PlayerViewModel$autoSyncEngine$1$1, reason: invalid class name */
            /* loaded from: classes23.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ LocalAutoSyncEngine.State $state;
                int label;
                final /* synthetic */ PlayerViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(LocalAutoSyncEngine.State state, PlayerViewModel playerViewModel, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$state = state;
                    this.this$0 = playerViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$state, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MutableStateFlow mutableStateFlow;
                    Object element$iv;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            if (((LocalAutoSyncEngine.State.Done) this.$state).getSwitchedTrack()) {
                                mutableStateFlow = this.this$0._availableSubtitles;
                                Iterable $this$firstOrNull$iv = (Iterable) mutableStateFlow.getValue();
                                LocalAutoSyncEngine.State state = this.$state;
                                Iterator it = $this$firstOrNull$iv.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        element$iv = it.next();
                                        if (Intrinsics.areEqual(((SubtitleTrack) element$iv).getUrl(), ((LocalAutoSyncEngine.State.Done) state).getTrackUrl())) {
                                        }
                                    } else {
                                        element$iv = null;
                                    }
                                }
                                SubtitleTrack it2 = (SubtitleTrack) element$iv;
                                if (it2 != null) {
                                    this.this$0.selectSubtitleTrack(it2);
                                }
                            }
                            this.this$0.applyAutoSyncOffset(((LocalAutoSyncEngine.State.Done) this.$state).getOffsetMs(), ((LocalAutoSyncEngine.State.Done) this.$state).getTextVerified());
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
        });
        this.audioCapture = new PlayerAudioCapture();
        MediaItem mediaItem = MediaItemCache.INSTANCE.get(this.mediaId);
        MediaType type = mediaItem != null ? mediaItem.getType() : null;
        String str = "series";
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
            case 2:
                break;
            default:
                if (!looksLikeEpisodeId(this.mediaId)) {
                    str = "movie";
                    break;
                }
                break;
        }
        this.itemType = str;
        this._cues = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.cues = FlowKt.asStateFlow(this._cues);
        this._streamUrl = StateFlowKt.MutableStateFlow(null);
        this.streamUrl = FlowKt.asStateFlow(this._streamUrl);
        this._currentStreamSource = StateFlowKt.MutableStateFlow(null);
        this.currentStreamSource = FlowKt.asStateFlow(this._currentStreamSource);
        this._streamMimeType = StateFlowKt.MutableStateFlow(null);
        this.streamMimeType = FlowKt.asStateFlow(this._streamMimeType);
        this._availableSubtitles = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.availableSubtitles = FlowKt.asStateFlow(this._availableSubtitles);
        this._activeSubtitleLabel = StateFlowKt.MutableStateFlow(null);
        this.activeSubtitleLabel = FlowKt.asStateFlow(this._activeSubtitleLabel);
        this._activeSubtitleTrackUrl = StateFlowKt.MutableStateFlow(null);
        this.activeSubtitleTrackUrl = FlowKt.asStateFlow(this._activeSubtitleTrackUrl);
        this._isLoadingSubtitles = StateFlowKt.MutableStateFlow(false);
        this.isLoadingSubtitles = FlowKt.asStateFlow(this._isLoadingSubtitles);
        this._subtitleError = StateFlowKt.MutableStateFlow(null);
        this.subtitleError = FlowKt.asStateFlow(this._subtitleError);
        this._audioTracks = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.audioTracks = FlowKt.asStateFlow(this._audioTracks);
        this._activeAudioIndex = StateFlowKt.MutableStateFlow(null);
        this.activeAudioIndex = FlowKt.asStateFlow(this._activeAudioIndex);
        this._playerSubtitleTracks = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.playerSubtitleTracks = FlowKt.asStateFlow(this._playerSubtitleTracks);
        this._activePlayerSubtitleIndex = StateFlowKt.MutableStateFlow(null);
        this.activePlayerSubtitleIndex = FlowKt.asStateFlow(this._activePlayerSubtitleIndex);
        this._sources = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.sources = FlowKt.asStateFlow(this._sources);
        this._isLoadingSources = StateFlowKt.MutableStateFlow(false);
        this.isLoadingSources = FlowKt.asStateFlow(this._isLoadingSources);
        this._episodes = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.episodes = FlowKt.asStateFlow(this._episodes);
        this._seasons = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.seasons = FlowKt.asStateFlow(this._seasons);
        this._parentShow = StateFlowKt.MutableStateFlow(null);
        this.parentShow = FlowKt.asStateFlow(this._parentShow);
        this._pendingEpisode = StateFlowKt.MutableStateFlow(null);
        this.pendingEpisode = FlowKt.asStateFlow(this._pendingEpisode);
        this._episodeSources = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.episodeSources = FlowKt.asStateFlow(this._episodeSources);
        this._isLoadingEpisodeSources = StateFlowKt.MutableStateFlow(false);
        this.isLoadingEpisodeSources = FlowKt.asStateFlow(this._isLoadingEpisodeSources);
        this._offsetMs = StateFlowKt.MutableStateFlow(0L);
        this.offsetMs = FlowKt.asStateFlow(this._offsetMs);
        this._syncState = StateFlowKt.MutableStateFlow(new SubtitleSyncUiState(false, false, false, 0L, null, false, 0, 127, null));
        this.syncState = FlowKt.asStateFlow(this._syncState);
        this._playbackError = StateFlowKt.MutableStateFlow(null);
        this.playbackError = FlowKt.asStateFlow(this._playbackError);
        this._isBuffering = StateFlowKt.MutableStateFlow(false);
        this._isPlaying = StateFlowKt.MutableStateFlow(false);
        this.isBuffering = FlowKt.asStateFlow(this._isBuffering);
        this._reloadToken = StateFlowKt.MutableStateFlow(0);
        this.reloadToken = FlowKt.asStateFlow(this._reloadToken);
        this.activeSubtitleRawSrt = "";
        this.triedContainerFallbacks = new ArrayList();
        this._softwareAudioNotice = StateFlowKt.MutableStateFlow(false);
        this.softwareAudioNotice = FlowKt.asStateFlow(this._softwareAudioNotice);
        this._audioDisabledNotice = StateFlowKt.MutableStateFlow(false);
        this.audioDisabledNotice = FlowKt.asStateFlow(this._audioDisabledNotice);
        this.subtitleHttpClient = LazyKt.lazy(new Function0<OkHttpClient>() { // from class: com.streamsync.app.ui.player.PlayerViewModel$subtitleHttpClient$2
            @Override // kotlin.jvm.functions.Function0
            public final OkHttpClient invoke() {
                return RetrofitClient.INSTANCE.getOkHttpClient().newBuilder().connectTimeout(15L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).build();
            }
        });
        this.rememberDwellMs = 120000L;
        this.rememberTickMs = 5000L;
    }

    public final SubtitleStyleStore getSubtitleStyleStore() {
        return this.subtitleStyleStore;
    }

    public final GeminiApiKeyStore getGeminiKeyStore() {
        return this.geminiKeyStore;
    }

    public final LocalAutoSyncEngine getAutoSyncEngine() {
        return this.autoSyncEngine;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SubtitleSyncUiState toUiState(LocalAutoSyncEngine.State $this$toUiState) {
        String str;
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Listening) {
            return new SubtitleSyncUiState(true, false, false, ((LocalAutoSyncEngine.State.Listening) $this$toUiState).getCapturedMs(), "Đang thu audio đang phát... đã " + (((LocalAutoSyncEngine.State.Listening) $this$toUiState).getCapturedMs() / 1000) + "s. Cần ít nhất 30 giây để so được.", false, 0, 102, null);
        }
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Transcribing) {
            return new SubtitleSyncUiState(false, true, false, ((LocalAutoSyncEngine.State.Transcribing) $this$toUiState).getCapturedMs(), "Đang gửi audio cho Gemini REST (đoạn " + ((LocalAutoSyncEngine.State.Transcribing) $this$toUiState).getChunksDone() + "/" + ((LocalAutoSyncEngine.State.Transcribing) $this$toUiState).getChunksTotal() + ")...", false, ((LocalAutoSyncEngine.State.Transcribing) $this$toUiState).getChunksDone(), 37, null);
        }
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Matching) {
            String note = ((LocalAutoSyncEngine.State.Matching) $this$toUiState).getNote();
            if (StringsKt.isBlank(note)) {
                note = "Đang tìm độ lệch chung giữa phụ đề và lời thoại vừa nghe...";
            }
            return new SubtitleSyncUiState(false, false, true, 0L, note, false, 0, 107, null);
        }
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Done) {
            long capturedMs = ((LocalAutoSyncEngine.State.Done) $this$toUiState).getCapturedMs();
            int textPairs = ((LocalAutoSyncEngine.State.Done) $this$toUiState).getTextPairs();
            StringBuilder $this$toUiState_u24lambda_u241 = new StringBuilder();
            $this$toUiState_u24lambda_u241.append("Đã áp dụng phụ đề \"" + ((LocalAutoSyncEngine.State.Done) $this$toUiState).getTrackLabel() + "\" dịch " + PlayerScreenKt.access$formatSigned(((LocalAutoSyncEngine.State.Done) $this$toUiState).getOffsetMs()) + " ");
            if (((LocalAutoSyncEngine.State.Done) $this$toUiState).getTextVerified()) {
                str = "(" + pct(((LocalAutoSyncEngine.State.Done) $this$toUiState).getBaseScore()) + " → " + pct(((LocalAutoSyncEngine.State.Done) $this$toUiState).getCorrectedScore()) + ", " + ((LocalAutoSyncEngine.State.Done) $this$toUiState).getTextPairs() + " cặp câu khớp). ";
            } else {
                str = "(đo bằng mốc thời gian trên chính dòng phụ đề: " + pct(((LocalAutoSyncEngine.State.Done) $this$toUiState).getBaseScore()) + " → " + pct(((LocalAutoSyncEngine.State.Done) $this$toUiState).getCorrectedScore()) + "; không cần mạng, không cần API key). ";
            }
            $this$toUiState_u24lambda_u241.append(str);
            $this$toUiState_u24lambda_u241.append("Đã tự áp dụng, không ghi file ra máy.");
            String sb = $this$toUiState_u24lambda_u241.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return new SubtitleSyncUiState(false, false, false, capturedMs, sb, false, textPairs, 39, null);
        }
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Rejected) {
            return new SubtitleSyncUiState(false, false, false, ((LocalAutoSyncEngine.State.Rejected) $this$toUiState).getCapturedMs(), ((LocalAutoSyncEngine.State.Rejected) $this$toUiState).getReason(), false, ((LocalAutoSyncEngine.State.Rejected) $this$toUiState).getSampleCount(), 39, null);
        }
        if ($this$toUiState instanceof LocalAutoSyncEngine.State.Failed) {
            return new SubtitleSyncUiState(false, false, false, ((LocalAutoSyncEngine.State.Failed) $this$toUiState).getCapturedMs(), ((LocalAutoSyncEngine.State.Failed) $this$toUiState).getMessage(), true, 0, 71, null);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final PlayerAudioCapture getAudioCapture() {
        return this.audioCapture;
    }

    private final boolean looksLikeEpisodeId(String id) {
        return AddonIdParser.INSTANCE.isEpisodeId(id);
    }

    public final StateFlow<List<SubtitleCue>> getCues() {
        return this.cues;
    }

    public final StateFlow<String> getStreamUrl() {
        return this.streamUrl;
    }

    public final StateFlow<StreamSource> getCurrentStreamSource() {
        return this.currentStreamSource;
    }

    public final StateFlow<String> getStreamMimeType() {
        return this.streamMimeType;
    }

    public final StateFlow<List<SubtitleTrack>> getAvailableSubtitles() {
        return this.availableSubtitles;
    }

    public final StateFlow<String> getActiveSubtitleLabel() {
        return this.activeSubtitleLabel;
    }

    public final StateFlow<String> getActiveSubtitleTrackUrl() {
        return this.activeSubtitleTrackUrl;
    }

    public final StateFlow<Boolean> isLoadingSubtitles() {
        return this.isLoadingSubtitles;
    }

    public final StateFlow<String> getSubtitleError() {
        return this.subtitleError;
    }

    public final StateFlow<List<AudioTrackOption>> getAudioTracks() {
        return this.audioTracks;
    }

    public final StateFlow<Integer> getActiveAudioIndex() {
        return this.activeAudioIndex;
    }

    public final StateFlow<List<PlayerSubtitleTrack>> getPlayerSubtitleTracks() {
        return this.playerSubtitleTracks;
    }

    public final StateFlow<Integer> getActivePlayerSubtitleIndex() {
        return this.activePlayerSubtitleIndex;
    }

    public final StateFlow<List<StreamSource>> getSources() {
        return this.sources;
    }

    public final StateFlow<Boolean> isLoadingSources() {
        return this.isLoadingSources;
    }

    public final StateFlow<List<SeriesVideo>> getEpisodes() {
        return this.episodes;
    }

    public final StateFlow<List<Integer>> getSeasons() {
        return this.seasons;
    }

    public final StateFlow<MediaItem> getParentShow() {
        return this.parentShow;
    }

    public final StateFlow<SeriesVideo> getPendingEpisode() {
        return this.pendingEpisode;
    }

    public final StateFlow<List<StreamSource>> getEpisodeSources() {
        return this.episodeSources;
    }

    public final StateFlow<Boolean> isLoadingEpisodeSources() {
        return this.isLoadingEpisodeSources;
    }

    public final StateFlow<Long> getOffsetMs() {
        return this.offsetMs;
    }

    public final StateFlow<SubtitleSyncUiState> getSyncState() {
        return this.syncState;
    }

    public final StateFlow<String> getPlaybackError() {
        return this.playbackError;
    }

    public final StateFlow<Boolean> isBuffering() {
        return this.isBuffering;
    }

    public final StateFlow<Integer> getReloadToken() {
        return this.reloadToken;
    }

    public final long getResumePositionMs() {
        return this.resumePositionMs;
    }

    public final void load() {
        this._offsetMs.setValue(Long.valueOf(this.offsetStore.get(this.mediaId)));
        this._playbackError.setValue(null);
        long loadStartedAtMs = SystemClock.elapsedRealtime();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$load$1(this, loadStartedAtMs, null), 3, null);
    }

    public static /* synthetic */ void onPlaybackError$default(PlayerViewModel playerViewModel, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        playerViewModel.onPlaybackError(str, z);
    }

    public final void onPlaybackError(String message, boolean retryAsHls) {
        String next;
        Intrinsics.checkNotNullParameter(message, "message");
        if (!retryAsHls || (next = nextContainerFallback()) == null) {
            this._isBuffering.setValue(false);
            this._playbackError.setValue(message);
            return;
        }
        this._isBuffering.setValue(false);
        this._playbackError.setValue(null);
        FileLog.d$default(FileLog.INSTANCE, "Container sai, thu lai voi " + StringsKt.substringAfterLast$default(next, '/', (String) null, 2, (Object) null) + " (da thu " + this.triedContainerFallbacks.size() + " kiểu)", null, 2, null);
        this._streamMimeType.setValue(next);
        MutableStateFlow<Integer> mutableStateFlow = this._reloadToken;
        mutableStateFlow.setValue(Integer.valueOf(mutableStateFlow.getValue().intValue() + 1));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0029, code lost:
    
        if (r0.equals(androidx.media3.common.MimeTypes.VIDEO_MP4) != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0033, code lost:
    
        r2 = kotlin.collections.CollectionsKt.listOf(androidx.media3.common.MimeTypes.VIDEO_MATROSKA);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0030, code lost:
    
        if (r0.equals(androidx.media3.common.MimeTypes.APPLICATION_MP4) == false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String nextContainerFallback() {
        /*
            r11 = this;
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> r0 = r11._streamMimeType
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r1 = 1
            java.lang.String r2 = "application/mp4"
            java.lang.String r3 = "video/x-matroska"
            if (r0 == 0) goto L38
            int r4 = r0.hashCode()
            switch(r4) {
                case -1248337486: goto L2c;
                case 1331848029: goto L23;
                case 2039520277: goto L17;
                default: goto L16;
            }
        L16:
            goto L38
        L17:
            boolean r4 = r0.equals(r3)
            if (r4 != 0) goto L1e
            goto L38
        L1e:
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            goto L4b
        L23:
            java.lang.String r4 = "video/mp4"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L38
            goto L33
        L2c:
            boolean r4 = r0.equals(r2)
            if (r4 != 0) goto L33
            goto L38
        L33:
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r3)
            goto L4b
        L38:
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]
            r5 = 0
            java.lang.String r6 = "application/x-mpegURL"
            r4[r5] = r6
            r4[r1] = r3
            r3 = 2
            r4[r3] = r2
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r4)
        L4b:
            r3 = r2
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            java.util.Iterator r5 = r3.iterator()
        L54:
            boolean r6 = r5.hasNext()
            r7 = 0
            if (r6 == 0) goto L6e
            java.lang.Object r6 = r5.next()
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8
            r9 = 0
            java.util.List<java.lang.String> r10 = r11.triedContainerFallbacks
            boolean r10 = r10.contains(r8)
            r8 = r10 ^ 1
            if (r8 == 0) goto L54
            goto L6f
        L6e:
            r6 = r7
        L6f:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L74
            return r7
        L74:
            r1 = r6
            java.util.List<java.lang.String> r3 = r11.triedContainerFallbacks
            r3.add(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.nextContainerFallback():java.lang.String");
    }

    public final void onIsPlayingChanged(boolean playing) {
        long since;
        if (playing) {
            if (this.requestedAtMs > 0) {
                since = SystemClock.elapsedRealtime() - this.requestedAtMs;
            } else {
                since = -1;
            }
            FileLog.d$default(FileLog.INSTANCE, "Phat: co khung hinh dau tien sau " + (since >= 0 ? since + " ms" : "? ms") + " tu luc gan nguon" + (this._streamMimeType.getValue() != null ? "" : " (chua ro mime)"), null, 2, null);
            this.requestedAtMs = 0L;
        }
        this._isPlaying.setValue(Boolean.valueOf(playing));
    }

    public final void onBufferingChanged(boolean buffering) {
        if (buffering) {
            this._playbackError.setValue(null);
        }
        this._isBuffering.setValue(Boolean.valueOf(buffering));
    }

    public final void retry() {
        this._playbackError.setValue(null);
        resetAudioFallbacks();
        this.triedContainerFallbacks.clear();
        MutableStateFlow<Integer> mutableStateFlow = this._reloadToken;
        mutableStateFlow.setValue(Integer.valueOf(mutableStateFlow.getValue().intValue() + 1));
    }

    public final StateFlow<Boolean> getSoftwareAudioNotice() {
        return this.softwareAudioNotice;
    }

    public final boolean onAudioDecoderFailed() {
        this._isBuffering.setValue(false);
        this._playbackError.setValue(null);
        if (!this.triedSoftwareFallback) {
            this.triedSoftwareFallback = true;
            this._softwareAudioNotice.setValue(true);
            MutableStateFlow<Integer> mutableStateFlow = this._reloadToken;
            mutableStateFlow.setValue(Integer.valueOf(mutableStateFlow.getValue().intValue() + 1));
            return true;
        }
        if (this.triedMuteFallback) {
            return false;
        }
        this.triedMuteFallback = true;
        this._audioDisabledNotice.setValue(true);
        MutableStateFlow<Integer> mutableStateFlow2 = this._reloadToken;
        mutableStateFlow2.setValue(Integer.valueOf(mutableStateFlow2.getValue().intValue() + 1));
        return true;
    }

    public final void resetAudioFallbacks() {
        this.triedSoftwareFallback = false;
        this.triedMuteFallback = false;
        this._softwareAudioNotice.setValue(false);
        this._audioDisabledNotice.setValue(false);
    }

    public final StateFlow<Boolean> getAudioDisabledNotice() {
        return this.audioDisabledNotice;
    }

    public final void selectSubtitleTrack(SubtitleTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$selectSubtitleTrack$1(this, track, null), 3, null);
    }

    public final void disableSubtitles() {
        this._cues.setValue(CollectionsKt.emptyList());
        this._subtitleError.setValue(null);
        this._activeSubtitleLabel.setValue(null);
        this._activeSubtitleTrackUrl.setValue(null);
        this.activeSubtitleUrl = null;
        this.activeSubtitleRawSrt = "";
    }

    public static /* synthetic */ void loadAddonSubtitles$default(PlayerViewModel playerViewModel, StreamSource streamSource, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            streamSource = playerViewModel.currentSource;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        playerViewModel.loadAddonSubtitles(streamSource, z);
    }

    public final void loadAddonSubtitles(StreamSource source, boolean force) {
        if (source == null || !StringsKt.contains$default((CharSequence) this.mediaId, (CharSequence) ":", false, 2, (Object) null) || this._isLoadingSubtitles.getValue().booleanValue()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$loadAddonSubtitles$1(this, source, force, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SubtitleTrack> mergeSubtitles(List<SubtitleTrack> fromStream, List<SubtitleTrack> fromAddons) {
        List out = new ArrayList();
        Set seen = new LinkedHashSet();
        for (SubtitleTrack track : CollectionsKt.plus((Collection) fromStream, (Iterable) fromAddons)) {
            if (!StringsKt.isBlank(track.getUrl()) && seen.add(track.getUrl())) {
                out.add(track);
            }
        }
        return out;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0025. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadSubtitleTrack(com.streamsync.app.data.model.SubtitleTrack r13, java.lang.String r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instructions count: 516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.loadSubtitleTrack(com.streamsync.app.data.model.SubtitleTrack, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient getSubtitleHttpClient() {
        return (OkHttpClient) this.subtitleHttpClient.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchSubtitleText(String url, String languageHint, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlayerViewModel$fetchSubtitleText$2(this, url, languageHint, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object fetchSubtitleText$default(PlayerViewModel playerViewModel, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return playerViewModel.fetchSubtitleText(str, str2, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0118, code lost:
    
        if (r3 != null) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String describe(java.lang.Throwable r24) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.describe(java.lang.Throwable):java.lang.String");
    }

    public final void loadSourcesAndEpisodes() {
        if (this._isLoadingSources.getValue().booleanValue()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$loadSourcesAndEpisodes$1(this, null), 3, null);
    }

    public final void switchSource(StreamSource source, long currentPositionMs) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (Intrinsics.areEqual(source.getStreamUrl(), this._streamUrl.getValue())) {
            return;
        }
        long resumeAt = RangesKt.coerceAtLeast(currentPositionMs, 0L);
        this.triedContainerFallbacks.clear();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$switchSource$1(this, source, resumeAt, null), 3, null);
    }

    public final void loadSourcesForEpisode(SeriesVideo episode) {
        Intrinsics.checkNotNullParameter(episode, "episode");
        String addonId = StringsKt.substringBefore(this.mediaId, ":", "");
        if (StringsKt.isBlank(addonId)) {
            return;
        }
        String episodeId = addonId + ":" + episode.getId();
        this._pendingEpisode.setValue(episode);
        this._episodeSources.setValue(CollectionsKt.emptyList());
        this._isLoadingEpisodeSources.setValue(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$loadSourcesForEpisode$1(this, episodeId, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadSourcesForEpisode$showSources(PlayerViewModel this$0, List<StreamSource> list) {
        if (list.isEmpty()) {
            return;
        }
        this$0._episodeSources.setValue(list);
        Iterable $this$forEach$iv = CollectionsKt.take(list, 3);
        for (Object element$iv : $this$forEach$iv) {
            StreamSource src = (StreamSource) element$iv;
            Context appContext = this$0.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            PlaybackPrewarmer.warm(appContext, src.getStreamUrl(), false, src.getRequestHeaders());
        }
    }

    public final void pickSourceForPendingEpisode(StreamSource source) {
        StreamSource copy;
        Intrinsics.checkNotNullParameter(source, "source");
        SeriesVideo episode = this._pendingEpisode.getValue();
        if (episode == null) {
            return;
        }
        String addonId = StringsKt.substringBefore(this.mediaId, ":", "");
        if (StringsKt.isBlank(addonId)) {
            return;
        }
        String episodeMediaId = addonId + ":" + episode.getId();
        StreamSelectionStore streamSelectionStore = StreamSelectionStore.INSTANCE;
        copy = source.copy((r34 & 1) != 0 ? source.mediaId : episodeMediaId, (r34 & 2) != 0 ? source.streamUrl : null, (r34 & 4) != 0 ? source.mimeType : null, (r34 & 8) != 0 ? source.subtitles : null, (r34 & 16) != 0 ? source.name : null, (r34 & 32) != 0 ? source.quality : null, (r34 & 64) != 0 ? source.provider : null, (r34 & 128) != 0 ? source.spec : null, (r34 & 256) != 0 ? source.size : null, (r34 & 512) != 0 ? source.qualityValue : 0, (r34 & 1024) != 0 ? source.addonId : null, (r34 & 2048) != 0 ? source.addonName : null, (r34 & 4096) != 0 ? source.addonLogo : null, (r34 & 8192) != 0 ? source.releaseName : null, (r34 & 16384) != 0 ? source.requestHeaders : null, (r34 & 32768) != 0 ? source.index : 0);
        streamSelectionStore.put(copy);
        MediaItem show = this._parentShow.getValue();
        if (show != null) {
            MediaItemCache.INSTANCE.put(EpisodeItemKt.toEpisodeItem(show, episode));
        }
        this._pendingEpisode.setValue(null);
    }

    public final void cancelPendingEpisode() {
        this._pendingEpisode.setValue(null);
        this._episodeSources.setValue(CollectionsKt.emptyList());
    }

    public final String episodeRouteFor(SeriesVideo episode) {
        Intrinsics.checkNotNullParameter(episode, "episode");
        String addonId = StringsKt.substringBefore(this.mediaId, ":", "");
        String itemId = StringsKt.substringAfter(this.mediaId, ":", "");
        if (StringsKt.isBlank(addonId) || StringsKt.isBlank(itemId)) {
            return null;
        }
        return addonId + ":" + episode.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String seriesShowIdOf(String id) {
        return AddonIdParser.INSTANCE.showIdOfEpisode(id);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x01d9, code lost:
    
        r7.append(" (không giải mã được)");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onTracksChanged(androidx.media3.common.Player r29) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.onTracksChanged(androidx.media3.common.Player):void");
    }

    public final void selectAudioTrack(Player player, int index) {
        Intrinsics.checkNotNullParameter(player, "player");
        ImmutableList<Tracks.Group> groups = player.getCurrentTracks().getGroups();
        Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
        Tracks.Group group = (Tracks.Group) CollectionsKt.getOrNull(groups, index);
        if (group != null && group.getType() == 1) {
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            Intrinsics.checkNotNullExpressionValue(mediaTrackGroup, "getMediaTrackGroup(...)");
            applyTrackOverride(player, mediaTrackGroup);
        }
    }

    public final void selectPlayerSubtitleTrack(Player player, int index) {
        Intrinsics.checkNotNullParameter(player, "player");
        ImmutableList<Tracks.Group> groups = player.getCurrentTracks().getGroups();
        Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
        Tracks.Group group = (Tracks.Group) CollectionsKt.getOrNull(groups, index);
        if (group != null && group.getType() == 3) {
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            Intrinsics.checkNotNullExpressionValue(mediaTrackGroup, "getMediaTrackGroup(...)");
            applyTrackOverride(player, mediaTrackGroup);
        }
    }

    public final void disablePlayerSubtitle(Player player) {
        Intrinsics.checkNotNullParameter(player, "player");
        player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().setTrackTypeDisabled(3, true).build());
        onTracksChanged(player);
    }

    private final void applyTrackOverride(Player player, TrackGroup group) {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            PlayerViewModel playerViewModel = this;
            m6717constructorimpl = Result.m6717constructorimpl(Integer.valueOf(group.type));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = null;
        }
        Integer num = (Integer) m6717constructorimpl;
        if (num == null) {
            return;
        }
        int type = num.intValue();
        TrackSelectionOverride override = new TrackSelectionOverride(group, 0);
        player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(type).setTrackTypeDisabled(type, false).setOverrideForType(override).build());
    }

    public final void adjustOffset(long deltaMs) {
        long updated = this._offsetMs.getValue().longValue() + deltaMs;
        this._offsetMs.setValue(Long.valueOf(updated));
        this.offsetStore.set(this.mediaId, updated);
    }

    public final void resetOffset() {
        this._offsetMs.setValue(0L);
        this.offsetStore.set(this.mediaId, 0L);
    }

    public final void startAutoSyncCapture(long positionMs) {
        String why;
        if (this._streamUrl.getValue() == null) {
            this._syncState.setValue(new SubtitleSyncUiState(false, false, false, 0L, "Chưa có video để bắt audio.", true, 0, 79, null));
            return;
        }
        if (this._availableSubtitles.getValue().isEmpty()) {
            String value = this._subtitleError.getValue();
            if (value != null) {
                why = value;
            } else {
                why = "Addon chưa trả về track phụ đề nào cho phim này. Mở nút phụ đề (CC) ở trên cùng để kiểm tra danh sách.";
            }
            this._syncState.setValue(new SubtitleSyncUiState(false, false, false, 0L, why, true, 0, 79, null));
            return;
        }
        if (!this.audioCapture.isPcmAvailable()) {
            this._syncState.setValue(new SubtitleSyncUiState(false, false, false, 0L, "Audio đang ở chế độ bitstream passthrough nên không bắt được PCM để đối chiếu. Tắt passthrough trong Cài đặt → Âm thanh rồi mở lại phim.", true, 0, 79, null));
            return;
        }
        this.autoSyncEngine.start(positionMs);
        this.audioCapture.start(positionMs, 20000L);
        this._syncState.setValue(new SubtitleSyncUiState(true, false, false, 0L, "Đang đồng bộ, tìm phụ đề tốt nhất... nghe 20s audio rồi app tự chọn track khớp nhất và áp dụng. Cứ để phim chạy, bấm Dừng nếu muốn chốt sớm.", false, 0, 110, null));
        startCaptureWatchdog();
    }

    public final void stopAutoSyncCapture() {
        if (this.audioCapture.isRunning()) {
            this.audioCapture.stop();
            finishAutoSync();
        }
    }

    public final void cancelAutoSync() {
        Job job = this.captureWatchdog;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.rememberDwellJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.audioCapture.stop();
        this.audioCapture.clear();
        Job job3 = this.syncJob;
        if (job3 != null) {
            Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
        }
        this.autoSyncEngine.cancel();
        this._syncState.setValue(new SubtitleSyncUiState(false, false, false, 0L, "Đã dừng Auto sync.", false, 0, 111, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishAutoSync() {
        Job launch$default;
        Job job = this.syncJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$finishAutoSync$1(this, null), 3, null);
        this.syncJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0111 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finishAutoSyncInternal(kotlin.coroutines.Continuation<? super kotlin.Unit> r22) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.finishAutoSyncInternal(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object buildCandidates(kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate>> r18) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.ui.player.PlayerViewModel.buildCandidates(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void startCaptureWatchdog() {
        Job launch$default;
        Job job = this.captureWatchdog;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        long deadline = SystemClock.elapsedRealtime() + 60000;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$startCaptureWatchdog$1(this, deadline, null), 3, null);
        this.captureWatchdog = launch$default;
    }

    private final void recordProgressThrottled(long positionMs, long durationMs) {
        long now = SystemClock.elapsedRealtime();
        long last = this.lastProgressWriteAtMs;
        if (last == 0 || now - last >= 10000) {
            this.lastProgressWriteAtMs = now;
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$recordProgressThrottled$1(this, positionMs, durationMs, null), 3, null);
        }
    }

    private final void pushPendingThrottled() {
        long now = SystemClock.elapsedRealtime();
        long last = this.lastPushAtMs;
        if (last == 0 || now - last >= 30000) {
            this.lastPushAtMs = now;
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$pushPendingThrottled$1(this, null), 3, null);
        }
    }

    public final void flushProgressNow(long positionMs, long durationMs) {
        this.lastProgressWriteAtMs = SystemClock.elapsedRealtime();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$flushProgressNow$1(this, positionMs, durationMs, null), 3, null);
    }

    public final void onTick(long positionMs, long durationMs) {
        Job launch$default;
        Job job = this.translationJob;
        if (!(job != null && job.isActive())) {
            SubtitleTranslator subtitleTranslator = this.translator;
            if (subtitleTranslator != null && subtitleTranslator.getIsNeeded()) {
                long target = this._offsetMs.getValue().longValue();
                launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$onTick$1(this, positionMs, target, null), 3, null);
                this.translationJob = launch$default;
            }
        }
        recordProgressThrottled(positionMs, durationMs);
        if (this.syncRepo.isServerConfigured()) {
            pushPendingThrottled();
        }
    }

    public final String activeCueText(long positionMs) {
        Object element$iv;
        long adjusted = positionMs - this._offsetMs.getValue().longValue();
        Iterable $this$firstOrNull$iv = this._cues.getValue();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            SubtitleCue it2 = (SubtitleCue) element$iv;
            long startMs = it2.getStartMs();
            boolean z = false;
            if (adjusted <= it2.getEndMs() && startMs <= adjusted) {
                z = true;
            }
            if (z) {
                break;
            }
        }
        SubtitleCue it3 = (SubtitleCue) element$iv;
        if (it3 == null) {
            return null;
        }
        String translatedText = it3.getTranslatedText();
        if (translatedText == null) {
            translatedText = it3.getOriginalText();
        }
        return translatedText;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        Job job = this.syncJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.translationJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        Job job3 = this.captureWatchdog;
        if (job3 != null) {
            Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
        }
        Job job4 = this.rememberDwellJob;
        if (job4 != null) {
            Job.DefaultImpls.cancel$default(job4, (CancellationException) null, 1, (Object) null);
        }
        this.audioCapture.clear();
        SubtitleTranslator subtitleTranslator = this.translator;
        if (subtitleTranslator != null) {
            subtitleTranslator.close();
        }
        StreamRequestHeaders.INSTANCE.clear();
    }

    private final void applyOffset(long offsetMs) {
        this._offsetMs.setValue(Long.valueOf(offsetMs));
        this.offsetStore.set(this.mediaId, offsetMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyAutoSyncOffset(long offsetMs, boolean aiConfirmed) {
        this._offsetMs.setValue(Long.valueOf(offsetMs));
        boolean alreadyRemembered = this.offsetStore.get(this.mediaId) == offsetMs;
        if (alreadyRemembered) {
            this.offsetStore.set(this.mediaId, offsetMs);
        } else if (!aiConfirmed) {
            FileLog.d$default(FileLog.INSTANCE, "Auto sync: không nhớ offset cho lần xem sau — AI chưa xác nhận (chỉ suy ra từ mốc thời gian)", null, 2, null);
        } else {
            startDwellBeforeRemembering(offsetMs);
        }
    }

    private final void startDwellBeforeRemembering(long offsetMs) {
        Job launch$default;
        Job job = this.rememberDwellJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.rememberAtMs = offsetMs;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new PlayerViewModel$startDwellBeforeRemembering$1(this, null), 3, null);
        this.rememberDwellJob = launch$default;
    }

    private final String pct(double score) {
        return ((int) (100 * score)) + "%";
    }

    /* compiled from: PlayerScreen.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerViewModel$Companion;", "", "()V", "CAPTURE_LIMIT_MS", "", "PEER_TRACK_LIMIT", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
