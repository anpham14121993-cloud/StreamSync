package com.streamsync.app.settings;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;

/* compiled from: SubtitleStyleStore.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR#\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR#\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r0\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/streamsync/app/settings/SubtitleStylePresets;", "", "()V", "BACKGROUND_COLORS", "", "Lkotlin/Pair;", "", "", "getBACKGROUND_COLORS", "()Ljava/util/List;", "BORDER_COLORS", "getBORDER_COLORS", "PRESETS", "Lcom/streamsync/app/settings/SubtitleStyle;", "getPRESETS", "TEXT_COLORS", "getTEXT_COLORS", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class SubtitleStylePresets {
    public static final int $stable;
    private static final List<Pair<Integer, String>> BACKGROUND_COLORS;
    private static final List<Pair<Integer, String>> BORDER_COLORS;
    public static final SubtitleStylePresets INSTANCE = new SubtitleStylePresets();
    private static final List<Pair<String, SubtitleStyle>> PRESETS;
    private static final List<Pair<Integer, String>> TEXT_COLORS;

    private SubtitleStylePresets() {
    }

    static {
        Integer valueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        TEXT_COLORS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(-1, "Trắng"), TuplesKt.to(Integer.valueOf(InputDeviceCompat.SOURCE_ANY), "Vàng"), TuplesKt.to(-16718337, "Xanh da trời"), TuplesKt.to(-9834322, "Xanh lá"), TuplesKt.to(-30080, "Đỏ"), TuplesKt.to(valueOf, "Đen")});
        BACKGROUND_COLORS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(0, "Trong suốt"), TuplesKt.to(-1728053248, "Đen 60%"), TuplesKt.to(-872415232, "Đen 80%"), TuplesKt.to(-233104613, "Đen xám 95%"), TuplesKt.to(-419430401, "Trắng 90%"), TuplesKt.to(Integer.MIN_VALUE, "Bóng mờ")});
        BORDER_COLORS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(valueOf, "Đen"), TuplesKt.to(-1, "Trắng"), TuplesKt.to(-15066598, "Đen mảnh")});
        PRESETS = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Mặc định", new SubtitleStyle(0.0f, 0.0f, 0, false, 0, 0.0f, 0, false, false, FrameMetricsAggregator.EVERY_DURATION, null)), TuplesKt.to("Kinh điển", new SubtitleStyle(22.0f, 0.0f, InputDeviceCompat.SOURCE_ANY, false, 0, 3.0f, ViewCompat.MEASURED_STATE_MASK, false, false, 386, null)), TuplesKt.to("Kính lớn", new SubtitleStyle(26.0f, 0.0f, -1, true, -233104613, 0.0f, 0, false, false, 450, null)), TuplesKt.to("Tối giản", new SubtitleStyle(18.0f, 0.0f, -1, false, 0, 0.0f, 0, false, true, 210, null))});
        $stable = 8;
    }

    public final List<Pair<Integer, String>> getTEXT_COLORS() {
        return TEXT_COLORS;
    }

    public final List<Pair<Integer, String>> getBACKGROUND_COLORS() {
        return BACKGROUND_COLORS;
    }

    public final List<Pair<Integer, String>> getBORDER_COLORS() {
        return BORDER_COLORS;
    }

    public final List<Pair<String, SubtitleStyle>> getPRESETS() {
        return PRESETS;
    }
}
