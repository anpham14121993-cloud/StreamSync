package com.streamsync.app.data.repository;

import androidx.exifinterface.media.ExifInterface;
import com.streamsync.app.data.addon.AddonClient;
import com.streamsync.app.data.addon.AddonMeta;
import com.streamsync.app.data.addon.AddonVideo;
import com.streamsync.app.data.model.StreamSource;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: AddonRepository.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a^\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\b\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\b2\u0006\u0010\u000e\u001a\u00020\u00012$\u0010\u000f\u001a \b\u0001\u0012\u0004\u0012\u0002H\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000b0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0010H\u0082@¢\u0006\u0002\u0010\u0013\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0000\u001a\n\u0010\u0019\u001a\u00020\u001a*\u00020\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"MAX_PARALLEL_ADDONS", "", "MAX_PARALLEL_CATALOGS", "MAX_SEARCH_ROWS", "PER_CATALOG_SEARCH_CAP", "STREAM_ADDON_HARD_LIMIT_MS", "", "SUBTITLE_EXTENSIONS", "", "", "mapParallelOrdered", "R", ExifInterface.GPS_DIRECTION_TRUE, "items", "limit", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/util/List;ILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSeriesWithEpisodes", "", "Lcom/streamsync/app/data/addon/AddonMeta;", "mergeSeriesEpisodesFrom", "full", "withMimeTypeFromHints", "Lcom/streamsync/app/data/model/StreamSource;", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class AddonRepositoryKt {
    private static final int MAX_PARALLEL_ADDONS = 6;
    private static final int MAX_PARALLEL_CATALOGS = 8;
    private static final int MAX_SEARCH_ROWS = 8;
    private static final int PER_CATALOG_SEARCH_CAP = 30;
    private static final long STREAM_ADDON_HARD_LIMIT_MS = 8000;
    private static final List<String> SUBTITLE_EXTENSIONS = CollectionsKt.listOf((Object[]) new String[]{".vtt", ".srt", ".ass", ".ssa", ".smi", ".sub", ".ttml", ".dfxp"});

    public static final <T, R> Object mapParallelOrdered(List<? extends T> list, int limit, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super List<? extends R>> continuation) {
        return CoroutineScopeKt.coroutineScope(new AddonRepositoryKt$mapParallelOrdered$2(limit, list, function2, null), continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
    
        if (r0 == null) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.streamsync.app.data.addon.AddonMeta mergeSeriesEpisodesFrom(com.streamsync.app.data.addon.AddonMeta r30, com.streamsync.app.data.addon.AddonMeta r31) {
        /*
            r13 = r30
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "full"
            r12 = r31
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.util.List r0 = r30.getVideos()
            java.util.Collection r0 = (java.util.Collection) r0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L21
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L1f
            goto L21
        L1f:
            r0 = r1
            goto L22
        L21:
            r0 = r2
        L22:
            if (r0 != 0) goto L25
            return r13
        L25:
            java.util.List r0 = r31.getVideos()
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L33
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L34
        L33:
            r1 = r2
        L34:
            if (r1 == 0) goto L37
            return r13
        L37:
            java.util.List r28 = r31.getVideos()
            java.lang.String r0 = r31.getName()
            if (r0 == 0) goto L51
            r1 = r0
            r3 = 0
            r4 = r1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            r2 = r2 ^ r4
            if (r2 == 0) goto L4e
            goto L4f
        L4e:
            r0 = 0
        L4f:
            if (r0 != 0) goto L55
        L51:
            java.lang.String r0 = r30.getName()
        L55:
            r3 = r0
            java.lang.String r0 = r30.getDescription()
            if (r0 != 0) goto L60
            java.lang.String r0 = r31.getDescription()
        L60:
            r6 = r0
            java.lang.String r0 = r30.getBackground()
            if (r0 != 0) goto L6b
            java.lang.String r0 = r31.getBackground()
        L6b:
            r7 = r0
            java.lang.String r0 = r30.getImdbRating()
            if (r0 != 0) goto L76
            java.lang.String r0 = r31.getImdbRating()
        L76:
            r29 = r0
            java.lang.String r0 = r30.getReleaseInfo()
            if (r0 != 0) goto L82
            java.lang.String r0 = r31.getReleaseInfo()
        L82:
            r10 = r0
            r26 = 33547675(0x1ffe59b, float:9.4001674E-38)
            r27 = 0
            r1 = 0
            r2 = 0
            r4 = 0
            r5 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r0 = r30
            r12 = r29
            r13 = r28
            com.streamsync.app.data.addon.AddonMeta r0 = com.streamsync.app.data.addon.AddonMeta.copy$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.repository.AddonRepositoryKt.mergeSeriesEpisodesFrom(com.streamsync.app.data.addon.AddonMeta, com.streamsync.app.data.addon.AddonMeta):com.streamsync.app.data.addon.AddonMeta");
    }

    public static final boolean isSeriesWithEpisodes(AddonMeta $this$isSeriesWithEpisodes) {
        Intrinsics.checkNotNullParameter($this$isSeriesWithEpisodes, "<this>");
        List<AddonVideo> videos = $this$isSeriesWithEpisodes.getVideos();
        return !(videos == null || videos.isEmpty());
    }

    public static final StreamSource withMimeTypeFromHints(StreamSource $this$withMimeTypeFromHints) {
        String guessed;
        StreamSource copy;
        Intrinsics.checkNotNullParameter($this$withMimeTypeFromHints, "<this>");
        String mimeType = $this$withMimeTypeFromHints.getMimeType();
        if (!(mimeType == null || StringsKt.isBlank(mimeType)) || (guessed = AddonClient.INSTANCE.guessMimeTypeFromUrl($this$withMimeTypeFromHints.getStreamUrl())) == null) {
            return $this$withMimeTypeFromHints;
        }
        copy = $this$withMimeTypeFromHints.copy((r34 & 1) != 0 ? $this$withMimeTypeFromHints.mediaId : null, (r34 & 2) != 0 ? $this$withMimeTypeFromHints.streamUrl : null, (r34 & 4) != 0 ? $this$withMimeTypeFromHints.mimeType : guessed, (r34 & 8) != 0 ? $this$withMimeTypeFromHints.subtitles : null, (r34 & 16) != 0 ? $this$withMimeTypeFromHints.name : null, (r34 & 32) != 0 ? $this$withMimeTypeFromHints.quality : null, (r34 & 64) != 0 ? $this$withMimeTypeFromHints.provider : null, (r34 & 128) != 0 ? $this$withMimeTypeFromHints.spec : null, (r34 & 256) != 0 ? $this$withMimeTypeFromHints.size : null, (r34 & 512) != 0 ? $this$withMimeTypeFromHints.qualityValue : 0, (r34 & 1024) != 0 ? $this$withMimeTypeFromHints.addonId : null, (r34 & 2048) != 0 ? $this$withMimeTypeFromHints.addonName : null, (r34 & 4096) != 0 ? $this$withMimeTypeFromHints.addonLogo : null, (r34 & 8192) != 0 ? $this$withMimeTypeFromHints.releaseName : null, (r34 & 16384) != 0 ? $this$withMimeTypeFromHints.requestHeaders : null, (r34 & 32768) != 0 ? $this$withMimeTypeFromHints.index : 0);
        return copy;
    }
}
