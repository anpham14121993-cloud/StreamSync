package androidx.media3.exoplayer.source.mediaparser;

import android.media.MediaParser;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.Util;
import java.io.IOException;

/* loaded from: classes6.dex */
public final class InputReaderAdapterV30 implements MediaParser.SeekableInputReader {
    private long currentPosition;
    private DataReader dataReader;
    private long lastSeekPosition;
    private long resourceLength;

    public void setDataReader(DataReader dataReader, long length) {
        this.dataReader = dataReader;
        this.resourceLength = length;
        this.lastSeekPosition = -1L;
    }

    public void setCurrentPosition(long position) {
        this.currentPosition = position;
    }

    public long getAndResetSeekPosition() {
        long lastSeekPosition = this.lastSeekPosition;
        this.lastSeekPosition = -1L;
        return lastSeekPosition;
    }

    @Override // android.media.MediaParser.SeekableInputReader
    public void seekToPosition(long position) {
        this.lastSeekPosition = position;
    }

    @Override // android.media.MediaParser.InputReader
    public int read(byte[] bytes, int offset, int readLength) throws IOException {
        int bytesRead = ((DataReader) Util.castNonNull(this.dataReader)).read(bytes, offset, readLength);
        this.currentPosition += bytesRead;
        return bytesRead;
    }

    @Override // android.media.MediaParser.InputReader
    public long getPosition() {
        return this.currentPosition;
    }

    @Override // android.media.MediaParser.InputReader
    public long getLength() {
        return this.resourceLength;
    }
}
