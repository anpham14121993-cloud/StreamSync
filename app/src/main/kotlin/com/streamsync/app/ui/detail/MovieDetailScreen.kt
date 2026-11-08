package com.streamsync.app.ui.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.streamsync.app.data.addon.AddonStream
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import com.streamsync.app.data.model.SeriesVideo
import com.streamsync.app.data.repository.MediaRepository

@Composable
fun MovieDetailScreen(
    mediaItem: MediaItem,
    onBackClick: () -> Unit = {},
    onPlayStream: (streamUrl: String, title: String) -> Unit
) {
    var fullMeta by remember { mutableStateOf(mediaItem) }
    var streams by remember { mutableStateOf<List<AddonStream>>(emptyList()) }
    var loadingStreams by remember { mutableStateOf(true) }
    var selectedEpisode by remember { mutableStateOf<SeriesVideo?>(null) }

    LaunchedEffect(mediaItem.id) {
        val typeStr = if (mediaItem.type == MediaType.SERIES) "series" else "movie"
        val enriched = MediaRepository.getMeta(typeStr, mediaItem.id)
        if (enriched != null) {
            fullMeta = enriched
            if (enriched.seriesVideos.isNotEmpty()) {
                selectedEpisode = enriched.seriesVideos.firstOrNull()
            }
        }

        // Fetch streams
        val streamId = if (selectedEpisode != null) selectedEpisode!!.id else mediaItem.id
        streams = MediaRepository.getStreams(typeStr, streamId)
        loadingStreams = false
    }

    LaunchedEffect(selectedEpisode) {
        if (selectedEpisode != null) {
            loadingStreams = true
            streams = MediaRepository.getStreams("series", selectedEpisode!!.id)
            loadingStreams = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 60.dp)
        ) {
            // 1. Header with Backdrop and Gradient
            item(key = "header_backdrop") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    val backdropUrl = fullMeta.backdropUrl.ifBlank { fullMeta.posterUrl }
                    if (backdropUrl.isNotBlank()) {
                        AsyncImage(
                            model = backdropUrl,
                            contentDescription = fullMeta.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Scrim gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.5f),
                                        Color.Transparent,
                                        Color(0xFF0F172A).copy(alpha = 0.9f),
                                        Color(0xFF0F172A)
                                    )
                                )
                            )
                    )

                    // Back button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(16.dp)
                            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = Color.White
                        )
                    }
                }
            }

            // 2. Info Card (Poster + Titles + Badges)
            item(key = "media_info") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    // Poster
                    AsyncImage(
                        model = fullMeta.posterUrl,
                        contentDescription = fullMeta.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(110.dp)
                            .height(160.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = fullMeta.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = "Rating",
                                tint = Color(0xFFFFB800),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = fullMeta.rating,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = fullMeta.year,
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp
                            )
                            if (fullMeta.durationMinutes > 0) {
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "${fullMeta.durationMinutes}p",
                                    color = Color(0xFF94A3B8),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        if (fullMeta.genres.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                items(fullMeta.genres) { genre ->
                                    Surface(
                                        color = Color(0xFF1E293B),
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = genre,
                                            fontSize = 11.sp,
                                            color = Color(0xFFCBD5E1),
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Quick play button
                        Button(
                            onClick = {
                                val stream = streams.firstOrNull { it.playableUrl.isNotBlank() }
                                val url = stream?.playableUrl ?: fullMeta.streamUrl
                                if (url.isNotBlank()) {
                                    val title = if (selectedEpisode != null) "${fullMeta.title} - ${selectedEpisode!!.title}" else fullMeta.title
                                    onPlayStream(url, title)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.PlayArrow,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Xem ngay",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // 3. Synopsis
            if (fullMeta.description.isNotBlank()) {
                item(key = "synopsis") {
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Text(
                            text = "Nội dung",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = fullMeta.description,
                            fontSize = 14.sp,
                            color = Color(0xFF94A3B8),
                            lineHeight = 21.sp
                        )
                    }
                }
            }

            // 4. Series Episodes (if series)
            if (fullMeta.seriesVideos.isNotEmpty()) {
                item(key = "series_episodes_title") {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Danh sách tập phim (${fullMeta.seriesVideos.size} tập)",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item(key = "series_episodes_list") {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(fullMeta.seriesVideos) { ep ->
                            val isSelected = selectedEpisode?.id == ep.id
                            Surface(
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFF1E293B),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .clickable { selectedEpisode = ep }
                            ) {
                                Text(
                                    text = "Tập ${ep.episode}",
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else Color(0xFFCBD5E1),
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            // 5. Stream Sources list
            item(key = "streams_header") {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nguồn phát trực tuyến",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    if (loadingStreams) {
                        Spacer(modifier = Modifier.width(10.dp))
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            if (!loadingStreams && streams.isEmpty()) {
                item(key = "empty_streams") {
                    Text(
                        text = "Chưa tìm thấy nguồn phát trực tiếp. Bạn có thể cài thêm Addon Stremio ở phần Cài đặt.",
                        color = Color(0xFF94A3B8),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            } else {
                items(streams) { stream ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isFocused by interactionSource.collectIsFocusedAsState()

                    Surface(
                        color = if (isFocused) Color(0xFF334155) else Color(0xFF1E293B),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 5.dp)
                            .focusable(interactionSource = interactionSource)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.08f),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                val url = stream.playableUrl
                                if (url.isNotBlank()) {
                                    val title = if (selectedEpisode != null) "${fullMeta.title} - ${selectedEpisode!!.title}" else fullMeta.title
                                    onPlayStream(url, title)
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stream.displayTitle,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                if (!stream.description.isNullOrBlank()) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = stream.description,
                                        color = Color(0xFF94A3B8),
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
