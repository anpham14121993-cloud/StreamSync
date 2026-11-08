package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.DolbyVisionConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.mp4.Atom;
import androidx.media3.extractor.mp4.FixedSampleSizeRechunker;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes24.dex */
public final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    public static List<TrackSampleTable> parseTraks(Atom.ContainerAtom moov, GaplessInfoHolder gaplessInfoHolder, long duration, DrmInitData drmInitData, boolean ignoreEditLists, boolean isQuickTime, Function<Track, Track> modifyTrackFunction) throws ParserException {
        Track track;
        List<TrackSampleTable> trackSampleTables = new ArrayList<>();
        for (int i = 0; i < moov.containerChildren.size(); i++) {
            Atom.ContainerAtom atom = moov.containerChildren.get(i);
            if (atom.type == 1953653099 && (track = modifyTrackFunction.apply(parseTrak(atom, (Atom.LeafAtom) Assertions.checkNotNull(moov.getLeafAtomOfType(Atom.TYPE_mvhd)), duration, drmInitData, ignoreEditLists, isQuickTime))) != null) {
                Atom.ContainerAtom stblAtom = (Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(atom.getContainerAtomOfType(Atom.TYPE_mdia))).getContainerAtomOfType(Atom.TYPE_minf))).getContainerAtomOfType(Atom.TYPE_stbl));
                TrackSampleTable trackSampleTable = parseStbl(track, stblAtom, gaplessInfoHolder);
                trackSampleTables.add(trackSampleTable);
            }
        }
        return trackSampleTables;
    }

    public static Metadata parseUdta(Atom.LeafAtom udtaAtom) {
        ParsableByteArray udtaData = udtaAtom.data;
        udtaData.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (udtaData.bytesLeft() >= 8) {
            int atomPosition = udtaData.getPosition();
            int atomSize = udtaData.readInt();
            int atomType = udtaData.readInt();
            if (atomType == 1835365473) {
                udtaData.setPosition(atomPosition);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(udtaData, atomPosition + atomSize));
            } else if (atomType == 1936553057) {
                udtaData.setPosition(atomPosition);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(udtaData, atomPosition + atomSize));
            } else if (atomType == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(udtaData));
            }
            udtaData.setPosition(atomPosition + atomSize);
        }
        return metadata;
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray mvhd) {
        long creationTimestampSeconds;
        long modificationTimestampSeconds;
        mvhd.setPosition(8);
        int fullAtom = mvhd.readInt();
        int version = Atom.parseFullAtomVersion(fullAtom);
        if (version == 0) {
            creationTimestampSeconds = mvhd.readUnsignedInt();
            modificationTimestampSeconds = mvhd.readUnsignedInt();
        } else {
            creationTimestampSeconds = mvhd.readLong();
            modificationTimestampSeconds = mvhd.readLong();
        }
        long timescale = mvhd.readUnsignedInt();
        return new Mp4TimestampData(creationTimestampSeconds, modificationTimestampSeconds, timescale);
    }

    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom meta) {
        Atom.LeafAtom hdlrAtom = meta.getLeafAtomOfType(Atom.TYPE_hdlr);
        Atom.LeafAtom keysAtom = meta.getLeafAtomOfType(Atom.TYPE_keys);
        Atom.LeafAtom ilstAtom = meta.getLeafAtomOfType(Atom.TYPE_ilst);
        if (hdlrAtom == null || keysAtom == null || ilstAtom == null || parseHdlr(hdlrAtom.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray keys = keysAtom.data;
        keys.setPosition(12);
        int entryCount = keys.readInt();
        String[] keyNames = new String[entryCount];
        for (int i = 0; i < entryCount; i++) {
            int entrySize = keys.readInt();
            keys.skipBytes(4);
            int keySize = entrySize - 8;
            keyNames[i] = keys.readString(keySize);
        }
        ParsableByteArray ilst = ilstAtom.data;
        ilst.setPosition(8);
        ArrayList<Metadata.Entry> entries = new ArrayList<>();
        while (ilst.bytesLeft() > 8) {
            int atomPosition = ilst.getPosition();
            int atomSize = ilst.readInt();
            int keyIndex = ilst.readInt() - 1;
            if (keyIndex >= 0 && keyIndex < keyNames.length) {
                String key = keyNames[keyIndex];
                Metadata.Entry entry = MetadataUtil.parseMdtaMetadataEntryFromIlst(ilst, atomPosition + atomSize, key);
                if (entry != null) {
                    entries.add(entry);
                }
            } else {
                Log.w(TAG, "Skipped metadata with unknown key index: " + keyIndex);
            }
            ilst.setPosition(atomPosition + atomSize);
        }
        if (entries.isEmpty()) {
            return null;
        }
        return new Metadata(entries);
    }

    public static void maybeSkipRemainingMetaAtomHeaderBytes(ParsableByteArray meta) {
        int endPosition = meta.getPosition();
        meta.skipBytes(4);
        if (meta.readInt() != 1751411826) {
            endPosition += 4;
        }
        meta.setPosition(endPosition);
    }

    private static Track parseTrak(Atom.ContainerAtom trak, Atom.LeafAtom mvhd, long duration, DrmInitData drmInitData, boolean ignoreEditLists, boolean isQuickTime) throws ParserException {
        long duration2;
        long durationUs;
        long[] editListDurations;
        long[] editListMediaTimes;
        Atom.ContainerAtom edtsAtom;
        Pair<long[], long[]> edtsData;
        Atom.ContainerAtom mdia = (Atom.ContainerAtom) Assertions.checkNotNull(trak.getContainerAtomOfType(Atom.TYPE_mdia));
        int trackType = getTrackTypeForHdlr(parseHdlr(((Atom.LeafAtom) Assertions.checkNotNull(mdia.getLeafAtomOfType(Atom.TYPE_hdlr))).data));
        if (trackType == -1) {
            return null;
        }
        TkhdData tkhdData = parseTkhd(((Atom.LeafAtom) Assertions.checkNotNull(trak.getLeafAtomOfType(Atom.TYPE_tkhd))).data);
        if (duration != C.TIME_UNSET) {
            duration2 = duration;
        } else {
            duration2 = tkhdData.duration;
        }
        long movieTimescale = parseMvhd(mvhd.data).timescale;
        if (duration2 == C.TIME_UNSET) {
            durationUs = -9223372036854775807L;
        } else {
            durationUs = Util.scaleLargeTimestamp(duration2, 1000000L, movieTimescale);
        }
        Atom.ContainerAtom stbl = (Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(mdia.getContainerAtomOfType(Atom.TYPE_minf))).getContainerAtomOfType(Atom.TYPE_stbl));
        Pair<Long, String> mdhdData = parseMdhd(((Atom.LeafAtom) Assertions.checkNotNull(mdia.getLeafAtomOfType(Atom.TYPE_mdhd))).data);
        Atom.LeafAtom stsd = stbl.getLeafAtomOfType(Atom.TYPE_stsd);
        if (stsd == null) {
            throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", null);
        }
        StsdData stsdData = parseStsd(stsd.data, tkhdData.id, tkhdData.rotationDegrees, (String) mdhdData.second, drmInitData, isQuickTime);
        if (!ignoreEditLists && (edtsAtom = trak.getContainerAtomOfType(Atom.TYPE_edts)) != null && (edtsData = parseEdts(edtsAtom)) != null) {
            long[] editListDurations2 = (long[]) edtsData.first;
            long[] editListMediaTimes2 = (long[]) edtsData.second;
            editListDurations = editListDurations2;
            editListMediaTimes = editListMediaTimes2;
        } else {
            editListDurations = null;
            editListMediaTimes = null;
        }
        if (stsdData.format == null) {
            return null;
        }
        return new Track(tkhdData.id, trackType, ((Long) mdhdData.first).longValue(), movieTimescale, durationUs, stsdData.format, stsdData.requiredSampleTransformation, stsdData.trackEncryptionBoxes, stsdData.nalUnitLengthFieldLength, editListDurations, editListMediaTimes);
    }

    private static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom stblAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean chunkOffsetsAreLongs;
        Atom.LeafAtom chunkOffsetsAtom;
        ParsableByteArray stss;
        Atom.LeafAtom chunkOffsetsAtom2;
        ParsableByteArray chunkOffsets;
        long[] offsets;
        int sampleCount;
        int remainingSynchronizationSamples;
        ChunkIterator chunkIterator;
        long[] offsets2;
        int remainingSamplesInChunk;
        long[] timestamps;
        long duration;
        Track track2;
        int remainingSynchronizationSamples2;
        int remainingSamplesAtTimestampOffset;
        int maximumSize;
        long[] offsets3;
        int[] sizes;
        int timestampDeltaInTimeUnits;
        int[] flags;
        int sampleCount2;
        int timestampOffset;
        int[] endIndices;
        int sampleCount3;
        int[] sizes2;
        long[] offsets4;
        int[] sizes3;
        int sampleCount4;
        ChunkIterator chunkIterator2;
        int i;
        Atom.LeafAtom stszAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (stszAtom != null) {
            sampleSizeBox = new StszSampleSizeBox(stszAtom, track.format);
        } else {
            Atom.LeafAtom stz2Atom = stblAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (stz2Atom == null) {
                throw ParserException.createForMalformedContainer("Track has no sample table size information", null);
            }
            sampleSizeBox = new Stz2SampleSizeBox(stz2Atom);
        }
        int sampleCount5 = sampleSizeBox.getSampleCount();
        if (sampleCount5 == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], 0L);
        }
        Atom.LeafAtom chunkOffsetsAtom3 = stblAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (chunkOffsetsAtom3 != null) {
            chunkOffsetsAreLongs = false;
            chunkOffsetsAtom = chunkOffsetsAtom3;
        } else {
            chunkOffsetsAreLongs = true;
            chunkOffsetsAtom = (Atom.LeafAtom) Assertions.checkNotNull(stblAtom.getLeafAtomOfType(Atom.TYPE_co64));
        }
        ParsableByteArray chunkOffsets2 = chunkOffsetsAtom.data;
        ParsableByteArray stsc = ((Atom.LeafAtom) Assertions.checkNotNull(stblAtom.getLeafAtomOfType(Atom.TYPE_stsc))).data;
        ParsableByteArray stts = ((Atom.LeafAtom) Assertions.checkNotNull(stblAtom.getLeafAtomOfType(Atom.TYPE_stts))).data;
        Atom.LeafAtom stssAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray stss2 = stssAtom != null ? stssAtom.data : null;
        Atom.LeafAtom cttsAtom = stblAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray ctts = cttsAtom != null ? cttsAtom.data : null;
        ChunkIterator chunkIterator3 = new ChunkIterator(stsc, chunkOffsets2, chunkOffsetsAreLongs);
        stts.setPosition(12);
        int remainingTimestampDeltaChanges = stts.readUnsignedIntToInt() - 1;
        int remainingSamplesAtTimestampDelta = stts.readUnsignedIntToInt();
        int timestampDeltaInTimeUnits2 = stts.readUnsignedIntToInt();
        int remainingTimestampOffsetChanges = 0;
        if (ctts != null) {
            ctts.setPosition(12);
            remainingTimestampOffsetChanges = ctts.readUnsignedIntToInt();
        }
        int nextSynchronizationSampleIndex = -1;
        int remainingSynchronizationSamples3 = 0;
        if (stss2 != null) {
            stss2.setPosition(12);
            remainingSynchronizationSamples3 = stss2.readUnsignedIntToInt();
            if (remainingSynchronizationSamples3 > 0) {
                nextSynchronizationSampleIndex = stss2.readUnsignedIntToInt() - 1;
                stss = stss2;
            } else {
                stss = null;
                nextSynchronizationSampleIndex = -1;
            }
        } else {
            stss = stss2;
        }
        int fixedSampleSize = sampleSizeBox.getFixedSampleSize();
        int nextSynchronizationSampleIndex2 = nextSynchronizationSampleIndex;
        String sampleMimeType = track.format.sampleMimeType;
        boolean rechunkFixedSizeSamples = fixedSampleSize != -1 && (MimeTypes.AUDIO_RAW.equals(sampleMimeType) || MimeTypes.AUDIO_MLAW.equals(sampleMimeType) || MimeTypes.AUDIO_ALAW.equals(sampleMimeType)) && remainingTimestampDeltaChanges == 0 && remainingTimestampOffsetChanges == 0 && remainingSynchronizationSamples3 == 0;
        long timestampTimeUnits = 0;
        if (rechunkFixedSizeSamples) {
            long[] chunkOffsetsBytes = new long[chunkIterator3.length];
            int maximumSize2 = chunkIterator3.length;
            int[] chunkSampleCounts = new int[maximumSize2];
            while (chunkIterator3.moveNext()) {
                chunkOffsetsBytes[chunkIterator3.index] = chunkIterator3.offset;
                chunkSampleCounts[chunkIterator3.index] = chunkIterator3.numSamples;
                chunkOffsetsAtom = chunkOffsetsAtom;
            }
            chunkOffsetsAtom2 = chunkOffsetsAtom;
            FixedSampleSizeRechunker.Results rechunkedResults = FixedSampleSizeRechunker.rechunk(fixedSampleSize, chunkOffsetsBytes, chunkSampleCounts, timestampDeltaInTimeUnits2);
            long[] offsets5 = rechunkedResults.offsets;
            int[] sizes4 = rechunkedResults.sizes;
            maximumSize = rechunkedResults.maximumSize;
            long[] timestamps2 = rechunkedResults.timestamps;
            int[] flags2 = rechunkedResults.flags;
            duration = rechunkedResults.duration;
            track2 = track;
            sampleCount2 = sampleCount5;
            chunkIterator = chunkIterator3;
            flags = flags2;
            timestamps = timestamps2;
            chunkOffsets = chunkOffsets2;
            sizes = sizes4;
            offsets3 = offsets5;
            timestampDeltaInTimeUnits = timestampDeltaInTimeUnits2;
        } else {
            chunkOffsetsAtom2 = chunkOffsetsAtom;
            long[] offsets6 = new long[sampleCount5];
            int[] sizes5 = new int[sampleCount5];
            long[] timestamps3 = new long[sampleCount5];
            int[] flags3 = new int[sampleCount5];
            int remainingSamplesInChunk2 = 0;
            int i2 = 0;
            chunkOffsets = chunkOffsets2;
            int remainingTimestampDeltaChanges2 = remainingTimestampDeltaChanges;
            int remainingSamplesAtTimestampDelta2 = remainingSamplesAtTimestampDelta;
            int remainingSynchronizationSamples4 = remainingSynchronizationSamples3;
            int remainingTimestampOffsetChanges2 = remainingTimestampOffsetChanges;
            int remainingSamplesAtTimestampOffset2 = 0;
            long offset = 0;
            int nextSynchronizationSampleIndex3 = nextSynchronizationSampleIndex2;
            int maximumSize3 = 0;
            int timestampOffset2 = 0;
            while (true) {
                ParsableByteArray stts2 = stts;
                if (i2 >= sampleCount5) {
                    offsets = offsets6;
                    sampleCount = sampleCount5;
                    remainingSynchronizationSamples = remainingSynchronizationSamples4;
                    chunkIterator = chunkIterator3;
                    offsets2 = timestamps3;
                    remainingSamplesInChunk = remainingSamplesInChunk2;
                    break;
                }
                boolean chunkDataComplete = true;
                while (remainingSamplesInChunk2 == 0) {
                    boolean moveNext = chunkIterator3.moveNext();
                    chunkDataComplete = moveNext;
                    if (!moveNext) {
                        break;
                    }
                    int sampleCount6 = sampleCount5;
                    long offset2 = chunkIterator3.offset;
                    offset = offset2;
                    remainingSamplesInChunk2 = chunkIterator3.numSamples;
                    sampleCount5 = sampleCount6;
                    remainingSynchronizationSamples4 = remainingSynchronizationSamples4;
                }
                int sampleCount7 = sampleCount5;
                remainingSynchronizationSamples = remainingSynchronizationSamples4;
                if (!chunkDataComplete) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    int sampleCount8 = i2;
                    long[] offsets7 = Arrays.copyOf(offsets6, sampleCount8);
                    sizes5 = Arrays.copyOf(sizes5, sampleCount8);
                    long[] timestamps4 = Arrays.copyOf(timestamps3, sampleCount8);
                    flags3 = Arrays.copyOf(flags3, sampleCount8);
                    offsets = offsets7;
                    offsets2 = timestamps4;
                    sampleCount = sampleCount8;
                    chunkIterator = chunkIterator3;
                    remainingSamplesInChunk = remainingSamplesInChunk2;
                    break;
                }
                if (ctts == null) {
                    timestampOffset = timestampOffset2;
                } else {
                    int remainingSamplesAtTimestampOffset3 = remainingSamplesAtTimestampOffset2;
                    while (remainingSamplesAtTimestampOffset3 == 0 && remainingTimestampOffsetChanges2 > 0) {
                        remainingSamplesAtTimestampOffset3 = ctts.readUnsignedIntToInt();
                        timestampOffset2 = ctts.readInt();
                        remainingTimestampOffsetChanges2--;
                    }
                    remainingSamplesAtTimestampOffset2 = remainingSamplesAtTimestampOffset3 - 1;
                    timestampOffset = timestampOffset2;
                }
                offsets6[i2] = offset;
                sizes5[i2] = sampleSizeBox.readNextSampleSize();
                if (sizes5[i2] > maximumSize3) {
                    maximumSize3 = sizes5[i2];
                }
                timestamps3[i2] = timestampTimeUnits + timestampOffset;
                flags3[i2] = stss == null ? 1 : 0;
                if (i2 != nextSynchronizationSampleIndex3) {
                    remainingSynchronizationSamples4 = remainingSynchronizationSamples;
                } else {
                    flags3[i2] = 1;
                    remainingSynchronizationSamples4 = remainingSynchronizationSamples - 1;
                    if (remainingSynchronizationSamples4 > 0) {
                        nextSynchronizationSampleIndex3 = ((ParsableByteArray) Assertions.checkNotNull(stss)).readUnsignedIntToInt() - 1;
                    }
                }
                ChunkIterator chunkIterator4 = chunkIterator3;
                long[] offsets8 = offsets6;
                timestampTimeUnits += timestampDeltaInTimeUnits2;
                remainingSamplesAtTimestampDelta2--;
                if (remainingSamplesAtTimestampDelta2 == 0 && remainingTimestampDeltaChanges2 > 0) {
                    int remainingSamplesAtTimestampDelta3 = stts2.readUnsignedIntToInt();
                    timestampDeltaInTimeUnits2 = stts2.readInt();
                    remainingTimestampDeltaChanges2--;
                    remainingSamplesAtTimestampDelta2 = remainingSamplesAtTimestampDelta3;
                }
                int remainingSamplesAtTimestampDelta4 = sizes5[i2];
                offset += remainingSamplesAtTimestampDelta4;
                remainingSamplesInChunk2--;
                i2++;
                timestampOffset2 = timestampOffset;
                chunkIterator3 = chunkIterator4;
                stts = stts2;
                sampleCount5 = sampleCount7;
                offsets6 = offsets8;
            }
            timestamps = offsets2;
            int timestampDeltaInTimeUnits3 = timestampDeltaInTimeUnits2;
            long duration2 = timestampTimeUnits + timestampOffset2;
            boolean isCttsValid = true;
            if (ctts != null) {
                while (true) {
                    if (remainingTimestampOffsetChanges2 <= 0) {
                        break;
                    }
                    if (ctts.readUnsignedIntToInt() != 0) {
                        isCttsValid = false;
                        break;
                    }
                    ctts.readInt();
                    remainingTimestampOffsetChanges2--;
                }
            }
            if (remainingSynchronizationSamples == 0 && remainingSamplesAtTimestampDelta2 == 0 && remainingSamplesInChunk == 0 && remainingTimestampDeltaChanges2 == 0 && remainingSamplesAtTimestampOffset2 == 0 && isCttsValid) {
                duration = duration2;
                remainingSamplesAtTimestampOffset = remainingSamplesAtTimestampOffset2;
                remainingSynchronizationSamples2 = remainingSynchronizationSamples;
                track2 = track;
            } else {
                duration = duration2;
                track2 = track;
                remainingSynchronizationSamples2 = remainingSynchronizationSamples;
                remainingSamplesAtTimestampOffset = remainingSamplesAtTimestampOffset2;
                Log.w(TAG, "Inconsistent stbl box for track " + track2.id + ": remainingSynchronizationSamples " + remainingSynchronizationSamples2 + ", remainingSamplesAtTimestampDelta " + remainingSamplesAtTimestampDelta2 + ", remainingSamplesInChunk " + remainingSamplesInChunk + ", remainingTimestampDeltaChanges " + remainingTimestampDeltaChanges2 + ", remainingSamplesAtTimestampOffset " + remainingSamplesAtTimestampOffset + (!isCttsValid ? ", ctts invalid" : ""));
            }
            maximumSize = maximumSize3;
            offsets3 = offsets;
            sizes = sizes5;
            timestampDeltaInTimeUnits = timestampDeltaInTimeUnits3;
            flags = flags3;
            sampleCount2 = sampleCount;
        }
        long durationUs = Util.scaleLargeTimestamp(duration, 1000000L, track2.timescale);
        if (track2.editListDurations == null) {
            Util.scaleLargeTimestampsInPlace(timestamps, 1000000L, track2.timescale);
            return new TrackSampleTable(track, offsets3, sizes, maximumSize, timestamps, flags, durationUs);
        }
        Track track3 = track2;
        int sampleCount9 = sampleCount2;
        if (track3.editListDurations.length == 1 && track3.type == 1 && timestamps.length >= 2) {
            long editStartTime = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[0];
            long editEndTime = editStartTime + Util.scaleLargeTimestamp(track3.editListDurations[0], track3.timescale, track3.movieTimescale);
            if (canApplyEditWithGaplessInfo(timestamps, duration, editStartTime, editEndTime)) {
                long paddingTimeUnits = duration - editEndTime;
                long encoderDelay = Util.scaleLargeTimestamp(editStartTime - timestamps[0], track3.format.sampleRate, track3.timescale);
                long encoderPadding = Util.scaleLargeTimestamp(paddingTimeUnits, track3.format.sampleRate, track3.timescale);
                if (encoderDelay != 0 || encoderPadding != 0) {
                    if (encoderDelay <= 2147483647L && encoderPadding <= 2147483647L) {
                        gaplessInfoHolder.encoderDelay = (int) encoderDelay;
                        gaplessInfoHolder.encoderPadding = (int) encoderPadding;
                        Util.scaleLargeTimestampsInPlace(timestamps, 1000000L, track3.timescale);
                        long editedDurationUs = Util.scaleLargeTimestamp(track3.editListDurations[0], 1000000L, track3.movieTimescale);
                        return new TrackSampleTable(track, offsets3, sizes, maximumSize, timestamps, flags, editedDurationUs);
                    }
                }
            }
        }
        if (track3.editListDurations.length == 1 && track3.editListDurations[0] == 0) {
            long editStartTime2 = ((long[]) Assertions.checkNotNull(track3.editListMediaTimes))[0];
            for (int i3 = 0; i3 < timestamps.length; i3++) {
                timestamps[i3] = Util.scaleLargeTimestamp(timestamps[i3] - editStartTime2, 1000000L, track3.timescale);
            }
            long durationUs2 = Util.scaleLargeTimestamp(duration - editStartTime2, 1000000L, track3.timescale);
            return new TrackSampleTable(track, offsets3, sizes, maximumSize, timestamps, flags, durationUs2);
        }
        boolean omitZeroDurationClippedSample = track3.type == 1;
        int[] startIndices = new int[track3.editListDurations.length];
        int[] endIndices2 = new int[track3.editListDurations.length];
        long[] editListMediaTimes = (long[]) Assertions.checkNotNull(track3.editListMediaTimes);
        int editedSampleCount = 0;
        int editedSampleCount2 = 0;
        int nextSampleIndex = 0;
        int nextSampleIndex2 = 0;
        while (nextSampleIndex2 < track3.editListDurations.length) {
            long editMediaTime = editListMediaTimes[nextSampleIndex2];
            if (editMediaTime != -1) {
                chunkIterator2 = chunkIterator;
                sizes3 = sizes;
                sampleCount4 = sampleCount9;
                offsets4 = offsets3;
                long editDuration = Util.scaleLargeTimestamp(track3.editListDurations[nextSampleIndex2], track3.timescale, track3.movieTimescale);
                startIndices[nextSampleIndex2] = Util.binarySearchFloor(timestamps, editMediaTime, true, true);
                endIndices2[nextSampleIndex2] = Util.binarySearchCeil(timestamps, editMediaTime + editDuration, omitZeroDurationClippedSample, false);
                while (true) {
                    if (startIndices[nextSampleIndex2] >= endIndices2[nextSampleIndex2]) {
                        i = 1;
                        break;
                    }
                    i = 1;
                    if ((flags[startIndices[nextSampleIndex2]] & 1) != 0) {
                        break;
                    }
                    startIndices[nextSampleIndex2] = startIndices[nextSampleIndex2] + 1;
                }
                editedSampleCount += endIndices2[nextSampleIndex2] - startIndices[nextSampleIndex2];
                editedSampleCount2 |= nextSampleIndex != startIndices[nextSampleIndex2] ? i : 0;
                nextSampleIndex = endIndices2[nextSampleIndex2];
            } else {
                offsets4 = offsets3;
                sizes3 = sizes;
                sampleCount4 = sampleCount9;
                chunkIterator2 = chunkIterator;
            }
            nextSampleIndex2++;
            chunkIterator = chunkIterator2;
            sizes = sizes3;
            sampleCount9 = sampleCount4;
            offsets3 = offsets4;
        }
        long[] offsets9 = offsets3;
        int[] sizes6 = sizes;
        int sampleCount10 = sampleCount9;
        int i4 = editedSampleCount2 | (editedSampleCount == sampleCount10 ? 0 : 1);
        long[] editedOffsets = i4 != 0 ? new long[editedSampleCount] : offsets9;
        int[] editedSizes = i4 != 0 ? new int[editedSampleCount] : sizes6;
        int editedMaximumSize = i4 != 0 ? 0 : maximumSize;
        int[] editedFlags = i4 != 0 ? new int[editedSampleCount] : flags;
        long[] editedTimestamps = new long[editedSampleCount];
        int sampleIndex = 0;
        long pts = 0;
        int editedMaximumSize2 = editedMaximumSize;
        int editedMaximumSize3 = 0;
        while (true) {
            int nextSampleIndex3 = nextSampleIndex;
            if (editedMaximumSize3 < track3.editListDurations.length) {
                long editMediaTime2 = track3.editListMediaTimes[editedMaximumSize3];
                int startIndex = startIndices[editedMaximumSize3];
                int editedSampleCount3 = editedSampleCount;
                int endIndex = endIndices2[editedMaximumSize3];
                if (i4 == 0) {
                    endIndices = endIndices2;
                    sampleCount3 = sampleCount10;
                    sizes2 = sizes6;
                } else {
                    endIndices = endIndices2;
                    int count = endIndex - startIndex;
                    sampleCount3 = sampleCount10;
                    System.arraycopy(offsets9, startIndex, editedOffsets, sampleIndex, count);
                    sizes2 = sizes6;
                    System.arraycopy(sizes2, startIndex, editedSizes, sampleIndex, count);
                    System.arraycopy(flags, startIndex, editedFlags, sampleIndex, count);
                }
                int j = startIndex;
                int editedMaximumSize4 = editedMaximumSize2;
                while (j < endIndex) {
                    int[] editedFlags2 = editedFlags;
                    int endIndex2 = endIndex;
                    long ptsUs = Util.scaleLargeTimestamp(pts, 1000000L, track3.movieTimescale);
                    boolean omitZeroDurationClippedSample2 = omitZeroDurationClippedSample;
                    int[] startIndices2 = startIndices;
                    long timeInSegmentUs = Util.scaleLargeTimestamp(timestamps[j] - editMediaTime2, 1000000L, track3.timescale);
                    int[] flags4 = flags;
                    if (canTrimSamplesWithTimestampChange(track3.type)) {
                        timeInSegmentUs = Math.max(0L, timeInSegmentUs);
                    }
                    editedTimestamps[sampleIndex] = ptsUs + timeInSegmentUs;
                    if (i4 != 0 && editedSizes[sampleIndex] > editedMaximumSize4) {
                        editedMaximumSize4 = sizes2[j];
                    }
                    sampleIndex++;
                    j++;
                    track3 = track;
                    editedFlags = editedFlags2;
                    endIndex = endIndex2;
                    flags = flags4;
                    omitZeroDurationClippedSample = omitZeroDurationClippedSample2;
                    startIndices = startIndices2;
                }
                pts += track.editListDurations[editedMaximumSize3];
                editedMaximumSize3++;
                editedMaximumSize2 = editedMaximumSize4;
                track3 = track;
                sizes6 = sizes2;
                nextSampleIndex = nextSampleIndex3;
                editedSampleCount = editedSampleCount3;
                endIndices2 = endIndices;
                sampleCount10 = sampleCount3;
                editedFlags = editedFlags;
                flags = flags;
                omitZeroDurationClippedSample = omitZeroDurationClippedSample;
                startIndices = startIndices;
            } else {
                int[] editedFlags3 = editedFlags;
                long editedDurationUs2 = Util.scaleLargeTimestamp(pts, 1000000L, track3.movieTimescale);
                return new TrackSampleTable(track, editedOffsets, editedSizes, editedMaximumSize2, editedTimestamps, editedFlags3, editedDurationUs2);
            }
        }
    }

    private static boolean canTrimSamplesWithTimestampChange(int trackType) {
        return trackType != 1;
    }

    private static Metadata parseUdtaMeta(ParsableByteArray meta, int limit) {
        meta.skipBytes(8);
        maybeSkipRemainingMetaAtomHeaderBytes(meta);
        while (meta.getPosition() < limit) {
            int atomPosition = meta.getPosition();
            int atomSize = meta.readInt();
            int atomType = meta.readInt();
            if (atomType == 1768715124) {
                meta.setPosition(atomPosition);
                return parseIlst(meta, atomPosition + atomSize);
            }
            meta.setPosition(atomPosition + atomSize);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray ilst, int limit) {
        ilst.skipBytes(8);
        ArrayList<Metadata.Entry> entries = new ArrayList<>();
        while (ilst.getPosition() < limit) {
            Metadata.Entry entry = MetadataUtil.parseIlstElement(ilst);
            if (entry != null) {
                entries.add(entry);
            }
        }
        if (entries.isEmpty()) {
            return null;
        }
        return new Metadata(entries);
    }

    private static Metadata parseXyz(ParsableByteArray xyzBox) {
        int length = xyzBox.readShort();
        xyzBox.skipBytes(2);
        String location = xyzBox.readString(length);
        int plusSignIndex = location.lastIndexOf(43);
        int minusSignIndex = location.lastIndexOf(45);
        int latitudeEndIndex = Math.max(plusSignIndex, minusSignIndex);
        try {
            float latitude = Float.parseFloat(location.substring(0, latitudeEndIndex));
            float longitude = Float.parseFloat(location.substring(latitudeEndIndex, location.length() - 1));
            return new Metadata(new Mp4LocationData(latitude, longitude));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return null;
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray tkhd) {
        int i;
        long duration;
        int rotationDegrees;
        int durationByteCount = 8;
        tkhd.setPosition(8);
        int fullAtom = tkhd.readInt();
        int version = Atom.parseFullAtomVersion(fullAtom);
        if (version == 0) {
            i = 8;
        } else {
            i = 16;
        }
        tkhd.skipBytes(i);
        int trackId = tkhd.readInt();
        tkhd.skipBytes(4);
        boolean durationUnknown = true;
        int durationPosition = tkhd.getPosition();
        if (version == 0) {
            durationByteCount = 4;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= durationByteCount) {
                break;
            }
            if (tkhd.getData()[durationPosition + i2] == -1) {
                i2++;
            } else {
                durationUnknown = false;
                break;
            }
        }
        if (durationUnknown) {
            tkhd.skipBytes(durationByteCount);
            duration = C.TIME_UNSET;
        } else {
            duration = version == 0 ? tkhd.readUnsignedInt() : tkhd.readUnsignedLongToLong();
            if (duration == 0) {
                duration = C.TIME_UNSET;
            }
        }
        tkhd.skipBytes(16);
        int a00 = tkhd.readInt();
        int a01 = tkhd.readInt();
        tkhd.skipBytes(4);
        int a10 = tkhd.readInt();
        int a11 = tkhd.readInt();
        if (a00 == 0 && a01 == 65536 && a10 == (-65536) && a11 == 0) {
            rotationDegrees = 90;
        } else if (a00 == 0 && a01 == (-65536) && a10 == 65536 && a11 == 0) {
            rotationDegrees = 270;
        } else {
            int rotationDegrees2 = -65536;
            if (a00 == rotationDegrees2 && a01 == 0 && a10 == 0 && a11 == (-65536)) {
                rotationDegrees = 180;
            } else {
                rotationDegrees = 0;
            }
        }
        return new TkhdData(trackId, duration, rotationDegrees);
    }

    private static int parseHdlr(ParsableByteArray hdlr) {
        hdlr.setPosition(16);
        return hdlr.readInt();
    }

    private static int getTrackTypeForHdlr(int hdlr) {
        if (hdlr == TYPE_soun) {
            return 1;
        }
        if (hdlr == TYPE_vide) {
            return 2;
        }
        if (hdlr == TYPE_text || hdlr == TYPE_sbtl || hdlr == TYPE_subt || hdlr == TYPE_clcp) {
            return 3;
        }
        if (hdlr == 1835365473) {
            return 5;
        }
        return -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray mdhd) {
        mdhd.setPosition(8);
        int fullAtom = mdhd.readInt();
        int version = Atom.parseFullAtomVersion(fullAtom);
        mdhd.skipBytes(version == 0 ? 8 : 16);
        long timescale = mdhd.readUnsignedInt();
        mdhd.skipBytes(version == 0 ? 4 : 8);
        int languageCode = mdhd.readUnsignedShort();
        String language = "" + ((char) (((languageCode >> 10) & 31) + 96)) + ((char) (((languageCode >> 5) & 31) + 96)) + ((char) ((languageCode & 31) + 96));
        return Pair.create(Long.valueOf(timescale), language);
    }

    private static StsdData parseStsd(ParsableByteArray stsd, int trackId, int rotationDegrees, String language, DrmInitData drmInitData, boolean isQuickTime) throws ParserException {
        int childAtomType;
        stsd.setPosition(12);
        int numberOfEntries = stsd.readInt();
        StsdData out = new StsdData(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            int childStartPosition = stsd.getPosition();
            int childAtomSize = stsd.readInt();
            ExtractorUtil.checkContainerInput(childAtomSize > 0, "childAtomSize must be positive");
            int childAtomType2 = stsd.readInt();
            if (childAtomType2 == 1635148593 || childAtomType2 == 1635148595 || childAtomType2 == 1701733238 || childAtomType2 == 1831958048 || childAtomType2 == 1836070006 || childAtomType2 == 1752589105 || childAtomType2 == 1751479857 || childAtomType2 == 1932670515 || childAtomType2 == 1211250227 || childAtomType2 == 1987063864 || childAtomType2 == 1987063865 || childAtomType2 == 1635135537 || childAtomType2 == 1685479798 || childAtomType2 == 1685479729 || childAtomType2 == 1685481573) {
                childAtomType = childAtomType2;
            } else if (childAtomType2 == 1685481521) {
                childAtomType = childAtomType2;
            } else {
                if (childAtomType2 == 1836069985 || childAtomType2 == 1701733217 || childAtomType2 == 1633889587 || childAtomType2 == 1700998451 || childAtomType2 == 1633889588 || childAtomType2 == 1835823201 || childAtomType2 == 1685353315 || childAtomType2 == 1685353317 || childAtomType2 == 1685353320 || childAtomType2 == 1685353324 || childAtomType2 == 1685353336 || childAtomType2 == 1935764850 || childAtomType2 == 1935767394 || childAtomType2 == 1819304813 || childAtomType2 == 1936684916 || childAtomType2 == 1953984371 || childAtomType2 == 778924082 || childAtomType2 == 778924083 || childAtomType2 == 1835557169 || childAtomType2 == 1835560241 || childAtomType2 == 1634492771 || childAtomType2 == 1634492791 || childAtomType2 == 1970037111 || childAtomType2 == 1332770163 || childAtomType2 == 1716281667) {
                    parseAudioSampleEntry(stsd, childAtomType2, childStartPosition, childAtomSize, trackId, language, isQuickTime, drmInitData, out, i);
                } else if (childAtomType2 == 1414810956 || childAtomType2 == 1954034535 || childAtomType2 == 2004251764 || childAtomType2 == 1937010800 || childAtomType2 == 1664495672) {
                    parseTextSampleEntry(stsd, childAtomType2, childStartPosition, childAtomSize, trackId, language, out);
                } else if (childAtomType2 == 1835365492) {
                    parseMetaDataSampleEntry(stsd, childAtomType2, childStartPosition, trackId, out);
                } else if (childAtomType2 == 1667329389) {
                    out.format = new Format.Builder().setId(trackId).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
                }
                stsd.setPosition(childStartPosition + childAtomSize);
            }
            parseVideoSampleEntry(stsd, childAtomType, childStartPosition, childAtomSize, trackId, rotationDegrees, drmInitData, out, i);
            stsd.setPosition(childStartPosition + childAtomSize);
        }
        return out;
    }

    private static void parseTextSampleEntry(ParsableByteArray parent, int atomType, int position, int atomSize, int trackId, String language, StsdData out) {
        String mimeType;
        parent.setPosition(position + 8 + 8);
        ImmutableList<byte[]> initializationData = null;
        long subsampleOffsetUs = Long.MAX_VALUE;
        if (atomType == 1414810956) {
            mimeType = MimeTypes.APPLICATION_TTML;
        } else if (atomType == 1954034535) {
            mimeType = MimeTypes.APPLICATION_TX3G;
            int sampleDescriptionLength = (atomSize - 8) - 8;
            byte[] sampleDescriptionData = new byte[sampleDescriptionLength];
            parent.readBytes(sampleDescriptionData, 0, sampleDescriptionLength);
            initializationData = ImmutableList.of(sampleDescriptionData);
        } else if (atomType == 2004251764) {
            mimeType = MimeTypes.APPLICATION_MP4VTT;
        } else if (atomType == 1937010800) {
            mimeType = MimeTypes.APPLICATION_TTML;
            subsampleOffsetUs = 0;
        } else if (atomType == 1664495672) {
            mimeType = MimeTypes.APPLICATION_MP4CEA608;
            out.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        out.format = new Format.Builder().setId(trackId).setSampleMimeType(mimeType).setLanguage(language).setSubsampleOffsetUs(subsampleOffsetUs).setInitializationData(initializationData).build();
    }

    private static void parseVideoSampleEntry(ParsableByteArray parent, int atomType, int position, int size, int trackId, int rotationDegrees, DrmInitData drmInitData, StsdData out, int entryIndex) throws ParserException {
        int stereoMode;
        int width;
        int height;
        float pixelWidthHeightRatio;
        byte[] projectionData;
        int height2;
        String codecs;
        int width2;
        int height3;
        boolean pixelWidthHeightRatioFromPasp;
        int childPosition;
        int height4;
        int atomType2;
        String codecs2;
        byte[] projectionData2;
        int colorTransfer;
        int stereoMode2;
        List<byte[]> initializationData;
        ByteBuffer hdrStaticInfo;
        ByteBuffer hdrStaticInfo2;
        float pixelWidthHeightRatio2;
        float pixelWidthHeightRatio3;
        DrmInitData copyWithSchemeType;
        int childAtomType = position;
        int i = size;
        DrmInitData drmInitData2 = drmInitData;
        StsdData stsdData = out;
        parent.setPosition(childAtomType + 8 + 8);
        parent.skipBytes(16);
        int width3 = parent.readUnsignedShort();
        int height5 = parent.readUnsignedShort();
        boolean pixelWidthHeightRatioFromPasp2 = false;
        float pixelWidthHeightRatio4 = 1.0f;
        parent.skipBytes(50);
        int childPosition2 = parent.getPosition();
        int atomType3 = atomType;
        if (atomType3 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parent, childAtomType, i);
            if (sampleEntryEncryptionData != null) {
                atomType3 = ((Integer) sampleEntryEncryptionData.first).intValue();
                if (drmInitData2 == null) {
                    copyWithSchemeType = null;
                } else {
                    copyWithSchemeType = drmInitData2.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                }
                drmInitData2 = copyWithSchemeType;
                stsdData.trackEncryptionBoxes[entryIndex] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parent.setPosition(childPosition2);
        }
        String mimeType = null;
        if (atomType3 == 1831958048) {
            mimeType = MimeTypes.VIDEO_MPEG;
        } else if (atomType3 == 1211250227) {
            mimeType = MimeTypes.VIDEO_H263;
        }
        String codecs3 = null;
        EsdsData esdsData = null;
        ByteBuffer hdrStaticInfo3 = null;
        int colorRange = -1;
        List<byte[]> initializationData2 = null;
        int colorTransfer2 = -1;
        DrmInitData drmInitData3 = drmInitData2;
        int colorSpace = -1;
        int maxNumReorderSamples = -1;
        int bitdepthLuma = 8;
        int bitdepthLuma2 = -1;
        int bitdepthChroma = 8;
        byte[] projectionData3 = null;
        while (true) {
            stereoMode = bitdepthLuma2;
            if (childPosition2 - childAtomType >= i) {
                width = width3;
                height = height5;
                pixelWidthHeightRatio = pixelWidthHeightRatio4;
                projectionData = projectionData3;
                height2 = colorTransfer2;
                codecs = codecs3;
            } else {
                parent.setPosition(childPosition2);
                int childStartPosition = parent.getPosition();
                projectionData = projectionData3;
                int childAtomSize = parent.readInt();
                if (childAtomSize == 0) {
                    pixelWidthHeightRatio = pixelWidthHeightRatio4;
                    if (parent.getPosition() - childAtomType == i) {
                        width = width3;
                        height = height5;
                        height2 = colorTransfer2;
                        codecs = codecs3;
                    }
                } else {
                    pixelWidthHeightRatio = pixelWidthHeightRatio4;
                }
                ExtractorUtil.checkContainerInput(childAtomSize > 0, "childAtomSize must be positive");
                int childAtomType2 = parent.readInt();
                if (childAtomType2 == 1635148611) {
                    ExtractorUtil.checkContainerInput(mimeType == null, null);
                    parent.setPosition(childStartPosition + 8);
                    AvcConfig avcConfig = AvcConfig.parse(parent);
                    List<byte[]> initializationData3 = avcConfig.initializationData;
                    stsdData.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                    if (pixelWidthHeightRatioFromPasp2) {
                        pixelWidthHeightRatio3 = pixelWidthHeightRatio;
                    } else {
                        pixelWidthHeightRatio3 = avcConfig.pixelWidthHeightRatio;
                    }
                    codecs3 = avcConfig.codecs;
                    float pixelWidthHeightRatio5 = pixelWidthHeightRatio3;
                    int maxNumReorderSamples2 = avcConfig.maxNumReorderFrames;
                    colorSpace = avcConfig.colorSpace;
                    maxNumReorderSamples = maxNumReorderSamples2;
                    int maxNumReorderSamples3 = avcConfig.colorRange;
                    colorTransfer2 = avcConfig.colorTransfer;
                    int colorRange2 = avcConfig.bitdepthLuma;
                    int bitdepthChroma2 = avcConfig.bitdepthChroma;
                    bitdepthLuma = colorRange2;
                    width2 = width3;
                    height3 = height5;
                    bitdepthChroma = bitdepthChroma2;
                    childPosition = childPosition2;
                    atomType2 = atomType3;
                    colorRange = maxNumReorderSamples3;
                    projectionData2 = projectionData;
                    pixelWidthHeightRatio4 = pixelWidthHeightRatio5;
                    initializationData2 = initializationData3;
                    mimeType = MimeTypes.VIDEO_H264;
                } else if (childAtomType2 == 1752589123) {
                    ExtractorUtil.checkContainerInput(mimeType == null, null);
                    parent.setPosition(childStartPosition + 8);
                    HevcConfig hevcConfig = HevcConfig.parse(parent);
                    List<byte[]> initializationData4 = hevcConfig.initializationData;
                    stsdData.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                    if (pixelWidthHeightRatioFromPasp2) {
                        pixelWidthHeightRatio2 = pixelWidthHeightRatio;
                    } else {
                        pixelWidthHeightRatio2 = hevcConfig.pixelWidthHeightRatio;
                    }
                    float pixelWidthHeightRatio6 = pixelWidthHeightRatio2;
                    int maxNumReorderSamples4 = hevcConfig.maxNumReorderPics;
                    codecs3 = hevcConfig.codecs;
                    colorSpace = hevcConfig.colorSpace;
                    maxNumReorderSamples = maxNumReorderSamples4;
                    int maxNumReorderSamples5 = hevcConfig.colorRange;
                    colorTransfer2 = hevcConfig.colorTransfer;
                    int colorRange3 = hevcConfig.bitdepthLuma;
                    int bitdepthChroma3 = hevcConfig.bitdepthChroma;
                    bitdepthLuma = colorRange3;
                    width2 = width3;
                    height3 = height5;
                    bitdepthChroma = bitdepthChroma3;
                    childPosition = childPosition2;
                    atomType2 = atomType3;
                    colorRange = maxNumReorderSamples5;
                    projectionData2 = projectionData;
                    pixelWidthHeightRatio4 = pixelWidthHeightRatio6;
                    initializationData2 = initializationData4;
                    mimeType = MimeTypes.VIDEO_H265;
                } else {
                    if (childAtomType2 == 1685480259) {
                        width2 = width3;
                        height3 = height5;
                        pixelWidthHeightRatioFromPasp = pixelWidthHeightRatioFromPasp2;
                        childPosition = childPosition2;
                        height4 = colorTransfer2;
                        atomType2 = atomType3;
                        codecs2 = codecs3;
                    } else if (childAtomType2 == 1685485123) {
                        width2 = width3;
                        height3 = height5;
                        pixelWidthHeightRatioFromPasp = pixelWidthHeightRatioFromPasp2;
                        childPosition = childPosition2;
                        height4 = colorTransfer2;
                        atomType2 = atomType3;
                        codecs2 = codecs3;
                    } else if (childAtomType2 == 1987076931) {
                        ExtractorUtil.checkContainerInput(mimeType == null, null);
                        String mimeType2 = atomType3 == 1987063864 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
                        parent.setPosition(childStartPosition + 12);
                        parent.skipBytes(2);
                        int byte3 = parent.readUnsignedByte();
                        int bitdepthLuma3 = byte3 >> 4;
                        bitdepthChroma = bitdepthLuma3;
                        boolean fullRangeFlag = (byte3 & 1) != 0;
                        int colorPrimaries = parent.readUnsignedByte();
                        int transferCharacteristics = parent.readUnsignedByte();
                        colorSpace = ColorInfo.isoColorPrimariesToColorSpace(colorPrimaries);
                        colorRange = fullRangeFlag ? 1 : 2;
                        width2 = width3;
                        height3 = height5;
                        colorTransfer2 = ColorInfo.isoTransferCharacteristicsToColorTransfer(transferCharacteristics);
                        childPosition = childPosition2;
                        bitdepthLuma = bitdepthLuma3;
                        atomType2 = atomType3;
                        pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                        mimeType = mimeType2;
                        projectionData2 = projectionData;
                    } else if (childAtomType2 == 1635135811) {
                        int childAtomBodySize = childAtomSize - 8;
                        byte[] initializationDataChunk = new byte[childAtomBodySize];
                        parent.readBytes(initializationDataChunk, 0, childAtomBodySize);
                        List<byte[]> initializationData5 = ImmutableList.of(initializationDataChunk);
                        initializationData2 = initializationData5;
                        parent.setPosition(childStartPosition + 8);
                        ColorInfo colorInfo = parseAv1c(parent);
                        boolean pixelWidthHeightRatioFromPasp3 = pixelWidthHeightRatioFromPasp2;
                        int bitdepthLuma4 = colorInfo.lumaBitdepth;
                        bitdepthLuma = bitdepthLuma4;
                        int bitdepthLuma5 = colorInfo.chromaBitdepth;
                        int colorSpace2 = colorInfo.colorSpace;
                        int colorSpace3 = colorInfo.colorRange;
                        int colorTransfer3 = colorInfo.colorTransfer;
                        pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                        colorTransfer2 = colorTransfer3;
                        colorRange = colorSpace3;
                        width2 = width3;
                        height3 = height5;
                        childPosition = childPosition2;
                        atomType2 = atomType3;
                        colorSpace = colorSpace2;
                        mimeType = MimeTypes.VIDEO_AV1;
                        projectionData2 = projectionData;
                        bitdepthChroma = bitdepthLuma5;
                        pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp3;
                    } else {
                        boolean pixelWidthHeightRatioFromPasp4 = pixelWidthHeightRatioFromPasp2;
                        boolean fullRangeFlag2 = false;
                        if (childAtomType2 == 1668050025) {
                            if (hdrStaticInfo3 != null) {
                                hdrStaticInfo2 = hdrStaticInfo3;
                            } else {
                                ByteBuffer hdrStaticInfo4 = allocateHdrStaticInfo();
                                hdrStaticInfo2 = hdrStaticInfo4;
                            }
                            hdrStaticInfo2.position(21);
                            hdrStaticInfo2.putShort(parent.readShort());
                            hdrStaticInfo2.putShort(parent.readShort());
                            pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                            hdrStaticInfo3 = hdrStaticInfo2;
                            width2 = width3;
                            height3 = height5;
                            childPosition = childPosition2;
                            atomType2 = atomType3;
                            pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                            projectionData2 = projectionData;
                        } else if (childAtomType2 == 1835295606) {
                            if (hdrStaticInfo3 != null) {
                                hdrStaticInfo = hdrStaticInfo3;
                            } else {
                                ByteBuffer hdrStaticInfo5 = allocateHdrStaticInfo();
                                hdrStaticInfo = hdrStaticInfo5;
                            }
                            short displayPrimariesGX = parent.readShort();
                            short displayPrimariesGY = parent.readShort();
                            atomType2 = atomType3;
                            short displayPrimariesBX = parent.readShort();
                            short displayPrimariesBY = parent.readShort();
                            height3 = height5;
                            short displayPrimariesRX = parent.readShort();
                            width2 = width3;
                            short displayPrimariesRY = parent.readShort();
                            String codecs4 = codecs3;
                            short whitePointX = parent.readShort();
                            childPosition = childPosition2;
                            short whitePointY = parent.readShort();
                            long maxDisplayMasteringLuminance = parent.readUnsignedInt();
                            long minDisplayMasteringLuminance = parent.readUnsignedInt();
                            hdrStaticInfo.position(1);
                            hdrStaticInfo.putShort(displayPrimariesRX);
                            hdrStaticInfo.putShort(displayPrimariesRY);
                            hdrStaticInfo.putShort(displayPrimariesGX);
                            hdrStaticInfo.putShort(displayPrimariesGY);
                            hdrStaticInfo.putShort(displayPrimariesBX);
                            hdrStaticInfo.putShort(displayPrimariesBY);
                            hdrStaticInfo.putShort(whitePointX);
                            hdrStaticInfo.putShort(whitePointY);
                            hdrStaticInfo.putShort((short) (maxDisplayMasteringLuminance / 10000));
                            hdrStaticInfo.putShort((short) (minDisplayMasteringLuminance / 10000));
                            pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                            hdrStaticInfo3 = hdrStaticInfo;
                            pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                            codecs3 = codecs4;
                            colorTransfer2 = colorTransfer2;
                            projectionData2 = projectionData;
                        } else {
                            width2 = width3;
                            height3 = height5;
                            childPosition = childPosition2;
                            int colorTransfer4 = colorTransfer2;
                            atomType2 = atomType3;
                            String codecs5 = codecs3;
                            if (childAtomType2 == 1681012275) {
                                ExtractorUtil.checkContainerInput(mimeType == null, null);
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                mimeType = MimeTypes.VIDEO_H263;
                                pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                codecs3 = codecs5;
                                colorTransfer2 = colorTransfer4;
                                projectionData2 = projectionData;
                            } else if (childAtomType2 == 1702061171) {
                                ExtractorUtil.checkContainerInput(mimeType == null, null);
                                EsdsData esdsData2 = parseEsdsFromParent(parent, childStartPosition);
                                String mimeType3 = esdsData2.mimeType;
                                byte[] initializationDataBytes = esdsData2.initializationData;
                                if (initializationDataBytes == null) {
                                    initializationData = initializationData2;
                                } else {
                                    initializationData = ImmutableList.of(initializationDataBytes);
                                }
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                esdsData = esdsData2;
                                mimeType = mimeType3;
                                initializationData2 = initializationData;
                                pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                codecs3 = codecs5;
                                colorTransfer2 = colorTransfer4;
                                projectionData2 = projectionData;
                            } else if (childAtomType2 == 1885434736) {
                                float pixelWidthHeightRatio7 = parsePaspFromParent(parent, childStartPosition);
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio7;
                                pixelWidthHeightRatioFromPasp2 = true;
                                codecs3 = codecs5;
                                colorTransfer2 = colorTransfer4;
                                projectionData2 = projectionData;
                            } else if (childAtomType2 == 1937126244) {
                                projectionData2 = parseProjFromParent(parent, childStartPosition, childAtomSize);
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                codecs3 = codecs5;
                                colorTransfer2 = colorTransfer4;
                            } else if (childAtomType2 == 1936995172) {
                                int version = parent.readUnsignedByte();
                                parent.skipBytes(3);
                                if (version == 0) {
                                    int layout = parent.readUnsignedByte();
                                    switch (layout) {
                                        case 0:
                                            stereoMode2 = 0;
                                            break;
                                        case 1:
                                            stereoMode2 = 1;
                                            break;
                                        case 2:
                                            stereoMode2 = 2;
                                            break;
                                        case 3:
                                            stereoMode2 = 3;
                                            break;
                                    }
                                    projectionData2 = projectionData;
                                    pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                    stereoMode = stereoMode2;
                                    pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                    codecs3 = codecs5;
                                    colorTransfer2 = colorTransfer4;
                                }
                                stereoMode2 = stereoMode;
                                projectionData2 = projectionData;
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                stereoMode = stereoMode2;
                                pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                codecs3 = codecs5;
                                colorTransfer2 = colorTransfer4;
                            } else {
                                if (childAtomType2 != 1668246642) {
                                    colorTransfer = colorTransfer4;
                                } else if (colorSpace == -1) {
                                    colorTransfer = colorTransfer4;
                                    if (colorTransfer == -1) {
                                        int colorType = parent.readInt();
                                        if (colorType == TYPE_nclx || colorType == TYPE_nclc) {
                                            int colorPrimaries2 = parent.readUnsignedShort();
                                            int transferCharacteristics2 = parent.readUnsignedShort();
                                            parent.skipBytes(2);
                                            if (childAtomSize == 19 && (parent.readUnsignedByte() & 128) != 0) {
                                                fullRangeFlag2 = true;
                                            }
                                            colorSpace = ColorInfo.isoColorPrimariesToColorSpace(colorPrimaries2);
                                            int colorRange4 = fullRangeFlag2 ? 1 : 2;
                                            int colorTransfer5 = ColorInfo.isoTransferCharacteristicsToColorTransfer(transferCharacteristics2);
                                            pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                            colorTransfer2 = colorTransfer5;
                                            colorRange = colorRange4;
                                            pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                            codecs3 = codecs5;
                                            projectionData2 = projectionData;
                                        } else {
                                            Log.w(TAG, "Unsupported color type: " + Atom.getAtomTypeString(colorType));
                                        }
                                    }
                                } else {
                                    colorTransfer = colorTransfer4;
                                }
                                projectionData2 = projectionData;
                                pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                                colorTransfer2 = colorTransfer;
                                pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp4;
                                codecs3 = codecs5;
                            }
                        }
                    }
                    DolbyVisionConfig dolbyVisionConfig = DolbyVisionConfig.parse(parent);
                    if (dolbyVisionConfig == null) {
                        codecs3 = codecs2;
                    } else {
                        codecs3 = dolbyVisionConfig.codecs;
                        mimeType = MimeTypes.VIDEO_DOLBY_VISION;
                    }
                    projectionData2 = projectionData;
                    pixelWidthHeightRatio4 = pixelWidthHeightRatio;
                    colorTransfer2 = height4;
                    pixelWidthHeightRatioFromPasp2 = pixelWidthHeightRatioFromPasp;
                }
                childPosition2 = childPosition + childAtomSize;
                childAtomType = position;
                stsdData = out;
                projectionData3 = projectionData2;
                bitdepthLuma2 = stereoMode;
                atomType3 = atomType2;
                height5 = height3;
                width3 = width2;
                i = size;
            }
        }
        if (mimeType == null) {
            return;
        }
        int bitdepthLuma6 = bitdepthChroma;
        Format.Builder formatBuilder = new Format.Builder().setId(trackId).setSampleMimeType(mimeType).setCodecs(codecs).setWidth(width).setHeight(height).setPixelWidthHeightRatio(pixelWidthHeightRatio).setRotationDegrees(rotationDegrees).setProjectionData(projectionData).setStereoMode(stereoMode).setInitializationData(initializationData2).setMaxNumReorderSamples(maxNumReorderSamples).setDrmInitData(drmInitData3).setColorInfo(new ColorInfo.Builder().setColorSpace(colorSpace).setColorRange(colorRange).setColorTransfer(height2).setHdrStaticInfo(hdrStaticInfo3 != null ? hdrStaticInfo3.array() : null).setLumaBitdepth(bitdepthLuma).setChromaBitdepth(bitdepthLuma6).build());
        if (esdsData != null) {
            Format.Builder averageBitrate = formatBuilder.setAverageBitrate(Ints.saturatedCast(esdsData.bitrate));
            int bitdepthChroma4 = Ints.saturatedCast(esdsData.peakBitrate);
            averageBitrate.setPeakBitrate(bitdepthChroma4);
        }
        out.format = formatBuilder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.common.ColorInfo parseAv1c(androidx.media3.common.util.ParsableByteArray r23) {
        /*
            Method dump skipped, instructions count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.AtomParsers.parseAv1c(androidx.media3.common.util.ParsableByteArray):androidx.media3.common.ColorInfo");
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parent, int atomType, int position, int trackId, StsdData out) {
        parent.setPosition(position + 8 + 8);
        if (atomType == 1835365492) {
            parent.readNullTerminatedString();
            String mimeType = parent.readNullTerminatedString();
            if (mimeType != null) {
                out.format = new Format.Builder().setId(trackId).setSampleMimeType(mimeType).build();
            }
        }
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom edtsAtom) {
        Atom.LeafAtom elstAtom = edtsAtom.getLeafAtomOfType(Atom.TYPE_elst);
        if (elstAtom == null) {
            return null;
        }
        ParsableByteArray elstData = elstAtom.data;
        elstData.setPosition(8);
        int fullAtom = elstData.readInt();
        int version = Atom.parseFullAtomVersion(fullAtom);
        int entryCount = elstData.readUnsignedIntToInt();
        long[] editListDurations = new long[entryCount];
        long[] editListMediaTimes = new long[entryCount];
        for (int i = 0; i < entryCount; i++) {
            editListDurations[i] = version == 1 ? elstData.readUnsignedLongToLong() : elstData.readUnsignedInt();
            editListMediaTimes[i] = version == 1 ? elstData.readLong() : elstData.readInt();
            int mediaRateInteger = elstData.readShort();
            if (mediaRateInteger != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            elstData.skipBytes(2);
        }
        return Pair.create(editListDurations, editListMediaTimes);
    }

    private static float parsePaspFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8);
        int hSpacing = parent.readUnsignedIntToInt();
        int vSpacing = parent.readUnsignedIntToInt();
        return hSpacing / vSpacing;
    }

    private static void parseAudioSampleEntry(ParsableByteArray parent, int atomType, int position, int size, int trackId, String language, boolean isQuickTime, DrmInitData drmInitData, StsdData out, int entryIndex) throws ParserException {
        int channelCount;
        int sampleRate;
        DrmInitData drmInitData2;
        int atomType2;
        String mimeType;
        int pcmEncoding;
        int esdsAtomPosition;
        String codecs;
        String codecs2;
        int atomType3;
        DrmInitData drmInitData3;
        ParsableByteArray parsableByteArray = parent;
        int i = position;
        int i2 = size;
        parsableByteArray.setPosition(i + 8 + 8);
        int quickTimeSoundDescriptionVersion = 0;
        if (isQuickTime) {
            quickTimeSoundDescriptionVersion = parent.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
        }
        int sampleRateMlp = 0;
        int pcmEncoding2 = -1;
        if (quickTimeSoundDescriptionVersion == 0 || quickTimeSoundDescriptionVersion == 1) {
            channelCount = parent.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            sampleRate = parent.readUnsignedFixedPoint1616();
            parsableByteArray.setPosition(parent.getPosition() - 4);
            sampleRateMlp = parent.readInt();
            if (quickTimeSoundDescriptionVersion == 1) {
                parsableByteArray.skipBytes(16);
            }
        } else if (quickTimeSoundDescriptionVersion == 2) {
            parsableByteArray.skipBytes(16);
            sampleRate = (int) Math.round(parent.readDouble());
            channelCount = parent.readUnsignedIntToInt();
            parsableByteArray.skipBytes(4);
            int bitsPerSample = parent.readUnsignedIntToInt();
            int formatSpecificFlags = parent.readUnsignedIntToInt();
            boolean isFloat = (formatSpecificFlags & 1) != 0;
            boolean isBigEndian = (formatSpecificFlags & 2) != 0;
            if (!isFloat) {
                if (bitsPerSample == 8) {
                    pcmEncoding2 = 3;
                } else if (bitsPerSample == 16) {
                    pcmEncoding2 = isBigEndian ? 268435456 : 2;
                } else if (bitsPerSample == 24) {
                    pcmEncoding2 = isBigEndian ? C.ENCODING_PCM_24BIT_BIG_ENDIAN : 21;
                } else if (bitsPerSample == 32) {
                    pcmEncoding2 = isBigEndian ? C.ENCODING_PCM_32BIT_BIG_ENDIAN : 22;
                }
            } else if (bitsPerSample == 32) {
                pcmEncoding2 = 4;
            }
            parsableByteArray.skipBytes(8);
        } else {
            return;
        }
        int childPosition = parent.getPosition();
        if (atomType != 1701733217) {
            drmInitData2 = drmInitData;
            atomType2 = atomType;
        } else {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i, i2);
            if (sampleEntryEncryptionData == null) {
                drmInitData2 = drmInitData;
                atomType2 = atomType;
            } else {
                int atomType4 = ((Integer) sampleEntryEncryptionData.first).intValue();
                if (drmInitData == null) {
                    atomType3 = atomType4;
                    drmInitData3 = null;
                } else {
                    atomType3 = atomType4;
                    drmInitData3 = drmInitData.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                }
                out.trackEncryptionBoxes[entryIndex] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
                atomType2 = atomType3;
                drmInitData2 = drmInitData3;
            }
            parsableByteArray.setPosition(childPosition);
        }
        if (atomType2 == 1633889587) {
            mimeType = MimeTypes.AUDIO_AC3;
        } else if (atomType2 == 1700998451) {
            mimeType = MimeTypes.AUDIO_E_AC3;
        } else if (atomType2 == 1633889588) {
            mimeType = MimeTypes.AUDIO_AC4;
        } else if (atomType2 == 1685353315) {
            mimeType = MimeTypes.AUDIO_DTS;
        } else if (atomType2 == 1685353320 || atomType2 == 1685353324) {
            mimeType = MimeTypes.AUDIO_DTS_HD;
        } else if (atomType2 == 1685353317) {
            mimeType = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (atomType2 == 1685353336) {
            mimeType = MimeTypes.AUDIO_DTS_X;
        } else if (atomType2 == 1935764850) {
            mimeType = MimeTypes.AUDIO_AMR_NB;
        } else if (atomType2 == 1935767394) {
            mimeType = MimeTypes.AUDIO_AMR_WB;
        } else if (atomType2 == 1936684916) {
            mimeType = MimeTypes.AUDIO_RAW;
            pcmEncoding2 = 2;
        } else if (atomType2 == 1953984371) {
            mimeType = MimeTypes.AUDIO_RAW;
            pcmEncoding2 = 268435456;
        } else if (atomType2 == 1819304813) {
            mimeType = MimeTypes.AUDIO_RAW;
            if (pcmEncoding2 == -1) {
                pcmEncoding2 = 2;
            }
        } else {
            mimeType = (atomType2 == 778924082 || atomType2 == 778924083) ? MimeTypes.AUDIO_MPEG : atomType2 == 1835557169 ? MimeTypes.AUDIO_MPEGH_MHA1 : atomType2 == 1835560241 ? MimeTypes.AUDIO_MPEGH_MHM1 : atomType2 == 1634492771 ? MimeTypes.AUDIO_ALAC : atomType2 == 1634492791 ? MimeTypes.AUDIO_ALAW : atomType2 == 1970037111 ? MimeTypes.AUDIO_MLAW : atomType2 == 1332770163 ? MimeTypes.AUDIO_OPUS : atomType2 == 1716281667 ? MimeTypes.AUDIO_FLAC : atomType2 == 1835823201 ? MimeTypes.AUDIO_TRUEHD : null;
        }
        int channelCount2 = channelCount;
        int channelCount3 = sampleRate;
        EsdsData esdsData = null;
        String codecs3 = null;
        String codecs4 = mimeType;
        int childPosition2 = childPosition;
        List<byte[]> initializationData = null;
        while (true) {
            EsdsData esdsData2 = esdsData;
            if (childPosition2 - i < i2) {
                parsableByteArray.setPosition(childPosition2);
                int childAtomSize = parent.readInt();
                ExtractorUtil.checkContainerInput(childAtomSize > 0, "childAtomSize must be positive");
                int childAtomType = parent.readInt();
                if (childAtomType == 1835557187) {
                    parsableByteArray.setPosition(childPosition2 + 8);
                    parsableByteArray.skipBytes(1);
                    int mpeghProfileLevelIndication = parent.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (Objects.equals(codecs4, MimeTypes.AUDIO_MPEGH_MHM1)) {
                        pcmEncoding = pcmEncoding2;
                        codecs2 = String.format("mhm1.%02X", Integer.valueOf(mpeghProfileLevelIndication));
                    } else {
                        pcmEncoding = pcmEncoding2;
                        codecs2 = String.format("mha1.%02X", Integer.valueOf(mpeghProfileLevelIndication));
                    }
                    int mpegh3daConfigLength = parent.readUnsignedShort();
                    byte[] initializationDataBytes = new byte[mpegh3daConfigLength];
                    String codecs5 = codecs2;
                    parsableByteArray.readBytes(initializationDataBytes, 0, mpegh3daConfigLength);
                    if (initializationData == null) {
                        initializationData = ImmutableList.of(initializationDataBytes);
                    } else {
                        initializationData = ImmutableList.of(initializationDataBytes, initializationData.get(0));
                    }
                    codecs3 = codecs5;
                } else {
                    pcmEncoding = pcmEncoding2;
                    if (childAtomType == 1835557200) {
                        parsableByteArray.setPosition(childPosition2 + 8);
                        int numCompatibleSets = parent.readUnsignedByte();
                        if (numCompatibleSets <= 0) {
                            codecs = codecs3;
                        } else {
                            byte[] mpeghCompatibleProfileLevelSet = new byte[numCompatibleSets];
                            codecs = codecs3;
                            parsableByteArray.readBytes(mpeghCompatibleProfileLevelSet, 0, numCompatibleSets);
                            if (initializationData == null) {
                                initializationData = ImmutableList.of(mpeghCompatibleProfileLevelSet);
                            } else {
                                initializationData = ImmutableList.of(initializationData.get(0), mpeghCompatibleProfileLevelSet);
                            }
                        }
                        codecs3 = codecs;
                    } else {
                        String codecs6 = codecs3;
                        if (childAtomType != 1702061171 && (!isQuickTime || childAtomType != 2002876005)) {
                            if (childAtomType == 1684103987) {
                                parsableByteArray.setPosition(childPosition2 + 8);
                                out.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(trackId), language, drmInitData2);
                            } else if (childAtomType == 1684366131) {
                                parsableByteArray.setPosition(childPosition2 + 8);
                                out.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(trackId), language, drmInitData2);
                            } else if (childAtomType == 1684103988) {
                                parsableByteArray.setPosition(childPosition2 + 8);
                                out.format = Ac4Util.parseAc4AnnexEFormat(parsableByteArray, Integer.toString(trackId), language, drmInitData2);
                            } else if (childAtomType == 1684892784) {
                                if (sampleRateMlp <= 0) {
                                    throw ParserException.createForMalformedContainer("Invalid sample rate for Dolby TrueHD MLP stream: " + sampleRateMlp, null);
                                }
                                channelCount2 = 2;
                                channelCount3 = sampleRateMlp;
                                codecs3 = codecs6;
                            } else {
                                if (childAtomType != 1684305011 && childAtomType != 1969517683) {
                                    if (childAtomType == 1682927731) {
                                        int childAtomBodySize = childAtomSize - 8;
                                        byte[] headerBytes = Arrays.copyOf(opusMagic, opusMagic.length + childAtomBodySize);
                                        parsableByteArray.setPosition(childPosition2 + 8);
                                        parsableByteArray.readBytes(headerBytes, opusMagic.length, childAtomBodySize);
                                        List<byte[]> initializationData2 = OpusUtil.buildInitializationData(headerBytes);
                                        initializationData = initializationData2;
                                        codecs3 = codecs6;
                                    } else if (childAtomType == 1684425825) {
                                        int childAtomBodySize2 = childAtomSize - 12;
                                        byte[] initializationDataBytes2 = new byte[childAtomBodySize2 + 4];
                                        initializationDataBytes2[0] = 102;
                                        initializationDataBytes2[1] = 76;
                                        initializationDataBytes2[2] = 97;
                                        initializationDataBytes2[3] = 67;
                                        parsableByteArray.setPosition(childPosition2 + 12);
                                        parsableByteArray.readBytes(initializationDataBytes2, 4, childAtomBodySize2);
                                        List<byte[]> initializationData3 = ImmutableList.of(initializationDataBytes2);
                                        initializationData = initializationData3;
                                        codecs3 = codecs6;
                                    } else if (childAtomType == 1634492771) {
                                        int childAtomBodySize3 = childAtomSize - 12;
                                        byte[] initializationDataBytes3 = new byte[childAtomBodySize3];
                                        parsableByteArray.setPosition(childPosition2 + 12);
                                        parsableByteArray.readBytes(initializationDataBytes3, 0, childAtomBodySize3);
                                        Pair<Integer, Integer> audioSpecificConfig = CodecSpecificDataUtil.parseAlacAudioSpecificConfig(initializationDataBytes3);
                                        int sampleRate2 = ((Integer) audioSpecificConfig.first).intValue();
                                        channelCount2 = ((Integer) audioSpecificConfig.second).intValue();
                                        initializationData = ImmutableList.of(initializationDataBytes3);
                                        channelCount3 = sampleRate2;
                                        codecs3 = codecs6;
                                    }
                                }
                                out.format = new Format.Builder().setId(trackId).setSampleMimeType(codecs4).setChannelCount(channelCount2).setSampleRate(channelCount3).setDrmInitData(drmInitData2).setLanguage(language).build();
                            }
                            codecs3 = codecs6;
                        }
                        if (childAtomType == 1702061171) {
                            esdsAtomPosition = childPosition2;
                        } else {
                            esdsAtomPosition = findBoxPosition(parsableByteArray, Atom.TYPE_esds, childPosition2, childAtomSize);
                        }
                        if (esdsAtomPosition == -1) {
                            codecs3 = codecs6;
                        } else {
                            EsdsData esdsData3 = parseEsdsFromParent(parsableByteArray, esdsAtomPosition);
                            codecs4 = esdsData3.mimeType;
                            byte[] initializationDataBytes4 = esdsData3.initializationData;
                            if (initializationDataBytes4 == null) {
                                esdsData2 = esdsData3;
                                codecs3 = codecs6;
                            } else if (MimeTypes.AUDIO_VORBIS.equals(codecs4)) {
                                initializationData = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(initializationDataBytes4);
                                esdsData2 = esdsData3;
                                codecs3 = codecs6;
                            } else {
                                if (MimeTypes.AUDIO_AAC.equals(codecs4)) {
                                    AacUtil.Config aacConfig = AacUtil.parseAudioSpecificConfig(initializationDataBytes4);
                                    channelCount3 = aacConfig.sampleRateHz;
                                    channelCount2 = aacConfig.channelCount;
                                    codecs6 = aacConfig.codecs;
                                }
                                initializationData = ImmutableList.of(initializationDataBytes4);
                                esdsData2 = esdsData3;
                                codecs3 = codecs6;
                            }
                        }
                    }
                }
                childPosition2 += childAtomSize;
                parsableByteArray = parent;
                i = position;
                i2 = size;
                esdsData = esdsData2;
                pcmEncoding2 = pcmEncoding;
            } else {
                int pcmEncoding3 = pcmEncoding2;
                String codecs7 = codecs3;
                if (out.format == null && codecs4 != null) {
                    Format.Builder formatBuilder = new Format.Builder().setId(trackId).setSampleMimeType(codecs4).setCodecs(codecs7).setChannelCount(channelCount2).setSampleRate(channelCount3).setPcmEncoding(pcmEncoding3).setInitializationData(initializationData).setDrmInitData(drmInitData2).setLanguage(language);
                    if (esdsData2 != null) {
                        formatBuilder.setAverageBitrate(Ints.saturatedCast(esdsData2.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsData2.peakBitrate));
                    }
                    out.format = formatBuilder.build();
                    return;
                }
                return;
            }
        }
    }

    private static int findBoxPosition(ParsableByteArray parent, int boxType, int parentBoxPosition, int parentBoxSize) throws ParserException {
        int childAtomPosition = parent.getPosition();
        ExtractorUtil.checkContainerInput(childAtomPosition >= parentBoxPosition, null);
        while (childAtomPosition - parentBoxPosition < parentBoxSize) {
            parent.setPosition(childAtomPosition);
            int childAtomSize = parent.readInt();
            ExtractorUtil.checkContainerInput(childAtomSize > 0, "childAtomSize must be positive");
            int childType = parent.readInt();
            if (childType == boxType) {
                return childAtomPosition;
            }
            childAtomPosition += childAtomSize;
        }
        return -1;
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parent, int position) {
        parent.setPosition(position + 8 + 4);
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        parent.skipBytes(2);
        int flags = parent.readUnsignedByte();
        if ((flags & 128) != 0) {
            parent.skipBytes(2);
        }
        if ((flags & 64) != 0) {
            parent.skipBytes(parent.readUnsignedByte());
        }
        if ((flags & 32) != 0) {
            parent.skipBytes(2);
        }
        parent.skipBytes(1);
        parseExpandableClassSize(parent);
        int objectTypeIndication = parent.readUnsignedByte();
        String mimeType = MimeTypes.getMimeTypeFromMp4ObjectType(objectTypeIndication);
        if (MimeTypes.AUDIO_MPEG.equals(mimeType) || MimeTypes.AUDIO_DTS.equals(mimeType) || MimeTypes.AUDIO_DTS_HD.equals(mimeType)) {
            return new EsdsData(mimeType, null, -1L, -1L);
        }
        parent.skipBytes(4);
        long peakBitrate = parent.readUnsignedInt();
        long bitrate = parent.readUnsignedInt();
        parent.skipBytes(1);
        int initializationDataSize = parseExpandableClassSize(parent);
        byte[] initializationData = new byte[initializationDataSize];
        parent.readBytes(initializationData, 0, initializationDataSize);
        return new EsdsData(mimeType, initializationData, bitrate > 0 ? bitrate : -1L, peakBitrate > 0 ? peakBitrate : -1L);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parent, int position, int size) throws ParserException {
        Pair<Integer, TrackEncryptionBox> result;
        int childPosition = parent.getPosition();
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            ExtractorUtil.checkContainerInput(childAtomSize > 0, "childAtomSize must be positive");
            int childAtomType = parent.readInt();
            if (childAtomType == 1936289382 && (result = parseCommonEncryptionSinfFromParent(parent, childPosition, childAtomSize)) != null) {
                return result;
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parent, int position, int size) throws ParserException {
        int childPosition = position + 8;
        int schemeInformationBoxPosition = -1;
        int schemeInformationBoxSize = 0;
        String schemeType = null;
        Integer dataFormat = null;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            int childAtomType = parent.readInt();
            if (childAtomType == 1718775137) {
                dataFormat = Integer.valueOf(parent.readInt());
            } else if (childAtomType == 1935894637) {
                parent.skipBytes(4);
                schemeType = parent.readString(4);
            } else if (childAtomType == 1935894633) {
                schemeInformationBoxPosition = childPosition;
                schemeInformationBoxSize = childAtomSize;
            }
            childPosition += childAtomSize;
        }
        if (C.CENC_TYPE_cenc.equals(schemeType) || C.CENC_TYPE_cbc1.equals(schemeType) || C.CENC_TYPE_cens.equals(schemeType) || C.CENC_TYPE_cbcs.equals(schemeType)) {
            ExtractorUtil.checkContainerInput(dataFormat != null, "frma atom is mandatory");
            ExtractorUtil.checkContainerInput(schemeInformationBoxPosition != -1, "schi atom is mandatory");
            TrackEncryptionBox encryptionBox = parseSchiFromParent(parent, schemeInformationBoxPosition, schemeInformationBoxSize, schemeType);
            ExtractorUtil.checkContainerInput(encryptionBox != null, "tenc atom is mandatory");
            return Pair.create(dataFormat, (TrackEncryptionBox) Util.castNonNull(encryptionBox));
        }
        return null;
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parent, int position, int size, String schemeType) {
        byte[] constantIv;
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            int childAtomType = parent.readInt();
            if (childAtomType == 1952804451) {
                int fullAtom = parent.readInt();
                int version = Atom.parseFullAtomVersion(fullAtom);
                parent.skipBytes(1);
                int defaultCryptByteBlock = 0;
                int defaultSkipByteBlock = 0;
                if (version == 0) {
                    parent.skipBytes(1);
                } else {
                    int patternByte = parent.readUnsignedByte();
                    defaultCryptByteBlock = (patternByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                    defaultSkipByteBlock = patternByte & 15;
                }
                boolean defaultIsProtected = parent.readUnsignedByte() == 1;
                int defaultPerSampleIvSize = parent.readUnsignedByte();
                byte[] defaultKeyId = new byte[16];
                parent.readBytes(defaultKeyId, 0, defaultKeyId.length);
                if (defaultIsProtected && defaultPerSampleIvSize == 0) {
                    int constantIvSize = parent.readUnsignedByte();
                    byte[] constantIv2 = new byte[constantIvSize];
                    parent.readBytes(constantIv2, 0, constantIvSize);
                    constantIv = constantIv2;
                } else {
                    constantIv = null;
                }
                return new TrackEncryptionBox(defaultIsProtected, schemeType, defaultPerSampleIvSize, defaultKeyId, defaultCryptByteBlock, defaultSkipByteBlock, constantIv);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parent, int position, int size) {
        int childPosition = position + 8;
        while (childPosition - position < size) {
            parent.setPosition(childPosition);
            int childAtomSize = parent.readInt();
            int childAtomType = parent.readInt();
            if (childAtomType == 1886547818) {
                return Arrays.copyOfRange(parent.getData(), childPosition, childPosition + childAtomSize);
            }
            childPosition += childAtomSize;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray data) {
        int currentByte = data.readUnsignedByte();
        int size = currentByte & 127;
        while ((currentByte & 128) == 128) {
            currentByte = data.readUnsignedByte();
            size = (size << 7) | (currentByte & 127);
        }
        return size;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] timestamps, long duration, long editStartTime, long editEndTime) {
        int lastIndex = timestamps.length - 1;
        int latestDelayIndex = Util.constrainValue(4, 0, lastIndex);
        int earliestPaddingIndex = Util.constrainValue(timestamps.length - 4, 0, lastIndex);
        return timestamps[0] <= editStartTime && editStartTime < timestamps[latestDelayIndex] && timestamps[earliestPaddingIndex] < editEndTime && editEndTime <= duration;
    }

    private AtomParsers() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray stsc, ParsableByteArray chunkOffsets, boolean chunkOffsetsAreLongs) throws ParserException {
            this.stsc = stsc;
            this.chunkOffsets = chunkOffsets;
            this.chunkOffsetsAreLongs = chunkOffsetsAreLongs;
            chunkOffsets.setPosition(12);
            this.length = chunkOffsets.readUnsignedIntToInt();
            stsc.setPosition(12);
            this.remainingSamplesPerChunkChanges = stsc.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(stsc.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long readUnsignedInt;
            int i;
            int i2 = this.index + 1;
            this.index = i2;
            if (i2 == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                readUnsignedInt = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                readUnsignedInt = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = readUnsignedInt;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i3 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i3;
                if (i3 > 0) {
                    i = this.stsc.readUnsignedIntToInt() - 1;
                } else {
                    i = -1;
                }
                this.nextSamplesPerChunkChangeIndex = i;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int id, long duration, int rotationDegrees) {
            this.id = id;
            this.duration = duration;
            this.rotationDegrees = rotationDegrees;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int numberOfEntries) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[numberOfEntries];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class EsdsData {
        private final long bitrate;
        private final byte[] initializationData;
        private final String mimeType;
        private final long peakBitrate;

        public EsdsData(String mimeType, byte[] initializationData, long bitrate, long peakBitrate) {
            this.mimeType = mimeType;
            this.initializationData = initializationData;
            this.bitrate = bitrate;
            this.peakBitrate = peakBitrate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes24.dex */
    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom stszAtom, Format trackFormat) {
            this.data = stszAtom.data;
            this.data.setPosition(12);
            int fixedSampleSize = this.data.readUnsignedIntToInt();
            if (MimeTypes.AUDIO_RAW.equals(trackFormat.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(trackFormat.pcmEncoding, trackFormat.channelCount);
                if (fixedSampleSize == 0 || fixedSampleSize % pcmFrameSize != 0) {
                    Log.w(AtomParsers.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + fixedSampleSize);
                    fixedSampleSize = pcmFrameSize;
                }
            }
            this.fixedSampleSize = fixedSampleSize == 0 ? -1 : fixedSampleSize;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            return this.fixedSampleSize == -1 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes24.dex */
    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Atom.LeafAtom stz2Atom) {
            this.data = stz2Atom.data;
            this.data.setPosition(12);
            this.fieldSize = this.data.readUnsignedIntToInt() & 255;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        @Override // androidx.media3.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 == 0) {
                this.currentByte = this.data.readUnsignedByte();
                return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
