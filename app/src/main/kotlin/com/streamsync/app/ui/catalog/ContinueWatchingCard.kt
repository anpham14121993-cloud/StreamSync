package com.streamsync.app.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.streamsync.app.data.local.MediaItemCache
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import com.streamsync.app.data.model.WatchProgress
import com.streamsync.app.ui.components.RowHeader
import com.streamsync.app.ui.player.AddonIdParser
import kotlin.math.max

/**
 * Modern Nuvio-style Continue Watching Shelf with wide landscape cards,
 * clear episode tags, progress bar, and remaining time.
 */
@Composable
fun ContinueWatchingPager(
    items: List<MediaItem>,
    progressSnapshot: List<WatchProgress>,
    modifier: Modifier = Modifier,
    onItemClick: (MediaItem) -> Unit,
) {
    if (items.isEmpty()) return

    val progressMap = remember(progressSnapshot) {
        progressSnapshot.associateBy { it.mediaId }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowHeader(
            title = "Tiếp tục xem",
            subtitle = "${items.size} nội dung đang xem"
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(
                items = items,
                key = { item -> "cw_${item.id}" }
            ) { item ->
                val progress = progressMap[item.id] ?: fallbackProgressOf(item)
                val showTitle = rememberShowTitle(item)

                ContinueWatchingCard(
                    item = item,
                    showTitle = showTitle,
                    progress = progress,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

/**
 * Landscape 16:9 Continue Watching Card with sleek progress bar, episode tag,
 * and high-contrast readable typography.
 */
@Composable
fun ContinueWatchingCard(
    item: MediaItem,
    showTitle: String?,
    progress: WatchProgress,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val cardWidth = 240.dp
    val cardHeight = 135.dp // 16:9 aspect ratio

    val thumbnailUrl = item.backgroundUrl ?: item.posterUrl
    val episodeInfo = remember(item) { episodeSeasonEpisodeLine(item) }
    val remainingText = remember(progress.positionMs, progress.durationMs) {
        if (progress.durationMs > progress.positionMs && progress.positionMs > 0) {
            val remainMin = max(1, ((progress.durationMs - progress.positionMs) / 60000).toInt())
            "Còn ${remainMin} phút"
        } else null
    }

    Column(
        modifier = modifier
            .width(cardWidth)
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
    ) {
        // Thumbnail & Progress Container
        Box(
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight)
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient for overlay readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.25f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )

            // Center Play Icon badge
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.55f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Episode / Season Badge top-start
            if (!episodeInfo.isNullOrBlank()) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.90f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = episodeInfo,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Remaining Time badge top-end
            if (remainingText != null) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    color = Color.Black.copy(alpha = 0.70f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = remainingText,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }

            // Sleek Glowing Progress Bar at the bottom
            ProgressBarWatched(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title and Subtitle Block
        val mainTitle = showTitle?.takeIf { it.isNotBlank() } ?: item.title
        Text(
            text = mainTitle,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Episode Title if available
        val episodeTitle = item.title.takeIf { it != mainTitle }
        if (!episodeTitle.isNullOrBlank()) {
            Text(
                text = episodeTitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Sleek progress bar attached to bottom of thumbnail card.
 */
@Composable
fun ProgressBarWatched(
    progress: WatchProgress,
    modifier: Modifier = Modifier,
) {
    val fraction = progress.fractionWatched.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.White.copy(alpha = 0.25f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction)
                .height(4.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

fun formatWatchedDuration(positionMs: Long): String {
    val totalSeconds = (positionMs / 1000).coerceAtLeast(0)
    val h = totalSeconds / 3600
    val m = (totalSeconds % 3600) / 60
    val s = totalSeconds % 60
    return if (h > 0) {
        String.format("%02d:%02d:%02d", h, m, s)
    } else {
        String.format("%02d:%02d", m, s)
    }
}

private fun fallbackProgressOf(item: MediaItem): WatchProgress {
    val duration = (item.durationSeconds ?: 0L) * 1000L
    return WatchProgress(
        mediaId = item.id,
        positionMs = 0L,
        durationMs = duration,
        updatedAtEpochMs = 0L,
        deviceId = "",
        streamUrl = null,
        addonId = item.addonId,
        addonName = item.addonName
    )
}

@Composable
private fun rememberShowTitle(item: MediaItem): String? {
    val id = item.id
    return remember(id) {
        if (item.type != MediaType.SERIES_EPISODE) {
            item.title
        } else {
            val showId = AddonIdParser.INSTANCE.showIdOfEpisode(id)
            if (showId != null) {
                MediaItemCache.INSTANCE.get(showId)?.title
            } else {
                null
            }
        }
    }
}

private fun episodeSeasonEpisodeLine(item: MediaItem): String? {
    val id = item.id
    val parts = id.split(":")
    return if (parts.size >= 3) {
        val season = parts.getOrNull(parts.size - 2)?.toIntOrNull()
        val episode = parts.getOrNull(parts.size - 1)?.toIntOrNull()
        if (season != null && episode != null) {
            "S${season} E${episode}"
        } else if (episode != null) {
            "Tập $episode"
        } else null
    } else null
}
