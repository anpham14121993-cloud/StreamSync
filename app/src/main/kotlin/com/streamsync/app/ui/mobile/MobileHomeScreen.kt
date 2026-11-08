package com.streamsync.app.ui.mobile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
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
import androidx.compose.ui.graphics.Color
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
    val scrollState = rememberScrollState()

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // 1. Hero Carousel with parallax and gestures
                HeroSlider(
                    items = heroItems,
                    onItemClick = onMediaClick
                )

                Spacer(modifier = Modifier.height(18.dp))

                // 2. Continue Watching Shelf (16:9 cards)
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

                // 3. Catalog Rows with 2:3 posters
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
}
