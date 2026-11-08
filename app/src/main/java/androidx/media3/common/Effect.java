package androidx.media3.common;

/* loaded from: classes24.dex */
public interface Effect {
    default long getDurationAfterEffectApplied(long durationUs) {
        return durationUs;
    }
}
