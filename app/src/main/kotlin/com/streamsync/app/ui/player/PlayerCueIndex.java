package com.streamsync.app.ui.player;

import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PlayerCueIndex.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0018\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\rJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0011\u001a\u00020\rJ\b\u0010\u0014\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerCueIndex;", "", "()V", "droppedOldest", "", "entries", "Ljava/util/ArrayList;", "Lcom/streamsync/app/ui/player/PlayerCueIndex$Entry;", "Lkotlin/collections/ArrayList;", "clear", "", "closeOpenEntry", "atUs", "", "onCues", "cueGroup", "Landroidx/media3/common/text/CueGroup;", "positionMs", "textAt", "", "trim", "Companion", "Entry", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class PlayerCueIndex {

    @Deprecated
    public static final int MAX_GROUPS = 3000;
    private volatile boolean droppedOldest;
    private final ArrayList<Entry> entries = new ArrayList<>();
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PlayerCueIndex.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerCueIndex$Entry;", "", "startUs", "", "endUs", "text", "", "(JJLjava/lang/String;)V", "getEndUs", "()J", "setEndUs", "(J)V", "getStartUs", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    public static final /* data */ class Entry {
        private long endUs;
        private final long startUs;
        private final String text;

        public static /* synthetic */ Entry copy$default(Entry entry, long j, long j2, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = entry.startUs;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = entry.endUs;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                str = entry.text;
            }
            return entry.copy(j3, j4, str);
        }

        /* renamed from: component1, reason: from getter */
        public final long getStartUs() {
            return this.startUs;
        }

        /* renamed from: component2, reason: from getter */
        public final long getEndUs() {
            return this.endUs;
        }

        /* renamed from: component3, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final Entry copy(long startUs, long endUs, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new Entry(startUs, endUs, text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) other;
            return this.startUs == entry.startUs && this.endUs == entry.endUs && Intrinsics.areEqual(this.text, entry.text);
        }

        public int hashCode() {
            return (((Long.hashCode(this.startUs) * 31) + Long.hashCode(this.endUs)) * 31) + this.text.hashCode();
        }

        public String toString() {
            return "Entry(startUs=" + this.startUs + ", endUs=" + this.endUs + ", text=" + this.text + ")";
        }

        public Entry(long startUs, long endUs, String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.startUs = startUs;
            this.endUs = endUs;
            this.text = text;
        }

        public final long getEndUs() {
            return this.endUs;
        }

        public final long getStartUs() {
            return this.startUs;
        }

        public final String getText() {
            return this.text;
        }

        public final void setEndUs(long j) {
            this.endUs = j;
        }
    }

    public final void onCues(CueGroup cueGroup, long positionMs) {
        if (cueGroup == null) {
            return;
        }
        Iterable cues = cueGroup.cues;
        Intrinsics.checkNotNullExpressionValue(cues, "cues");
        Iterable $this$mapNotNull$iv = cues;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            CharSequence charSequence = ((Cue) element$iv$iv$iv).text;
            String obj = charSequence != null ? charSequence.toString() : null;
            if (obj != null) {
                destination$iv$iv.add(obj);
            }
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it = $this$filter$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv = it.next();
            if (true ^ StringsKt.isBlank((String) element$iv$iv)) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        String it2 = CollectionsKt.joinToString$default((List) destination$iv$iv2, "\n", null, null, 0, null, null, 62, null);
        String text = it2.length() > 0 ? it2 : null;
        if (text == null) {
            closeOpenEntry(1000 * positionMs);
            return;
        }
        long startUs = cueGroup.presentationTimeUs;
        Entry last = (Entry) CollectionsKt.lastOrNull((List) this.entries);
        if (last != null && Intrinsics.areEqual(last.getText(), text) && last.getStartUs() == startUs) {
            return;
        }
        if (last != null && last.getStartUs() > startUs) {
            this.entries.clear();
        }
        if (last != null && last.getEndUs() == Long.MAX_VALUE) {
            last.setEndUs(startUs);
        }
        this.entries.add(new Entry(startUs, Long.MAX_VALUE, text));
        trim();
    }

    public final String textAt(long positionMs) {
        if (this.entries.isEmpty()) {
            return null;
        }
        long targetUs = 1000 * positionMs;
        int i = CollectionsKt.getLastIndex(this.entries);
        while (i > 0 && this.entries.get(i).getStartUs() > targetUs) {
            i--;
        }
        Entry entry = this.entries.get(i);
        Intrinsics.checkNotNullExpressionValue(entry, "get(...)");
        Entry entry2 = entry;
        if (entry2.getStartUs() <= targetUs && targetUs < entry2.getEndUs()) {
            return entry2.getText();
        }
        return null;
    }

    private final void closeOpenEntry(long atUs) {
        Entry last = (Entry) CollectionsKt.lastOrNull((List) this.entries);
        if (last != null && atUs > last.getStartUs()) {
            last.setEndUs(atUs);
        }
    }

    public final void clear() {
        this.entries.clear();
        this.droppedOldest = false;
    }

    /* renamed from: droppedOldest, reason: from getter */
    public final boolean getDroppedOldest() {
        return this.droppedOldest;
    }

    private final void trim() {
        if (this.entries.size() <= 3000) {
            return;
        }
        this.droppedOldest = true;
        this.entries.subList(0, this.entries.size() - 3000).clear();
    }

    /* compiled from: PlayerCueIndex.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/streamsync/app/ui/player/PlayerCueIndex$Companion;", "", "()V", "MAX_GROUPS", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
