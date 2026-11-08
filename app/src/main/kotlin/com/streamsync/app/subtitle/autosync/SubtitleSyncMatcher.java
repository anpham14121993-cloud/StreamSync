package com.streamsync.app.subtitle.autosync;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: SubtitleSyncMatcher.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\bÇ\u0002\u0018\u00002\u00020\u0001:\u0004>?@AB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0002JH\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00152\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\fH\u0002J8\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00152\b\b\u0002\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020\fJ$\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010#\u001a\u00020\u00072\b\b\u0002\u0010$\u001a\u00020\u0007J\u0010\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010&J(\u0010(\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010)\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00150\u00132\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u0015J:\u0010+\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00152\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\u0007J,\u00101\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00152\b\b\u0002\u00102\u001a\u00020\fJ4\u00103\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00152\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020\fH\u0002J\u001a\u00106\u001a\u00020\u00072\b\u00107\u001a\u0004\u0018\u00010&2\b\u00108\u001a\u0004\u0018\u00010&J\u0018\u00109\u001a\b\u0012\u0004\u0012\u00020&0\u00042\b\u0010'\u001a\u0004\u0018\u00010&H\u0002J0\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u00162\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020\f2\u0006\u0010<\u001a\u00020\f2\u0006\u0010=\u001a\u00020\fH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher;", "", "()V", "BIDI_MARKS", "", "", "EPSILON", "", "HEBREW_END", "HEBREW_START", "MIN_CUES", "OFFSET_COARSE_STEP_MS", "", "OFFSET_FINE_STEP_MS", "SINGLE_CUE_MAX_MS", "TIMING_PADDING_MS", "abs", "v", "considerBest", "Lkotlin/Pair;", "cues", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "refs", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$Reference;", "bestOffset", "bestScore", "candidateOffset", "estimateOffsetMatch", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$OffsetMatch;", "minOffsetMs", "maxOffsetMs", "isAccepted", "", "match", "scoreFloor", "scoreLift", "normalize", "", "input", "pickBest", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$ScoredCandidate;", "candidates", "scoreByText", "samples", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$SpokenSample;", "minJitterMs", "maxJitterMs", "threshold", "scoreByTiming", "toleranceMs", "scoreSortedShifted", "shift", "padding", "similarity", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "b", "tokens", "windowCoverage", "cue", "refStart", "refEnd", "OffsetMatch", "Reference", "ScoredCandidate", "SpokenSample", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class SubtitleSyncMatcher {
    public static final double EPSILON = 1.0E-9d;
    public static final int MIN_CUES = 3;
    public static final long OFFSET_COARSE_STEP_MS = 250;
    public static final long OFFSET_FINE_STEP_MS = 25;
    public static final long SINGLE_CUE_MAX_MS = 7000;
    public static final long TIMING_PADDING_MS = 400;
    public static final SubtitleSyncMatcher INSTANCE = new SubtitleSyncMatcher();
    private static final Set<Integer> BIDI_MARKS = SetsKt.setOf((Object[]) new Integer[]{8206, 8207, 8234, 8235, 8236, 8294, 8295, 8297});
    private static final int HEBREW_START = 1424;
    private static final int HEBREW_END = 1480;
    public static final int $stable = 8;

    private SubtitleSyncMatcher() {
    }

    /* compiled from: SubtitleSyncMatcher.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$Reference;", "", "startMs", "", "endMs", "text", "", "(JJLjava/lang/String;)V", "getEndMs", "()J", "getStartMs", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class Reference {
        public static final int $stable = 0;
        private final long endMs;
        private final long startMs;
        private final String text;

        public static /* synthetic */ Reference copy$default(Reference reference, long j, long j2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = reference.startMs;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = reference.endMs;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                str = reference.text;
            }
            return reference.copy(j3, j4, str);
        }

        /* renamed from: component1, reason: from getter */
        public final long getStartMs() {
            return this.startMs;
        }

        /* renamed from: component2, reason: from getter */
        public final long getEndMs() {
            return this.endMs;
        }

        /* renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final Reference copy(long startMs, long endMs, String text) {
            return new Reference(startMs, endMs, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Reference)) {
                return false;
            }
            Reference reference = (Reference) other;
            return this.startMs == reference.startMs && this.endMs == reference.endMs && Intrinsics.areEqual(this.text, reference.text);
        }

        public int hashCode() {
            return (((Long.hashCode(this.startMs) * 31) + Long.hashCode(this.endMs)) * 31) + (this.text == null ? 0 : this.text.hashCode());
        }

        public String toString() {
            return "Reference(startMs=" + this.startMs + ", endMs=" + this.endMs + ", text=" + this.text + ")";
        }

        public Reference(long startMs, long endMs, String text) {
            this.startMs = startMs;
            this.endMs = endMs;
            this.text = text;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ Reference(long r7, long r9, java.lang.String r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
            /*
                r6 = this;
                r12 = r12 & 4
                if (r12 == 0) goto L7
                r11 = 0
                r5 = r11
                goto L8
            L7:
                r5 = r11
            L8:
                r0 = r6
                r1 = r7
                r3 = r9
                r0.<init>(r1, r3, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher.Reference.<init>(long, long, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final long getStartMs() {
            return this.startMs;
        }

        public final long getEndMs() {
            return this.endMs;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* compiled from: SubtitleSyncMatcher.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$SpokenSample;", "", "positionMs", "", "text", "", "(JLjava/lang/String;)V", "getPositionMs", "()J", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class SpokenSample {
        public static final int $stable = 0;
        private final long positionMs;
        private final String text;

        public static /* synthetic */ SpokenSample copy$default(SpokenSample spokenSample, long j, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = spokenSample.positionMs;
            }
            if ((i & 2) != 0) {
                str = spokenSample.text;
            }
            return spokenSample.copy(j, str);
        }

        /* renamed from: component1, reason: from getter */
        public final long getPositionMs() {
            return this.positionMs;
        }

        /* renamed from: component2, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final SpokenSample copy(long positionMs, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new SpokenSample(positionMs, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpokenSample)) {
                return false;
            }
            SpokenSample spokenSample = (SpokenSample) other;
            return this.positionMs == spokenSample.positionMs && Intrinsics.areEqual(this.text, spokenSample.text);
        }

        public int hashCode() {
            return (Long.hashCode(this.positionMs) * 31) + this.text.hashCode();
        }

        public String toString() {
            return "SpokenSample(positionMs=" + this.positionMs + ", text=" + this.text + ")";
        }

        public SpokenSample(long positionMs, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.positionMs = positionMs;
            this.text = text;
        }

        public final long getPositionMs() {
            return this.positionMs;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* compiled from: SubtitleSyncMatcher.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$OffsetMatch;", "", "offsetMs", "", "correctedScore", "", "baseScore", "(JDD)V", "getBaseScore", "()D", "getCorrectedScore", "getOffsetMs", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class OffsetMatch {
        public static final int $stable = 0;
        private final double baseScore;
        private final double correctedScore;
        private final long offsetMs;

        public static /* synthetic */ OffsetMatch copy$default(OffsetMatch offsetMatch, long j, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = offsetMatch.offsetMs;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                d = offsetMatch.correctedScore;
            }
            double d3 = d;
            if ((i & 4) != 0) {
                d2 = offsetMatch.baseScore;
            }
            return offsetMatch.copy(j2, d3, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getOffsetMs() {
            return this.offsetMs;
        }

        /* renamed from: component2, reason: from getter */
        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        /* renamed from: component3, reason: from getter */
        public final double getBaseScore() {
            return this.baseScore;
        }

        public final OffsetMatch copy(long offsetMs, double correctedScore, double baseScore) {
            return new OffsetMatch(offsetMs, correctedScore, baseScore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OffsetMatch)) {
                return false;
            }
            OffsetMatch offsetMatch = (OffsetMatch) other;
            return this.offsetMs == offsetMatch.offsetMs && Double.compare(this.correctedScore, offsetMatch.correctedScore) == 0 && Double.compare(this.baseScore, offsetMatch.baseScore) == 0;
        }

        public int hashCode() {
            return (((Long.hashCode(this.offsetMs) * 31) + Double.hashCode(this.correctedScore)) * 31) + Double.hashCode(this.baseScore);
        }

        public String toString() {
            return "OffsetMatch(offsetMs=" + this.offsetMs + ", correctedScore=" + this.correctedScore + ", baseScore=" + this.baseScore + ")";
        }

        public OffsetMatch(long offsetMs, double correctedScore, double baseScore) {
            this.offsetMs = offsetMs;
            this.correctedScore = correctedScore;
            this.baseScore = baseScore;
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final double getBaseScore() {
            return this.baseScore;
        }
    }

    /* compiled from: SubtitleSyncMatcher.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003JM\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\tHÆ\u0001J\u0013\u0010#\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$ScoredCandidate;", "", "id", "", "label", "cues", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "baseScore", "", "match", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$OffsetMatch;", "correctedScore", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;DLcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$OffsetMatch;D)V", "accepted", "", "getAccepted", "()Z", "getBaseScore", "()D", "getCorrectedScore", "getCues", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "getLabel", "getMatch", "()Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$OffsetMatch;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class ScoredCandidate {
        public static final int $stable = 8;
        private final double baseScore;
        private final double correctedScore;
        private final List<SubtitleCue> cues;
        private final String id;
        private final String label;
        private final OffsetMatch match;

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final List<SubtitleCue> component3() {
            return this.cues;
        }

        /* renamed from: component4, reason: from getter */
        public final double getBaseScore() {
            return this.baseScore;
        }

        /* renamed from: component5, reason: from getter */
        public final OffsetMatch getMatch() {
            return this.match;
        }

        /* renamed from: component6, reason: from getter */
        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final ScoredCandidate copy(String id, String label, List<SubtitleCue> cues, double baseScore, OffsetMatch match, double correctedScore) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(cues, "cues");
            return new ScoredCandidate(id, label, cues, baseScore, match, correctedScore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScoredCandidate)) {
                return false;
            }
            ScoredCandidate scoredCandidate = (ScoredCandidate) other;
            return Intrinsics.areEqual(this.id, scoredCandidate.id) && Intrinsics.areEqual(this.label, scoredCandidate.label) && Intrinsics.areEqual(this.cues, scoredCandidate.cues) && Double.compare(this.baseScore, scoredCandidate.baseScore) == 0 && Intrinsics.areEqual(this.match, scoredCandidate.match) && Double.compare(this.correctedScore, scoredCandidate.correctedScore) == 0;
        }

        public int hashCode() {
            return (((((((((this.id.hashCode() * 31) + this.label.hashCode()) * 31) + this.cues.hashCode()) * 31) + Double.hashCode(this.baseScore)) * 31) + (this.match == null ? 0 : this.match.hashCode())) * 31) + Double.hashCode(this.correctedScore);
        }

        public String toString() {
            return "ScoredCandidate(id=" + this.id + ", label=" + this.label + ", cues=" + this.cues + ", baseScore=" + this.baseScore + ", match=" + this.match + ", correctedScore=" + this.correctedScore + ")";
        }

        public ScoredCandidate(String id, String label, List<SubtitleCue> cues, double baseScore, OffsetMatch match, double correctedScore) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(cues, "cues");
            this.id = id;
            this.label = label;
            this.cues = cues;
            this.baseScore = baseScore;
            this.match = match;
            this.correctedScore = correctedScore;
        }

        public final String getId() {
            return this.id;
        }

        public final String getLabel() {
            return this.label;
        }

        public final List<SubtitleCue> getCues() {
            return this.cues;
        }

        public final double getBaseScore() {
            return this.baseScore;
        }

        public final OffsetMatch getMatch() {
            return this.match;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final boolean getAccepted() {
            return SubtitleSyncMatcher.isAccepted$default(SubtitleSyncMatcher.INSTANCE, this.match, 0.0d, 0.0d, 6, null);
        }
    }

    public final String normalize(String input) {
        StringBuilder out = new StringBuilder();
        boolean pendingSpace = false;
        String str = input == null ? "" : input;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (!(ch <= HEBREW_END && HEBREW_START <= ch) && !BIDI_MARKS.contains(Integer.valueOf(ch))) {
                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    if (pendingSpace) {
                        if (out.length() > 0) {
                            out.append(' ');
                        }
                    }
                    pendingSpace = false;
                    out.append(Character.toLowerCase(ch));
                } else {
                    pendingSpace = true;
                }
            }
        }
        String sb = out.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    private final Set<String> tokens(String input) {
        String normalized = normalize(input);
        if (normalized.length() == 0) {
            return SetsKt.emptySet();
        }
        if (!StringsKt.contains$default((CharSequence) normalized, ' ', false, 2, (Object) null)) {
            if (normalized.length() == 1) {
                return SetsKt.setOf(normalized);
            }
            Iterable $this$mapTo$iv = RangesKt.until(0, normalized.length() - 1);
            Collection destination$iv = new LinkedHashSet();
            Iterator<Integer> it = $this$mapTo$iv.iterator();
            while (it.hasNext()) {
                int item$iv = ((IntIterator) it).nextInt();
                String substring = normalized.substring(item$iv, item$iv + 2);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                destination$iv.add(substring);
            }
            return (Set) destination$iv;
        }
        Iterable $this$filter$iv = StringsKt.split$default((CharSequence) normalized, new char[]{' '}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it2 = (String) element$iv$iv;
            String it3 = it2.length() >= 2 ? 1 : null;
            if (it3 != null) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (Set) CollectionsKt.toCollection((List) destination$iv$iv, new LinkedHashSet());
    }

    public final double similarity(String a, String b) {
        Set ta = tokens(a);
        Set tb = tokens(b);
        if (ta.isEmpty() || tb.isEmpty()) {
            return 0.0d;
        }
        Pair pair = ta.size() <= tb.size() ? TuplesKt.to(ta, tb) : TuplesKt.to(tb, ta);
        Set<String> small = (Set) pair.component1();
        Set large = (Set) pair.component2();
        int inter = 0;
        for (String t : small) {
            if (large.contains(t)) {
                inter++;
            }
        }
        return inter / Math.min(ta.size(), tb.size());
    }

    private final long windowCoverage(SubtitleCue cue, long shift, long padding, long refStart, long refEnd) {
        long winStart = (cue.getStartMs() + shift) - padding;
        long winEnd = cue.getEndMs() + shift + padding;
        return Math.max(0L, Math.min(winEnd, refEnd) - Math.max(winStart, refStart));
    }

    private final double scoreSortedShifted(List<SubtitleCue> cues, List<Reference> refs, long shift, long padding) {
        long best;
        List sorted;
        Iterator<Reference> it;
        long duration;
        long covered;
        Iterator it2;
        if (cues.isEmpty() || refs.isEmpty()) {
            return 0.0d;
        }
        List<SubtitleCue> $this$sortedBy$iv = cues;
        List sorted2 = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher$scoreSortedShifted$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it3 = (SubtitleCue) t;
                SubtitleCue it4 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it3.getStartMs()), Long.valueOf(it4.getStartMs()));
            }
        });
        Iterator<Reference> it3 = refs.iterator();
        double acc = 0.0d;
        while (it3.hasNext()) {
            Reference ref = it3.next();
            long refStart = ref.getStartMs();
            long refEnd = ref.getEndMs();
            long duration2 = Math.max(1L, refEnd - refStart);
            if (duration2 > SINGLE_CUE_MAX_MS) {
                covered = 0;
                Iterator it4 = sorted2.iterator();
                while (it4.hasNext()) {
                    SubtitleCue cue = (SubtitleCue) it4.next();
                    if ((cue.getStartMs() + shift) - padding >= refEnd) {
                        break;
                    }
                    if (cue.getEndMs() + shift <= refStart) {
                        it2 = it4;
                    } else {
                        long from = Math.max(refStart, (cue.getStartMs() + shift) - padding);
                        it2 = it4;
                        long to = Math.min(refEnd, cue.getEndMs() + shift + padding);
                        if (to > from) {
                            covered += to - from;
                            it4 = it2;
                        }
                    }
                    it4 = it2;
                }
                sorted = sorted2;
                it = it3;
                duration = duration2;
            } else {
                Iterator it5 = sorted2.iterator();
                long best2 = 0;
                while (true) {
                    if (!it5.hasNext()) {
                        best = best2;
                        sorted = sorted2;
                        it = it3;
                        duration = duration2;
                        break;
                    }
                    SubtitleCue cue2 = (SubtitleCue) it5.next();
                    if ((cue2.getStartMs() + shift) - padding >= refEnd) {
                        sorted = sorted2;
                        it = it3;
                        best = best2;
                        duration = duration2;
                        break;
                    }
                    if (cue2.getEndMs() + shift + padding > refStart) {
                        long refEnd2 = refEnd;
                        long hit = windowCoverage(cue2, shift, padding, refStart, refEnd2);
                        best2 = Math.max(best2, hit);
                        duration2 = duration2;
                        it5 = it5;
                        sorted2 = sorted2;
                        it3 = it3;
                        refEnd = refEnd2;
                        refStart = refStart;
                    } else {
                        it5 = it5;
                        sorted2 = sorted2;
                        it3 = it3;
                    }
                }
                covered = best;
            }
            acc += Math.min(Math.max(covered / duration, 0.0d), 1.0d);
            sorted2 = sorted;
            it3 = it;
        }
        return acc / refs.size();
    }

    public static /* synthetic */ double scoreByTiming$default(SubtitleSyncMatcher subtitleSyncMatcher, List list, List list2, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 400;
        }
        return subtitleSyncMatcher.scoreByTiming(list, list2, j);
    }

    public final double scoreByTiming(List<SubtitleCue> cues, List<Reference> refs, long toleranceMs) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(refs, "refs");
        return scoreSortedShifted(cues, refs, 0L, toleranceMs);
    }

    public final double scoreByText(List<SubtitleCue> cues, List<SpokenSample> samples, long minJitterMs, long maxJitterMs, double threshold) {
        Iterator<SpokenSample> it;
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(samples, "samples");
        if (!cues.isEmpty() && !samples.isEmpty()) {
            int matched = 0;
            Iterator<SpokenSample> it2 = samples.iterator();
            while (it2.hasNext()) {
                SpokenSample sample = it2.next();
                long center = sample.getPositionMs() - minJitterMs;
                long from = center - maxJitterMs;
                long to = center + maxJitterMs;
                double best = 0.0d;
                Iterator<SubtitleCue> it3 = cues.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        it = it2;
                        break;
                    }
                    SubtitleCue cue = it3.next();
                    if (cue.getEndMs() >= from) {
                        if (cue.getStartMs() > to) {
                            it = it2;
                            break;
                        }
                        best = Math.max(best, similarity(sample.getText(), cue.getOriginalText()));
                        it2 = it2;
                        sample = sample;
                    }
                }
                if (best >= threshold) {
                    matched++;
                    it2 = it;
                } else {
                    it2 = it;
                }
            }
            return matched / samples.size();
        }
        return 0.0d;
    }

    private final Pair<Long, Double> considerBest(List<SubtitleCue> cues, List<Reference> refs, long bestOffset, double bestScore, long candidateOffset) {
        double s = scoreSortedShifted(cues, refs, candidateOffset, 0L);
        if (s > bestScore + 1.0E-9d) {
            return TuplesKt.to(Long.valueOf(candidateOffset), Double.valueOf(s));
        }
        if (s > bestScore - 1.0E-9d && abs(candidateOffset) < abs(bestOffset)) {
            return TuplesKt.to(Long.valueOf(candidateOffset), Double.valueOf(bestScore));
        }
        return TuplesKt.to(Long.valueOf(bestOffset), Double.valueOf(bestScore));
    }

    public static /* synthetic */ OffsetMatch estimateOffsetMatch$default(SubtitleSyncMatcher subtitleSyncMatcher, List list, List list2, long j, long j2, int i, Object obj) {
        long j3;
        long j4;
        if ((i & 4) == 0) {
            j3 = j;
        } else {
            j3 = 700;
        }
        if ((i & 8) == 0) {
            j4 = j2;
        } else {
            j4 = 10000;
        }
        return subtitleSyncMatcher.estimateOffsetMatch(list, list2, j3, j4);
    }

    public final OffsetMatch estimateOffsetMatch(List<SubtitleCue> cues, List<Reference> refs, long minOffsetMs, long maxOffsetMs) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(refs, "refs");
        if (cues.size() >= 3 && refs.size() >= 4) {
            double baseScore = scoreSortedShifted(cues, refs, 0L, 0L);
            long bestOffset = 0;
            double bestScore = baseScore;
            for (long off = -maxOffsetMs; off <= maxOffsetMs; off += 250) {
                Pair r = considerBest(cues, refs, bestOffset, bestScore, off);
                bestOffset = r.getFirst().longValue();
                bestScore = r.getSecond().doubleValue();
            }
            long from = bestOffset - 250;
            long to = bestOffset + 250;
            long bestOffset2 = bestOffset;
            double bestScore2 = bestScore;
            for (long fine = from; fine <= to; fine += 25) {
                long bestOffset3 = fine;
                Pair r2 = considerBest(cues, refs, bestOffset2, bestScore2, bestOffset3);
                bestOffset2 = r2.getFirst().longValue();
                bestScore2 = r2.getSecond().doubleValue();
            }
            long bestOffset4 = bestOffset2;
            if (abs(bestOffset4) < minOffsetMs) {
                return null;
            }
            return new OffsetMatch(bestOffset4, bestScore2, baseScore);
        }
        return null;
    }

    public static /* synthetic */ boolean isAccepted$default(SubtitleSyncMatcher subtitleSyncMatcher, OffsetMatch offsetMatch, double d, double d2, int i, Object obj) {
        double d3;
        double d4;
        if ((i & 2) == 0) {
            d3 = d;
        } else {
            d3 = 0.62d;
        }
        if ((i & 4) == 0) {
            d4 = d2;
        } else {
            d4 = 0.1d;
        }
        return subtitleSyncMatcher.isAccepted(offsetMatch, d3, d4);
    }

    public final boolean isAccepted(OffsetMatch match, double scoreFloor, double scoreLift) {
        if (match == null || match.getCorrectedScore() < scoreFloor || match.getCorrectedScore() < match.getBaseScore() + scoreLift) {
            return false;
        }
        return true;
    }

    public final Pair<ScoredCandidate, List<ScoredCandidate>> pickBest(List<ScoredCandidate> candidates) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        ScoredCandidate best = null;
        for (ScoredCandidate entry : candidates) {
            if (entry.getAccepted()) {
                if (best == null || entry.getCorrectedScore() > best.getCorrectedScore()) {
                    best = entry;
                }
            } else if (best == null && entry.getCorrectedScore() > 0.0d) {
                best = entry;
            }
        }
        List<ScoredCandidate> $this$sortedByDescending$iv = candidates;
        return TuplesKt.to(best, CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher$pickBest$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleSyncMatcher.ScoredCandidate it = (SubtitleSyncMatcher.ScoredCandidate) t2;
                SubtitleSyncMatcher.ScoredCandidate it2 = (SubtitleSyncMatcher.ScoredCandidate) t;
                return ComparisonsKt.compareValues(Double.valueOf(it.getCorrectedScore()), Double.valueOf(it2.getCorrectedScore()));
            }
        }));
    }

    private final long abs(long v) {
        return v < 0 ? -v : v;
    }
}
