package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes5.dex */
public final class DefaultMediaCodecAdapterFactory implements MediaCodecAdapter.Factory {
    private static final int MODE_DEFAULT = 0;
    private static final int MODE_DISABLED = 2;
    private static final int MODE_ENABLED = 1;
    private static final String TAG = "DMCodecAdapterFactory";
    private boolean asyncCryptoFlagEnabled;
    private int asynchronousMode;
    private final Context context;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    private @interface Mode {
    }

    @Deprecated
    public DefaultMediaCodecAdapterFactory() {
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = true;
        this.context = null;
    }

    public DefaultMediaCodecAdapterFactory(Context context) {
        this.context = context;
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = true;
    }

    public DefaultMediaCodecAdapterFactory forceEnableAsynchronous() {
        this.asynchronousMode = 1;
        return this;
    }

    public DefaultMediaCodecAdapterFactory forceDisableAsynchronous() {
        this.asynchronousMode = 2;
        return this;
    }

    public DefaultMediaCodecAdapterFactory experimentalSetAsyncCryptoFlagEnabled(boolean enableAsyncCryptoFlag) {
        this.asyncCryptoFlagEnabled = enableAsyncCryptoFlag;
        return this;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
    public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration configuration) throws IOException {
        if (Util.SDK_INT >= 23 && (this.asynchronousMode == 1 || (this.asynchronousMode == 0 && shouldUseAsynchronousAdapterInDefaultMode()))) {
            int trackType = MimeTypes.getTrackType(configuration.format.sampleMimeType);
            Log.i(TAG, "Creating an asynchronous MediaCodec adapter for track type " + Util.getTrackTypeString(trackType));
            AsynchronousMediaCodecAdapter.Factory factory = new AsynchronousMediaCodecAdapter.Factory(trackType);
            factory.experimentalSetAsyncCryptoFlagEnabled(this.asyncCryptoFlagEnabled);
            return factory.createAdapter(configuration);
        }
        return new SynchronousMediaCodecAdapter.Factory().createAdapter(configuration);
    }

    private boolean shouldUseAsynchronousAdapterInDefaultMode() {
        if (Util.SDK_INT >= 31) {
            return true;
        }
        return this.context != null && Util.SDK_INT >= 28 && this.context.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen");
    }
}
