package androidx.media3.exoplayer.upstream.experimental;

/* loaded from: classes11.dex */
public interface BandwidthStatistic {
    void addSample(long j, long j2);

    long getBandwidthEstimate();

    void reset();
}
