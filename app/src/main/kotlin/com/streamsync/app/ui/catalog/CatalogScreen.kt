package com.streamsync.app.ui.catalog

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.streamsync.app.data.model.CatalogRow
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.ui.components.CatalogRowSection
import com.streamsync.app.ui.components.HeroSlider
import com.streamsync.app.ui.components.HomeSkeletonHero
import com.streamsync.app.ui.components.HomeSkeletonRow
import com.streamsync.app.ui.theme.ThemeKt
import com.streamsync.app.util.StreamSyncImageLoaderKt

private const val HERO_ITEM_COUNT = 6
private const val PREFETCH_ROW_COUNT = 3

/**
 * Main Home / Catalog Screen for StreamSync.
 * Redesigned with Nuvio's fluid layout, responsive Hero Banner with physics-based
 * touch gestures, 16:9 continue watching cards, and smooth shelf scrolling.
 */
@Composable
fun CatalogScreen(
    onItemClick: (MediaItem) -> Unit,
    onContinueClick: (MediaItem) -> Unit,
    onOpenCatalog: (String) -> Unit,
    onSettingsClick: () -> Unit,
    onAddonsClick: () -> Unit,
    onRowsLoaded: (List<CatalogRow>) -> Unit = {},
) {
    val context = LocalContext.current
    val ui = ThemeKt.getLocalUiSettings().current

    val viewModel: CatalogViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                CatalogViewModel(context.applicationContext)
            }
        }
    )

    val rows by viewModel.rows.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val continueWatching by viewModel.continueWatching.collectAsStateWithLifecycle()
    val progressSnapshot by viewModel.progressSnapshot.collectAsStateWithLifecycle()
    val installedAddons by viewModel.installedAddons.collectAsStateWithLifecycle()
    val layout by viewModel.layout.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    // Prefetch posters for top rows when loaded
    LaunchedEffect(rows) {
        if (rows.isNotEmpty()) {
            onRowsLoaded(rows)
            val postersToWarm = rows.take(PREFETCH_ROW_COUNT).flatMap { it.items.take(8) }
            StreamSyncImageLoaderKt.prefetchPosters(context, postersToWarm)
        }
    }

    // Curate Hero banner items
    val heroItems = remember(rows, layout.featuredKeys) {
        val featuredFromKeys = layout.featuredKeys.mapNotNull { key ->
            rows.firstOrNull { it.key == key }?.items?.firstOrNull()
        }
        val fromRows = rows.flatMap { it.items }
        (featuredFromKeys + fromRows).distinctBy { it.id }.take(HERO_ITEM_COUNT)
    }

    // Filter and sort catalog rows according to layout settings
    val visibleRows = remember(rows, layout.order, layout.hidden) {
        val nonHidden = rows.filterNot { it.key in layout.hidden }
        if (layout.order.isEmpty()) {
            nonHidden
        } else {
            val map = nonHidden.associateBy { it.key }
            val ordered = layout.order.mapNotNull { map[it] }
            val remaining = nonHidden.filterNot { it.key in layout.order }
            ordered + remaining
        }
    }

    val showContinueWatching = layout.showContinueWatching && continueWatching.isNotEmpty()
    val isInitialLoading = loading && rows.isEmpty()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val viewportHeight = maxHeight

        Crossfade(
            targetState = isInitialLoading,
            animationSpec = tween(durationMillis = 350),
            label = "HomeLoadingCrossfade"
        ) { isLoading ->
            if (isLoading) {
                // Nuvio-style Skeleton Shimmer Loading
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false
                ) {
                    item(key = "skeleton_hero") {
                        HomeSkeletonHero(viewportHeight = viewportHeight)
                    }
                    items(3, key = { "skeleton_row_$it" }) {
                        HomeSkeletonRow()
                    }
                }
            } else if (rows.isEmpty() && continueWatching.isEmpty()) {
                // Empty State
                EmptyState(
                    message = error ?: "Không có danh mục nào để hiển thị",
                    installedAddons = installedAddons,
                    onAddonsClick = onAddonsClick
                )
            } else {
                // Content Feed with Fluid Scrolling
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    // 1. Hero Carousel Banner
                    if (heroItems.isNotEmpty()) {
                        item(key = "home_hero", contentType = "hero") {
                            HeroSlider(
                                items = heroItems,
                                onOpenDetail = onItemClick,
                                autoSlide = ui.heroAutoSlide,
                                slideIntervalMs = ui.heroSlideMs,
                                viewportHeight = viewportHeight
                            )
                        }
                    }

                    // 2. Continue Watching Shelf (Nuvio-style landscape cards)
                    if (showContinueWatching) {
                        item(key = "home_continue_watching", contentType = "continue_watching") {
                            Spacer(modifier = Modifier.height(18.dp))
                            ContinueWatchingPager(
                                items = continueWatching,
                                progressSnapshot = progressSnapshot,
                                onItemClick = onContinueClick
                            )
                        }
                    }

                    // 3. Catalog Shelves
                    items(
                        items = visibleRows,
                        key = { it.key },
                        contentType = { "catalog_row" }
                    ) { row ->
                        Spacer(modifier = Modifier.height(20.dp))
                        CatalogRowSection(
                            row = row,
                            onItemClick = onItemClick,
                            onOpenAll = { onOpenCatalog(row.key) },
                            postersPerRow = ui.postersPerRow,
                            titleSp = ui.homeTitleSp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(
    message: String,
    installedAddons: Int,
    onAddonsClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Extension,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAddonsClick,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Quản lý Addon",
                    fontWeight = FontWeight.Bold
                )
            }

            if (installedAddons > 0) {
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Đã cài $installedAddons addon nhưng chưa danh mục nào trả về phim. Kiểm tra lại manifest.json của addon.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
