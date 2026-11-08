package com.streamsync.app.data.addon;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0014\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\fH\u0002¨\u0006\u000f"}, d2 = {"Lcom/streamsync/app/data/addon/AddonExtraDefAdapter;", "Lcom/google/gson/JsonDeserializer;", "Lcom/streamsync/app/data/addon/AddonExtraDef;", "()V", "deserialize", "json", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "context", "Lcom/google/gson/JsonDeserializationContext;", "stringOrEmpty", "", "Lcom/google/gson/JsonObject;", "key", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonExtraDefAdapter implements JsonDeserializer<AddonExtraDef> {
    public static final int $stable = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d7  */
    @Override // com.google.gson.JsonDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.streamsync.app.data.addon.AddonExtraDef deserialize(com.google.gson.JsonElement r24, java.lang.reflect.Type r25, com.google.gson.JsonDeserializationContext r26) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonExtraDefAdapter.deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext):com.streamsync.app.data.addon.AddonExtraDef");
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
}
