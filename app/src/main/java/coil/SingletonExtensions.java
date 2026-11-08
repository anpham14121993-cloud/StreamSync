package coil;

import android.content.Context;
import android.widget.ImageView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.util.CoilUtils;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: Extensions.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0007H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u000f*\u00020\u0007H\u0086\b\u001a<\u0010\u0011\u001a\u00020\u0012*\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000f0\u0016¢\u0006\u0002\b\u0018H\u0086\b\u001a<\u0010\u0019\u001a\u00020\u0012*\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000f0\u0016¢\u0006\u0002\b\u0018H\u0087\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00078Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0018\u0010\f\u001a\u0004\u0018\u00010\u0006*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u001a"}, d2 = {"imageLoader", "Lcoil/ImageLoader;", "Landroid/content/Context;", "getImageLoader", "(Landroid/content/Context;)Lcoil/ImageLoader;", TtmlNode.TAG_METADATA, "Lcoil/request/ImageResult;", "Landroid/widget/ImageView;", "getMetadata$annotations", "(Landroid/widget/ImageView;)V", "getMetadata", "(Landroid/widget/ImageView;)Lcoil/request/ImageResult;", "result", "getResult", "clear", "", "dispose", "load", "Lcoil/request/Disposable;", "data", "", "builder", "Lkotlin/Function1;", "Lcoil/request/ImageRequest$Builder;", "Lkotlin/ExtensionFunctionType;", "loadAny", "coil-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* renamed from: coil.-SingletonExtensions, reason: invalid class name */
/* loaded from: classes24.dex */
public final class SingletonExtensions {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to 'result'.", replaceWith = @ReplaceWith(expression = "result", imports = {"coil.result"}))
    public static /* synthetic */ void getMetadata$annotations(ImageView imageView) {
    }

    public static final ImageLoader getImageLoader(Context $this$imageLoader) {
        return Coil.imageLoader($this$imageLoader);
    }

    public static /* synthetic */ Disposable load$default(ImageView $this$load_u24default, Object data, ImageLoader imageLoader, Function1 builder, int i, Object obj) {
        if ((i & 2) != 0) {
            Context $this$imageLoader$iv = $this$load_u24default.getContext();
            imageLoader = Coil.imageLoader($this$imageLoader$iv);
        }
        if ((i & 4) != 0) {
            Function1 builder2 = new Function1<ImageRequest.Builder, Unit>() { // from class: coil.-SingletonExtensions$load$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ImageRequest.Builder builder3) {
                    invoke2(builder3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ImageRequest.Builder $this$null) {
                }
            };
            builder = builder2;
        }
        ImageRequest.Builder target = new ImageRequest.Builder($this$load_u24default.getContext()).data(data).target($this$load_u24default);
        builder.invoke(target);
        ImageRequest request = target.build();
        return imageLoader.enqueue(request);
    }

    public static final Disposable load(ImageView $this$load, Object data, ImageLoader imageLoader, Function1<? super ImageRequest.Builder, Unit> function1) {
        ImageRequest.Builder target = new ImageRequest.Builder($this$load.getContext()).data(data).target($this$load);
        function1.invoke(target);
        ImageRequest request = target.build();
        return imageLoader.enqueue(request);
    }

    public static final void dispose(ImageView $this$dispose) {
        CoilUtils.dispose($this$dispose);
    }

    public static final ImageResult getResult(ImageView $this$result) {
        return CoilUtils.result($this$result);
    }

    public static /* synthetic */ Disposable loadAny$default(ImageView $this$loadAny_u24default, Object data, ImageLoader imageLoader, Function1 builder, int i, Object obj) {
        if ((i & 2) != 0) {
            Context $this$imageLoader$iv = $this$loadAny_u24default.getContext();
            imageLoader = Coil.imageLoader($this$imageLoader$iv);
        }
        if ((i & 4) != 0) {
            Function1 builder2 = new Function1<ImageRequest.Builder, Unit>() { // from class: coil.-SingletonExtensions$loadAny$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ImageRequest.Builder builder3) {
                    invoke2(builder3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ImageRequest.Builder $this$null) {
                }
            };
            builder = builder2;
        }
        ImageRequest.Builder target = new ImageRequest.Builder($this$loadAny_u24default.getContext()).data(data).target($this$loadAny_u24default);
        builder.invoke(target);
        ImageRequest request$iv = target.build();
        return imageLoader.enqueue(request$iv);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to 'load'.", replaceWith = @ReplaceWith(expression = "load(data, imageLoader, builder)", imports = {"coil.imageLoader", "coil.load"}))
    public static final Disposable loadAny(ImageView $this$loadAny, Object data, ImageLoader imageLoader, Function1<? super ImageRequest.Builder, Unit> function1) {
        ImageRequest.Builder target = new ImageRequest.Builder($this$loadAny.getContext()).data(data).target($this$loadAny);
        function1.invoke(target);
        ImageRequest request$iv = target.build();
        return imageLoader.enqueue(request$iv);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to 'dispose'.", replaceWith = @ReplaceWith(expression = "dispose()", imports = {"coil.dispose"}))
    public static final void clear(ImageView $this$clear) {
        CoilUtils.dispose($this$clear);
    }

    public static final ImageResult getMetadata(ImageView $this$metadata) {
        return CoilUtils.result($this$metadata);
    }
}
