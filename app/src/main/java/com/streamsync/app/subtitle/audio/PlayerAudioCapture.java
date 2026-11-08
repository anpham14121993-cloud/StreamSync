package com.streamsync.app.subtitle.audio;

import androidx.media3.exoplayer.audio.TeeAudioProcessor;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.LastStreamStore;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: PlayerAudioCapture.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\b\u0007\u0018\u0000 D2\u00020\u0001:\u0002CDB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\u0017J \u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\nH\u0002J \u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\nH\u0002J \u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\nH\u0016J\u0010\u00102\u001a\u00020\u00172\u0006\u0010&\u001a\u00020'H\u0016J\u0006\u00103\u001a\u00020\u0010J \u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u0002052\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020\u0017H\u0002J\b\u0010;\u001a\u0004\u0018\u00010\u0016J\u0010\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0002J\u0016\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004J\u0006\u0010A\u001a\u00020\u0017J\u0010\u0010B\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001fH\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/streamsync/app/subtitle/audio/PlayerAudioCapture;", "Landroidx/media3/exoplayer/audio/TeeAudioProcessor$AudioBufferSink;", "()V", "captureStartPositionMs", "", "getCaptureStartPositionMs", "()J", "capturedMs", "getCapturedMs", "capturedSamples", "", "inputFormat", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/streamsync/app/subtitle/audio/AudioProcessorAudioFormat;", "kotlin.jvm.PlatformType", "isPcmAvailable", "", "()Z", "isRunning", "maxAudioMs", "onChunk", "Lkotlin/Function2;", "", "", "getOnChunk", "()Lkotlin/jvm/functions/Function2;", "setOnChunk", "(Lkotlin/jvm/functions/Function2;)V", "pcm", "Lcom/streamsync/app/subtitle/audio/PlayerAudioCapture$ByteArrayOutputStreamLite;", "pending", "", "running", "Ljava/util/concurrent/atomic/AtomicBoolean;", "startPositionMs", "targetFormat", "clear", "copyLocked", "buffer", "Ljava/nio/ByteBuffer;", "format", "frames", "emitChunkLocked", "samples", "offset", "srcFrames", "flush", "sampleRateHz", "channelCount", "encoding", "handleBuffer", "hasUsableAudio", "lerp", "", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "frac", "", "resetBuffers", "snapshotPcm", "sourceFramesPerChunk", "srcRate", TtmlNode.START, "positionMs", "limitMs", "stop", "toPcmBytes", "ByteArrayOutputStreamLite", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class PlayerAudioCapture implements TeeAudioProcessor.AudioBufferSink {
    public static final int CHUNK_MS = 100;
    public static final int CHUNK_SAMPLES = 1600;
    public static final int TARGET_RATE = 16000;
    private volatile int capturedSamples;
    private volatile long maxAudioMs;
    private volatile Function2<? super byte[], ? super Long, Unit> onChunk;
    private volatile long startPositionMs;
    public static final int $stable = 8;
    private final AudioProcessorAudioFormat targetFormat = new AudioProcessorAudioFormat(16000, 1, 2);
    private final AtomicReference<AudioProcessorAudioFormat> inputFormat = new AtomicReference<>(this.targetFormat);
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final ByteArrayOutputStreamLite pcm = new ByteArrayOutputStreamLite(960000);
    private short[] pending = new short[0];

    public final Function2<byte[], Long, Unit> getOnChunk() {
        return this.onChunk;
    }

    public final void setOnChunk(Function2<? super byte[], ? super Long, Unit> function2) {
        this.onChunk = function2;
    }

    public final boolean isPcmAvailable() {
        int encoding = this.inputFormat.get().getEncoding();
        return encoding == 2 || encoding == 4;
    }

    public final long getCapturedMs() {
        return (this.capturedSamples * 1000) / 16000;
    }

    public final void start(long positionMs, long limitMs) {
        synchronized (this) {
            resetBuffers();
            this.startPositionMs = positionMs;
            this.maxAudioMs = limitMs;
            this.running.set(true);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void stop() {
        this.running.set(false);
    }

    public final boolean isRunning() {
        return this.running.get();
    }

    public final void clear() {
        synchronized (this) {
            this.running.set(false);
            resetBuffers();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final byte[] snapshotPcm() {
        byte[] byteArray;
        synchronized (this) {
            byteArray = this.capturedSamples <= 0 ? null : this.pcm.toByteArray(this.capturedSamples * 2);
        }
        return byteArray;
    }

    /* renamed from: getCaptureStartPositionMs, reason: from getter */
    public final long getStartPositionMs() {
        return this.startPositionMs;
    }

    @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
    public void flush(int sampleRateHz, int channelCount, int encoding) {
        this.inputFormat.set(new AudioProcessorAudioFormat(sampleRateHz, channelCount, encoding));
        this.pending = new short[0];
    }

    @Override // androidx.media3.exoplayer.audio.TeeAudioProcessor.AudioBufferSink
    public void handleBuffer(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        synchronized (this) {
            if (this.running.get() && isPcmAvailable()) {
                int bytes = buffer.remaining();
                if (bytes <= 0) {
                    return;
                }
                AudioProcessorAudioFormat format = this.inputFormat.get();
                int srcRate = Math.max(1, format.getSampleRate());
                int frameCount = bytes / format.getBytesPerFrame();
                if (frameCount <= 0) {
                    return;
                }
                int startPos = buffer.position();
                try {
                    if (this.maxAudioMs > 0) {
                        long j = 16000;
                        long maxSamples = (this.maxAudioMs * j) / 1000;
                        try {
                            long pendingTarget = (this.pending.length * j) / srcRate;
                            long remaining = (maxSamples - this.capturedSamples) - pendingTarget;
                            if (remaining <= 0) {
                                this.running.set(false);
                                buffer.position(startPos);
                                return;
                            }
                            long pendingTarget2 = srcRate;
                            int allowedFrames = ((int) ((pendingTarget2 * remaining) / j)) + 1;
                            if (frameCount >= allowedFrames) {
                                Intrinsics.checkNotNull(format);
                                copyLocked(buffer, format, RangesKt.coerceAtLeast(allowedFrames, 1));
                                this.running.set(false);
                                buffer.position(startPos);
                                return;
                            }
                        } catch (Throwable th) {
                            th = th;
                            buffer.position(startPos);
                            throw th;
                        }
                    }
                    Intrinsics.checkNotNull(format);
                    copyLocked(buffer, format, frameCount);
                    buffer.position(startPos);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    private final void copyLocked(ByteBuffer buffer, AudioProcessorAudioFormat format, int frames) {
        short[] merged;
        int total;
        float v;
        boolean isFloat = format.getEncoding() == 4;
        int channels = Math.max(1, format.getChannelCount());
        ByteOrder order = buffer.order();
        buffer.order(ByteOrder.nativeOrder());
        short[] mono = new short[frames];
        int frame = 0;
        int sampleIndex = 0;
        while (frame < frames) {
            float acc = 0.0f;
            for (int c = 0; c < channels; c++) {
                if (isFloat) {
                    v = buffer.getFloat();
                } else {
                    v = buffer.getShort() / 32768.0f;
                }
                acc += v;
            }
            int c2 = sampleIndex + 1;
            mono[sampleIndex] = (short) MathKt.roundToInt(RangesKt.coerceIn((acc / channels) * 32767.0f, -32768.0f, 32767.0f));
            frame++;
            sampleIndex = c2;
        }
        buffer.order(order);
        int total2 = this.pending.length + frames;
        if (this.pending.length == 0) {
            merged = mono;
            total = total2;
        } else {
            merged = new short[total2];
            ArraysKt.copyInto$default(this.pending, merged, 0, 0, 0, 14, (Object) null);
            total = total2;
            ArraysKt.copyInto$default(mono, merged, this.pending.length, 0, 0, 12, (Object) null);
        }
        int srcPerChunk = sourceFramesPerChunk(format.getSampleRate());
        int offset = 0;
        while (offset + srcPerChunk <= total) {
            emitChunkLocked(merged, offset, srcPerChunk);
            offset += srcPerChunk;
        }
        this.pending = total - offset > 0 ? ArraysKt.copyOfRange(merged, offset, total) : new short[0];
    }

    private final void emitChunkLocked(short[] samples, int offset, int srcFrames) {
        short[] down;
        if (srcFrames <= 0) {
            return;
        }
        if (srcFrames != 1600) {
            double ratio = srcFrames / CHUNK_SAMPLES;
            short[] sArr = new short[CHUNK_SAMPLES];
            int i = 0;
            for (int i2 = CHUNK_SAMPLES; i < i2; i2 = CHUNK_SAMPLES) {
                double srcPos = i * ratio;
                int i0 = RangesKt.coerceIn((int) srcPos, 0, srcFrames - 1);
                int i1 = Math.min(i0 + 1, srcFrames - 1);
                float frac = (float) (srcPos - i0);
                sArr[i] = lerp(samples[offset + i0], samples[offset + i1], frac);
                i++;
            }
            down = sArr;
        } else {
            down = ArraysKt.copyOfRange(samples, offset, offset + srcFrames);
        }
        this.pcm.write(down);
        this.capturedSamples += down.length;
        long chunkStartMs = this.startPositionMs + (((this.capturedSamples - down.length) * 1000) / 16000);
        Function2<? super byte[], ? super Long, Unit> function2 = this.onChunk;
        if (function2 != null) {
            function2.invoke(toPcmBytes(down), Long.valueOf(chunkStartMs));
        }
    }

    private final byte[] toPcmBytes(short[] samples) {
        byte[] out = new byte[samples.length * 2];
        int i = 0;
        for (short v : samples) {
            int i2 = i + 1;
            out[i] = (byte) (v & 255);
            i = i2 + 1;
            out[i2] = (byte) ((v >> 8) & 255);
        }
        return out;
    }

    private final short lerp(short a, short b, float frac) {
        float plain = (a * (1.0f - frac)) + (b * frac);
        float left = (a * 0.5f) + (plain * 0.5f);
        float right = (plain * 0.5f) + (b * 0.5f);
        return (short) MathKt.roundToInt(RangesKt.coerceIn((0.25f * left) + (0.75f * right), -32768.0f, 32767.0f));
    }

    private final int sourceFramesPerChunk(int srcRate) {
        return (int) Math.max(1L, (Math.max(1, srcRate) * 1600) / 16000);
    }

    private final void resetBuffers() {
        this.pcm.reset();
        this.pending = new short[0];
        this.capturedSamples = 0;
    }

    public final boolean hasUsableAudio() {
        return getCapturedMs() >= 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PlayerAudioCapture.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0017\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0003J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/streamsync/app/subtitle/audio/PlayerAudioCapture$ByteArrayOutputStreamLite;", "", "initialCapacity", "", "(I)V", "buffer", "", "size", "reset", "", "toByteArray", LastStreamStore.KEY_LENGTH, "write", "samples", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    public static final class ByteArrayOutputStreamLite {
        private byte[] buffer;
        private int size;

        public ByteArrayOutputStreamLite(int initialCapacity) {
            this.buffer = new byte[Math.max(1024, initialCapacity)];
        }

        public final void write(short[] samples) {
            Intrinsics.checkNotNullParameter(samples, "samples");
            int need = this.size + (samples.length * 2);
            if (need > this.buffer.length) {
                int cap = this.buffer.length;
                while (cap < need) {
                    cap *= 2;
                }
                byte[] copyOf = Arrays.copyOf(this.buffer, cap);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                this.buffer = copyOf;
            }
            int i = this.size;
            int length = samples.length;
            int i2 = 0;
            while (i2 < length) {
                short s = samples[i2];
                int i3 = i + 1;
                this.buffer[i] = (byte) (s & 255);
                this.buffer[i3] = (byte) ((s >> 8) & 255);
                i2++;
                i = i3 + 1;
            }
            this.size = need;
        }

        public final byte[] toByteArray(int length) {
            byte[] copyOf = Arrays.copyOf(this.buffer, Math.min(length, this.size));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            return copyOf;
        }

        public final void reset() {
            this.size = 0;
        }
    }
}
