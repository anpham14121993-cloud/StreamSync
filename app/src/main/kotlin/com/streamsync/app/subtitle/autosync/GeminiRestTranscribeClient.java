package com.streamsync.app.subtitle.autosync;

import android.util.Base64;
import androidx.autofill.HintConstants;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.settings.BufferCeiling;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import okhttp3.OkHttpClient;

/* compiled from: GeminiRestTranscribeClient.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 S2\u00020\u0001:\u0002STB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0003H\u0002J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u001e\u0010\u001b\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010\u00032\u0006\u0010!\u001a\u00020\u0003H\u0002J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020\u0003H\u0002J<\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u001d2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d2\b\b\u0002\u0010)\u001a\u00020*H\u0086@¢\u0006\u0002\u0010+J\u0018\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0003H\u0002J\u0019\u00100\u001a\u0004\u0018\u00010*2\b\u00101\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0002\u00102J\u0019\u00103\u001a\u0004\u0018\u00010*2\b\u00104\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u00105J\u0017\u00106\u001a\u0004\u0018\u00010*2\u0006\u0010\u001a\u001a\u00020\u0003H\u0002¢\u0006\u0002\u00105J\u001e\u00107\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010!\u001a\u00020\u00032\u0006\u00109\u001a\u00020*H\u0002JF\u0010:\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u00172\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020\u00032\b\b\u0002\u0010>\u001a\u00020\u00172\b\b\u0002\u0010?\u001a\u00020*H\u0082@¢\u0006\u0002\u0010@J^\u0010A\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010B\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020*28\b\u0002\u0010D\u001a2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(H\u0012\u0013\u0012\u00110\u0017¢\u0006\f\bF\u0012\b\bG\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\u00100EH\u0086@¢\u0006\u0002\u0010JJ4\u0010K\u001a\n\u0012\u0004\u0012\u000208\u0018\u00010\u001d2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010M\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0082@¢\u0006\u0002\u0010NJ \u0010O\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*H\u0082@¢\u0006\u0002\u0010PJ6\u0010Q\u001a\n\u0012\u0004\u0012\u000208\u0018\u00010\u001d2\f\u0010R\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010M\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020*H\u0086@¢\u0006\u0002\u0010NR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient;", "", "apiKey", "", "languageCode", "(Ljava/lang/String;Ljava/lang/String;)V", "http", "Lokhttp3/OkHttpClient;", "httpText", "semaphore", "Lkotlinx/coroutines/sync/Semaphore;", "buildMatchPrompt", "buildRequestBody", "wav", "", "collectWords", "", "node", "Lcom/google/gson/JsonElement;", "out", "", "Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$RawWord;", "depth", "", "describeError", "code", TtmlNode.TAG_BODY, "encodeIndexed", "lines", "", "sep", "extractErrorDetail", "extractFirstText", "json", "extractJsonArray", "Lcom/google/gson/JsonArray;", "text", "matchSubtitleLines", "Lcom/streamsync/app/subtitle/autosync/LineMatch;", "referenceLines", "candidateLines", "deadlineMs", "", "(Ljava/util/List;Ljava/util/List;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "optInt", "o", "Lcom/google/gson/JsonObject;", "key", "parseOffset", "element", "(Lcom/google/gson/JsonElement;)Ljava/lang/Long;", "parseRetryAfter", "header", "(Ljava/lang/String;)Ljava/lang/Long;", "parseRetryDelay", "parseWords", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "chunkStartMs", "postWithRetry", "chunkIndex", "endpoint", "contentType", "maxAttempts", "maxWaitMs", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transcribe", "pcm", "captureStartMs", "onProgress", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "done", "total", "([BJLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translateBatch", "batch", "targetLanguage", "(Ljava/util/List;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translateWithAnyModel", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translateWords", "words", "Companion", "RawWord", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes19.dex */
public final class GeminiRestTranscribeClient {
    public static final long BASE_BACKOFF_MS = 1500;
    public static final int BYTES_PER_SAMPLE = 2;
    public static final int CHUNK_SECONDS = 120;
    public static final long DUPLICATE_WINDOW_MS = 1500;
    public static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/interactions";
    public static final String GENERATE_CONTENT_BASE = "https://generativelanguage.googleapis.com/v1beta/models/";
    public static final int MAX_ATTEMPTS = 4;
    public static final long MAX_BACKOFF_MS = 45000;
    public static final int MAX_CONCURRENT = 2;
    public static final int MAX_WORDS_PER_TRANSLATE = 120;
    public static final String MODEL = "gemini-3.5-transcribe";
    public static final long MODEL_SWITCH_PAUSE_MS = 2000;
    public static final long OFFSET_LIMIT_MS = 10000;
    public static final int OVERLAP_SECONDS = 6;
    public static final int SAMPLE_RATE = 16000;
    public static final long TRANSLATE_MAX_WAIT_MS = 12000;
    public static final int TRANSLATE_ROUNDS = 3;
    public static final long TRANSLATE_ROUND_PAUSE_MS = 12000;
    private final String apiKey;
    private final OkHttpClient http;
    private final OkHttpClient httpText;
    private final String languageCode;
    private final Semaphore semaphore;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final List<String> TRANSLATE_MODELS = CollectionsKt.listOf((Object[]) new String[]{"gemini-flash-lite-latest", "gemini-3.5-flash", "gemini-3.8-flash"});

