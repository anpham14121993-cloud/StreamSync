package com.streamsync.app.subtitle.autosync;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: SubtitleOffsetSync.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\f\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \bÇ\u0002\u0018\u00002\u00020\u0001:\bvwxyz{|}B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0006J$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001b2\b\b\u0002\u0010\"\u001a\u00020\fJ\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\fH\u0002Jd\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0018\u0010)\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020&0,2\u0006\u0010-\u001a\u00020.2\u001e\b\u0002\u0010/\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020100\u0012\u0006\u0012\u0004\u0018\u00010\u00010*H\u0086@¢\u0006\u0002\u00102JH\u00103\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0018\u0001042\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\f0\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0006J\"\u00106\u001a\u00020\b2\f\u00107\u001a\b\u0012\u0004\u0012\u00020&082\f\u00109\u001a\b\u0012\u0004\u0012\u00020&08J\u000e\u0010:\u001a\u00020&2\u0006\u0010;\u001a\u00020&J0\u0010<\u001a\u00020&2\u0006\u00109\u001a\u00020&2\u0006\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0006H\u0002J8\u0010<\u001a\u00020&2\u0006\u00107\u001a\u00020&2\u0006\u00109\u001a\u00020&2\u0006\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0006H\u0002J\u000e\u0010B\u001a\u00020&2\u0006\u0010C\u001a\u00020\fJ\u000e\u0010D\u001a\u00020&2\u0006\u0010'\u001a\u00020\fJ\u0018\u0010E\u001a\u00020&2\u0006\u0010>\u001a\u00020\f2\u0006\u0010A\u001a\u00020\u0006H\u0002J\u000e\u0010F\u001a\u00020.2\u0006\u0010;\u001a\u00020&J\"\u0010G\u001a\u00020.2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u000e\u0010I\u001a\u00020.2\u0006\u0010;\u001a\u00020&J$\u0010J\u001a\u00020\u00062\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0K0\u001b2\u0006\u0010'\u001a\u00020\fH\u0002J0\u0010L\u001a\u00020M2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020O0\u001bJ\u000e\u0010P\u001a\u00020&2\u0006\u0010;\u001a\u00020&J\u000e\u0010Q\u001a\u00020\f2\u0006\u0010R\u001a\u00020&J\u0010\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020\bH\u0002J<\u0010U\u001a\u00020V2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010W\u001a\u00020\f2\u0006\u0010X\u001a\u00020\b2\b\u0010Y\u001a\u0004\u0018\u00010MJ\u001c\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J.\u0010[\u001a\u0004\u0018\u00010\\2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u0006J8\u0010]\u001a\b\u0012\u0004\u0012\u00020^0\u001b2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b2\u0012\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001b2\b\b\u0002\u0010a\u001a\u00020\fJ@\u0010b\u001a\b\u0012\u0004\u0012\u00020^0\u001b2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001b2\u0012\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001b2\u0006\u0010a\u001a\u00020\f2\u0006\u0010d\u001a\u00020.H\u0002J*\u0010e\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010C\u001a\u00020\f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ*\u0010f\u001a\u00020\b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010C\u001a\u00020\f2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001bJB\u0010g\u001a\b\u0012\u0004\u0012\u00020^0\u001b2\u0012\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0002\u0010a\u001a\u00020\f2\b\b\u0002\u0010d\u001a\u00020.J<\u0010h\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0018\u0001042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\"\u0010i\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010C\u001a\u00020\fJ\u0016\u0010j\u001a\u00020&2\u0006\u0010k\u001a\u00020&2\u0006\u0010C\u001a\u00020\fJ\u0016\u0010l\u001a\u00020&2\u0006\u0010R\u001a\u00020&2\u0006\u0010C\u001a\u00020\fJ\u0014\u0010m\u001a\u00020&2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0014\u0010n\u001a\b\u0012\u0004\u0012\u00020&082\u0006\u0010o\u001a\u00020&J$\u0010p\u001a\u00020\u00062\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0K0\u001b2\u0006\u0010'\u001a\u00020\fH\u0002J(\u0010q\u001a\b\u0012\u0004\u0012\u00020&0\u001b2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0019\u0010t\u001a\u0004\u0018\u00010\f*\b\u0012\u0004\u0012\u00020\f0\u001bH\u0002¢\u0006\u0002\u0010uR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006~"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync;", "", "()V", "HTML_TAG_REGEX", "Lkotlin/text/Regex;", "MATCH_AI_CANDIDATE_LINES", "", "MATCH_AI_MIN_GAIN", "", "MATCH_AI_MIN_PAIRS", "MATCH_AI_MIN_PAIR_FRACTION", "MATCH_AI_OUTLIER_MS", "", "MATCH_AI_REFERENCE_LINES", "MATCH_AI_WINDOW_MS", "MATCH_FAST_ACCEPT_SPAN_MS", "MATCH_MIN_REF_SPAN_MS", "MATCH_OFFSET_MAX_MS", "MATCH_OFFSET_MIN_MS", "MIN_DURATION_MS", "MULTI_SPACE_REGEX", "SDH_ONLY_REGEX", "SPEECH_GAP_MS", "TIME_LINE", "alignDiagnostics", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AlignDiagnostics;", "referenceCues", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "bufferedCount", "buildSpokenReferences", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$Reference;", "words", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "gapMs", "clampCues", "cues", "clock", "", TranslateLanguage.MALAY, "collectReferenceCues", "provider", "Lkotlin/Function1;", "seenTexts", "", "allowNonLatin", "", "onPoll", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Ljava/util/Set;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "escalate", "Lkotlin/Pair;", "offsets", "f1", CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY, "", "b", "foldForMatch", "text", "format3", "c", "milli", "sep", "", "digits", "formatSigned", "offsetMs", "formatTimestamp", "frac", "isMostlyNonLatin", "isSameContent", "candidateCues", "isSdhOnly", "lowerBound", "Lkotlin/collections/IndexedValue;", "measureOffsetWithAi", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AiLineSync;", "matches", "Lcom/streamsync/app/subtitle/autosync/LineMatch;", "normalizeForCompare", "parseTimestampMs", "token", "pct", "v", "reconcileWithAi", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$Reconciled;", "timingOffsetMs", "timingScore", "ai", "referenceRefs", "scoreAgainstBuiltIn", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$BuiltInScore;", "scoreAgainstHearing", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$ScoredCue;", "references", "candidates", "tol", "scoreAgainstRefs", "refs", "allowOffsets", "scoreAt", "scoreAtRefs", "scoreCandidatesWithOffsets", "selectAiCues", "shiftCues", "shiftTimestamps", "raw", "shiftToken", "toSrt", "tokens", "folded", "upperBound", "validate", "original", "shifted", "median", "(Ljava/util/List;)Ljava/lang/Long;", "AiLineSync", "AiVerdict", "AlignDiagnostics", "BuiltInScore", "OffsetDecision", "PlayerRef", "Reconciled", "ScoredCue", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class SubtitleOffsetSync {
    public static final int MATCH_AI_CANDIDATE_LINES = 40;
    public static final double MATCH_AI_MIN_GAIN = 0.01d;
    public static final int MATCH_AI_MIN_PAIRS = 3;
    public static final double MATCH_AI_MIN_PAIR_FRACTION = 0.25d;
    public static final long MATCH_AI_OUTLIER_MS = 450;
    public static final int MATCH_AI_REFERENCE_LINES = 8;
    public static final long MATCH_AI_WINDOW_MS = 10000;
    public static final long MATCH_FAST_ACCEPT_SPAN_MS = 15000;
    public static final long MATCH_MIN_REF_SPAN_MS = 30000;
    public static final long MATCH_OFFSET_MAX_MS = 10000;
    public static final long MATCH_OFFSET_MIN_MS = 700;
    public static final long MIN_DURATION_MS = 100;
    public static final long SPEECH_GAP_MS = 600;
    public static final SubtitleOffsetSync INSTANCE = new SubtitleOffsetSync();
    private static final Regex HTML_TAG_REGEX = new Regex("<[^>]*>");
    private static final Regex MULTI_SPACE_REGEX = new Regex("\\s+");
    private static final Regex SDH_ONLY_REGEX = new Regex("^[\\s♪♫]*(\\[[^]]*]|\\([^)]*\\)|[-–—\\s])*[\\s♪♫]*$");
    private static final Regex TIME_LINE = new Regex("(\\d{1,2}:\\d{2}:\\d{2}[.,]\\d{1,3}|\\d{1,2}:\\d{2}[.,]\\d{1,3}|\\d{1,3}[.,]\\d{1,3})(\\s*-->\\s*)(\\d{1,2}:\\d{2}:\\d{2}[.,]\\d{1,3}|\\d{1,2}:\\d{2}[.,]\\d{1,3}|\\d{1,3}[.,]\\d{1,3})");
    public static final int $stable = 8;

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AiVerdict;", "", "(Ljava/lang/String;I)V", "CONFIRMED", "UNCONFIRMED", "REJECTED", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public enum AiVerdict {
        CONFIRMED,
        UNCONFIRMED,
        REJECTED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<AiVerdict> getEntries() {
            return $ENTRIES;
        }
    }

    private SubtitleOffsetSync() {
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b*\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f¢\u0006\u0002\u0010\u0012J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u000eHÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\t\u00101\u001a\u00020\u000eHÆ\u0003J~\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u000e2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\bHÖ\u0001J\t\u00107\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0010\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0014¨\u00068"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$OffsetDecision;", "", "offsetMs", "", "baseScore", "", "correctedScore", "textPairs", "", "consensusMs", "bestTextScore", "heardPreview", "", "textVerified", "", "offsetFromTiming", "accepted", "reason", "(JDDILjava/lang/Long;DLjava/lang/String;ZZZLjava/lang/String;)V", "getAccepted", "()Z", "getBaseScore", "()D", "getBestTextScore", "getConsensusMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "correctedPercent", "getCorrectedPercent", "()I", "getCorrectedScore", "getHeardPreview", "()Ljava/lang/String;", "getOffsetFromTiming", "getOffsetMs", "()J", "getReason", "getTextPairs", "getTextVerified", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JDDILjava/lang/Long;DLjava/lang/String;ZZZLjava/lang/String;)Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$OffsetDecision;", "equals", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class OffsetDecision {
        public static final int $stable = 0;
        private final boolean accepted;
        private final double baseScore;
        private final double bestTextScore;
        private final Long consensusMs;
        private final double correctedScore;
        private final String heardPreview;
        private final boolean offsetFromTiming;
        private final long offsetMs;
        private final String reason;
        private final int textPairs;
        private final boolean textVerified;

        /* renamed from: component1, reason: from getter */
        public final long getOffsetMs() {
            return this.offsetMs;
        }

        /* renamed from: component10, reason: from getter */
        public final boolean getAccepted() {
            return this.accepted;
        }

        /* renamed from: component11, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        /* renamed from: component2, reason: from getter */
        public final double getBaseScore() {
            return this.baseScore;
        }

        /* renamed from: component3, reason: from getter */
        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        /* renamed from: component4, reason: from getter */
        public final int getTextPairs() {
            return this.textPairs;
        }

        /* renamed from: component5, reason: from getter */
        public final Long getConsensusMs() {
            return this.consensusMs;
        }

        /* renamed from: component6, reason: from getter */
        public final double getBestTextScore() {
            return this.bestTextScore;
        }

        /* renamed from: component7, reason: from getter */
        public final String getHeardPreview() {
            return this.heardPreview;
        }

        /* renamed from: component8, reason: from getter */
        public final boolean getTextVerified() {
            return this.textVerified;
        }

        /* renamed from: component9, reason: from getter */
        public final boolean getOffsetFromTiming() {
            return this.offsetFromTiming;
        }

        public final OffsetDecision copy(long offsetMs, double baseScore, double correctedScore, int textPairs, Long consensusMs, double bestTextScore, String heardPreview, boolean textVerified, boolean offsetFromTiming, boolean accepted, String reason) {
            Intrinsics.checkNotNullParameter(heardPreview, "heardPreview");
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new OffsetDecision(offsetMs, baseScore, correctedScore, textPairs, consensusMs, bestTextScore, heardPreview, textVerified, offsetFromTiming, accepted, reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OffsetDecision)) {
                return false;
            }
            OffsetDecision offsetDecision = (OffsetDecision) other;
            return this.offsetMs == offsetDecision.offsetMs && Double.compare(this.baseScore, offsetDecision.baseScore) == 0 && Double.compare(this.correctedScore, offsetDecision.correctedScore) == 0 && this.textPairs == offsetDecision.textPairs && Intrinsics.areEqual(this.consensusMs, offsetDecision.consensusMs) && Double.compare(this.bestTextScore, offsetDecision.bestTextScore) == 0 && Intrinsics.areEqual(this.heardPreview, offsetDecision.heardPreview) && this.textVerified == offsetDecision.textVerified && this.offsetFromTiming == offsetDecision.offsetFromTiming && this.accepted == offsetDecision.accepted && Intrinsics.areEqual(this.reason, offsetDecision.reason);
        }

        public int hashCode() {
            return (((((((((((((((((((Long.hashCode(this.offsetMs) * 31) + Double.hashCode(this.baseScore)) * 31) + Double.hashCode(this.correctedScore)) * 31) + Integer.hashCode(this.textPairs)) * 31) + (this.consensusMs == null ? 0 : this.consensusMs.hashCode())) * 31) + Double.hashCode(this.bestTextScore)) * 31) + this.heardPreview.hashCode()) * 31) + Boolean.hashCode(this.textVerified)) * 31) + Boolean.hashCode(this.offsetFromTiming)) * 31) + Boolean.hashCode(this.accepted)) * 31) + this.reason.hashCode();
        }

        public String toString() {
            return "OffsetDecision(offsetMs=" + this.offsetMs + ", baseScore=" + this.baseScore + ", correctedScore=" + this.correctedScore + ", textPairs=" + this.textPairs + ", consensusMs=" + this.consensusMs + ", bestTextScore=" + this.bestTextScore + ", heardPreview=" + this.heardPreview + ", textVerified=" + this.textVerified + ", offsetFromTiming=" + this.offsetFromTiming + ", accepted=" + this.accepted + ", reason=" + this.reason + ")";
        }

        public OffsetDecision(long offsetMs, double baseScore, double correctedScore, int textPairs, Long consensusMs, double bestTextScore, String heardPreview, boolean textVerified, boolean offsetFromTiming, boolean accepted, String reason) {
            Intrinsics.checkNotNullParameter(heardPreview, "heardPreview");
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.offsetMs = offsetMs;
            this.baseScore = baseScore;
            this.correctedScore = correctedScore;
            this.textPairs = textPairs;
            this.consensusMs = consensusMs;
            this.bestTextScore = bestTextScore;
            this.heardPreview = heardPreview;
            this.textVerified = textVerified;
            this.offsetFromTiming = offsetFromTiming;
            this.accepted = accepted;
            this.reason = reason;
        }

        public /* synthetic */ OffsetDecision(long j, double d, double d2, int i, Long l, double d3, String str, boolean z, boolean z2, boolean z3, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, d, d2, i, l, (i2 & 32) != 0 ? 0.0d : d3, (i2 & 64) != 0 ? "" : str, (i2 & 128) != 0 ? true : z, (i2 & 256) != 0 ? false : z2, z3, (i2 & 1024) != 0 ? "" : str2);
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public final double getBaseScore() {
            return this.baseScore;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final int getTextPairs() {
            return this.textPairs;
        }

        public final Long getConsensusMs() {
            return this.consensusMs;
        }

        public final double getBestTextScore() {
            return this.bestTextScore;
        }

        public final String getHeardPreview() {
            return this.heardPreview;
        }

        public final boolean getTextVerified() {
            return this.textVerified;
        }

        public final boolean getOffsetFromTiming() {
            return this.offsetFromTiming;
        }

        public final boolean getAccepted() {
            return this.accepted;
        }

        public final String getReason() {
            return this.reason;
        }

        public final int getCorrectedPercent() {
            return (int) (this.correctedScore * 100);
        }
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J:\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0013\u0010\u000e¨\u0006!"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AiLineSync;", "", "pairs", "", "offsetMs", "", "unfixableShiftMs", "sent", "(ILjava/lang/Long;Ljava/lang/Long;I)V", "notThisDialogue", "", "getNotThisDialogue", "()Z", "getOffsetMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPairs", "()I", "getSent", "getUnfixableShiftMs", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/Long;Ljava/lang/Long;I)Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AiLineSync;", "equals", "other", "hashCode", "toString", "", "verdict", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AiVerdict;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class AiLineSync {
        public static final int $stable = 0;
        private final Long offsetMs;
        private final int pairs;
        private final int sent;
        private final Long unfixableShiftMs;

        public static /* synthetic */ AiLineSync copy$default(AiLineSync aiLineSync, int i, Long l, Long l2, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = aiLineSync.pairs;
            }
            if ((i3 & 2) != 0) {
                l = aiLineSync.offsetMs;
            }
            if ((i3 & 4) != 0) {
                l2 = aiLineSync.unfixableShiftMs;
            }
            if ((i3 & 8) != 0) {
                i2 = aiLineSync.sent;
            }
            return aiLineSync.copy(i, l, l2, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getPairs() {
            return this.pairs;
        }

        /* renamed from: component2, reason: from getter */
        public final Long getOffsetMs() {
            return this.offsetMs;
        }

        /* renamed from: component3, reason: from getter */
        public final Long getUnfixableShiftMs() {
            return this.unfixableShiftMs;
        }

        /* renamed from: component4, reason: from getter */
        public final int getSent() {
            return this.sent;
        }

        public final AiLineSync copy(int pairs, Long offsetMs, Long unfixableShiftMs, int sent) {
            return new AiLineSync(pairs, offsetMs, unfixableShiftMs, sent);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AiLineSync)) {
                return false;
            }
            AiLineSync aiLineSync = (AiLineSync) other;
            return this.pairs == aiLineSync.pairs && Intrinsics.areEqual(this.offsetMs, aiLineSync.offsetMs) && Intrinsics.areEqual(this.unfixableShiftMs, aiLineSync.unfixableShiftMs) && this.sent == aiLineSync.sent;
        }

        public int hashCode() {
            return (((((Integer.hashCode(this.pairs) * 31) + (this.offsetMs == null ? 0 : this.offsetMs.hashCode())) * 31) + (this.unfixableShiftMs != null ? this.unfixableShiftMs.hashCode() : 0)) * 31) + Integer.hashCode(this.sent);
        }

        public String toString() {
            return "AiLineSync(pairs=" + this.pairs + ", offsetMs=" + this.offsetMs + ", unfixableShiftMs=" + this.unfixableShiftMs + ", sent=" + this.sent + ")";
        }

        public AiLineSync(int pairs, Long offsetMs, Long unfixableShiftMs, int sent) {
            this.pairs = pairs;
            this.offsetMs = offsetMs;
            this.unfixableShiftMs = unfixableShiftMs;
            this.sent = sent;
        }

        public /* synthetic */ AiLineSync(int i, Long l, Long l2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? null : l, (i3 & 4) != 0 ? null : l2, i2);
        }

        public final int getPairs() {
            return this.pairs;
        }

        public final Long getOffsetMs() {
            return this.offsetMs;
        }

        public final Long getUnfixableShiftMs() {
            return this.unfixableShiftMs;
        }

        public final int getSent() {
            return this.sent;
        }

        public final boolean getNotThisDialogue() {
            return ((double) this.pairs) < ((double) this.sent) * 0.25d;
        }

        public final AiVerdict verdict() {
            if (!getNotThisDialogue() && this.unfixableShiftMs == null) {
                return this.offsetMs == null ? AiVerdict.UNCONFIRMED : AiVerdict.CONFIRMED;
            }
            return AiVerdict.REJECTED;
        }
    }

    public final boolean isMostlyNonLatin(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        int latin = 0;
        int other = 0;
        int length = text.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                if (ch < 592) {
                    latin++;
                } else {
                    if (!(7680 <= ch && ch < 7936)) {
                        other++;
                    }
                }
            }
            i++;
        }
        return other > latin;
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$PlayerRef;", "", "()V", "BUDGET_MS", "", "BUFFERED_REF_INTERVALS", "", "GIVE_UP_MS", "POLL_MS", "SWITCH_WAIT_MS", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final class PlayerRef {
        public static final int $stable = 0;
        public static final long BUDGET_MS = 20000;
        public static final int BUFFERED_REF_INTERVALS = 12;
        public static final long GIVE_UP_MS = 4000;
        public static final PlayerRef INSTANCE = new PlayerRef();
        public static final long POLL_MS = 500;
        public static final long SWITCH_WAIT_MS = 6000;

        private PlayerRef() {
        }
    }

    public static /* synthetic */ Object collectReferenceCues$default(SubtitleOffsetSync subtitleOffsetSync, Function1 function1, Set set, boolean z, Function1 function12, Continuation continuation, int i, Object obj) {
        Function1 function13;
        if ((i & 8) == 0) {
            function13 = function12;
        } else {
            function13 = new SubtitleOffsetSync$collectReferenceCues$2(null);
        }
        return subtitleOffsetSync.collectReferenceCues(function1, set, z, function13, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0029. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:109:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x024d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0265  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:75:0x024e -> B:12:0x025d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collectReferenceCues(kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.util.List<com.streamsync.app.data.model.SubtitleCue>> r27, java.util.Set<java.lang.String> r28, boolean r29, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r30, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.data.model.SubtitleCue>> r31) {
        /*
            Method dump skipped, instructions count: 706
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.collectReferenceCues(kotlin.jvm.functions.Function1, java.util.Set, boolean, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String normalizeForCompare(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.trim((CharSequence) MULTI_SPACE_REGEX.replace(HTML_TAG_REGEX.replace(text, " "), " ")).toString();
    }

    public final boolean isSdhOnly(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return SDH_ONLY_REGEX.matches(HTML_TAG_REGEX.replace(text, " "));
    }

    public final Pair<List<SubtitleCue>, List<SubtitleCue>> selectAiCues(List<SubtitleCue> referenceCues, List<SubtitleCue> candidateCues) {
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Intrinsics.checkNotNullParameter(candidateCues, "candidateCues");
        List<SubtitleCue> $this$filter$iv = referenceCues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SubtitleCue it = (SubtitleCue) element$iv$iv;
            if ((StringsKt.isBlank(it.getOriginalText()) ^ true) && !INSTANCE.isSdhOnly(it.getOriginalText())) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List reference = CollectionsKt.take((List) destination$iv$iv, 8);
        if (reference.size() < 3) {
            return null;
        }
        Iterator it2 = reference.iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        SubtitleCue it3 = (SubtitleCue) it2.next();
        long startMs = it3.getStartMs();
        while (it2.hasNext()) {
            SubtitleCue it4 = (SubtitleCue) it2.next();
            long startMs2 = it4.getStartMs();
            if (startMs > startMs2) {
                startMs = startMs2;
            }
        }
        long windowStart = startMs - 10000;
        Iterator it5 = reference.iterator();
        if (!it5.hasNext()) {
            throw new NoSuchElementException();
        }
        SubtitleCue it6 = (SubtitleCue) it5.next();
        long endMs = it6.getEndMs();
        while (it5.hasNext()) {
            SubtitleCue it7 = (SubtitleCue) it5.next();
            long endMs2 = it7.getEndMs();
            if (endMs < endMs2) {
                endMs = endMs2;
            }
        }
        long windowEnd = endMs + 10000;
        List<SubtitleCue> $this$filter$iv2 = candidateCues;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            SubtitleCue it8 = (SubtitleCue) element$iv$iv2;
            SubtitleCue it9 = (it8.getEndMs() < windowStart || it8.getStartMs() > windowEnd) ? null : 1;
            if (it9 != null) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        List candidate = CollectionsKt.take((List) destination$iv$iv2, 40);
        if (candidate.size() < 3) {
            return null;
        }
        return TuplesKt.to(reference, candidate);
    }

    public final AiLineSync measureOffsetWithAi(List<SubtitleCue> referenceCues, List<SubtitleCue> candidateCues, List<LineMatch> matches) {
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Intrinsics.checkNotNullParameter(candidateCues, "candidateCues");
        Intrinsics.checkNotNullParameter(matches, "matches");
        List<LineMatch> $this$map$iv = matches;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            LineMatch it = (LineMatch) item$iv$iv;
            destination$iv$iv.add(Long.valueOf(referenceCues.get(it.getReferenceIndex()).getStartMs() - candidateCues.get(it.getCandidateIndex()).getStartMs()));
        }
        List offsets = CollectionsKt.sorted((List) destination$iv$iv);
        if (offsets.size() < 3) {
            return new AiLineSync(offsets.size(), null, null, referenceCues.size());
        }
        long median = ((Number) offsets.get(offsets.size() / 2)).longValue();
        List $this$filter$iv = offsets;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it2 = $this$filter$iv.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object element$iv$iv = it2.next();
            if (Math.abs(((Number) element$iv$iv).longValue() - median) <= 450) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List inliers = (List) destination$iv$iv2;
        if (inliers.size() >= 3) {
            long mean = (long) (CollectionsKt.sumOfLong(inliers) / inliers.size());
            if (Math.abs(mean) > 10000) {
                return new AiLineSync(inliers.size(), null, Long.valueOf(mean), 0);
            }
            return new AiLineSync(inliers.size(), Long.valueOf(mean), null, 0);
        }
        return new AiLineSync(offsets.size(), null, null, 0);
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$Reconciled;", "", "offsetMs", "", "correctedScore", "", "confirmedByAi", "", "replacedTiming", "(JDZZ)V", "getConfirmedByAi", "()Z", "getCorrectedScore", "()D", "getOffsetMs", "()J", "getReplacedTiming", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class Reconciled {
        public static final int $stable = 0;
        private final boolean confirmedByAi;
        private final double correctedScore;
        private final long offsetMs;
        private final boolean replacedTiming;

        public static /* synthetic */ Reconciled copy$default(Reconciled reconciled, long j, double d, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = reconciled.offsetMs;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                d = reconciled.correctedScore;
            }
            double d2 = d;
            if ((i & 4) != 0) {
                z = reconciled.confirmedByAi;
            }
            boolean z3 = z;
            if ((i & 8) != 0) {
                z2 = reconciled.replacedTiming;
            }
            return reconciled.copy(j2, d2, z3, z2);
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
        public final boolean getConfirmedByAi() {
            return this.confirmedByAi;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getReplacedTiming() {
            return this.replacedTiming;
        }

        public final Reconciled copy(long offsetMs, double correctedScore, boolean confirmedByAi, boolean replacedTiming) {
            return new Reconciled(offsetMs, correctedScore, confirmedByAi, replacedTiming);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Reconciled)) {
                return false;
            }
            Reconciled reconciled = (Reconciled) other;
            return this.offsetMs == reconciled.offsetMs && Double.compare(this.correctedScore, reconciled.correctedScore) == 0 && this.confirmedByAi == reconciled.confirmedByAi && this.replacedTiming == reconciled.replacedTiming;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.offsetMs) * 31) + Double.hashCode(this.correctedScore)) * 31) + Boolean.hashCode(this.confirmedByAi)) * 31) + Boolean.hashCode(this.replacedTiming);
        }

        public String toString() {
            return "Reconciled(offsetMs=" + this.offsetMs + ", correctedScore=" + this.correctedScore + ", confirmedByAi=" + this.confirmedByAi + ", replacedTiming=" + this.replacedTiming + ")";
        }

        public Reconciled(long offsetMs, double correctedScore, boolean confirmedByAi, boolean replacedTiming) {
            this.offsetMs = offsetMs;
            this.correctedScore = correctedScore;
            this.confirmedByAi = confirmedByAi;
            this.replacedTiming = replacedTiming;
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final boolean getConfirmedByAi() {
            return this.confirmedByAi;
        }

        public final boolean getReplacedTiming() {
            return this.replacedTiming;
        }
    }

    public final Reconciled reconcileWithAi(List<SubtitleCue> cues, List<SubtitleCue> referenceCues, long timingOffsetMs, double timingScore, AiLineSync ai) {
        Long offsetMs;
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Reconciled keep = new Reconciled(timingOffsetMs, timingScore, false, false);
        if (ai == null || (offsetMs = ai.getOffsetMs()) == null) {
            return keep;
        }
        long aiOffset = offsetMs.longValue();
        if (ai.verdict() != AiVerdict.CONFIRMED) {
            return keep;
        }
        if (Math.abs(aiOffset - timingOffsetMs) <= 450) {
            return Reconciled.copy$default(keep, 0L, 0.0d, true, false, 11, null);
        }
        if (Math.abs(aiOffset) >= 700 && cues.size() >= 3 && referenceCues.size() >= 4) {
            double corrected = scoreAt(cues, aiOffset, referenceCues);
            return corrected < timingScore + 0.01d ? keep : new Reconciled(aiOffset, corrected, true, true);
        }
        return keep;
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$BuiltInScore;", "", "offsetMs", "", "baseScore", "", "correctedScore", "accepted", "", "(JDDZ)V", "getAccepted", "()Z", "getBaseScore", "()D", "getCorrectedScore", "getOffsetMs", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class BuiltInScore {
        public static final int $stable = 0;
        private final boolean accepted;
        private final double baseScore;
        private final double correctedScore;
        private final long offsetMs;

        /* renamed from: component1, reason: from getter */
        public final long getOffsetMs() {
            return this.offsetMs;
        }

        /* renamed from: component2, reason: from getter */
        public final double getBaseScore() {
            return this.baseScore;
        }

        /* renamed from: component3, reason: from getter */
        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getAccepted() {
            return this.accepted;
        }

        public final BuiltInScore copy(long offsetMs, double baseScore, double correctedScore, boolean accepted) {
            return new BuiltInScore(offsetMs, baseScore, correctedScore, accepted);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BuiltInScore)) {
                return false;
            }
            BuiltInScore builtInScore = (BuiltInScore) other;
            return this.offsetMs == builtInScore.offsetMs && Double.compare(this.baseScore, builtInScore.baseScore) == 0 && Double.compare(this.correctedScore, builtInScore.correctedScore) == 0 && this.accepted == builtInScore.accepted;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.offsetMs) * 31) + Double.hashCode(this.baseScore)) * 31) + Double.hashCode(this.correctedScore)) * 31) + Boolean.hashCode(this.accepted);
        }

        public String toString() {
            return "BuiltInScore(offsetMs=" + this.offsetMs + ", baseScore=" + this.baseScore + ", correctedScore=" + this.correctedScore + ", accepted=" + this.accepted + ")";
        }

        public BuiltInScore(long offsetMs, double baseScore, double correctedScore, boolean accepted) {
            this.offsetMs = offsetMs;
            this.baseScore = baseScore;
            this.correctedScore = correctedScore;
            this.accepted = accepted;
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public final double getBaseScore() {
            return this.baseScore;
        }

        public final double getCorrectedScore() {
            return this.correctedScore;
        }

        public final boolean getAccepted() {
            return this.accepted;
        }
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u001b\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JW\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\"\u001a\u00020\u0003J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006%"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$AlignDiagnostics;", "", "src", "", "buffered", "", "total", "candNear", "", "Lkotlin/Pair;", "", "candRangeStart", "candRangeEnd", "(Ljava/lang/String;IILjava/util/List;JJ)V", "getBuffered", "()I", "getCandNear", "()Ljava/util/List;", "getCandRangeEnd", "()J", "getCandRangeStart", "getSrc", "()Ljava/lang/String;", "getTotal", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "format", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class AlignDiagnostics {
        public static final int $stable = 8;
        private final int buffered;
        private final List<Pair<Long, Long>> candNear;
        private final long candRangeEnd;
        private final long candRangeStart;
        private final String src;
        private final int total;

        /* renamed from: component1, reason: from getter */
        public final String getSrc() {
            return this.src;
        }

        /* renamed from: component2, reason: from getter */
        public final int getBuffered() {
            return this.buffered;
        }

        /* renamed from: component3, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        public final List<Pair<Long, Long>> component4() {
            return this.candNear;
        }

        /* renamed from: component5, reason: from getter */
        public final long getCandRangeStart() {
            return this.candRangeStart;
        }

        /* renamed from: component6, reason: from getter */
        public final long getCandRangeEnd() {
            return this.candRangeEnd;
        }

        public final AlignDiagnostics copy(String src, int buffered, int total, List<Pair<Long, Long>> candNear, long candRangeStart, long candRangeEnd) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(candNear, "candNear");
            return new AlignDiagnostics(src, buffered, total, candNear, candRangeStart, candRangeEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlignDiagnostics)) {
                return false;
            }
            AlignDiagnostics alignDiagnostics = (AlignDiagnostics) other;
            return Intrinsics.areEqual(this.src, alignDiagnostics.src) && this.buffered == alignDiagnostics.buffered && this.total == alignDiagnostics.total && Intrinsics.areEqual(this.candNear, alignDiagnostics.candNear) && this.candRangeStart == alignDiagnostics.candRangeStart && this.candRangeEnd == alignDiagnostics.candRangeEnd;
        }

        public int hashCode() {
            return (((((((((this.src.hashCode() * 31) + Integer.hashCode(this.buffered)) * 31) + Integer.hashCode(this.total)) * 31) + this.candNear.hashCode()) * 31) + Long.hashCode(this.candRangeStart)) * 31) + Long.hashCode(this.candRangeEnd);
        }

        public String toString() {
            return "AlignDiagnostics(src=" + this.src + ", buffered=" + this.buffered + ", total=" + this.total + ", candNear=" + this.candNear + ", candRangeStart=" + this.candRangeStart + ", candRangeEnd=" + this.candRangeEnd + ")";
        }

        public AlignDiagnostics(String src, int buffered, int total, List<Pair<Long, Long>> candNear, long candRangeStart, long candRangeEnd) {
            Intrinsics.checkNotNullParameter(src, "src");
            Intrinsics.checkNotNullParameter(candNear, "candNear");
            this.src = src;
            this.buffered = buffered;
            this.total = total;
            this.candNear = candNear;
            this.candRangeStart = candRangeStart;
            this.candRangeEnd = candRangeEnd;
        }

        public final String getSrc() {
            return this.src;
        }

        public final int getBuffered() {
            return this.buffered;
        }

        public final int getTotal() {
            return this.total;
        }

        public final List<Pair<Long, Long>> getCandNear() {
            return this.candNear;
        }

        public final long getCandRangeStart() {
            return this.candRangeStart;
        }

        public final long getCandRangeEnd() {
            return this.candRangeEnd;
        }

        public final String format() {
            StringBuilder $this$format_u24lambda_u240 = new StringBuilder();
            $this$format_u24lambda_u240.append("align src=").append(this.src);
            $this$format_u24lambda_u240.append(" buffered=").append(this.buffered);
            $this$format_u24lambda_u240.append(" total=").append(this.total);
            $this$format_u24lambda_u240.append(" refs=").append(this.candNear.size());
            $this$format_u24lambda_u240.append(" candNear=").append(this.candNear);
            $this$format_u24lambda_u240.append(" candRange=").append(this.candRangeStart).append("..").append(this.candRangeEnd);
            String sb = $this$format_u24lambda_u240.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return sb;
        }
    }

    public final boolean isSameContent(List<SubtitleCue> referenceCues, List<SubtitleCue> candidateCues) {
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Intrinsics.checkNotNullParameter(candidateCues, "candidateCues");
        if (referenceCues.isEmpty() || candidateCues.isEmpty()) {
            return false;
        }
        List<SubtitleCue> $this$map$iv = referenceCues;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SubtitleCue it = (SubtitleCue) item$iv$iv;
            destination$iv$iv.add(INSTANCE.normalizeForCompare(it.getOriginalText()));
        }
        Set a = CollectionsKt.toSet((List) destination$iv$iv);
        List<SubtitleCue> $this$map$iv2 = candidateCues;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            SubtitleCue it2 = (SubtitleCue) item$iv$iv2;
            destination$iv$iv2.add(INSTANCE.normalizeForCompare(it2.getOriginalText()));
        }
        Set b = CollectionsKt.toSet((List) destination$iv$iv2);
        return Intrinsics.areEqual(a, b);
    }

    public static /* synthetic */ AlignDiagnostics alignDiagnostics$default(SubtitleOffsetSync subtitleOffsetSync, List list, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return subtitleOffsetSync.alignDiagnostics(list, i);
    }

    public final AlignDiagnostics alignDiagnostics(List<SubtitleCue> referenceCues, int bufferedCount) {
        Object maxElem$iv;
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        int total = referenceCues.size();
        String src = bufferedCount == 0 ? "realtime" : bufferedCount == total ? "buffer" : "buffer+realtime";
        SubtitleCue subtitleCue = (SubtitleCue) CollectionsKt.lastOrNull((List) referenceCues);
        long lastStart = subtitleCue != null ? subtitleCue.getStartMs() : 0L;
        int n = 0;
        for (SubtitleCue cue : referenceCues) {
            if (cue.getEndMs() >= lastStart) {
                break;
            }
            n++;
        }
        if (n < 0) {
            n = 0;
        }
        int from = RangesKt.coerceAtLeast(n, 0);
        int to = Math.min(from + 3, total);
        Iterable $this$map$iv = referenceCues.subList(from, to);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SubtitleCue it = (SubtitleCue) item$iv$iv;
            destination$iv$iv.add(TuplesKt.to(Long.valueOf(it.getStartMs()), Long.valueOf(it.getEndMs())));
        }
        List near = (List) destination$iv$iv;
        List<SubtitleCue> $this$maxByOrNull$iv = referenceCues;
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                SubtitleCue it2 = (SubtitleCue) maxElem$iv;
                long maxValue$iv = it2.getEndMs();
                do {
                    Object e$iv = iterator$iv.next();
                    SubtitleCue it3 = (SubtitleCue) e$iv;
                    long v$iv = it3.getEndMs();
                    if (maxValue$iv < v$iv) {
                        maxElem$iv = e$iv;
                        maxValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            maxElem$iv = null;
        }
        SubtitleCue subtitleCue2 = (SubtitleCue) maxElem$iv;
        long rangeEnd = subtitleCue2 != null ? subtitleCue2.getEndMs() : 0L;
        return new AlignDiagnostics(src, bufferedCount, total, near, lastStart, rangeEnd);
    }

    public static /* synthetic */ List buildSpokenReferences$default(SubtitleOffsetSync subtitleOffsetSync, List list, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 600;
        }
        return subtitleOffsetSync.buildSpokenReferences(list, j);
    }

    public final List<SubtitleSyncMatcher.Reference> buildSpokenReferences(List<TimedWord> words, long gapMs) {
        Intrinsics.checkNotNullParameter(words, "words");
        if (words.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<TimedWord> $this$sortedBy$iv = words;
        List sorted = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.SubtitleOffsetSync$buildSpokenReferences$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                TimedWord it = (TimedWord) t;
                TimedWord it2 = (TimedWord) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it.getStartMs()), Long.valueOf(it2.getStartMs()));
            }
        });
        char c = '\f';
        ArrayList out = new ArrayList(12);
        long start = ((TimedWord) CollectionsKt.first(sorted)).getStartMs();
        long end = ((TimedWord) CollectionsKt.first(sorted)).getEndMs();
        for (TimedWord w : CollectionsKt.drop(sorted, 1)) {
            if (w.getStartMs() - end > gapMs) {
                out.add(new SubtitleSyncMatcher.Reference(start, end, ""));
                if (out.size() >= 12) {
                    return out;
                }
                start = w.getStartMs();
                end = w.getEndMs();
                c = '\f';
            } else {
                end = Math.max(end, w.getEndMs());
            }
        }
        out.add(new SubtitleSyncMatcher.Reference(start, end, ""));
        return out;
    }

    public static /* synthetic */ List scoreAgainstHearing$default(SubtitleOffsetSync subtitleOffsetSync, List list, List list2, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 400;
        }
        return subtitleOffsetSync.scoreAgainstHearing(list, list2, j);
    }

    public final List<ScoredCue> scoreAgainstHearing(List<SubtitleSyncMatcher.Reference> references, List<? extends List<SubtitleCue>> candidates, long tol) {
        Intrinsics.checkNotNullParameter(references, "references");
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        return scoreAgainstRefs(references, candidates, tol, true);
    }

    /* compiled from: SubtitleOffsetSync.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJB\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$ScoredCue;", "", "index", "", "normal", "", "offsetMs", "", "corrected", "consensusMs", "(IDJDLjava/lang/Long;)V", "getConsensusMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCorrected", "()D", "getIndex", "()I", "getNormal", "getOffsetMs", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "(IDJDLjava/lang/Long;)Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$ScoredCue;", "equals", "", "other", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class ScoredCue {
        public static final int $stable = 0;
        private final Long consensusMs;
        private final double corrected;
        private final int index;
        private final double normal;
        private final long offsetMs;

        /* renamed from: component1, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        /* renamed from: component2, reason: from getter */
        public final double getNormal() {
            return this.normal;
        }

        /* renamed from: component3, reason: from getter */
        public final long getOffsetMs() {
            return this.offsetMs;
        }

        /* renamed from: component4, reason: from getter */
        public final double getCorrected() {
            return this.corrected;
        }

        /* renamed from: component5, reason: from getter */
        public final Long getConsensusMs() {
            return this.consensusMs;
        }

        public final ScoredCue copy(int index, double normal, long offsetMs, double corrected, Long consensusMs) {
            return new ScoredCue(index, normal, offsetMs, corrected, consensusMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScoredCue)) {
                return false;
            }
            ScoredCue scoredCue = (ScoredCue) other;
            return this.index == scoredCue.index && Double.compare(this.normal, scoredCue.normal) == 0 && this.offsetMs == scoredCue.offsetMs && Double.compare(this.corrected, scoredCue.corrected) == 0 && Intrinsics.areEqual(this.consensusMs, scoredCue.consensusMs);
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.index) * 31) + Double.hashCode(this.normal)) * 31) + Long.hashCode(this.offsetMs)) * 31) + Double.hashCode(this.corrected)) * 31) + (this.consensusMs == null ? 0 : this.consensusMs.hashCode());
        }

        public String toString() {
            return "ScoredCue(index=" + this.index + ", normal=" + this.normal + ", offsetMs=" + this.offsetMs + ", corrected=" + this.corrected + ", consensusMs=" + this.consensusMs + ")";
        }

        public ScoredCue(int index, double normal, long offsetMs, double corrected, Long consensusMs) {
            this.index = index;
            this.normal = normal;
            this.offsetMs = offsetMs;
            this.corrected = corrected;
            this.consensusMs = consensusMs;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ ScoredCue(int r11, double r12, long r14, double r16, java.lang.Long r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
            /*
                r10 = this;
                r0 = r19 & 16
                if (r0 == 0) goto L7
                r0 = 0
                r9 = r0
                goto L9
            L7:
                r9 = r18
            L9:
                r1 = r10
                r2 = r11
                r3 = r12
                r5 = r14
                r7 = r16
                r1.<init>(r2, r3, r5, r7, r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.ScoredCue.<init>(int, double, long, double, java.lang.Long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final int getIndex() {
            return this.index;
        }

        public final double getNormal() {
            return this.normal;
        }

        public final long getOffsetMs() {
            return this.offsetMs;
        }

        public final double getCorrected() {
            return this.corrected;
        }

        public final Long getConsensusMs() {
            return this.consensusMs;
        }
    }

    public static /* synthetic */ List scoreCandidatesWithOffsets$default(SubtitleOffsetSync subtitleOffsetSync, List list, List list2, long j, boolean z, int i, Object obj) {
        long j2;
        boolean z2;
        if ((i & 4) == 0) {
            j2 = j;
        } else {
            j2 = 400;
        }
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        return subtitleOffsetSync.scoreCandidatesWithOffsets(list, list2, j2, z2);
    }

    public final List<ScoredCue> scoreCandidatesWithOffsets(List<? extends List<SubtitleCue>> candidates, List<SubtitleCue> referenceCues, long tol, boolean allowOffsets) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        return scoreAgainstRefs(referenceRefs(referenceCues), candidates, tol, allowOffsets);
    }

    private final List<ScoredCue> scoreAgainstRefs(List<SubtitleSyncMatcher.Reference> refs, List<? extends List<SubtitleCue>> candidates, long tol, boolean allowOffsets) {
        Long l;
        Object maxElem$iv;
        List matches;
        Iterable $this$mapTo$iv$iv;
        int $i$f$mapTo;
        Iterable $this$mapNotNull$iv;
        SubtitleSyncMatcher.OffsetMatch it;
        if (refs.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<? extends List<SubtitleCue>> $this$map$iv = candidates;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator it2 = $this$map$iv.iterator();
        while (it2.hasNext()) {
            List it3 = (List) it2.next();
            destination$iv$iv.add(Double.valueOf(SubtitleSyncMatcher.INSTANCE.scoreByTiming(it3, refs, tol)));
        }
        List normals = (List) destination$iv$iv;
        List<? extends List<SubtitleCue>> $this$mapIndexed$iv = candidates;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        Iterator it4 = $this$mapIndexed$iv.iterator();
        while (true) {
            boolean z = false;
            l = null;
            if (!it4.hasNext()) {
                break;
            }
            Object item$iv$iv = it4.next();
            int index$iv$iv2 = index$iv$iv + 1;
            if (index$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List cues = (List) item$iv$iv;
            int i = index$iv$iv;
            if (allowOffsets && ((Number) normals.get(i)).doubleValue() < 0.7d) {
                it = SubtitleSyncMatcher.INSTANCE.estimateOffsetMatch(cues, refs, 700L, 10000L);
                if (it != null) {
                    if (it.getCorrectedScore() >= 0.62d && it.getCorrectedScore() >= ((Number) normals.get(i)).doubleValue() + 0.1d) {
                        z = true;
                    }
                    if (z) {
                        destination$iv$iv2.add(it);
                        index$iv$iv = index$iv$iv2;
                    }
                }
                it = null;
                destination$iv$iv2.add(it);
                index$iv$iv = index$iv$iv2;
            }
            it = null;
            destination$iv$iv2.add(it);
            index$iv$iv = index$iv$iv2;
        }
        List matches2 = (List) destination$iv$iv2;
        List $this$mapNotNull$iv2 = matches2;
        Collection destination$iv$iv3 = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            SubtitleSyncMatcher.OffsetMatch it5 = (SubtitleSyncMatcher.OffsetMatch) element$iv$iv$iv;
            Long valueOf = it5 != null ? Long.valueOf(it5.getOffsetMs()) : null;
            if (valueOf != null) {
                Long l2 = valueOf;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                destination$iv$iv3.add(l2);
            } else {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
            }
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
        }
        List kept = (List) destination$iv$iv3;
        if (kept.size() >= 2) {
            List $this$map$iv2 = kept;
            Collection destination$iv$iv4 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            Iterator it6 = $this$map$iv2.iterator();
            while (it6.hasNext()) {
                long o = ((Number) it6.next()).longValue();
                Iterable $this$filter$iv = kept;
                Collection destination$iv$iv5 = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    List kept2 = kept;
                    long it7 = ((Number) element$iv$iv).longValue();
                    if (Math.abs(it7 - o) <= 400) {
                        destination$iv$iv5.add(element$iv$iv);
                    }
                    kept = kept2;
                }
                destination$iv$iv4.add((List) destination$iv$iv5);
                kept = kept;
            }
            Iterable neighbours = (List) destination$iv$iv4;
            Iterable $this$maxByOrNull$iv = neighbours;
            Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
            if (iterator$iv.hasNext()) {
                maxElem$iv = iterator$iv.next();
                if (iterator$iv.hasNext()) {
                    List it8 = (List) maxElem$iv;
                    int maxValue$iv = it8.size();
                    do {
                        Object e$iv = iterator$iv.next();
                        List it9 = (List) e$iv;
                        int v$iv = it9.size();
                        if (maxValue$iv < v$iv) {
                            maxElem$iv = e$iv;
                            maxValue$iv = v$iv;
                        }
                    } while (iterator$iv.hasNext());
                }
            } else {
                maxElem$iv = null;
            }
            List best = (List) maxElem$iv;
            if (best != null && best.size() >= 2) {
                l = (Long) CollectionsKt.sorted(best).get(best.size() / 2);
            }
        }
        Long consensus = l;
        Iterable $this$map$iv3 = CollectionsKt.getIndices(candidates);
        Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
        Iterable $this$mapTo$iv$iv2 = $this$map$iv3;
        int $i$f$mapTo2 = 0;
        Iterator<Integer> it10 = $this$mapTo$iv$iv2.iterator();
        while (it10.hasNext()) {
            int item$iv$iv2 = ((IntIterator) it10).nextInt();
            Iterable $this$map$iv4 = $this$map$iv3;
            List cues2 = candidates.get(item$iv$iv2);
            double normal = ((Number) normals.get(item$iv$iv2)).doubleValue();
            long chosen = 0;
            double corrected = normal;
            SubtitleSyncMatcher.OffsetMatch own = (SubtitleSyncMatcher.OffsetMatch) matches2.get(item$iv$iv2);
            if (own == null || own.getCorrectedScore() < 0.8d) {
                matches = matches2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                $i$f$mapTo = $i$f$mapTo2;
            } else {
                matches = matches2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                $i$f$mapTo = $i$f$mapTo2;
                double re = INSTANCE.scoreAtRefs(cues2, own.getOffsetMs(), refs);
                if (re >= normal + 0.1d) {
                    chosen = own.getOffsetMs();
                    corrected = re;
                }
            }
            if (consensus != null) {
                double re2 = INSTANCE.scoreAtRefs(cues2, consensus.longValue(), refs);
                if (re2 >= 0.68d && re2 >= normal + 0.1d) {
                    chosen = consensus.longValue();
                    corrected = re2;
                }
            }
            destination$iv$iv6.add(new ScoredCue(item$iv$iv2, normal, chosen, corrected, consensus));
            $this$map$iv3 = $this$map$iv4;
            matches2 = matches;
            $this$mapTo$iv$iv2 = $this$mapTo$iv$iv;
            $i$f$mapTo2 = $i$f$mapTo;
        }
        return (List) destination$iv$iv6;
    }

    public static /* synthetic */ BuiltInScore scoreAgainstBuiltIn$default(SubtitleOffsetSync subtitleOffsetSync, List list, List list2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return subtitleOffsetSync.scoreAgainstBuiltIn(list, list2, i);
    }

    public final BuiltInScore scoreAgainstBuiltIn(List<SubtitleCue> referenceCues, List<SubtitleCue> candidateCues, int bufferedCount) {
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Intrinsics.checkNotNullParameter(candidateCues, "candidateCues");
        if (referenceCues.size() >= 2 && candidateCues.size() >= 3) {
            List<SubtitleCue> $this$map$iv = referenceCues;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                SubtitleCue it = (SubtitleCue) item$iv$iv;
                destination$iv$iv.add(new SubtitleSyncMatcher.Reference(it.getStartMs(), it.getEndMs(), INSTANCE.normalizeForCompare(it.getOriginalText())));
                $this$map$iv = $this$map$iv;
            }
            List refs = (List) destination$iv$iv;
            if (refs.size() < 4) {
                return null;
            }
            double base = SubtitleSyncMatcher.INSTANCE.scoreByTiming(candidateCues, refs, 400L);
            SubtitleSyncMatcher.OffsetMatch match = SubtitleSyncMatcher.INSTANCE.estimateOffsetMatch(candidateCues, refs, 700L, 10000L);
            if (match == null) {
                return null;
            }
            Pair accepted = escalate(candidateCues, referenceCues, CollectionsKt.listOf(Long.valueOf(match.getOffsetMs())), bufferedCount);
            if (accepted == null) {
                return new BuiltInScore(match.getOffsetMs(), base, match.getCorrectedScore(), false);
            }
            return new BuiltInScore(accepted.getFirst().longValue(), base, accepted.getSecond().doubleValue(), true);
        }
        return null;
    }

    public final double scoreAtRefs(List<SubtitleCue> cues, long offsetMs, List<SubtitleSyncMatcher.Reference> refs) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(refs, "refs");
        return SubtitleSyncMatcher.INSTANCE.scoreByTiming(shiftCues(cues, offsetMs), refs, 400L);
    }

    public final double scoreAt(List<SubtitleCue> cues, long offsetMs, List<SubtitleCue> referenceCues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        List refs = referenceRefs(referenceCues);
        return SubtitleSyncMatcher.INSTANCE.scoreByTiming(shiftCues(cues, offsetMs), refs, 400L);
    }

    public static /* synthetic */ Pair escalate$default(SubtitleOffsetSync subtitleOffsetSync, List list, List list2, List list3, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return subtitleOffsetSync.escalate(list, list2, list3, i);
    }

    public final Pair<Long, Double> escalate(List<SubtitleCue> cues, List<SubtitleCue> referenceCues, List<Long> offsets, int bufferedCount) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(referenceCues, "referenceCues");
        Intrinsics.checkNotNullParameter(offsets, "offsets");
        Pair best = null;
        Iterator it = CollectionsKt.distinct(offsets).iterator();
        while (it.hasNext()) {
            long offset = ((Number) it.next()).longValue();
            if (offset != 0) {
                double score = scoreAt(cues, offset, referenceCues);
                if (best == null || score > ((Number) best.getSecond()).doubleValue()) {
                    best = TuplesKt.to(Long.valueOf(offset), Double.valueOf(score));
                }
                if (ArvioGates.INSTANCE.acceptLadder(referenceCues.size(), bufferedCount, score)) {
                    return TuplesKt.to(Long.valueOf(offset), Double.valueOf(score));
                }
            }
        }
        return null;
    }

    private final List<SubtitleSyncMatcher.Reference> referenceRefs(List<SubtitleCue> referenceCues) {
        List<SubtitleCue> $this$map$iv = referenceCues;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SubtitleCue it = (SubtitleCue) item$iv$iv;
            destination$iv$iv.add(new SubtitleSyncMatcher.Reference(it.getStartMs(), it.getEndMs(), INSTANCE.normalizeForCompare(it.getOriginalText())));
        }
        return (List) destination$iv$iv;
    }

    public final String shiftTimestamps(String raw, final long offsetMs) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        if (offsetMs == 0 || !StringsKt.contains$default((CharSequence) raw, (CharSequence) "-->", false, 2, (Object) null)) {
            return raw;
        }
        return TIME_LINE.replace(raw, new Function1<MatchResult, CharSequence>() { // from class: com.streamsync.app.subtitle.autosync.SubtitleOffsetSync$shiftTimestamps$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult m) {
                Intrinsics.checkNotNullParameter(m, "m");
                String start = SubtitleOffsetSync.INSTANCE.shiftToken(m.getGroupValues().get(1), offsetMs);
                String end = SubtitleOffsetSync.INSTANCE.shiftToken(m.getGroupValues().get(3), offsetMs);
                String fixedEnd = SubtitleOffsetSync.INSTANCE.parseTimestampMs(end) <= SubtitleOffsetSync.INSTANCE.parseTimestampMs(start) ? start : end;
                return start + ((Object) m.getGroupValues().get(2)) + fixedEnd;
            }
        });
    }

    public final String shiftToken(String token, long offsetMs) {
        Intrinsics.checkNotNullParameter(token, "token");
        long normalized = RangesKt.coerceAtLeast(parseTimestampMs(token) + offsetMs, 0L);
        String $this$indexOfFirst$iv = token;
        int index$iv = 0;
        int length = $this$indexOfFirst$iv.length();
        while (true) {
            if (index$iv < length) {
                char it = $this$indexOfFirst$iv.charAt(index$iv);
                if (((it == ',' || it == '.') ? (char) 1 : (char) 0) != 0) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int sepIndex = index$iv;
        char sep = sepIndex >= 0 ? token.charAt(sepIndex) : ',';
        int fracDigits = sepIndex >= 0 ? (token.length() - sepIndex) - 1 : 3;
        String substring = token.substring(0, sepIndex >= 0 ? sepIndex : token.length());
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        List parts = StringsKt.split$default((CharSequence) substring, new char[]{':'}, false, 0, 6, (Object) null);
        if (parts.size() >= 3) {
            long j = 1000;
            long totalSec = normalized / j;
            long j2 = 60;
            return format3(StringsKt.padStart(String.valueOf(totalSec / 3600), 2, '0'), StringsKt.padStart(String.valueOf((totalSec / j2) % j2), 2, '0'), StringsKt.padStart(String.valueOf(totalSec % j2), 2, '0'), normalized % j, sep, fracDigits);
        }
        long j3 = 1000;
        long totalSec2 = normalized / j3;
        long j4 = 60;
        return format3(StringsKt.padStart(String.valueOf(totalSec2 / j4), 2, '0'), StringsKt.padStart(String.valueOf(totalSec2 % j4), 2, '0'), normalized % j3, sep, fracDigits);
    }

    private final String format3(String a, String b, String c, long milli, char sep, int digits) {
        return a + ":" + b + ":" + c + sep + frac(milli, digits);
    }

    private final String format3(String b, String c, long milli, char sep, int digits) {
        return b + ":" + c + sep + frac(milli, digits);
    }

    private final String frac(long milli, int digits) {
        String s = StringsKt.padStart(String.valueOf(milli), 3, '0');
        switch (digits) {
            case 1:
                String substring = s.substring(0, 1);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                return substring;
            case 2:
                String substring2 = s.substring(0, 2);
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                return substring2;
            default:
                return s;
        }
    }

    public final long parseTimestampMs(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        String $this$indexOfFirst$iv = token;
        int index$iv = 0;
        int length = $this$indexOfFirst$iv.length();
        while (true) {
            if (index$iv < length) {
                char it = $this$indexOfFirst$iv.charAt(index$iv);
                if (((it == ',' || it == '.') ? (char) 1 : (char) 0) != 0) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        int sepIndex = index$iv;
        long j = 0;
        if (sepIndex < 0) {
            return 0L;
        }
        String timePart = token.substring(0, sepIndex);
        Intrinsics.checkNotNullExpressionValue(timePart, "substring(...)");
        String substring = token.substring(sepIndex + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String str = substring;
        if (str.length() == 0) {
            str = "0";
        }
        String fracPart = str;
        String substring2 = StringsKt.padEnd(fracPart, 3, '0').substring(0, 3);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        Long longOrNull = StringsKt.toLongOrNull(substring2);
        long millis = longOrNull != null ? longOrNull.longValue() : 0L;
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) timePart, new char[]{':'}, false, 0, 6, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Long longOrNull2 = StringsKt.toLongOrNull((String) item$iv$iv);
            destination$iv$iv.add(Long.valueOf(longOrNull2 != null ? longOrNull2.longValue() : j));
            j = 0;
        }
        List nums = (List) destination$iv$iv;
        switch (nums.size()) {
            case 1:
                return (((Number) nums.get(0)).longValue() * 1000) + millis;
            case 2:
                return (((Number) nums.get(0)).longValue() * 60000) + (((Number) nums.get(1)).longValue() * 1000) + millis;
            case 3:
                return (((Number) nums.get(0)).longValue() * 3600000) + (((Number) nums.get(1)).longValue() * 60000) + (((Number) nums.get(2)).longValue() * 1000) + millis;
            default:
                return 0L;
        }
    }

    public final List<SubtitleCue> shiftCues(List<SubtitleCue> cues, long offsetMs) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        List<SubtitleCue> $this$map$iv = cues;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SubtitleCue it = (SubtitleCue) item$iv$iv;
            destination$iv$iv.add(SubtitleCue.copy$default(it, 0, it.getStartMs() + offsetMs, it.getEndMs() + offsetMs, null, null, 25, null));
        }
        return (List) destination$iv$iv;
    }

    public final List<String> validate(List<SubtitleCue> original, List<SubtitleCue> shifted) {
        Iterator<SubtitleCue> it;
        int i;
        Intrinsics.checkNotNullParameter(original, "original");
        Intrinsics.checkNotNullParameter(shifted, "shifted");
        List problems = new ArrayList();
        if (shifted.size() != original.size()) {
            problems.add("số câu output " + shifted.size() + " != input " + original.size());
            return problems;
        }
        long prevStart = -1;
        Iterator<SubtitleCue> it2 = shifted.iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            int i3 = i2;
            int i4 = i2 + 1;
            SubtitleCue cue = it2.next();
            if (cue.getStartMs() < 0) {
                problems.add("câu " + (i3 + 1) + ": start âm (" + cue.getStartMs() + ")");
            }
            if (cue.getEndMs() < 0) {
                problems.add("câu " + (i3 + 1) + ": end âm (" + cue.getEndMs() + ")");
            }
            if (cue.getEndMs() > cue.getStartMs()) {
                it = it2;
                i = i4;
            } else {
                it = it2;
                i = i4;
                problems.add("câu " + (i3 + 1) + ": end (" + cue.getEndMs() + ") <= start (" + cue.getStartMs() + ")");
            }
            if (cue.getStartMs() < prevStart) {
                problems.add("câu " + (i3 + 1) + ": start đảo thứ tự (" + cue.getStartMs() + " < " + prevStart + ")");
            }
            prevStart = cue.getStartMs();
            if (!Intrinsics.areEqual(original.get(i3).getOriginalText(), cue.getOriginalText())) {
                problems.add("câu " + (i3 + 1) + ": chữ phụ đề bị thay đổi");
            }
            it2 = it;
            i2 = i;
        }
        return problems;
    }

    public final List<SubtitleCue> clampCues(List<SubtitleCue> cues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        long prevStart = 0;
        List<SubtitleCue> $this$map$iv = cues;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            SubtitleCue cue = (SubtitleCue) item$iv$iv;
            long s = RangesKt.coerceAtLeast(cue.getStartMs(), 0L);
            long e = cue.getEndMs();
            if (s < prevStart) {
                s = prevStart;
            }
            long s2 = s;
            if (e <= s2) {
                e = s2 + 100;
            }
            prevStart = s2;
            destination$iv$iv.add(SubtitleCue.copy$default(cue, 0, s2, e, null, null, 25, null));
        }
        return (List) destination$iv$iv;
    }

    public final String toSrt(List<SubtitleCue> cues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        StringBuilder $this$toSrt_u24lambda_u2432 = new StringBuilder();
        int i = 0;
        for (SubtitleCue cue : cues) {
            int i2 = i;
            i++;
            $this$toSrt_u24lambda_u2432.append(i2 + 1).append('\n');
            $this$toSrt_u24lambda_u2432.append(INSTANCE.formatTimestamp(cue.getStartMs()));
            $this$toSrt_u24lambda_u2432.append(" --> ");
            $this$toSrt_u24lambda_u2432.append(INSTANCE.formatTimestamp(cue.getEndMs()));
            $this$toSrt_u24lambda_u2432.append('\n');
            $this$toSrt_u24lambda_u2432.append(cue.getOriginalText());
            $this$toSrt_u24lambda_u2432.append("\n\n");
        }
        String sb = $this$toSrt_u24lambda_u2432.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    public final String formatTimestamp(long ms) {
        long total = RangesKt.coerceAtLeast(ms, 0L);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%02d:%02d:%02d,%03d", Arrays.copyOf(new Object[]{Long.valueOf(total / 3600000), Long.valueOf((total % 3600000) / 60000), Long.valueOf((total % 60000) / 1000), Long.valueOf(total % 1000)}, 4));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final String foldForMatch(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        String decomposed = Normalizer.normalize(text, Normalizer.Form.NFD);
        StringBuilder sb = new StringBuilder(decomposed.length());
        boolean pendingSpace = false;
        Intrinsics.checkNotNull(decomposed);
        int length = decomposed.length();
        for (int i = 0; i < length; i++) {
            char ch = decomposed.charAt(i);
            int type = Character.getType(ch);
            switch (type) {
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    if (ch == 273 || ch == 272) {
                        if (pendingSpace) {
                            if (sb.length() > 0) {
                                sb.append(' ');
                            }
                        }
                        pendingSpace = false;
                        sb.append('d');
                        break;
                    } else if (Character.isLetterOrDigit(ch)) {
                        if (pendingSpace) {
                            if (sb.length() > 0) {
                                sb.append(' ');
                            }
                        }
                        pendingSpace = false;
                        sb.append(Character.toLowerCase(ch));
                        break;
                    } else {
                        pendingSpace = true;
                        break;
                    }
                    break;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final Set<String> tokens(String folded) {
        Intrinsics.checkNotNullParameter(folded, "folded");
        if (folded.length() == 0) {
            return SetsKt.emptySet();
        }
        if (StringsKt.contains$default((CharSequence) folded, ' ', false, 2, (Object) null)) {
            Iterable $this$filter$iv = StringsKt.split$default((CharSequence) folded, new char[]{' '}, false, 0, 6, (Object) null);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                String it = (String) element$iv$iv;
                String it2 = it.length() >= 2 ? 1 : null;
                if (it2 != null) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            return (Set) CollectionsKt.toCollection((List) destination$iv$iv, new LinkedHashSet());
        }
        if (folded.length() <= 3) {
            return SetsKt.setOf(folded);
        }
        LinkedHashSet out = new LinkedHashSet();
        int length = folded.length() - 1;
        for (int i = 0; i < length; i++) {
            String substring = folded.substring(i, i + 2);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            out.add(substring);
        }
        return out;
    }

    public final double f1(Set<String> a, Set<String> b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a.isEmpty() || b.isEmpty()) {
            return 0.0d;
        }
        int inter = 0;
        for (String t : a) {
            if (b.contains(t)) {
                inter++;
            }
        }
        if (inter == 0) {
            return 0.0d;
        }
        return (inter * 2.0d) / (a.size() + b.size());
    }

    private final Long median(List<Long> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (Long) CollectionsKt.sorted(list).get(list.size() / 2);
    }

    private final String pct(double v) {
        return ((int) (100 * v)) + "%";
    }

    private final String clock(long ms) {
        return formatTimestamp(ms);
    }

    public final String formatSigned(long offsetMs) {
        return (offsetMs >= 0 ? new StringBuilder().append("+") : new StringBuilder()).append(offsetMs).append(TranslateLanguage.MALAY).toString();
    }

    private final int lowerBound(List<IndexedValue<SubtitleCue>> cues, long ms) {
        int lo = 0;
        int hi = cues.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (cues.get(mid).getValue().getStartMs() < ms) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private final int upperBound(List<IndexedValue<SubtitleCue>> cues, long ms) {
        int lo = 0;
        int hi = cues.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (cues.get(mid).getValue().getStartMs() <= ms) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo - 1;
    }
}
