package com.streamsync.app.transcode;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: Ac3EncoderAvailability.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0006J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u000eJ\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/transcode/Ac3EncoderAvailability;", "", "()V", "LIBRARY", "", "available", "", "checked", "failureReason", "isAvailable", "resetForTest", "", "resetForTest$app_mobileDebug", "resetUncheckedForTest", "resetUncheckedForTest$app_mobileDebug", "unavailableReason", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes18.dex */
public final class Ac3EncoderAvailability {
    private static final String LIBRARY = "ac3encoder";
    private static volatile boolean available;
    private static volatile boolean checked;
    private static volatile String failureReason;
    public static final Ac3EncoderAvailability INSTANCE = new Ac3EncoderAvailability();
    public static final int $stable = 8;

    private Ac3EncoderAvailability() {
    }

    public final boolean isAvailable() {
        if (checked) {
            return available;
        }
        synchronized (this) {
            if (checked) {
                return available;
            }
            boolean z = false;
            try {
                try {
                    System.loadLibrary("ac3encoder");
                    failureReason = null;
                    z = true;
                } catch (SecurityException e) {
                    String message = e.getMessage();
                    if (message == null) {
                        message = e.toString();
                    }
                    failureReason = message;
                }
            } catch (UnsatisfiedLinkError e2) {
                String message2 = e2.getMessage();
                if (message2 == null) {
                    message2 = e2.toString();
                }
                failureReason = message2;
            }
            available = z;
            checked = true;
            return available;
        }
    }

    public final String unavailableReason() {
        isAvailable();
        String reason = failureReason;
        return reason == null ? "thư viện chưa được nạp" : reason;
    }

    public final void resetForTest$app_mobileDebug(boolean available2) {
        checked = true;
        available = available2;
        failureReason = available2 ? null : "do test ép";
    }

    public final void resetUncheckedForTest$app_mobileDebug() {
        checked = false;
        available = false;
        failureReason = null;
    }
}
