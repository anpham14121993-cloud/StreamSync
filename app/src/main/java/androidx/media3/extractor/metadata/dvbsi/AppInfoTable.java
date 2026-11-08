package androidx.media3.extractor.metadata.dvbsi;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;

/* loaded from: classes24.dex */
public final class AppInfoTable implements Metadata.Entry {
    public static final int CONTROL_CODE_AUTOSTART = 1;
    public static final int CONTROL_CODE_PRESENT = 2;
    public static final Parcelable.Creator<AppInfoTable> CREATOR = new Parcelable.Creator<AppInfoTable>() { // from class: androidx.media3.extractor.metadata.dvbsi.AppInfoTable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfoTable createFromParcel(Parcel in) {
            String url = (String) Assertions.checkNotNull(in.readString());
            int controlCode = in.readInt();
            return new AppInfoTable(controlCode, url);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppInfoTable[] newArray(int size) {
            return new AppInfoTable[size];
        }
    };
    public final int controlCode;
    public final String url;

    public AppInfoTable(int controlCode, String url) {
        this.controlCode = controlCode;
        this.url = url;
    }

    public String toString() {
        return "Ait(controlCode=" + this.controlCode + ",url=" + this.url + ")";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeInt(this.controlCode);
    }
}
