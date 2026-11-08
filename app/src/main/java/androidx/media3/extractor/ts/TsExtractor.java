package androidx.media3.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.C;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes24.dex */
public final class TsExtractor implements Extractor {
    private static final long AC3_FORMAT_IDENTIFIER = 1094921523;
    private static final long AC4_FORMAT_IDENTIFIER = 1094921524;
    private static final int BUFFER_SIZE = 9400;
    public static final int DEFAULT_TIMESTAMP_SEARCH_BYTES = 112800;
    private static final long E_AC3_FORMAT_IDENTIFIER = 1161904947;

    @Deprecated
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: androidx.media3.extractor.ts.TsExtractor$$ExternalSyntheticLambda1
        @Override // androidx.media3.extractor.ExtractorsFactory
        public final Extractor[] createExtractors() {
            return TsExtractor.lambda$static$1();
        }
    };
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 1;
    private static final long HEVC_FORMAT_IDENTIFIER = 1212503619;
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int SNIFF_TS_PACKET_COUNT = 5;
    public static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_AC4 = 172;
    public static final int TS_STREAM_TYPE_AIT = 257;
    public static final int TS_STREAM_TYPE_DC2_H262 = 128;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DTS_HD = 136;
    public static final int TS_STREAM_TYPE_DTS_UHD = 139;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H263 = 16;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MHAS = 45;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    public static final int TS_SYNC_BYTE = 71;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private final int extractorFlags;
    private boolean hasOutputSeekMap;
    private TsPayloadReader id3Reader;
    private final int mode;
    private ExtractorOutput output;
    private final TsPayloadReader.Factory payloadReaderFactory;
    private int pcrPid;
    private boolean pendingSeekToStart;
    private int remainingPmts;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final List<TimestampAdjuster> timestampAdjusters;
    private final int timestampSearchBytes;
    private final SparseBooleanArray trackIds;
    private final SparseBooleanArray trackPids;
    private boolean tracksEnded;
    private TsBinarySearchSeeker tsBinarySearchSeeker;
    private final ParsableByteArray tsPacketBuffer;
    private final SparseArray<TsPayloadReader> tsPayloadReaders;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface Flags {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface Mode {
    }

    static /* synthetic */ int access$108(TsExtractor x0) {
        int i = x0.remainingPmts;
        x0.remainingPmts = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory subtitleParserFactory) {
        return new Extractor[]{new TsExtractor(subtitleParserFactory)};
    }

    public static ExtractorsFactory newFactory(final SubtitleParser.Factory subtitleParserFactory) {
        return new ExtractorsFactory() { // from class: androidx.media3.extractor.ts.TsExtractor$$ExternalSyntheticLambda0
            @Override // androidx.media3.extractor.ExtractorsFactory
            public final Extractor[] createExtractors() {
                return TsExtractor.lambda$newFactory$0(SubtitleParser.Factory.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new TsExtractor(1, SubtitleParser.Factory.UNSUPPORTED)};
    }

    @Deprecated
    public TsExtractor() {
        this(1, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    public TsExtractor(SubtitleParser.Factory subtitleParserFactory) {
        this(1, 0, subtitleParserFactory, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    public TsExtractor(int extractorFlags, SubtitleParser.Factory subtitleParserFactory) {
        this(1, extractorFlags, subtitleParserFactory, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(0), DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    @Deprecated
    public TsExtractor(int defaultTsPayloadReaderFlags) {
        this(1, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(defaultTsPayloadReaderFlags), DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    @Deprecated
    public TsExtractor(int mode, int defaultTsPayloadReaderFlags, int timestampSearchBytes) {
        this(mode, 1, SubtitleParser.Factory.UNSUPPORTED, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(defaultTsPayloadReaderFlags), timestampSearchBytes);
    }

    @Deprecated
    public TsExtractor(int mode, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory payloadReaderFactory) {
        this(mode, 1, SubtitleParser.Factory.UNSUPPORTED, timestampAdjuster, payloadReaderFactory, DEFAULT_TIMESTAMP_SEARCH_BYTES);
    }

    @Deprecated
    public TsExtractor(int mode, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory payloadReaderFactory, int timestampSearchBytes) {
        this(mode, 1, SubtitleParser.Factory.UNSUPPORTED, timestampAdjuster, payloadReaderFactory, timestampSearchBytes);
    }

    public TsExtractor(int mode, int extractorFlags, SubtitleParser.Factory subtitleParserFactory, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory payloadReaderFactory, int timestampSearchBytes) {
        this.payloadReaderFactory = (TsPayloadReader.Factory) Assertions.checkNotNull(payloadReaderFactory);
        this.timestampSearchBytes = timestampSearchBytes;
        this.mode = mode;
        this.extractorFlags = extractorFlags;
        this.subtitleParserFactory = subtitleParserFactory;
        if (mode == 1 || mode == 2) {
            this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
        } else {
            this.timestampAdjusters = new ArrayList();
            this.timestampAdjusters.add(timestampAdjuster);
        }
        this.tsPacketBuffer = new ParsableByteArray(new byte[BUFFER_SIZE], 0);
        this.trackIds = new SparseBooleanArray();
        this.trackPids = new SparseBooleanArray();
        this.tsPayloadReaders = new SparseArray<>();
        this.continuityCounters = new SparseIntArray();
        this.durationReader = new TsDurationReader(timestampSearchBytes);
        this.output = ExtractorOutput.PLACEHOLDER;
        this.pcrPid = -1;
        resetPayloadReaders();
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput input) throws IOException {
        byte[] buffer = this.tsPacketBuffer.getData();
        input.peekFully(buffer, 0, 940);
        for (int startPosCandidate = 0; startPosCandidate < 188; startPosCandidate++) {
            boolean isSyncBytePatternCorrect = true;
            int i = 0;
            while (true) {
                if (i >= 5) {
                    break;
                }
                if (buffer[(i * TS_PACKET_SIZE) + startPosCandidate] == 71) {
                    i++;
                } else {
                    isSyncBytePatternCorrect = false;
                    break;
                }
            }
            if (isSyncBytePatternCorrect) {
                input.skipFully(startPosCandidate);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        ExtractorOutput extractorOutput;
        if ((this.extractorFlags & 1) == 0) {
            extractorOutput = new SubtitleTranscodingExtractorOutput(output, this.subtitleParserFactory);
        } else {
            extractorOutput = output;
        }
        this.output = extractorOutput;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long position, long timeUs) {
        Assertions.checkState(this.mode != 2);
        int timestampAdjustersCount = this.timestampAdjusters.size();
        for (int i = 0; i < timestampAdjustersCount; i++) {
            TimestampAdjuster timestampAdjuster = this.timestampAdjusters.get(i);
            boolean resetTimestampAdjuster = timestampAdjuster.getTimestampOffsetUs() == C.TIME_UNSET;
            if (!resetTimestampAdjuster) {
                long adjusterFirstSampleTimestampUs = timestampAdjuster.getFirstSampleTimestampUs();
                resetTimestampAdjuster = (adjusterFirstSampleTimestampUs == C.TIME_UNSET || adjusterFirstSampleTimestampUs == 0 || adjusterFirstSampleTimestampUs == timeUs) ? false : true;
            }
            if (resetTimestampAdjuster) {
                timestampAdjuster.reset(timeUs);
            }
        }
        if (timeUs != 0 && this.tsBinarySearchSeeker != null) {
            this.tsBinarySearchSeeker.setSeekTargetUs(timeUs);
        }
        this.tsPacketBuffer.reset(0);
        this.continuityCounters.clear();
        for (int i2 = 0; i2 < this.tsPayloadReaders.size(); i2++) {
            this.tsPayloadReaders.valueAt(i2).seek();
        }
        this.bytesSinceLastSync = 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
        int i;
        long inputLength = input.getLength();
        boolean isModeHls = this.mode == 2;
        if (this.tracksEnded) {
            boolean canReadDuration = (inputLength == -1 || isModeHls) ? false : true;
            if (canReadDuration && !this.durationReader.isDurationReadFinished()) {
                return this.durationReader.readDuration(input, seekPosition, this.pcrPid);
            }
            maybeOutputSeekMap(inputLength);
            if (this.pendingSeekToStart) {
                this.pendingSeekToStart = false;
                seek(0L, 0L);
                if (input.getPosition() != 0) {
                    seekPosition.position = 0L;
                    return 1;
                }
            }
            if (this.tsBinarySearchSeeker != null && this.tsBinarySearchSeeker.isSeeking()) {
                return this.tsBinarySearchSeeker.handlePendingSeek(input, seekPosition);
            }
        }
        boolean canReadDuration2 = fillBufferWithAtLeastOnePacket(input);
        if (!canReadDuration2) {
            for (int i2 = 0; i2 < this.tsPayloadReaders.size(); i2++) {
                TsPayloadReader payloadReader = this.tsPayloadReaders.valueAt(i2);
                if (payloadReader instanceof PesReader) {
                    PesReader pesReader = (PesReader) payloadReader;
                    if (pesReader.canConsumeSynthesizedEmptyPusi(isModeHls)) {
                        pesReader.consume(new ParsableByteArray(), 1);
                    }
                }
            }
            return -1;
        }
        int endOfPacket = findEndOfFirstTsPacketInBuffer();
        int limit = this.tsPacketBuffer.limit();
        if (endOfPacket > limit) {
            return 0;
        }
        int tsPacketHeader = this.tsPacketBuffer.readInt();
        if ((8388608 & tsPacketHeader) == 0) {
            int packetHeaderFlags = 0 | ((4194304 & tsPacketHeader) != 0 ? 1 : 0);
            int pid = (2096896 & tsPacketHeader) >> 8;
            boolean adaptationFieldExists = (tsPacketHeader & 32) != 0;
            boolean payloadExists = (tsPacketHeader & 16) != 0;
            TsPayloadReader payloadReader2 = payloadExists ? this.tsPayloadReaders.get(pid) : null;
            if (payloadReader2 == null) {
                this.tsPacketBuffer.setPosition(endOfPacket);
                return 0;
            }
            if (this.mode != 2) {
                int continuityCounter = tsPacketHeader & 15;
                int previousCounter = this.continuityCounters.get(pid, continuityCounter - 1);
                this.continuityCounters.put(pid, continuityCounter);
                if (previousCounter == continuityCounter) {
                    this.tsPacketBuffer.setPosition(endOfPacket);
                    return 0;
                }
                if (continuityCounter != ((previousCounter + 1) & 15)) {
                    payloadReader2.seek();
                }
            }
            if (adaptationFieldExists) {
                int adaptationFieldLength = this.tsPacketBuffer.readUnsignedByte();
                int adaptationFieldFlags = this.tsPacketBuffer.readUnsignedByte();
                if ((adaptationFieldFlags & 64) != 0) {
                    i = 2;
                } else {
                    i = 0;
                }
                packetHeaderFlags |= i;
                this.tsPacketBuffer.skipBytes(adaptationFieldLength - 1);
            }
            boolean wereTracksEnded = this.tracksEnded;
            if (shouldConsumePacketPayload(pid)) {
                this.tsPacketBuffer.setLimit(endOfPacket);
                payloadReader2.consume(this.tsPacketBuffer, packetHeaderFlags);
                this.tsPacketBuffer.setLimit(limit);
            }
            if (this.mode != 2 && !wereTracksEnded && this.tracksEnded && inputLength != -1) {
                this.pendingSeekToStart = true;
            }
            this.tsPacketBuffer.setPosition(endOfPacket);
            return 0;
        }
        this.tsPacketBuffer.setPosition(endOfPacket);
        return 0;
    }

    private void maybeOutputSeekMap(long inputLength) {
        if (!this.hasOutputSeekMap) {
            this.hasOutputSeekMap = true;
            if (this.durationReader.getDurationUs() != C.TIME_UNSET) {
                this.tsBinarySearchSeeker = new TsBinarySearchSeeker(this.durationReader.getPcrTimestampAdjuster(), this.durationReader.getDurationUs(), inputLength, this.pcrPid, this.timestampSearchBytes);
                this.output.seekMap(this.tsBinarySearchSeeker.getSeekMap());
            } else {
                this.output.seekMap(new SeekMap.Unseekable(this.durationReader.getDurationUs()));
            }
        }
    }

    private boolean fillBufferWithAtLeastOnePacket(ExtractorInput input) throws IOException {
        byte[] data = this.tsPacketBuffer.getData();
        if (9400 - this.tsPacketBuffer.getPosition() < 188) {
            int bytesLeft = this.tsPacketBuffer.bytesLeft();
            if (bytesLeft > 0) {
                System.arraycopy(data, this.tsPacketBuffer.getPosition(), data, 0, bytesLeft);
            }
            this.tsPacketBuffer.reset(data, bytesLeft);
        }
        while (this.tsPacketBuffer.bytesLeft() < 188) {
            int limit = this.tsPacketBuffer.limit();
            int read = input.read(data, limit, 9400 - limit);
            if (read == -1) {
                return false;
            }
            this.tsPacketBuffer.setLimit(limit + read);
        }
        return true;
    }

    private int findEndOfFirstTsPacketInBuffer() throws ParserException {
        int searchStart = this.tsPacketBuffer.getPosition();
        int limit = this.tsPacketBuffer.limit();
        int syncBytePosition = TsUtil.findSyncBytePosition(this.tsPacketBuffer.getData(), searchStart, limit);
        this.tsPacketBuffer.setPosition(syncBytePosition);
        int endOfPacket = syncBytePosition + TS_PACKET_SIZE;
        if (endOfPacket > limit) {
            this.bytesSinceLastSync += syncBytePosition - searchStart;
            if (this.mode == 2 && this.bytesSinceLastSync > 376) {
                throw ParserException.createForMalformedContainer("Cannot find sync byte. Most likely not a Transport Stream.", null);
            }
        } else {
            this.bytesSinceLastSync = 0;
        }
        return endOfPacket;
    }

    private boolean shouldConsumePacketPayload(int packetPid) {
        return this.mode == 2 || this.tracksEnded || !this.trackPids.get(packetPid, false);
    }

    private void resetPayloadReaders() {
        this.trackIds.clear();
        this.tsPayloadReaders.clear();
        SparseArray<TsPayloadReader> initialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
        int initialPayloadReadersSize = initialPayloadReaders.size();
        for (int i = 0; i < initialPayloadReadersSize; i++) {
            this.tsPayloadReaders.put(initialPayloadReaders.keyAt(i), initialPayloadReaders.valueAt(i));
        }
        this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
        this.id3Reader = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public PatReader() {
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator idGenerator) {
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray sectionData) {
            int tableId = sectionData.readUnsignedByte();
            if (tableId != 0) {
                return;
            }
            int secondHeaderByte = sectionData.readUnsignedByte();
            if ((secondHeaderByte & 128) == 0) {
                return;
            }
            sectionData.skipBytes(6);
            int programCount = sectionData.bytesLeft() / 4;
            for (int i = 0; i < programCount; i++) {
                sectionData.readBytes(this.patScratch, 4);
                int programNumber = this.patScratch.readBits(16);
                this.patScratch.skipBits(3);
                if (programNumber == 0) {
                    this.patScratch.skipBits(13);
                } else {
                    int pid = this.patScratch.readBits(13);
                    if (TsExtractor.this.tsPayloadReaders.get(pid) == null) {
                        TsExtractor.this.tsPayloadReaders.put(pid, new SectionReader(new PmtReader(pid)));
                        TsExtractor.access$108(TsExtractor.this);
                    }
                }
            }
            if (TsExtractor.this.mode != 2) {
                TsExtractor.this.tsPayloadReaders.remove(0);
            }
        }
    }

    /* loaded from: classes24.dex */
    private class PmtReader implements SectionPayloadReader {
        private static final int TS_PMT_DESC_AC3 = 106;
        private static final int TS_PMT_DESC_AIT = 111;
        private static final int TS_PMT_DESC_DTS = 123;
        private static final int TS_PMT_DESC_DVBSUBS = 89;
        private static final int TS_PMT_DESC_DVB_EXT = 127;
        private static final int TS_PMT_DESC_DVB_EXT_AC4 = 21;
        private static final int TS_PMT_DESC_DVB_EXT_DTS_HD = 14;
        private static final int TS_PMT_DESC_DVB_EXT_DTS_UHD = 33;
        private static final int TS_PMT_DESC_EAC3 = 122;
        private static final int TS_PMT_DESC_ISO639_LANG = 10;
        private static final int TS_PMT_DESC_REGISTRATION = 5;
        private final int pid;
        private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
        private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();
        private final SparseIntArray trackIdToPidScratch = new SparseIntArray();

        public PmtReader(int pid) {
            this.pid = pid;
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator idGenerator) {
        }

        @Override // androidx.media3.extractor.ts.SectionPayloadReader
        public void consume(ParsableByteArray sectionData) {
            TimestampAdjuster timestampAdjuster;
            int tableId = sectionData.readUnsignedByte();
            if (tableId == 2) {
                if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                    timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
                } else {
                    timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                    TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
                }
                int secondHeaderByte = sectionData.readUnsignedByte();
                if ((secondHeaderByte & 128) == 0) {
                    return;
                }
                sectionData.skipBytes(1);
                int programNumber = sectionData.readUnsignedShort();
                int i = 3;
                sectionData.skipBytes(3);
                sectionData.readBytes(this.pmtScratch, 2);
                this.pmtScratch.skipBits(3);
                int i2 = 13;
                TsExtractor.this.pcrPid = this.pmtScratch.readBits(13);
                sectionData.readBytes(this.pmtScratch, 2);
                int i3 = 4;
                this.pmtScratch.skipBits(4);
                int programInfoLength = this.pmtScratch.readBits(12);
                sectionData.skipBytes(programInfoLength);
                int i4 = 21;
                if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                    TsPayloadReader.EsInfo id3EsInfo = new TsPayloadReader.EsInfo(21, null, 0, null, Util.EMPTY_BYTE_ARRAY);
                    TsExtractor.this.id3Reader = TsExtractor.this.payloadReaderFactory.createPayloadReader(21, id3EsInfo);
                    if (TsExtractor.this.id3Reader != null) {
                        TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(programNumber, 21, 8192));
                    }
                }
                this.trackIdToReaderScratch.clear();
                this.trackIdToPidScratch.clear();
                int remainingEntriesLength = sectionData.bytesLeft();
                while (remainingEntriesLength > 0) {
                    sectionData.readBytes(this.pmtScratch, 5);
                    int streamType = this.pmtScratch.readBits(8);
                    this.pmtScratch.skipBits(i);
                    int elementaryPid = this.pmtScratch.readBits(i2);
                    this.pmtScratch.skipBits(i3);
                    int esInfoLength = this.pmtScratch.readBits(12);
                    TsPayloadReader.EsInfo esInfo = readEsInfo(sectionData, esInfoLength);
                    if (streamType == 6 || streamType == 5) {
                        streamType = esInfo.streamType;
                    }
                    remainingEntriesLength -= esInfoLength + 5;
                    int trackId = TsExtractor.this.mode == 2 ? streamType : elementaryPid;
                    if (!TsExtractor.this.trackIds.get(trackId)) {
                        TsPayloadReader reader = (TsExtractor.this.mode == 2 && streamType == i4) ? TsExtractor.this.id3Reader : TsExtractor.this.payloadReaderFactory.createPayloadReader(streamType, esInfo);
                        if (TsExtractor.this.mode != 2 || elementaryPid < this.trackIdToPidScratch.get(trackId, 8192)) {
                            this.trackIdToPidScratch.put(trackId, elementaryPid);
                            this.trackIdToReaderScratch.put(trackId, reader);
                        }
                    }
                    i4 = 21;
                    i = 3;
                    i3 = 4;
                    i2 = 13;
                }
                int trackIdCount = this.trackIdToPidScratch.size();
                for (int i5 = 0; i5 < trackIdCount; i5++) {
                    int trackId2 = this.trackIdToPidScratch.keyAt(i5);
                    int trackPid = this.trackIdToPidScratch.valueAt(i5);
                    TsExtractor.this.trackIds.put(trackId2, true);
                    TsExtractor.this.trackPids.put(trackPid, true);
                    TsPayloadReader reader2 = this.trackIdToReaderScratch.valueAt(i5);
                    if (reader2 != null) {
                        if (reader2 != TsExtractor.this.id3Reader) {
                            reader2.init(timestampAdjuster, TsExtractor.this.output, new TsPayloadReader.TrackIdGenerator(programNumber, trackId2, 8192));
                        }
                        TsExtractor.this.tsPayloadReaders.put(trackPid, reader2);
                    }
                }
                if (TsExtractor.this.mode == 2) {
                    if (!TsExtractor.this.tracksEnded) {
                        TsExtractor.this.output.endTracks();
                        TsExtractor.this.remainingPmts = 0;
                        TsExtractor.this.tracksEnded = true;
                        return;
                    }
                    return;
                }
                int i6 = 0;
                TsExtractor.this.tsPayloadReaders.remove(this.pid);
                TsExtractor tsExtractor = TsExtractor.this;
                if (TsExtractor.this.mode != 1) {
                    i6 = TsExtractor.this.remainingPmts - 1;
                }
                tsExtractor.remainingPmts = i6;
                if (TsExtractor.this.remainingPmts == 0) {
                    TsExtractor.this.output.endTracks();
                    TsExtractor.this.tracksEnded = true;
                }
            }
        }

        private TsPayloadReader.EsInfo readEsInfo(ParsableByteArray data, int length) {
            int descriptorsStartPosition = data.getPosition();
            int descriptorsEndPosition = descriptorsStartPosition + length;
            int streamType = -1;
            String language = null;
            int audioType = 0;
            List<TsPayloadReader.DvbSubtitleInfo> dvbSubtitleInfos = null;
            while (data.getPosition() < descriptorsEndPosition) {
                int descriptorTag = data.readUnsignedByte();
                int descriptorLength = data.readUnsignedByte();
                int positionOfNextDescriptor = data.getPosition() + descriptorLength;
                if (positionOfNextDescriptor > descriptorsEndPosition) {
                    break;
                }
                if (descriptorTag == 5) {
                    long formatIdentifier = data.readUnsignedInt();
                    if (formatIdentifier == TsExtractor.AC3_FORMAT_IDENTIFIER) {
                        streamType = TsExtractor.TS_STREAM_TYPE_AC3;
                    } else if (formatIdentifier == TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                        streamType = TsExtractor.TS_STREAM_TYPE_E_AC3;
                    } else if (formatIdentifier == TsExtractor.AC4_FORMAT_IDENTIFIER) {
                        streamType = TsExtractor.TS_STREAM_TYPE_AC4;
                    } else if (formatIdentifier == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                        streamType = 36;
                    }
                } else if (descriptorTag == TS_PMT_DESC_AC3) {
                    streamType = TsExtractor.TS_STREAM_TYPE_AC3;
                } else if (descriptorTag == TS_PMT_DESC_EAC3) {
                    streamType = TsExtractor.TS_STREAM_TYPE_E_AC3;
                } else if (descriptorTag == 127) {
                    int descriptorTagExt = data.readUnsignedByte();
                    if (descriptorTagExt == 21) {
                        streamType = TsExtractor.TS_STREAM_TYPE_AC4;
                    } else if (descriptorTagExt == 14) {
                        streamType = TsExtractor.TS_STREAM_TYPE_DTS_HD;
                    } else if (descriptorTagExt == 33) {
                        streamType = TsExtractor.TS_STREAM_TYPE_DTS_UHD;
                    }
                } else if (descriptorTag == TS_PMT_DESC_DTS) {
                    streamType = TsExtractor.TS_STREAM_TYPE_DTS;
                } else if (descriptorTag == 10) {
                    language = data.readString(3).trim();
                    audioType = data.readUnsignedByte();
                } else if (descriptorTag == 89) {
                    streamType = 89;
                    dvbSubtitleInfos = new ArrayList<>();
                    while (data.getPosition() < positionOfNextDescriptor) {
                        String dvbLanguage = data.readString(3).trim();
                        int dvbSubtitlingType = data.readUnsignedByte();
                        byte[] initializationData = new byte[4];
                        data.readBytes(initializationData, 0, 4);
                        dvbSubtitleInfos.add(new TsPayloadReader.DvbSubtitleInfo(dvbLanguage, dvbSubtitlingType, initializationData));
                    }
                } else if (descriptorTag == TS_PMT_DESC_AIT) {
                    streamType = 257;
                }
                data.skipBytes(positionOfNextDescriptor - data.getPosition());
            }
            data.setPosition(descriptorsEndPosition);
            return new TsPayloadReader.EsInfo(streamType, language, audioType, dvbSubtitleInfos, Arrays.copyOfRange(data.getData(), descriptorsStartPosition, descriptorsEndPosition));
        }
    }
}
