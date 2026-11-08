package coil.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.base.R;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.disk.DiskCache;
import coil.fetch.Fetcher;
import coil.intercept.Interceptor;
import coil.intercept.RealInterceptorChain;
import coil.memory.MemoryCache;
import coil.request.Parameters;
import coil.request.Tags;
import coil.request.ViewTargetRequestManager;
import coil.size.Dimension;
import coil.size.Scale;
import coil.size.Size;
import coil.size.Sizes;
import com.streamsync.app.settings.BufferCeiling;
import java.io.Closeable;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Deferred;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000\u009c\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020A2\u0006\u0010M\u001a\u00020\rH\u0000\u001a\u0010\u0010N\u001a\u00020\r2\u0006\u0010L\u001a\u00020AH\u0000\u001a\u0010\u0010O\u001a\u0002022\u0006\u0010P\u001a\u00020&H\u0000\u001a\b\u0010Q\u001a\u000202H\u0000\u001a\b\u0010R\u001a\u00020SH\u0000\u001a\f\u0010T\u001a\u00020U*\u00020VH\u0000\u001a\u0017\u0010W\u001a\u00020X*\u00020X2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0080\b\u001a+\u0010W\u001a\u00020X*\u00020X2\u001c\u0010[\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030]\u0012\b\u0012\u0006\u0012\u0002\b\u00030^\u0018\u00010\\H\u0080\b\u001a\u0014\u0010_\u001a\u00020`*\u00020`2\u0006\u0010a\u001a\u00020\u0001H\u0000\u001a\f\u0010b\u001a\u00020U*\u00020cH\u0000\u001a\u0019\u0010d\u001a\u0004\u0018\u00010e*\u00020f2\b\u0010g\u001a\u0004\u0018\u00010hH\u0080\n\u001a\u001f\u0010i\u001a\u0004\u0018\u0001Hj\"\u0004\b\u0000\u0010j*\b\u0012\u0004\u0012\u0002Hj0kH\u0000¢\u0006\u0002\u0010l\u001a\u0018\u0010m\u001a\u0004\u0018\u00010\u0001*\u00020n2\b\u0010o\u001a\u0004\u0018\u00010\u0001H\u0000\u001a#\u0010p\u001a\u00020\u0007*\u00020q2\u0006\u0010D\u001a\u00020E2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00070sH\u0080\b\u001a\f\u0010t\u001a\u000202*\u00020\u0007H\u0000\u001a\u000e\u0010u\u001a\u00020v*\u0004\u0018\u00010vH\u0000\u001a\u000e\u0010u\u001a\u00020w*\u0004\u0018\u00010wH\u0000\u001a\u000e\u0010u\u001a\u00020\t*\u0004\u0018\u00010\tH\u0000\u001a\f\u0010x\u001a\u00020y*\u00020zH\u0000\u001a\u0014\u0010{\u001a\u00020\u0007*\u00020\u00012\u0006\u0010|\u001a\u00020\u0007H\u0000\u001a\u0014\u0010}\u001a\u00020\u0007*\u00020~2\u0006\u0010D\u001a\u00020EH\u0000\u001a#\u0010\u007f\u001a\u00020\u0007*\u00020q2\u0006\u0010D\u001a\u00020E2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00070sH\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u000e\u0010\u0016\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0080\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001a\"\u0018\u0010\u001c\u001a\u00020\u0001*\u00020\u001d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0018\u0010 \u001a\u00020!*\u00020\"8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$\"\u001a\u0010%\u001a\u0004\u0018\u00010\u0001*\u00020&8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(\"\u0018\u0010)\u001a\u00020\u0007*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\"\u0019\u0010-\u001a\u00020\u0007*\u00020.8À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0018\u00101\u001a\u000202*\u00020\"8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u00103\"\u0018\u00104\u001a\u000202*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105\"\u0018\u00106\u001a\u00020\u0007*\u0002078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0018\u0010:\u001a\u00020;*\u00020<8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>\"\u0018\u0010?\u001a\u00020@*\u00020A8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C\"\u0018\u0010D\u001a\u00020E*\u00020F8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010H\"\u0018\u0010I\u001a\u00020\u0007*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010,¨\u0006\u0080\u0001"}, d2 = {"ASSET_FILE_PATH_ROOT", "", "DEFAULT_BITMAP_CONFIG", "Landroid/graphics/Bitmap$Config;", "getDEFAULT_BITMAP_CONFIG", "()Landroid/graphics/Bitmap$Config;", "DEFAULT_MEMORY_CLASS_MEGABYTES", "", "EMPTY_HEADERS", "Lokhttp3/Headers;", "getEMPTY_HEADERS", "()Lokhttp3/Headers;", "LOW_MEMORY_MULTIPLIER", "", "MIME_TYPE_HEIC", "MIME_TYPE_HEIF", "MIME_TYPE_JPEG", "MIME_TYPE_WEBP", "NULL_COLOR_SPACE", "Landroid/graphics/ColorSpace;", "getNULL_COLOR_SPACE", "()Landroid/graphics/ColorSpace;", "STANDARD_MEMORY_MULTIPLIER", "VALID_TRANSFORMATION_CONFIGS", "", "getVALID_TRANSFORMATION_CONFIGS", "()[Landroid/graphics/Bitmap$Config;", "[Landroid/graphics/Bitmap$Config;", "emoji", "Lcoil/decode/DataSource;", "getEmoji", "(Lcoil/decode/DataSource;)Ljava/lang/String;", "eventListener", "Lcoil/EventListener;", "Lcoil/intercept/Interceptor$Chain;", "getEventListener", "(Lcoil/intercept/Interceptor$Chain;)Lcoil/EventListener;", "firstPathSegment", "Landroid/net/Uri;", "getFirstPathSegment", "(Landroid/net/Uri;)Ljava/lang/String;", "height", "Landroid/graphics/drawable/Drawable;", "getHeight", "(Landroid/graphics/drawable/Drawable;)I", "identityHashCode", "", "getIdentityHashCode", "(Ljava/lang/Object;)I", "isPlaceholderCached", "", "(Lcoil/intercept/Interceptor$Chain;)Z", "isVector", "(Landroid/graphics/drawable/Drawable;)Z", "nightMode", "Landroid/content/res/Configuration;", "getNightMode", "(Landroid/content/res/Configuration;)I", "requestManager", "Lcoil/request/ViewTargetRequestManager;", "Landroid/view/View;", "getRequestManager", "(Landroid/view/View;)Lcoil/request/ViewTargetRequestManager;", "safeCacheDir", "Ljava/io/File;", "Landroid/content/Context;", "getSafeCacheDir", "(Landroid/content/Context;)Ljava/io/File;", "scale", "Lcoil/size/Scale;", "Landroid/widget/ImageView;", "getScale", "(Landroid/widget/ImageView;)Lcoil/size/Scale;", "width", "getWidth", "calculateMemoryCacheSize", "context", "percent", "defaultMemoryCacheSizePercent", "isAssetUri", "uri", "isMainThread", "unsupported", "", "abortQuietly", "", "Lcoil/disk/DiskCache$Editor;", "addFirst", "Lcoil/ComponentRegistry$Builder;", "factory", "Lcoil/decode/Decoder$Factory;", "pair", "Lkotlin/Pair;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/lang/Class;", "addUnsafeNonAscii", "Lokhttp3/Headers$Builder;", "line", "closeQuietly", "Ljava/io/Closeable;", "get", "Lcoil/memory/MemoryCache$Value;", "Lcoil/memory/MemoryCache;", "key", "Lcoil/memory/MemoryCache$Key;", "getCompletedOrNull", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/Deferred;", "(Lkotlinx/coroutines/Deferred;)Ljava/lang/Object;", "getMimeTypeFromUrl", "Landroid/webkit/MimeTypeMap;", "url", "heightPx", "Lcoil/size/Size;", "original", "Lkotlin/Function0;", "isMinOrMax", "orEmpty", "Lcoil/request/Parameters;", "Lcoil/request/Tags;", "requireBody", "Lokhttp3/ResponseBody;", "Lokhttp3/Response;", "toNonNegativeInt", "defaultValue", "toPx", "Lcoil/size/Dimension;", "widthPx", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* renamed from: coil.util.-Utils, reason: invalid class name */
/* loaded from: classes24.dex */
public final class Utils {
    public static final String ASSET_FILE_PATH_ROOT = "android_asset";
    private static final Bitmap.Config DEFAULT_BITMAP_CONFIG;
    private static final int DEFAULT_MEMORY_CLASS_MEGABYTES = 256;
    private static final Headers EMPTY_HEADERS;
    private static final double LOW_MEMORY_MULTIPLIER = 0.15d;
    public static final String MIME_TYPE_HEIC = "image/heic";
    public static final String MIME_TYPE_HEIF = "image/heif";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_WEBP = "image/webp";
    private static final ColorSpace NULL_COLOR_SPACE = null;
    private static final double STANDARD_MEMORY_MULTIPLIER = 0.2d;
    private static final Bitmap.Config[] VALID_TRANSFORMATION_CONFIGS;

    /* compiled from: Utils.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* renamed from: coil.util.-Utils$WhenMappings */
    /* loaded from: classes24.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DataSource.values().length];
            try {
                iArr[DataSource.MEMORY_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DataSource.MEMORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DataSource.DISK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DataSource.NETWORK.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            try {
                iArr2[ImageView.ScaleType.FIT_START.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[Scale.values().length];
            try {
                iArr3[Scale.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr3[Scale.FIT.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public static final ViewTargetRequestManager getRequestManager(View $this$requestManager) {
        ViewTargetRequestManager $this$_get_requestManager__u24lambda_u242_u24lambda_u241;
        Object tag = $this$requestManager.getTag(R.id.coil_request_manager);
        ViewTargetRequestManager manager = tag instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag : null;
        if (manager == null) {
            synchronized ($this$requestManager) {
                Object tag2 = $this$requestManager.getTag(R.id.coil_request_manager);
                $this$_get_requestManager__u24lambda_u242_u24lambda_u241 = tag2 instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag2 : null;
                if ($this$_get_requestManager__u24lambda_u242_u24lambda_u241 == null) {
                    $this$_get_requestManager__u24lambda_u242_u24lambda_u241 = new ViewTargetRequestManager($this$requestManager);
                    $this$requestManager.addOnAttachStateChangeListener($this$_get_requestManager__u24lambda_u242_u24lambda_u241);
                    $this$requestManager.setTag(R.id.coil_request_manager, $this$_get_requestManager__u24lambda_u242_u24lambda_u241);
                }
            }
            return $this$_get_requestManager__u24lambda_u242_u24lambda_u241;
        }
        return manager;
    }

    public static final String getEmoji(DataSource $this$emoji) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$emoji.ordinal()]) {
            case 1:
            case 2:
                return Emoji.BRAIN;
            case 3:
                return Emoji.FLOPPY;
            case 4:
                return Emoji.CLOUD;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final int getWidth(Drawable $this$width) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = $this$width instanceof BitmapDrawable ? (BitmapDrawable) $this$width : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? $this$width.getIntrinsicWidth() : bitmap.getWidth();
    }

    public static final int getHeight(Drawable $this$height) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = $this$height instanceof BitmapDrawable ? (BitmapDrawable) $this$height : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? $this$height.getIntrinsicHeight() : bitmap.getHeight();
    }

    public static final boolean isVector(Drawable $this$isVector) {
        return ($this$isVector instanceof VectorDrawable) || ($this$isVector instanceof VectorDrawableCompat);
    }

    public static final void closeQuietly(Closeable $this$closeQuietly) {
        try {
            $this$closeQuietly.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
        }
    }

    public static final Scale getScale(ImageView $this$scale) {
        ImageView.ScaleType scaleType = $this$scale.getScaleType();
        switch (scaleType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[scaleType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return Scale.FIT;
            default:
                return Scale.FILL;
        }
    }

    public static final String getMimeTypeFromUrl(MimeTypeMap $this$getMimeTypeFromUrl, String url) {
        String str = url;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String extension = StringsKt.substringAfterLast(StringsKt.substringAfterLast$default(StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(url, '#', (String) null, 2, (Object) null), '?', (String) null, 2, (Object) null), '/', (String) null, 2, (Object) null), '.', "");
        return $this$getMimeTypeFromUrl.getMimeTypeFromExtension(extension);
    }

    public static final String getFirstPathSegment(Uri $this$firstPathSegment) {
        return (String) CollectionsKt.firstOrNull((List) $this$firstPathSegment.getPathSegments());
    }

    public static final int getNightMode(Configuration $this$nightMode) {
        return $this$nightMode.uiMode & 48;
    }

    static {
        Bitmap.Config[] configArr;
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT >= 26) {
            configArr = new Bitmap.Config[]{Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16};
        } else {
            configArr = new Bitmap.Config[]{Bitmap.Config.ARGB_8888};
        }
        VALID_TRANSFORMATION_CONFIGS = configArr;
        if (Build.VERSION.SDK_INT >= 26) {
            config = Bitmap.Config.HARDWARE;
        } else {
            config = Bitmap.Config.ARGB_8888;
        }
        DEFAULT_BITMAP_CONFIG = config;
        EMPTY_HEADERS = new Headers.Builder().build();
    }

    public static final Bitmap.Config[] getVALID_TRANSFORMATION_CONFIGS() {
        return VALID_TRANSFORMATION_CONFIGS;
    }

    public static final Bitmap.Config getDEFAULT_BITMAP_CONFIG() {
        return DEFAULT_BITMAP_CONFIG;
    }

    public static final ColorSpace getNULL_COLOR_SPACE() {
        return NULL_COLOR_SPACE;
    }

    public static final Headers getEMPTY_HEADERS() {
        return EMPTY_HEADERS;
    }

    public static final Headers orEmpty(Headers $this$orEmpty) {
        return $this$orEmpty == null ? EMPTY_HEADERS : $this$orEmpty;
    }

    public static final Tags orEmpty(Tags $this$orEmpty) {
        return $this$orEmpty == null ? Tags.EMPTY : $this$orEmpty;
    }

    public static final Parameters orEmpty(Parameters $this$orEmpty) {
        return $this$orEmpty == null ? Parameters.EMPTY : $this$orEmpty;
    }

    public static final boolean isMainThread() {
        return Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper());
    }

    public static final int getIdentityHashCode(Object $this$identityHashCode) {
        return System.identityHashCode($this$identityHashCode);
    }

    public static final <T> T getCompletedOrNull(Deferred<? extends T> deferred) {
        try {
            return deferred.getCompleted();
        } catch (Throwable th) {
            return null;
        }
    }

    public static final MemoryCache.Value get(MemoryCache $this$get, MemoryCache.Key key) {
        if (key != null) {
            return $this$get.get(key);
        }
        return null;
    }

    public static final File getSafeCacheDir(Context $this$safeCacheDir) {
        File cacheDir = $this$safeCacheDir.getCacheDir();
        if (cacheDir == null) {
            throw new IllegalStateException("cacheDir == null".toString());
        }
        cacheDir.mkdirs();
        return cacheDir;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder $this$addFirst, Pair<? extends Fetcher.Factory<?>, ? extends Class<?>> pair) {
        if (pair != 0) {
            $this$addFirst.getFetcherFactories$coil_base_release().add(0, pair);
        }
        return $this$addFirst;
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder $this$addFirst, Decoder.Factory factory) {
        if (factory != null) {
            $this$addFirst.getDecoderFactories$coil_base_release().add(0, factory);
        }
        return $this$addFirst;
    }

    public static final int toNonNegativeInt(String $this$toNonNegativeInt, int defaultValue) {
        Long longOrNull = StringsKt.toLongOrNull($this$toNonNegativeInt);
        if (longOrNull == null) {
            return defaultValue;
        }
        long value = longOrNull.longValue();
        if (value > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (value < 0) {
            return 0;
        }
        return (int) value;
    }

    public static final void abortQuietly(DiskCache.Editor $this$abortQuietly) {
        try {
            $this$abortQuietly.abort();
        } catch (Exception e) {
        }
    }

    public static final boolean isPlaceholderCached(Interceptor.Chain $this$isPlaceholderCached) {
        return ($this$isPlaceholderCached instanceof RealInterceptorChain) && ((RealInterceptorChain) $this$isPlaceholderCached).getIsPlaceholderCached();
    }

    public static final EventListener getEventListener(Interceptor.Chain $this$eventListener) {
        return $this$eventListener instanceof RealInterceptorChain ? ((RealInterceptorChain) $this$eventListener).getEventListener() : EventListener.NONE;
    }

    public static final boolean isMinOrMax(int $this$isMinOrMax) {
        return $this$isMinOrMax == Integer.MIN_VALUE || $this$isMinOrMax == Integer.MAX_VALUE;
    }

    public static final int widthPx(Size $this$widthPx, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal($this$widthPx) ? function0.invoke().intValue() : toPx($this$widthPx.getWidth(), scale);
    }

    public static final int heightPx(Size $this$heightPx, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal($this$heightPx) ? function0.invoke().intValue() : toPx($this$heightPx.getHeight(), scale);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0015. Please report as an issue. */
    public static final int toPx(Dimension $this$toPx, Scale scale) {
        int i;
        if ($this$toPx instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) $this$toPx).px;
        }
        switch (WhenMappings.$EnumSwitchMapping$2[scale.ordinal()]) {
            case 1:
                i = Integer.MIN_VALUE;
                return i;
            case 2:
                i = Integer.MAX_VALUE;
                return i;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final Void unsupported() {
        throw new IllegalStateException("Unsupported".toString());
    }

    public static final boolean isAssetUri(Uri uri) {
        return Intrinsics.areEqual(uri.getScheme(), "file") && Intrinsics.areEqual(getFirstPathSegment(uri), ASSET_FILE_PATH_ROOT);
    }

    public static final Headers.Builder addUnsafeNonAscii(Headers.Builder $this$addUnsafeNonAscii, String line) {
        int index = StringsKt.indexOf$default((CharSequence) line, ':', 0, false, 6, (Object) null);
        if (!(index != -1)) {
            throw new IllegalArgumentException(("Unexpected header: " + line).toString());
        }
        String substring = line.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String obj = StringsKt.trim((CharSequence) substring).toString();
        String substring2 = line.substring(index + 1);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        $this$addUnsafeNonAscii.addUnsafeNonAscii(obj, substring2);
        return $this$addUnsafeNonAscii;
    }

    public static final ResponseBody requireBody(Response $this$requireBody) {
        ResponseBody body = $this$requireBody.body();
        if (body != null) {
            return body;
        }
        throw new IllegalStateException("response body == null".toString());
    }

    public static final double defaultMemoryCacheSizePercent(Context context) {
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            ActivityManager activityManager = (ActivityManager) systemService;
            return activityManager.isLowRamDevice() ? LOW_MEMORY_MULTIPLIER : STANDARD_MEMORY_MULTIPLIER;
        } catch (Exception e) {
            return STANDARD_MEMORY_MULTIPLIER;
        }
    }

    public static final int calculateMemoryCacheSize(Context context, double percent) {
        int i;
        try {
            Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
            Intrinsics.checkNotNull(systemService);
            ActivityManager activityManager = (ActivityManager) systemService;
            boolean isLargeHeap = (context.getApplicationInfo().flags & 1048576) != 0;
            i = isLargeHeap ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
        } catch (Exception e) {
            i = 256;
        }
        int memoryClassMegabytes = i;
        double d = 1024;
        return (int) (memoryClassMegabytes * percent * d * d);
    }
}
