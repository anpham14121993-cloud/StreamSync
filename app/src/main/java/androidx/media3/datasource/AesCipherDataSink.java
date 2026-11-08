package androidx.media3.datasource;

import androidx.media3.common.util.Util;
import java.io.IOException;

/* loaded from: classes24.dex */
public final class AesCipherDataSink implements DataSink {
    private AesFlushingCipher cipher;
    private final byte[] scratch;
    private final byte[] secretKey;
    private final DataSink wrappedDataSink;

    public AesCipherDataSink(byte[] secretKey, DataSink wrappedDataSink) {
        this(secretKey, wrappedDataSink, null);
    }

    public AesCipherDataSink(byte[] secretKey, DataSink wrappedDataSink, byte[] scratch) {
        this.wrappedDataSink = wrappedDataSink;
        this.secretKey = secretKey;
        this.scratch = scratch;
    }

    @Override // androidx.media3.datasource.DataSink
    public void open(DataSpec dataSpec) throws IOException {
        this.wrappedDataSink.open(dataSpec);
        this.cipher = new AesFlushingCipher(1, this.secretKey, dataSpec.key, dataSpec.position + dataSpec.uriPositionOffset);
    }

    @Override // androidx.media3.datasource.DataSink
    public void write(byte[] buffer, int offset, int length) throws IOException {
        if (this.scratch == null) {
            ((AesFlushingCipher) Util.castNonNull(this.cipher)).updateInPlace(buffer, offset, length);
            this.wrappedDataSink.write(buffer, offset, length);
            return;
        }
        int bytesProcessed = 0;
        while (bytesProcessed < length) {
            int bytesToProcess = Math.min(length - bytesProcessed, this.scratch.length);
            ((AesFlushingCipher) Util.castNonNull(this.cipher)).update(buffer, offset + bytesProcessed, bytesToProcess, this.scratch, 0);
            this.wrappedDataSink.write(this.scratch, 0, bytesToProcess);
            bytesProcessed += bytesToProcess;
        }
    }

    @Override // androidx.media3.datasource.DataSink
    public void close() throws IOException {
        this.cipher = null;
        this.wrappedDataSink.close();
    }
}
