package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.streamsync.app.settings.BufferCeiling;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u0014\u0010\u0010\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0002¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/data/addon/AddonResourceDefAdapter;", "Lcom/google/gson/JsonDeserializer;", "Lcom/streamsync/app/data/addon/AddonResourceDef;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "stringList", "", "", "Lcom/google/gson/JsonObject;", "key", "stringOrEmpty", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonResourceDefAdapter implements JsonDeserializer<AddonResourceDef> {
    public static final int $stable = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public AddonResourceDef deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (json.isJsonPrimitive()) {
            String asString = json.getAsString();
            Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
            return new AddonResourceDef(asString, null, null, 6, null);
        }
        if (json.isJsonObject()) {
            JsonObject obj = json.getAsJsonObject();
            Intrinsics.checkNotNull(obj);
            return new AddonResourceDef(stringOrEmpty(obj, HintConstants.AUTOFILL_HINT_NAME), stringList(obj, "types"), stringList(obj, "idPrefixes"));
        }
        return new AddonResourceDef(null, null, null, 7, null);
    }

    private final String stringOrEmpty(JsonObject $this$stringOrEmpty, String key) {
        JsonElement it = $this$stringOrEmpty.get(key);
        String str = null;
        if (it != null) {
            if (!it.isJsonPrimitive()) {
                it = null;
            }
            if (it != null) {
                str = it.getAsString();
            }
        }
        return str == null ? "" : str;
    }

    private final List<String> stringList(JsonObject $this$stringList, String key) {
        Iterable asJsonArray;
        JsonElement it = $this$stringList.get(key);
        ArrayList arrayList = null;
        if (it != null) {
            if (!it.isJsonArray()) {
                it = null;
            }
            if (it != null && (asJsonArray = it.getAsJsonArray()) != null) {
                Iterable $this$mapNotNull$iv = asJsonArray;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    JsonElement it2 = (JsonElement) element$iv$iv$iv;
                    JsonElement jsonElement = it2.isJsonPrimitive() ? it2 : null;
                    String asString = jsonElement != null ? jsonElement.getAsString() : null;
                    if (asString != null) {
                        destination$iv$iv.add(asString);
                    }
                }
                arrayList = (List) destination$iv$iv;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }
}
