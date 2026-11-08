package androidx.compose.runtime;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0015H\u0096\u0002J\u0011\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003H\u0096\u0002J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/DataIterator;", "", "", "", "table", "Landroidx/compose/runtime/SlotTable;", "group", "", "(Landroidx/compose/runtime/SlotTable;I)V", TtmlNode.END, "getEnd", "()I", "index", "getIndex", "setIndex", "(I)V", TtmlNode.START, "getStart", "getTable", "()Landroidx/compose/runtime/SlotTable;", "hasNext", "", "iterator", "next", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class DataIterator implements Iterable<Object>, Iterator<Object>, KMappedMarker {
    private final int end;
    private int index;
    private final int start;
    private final SlotTable table;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public DataIterator(SlotTable table, int group) {
        int dataAnchor;
        this.table = table;
        dataAnchor = SlotTableKt.dataAnchor(this.table.getGroups(), group);
        this.start = dataAnchor;
        this.end = group + 1 < this.table.getGroupsSize() ? SlotTableKt.dataAnchor(this.table.getGroups(), group + 1) : this.table.getSlotsSize();
        this.index = this.start;
    }

    public final SlotTable getTable() {
        return this.table;
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEnd() {
        return this.end;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.end;
    }

    @Override // java.util.Iterator
    public Object next() {
        Object obj;
        if (this.index >= 0 && this.index < this.table.getSlots().length) {
            obj = this.table.getSlots()[this.index];
        } else {
            obj = null;
        }
        this.index++;
        return obj;
    }
}
