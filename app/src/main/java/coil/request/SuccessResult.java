package coil.request;

import android.graphics.drawable.Drawable;
import coil.decode.DataSource;
import coil.memory.MemoryCache;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageResult.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJP\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rJ\u0013\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006!"}, d2 = {"Lcoil/request/SuccessResult;", "Lcoil/request/ImageResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "request", "Lcoil/request/ImageRequest;", "dataSource", "Lcoil/decode/DataSource;", "memoryCacheKey", "Lcoil/memory/MemoryCache$Key;", "diskCacheKey", "", "isSampled", "", "isPlaceholderCached", "(Landroid/graphics/drawable/Drawable;Lcoil/request/ImageRequest;Lcoil/decode/DataSource;Lcoil/memory/MemoryCache$Key;Ljava/lang/String;ZZ)V", "getDataSource", "()Lcoil/decode/DataSource;", "getDiskCacheKey", "()Ljava/lang/String;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "getMemoryCacheKey", "()Lcoil/memory/MemoryCache$Key;", "getRequest", "()Lcoil/request/ImageRequest;", "copy", "equals", "other", "", "hashCode", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class SuccessResult extends ImageResult {
    private final DataSource dataSource;
    private final String diskCacheKey;
    private final Drawable drawable;
    private final boolean isPlaceholderCached;
    private final boolean isSampled;
    private final MemoryCache.Key memoryCacheKey;
    private final ImageRequest request;

    public /* synthetic */ SuccessResult(Drawable drawable, ImageRequest imageRequest, DataSource dataSource, MemoryCache.Key key, String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(drawable, imageRequest, dataSource, (i & 8) != 0 ? null : key, (i & 16) != 0 ? null : str, (i & 32) != 0 ? false : z, (i & 64) != 0 ? false : z2);
    }

    @Override // coil.request.ImageResult
    public Drawable getDrawable() {
        return this.drawable;
    }

    @Override // coil.request.ImageResult
    public ImageRequest getRequest() {
        return this.request;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    public final MemoryCache.Key getMemoryCacheKey() {
        return this.memoryCacheKey;
    }

    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    /* renamed from: isSampled, reason: from getter */
    public final boolean getIsSampled() {
        return this.isSampled;
    }

    /* renamed from: isPlaceholderCached, reason: from getter */
    public final boolean getIsPlaceholderCached() {
        return this.isPlaceholderCached;
    }

    public SuccessResult(Drawable drawable, ImageRequest request, DataSource dataSource, MemoryCache.Key memoryCacheKey, String diskCacheKey, boolean isSampled, boolean isPlaceholderCached) {
        super(null);
        this.drawable = drawable;
        this.request = request;
        this.dataSource = dataSource;
        this.memoryCacheKey = memoryCacheKey;
        this.diskCacheKey = diskCacheKey;
        this.isSampled = isSampled;
        this.isPlaceholderCached = isPlaceholderCached;
    }

    public static /* synthetic */ SuccessResult copy$default(SuccessResult successResult, Drawable drawable, ImageRequest imageRequest, DataSource dataSource, MemoryCache.Key key, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable = successResult.getDrawable();
        }
        return successResult.copy(drawable, (i & 2) != 0 ? successResult.getRequest() : imageRequest, (i & 4) != 0 ? successResult.dataSource : dataSource, (i & 8) != 0 ? successResult.memoryCacheKey : key, (i & 16) != 0 ? successResult.diskCacheKey : str, (i & 32) != 0 ? successResult.isSampled : z, (i & 64) != 0 ? successResult.isPlaceholderCached : z2);
    }

    public final SuccessResult copy(Drawable drawable, ImageRequest request, DataSource dataSource, MemoryCache.Key memoryCacheKey, String diskCacheKey, boolean isSampled, boolean isPlaceholderCached) {
        return new SuccessResult(drawable, request, dataSource, memoryCacheKey, diskCacheKey, isSampled, isPlaceholderCached);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SuccessResult) && Intrinsics.areEqual(getDrawable(), ((SuccessResult) other).getDrawable()) && Intrinsics.areEqual(getRequest(), ((SuccessResult) other).getRequest()) && this.dataSource == ((SuccessResult) other).dataSource && Intrinsics.areEqual(this.memoryCacheKey, ((SuccessResult) other).memoryCacheKey) && Intrinsics.areEqual(this.diskCacheKey, ((SuccessResult) other).diskCacheKey) && this.isSampled == ((SuccessResult) other).isSampled && this.isPlaceholderCached == ((SuccessResult) other).isPlaceholderCached;
    }

    public int hashCode() {
        int result = getDrawable().hashCode();
        int result2 = ((((result * 31) + getRequest().hashCode()) * 31) + this.dataSource.hashCode()) * 31;
        MemoryCache.Key key = this.memoryCacheKey;
        int result3 = (result2 + (key != null ? key.hashCode() : 0)) * 31;
        String str = this.diskCacheKey;
        return ((((result3 + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.isSampled)) * 31) + Boolean.hashCode(this.isPlaceholderCached);
    }
}
