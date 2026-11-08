package com.streamsync.app.subtitle;

import androidx.core.os.EnvironmentCompat;
import androidx.core.text.util.LocalePreferences;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import com.streamsync.app.data.model.SubtitleCue;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.tasks.TasksKt;

/* compiled from: SubtitleTranslator.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0086@¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ.\u0010\u001b\u001a\u00020\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u0086@¢\u0006\u0002\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/streamsync/app/subtitle/SubtitleTranslator;", "", "sourceLangTag", "", "targetLangTag", "(Ljava/lang/String;Ljava/lang/String;)V", "isNeeded", "", "()Z", "translator", "Lcom/google/mlkit/nl/translate/Translator;", "close", "", "()Lkotlin/Unit;", "ensureModelDownloaded", "requireWifi", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translate", "text", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "translateAllInBackground", "Lkotlinx/coroutines/Job;", "cues", "", "Lcom/streamsync/app/data/model/SubtitleCue;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "translateUpcoming", "currentPositionMs", "", "lookAheadMs", "(Ljava/util/List;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class SubtitleTranslator {
    private final boolean isNeeded;
    private final Translator translator;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Set<String> UNKNOWN_TAGS = SetsKt.setOf((Object[]) new String[]{"und", EnvironmentCompat.MEDIA_UNKNOWN, "zxx", "mis", "mul", "none", "null"});
    private static final Map<String, String> LANGUAGE_NAMES = MapsKt.mapOf(TuplesKt.to("afrikaans", TranslateLanguage.AFRIKAANS), TuplesKt.to("albanian", TranslateLanguage.ALBANIAN), TuplesKt.to("arabic", TranslateLanguage.ARABIC), TuplesKt.to("belarusian", TranslateLanguage.BELARUSIAN), TuplesKt.to("bengali", TranslateLanguage.BENGALI), TuplesKt.to("bulgarian", TranslateLanguage.BULGARIAN), TuplesKt.to("catalan", TranslateLanguage.CATALAN), TuplesKt.to(LocalePreferences.CalendarType.CHINESE, TranslateLanguage.CHINESE), TuplesKt.to("croatian", TranslateLanguage.CROATIAN), TuplesKt.to("czech", TranslateLanguage.CZECH), TuplesKt.to("danish", TranslateLanguage.DANISH), TuplesKt.to("dutch", TranslateLanguage.DUTCH), TuplesKt.to("english", TranslateLanguage.ENGLISH), TuplesKt.to("esperanto", TranslateLanguage.ESPERANTO), TuplesKt.to("estonian", TranslateLanguage.ESTONIAN), TuplesKt.to("finnish", TranslateLanguage.FINNISH), TuplesKt.to("french", TranslateLanguage.FRENCH), TuplesKt.to("galician", TranslateLanguage.GALICIAN), TuplesKt.to("georgian", TranslateLanguage.GEORGIAN), TuplesKt.to("german", TranslateLanguage.GERMAN), TuplesKt.to("greek", TranslateLanguage.GREEK), TuplesKt.to("gujarati", TranslateLanguage.GUJARATI), TuplesKt.to("haitian creole", TranslateLanguage.HAITIAN_CREOLE), TuplesKt.to(LocalePreferences.CalendarType.HEBREW, TranslateLanguage.HEBREW), TuplesKt.to("hindi", TranslateLanguage.HINDI), TuplesKt.to("hungarian", TranslateLanguage.HUNGARIAN), TuplesKt.to("icelandic", TranslateLanguage.ICELANDIC), TuplesKt.to("indonesian", "id"), TuplesKt.to("irish", TranslateLanguage.IRISH), TuplesKt.to("italian", TranslateLanguage.ITALIAN), TuplesKt.to("japanese", TranslateLanguage.JAPANESE), TuplesKt.to("kannada", TranslateLanguage.KANNADA), TuplesKt.to("korean", TranslateLanguage.KOREAN), TuplesKt.to("latvian", TranslateLanguage.LATVIAN), TuplesKt.to("lithuanian", TranslateLanguage.LITHUANIAN), TuplesKt.to("macedonian", TranslateLanguage.MACEDONIAN), TuplesKt.to("malay", TranslateLanguage.MALAY), TuplesKt.to("maltese", TranslateLanguage.MALTESE), TuplesKt.to("marathi", TranslateLanguage.MARATHI), TuplesKt.to("norwegian", TranslateLanguage.NORWEGIAN), TuplesKt.to(LocalePreferences.CalendarType.PERSIAN, TranslateLanguage.PERSIAN), TuplesKt.to("polish", TranslateLanguage.POLISH), TuplesKt.to("portuguese", TranslateLanguage.PORTUGUESE), TuplesKt.to("romanian", TranslateLanguage.ROMANIAN), TuplesKt.to("russian", TranslateLanguage.RUSSIAN), TuplesKt.to("slovak", TranslateLanguage.SLOVAK), TuplesKt.to("slovenian", TranslateLanguage.SLOVENIAN), TuplesKt.to("spanish", TranslateLanguage.SPANISH), TuplesKt.to("swahili", TranslateLanguage.SWAHILI), TuplesKt.to("swedish", TranslateLanguage.SWEDISH), TuplesKt.to("tagalog", TranslateLanguage.TAGALOG), TuplesKt.to("tamil", TranslateLanguage.TAMIL), TuplesKt.to("telugu", TranslateLanguage.TELUGU), TuplesKt.to("thai", TranslateLanguage.THAI), TuplesKt.to("turkish", TranslateLanguage.TURKISH), TuplesKt.to("ukrainian", TranslateLanguage.UKRAINIAN), TuplesKt.to("urdu", TranslateLanguage.URDU), TuplesKt.to("vietnamese", "vi"), TuplesKt.to("welsh", TranslateLanguage.WELSH));
    private static final Map<String, String> ISO_639_2_TO_1 = MapsKt.mapOf(TuplesKt.to("afr", TranslateLanguage.AFRIKAANS), TuplesKt.to("alb", TranslateLanguage.ALBANIAN), TuplesKt.to("amh", "am"), TuplesKt.to("ara", TranslateLanguage.ARABIC), TuplesKt.to("arm", "hy"), TuplesKt.to("aze", "az"), TuplesKt.to("bel", TranslateLanguage.BELARUSIAN), TuplesKt.to("ben", TranslateLanguage.BENGALI), TuplesKt.to("bod", "bo"), TuplesKt.to("bre", "br"), TuplesKt.to("bul", TranslateLanguage.BULGARIAN), TuplesKt.to("cat", TranslateLanguage.CATALAN), TuplesKt.to("ces", TranslateLanguage.CZECH), TuplesKt.to("chi", TranslateLanguage.CHINESE), TuplesKt.to("cze", TranslateLanguage.CZECH), TuplesKt.to("dan", TranslateLanguage.DANISH), TuplesKt.to("deu", TranslateLanguage.GERMAN), TuplesKt.to("dut", TranslateLanguage.DUTCH), TuplesKt.to("ell", TranslateLanguage.GREEK), TuplesKt.to("eng", TranslateLanguage.ENGLISH), TuplesKt.to("epo", TranslateLanguage.ESPERANTO), TuplesKt.to("est", TranslateLanguage.ESTONIAN), TuplesKt.to("eus", "eu"), TuplesKt.to("fas", TranslateLanguage.PERSIAN), TuplesKt.to("fil", TranslateLanguage.TAGALOG), TuplesKt.to("fin", TranslateLanguage.FINNISH), TuplesKt.to("fra", TranslateLanguage.FRENCH), TuplesKt.to("fre", TranslateLanguage.FRENCH), TuplesKt.to("geo", TranslateLanguage.GEORGIAN), TuplesKt.to("ger", TranslateLanguage.GERMAN), TuplesKt.to("gle", TranslateLanguage.IRISH), TuplesKt.to("glg", TranslateLanguage.GALICIAN), TuplesKt.to("gre", TranslateLanguage.GREEK), TuplesKt.to("guj", TranslateLanguage.GUJARATI), TuplesKt.to("hat", TranslateLanguage.HAITIAN_CREOLE), TuplesKt.to("hau", "ha"), TuplesKt.to("heb", TranslateLanguage.HEBREW), TuplesKt.to("hin", TranslateLanguage.HINDI), TuplesKt.to("hrv", TranslateLanguage.CROATIAN), TuplesKt.to("hun", TranslateLanguage.HUNGARIAN), TuplesKt.to("hye", "hy"), TuplesKt.to("ice", TranslateLanguage.ICELANDIC), TuplesKt.to("ind", "id"), TuplesKt.to("isl", TranslateLanguage.ICELANDIC), TuplesKt.to("ita", TranslateLanguage.ITALIAN), TuplesKt.to("jpn", TranslateLanguage.JAPANESE), TuplesKt.to("kan", TranslateLanguage.KANNADA), TuplesKt.to("kor", TranslateLanguage.KOREAN), TuplesKt.to("lav", TranslateLanguage.LATVIAN), TuplesKt.to("lit", TranslateLanguage.LITHUANIAN), TuplesKt.to("mkd", TranslateLanguage.MACEDONIAN), TuplesKt.to("mal", "ml"), TuplesKt.to("mar", TranslateLanguage.MARATHI), TuplesKt.to("mlt", TranslateLanguage.MALTESE), TuplesKt.to(LocalePreferences.FirstDayOfWeek.MONDAY, "mn"), TuplesKt.to("mri", "mi"), TuplesKt.to("msa", TranslateLanguage.MALAY), TuplesKt.to("nep", "ne"), TuplesKt.to("nld", TranslateLanguage.DUTCH), TuplesKt.to(CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, TranslateLanguage.NORWEGIAN), TuplesKt.to("nno", "nn"), TuplesKt.to("per", TranslateLanguage.PERSIAN), TuplesKt.to("pol", TranslateLanguage.POLISH), TuplesKt.to("por", TranslateLanguage.PORTUGUESE), TuplesKt.to("ron", TranslateLanguage.ROMANIAN), TuplesKt.to("rum", TranslateLanguage.ROMANIAN), TuplesKt.to("rus", TranslateLanguage.RUSSIAN), TuplesKt.to("sin", "si"), TuplesKt.to("slk", TranslateLanguage.SLOVAK), TuplesKt.to("slv", TranslateLanguage.SLOVENIAN), TuplesKt.to("sme", "se"), TuplesKt.to("spa", TranslateLanguage.SPANISH), TuplesKt.to("srp", "sr"), TuplesKt.to("swa", TranslateLanguage.SWAHILI), TuplesKt.to("swe", TranslateLanguage.SWEDISH), TuplesKt.to("tam", TranslateLanguage.TAMIL), TuplesKt.to("tel", TranslateLanguage.TELUGU), TuplesKt.to("tgl", TranslateLanguage.TAGALOG), TuplesKt.to("tha", TranslateLanguage.THAI), TuplesKt.to("tur", TranslateLanguage.TURKISH), TuplesKt.to("ukr", TranslateLanguage.UKRAINIAN), TuplesKt.to("urd", TranslateLanguage.URDU), TuplesKt.to("vie", "vi"), TuplesKt.to("zho", TranslateLanguage.CHINESE));

    /* JADX WARN: Multi-variable type inference failed */
    public SubtitleTranslator(String sourceLangTag, String targetLangTag) {
        Translator translator;
        Intrinsics.checkNotNullParameter(sourceLangTag, "sourceLangTag");
        Intrinsics.checkNotNullParameter(targetLangTag, "targetLangTag");
        String resolveLanguage = Companion.resolveLanguage(sourceLangTag);
        String resolveLanguage2 = Companion.resolveLanguage(targetLangTag);
        if (resolveLanguage != null && resolveLanguage2 != null && !Intrinsics.areEqual(resolveLanguage, resolveLanguage2)) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SubtitleTranslator subtitleTranslator = this;
                translator = Result.m6717constructorimpl(Translation.getClient(new TranslatorOptions.Builder().setSourceLanguage(resolveLanguage).setTargetLanguage(resolveLanguage2).build()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                translator = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            r2 = Result.m6723isFailureimpl(translator) ? null : translator;
        }
        this.translator = r2;
        this.isNeeded = this.translator != null;
    }

    /* renamed from: isNeeded, reason: from getter */
    public final boolean getIsNeeded() {
        return this.isNeeded;
    }

    public static /* synthetic */ Object ensureModelDownloaded$default(SubtitleTranslator subtitleTranslator, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return subtitleTranslator.ensureModelDownloaded(z, continuation);
    }

    public final Object ensureModelDownloaded(boolean requireWifi, Continuation<? super Unit> continuation) {
        Translator client = this.translator;
        if (client == null) {
            return Unit.INSTANCE;
        }
        DownloadConditions.Builder $this$ensureModelDownloaded_u24lambda_u241 = new DownloadConditions.Builder();
        if (requireWifi) {
            $this$ensureModelDownloaded_u24lambda_u241.requireWifi();
        }
        DownloadConditions conditions = $this$ensureModelDownloaded_u24lambda_u241.build();
        Intrinsics.checkNotNullExpressionValue(conditions, "build(...)");
        Task<Void> downloadModelIfNeeded = client.downloadModelIfNeeded(conditions);
        Intrinsics.checkNotNullExpressionValue(downloadModelIfNeeded, "downloadModelIfNeeded(...)");
        Object await = TasksKt.await(downloadModelIfNeeded, continuation);
        return await == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? await : Unit.INSTANCE;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translate(java.lang.String r7, kotlin.coroutines.Continuation<? super java.lang.String> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.streamsync.app.subtitle.SubtitleTranslator$translate$1
            if (r0 == 0) goto L14
            r0 = r8
            com.streamsync.app.subtitle.SubtitleTranslator$translate$1 r0 = (com.streamsync.app.subtitle.SubtitleTranslator$translate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.streamsync.app.subtitle.SubtitleTranslator$translate$1 r0 = new com.streamsync.app.subtitle.SubtitleTranslator$translate$1
            r0.<init>(r6, r8)
        L19:
            r8 = r0
            java.lang.Object r0 = r8.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            switch(r2) {
                case 0: goto L39;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            r7 = 0
            java.lang.Object r1 = r8.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L37
            r3 = r0
            goto L5f
        L37:
            r7 = move-exception
            goto L6a
        L39:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r6
            com.google.mlkit.nl.translate.Translator r3 = r2.translator
            if (r3 != 0) goto L42
            return r7
        L42:
            kotlin.Result$Companion r4 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L66
            com.streamsync.app.subtitle.SubtitleTranslator r2 = (com.streamsync.app.subtitle.SubtitleTranslator) r2     // Catch: java.lang.Throwable -> L66
            r2 = 0
            com.google.android.gms.tasks.Task r4 = r3.translate(r7)     // Catch: java.lang.Throwable -> L66
            java.lang.String r3 = "translate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)     // Catch: java.lang.Throwable -> L66
            r8.L$0 = r7     // Catch: java.lang.Throwable -> L66
            r3 = 1
            r8.label = r3     // Catch: java.lang.Throwable -> L66
            java.lang.Object r3 = kotlinx.coroutines.tasks.TasksKt.await(r4, r8)     // Catch: java.lang.Throwable -> L66
            if (r3 != r1) goto L5d
            return r1
        L5d:
            r1 = r7
            r7 = r2
        L5f:
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L37
            java.lang.Object r7 = kotlin.Result.m6717constructorimpl(r3)     // Catch: java.lang.Throwable -> L37
            goto L74
        L66:
            r1 = move-exception
            r5 = r1
            r1 = r7
            r7 = r5
        L6a:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m6717constructorimpl(r7)
        L74:
            boolean r2 = kotlin.Result.m6723isFailureimpl(r7)
            if (r2 == 0) goto L7c
            r7 = r1
        L7c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.SubtitleTranslator.translate(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object translateUpcoming$default(SubtitleTranslator subtitleTranslator, List list, long j, long j2, Continuation continuation, int i, Object obj) {
        long j3;
        if ((i & 4) == 0) {
            j3 = j2;
        } else {
            j3 = 8000;
        }
        return subtitleTranslator.translateUpcoming(list, j, j3, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0076 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00f1 -> B:15:0x00fb). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translateUpcoming(java.util.List<com.streamsync.app.data.model.SubtitleCue> r26, long r27, long r29, kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.subtitle.SubtitleTranslator.translateUpcoming(java.util.List, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Job translateAllInBackground(List<SubtitleCue> cues, CoroutineScope scope) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(cues, "cues");
        Intrinsics.checkNotNullParameter(scope, "scope");
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getDefault(), null, new SubtitleTranslator$translateAllInBackground$1(cues, this, null), 2, null);
        return launch$default;
    }

    public final Unit close() {
        Translator translator = this.translator;
        if (translator == null) {
            return null;
        }
        translator.close();
        return Unit.INSTANCE;
    }

    /* compiled from: SubtitleTranslator.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0005R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/subtitle/SubtitleTranslator$Companion;", "", "()V", "ISO_639_2_TO_1", "", "", "getISO_639_2_TO_1", "()Ljava/util/Map;", "LANGUAGE_NAMES", "getLANGUAGE_NAMES", "UNKNOWN_TAGS", "", "getUNKNOWN_TAGS", "()Ljava/util/Set;", "resolveLanguage", "raw", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String resolveLanguage(String raw) {
            Intrinsics.checkNotNullParameter(raw, "raw");
            String tag = StringsKt.trim((CharSequence) raw).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(tag, "toLowerCase(...)");
            if ((tag.length() == 0) || getUNKNOWN_TAGS().contains(tag)) {
                return null;
            }
            String base = StringsKt.substringBefore$default(StringsKt.substringBefore$default(tag, '-', (String) null, 2, (Object) null), '_', (String) null, 2, (Object) null);
            String fromLanguageTag = TranslateLanguage.fromLanguageTag(base);
            if (fromLanguageTag != null) {
                return fromLanguageTag;
            }
            String str = getLANGUAGE_NAMES().get(base);
            if (str != null) {
                return str;
            }
            String str2 = getLANGUAGE_NAMES().get(StringsKt.replace$default(base, "_", " ", false, 4, (Object) null));
            return str2 == null ? getISO_639_2_TO_1().get(base) : str2;
        }

        public final Set<String> getUNKNOWN_TAGS() {
            return SubtitleTranslator.UNKNOWN_TAGS;
        }

        public final Map<String, String> getLANGUAGE_NAMES() {
            return SubtitleTranslator.LANGUAGE_NAMES;
        }

        public final Map<String, String> getISO_639_2_TO_1() {
            return SubtitleTranslator.ISO_639_2_TO_1;
        }
    }
}
