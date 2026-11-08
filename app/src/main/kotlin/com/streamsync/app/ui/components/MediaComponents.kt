package com.streamsync.app.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.streamsync.app.data.model.CatalogRow
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

private const val POSTER_ASPECT_RATIO = 0.675f // 2:3 cinematic poster ratio

/**
 * Nuvio-inspired Hero Banner Carousel with native HorizontalPager swipe physics,
 * parallax depth, expanding pill indicators, and cinematic multi-stop gradients.
 */
@Composable
fun HeroSlider(
    items: List<MediaItem>,
    modifier: Modifier = Modifier,
    onItemClick: (MediaItem) -> Unit = {},
    onOpenDetail: (MediaItem) -> Unit = onItemClick,
    autoSlide: Boolean = true,
    slideIntervalMs: Int = 7000,
    viewportHeight: Dp? = null,
) {
    if (items.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { items.size }
    )
    val coroutineScope = rememberCoroutineScope()

    // Smooth auto-slide when user is not actively dragging
    if (autoSlide && items.size > 1) {
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                delay(slideIntervalMs.toLong())
                val nextPage = (pagerState.currentPage + 1) % items.size
                pagerState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(durationMillis = 650, easing = FastOutSlowInEasing)
                )
            }
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
    ) {
        val heroWidth = maxWidth
        val isTablet = heroWidth >= 600.dp

        val heroHeight = when {
            isTablet -> (heroWidth * 0.48f).coerceIn(360.dp, 440.dp)
            viewportHeight != null -> (viewportHeight * 0.72f).coerceIn(400.dp, 560.dp)
            else -> 460.dp
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heroHeight)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                key = { page -> items[page].id }
            ) { page ->
                val item = items[page]
                val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).coerceIn(-1f, 1f)

                Box(modifier = Modifier.fillMaxSize()) {
                    // Parallax Backdrop Image
                    val backdropUrl = item.backdropUrl.ifBlank { item.posterUrl }
                    AsyncImage(
                        model = backdropUrl,
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationX = pageOffset * size.width * 0.12f
                                scaleX = 1.08f
                                scaleY = 1.08f
                            },
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )

                    // Cinematic Multi-stop Gradient Vignette
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.20f),
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.60f),
                                        MaterialTheme.colorScheme.background.copy(alpha = 0.92f),
                                        MaterialTheme.colorScheme.background
                                    )
                                )
                            )
                    )

                    // Top Status Bar Scrim
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .align(Alignment.TopCenter)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.60f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    // Foreground Media Info & Actions
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(
                                horizontal = if (isTablet) 36.dp else 20.dp,
                                vertical = 24.dp
                            )
                            .graphicsLayer {
                                translationX = pageOffset * size.width * 0.22f
                                alpha = (1f - abs(pageOffset) * 1.4f).coerceIn(0f, 1f)
                            },
                        horizontalAlignment = if (isTablet) Alignment.Start else Alignment.CenterHorizontally
                    ) {
                        HeroContentInfo(
                            item = item,
                            isTablet = isTablet,
                            onOpenDetail = onOpenDetail
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // "Xem chi tiết" / "Phát ngay" Action Button Pill
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(40.dp))
                                .clickable { onOpenDetail(item) },
                            color = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(40.dp),
                            shadowElevation = 6.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 11.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    text = "Xem chi tiết",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.3.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            // Interactive Capsule Indicators at Bottom Center
            if (items.size > 1) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.indices.forEach { index ->
                        val isSelected = pagerState.currentPage == index
                        val widthAnim by animateDpAsState(
                            targetValue = if (isSelected) 24.dp else 7.dp,
                            animationSpec = tween(250),
                            label = "indicator_width"
                        )
                        val alphaAnim by animateFloatAsState(
                            targetValue = if (isSelected) 1f else 0.4f,
                            animationSpec = tween(250),
                            label = "indicator_alpha"
                        )

                        Box(
                            modifier = Modifier
                                .width(widthAnim)
                                .height(7.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = alphaAnim))
                                .clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroContentInfo(
    item: MediaItem,
    isTablet: Boolean,
    onOpenDetail: (MediaItem) -> Unit,
) {
    var logoLoadError by remember(item.id, item.logoUrl) { mutableStateOf(false) }
    val logoUrl = item.logoUrl.takeIf { it.isNotBlank() }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isTablet) Alignment.Start else Alignment.CenterHorizontally
    ) {
        if (logoUrl != null && !logoLoadError) {
            AsyncImage(
                model = logoUrl,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth(if (isTablet) 0.55f else 0.70f)
                    .height(64.dp)
                    .clickable { onOpenDetail(item) },
                contentScale = ContentScale.Fit,
                alignment = if (isTablet) Alignment.CenterStart else Alignment.Center,
                onError = { logoLoadError = true }
            )
        } else {
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenDetail(item) },
                style = if (isTablet) MaterialTheme.typography.headlineLarge else MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Black,
                textAlign = if (isTablet) TextAlign.Start else TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Metadata badges row (Type • Rating • Genre • Year)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (isTablet) Arrangement.spacedBy(8.dp, Alignment.Start) else Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Media Type Tag
            val typeText = when (item.type) {
                MediaType.SERIES -> "PHIM BỘ"
                MediaType.MOVIE -> "PHIM LẺ"
            }
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.75f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = typeText,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Rating Badge
            val rating = item.rating.takeIf { it.isNotBlank() }
            if (rating != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFF59E0B).copy(alpha = 0.25f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = Color(0xFFFBBF24),
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = rating,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFBBF24)
                    )
                }
            }

            // First Genre
            val firstGenre = item.genres.firstOrNull()?.takeIf { it.isNotBlank() }
            if (firstGenre != null) {
                HeroDot()
                Text(
                    text = firstGenre,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.85f),
                    fontWeight = FontWeight.Medium
                )
            }

            // Year / Release Info
            val releaseYear = item.year.takeIf { it.isNotBlank() }
            if (releaseYear != null) {
                HeroDot()
                Text(
                    text = releaseYear,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.85f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun HeroDot() {
    Box(
        modifier = Modifier
            .size(3.5.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.6f))
    )
}

/**
 * Modern Poster Card matching Nuvio's 2:3 cinematic poster styling,
 * smooth image loading, subtle rating badge, and ripple feedback.
 */
@Composable
fun PosterCard(
    item: MediaItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Int = 124,
    showMeta: Boolean = true,
) {
    val cardWidth = width.dp
    val posterHeight = cardWidth / POSTER_ASPECT_RATIO

    Column(
        modifier = modifier
            .width(cardWidth)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .width(cardWidth)
                .height(posterHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            AsyncImage(
                model = item.posterUrl,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Rating Pill overlay on poster
            val rating = item.rating.takeIf { it.isNotBlank() }
            if (rating != null) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp),
                    color = Color.Black.copy(alpha = 0.70f),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = Color(0xFFFBBF24),
                            modifier = Modifier.size(11.dp)
                        )
                        Text(
                            text = rating,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        if (showMeta) {
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            val meta = item.year.takeIf { it.isNotBlank() } ?: item.genres.firstOrNull()
            if (meta != null) {
                Text(
                    text = meta,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Sleek Section Header with bold title and Nuvio-style circular/pill "Xem tất cả" button.
 */
@Composable
fun RowHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showMore: Boolean = false,
    onOpenAll: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 19.sp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        if (showMore || onOpenAll != null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                    .clickable(enabled = onOpenAll != null, onClick = { onOpenAll?.invoke() }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = "Xem tất cả",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

/**
 * Full Catalog Row Section with responsive header and horizontal LazyRow.
 */
@Composable
fun CatalogRowSection(
    row: CatalogRow,
    onItemClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier,
    onOpenAll: () -> Unit = {},
    postersPerRow: Int = 8,
    titleSp: Int = 18,
) {
    if (row.items.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowHeader(
            title = row.title,
            subtitle = row.addonName.takeIf { it.isNotBlank() && it != row.title },
            onOpenAll = onOpenAll
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = row.items,
                key = { item -> "${row.id}_${item.id}" }
            ) { item ->
                PosterCard(
                    item = item,
                    onClick = { onItemClick(item) },
                    width = 124
                )
            }
        }
    }
}
