package androidx.media3.extractor.mkv;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import java.io.IOException;

/* loaded from: classes24.dex */
final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput input) throws IOException {
        long inputLength = input.getLength();
        long j = 1024;
        if (inputLength != -1 && inputLength <= 1024) {
            j = inputLength;
        }
        int bytesToSearch = (int) j;
        input.peekFully(this.scratch.getData(), 0, 4);
        long tag = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (tag != 440786851) {
            int i = this.peekLength + 1;
            this.peekLength = i;
            if (i == bytesToSearch) {
                return false;
            }
            input.peekFully(this.scratch.getData(), 0, 1);
            tag = ((tag << 8) & (-256)) | (this.scratch.getData()[0] & 255);
        }
        long headerSize = readUint(input);
        long headerStart = this.peekLength;
        if (headerSize != Long.MIN_VALUE && (inputLength == -1 || headerStart + headerSize < inputLength)) {
            while (this.peekLength < headerStart + headerSize) {
                long id = readUint(input);
                if (id == Long.MIN_VALUE) {
                    return false;
                }
                long tag2 = tag;
                long size = readUint(input);
                if (size < 0 || size > 2147483647L) {
                    return false;
                }
                if (size != 0) {
                    int sizeInt = (int) size;
                    input.advancePeekPosition(sizeInt);
                    this.peekLength += sizeInt;
                }
                tag = tag2;
            }
            return ((long) this.peekLength) == headerStart + headerSize;
        }
        return false;
    }

    private long readUint(ExtractorInput input) throws IOException {
        input.peekFully(this.scratch.getData(), 0, 1);
        int value = this.scratch.getData()[0] & 255;
        if (value == 0) {
            return Long.MIN_VALUE;
        }
        int mask = 128;
        int length = 0;
        while ((value & mask) == 0) {
            mask >>= 1;
            length++;
        }
        int value2 = value & (~mask);
        input.peekFully(this.scratch.getData(), 1, length);
        for (int i = 0; i < length; i++) {
            value2 = (value2 << 8) + (this.scratch.getData()[i + 1] & 255);
        }
        int i2 = this.peekLength;
        this.peekLength = i2 + length + 1;
        return value2;
    }
}
