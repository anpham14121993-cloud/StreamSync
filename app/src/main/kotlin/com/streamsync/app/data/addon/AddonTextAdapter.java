package com.streamsync.app.data.addon;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.streamsync.app.settings.BufferCeiling;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/streamsync/app/data/addon/AddonTextAdapter;", "Lcom/google/gson/JsonDeserializer;", "", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonTextAdapter implements JsonDeserializer<String> {
    public static final int $stable = 0;

    @Override // com.google.gson.JsonDeserializer
    public String deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        Object element$iv;
        JsonElement jsonElement;
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (!json.isJsonPrimitive()) {
            if (json.isJsonObject()) {
                Iterable entrySet = json.getAsJsonObject().entrySet();
                Intrinsics.checkNotNullExpressionValue(entrySet, "entrySet(...)");
                Iterable $this$firstOrNull$iv = entrySet;
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        element$iv = it.next();
                        Map.Entry it2 = (Map.Entry) element$iv;
                        if (((JsonElement) it2.getValue()).isJsonPrimitive()) {
                            break;
                        }
                    } else {
                        element$iv = null;
                        break;
                    }
                }
                Map.Entry entry = (Map.Entry) element$iv;
                String asString = (entry == null || (jsonElement = (JsonElement) entry.getValue()) == null) ? null : jsonElement.getAsString();
                return asString == null ? "" : asString;
            }
            if (!json.isJsonArray()) {
                return "";
            }
            JsonArray asJsonArray = json.getAsJsonArray();
            Intrinsics.checkNotNullExpressionValue(asJsonArray, "getAsJsonArray(...)");
            JsonElement jsonElement2 = (JsonElement) CollectionsKt.firstOrNull(asJsonArray);
            String asString2 = jsonElement2 != null ? jsonElement2.getAsString() : null;
            return asString2 == null ? "" : asString2;
        }
        String asString3 = json.getAsString();
        Intrinsics.checkNotNullExpressionValue(asString3, "getAsString(...)");
        return asString3;
    }
}
