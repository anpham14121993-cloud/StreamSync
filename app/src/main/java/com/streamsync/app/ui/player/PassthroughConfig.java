package com.streamsync.app.ui.player;

import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DecoderPreference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpticalPassthroughRenderersFactory.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000eHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003Jy\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010+\u001a\u00020\u00032\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u000200HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012¨\u00061"}, d2 = {"Lcom/streamsync/app/ui/player/PassthroughConfig;", "", "forcePassthrough", "", "ac3", "eac3", "dolbyAtmos", "dts", "dtsHd", "transcodeToAc3", "decoderPreference", "Lcom/streamsync/app/settings/DecoderPreference;", "dolbyVisionP7ToHevc", "passthroughMode", "Lcom/streamsync/app/ui/player/PassthroughMode;", "preferSoftwareAudio", "(ZZZZZZZLcom/streamsync/app/settings/DecoderPreference;ZLcom/streamsync/app/ui/player/PassthroughMode;Z)V", "getAc3", "()Z", "getDecoderPreference", "()Lcom/streamsync/app/settings/DecoderPreference;", "getDolbyAtmos", "getDolbyVisionP7ToHevc", "getDts", "getDtsHd", "getEac3", "getForcePassthrough", "getPassthroughMode", "()Lcom/streamsync/app/ui/player/PassthroughMode;", "getPreferSoftwareAudio", "getTranscodeToAc3", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final /* data */ class PassthroughConfig {
    public static final int $stable = 0;
    private final boolean ac3;
    private final DecoderPreference decoderPreference;
    private final boolean dolbyAtmos;
    private final boolean dolbyVisionP7ToHevc;
    private final boolean dts;
    private final boolean dtsHd;
    private final boolean eac3;
    private final boolean forcePassthrough;
    private final PassthroughMode passthroughMode;
    private final boolean preferSoftwareAudio;
    private final boolean transcodeToAc3;

    /* renamed from: component1, reason: from getter */
    public final boolean getForcePassthrough() {
        return this.forcePassthrough;
    }

    /* renamed from: component10, reason: from getter */
    public final PassthroughMode getPassthroughMode() {
        return this.passthroughMode;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getPreferSoftwareAudio() {
        return this.preferSoftwareAudio;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getAc3() {
        return this.ac3;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getEac3() {
        return this.eac3;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getDolbyAtmos() {
        return this.dolbyAtmos;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getDts() {
        return this.dts;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getDtsHd() {
        return this.dtsHd;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getTranscodeToAc3() {
        return this.transcodeToAc3;
    }

    /* renamed from: component8, reason: from getter */
    public final DecoderPreference getDecoderPreference() {
        return this.decoderPreference;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getDolbyVisionP7ToHevc() {
        return this.dolbyVisionP7ToHevc;
    }

    public final PassthroughConfig copy(boolean forcePassthrough, boolean ac3, boolean eac3, boolean dolbyAtmos, boolean dts, boolean dtsHd, boolean transcodeToAc3, DecoderPreference decoderPreference, boolean dolbyVisionP7ToHevc, PassthroughMode passthroughMode, boolean preferSoftwareAudio) {
        Intrinsics.checkNotNullParameter(decoderPreference, "decoderPreference");
        return new PassthroughConfig(forcePassthrough, ac3, eac3, dolbyAtmos, dts, dtsHd, transcodeToAc3, decoderPreference, dolbyVisionP7ToHevc, passthroughMode, preferSoftwareAudio);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PassthroughConfig)) {
            return false;
        }
        PassthroughConfig passthroughConfig = (PassthroughConfig) other;
        return this.forcePassthrough == passthroughConfig.forcePassthrough && this.ac3 == passthroughConfig.ac3 && this.eac3 == passthroughConfig.eac3 && this.dolbyAtmos == passthroughConfig.dolbyAtmos && this.dts == passthroughConfig.dts && this.dtsHd == passthroughConfig.dtsHd && this.transcodeToAc3 == passthroughConfig.transcodeToAc3 && this.decoderPreference == passthroughConfig.decoderPreference && this.dolbyVisionP7ToHevc == passthroughConfig.dolbyVisionP7ToHevc && this.passthroughMode == passthroughConfig.passthroughMode && this.preferSoftwareAudio == passthroughConfig.preferSoftwareAudio;
    }

    public int hashCode() {
        return (((((((((((((((((((Boolean.hashCode(this.forcePassthrough) * 31) + Boolean.hashCode(this.ac3)) * 31) + Boolean.hashCode(this.eac3)) * 31) + Boolean.hashCode(this.dolbyAtmos)) * 31) + Boolean.hashCode(this.dts)) * 31) + Boolean.hashCode(this.dtsHd)) * 31) + Boolean.hashCode(this.transcodeToAc3)) * 31) + this.decoderPreference.hashCode()) * 31) + Boolean.hashCode(this.dolbyVisionP7ToHevc)) * 31) + (this.passthroughMode == null ? 0 : this.passthroughMode.hashCode())) * 31) + Boolean.hashCode(this.preferSoftwareAudio);
    }

    public String toString() {
        return "PassthroughConfig(forcePassthrough=" + this.forcePassthrough + ", ac3=" + this.ac3 + ", eac3=" + this.eac3 + ", dolbyAtmos=" + this.dolbyAtmos + ", dts=" + this.dts + ", dtsHd=" + this.dtsHd + ", transcodeToAc3=" + this.transcodeToAc3 + ", decoderPreference=" + this.decoderPreference + ", dolbyVisionP7ToHevc=" + this.dolbyVisionP7ToHevc + ", passthroughMode=" + this.passthroughMode + ", preferSoftwareAudio=" + this.preferSoftwareAudio + ")";
    }

    public PassthroughConfig(boolean forcePassthrough, boolean ac3, boolean eac3, boolean dolbyAtmos, boolean dts, boolean dtsHd, boolean transcodeToAc3, DecoderPreference decoderPreference, boolean dolbyVisionP7ToHevc, PassthroughMode passthroughMode, boolean preferSoftwareAudio) {
        Intrinsics.checkNotNullParameter(decoderPreference, "decoderPreference");
        this.forcePassthrough = forcePassthrough;
        this.ac3 = ac3;
        this.eac3 = eac3;
        this.dolbyAtmos = dolbyAtmos;
        this.dts = dts;
        this.dtsHd = dtsHd;
        this.transcodeToAc3 = transcodeToAc3;
        this.decoderPreference = decoderPreference;
        this.dolbyVisionP7ToHevc = dolbyVisionP7ToHevc;
        this.passthroughMode = passthroughMode;
        this.preferSoftwareAudio = preferSoftwareAudio;
    }

    public /* synthetic */ PassthroughConfig(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, DecoderPreference decoderPreference, boolean z8, PassthroughMode passthroughMode, boolean z9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, z3, z4, z5, z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? DecoderPreference.PREFER_APP : decoderPreference, (i & 256) != 0 ? true : z8, (i & 512) != 0 ? null : passthroughMode, (i & 1024) != 0 ? false : z9);
    }

    public final boolean getForcePassthrough() {
        return this.forcePassthrough;
    }

    public final boolean getAc3() {
        return this.ac3;
    }

    public final boolean getEac3() {
        return this.eac3;
    }

    public final boolean getDolbyAtmos() {
        return this.dolbyAtmos;
    }

    public final boolean getDts() {
        return this.dts;
    }

    public final boolean getDtsHd() {
        return this.dtsHd;
    }

    public final boolean getTranscodeToAc3() {
        return this.transcodeToAc3;
    }

    public final DecoderPreference getDecoderPreference() {
        return this.decoderPreference;
    }

    public final boolean getDolbyVisionP7ToHevc() {
        return this.dolbyVisionP7ToHevc;
    }

    public final PassthroughMode getPassthroughMode() {
        return this.passthroughMode;
    }

    public final boolean getPreferSoftwareAudio() {
        return this.preferSoftwareAudio;
    }
}
