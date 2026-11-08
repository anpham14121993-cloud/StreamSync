package com.streamsync.app.data.addon;

import androidx.media3.common.MimeTypes;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.streamsync.app.data.model.SubtitleTrack;
import com.streamsync.app.settings.BufferCeiling;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: AddonClient.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002\u001a\u0012\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u001a\f\u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0000\u001a \u0010\u000b\u001a\u00020\f*\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"LANGUAGE_LABELS", "", "", "guessStreamMimeType", "url", "hint", "normalizeAddonUrl", "input", "normalizeMimeType", "raw", "pathEncode", "toTrack", "Lcom/streamsync/app/data/model/SubtitleTrack;", "Lcom/streamsync/app/data/addon/AddonSubtitle;", "fromStream", "", "addonName", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonClientKt {
    private static final Map<String, String> LANGUAGE_LABELS = MapsKt.mapOf(TuplesKt.to("vi", "Tiếng Việt"), TuplesKt.to(TranslateLanguage.ENGLISH, "English"), TuplesKt.to(TranslateLanguage.CHINESE, "Tiếng Trung"), TuplesKt.to(TranslateLanguage.JAPANESE, "Tiếng Nhật"), TuplesKt.to(TranslateLanguage.KOREAN, "Tiếng Hàn"), TuplesKt.to(TranslateLanguage.FRENCH, "Tiếng Pháp"), TuplesKt.to(TranslateLanguage.SPANISH, "Tiếng Tây Ban Nha"), TuplesKt.to(TranslateLanguage.GERMAN, "Tiếng Đức"), TuplesKt.to(TranslateLanguage.PORTUGUESE, "Tiếng Bồ Đào Nha"), TuplesKt.to(TranslateLanguage.RUSSIAN, "Tiếng Nga"), TuplesKt.to("und", "Không rõ ngôn ngữ"));

    public static final String normalizeAddonUrl(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        String url = StringsKt.trim((CharSequence) input).toString();
        if (!(url.length() > 0)) {
            throw new IllegalArgumentException("URL addon rỗng".toString());
        }
        if (!StringsKt.startsWith$default(url, "http://", false, 2, (Object) null) && !StringsKt.startsWith$default(url, "https://", false, 2, (Object) null)) {
            url = "https://" + url;
        }
        String url2 = StringsKt.substringBefore$default(StringsKt.substringBefore$default(url, '?', (String) null, 2, (Object) null), '#', (String) null, 2, (Object) null);
        if (StringsKt.endsWith$default(url2, "/manifest.json", false, 2, (Object) null)) {
            url2 = StringsKt.removeSuffix(url2, (CharSequence) "/manifest.json");
        } else if (StringsKt.endsWith$default(url2, ".json", false, 2, (Object) null)) {
            url2 = StringsKt.substringBeforeLast$default(url2, '/', (String) null, 2, (Object) null);
        }
        return StringsKt.trimEnd(url2, '/');
    }

    public static /* synthetic */ String guessStreamMimeType$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return guessStreamMimeType(str, str2);
    }

    public static final String guessStreamMimeType(String url, String hint) {
        Intrinsics.checkNotNullParameter(url, "url");
        String it = normalizeMimeType(hint);
        if (it != null) {
            return it;
        }
        String lower = StringsKt.substringBefore$default(url, '#', (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        String path = StringsKt.substringBefore$default(lower, '?', (String) null, 2, (Object) null);
        if (StringsKt.contains$default((CharSequence) lower, (CharSequence) ".m3u8", false, 2, (Object) null)) {
            return MimeTypes.APPLICATION_M3U8;
        }
        if (StringsKt.contains$default((CharSequence) lower, (CharSequence) ".mpd", false, 2, (Object) null)) {
            return MimeTypes.APPLICATION_MPD;
        }
        if (StringsKt.endsWith$default(path, ".mp4", false, 2, (Object) null) || StringsKt.endsWith$default(path, ".m4v", false, 2, (Object) null)) {
            return MimeTypes.APPLICATION_MP4;
        }
        if (StringsKt.endsWith$default(path, ".ts", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_MP2T;
        }
        if (StringsKt.endsWith$default(path, ".webm", false, 2, (Object) null)) {
            return MimeTypes.VIDEO_WEBM;
        }
        return null;
    }

    public static final String normalizeMimeType(String raw) {
        String substringBefore$default;
        String obj;
        if (raw != null && (substringBefore$default = StringsKt.substringBefore$default(raw, ';', (String) null, 2, (Object) null)) != null && (obj = StringsKt.trim((CharSequence) substringBefore$default).toString()) != null) {
            String value = obj.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(value, "toLowerCase(...)");
            if (value != null) {
                if (!(value.length() > 0)) {
                    value = null;
                }
                if (value != null) {
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "mpegurl", false, 2, (Object) null)) {
                        return MimeTypes.APPLICATION_M3U8;
                    }
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "dash+xml", false, 2, (Object) null)) {
                        return MimeTypes.APPLICATION_MPD;
                    }
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "mp4", false, 2, (Object) null)) {
                        return MimeTypes.APPLICATION_MP4;
                    }
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "mp2t", false, 2, (Object) null)) {
                        return MimeTypes.VIDEO_MP2T;
                    }
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "webm", false, 2, (Object) null)) {
                        return MimeTypes.VIDEO_WEBM;
                    }
                    if (StringsKt.contains$default((CharSequence) value, (CharSequence) "matroska", false, 2, (Object) null)) {
                        return MimeTypes.VIDEO_MATROSKA;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ SubtitleTrack toTrack$default(AddonSubtitle addonSubtitle, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return toTrack(addonSubtitle, z, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.streamsync.app.data.model.SubtitleTrack toTrack(com.streamsync.app.data.addon.AddonSubtitle r12, boolean r13, java.lang.String r14) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = r12.getLang()
            java.lang.String r1 = ""
            if (r0 != 0) goto Le
            r0 = r1
        Le:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r2 = r0.length()
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L1a
            r2 = r3
            goto L1b
        L1a:
            r2 = r4
        L1b:
            if (r2 == 0) goto L21
            r0 = 0
            java.lang.String r0 = "und"
        L21:
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = r12.getName()
            r5 = 0
            if (r2 == 0) goto L47
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.CharSequence r2 = kotlin.text.StringsKt.trim(r2)
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L47
            r6 = r2
            r7 = 0
            r8 = r6
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            int r8 = r8.length()
            if (r8 <= 0) goto L42
            goto L43
        L42:
            r3 = r4
        L43:
            if (r3 == 0) goto L47
            r9 = r2
            goto L48
        L47:
            r9 = r5
        L48:
            com.streamsync.app.data.model.SubtitleTrack r2 = new com.streamsync.app.data.model.SubtitleTrack
            java.util.Map<java.lang.String, java.lang.String> r3 = com.streamsync.app.data.addon.AddonClientKt.LANGUAGE_LABELS
            java.util.Locale r4 = java.util.Locale.ROOT
            java.lang.String r4 = r0.toLowerCase(r4)
            java.lang.String r5 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L62
            r7 = r0
            goto L63
        L62:
            r7 = r3
        L63:
            java.lang.String r3 = r12.getUrl()
            if (r3 != 0) goto L6b
            r8 = r1
            goto L6c
        L6b:
            r8 = r3
        L6c:
            r5 = r2
            r6 = r0
            r10 = r14
            r11 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonClientKt.toTrack(com.streamsync.app.data.addon.AddonSubtitle, boolean, java.lang.String):com.streamsync.app.data.model.SubtitleTrack");
    }

    public static final String pathEncode(String $this$pathEncode) {
        Intrinsics.checkNotNullParameter($this$pathEncode, "<this>");
        String encode = URLEncoder.encode($this$pathEncode, Charsets.UTF_8.name());
        Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
        return StringsKt.replace$default(StringsKt.replace$default(encode, "+", "%20", false, 4, (Object) null), "%3A", ":", false, 4, (Object) null);
    }
}
