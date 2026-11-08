package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiRestException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "httpCode", "", "(Ljava/lang/String;I)V", "getHttpCode", "()I", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class GeminiRestException extends Exception {
    public static final int $stable = 0;
    private final int httpCode;

    public /* synthetic */ GeminiRestException(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i);
    }

    public final int getHttpCode() {
        return this.httpCode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeminiRestException(String message, int httpCode) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        this.httpCode = httpCode;
    }
}
