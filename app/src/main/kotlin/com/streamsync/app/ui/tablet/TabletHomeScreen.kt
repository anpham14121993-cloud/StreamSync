package com.streamsync.app.ui.tablet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.repository.MediaRepository
import com.streamsync.app.ui.components.CatalogRowSection
import com.streamsync.app.ui.components.ContinueWatchingShelf
import com.streamsync.app.ui.components.HeroSlider

@Composable
fun TabletHomeScreen(
    onMediaClick: (MediaItem) -> Unit = {}
) {
    var selectedNavIndex by remember { mutableIntStateOf(0) }
    val catalogRows by MediaRepository.catalogRowsState.collectAsState(initial = emptyList())
    val heroItems by MediaRepository.heroItemsState.collectAsState(initial = emptyList())
    val continueWatching by MediaRepository.continueWatching.collectAsState(initial = emptyList())

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Adaptive Navigation Rail on Left Edge
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            val items = listOf(
                "Trang chủ" to Icons.Rounded.Home,
                "Khám phá" to Icons.Rounded.Explore,
                "Tìm kiếm" to Icons.Rounded.Search,
                "Addon" to Icons.Rounded.Extension
            )
            items.forEachIndexed { index, (label, icon) ->
                NavigationRailItem(
                    selected = selectedNavIndex == index,
                    onClick = { selectedNavIndex = index },
                    icon = { Icon(icon, contentDescription = label) },
                    label = { Text(label) },
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        // Two-column / Wide content area with LazyColumn
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item(key = "tablet_hero") {
                HeroSlider(
                    items = heroItems,
                    onItemClick = onMediaClick,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (continueWatching.isNotEmpty()) {
                item(key = "tablet_cw") {
                    Spacer(modifier = Modifier.height(12.dp))
                    ContinueWatchingShelf(
                        items = continueWatching,
                        onItemClick = { cw ->
                            onMediaClick(
                                MediaItem(
                                    id = cw.mediaId,
                                    title = cw.title,
                                    backdropUrl = cw.backdropUrl,
                                    streamUrl = cw.streamUrl
                                )
                            )
                        }
                    )
                }
            }

            items(
                items = catalogRows,
                key = { it.id }
            ) { row ->
                Spacer(modifier = Modifier.height(16.dp))
                CatalogRowSection(
                    row = row,
                    onItemClick = onMediaClick,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }
    }
}
