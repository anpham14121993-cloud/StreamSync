package androidx.media3.exoplayer.upstream;

import android.content.Context;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.MotionEventCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.mlkit.common.MlKitException;
import com.streamsync.app.settings.BufferCeiling;
import java.util.HashMap;
import java.util.Map;
import kotlin.io.encoding.Base64;
import kotlin.text.Typography;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes10.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    private static final int COUNTRY_GROUP_INDEX_2G = 1;
    private static final int COUNTRY_GROUP_INDEX_3G = 2;
    private static final int COUNTRY_GROUP_INDEX_4G = 3;
    private static final int COUNTRY_GROUP_INDEX_5G_NSA = 4;
    private static final int COUNTRY_GROUP_INDEX_5G_SA = 5;
    private static final int COUNTRY_GROUP_INDEX_WIFI = 0;
    public static final long DEFAULT_INITIAL_BITRATE_ESTIMATE = 1000000;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_3G;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_4G;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA;
    public static final int DEFAULT_SLIDING_WINDOW_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private static DefaultBandwidthMeter singletonInstance;
    private long bitrateEstimate;
    private final Clock clock;
    private final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    private final ImmutableMap<Integer, Long> initialBitrateEstimates;
    private long lastReportedBitrateEstimate;
    private int networkType;
    private int networkTypeOverride;
    private boolean networkTypeOverrideSet;
    private final boolean resetOnNetworkTypeChange;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = ImmutableList.of(4300000L, 3200000L, 2400000L, 1700000L, 860000L);
    public static final ImmutableList<Long> DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = ImmutableList.of(1500000L, 980000L, 750000L, 520000L, 290000L);

    static {
        Long valueOf = Long.valueOf(SilenceSkippingAudioProcessor.DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = ImmutableList.of((long) valueOf, 1300000L, 1000000L, 860000L, 610000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = ImmutableList.of(2500000L, 1700000L, 1200000L, 970000L, 680000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA = ImmutableList.of(4700000L, 2800000L, 2100000L, 1700000L, 980000L);
        DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA = ImmutableList.of(2700000L, (long) valueOf, 1600000L, 1300000L, 1000000L);
    }

    /* loaded from: classes10.dex */
    public static final class Builder {
        private Clock clock;
        private final Context context;
        private Map<Integer, Long> initialBitrateEstimates;
        private boolean resetOnNetworkTypeChange;
        private int slidingWindowMaxWeight;

        public Builder(Context context) {
            this.context = context == null ? null : context.getApplicationContext();
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.getCountryCode(context));
            this.slidingWindowMaxWeight = 2000;
            this.clock = Clock.DEFAULT;
            this.resetOnNetworkTypeChange = true;
        }

        public Builder setSlidingWindowMaxWeight(int slidingWindowMaxWeight) {
            this.slidingWindowMaxWeight = slidingWindowMaxWeight;
            return this;
        }

        public Builder setInitialBitrateEstimate(long initialBitrateEstimate) {
            for (Integer networkType : this.initialBitrateEstimates.keySet()) {
                setInitialBitrateEstimate(networkType.intValue(), initialBitrateEstimate);
            }
            return this;
        }

        public Builder setInitialBitrateEstimate(int networkType, long initialBitrateEstimate) {
            this.initialBitrateEstimates.put(Integer.valueOf(networkType), Long.valueOf(initialBitrateEstimate));
            return this;
        }

        public Builder setInitialBitrateEstimate(String countryCode) {
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Ascii.toUpperCase(countryCode));
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setResetOnNetworkTypeChange(boolean resetOnNetworkTypeChange) {
            this.resetOnNetworkTypeChange = resetOnNetworkTypeChange;
            return this;
        }

        public DefaultBandwidthMeter build() {
            return new DefaultBandwidthMeter(this.context, this.initialBitrateEstimates, this.slidingWindowMaxWeight, this.clock, this.resetOnNetworkTypeChange);
        }

        private static Map<Integer, Long> getInitialBitrateEstimatesForCountry(String countryCode) {
            int[] groupIndices = DefaultBandwidthMeter.getInitialBitrateCountryGroupAssignment(countryCode);
            Map<Integer, Long> result = new HashMap<>(8);
            result.put(0, 1000000L);
            result.put(2, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI.get(groupIndices[0]));
            result.put(3, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_2G.get(groupIndices[1]));
            result.put(4, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_3G.get(groupIndices[2]));
            result.put(5, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_4G.get(groupIndices[3]));
            result.put(10, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_NSA.get(groupIndices[4]));
            result.put(9, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_5G_SA.get(groupIndices[5]));
            result.put(7, DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI.get(groupIndices[0]));
            return result;
        }
    }

    public static synchronized DefaultBandwidthMeter getSingletonInstance(Context context) {
        DefaultBandwidthMeter defaultBandwidthMeter;
        synchronized (DefaultBandwidthMeter.class) {
            if (singletonInstance == null) {
                singletonInstance = new Builder(context).build();
            }
            defaultBandwidthMeter = singletonInstance;
        }
        return defaultBandwidthMeter;
    }

    private DefaultBandwidthMeter(Context context, Map<Integer, Long> initialBitrateEstimates, int maxWeight, Clock clock, boolean resetOnNetworkTypeChange) {
        this.initialBitrateEstimates = ImmutableMap.copyOf((Map) initialBitrateEstimates);
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.slidingPercentile = new SlidingPercentile(maxWeight);
        this.clock = clock;
        this.resetOnNetworkTypeChange = resetOnNetworkTypeChange;
        if (context != null) {
            NetworkTypeObserver networkTypeObserver = NetworkTypeObserver.getInstance(context);
            this.networkType = networkTypeObserver.getNetworkType();
            this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(this.networkType);
            networkTypeObserver.register(new NetworkTypeObserver.Listener() { // from class: androidx.media3.exoplayer.upstream.DefaultBandwidthMeter$$ExternalSyntheticLambda0
                @Override // androidx.media3.common.util.NetworkTypeObserver.Listener
                public final void onNetworkTypeChanged(int i) {
                    DefaultBandwidthMeter.this.onNetworkTypeChanged(i);
                }
            });
            return;
        }
        this.networkType = 0;
        this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(0);
    }

    public synchronized void setNetworkTypeOverride(int networkType) {
        this.networkTypeOverride = networkType;
        this.networkTypeOverrideSet = true;
        onNetworkTypeChanged(networkType);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public TransferListener getTransferListener() {
        return this;
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void addEventListener(Handler eventHandler, BandwidthMeter.EventListener eventListener) {
        Assertions.checkNotNull(eventHandler);
        Assertions.checkNotNull(eventListener);
        this.eventDispatcher.addListener(eventHandler, eventListener);
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter
    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    @Override // androidx.media3.datasource.TransferListener
    public void onTransferInitializing(DataSource source, DataSpec dataSpec, boolean isNetwork) {
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferStart(DataSource source, DataSpec dataSpec, boolean isNetwork) {
        if (isTransferAtFullNetworkSpeed(dataSpec, isNetwork)) {
            if (this.streamCount == 0) {
                this.sampleStartTimeMs = this.clock.elapsedRealtime();
            }
            this.streamCount++;
        }
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onBytesTransferred(DataSource source, DataSpec dataSpec, boolean isNetwork, int bytesTransferred) {
        if (isTransferAtFullNetworkSpeed(dataSpec, isNetwork)) {
            this.sampleBytesTransferred += bytesTransferred;
        }
    }

    @Override // androidx.media3.datasource.TransferListener
    public synchronized void onTransferEnd(DataSource source, DataSpec dataSpec, boolean isNetwork) {
        if (isTransferAtFullNetworkSpeed(dataSpec, isNetwork)) {
            Assertions.checkState(this.streamCount > 0);
            long nowMs = this.clock.elapsedRealtime();
            int sampleElapsedTimeMs = (int) (nowMs - this.sampleStartTimeMs);
            this.totalElapsedTimeMs += sampleElapsedTimeMs;
            this.totalBytesTransferred += this.sampleBytesTransferred;
            if (sampleElapsedTimeMs > 0) {
                float bitsPerSecond = (((float) this.sampleBytesTransferred) * 8000.0f) / sampleElapsedTimeMs;
                this.slidingPercentile.addSample((int) Math.sqrt(this.sampleBytesTransferred), bitsPerSecond);
                if (this.totalElapsedTimeMs >= 2000 || this.totalBytesTransferred >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    this.bitrateEstimate = this.slidingPercentile.getPercentile(0.5f);
                }
                maybeNotifyBandwidthSample(sampleElapsedTimeMs, this.sampleBytesTransferred, this.bitrateEstimate);
                this.sampleStartTimeMs = nowMs;
                this.sampleBytesTransferred = 0L;
            }
            this.streamCount--;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onNetworkTypeChanged(int networkType) {
        if (this.networkType == 0 || this.resetOnNetworkTypeChange) {
            if (this.networkTypeOverrideSet) {
                networkType = this.networkTypeOverride;
            }
            if (this.networkType == networkType) {
                return;
            }
            this.networkType = networkType;
            if (networkType != 1 && networkType != 0 && networkType != 8) {
                this.bitrateEstimate = getInitialBitrateEstimateForNetworkType(networkType);
                long nowMs = this.clock.elapsedRealtime();
                int sampleElapsedTimeMs = this.streamCount > 0 ? (int) (nowMs - this.sampleStartTimeMs) : 0;
                maybeNotifyBandwidthSample(sampleElapsedTimeMs, this.sampleBytesTransferred, this.bitrateEstimate);
                this.sampleStartTimeMs = nowMs;
                this.sampleBytesTransferred = 0L;
                this.totalBytesTransferred = 0L;
                this.totalElapsedTimeMs = 0L;
                this.slidingPercentile.reset();
            }
        }
    }

    private void maybeNotifyBandwidthSample(int elapsedMs, long bytesTransferred, long bitrateEstimate) {
        if (elapsedMs == 0 && bytesTransferred == 0 && bitrateEstimate == this.lastReportedBitrateEstimate) {
            return;
        }
        this.lastReportedBitrateEstimate = bitrateEstimate;
        this.eventDispatcher.bandwidthSample(elapsedMs, bytesTransferred, bitrateEstimate);
    }

    private long getInitialBitrateEstimateForNetworkType(int networkType) {
        Long initialBitrateEstimate = this.initialBitrateEstimates.get(Integer.valueOf(networkType));
        if (initialBitrateEstimate == null) {
            initialBitrateEstimate = this.initialBitrateEstimates.get(0);
        }
        if (initialBitrateEstimate == null) {
            initialBitrateEstimate = 1000000L;
        }
        return initialBitrateEstimate.longValue();
    }

    private static boolean isTransferAtFullNetworkSpeed(DataSpec dataSpec, boolean isNetwork) {
        return isNetwork && !dataSpec.isFlagSet(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int[] getInitialBitrateCountryGroupAssignment(String country) {
        char c;
        switch (country.hashCode()) {
            case 2083:
                if (country.equals("AD")) {
                    c = 205;
                    break;
                }
                c = 65535;
                break;
            case 2084:
                if (country.equals("AE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 2085:
                if (country.equals("AF")) {
                    c = 168;
                    break;
                }
                c = 65535;
                break;
            case 2086:
                if (country.equals("AG")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 2088:
                if (country.equals("AI")) {
                    c = 206;
                    break;
                }
                c = 65535;
                break;
            case 2091:
                if (country.equals("AL")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2092:
                if (country.equals("AM")) {
                    c = 's';
                    break;
                }
                c = 65535;
                break;
            case 2094:
                if (country.equals("AO")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2096:
                if (country.equals("AQ")) {
                    c = 145;
                    break;
                }
                c = 65535;
                break;
            case 2097:
                if (country.equals("AR")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2098:
                if (country.equals("AS")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2099:
                if (country.equals("AT")) {
                    c = 137;
                    break;
                }
                c = 65535;
                break;
            case 2100:
                if (country.equals("AU")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 2102:
                if (country.equals("AW")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2103:
                if (country.equals("AX")) {
                    c = 198;
                    break;
                }
                c = 65535;
                break;
            case 2105:
                if (country.equals("AZ")) {
                    c = 157;
                    break;
                }
                c = 65535;
                break;
            case 2111:
                if (country.equals("BA")) {
                    c = 186;
                    break;
                }
                c = 65535;
                break;
            case 2112:
                if (country.equals("BB")) {
                    c = 207;
                    break;
                }
                c = 65535;
                break;
            case 2114:
                if (country.equals("BD")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2115:
                if (country.equals("BE")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 2116:
                if (country.equals("BF")) {
                    c = 171;
                    break;
                }
                c = 65535;
                break;
            case 2117:
                if (country.equals("BG")) {
                    c = 150;
                    break;
                }
                c = 65535;
                break;
            case 2118:
                if (country.equals("BH")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2119:
                if (country.equals("BI")) {
                    c = 227;
                    break;
                }
                c = 65535;
                break;
            case 2120:
                if (country.equals("BJ")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 2122:
                if (country.equals("BL")) {
                    c = Ascii.MAX;
                    break;
                }
                c = 65535;
                break;
            case 2123:
                if (country.equals("BM")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 2124:
                if (country.equals("BN")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 2125:
                if (country.equals("BO")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 2127:
                if (country.equals("BQ")) {
                    c = 208;
                    break;
                }
                c = 65535;
                break;
            case 2128:
                if (country.equals("BR")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 2129:
                if (country.equals("BS")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 2130:
                if (country.equals("BT")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 2133:
                if (country.equals("BW")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 2135:
                if (country.equals("BY")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 2136:
                if (country.equals("BZ")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 2142:
                if (country.equals("CA")) {
                    c = 192;
                    break;
                }
                c = 65535;
                break;
            case 2145:
                if (country.equals("CD")) {
                    c = '\\';
                    break;
                }
                c = 65535;
                break;
            case 2147:
                if (country.equals("CF")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2148:
                if (country.equals("CG")) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case 2149:
                if (country.equals("CH")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 2150:
                if (country.equals("CI")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 2152:
                if (country.equals("CK")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 2153:
                if (country.equals("CL")) {
                    c = Typography.degree;
                    break;
                }
                c = 65535;
                break;
            case 2154:
                if (country.equals("CM")) {
                    c = 'c';
                    break;
                }
                c = 65535;
                break;
            case 2155:
                if (country.equals("CN")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case 2156:
                if (country.equals("CO")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 2159:
                if (country.equals("CR")) {
                    c = 'l';
                    break;
                }
                c = 65535;
                break;
            case 2162:
                if (country.equals("CU")) {
                    c = 180;
                    break;
                }
                c = 65535;
                break;
            case 2163:
                if (country.equals("CV")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 2164:
                if (country.equals("CW")) {
                    c = 209;
                    break;
                }
                c = 65535;
                break;
            case 2165:
                if (country.equals("CX")) {
                    c = 199;
                    break;
                }
                c = 65535;
                break;
            case 2166:
                if (country.equals("CY")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 2167:
                if (country.equals("CZ")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 2177:
                if (country.equals("DE")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case 2182:
                if (country.equals("DJ")) {
                    c = 158;
                    break;
                }
                c = 65535;
                break;
            case 2183:
                if (country.equals("DK")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 2185:
                if (country.equals("DM")) {
                    c = 210;
                    break;
                }
                c = 65535;
                break;
            case 2187:
                if (country.equals("DO")) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case 2198:
                if (country.equals("DZ")) {
                    c = 178;
                    break;
                }
                c = 65535;
                break;
            case 2206:
                if (country.equals("EC")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case 2208:
                if (country.equals("EE")) {
                    c = 138;
                    break;
                }
                c = 65535;
                break;
            case 2210:
                if (country.equals("EG")) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case 2221:
                if (country.equals("ER")) {
                    c = 146;
                    break;
                }
                c = 65535;
                break;
            case 2222:
                if (country.equals("ES")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case 2223:
                if (country.equals("ET")) {
                    c = Typography.quote;
                    break;
                }
                c = 65535;
                break;
            case 2243:
                if (country.equals("FI")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 2244:
                if (country.equals("FJ")) {
                    c = Typography.dollar;
                    break;
                }
                c = 65535;
                break;
            case 2245:
                if (country.equals("FK")) {
                    c = 153;
                    break;
                }
                c = 65535;
                break;
            case 2247:
                if (country.equals("FM")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 2249:
                if (country.equals("FO")) {
                    c = Typography.amp;
                    break;
                }
                c = 65535;
                break;
            case 2252:
                if (country.equals("FR")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case 2266:
                if (country.equals("GA")) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case 2267:
                if (country.equals("GB")) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 2269:
                if (country.equals("GD")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 2270:
                if (country.equals("GE")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 2271:
                if (country.equals("GF")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 2272:
                if (country.equals("GG")) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case 2273:
                if (country.equals("GH")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case 2274:
                if (country.equals("GI")) {
                    c = '?';
                    break;
                }
                c = 65535;
                break;
            case 2277:
                if (country.equals("GL")) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case 2278:
                if (country.equals("GM")) {
                    c = 164;
                    break;
                }
                c = 65535;
                break;
            case 2279:
                if (country.equals("GN")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 2281:
                if (country.equals("GP")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case 2282:
                if (country.equals("GQ")) {
                    c = 228;
                    break;
                }
                c = 65535;
                break;
            case 2283:
                if (country.equals("GR")) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 2285:
                if (country.equals("GT")) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 2286:
                if (country.equals("GU")) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 2288:
                if (country.equals("GW")) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 2290:
                if (country.equals("GY")) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 2307:
                if (country.equals("HK")) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case 2314:
                if (country.equals("HR")) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case 2316:
                if (country.equals("HT")) {
                    c = 229;
                    break;
                }
                c = 65535;
                break;
            case 2317:
                if (country.equals("HU")) {
                    c = 139;
                    break;
                }
                c = 65535;
                break;
            case 2331:
                if (country.equals("ID")) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case 2332:
                if (country.equals("IE")) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case 2339:
                if (country.equals("IL")) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case 2340:
                if (country.equals("IM")) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 2341:
                if (country.equals("IN")) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case 2342:
                if (country.equals("IO")) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case 2344:
                if (country.equals("IQ")) {
                    c = Typography.less;
                    break;
                }
                c = 65535;
                break;
            case 2345:
                if (country.equals("IR")) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case 2346:
                if (country.equals("IS")) {
                    c = 140;
                    break;
                }
                c = 65535;
                break;
            case 2347:
                if (country.equals("IT")) {
                    c = Typography.greater;
                    break;
                }
                c = 65535;
                break;
            case 2363:
                if (country.equals("JE")) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 2371:
                if (country.equals("JM")) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case 2373:
                if (country.equals("JO")) {
                    c = 187;
                    break;
                }
                c = 65535;
                break;
            case 2374:
                if (country.equals("JP")) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case 2394:
                if (country.equals("KE")) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case 2396:
                if (country.equals("KG")) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case 2397:
                if (country.equals("KH")) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case 2398:
                if (country.equals("KI")) {
                    c = 181;
                    break;
                }
                c = 65535;
                break;
            case 2402:
                if (country.equals("KM")) {
                    c = 218;
                    break;
                }
                c = 65535;
                break;
            case 2403:
                if (country.equals("KN")) {
                    c = 211;
                    break;
                }
                c = 65535;
                break;
            case 2407:
                if (country.equals("KR")) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case 2412:
                if (country.equals("KW")) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case 2414:
                if (country.equals("KY")) {
                    c = 212;
                    break;
                }
                c = 65535;
                break;
            case 2415:
                if (country.equals("KZ")) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case 2421:
                if (country.equals("LA")) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 2422:
                if (country.equals("LB")) {
                    c = 'L';
                    break;
                }
                c = 65535;
                break;
            case 2423:
                if (country.equals("LC")) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case 2429:
                if (country.equals("LI")) {
                    c = 200;
                    break;
                }
                c = 65535;
                break;
            case 2431:
                if (country.equals("LK")) {
                    c = '^';
                    break;
                }
                c = 65535;
                break;
            case 2438:
                if (country.equals("LR")) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case 2439:
                if (country.equals("LS")) {
                    c = 'w';
                    break;
                }
                c = 65535;
                break;
            case 2440:
                if (country.equals("LT")) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case 2441:
                if (country.equals("LU")) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 2442:
                if (country.equals("LV")) {
                    c = 141;
                    break;
                }
                c = 65535;
                break;
            case 2445:
                if (country.equals("LY")) {
                    c = 159;
                    break;
                }
                c = 65535;
                break;
            case 2452:
                if (country.equals("MA")) {
                    c = 'R';
                    break;
                }
                c = 65535;
                break;
            case 2454:
                if (country.equals("MC")) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case 2455:
                if (country.equals("MD")) {
                    c = 'U';
                    break;
                }
                c = 65535;
                break;
            case 2456:
                if (country.equals("ME")) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case 2457:
                if (country.equals("MF")) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case 2458:
                if (country.equals("MG")) {
                    c = 'Z';
                    break;
                }
                c = 65535;
                break;
            case 2459:
                if (country.equals("MH")) {
                    c = 220;
                    break;
                }
                c = 65535;
                break;
            case 2462:
                if (country.equals("MK")) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case 2463:
                if (country.equals("ML")) {
                    c = ']';
                    break;
                }
                c = 65535;
                break;
            case 2464:
                if (country.equals("MM")) {
                    c = '_';
                    break;
                }
                c = 65535;
                break;
            case 2465:
                if (country.equals("MN")) {
                    c = '`';
                    break;
                }
                c = 65535;
                break;
            case 2466:
                if (country.equals("MO")) {
                    c = 'a';
                    break;
                }
                c = 65535;
                break;
            case 2467:
                if (country.equals("MP")) {
                    c = 128;
                    break;
                }
                c = 65535;
                break;
            case 2468:
                if (country.equals("MQ")) {
                    c = 'b';
                    break;
                }
                c = 65535;
                break;
            case 2469:
                if (country.equals("MR")) {
                    c = 'd';
                    break;
                }
                c = 65535;
                break;
            case 2470:
                if (country.equals("MS")) {
                    c = 201;
                    break;
                }
                c = 65535;
                break;
            case 2471:
                if (country.equals("MT")) {
                    c = 142;
                    break;
                }
                c = 65535;
                break;
            case 2472:
                if (country.equals("MU")) {
                    c = 'e';
                    break;
                }
                c = 65535;
                break;
            case 2473:
                if (country.equals("MV")) {
                    c = 'f';
                    break;
                }
                c = 65535;
                break;
            case 2474:
                if (country.equals("MW")) {
                    c = 'g';
                    break;
                }
                c = 65535;
                break;
            case 2475:
                if (country.equals("MX")) {
                    c = 'h';
                    break;
                }
                c = 65535;
                break;
            case 2476:
                if (country.equals("MY")) {
                    c = 'i';
                    break;
                }
                c = 65535;
                break;
            case 2477:
                if (country.equals("MZ")) {
                    c = 224;
                    break;
                }
                c = 65535;
                break;
            case 2483:
                if (country.equals("NA")) {
                    c = 'j';
                    break;
                }
                c = 65535;
                break;
            case 2485:
                if (country.equals("NC")) {
                    c = 233;
                    break;
                }
                c = 65535;
                break;
            case 2487:
                if (country.equals("NE")) {
                    c = 230;
                    break;
                }
                c = 65535;
                break;
            case 2488:
                if (country.equals("NF")) {
                    c = 154;
                    break;
                }
                c = 65535;
                break;
            case 2489:
                if (country.equals("NG")) {
                    c = 'k';
                    break;
                }
                c = 65535;
                break;
            case 2491:
                if (country.equals("NI")) {
                    c = 'm';
                    break;
                }
                c = 65535;
                break;
            case 2494:
                if (country.equals("NL")) {
                    c = 'n';
                    break;
                }
                c = 65535;
                break;
            case 2497:
                if (country.equals("NO")) {
                    c = 'o';
                    break;
                }
                c = 65535;
                break;
            case 2498:
                if (country.equals("NP")) {
                    c = 'p';
                    break;
                }
                c = 65535;
                break;
            case DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS /* 2500 */:
                if (country.equals("NR")) {
                    c = Typography.paragraph;
                    break;
                }
                c = 65535;
                break;
            case 2503:
                if (country.equals("NU")) {
                    c = 147;
                    break;
                }
                c = 65535;
                break;
            case 2508:
                if (country.equals("NZ")) {
                    c = 'q';
                    break;
                }
                c = 65535;
                break;
            case 2526:
                if (country.equals("OM")) {
                    c = 'r';
                    break;
                }
                c = 65535;
                break;
            case 2545:
                if (country.equals("PA")) {
                    c = 't';
                    break;
                }
                c = 65535;
                break;
            case 2549:
                if (country.equals("PE")) {
                    c = 'u';
                    break;
                }
                c = 65535;
                break;
            case 2550:
                if (country.equals("PF")) {
                    c = 'v';
                    break;
                }
                c = 65535;
                break;
            case 2551:
                if (country.equals("PG")) {
                    c = 'x';
                    break;
                }
                c = 65535;
                break;
            case 2552:
                if (country.equals("PH")) {
                    c = 'y';
                    break;
                }
                c = 65535;
                break;
            case 2555:
                if (country.equals("PK")) {
                    c = 'z';
                    break;
                }
                c = 65535;
                break;
            case 2556:
                if (country.equals("PL")) {
                    c = '{';
                    break;
                }
                c = 65535;
                break;
            case 2557:
                if (country.equals("PM")) {
                    c = 202;
                    break;
                }
                c = 65535;
                break;
            case 2562:
                if (country.equals("PR")) {
                    c = '|';
                    break;
                }
                c = 65535;
                break;
            case 2563:
                if (country.equals("PS")) {
                    c = '}';
                    break;
                }
                c = 65535;
                break;
            case 2564:
                if (country.equals("PT")) {
                    c = 151;
                    break;
                }
                c = 65535;
                break;
            case 2567:
                if (country.equals("PW")) {
                    c = '~';
                    break;
                }
                c = 65535;
                break;
            case 2569:
                if (country.equals("PY")) {
                    c = 129;
                    break;
                }
                c = 65535;
                break;
            case 2576:
                if (country.equals("QA")) {
                    c = 130;
                    break;
                }
                c = 65535;
                break;
            case 2611:
                if (country.equals("RE")) {
                    c = 131;
                    break;
                }
                c = 65535;
                break;
            case 2621:
                if (country.equals("RO")) {
                    c = 132;
                    break;
                }
                c = 65535;
                break;
            case 2625:
                if (country.equals("RS")) {
                    c = 133;
                    break;
                }
                c = 65535;
                break;
            case 2627:
                if (country.equals("RU")) {
                    c = 134;
                    break;
                }
                c = 65535;
                break;
            case 2629:
                if (country.equals("RW")) {
                    c = 135;
                    break;
                }
                c = 65535;
                break;
            case 2638:
                if (country.equals("SA")) {
                    c = 136;
                    break;
                }
                c = 65535;
                break;
            case 2639:
                if (country.equals("SB")) {
                    c = 237;
                    break;
                }
                c = 65535;
                break;
            case 2640:
                if (country.equals("SC")) {
                    c = 148;
                    break;
                }
                c = 65535;
                break;
            case 2641:
                if (country.equals("SD")) {
                    c = 172;
                    break;
                }
                c = 65535;
                break;
            case 2642:
                if (country.equals("SE")) {
                    c = 143;
                    break;
                }
                c = 65535;
                break;
            case 2644:
                if (country.equals("SG")) {
                    c = 144;
                    break;
                }
                c = 65535;
                break;
            case 2645:
                if (country.equals("SH")) {
                    c = 149;
                    break;
                }
                c = 65535;
                break;
            case 2646:
                if (country.equals("SI")) {
                    c = 152;
                    break;
                }
                c = 65535;
                break;
            case 2647:
                if (country.equals("SJ")) {
                    c = 155;
                    break;
                }
                c = 65535;
                break;
            case 2648:
                if (country.equals("SK")) {
                    c = 156;
                    break;
                }
                c = 65535;
                break;
            case 2649:
                if (country.equals("SL")) {
                    c = Typography.nbsp;
                    break;
                }
                c = 65535;
                break;
            case 2650:
                if (country.equals("SM")) {
                    c = 203;
                    break;
                }
                c = 65535;
                break;
            case 2651:
                if (country.equals("SN")) {
                    c = 161;
                    break;
                }
                c = 65535;
                break;
            case 2652:
                if (country.equals("SO")) {
                    c = Typography.cent;
                    break;
                }
                c = 65535;
                break;
            case 2655:
                if (country.equals("SR")) {
                    c = Typography.pound;
                    break;
                }
                c = 65535;
                break;
            case 2656:
                if (country.equals("SS")) {
                    c = 165;
                    break;
                }
                c = 65535;
                break;
            case 2657:
                if (country.equals("ST")) {
                    c = 166;
                    break;
                }
                c = 65535;
                break;
            case 2659:
                if (country.equals("SV")) {
                    c = Typography.section;
                    break;
                }
                c = 65535;
                break;
            case 2661:
                if (country.equals("SX")) {
                    c = 213;
                    break;
                }
                c = 65535;
                break;
            case 2662:
                if (country.equals("SY")) {
                    c = 173;
                    break;
                }
                c = 65535;
                break;
            case 2663:
                if (country.equals("SZ")) {
                    c = Typography.copyright;
                    break;
                }
                c = 65535;
                break;
            case 2671:
                if (country.equals("TC")) {
                    c = 170;
                    break;
                }
                c = 65535;
                break;
            case 2672:
                if (country.equals("TD")) {
                    c = Typography.registered;
                    break;
                }
                c = 65535;
                break;
            case 2675:
                if (country.equals("TG")) {
                    c = 175;
                    break;
                }
                c = 65535;
                break;
            case 2676:
                if (country.equals("TH")) {
                    c = Typography.plusMinus;
                    break;
                }
                c = 65535;
                break;
            case 2678:
                if (country.equals("TJ")) {
                    c = 179;
                    break;
                }
                c = 65535;
                break;
            case 2680:
                if (country.equals("TL")) {
                    c = Typography.middleDot;
                    break;
                }
                c = 65535;
                break;
            case 2681:
                if (country.equals("TM")) {
                    c = 221;
                    break;
                }
                c = 65535;
                break;
            case 2682:
                if (country.equals("TN")) {
                    c = 184;
                    break;
                }
                c = 65535;
                break;
            case 2683:
                if (country.equals("TO")) {
                    c = 185;
                    break;
                }
                c = 65535;
                break;
            case 2686:
                if (country.equals("TR")) {
                    c = 188;
                    break;
                }
                c = 65535;
                break;
            case 2688:
                if (country.equals("TT")) {
                    c = Typography.half;
                    break;
                }
                c = 65535;
                break;
            case 2690:
                if (country.equals("TV")) {
                    c = 222;
                    break;
                }
                c = 65535;
                break;
            case 2691:
                if (country.equals("TW")) {
                    c = 190;
                    break;
                }
                c = 65535;
                break;
            case 2694:
                if (country.equals("TZ")) {
                    c = 191;
                    break;
                }
                c = 65535;
                break;
            case 2700:
                if (country.equals("UA")) {
                    c = 193;
                    break;
                }
                c = 65535;
                break;
            case 2706:
                if (country.equals("UG")) {
                    c = 194;
                    break;
                }
                c = 65535;
                break;
            case 2718:
                if (country.equals("US")) {
                    c = 195;
                    break;
                }
                c = 65535;
                break;
            case 2724:
                if (country.equals("UY")) {
                    c = 196;
                    break;
                }
                c = 65535;
                break;
            case 2725:
                if (country.equals("UZ")) {
                    c = 197;
                    break;
                }
                c = 65535;
                break;
            case 2731:
                if (country.equals("VA")) {
                    c = 204;
                    break;
                }
                c = 65535;
                break;
            case 2733:
                if (country.equals("VC")) {
                    c = 214;
                    break;
                }
                c = 65535;
                break;
            case 2735:
                if (country.equals("VE")) {
                    c = 231;
                    break;
                }
                c = 65535;
                break;
            case 2737:
                if (country.equals("VG")) {
                    c = Typography.times;
                    break;
                }
                c = 65535;
                break;
            case 2739:
                if (country.equals("VI")) {
                    c = 216;
                    break;
                }
                c = 65535;
                break;
            case 2744:
                if (country.equals("VN")) {
                    c = 217;
                    break;
                }
                c = 65535;
                break;
            case 2751:
                if (country.equals("VU")) {
                    c = 219;
                    break;
                }
                c = 65535;
                break;
            case 2767:
                if (country.equals("WF")) {
                    c = 223;
                    break;
                }
                c = 65535;
                break;
            case 2780:
                if (country.equals("WS")) {
                    c = 225;
                    break;
                }
                c = 65535;
                break;
            case 2803:
                if (country.equals("XK")) {
                    c = 226;
                    break;
                }
                c = 65535;
                break;
            case 2828:
                if (country.equals("YE")) {
                    c = 232;
                    break;
                }
                c = 65535;
                break;
            case 2843:
                if (country.equals("YT")) {
                    c = 234;
                    break;
                }
                c = 65535;
                break;
            case 2855:
                if (country.equals("ZA")) {
                    c = 235;
                    break;
                }
                c = 65535;
                break;
            case 2867:
                if (country.equals("ZM")) {
                    c = 236;
                    break;
                }
                c = 65535;
                break;
            case 2877:
                if (country.equals("ZW")) {
                    c = 238;
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
                return new int[]{1, 4, 2, 3, 4, 1};
            case 1:
                return new int[]{1, 1, 1, 2, 2, 2};
            case 2:
                return new int[]{3, 4, 4, 3, 2, 2};
            case 3:
                return new int[]{2, 2, 2, 2, 1, 2};
            case 4:
                return new int[]{2, 2, 3, 3, 2, 2};
            case 5:
                return new int[]{0, 3, 1, 1, 3, 0};
            case 6:
                return new int[]{2, 2, 3, 4, 2, 2};
            case 7:
                return new int[]{2, 1, 3, 2, 4, 2};
            case '\b':
                return new int[]{0, 0, 1, 0, 1, 2};
            case '\t':
                return new int[]{1, 3, 1, 3, 4, 2};
            case '\n':
                return new int[]{4, 4, 2, 3, 2, 2};
            case 11:
                return new int[]{0, 2, 0, 0, 2, 2};
            case '\f':
                return new int[]{3, 2, 0, 0, 2, 2};
            case '\r':
                return new int[]{1, 2, 4, 4, 2, 2};
            case 14:
                return new int[]{1, 1, 1, 1, 2, 4};
            case 15:
                return new int[]{3, 2, 1, 1, 2, 2};
            case 16:
                return new int[]{3, 1, 2, 2, 3, 2};
            case 17:
                return new int[]{3, 2, 1, 0, 2, 2};
            case 18:
                return new int[]{1, 2, 3, 3, 2, 2};
            case 19:
                return new int[]{4, 2, 4, 2, 2, 2};
            case 20:
                return new int[]{0, 1, 0, 0, 0, 2};
            case 21:
            case 22:
                return new int[]{2, 4, 3, 4, 2, 2};
            case 23:
            case 24:
                return new int[]{2, 2, 2, 1, 2, 2};
            case 25:
                return new int[]{2, 0, 1, 1, 3, 1};
            case 26:
                return new int[]{2, 3, 3, 2, 2, 2};
            case 27:
                return new int[]{2, 3, 0, 1, 2, 2};
            case 28:
                return new int[]{1, 0, 1, 0, 0, 2};
            case 29:
                return new int[]{0, 0, 2, 0, 1, 2};
            case 30:
                return new int[]{0, 1, 4, 2, 2, 1};
            case 31:
                return new int[]{0, 0, 2, 0, 0, 2};
            case ' ':
                return new int[]{1, 3, 2, 1, 2, 2};
            case '!':
                return new int[]{0, 0, 0, 0, 1, 0};
            case '\"':
                return new int[]{4, 3, 4, 4, 4, 2};
            case '#':
                return new int[]{0, 0, 0, 1, 0, 2};
            case '$':
                return new int[]{3, 2, 2, 3, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_6 /* 37 */:
                return new int[]{4, 2, 4, 0, 2, 2};
            case '&':
                return new int[]{0, 2, 2, 0, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                return new int[]{1, 1, 1, 1, 0, 2};
            case '(':
                return new int[]{3, 4, 0, 0, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                return new int[]{1, 1, 3, 2, 2, 2};
            case '*':
                return new int[]{2, 2, 0, 0, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_12 /* 43 */:
                return new int[]{1, 1, 0, 2, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                return new int[]{3, 2, 3, 3, 2, 2};
            case '-':
                return new int[]{0, 2, 1, 1, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_15 /* 46 */:
                return new int[]{3, 3, 3, 2, 2, 2};
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                return new int[]{3, 4, 4, 2, 2, 2};
            case BufferCeiling.MIN_CEILING_MB /* 48 */:
                return new int[]{2, 1, 1, 3, 2, 2};
            case '1':
                return new int[]{1, 0, 0, 0, 1, 2};
            case '2':
                return new int[]{2, 1, 2, 1, 2, 2};
            case '3':
                return new int[]{2, 2, 4, 3, 3, 2};
            case '4':
                return new int[]{4, 4, 1, 2, 2, 2};
            case '5':
                return new int[]{3, 1, 1, 3, 2, 2};
            case '6':
                return new int[]{0, 1, 0, 1, 1, 0};
            case '7':
                return new int[]{3, 1, 3, 3, 2, 4};
            case '8':
                return new int[]{1, 1, 1, 1, 1, 2};
            case '9':
                return new int[]{1, 2, 2, 3, 4, 2};
            case ':':
                return new int[]{1, 1, 3, 2, 2, 3};
            case ';':
                return new int[]{3, 2, 2, 0, 2, 2};
            case '<':
                return new int[]{3, 2, 3, 2, 2, 2};
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                return new int[]{4, 2, 3, 3, 4, 3};
            case '>':
                return new int[]{0, 1, 1, 2, 1, 2};
            case '?':
            case '@':
            case 'A':
                return new int[]{0, 2, 0, 1, 2, 2};
            case 'B':
                return new int[]{2, 4, 3, 1, 2, 2};
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                return new int[]{0, 3, 2, 3, 4, 2};
            case 'D':
                return new int[]{3, 2, 1, 1, 1, 2};
            case 'E':
                return new int[]{2, 1, 1, 2, 2, 2};
            case 'F':
                return new int[]{1, 0, 4, 2, 2, 2};
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                return new int[]{0, 2, 2, 4, 4, 4};
            case 'H':
            case 'I':
                return new int[]{1, 0, 0, 0, 0, 2};
            case 'J':
                return new int[]{2, 1, 2, 2, 3, 2};
            case 'K':
                return new int[]{1, 2, 1, 3, 2, 2};
            case Base64.mimeLineLength /* 76 */:
                return new int[]{3, 1, 1, 2, 2, 2};
            case 'M':
                return new int[]{2, 2, 1, 1, 2, 2};
            case 'N':
            case 'O':
                return new int[]{3, 4, 4, 4, 2, 2};
            case 'P':
                return new int[]{0, 1, 0, 1, 0, 2};
            case 'Q':
                return new int[]{4, 0, 3, 2, 1, 3};
            case 'R':
                return new int[]{3, 3, 1, 1, 2, 2};
            case 'S':
            case 'T':
                return new int[]{1, 2, 2, 0, 2, 2};
            case 'U':
                return new int[]{1, 0, 0, 0, 2, 2};
            case 'V':
                return new int[]{2, 0, 0, 1, 3, 2};
            case 'W':
                return new int[]{1, 2, 2, 3, 2, 2};
            case 'X':
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 'Z':
                return new int[]{3, 4, 3, 3, 2, 2};
            case '[':
                return new int[]{1, 0, 0, 1, 3, 2};
            case '\\':
            case ']':
                return new int[]{3, 3, 2, 2, 2, 2};
            case '^':
            case '_':
                return new int[]{3, 2, 3, 3, 4, 2};
            case BufferCeiling.UI_RESERVE_MB /* 96 */:
                return new int[]{2, 0, 2, 2, 2, 2};
            case 'a':
                return new int[]{0, 2, 4, 4, 3, 1};
            case 'b':
                return new int[]{2, 1, 2, 3, 2, 2};
            case 'c':
            case 'd':
                return new int[]{4, 3, 3, 4, 2, 2};
            case MlKitException.NOT_ENOUGH_SPACE /* 101 */:
                return new int[]{3, 1, 0, 2, 2, 2};
            case 'f':
                return new int[]{3, 2, 1, 3, 4, 2};
            case 'g':
                return new int[]{3, 2, 2, 1, 2, 2};
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                return new int[]{2, 4, 4, 4, 3, 2};
            case 'i':
                return new int[]{1, 0, 4, 1, 1, 0};
            case 'j':
                return new int[]{3, 4, 3, 2, 2, 2};
            case 'k':
                return new int[]{3, 4, 2, 1, 2, 2};
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                return new int[]{2, 4, 4, 4, 2, 2};
            case 'n':
                return new int[]{2, 1, 4, 3, 0, 4};
            case 'o':
                return new int[]{0, 0, 3, 0, 0, 2};
            case 'p':
                return new int[]{2, 2, 4, 3, 2, 2};
            case 'q':
                return new int[]{0, 0, 1, 2, 4, 2};
            case 'r':
                return new int[]{2, 3, 1, 2, 4, 2};
            case 's':
            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                return new int[]{2, 3, 2, 3, 2, 2};
            case 'u':
                return new int[]{1, 2, 4, 4, 3, 2};
            case 'v':
                return new int[]{2, 2, 3, 1, 2, 2};
            case 'w':
            case 'x':
                return new int[]{4, 3, 3, 3, 2, 2};
            case 'y':
                return new int[]{2, 1, 2, 3, 2, 1};
            case 'z':
                return new int[]{3, 3, 3, 3, 2, 2};
            case '{':
                return new int[]{1, 0, 2, 2, 4, 4};
            case '|':
                return new int[]{2, 0, 2, 1, 2, 0};
            case '}':
                return new int[]{3, 4, 1, 3, 2, 2};
            case WebSocketProtocol.PAYLOAD_SHORT /* 126 */:
                return new int[]{2, 2, 4, 1, 2, 2};
            case 127:
            case 128:
            case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                return new int[]{1, 2, 2, 2, 2, 2};
            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                return new int[]{1, 4, 4, 4, 4, 2};
            case 131:
                return new int[]{0, 3, 2, 3, 1, 2};
            case 132:
                return new int[]{0, 0, 1, 1, 3, 2};
            case 133:
                return new int[]{1, 0, 0, 1, 2, 2};
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
                return new int[]{1, 0, 0, 1, 3, 3};
            case TsExtractor.TS_STREAM_TYPE_E_AC3 /* 135 */:
                return new int[]{3, 3, 2, 0, 2, 2};
            case TsExtractor.TS_STREAM_TYPE_DTS_HD /* 136 */:
                return new int[]{3, 1, 1, 2, 2, 0};
            case 137:
            case TsExtractor.TS_STREAM_TYPE_DTS /* 138 */:
            case TsExtractor.TS_STREAM_TYPE_DTS_UHD /* 139 */:
            case 140:
            case 141:
            case 142:
            case 143:
                return new int[]{0, 0, 0, 0, 0, 2};
            case 144:
                return new int[]{2, 3, 3, 3, 1, 1};
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
                return new int[]{4, 2, 2, 2, 2, 2};
            case 150:
            case 151:
            case 152:
                return new int[]{0, 0, 0, 0, 1, 2};
            case 153:
            case 154:
            case 155:
                return new int[]{3, 2, 2, 2, 2, 2};
            case 156:
                return new int[]{0, 1, 1, 1, 2, 2};
            case 157:
            case 158:
            case 159:
            case 160:
                return new int[]{4, 2, 3, 3, 2, 2};
            case 161:
                return new int[]{4, 4, 3, 2, 2, 2};
            case 162:
                return new int[]{2, 2, 3, 4, 4, 2};
            case 163:
                return new int[]{2, 4, 4, 1, 2, 2};
            case 164:
            case 165:
                return new int[]{4, 3, 2, 4, 2, 2};
            case 166:
                return new int[]{2, 2, 1, 2, 2, 2};
            case 167:
                return new int[]{2, 3, 2, 1, 2, 2};
            case 168:
            case 169:
                return new int[]{4, 4, 3, 4, 2, 2};
            case 170:
                return new int[]{3, 2, 1, 2, 2, 2};
            case 171:
            case TsExtractor.TS_STREAM_TYPE_AC4 /* 172 */:
            case 173:
            case 174:
                return new int[]{4, 3, 4, 4, 2, 2};
            case 175:
                return new int[]{3, 4, 1, 0, 2, 2};
            case 176:
            case 177:
                return new int[]{0, 1, 2, 2, 2, 2};
            case 178:
            case 179:
                return new int[]{3, 3, 4, 4, 2, 2};
            case 180:
            case 181:
            case 182:
            case 183:
                return new int[]{4, 2, 4, 4, 2, 2};
            case 184:
                return new int[]{3, 1, 1, 1, 2, 2};
            case 185:
                return new int[]{3, 2, 4, 3, 2, 2};
            case 186:
            case 187:
            case TsExtractor.TS_PACKET_SIZE /* 188 */:
                return new int[]{1, 1, 1, 1, 2, 2};
            case PsExtractor.PRIVATE_STREAM_1 /* 189 */:
                return new int[]{2, 4, 1, 0, 2, 2};
            case 190:
                return new int[]{0, 0, 0, 0, 0, 0};
            case 191:
                return new int[]{3, 4, 2, 1, 3, 2};
            case PsExtractor.AUDIO_STREAM /* 192 */:
            case 193:
                return new int[]{0, 2, 1, 2, 3, 3};
            case 194:
                return new int[]{3, 3, 2, 3, 4, 2};
            case 195:
                return new int[]{2, 2, 4, 1, 3, 1};
            case 196:
                return new int[]{2, 1, 1, 2, 1, 2};
            case 197:
                return new int[]{1, 2, 3, 4, 3, 2};
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
                return new int[]{0, 2, 2, 2, 2, 2};
            case MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR /* 205 */:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
                return new int[]{1, 2, 0, 0, 2, 2};
            case 215:
                return new int[]{2, 2, 1, 1, 2, 4};
            case 216:
                return new int[]{0, 2, 1, 2, 2, 2};
            case 217:
                return new int[]{0, 0, 1, 2, 2, 2};
            case 218:
            case 219:
                return new int[]{4, 3, 3, 2, 2, 2};
            case 220:
            case 221:
            case 222:
            case 223:
                return new int[]{4, 2, 2, 4, 2, 2};
            case 224:
            case 225:
                return new int[]{3, 1, 2, 2, 2, 2};
            case 226:
                return new int[]{1, 2, 1, 1, 2, 2};
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
                return new int[]{4, 4, 4, 4, 2, 2};
            case 233:
            case 234:
                return new int[]{2, 3, 3, 4, 2, 2};
            case 235:
                return new int[]{2, 4, 2, 1, 1, 2};
            case 236:
                return new int[]{4, 4, 4, 3, 2, 2};
            case 237:
            case 238:
                return new int[]{4, 2, 4, 3, 2, 2};
            default:
                return new int[]{2, 2, 2, 2, 2, 2};
        }
    }
}
