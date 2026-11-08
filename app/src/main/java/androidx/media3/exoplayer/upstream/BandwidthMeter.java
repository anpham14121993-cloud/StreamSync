package androidx.media3.exoplayer.upstream;

import android.os.Handler;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes10.dex */
public interface BandwidthMeter {
    void addEventListener(Handler handler, EventListener eventListener);

    long getBitrateEstimate();

    TransferListener getTransferListener();

    void removeEventListener(EventListener eventListener);

    /* loaded from: classes10.dex */
    public interface EventListener {
        void onBandwidthSample(int i, long j, long j2);

        /* loaded from: classes10.dex */
        public static final class EventDispatcher {
            private final CopyOnWriteArrayList<HandlerAndListener> listeners = new CopyOnWriteArrayList<>();

            public void addListener(Handler eventHandler, EventListener eventListener) {
                Assertions.checkNotNull(eventHandler);
                Assertions.checkNotNull(eventListener);
                removeListener(eventListener);
                this.listeners.add(new HandlerAndListener(eventHandler, eventListener));
            }

            public void removeListener(EventListener eventListener) {
                Iterator<HandlerAndListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    HandlerAndListener handlerAndListener = it.next();
                    if (handlerAndListener.listener == eventListener) {
                        handlerAndListener.release();
                        this.listeners.remove(handlerAndListener);
                    }
                }
            }

            public void bandwidthSample(final int elapsedMs, final long bytesTransferred, final long bitrateEstimate) {
                Iterator<HandlerAndListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    final HandlerAndListener handlerAndListener = it.next();
                    if (!handlerAndListener.released) {
                        handlerAndListener.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.upstream.BandwidthMeter$EventListener$EventDispatcher$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener.this.listener.onBandwidthSample(elapsedMs, bytesTransferred, bitrateEstimate);
                            }
                        });
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes10.dex */
            public static final class HandlerAndListener {
                private final Handler handler;
                private final EventListener listener;
                private boolean released;

                public HandlerAndListener(Handler handler, EventListener eventListener) {
                    this.handler = handler;
                    this.listener = eventListener;
                }

                public void release() {
                    this.released = true;
                }
            }
        }
    }

    default long getTimeToFirstByteEstimateUs() {
        return C.TIME_UNSET;
    }
}
