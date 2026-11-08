package com.streamsync.app.data.model;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubtitleModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J=\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001c\u0010\t\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/streamsync/app/data/model/SubtitleCue;", "", "index", "", "startMs", "", "endMs", "originalText", "", "translatedText", "(IJJLjava/lang/String;Ljava/lang/String;)V", "getEndMs", "()J", "getIndex", "()I", "getOriginalText", "()Ljava/lang/String;", "getStartMs", "getTranslatedText", "setTranslatedText", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class SubtitleCue {
    public static final int $stable = 8;
    private final long endMs;
    private final int index;
    private final String originalText;
    private final long startMs;
    private String translatedText;

    public static /* synthetic */ SubtitleCue copy$default(SubtitleCue subtitleCue, int i, long j, long j2, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = subtitleCue.index;
        }
        if ((i2 & 2) != 0) {
            j = subtitleCue.startMs;
        }
        long j3 = j;
        if ((i2 & 4) != 0) {
            j2 = subtitleCue.endMs;
        }
        long j4 = j2;
        if ((i2 & 8) != 0) {
            str = subtitleCue.originalText;
        }
        String str3 = str;
        if ((i2 & 16) != 0) {
            str2 = subtitleCue.translatedText;
        }
        return subtitleCue.copy(i, j3, j4, str3, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* renamed from: component2, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* renamed from: component3, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* renamed from: component4, reason: from getter */
    public final String getOriginalText() {
        return this.originalText;
    }

    /* renamed from: component5, reason: from getter */
    public final String getTranslatedText() {
        return this.translatedText;
    }

    public final SubtitleCue copy(int index, long startMs, long endMs, String originalText, String translatedText) {
        Intrinsics.checkNotNullParameter(originalText, "originalText");
        return new SubtitleCue(index, startMs, endMs, originalText, translatedText);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleCue)) {
            return false;
        }
        SubtitleCue subtitleCue = (SubtitleCue) other;
        return this.index == subtitleCue.index && this.startMs == subtitleCue.startMs && this.endMs == subtitleCue.endMs && Intrinsics.areEqual(this.originalText, subtitleCue.originalText) && Intrinsics.areEqual(this.translatedText, subtitleCue.translatedText);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.index) * 31) + Long.hashCode(this.startMs)) * 31) + Long.hashCode(this.endMs)) * 31) + this.originalText.hashCode()) * 31) + (this.translatedText == null ? 0 : this.translatedText.hashCode());
    }

    public String toString() {
        return "SubtitleCue(index=" + this.index + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ", originalText=" + this.originalText + ", translatedText=" + this.translatedText + ")";
    }

    public SubtitleCue(int index, long startMs, long endMs, String originalText, String translatedText) {
        Intrinsics.checkNotNullParameter(originalText, "originalText");
        this.index = index;
        this.startMs = startMs;
        this.endMs = endMs;
        this.originalText = originalText;
        this.translatedText = translatedText;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SubtitleCue(int r10, long r11, long r13, java.lang.String r15, java.lang.String r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 16
            if (r0 == 0) goto L7
            r0 = 0
            r8 = r0
            goto L9
        L7:
            r8 = r16
        L9:
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r13
            r7 = r15
            r1.<init>(r2, r3, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.model.SubtitleCue.<init>(int, long, long, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final String getOriginalText() {
        return this.originalText;
    }

    public final String getTranslatedText() {
        return this.translatedText;
    }

    public final void setTranslatedText(String str) {
        this.translatedText = str;
    }
}
