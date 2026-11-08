package com.streamsync.app.ui.mobile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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
fun MobileHomeScreen(
    onMediaClick: (MediaItem) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val catalogRows by remember { MediaRepository.getCatalogRows() }.collectAsState(initial = emptyList())
    val heroItems = remember { MediaRepository.heroItems }
    val continueWatching = remember { MediaRepository.continueWatchingList }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                contentColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            ) {
                val navItems = listOf(
                    "Trang chủ" to Icons.Rounded.Home,
                    "Khám phá" to Icons.Rounded.Explore,
                    "Tìm kiếm" to Icons.Rounded.Search,
                    "Thư viện" to Icons.Rounded.VideoLibrary
                )
                navItems.forEachIndexed { index, (label, icon) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        )
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding() + 24.dp)
        ) {
            // 1. Hero Carousel with native smooth gestures
            item(key = "hero_carousel") {
                HeroSlider(
                    items = heroItems,
                    onItemClick = onMediaClick
                )
            }

            // 2. Continue Watching Shelf (16:9 cards)
            item(key = "continue_watching") {
                Spacer(modifier = Modifier.height(18.dp))
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
            }

            // 3. Catalog Rows with 2:3 posters
            items(
                items = catalogRows,
                key = { it.id }
            ) { row ->
                Spacer(modifier = Modifier.height(16.dp))
                CatalogRowSection(
                    row = row,
                    onItemClick = onMediaClick
                )
            }
        }
    }
}
