package com.streamsync.app.ui.player;

import android.content.Context;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.audio.AudioCapabilities;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OpticalPassthroughRenderersFactory.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0014J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/streamsync/app/ui/player/OpticalPassthroughRenderersFactory;", "Landroidx/media3/exoplayer/DefaultRenderersFactory;", "context", "Landroid/content/Context;", "config", "Lcom/streamsync/app/ui/player/PassthroughConfig;", "extraAudioProcessors", "", "Landroidx/media3/common/audio/AudioProcessor;", "(Landroid/content/Context;Lcom/streamsync/app/ui/player/PassthroughConfig;[Landroidx/media3/common/audio/AudioProcessor;)V", "[Landroidx/media3/common/audio/AudioProcessor;", "buildAudioSink", "Landroidx/media3/exoplayer/audio/AudioSink;", "enableFloatOutput", "", "enableAudioTrackPlaybackParams", "buildSink", "builder", "Landroidx/media3/exoplayer/audio/DefaultAudioSink$Builder;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class OpticalPassthroughRenderersFactory extends DefaultRenderersFactory {
    public static final int $stable = 8;
    private final PassthroughConfig config;
    private final AudioProcessor[] extraAudioProcessors;

    public /* synthetic */ OpticalPassthroughRenderersFactory(Context context, PassthroughConfig passthroughConfig, AudioProcessor[] audioProcessorArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, passthroughConfig, (i & 4) != 0 ? new AudioProcessor[0] : audioProcessorArr);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OpticalPassthroughRenderersFactory(Context context, PassthroughConfig config, AudioProcessor[] extraAudioProcessors) {
        super(context);
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(extraAudioProcessors, "extraAudioProcessors");
        this.config = config;
        this.extraAudioProcessors = extraAudioProcessors;
        if (this.config.getPreferSoftwareAudio()) {
            i = 2;
        } else {
            i = 1;
        }
        setExtensionRendererMode(i);
        setMediaCodecSelector(new PreferenceMediaCodecSelector(this.config.getDecoderPreference()));
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected AudioSink buildAudioSink(Context context, boolean enableFloatOutput, boolean enableAudioTrackPlaybackParams) {
        Intrinsics.checkNotNullParameter(context, "context");
        DefaultAudioSink.Builder builder = new DefaultAudioSink.Builder(context).setEnableFloatOutput(enableFloatOutput).setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams);
        Intrinsics.checkNotNullExpressionValue(builder, "setEnableAudioTrackPlaybackParams(...)");
        if (!(this.extraAudioProcessors.length == 0)) {
            builder.setAudioProcessors(this.extraAudioProcessors);
        }
        DefaultAudioSink.Builder audioCapabilities = builder.setAudioCapabilities(new AudioCapabilities(new int[0], 8));
        Intrinsics.checkNotNullExpressionValue(audioCapabilities, "setAudioCapabilities(...)");
        return buildSink(audioCapabilities);
    }

    private final AudioSink buildSink(DefaultAudioSink.Builder builder) {
        DefaultAudioSink baseSink = builder.build();
        Intrinsics.checkNotNullExpressionValue(baseSink, "build(...)");
        this.config.getTranscodeToAc3();
        return baseSink;
    }
}