    public GeminiRestTranscribeClient(String apiKey, String languageCode) {
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(languageCode, "languageCode");
        this.apiKey = apiKey;
        this.languageCode = languageCode;
        this.http = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(120L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).callTimeout(180L, TimeUnit.SECONDS).build();
        this.httpText = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(25L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).callTimeout(30L, TimeUnit.SECONDS).build();
        this.semaphore = SemaphoreKt.Semaphore$default(2, 0, 2, null);
    }

    /* compiled from: GeminiRestTranscribeClient.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001d\u001a\u00020\nJ\u001e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006J6\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00172\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00170\u00172\u0006\u0010(\u001a\u00020\u0004J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020&0\u00172\u0006\u0010*\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$Companion;", "", "()V", "BASE_BACKOFF_MS", "", "BYTES_PER_SAMPLE", "", "CHUNK_SECONDS", "DUPLICATE_WINDOW_MS", "ENDPOINT", "", "GENERATE_CONTENT_BASE", "MAX_ATTEMPTS", "MAX_BACKOFF_MS", "MAX_CONCURRENT", "MAX_WORDS_PER_TRANSLATE", "MODEL", "MODEL_SWITCH_PAUSE_MS", "OFFSET_LIMIT_MS", "OVERLAP_SECONDS", "SAMPLE_RATE", "TRANSLATE_MAX_WAIT_MS", "TRANSLATE_MODELS", "", "getTRANSLATE_MODELS", "()Ljava/util/List;", "TRANSLATE_ROUNDS", "TRANSLATE_ROUND_PAUSE_MS", "bcp47", "code", "buildWav", "", "pcm", "fromSample", "toSample", "mergeChunks", "Lcom/streamsync/app/subtitle/autosync/TimedWord;", "chunks", "Lcom/streamsync/app/subtitle/autosync/AudioChunk;", "wordsPerChunk", "captureStartMs", "planChunks", "totalSamples", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getTRANSLATE_MODELS() {
            return GeminiRestTranscribeClient.TRANSLATE_MODELS;
        }

        public final List<AudioChunk> planChunks(int totalSamples) {
            if (totalSamples <= 0) {
                return CollectionsKt.emptyList();
            }
            if (totalSamples <= 1920000) {
                return CollectionsKt.listOf(new AudioChunk(0, 0, totalSamples));
            }
            List out = new ArrayList();
            int start = 0;
            int index = 0;
            while (true) {
                int end = Math.min(start + 1920000, totalSamples);
                int index2 = index + 1;
                out.add(new AudioChunk(index, start, end));
                if (end < totalSamples) {
                    start += 1824000;
                    index = index2;
                } else {
                    return out;
                }
            }
        }

        public final byte[] buildWav(byte[] pcm, int fromSample, int toSample) {
            Intrinsics.checkNotNullParameter(pcm, "pcm");
            int from = RangesKt.coerceIn(fromSample * 2, 0, pcm.length);
            int to = RangesKt.coerceIn(toSample * 2, from, pcm.length);
            int dataSize = to - from;
            ByteBuffer buf = ByteBuffer.allocate(dataSize + 44).order(ByteOrder.LITTLE_ENDIAN);
            byte[] bytes = "RIFF".getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            buf.put(bytes);
            buf.putInt(dataSize + 36);
            byte[] bytes2 = "WAVE".getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            buf.put(bytes2);
            byte[] bytes3 = "fmt ".getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
            buf.put(bytes3);
            buf.putInt(16);
            buf.putShort((short) 1);
            buf.putShort((short) 1);
            buf.putInt(16000);
            buf.putInt(32000);
            buf.putShort((short) 2);
            buf.putShort((short) 16);
            byte[] bytes4 = "data".getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes4, "getBytes(...)");
            buf.put(bytes4);
            buf.putInt(dataSize);
            buf.put(pcm, from, dataSize);
            byte[] array = buf.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            return array;
        }

        /* JADX WARN: Type inference failed for: r8v5, types: [java.util.List, T] */
        public final List<TimedWord> mergeChunks(List<AudioChunk> chunks, List<? extends List<TimedWord>> wordsPerChunk, long captureStartMs) {
            Object answer$iv;
            Intrinsics.checkNotNullParameter(chunks, "chunks");
            Intrinsics.checkNotNullParameter(wordsPerChunk, "wordsPerChunk");
            List<Cand> all = new ArrayList();
            for (Pair pair : CollectionsKt.zip(chunks, wordsPerChunk)) {
                AudioChunk chunk = (AudioChunk) pair.component1();
                List<TimedWord> words = (List) pair.component2();
                long j = 16000;
                long chunkStart = captureStartMs + ((chunk.getStartSample() * 1000) / j);
                long chunkEnd = captureStartMs + ((chunk.getEndSample() * 1000) / j);
                for (TimedWord w : words) {
                    long interior = Math.min(w.getStartMs() - chunkStart, chunkEnd - w.getEndMs());
                    all.add(new Cand(w, interior));
                }
            }
            if (all.isEmpty()) {
                return CollectionsKt.emptyList();
            }
            HashMap byText = new HashMap();
            for (Cand c : all) {
                HashMap $this$getOrPut$iv = byText;
                String foldForMatch = SubtitleOffsetSync.INSTANCE.foldForMatch(c.getWord().getText());
                Object value$iv = $this$getOrPut$iv.get(foldForMatch);
                if (value$iv == null) {
                    answer$iv = new ArrayList();
                    $this$getOrPut$iv.put(foldForMatch, answer$iv);
                } else {
                    answer$iv = value$iv;
                }
                ((List) answer$iv).add(c);
            }
            List kept = new ArrayList();
            for (List<Cand> list : byText.values()) {
                Intrinsics.checkNotNull(list);
                if (list.size() > 1) {
                    CollectionsKt.sortWith(list, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$Companion$mergeChunks$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Cand it = (Cand) t;
                            Cand it2 = (Cand) t2;
                            return ComparisonsKt.compareValues(Long.valueOf(it.getWord().getStartMs()), Long.valueOf(it2.getWord().getStartMs()));
                        }
                    });
                }
                Ref.ObjectRef group = new Ref.ObjectRef();
                group.element = new ArrayList();
                long anchor = Long.MIN_VALUE;
                for (Cand c2 : list) {
                    if ((!((Collection) group.element).isEmpty()) && c2.getWord().getStartMs() - anchor > 1500) {
                        mergeChunks$flush(group, kept);
                    }
                    if (((List) group.element).isEmpty()) {
                        anchor = c2.getWord().getStartMs();
                    }
                    ((List) group.element).add(c2);
                }
                mergeChunks$flush(group, kept);
            }
            List $this$map$iv = kept;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Cand it = (Cand) item$iv$iv;
                destination$iv$iv.add(it.getWord());
            }
            Iterable $this$sortedBy$iv = (List) destination$iv$iv;
            return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$Companion$mergeChunks$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    TimedWord it2 = (TimedWord) t;
                    TimedWord it3 = (TimedWord) t2;
                    return ComparisonsKt.compareValues(Long.valueOf(it2.getStartMs()), Long.valueOf(it3.getStartMs()));
                }
            });
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, T] */
        private static final void mergeChunks$flush(Ref.ObjectRef<List<Cand>> objectRef, List<Cand> list) {
            Object maxElem$iv;
            if (!objectRef.element.isEmpty()) {
                List<Cand> list2 = list;
                Iterable $this$maxByOrNull$iv = objectRef.element;
                Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
                if (iterator$iv.hasNext()) {
                    maxElem$iv = iterator$iv.next();
                    if (iterator$iv.hasNext()) {
                        Cand it = (Cand) maxElem$iv;
                        long maxValue$iv = it.getInteriorMs();
                        do {
                            Object e$iv = iterator$iv.next();
                            Cand it2 = (Cand) e$iv;
                            long v$iv = it2.getInteriorMs();
                            if (maxValue$iv < v$iv) {
                                maxElem$iv = e$iv;
                                maxValue$iv = v$iv;
                            }
                        } while (iterator$iv.hasNext());
                    }
                } else {
                    maxElem$iv = null;
                }
                Intrinsics.checkNotNull(maxElem$iv);
                list2.add(maxElem$iv);
            }
            objectRef.element = new ArrayList();
        }

        public final String bcp47(String code) {
            Intrinsics.checkNotNullParameter(code, "code");
            String lowerCase = code.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String substringBefore$default = StringsKt.substringBefore$default(lowerCase, '-', (String) null, 2, (Object) null);
            switch (substringBefore$default.hashCode()) {
                case 3201:
                    if (substringBefore$default.equals(TranslateLanguage.GERMAN)) {
                        return "de-DE";
                    }
                    return null;
                case 3241:
                    if (substringBefore$default.equals(TranslateLanguage.ENGLISH)) {
                        return "en-US";
                    }
                    return null;
                case 3246:
                    if (substringBefore$default.equals(TranslateLanguage.SPANISH)) {
                        return "es-ES";
                    }
                    return null;
                case 3276:
                    if (substringBefore$default.equals(TranslateLanguage.FRENCH)) {
                        return "fr-FR";
                    }
                    return null;
                case 3355:
                    if (substringBefore$default.equals("id")) {
                        return "id-ID";
                    }
                    return null;
                case 3383:
                    if (substringBefore$default.equals(TranslateLanguage.JAPANESE)) {
                        return "ja-JP";
                    }
                    return null;
                case 3428:
                    if (substringBefore$default.equals(TranslateLanguage.KOREAN)) {
                        return "ko-KR";
                    }
                    return null;
                case 3763:
                    if (substringBefore$default.equals("vi")) {
                        return "vi-VN";
                    }
                    return null;
                case 3886:
                    if (substringBefore$default.equals(TranslateLanguage.CHINESE)) {
                        return "cmn-Hans-CN";
                    }
                    return null;
                default:
                    return null;
            }
        }
    }

    public static /* synthetic */ Object transcribe$default(GeminiRestTranscribeClient geminiRestTranscribeClient, byte[] bArr, long j, Function2 function2, Continuation continuation, int i, Object obj) {
        Function2 function22;
        if ((i & 4) == 0) {
            function22 = function2;
        } else {
            function22 = new Function2<Integer, Integer, Unit>() { // from class: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$transcribe$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                    invoke(num.intValue(), num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i2, int i3) {
                }
            };
        }
        return geminiRestTranscribeClient.transcribe(bArr, j, function22, continuation);
    }

    public final Object transcribe(byte[] pcm, long captureStartMs, Function2<? super Integer, ? super Integer, Unit> function2, Continuation<? super List<TimedWord>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new GeminiRestTranscribeClient$transcribe$3(pcm, captureStartMs, this, function2, null), continuation);
    }

    public static /* synthetic */ Object matchSubtitleLines$default(GeminiRestTranscribeClient geminiRestTranscribeClient, List list, List list2, long j, Continuation continuation, int i, Object obj) {
        long j2;
        if ((i & 4) == 0) {
            j2 = j;
        } else {
            j2 = Long.MAX_VALUE;
        }
        return geminiRestTranscribeClient.matchSubtitleLines(list, list2, j2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0033 A[Catch: all -> 0x0038, CancellationException -> 0x003a, TRY_ENTER, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x003a, all -> 0x0038, blocks: (B:12:0x0033, B:14:0x006d, B:22:0x0053), top: B:7:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object matchSubtitleLines(java.util.List<java.lang.String> r15, java.util.List<java.lang.String> r16, long r17, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.subtitle.autosync.LineMatch>> r19) {
        /*
            r14 = this;
            r0 = r19
            boolean r1 = r0 instanceof com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$1
            if (r1 == 0) goto L18
            r1 = r0
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$1 r1 = (com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r0 = r1
            r9 = r14
            goto L1f
        L18:
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$1 r1 = new com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$1
            r9 = r14
            r1.<init>(r14, r0)
            r0 = r1
        L1f:
            java.lang.Object r10 = r1.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r11 = 0
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L33;
                default: goto L2b;
            }
        L2b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L33:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r2 = r10
            goto L6d
        L38:
            r0 = move-exception
            goto L71
        L3a:
            r0 = move-exception
            goto L90
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            r3 = r14
            r5 = r16
            r4 = r15
            r6 = r17
            boolean r2 = r4.isEmpty()
            if (r2 != 0) goto L91
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L52
            goto L91
        L52:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r12 = r2
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$2 r13 = new com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$matchSubtitleLines$2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r8 = 0
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r8)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r2 = 1
            r1.label = r2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r12, r13, r1)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            if (r2 != r0) goto L6d
            return r0
        L6d:
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r11 = r2
            goto L8f
        L71:
            com.streamsync.app.util.FileLog r2 = com.streamsync.app.util.FileLog.INSTANCE
            java.lang.String r3 = r0.getMessage()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Đối chiếu dòng phụ đề thất bại: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r4 = 2
            com.streamsync.app.util.FileLog.w$default(r2, r3, r11, r4, r11)
        L8f:
            return r11
        L90:
            throw r0
        L91:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.matchSubtitleLines(java.util.List, java.util.List, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int optInt(JsonObject o, String key) {
        Object m6717constructorimpl;
        JsonElement el = o.get(key);
        if (el == null || !el.isJsonPrimitive()) {
            return -1;
        }
        if (el.getAsJsonPrimitive().isNumber()) {
            return el.getAsInt();
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            GeminiRestTranscribeClient geminiRestTranscribeClient = this;
            String asString = el.getAsString();
            Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
            m6717constructorimpl = Result.m6717constructorimpl(Integer.valueOf(Integer.parseInt(StringsKt.trim((CharSequence) asString).toString())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = -1;
        }
        return ((Number) m6717constructorimpl).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildMatchPrompt() {
        return "You align two subtitle files for the SAME video, written in different languages.\nYou are given REFERENCE lines and CANDIDATE lines, each prefixed with its index.\nRules:\n1. Return ONLY a valid JSON array of objects, each exactly {\"r\": <reference index>, \"c\": <candidate index>}.\n2. Pair a reference line with the candidate line that expresses the SAME dialogue, across languages.\n3. OMIT any reference line you are not confident about. A short, correct list beats a complete, guessed one.\n4. Never translate, never invent text, never output anything except the JSON array.\n5. Each index may appear at most once.";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodeIndexed(List<String> lines, String sep) {
        List<String> $this$mapIndexed$iv = lines;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int index$iv$iv2 = index$iv$iv + 1;
            if (index$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String line = (String) item$iv$iv;
            destination$iv$iv.add("\"" + index$iv$iv + ": " + StringsKt.replace$default(line, "\n", sep, false, 4, (Object) null) + "\"");
            index$iv$iv = index$iv$iv2;
        }
        return CollectionsKt.joinToString$default((List) destination$iv$iv, ",", "[", "]", 0, null, null, 56, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonArray extractJsonArray(String text) {
        List<String> groupValues;
        MatchResult find$default = Regex.find$default(new Regex("```(?:json)?\\s*([\\s\\S]*?)```"), text, 0, 2, null);
        String fenced = (find$default == null || (groupValues = find$default.getGroupValues()) == null) ? null : groupValues.get(1);
        String body = fenced == null ? new Regex("```[^`]*```").replace(text, "") : fenced;
        try {
            Result.Companion companion = Result.INSTANCE;
            GeminiRestTranscribeClient geminiRestTranscribeClient = this;
            return JsonParser.parseString(body).getAsJsonArray();
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
            int open = StringsKt.indexOf$default((CharSequence) body, '[', 0, false, 6, (Object) null);
            int close = StringsKt.lastIndexOf$default((CharSequence) body, ']', 0, false, 6, (Object) null);
            if (open >= 0 && close > open) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    String substring = body.substring(open, close + 1);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    return JsonParser.parseString(substring).getAsJsonArray();
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m6717constructorimpl(ResultKt.createFailure(th2));
                    return null;
                }
            }
            return null;
        }
    }

    public static /* synthetic */ Object translateWords$default(GeminiRestTranscribeClient geminiRestTranscribeClient, List list, String str, long j, Continuation continuation, int i, Object obj) {
        long j2;
        if ((i & 4) == 0) {
            j2 = j;
        } else {
            j2 = Long.MAX_VALUE;
        }
        return geminiRestTranscribeClient.translateWords(list, str, j2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0033 A[Catch: all -> 0x0038, CancellationException -> 0x003a, TRY_ENTER, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x003a, all -> 0x0038, blocks: (B:12:0x0033, B:14:0x0072, B:22:0x0057), top: B:7:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translateWords(java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> r16, java.lang.String r17, long r18, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.subtitle.autosync.TimedWord>> r20) {
        /*
            r15 = this;
            r0 = r20
            boolean r1 = r0 instanceof com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$1
            if (r1 == 0) goto L18
            r1 = r0
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$1 r1 = (com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r0 = r1
            r9 = r15
            goto L1f
        L18:
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$1 r1 = new com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$1
            r9 = r15
            r1.<init>(r15, r0)
            r0 = r1
        L1f:
            java.lang.Object r10 = r1.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r11 = 0
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L33;
                default: goto L2b;
            }
        L2b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L33:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r2 = r10
            goto L72
        L38:
            r0 = move-exception
            goto L76
        L3a:
            r0 = move-exception
            goto L95
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r15
            r12 = r17
            r3 = r16
            r4 = r18
            boolean r2 = r3.isEmpty()
            if (r2 != 0) goto L96
            r2 = r12
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L56
            goto L96
        L56:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r13 = r2
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$2 r14 = new com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$translateWords$2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r8 = 0
            r2 = r14
            r7 = r12
            r2.<init>(r3, r4, r6, r7, r8)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r2 = 1
            r1.label = r2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r13, r14, r1)     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            if (r2 != r0) goto L72
            return r0
        L72:
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> L38 java.util.concurrent.CancellationException -> L3a
            r11 = r2
            goto L94
        L76:
            com.streamsync.app.util.FileLog r2 = com.streamsync.app.util.FileLog.INSTANCE
            java.lang.String r3 = r0.getMessage()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Dịch từ thất bại: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            r4 = 2
            com.streamsync.app.util.FileLog.w$default(r2, r3, r11, r4, r11)
        L94:
            return r11
        L95:
            throw r0
        L96:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.translateWords(java.util.List, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0194 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translateBatch(java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> r23, java.lang.String r24, long r25, kotlin.coroutines.Continuation<? super java.util.List<com.streamsync.app.subtitle.autosync.TimedWord>> r27) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.translateBatch(java.util.List, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x002d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0282 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0030  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x01c5 -> B:17:0x00b4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x024c -> B:13:0x0253). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translateWithAnyModel(java.lang.String r28, long r29, kotlin.coroutines.Continuation<? super java.lang.String> r31) {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.translateWithAnyModel(java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractFirstText(String json) {
        Object m6717constructorimpl;
        JsonElement jsonElement;
        JsonObject asJsonObject;
        JsonObject asJsonObject2;
        JsonArray asJsonArray;
        JsonElement jsonElement2;
        JsonObject asJsonObject3;
        JsonElement jsonElement3;
        try {
            Result.Companion companion = Result.INSTANCE;
            GeminiRestTranscribeClient geminiRestTranscribeClient = this;
            JsonArray asJsonArray2 = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray("candidates");
            m6717constructorimpl = Result.m6717constructorimpl((asJsonArray2 == null || (jsonElement = asJsonArray2.get(0)) == null || (asJsonObject = jsonElement.getAsJsonObject()) == null || (asJsonObject2 = asJsonObject.getAsJsonObject("content")) == null || (asJsonArray = asJsonObject2.getAsJsonArray("parts")) == null || (jsonElement2 = asJsonArray.get(0)) == null || (asJsonObject3 = jsonElement2.getAsJsonObject()) == null || (jsonElement3 = asJsonObject3.get("text")) == null) ? null : jsonElement3.getAsString());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m6723isFailureimpl(m6717constructorimpl) ? null : m6717constructorimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildRequestBody(byte[] wav) {
        JsonObject $this$buildRequestBody_u24lambda_u2418 = new JsonObject();
        $this$buildRequestBody_u24lambda_u2418.addProperty("type", MimeTypes.BASE_TYPE_AUDIO);
        $this$buildRequestBody_u24lambda_u2418.addProperty("data", Base64.encodeToString(wav, 2));
        $this$buildRequestBody_u24lambda_u2418.addProperty("mime_type", MimeTypes.AUDIO_WAV);
        JsonObject $this$buildRequestBody_u24lambda_u2422 = new JsonObject();
        JsonArray codes = new JsonArray();
        String it = INSTANCE.bcp47(this.languageCode);
        if (it != null) {
            codes.add(it);
        }
        $this$buildRequestBody_u24lambda_u2422.add("language_codes", codes);
        JsonObject $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421 = new JsonObject();
        $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421.addProperty("type", "verbatim");
        JsonArray $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421_u24lambda_u2420 = new JsonArray();
        $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421_u24lambda_u2420.add("word");
        Unit unit = Unit.INSTANCE;
        $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421.add("timestamp_granularities", $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421_u24lambda_u2420);
        Unit unit2 = Unit.INSTANCE;
        $this$buildRequestBody_u24lambda_u2422.add("mode", $this$buildRequestBody_u24lambda_u2422_u24lambda_u2421);
        JsonObject $this$buildRequestBody_u24lambda_u2425 = new JsonObject();
        $this$buildRequestBody_u24lambda_u2425.addProperty("model", MODEL);
        JsonArray $this$buildRequestBody_u24lambda_u2425_u24lambda_u2423 = new JsonArray();
        $this$buildRequestBody_u24lambda_u2425_u24lambda_u2423.add($this$buildRequestBody_u24lambda_u2418);
        Unit unit3 = Unit.INSTANCE;
        $this$buildRequestBody_u24lambda_u2425.add("input", $this$buildRequestBody_u24lambda_u2425_u24lambda_u2423);
        JsonObject $this$buildRequestBody_u24lambda_u2425_u24lambda_u2424 = new JsonObject();
        $this$buildRequestBody_u24lambda_u2425_u24lambda_u2424.add("transcription_config", $this$buildRequestBody_u24lambda_u2422);
        Unit unit4 = Unit.INSTANCE;
        $this$buildRequestBody_u24lambda_u2425.add("generation_config", $this$buildRequestBody_u24lambda_u2425_u24lambda_u2424);
        String jsonObject = $this$buildRequestBody_u24lambda_u2425.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject, "toString(...)");
        return jsonObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:49|50|(5:52|53|54|55|56)(1:199)|57|(4:(3:61|(1:194)(1:65)|(9:67|(3:95|96|(32:98|99|100|(3:182|183|184)(1:102)|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|(1:131)(26:132|133|134|135|136|137|138|139|140|141|142|13|14|15|16|17|18|(0)(0)|21|22|23|(0)(0)|(0)|28|29|(0)(0))))|69|70|71|72|73|74|75))|73|74|75)|195|(0)|69|70|71|72) */
    /* JADX WARN: Can't wrap try/catch for region: R(14:13|(1:14)|15|16|17|18|(2:208|209)(1:20)|22|23|(2:200|201)(1:25)|(1:27)|28|29|(4:31|32|33|34)(14:49|50|(5:52|53|54|55|56)(1:199)|57|(3:61|(1:194)(1:65)|(9:67|(3:95|96|(32:98|99|100|(3:182|183|184)(1:102)|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|(1:131)(26:132|133|134|135|136|137|138|139|140|141|142|13|14|15|16|17|18|(0)(0)|21|22|23|(0)(0)|(0)|28|29|(0)(0))))|69|70|71|72|73|74|75))|195|(0)|69|70|71|72|73|74|75)) */
    /* JADX WARN: Can't wrap try/catch for region: R(26:132|133|134|135|136|137|138|139|140|141|142|13|14|15|16|17|18|(0)(0)|21|22|23|(0)(0)|(0)|28|29|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02ee, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02d6, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02d7, code lost:
    
        r1 = r27;
        r2 = r29;
        r4 = r30;
        r3 = r18;
        r22 = r8;
        r9 = r28;
        r8 = r16;
        r16 = r0;
        r0 = r10;
        r10 = r13;
        r13 = r11;
        r11 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x04c2, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x04c3, code lost:
    
        r2 = r26;
        r4 = r16;
        r22 = r14;
        r14 = r13;
        r16 = r0;
        r0 = r10;
        r10 = r12;
        r13 = r11;
        r11 = r8;
        r9 = r17;
        r8 = r15;
        r15 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0464, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0465, code lost:
    
        r2 = r0;
        r18 = r3;
        r1 = r12;
        r4 = r16;
        r3 = r26;
        r12 = r31;
        r17 = r13;
        r13 = r2;
        r16 = r17;
        r15 = r16;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x011f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x013c A[Catch: IOException -> 0x04c2, GeminiRestException -> 0x05d5, TRY_ENTER, TryCatch #10 {IOException -> 0x04c2, blocks: (B:18:0x0102, B:21:0x013e, B:20:0x013c), top: B:17:0x0102 }] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x059e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 10 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:141:0x02c5 -> B:13:0x00eb). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x056e -> B:12:0x0580). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object postWithRetry(java.lang.String r26, int r27, java.lang.String r28, java.lang.String r29, int r30, long r31, kotlin.coroutines.Continuation<? super java.lang.String> r33) {
        /*
            Method dump skipped, instructions count: 1508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.postWithRetry(java.lang.String, int, java.lang.String, java.lang.String, int, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object postWithRetry$default(GeminiRestTranscribeClient geminiRestTranscribeClient, String str, int i, String str2, String str3, int i2, long j, Continuation continuation, int i3, Object obj) {
        return geminiRestTranscribeClient.postWithRetry(str, i, (i3 & 4) != 0 ? ENDPOINT : str2, (i3 & 8) != 0 ? "application/json; charset=utf-8" : str3, (i3 & 16) != 0 ? 4 : i2, (i3 & 32) != 0 ? 45000L : j, continuation);
    }

    private final String describeError(int code, String body) {
        String detail = extractErrorDetail(body);
        if (code == 400 && StringsKt.contains$default((CharSequence) detail, (CharSequence) "API key not valid", false, 2, (Object) null)) {
            return "API key bị Google từ chối. Dán lại key trong Cài đặt → Đồng bộ phụ đề.";
        }
        if (code == 401 || code == 403) {
            return "Gemini từ chối quyền truy cập (HTTP " + code + "). Kiểm tra key và billing." + (detail.length() > 0 ? " Chi tiết: " + detail : "");
        }
        if (code == 404) {
            return "Model gemini-3.5-transcribe không dùng được với key này (HTTP 404)." + (detail.length() > 0 ? " " + detail : "");
        }
        if (code == 429) {
            return "Hết hạn mức gọi API của Gemini (HTTP 429) sau 4 lần thử. Thử lại sau ít phút hoặc bật billing.";
        }
        return "Gemini trả lỗi HTTP " + code + "." + (detail.length() > 0 ? " " + detail : "");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a A[Catch: all -> 0x0068, TryCatch #0 {all -> 0x0068, blocks: (B:3:0x0002, B:6:0x0063, B:14:0x0014, B:16:0x0021, B:20:0x0030, B:23:0x003a, B:25:0x0042, B:29:0x0051, B:31:0x0057), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String extractErrorDetail(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = ""
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L68
            r1 = r10
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient r1 = (com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient) r1     // Catch: java.lang.Throwable -> L68
            r2 = 0
            com.google.gson.JsonElement r3 = com.google.gson.JsonParser.parseString(r11)     // Catch: java.lang.Throwable -> L68
            boolean r4 = r3.isJsonObject()     // Catch: java.lang.Throwable -> L68
            if (r4 != 0) goto L14
            r5 = r0
            goto L63
        L14:
            com.google.gson.JsonObject r4 = r3.getAsJsonObject()     // Catch: java.lang.Throwable -> L68
            java.lang.String r5 = "error"
            com.google.gson.JsonElement r4 = r4.get(r5)     // Catch: java.lang.Throwable -> L68
            r5 = 0
            if (r4 == 0) goto L35
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Throwable -> L68
            r6 = r4
            r7 = 0
            boolean r8 = r6.isJsonObject()     // Catch: java.lang.Throwable -> L68
            if (r8 == 0) goto L2d
            goto L2e
        L2d:
            r4 = r5
        L2e:
            if (r4 == 0) goto L35
            com.google.gson.JsonObject r4 = r4.getAsJsonObject()     // Catch: java.lang.Throwable -> L68
            goto L36
        L35:
            r4 = r5
        L36:
            if (r4 != 0) goto L3a
            r5 = r0
            goto L63
        L3a:
            java.lang.String r6 = "message"
            com.google.gson.JsonElement r6 = r4.get(r6)     // Catch: java.lang.Throwable -> L68
            if (r6 == 0) goto L60
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Throwable -> L68
            r7 = r6
            r8 = 0
            boolean r9 = r7.isJsonPrimitive()     // Catch: java.lang.Throwable -> L68
            if (r9 == 0) goto L4e
            goto L4f
        L4e:
            r6 = r5
        L4f:
            if (r6 == 0) goto L60
            java.lang.String r6 = r6.getAsString()     // Catch: java.lang.Throwable -> L68
            if (r6 == 0) goto L60
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Throwable -> L68
            r5 = 200(0xc8, float:2.8E-43)
            java.lang.String r5 = kotlin.text.StringsKt.take(r6, r5)     // Catch: java.lang.Throwable -> L68
        L60:
            if (r5 != 0) goto L63
            r5 = r0
        L63:
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r5)     // Catch: java.lang.Throwable -> L68
            goto L73
        L68:
            r1 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m6717constructorimpl(r1)
        L73:
            boolean r2 = kotlin.Result.m6723isFailureimpl(r1)
            if (r2 == 0) goto L7a
            goto L7b
        L7a:
            r0 = r1
        L7b:
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.extractErrorDetail(java.lang.String):java.lang.String");
    }

    private final Long parseRetryDelay(String body) {
        List<String> groupValues;
        String str;
        Double doubleOrNull;
        MatchResult find$default = Regex.find$default(new Regex("retry in ([0-9.]+)\\s*s"), body, 0, 2, null);
        if (find$default == null || (groupValues = find$default.getGroupValues()) == null || (str = groupValues.get(1)) == null || (doubleOrNull = StringsKt.toDoubleOrNull(str)) == null) {
            return null;
        }
        double it = doubleOrNull.doubleValue();
        return Long.valueOf((long) (1000 * it));
    }

    private final Long parseRetryAfter(String header) {
        String obj;
        Double doubleOrNull;
        if (header == null || (obj = StringsKt.trim((CharSequence) header).toString()) == null || (doubleOrNull = StringsKt.toDoubleOrNull(obj)) == null) {
            return null;
        }
        double it = doubleOrNull.doubleValue();
        return Long.valueOf((long) (1000 * it));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.streamsync.app.subtitle.autosync.TimedWord> parseWords(java.lang.String r34, long r35) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.parseWords(java.lang.String, long):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GeminiRestTranscribeClient.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\tJ0\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$RawWord;", "", "text", "", "startMs", "", "endMs", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "getEndMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStartMs", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lcom/streamsync/app/subtitle/autosync/GeminiRestTranscribeClient$RawWord;", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes19.dex */
    public static final /* data */ class RawWord {
        private final Long endMs;
        private final Long startMs;
        private final String text;

        public static /* synthetic */ RawWord copy$default(RawWord rawWord, String str, Long l, Long l2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = rawWord.text;
            }
            if ((i & 2) != 0) {
                l = rawWord.startMs;
            }
            if ((i & 4) != 0) {
                l2 = rawWord.endMs;
            }
            return rawWord.copy(str, l, l2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* renamed from: component2, reason: from getter */
        public final Long getStartMs() {
            return this.startMs;
        }

        /* renamed from: component3, reason: from getter */
        public final Long getEndMs() {
            return this.endMs;
        }

        public final RawWord copy(String text, Long startMs, Long endMs) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new RawWord(text, startMs, endMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RawWord)) {
                return false;
            }
            RawWord rawWord = (RawWord) other;
            return Intrinsics.areEqual(this.text, rawWord.text) && Intrinsics.areEqual(this.startMs, rawWord.startMs) && Intrinsics.areEqual(this.endMs, rawWord.endMs);
        }

        public int hashCode() {
            return (((this.text.hashCode() * 31) + (this.startMs == null ? 0 : this.startMs.hashCode())) * 31) + (this.endMs != null ? this.endMs.hashCode() : 0);
        }

        public String toString() {
            return "RawWord(text=" + this.text + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ")";
        }

        public RawWord(String text, Long startMs, Long endMs) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
            this.startMs = startMs;
            this.endMs = endMs;
        }

        public final Long getEndMs() {
            return this.endMs;
        }

        public final Long getStartMs() {
            return this.startMs;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void collectWords(com.google.gson.JsonElement r8, java.util.List<com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.RawWord> r9, int r10) {
        /*
            r7 = this;
            r0 = 12
            if (r10 <= r0) goto L5
            return
        L5:
            boolean r0 = r8.isJsonObject()
            if (r0 == 0) goto L96
            com.google.gson.JsonObject r0 = r8.getAsJsonObject()
            java.lang.String r1 = "type"
            com.google.gson.JsonElement r1 = r0.get(r1)
            r2 = 0
            if (r1 == 0) goto L2a
            r3 = r1
            r4 = 0
            boolean r3 = r3.isJsonPrimitive()
            if (r3 == 0) goto L22
            goto L23
        L22:
            r1 = r2
        L23:
            if (r1 == 0) goto L2a
            java.lang.String r1 = r1.getAsString()
            goto L2b
        L2a:
            r1 = r2
        L2b:
            java.lang.String r3 = "word_info"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r3)
            if (r3 == 0) goto L70
            java.lang.String r3 = "text"
            com.google.gson.JsonElement r3 = r0.get(r3)
            if (r3 == 0) goto L4b
            r4 = r3
            r5 = 0
            boolean r4 = r4.isJsonPrimitive()
            if (r4 == 0) goto L44
            goto L45
        L44:
            r3 = r2
        L45:
            if (r3 == 0) goto L4b
            java.lang.String r2 = r3.getAsString()
        L4b:
            if (r2 != 0) goto L4f
            java.lang.String r2 = ""
        L4f:
            r3 = r9
            java.util.Collection r3 = (java.util.Collection) r3
            com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$RawWord r4 = new com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient$RawWord
            java.lang.String r5 = "start_offset"
            com.google.gson.JsonElement r5 = r0.get(r5)
            java.lang.Long r5 = r7.parseOffset(r5)
            java.lang.String r6 = "end_offset"
            com.google.gson.JsonElement r6 = r0.get(r6)
            java.lang.Long r6 = r7.parseOffset(r6)
            r4.<init>(r2, r5, r6)
            r3.add(r4)
            return
        L70:
            java.util.Set r2 = r0.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L78:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lb9
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.Object r3 = r3.getValue()
            com.google.gson.JsonElement r3 = (com.google.gson.JsonElement) r3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r4 = r10 + 1
            r7.collectWords(r3, r9, r4)
            goto L78
        L96:
            boolean r0 = r8.isJsonArray()
            if (r0 == 0) goto Lb9
            com.google.gson.JsonArray r0 = r8.getAsJsonArray()
            java.util.Iterator r0 = r0.iterator()
        La4:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lb9
            java.lang.Object r1 = r0.next()
            com.google.gson.JsonElement r1 = (com.google.gson.JsonElement) r1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r2 = r10 + 1
            r7.collectWords(r1, r9, r2)
            goto La4
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.collectWords(com.google.gson.JsonElement, java.util.List, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Long parseOffset(com.google.gson.JsonElement r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto L15
            r1 = r8
            r2 = 0
            boolean r1 = r1.isJsonPrimitive()
            if (r1 == 0) goto Ld
            r1 = r8
            goto Le
        Ld:
            r1 = r0
        Le:
            if (r1 == 0) goto L15
            java.lang.String r1 = r1.getAsString()
            goto L16
        L15:
            r1 = r0
        L16:
            if (r1 != 0) goto L19
            return r0
        L19:
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.CharSequence r2 = kotlin.text.StringsKt.trim(r2)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "ms"
            r4 = 0
            r5 = 2
            boolean r3 = kotlin.text.StringsKt.endsWith$default(r2, r3, r4, r5, r0)
            if (r3 == 0) goto L4d
            java.lang.String r3 = kotlin.text.StringsKt.dropLast(r2, r5)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim(r3)
            java.lang.String r3 = r3.toString()
            java.lang.Double r3 = kotlin.text.StringsKt.toDoubleOrNull(r3)
            if (r3 == 0) goto L8f
            double r3 = r3.doubleValue()
            long r3 = (long) r3
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            goto L8f
        L4d:
            java.lang.String r3 = "s"
            boolean r3 = kotlin.text.StringsKt.endsWith$default(r2, r3, r4, r5, r0)
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r3 == 0) goto L7b
            r3 = 1
            java.lang.String r3 = kotlin.text.StringsKt.dropLast(r2, r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim(r3)
            java.lang.String r3 = r3.toString()
            java.lang.Double r3 = kotlin.text.StringsKt.toDoubleOrNull(r3)
            if (r3 == 0) goto L8f
            java.lang.Number r3 = (java.lang.Number) r3
            double r5 = r3.doubleValue()
            r0 = 0
            double r3 = (double) r4
            double r3 = r3 * r5
            long r3 = (long) r3
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            goto L8f
        L7b:
            java.lang.Double r3 = kotlin.text.StringsKt.toDoubleOrNull(r2)
            if (r3 == 0) goto L8f
            java.lang.Number r3 = (java.lang.Number) r3
            double r5 = r3.doubleValue()
            r0 = 0
            double r3 = (double) r4
            double r3 = r3 * r5
            long r3 = (long) r3
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
        L8f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.autosync.GeminiRestTranscribeClient.parseOffset(com.google.gson.JsonElement):java.lang.Long");
    }
}
