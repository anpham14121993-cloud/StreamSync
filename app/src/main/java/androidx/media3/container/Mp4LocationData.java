package androidx.media3.container;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import com.google.common.primitives.Floats;

/* loaded from: classes24.dex */
public final class Mp4LocationData implements Metadata.Entry {
    public static final Parcelable.Creator<Mp4LocationData> CREATOR = new Parcelable.Creator<Mp4LocationData>() { // from class: androidx.media3.container.Mp4LocationData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Mp4LocationData createFromParcel(Parcel in) {
            return new Mp4LocationData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Mp4LocationData[] newArray(int size) {
            return new Mp4LocationData[size];
        }
    };
    public final float latitude;
    public final float longitude;

    public Mp4LocationData(float latitude, float longitude) {
        Assertions.checkArgument(latitude >= -90.0f && latitude <= 90.0f && longitude >= -180.0f && longitude <= 180.0f, "Invalid latitude or longitude");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Mp4LocationData(Parcel in) {
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Mp4LocationData other = (Mp4LocationData) obj;
        if (this.latitude == other.latitude && this.longitude == other.longitude) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (17 * 31) + Floats.hashCode(this.latitude);
        return (result * 31) + Floats.hashCode(this.longitude);
    }

    public String toString() {
        return "xyz: latitude=" + this.latitude + ", longitude=" + this.longitude;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.latitude);
        dest.writeFloat(this.longitude);
    }
}
