package com.streamsync.app.data.addon;

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
import kotlin.text.StringsKt;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0002¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/data/addon/AddonTrailerAdapter;", "Lcom/google/gson/JsonDeserializer;", "", "Lcom/streamsync/app/data/addon/AddonTrailer;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "stringOf", "", "Lcom/google/gson/JsonObject;", "key", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonTrailerAdapter implements JsonDeserializer<List<? extends AddonTrailer>> {
    public static final int $stable = 0;

    @Override // com.google.gson.JsonDeserializer
    public List<? extends AddonTrailer> deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        Iterable $this$mapNotNull$iv;
        AddonTrailer addonTrailer;
        String str;
        Intrinsics.checkNotNullParameter(json, "json");
        String str2 = "type";
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(context, "context");
        if (!json.isJsonArray()) {
            return CollectionsKt.emptyList();
        }
        Iterable asJsonArray = json.getAsJsonArray();
        Intrinsics.checkNotNullExpressionValue(asJsonArray, "getAsJsonArray(...)");
        Iterable $this$mapNotNull$iv2 = asJsonArray;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            JsonElement el = (JsonElement) element$iv$iv$iv;
            if (el.isJsonObject()) {
                JsonObject obj = el.getAsJsonObject();
                Intrinsics.checkNotNull(obj);
                String id = stringOf(obj, "source");
                if (id == null && (id = stringOf(obj, "ytId")) == null && (id = stringOf(obj, "id")) == null) {
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                    addonTrailer = null;
                    str = str2;
                } else {
                    $this$mapNotNull$iv = $this$mapNotNull$iv2;
                    str = str2;
                    addonTrailer = new AddonTrailer(id, stringOf(obj, str2), stringOf(obj, "title"));
                }
            } else {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                addonTrailer = null;
                str = str2;
            }
            if (addonTrailer != null) {
                destination$iv$iv.add(addonTrailer);
            }
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            str2 = str;
        }
        return (List) destination$iv$iv;
    }

    private final String stringOf(JsonObject $this$stringOf, String key) {
        String asString;
        String it;
        JsonElement it2 = $this$stringOf.get(key);
        if (it2 == null) {
            return null;
        }
        if (!it2.isJsonPrimitive()) {
            it2 = null;
        }
        if (it2 == null || (asString = it2.getAsString()) == null || (it = StringsKt.trim((CharSequence) asString).toString()) == null) {
            return null;
        }
        if (it.length() > 0) {
            return it;
        }
        return null;
    }
}
