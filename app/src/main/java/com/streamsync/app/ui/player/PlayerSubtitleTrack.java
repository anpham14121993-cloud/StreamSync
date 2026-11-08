package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerSubtitleTrack;", "", "groupIndex", "", "label", "", "language", "(ILjava/lang/String;Ljava/lang/String;)V", "getGroupIndex", "()I", "getLabel", "()Ljava/lang/String;", "getLanguage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final /* data */ class PlayerSubtitleTrack {
    public static final int $stable = 0;
    private final int groupIndex;
    private final String label;
    private final String language;

    public static /* synthetic */ PlayerSubtitleTrack copy$default(PlayerSubtitleTrack playerSubtitleTrack, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = playerSubtitleTrack.groupIndex;
        }
        if ((i2 & 2) != 0) {
            str = playerSubtitleTrack.label;
        }
        if ((i2 & 4) != 0) {
            str2 = playerSubtitleTrack.language;
        }
        return playerSubtitleTrack.copy(i, str, str2);
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

    public final PlayerSubtitleTrack copy(int groupIndex, String label, String language) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new PlayerSubtitleTrack(groupIndex, label, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerSubtitleTrack)) {
            return false;
        }
        PlayerSubtitleTrack playerSubtitleTrack = (PlayerSubtitleTrack) other;
        return this.groupIndex == playerSubtitleTrack.groupIndex && Intrinsics.areEqual(this.label, playerSubtitleTrack.label) && Intrinsics.areEqual(this.language, playerSubtitleTrack.language);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.groupIndex) * 31) + this.label.hashCode()) * 31) + (this.language == null ? 0 : this.language.hashCode());
    }

    public String toString() {
        return "PlayerSubtitleTrack(groupIndex=" + this.groupIndex + ", label=" + this.label + ", language=" + this.language + ")";
    }

    public PlayerSubtitleTrack(int groupIndex, String label, String language) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.groupIndex = groupIndex;
        this.label = label;
        this.language = language;
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
}
