package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.image.ImageRenderer;
import androidx.media3.exoplayer.mediacodec.DefaultMediaCodecAdapterFactory;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    public static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private final DefaultMediaCodecAdapterFactory codecAdapterFactory;
    private final Context context;
    private boolean enableAudioTrackPlaybackParams;
    private boolean enableDecoderFallback;
    private boolean enableFloatOutput;
    private int extensionRendererMode = 0;
    private long allowedVideoJoiningTimeMs = 5000;
    private MediaCodecSelector mediaCodecSelector = MediaCodecSelector.DEFAULT;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface ExtensionRendererMode {
    }

    public DefaultRenderersFactory(Context context) {
        this.context = context;
        this.codecAdapterFactory = new DefaultMediaCodecAdapterFactory(context);
    }

    public final DefaultRenderersFactory setExtensionRendererMode(int extensionRendererMode) {
        this.extensionRendererMode = extensionRendererMode;
        return this;
    }

    public final DefaultRenderersFactory forceEnableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceEnableAsynchronous();
        return this;
    }

    public final DefaultRenderersFactory forceDisableMediaCodecAsynchronousQueueing() {
        this.codecAdapterFactory.forceDisableAsynchronous();
        return this;
    }

    public final DefaultRenderersFactory experimentalSetMediaCodecAsyncCryptoFlagEnabled(boolean enableAsyncCryptoFlag) {
        this.codecAdapterFactory.experimentalSetAsyncCryptoFlagEnabled(enableAsyncCryptoFlag);
        return this;
    }

    public final DefaultRenderersFactory setEnableDecoderFallback(boolean enableDecoderFallback) {
        this.enableDecoderFallback = enableDecoderFallback;
        return this;
    }

    public final DefaultRenderersFactory setMediaCodecSelector(MediaCodecSelector mediaCodecSelector) {
        this.mediaCodecSelector = mediaCodecSelector;
        return this;
    }

    public final DefaultRenderersFactory setEnableAudioFloatOutput(boolean enableFloatOutput) {
        this.enableFloatOutput = enableFloatOutput;
        return this;
    }

    public final DefaultRenderersFactory setEnableAudioTrackPlaybackParams(boolean enableAudioTrackPlaybackParams) {
        this.enableAudioTrackPlaybackParams = enableAudioTrackPlaybackParams;
        return this;
    }

    public final DefaultRenderersFactory setAllowedVideoJoiningTimeMs(long allowedVideoJoiningTimeMs) {
        this.allowedVideoJoiningTimeMs = allowedVideoJoiningTimeMs;
        return this;
    }

    @Override // androidx.media3.exoplayer.RenderersFactory
    public Renderer[] createRenderers(Handler eventHandler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textRendererOutput, MetadataOutput metadataRendererOutput) {
        ArrayList<Renderer> renderersList = new ArrayList<>();
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, eventHandler, videoRendererEventListener, this.allowedVideoJoiningTimeMs, renderersList);
        AudioSink audioSink = buildAudioSink(this.context, this.enableFloatOutput, this.enableAudioTrackPlaybackParams);
        if (audioSink != null) {
            buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, this.enableDecoderFallback, audioSink, eventHandler, audioRendererEventListener, renderersList);
        }
        buildTextRenderers(this.context, textRendererOutput, eventHandler.getLooper(), this.extensionRendererMode, renderersList);
        buildMetadataRenderers(this.context, metadataRendererOutput, eventHandler.getLooper(), this.extensionRendererMode, renderersList);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, renderersList);
        buildImageRenderers(renderersList);
        buildMiscellaneousRenderers(this.context, eventHandler, this.extensionRendererMode, renderersList);
        return (Renderer[]) renderersList.toArray(new Renderer[0]);
    }

    protected void buildVideoRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, long allowedVideoJoiningTimeMs, ArrayList<Renderer> out) {
        int extensionRendererIndex;
        int extensionRendererIndex2;
        int extensionRendererIndex3;
        MediaCodecVideoRenderer videoRenderer = new MediaCodecVideoRenderer(context, getCodecAdapterFactory(), mediaCodecSelector, allowedVideoJoiningTimeMs, enableDecoderFallback, eventHandler, eventListener, 50);
        out.add(videoRenderer);
        if (extensionRendererMode == 0) {
            return;
        }
        int extensionRendererIndex4 = out.size();
        if (extensionRendererMode != 2) {
            extensionRendererIndex = extensionRendererIndex4;
        } else {
            extensionRendererIndex = extensionRendererIndex4 - 1;
        }
        try {
            Class<?> clazz = Class.forName("androidx.media3.decoder.vp9.LibvpxVideoRenderer");
            Constructor<?> constructor = clazz.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
            Renderer renderer = (Renderer) constructor.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
            extensionRendererIndex2 = extensionRendererIndex + 1;
            try {
                out.add(extensionRendererIndex, renderer);
                Log.i(TAG, "Loaded LibvpxVideoRenderer.");
            } catch (ClassNotFoundException e) {
                extensionRendererIndex = extensionRendererIndex2;
                extensionRendererIndex2 = extensionRendererIndex;
                Class<?> clazz2 = Class.forName("androidx.media3.decoder.av1.Libgav1VideoRenderer");
                Constructor<?> constructor2 = clazz2.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
                Renderer renderer2 = (Renderer) constructor2.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
                extensionRendererIndex3 = extensionRendererIndex2 + 1;
                try {
                    out.add(extensionRendererIndex2, renderer2);
                    Log.i(TAG, "Loaded Libgav1VideoRenderer.");
                } catch (ClassNotFoundException e2) {
                    extensionRendererIndex2 = extensionRendererIndex3;
                    extensionRendererIndex3 = extensionRendererIndex2;
                    Class<?> clazz3 = Class.forName("androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer");
                    Constructor<?> constructor3 = clazz3.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
                    Renderer renderer3 = (Renderer) constructor3.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
                    int extensionRendererIndex5 = extensionRendererIndex3 + 1;
                    out.add(extensionRendererIndex3, renderer3);
                    Log.i(TAG, "Loaded FfmpegVideoRenderer.");
                } catch (Exception e3) {
                    e = e3;
                    throw new RuntimeException("Error instantiating AV1 extension", e);
                }
                Class<?> clazz32 = Class.forName("androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer");
                Constructor<?> constructor32 = clazz32.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
                Renderer renderer32 = (Renderer) constructor32.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
                int extensionRendererIndex52 = extensionRendererIndex3 + 1;
                try {
                    out.add(extensionRendererIndex3, renderer32);
                    Log.i(TAG, "Loaded FfmpegVideoRenderer.");
                } catch (ClassNotFoundException e4) {
                    extensionRendererIndex3 = extensionRendererIndex52;
                    return;
                } catch (Exception e5) {
                    e = e5;
                    throw new RuntimeException("Error instantiating FFmpeg extension", e);
                }
            } catch (Exception e6) {
                e = e6;
                throw new RuntimeException("Error instantiating VP9 extension", e);
            }
        } catch (ClassNotFoundException e7) {
        } catch (Exception e8) {
            e = e8;
        }
        try {
            Class<?> clazz22 = Class.forName("androidx.media3.decoder.av1.Libgav1VideoRenderer");
            Constructor<?> constructor22 = clazz22.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
            Renderer renderer22 = (Renderer) constructor22.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
            extensionRendererIndex3 = extensionRendererIndex2 + 1;
            out.add(extensionRendererIndex2, renderer22);
            Log.i(TAG, "Loaded Libgav1VideoRenderer.");
        } catch (ClassNotFoundException e9) {
        } catch (Exception e10) {
            e = e10;
        }
        try {
            Class<?> clazz322 = Class.forName("androidx.media3.decoder.ffmpeg.ExperimentalFfmpegVideoRenderer");
            Constructor<?> constructor322 = clazz322.getConstructor(Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE);
            Renderer renderer322 = (Renderer) constructor322.newInstance(Long.valueOf(allowedVideoJoiningTimeMs), eventHandler, eventListener, 50);
            int extensionRendererIndex522 = extensionRendererIndex3 + 1;
            out.add(extensionRendererIndex3, renderer322);
            Log.i(TAG, "Loaded FfmpegVideoRenderer.");
        } catch (ClassNotFoundException e11) {
        } catch (Exception e12) {
            e = e12;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:5|(1:7)(1:76)|8|(5:9|10|11|12|13)|14|(3:15|16|17)|(4:(2:18|19)|28|29|31)|20|21|22|23|24|25|26|27) */
    /* JADX WARN: Can't wrap try/catch for region: R(17:5|(1:7)(1:76)|8|(5:9|10|11|12|13)|14|15|16|17|(4:(2:18|19)|28|29|31)|20|21|22|23|24|25|26|27) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00dc, code lost:
    
        r15 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e8, code lost:
    
        r9 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e6, code lost:
    
        throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00de, code lost:
    
        r0 = e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void buildAudioRenderers(android.content.Context r17, int r18, androidx.media3.exoplayer.mediacodec.MediaCodecSelector r19, boolean r20, androidx.media3.exoplayer.audio.AudioSink r21, android.os.Handler r22, androidx.media3.exoplayer.audio.AudioRendererEventListener r23, java.util.ArrayList<androidx.media3.exoplayer.Renderer> r24) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, int, androidx.media3.exoplayer.mediacodec.MediaCodecSelector, boolean, androidx.media3.exoplayer.audio.AudioSink, android.os.Handler, androidx.media3.exoplayer.audio.AudioRendererEventListener, java.util.ArrayList):void");
    }

    protected void buildTextRenderers(Context context, TextOutput output, Looper outputLooper, int extensionRendererMode, ArrayList<Renderer> out) {
        out.add(new TextRenderer(output, outputLooper));
    }

    protected void buildMetadataRenderers(Context context, MetadataOutput output, Looper outputLooper, int extensionRendererMode, ArrayList<Renderer> out) {
        out.add(new MetadataRenderer(output, outputLooper));
    }

    protected void buildCameraMotionRenderers(Context context, int extensionRendererMode, ArrayList<Renderer> out) {
        out.add(new CameraMotionRenderer());
    }

    protected void buildImageRenderers(ArrayList<Renderer> out) {
        out.add(new ImageRenderer(ImageDecoder.Factory.DEFAULT, null));
    }

    protected void buildMiscellaneousRenderers(Context context, Handler eventHandler, int extensionRendererMode, ArrayList<Renderer> out) {
    }

    protected AudioSink buildAudioSink(Context context, boolean enableFloatOutput, boolean enableAudioTrackPlaybackParams) {
        return new DefaultAudioSink.Builder(context).setEnableFloatOutput(enableFloatOutput).setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams).build();
    }

    protected MediaCodecAdapter.Factory getCodecAdapterFactory() {
        return this.codecAdapterFactory;
    }
}
