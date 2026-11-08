package com.streamsync.app.transcode;

import android.media.AudioFormat;
import android.media.AudioTrack;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.extractor.DtsUtil;
import com.streamsync.app.settings.BufferCeiling;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Ac3TranscodingAudioSink.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0012H\u0096\u0001J\t\u0010\u0019\u001a\u00020\u0012H\u0096\u0001J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0097\u0001J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0096\u0001J\u0011\u0010 \u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0014H\u0096\u0001J\t\u0010!\u001a\u00020\"H\u0096\u0001J\t\u0010#\u001a\u00020\u0010H\u0096\u0001J \u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0005H\u0016J\t\u0010)\u001a\u00020\u0012H\u0096\u0001J\t\u0010*\u001a\u00020\u0010H\u0096\u0001J\b\u0010+\u001a\u00020\u0010H\u0016J\b\u0010,\u001a\u00020\u0012H\u0016J\b\u0010-\u001a\u00020\u0012H\u0016J\t\u0010.\u001a\u00020\u0012H\u0096\u0001J\b\u0010/\u001a\u00020\u0012H\u0002J\b\u00100\u001a\u00020\u0012H\u0016J\u0011\u00101\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001cH\u0096\u0001J\u0011\u00102\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0005H\u0096\u0001J\u0011\u00103\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u000204H\u0096\u0001J\u0011\u00105\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u000206H\u0096\u0001J\u0011\u00107\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\"H\u0096\u0001J\u0011\u00108\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0010H\u0096\u0001J\u0011\u00109\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020:H\u0096\u0001J\u0011\u0010;\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0014H\u0096\u0001R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/streamsync/app/transcode/Ac3TranscodingAudioSink;", "Landroidx/media3/exoplayer/audio/AudioSink;", "delegate", "bitRateForChannels", "Lkotlin/Function1;", "", "(Landroidx/media3/exoplayer/audio/AudioSink;Lkotlin/jvm/functions/Function1;)V", "ac3Track", "Landroid/media/AudioTrack;", "channelCount", "encoder", "Lcom/streamsync/app/transcode/Ac3Encoder;", "pending", "", "pendingLen", "transcodeActive", "", "configure", "", "inputFormat", "Landroidx/media3/common/Format;", "specifiedBufferSize", "outputChannels", "", "disableTunneling", "enableTunnelingV21", "flush", "getAudioAttributes", "Landroidx/media3/common/AudioAttributes;", "getCurrentPositionUs", "", "p0", "getFormatSupport", "getPlaybackParameters", "Landroidx/media3/common/PlaybackParameters;", "getSkipSilenceEnabled", "handleBuffer", "buffer", "Ljava/nio/ByteBuffer;", "presentationTimeUs", "encodedAccessUnitCount", "handleDiscontinuity", "hasPendingData", "isEnded", "pause", "play", "playToEndOfStream", "releaseTranscodeState", "reset", "setAudioAttributes", "setAudioSessionId", "setAuxEffectInfo", "Landroidx/media3/common/AuxEffectInfo;", "setListener", "Landroidx/media3/exoplayer/audio/AudioSink$Listener;", "setPlaybackParameters", "setSkipSilenceEnabled", "setVolume", "", "supportsFormat", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes18.dex */
public final class Ac3TranscodingAudioSink implements AudioSink {
    public static final int $stable = 8;
    private AudioTrack ac3Track;
    private final Function1<Integer, Integer> bitRateForChannels;
    private int channelCount;
    private final AudioSink delegate;
    private Ac3Encoder encoder;
    private short[] pending;
    private int pendingLen;
    private boolean transcodeActive;

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void disableTunneling() {
        this.delegate.disableTunneling();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void enableTunnelingV21() {
        this.delegate.enableTunnelingV21();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioAttributes getAudioAttributes() {
        return this.delegate.getAudioAttributes();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getCurrentPositionUs(boolean p0) {
        return this.delegate.getCurrentPositionUs(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public int getFormatSupport(Format p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return this.delegate.getFormatSupport(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.delegate.getPlaybackParameters();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return this.delegate.getSkipSilenceEnabled();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void handleDiscontinuity() {
        this.delegate.handleDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean hasPendingData() {
        return this.delegate.hasPendingData();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void playToEndOfStream() {
        this.delegate.playToEndOfStream();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioAttributes(AudioAttributes p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.delegate.setAudioAttributes(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioSessionId(int p0) {
        this.delegate.setAudioSessionId(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.delegate.setAuxEffectInfo(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setListener(AudioSink.Listener p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.delegate.setListener(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.delegate.setPlaybackParameters(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setSkipSilenceEnabled(boolean p0) {
        this.delegate.setSkipSilenceEnabled(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVolume(float p0) {
        this.delegate.setVolume(p0);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean supportsFormat(Format p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return this.delegate.supportsFormat(p0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Ac3TranscodingAudioSink(AudioSink delegate, Function1<? super Integer, Integer> bitRateForChannels) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(bitRateForChannels, "bitRateForChannels");
        this.delegate = delegate;
        this.bitRateForChannels = bitRateForChannels;
        this.pending = new short[0];
    }

    public /* synthetic */ Ac3TranscodingAudioSink(AudioSink audioSink, AnonymousClass1 anonymousClass1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(audioSink, (i & 2) != 0 ? new Function1<Integer, Integer>() { // from class: com.streamsync.app.transcode.Ac3TranscodingAudioSink.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Integer invoke(int channels) {
                return Integer.valueOf(channels > 2 ? 448000 : DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND);
            }
        } : anonymousClass1);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void configure(Format inputFormat, int specifiedBufferSize, int[] outputChannels) {
        int chMask;
        int it;
        Intrinsics.checkNotNullParameter(inputFormat, "inputFormat");
        boolean isPcm = Intrinsics.areEqual(inputFormat.sampleMimeType, MimeTypes.AUDIO_RAW);
        int channels = inputFormat.channelCount;
        if (isPcm && (channels == 2 || channels == 6)) {
            releaseTranscodeState();
            this.channelCount = channels;
            this.encoder = new Ac3Encoder(inputFormat.sampleRate, channels, this.bitRateForChannels.invoke(Integer.valueOf(channels)).intValue());
            Ac3Encoder ac3Encoder = this.encoder;
            Intrinsics.checkNotNull(ac3Encoder);
            this.pending = new short[ac3Encoder.getFrameSize() * channels];
            this.pendingLen = 0;
            if (channels == 6) {
                chMask = 252;
            } else {
                chMask = 12;
            }
            AudioFormat platformFormat = new AudioFormat.Builder().setEncoding(5).setSampleRate(inputFormat.sampleRate).setChannelMask(chMask).build();
            int minBufferSize = AudioTrack.getMinBufferSize(inputFormat.sampleRate, chMask, 5);
            Integer valueOf = Integer.valueOf(specifiedBufferSize);
            int it2 = valueOf.intValue();
            if (it2 <= 0) {
                it = 0;
            } else {
                it = 1;
            }
            if (it == 0) {
                valueOf = null;
            }
            int minBuf = RangesKt.coerceAtLeast(minBufferSize, valueOf != null ? valueOf.intValue() : 0);
            this.ac3Track = new AudioTrack.Builder().setAudioFormat(platformFormat).setBufferSizeInBytes(minBuf > 0 ? minBuf : 65536).setTransferMode(1).build();
            this.transcodeActive = true;
            return;
        }
        this.transcodeActive = false;
        this.delegate.configure(inputFormat, specifiedBufferSize, outputChannels);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean handleBuffer(ByteBuffer buffer, long presentationTimeUs, int encodedAccessUnitCount) {
        AudioTrack track;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (!this.transcodeActive) {
            return this.delegate.handleBuffer(buffer, presentationTimeUs, encodedAccessUnitCount);
        }
        Ac3Encoder enc = this.encoder;
        if (enc == null || (track = this.ac3Track) == null) {
            return true;
        }
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        ShortBuffer shortBuf = buffer.asShortBuffer();
        Intrinsics.checkNotNullExpressionValue(shortBuf, "asShortBuffer(...)");
        int remaining = shortBuf.remaining();
        while (remaining > 0) {
            int space = this.pending.length - this.pendingLen;
            int toCopy = Math.min(space, remaining);
            shortBuf.get(this.pending, this.pendingLen, toCopy);
            this.pendingLen += toCopy;
            remaining -= toCopy;
            if (this.pendingLen == this.pending.length) {
                byte[] ac3Frame = enc.encode(this.pending);
                if (ac3Frame != null) {
                    track.write(ac3Frame, 0, ac3Frame.length, 0);
                    if (track.getPlayState() != 3) {
                        track.play();
                    }
                }
                this.pendingLen = 0;
            }
        }
        buffer.position(buffer.limit());
        return true;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void play() {
        if (!this.transcodeActive) {
            this.delegate.play();
            return;
        }
        AudioTrack audioTrack = this.ac3Track;
        if (audioTrack != null) {
            audioTrack.play();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void pause() {
        if (!this.transcodeActive) {
            this.delegate.pause();
            return;
        }
        AudioTrack audioTrack = this.ac3Track;
        if (audioTrack != null) {
            audioTrack.pause();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void flush() {
        if (this.transcodeActive) {
            this.pendingLen = 0;
            AudioTrack audioTrack = this.ac3Track;
            if (audioTrack != null) {
                audioTrack.flush();
                return;
            }
            return;
        }
        this.delegate.flush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void reset() {
        releaseTranscodeState();
        this.delegate.reset();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean isEnded() {
        return this.transcodeActive ? this.pendingLen == 0 : this.delegate.isEnded();
    }

    private final void releaseTranscodeState() {
        Ac3Encoder ac3Encoder = this.encoder;
        if (ac3Encoder != null) {
            ac3Encoder.close();
        }
        this.encoder = null;
        AudioTrack it = this.ac3Track;
        if (it != null) {
            it.stop();
            it.release();
        }
        this.ac3Track = null;
        this.transcodeActive = false;
        this.pendingLen = 0;
    }
}
