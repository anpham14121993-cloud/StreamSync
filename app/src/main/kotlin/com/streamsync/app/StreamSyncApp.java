package com.streamsync.app;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import coil.Coil;
import coil.ImageLoader;
import coil.ImageLoaderFactory;
import coil.memory.MemoryCache;
import com.streamsync.app.diag.CrashReporter;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.settings.DeviceMemory;
import com.streamsync.app.util.FileLog;
import com.streamsync.app.util.StreamSyncImageLoaderKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: StreamSyncApp.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\rH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/streamsync/app/StreamSyncApp;", "Landroid/app/Application;", "Lcoil/ImageLoaderFactory;", "()V", "appScope", "Lkotlinx/coroutines/CoroutineScope;", "getAppScope", "()Lkotlinx/coroutines/CoroutineScope;", "appScope$delegate", "Lkotlin/Lazy;", "newImageLoader", "Lcoil/ImageLoader;", "onCreate", "", "onLowMemory", "onTrimMemory", "level", "", "releaseDecodedImages", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class StreamSyncApp extends Application implements ImageLoaderFactory {
    public static final int $stable = 8;

    /* renamed from: appScope$delegate, reason: from kotlin metadata */
    private final Lazy appScope = LazyKt.lazy(new Function0<CoroutineScope>() { // from class: com.streamsync.app.StreamSyncApp$appScope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        }
    });

    public final CoroutineScope getAppScope() {
        return (CoroutineScope) this.appScope.getValue();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FileLog.INSTANCE.init(this);
        CrashReporter.INSTANCE.install(this);
        DeviceMemory.Info info = DeviceMemory.INSTANCE.read(this);
        FileLog fileLog = FileLog.INSTANCE;
        int myPid = Process.myPid();
        String str = Build.VERSION.RELEASE;
        int i = Build.VERSION.SDK_INT;
        String[] SUPPORTED_ABIS = Build.SUPPORTED_ABIS;
        Intrinsics.checkNotNullExpressionValue(SUPPORTED_ABIS, "SUPPORTED_ABIS");
        FileLog.d$default(fileLog, "App khoi dong | pid=" + myPid + " Android=" + str + " API=" + i + " abi=" + ArraysKt.joinToString$default(SUPPORTED_ABIS, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + " RAM=" + info.getTotalMb() + "MB con trong=" + info.getAvailableMb() + "MB heap toi da=" + (Runtime.getRuntime().maxMemory() / 1048576) + "MB | log o: " + FileLog.INSTANCE.location(), null, 2, null);
        FileLog.d$default(FileLog.INSTANCE, "Lenh doc: cat " + FileLog.INSTANCE.location(), null, 2, null);
    }

    @Override // coil.ImageLoaderFactory
    public ImageLoader newImageLoader() {
        return StreamSyncImageLoaderKt.buildAppImageLoader(this);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Runtime rt = Runtime.getRuntime();
        long j = 1048576;
        FileLog.d$default(FileLog.INSTANCE, "onTrimMemory mức " + level + " (heap " + (rt.totalMemory() / j) + "/" + (rt.maxMemory() / j) + " MB)", null, 2, null);
        if (level >= 10) {
            releaseDecodedImages();
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        FileLog.w$default(FileLog.INSTANCE, "onLowMemory: het bo mem, xoa vung anh da giai ma", null, 2, null);
        releaseDecodedImages();
    }

    private final void releaseDecodedImages() {
        Object m6717constructorimpl;
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            StreamSyncApp $this$releaseDecodedImages_u24lambda_u240 = this;
            MemoryCache memoryCache = Coil.imageLoader($this$releaseDecodedImages_u24lambda_u240).getMemoryCache();
            if (memoryCache != null) {
                memoryCache.clear();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            m6717constructorimpl = Result.m6717constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        Throwable it = Result.m6720exceptionOrNullimpl(m6717constructorimpl);
        if (it != null) {
            FileLog.w$default(FileLog.INSTANCE, "khong xoa duoc memory cache cua Coil: " + it.getMessage(), null, 2, null);
        }
    }
}
