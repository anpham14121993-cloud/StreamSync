package androidx.media3.decoder.ffmpeg;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DecoderAudioRenderer;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* loaded from: classes23.dex */
public final class FfmpegAudioRenderer extends DecoderAudioRenderer<FfmpegAudioDecoder> {
    private static final int DEFAULT_INPUT_BUFFER_SIZE = 5760;
    private static final int NUM_BUFFERS = 16;
    private static final String TAG = "FfmpegAudioRenderer";

    public FfmpegAudioRenderer() {
        this((Handler) null, (AudioRendererEventListener) null, new AudioProcessor[0]);
    }

    public FfmpegAudioRenderer(Handler eventHandler, AudioRendererEventListener eventListener, AudioProcessor... audioProcessors) {
        this(eventHandler, eventListener, new DefaultAudioSink.Builder().setAudioProcessors(audioProcessors).build());
    }

    public FfmpegAudioRenderer(Handler eventHandler, AudioRendererEventListener eventListener, AudioSink audioSink) {
        super(eventHandler, eventListener, audioSink);
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    protected int supportsFormatInternal(Format format) {
        String mimeType = (String) Assertions.checkNotNull(format.sampleMimeType);
        if (!FfmpegLibrary.isAvailable() || !MimeTypes.isAudio(mimeType)) {
            return 0;
        }
        if (FfmpegLibrary.supportsFormat(mimeType)) {
            if (sinkSupportsFormat(format, 2) || sinkSupportsFormat(format, 4)) {
                return format.cryptoType != 0 ? 2 : 4;
            }
            return 1;
        }
        return 1;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.RendererCapabilities
    public int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public FfmpegAudioDecoder createDecoder(Format format, CryptoConfig cryptoConfig) throws FfmpegDecoderException {
        TraceUtil.beginSection("createFfmpegAudioDecoder");
        int initialInputBufferSize = format.maxInputSize != -1 ? format.maxInputSize : DEFAULT_INPUT_BUFFER_SIZE;
        FfmpegAudioDecoder decoder = new FfmpegAudioDecoder(format, 16, 16, initialInputBufferSize, shouldOutputFloat(format));
        TraceUtil.endSection();
        return decoder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.exoplayer.audio.DecoderAudioRenderer
    public Format getOutputFormat(FfmpegAudioDecoder decoder) {
        Assertions.checkNotNull(decoder);
        return new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setChannelCount(decoder.getChannelCount()).setSampleRate(decoder.getSampleRate()).setPcmEncoding(decoder.getEncoding()).build();
    }

    private boolean sinkSupportsFormat(Format inputFormat, int pcmEncoding) {
        return sinkSupportsFormat(Util.getPcmFormat(pcmEncoding, inputFormat.channelCount, inputFormat.sampleRate));
    }

    private boolean shouldOutputFloat(Format inputFormat) {
        if (!sinkSupportsFormat(inputFormat, 2)) {
            return true;
        }
        int formatSupport = getSinkFormatSupport(Util.getPcmFormat(4, inputFormat.channelCount, inputFormat.sampleRate));
        switch (formatSupport) {
            case 2:
                return true ^ MimeTypes.AUDIO_AC3.equals(inputFormat.sampleMimeType);
            default:
                return false;
        }
    }
}
