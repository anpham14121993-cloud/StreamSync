package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;

/* loaded from: classes24.dex */
public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public Id3Frame(String id) {
        this.id = id;
    }

    public String toString() {
        return this.id;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
