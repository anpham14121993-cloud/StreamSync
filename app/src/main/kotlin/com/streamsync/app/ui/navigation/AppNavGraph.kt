package com.streamsync.app.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.ui.detail.MovieDetailScreen
import com.streamsync.app.ui.mobile.MobileHomeScreen
import com.streamsync.app.ui.player.PlayerScreen
import com.streamsync.app.ui.tablet.TabletHomeScreen
import com.streamsync.app.ui.tv.TvHomeScreen
import com.streamsync.app.util.DeviceDetection
import com.streamsync.app.util.DeviceKind

data class PlaybackTarget(
    val mediaItem: MediaItem,
    val streamUrl: String,
    val title: String
)

@Composable
fun AppNavGraph(
    windowWidthSizeClass: WindowWidthSizeClass
) {
    val context = LocalContext.current
    val deviceKind = remember(windowWidthSizeClass) {
        DeviceDetection.getDeviceKind(context, windowWidthSizeClass)
    }

    var selectedMediaForDetail by remember { mutableStateOf<MediaItem?>(null) }
    var activePlayback by remember { mutableStateOf<PlaybackTarget?>(null) }

    when {
        activePlayback != null -> {
            PlayerScreen(
                mediaItem = activePlayback!!.mediaItem,
                streamUrl = activePlayback!!.streamUrl,
                titleOverride = activePlayback!!.title,
                onBackClick = { activePlayback = null }
            )
        }
        selectedMediaForDetail != null -> {
            MovieDetailScreen(
                mediaItem = selectedMediaForDetail!!,
                onBackClick = { selectedMediaForDetail = null },
                onPlayStream = { streamUrl, title ->
                    activePlayback = PlaybackTarget(
                        mediaItem = selectedMediaForDetail!!,
                        streamUrl = streamUrl,
                        title = title
                    )
                }
            )
        }
        else -> {
            when (deviceKind) {
                DeviceKind.TV -> {
                    TvHomeScreen(
                        onMediaClick = { media -> selectedMediaForDetail = media }
                    )
                }
                DeviceKind.TABLET -> {
                    TabletHomeScreen(
                        onMediaClick = { media -> selectedMediaForDetail = media }
                    )
                }
                DeviceKind.MOBILE -> {
                    MobileHomeScreen(
                        onMediaClick = { media -> selectedMediaForDetail = media }
                    )
                }
            }
        }
    }
}
