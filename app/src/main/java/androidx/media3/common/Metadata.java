package androidx.media3.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import com.google.common.primitives.Longs;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes24.dex */
public final class Metadata implements Parcelable {
    public static final Parcelable.Creator<Metadata> CREATOR = new Parcelable.Creator<Metadata>() { // from class: androidx.media3.common.Metadata.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Metadata createFromParcel(Parcel in) {
            return new Metadata(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };
    private final Entry[] entries;
    public final long presentationTimeUs;

    /* loaded from: classes24.dex */
    public interface Entry extends Parcelable {
        default Format getWrappedMetadataFormat() {
            return null;
        }

        default byte[] getWrappedMetadataBytes() {
            return null;
        }

        default void populateMediaMetadata(MediaMetadata.Builder builder) {
        }
    }

    public Metadata(Entry... entries) {
        this(C.TIME_UNSET, entries);
    }

    public Metadata(long presentationTimeUs, Entry... entries) {
        this.presentationTimeUs = presentationTimeUs;
        this.entries = entries;
    }

    public Metadata(List<? extends Entry> entries) {
        this((Entry[]) entries.toArray(new Entry[0]));
    }

    public Metadata(long presentationTimeUs, List<? extends Entry> entries) {
        this(presentationTimeUs, (Entry[]) entries.toArray(new Entry[0]));
    }

    Metadata(Parcel in) {
        this.entries = new Entry[in.readInt()];
        for (int i = 0; i < this.entries.length; i++) {
            this.entries[i] = (Entry) in.readParcelable(Entry.class.getClassLoader());
        }
        this.presentationTimeUs = in.readLong();
    }

    public int length() {
        return this.entries.length;
    }

    public Entry get(int index) {
        return this.entries[index];
    }

    public Metadata copyWithAppendedEntriesFrom(Metadata other) {
        if (other == null) {
            return this;
        }
        return copyWithAppendedEntries(other.entries);
    }

    public Metadata copyWithAppendedEntries(Entry... entriesToAppend) {
        if (entriesToAppend.length == 0) {
            return this;
        }
        return new Metadata(this.presentationTimeUs, (Entry[]) Util.nullSafeArrayConcatenation(this.entries, entriesToAppend));
    }

    public Metadata copyWithPresentationTimeUs(long presentationTimeUs) {
        if (this.presentationTimeUs == presentationTimeUs) {
            return this;
        }
        return new Metadata(presentationTimeUs, this.entries);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Metadata other = (Metadata) obj;
        if (Arrays.equals(this.entries, other.entries) && this.presentationTimeUs == other.presentationTimeUs) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Arrays.hashCode(this.entries);
        return (result * 31) + Longs.hashCode(this.presentationTimeUs);
    }

    public String toString() {
        return "entries=" + Arrays.toString(this.entries) + (this.presentationTimeUs == C.TIME_UNSET ? "" : ", presentationTimeUs=" + this.presentationTimeUs);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.entries.length);
        for (Entry entry : this.entries) {
            dest.writeParcelable(entry, 0);
        }
        dest.writeLong(this.presentationTimeUs);
    }
}
