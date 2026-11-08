package androidx.media3.extractor.mp4;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes24.dex */
public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final TrackOutput.CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    public final String schemeType;

    public TrackEncryptionBox(boolean isEncrypted, String schemeType, int perSampleIvSize, byte[] keyId, int defaultEncryptedBlocks, int defaultClearBlocks, byte[] defaultInitializationVector) {
        Assertions.checkArgument((defaultInitializationVector == null) ^ (perSampleIvSize == 0));
        this.isEncrypted = isEncrypted;
        this.schemeType = schemeType;
        this.perSampleIvSize = perSampleIvSize;
        this.defaultInitializationVector = defaultInitializationVector;
        this.cryptoData = new TrackOutput.CryptoData(schemeToCryptoMode(schemeType), keyId, defaultEncryptedBlocks, defaultClearBlocks);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int schemeToCryptoMode(String schemeType) {
        char c;
        if (schemeType == null) {
            return 1;
        }
        switch (schemeType.hashCode()) {
            case 3046605:
                if (schemeType.equals(C.CENC_TYPE_cbc1)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3046671:
                if (schemeType.equals(C.CENC_TYPE_cbcs)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3049879:
                if (schemeType.equals(C.CENC_TYPE_cenc)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3049895:
                if (schemeType.equals(C.CENC_TYPE_cens)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                Log.w(TAG, "Unsupported protection scheme type '" + schemeType + "'. Assuming AES-CTR crypto mode.");
                return 1;
        }
    }
}
