package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class SilenceSkippingAudioProcessor extends androidx.media3.common.audio.BaseAudioProcessor {
    private static final int AVOID_TRUNCATION_FACTOR = 1000;
    public static final long DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US = 2000000;
    public static final long DEFAULT_MINIMUM_SILENCE_DURATION_US = 100000;
    public static final int DEFAULT_MIN_VOLUME_TO_KEEP_PERCENTAGE = 10;

    @Deprecated
    public static final long DEFAULT_PADDING_SILENCE_US = 20000;
    public static final float DEFAULT_SILENCE_RETENTION_RATIO = 0.2f;
    public static final short DEFAULT_SILENCE_THRESHOLD_LEVEL = 1024;
    private static final int DO_NOT_CHANGE_VOLUME = 3;
    private static final int FADE_IN = 2;
    private static final int FADE_OUT = 0;
    private static final int MUTE = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SHORTENING_SILENCE = 1;
    private int bytesPerFrame;
    private byte[] contiguousOutputBuffer;
    private boolean enabled;
    private final long maxSilenceToKeepDurationUs;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferContentsSize;
    private int maybeSilenceBufferStartIndex;
    private final int minVolumeToKeepPercentageWhenMuting;
    private final long minimumSilenceDurationUs;
    private int outputSilenceFramesSinceNoise;
    private final float silenceRetentionRatio;
    private final short silenceThresholdLevel;
    private long skippedFrames;
    private int state;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    private @interface State {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    private @interface VolumeChangeType {
    }

    public SilenceSkippingAudioProcessor() {
        this(100000L, 0.2f, DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US, 10, DEFAULT_SILENCE_THRESHOLD_LEVEL);
    }

    @Deprecated
    public SilenceSkippingAudioProcessor(long minimumSilenceDurationUs, long paddingSilenceUs, short silenceThresholdLevel) {
        this(minimumSilenceDurationUs, ((float) paddingSilenceUs) / ((float) minimumSilenceDurationUs), minimumSilenceDurationUs, 0, silenceThresholdLevel);
    }

    public SilenceSkippingAudioProcessor(long minimumSilenceDurationUs, float silenceRetentionRatio, long maxSilenceToKeepDurationUs, int minVolumeToKeepPercentageWhenMuting, short silenceThresholdLevel) {
        boolean z = false;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
        if (silenceRetentionRatio >= 0.0f && silenceRetentionRatio <= 1.0f) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.minimumSilenceDurationUs = minimumSilenceDurationUs;
        this.silenceRetentionRatio = silenceRetentionRatio;
        this.maxSilenceToKeepDurationUs = maxSilenceToKeepDurationUs;
        this.minVolumeToKeepPercentageWhenMuting = minVolumeToKeepPercentageWhenMuting;
        this.silenceThresholdLevel = silenceThresholdLevel;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.contiguousOutputBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    protected AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat inputAudioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (inputAudioFormat.encoding != 2) {
            throw new AudioProcessor.UnhandledAudioFormatException(inputAudioFormat);
        }
        if (inputAudioFormat.sampleRate == -1) {
            return AudioProcessor.AudioFormat.NOT_SET;
        }
        return inputAudioFormat;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor, androidx.media3.common.audio.AudioProcessor
    public boolean isActive() {
        return super.isActive() && this.enabled;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer inputBuffer) {
        while (inputBuffer.hasRemaining() && !hasPendingOutput()) {
            switch (this.state) {
                case 0:
                    processNoisy(inputBuffer);
                    break;
                case 1:
                    shortenSilenceSilenceUntilNoise(inputBuffer);
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onQueueEndOfStream() {
        if (this.maybeSilenceBufferContentsSize > 0) {
            outputShortenedSilenceBuffer(true);
            this.outputSilenceFramesSinceNoise = 0;
        }
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        if (isActive()) {
            this.bytesPerFrame = this.inputAudioFormat.channelCount * 2;
            int maybeSilenceBufferSize = alignToBytePerFrameBoundary(durationUsToFrames(this.minimumSilenceDurationUs) / 2) * 2;
            if (this.maybeSilenceBuffer.length != maybeSilenceBufferSize) {
                this.maybeSilenceBuffer = new byte[maybeSilenceBufferSize];
                this.contiguousOutputBuffer = new byte[maybeSilenceBufferSize];
            }
        }
        this.state = 0;
        this.skippedFrames = 0L;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.enabled = false;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.contiguousOutputBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    private void processNoisy(ByteBuffer inputBuffer) {
        int limit = inputBuffer.limit();
        inputBuffer.limit(Math.min(limit, inputBuffer.position() + this.maybeSilenceBuffer.length));
        int noiseLimit = findNoiseLimit(inputBuffer);
        if (noiseLimit == inputBuffer.position()) {
            this.state = 1;
        } else {
            inputBuffer.limit(Math.min(noiseLimit, inputBuffer.capacity()));
            output(inputBuffer);
        }
        inputBuffer.limit(limit);
    }

    private void shortenSilenceSilenceUntilNoise(ByteBuffer inputBuffer) {
        int indexToWriteTo;
        int amountInUpperPartOfBuffer;
        Assertions.checkState(this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length);
        int limit = inputBuffer.limit();
        int noisePosition = findNoisePosition(inputBuffer);
        int silenceInputSize = noisePosition - inputBuffer.position();
        if (this.maybeSilenceBufferStartIndex + this.maybeSilenceBufferContentsSize < this.maybeSilenceBuffer.length) {
            amountInUpperPartOfBuffer = this.maybeSilenceBuffer.length - (this.maybeSilenceBufferContentsSize + this.maybeSilenceBufferStartIndex);
            indexToWriteTo = this.maybeSilenceBufferStartIndex + this.maybeSilenceBufferContentsSize;
        } else {
            int amountInUpperPartOfBuffer2 = this.maybeSilenceBuffer.length - this.maybeSilenceBufferStartIndex;
            indexToWriteTo = this.maybeSilenceBufferContentsSize - amountInUpperPartOfBuffer2;
            amountInUpperPartOfBuffer = this.maybeSilenceBufferStartIndex - indexToWriteTo;
        }
        boolean noiseFound = noisePosition < limit;
        int bytesOfInput = Math.min(silenceInputSize, amountInUpperPartOfBuffer);
        inputBuffer.limit(inputBuffer.position() + bytesOfInput);
        inputBuffer.get(this.maybeSilenceBuffer, indexToWriteTo, bytesOfInput);
        this.maybeSilenceBufferContentsSize += bytesOfInput;
        Assertions.checkState(this.maybeSilenceBufferContentsSize <= this.maybeSilenceBuffer.length);
        boolean shouldTransitionToNoisyState = noiseFound && silenceInputSize < amountInUpperPartOfBuffer;
        outputShortenedSilenceBuffer(shouldTransitionToNoisyState);
        if (shouldTransitionToNoisyState) {
            this.state = 0;
            this.outputSilenceFramesSinceNoise = 0;
        }
        inputBuffer.limit(limit);
    }

    private void outputShortenedSilenceBuffer(boolean shouldTransitionToNoisyState) {
        int bytesConsumed;
        int bytesToOutput;
        int sizeBeforeOutput = this.maybeSilenceBufferContentsSize;
        if (this.maybeSilenceBufferContentsSize == this.maybeSilenceBuffer.length || shouldTransitionToNoisyState) {
            if (this.outputSilenceFramesSinceNoise == 0) {
                if (shouldTransitionToNoisyState) {
                    bytesToOutput = this.maybeSilenceBufferContentsSize;
                    outputSilence(bytesToOutput, 3);
                    bytesConsumed = bytesToOutput;
                } else {
                    int volumeChangeType = this.maybeSilenceBufferContentsSize;
                    Assertions.checkState(volumeChangeType >= this.maybeSilenceBuffer.length / 2);
                    bytesToOutput = this.maybeSilenceBuffer.length / 2;
                    outputSilence(bytesToOutput, 0);
                    bytesConsumed = bytesToOutput;
                }
            } else if (shouldTransitionToNoisyState) {
                int bytesRemainingAfterOutputtingHalfMin = this.maybeSilenceBufferContentsSize - (this.maybeSilenceBuffer.length / 2);
                bytesConsumed = (this.maybeSilenceBuffer.length / 2) + bytesRemainingAfterOutputtingHalfMin;
                int shortenedSilenceLength = calculateShortenedSilenceLength(bytesRemainingAfterOutputtingHalfMin);
                int bytesToOutput2 = (this.maybeSilenceBuffer.length / 2) + shortenedSilenceLength;
                outputSilence(bytesToOutput2, 2);
                bytesToOutput = bytesToOutput2;
            } else {
                bytesConsumed = this.maybeSilenceBufferContentsSize - (this.maybeSilenceBuffer.length / 2);
                bytesToOutput = calculateShortenedSilenceLength(bytesConsumed);
                outputSilence(bytesToOutput, 1);
            }
            Assertions.checkState(bytesConsumed % this.bytesPerFrame == 0, "bytesConsumed is not aligned to frame size: %s" + bytesConsumed);
            Assertions.checkState(sizeBeforeOutput >= bytesToOutput);
            this.maybeSilenceBufferContentsSize -= bytesConsumed;
            this.maybeSilenceBufferStartIndex += bytesConsumed;
            this.maybeSilenceBufferStartIndex %= this.maybeSilenceBuffer.length;
            this.outputSilenceFramesSinceNoise += bytesToOutput / this.bytesPerFrame;
            this.skippedFrames += (bytesConsumed - bytesToOutput) / this.bytesPerFrame;
        }
    }

    private int calculateShortenedSilenceLength(int silenceToShortenBytes) {
        int bytesNeededToReachMax = ((durationUsToFrames(this.maxSilenceToKeepDurationUs) - this.outputSilenceFramesSinceNoise) * this.bytesPerFrame) - (this.maybeSilenceBuffer.length / 2);
        Assertions.checkState(bytesNeededToReachMax >= 0);
        return alignToBytePerFrameBoundary(Math.min((silenceToShortenBytes * this.silenceRetentionRatio) + 0.5f, bytesNeededToReachMax));
    }

    private int alignToBytePerFrameBoundary(int value) {
        return (value / this.bytesPerFrame) * this.bytesPerFrame;
    }

    private int alignToBytePerFrameBoundary(float value) {
        return alignToBytePerFrameBoundary((int) value);
    }

    private void outputRange(byte[] data, int size, int rampType) {
        Assertions.checkArgument(size % this.bytesPerFrame == 0, "byteOutput size is not aligned to frame size " + size);
        modifyVolume(data, size, rampType);
        replaceOutputBuffer(size).put(data, 0, size).flip();
    }

    private void outputSilence(int sizeToOutput, int rampType) {
        if (sizeToOutput == 0) {
            return;
        }
        Assertions.checkArgument(this.maybeSilenceBufferContentsSize >= sizeToOutput);
        if (rampType == 2) {
            if (this.maybeSilenceBufferStartIndex + this.maybeSilenceBufferContentsSize <= this.maybeSilenceBuffer.length) {
                System.arraycopy(this.maybeSilenceBuffer, (this.maybeSilenceBufferStartIndex + this.maybeSilenceBufferContentsSize) - sizeToOutput, this.contiguousOutputBuffer, 0, sizeToOutput);
            } else {
                int sizeInUpperPartOfArray = this.maybeSilenceBuffer.length - this.maybeSilenceBufferStartIndex;
                int sizeInLowerPartOfArray = this.maybeSilenceBufferContentsSize - sizeInUpperPartOfArray;
                if (sizeInLowerPartOfArray >= sizeToOutput) {
                    System.arraycopy(this.maybeSilenceBuffer, sizeInLowerPartOfArray - sizeToOutput, this.contiguousOutputBuffer, 0, sizeToOutput);
                } else {
                    int sizeToOutputInUpperPart = sizeToOutput - sizeInLowerPartOfArray;
                    System.arraycopy(this.maybeSilenceBuffer, this.maybeSilenceBuffer.length - sizeToOutputInUpperPart, this.contiguousOutputBuffer, 0, sizeToOutputInUpperPart);
                    System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, sizeToOutputInUpperPart, sizeInLowerPartOfArray);
                }
            }
        } else if (this.maybeSilenceBufferStartIndex + sizeToOutput <= this.maybeSilenceBuffer.length) {
            System.arraycopy(this.maybeSilenceBuffer, this.maybeSilenceBufferStartIndex, this.contiguousOutputBuffer, 0, sizeToOutput);
        } else {
            int sizeToCopyInUpperPartOfArray = this.maybeSilenceBuffer.length - this.maybeSilenceBufferStartIndex;
            System.arraycopy(this.maybeSilenceBuffer, this.maybeSilenceBufferStartIndex, this.contiguousOutputBuffer, 0, sizeToCopyInUpperPartOfArray);
            int amountToCopyFromLowerPartOfArray = sizeToOutput - sizeToCopyInUpperPartOfArray;
            System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, sizeToCopyInUpperPartOfArray, amountToCopyFromLowerPartOfArray);
        }
        Assertions.checkArgument(sizeToOutput % this.bytesPerFrame == 0, "sizeToOutput is not aligned to frame size: " + sizeToOutput);
        Assertions.checkState(this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length);
        outputRange(this.contiguousOutputBuffer, sizeToOutput, rampType);
    }

    private void modifyVolume(byte[] sampleBuffer, int size, int volumeChangeType) {
        int volumeModificationPercentage;
        if (volumeChangeType == 3) {
            return;
        }
        for (int idx = 0; idx < size; idx += 2) {
            byte mostSignificantByte = sampleBuffer[idx + 1];
            byte leastSignificantByte = sampleBuffer[idx];
            int sample = twoByteSampleToInt(mostSignificantByte, leastSignificantByte);
            if (volumeChangeType == 0) {
                volumeModificationPercentage = calculateFadeOutPercentage(idx, size - 1);
            } else if (volumeChangeType == 2) {
                volumeModificationPercentage = calculateFadeInPercentage(idx, size - 1);
            } else {
                volumeModificationPercentage = this.minVolumeToKeepPercentageWhenMuting;
            }
            sampleIntToTwoBigEndianBytes(sampleBuffer, idx, (sample * volumeModificationPercentage) / 100);
        }
    }

    private int calculateFadeOutPercentage(int value, int max) {
        return (((this.minVolumeToKeepPercentageWhenMuting - 100) * ((value * 1000) / max)) / 1000) + 100;
    }

    private int calculateFadeInPercentage(int value, int max) {
        return this.minVolumeToKeepPercentageWhenMuting + ((((100 - this.minVolumeToKeepPercentageWhenMuting) * (value * 1000)) / max) / 1000);
    }

    private static int twoByteSampleToInt(byte mostSignificantByte, byte leastSignificantByte) {
        return (leastSignificantByte & 255) | (mostSignificantByte << 8);
    }

    private static void sampleIntToTwoBigEndianBytes(byte[] byteArray, int startIndex, int sample) {
        if (sample >= 32767) {
            byteArray[startIndex] = -1;
            byteArray[startIndex + 1] = Byte.MAX_VALUE;
        } else if (sample <= -32768) {
            byteArray[startIndex] = 0;
            byteArray[startIndex + 1] = Byte.MIN_VALUE;
        } else {
            byteArray[startIndex] = (byte) (sample & 255);
            byteArray[startIndex + 1] = (byte) (sample >> 8);
        }
    }

    private void output(ByteBuffer data) {
        replaceOutputBuffer(data.remaining()).put(data).flip();
    }

    private int durationUsToFrames(long durationUs) {
        return (int) ((this.inputAudioFormat.sampleRate * durationUs) / 1000000);
    }

    private int findNoisePosition(ByteBuffer buffer) {
        for (int i = buffer.position() + 1; i < buffer.limit(); i += 2) {
            if (isNoise(buffer.get(i), buffer.get(i - 1))) {
                return this.bytesPerFrame * (i / this.bytesPerFrame);
            }
        }
        int i2 = buffer.limit();
        return i2;
    }

    private int findNoiseLimit(ByteBuffer buffer) {
        for (int i = buffer.limit() - 1; i >= buffer.position(); i -= 2) {
            if (isNoise(buffer.get(i), buffer.get(i - 1))) {
                return (this.bytesPerFrame * (i / this.bytesPerFrame)) + this.bytesPerFrame;
            }
        }
        int i2 = buffer.position();
        return i2;
    }

    private boolean isNoise(byte mostSignificantByte, byte leastSignificantByte) {
        return Math.abs(twoByteSampleToInt(mostSignificantByte, leastSignificantByte)) > this.silenceThresholdLevel;
    }
}
