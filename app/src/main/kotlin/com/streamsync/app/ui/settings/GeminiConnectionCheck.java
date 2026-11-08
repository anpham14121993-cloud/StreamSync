package com.streamsync.app.ui.settings;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: GeminiConnectionCheck.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0086@¢\u0006\u0002\u0010\bJ<\u0010\t\u001a\u0004\u0018\u0001H\n\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0082@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/ui/settings/GeminiConnectionCheck;", "", "()V", "run", "", "context", "Landroid/content/Context;", "key", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeoutOrNullCompat", ExifInterface.GPS_DIRECTION_TRUE, TranslateLanguage.MALAY, "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
public final class GeminiConnectionCheck {
    public static final int $stable = 0;
    public static final GeminiConnectionCheck INSTANCE = new GeminiConnectionCheck();

    private GeminiConnectionCheck() {
    }

    public final Object run(Context context, String key, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new GeminiConnectionCheck$run$2(context, key, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object withTimeoutOrNullCompat(long ms, Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        return TimeoutKt.withTimeoutOrNull(ms, new GeminiConnectionCheck$withTimeoutOrNullCompat$2(function1, null), continuation);
    }
}
