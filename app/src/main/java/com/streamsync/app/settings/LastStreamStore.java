package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: LastStreamStore.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ6\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\f2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/settings/LastStreamStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clear", "", "parseHeaders", "", "", "raw", "read", "Lcom/streamsync/app/settings/LastStream;", "remember", "url", LastStreamStore.KEY_HEADERS, "contentLengthBytes", "", "durationMs", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class LastStreamStore {

    @Deprecated
    public static final String KEY_DURATION = "duration";

    @Deprecated
    public static final String KEY_HEADERS = "headers";

    @Deprecated
    public static final String KEY_LENGTH = "length";

    @Deprecated
    public static final String KEY_SAVED_AT = "saved_at";

    @Deprecated
    public static final String KEY_URL = "url";
    private final SharedPreferences prefs;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public LastStreamStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = context.getApplicationContext().getSharedPreferences("streamsync_last_stream", 0);
    }

    public static /* synthetic */ void remember$default(LastStreamStore lastStreamStore, String str, Map map, long j, long j2, int i, Object obj) {
        Map map2;
        if ((i & 2) == 0) {
            map2 = map;
        } else {
            map2 = null;
        }
        lastStreamStore.remember(str, map2, j, j2);
    }

    public final void remember(String url, Map<String, String> headers, long contentLengthBytes, long durationMs) {
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.isBlank(url)) {
            return;
        }
        SharedPreferences.Editor $this$remember_u24lambda_u240 = this.prefs.edit();
        $this$remember_u24lambda_u240.putString("url", url);
        $this$remember_u24lambda_u240.putLong(KEY_LENGTH, contentLengthBytes);
        $this$remember_u24lambda_u240.putLong(KEY_DURATION, durationMs);
        $this$remember_u24lambda_u240.putString(KEY_HEADERS, CollectionsKt.joinToString$default((headers == null ? MapsKt.emptyMap() : headers).entrySet(), "\n", null, null, 0, null, new Function1<Map.Entry<? extends String, ? extends String>, CharSequence>() { // from class: com.streamsync.app.settings.LastStreamStore$remember$1$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(Map.Entry<String, String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ((Object) it.getKey()) + ": " + ((Object) it.getValue());
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends String> entry) {
                return invoke2((Map.Entry<String, String>) entry);
            }
        }, 30, null));
        $this$remember_u24lambda_u240.putLong(KEY_SAVED_AT, System.currentTimeMillis());
        $this$remember_u24lambda_u240.apply();
    }

    public final LastStream read() {
        String it = this.prefs.getString("url", null);
        if (it != null) {
            String url = StringsKt.isBlank(it) ^ true ? it : null;
            if (url != null) {
                long length = this.prefs.getLong(KEY_LENGTH, 0L);
                long duration = this.prefs.getLong(KEY_DURATION, 0L);
                String string = this.prefs.getString(KEY_HEADERS, "");
                return new LastStream(url, parseHeaders(string != null ? string : ""), length, duration, this.prefs.getLong(KEY_SAVED_AT, 0L));
            }
        }
        return null;
    }

    public final void clear() {
        this.prefs.edit().clear().apply();
    }

    private final Map<String, String> parseHeaders(String raw) {
        return MapsKt.toMap(SequencesKt.mapNotNull(StringsKt.lineSequence(raw), new Function1<String, Pair<? extends String, ? extends String>>() { // from class: com.streamsync.app.settings.LastStreamStore$parseHeaders$1
            @Override // kotlin.jvm.functions.Function1
            public final Pair<String, String> invoke(String line) {
                int idx;
                Intrinsics.checkNotNullParameter(line, "line");
                if (StringsKt.isBlank(line) || (idx = StringsKt.indexOf$default((CharSequence) line, ':', 0, false, 6, (Object) null)) <= 0) {
                    return null;
                }
                String substring = line.substring(0, idx);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                String obj = StringsKt.trim((CharSequence) substring).toString();
                String substring2 = line.substring(idx + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                return TuplesKt.to(obj, StringsKt.trim((CharSequence) substring2).toString());
            }
        }));
    }

    /* compiled from: LastStreamStore.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/streamsync/app/settings/LastStreamStore$Companion;", "", "()V", "KEY_DURATION", "", "KEY_HEADERS", "KEY_LENGTH", "KEY_SAVED_AT", "KEY_URL", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
