package com.streamsync.app.data.addon;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.streamsync.app.settings.BufferCeiling;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001e\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u0006H\u0002¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/addon/AddonBehaviorHintsAdapter;", "Lcom/google/gson/JsonDeserializer;", "Lcom/streamsync/app/data/addon/AddonBehaviorHints;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "parseHeaderMap", "", "", "element", "parseProxyHeaders", "Lcom/streamsync/app/data/addon/ProxyHeaders;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonBehaviorHintsAdapter implements JsonDeserializer<AddonBehaviorHints> {
    public static final int $stable = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    @Override // com.google.gson.JsonDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.streamsync.app.data.addon.AddonBehaviorHints deserialize(com.google.gson.JsonElement r8, java.lang.reflect.Type r9, com.google.gson.JsonDeserializationContext r10) {
        /*
            r7 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            boolean r0 = r8.isJsonObject()
            if (r0 != 0) goto L21
            com.streamsync.app.data.addon.AddonBehaviorHints r0 = new com.streamsync.app.data.addon.AddonBehaviorHints
            r5 = 7
            r6 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            return r0
        L21:
            com.google.gson.JsonObject r0 = r8.getAsJsonObject()
            java.lang.String r1 = "mimeType"
            com.google.gson.JsonElement r1 = r0.get(r1)
            r2 = 0
            if (r1 == 0) goto L40
            r3 = r1
            r4 = 0
            boolean r3 = r3.isJsonPrimitive()
            if (r3 == 0) goto L38
            goto L39
        L38:
            r1 = r2
        L39:
            if (r1 == 0) goto L40
            java.lang.String r1 = r1.getAsString()
            goto L41
        L40:
            r1 = r2
        L41:
            java.lang.String r3 = "filename"
            com.google.gson.JsonElement r3 = r0.get(r3)
            if (r3 == 0) goto L5a
            r4 = r3
            r5 = 0
            boolean r4 = r4.isJsonPrimitive()
            if (r4 == 0) goto L52
            goto L53
        L52:
            r3 = r2
        L53:
            if (r3 == 0) goto L5a
            java.lang.String r3 = r3.getAsString()
            goto L5b
        L5a:
            r3 = r2
        L5b:
            java.lang.String r4 = "proxyHeaders"
            com.google.gson.JsonElement r4 = r0.get(r4)
            if (r4 == 0) goto L69
            r2 = r4
            r4 = 0
            com.streamsync.app.data.addon.ProxyHeaders r2 = r7.parseProxyHeaders(r2)
        L69:
            com.streamsync.app.data.addon.AddonBehaviorHints r4 = new com.streamsync.app.data.addon.AddonBehaviorHints
            r4.<init>(r1, r3, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonBehaviorHintsAdapter.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):com.streamsync.app.data.addon.AddonBehaviorHints");
    }

    private final ProxyHeaders parseProxyHeaders(JsonElement element) {
        if (!element.isJsonObject()) {
            return null;
        }
        JsonObject obj = element.getAsJsonObject();
        JsonElement p0 = obj.get("request");
        Map<String, String> parseHeaderMap = p0 != null ? parseHeaderMap(p0) : null;
        JsonElement p02 = obj.get("response");
        return new ProxyHeaders(parseHeaderMap, p02 != null ? parseHeaderMap(p02) : null);
    }

    private final Map<String, String> parseHeaderMap(JsonElement element) {
        if (!element.isJsonObject()) {
            return null;
        }
        LinkedHashMap out = new LinkedHashMap();
        for (Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
            Intrinsics.checkNotNull(entry);
            String k = entry.getKey();
            JsonElement v = entry.getValue();
            if (v.isJsonPrimitive() && v.getAsJsonPrimitive().isString()) {
                Intrinsics.checkNotNull(k);
                String key = StringsKt.trim((CharSequence) k).toString();
                String asString = v.getAsString();
                Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
                String value = StringsKt.trim((CharSequence) asString).toString();
                if (!(key.length() == 0)) {
                    if (!(value.length() == 0)) {
                        out.put(key, value);
                    }
                }
            }
        }
        return out;
    }
}
