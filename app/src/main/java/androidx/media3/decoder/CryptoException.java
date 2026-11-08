package androidx.media3.decoder;

/* loaded from: classes24.dex */
public class CryptoException extends Exception {
    public final int errorCode;

    public CryptoException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
