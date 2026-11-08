package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* loaded from: classes7.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0061, code lost:
    
        if (r0.getPosition() != r18) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0087, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0084, code lost:
    
        if (r0.getPosition() != r18) goto L40;
     */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(androidx.media3.common.DataReader r15, android.net.Uri r16, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r17, long r18, long r20, androidx.media3.extractor.ExtractorOutput r22) throws java.io.IOException {
        /*
            r14 = this;
            r1 = r14
            androidx.media3.extractor.DefaultExtractorInput r0 = new androidx.media3.extractor.DefaultExtractorInput
            r2 = r0
            r3 = r15
            r4 = r18
            r6 = r20
            r2.<init>(r3, r4, r6)
            r1.extractorInput = r2
            androidx.media3.extractor.Extractor r0 = r1.extractor
            if (r0 == 0) goto L13
            return
        L13:
            androidx.media3.extractor.ExtractorsFactory r0 = r1.extractorsFactory
            r3 = r16
            r4 = r17
            androidx.media3.extractor.Extractor[] r5 = r0.createExtractors(r3, r4)
            int r0 = r5.length
            com.google.common.collect.ImmutableList$Builder r6 = com.google.common.collect.ImmutableList.builderWithExpectedSize(r0)
            int r0 = r5.length
            r7 = 0
            r8 = 1
            if (r0 != r8) goto L2d
            r0 = r5[r7]
            r1.extractor = r0
            goto L98
        L2d:
            int r9 = r5.length
            r10 = r7
        L2f:
            if (r10 >= r9) goto L94
            r11 = r5[r10]
            boolean r0 = r11.sniff(r2)     // Catch: java.lang.Throwable -> L64 java.io.EOFException -> L79
            if (r0 == 0) goto L4f
            r1.extractor = r11     // Catch: java.lang.Throwable -> L64 java.io.EOFException -> L79
            androidx.media3.extractor.Extractor r0 = r1.extractor
            if (r0 != 0) goto L47
            long r9 = r2.getPosition()
            int r0 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r0 != 0) goto L48
        L47:
            r7 = r8
        L48:
            androidx.media3.common.util.Assertions.checkState(r7)
            r2.resetPeekPosition()
            goto L94
        L4f:
            java.util.List r0 = r11.getSniffFailureDetails()     // Catch: java.lang.Throwable -> L64 java.io.EOFException -> L79
            r6.addAll(r0)     // Catch: java.lang.Throwable -> L64 java.io.EOFException -> L79
            androidx.media3.extractor.Extractor r0 = r1.extractor
            if (r0 != 0) goto L63
            long r12 = r2.getPosition()
            int r0 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r0 != 0) goto L87
        L63:
            goto L89
        L64:
            r0 = move-exception
            androidx.media3.extractor.Extractor r9 = r1.extractor
            if (r9 != 0) goto L71
            long r9 = r2.getPosition()
            int r9 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r9 != 0) goto L72
        L71:
            r7 = r8
        L72:
            androidx.media3.common.util.Assertions.checkState(r7)
            r2.resetPeekPosition()
            throw r0
        L79:
            r0 = move-exception
            androidx.media3.extractor.Extractor r0 = r1.extractor
            if (r0 != 0) goto L89
            long r12 = r2.getPosition()
            int r0 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r0 != 0) goto L87
            goto L89
        L87:
            r0 = r7
            goto L8a
        L89:
            r0 = r8
        L8a:
            androidx.media3.common.util.Assertions.checkState(r0)
            r2.resetPeekPosition()
            int r10 = r10 + 1
            goto L2f
        L94:
            androidx.media3.extractor.Extractor r0 = r1.extractor
            if (r0 == 0) goto La0
        L98:
            androidx.media3.extractor.Extractor r0 = r1.extractor
            r7 = r22
            r0.init(r7)
            return
        La0:
            r7 = r22
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r0 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "None of the available extractors ("
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = ", "
            com.google.common.base.Joiner r9 = com.google.common.base.Joiner.on(r9)
            com.google.common.collect.ImmutableList r10 = com.google.common.collect.ImmutableList.copyOf(r5)
            androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0 r11 = new androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0
            r11.<init>()
            java.util.List r10 = com.google.common.collect.Lists.transform(r10, r11)
            java.lang.String r9 = r9.join(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = ") could read the stream."
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r16)
            android.net.Uri r9 = (android.net.Uri) r9
            com.google.common.collect.ImmutableList r10 = r6.build()
            r0.<init>(r8, r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        if (this.extractor != null) {
            this.extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        if (this.extractor == null) {
            return;
        }
        Extractor underlyingExtractor = this.extractor.getUnderlyingImplementation();
        if (underlyingExtractor instanceof Mp3Extractor) {
            ((Mp3Extractor) underlyingExtractor).disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        if (this.extractorInput != null) {
            return this.extractorInput.getPosition();
        }
        return -1L;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long position, long seekTimeUs) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(position, seekTimeUs);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }
}
