package com.streamsync.app.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.ui.mobile.MobileHomeScreen
import com.streamsync.app.ui.player.PlayerScreen
import com.streamsync.app.ui.tablet.TabletHomeScreen
import com.streamsync.app.ui.tv.TvHomeScreen
import com.streamsync.app.util.DeviceDetection
import com.streamsync.app.util.DeviceKind

@Composable
fun AppNavGraph(
    windowWidthSizeClass: WindowWidthSizeClass
) {
    val context = LocalContext.current
    val deviceKind = remember(windowWidthSizeClass) {
        DeviceDetection.getDeviceKind(context, windowWidthSizeClass)
    }

    var selectedMediaForPlayback by remember { mutableStateOf<MediaItem?>(null) }

    if (selectedMediaForPlayback != null) {
        PlayerScreen(
            mediaItem = selectedMediaForPlayback!!,
            onBackClick = { selectedMediaForPlayback = null }
        )
    } else {
        when (deviceKind) {
            DeviceKind.TV -> {
                TvHomeScreen(
                    onMediaClick = { media -> selectedMediaForPlayback = media }
                )
            }
            DeviceKind.TABLET -> {
                TabletHomeScreen(
                    onMediaClick = { media -> selectedMediaForPlayback = media }
                )
            }
            DeviceKind.MOBILE -> {
                MobileHomeScreen(
                    onMediaClick = { media -> selectedMediaForPlayback = media }
                )
            }
        }
    }
}
