package androidx.media3.exoplayer.drm;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* loaded from: classes5.dex */
public final class OfflineLicenseHelper {
    private static final Format FORMAT_WITH_EMPTY_DRM_INIT_DATA = new Format.Builder().setDrmInitData(new DrmInitData(new DrmInitData.SchemeData[0])).build();
    private final ConditionVariable drmListenerConditionVariable;
    private final DefaultDrmSessionManager drmSessionManager;
    private final DrmSessionEventListener.EventDispatcher eventDispatcher;
    private final Handler handler;
    private final HandlerThread handlerThread = new HandlerThread("ExoPlayer:OfflineLicenseHelper");

    public static OfflineLicenseHelper newWidevineInstance(String defaultLicenseUrl, DataSource.Factory dataSourceFactory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(defaultLicenseUrl, false, dataSourceFactory, eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String defaultLicenseUrl, boolean forceDefaultLicenseUrl, DataSource.Factory dataSourceFactory, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return newWidevineInstance(defaultLicenseUrl, forceDefaultLicenseUrl, dataSourceFactory, null, eventDispatcher);
    }

    public static OfflineLicenseHelper newWidevineInstance(String defaultLicenseUrl, boolean forceDefaultLicenseUrl, DataSource.Factory dataSourceFactory, Map<String, String> optionalKeyRequestParameters, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return new OfflineLicenseHelper(new DefaultDrmSessionManager.Builder().setKeyRequestParameters(optionalKeyRequestParameters).build(new HttpMediaDrmCallback(defaultLicenseUrl, forceDefaultLicenseUrl, dataSourceFactory)), eventDispatcher);
    }

    public OfflineLicenseHelper(DefaultDrmSessionManager defaultDrmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.drmSessionManager = defaultDrmSessionManager;
        this.eventDispatcher = eventDispatcher;
        this.handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
        this.drmListenerConditionVariable = new ConditionVariable();
        DrmSessionEventListener eventListener = new DrmSessionEventListener() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper.1
            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysLoaded(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmSessionManagerError(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId, Exception e) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysRestored(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
            public void onDrmKeysRemoved(int windowIndex, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }
        };
        eventDispatcher.addEventListener(new Handler(this.handlerThread.getLooper()), eventListener);
    }

    public synchronized byte[] downloadLicense(Format format) throws DrmSession.DrmSessionException {
        Assertions.checkArgument(format.drmInitData != null);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, null, format);
    }

    public synchronized byte[] renewLicense(byte[] offlineLicenseKeySetId) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(offlineLicenseKeySetId);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, offlineLicenseKeySetId, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public synchronized void releaseLicense(byte[] offlineLicenseKeySetId) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(offlineLicenseKeySetId);
        acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(3, offlineLicenseKeySetId, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public synchronized Pair<Long, Long> getLicenseDurationRemainingSec(byte[] offlineLicenseKeySetId) throws DrmSession.DrmSessionException {
        final SettableFuture<Pair<Long, Long>> licenseDurationRemainingSec;
        Assertions.checkNotNull(offlineLicenseKeySetId);
        try {
            final DrmSession drmSession = acquireFirstSessionOnHandlerThread(1, offlineLicenseKeySetId, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
            licenseDurationRemainingSec = SettableFuture.create();
            this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineLicenseHelper.this.lambda$getLicenseDurationRemainingSec$0(licenseDurationRemainingSec, drmSession);
                }
            });
            try {
                try {
                } finally {
                    releaseManagerOnHandlerThread();
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } catch (DrmSession.DrmSessionException e2) {
            if (e2.getCause() instanceof KeysExpiredException) {
                return Pair.create(0L, 0L);
            }
            throw e2;
        }
        return licenseDurationRemainingSec.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getLicenseDurationRemainingSec$0(SettableFuture licenseDurationRemainingSec, DrmSession drmSession) {
        try {
            licenseDurationRemainingSec.set((Pair) Assertions.checkNotNull(WidevineUtil.getLicenseDurationRemainingSec(drmSession)));
        } finally {
            try {
            } finally {
            }
        }
    }

    public void release() {
        this.handlerThread.quit();
    }

    private byte[] acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(int licenseMode, byte[] offlineLicenseKeySetId, Format format) throws DrmSession.DrmSessionException {
        final DrmSession drmSession = acquireFirstSessionOnHandlerThread(licenseMode, offlineLicenseKeySetId, format);
        final SettableFuture<byte[]> keySetId = SettableFuture.create();
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.this.lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1(keySetId, drmSession);
            }
        });
        try {
            try {
                return (byte[]) Assertions.checkNotNull(keySetId.get());
            } finally {
                releaseManagerOnHandlerThread();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1(SettableFuture keySetId, DrmSession drmSession) {
        try {
            keySetId.set(drmSession.getOfflineLicenseKeySetId());
        } finally {
            try {
            } finally {
            }
        }
    }

    private DrmSession acquireFirstSessionOnHandlerThread(final int licenseMode, final byte[] offlineLicenseKeySetId, final Format format) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(format.drmInitData);
        final SettableFuture<DrmSession> drmSessionFuture = SettableFuture.create();
        this.drmListenerConditionVariable.close();
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.this.lambda$acquireFirstSessionOnHandlerThread$2(licenseMode, offlineLicenseKeySetId, drmSessionFuture, format);
            }
        });
        try {
            final DrmSession drmSession = drmSessionFuture.get();
            this.drmListenerConditionVariable.block();
            final SettableFuture<DrmSession.DrmSessionException> drmSessionError = SettableFuture.create();
            this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineLicenseHelper.this.lambda$acquireFirstSessionOnHandlerThread$3(drmSession, drmSessionError);
                }
            });
            try {
                if (drmSessionError.get() != null) {
                    throw drmSessionError.get();
                }
                return drmSession;
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } catch (InterruptedException | ExecutionException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acquireFirstSessionOnHandlerThread$2(int licenseMode, byte[] offlineLicenseKeySetId, SettableFuture drmSessionFuture, Format format) {
        try {
            this.drmSessionManager.setPlayer((Looper) Assertions.checkNotNull(Looper.myLooper()), PlayerId.UNSET);
            this.drmSessionManager.prepare();
            try {
                this.drmSessionManager.setMode(licenseMode, offlineLicenseKeySetId);
                drmSessionFuture.set((DrmSession) Assertions.checkNotNull(this.drmSessionManager.acquireSession(this.eventDispatcher, format)));
            } catch (Throwable e) {
                this.drmSessionManager.release();
                throw e;
            }
        } catch (Throwable e2) {
            drmSessionFuture.setException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$acquireFirstSessionOnHandlerThread$3(DrmSession drmSession, SettableFuture drmSessionError) {
        try {
            DrmSession.DrmSessionException error = drmSession.getError();
            if (drmSession.getState() == 1) {
                drmSession.release(this.eventDispatcher);
                this.drmSessionManager.release();
            }
            drmSessionError.set(error);
        } catch (Throwable e) {
            drmSessionError.setException(e);
            drmSession.release(this.eventDispatcher);
            this.drmSessionManager.release();
        }
    }

    private void releaseManagerOnHandlerThread() {
        final SettableFuture<Void> result = SettableFuture.create();
        this.handler.post(new Runnable() { // from class: androidx.media3.exoplayer.drm.OfflineLicenseHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                OfflineLicenseHelper.this.lambda$releaseManagerOnHandlerThread$4(result);
            }
        });
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseManagerOnHandlerThread$4(SettableFuture result) {
        try {
            this.drmSessionManager.release();
            result.set(null);
        } catch (Throwable e) {
            result.setException(e);
        }
    }
}
