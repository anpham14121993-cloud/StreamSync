package androidx.media3.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.SystemClock;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes24.dex */
public final class WearUnsuitableOutputPlaybackSuppressionResolverListener implements Player.Listener {
    public static final long DEFAULT_PLAYBACK_SUPPRESSION_AUTO_RESUME_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5);
    private static final String EXTRA_BLUETOOTH_SETTINGS_CLOSE_ON_CONNECT = "EXTRA_CLOSE_ON_CONNECT";
    private static final String EXTRA_BLUETOOTH_SETTINGS_CONNECTION_ONLY = "EXTRA_CONNECTION_ONLY";
    private static final String EXTRA_BLUETOOTH_SETTINGS_FILTER_TYPE = "android.bluetooth.devicepicker.extra.FILTER_TYPE";
    private static final String EXTRA_OUTPUT_SWITCHER_PACKAGE_NAME = "com.android.settings.panel.extra.PACKAGE_NAME";
    private static final int FILTER_TYPE_AUDIO = 1;
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_NAME = "com.android.settings.panel.action.MEDIA_OUTPUT";
    private final Context applicationContext;
    private final long autoResumeTimeoutAfterUnsuitableOutputSuppressionMs;
    private final Clock clock;
    private long unsuitableOutputPlaybackSuppressionStartRealtimeMs;

    public WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context) {
        this(context, DEFAULT_PLAYBACK_SUPPRESSION_AUTO_RESUME_TIMEOUT_MS);
    }

    public WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context, long autoResumeTimeoutMs) {
        this(context, autoResumeTimeoutMs, SystemClock.DEFAULT);
    }

    WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context, long autoResumeTimeoutMs, Clock clock) {
        Assertions.checkArgument(autoResumeTimeoutMs >= 0);
        this.applicationContext = context.getApplicationContext();
        this.autoResumeTimeoutAfterUnsuitableOutputSuppressionMs = autoResumeTimeoutMs;
        this.clock = clock;
        this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onEvents(Player player, Player.Events events) {
        if (!Util.isWear(this.applicationContext)) {
            return;
        }
        if ((events.contains(6) || events.contains(5)) && player.getPlayWhenReady() && player.getPlaybackSuppressionReason() == 3) {
            player.pause();
            this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = this.clock.elapsedRealtime();
            if (events.contains(5)) {
                launchSystemMediaOutputSwitcherUi(this.applicationContext);
                return;
            }
            return;
        }
        if (events.contains(6) && player.getPlaybackSuppressionReason() == 0 && this.unsuitableOutputPlaybackSuppressionStartRealtimeMs != C.TIME_UNSET && this.clock.elapsedRealtime() - this.unsuitableOutputPlaybackSuppressionStartRealtimeMs < this.autoResumeTimeoutAfterUnsuitableOutputSuppressionMs) {
            this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = C.TIME_UNSET;
            player.play();
        }
    }

    private static void launchSystemMediaOutputSwitcherUi(Context context) {
        Intent outputSwitcherLaunchIntent = new Intent(OUTPUT_SWITCHER_INTENT_ACTION_NAME).addFlags(268435456).putExtra(EXTRA_OUTPUT_SWITCHER_PACKAGE_NAME, context.getPackageName());
        ComponentName outputSwitcherSystemComponentName = getSystemOrSystemUpdatedAppComponent(context, outputSwitcherLaunchIntent);
        if (outputSwitcherSystemComponentName != null) {
            outputSwitcherLaunchIntent.setComponent(outputSwitcherSystemComponentName);
            context.startActivity(outputSwitcherLaunchIntent);
            return;
        }
        Intent bluetoothSettingsLaunchIntent = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268468224).putExtra(EXTRA_BLUETOOTH_SETTINGS_CLOSE_ON_CONNECT, true).putExtra(EXTRA_BLUETOOTH_SETTINGS_CONNECTION_ONLY, true).putExtra(EXTRA_BLUETOOTH_SETTINGS_FILTER_TYPE, 1);
        ComponentName bluetoothSettingsSystemComponentName = getSystemOrSystemUpdatedAppComponent(context, bluetoothSettingsLaunchIntent);
        if (bluetoothSettingsSystemComponentName != null) {
            bluetoothSettingsLaunchIntent.setComponent(bluetoothSettingsSystemComponentName);
            context.startActivity(bluetoothSettingsLaunchIntent);
        }
    }

    private static ComponentName getSystemOrSystemUpdatedAppComponent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && activityInfo.applicationInfo != null) {
                ApplicationInfo appInfo = activityInfo.applicationInfo;
                if ((appInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) != 0) {
                    return new ComponentName(activityInfo.packageName, activityInfo.name);
                }
            }
        }
        return null;
    }
}
