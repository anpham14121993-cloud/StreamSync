package com.streamsync.app.subtitle.autosync;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: ArvioGates.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0006J\u001e\u00105\u001a\u0002012\u0006\u00104\u001a\u00020\u00062\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u000201R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/ArvioGates;", "", "()V", "AI_CANDIDATE_LINES", "", "AI_MIN_GAIN", "", "AI_MIN_PAIRS", "AI_MIN_PAIR_FRACTION", "AI_OUTLIER_MS", "", "AI_REFERENCE_LINES", "AI_WINDOW_MS", "BUFFERED_REF_INTERVALS", "CORROBORATED_ACCEPT", "CORROBORATE_FLOOR", "EARLY_ACCEPT_REFS", "EARLY_ACCEPT_SCORE", "EARLY_REFS", "EMBEDDED_WAIT_MS", "FAST_ACCEPT_REFS", "FAST_ACCEPT_SCORE", "FINAL_ACCEPT_HEARING", "FINAL_ACCEPT_TIMING_ONLY", "HARD_CAP_MS", "MAX_CANDIDATES", "MAX_SAMPLES", "MIN_BUFFERED_FOR_REJECT", "MIN_REF_SPAN_FAST_MS", "MIN_REF_SPAN_MS", "MIN_SAMPLES", "MIN_SIMILARITY", "OFFSET_ACCEPT", "OFFSET_CORROBORATE_MIN", "OFFSET_CORROBORATE_MS", "OFFSET_MAX_MS", "OFFSET_MIN_GAIN", "OFFSET_MIN_MS", "OUTER_POLL_MS", "OVERLAP_TOLERANCE_MS", "PLAYER_REF_BUDGET_MS", "PLAYER_REF_GIVE_UP_MS", "PLAYER_REF_POLL_MS", "PLAYER_REF_SWITCH_WAIT_MS", "SOURCES_WAIT_MS", "SUCCESS_THRESHOLD_HEARING", "SUCCESS_THRESHOLD_TIMING", "TOLERANCE_MS", "acceptLadder", "", "refCount", "bufferedCount", "score", "finalAccept", "offsetMs", "hasHearing", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class ArvioGates {
    public static final int $stable = 0;
    public static final int AI_CANDIDATE_LINES = 40;
    public static final double AI_MIN_GAIN = 0.01d;
    public static final int AI_MIN_PAIRS = 3;
    public static final double AI_MIN_PAIR_FRACTION = 0.25d;
    public static final long AI_OUTLIER_MS = 450;
    public static final int AI_REFERENCE_LINES = 8;
    public static final long AI_WINDOW_MS = 10000;
    public static final int BUFFERED_REF_INTERVALS = 12;
    public static final double CORROBORATED_ACCEPT = 0.68d;
    public static final double CORROBORATE_FLOOR = 0.62d;
    public static final int EARLY_ACCEPT_REFS = 4;
    public static final double EARLY_ACCEPT_SCORE = 0.8d;
    public static final int EARLY_REFS = 6;
    public static final long EMBEDDED_WAIT_MS = 8000;
    public static final int FAST_ACCEPT_REFS = 5;
    public static final double FAST_ACCEPT_SCORE = 0.85d;
    public static final double FINAL_ACCEPT_HEARING = 0.7d;
    public static final double FINAL_ACCEPT_TIMING_ONLY = 0.3d;
    public static final long HARD_CAP_MS = 90000;
    public static final ArvioGates INSTANCE = new ArvioGates();
    public static final int MAX_CANDIDATES = 10;
    public static final int MAX_SAMPLES = 12;
    public static final int MIN_BUFFERED_FOR_REJECT = 4;
    public static final long MIN_REF_SPAN_FAST_MS = 15000;
    public static final long MIN_REF_SPAN_MS = 30000;
    public static final int MIN_SAMPLES = 4;
    public static final double MIN_SIMILARITY = 0.35d;
    public static final double OFFSET_ACCEPT = 0.8d;
    public static final int OFFSET_CORROBORATE_MIN = 2;
    public static final long OFFSET_CORROBORATE_MS = 400;
    public static final long OFFSET_MAX_MS = 10000;
    public static final double OFFSET_MIN_GAIN = 0.1d;
    public static final long OFFSET_MIN_MS = 700;
    public static final long OUTER_POLL_MS = 200;
    public static final long OVERLAP_TOLERANCE_MS = 400;
    public static final long PLAYER_REF_BUDGET_MS = 20000;
    public static final long PLAYER_REF_GIVE_UP_MS = 4000;
    public static final long PLAYER_REF_POLL_MS = 500;
    public static final long PLAYER_REF_SWITCH_WAIT_MS = 6000;
    public static final long SOURCES_WAIT_MS = 15000;
    public static final double SUCCESS_THRESHOLD_HEARING = 0.3d;
    public static final double SUCCESS_THRESHOLD_TIMING = 0.7d;
    public static final long TOLERANCE_MS = 1500;

    private ArvioGates() {
    }

    public final boolean finalAccept(double score, long offsetMs, boolean hasHearing) {
        double gate = hasHearing ? 0.7d : 0.3d;
        if (score >= gate) {
            return true;
        }
        return offsetMs != 0 && score >= 0.68d;
    }

    public final boolean acceptLadder(int refCount, int bufferedCount, double score) {
        if (refCount >= 4 && score >= 0.8d) {
            return true;
        }
        if (refCount < 5 || score < 0.85d) {
            return (refCount >= 6 && bufferedCount >= 4) || score >= 0.68d;
        }
        return true;
    }
}
