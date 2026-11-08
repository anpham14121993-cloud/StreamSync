package androidx.media3.decoder.ffmpeg;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.decoder.SimpleDecoder;
import androidx.media3.decoder.SimpleDecoderOutputBuffer;
import androidx.media3.extractor.mp4.Atom;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes23.dex */
public final class FfmpegAudioDecoder extends SimpleDecoder<DecoderInputBuffer, SimpleDecoderOutputBuffer, FfmpegDecoderException> {
    private static final int AUDIO_DECODER_ERROR_INVALID_DATA = -1;
    private static final int AUDIO_DECODER_ERROR_OTHER = -2;
    private static final int INITIAL_OUTPUT_BUFFER_SIZE_16BIT = 65535;
    private static final int INITIAL_OUTPUT_BUFFER_SIZE_32BIT = 131070;
    private volatile int channelCount;
    private final String codecName;
    private final int encoding;
    private final byte[] extraData;
    private boolean hasOutputFormat;
    private long nativeContext;
    private int outputBufferSize;
    private volatile int sampleRate;

    private native int ffmpegDecode(long context, ByteBuffer inputData, int inputSize, SimpleDecoderOutputBuffer decoderOutputBuffer, ByteBuffer outputData, int outputSize);

    private native int ffmpegGetChannelCount(long context);

    private native int ffmpegGetSampleRate(long context);

    private native long ffmpegInitialize(String codecName, byte[] extraData, boolean outputFloat, int rawSampleRate, int rawChannelCount);

    private native void ffmpegRelease(long context);

    private native long ffmpegReset(long context, byte[] extraData);

    public FfmpegAudioDecoder(Format format, int numInputBuffers, int numOutputBuffers, int initialInputBufferSize, boolean outputFloat) throws FfmpegDecoderException {
        super(new DecoderInputBuffer[numInputBuffers], new SimpleDecoderOutputBuffer[numOutputBuffers]);
        if (!FfmpegLibrary.isAvailable()) {
            throw new FfmpegDecoderException("Failed to load decoder native libraries.");
        }
        Assertions.checkNotNull(format.sampleMimeType);
        this.codecName = (String) Assertions.checkNotNull(FfmpegLibrary.getCodecName(format.sampleMimeType));
        this.extraData = getExtraData(format.sampleMimeType, format.initializationData);
        this.encoding = outputFloat ? 4 : 2;
        this.outputBufferSize = outputFloat ? INITIAL_OUTPUT_BUFFER_SIZE_32BIT : 65535;
        this.nativeContext = ffmpegInitialize(this.codecName, this.extraData, outputFloat, format.sampleRate, format.channelCount);
        if (this.nativeContext == 0) {
            throw new FfmpegDecoderException("Initialization failed.");
        }
        setInitialInputBufferSize(initialInputBufferSize);
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
    }

