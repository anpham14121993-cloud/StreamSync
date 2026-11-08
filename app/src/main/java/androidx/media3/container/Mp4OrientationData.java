package androidx.media3.container;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;

/* loaded from: classes24.dex */
public final class Mp4OrientationData implements Metadata.Entry {
    public static final Parcelable.Creator<Mp4OrientationData> CREATOR = new Parcelable.Creator<Mp4OrientationData>() { // from class: androidx.media3.container.Mp4OrientationData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Mp4OrientationData createFromParcel(Parcel in) {
            return new Mp4OrientationData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Mp4OrientationData[] newArray(int size) {
            return new Mp4OrientationData[size];
        }
    };
    public final int orientation;

    public Mp4OrientationData(int orientation) {
        Assertions.checkArgument(orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270, "Unsupported orientation");
        this.orientation = orientation;
    }

    private Mp4OrientationData(Parcel in) {
        this.orientation = in.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mp4OrientationData)) {
            return false;
        }
        Mp4OrientationData other = (Mp4OrientationData) obj;
        return this.orientation == other.orientation;
    }

    public int hashCode() {
        int result = (17 * 31) + Integer.hashCode(this.orientation);
        return result;
    }

    public String toString() {
        return "Orientation= " + this.orientation;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orientation);
    }
}
