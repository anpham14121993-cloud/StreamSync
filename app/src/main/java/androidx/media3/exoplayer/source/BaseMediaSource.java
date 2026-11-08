package androidx.media3.exoplayer.source;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes7.dex */
public abstract class BaseMediaSource implements MediaSource {
    private Looper looper;
    private PlayerId playerId;
    private Timeline timeline;
    private final ArrayList<MediaSource.MediaSourceCaller> mediaSourceCallers = new ArrayList<>(1);
    private final HashSet<MediaSource.MediaSourceCaller> enabledMediaSourceCallers = new HashSet<>(1);
    private final MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher = new DrmSessionEventListener.EventDispatcher();

    protected abstract void prepareSourceInternal(TransferListener transferListener);

    protected abstract void releaseSourceInternal();

    protected void enableInternal() {
    }

    protected void disableInternal() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void refreshSourceInfo(Timeline timeline) {
        this.timeline = timeline;
        Iterator<MediaSource.MediaSourceCaller> it = this.mediaSourceCallers.iterator();
        while (it.hasNext()) {
            MediaSource.MediaSourceCaller caller = it.next();
            caller.onSourceInfoRefreshed(this, timeline);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(windowIndex, mediaPeriodId);
    }

    @Deprecated
    protected final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId, long mediaTimeOffsetMs) {
        Assertions.checkNotNull(mediaPeriodId);
        return this.eventDispatcher.withParameters(0, mediaPeriodId);
    }

    @Deprecated
    protected final MediaSourceEventListener.EventDispatcher createEventDispatcher(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId, long mediaTimeOffsetMs) {
        return this.eventDispatcher.withParameters(windowIndex, mediaPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(0, mediaPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DrmSessionEventListener.EventDispatcher createDrmEventDispatcher(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
        return this.drmEventDispatcher.withParameters(windowIndex, mediaPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isEnabled() {
        return !this.enabledMediaSourceCallers.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PlayerId getPlayerId() {
        return (PlayerId) Assertions.checkStateNotNull(this.playerId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean prepareSourceCalled() {
        return !this.mediaSourceCallers.isEmpty();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addEventListener(Handler handler, MediaSourceEventListener eventListener) {
        Assertions.checkNotNull(handler);
        Assertions.checkNotNull(eventListener);
        this.eventDispatcher.addEventListener(handler, eventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeEventListener(MediaSourceEventListener eventListener) {
        this.eventDispatcher.removeEventListener(eventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void addDrmEventListener(Handler handler, DrmSessionEventListener eventListener) {
        Assertions.checkNotNull(handler);
        Assertions.checkNotNull(eventListener);
        this.drmEventDispatcher.addEventListener(handler, eventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void removeDrmEventListener(DrmSessionEventListener eventListener) {
        this.drmEventDispatcher.removeEventListener(eventListener);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller caller, TransferListener mediaTransferListener) {
        prepareSource(caller, mediaTransferListener, PlayerId.UNSET);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void prepareSource(MediaSource.MediaSourceCaller caller, TransferListener mediaTransferListener, PlayerId playerId) {
        Looper looper = Looper.myLooper();
        Assertions.checkArgument(this.looper == null || this.looper == looper);
        this.playerId = playerId;
        Timeline timeline = this.timeline;
        this.mediaSourceCallers.add(caller);
        if (this.looper == null) {
            this.looper = looper;
            this.enabledMediaSourceCallers.add(caller);
            prepareSourceInternal(mediaTransferListener);
        } else if (timeline != null) {
            enable(caller);
            caller.onSourceInfoRefreshed(this, timeline);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void enable(MediaSource.MediaSourceCaller caller) {
        Assertions.checkNotNull(this.looper);
        boolean wasDisabled = this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.add(caller);
        if (wasDisabled) {
            enableInternal();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void disable(MediaSource.MediaSourceCaller caller) {
        boolean wasEnabled = !this.enabledMediaSourceCallers.isEmpty();
        this.enabledMediaSourceCallers.remove(caller);
        if (wasEnabled && this.enabledMediaSourceCallers.isEmpty()) {
            disableInternal();
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public final void releaseSource(MediaSource.MediaSourceCaller caller) {
        this.mediaSourceCallers.remove(caller);
        if (this.mediaSourceCallers.isEmpty()) {
            this.looper = null;
            this.timeline = null;
            this.playerId = null;
            this.enabledMediaSourceCallers.clear();
            releaseSourceInternal();
            return;
        }
        disable(caller);
    }
}
