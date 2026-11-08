package com.streamsync.app.subtitle.autosync;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine;
import com.streamsync.app.subtitle.autosync.SubtitleOffsetSync;
import com.streamsync.app.util.FileLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: LocalAutoSyncEngine.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 U2\u00020\u0001:\u0003UVWB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ \u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J0\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u0014H\u0082@¢\u0006\u0002\u0010\u001fJZ\u0010 \u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"\u0018\u00010!2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0!2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0)H\u0082@¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0002J\u0006\u0010.\u001a\u00020\u0007J\u0016\u0010/\u001a\u00020,2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!H\u0002J,\u00100\u001a\b\u0012\u0004\u0012\u00020'0!2\f\u00101\u001a\b\u0012\u0004\u0012\u00020'0!2\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0011H\u0002J.\u00104\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!H\u0086@¢\u0006\u0002\u00108JN\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!2\u0018\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!2\u0018\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!H\u0002J\u0010\u0010<\u001a\u00020,2\u0006\u0010=\u001a\u00020,H\u0002J\u000e\u0010>\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0011J\u0010\u0010?\u001a\u00020,2\u0006\u0010@\u001a\u00020\u0017H\u0002J\u0018\u0010A\u001a\u0004\u0018\u00010\u001a2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!H\u0002J$\u0010B\u001a\b\u0012\u0004\u0012\u00020'0!2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\u0006\u0010C\u001a\u00020\u0011H\u0002J^\u0010D\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020'0!2\u0018\u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!H\u0002J\u0010\u0010I\u001a\u00020\u00172\u0006\u00105\u001a\u000206H\u0002J.\u0010J\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!H\u0082@¢\u0006\u0002\u00108J>\u0010K\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020M0!2\u0006\u0010C\u001a\u00020\u0011H\u0002J>\u0010N\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001c0\"0!2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0!2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020'0!2\u0006\u0010C\u001a\u00020\u0011H\u0002J\u000e\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0011J\"\u0010Q\u001a\b\u0012\u0004\u0012\u00020'0!*\u00020\u001a2\u0006\u0010C\u001a\u00020\u00112\u0006\u0010R\u001a\u00020\u0014H\u0002J\u0013\u0010S\u001a\u00020\u0011*\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010TR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine;", "", "keyStore", "Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore;", "onState", "Lkotlin/Function1;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "", "(Lcom/streamsync/app/subtitle/autosync/GeminiApiKeyStore;Lkotlin/jvm/functions/Function1;)V", "HTML_TAG_REGEX", "Lkotlin/text/Regex;", "MULTI_SPACE_REGEX", "finished", "", "isRunning", "()Z", "lastCapturedMs", "", "acceptedByArvio", "refCount", "", "bufferedCount", "score", "", "applyDecision", "track", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "decision", "Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$OffsetDecision;", "capturedMs", "sampleCount", "(Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;Lcom/streamsync/app/subtitle/autosync/SubtitleOffsetSync$OffsetDecision;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "arbitrateWithAi", "", "Lkotlin/Pair;", "client", "Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient;", "candidates", "referenceCuesAll", "Lcom/streamsync/app/data/model/SubtitleCue;", "timed", "", "(Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bcp47Name", "", "code", "cancel", "candidateSummary", "cuesInWindow", "cues", "windowStartMs", "windowEndMs", "finish", "pcm", "", "captureStartMs", "([BJLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mergeHearingWithCues", "cueScored", "heardScored", "normalizeCueTextForCompare", "text", "onCaptureProgress", "pctOf", "v", "pickReference", "referenceNearUsable", "fromMs", "referenceTrackShift", TtmlNode.COMBINE_ALL, "comparable", "referenceCues", "scored", "rmsOf", "runSync", "scoreAgainstHearingAll", "heard", "Lcom/streamsync/app/subtitle/autosync/SubtitleSyncMatcher$Reference;", "scoreAllTracks", TtmlNode.START, "positionMs", "cuesNear", "limit", "orZero", "(Ljava/lang/Long;)J", "Companion", "State", "SubtitleTrackCandidate", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class LocalAutoSyncEngine {

    @Deprecated
    public static final long AI_BUDGET_MS = 45000;

    @Deprecated
    public static final long ALIGN_BUDGET_MS = 70000;

    @Deprecated
    public static final long ALIGN_LINE_MARGIN_MS = 20000;

    @Deprecated
    public static final String AUTO_LANGUAGE = "auto";

    @Deprecated
    public static final long CORROBORATE_TOLERANCE_MS = 400;

    @Deprecated
    public static final int MAX_ALIGN_LINES = 40;

    @Deprecated
    public static final int MAX_ALIGN_MATCHED = 3;

    @Deprecated
    public static final int MAX_ALIGN_TRACKS = 4;

    @Deprecated
    public static final int MAX_TRANSLATE_LANGUAGES = 2;

    @Deprecated
    public static final long MIN_CAPTURE_MS = 1000;

    @Deprecated
    public static final int MIN_CORROBORATING = 2;

    @Deprecated
    public static final double MIN_RMS = 0.001d;

    @Deprecated
    public static final long SYNC_DEADLINE_MS = 75000;

    @Deprecated
    public static final long TRANSLATE_BUDGET_MS = 100000;
    private final Regex HTML_TAG_REGEX;
    private final Regex MULTI_SPACE_REGEX;
    private boolean finished;
    private final GeminiApiKeyStore keyStore;
    private long lastCapturedMs;
    private final Function1<State, Unit> onState;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public LocalAutoSyncEngine(GeminiApiKeyStore keyStore, Function1<? super State, Unit> onState) {
        Intrinsics.checkNotNullParameter(keyStore, "keyStore");
        Intrinsics.checkNotNullParameter(onState, "onState");
        this.keyStore = keyStore;
        this.onState = onState;
        this.HTML_TAG_REGEX = new Regex("<[^>]+>");
        this.MULTI_SPACE_REGEX = new Regex("\\s+");
    }

    /* compiled from: LocalAutoSyncEngine.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JK\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006!"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$SubtitleTrackCandidate;", "", "label", "", "url", "isActive", "", "languageCode", "raw", "cues", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getCues", "()Ljava/util/List;", "()Z", "getLabel", "()Ljava/lang/String;", "getLanguageCode", "getRaw", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class SubtitleTrackCandidate {
        public static final int $stable = 8;
        private final List<SubtitleCue> cues;
        private final boolean isActive;
        private final String label;
        private final String languageCode;
        private final String raw;
        private final String url;

        public static /* synthetic */ SubtitleTrackCandidate copy$default(SubtitleTrackCandidate subtitleTrackCandidate, String str, String str2, boolean z, String str3, String str4, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitleTrackCandidate.label;
            }
            if ((i & 2) != 0) {
                str2 = subtitleTrackCandidate.url;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                z = subtitleTrackCandidate.isActive;
            }
            boolean z2 = z;
            if ((i & 8) != 0) {
                str3 = subtitleTrackCandidate.languageCode;
            }
            String str6 = str3;
            if ((i & 16) != 0) {
                str4 = subtitleTrackCandidate.raw;
            }
            String str7 = str4;
            if ((i & 32) != 0) {
                list = subtitleTrackCandidate.cues;
            }
            return subtitleTrackCandidate.copy(str, str5, z2, str6, str7, list);
        }

        /* renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsActive() {
            return this.isActive;
        }

        /* renamed from: component4, reason: from getter */
        public final String getLanguageCode() {
            return this.languageCode;
        }

        /* renamed from: component5, reason: from getter */
        public final String getRaw() {
            return this.raw;
        }

        public final List<SubtitleCue> component6() {
            return this.cues;
        }

        public final SubtitleTrackCandidate copy(String label, String url, boolean isActive, String languageCode, String raw, List<SubtitleCue> cues) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(languageCode, "languageCode");
            Intrinsics.checkNotNullParameter(raw, "raw");
            Intrinsics.checkNotNullParameter(cues, "cues");
            return new SubtitleTrackCandidate(label, url, isActive, languageCode, raw, cues);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleTrackCandidate)) {
                return false;
            }
            SubtitleTrackCandidate subtitleTrackCandidate = (SubtitleTrackCandidate) other;
            return Intrinsics.areEqual(this.label, subtitleTrackCandidate.label) && Intrinsics.areEqual(this.url, subtitleTrackCandidate.url) && this.isActive == subtitleTrackCandidate.isActive && Intrinsics.areEqual(this.languageCode, subtitleTrackCandidate.languageCode) && Intrinsics.areEqual(this.raw, subtitleTrackCandidate.raw) && Intrinsics.areEqual(this.cues, subtitleTrackCandidate.cues);
        }

        public int hashCode() {
            return (((((((((this.label.hashCode() * 31) + this.url.hashCode()) * 31) + Boolean.hashCode(this.isActive)) * 31) + this.languageCode.hashCode()) * 31) + this.raw.hashCode()) * 31) + this.cues.hashCode();
        }

        public String toString() {
            return "SubtitleTrackCandidate(label=" + this.label + ", url=" + this.url + ", isActive=" + this.isActive + ", languageCode=" + this.languageCode + ", raw=" + this.raw + ", cues=" + this.cues + ")";
        }

        public SubtitleTrackCandidate(String label, String url, boolean isActive, String languageCode, String raw, List<SubtitleCue> cues) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(languageCode, "languageCode");
            Intrinsics.checkNotNullParameter(raw, "raw");
            Intrinsics.checkNotNullParameter(cues, "cues");
            this.label = label;
            this.url = url;
            this.isActive = isActive;
            this.languageCode = languageCode;
            this.raw = raw;
            this.cues = cues;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ SubtitleTrackCandidate(java.lang.String r8, java.lang.String r9, boolean r10, java.lang.String r11, java.lang.String r12, java.util.List r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
            /*
                r7 = this;
                r14 = r14 & 8
                if (r14 == 0) goto L8
                java.lang.String r11 = ""
                r4 = r11
                goto L9
            L8:
                r4 = r11
            L9:
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r5 = r12
                r6 = r13
                r0.<init>(r1, r2, r3, r4, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate.<init>(java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getUrl() {
            return this.url;
        }

        public final boolean isActive() {
            return this.isActive;
        }

        public final String getLanguageCode() {
            return this.languageCode;
        }

        public final String getRaw() {
            return this.raw;
        }

        public final List<SubtitleCue> getCues() {
            return this.cues;
        }
    }

    /* compiled from: LocalAutoSyncEngine.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0082\u0001\u0006\b\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "", "Done", "Failed", "Listening", "Matching", "Rejected", "Transcribing", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Done;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Failed;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Listening;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Matching;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Rejected;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Transcribing;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public interface State {

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Listening;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "capturedMs", "", "(J)V", "getCapturedMs", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Listening implements State {
            public static final int $stable = 0;
            private final long capturedMs;

            public static /* synthetic */ Listening copy$default(Listening listening, long j, int i, Object obj) {
                if ((i & 1) != 0) {
                    j = listening.capturedMs;
                }
                return listening.copy(j);
            }

            /* renamed from: component1, reason: from getter */
            public final long getCapturedMs() {
                return this.capturedMs;
            }

            public final Listening copy(long capturedMs) {
                return new Listening(capturedMs);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Listening) && this.capturedMs == ((Listening) other).capturedMs;
            }

            public int hashCode() {
                return Long.hashCode(this.capturedMs);
            }

            public String toString() {
                return "Listening(capturedMs=" + this.capturedMs + ")";
            }

            public Listening(long capturedMs) {
                this.capturedMs = capturedMs;
            }

            public final long getCapturedMs() {
                return this.capturedMs;
            }
        }

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Transcribing;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "chunksDone", "", "chunksTotal", "capturedMs", "", "(IIJ)V", "getCapturedMs", "()J", "getChunksDone", "()I", "getChunksTotal", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Transcribing implements State {
            public static final int $stable = 0;
            private final long capturedMs;
            private final int chunksDone;
            private final int chunksTotal;

            public static /* synthetic */ Transcribing copy$default(Transcribing transcribing, int i, int i2, long j, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = transcribing.chunksDone;
                }
                if ((i3 & 2) != 0) {
                    i2 = transcribing.chunksTotal;
                }
                if ((i3 & 4) != 0) {
                    j = transcribing.capturedMs;
                }
                return transcribing.copy(i, i2, j);
            }

            /* renamed from: component1, reason: from getter */
            public final int getChunksDone() {
                return this.chunksDone;
            }

            /* renamed from: component2, reason: from getter */
            public final int getChunksTotal() {
                return this.chunksTotal;
            }

            /* renamed from: component3, reason: from getter */
            public final long getCapturedMs() {
                return this.capturedMs;
            }

            public final Transcribing copy(int chunksDone, int chunksTotal, long capturedMs) {
                return new Transcribing(chunksDone, chunksTotal, capturedMs);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Transcribing)) {
                    return false;
                }
                Transcribing transcribing = (Transcribing) other;
                return this.chunksDone == transcribing.chunksDone && this.chunksTotal == transcribing.chunksTotal && this.capturedMs == transcribing.capturedMs;
            }

            public int hashCode() {
                return (((Integer.hashCode(this.chunksDone) * 31) + Integer.hashCode(this.chunksTotal)) * 31) + Long.hashCode(this.capturedMs);
            }

            public String toString() {
                return "Transcribing(chunksDone=" + this.chunksDone + ", chunksTotal=" + this.chunksTotal + ", capturedMs=" + this.capturedMs + ")";
            }

            public Transcribing(int chunksDone, int chunksTotal, long capturedMs) {
                this.chunksDone = chunksDone;
                this.chunksTotal = chunksTotal;
                this.capturedMs = capturedMs;
            }

            public final int getChunksDone() {
                return this.chunksDone;
            }

            public final int getChunksTotal() {
                return this.chunksTotal;
            }

            public final long getCapturedMs() {
                return this.capturedMs;
            }
        }

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Matching;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "note", "", "(Ljava/lang/String;)V", "getNote", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Matching implements State {
            public static final int $stable = 0;
            private final String note;

            /* JADX WARN: Multi-variable type inference failed */
            public Matching() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public static /* synthetic */ Matching copy$default(Matching matching, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = matching.note;
                }
                return matching.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getNote() {
                return this.note;
            }

            public final Matching copy(String note) {
                Intrinsics.checkNotNullParameter(note, "note");
                return new Matching(note);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Matching) && Intrinsics.areEqual(this.note, ((Matching) other).note);
            }

            public int hashCode() {
                return this.note.hashCode();
            }

            public String toString() {
                return "Matching(note=" + this.note + ")";
            }

            public Matching(String note) {
                Intrinsics.checkNotNullParameter(note, "note");
                this.note = note;
            }

            public /* synthetic */ Matching(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? "" : str);
            }

            public final String getNote() {
                return this.note;
            }
        }

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u000fHÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\bHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J\t\u0010)\u001a\u00020\fHÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003Jm\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fHÆ\u0001J\u0013\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020\bHÖ\u0001J\t\u00100\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001f¨\u00061"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Done;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "offsetMs", "", "baseScore", "", "correctedScore", "textPairs", "", "sampleCount", "capturedMs", "trackLabel", "", "trackUrl", "switchedTrack", "", "textVerified", "(JDDIIJLjava/lang/String;Ljava/lang/String;ZZ)V", "getBaseScore", "()D", "getCapturedMs", "()J", "getCorrectedScore", "getOffsetMs", "getSampleCount", "()I", "getSwitchedTrack", "()Z", "getTextPairs", "getTextVerified", "getTrackLabel", "()Ljava/lang/String;", "getTrackUrl", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Done implements State {
            public static final int $stable = 0;
            private final double baseScore;
            private final long capturedMs;
            private final double correctedScore;
            private final long offsetMs;
            private final int sampleCount;
            private final boolean switchedTrack;
            private final int textPairs;
            private final boolean textVerified;
            private final String trackLabel;
            private final String trackUrl;

            /* renamed from: component1, reason: from getter */
            public final long getOffsetMs() {
                return this.offsetMs;
            }

            /* renamed from: component10, reason: from getter */
            public final boolean getTextVerified() {
                return this.textVerified;
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
            public final int getSampleCount() {
                return this.sampleCount;
            }

            /* renamed from: component6, reason: from getter */
            public final long getCapturedMs() {
                return this.capturedMs;
            }

            /* renamed from: component7, reason: from getter */
            public final String getTrackLabel() {
                return this.trackLabel;
            }

            /* renamed from: component8, reason: from getter */
            public final String getTrackUrl() {
                return this.trackUrl;
            }

            /* renamed from: component9, reason: from getter */
            public final boolean getSwitchedTrack() {
                return this.switchedTrack;
            }

            public final Done copy(long offsetMs, double baseScore, double correctedScore, int textPairs, int sampleCount, long capturedMs, String trackLabel, String trackUrl, boolean switchedTrack, boolean textVerified) {
                Intrinsics.checkNotNullParameter(trackLabel, "trackLabel");
                Intrinsics.checkNotNullParameter(trackUrl, "trackUrl");
                return new Done(offsetMs, baseScore, correctedScore, textPairs, sampleCount, capturedMs, trackLabel, trackUrl, switchedTrack, textVerified);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Done)) {
                    return false;
                }
                Done done = (Done) other;
                return this.offsetMs == done.offsetMs && Double.compare(this.baseScore, done.baseScore) == 0 && Double.compare(this.correctedScore, done.correctedScore) == 0 && this.textPairs == done.textPairs && this.sampleCount == done.sampleCount && this.capturedMs == done.capturedMs && Intrinsics.areEqual(this.trackLabel, done.trackLabel) && Intrinsics.areEqual(this.trackUrl, done.trackUrl) && this.switchedTrack == done.switchedTrack && this.textVerified == done.textVerified;
            }

            public int hashCode() {
                return (((((((((((((((((Long.hashCode(this.offsetMs) * 31) + Double.hashCode(this.baseScore)) * 31) + Double.hashCode(this.correctedScore)) * 31) + Integer.hashCode(this.textPairs)) * 31) + Integer.hashCode(this.sampleCount)) * 31) + Long.hashCode(this.capturedMs)) * 31) + this.trackLabel.hashCode()) * 31) + this.trackUrl.hashCode()) * 31) + Boolean.hashCode(this.switchedTrack)) * 31) + Boolean.hashCode(this.textVerified);
            }

            public String toString() {
                return "Done(offsetMs=" + this.offsetMs + ", baseScore=" + this.baseScore + ", correctedScore=" + this.correctedScore + ", textPairs=" + this.textPairs + ", sampleCount=" + this.sampleCount + ", capturedMs=" + this.capturedMs + ", trackLabel=" + this.trackLabel + ", trackUrl=" + this.trackUrl + ", switchedTrack=" + this.switchedTrack + ", textVerified=" + this.textVerified + ")";
            }

            public Done(long offsetMs, double baseScore, double correctedScore, int textPairs, int sampleCount, long capturedMs, String trackLabel, String trackUrl, boolean switchedTrack, boolean textVerified) {
                Intrinsics.checkNotNullParameter(trackLabel, "trackLabel");
                Intrinsics.checkNotNullParameter(trackUrl, "trackUrl");
                this.offsetMs = offsetMs;
                this.baseScore = baseScore;
                this.correctedScore = correctedScore;
                this.textPairs = textPairs;
                this.sampleCount = sampleCount;
                this.capturedMs = capturedMs;
                this.trackLabel = trackLabel;
                this.trackUrl = trackUrl;
                this.switchedTrack = switchedTrack;
                this.textVerified = textVerified;
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

            public final int getSampleCount() {
                return this.sampleCount;
            }

            public final long getCapturedMs() {
                return this.capturedMs;
            }

            public final String getTrackLabel() {
                return this.trackLabel;
            }

            public final String getTrackUrl() {
                return this.trackUrl;
            }

            public final boolean getSwitchedTrack() {
                return this.switchedTrack;
            }

            public final boolean getTextVerified() {
                return this.textVerified;
            }
        }

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Rejected;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "reason", "", "capturedMs", "", "sampleCount", "", "(Ljava/lang/String;JI)V", "getCapturedMs", "()J", "getReason", "()Ljava/lang/String;", "getSampleCount", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Rejected implements State {
            public static final int $stable = 0;
            private final long capturedMs;
            private final String reason;
            private final int sampleCount;

            public static /* synthetic */ Rejected copy$default(Rejected rejected, String str, long j, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = rejected.reason;
                }
                if ((i2 & 2) != 0) {
                    j = rejected.capturedMs;
                }
                if ((i2 & 4) != 0) {
                    i = rejected.sampleCount;
                }
                return rejected.copy(str, j, i);
            }

            /* renamed from: component1, reason: from getter */
            public final String getReason() {
                return this.reason;
            }

            /* renamed from: component2, reason: from getter */
            public final long getCapturedMs() {
                return this.capturedMs;
            }

            /* renamed from: component3, reason: from getter */
            public final int getSampleCount() {
                return this.sampleCount;
            }

            public final Rejected copy(String reason, long capturedMs, int sampleCount) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                return new Rejected(reason, capturedMs, sampleCount);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Rejected)) {
                    return false;
                }
                Rejected rejected = (Rejected) other;
                return Intrinsics.areEqual(this.reason, rejected.reason) && this.capturedMs == rejected.capturedMs && this.sampleCount == rejected.sampleCount;
            }

            public int hashCode() {
                return (((this.reason.hashCode() * 31) + Long.hashCode(this.capturedMs)) * 31) + Integer.hashCode(this.sampleCount);
            }

            public String toString() {
                return "Rejected(reason=" + this.reason + ", capturedMs=" + this.capturedMs + ", sampleCount=" + this.sampleCount + ")";
            }

            public Rejected(String reason, long capturedMs, int sampleCount) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
                this.capturedMs = capturedMs;
                this.sampleCount = sampleCount;
            }

            public final String getReason() {
                return this.reason;
            }

            public final long getCapturedMs() {
                return this.capturedMs;
            }

            public final int getSampleCount() {
                return this.sampleCount;
            }
        }

        /* compiled from: LocalAutoSyncEngine.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State$Failed;", "Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$State;", "message", "", "capturedMs", "", "(Ljava/lang/String;J)V", "getCapturedMs", "()J", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes19.dex */
        public static final /* data */ class Failed implements State {
            public static final int $stable = 0;
            private final long capturedMs;
            private final String message;

            public static /* synthetic */ Failed copy$default(Failed failed, String str, long j, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = failed.message;
                }
                if ((i & 2) != 0) {
                    j = failed.capturedMs;
                }
                return failed.copy(str, j);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final long getCapturedMs() {
                return this.capturedMs;
            }

            public final Failed copy(String message, long capturedMs) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new Failed(message, capturedMs);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Failed)) {
                    return false;
                }
                Failed failed = (Failed) other;
                return Intrinsics.areEqual(this.message, failed.message) && this.capturedMs == failed.capturedMs;
            }

            public int hashCode() {
                return (this.message.hashCode() * 31) + Long.hashCode(this.capturedMs);
            }

            public String toString() {
                return "Failed(message=" + this.message + ", capturedMs=" + this.capturedMs + ")";
            }

            public Failed(String message, long capturedMs) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.capturedMs = capturedMs;
            }

            public /* synthetic */ Failed(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? 0L : j);
            }

            public final String getMessage() {
                return this.message;
            }

            public final long getCapturedMs() {
                return this.capturedMs;
            }
        }
    }

    public final boolean isRunning() {
        return !this.finished;
    }

    public final void start(long positionMs) {
        this.finished = false;
        this.lastCapturedMs = 0L;
        this.onState.invoke(new State.Listening(0L));
    }

    public final void onCaptureProgress(long capturedMs) {
        this.lastCapturedMs = capturedMs;
        if (this.finished) {
            return;
        }
        this.onState.invoke(new State.Listening(capturedMs));
    }

    public final void cancel() {
        this.finished = true;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object finish(byte[] r10, long r11, java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$finish$1
            if (r0 == 0) goto L14
            r0 = r14
            com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$finish$1 r0 = (com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$finish$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$finish$1 r0 = new com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$finish$1
            r0.<init>(r9, r14)
        L19:
            r14 = r0
            java.lang.Object r6 = r14.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r14.label
            switch(r0) {
                case 0: goto L39;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            java.lang.Object r10 = r14.L$0
            com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine r10 = (com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine) r10
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L35 java.util.concurrent.CancellationException -> L37
            goto L59
        L35:
            r11 = move-exception
            goto L5c
        L37:
            r10 = move-exception
            goto L93
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            r8 = r9
            r2 = r11
            r4 = r13
            r1 = r10
            boolean r10 = r8.finished
            if (r10 == 0) goto L47
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L47:
            r10 = 1
            r8.finished = r10
            r14.L$0 = r8     // Catch: java.util.concurrent.CancellationException -> L37 java.lang.Throwable -> L5a
            r14.label = r10     // Catch: java.util.concurrent.CancellationException -> L37 java.lang.Throwable -> L5a
            r0 = r8
            r5 = r14
            java.lang.Object r10 = r0.runSync(r1, r2, r4, r5)     // Catch: java.util.concurrent.CancellationException -> L37 java.lang.Throwable -> L5a
            if (r10 != r7) goto L58
            return r7
        L58:
            r10 = r8
        L59:
            goto L90
        L5a:
            r11 = move-exception
            r10 = r8
        L5c:
            com.streamsync.app.util.FileLog r12 = com.streamsync.app.util.FileLog.INSTANCE
            java.lang.String r13 = "Auto sync lỗi chưa xử lý, đã báo Failed"
            r12.w(r13, r11)
            kotlin.jvm.functions.Function1<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$State, kotlin.Unit> r12 = r10.onState
            com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$State$Failed r13 = new com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$State$Failed
            java.lang.String r0 = r11.getMessage()
            if (r0 != 0) goto L75
            java.lang.Class r0 = r11.getClass()
            java.lang.String r0 = r0.getSimpleName()
        L75:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r1 = "Đồng bộ lỗi: "
            java.lang.StringBuilder r11 = r11.append(r1)
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r11 = r11.toString()
            long r0 = r10.lastCapturedMs
            r13.<init>(r11, r0)
            r12.invoke(r13)
        L90:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L93:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.finish(byte[], long, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x094e, code lost:
    
        if (r0 == null) goto L273;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002e. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0781 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0826  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0843  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x086e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x08c0  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x09e6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0870  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x055d  */
    /* JADX WARN: Type inference failed for: r3v23, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r3v59, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r3v70, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r3v82, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r8v29, types: [java.util.List, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runSync(byte[] r50, long r51, java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r53, kotlin.coroutines.Continuation<? super kotlin.Unit> r54) {
        /*
            Method dump skipped, instructions count: 2602
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.runSync(byte[], long, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object applyDecision$default(LocalAutoSyncEngine localAutoSyncEngine, SubtitleTrackCandidate subtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision offsetDecision, long j, int i, Continuation continuation, int i2, Object obj) {
        int i3;
        if ((i2 & 8) == 0) {
            i3 = i;
        } else {
            i3 = 0;
        }
        return localAutoSyncEngine.applyDecision(subtitleTrackCandidate, offsetDecision, j, i3, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object applyDecision(SubtitleTrackCandidate track, SubtitleOffsetSync.OffsetDecision decision, long capturedMs, int sampleCount, Continuation<? super Unit> continuation) {
        long offset = decision.getOffsetMs();
        List shiftedCues = SubtitleOffsetSync.INSTANCE.clampCues(SubtitleOffsetSync.INSTANCE.shiftCues(track.getCues(), offset));
        List problems = SubtitleOffsetSync.INSTANCE.validate(track.getCues(), shiftedCues);
        if (!problems.isEmpty()) {
            FileLog.w$default(FileLog.INSTANCE, "Auto sync: kết quả không hợp lệ: " + CollectionsKt.joinToString$default(problems, "; ", null, null, 0, null, null, 62, null), null, 2, null);
            this.onState.invoke(new State.Failed("Kết quả đồng bộ không hợp lệ (" + CollectionsKt.first(problems) + "), không ghi file.", capturedMs));
            return Unit.INSTANCE;
        }
        FileLog.d$default(FileLog.INSTANCE, "Auto sync xong: offset=" + SubtitleOffsetSync.INSTANCE.formatSigned(offset) + ", điểm " + decision.getBaseScore() + "->" + decision.getCorrectedScore() + ", " + decision.getTextPairs() + " cặp chữ, track=" + track.getLabel() + " (áp trong player)", null, 2, null);
        this.onState.invoke(new State.Done(offset, decision.getBaseScore(), decision.getCorrectedScore(), decision.getTextPairs(), sampleCount, capturedMs, track.getLabel(), track.getUrl(), !track.isActive(), decision.getTextVerified()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:103:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0131 A[LOOP:1: B:25:0x012b->B:27:0x0131, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0171 A[LOOP:2: B:30:0x016b->B:32:0x0171, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01bf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x03f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x01c0 -> B:12:0x01c7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object arbitrateWithAi(com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient r48, java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r49, java.util.List<com.streamsync.app.data.model.SubtitleCue> r50, java.util.Map<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision> r51, kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision>>> r52) {
        /*
            Method dump skipped, instructions count: 1042
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.arbitrateWithAi(com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient, java.util.List, java.util.List, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean acceptedByArvio(int refCount, int bufferedCount, double score) {
        return ArvioGates.INSTANCE.acceptLadder(refCount, bufferedCount, score);
    }

    private final List<SubtitleCue> referenceNearUsable(List<SubtitleTrackCandidate> candidates, long fromMs) {
        SubtitleTrackCandidate track = pickReference(candidates);
        if (track == null) {
            return CollectionsKt.emptyList();
        }
        Iterable $this$filter$iv = track.getCues();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SubtitleCue it = (SubtitleCue) element$iv$iv;
            if (it.getEndMs() >= fromMs - 2000) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        return CollectionsKt.take(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$referenceNearUsable$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it2 = (SubtitleCue) t;
                SubtitleCue it3 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it2.getStartMs()), Long.valueOf(it3.getStartMs()));
            }
        }), 12);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0173 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.Pair<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision>> referenceTrackShift(java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r31, java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r32, java.util.List<com.streamsync.app.data.model.SubtitleCue> r33, java.util.List<kotlin.Pair<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision>> r34) {
        /*
            Method dump skipped, instructions count: 707
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.referenceTrackShift(java.util.List, java.util.List, java.util.List, java.util.List):java.util.List");
    }

    private final long orZero(Long $this$orZero) {
        if ($this$orZero != null) {
            return $this$orZero.longValue();
        }
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x003d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.Pair<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision>> scoreAgainstHearingAll(java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r46, java.util.List<com.streamsync.app.subtitle.autosync.SubtitleSyncMatcher.Reference> r47, long r48) {
        /*
            Method dump skipped, instructions count: 548
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.scoreAgainstHearingAll(java.util.List, java.util.List, long):java.util.List");
    }

    private final List<Pair<SubtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision>> mergeHearingWithCues(List<Pair<SubtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision>> cueScored, List<Pair<SubtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision>> heardScored) {
        List<Pair<SubtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision>> $this$associateBy$iv = heardScored;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associateBy$iv) {
            Pair it = (Pair) element$iv$iv;
            destination$iv$iv.put(((SubtitleTrackCandidate) it.getFirst()).getUrl(), element$iv$iv);
        }
        List<Pair<SubtitleTrackCandidate, SubtitleOffsetSync.OffsetDecision>> $this$map$iv = cueScored;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Pair pair = (Pair) item$iv$iv;
            SubtitleTrackCandidate cand = (SubtitleTrackCandidate) pair.component1();
            SubtitleOffsetSync.OffsetDecision cueDecision = (SubtitleOffsetSync.OffsetDecision) pair.component2();
            Pair h = (Pair) destination$iv$iv.get(cand.getUrl());
            destination$iv$iv2.add((h == null || !((SubtitleOffsetSync.OffsetDecision) h.getSecond()).getAccepted()) ? TuplesKt.to(cand, cueDecision) : TuplesKt.to(cand, h.getSecond()));
        }
        return (List) destination$iv$iv2;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.Pair<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate, com.streamsync.app.subtitle.autosync.SubtitleOffsetSync.OffsetDecision>> scoreAllTracks(java.util.List<com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.SubtitleTrackCandidate> r46, java.util.List<com.streamsync.app.data.model.SubtitleCue> r47, long r48) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine.scoreAllTracks(java.util.List, java.util.List, long):java.util.List");
    }

    private final List<SubtitleCue> cuesNear(SubtitleTrackCandidate $this$cuesNear, long fromMs, int limit) {
        Iterable $this$filter$iv = $this$cuesNear.getCues();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SubtitleCue it = (SubtitleCue) element$iv$iv;
            if (it.getStartMs() >= fromMs) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        return CollectionsKt.take(CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$cuesNear$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it2 = (SubtitleCue) t;
                SubtitleCue it3 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it2.getStartMs()), Long.valueOf(it3.getStartMs()));
            }
        }), limit);
    }

    private final SubtitleTrackCandidate pickReference(List<SubtitleTrackCandidate> candidates) {
        Object obj;
        Object element$iv;
        List<SubtitleTrackCandidate> $this$firstOrNull$iv = candidates;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            obj = null;
            if (it.hasNext()) {
                element$iv = it.next();
                SubtitleTrackCandidate it2 = (SubtitleTrackCandidate) element$iv;
                if (it2.isActive()) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        SubtitleTrackCandidate active = (SubtitleTrackCandidate) element$iv;
        if (active != null) {
            return active;
        }
        List<SubtitleTrackCandidate> $this$firstOrNull$iv2 = candidates;
        Iterator it3 = $this$firstOrNull$iv2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object element$iv2 = it3.next();
            SubtitleTrackCandidate it4 = (SubtitleTrackCandidate) element$iv2;
            if (StringsKt.isBlank(it4.getLanguageCode())) {
                obj = element$iv2;
                break;
            }
        }
        SubtitleTrackCandidate subtitleTrackCandidate = (SubtitleTrackCandidate) obj;
        return subtitleTrackCandidate == null ? (SubtitleTrackCandidate) CollectionsKt.firstOrNull((List) candidates) : subtitleTrackCandidate;
    }

    private final List<SubtitleCue> cuesInWindow(List<SubtitleCue> cues, long windowStartMs, long windowEndMs) {
        List<SubtitleCue> $this$filter$iv = cues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            SubtitleCue it = (SubtitleCue) element$iv$iv;
            if (it.getEndMs() >= windowStartMs && it.getStartMs() <= windowEndMs) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$cuesInWindow$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SubtitleCue it2 = (SubtitleCue) t;
                SubtitleCue it3 = (SubtitleCue) t2;
                return ComparisonsKt.compareValues(Long.valueOf(it2.getStartMs()), Long.valueOf(it3.getStartMs()));
            }
        });
    }

    private final String normalizeCueTextForCompare(String text) {
        return StringsKt.trim((CharSequence) this.MULTI_SPACE_REGEX.replace(this.HTML_TAG_REGEX.replace(text, " "), " ")).toString();
    }

    private final String pctOf(double v) {
        return ((int) (100 * v)) + "%";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0017. Please report as an issue. */
    private final String bcp47Name(String code) {
        String lowerCase = code.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String substringBefore$default = StringsKt.substringBefore$default(lowerCase, '-', (String) null, 2, (Object) null);
        switch (substringBefore$default.hashCode()) {
            case 3201:
                if (substringBefore$default.equals(TranslateLanguage.GERMAN)) {
                    return "tiếng Đức";
                }
                return code;
            case 3241:
                if (substringBefore$default.equals(TranslateLanguage.ENGLISH)) {
                    return "English";
                }
                return code;
            case 3246:
                if (substringBefore$default.equals(TranslateLanguage.SPANISH)) {
                    return "tiếng Tây Ban Nha";
                }
                return code;
            case 3276:
                if (substringBefore$default.equals(TranslateLanguage.FRENCH)) {
                    return "tiếng Pháp";
                }
                return code;
            case 3355:
                if (substringBefore$default.equals("id")) {
                    return "tiếng Indonesia";
                }
                return code;
            case 3383:
                if (substringBefore$default.equals(TranslateLanguage.JAPANESE)) {
                    return "tiếng Nhật";
                }
                return code;
            case 3428:
                if (substringBefore$default.equals(TranslateLanguage.KOREAN)) {
                    return "tiếng Hàn";
                }
                return code;
            case 3763:
                if (substringBefore$default.equals("vi")) {
                    return "tiếng Việt";
                }
                return code;
            case 3886:
                if (substringBefore$default.equals(TranslateLanguage.CHINESE)) {
                    return "tiếng Trung";
                }
                return code;
            default:
                return code;
        }
    }

    private final String candidateSummary(List<SubtitleTrackCandidate> candidates) {
        if (candidates.isEmpty()) {
            return "";
        }
        String list = CollectionsKt.joinToString$default(CollectionsKt.take(candidates, 6), ", ", null, null, 0, null, new Function1<SubtitleTrackCandidate, CharSequence>() { // from class: com.streamsync.app.subtitle.autosync.LocalAutoSyncEngine$candidateSummary$list$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(LocalAutoSyncEngine.SubtitleTrackCandidate it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getLabel() + " (" + it.getCues().size() + " câu)";
            }
        }, 30, null);
        String extra = candidates.size() > 6 ? ", …" : "";
        return "\nĐã thử " + candidates.size() + " track: " + list + extra + ".";
    }

    private final double rmsOf(byte[] pcm) {
        double sum = 0.0d;
        int count = 0;
        for (int i = 0; i + 1 < pcm.length; i += 8) {
            short sample = (short) ((pcm[i + 1] << 8) | (pcm[i] & 255));
            double v = sample / 32768.0d;
            sum += v * v;
            count++;
        }
        if (count == 0) {
            return 0.0d;
        }
        return Math.sqrt(sum / count);
    }

    /* compiled from: LocalAutoSyncEngine.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/LocalAutoSyncEngine$Companion;", "", "()V", "AI_BUDGET_MS", "", "ALIGN_BUDGET_MS", "ALIGN_LINE_MARGIN_MS", "AUTO_LANGUAGE", "", "CORROBORATE_TOLERANCE_MS", "MAX_ALIGN_LINES", "", "MAX_ALIGN_MATCHED", "MAX_ALIGN_TRACKS", "MAX_TRANSLATE_LANGUAGES", "MIN_CAPTURE_MS", "MIN_CORROBORATING", "MIN_RMS", "", "SYNC_DEADLINE_MS", "TRANSLATE_BUDGET_MS", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
