package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.TextRange;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.media3.extractor.ts.TsExtractor;
import com.streamsync.app.settings.BufferCeiling;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* compiled from: TextInputServiceAndroid.android.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\f\u0010\u0011\u001a\u00020\u000b*\u00020\fH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DEBUG_CLASS", "", "hasFlag", "", "bits", "", "flag", "asExecutor", "Ljava/util/concurrent/Executor;", "Landroid/view/Choreographer;", "update", "", "Landroid/view/inputmethod/EditorInfo;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "updateWithEmojiCompat", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class TextInputServiceAndroid_androidKt {
    private static final String DEBUG_CLASS = "TextInputServiceAndroid";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo $this$updateWithEmojiCompat) {
        if (EmojiCompat.isConfigured()) {
            EmojiCompat.get().updateEditorInfo($this$updateWithEmojiCompat);
        }
    }

    public static final void update(EditorInfo $this$update, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        String it;
        int imeAction = imeOptions.getImeAction();
        int i = 6;
        if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5411getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i = 0;
            }
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5415getNoneeUduSuo())) {
            i = 1;
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5413getGoeUduSuo())) {
            i = 2;
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5414getNexteUduSuo())) {
            i = 5;
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5416getPreviouseUduSuo())) {
            i = 7;
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5417getSearcheUduSuo())) {
            i = 3;
        } else if (ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5418getSendeUduSuo())) {
            i = 4;
        } else if (!ImeAction.m5399equalsimpl0(imeAction, ImeAction.INSTANCE.m5412getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction".toString());
        }
        $this$update.imeOptions = i;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (it = platformImeOptions.getPrivateImeOptions()) != null) {
            $this$update.privateImeOptions = it;
        }
        int keyboardType = imeOptions.getKeyboardType();
        if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5466getTextPjHm6EE())) {
            $this$update.inputType = 1;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5459getAsciiPjHm6EE())) {
            $this$update.inputType = 1;
            $this$update.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5462getNumberPjHm6EE())) {
            $this$update.inputType = 2;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5465getPhonePjHm6EE())) {
            $this$update.inputType = 3;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5467getUriPjHm6EE())) {
            $this$update.inputType = 17;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5461getEmailPjHm6EE())) {
            $this$update.inputType = 33;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5464getPasswordPjHm6EE())) {
            $this$update.inputType = TsExtractor.TS_STREAM_TYPE_AC3;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5463getNumberPasswordPjHm6EE())) {
            $this$update.inputType = 18;
        } else if (KeyboardType.m5446equalsimpl0(keyboardType, KeyboardType.INSTANCE.m5460getDecimalPjHm6EE())) {
            $this$update.inputType = 8194;
        } else {
            throw new IllegalStateException("Invalid Keyboard Type".toString());
        }
        if (!imeOptions.getSingleLine() && hasFlag($this$update.inputType, 1)) {
            $this$update.inputType |= 131072;
            if (ImeAction.m5399equalsimpl0(imeOptions.getImeAction(), ImeAction.INSTANCE.m5411getDefaulteUduSuo())) {
                $this$update.imeOptions |= 1073741824;
            }
        }
        if (hasFlag($this$update.inputType, 1)) {
            int capitalization = imeOptions.getCapitalization();
            if (KeyboardCapitalization.m5431equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m5439getCharactersIUNYP9k())) {
                $this$update.inputType |= 4096;
            } else if (KeyboardCapitalization.m5431equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m5442getWordsIUNYP9k())) {
                $this$update.inputType |= 8192;
            } else if (KeyboardCapitalization.m5431equalsimpl0(capitalization, KeyboardCapitalization.INSTANCE.m5441getSentencesIUNYP9k())) {
                $this$update.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                $this$update.inputType |= 32768;
            }
        }
        $this$update.initialSelStart = TextRange.m5235getStartimpl(textFieldValue.getSelection());
        $this$update.initialSelEnd = TextRange.m5230getEndimpl(textFieldValue.getSelection());
        EditorInfoCompat.setInitialSurroundingText($this$update, textFieldValue.getText());
        $this$update.imeOptions |= 33554432;
    }

    public static final Executor asExecutor(final Choreographer $this$asExecutor) {
        return new Executor() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                TextInputServiceAndroid_androidKt.asExecutor$lambda$2($this$asExecutor, runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void asExecutor$lambda$2(Choreographer $this_asExecutor, final Runnable runnable) {
        $this_asExecutor.postFrameCallback(new Choreographer.FrameCallback() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt$$ExternalSyntheticLambda0
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                runnable.run();
            }
        });
    }

    private static final boolean hasFlag(int bits, int flag) {
        return (bits & flag) == flag;
    }
}
