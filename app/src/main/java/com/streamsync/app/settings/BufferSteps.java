package com.streamsync.app.settings;

import androidx.media3.exoplayer.DefaultLoadControl;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: BufferSteps.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004J\u0016\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/streamsync/app/settings/BufferSteps;", "", "()V", "BUFFER_SIZE_DEFAULT_MB", "", "BUFFER_SIZE_MB", "Lkotlin/ranges/IntRange;", "getBUFFER_SIZE_MB", "()Lkotlin/ranges/IntRange;", "CHUNK_DEFAULT_MB", "CHUNK_SIZES_MB", "", "getCHUNK_SIZES_MB", "()Ljava/util/List;", "MAX_BUFFER_DEFAULT_SECONDS", "MAX_BUFFER_SECONDS", "getMAX_BUFFER_SECONDS", "MIN_BUFFER_DEFAULT_SECONDS", "MIN_BUFFER_SECONDS", "getMIN_BUFFER_SECONDS", "TIME_STEP_SECONDS", "chunkMbFromLegacyKb", "valueKb", "normalizeChunkMb", "valueMb", "normalizeMaxBufferSeconds", "valueSeconds", "normalizeMinBufferSeconds", "normalizeTimeSeconds", "range", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class BufferSteps {
    public static final int BUFFER_SIZE_DEFAULT_MB = 64;
    public static final int CHUNK_DEFAULT_MB = 1;
    public static final int MAX_BUFFER_DEFAULT_SECONDS = 60;
    public static final int MIN_BUFFER_DEFAULT_SECONDS = 30;
    public static final int TIME_STEP_SECONDS = 10;
    public static final BufferSteps INSTANCE = new BufferSteps();
    private static final List<Integer> CHUNK_SIZES_MB = CollectionsKt.listOf((Object[]) new Integer[]{1, 2, 4, 8, 16, 32, 64});
    private static final IntRange MIN_BUFFER_SECONDS = new IntRange(10, 300);
    private static final IntRange MAX_BUFFER_SECONDS = new IntRange(20, 600);
    private static final IntRange BUFFER_SIZE_MB = new IntRange(8, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS);
    public static final int $stable = 8;

    private BufferSteps() {
    }

    public final List<Integer> getCHUNK_SIZES_MB() {
        return CHUNK_SIZES_MB;
    }

    public final IntRange getMIN_BUFFER_SECONDS() {
        return MIN_BUFFER_SECONDS;
    }

    public final IntRange getMAX_BUFFER_SECONDS() {
        return MAX_BUFFER_SECONDS;
    }

    public final IntRange getBUFFER_SIZE_MB() {
        return BUFFER_SIZE_MB;
    }

    public final int normalizeChunkMb(int valueMb) {
        Object minElem$iv;
        Iterable $this$minByOrNull$iv = CHUNK_SIZES_MB;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                int it = ((Number) minElem$iv).intValue();
                int minValue$iv = Math.abs(it - valueMb);
                do {
                    Object e$iv = iterator$iv.next();
                    int it2 = ((Number) e$iv).intValue();
                    int v$iv = Math.abs(it2 - valueMb);
                    if (minValue$iv > v$iv) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            minElem$iv = null;
        }
        Integer num = (Integer) minElem$iv;
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    public final int chunkMbFromLegacyKb(int valueKb) {
        return normalizeChunkMb(Math.round(valueKb / 1024.0f));
    }

    public final int normalizeTimeSeconds(int valueSeconds, IntRange range) {
        Intrinsics.checkNotNullParameter(range, "range");
        int clamped = RangesKt.coerceIn(valueSeconds, range.getFirst(), range.getLast());
        int steps = Math.round((clamped - range.getFirst()) / 10);
        return RangesKt.coerceIn(range.getFirst() + (steps * 10), range.getFirst(), range.getLast());
    }

    public final int normalizeMinBufferSeconds(int valueSeconds) {
        return normalizeTimeSeconds(valueSeconds, MIN_BUFFER_SECONDS);
    }

    public final int normalizeMaxBufferSeconds(int valueSeconds) {
        return normalizeTimeSeconds(valueSeconds, MAX_BUFFER_SECONDS);
    }
}
