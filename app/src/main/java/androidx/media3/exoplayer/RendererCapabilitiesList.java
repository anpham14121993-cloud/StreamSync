package androidx.media3.exoplayer;

/* loaded from: classes4.dex */
public interface RendererCapabilitiesList {

    /* loaded from: classes4.dex */
    public interface Factory {
        RendererCapabilitiesList createRendererCapabilitiesList();
    }

    RendererCapabilities[] getRendererCapabilities();

    void release();

    int size();
}
