package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.common.Metadata;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.SystemClock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class DefaultRendererCapabilitiesList implements RendererCapabilitiesList {
    private final Renderer[] renderers;

    /* loaded from: classes4.dex */
    public static final class Factory implements RendererCapabilitiesList.Factory {
        private final RenderersFactory renderersFactory;

        public Factory(Context context) {
            this.renderersFactory = new DefaultRenderersFactory(context);
        }

        public Factory(RenderersFactory renderersFactory) {
            this.renderersFactory = renderersFactory;
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList.Factory
        public DefaultRendererCapabilitiesList createRendererCapabilitiesList() {
            Renderer[] renderers = this.renderersFactory.createRenderers(Util.createHandlerForCurrentOrMainLooper(), new VideoRendererEventListener() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList.Factory.1
            }, new AudioRendererEventListener() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList.Factory.2
            }, new TextOutput() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList$Factory$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.text.TextOutput
                public final void onCues(CueGroup cueGroup) {
                    DefaultRendererCapabilitiesList.Factory.lambda$createRendererCapabilitiesList$0(cueGroup);
                }
            }, new MetadataOutput() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList$Factory$$ExternalSyntheticLambda1
                @Override // androidx.media3.exoplayer.metadata.MetadataOutput
                public final void onMetadata(Metadata metadata) {
                    DefaultRendererCapabilitiesList.Factory.lambda$createRendererCapabilitiesList$1(metadata);
                }
            });
            return new DefaultRendererCapabilitiesList(renderers);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$createRendererCapabilitiesList$0(CueGroup cueGroup) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$createRendererCapabilitiesList$1(Metadata metadata) {
        }
    }

    private DefaultRendererCapabilitiesList(Renderer[] renderers) {
        this.renderers = (Renderer[]) Arrays.copyOf(renderers, renderers.length);
        for (int i = 0; i < renderers.length; i++) {
            this.renderers[i].init(i, PlayerId.UNSET, SystemClock.DEFAULT);
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public RendererCapabilities[] getRendererCapabilities() {
        RendererCapabilities[] rendererCapabilities = new RendererCapabilities[this.renderers.length];
        for (int i = 0; i < this.renderers.length; i++) {
            rendererCapabilities[i] = this.renderers[i].getCapabilities();
        }
        return rendererCapabilities;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public int size() {
        return this.renderers.length;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public void release() {
        for (Renderer renderer : this.renderers) {
            renderer.release();
        }
    }
}
