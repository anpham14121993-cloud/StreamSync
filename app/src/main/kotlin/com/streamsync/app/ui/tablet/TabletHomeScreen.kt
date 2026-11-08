package com.streamsync.app.ui.tablet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.VideoLibrary
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
import androidx.compose.ui.graphics.Color
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
    val catalogRows by remember { MediaRepository.getCatalogRows() }.collectAsState(initial = emptyList())
    val heroItems = remember { MediaRepository.heroItems }
    val continueWatching = remember { MediaRepository.continueWatchingList }
    val scrollState = rememberScrollState()

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
                "Thư viện" to Icons.Rounded.VideoLibrary,
                "Cài đặt" to Icons.Rounded.Settings
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

        // Two-column / Wide content area
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            HeroSlider(
                items = heroItems,
                onItemClick = onMediaClick,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            ContinueWatchingShelf(
                items = continueWatching,
                onItemClick = { cw ->
                    onMediaClick(
                        MediaItem(
                            id = cw.mediaId,
                            title = cw.title,
                            backdropUrl = cw.backdropUrl
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            catalogRows.forEach { row ->
                CatalogRowSection(
                    row = row,
                    onItemClick = onMediaClick,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
