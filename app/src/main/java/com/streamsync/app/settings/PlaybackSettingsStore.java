package com.streamsync.app.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.streamsync.app.ui.player.PassthroughMode;
import com.streamsync.app.ui.player.VideoScaleMode;
import com.streamsync.app.ui.player.VodDiskCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: PlaybackSettingsStore.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\bD\b\u0007\u0018\u0000 p2\u00020\u0001:\u0001pB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0011J\u0006\u0010\u001a\u001a\u00020\u0011J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0011J\u0006\u0010\u001e\u001a\u00020\u0011J\u0006\u0010\u001f\u001a\u00020\u0011J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u0004\u0018\u00010#J\b\u0010$\u001a\u0004\u0018\u00010#J\b\u0010%\u001a\u0004\u0018\u00010#J\b\u0010&\u001a\u0004\u0018\u00010#J\u000e\u0010'\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010(\u001a\u00020\u0011J\u0006\u0010)\u001a\u00020\u0011J\u0006\u0010*\u001a\u00020\u000bJ\u0006\u0010+\u001a\u00020\u0011J\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020-J\u0006\u0010/\u001a\u00020-J\u0006\u00100\u001a\u00020-J\u0006\u00101\u001a\u00020-J\u0006\u00102\u001a\u00020-J\u0006\u00103\u001a\u00020-J\u0006\u00104\u001a\u00020-J\u0006\u00105\u001a\u00020-J\u0006\u00106\u001a\u00020-J\u0006\u00107\u001a\u00020-J\u0006\u00108\u001a\u00020-J\u0006\u00109\u001a\u00020-J\u0006\u0010:\u001a\u00020-J\u0006\u0010;\u001a\u00020-J\u0006\u0010<\u001a\u00020-J\u0006\u0010=\u001a\u00020-J\u0006\u0010>\u001a\u00020-J\u0006\u0010?\u001a\u00020\tJ\u000e\u0010@\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010B\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010E\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010H\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010I\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010J\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010K\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0016J\u000e\u0010L\u001a\u00020\t2\u0006\u0010M\u001a\u00020\u0018J\u000e\u0010N\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010O\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010P\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010Q\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010R\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010S\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010T\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010U\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010V\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010W\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010X\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010Y\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\u001cJ\u000e\u0010[\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010\\\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010]\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010^\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010_\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010`\u001a\u00020\t2\u0006\u0010G\u001a\u00020\u0011J\u000e\u0010a\u001a\u00020\t2\u0006\u0010b\u001a\u00020!J\u001a\u0010c\u001a\u00020\t2\b\u0010d\u001a\u0004\u0018\u00010#2\b\u0010e\u001a\u0004\u0018\u00010#J\u001a\u0010f\u001a\u00020\t2\b\u0010d\u001a\u0004\u0018\u00010#2\b\u0010e\u001a\u0004\u0018\u00010#J\u0016\u0010g\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010h\u001a\u00020\rJ\u000e\u0010i\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010j\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0011J\u000e\u0010k\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010l\u001a\u00020\t2\u0006\u0010b\u001a\u00020\u000bJ\u000e\u0010m\u001a\u00020\t2\u0006\u0010A\u001a\u00020-J\u000e\u0010n\u001a\u00020\t2\u0006\u0010o\u001a\u00020\u0011R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006q"}, d2 = {"Lcom/streamsync/app/settings/PlaybackSettingsStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearVodCache", "", "defaultVideoScaleMode", "Lcom/streamsync/app/ui/player/VideoScaleMode;", "effectiveRamTier", "Lcom/streamsync/app/settings/RamTier;", "kind", "Lcom/streamsync/app/settings/DeviceKind;", "getBackBufferSeconds", "", "getBufferSizeMb", "getChunkCount", "getChunkSizeMb", "getDecoderPreference", "Lcom/streamsync/app/settings/DecoderPreference;", "getDnsProvider", "Lcom/streamsync/app/settings/DnsProvider;", "getDpiDelayMs", "getDpiFragmentSize", "getInitialBitrateBps", "", "getMaxBufferSeconds", "getMinBufferSeconds", "getParallelConnections", "getPassthroughMode", "Lcom/streamsync/app/ui/player/PassthroughMode;", "getPreferredAudioLanguage1", "", "getPreferredAudioLanguage2", "getPreferredSubtitleLanguage1", "getPreferredSubtitleLanguage2", "getRamTier", "getSeekBufferSeconds", "getStartBufferSeconds", "getVideoScaleMode", "getVodCacheSizeMb", "isAc3PassthroughEnabled", "", "isAutoRamEnabled", "isBufferBySizeEnabled", "isCustomBufferEnabled", "isDolbyAtmosPassthroughEnabled", "isDolbyVisionP7ToHevcEnabled", "isDolbyVisionToHdr10Enabled", "isDpiBypassEnabled", "isDtsHdMaPassthroughEnabled", "isDtsHdPassthroughEnabled", "isDtsPassthroughEnabled", "isEac3PassthroughEnabled", "isHttp2Enabled", "isLoadingScreenEnabled", "isManagedBufferEnabled", "isOpticalPassthroughEnabled", "isTranscodeToAc3Enabled", "isVodCacheEnabled", "resetBufferAndNetwork", "setAc3PassthroughEnabled", "enabled", "setAutoRamEnabled", "setBackBufferSeconds", "valueSec", "setBufferBySizeEnabled", "setBufferSizeMb", "value", "setChunkCount", "setChunkSizeMb", "setCustomBufferEnabled", "setDecoderPreference", "setDnsProvider", "provider", "setDolbyAtmosPassthroughEnabled", "setDolbyVisionP7ToHevcEnabled", "setDolbyVisionToHdr10Enabled", "setDpiBypassEnabled", "setDpiDelayMs", "setDpiFragmentSize", "setDtsHdMaPassthroughEnabled", "setDtsHdPassthroughEnabled", "setDtsPassthroughEnabled", "setEac3PassthroughEnabled", "setHttp2Enabled", "setInitialBitrateBps", "bps", "setLoadingScreenEnabled", "setManagedBufferEnabled", "setMaxBufferSeconds", "setMinBufferSeconds", "setOpticalPassthroughEnabled", "setParallelConnections", "setPassthroughMode", "mode", "setPreferredAudioLanguages", "lang1", "lang2", "setPreferredSubtitleLanguages", "setRamTier", "tier", "setSeekBufferSeconds", "setStartBufferSeconds", "setTranscodeToAc3Enabled", "setVideoScaleMode", "setVodCacheEnabled", "setVodCacheSizeMb", "valueMb", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class PlaybackSettingsStore {

    @Deprecated
    public static final int DEFAULT_VOD_CACHE_MB = 2048;

    @Deprecated
    public static final String KEY_AUDIO_LANG_1 = "preferred_audio_lang_1";

    @Deprecated
    public static final String KEY_AUDIO_LANG_2 = "preferred_audio_lang_2";

    @Deprecated
    public static final String KEY_AUTO_RAM = "auto_ram";

    @Deprecated
    public static final String KEY_BACK_BUFFER_SECONDS = "back_buffer_seconds";

    @Deprecated
    public static final String KEY_BUFFER_BY_SIZE = "buffer_by_size";

    @Deprecated
    public static final String KEY_BUFFER_SIZE_MB = "buffer_size_mb";

    @Deprecated
    public static final String KEY_CHUNK_COUNT = "chunk_count";

    @Deprecated
    public static final String KEY_CHUNK_SIZE_KB_LEGACY = "chunk_size_kb";

    @Deprecated
    public static final String KEY_CHUNK_SIZE_MB = "chunk_size_mb";

    @Deprecated
    public static final String KEY_CUSTOM_BUFFER = "custom_buffer";

    @Deprecated
    public static final String KEY_DECODER = "decoder_preference";

    @Deprecated
    public static final String KEY_DNS_PROVIDER = "dns_provider";

    @Deprecated
    public static final String KEY_DPI_BYPASS = "dpi_bypass";

    @Deprecated
    public static final String KEY_DPI_DELAY = "dpi_delay_ms";

    @Deprecated
    public static final String KEY_DPI_FRAGMENT = "dpi_fragment_size";

    @Deprecated
    public static final String KEY_DV7_TO_HEVC = "dv7_to_hevc";

    @Deprecated
    public static final String KEY_DV_TO_HDR10 = "dv_to_hdr10";

    @Deprecated
    public static final String KEY_HTTP2 = "http2_enabled";

    @Deprecated
    public static final String KEY_INITIAL_BITRATE_BPS = "initial_bitrate_bps";

    @Deprecated
    public static final String KEY_LOADING_SCREEN = "loading_screen_enabled";

    @Deprecated
    public static final String KEY_MANAGED_BUFFER = "managed_buffer";

    @Deprecated
    public static final String KEY_MAX_BUFFER_SECONDS = "max_buffer_seconds";

    @Deprecated
    public static final String KEY_MIN_BUFFER_SECONDS = "min_buffer_seconds";

    @Deprecated
    public static final String KEY_OPTICAL_PASSTHROUGH = "optical_passthrough";

    @Deprecated
    public static final String KEY_PARALLEL = "parallel_connections";

    @Deprecated
    public static final String KEY_PASSTHROUGH_AC3 = "passthrough_ac3";

    @Deprecated
    public static final String KEY_PASSTHROUGH_ATMOS = "passthrough_dolby_atmos";

    @Deprecated
    public static final String KEY_PASSTHROUGH_DTS = "passthrough_dts";

    @Deprecated
    public static final String KEY_PASSTHROUGH_DTS_HD = "passthrough_dts_hd";

    @Deprecated
    public static final String KEY_PASSTHROUGH_DTS_HD_MA = "passthrough_dts_hd_ma";

    @Deprecated
    public static final String KEY_PASSTHROUGH_EAC3 = "passthrough_eac3";

    @Deprecated
    public static final String KEY_PASSTHROUGH_MODE = "passthrough_mode";

    @Deprecated
    public static final String KEY_RAM_TIER_PREFIX = "ram_tier_";

    @Deprecated
    public static final String KEY_SEEK_BUFFER_SECONDS = "seek_buffer_seconds";

    @Deprecated
    public static final String KEY_START_BUFFER_SECONDS = "start_buffer_seconds";

    @Deprecated
    public static final String KEY_SUB_LANG_1 = "preferred_sub_lang_1";

    @Deprecated
    public static final String KEY_SUB_LANG_2 = "preferred_sub_lang_2";

    @Deprecated
    public static final String KEY_TRANSCODE_TO_AC3 = "transcode_to_ac3";

    @Deprecated
    public static final String KEY_VIDEO_SCALE = "video_scale_mode";

    @Deprecated
    public static final String KEY_VOD_CACHE_ENABLED = "vod_cache_enabled";

    @Deprecated
    public static final String KEY_VOD_CACHE_SIZE_MB = "vod_cache_size_mb";
    private final SharedPreferences prefs;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final PassthroughMode DEFAULT_PASSTHROUGH_MODE = PassthroughMode.TUDO;

    public PlaybackSettingsStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = context.getApplicationContext().getSharedPreferences("playback_settings", 0);
    }

    public final PassthroughMode getPassthroughMode() {
        Object obj = null;
        String name = this.prefs.getString(KEY_PASSTHROUGH_MODE, null);
        if (name == null) {
            return DEFAULT_PASSTHROUGH_MODE;
        }
        Iterable $this$firstOrNull$iv = PassthroughMode.getEntries();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            PassthroughMode it2 = (PassthroughMode) element$iv;
            if (Intrinsics.areEqual(it2.name(), name)) {
                obj = element$iv;
                break;
            }
        }
        PassthroughMode passthroughMode = (PassthroughMode) obj;
        if (passthroughMode != null) {
            return passthroughMode;
        }
        return DEFAULT_PASSTHROUGH_MODE;
    }

    public final void setPassthroughMode(PassthroughMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.prefs.edit().putString(KEY_PASSTHROUGH_MODE, mode.name()).apply();
    }

    public final boolean isOpticalPassthroughEnabled() {
        return getPassthroughMode() != PassthroughMode.TUDO;
    }

    public final void setOpticalPassthroughEnabled(boolean enabled) {
        setPassthroughMode(enabled ? PassthroughMode.EP : PassthroughMode.TAT);
    }

    public final boolean isAc3PassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_AC3, true);
    }

    public final void setAc3PassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_AC3, enabled).apply();
    }

    public final boolean isEac3PassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_EAC3, true);
    }

    public final void setEac3PassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_EAC3, enabled).apply();
    }

    public final boolean isDtsPassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_DTS, true);
    }

    public final void setDtsPassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_DTS, enabled).apply();
    }

    public final boolean isDolbyAtmosPassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_ATMOS, false);
    }

    public final void setDolbyAtmosPassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_ATMOS, enabled).apply();
    }

    public final boolean isDtsHdPassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_DTS_HD, false);
    }

    public final void setDtsHdPassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_DTS_HD, enabled).apply();
    }

    public final boolean isDtsHdMaPassthroughEnabled() {
        return this.prefs.getBoolean(KEY_PASSTHROUGH_DTS_HD_MA, false);
    }

    public final void setDtsHdMaPassthroughEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_PASSTHROUGH_DTS_HD_MA, enabled).apply();
    }

    public final boolean isTranscodeToAc3Enabled() {
        return this.prefs.getBoolean(KEY_TRANSCODE_TO_AC3, false);
    }

    public final void setTranscodeToAc3Enabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_TRANSCODE_TO_AC3, enabled).apply();
    }

    public final String getPreferredAudioLanguage1() {
        return this.prefs.getString(KEY_AUDIO_LANG_1, null);
    }

    public final String getPreferredAudioLanguage2() {
        return this.prefs.getString(KEY_AUDIO_LANG_2, null);
    }

    public final void setPreferredAudioLanguages(String lang1, String lang2) {
        this.prefs.edit().putString(KEY_AUDIO_LANG_1, lang1).putString(KEY_AUDIO_LANG_2, lang2).apply();
    }

    public final String getPreferredSubtitleLanguage1() {
        return this.prefs.getString(KEY_SUB_LANG_1, null);
    }

    public final String getPreferredSubtitleLanguage2() {
        return this.prefs.getString(KEY_SUB_LANG_2, null);
    }

    public final void setPreferredSubtitleLanguages(String lang1, String lang2) {
        this.prefs.edit().putString(KEY_SUB_LANG_1, lang1).putString(KEY_SUB_LANG_2, lang2).apply();
    }

    public final DecoderPreference getDecoderPreference() {
        Object m6717constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            PlaybackSettingsStore $this$getDecoderPreference_u24lambda_u241 = this;
            String string = $this$getDecoderPreference_u24lambda_u241.prefs.getString(KEY_DECODER, null);
            if (string == null) {
                string = "PREFER_APP";
            }
            Intrinsics.checkNotNull(string);
            m6717constructorimpl = Result.m6717constructorimpl(DecoderPreference.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        DecoderPreference decoderPreference = DecoderPreference.PREFER_APP;
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = decoderPreference;
        }
        return (DecoderPreference) m6717constructorimpl;
    }

    public final void setDecoderPreference(DecoderPreference value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.prefs.edit().putString(KEY_DECODER, value.name()).apply();
    }

    public final boolean isLoadingScreenEnabled() {
        return this.prefs.getBoolean(KEY_LOADING_SCREEN, true);
    }

    public final void setLoadingScreenEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_LOADING_SCREEN, enabled).apply();
    }

    public final int getStartBufferSeconds() {
        return RangesKt.coerceIn(this.prefs.getInt(KEY_START_BUFFER_SECONDS, 2), 1, 10);
    }

    public final void setStartBufferSeconds(int valueSec) {
        this.prefs.edit().putInt(KEY_START_BUFFER_SECONDS, RangesKt.coerceIn(valueSec, 1, 10)).apply();
    }

    public final int getSeekBufferSeconds() {
        return RangesKt.coerceIn(this.prefs.getInt(KEY_SEEK_BUFFER_SECONDS, 2), 1, 10);
    }

    public final void setSeekBufferSeconds(int valueSec) {
        this.prefs.edit().putInt(KEY_SEEK_BUFFER_SECONDS, RangesKt.coerceIn(valueSec, 1, 10)).apply();
    }

    public final boolean isManagedBufferEnabled() {
        return this.prefs.getBoolean(KEY_MANAGED_BUFFER, true);
    }

    public final void setManagedBufferEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_MANAGED_BUFFER, enabled).apply();
    }

    public final int getBackBufferSeconds() {
        return RangesKt.coerceIn(this.prefs.getInt(KEY_BACK_BUFFER_SECONDS, 30), 0, 300);
    }

    public final void setBackBufferSeconds(int valueSec) {
        this.prefs.edit().putInt(KEY_BACK_BUFFER_SECONDS, RangesKt.coerceIn(valueSec, 0, 300)).apply();
    }

    public final long getInitialBitrateBps() {
        return RangesKt.coerceIn(this.prefs.getLong(KEY_INITIAL_BITRATE_BPS, 25000000L), 500000L, 200000000L);
    }

    public final void setInitialBitrateBps(long bps) {
        this.prefs.edit().putLong(KEY_INITIAL_BITRATE_BPS, RangesKt.coerceIn(bps, 500000L, 200000000L)).apply();
    }

    public final boolean isVodCacheEnabled() {
        return this.prefs.getBoolean(KEY_VOD_CACHE_ENABLED, false);
    }

    public final void setVodCacheEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_VOD_CACHE_ENABLED, enabled).apply();
    }

    public final int getVodCacheSizeMb() {
        return RangesKt.coerceIn(this.prefs.getInt(KEY_VOD_CACHE_SIZE_MB, 2048), 256, 8192);
    }

    public final void setVodCacheSizeMb(int valueMb) {
        this.prefs.edit().putInt(KEY_VOD_CACHE_SIZE_MB, RangesKt.coerceIn(valueMb, 256, 8192)).apply();
    }

    public final void clearVodCache(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        VodDiskCache vodDiskCache = VodDiskCache.INSTANCE;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        vodDiskCache.clear(applicationContext);
    }

    public final boolean isDolbyVisionP7ToHevcEnabled() {
        return this.prefs.getBoolean(KEY_DV7_TO_HEVC, true);
    }

    public final void setDolbyVisionP7ToHevcEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_DV7_TO_HEVC, enabled).apply();
    }

    public final boolean isDolbyVisionToHdr10Enabled() {
        return this.prefs.getBoolean(KEY_DV_TO_HDR10, false);
    }

    public final void setDolbyVisionToHdr10Enabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_DV_TO_HDR10, enabled).apply();
    }

    public final DnsProvider getDnsProvider() {
        return DnsProvider.INSTANCE.fromNameOrDefault(this.prefs.getString(KEY_DNS_PROVIDER, null));
    }

    public final void setDnsProvider(DnsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.prefs.edit().putString(KEY_DNS_PROVIDER, provider.name()).apply();
    }

    public final boolean isDpiBypassEnabled() {
        return this.prefs.getBoolean(KEY_DPI_BYPASS, false);
    }

    public final void setDpiBypassEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_DPI_BYPASS, enabled).apply();
    }

    public final int getDpiFragmentSize() {
        return this.prefs.getInt(KEY_DPI_FRAGMENT, 8);
    }

    public final void setDpiFragmentSize(int value) {
        this.prefs.edit().putInt(KEY_DPI_FRAGMENT, RangesKt.coerceIn(value, 2, 64)).apply();
    }

    public final int getDpiDelayMs() {
        return this.prefs.getInt(KEY_DPI_DELAY, 1);
    }

    public final void setDpiDelayMs(int value) {
        this.prefs.edit().putInt(KEY_DPI_DELAY, RangesKt.coerceIn(value, 0, 20)).apply();
    }

    public final boolean isAutoRamEnabled() {
        return this.prefs.getBoolean(KEY_AUTO_RAM, true);
    }

    public final void setAutoRamEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_AUTO_RAM, enabled).apply();
    }

    public final RamTier getRamTier(DeviceKind kind) {
        Object m6717constructorimpl;
        Intrinsics.checkNotNullParameter(kind, "kind");
        try {
            Result.Companion companion = Result.INSTANCE;
            PlaybackSettingsStore $this$getRamTier_u24lambda_u242 = this;
            String string = $this$getRamTier_u24lambda_u242.prefs.getString(KEY_RAM_TIER_PREFIX + kind.name(), null);
            if (string == null) {
                string = "RAM_4";
            }
            Intrinsics.checkNotNull(string);
            m6717constructorimpl = Result.m6717constructorimpl(RamTier.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        RamTier ramTier = RamTier.RAM_4;
        if (Result.m6723isFailureimpl(m6717constructorimpl)) {
            m6717constructorimpl = ramTier;
        }
        return (RamTier) m6717constructorimpl;
    }

    public final void setRamTier(DeviceKind kind, RamTier tier) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(tier, "tier");
        this.prefs.edit().putString(KEY_RAM_TIER_PREFIX + kind.name(), tier.name()).apply();
    }

    public final RamTier effectiveRamTier(Context context, DeviceKind kind) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (isAutoRamEnabled()) {
            return RamTier.INSTANCE.forRamMb(DeviceMemory.INSTANCE.read(context).getTotalMb());
        }
        return getRamTier(kind);
    }

    public final boolean isCustomBufferEnabled() {
        return this.prefs.getBoolean(KEY_CUSTOM_BUFFER, false);
    }

    public final void setCustomBufferEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_CUSTOM_BUFFER, enabled).apply();
    }

    public final int getMinBufferSeconds() {
        return BufferSteps.INSTANCE.normalizeMinBufferSeconds(this.prefs.getInt(KEY_MIN_BUFFER_SECONDS, 30));
    }

    public final void setMinBufferSeconds(int value) {
        this.prefs.edit().putInt(KEY_MIN_BUFFER_SECONDS, BufferSteps.INSTANCE.normalizeMinBufferSeconds(value)).apply();
    }

    public final int getMaxBufferSeconds() {
        return BufferSteps.INSTANCE.normalizeMaxBufferSeconds(this.prefs.getInt(KEY_MAX_BUFFER_SECONDS, 60));
    }

    public final void setMaxBufferSeconds(int value) {
        this.prefs.edit().putInt(KEY_MAX_BUFFER_SECONDS, BufferSteps.INSTANCE.normalizeMaxBufferSeconds(value)).apply();
    }

    public final boolean isBufferBySizeEnabled() {
        return this.prefs.getBoolean(KEY_BUFFER_BY_SIZE, false);
    }

    public final void setBufferBySizeEnabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_BUFFER_BY_SIZE, enabled).apply();
    }

    public final int getBufferSizeMb() {
        return this.prefs.getInt(KEY_BUFFER_SIZE_MB, 64);
    }

    public final void setBufferSizeMb(int value) {
        this.prefs.edit().putInt(KEY_BUFFER_SIZE_MB, RangesKt.coerceIn(value, BufferSteps.INSTANCE.getBUFFER_SIZE_MB().getFirst(), BufferSteps.INSTANCE.getBUFFER_SIZE_MB().getLast())).apply();
    }

    public final boolean isHttp2Enabled() {
        return this.prefs.getBoolean(KEY_HTTP2, true);
    }

    public final void setHttp2Enabled(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_HTTP2, enabled).apply();
    }

    public final int getParallelConnections() {
        return this.prefs.getInt(KEY_PARALLEL, 1);
    }

    public final void setParallelConnections(int value) {
        this.prefs.edit().putInt(KEY_PARALLEL, RangesKt.coerceIn(value, 1, 8)).apply();
    }

    public final int getChunkSizeMb() {
        int legacyKb = this.prefs.getInt(KEY_CHUNK_SIZE_KB_LEGACY, -1);
        if (legacyKb > 0) {
            int mb = BufferSteps.INSTANCE.chunkMbFromLegacyKb(legacyKb);
            this.prefs.edit().putInt(KEY_CHUNK_SIZE_MB, mb).remove(KEY_CHUNK_SIZE_KB_LEGACY).apply();
            return mb;
        }
        return BufferSteps.INSTANCE.normalizeChunkMb(this.prefs.getInt(KEY_CHUNK_SIZE_MB, 1));
    }

    public final void setChunkSizeMb(int value) {
        this.prefs.edit().putInt(KEY_CHUNK_SIZE_MB, BufferSteps.INSTANCE.normalizeChunkMb(value)).remove(KEY_CHUNK_SIZE_KB_LEGACY).apply();
    }

    public final int getChunkCount() {
        return this.prefs.getInt(KEY_CHUNK_COUNT, 1);
    }

    public final void setChunkCount(int value) {
        this.prefs.edit().putInt(KEY_CHUNK_COUNT, RangesKt.coerceIn(value, 1, 8)).apply();
    }

    public final VideoScaleMode getVideoScaleMode() {
        Object obj = null;
        String name = this.prefs.getString(KEY_VIDEO_SCALE, null);
        if (name == null) {
            return defaultVideoScaleMode();
        }
        Iterable $this$firstOrNull$iv = VideoScaleMode.getEntries();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            VideoScaleMode it2 = (VideoScaleMode) element$iv;
            if (Intrinsics.areEqual(it2.name(), name)) {
                obj = element$iv;
                break;
            }
        }
        VideoScaleMode videoScaleMode = (VideoScaleMode) obj;
        return videoScaleMode == null ? defaultVideoScaleMode() : videoScaleMode;
    }

    public final void setVideoScaleMode(VideoScaleMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.prefs.edit().putString(KEY_VIDEO_SCALE, mode.name()).apply();
    }

    private final VideoScaleMode defaultVideoScaleMode() {
        return VideoScaleMode.FIT;
    }

    public final void resetBufferAndNetwork() {
        SharedPreferences.Editor $this$resetBufferAndNetwork_u24lambda_u246 = this.prefs.edit();
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_AUTO_RAM);
        Iterable $this$filter$iv = this.prefs.getAll().keySet();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            Intrinsics.checkNotNull(it);
            if (StringsKt.startsWith$default(it, KEY_RAM_TIER_PREFIX, false, 2, (Object) null)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$forEach$iv = (List) destination$iv$iv;
        for (Object element$iv : $this$forEach$iv) {
            $this$resetBufferAndNetwork_u24lambda_u246.remove((String) element$iv);
        }
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_CUSTOM_BUFFER);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_MIN_BUFFER_SECONDS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_MAX_BUFFER_SECONDS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_BUFFER_BY_SIZE);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_BUFFER_SIZE_MB);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_MANAGED_BUFFER);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_BACK_BUFFER_SECONDS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_START_BUFFER_SECONDS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_SEEK_BUFFER_SECONDS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_INITIAL_BITRATE_BPS);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_HTTP2);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_PARALLEL);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_CHUNK_SIZE_MB);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_CHUNK_SIZE_KB_LEGACY);
        $this$resetBufferAndNetwork_u24lambda_u246.remove(KEY_CHUNK_COUNT);
        $this$resetBufferAndNetwork_u24lambda_u246.apply();
    }

    /* compiled from: PlaybackSettingsStore.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b)\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/streamsync/app/settings/PlaybackSettingsStore$Companion;", "", "()V", "DEFAULT_PASSTHROUGH_MODE", "Lcom/streamsync/app/ui/player/PassthroughMode;", "getDEFAULT_PASSTHROUGH_MODE", "()Lcom/streamsync/app/ui/player/PassthroughMode;", "DEFAULT_VOD_CACHE_MB", "", "KEY_AUDIO_LANG_1", "", "KEY_AUDIO_LANG_2", "KEY_AUTO_RAM", "KEY_BACK_BUFFER_SECONDS", "KEY_BUFFER_BY_SIZE", "KEY_BUFFER_SIZE_MB", "KEY_CHUNK_COUNT", "KEY_CHUNK_SIZE_KB_LEGACY", "KEY_CHUNK_SIZE_MB", "KEY_CUSTOM_BUFFER", "KEY_DECODER", "KEY_DNS_PROVIDER", "KEY_DPI_BYPASS", "KEY_DPI_DELAY", "KEY_DPI_FRAGMENT", "KEY_DV7_TO_HEVC", "KEY_DV_TO_HDR10", "KEY_HTTP2", "KEY_INITIAL_BITRATE_BPS", "KEY_LOADING_SCREEN", "KEY_MANAGED_BUFFER", "KEY_MAX_BUFFER_SECONDS", "KEY_MIN_BUFFER_SECONDS", "KEY_OPTICAL_PASSTHROUGH", "KEY_PARALLEL", "KEY_PASSTHROUGH_AC3", "KEY_PASSTHROUGH_ATMOS", "KEY_PASSTHROUGH_DTS", "KEY_PASSTHROUGH_DTS_HD", "KEY_PASSTHROUGH_DTS_HD_MA", "KEY_PASSTHROUGH_EAC3", "KEY_PASSTHROUGH_MODE", "KEY_RAM_TIER_PREFIX", "KEY_SEEK_BUFFER_SECONDS", "KEY_START_BUFFER_SECONDS", "KEY_SUB_LANG_1", "KEY_SUB_LANG_2", "KEY_TRANSCODE_TO_AC3", "KEY_VIDEO_SCALE", "KEY_VOD_CACHE_ENABLED", "KEY_VOD_CACHE_SIZE_MB", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PassthroughMode getDEFAULT_PASSTHROUGH_MODE() {
            return PlaybackSettingsStore.DEFAULT_PASSTHROUGH_MODE;
        }
    }
}
