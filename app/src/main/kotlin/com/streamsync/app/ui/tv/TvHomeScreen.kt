package com.streamsync.app.ui.tv

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.repository.MediaRepository
import com.streamsync.app.ui.theme.AccentYellow
import com.streamsync.app.ui.theme.DarkBackground
import com.streamsync.app.ui.theme.FocusBorderColor
import com.streamsync.app.ui.theme.PrimaryRed

/**
 * 10-foot TV Experience with safe margins (58dp overscan padding),
 * D-Pad focus indicators, larger fonts, and horizontal landscape focus.
 */
@Composable
fun TvHomeScreen(
    onMediaClick: (MediaItem) -> Unit = {}
) {
    val catalogRows by MediaRepository.catalogRowsState.collectAsState(initial = emptyList())
    val heroItems by MediaRepository.heroItemsState.collectAsState(initial = emptyList())
    val heroItem = heroItems.firstOrNull() ?: MediaItem(id = "default", title = "StreamSync")
    var selectedTopNav by remember { mutableIntStateOf(0) }
    val topNavCategories = remember { listOf("Trang chủ", "Phim lẻ", "Phim bộ", "Thịnh hành", "Cài đặt") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Safe overscan margins for TV: 58dp horizontal, 36dp vertical
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 58.dp, vertical = 36.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. Top TV Category Bar (Focusable with D-Pad)
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp)
                ) {
                    Text(
                        text = "StreamSync",
                        color = PrimaryRed,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(end = 24.dp)
                    )

                    topNavCategories.forEachIndexed { index, title ->
                        TvNavPill(
                            title = title,
                            isSelected = selectedTopNav == index,
                            onSelect = { selectedTopNav = index }
                        )
                    }
                }
            }

            // 2. Large TV Billboard Hero
            item {
                TvHeroBillboard(
                    item = heroItem,
                    onPlayClick = { onMediaClick(heroItem) },
                    modifier = Modifier.padding(bottom = 36.dp)
                )
            }

            // 3. Catalog Rows with TV Focus
            itemsIndexed(catalogRows) { _, row ->
                TvCatalogRowSection(
                    row = row,
                    onItemClick = onMediaClick,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    }
}

@Composable
fun TvNavPill(
    title: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Surface(
        color = when {
            isFocused -> Color.White
            isSelected -> Color.White.copy(alpha = 0.15f)
            else -> Color.Transparent
        },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = if (isFocused) 2.dp else 1.dp,
            color = if (isFocused) FocusBorderColor else Color.White.copy(alpha = 0.12f)
        ),
        modifier = Modifier
            .focusable(interactionSource = interactionSource)
            .clickable(interactionSource = interactionSource, indication = null, onClick = onSelect)
    ) {
        Text(
            text = title,
            color = if (isFocused) Color.Black else Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun TvHeroBillboard(
    item: MediaItem,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val playInteractionSource = remember { MutableInteractionSource() }
    val isPlayFocused by playInteractionSource.collectIsFocusedAsState()

    val playScale by animateFloatAsState(
        targetValue = if (isPlayFocused) 1.1f else 1.0f,
        animationSpec = tween(120),
        label = "tv_play_scale"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .clip(RoundedCornerShape(20.dp)),
        color = Color(0xFF131B2A),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.backdropUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient vignette
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.95f),
                                Color.Black.copy(alpha = 0.70f),
                                Color.Transparent
                            ),
                            endX = 900f
                        )
                    )
            )

            // Info Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = AccentYellow,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${item.rating} • ${item.year} • ${item.genres.joinToString()}",
                        color = Color(0xFFE2E8F0),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = item.description,
                    color = Color(0xFFCBD5E1),
                    fontSize = 15.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(520.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Play Button with Focus glow
                Surface(
                    color = if (isPlayFocused) Color.White else PrimaryRed,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        width = if (isPlayFocused) 3.dp else 0.dp,
                        color = FocusBorderColor
                    ),
                    modifier = Modifier
                        .scale(playScale)
                        .focusable(interactionSource = playInteractionSource)
                        .clickable(interactionSource = playInteractionSource, indication = null, onClick = onPlayClick)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = if (isPlayFocused) Color.Black else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Xem Ngay",
                            color = if (isPlayFocused) Color.Black else Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
