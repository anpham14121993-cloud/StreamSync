package androidx.media3.exoplayer.hls;

import java.io.IOException;

/* loaded from: classes12.dex */
public final class SampleQueueMappingException extends IOException {
    public SampleQueueMappingException(String mimeType) {
        super("Unable to bind a sample queue to TrackGroup with MIME type " + mimeType + ".");
    }
}
