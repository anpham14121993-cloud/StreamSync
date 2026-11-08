package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

/* loaded from: classes24.dex */
final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    private final int bitrate;

    public ConstantBitrateSeeker(long inputLength, long firstFramePosition, MpegAudioUtil.Header mpegAudioHeader, boolean allowSeeksIfLengthUnknown) {
        this(inputLength, firstFramePosition, mpegAudioHeader.bitrate, mpegAudioHeader.frameSize, allowSeeksIfLengthUnknown);
    }

    public ConstantBitrateSeeker(long inputLength, long firstFramePosition, int bitrate, int frameSize, boolean allowSeeksIfLengthUnknown) {
        super(inputLength, firstFramePosition, bitrate, frameSize, allowSeeksIfLengthUnknown);
        this.bitrate = bitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long position) {
        return getTimeUsAtPosition(position);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return -1L;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
    }
}
