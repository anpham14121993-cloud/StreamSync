package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes4.dex */
public interface RendererCapabilities {
    public static final int ADAPTIVE_NOT_SEAMLESS = 8;
    public static final int ADAPTIVE_NOT_SUPPORTED = 0;
    public static final int ADAPTIVE_SEAMLESS = 16;
    public static final int ADAPTIVE_SUPPORT_MASK = 24;
    public static final int AUDIO_OFFLOAD_GAPLESS_SUPPORTED = 1024;
    public static final int AUDIO_OFFLOAD_NOT_SUPPORTED = 0;
    public static final int AUDIO_OFFLOAD_SPEED_CHANGE_SUPPORTED = 2048;
    public static final int AUDIO_OFFLOAD_SUPPORTED = 512;
    public static final int AUDIO_OFFLOAD_SUPPORT_MASK = 3584;
    public static final int DECODER_SUPPORT_FALLBACK = 0;
    public static final int DECODER_SUPPORT_FALLBACK_MIMETYPE = 256;
    public static final int DECODER_SUPPORT_MASK = 384;
    public static final int DECODER_SUPPORT_PRIMARY = 128;
    public static final int FORMAT_SUPPORT_MASK = 7;
    public static final int HARDWARE_ACCELERATION_NOT_SUPPORTED = 0;
    public static final int HARDWARE_ACCELERATION_SUPPORTED = 64;
    public static final int HARDWARE_ACCELERATION_SUPPORT_MASK = 64;
    public static final int TUNNELING_NOT_SUPPORTED = 0;
    public static final int TUNNELING_SUPPORTED = 32;
    public static final int TUNNELING_SUPPORT_MASK = 32;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface AdaptiveSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface AudioOffloadSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Capabilities {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface DecoderSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface HardwareAccelerationSupport {
    }

    /* loaded from: classes4.dex */
    public interface Listener {
        void onRendererCapabilitiesChanged(Renderer renderer);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface TunnelingSupport {
    }

    String getName();

    int getTrackType();

    int supportsFormat(Format format) throws ExoPlaybackException;

    int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException;

    static int create(int formatSupport) {
        return create(formatSupport, 0, 0, 0);
    }

    static int create(int formatSupport, int adaptiveSupport, int tunnelingSupport) {
        return create(formatSupport, adaptiveSupport, tunnelingSupport, 0, 128, 0);
    }

    static int create(int formatSupport, int adaptiveSupport, int tunnelingSupport, int audioOffloadSupport) {
        return create(formatSupport, adaptiveSupport, tunnelingSupport, 0, 128, audioOffloadSupport);
    }

    static int create(int formatSupport, int adaptiveSupport, int tunnelingSupport, int hardwareAccelerationSupport, int decoderSupport) {
        return create(formatSupport, adaptiveSupport, tunnelingSupport, hardwareAccelerationSupport, decoderSupport, 0);
    }

    static int create(int formatSupport, int adaptiveSupport, int tunnelingSupport, int hardwareAccelerationSupport, int decoderSupport, int audioOffloadSupport) {
        return formatSupport | adaptiveSupport | tunnelingSupport | hardwareAccelerationSupport | decoderSupport | audioOffloadSupport;
    }

    static int getFormatSupport(int supportFlags) {
        return supportFlags & 7;
    }

    static boolean isFormatSupported(int supportFlags, boolean allowExceedsCapabilities) {
        int formatSupport = getFormatSupport(supportFlags);
        return formatSupport == 4 || (allowExceedsCapabilities && formatSupport == 3);
    }

    static int getAdaptiveSupport(int supportFlags) {
        return supportFlags & 24;
    }

    static int getTunnelingSupport(int supportFlags) {
        return supportFlags & 32;
    }

    static int getHardwareAccelerationSupport(int supportFlags) {
        return supportFlags & 64;
    }

    static int getDecoderSupport(int supportFlags) {
        return supportFlags & DECODER_SUPPORT_MASK;
    }

    static int getAudioOffloadSupport(int supportFlags) {
        return supportFlags & AUDIO_OFFLOAD_SUPPORT_MASK;
    }

    default void setListener(Listener listener) {
    }

    default void clearListener() {
    }
}
