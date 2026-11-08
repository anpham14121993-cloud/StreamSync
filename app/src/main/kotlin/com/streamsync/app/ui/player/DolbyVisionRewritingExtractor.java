package com.streamsync.app.ui.player;

import android.util.Log;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DolbyVisionRewritingExtractor.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096\u0001J\t\u0010\u0011\u001a\u00020\bH\u0096\u0001J\u0019\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0013H\u0096\u0001J\u0011\u0010\u0014\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionRewritingExtractor;", "Landroidx/media3/extractor/Extractor;", "delegate", "enabled", "", "injectHdrColorInfo", "(Landroidx/media3/extractor/Extractor;ZZ)V", "init", "", "output", "Landroidx/media3/extractor/ExtractorOutput;", "read", "", "p0", "Landroidx/media3/extractor/ExtractorInput;", "p1", "Landroidx/media3/extractor/PositionHolder;", "release", "seek", "", "sniff", "RewritingExtractorOutput", "RewritingTrackOutput", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class DolbyVisionRewritingExtractor implements Extractor {
    public static final int $stable = 8;
    private final Extractor delegate;
    private final boolean enabled;
    private final boolean injectHdrColorInfo;

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput p0, PositionHolder p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        return this.delegate.read(p0, p1);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        this.delegate.release();
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long p0, long p1) {
        this.delegate.seek(p0, p1);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return this.delegate.sniff(p0);
    }

    public DolbyVisionRewritingExtractor(Extractor delegate, boolean enabled, boolean injectHdrColorInfo) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.enabled = enabled;
        this.injectHdrColorInfo = injectHdrColorInfo;
    }

    public /* synthetic */ DolbyVisionRewritingExtractor(Extractor extractor, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(extractor, z, (i & 4) != 0 ? false : z2);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        if (this.enabled) {
            this.delegate.init(new RewritingExtractorOutput(output, this.injectHdrColorInfo));
        } else {
            this.delegate.init(output);
        }
    }

    /* compiled from: DolbyVisionRewritingExtractor.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0007H\u0096\u0001J\u0011\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0096\u0001J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionRewritingExtractor$RewritingExtractorOutput;", "Landroidx/media3/extractor/ExtractorOutput;", "delegate", "injectHdrColorInfo", "", "(Landroidx/media3/extractor/ExtractorOutput;Z)V", "endTracks", "", "seekMap", "p0", "Landroidx/media3/extractor/SeekMap;", "track", "Landroidx/media3/extractor/TrackOutput;", "id", "", "type", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class RewritingExtractorOutput implements ExtractorOutput {
        private final ExtractorOutput delegate;
        private final boolean injectHdrColorInfo;

        @Override // androidx.media3.extractor.ExtractorOutput
        public void endTracks() {
            this.delegate.endTracks();
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public void seekMap(SeekMap p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            this.delegate.seekMap(p0);
        }

        public RewritingExtractorOutput(ExtractorOutput delegate, boolean injectHdrColorInfo) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
            this.injectHdrColorInfo = injectHdrColorInfo;
        }

        @Override // androidx.media3.extractor.ExtractorOutput
        public TrackOutput track(int id, int type) {
            TrackOutput trackOutput = this.delegate.track(id, type);
            Intrinsics.checkNotNullExpressionValue(trackOutput, "track(...)");
            if (type == 2) {
                return new RewritingTrackOutput(trackOutput, this.injectHdrColorInfo);
            }
            return trackOutput;
        }
    }

    /* compiled from: DolbyVisionRewritingExtractor.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\bH\u0002J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0096\u0001J!\u0010\n\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0096\u0001J5\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionRewritingExtractor$RewritingTrackOutput;", "Landroidx/media3/extractor/TrackOutput;", "delegate", "injectHdrColorInfo", "", "(Landroidx/media3/extractor/TrackOutput;Z)V", "format", "", "Landroidx/media3/common/Format;", "rewriteIfDolbyVision", "sampleData", "", "p0", "Landroidx/media3/common/DataReader;", "p1", "p2", "p3", "Landroidx/media3/common/util/ParsableByteArray;", "sampleMetadata", "", "p4", "Landroidx/media3/extractor/TrackOutput$CryptoData;", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes23.dex */
    private static final class RewritingTrackOutput implements TrackOutput {
        private static final Companion Companion = new Companion(null);

        @Deprecated
        public static final String TAG = "DolbyVision";
        private final TrackOutput delegate;
        private final boolean injectHdrColorInfo;

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader p0, int p1, boolean p2, int p3) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return this.delegate.sampleData(p0, p1, p2, p3);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray p0, int p1, int p2) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            this.delegate.sampleData(p0, p1, p2);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long p0, int p1, int p2, int p3, TrackOutput.CryptoData p4) {
            this.delegate.sampleMetadata(p0, p1, p2, p3, p4);
        }

        public RewritingTrackOutput(TrackOutput delegate, boolean injectHdrColorInfo) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
            this.injectHdrColorInfo = injectHdrColorInfo;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            this.delegate.format(rewriteIfDolbyVision(format));
        }

        private final Format rewriteIfDolbyVision(Format format) {
            if (!DolbyVisionFallback.INSTANCE.needsRewrite(format)) {
                return format;
            }
            String fallbackMime = DolbyVisionFallback.INSTANCE.baseLayerMimeFor(format.codecs);
            if (fallbackMime == null) {
                fallbackMime = MimeTypes.VIDEO_H265;
            }
            Format out = DolbyVisionFallback.INSTANCE.rewrite(format, fallbackMime, this.injectHdrColorInfo);
            String describe = DolbyVisionFallback.INSTANCE.describe(format);
            boolean z = out.initializationData != null;
            ColorInfo colorInfo = out.colorInfo;
            Log.i(TAG, "Bo lop Dolby Vision: " + describe + " -> " + fallbackMime + ", giu initializationData=" + z + ", chen colorInfo HDR=" + (colorInfo != null ? Integer.valueOf(colorInfo.colorTransfer) : null));
            return out;
        }

        /* compiled from: DolbyVisionRewritingExtractor.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionRewritingExtractor$RewritingTrackOutput$Companion;", "", "()V", "TAG", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
        /* loaded from: classes23.dex */
        private static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
