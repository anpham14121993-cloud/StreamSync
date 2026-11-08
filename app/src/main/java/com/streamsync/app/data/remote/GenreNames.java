package com.streamsync.app.data.remote;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: GenreNames.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/streamsync/app/data/remote/GenreNames;", "", "()V", "VIETNAMESE", "", "", "vietnamese", HintConstants.AUTOFILL_HINT_NAME, "vietnamize", "raw", "vietnamizeNames", "", "names", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class GenreNames {
    public static final GenreNames INSTANCE = new GenreNames();
    private static final Map<String, String> VIETNAMESE = MapsKt.mapOf(TuplesKt.to("action", "Hành động"), TuplesKt.to("adventure", "Phiêu lưu"), TuplesKt.to("animation", "Hoạt hình"), TuplesKt.to("comedy", "Hài kịch"), TuplesKt.to("crime", "Tội phạm"), TuplesKt.to("documentary", "Tài liệu"), TuplesKt.to("drama", "Chính kịch"), TuplesKt.to("family", "Gia đình"), TuplesKt.to("fantasy", "Giả tưởng"), TuplesKt.to("history", "Lịch sử"), TuplesKt.to("horror", "Kinh dị"), TuplesKt.to("music", "Âm nhạc"), TuplesKt.to("mystery", "Bí ẩn"), TuplesKt.to("romance", "Tình cảm"), TuplesKt.to("science fiction", "Viễn tưởng"), TuplesKt.to("sci-fi", "Viễn tưởng"), TuplesKt.to("scifi", "Viễn tưởng"), TuplesKt.to("thriller", "Hồi hộp"), TuplesKt.to("war", "Chiến tranh"), TuplesKt.to("western", "Viễn Tây"), TuplesKt.to("tv movie", "Phim truyền hình"), TuplesKt.to("action & adventure", "Hành động & Phiêu lưu"), TuplesKt.to("sci-fi & fantasy", "Viễn tưởng & Khoa học viễn tưởng"), TuplesKt.to("news", "Tin tức"), TuplesKt.to("reality", "Thực tế"), TuplesKt.to("soap", "Phim truyền hình nhiều tập"), TuplesKt.to("talk", "Chương trình trò chuyện"), TuplesKt.to("war & politics", "Chiến tranh & Chính trị"), TuplesKt.to("anime", "Hoạt hình"), TuplesKt.to("kids", "Thiếu nhi"), TuplesKt.to("musical", "Nhạc kịch"), TuplesKt.to("bí ẩn", "Bí ẩn"), TuplesKt.to("hành động", "Hành động"), TuplesKt.to("hài", "Hài kịch"), TuplesKt.to("hình sự", "Tội phạm"), TuplesKt.to("khoa học viễn tưởng", "Viễn tưởng"), TuplesKt.to("miền tây", "Viễn Tây"), TuplesKt.to("gây cấn", "Hồi hộp"), TuplesKt.to("tình cảm", "Tình cảm"), TuplesKt.to("chính kịch", "Chính kịch"), TuplesKt.to("giả tượng", "Giả tưởng"), TuplesKt.to("kinh dị", "Kinh dị"), TuplesKt.to("lịch sử", "Lịch sử"), TuplesKt.to("phiêu lưu", "Phiêu lưu"), TuplesKt.to("gia đình", "Gia đình"), TuplesKt.to("hoạt hình", "Hoạt hình"), TuplesKt.to("hồi hộp", "Hồi hộp"), TuplesKt.to("tình cảm", "Tình cảm"), TuplesKt.to("trinh thám", "Bí ẩn"), TuplesKt.to("võ thuật", "Hành động"), TuplesKt.to("cổ trang", "Cổ trang"));
    public static final int $stable = 8;

    private GenreNames() {
    }

    public final List<String> vietnamizeNames(List<String> names) {
        Intrinsics.checkNotNullParameter(names, "names");
        return SequencesKt.toList(SequencesKt.distinct(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(names), new Function1<String, String>() { // from class: com.streamsync.app.data.remote.GenreNames$vietnamizeNames$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return GenreNames.INSTANCE.vietnamize(it);
            }
        }), new Function1<String, Boolean>() { // from class: com.streamsync.app.data.remote.GenreNames$vietnamizeNames$2
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it.length() > 0);
            }
        })));
    }

    public final String vietnamize(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String name = StringsKt.trim((CharSequence) raw).toString();
        if (name.length() == 0) {
            return "";
        }
        String bare = StringsKt.trim((CharSequence) StringsKt.removePrefix(name, (CharSequence) "Phim")).toString();
        String str = bare;
        if (str.length() == 0) {
            str = name;
        }
        String lookup = str;
        Map<String, String> map = VIETNAMESE;
        String lowerCase = lookup.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String fromVi = map.get(lowerCase);
        if (fromVi == null) {
            Map<String, String> map2 = VIETNAMESE;
            String lowerCase2 = name.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            fromVi = map2.get(lowerCase2);
        }
        return fromVi != null ? fromVi : lookup.length() >= 3 ? lookup : name;
    }

    private final String vietnamese(String name) {
        Map<String, String> map = VIETNAMESE;
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return map.get(lowerCase);
    }
}
