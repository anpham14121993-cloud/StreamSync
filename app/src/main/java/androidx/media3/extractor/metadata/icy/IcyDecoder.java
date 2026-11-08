package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.Metadata;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes24.dex */
public final class IcyDecoder extends SimpleMetadataDecoder {
    private static final Pattern METADATA_ELEMENT = Pattern.compile("(.+?)='(.*?)';", 32);
    private static final String STREAM_KEY_NAME = "streamtitle";
    private static final String STREAM_KEY_URL = "streamurl";
    private final CharsetDecoder utf8Decoder = Charsets.UTF_8.newDecoder();
    private final CharsetDecoder iso88591Decoder = Charsets.ISO_8859_1.newDecoder();

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    protected Metadata decode(MetadataInputBuffer inputBuffer, ByteBuffer buffer) {
        char c;
        String icyString = decodeToString(buffer);
        byte[] icyBytes = new byte[buffer.limit()];
        buffer.get(icyBytes);
        if (icyString == null) {
            return new Metadata(new IcyInfo(icyBytes, null, null));
        }
        String name = null;
        String url = null;
        Matcher matcher = METADATA_ELEMENT.matcher(icyString);
        for (int index = 0; matcher.find(index); index = matcher.end()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            if (key != null) {
                String lowerCase = Ascii.toLowerCase(key);
                switch (lowerCase.hashCode()) {
                    case -315603473:
                        if (lowerCase.equals(STREAM_KEY_URL)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1646559960:
                        if (lowerCase.equals(STREAM_KEY_NAME)) {
                            c = 0;
                            break;
                        }
                        break;
                }
                c = 65535;
                switch (c) {
                    case 0:
                        name = value;
                        break;
                    case 1:
                        url = value;
                        break;
                }
            }
        }
        return new Metadata(new IcyInfo(icyBytes, name, url));
    }

    private String decodeToString(ByteBuffer data) {
        try {
            return this.utf8Decoder.decode(data).toString();
        } catch (CharacterCodingException e) {
            try {
                String charBuffer = this.iso88591Decoder.decode(data).toString();
                this.iso88591Decoder.reset();
                data.rewind();
                return charBuffer;
            } catch (CharacterCodingException e2) {
                this.iso88591Decoder.reset();
                data.rewind();
                return null;
            } catch (Throwable th) {
                this.iso88591Decoder.reset();
                data.rewind();
                throw th;
            }
        } finally {
            this.utf8Decoder.reset();
            data.rewind();
        }
    }
}
