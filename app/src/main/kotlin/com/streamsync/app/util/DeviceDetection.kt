package com.streamsync.app.util

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

enum class DeviceKind {
    MOBILE,
    TABLET,
    TV
}

object DeviceDetection {

    /**
     * Tự nhận biết Android TV bằng UiModeManager (UI_MODE_TYPE_TELEVISION)
     */
    fun isTelevision(context: Context): Boolean {
        return runCatching {
            val uiModeManager = context.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager
            uiModeManager?.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
        }.getOrDefault(false)
    }

    /**
     * Xác định loại thiết bị kết hợp UiModeManager và WindowWidthSizeClass
     */
    fun getDeviceKind(
        context: Context,
        windowWidthSizeClass: WindowWidthSizeClass
    ): DeviceKind {
        if (isTelevision(context)) {
            return DeviceKind.TV
        }
        return when (windowWidthSizeClass) {
            WindowWidthSizeClass.Expanded -> DeviceKind.TABLET
            else -> DeviceKind.MOBILE
        }
    }
}
