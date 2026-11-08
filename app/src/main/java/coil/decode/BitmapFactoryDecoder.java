package coil.decode;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import coil.ImageLoader;
import coil.decode.Decoder;
import coil.decode.ImageSource;
import coil.fetch.SourceResult;
import coil.request.Options;
import coil.size.Scale;
import coil.size.Size;
import coil.size.Sizes;
import coil.util.Bitmaps;
import coil.util.Utils;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* compiled from: BitmapFactoryDecoder.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0003\u0016\u0017\u0018B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0015\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\f\u0010\r\u001a\u00020\u000e*\u00020\u0012H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder;", "Lcoil/decode/Decoder;", "source", "Lcoil/decode/ImageSource;", "options", "Lcoil/request/Options;", "(Lcoil/decode/ImageSource;Lcoil/request/Options;)V", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Lkotlinx/coroutines/sync/Semaphore;)V", "exifOrientationPolicy", "Lcoil/decode/ExifOrientationPolicy;", "(Lcoil/decode/ImageSource;Lcoil/request/Options;Lkotlinx/coroutines/sync/Semaphore;Lcoil/decode/ExifOrientationPolicy;)V", "decode", "Lcoil/decode/DecodeResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configureConfig", "", "Landroid/graphics/BitmapFactory$Options;", "exifData", "Lcoil/decode/ExifData;", "configureScale", "Companion", "ExceptionCatchingSource", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class BitmapFactoryDecoder implements Decoder {
    public static final int DEFAULT_MAX_PARALLELISM = 4;
    private final ExifOrientationPolicy exifOrientationPolicy;
    private final Options options;
    private final Semaphore parallelismLock;
    private final ImageSource source;

    public BitmapFactoryDecoder(ImageSource source, Options options, Semaphore parallelismLock, ExifOrientationPolicy exifOrientationPolicy) {
        this.source = source;
        this.options = options;
        this.parallelismLock = parallelismLock;
        this.exifOrientationPolicy = exifOrientationPolicy;
    }

    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, Semaphore semaphore, ExifOrientationPolicy exifOrientationPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageSource, options, (i & 4) != 0 ? SemaphoreKt.Semaphore$default(Integer.MAX_VALUE, 0, 2, null) : semaphore, (i & 8) != 0 ? ExifOrientationPolicy.RESPECT_PERFORMANCE : exifOrientationPolicy);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public /* synthetic */ BitmapFactoryDecoder(ImageSource source, Options options) {
        this(source, options, null, null, 12, null);
    }

    public /* synthetic */ BitmapFactoryDecoder(ImageSource imageSource, Options options, Semaphore semaphore, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageSource, options, (i & 4) != 0 ? SemaphoreKt.Semaphore$default(Integer.MAX_VALUE, 0, 2, null) : semaphore);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
    public /* synthetic */ BitmapFactoryDecoder(ImageSource source, Options options, Semaphore parallelismLock) {
        this(source, options, parallelismLock, null, 8, null);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // coil.decode.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object decode(kotlin.coroutines.Continuation<? super coil.decode.DecodeResult> r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof coil.decode.BitmapFactoryDecoder$decode$1
            if (r0 == 0) goto L14
            r0 = r12
            coil.decode.BitmapFactoryDecoder$decode$1 r0 = (coil.decode.BitmapFactoryDecoder$decode$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            coil.decode.BitmapFactoryDecoder$decode$1 r0 = new coil.decode.BitmapFactoryDecoder$decode$1
            r0.<init>(r11, r12)
        L19:
            r12 = r0
            java.lang.Object r0 = r12.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r12.label
            r3 = 1
            switch(r2) {
                case 0: goto L49;
                case 1: goto L3c;
                case 2: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L2e:
            r1 = 0
            r2 = 0
            java.lang.Object r3 = r12.L$0
            kotlinx.coroutines.sync.Semaphore r3 = (kotlinx.coroutines.sync.Semaphore) r3
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> L3a
            r4 = r3
            r3 = r0
            goto L7b
        L3a:
            r2 = move-exception
            goto L8b
        L3c:
            r2 = 0
            java.lang.Object r4 = r12.L$1
            kotlinx.coroutines.sync.Semaphore r4 = (kotlinx.coroutines.sync.Semaphore) r4
            java.lang.Object r5 = r12.L$0
            coil.decode.BitmapFactoryDecoder r5 = (coil.decode.BitmapFactoryDecoder) r5
            kotlin.ResultKt.throwOnFailure(r0)
            goto L61
        L49:
            kotlin.ResultKt.throwOnFailure(r0)
            r5 = r11
            kotlinx.coroutines.sync.Semaphore r2 = r5.parallelismLock
            r4 = 0
            r12.L$0 = r5
            r12.L$1 = r2
            r12.label = r3
            java.lang.Object r6 = r2.acquire(r12)
            if (r6 != r1) goto L5e
            return r1
        L5e:
            r10 = r4
            r4 = r2
            r2 = r10
        L61:
            r6 = 0
            coil.decode.BitmapFactoryDecoder$decode$2$1 r7 = new coil.decode.BitmapFactoryDecoder$decode$2$1     // Catch: java.lang.Throwable -> L86
            r7.<init>()     // Catch: java.lang.Throwable -> L86
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7     // Catch: java.lang.Throwable -> L86
            r12.L$0 = r4     // Catch: java.lang.Throwable -> L86
            r8 = 0
            r12.L$1 = r8     // Catch: java.lang.Throwable -> L86
            r9 = 2
            r12.label = r9     // Catch: java.lang.Throwable -> L86
            java.lang.Object r3 = kotlinx.coroutines.InterruptibleKt.runInterruptible$default(r8, r7, r12, r3, r8)     // Catch: java.lang.Throwable -> L86
            if (r3 != r1) goto L79
            return r1
        L79:
            r1 = r2
            r2 = r6
        L7b:
            coil.decode.DecodeResult r3 = (coil.decode.DecodeResult) r3     // Catch: java.lang.Throwable -> L83
            r4.release()
            return r3
        L83:
            r2 = move-exception
            r3 = r4
            goto L8b
        L86:
            r1 = move-exception
            r3 = r4
            r10 = r2
            r2 = r1
            r1 = r10
        L8b:
            r3.release()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.decode.BitmapFactoryDecoder.decode(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DecodeResult decode(BitmapFactory.Options $this$decode) {
        ExceptionCatchingSource safeSource = new ExceptionCatchingSource(this.source.source());
        BufferedSource safeBufferedSource = Okio.buffer(safeSource);
        boolean z = true;
        $this$decode.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(safeBufferedSource.peek().inputStream(), null, $this$decode);
        Exception exception = safeSource.getException();
        if (exception != null) {
            throw exception;
        }
        $this$decode.inJustDecodeBounds = false;
        ExifData exifData = ExifUtils.INSTANCE.getExifData($this$decode.outMimeType, safeBufferedSource, this.exifOrientationPolicy);
        Exception it = safeSource.getException();
        if (it != null) {
            throw it;
        }
        $this$decode.inMutable = false;
        if (Build.VERSION.SDK_INT >= 26 && this.options.getColorSpace() != null) {
            $this$decode.inPreferredColorSpace = this.options.getColorSpace();
        }
        $this$decode.inPremultiplied = this.options.getPremultipliedAlpha();
        configureConfig($this$decode, exifData);
        configureScale($this$decode, exifData);
        BufferedSource it2 = safeBufferedSource;
        try {
            Bitmap outBitmap = BitmapFactory.decodeStream(it2.inputStream(), null, $this$decode);
            CloseableKt.closeFinally(it2, null);
            Exception it3 = safeSource.getException();
            if (it3 != null) {
                throw it3;
            }
            if (outBitmap == null) {
                throw new IllegalStateException("BitmapFactory returned a null bitmap. Often this means BitmapFactory could not decode the image data read from the input source (e.g. network, disk, or memory) as it's not encoded as a valid image format.".toString());
            }
            outBitmap.setDensity(this.options.getContext().getResources().getDisplayMetrics().densityDpi);
            Bitmap bitmap = ExifUtils.INSTANCE.reverseTransformations(outBitmap, exifData);
            Context context$iv = this.options.getContext();
            Resources resources$iv$iv = context$iv.getResources();
            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources$iv$iv, bitmap);
            if ($this$decode.inSampleSize <= 1 && !$this$decode.inScaled) {
                z = false;
            }
            return new DecodeResult(bitmapDrawable, z);
        } finally {
        }
    }

    private final void configureConfig(BitmapFactory.Options $this$configureConfig, ExifData exifData) {
        Bitmap.Config config = this.options.getConfig();
        if (exifData.getIsFlipped() || ExifUtilsKt.isRotated(exifData)) {
            config = Bitmaps.toSoftware(config);
        }
        if (this.options.getAllowRgb565() && config == Bitmap.Config.ARGB_8888 && Intrinsics.areEqual($this$configureConfig.outMimeType, "image/jpeg")) {
            config = Bitmap.Config.RGB_565;
        }
        if (Build.VERSION.SDK_INT >= 26 && $this$configureConfig.outConfig == Bitmap.Config.RGBA_F16 && config != Bitmap.Config.HARDWARE) {
            config = Bitmap.Config.RGBA_F16;
        }
        $this$configureConfig.inPreferredConfig = config;
    }

    private final void configureScale(BitmapFactory.Options $this$configureScale, ExifData exifData) {
        ImageSource.Metadata metadata = this.source.getMetadata();
        if ((metadata instanceof ResourceMetadata) && Sizes.isOriginal(this.options.getSize())) {
            $this$configureScale.inSampleSize = 1;
            $this$configureScale.inScaled = true;
            $this$configureScale.inDensity = ((ResourceMetadata) metadata).getDensity();
            $this$configureScale.inTargetDensity = this.options.getContext().getResources().getDisplayMetrics().densityDpi;
            return;
        }
        if ($this$configureScale.outWidth <= 0 || $this$configureScale.outHeight <= 0) {
            $this$configureScale.inSampleSize = 1;
            $this$configureScale.inScaled = false;
            return;
        }
        int srcWidth = ExifUtilsKt.isSwapped(exifData) ? $this$configureScale.outHeight : $this$configureScale.outWidth;
        int srcHeight = ExifUtilsKt.isSwapped(exifData) ? $this$configureScale.outWidth : $this$configureScale.outHeight;
        Size $this$widthPx$iv = this.options.getSize();
        Scale scale$iv = this.options.getScale();
        int dstWidth = Sizes.isOriginal($this$widthPx$iv) ? srcWidth : Utils.toPx($this$widthPx$iv.getWidth(), scale$iv);
        Size $this$heightPx$iv = this.options.getSize();
        Scale scale$iv2 = this.options.getScale();
        int dstHeight = Sizes.isOriginal($this$heightPx$iv) ? srcHeight : Utils.toPx($this$heightPx$iv.getHeight(), scale$iv2);
        $this$configureScale.inSampleSize = DecodeUtils.calculateInSampleSize(srcWidth, srcHeight, dstWidth, dstHeight, this.options.getScale());
        double scale = DecodeUtils.computeSizeMultiplier(srcWidth / $this$configureScale.inSampleSize, srcHeight / $this$configureScale.inSampleSize, dstWidth, dstHeight, this.options.getScale());
        if (this.options.getAllowInexactSize()) {
            scale = RangesKt.coerceAtMost(scale, 1.0d);
        }
        $this$configureScale.inScaled = true ^ (scale == 1.0d);
        if ($this$configureScale.inScaled) {
            if (scale > 1.0d) {
                $this$configureScale.inDensity = MathKt.roundToInt(Integer.MAX_VALUE / scale);
                $this$configureScale.inTargetDensity = Integer.MAX_VALUE;
            } else {
                $this$configureScale.inDensity = Integer.MAX_VALUE;
                $this$configureScale.inTargetDensity = MathKt.roundToInt(Integer.MAX_VALUE * scale);
            }
        }
    }

    /* compiled from: BitmapFactoryDecoder.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0017¢\u0006\u0002\u0010\u0002B\u0011\b\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0004H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$Factory;", "Lcoil/decode/Decoder$Factory;", "()V", "maxParallelism", "", "(I)V", "exifOrientationPolicy", "Lcoil/decode/ExifOrientationPolicy;", "(ILcoil/decode/ExifOrientationPolicy;)V", "parallelismLock", "Lkotlinx/coroutines/sync/Semaphore;", "create", "Lcoil/decode/Decoder;", "result", "Lcoil/fetch/SourceResult;", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "equals", "", "other", "", "hashCode", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Factory implements Decoder.Factory {
        private final ExifOrientationPolicy exifOrientationPolicy;
        private final Semaphore parallelismLock;

        public Factory(int maxParallelism, ExifOrientationPolicy exifOrientationPolicy) {
            this.exifOrientationPolicy = exifOrientationPolicy;
            this.parallelismLock = SemaphoreKt.Semaphore$default(maxParallelism, 0, 2, null);
        }

        public /* synthetic */ Factory(int i, ExifOrientationPolicy exifOrientationPolicy, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 4 : i, (i2 & 2) != 0 ? ExifOrientationPolicy.RESPECT_PERFORMANCE : exifOrientationPolicy);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Factory() {
            this(0, null, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Kept for binary compatibility.")
        public /* synthetic */ Factory(int i) {
            this(i, null, 2, 0 == true ? 1 : 0);
        }

        public /* synthetic */ Factory(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 4 : i);
        }

        @Override // coil.decode.Decoder.Factory
        public Decoder create(SourceResult result, Options options, ImageLoader imageLoader) {
            return new BitmapFactoryDecoder(result.getSource(), options, this.parallelismLock, this.exifOrientationPolicy);
        }

        public boolean equals(Object other) {
            return other instanceof Factory;
        }

        public int hashCode() {
            return getClass().hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BitmapFactoryDecoder.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0016R.\u0010\b\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$ExceptionCatchingSource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "(Lokio/Source;)V", "<set-?>", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "getException", "()Ljava/lang/Exception;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class ExceptionCatchingSource extends ForwardingSource {
        private Exception exception;

        public ExceptionCatchingSource(Source delegate) {
            super(delegate);
        }

        public final Exception getException() {
            return this.exception;
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer sink, long byteCount) {
            try {
                return super.read(sink, byteCount);
            } catch (Exception e) {
                this.exception = e;
                throw e;
            }
        }
    }
}
