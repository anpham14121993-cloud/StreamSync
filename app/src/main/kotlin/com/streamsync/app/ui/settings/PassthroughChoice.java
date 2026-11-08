package com.streamsync.app.ui.settings;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.ui.player.PassthroughMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AudioSettingsScreen.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/ui/settings/PassthroughChoice;", "", "mode", "Lcom/streamsync/app/ui/player/PassthroughMode;", "label", "", "explain", "(Lcom/streamsync/app/ui/player/PassthroughMode;Ljava/lang/String;Ljava/lang/String;)V", "getExplain", "()Ljava/lang/String;", "getLabel", "getMode", "()Lcom/streamsync/app/ui/player/PassthroughMode;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes17.dex */
final /* data */ class PassthroughChoice {
    private final String explain;
    private final String label;
    private final PassthroughMode mode;

    public static /* synthetic */ PassthroughChoice copy$default(PassthroughChoice passthroughChoice, PassthroughMode passthroughMode, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            passthroughMode = passthroughChoice.mode;
        }
        if ((i & 2) != 0) {
            str = passthroughChoice.label;
        }
        if ((i & 4) != 0) {
            str2 = passthroughChoice.explain;
        }
        return passthroughChoice.copy(passthroughMode, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final PassthroughMode getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component3, reason: from getter */
    public final String getExplain() {
        return this.explain;
    }

    public final PassthroughChoice copy(PassthroughMode mode, String label, String explain) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(explain, "explain");
        return new PassthroughChoice(mode, label, explain);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PassthroughChoice)) {
            return false;
        }
        PassthroughChoice passthroughChoice = (PassthroughChoice) other;
        return this.mode == passthroughChoice.mode && Intrinsics.areEqual(this.label, passthroughChoice.label) && Intrinsics.areEqual(this.explain, passthroughChoice.explain);
    }

    public int hashCode() {
        return (((this.mode.hashCode() * 31) + this.label.hashCode()) * 31) + this.explain.hashCode();
    }

    public String toString() {
        return "PassthroughChoice(mode=" + this.mode + ", label=" + this.label + ", explain=" + this.explain + ")";
    }

    public PassthroughChoice(PassthroughMode mode, String label, String explain) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(explain, "explain");
        this.mode = mode;
        this.label = label;
        this.explain = explain;
    }

    public final PassthroughMode getMode() {
        return this.mode;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getExplain() {
        return this.explain;
    }
}
