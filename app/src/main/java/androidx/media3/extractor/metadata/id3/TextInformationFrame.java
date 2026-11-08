package androidx.media3.extractor.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes24.dex */
public final class TextInformationFrame extends Id3Frame {
    public static final Parcelable.Creator<TextInformationFrame> CREATOR = new Parcelable.Creator<TextInformationFrame>() { // from class: androidx.media3.extractor.metadata.id3.TextInformationFrame.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInformationFrame createFromParcel(Parcel in) {
            return new TextInformationFrame(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInformationFrame[] newArray(int size) {
            return new TextInformationFrame[size];
        }
    };
    public final String description;

    @Deprecated
    public final String value;
    public final ImmutableList<String> values;

    public TextInformationFrame(String id, String description, List<String> values) {
        super(id);
        Assertions.checkArgument(!values.isEmpty());
        this.description = description;
        this.values = ImmutableList.copyOf((Collection) values);
        this.value = this.values.get(0);
    }

    @Deprecated
    public TextInformationFrame(String id, String description, String value) {
        this(id, description, ImmutableList.of(value));
    }

    private TextInformationFrame(Parcel in) {
        this((String) Assertions.checkNotNull(in.readString()), in.readString(), ImmutableList.copyOf((String[]) Assertions.checkNotNull(in.createStringArray())));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0179. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x01ab. Please report as an issue. */
    @Override // androidx.media3.common.Metadata.Entry
    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        char c;
        String str = this.id;
        switch (str.hashCode()) {
            case 82815:
                if (str.equals("TAL")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 82878:
                if (str.equals("TCM")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 82897:
                if (str.equals("TDA")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 83253:
                if (str.equals("TP1")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 83254:
                if (str.equals("TP2")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 83255:
                if (str.equals("TP3")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 83341:
                if (str.equals("TRK")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 83378:
                if (str.equals("TT2")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 83536:
                if (str.equals("TXT")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 83552:
                if (str.equals("TYE")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 2567331:
                if (str.equals("TALB")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2569357:
                if (str.equals("TCOM")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 2569358:
                if (str.equals("TCON")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 2569891:
                if (str.equals("TDAT")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 2570401:
                if (str.equals("TDRC")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 2570410:
                if (str.equals("TDRL")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 2571565:
                if (str.equals("TEXT")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 2575251:
                if (str.equals("TIT2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2581512:
                if (str.equals("TPE1")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2581513:
                if (str.equals("TPE2")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2581514:
                if (str.equals("TPE3")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2583398:
                if (str.equals("TRCK")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2590194:
                if (str.equals("TYER")) {
                    c = 11;
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
            case 1:
                builder.setTitle(this.values.get(0));
                return;
            case 2:
            case 3:
                builder.setArtist(this.values.get(0));
                return;
            case 4:
            case 5:
                builder.setAlbumArtist(this.values.get(0));
                return;
            case 6:
            case 7:
                builder.setAlbumTitle(this.values.get(0));
                return;
            case '\b':
            case '\t':
                String[] trackNumbers = Util.split(this.values.get(0), "/");
                try {
                    int trackNumber = Integer.parseInt(trackNumbers[0]);
                    Integer totalTrackCount = trackNumbers.length > 1 ? Integer.valueOf(Integer.parseInt(trackNumbers[1])) : null;
                    builder.setTrackNumber(Integer.valueOf(trackNumber)).setTotalTrackCount(totalTrackCount);
                    return;
                } catch (NumberFormatException e) {
                    return;
                }
            case '\n':
            case 11:
                try {
                    builder.setRecordingYear(Integer.valueOf(Integer.parseInt(this.values.get(0))));
                    return;
                } catch (NumberFormatException e2) {
                    return;
                }
            case '\f':
            case '\r':
                try {
                    String date = this.values.get(0);
                    int month = Integer.parseInt(date.substring(2, 4));
                    int day = Integer.parseInt(date.substring(0, 2));
                    builder.setRecordingMonth(Integer.valueOf(month)).setRecordingDay(Integer.valueOf(day));
                    return;
                } catch (NumberFormatException e3) {
                    return;
                } catch (StringIndexOutOfBoundsException e4) {
                    return;
                }
            case 14:
                List<Integer> recordingDate = parseId3v2point4TimestampFrameForDate(this.values.get(0));
                switch (recordingDate.size()) {
                    case 3:
                        builder.setRecordingDay(recordingDate.get(2));
                    case 2:
                        builder.setRecordingMonth(recordingDate.get(1));
                    case 1:
                        builder.setRecordingYear(recordingDate.get(0));
                        return;
                    default:
                        return;
                }
            case 15:
                List<Integer> releaseDate = parseId3v2point4TimestampFrameForDate(this.values.get(0));
                switch (releaseDate.size()) {
                    case 3:
                        builder.setReleaseDay(releaseDate.get(2));
                    case 2:
                        builder.setReleaseMonth(releaseDate.get(1));
                    case 1:
                        builder.setReleaseYear(releaseDate.get(0));
                        return;
                    default:
                        return;
                }
            case 16:
            case 17:
                builder.setComposer(this.values.get(0));
                return;
            case 18:
            case 19:
                builder.setConductor(this.values.get(0));
                return;
            case 20:
            case 21:
                builder.setWriter(this.values.get(0));
                return;
            case 22:
                Integer genreCode = Ints.tryParse(this.values.get(0));
                if (genreCode == null) {
                    builder.setGenre(this.values.get(0));
                    return;
                }
                String genre = Id3Util.resolveV1Genre(genreCode.intValue());
                if (genre != null) {
                    builder.setGenre(genre);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextInformationFrame other = (TextInformationFrame) obj;
        if (Util.areEqual(this.id, other.id) && Util.areEqual(this.description, other.description) && this.values.equals(other.values)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (17 * 31) + this.id.hashCode();
        return (((result * 31) + (this.description != null ? this.description.hashCode() : 0)) * 31) + this.values.hashCode();
    }

    @Override // androidx.media3.extractor.metadata.id3.Id3Frame
    public String toString() {
        return this.id + ": description=" + this.description + ": values=" + this.values;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.description);
        dest.writeStringArray((String[]) this.values.toArray(new String[0]));
    }

    private static List<Integer> parseId3v2point4TimestampFrameForDate(String value) {
        List<Integer> dates = new ArrayList<>();
        try {
            if (value.length() >= 10) {
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(0, 4))));
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(5, 7))));
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(8, 10))));
            } else if (value.length() >= 7) {
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(0, 4))));
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(5, 7))));
            } else if (value.length() >= 4) {
                dates.add(Integer.valueOf(Integer.parseInt(value.substring(0, 4))));
            }
            return dates;
        } catch (NumberFormatException e) {
            return new ArrayList();
        }
    }
}
