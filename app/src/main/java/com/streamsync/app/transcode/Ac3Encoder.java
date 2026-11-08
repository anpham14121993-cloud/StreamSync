package com.streamsync.app.transcode;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Ac3Encoder.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0011\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\fH\u0082 J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082 J\u0011\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\fH\u0082 J!\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0082 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/transcode/Ac3Encoder;", "", "sampleRate", "", "channels", "bitRate", "(III)V", "getChannels", "()I", "frameSize", "getFrameSize", "nativeHandle", "", "close", "", "encode", "", "pcm", "", "nativeClose", "handle", "nativeEncode", "nativeGetFrameSize", "nativeOpen", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes18.dex */
public final class Ac3Encoder {

    @Deprecated
    public static final String LIBRARY_NAME = "ac3encoder";
    private final int channels;
    private final int frameSize;
    private long nativeHandle;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    private final native void nativeClose(long handle);

    private final native byte[] nativeEncode(long handle, short[] pcm);

    private final native int nativeGetFrameSize(long handle);

    private final native long nativeOpen(int sampleRate, int channels, int bitRate);

    public Ac3Encoder(int sampleRate, int channels, int bitRate) {
        this.channels = channels;
        if (!Ac3EncoderAvailability.INSTANCE.isAvailable()) {
            throw new IllegalStateException("Ban build nay KHONG co thu vien ac3encoder. Can build FFmpeg roi copy vao app/src/main/cpp/ffmpeg/ theo README trong thu muc do.".toString());
        }
        this.nativeHandle = nativeOpen(sampleRate, this.channels, bitRate);
        if (!(this.nativeHandle != 0)) {
            throw new IllegalStateException("Khong mo duoc AC3 encoder - kiem tra .so co duoc build va load dung chua (xem README trong app/src/main/cpp), hoac xem logcat tag Ac3EncoderJNI".toString());
        }
        this.frameSize = nativeGetFrameSize(this.nativeHandle);
    }

    public final int getChannels() {
        return this.channels;
    }

    /* compiled from: Ac3Encoder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/streamsync/app/transcode/Ac3Encoder$Companion;", "", "()V", "LIBRARY_NAME", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes18.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final int getFrameSize() {
        return this.frameSize;
    }

    public final byte[] encode(short[] pcm) {
        Intrinsics.checkNotNullParameter(pcm, "pcm");
        if (!(this.nativeHandle != 0)) {
            throw new IllegalStateException("Encoder da bi close()".toString());
        }
        if (!(pcm.length == this.frameSize * this.channels)) {
            throw new IllegalArgumentException(("PCM chunk sai kich thuoc: " + pcm.length + ", can " + (this.frameSize * this.channels)).toString());
        }
        return nativeEncode(this.nativeHandle, pcm);
    }

    public final void close() {
        if (this.nativeHandle != 0) {
            nativeClose(this.nativeHandle);
            this.nativeHandle = 0L;
        }
    }
}
