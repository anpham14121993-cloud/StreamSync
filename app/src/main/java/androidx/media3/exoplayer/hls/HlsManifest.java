package androidx.media3.exoplayer.hls;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;

/* loaded from: classes12.dex */
public final class HlsManifest {
    public final HlsMediaPlaylist mediaPlaylist;
    public final HlsMultivariantPlaylist multivariantPlaylist;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HlsManifest(HlsMultivariantPlaylist multivariantPlaylist, HlsMediaPlaylist mediaPlaylist) {
        this.multivariantPlaylist = multivariantPlaylist;
        this.mediaPlaylist = mediaPlaylist;
    }
}
