package androidx.media3.extractor.metadata.flac;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;

@Deprecated
/* loaded from: classes24.dex */
public class VorbisComment implements Metadata.Entry {
    public static final Parcelable.Creator<VorbisComment> CREATOR = new Parcelable.Creator<VorbisComment>() { // from class: androidx.media3.extractor.metadata.flac.VorbisComment.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VorbisComment createFromParcel(Parcel in) {
            return new VorbisComment(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VorbisComment[] newArray(int size) {
            return new VorbisComment[size];
        }
    };
    public final String key;
    public final String value;

    public VorbisComment(String key, String value) {
        this.key = Ascii.toUpperCase(key);
        this.value = value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VorbisComment(Parcel in) {
        this.key = (String) Util.castNonNull(in.readString());
        this.value = (String) Util.castNonNull(in.readString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        char c;
        String str = this.key;
        switch (str.hashCode()) {
            case 62359119:
                if (str.equals("ALBUM")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 79833656:
                if (str.equals("TITLE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 428414940:
                if (str.equals("DESCRIPTION")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1746739798:
                if (str.equals("ALBUMARTIST")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1939198791:
                if (str.equals("ARTIST")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                builder.setTitle(this.value);
                return;
            case 1:
                builder.setArtist(this.value);
                return;
            case 2:
                builder.setAlbumTitle(this.value);
                return;
            case 3:
                builder.setAlbumArtist(this.value);
                return;
            case 4:
                builder.setDescription(this.value);
                return;
            default:
                return;
        }
    }

    public String toString() {
        return "VC: " + this.key + "=" + this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VorbisComment other = (VorbisComment) obj;
        if (this.key.equals(other.key) && this.value.equals(other.value)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (17 * 31) + this.key.hashCode();
        return (result * 31) + this.value.hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
