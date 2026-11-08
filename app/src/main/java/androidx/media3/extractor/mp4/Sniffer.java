package androidx.media3.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import java.io.IOException;

/* loaded from: classes24.dex */
final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    public static SniffFailure sniffFragmented(ExtractorInput input) throws IOException {
        return sniffInternal(input, true, false);
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput input, boolean acceptHeic) throws IOException {
        return sniffInternal(input, false, acceptHeic);
    }

    private static SniffFailure sniffInternal(ExtractorInput input, boolean fragmented, boolean acceptHeic) throws IOException {
        int bytesToSearch;
        long inputLength = input.getLength();
        long j = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        if (inputLength != -1 && inputLength <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j = inputLength;
        }
        int bytesToSearch2 = (int) j;
        ParsableByteArray buffer = new ParsableByteArray(64);
        int bytesSearched = 0;
        boolean foundGoodFileType = false;
        boolean isFragmented = false;
        while (bytesSearched < bytesToSearch2) {
            int headerSize = 8;
            buffer.reset(8);
            boolean success = input.peekFully(buffer.getData(), 0, 8, true);
            if (!success) {
                break;
            }
            long atomSize = buffer.readUnsignedInt();
            int atomType = buffer.readInt();
            if (atomSize == 1) {
                headerSize = 16;
                input.peekFully(buffer.getData(), 8, 8);
                buffer.setLimit(16);
                atomSize = buffer.readLong();
            } else if (atomSize == 0) {
                long fileEndPosition = input.getLength();
                if (fileEndPosition != -1) {
                    atomSize = (fileEndPosition - input.getPeekPosition()) + 8;
                }
            }
            if (atomSize < headerSize) {
                return new AtomSizeTooSmallSniffFailure(atomType, atomSize, headerSize);
            }
            bytesSearched += headerSize;
            if (atomType == 1836019574) {
                bytesToSearch2 += (int) atomSize;
                if (inputLength != -1 && bytesToSearch2 > inputLength) {
                    bytesToSearch2 = (int) inputLength;
                }
            } else {
                if (atomType != 1836019558 && atomType != 1836475768) {
                    if (atomType == 1835295092) {
                        foundGoodFileType = true;
                    }
                    long inputLength2 = inputLength;
                    if ((bytesSearched + atomSize) - headerSize >= bytesToSearch2) {
                        break;
                    }
                    int atomDataSize = (int) (atomSize - headerSize);
                    bytesSearched += atomDataSize;
                    if (atomType != 1718909296) {
                        bytesToSearch = bytesToSearch2;
                        if (atomDataSize != 0) {
                            input.advancePeekPosition(atomDataSize);
                        }
                    } else {
                        if (atomDataSize < 8) {
                            return new AtomSizeTooSmallSniffFailure(atomType, atomDataSize, 8);
                        }
                        bytesToSearch = bytesToSearch2;
                        buffer.reset(atomDataSize);
                        input.peekFully(buffer.getData(), 0, atomDataSize);
                        int majorBrand = buffer.readInt();
                        if (isCompatibleBrand(majorBrand, acceptHeic)) {
                            foundGoodFileType = true;
                        }
                        buffer.skipBytes(4);
                        int compatibleBrandsCount = buffer.bytesLeft() / 4;
                        int[] compatibleBrands = null;
                        if (!foundGoodFileType && compatibleBrandsCount > 0) {
                            compatibleBrands = new int[compatibleBrandsCount];
                            int i = 0;
                            while (true) {
                                if (i >= compatibleBrandsCount) {
                                    break;
                                }
                                compatibleBrands[i] = buffer.readInt();
                                int compatibleBrandsCount2 = compatibleBrandsCount;
                                if (!isCompatibleBrand(compatibleBrands[i], acceptHeic)) {
                                    i++;
                                    compatibleBrandsCount = compatibleBrandsCount2;
                                } else {
                                    foundGoodFileType = true;
                                    break;
                                }
                            }
                        }
                        if (!foundGoodFileType) {
                            return new UnsupportedBrandsSniffFailure(majorBrand, compatibleBrands);
                        }
                    }
                    bytesToSearch2 = bytesToSearch;
                    inputLength = inputLength2;
                }
                isFragmented = true;
                break;
            }
        }
        if (!foundGoodFileType) {
            return NoDeclaredBrandSniffFailure.INSTANCE;
        }
        if (fragmented != isFragmented) {
            if (isFragmented) {
                return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
            }
            return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
        }
        return null;
    }

    private static boolean isCompatibleBrand(int brand, boolean acceptHeic) {
        if ((brand >>> 8) == 3368816) {
            return true;
        }
        if (brand == 1751476579 && acceptHeic) {
            return true;
        }
        for (int compatibleBrand : COMPATIBLE_BRANDS) {
            if (compatibleBrand == brand) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
