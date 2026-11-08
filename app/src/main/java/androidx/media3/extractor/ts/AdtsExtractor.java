package androidx.media3.extractor.ts;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes24.dex */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.ts.AdtsExtractor$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return AdtsExtractor.lambda$static$0();
        }
    };
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING_ALWAYS = 2;
    private static final int MAX_PACKET_SIZE = 2048;
    private static final int MAX_SNIFF_BYTES = 8192;
    private static final int NUM_FRAMES_FOR_AVERAGE_FRAME_SIZE = 1000;
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface Flags {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AdtsExtractor()};
    }

    public AdtsExtractor() {
        this(0);
    }

    public AdtsExtractor(int flags) {
        this.flags = (flags & 2) != 0 ? flags | 1 : flags;
        this.reader = new AdtsReader(true);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1L;
        this.scratch = new ParsableByteArray(10);
        this.scratchBits = new ParsableBitArray(this.scratch.getData());
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        int startPosition = peekId3Header(input);
        int headerPosition = startPosition;
        int totalValidFramesSize = 0;
        int validFramesCount = 0;
        do {
            input.peekFully(this.scratch.getData(), 0, 2);
            this.scratch.setPosition(0);
            int syncBytes = this.scratch.readUnsignedShort();
            if (!AdtsReader.isAdtsSyncWord(syncBytes)) {
                validFramesCount = 0;
                totalValidFramesSize = 0;
                headerPosition++;
                input.resetPeekPosition();
                input.advancePeekPosition(headerPosition);
            } else {
                validFramesCount++;
                if (validFramesCount >= 4 && totalValidFramesSize > 188) {
                    return true;
                }
                input.peekFully(this.scratch.getData(), 0, 4);
                this.scratchBits.setPosition(14);
                int frameSize = this.scratchBits.readBits(13);
                if (frameSize <= 6) {
                    validFramesCount = 0;
                    totalValidFramesSize = 0;
                    headerPosition++;
                    input.resetPeekPosition();
                    input.advancePeekPosition(headerPosition);
                } else {
                    input.advancePeekPosition(frameSize - 6);
                    totalValidFramesSize += frameSize;
                }
            }
        } while (headerPosition - startPosition < 8192);
        return false;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        this.extractorOutput = output;
        this.reader.createTracks(output, new TsPayloadReader.TrackIdGenerator(0, 1));
        output.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = timeUs;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        long inputLength = input.getLength();
        boolean canUseConstantBitrateSeeking = ((this.flags & 2) == 0 && ((this.flags & 1) == 0 || inputLength == -1)) ? false : true;
        if (canUseConstantBitrateSeeking) {
            calculateAverageFrameSize(input);
        }
        int bytesRead = input.read(this.packetBuffer.getData(), 0, 2048);
        boolean readEndOfStream = bytesRead == -1;
        maybeOutputSeekMap(inputLength, readEndOfStream);
        if (readEndOfStream) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(bytesRead);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    private int peekId3Header(ExtractorInput input) throws IOException {
        int firstFramePosition = 0;
        while (true) {
            input.peekFully(this.scratch.getData(), 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int length = this.scratch.readSynchSafeInt();
            firstFramePosition += length + 10;
            input.advancePeekPosition(length);
        }
        input.resetPeekPosition();
        input.advancePeekPosition(firstFramePosition);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = firstFramePosition;
        }
        return firstFramePosition;
    }

    @RequiresNonNull({"extractorOutput"})
    private void maybeOutputSeekMap(long inputLength, boolean readEndOfStream) {
        if (this.hasOutputSeekMap) {
            return;
        }
        boolean useConstantBitrateSeeking = (this.flags & 1) != 0 && this.averageFrameSize > 0;
        if (useConstantBitrateSeeking && this.reader.getSampleDurationUs() == C.TIME_UNSET && !readEndOfStream) {
            return;
        }
        if (!useConstantBitrateSeeking || this.reader.getSampleDurationUs() == C.TIME_UNSET) {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        } else {
            this.extractorOutput.seekMap(getConstantBitrateSeekMap(inputLength, (this.flags & 2) != 0));
        }
        this.hasOutputSeekMap = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
    
        r9.hasCalculatedAverageFrameSize = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0079, code lost:
    
        throw androidx.media3.common.ParserException.createForMalformedContainer("Malformed ADTS stream", null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void calculateAverageFrameSize(androidx.media3.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.hasCalculatedAverageFrameSize
            if (r0 == 0) goto L5
            return
        L5:
            r0 = -1
            r9.averageFrameSize = r0
            r10.resetPeekPosition()
            long r1 = r10.getPosition()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L18
            r9.peekId3Header(r10)
        L18:
            r1 = 0
            r2 = 0
        L1b:
            r4 = 1
            androidx.media3.common.util.ParsableByteArray r5 = r9.scratch     // Catch: java.io.EOFException -> L7b
            byte[] r5 = r5.getData()     // Catch: java.io.EOFException -> L7b
            r6 = 0
            r7 = 2
            boolean r5 = r10.peekFully(r5, r6, r7, r4)     // Catch: java.io.EOFException -> L7b
            if (r5 == 0) goto L7a
            androidx.media3.common.util.ParsableByteArray r5 = r9.scratch     // Catch: java.io.EOFException -> L7b
            r5.setPosition(r6)     // Catch: java.io.EOFException -> L7b
            androidx.media3.common.util.ParsableByteArray r5 = r9.scratch     // Catch: java.io.EOFException -> L7b
            int r5 = r5.readUnsignedShort()     // Catch: java.io.EOFException -> L7b
            boolean r7 = androidx.media3.extractor.ts.AdtsReader.isAdtsSyncWord(r5)     // Catch: java.io.EOFException -> L7b
            if (r7 != 0) goto L3d
            r1 = 0
            goto L7a
        L3d:
            androidx.media3.common.util.ParsableByteArray r7 = r9.scratch     // Catch: java.io.EOFException -> L7b
            byte[] r7 = r7.getData()     // Catch: java.io.EOFException -> L7b
            r8 = 4
            boolean r6 = r10.peekFully(r7, r6, r8, r4)     // Catch: java.io.EOFException -> L7b
            if (r6 != 0) goto L4b
            goto L7a
        L4b:
            androidx.media3.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch: java.io.EOFException -> L7b
            r7 = 14
            r6.setPosition(r7)     // Catch: java.io.EOFException -> L7b
            androidx.media3.common.util.ParsableBitArray r6 = r9.scratchBits     // Catch: java.io.EOFException -> L7b
            r7 = 13
            int r6 = r6.readBits(r7)     // Catch: java.io.EOFException -> L7b
            r7 = 6
            if (r6 <= r7) goto L70
            long r7 = (long) r6     // Catch: java.io.EOFException -> L7b
            long r2 = r2 + r7
            int r1 = r1 + 1
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r1 != r7) goto L66
            goto L7a
        L66:
            int r7 = r6 + (-6)
            boolean r7 = r10.advancePeekPosition(r7, r4)     // Catch: java.io.EOFException -> L7b
            if (r7 != 0) goto L6f
            goto L7a
        L6f:
            goto L1b
        L70:
            r9.hasCalculatedAverageFrameSize = r4     // Catch: java.io.EOFException -> L7b
            java.lang.String r7 = "Malformed ADTS stream"
            r8 = 0
            androidx.media3.common.ParserException r7 = androidx.media3.common.ParserException.createForMalformedContainer(r7, r8)     // Catch: java.io.EOFException -> L7b
            throw r7     // Catch: java.io.EOFException -> L7b
        L7a:
            goto L7c
        L7b:
            r5 = move-exception
        L7c:
            r10.resetPeekPosition()
            if (r1 <= 0) goto L88
            long r5 = (long) r1
            long r5 = r2 / r5
            int r0 = (int) r5
            r9.averageFrameSize = r0
            goto L8a
        L88:
            r9.averageFrameSize = r0
        L8a:
            r9.hasCalculatedAverageFrameSize = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.ts.AdtsExtractor.calculateAverageFrameSize(androidx.media3.extractor.ExtractorInput):void");
    }

    private SeekMap getConstantBitrateSeekMap(long inputLength, boolean allowSeeksIfLengthUnknown) {
        int bitrate = getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs());
        return new ConstantBitrateSeekMap(inputLength, this.firstFramePosition, bitrate, this.averageFrameSize, allowSeeksIfLengthUnknown);
    }

    private static int getBitrateFromFrameSize(int frameSize, long durationUsPerFrame) {
        return (int) (((frameSize * 8) * 1000000) / durationUsPerFrame);
    }
}
