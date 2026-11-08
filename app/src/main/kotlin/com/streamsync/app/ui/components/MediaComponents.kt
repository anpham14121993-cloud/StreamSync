package com.streamsync.app.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

// Constants tuned for smooth 60/120fps gesture physics & Nuvio-style visual depth
private const val HERO_BACKGROUND_PARALLAX = 0.055f
private const val HERO_BACKGROUND_SCALE = 1.14f
private const val HERO_CONTENT_PARALLAX = 0.18f
private const val HERO_SWIPE_THRESHOLD_FRACTION = 0.16f
private const val HERO_SWIPE_VELOCITY_THRESHOLD = 300f
private const val DEFAULT_HERO_AUTO_SLIDE_INTERVAL_MS = 8_000L
private const val POSTER_ASPECT_RATIO = 0.675f // 2:3 cinematic poster ratio

/**
 * Nuvio-inspired Hero Banner Carousel with smooth swipe gestures, velocity fling,
 * horizontal/vertical drag disambiguation, parallax depth, expanding pill indicators,
 * and cinematic multi-stop gradients.
 */
@Composable
fun HeroSlider(
    items: List<MediaItem>,
    modifier: Modifier = Modifier,
    onItemClick: (MediaItem) -> Unit = {},
    onOpenDetail: (MediaItem) -> Unit = onItemClick,
    autoSlide: Boolean = true,
    slideIntervalMs: Int = 8000,
    viewportHeight: Dp? = null,
) {
    if (items.isEmpty()) return

    val actualItemCount = items.size
    val pagerState = key(actualItemCount) {
        rememberPagerState(
            initialPage = if (actualItemCount > 1) {
                (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % actualItemCount)
            } else {
                0
            },
            pageCount = { if (actualItemCount > 1) Int.MAX_VALUE else 1 }
        )
    }

    val coroutineScope = rememberCoroutineScope()
    var isUserInteracting by remember { mutableStateOf(false) }

    // Auto-scroll loop with safety checks
    if (autoSlide && actualItemCount > 1) {
        val settledPage = pagerState.settledPage
        LaunchedEffect(settledPage, isUserInteracting) {
            if (isUserInteracting) return@LaunchedEffect
            val interval = if (slideIntervalMs > 0) slideIntervalMs.toLong() else DEFAULT_HERO_AUTO_SLIDE_INTERVAL_MS
            delay(interval)
            while (pagerState.isScrollInProgress || isUserInteracting) {
                delay(150L)
            }
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(durationMillis = 650, easing = FastOutSlowInEasing)
            )
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .homeHeroPagerGesture(
                pagerState = pagerState,
                itemCount = actualItemCount,
                coroutineScope = coroutineScope,
                onInteractionChanged = { isUserInteracting = it }
            )
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
    ) {
        val heroWidth = maxWidth
        val heroWidthPx = with(LocalDensity.current) { heroWidth.toPx() }
        val isTablet = heroWidth >= 600.dp

        // Dynamic responsive hero height
        val heroHeight = when {
            isTablet -> (heroWidth * 0.48f).coerceIn(360.dp, 440.dp)
            viewportHeight != null -> (viewportHeight * 0.72f).coerceIn(400.dp, 560.dp)
            else -> 460.dp
        }

        val currentPage = pagerState.currentPage
        val currentOffsetFraction = pagerState.currentPageOffsetFraction

        // Layer computation for silky smooth crossfading and parallax
        val visiblePages = listOf(
            currentPage,
            (currentPage - 1).coerceAtLeast(0),
            (currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
        ).distinct().mapNotNull { index ->
            val pageOffset = (currentPage - index) + currentOffsetFraction
            val visibility = (1f - abs(pageOffset)).coerceIn(0f, 1f)
            if (visibility <= 0f) null
            else HeroPageLayer(index % actualItemCount, visibility, pageOffset)
        }.sortedBy { it.visibility }

        val activeLayer = visiblePages.lastOrNull()
        val currentItem = activeLayer?.let { items[it.itemIndex] } ?: items[currentPage % actualItemCount]

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(heroHeight)
        ) {
            // Invisible Pager for position sync
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.001f }
            ) {
                Box(modifier = Modifier.fillMaxSize())
            }

            // Layered Backdrop with Parallax
            visiblePages.forEach { layer ->
                val item = items[layer.itemIndex]
                val backdropUrl = item.backgroundUrl ?: item.posterUrl
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = layer.visibility
                            translationX = -layer.offset * heroWidthPx * HERO_BACKGROUND_PARALLAX
                            scaleX = HERO_BACKGROUND_SCALE
                            scaleY = HERO_BACKGROUND_SCALE
                        }
                ) {
                    AsyncImage(
                        model = backdropUrl,
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                }
            }

            // Cinematic Gradient Overlays (Multi-stop smooth blending)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background.copy(alpha = 0.15f),
                                MaterialTheme.colorScheme.background.copy(alpha = 0.45f),
                                MaterialTheme.colorScheme.background.copy(alpha = 0.88f),
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )

            // Top vignette for status bar readibility
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.55f),
                                Color.Transparent
                            )
                        )
                    )
            )

            // Content Block with subtle parallax & bold typography
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        horizontal = if (isTablet) 36.dp else 20.dp,
                        vertical = 20.dp
                    ),
                horizontalAlignment = if (isTablet) Alignment.Start else Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.65f else 1f)
                        .widthIn(max = 560.dp),
                    contentAlignment = if (isTablet) Alignment.BottomStart else Alignment.BottomCenter
                ) {
                    visiblePages.forEach { layer ->
                        val item = items[layer.itemIndex]
                        Box(
                            modifier = Modifier.graphicsLayer {
                                alpha = layer.visibility
                                translationX = -layer.offset * heroWidthPx * HERO_CONTENT_PARALLAX
                            }
                        ) {
                            HeroContentInfo(
                                item = item,
                                isTablet = isTablet,
                                onOpenDetail = onOpenDetail
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // "Xem chi tiết" / "Phát ngay" Action Button Pill
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .clickable { onOpenDetail(currentItem) },
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

                // Interactive Capsule Indicators
                if (actualItemCount > 1) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items.forEachIndexed { index, _ ->
                            val activeFraction = visiblePages
                                .firstOrNull { it.itemIndex == index }
                                ?.visibility ?: 0f

                            val isCurrent = (currentPage % actualItemCount) == index
                            val targetWidth = if (isCurrent) 24.dp else 7.dp

                            Box(
                                modifier = Modifier
                                    .clickable {
                                        coroutineScope.launch {
                                            val target = heroPageForItem(pagerState.currentPage, index, actualItemCount)
                                            pagerState.animateScrollToPage(target)
                                        }
                                    }
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .graphicsLayer {
                                        alpha = 0.30f + (0.70f * activeFraction)
                                    }
                                    .width(7.dp + (17.dp * activeFraction))
                                    .height(7.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class HeroPageLayer(
    val itemIndex: Int,
    val visibility: Float,
    val offset: Float,
)

private fun heroPageForItem(currentPage: Int, itemIndex: Int, itemCount: Int): Int {
    val page = currentPage.toLong() - (currentPage % itemCount) + itemIndex
    return listOf(page - itemCount, page, page + itemCount)
        .filter { it in 0L until Int.MAX_VALUE.toLong() }
        .minByOrNull { abs(it - currentPage) }
        ?.toInt() ?: currentPage
}

@Composable
private fun HeroContentInfo(
    item: MediaItem,
    isTablet: Boolean,
    onOpenDetail: (MediaItem) -> Unit,
) {
    var logoLoadError by remember(item.id, item.logoUrl) { mutableStateOf(false) }
    val logoUrl = item.logoUrl?.takeIf { it.isNotBlank() }

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
                else -> item.type.name
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
 * Nuvio-style gesture handler for HeroSlider.
 * Gracefully lets vertical scroll events through to the outer LazyColumn without lag,
 * while catching fast horizontal swipes and smooth drags with velocity tracking.
 */
private fun Modifier.homeHeroPagerGesture(
    pagerState: PagerState,
    itemCount: Int,
    coroutineScope: CoroutineScope,
    onInteractionChanged: (Boolean) -> Unit,
): Modifier {
    if (itemCount <= 1) return this

    return pointerInput(pagerState, itemCount) {
        awaitEachGesture {
            val down = awaitFirstDown(pass = PointerEventPass.Initial)
            val widthPx = size.width.toFloat().takeIf { it > 0f } ?: return@awaitEachGesture
            val velocityTracker = VelocityTracker().apply {
                addPosition(down.uptimeMillis, down.position)
            }
            val startPage = pagerState.currentPage
            var totalDx = 0f
            var totalDy = 0f
            var dragging = false

            while (true) {
                val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                val change = event.changes.firstOrNull { it.id == down.id } ?: break
                velocityTracker.addPosition(change.uptimeMillis, change.position)

                if (!change.pressed) {
                    if (dragging) {
                        onInteractionChanged(false)
                        val velocityX = velocityTracker.calculateVelocity().x
                        val thresholdPassed = abs(totalDx) > widthPx * HERO_SWIPE_THRESHOLD_FRACTION ||
                            abs(velocityX) > HERO_SWIPE_VELOCITY_THRESHOLD

                        val targetPage = if (thresholdPassed) {
                            if (totalDx > 0f) (startPage - 1).coerceAtLeast(0)
                            else (startPage + 1).coerceAtMost(pagerState.pageCount - 1)
                        } else {
                            startPage
                        }

                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = targetPage,
                                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
                            )
                        }
                    }
                    break
                }

                val delta = change.position - change.previousPosition
                totalDx += delta.x
                totalDy += delta.y

                if (!dragging) {
                    val horizontalDrag = abs(totalDx) > viewConfiguration.touchSlop && abs(totalDx) > abs(totalDy)
                    val verticalDrag = abs(totalDy) > viewConfiguration.touchSlop && abs(totalDy) > abs(totalDx)

                    when {
                        verticalDrag -> {
                            // Immediately yield to vertical scroll in LazyColumn!
                            break
                        }
                        horizontalDrag -> {
                            dragging = true
                            onInteractionChanged(true)
                        }
                        else -> continue
                    }
                }

                // 1:1 real-time finger dragging
                pagerState.dispatchRawDelta(-delta.x)
                change.consume()
            }
        }
    }
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

/**
 * Generic Poster Row for catalogs or collections.
 */
@Composable
fun PosterRow(
    title: String,
    items: List<MediaItem>,
    onItemClick: (MediaItem) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onOpenAll: (() -> Unit)? = null,
) {
    if (items.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowHeader(
            title = title,
            subtitle = subtitle,
            onOpenAll = onOpenAll
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = items,
                key = { item -> "row_${title}_${item.id}" }
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

/**
 * Skeleton shimmer loading block matching Nuvio's loading aesthetic.
 */
@Composable
fun HomeSkeletonHero(
    modifier: Modifier = Modifier,
    viewportHeight: Dp? = null,
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnim"
    )

    val shimmerColors = listOf(
        Color(0xFF262626),
        Color(0xFF383838),
        Color(0xFF262626)
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    val height = viewportHeight?.let { (it * 0.72f).coerceIn(400.dp, 560.dp) } ?: 460.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
            .background(brush)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.15f))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(14.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White.copy(alpha = 0.12f))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(42.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color.White.copy(alpha = 0.20f))
            )
        }
    }
}

/**
 * Skeleton shimmer row for catalog shelves during loading.
 */
@Composable
fun HomeSkeletonRow(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(130.dp)
                .height(20.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(5) {
                Box(
                    modifier = Modifier
                        .width(124.dp)
                        .height(184.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                )
            }
        }
    }
}
