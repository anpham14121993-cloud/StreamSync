package com.streamsync.app;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.streamsync.app.data.local.MediaItemCache;
import com.streamsync.app.data.remote.RetrofitClient;
import com.streamsync.app.diag.FrameTimingLogger;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.util.FileLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u0084\u0002²\u0006\f\u0010\n\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002"}, d2 = {"Lcom/streamsync/app/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_mobileDebug", "uiSettings", "Lcom/streamsync/app/settings/UiSettings;", "lastExitReport", ""}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetrofitClient.INSTANCE.init(this);
        FileLog.d$default(FileLog.INSTANCE, "Dang doc cache danh muc tu dia...", null, 2, null);
        Thread $this$onCreate_u24lambda_u241 = new Thread(new Runnable() { // from class: com.streamsync.app.MainActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.onCreate$lambda$0(MainActivity.this);
            }
        });
        $this$onCreate_u24lambda_u241.setDaemon(true);
        $this$onCreate_u24lambda_u241.start();
        FrameTimingLogger.INSTANCE.install(this);
        FrameTimingLogger.INSTANCE.setSuppressed(true);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.setSystemBarsBehavior(1);
        FrameTimingLogger.INSTANCE.setSuppressed(false);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(366846119, true, new MainActivity$onCreate$4(this)), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        long t0 = System.currentTimeMillis();
        int cachedItems = MediaItemCache.INSTANCE.attach(this$0);
        FileLog.d$default(FileLog.INSTANCE, "Nap cache tu bo nho app: " + cachedItems + " muc trong " + (System.currentTimeMillis() - t0) + " ms (nen)", null, 2, null);
    }
}
