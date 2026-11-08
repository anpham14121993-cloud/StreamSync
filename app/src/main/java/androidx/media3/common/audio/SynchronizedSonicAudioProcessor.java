package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import java.nio.ByteBuffer;

/* loaded from: classes24.dex */
class SynchronizedSonicAudioProcessor implements AudioProcessor {
    private final Object lock;
    private final SonicAudioProcessor sonicAudioProcessor = new SonicAudioProcessor();

    public SynchronizedSonicAudioProcessor(Object lock) {
        this.lock = lock;
    }

    public final void setSpeed(float speed) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setSpeed(speed);
        }
    }

    public final void setPitch(float pitch) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setPitch(pitch);
        }
    }

    public final void setOutputSampleRateHz(int sampleRateHz) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.setOutputSampleRateHz(sampleRateHz);
        }
    }

    public final long getMediaDuration(long playoutDuration) {
        long mediaDuration;
        synchronized (this.lock) {
            mediaDuration = this.sonicAudioProcessor.getMediaDuration(playoutDuration);
        }
        return mediaDuration;
    }

    public final long getPlayoutDuration(long mediaDuration) {
        long playoutDuration;
        synchronized (this.lock) {
            playoutDuration = this.sonicAudioProcessor.getPlayoutDuration(mediaDuration);
        }
        return playoutDuration;
    }

    public final long getProcessedInputBytes() {
        long processedInputBytes;
        synchronized (this.lock) {
            processedInputBytes = this.sonicAudioProcessor.getProcessedInputBytes();
        }
        return processedInputBytes;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public long getDurationAfterProcessorApplied(long durationUs) {
        return getPlayoutDuration(durationUs);
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final AudioProcessor.AudioFormat configure(AudioProcessor.AudioFormat inputAudioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        AudioProcessor.AudioFormat configure;
        synchronized (this.lock) {
            configure = this.sonicAudioProcessor.configure(inputAudioFormat);
        }
        return configure;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isActive() {
        boolean isActive;
        synchronized (this.lock) {
            isActive = this.sonicAudioProcessor.isActive();
        }
        return isActive;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueInput(ByteBuffer inputBuffer) {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueInput(inputBuffer);
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void queueEndOfStream() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.queueEndOfStream();
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final ByteBuffer getOutput() {
        ByteBuffer output;
        synchronized (this.lock) {
            output = this.sonicAudioProcessor.getOutput();
        }
        return output;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final boolean isEnded() {
        boolean isEnded;
        synchronized (this.lock) {
            isEnded = this.sonicAudioProcessor.isEnded();
        }
        return isEnded;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void flush() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.flush();
        }
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public final void reset() {
        synchronized (this.lock) {
            this.sonicAudioProcessor.reset();
        }
    }
}
