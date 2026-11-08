package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/ui/player/AudioTrackOption;", "", "groupIndex", "", "label", "", "language", "isDefault", "", "(ILjava/lang/String;Ljava/lang/String;Z)V", "getGroupIndex", "()I", "()Z", "getLabel", "()Ljava/lang/String;", "getLanguage", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final /* data */ class AudioTrackOption {
    public static final int $stable = 0;
    private final int groupIndex;
    private final boolean isDefault;
    private final String label;
    private final String language;

    public static /* synthetic */ AudioTrackOption copy$default(AudioTrackOption audioTrackOption, int i, String str, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = audioTrackOption.groupIndex;
        }
        if ((i2 & 2) != 0) {
            str = audioTrackOption.label;
        }
        if ((i2 & 4) != 0) {
            str2 = audioTrackOption.language;
        }
        if ((i2 & 8) != 0) {
            z = audioTrackOption.isDefault;
        }
        return audioTrackOption.copy(i, str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getGroupIndex() {
        return this.groupIndex;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsDefault() {
        return this.isDefault;
    }

    public final AudioTrackOption copy(int groupIndex, String label, String language, boolean isDefault) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new AudioTrackOption(groupIndex, label, language, isDefault);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioTrackOption)) {
            return false;
        }
        AudioTrackOption audioTrackOption = (AudioTrackOption) other;
        return this.groupIndex == audioTrackOption.groupIndex && Intrinsics.areEqual(this.label, audioTrackOption.label) && Intrinsics.areEqual(this.language, audioTrackOption.language) && this.isDefault == audioTrackOption.isDefault;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.groupIndex) * 31) + this.label.hashCode()) * 31) + (this.language == null ? 0 : this.language.hashCode())) * 31) + Boolean.hashCode(this.isDefault);
    }

    public String toString() {
        return "AudioTrackOption(groupIndex=" + this.groupIndex + ", label=" + this.label + ", language=" + this.language + ", isDefault=" + this.isDefault + ")";
    }

    public AudioTrackOption(int groupIndex, String label, String language, boolean isDefault) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.groupIndex = groupIndex;
        this.label = label;
        this.language = language;
        this.isDefault = isDefault;
    }

    public final int getGroupIndex() {
        return this.groupIndex;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final boolean isDefault() {
        return this.isDefault;
    }
}
