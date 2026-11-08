package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: KeyMapping.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"platformDefaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getPlatformDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class KeyMapping_androidKt {
    private static final KeyMapping platformDefaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMapping_androidKt$platformDefaultKeyMapping$1
        @Override // androidx.compose.foundation.text.KeyMapping
        /* renamed from: map-ZmokQxo */
        public KeyCommand mo862mapZmokQxo(KeyEvent event) {
            KeyCommand keyCommand = null;
            if (KeyEvent_androidKt.m4424isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m4421isAltPressedZmokQxo(event)) {
                long m4418getKeyZmokQxo = KeyEvent_androidKt.m4418getKeyZmokQxo(event);
                if (Key.m4110equalsimpl0(m4418getKeyZmokQxo, MappedKeys.INSTANCE.m884getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_LEFT;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo, MappedKeys.INSTANCE.m885getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo, MappedKeys.INSTANCE.m886getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_HOME;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo, MappedKeys.INSTANCE.m883getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.SELECT_END;
                }
            } else if (KeyEvent_androidKt.m4421isAltPressedZmokQxo(event)) {
                long m4418getKeyZmokQxo2 = KeyEvent_androidKt.m4418getKeyZmokQxo(event);
                if (Key.m4110equalsimpl0(m4418getKeyZmokQxo2, MappedKeys.INSTANCE.m884getDirectionLeftEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_LEFT;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo2, MappedKeys.INSTANCE.m885getDirectionRightEK5gGoQ())) {
                    keyCommand = KeyCommand.LINE_RIGHT;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo2, MappedKeys.INSTANCE.m886getDirectionUpEK5gGoQ())) {
                    keyCommand = KeyCommand.HOME;
                } else if (Key.m4110equalsimpl0(m4418getKeyZmokQxo2, MappedKeys.INSTANCE.m883getDirectionDownEK5gGoQ())) {
                    keyCommand = KeyCommand.END;
                }
            }
            return keyCommand == null ? KeyMappingKt.getDefaultKeyMapping().mo862mapZmokQxo(event) : keyCommand;
        }
    };

    public static final KeyMapping getPlatformDefaultKeyMapping() {
        return platformDefaultKeyMapping;
    }
}
