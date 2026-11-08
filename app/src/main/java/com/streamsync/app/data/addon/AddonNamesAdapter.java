package com.streamsync.app.data.addon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.streamsync.app.settings.BufferCeiling;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u0004\u0018\u00010\u0003*\u00020\u0007H\u0002¨\u0006\r"}, d2 = {"Lcom/streamsync/app/data/addon/AddonNamesAdapter;", "Lcom/google/gson/JsonDeserializer;", "", "", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "asStringOrNull", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonNamesAdapter implements JsonDeserializer<List<? extends String>> {
    public static final int $stable = 0;

    @Override // com.google.gson.JsonDeserializer
    public List<? extends String> deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (json.isJsonArray()) {
            Iterable asJsonArray = json.getAsJsonArray();
            Intrinsics.checkNotNullExpressionValue(asJsonArray, "getAsJsonArray(...)");
            Iterable $this$mapNotNull$iv = asJsonArray;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                JsonElement it = (JsonElement) element$iv$iv$iv;
                Intrinsics.checkNotNull(it);
                Iterable $this$mapNotNull$iv2 = $this$mapNotNull$iv;
                String asStringOrNull = asStringOrNull(it);
                if (asStringOrNull != null) {
                    destination$iv$iv.add(asStringOrNull);
                }
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
            }
            return (List) destination$iv$iv;
        }
        if (!json.isJsonPrimitive()) {
            return CollectionsKt.emptyList();
        }
        String asString = json.getAsString();
        Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) asString, new char[]{','}, false, 0, 6, (Object) null);
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it2 = (String) item$iv$iv;
            destination$iv$iv2.add(StringsKt.trim((CharSequence) it2).toString());
        }
        Iterable $this$filter$iv = (List) destination$iv$iv2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it3 = (String) element$iv$iv;
            if (it3.length() > 0) {
                destination$iv$iv3.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv3;
    }

    private final String asStringOrNull(JsonElement $this$asStringOrNull) {
        JsonElement jsonElement = $this$asStringOrNull.isJsonPrimitive() ? $this$asStringOrNull : null;
        if (jsonElement != null) {
            return jsonElement.getAsString();
        }
        return null;
    }
}