    @Override // androidx.media3.decoder.SimpleDecoder
    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(2, FfmpegLibrary.getInputBufferPaddingSize());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public SimpleDecoderOutputBuffer createOutputBuffer() {
        return new SimpleDecoderOutputBuffer(new DecoderOutputBuffer.Owner() { // from class: androidx.media3.decoder.ffmpeg.FfmpegAudioDecoder$$ExternalSyntheticLambda0
            @Override // androidx.media3.decoder.DecoderOutputBuffer.Owner
            public final void releaseOutputBuffer(DecoderOutputBuffer decoderOutputBuffer) {
                FfmpegAudioDecoder.this.releaseOutputBuffer((SimpleDecoderOutputBuffer) decoderOutputBuffer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public FfmpegDecoderException createUnexpectedDecodeException(Throwable error) {
        return new FfmpegDecoderException("Unexpected decode error", error);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.media3.decoder.SimpleDecoder
    public FfmpegDecoderException decode(DecoderInputBuffer inputBuffer, SimpleDecoderOutputBuffer outputBuffer, boolean reset) {
        if (reset) {
            this.nativeContext = ffmpegReset(this.nativeContext, this.extraData);
            if (this.nativeContext == 0) {
                return new FfmpegDecoderException("Error resetting (see logcat).");
            }
        }
        ByteBuffer inputData = (ByteBuffer) Util.castNonNull(inputBuffer.data);
        int inputSize = inputData.limit();
        int result = ffmpegDecode(this.nativeContext, inputData, inputSize, outputBuffer, outputBuffer.init(inputBuffer.timeUs, this.outputBufferSize), this.outputBufferSize);
        if (result == -2) {
            return new FfmpegDecoderException("Error decoding (see logcat).");
        }
        if (result == -1) {
            outputBuffer.shouldBeSkipped = true;
            return null;
        }
        if (result == 0) {
            outputBuffer.shouldBeSkipped = true;
            return null;
        }
        if (!this.hasOutputFormat) {
            this.channelCount = ffmpegGetChannelCount(this.nativeContext);
            this.sampleRate = ffmpegGetSampleRate(this.nativeContext);
            if (this.sampleRate == 0 && "alac".equals(this.codecName)) {
                Assertions.checkNotNull(this.extraData);
                ParsableByteArray parsableExtraData = new ParsableByteArray(this.extraData);
                parsableExtraData.setPosition(this.extraData.length - 4);
                this.sampleRate = parsableExtraData.readUnsignedIntToInt();
            }
            this.hasOutputFormat = true;
        }
        ByteBuffer outputData = (ByteBuffer) Assertions.checkNotNull(outputBuffer.data);
        outputData.position(0);
        outputData.limit(result);
        return null;
    }

    private ByteBuffer growOutputBuffer(SimpleDecoderOutputBuffer outputBuffer, int requiredSize) {
        this.outputBufferSize = requiredSize;
        return outputBuffer.grow(requiredSize);
    }

    @Override // androidx.media3.decoder.SimpleDecoder, androidx.media3.decoder.Decoder
    public void release() {
        super.release();
        ffmpegRelease(this.nativeContext);
        this.nativeContext = 0L;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getEncoding() {
        return this.encoding;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static byte[] getExtraData(String mimeType, List<byte[]> initializationData) {
        char c;
        switch (mimeType.hashCode()) {
            case -1003765268:
                if (mimeType.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -53558318:
                if (mimeType.equals(MimeTypes.AUDIO_AAC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1504470054:
                if (mimeType.equals(MimeTypes.AUDIO_ALAC)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1504891608:
                if (mimeType.equals(MimeTypes.AUDIO_OPUS)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return initializationData.get(0);
            case 2:
                return getAlacExtraData(initializationData);
            case 3:
                return getVorbisExtraData(initializationData);
            default:
                return null;
        }
    }

    private static byte[] getAlacExtraData(List<byte[]> initializationData) {
        byte[] magicCookie = initializationData.get(0);
        int alacAtomLength = magicCookie.length + 12;
        ByteBuffer alacAtom = ByteBuffer.allocate(alacAtomLength);
        alacAtom.putInt(alacAtomLength);
        alacAtom.putInt(Atom.TYPE_alac);
        alacAtom.putInt(0);
        alacAtom.put(magicCookie, 0, magicCookie.length);
        return alacAtom.array();
    }

    private static byte[] getVorbisExtraData(List<byte[]> initializationData) {
        byte[] header0 = initializationData.get(0);
        byte[] header1 = initializationData.get(1);
        byte[] extraData = new byte[header0.length + header1.length + 6];
        extraData[0] = (byte) (header0.length >> 8);
        extraData[1] = (byte) (header0.length & 255);
        System.arraycopy(header0, 0, extraData, 2, header0.length);
        extraData[header0.length + 2] = 0;
        extraData[header0.length + 3] = 0;
        extraData[header0.length + 4] = (byte) (header1.length >> 8);
        extraData[header0.length + 5] = (byte) (header1.length & 255);
        System.arraycopy(header1, 0, extraData, header0.length + 6, header1.length);
        return extraData;
    }
}
