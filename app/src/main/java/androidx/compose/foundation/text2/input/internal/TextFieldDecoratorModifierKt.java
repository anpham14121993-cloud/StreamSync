package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: TextFieldDecoratorModifier.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"withDefaultsFrom", "Landroidx/compose/foundation/text/KeyboardOptions;", "defaults", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class TextFieldDecoratorModifierKt {
    public static final KeyboardOptions withDefaultsFrom(KeyboardOptions $this$withDefaultsFrom, KeyboardOptions defaults) {
        int capitalization;
        int keyboardType;
        int imeAction;
        if (defaults == null) {
            return $this$withDefaultsFrom;
        }
        if (!KeyboardCapitalization.m5431equalsimpl0($this$withDefaultsFrom.getCapitalization(), KeyboardCapitalization.INSTANCE.m5440getNoneIUNYP9k())) {
            capitalization = $this$withDefaultsFrom.getCapitalization();
        } else {
            capitalization = defaults.getCapitalization();
        }
        boolean z = $this$withDefaultsFrom.getAutoCorrect() && defaults.getAutoCorrect();
        if (!KeyboardType.m5446equalsimpl0($this$withDefaultsFrom.getKeyboardType(), KeyboardType.INSTANCE.m5466getTextPjHm6EE())) {
            keyboardType = $this$withDefaultsFrom.getKeyboardType();
        } else {
            keyboardType = defaults.getKeyboardType();
        }
        if (!ImeAction.m5399equalsimpl0($this$withDefaultsFrom.getImeAction(), ImeAction.INSTANCE.m5411getDefaulteUduSuo())) {
            imeAction = $this$withDefaultsFrom.getImeAction();
        } else {
            imeAction = defaults.getImeAction();
        }
        return new KeyboardOptions(capitalization, z, keyboardType, imeAction, null, 16, null);
    }
}
