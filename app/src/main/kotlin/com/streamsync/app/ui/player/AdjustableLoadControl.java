package com.streamsync.app.ui.player;

import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AdjustableLoadControl.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003J\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0003J(\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020!H\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u001e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u001e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/streamsync/app/ui/player/AdjustableLoadControl;", "Landroidx/media3/exoplayer/DefaultLoadControl;", "minBufferMs", "", "maxBufferMs", "bufferForPlaybackMs", "bufferForPlaybackAfterRebufferMs", "backBufferSec", "retainBackBufferFromKeyframe", "", "prioritizeTimeOverSizeThresholds", "targetBufferBytes", "allocatorPoolSize", "(IIIIIZZII)V", "getBackBufferSec", "()I", "justSeeked", "getJustSeeked", "()Z", "setJustSeeked", "(Z)V", "getMaxBufferMs", "<set-?>", "seekBufferMs", "getSeekBufferMs", "startBufferMs", "getStartBufferMs", "setSeekBufferMs", "", "valueMs", "setStartBufferMs", "shouldStartPlayback", "bufferedDurationUs", "", "playbackSpeed", "", "rebuffering", "playbackPositionUs", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class AdjustableLoadControl extends DefaultLoadControl {
    public static final int DEFAULT_REBUFFER_MS = 5000;
    private final int backBufferSec;
    private volatile boolean justSeeked;
    private final int maxBufferMs;
    private volatile int seekBufferMs;
    private volatile int startBufferMs;
    private final int targetBufferBytes;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public final int getMaxBufferMs() {
        return this.maxBufferMs;
    }

    public final int getBackBufferSec() {
        return this.backBufferSec;
    }

    public AdjustableLoadControl(int minBufferMs, int maxBufferMs, int bufferForPlaybackMs, int bufferForPlaybackAfterRebufferMs, int backBufferSec, boolean retainBackBufferFromKeyframe, boolean prioritizeTimeOverSizeThresholds, int targetBufferBytes, int allocatorPoolSize) {
        super(new DefaultAllocator(true, 65536, allocatorPoolSize), minBufferMs, maxBufferMs, bufferForPlaybackMs, bufferForPlaybackAfterRebufferMs, targetBufferBytes, prioritizeTimeOverSizeThresholds, backBufferSec * 1000, retainBackBufferFromKeyframe);
        this.maxBufferMs = maxBufferMs;
        this.backBufferSec = backBufferSec;
        this.targetBufferBytes = targetBufferBytes;
        this.startBufferMs = bufferForPlaybackMs;
        this.seekBufferMs = bufferForPlaybackAfterRebufferMs;
    }

    public final int getStartBufferMs() {
        return this.startBufferMs;
    }

    public final int getSeekBufferMs() {
        return this.seekBufferMs;
    }

    public final boolean getJustSeeked() {
        return this.justSeeked;
    }

    public final void setJustSeeked(boolean z) {
        this.justSeeked = z;
    }

    public final void setStartBufferMs(int valueMs) {
        if (valueMs > 0) {
            this.startBufferMs = valueMs;
        }
    }

    public final void setSeekBufferMs(int valueMs) {
        if (valueMs > 0) {
            this.seekBufferMs = valueMs;
        }
    }

    @Override // androidx.media3.exoplayer.LoadControl
    public boolean shouldStartPlayback(long bufferedDurationUs, float playbackSpeed, boolean rebuffering, long playbackPositionUs) {
        int threshold;
        long needed;
        if (rebuffering) {
            threshold = this.justSeeked ? this.seekBufferMs : 5000;
        } else {
            threshold = this.startBufferMs;
        }
        if (playbackSpeed > 0.0f) {
            needed = (long) ((threshold * 1000.0d) / playbackSpeed);
        } else {
            needed = threshold * 1000;
        }
        return bufferedDurationUs >= needed;
    }

    /* compiled from: AdjustableLoadControl.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/ui/player/AdjustableLoadControl$Companion;", "", "()V", "DEFAULT_REBUFFER_MS", "", "stock", "Lcom/streamsync/app/ui/player/AdjustableLoadControl;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AdjustableLoadControl stock() {
            return new AdjustableLoadControl(50000, 50000, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000, 10, true, false, -1, 64);
        }
    }
}
