package com.streamsync.app.ui.mobile

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.repository.MediaRepository
import com.streamsync.app.ui.components.CatalogRowSection
import com.streamsync.app.ui.components.ContinueWatchingShelf
import com.streamsync.app.ui.components.HeroSlider
import com.streamsync.app.ui.components.PosterCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MobileHomeScreen(
    onMediaClick: (MediaItem) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val catalogRows by MediaRepository.catalogRowsState.collectAsState(initial = emptyList())
    val heroItems by MediaRepository.heroItemsState.collectAsState(initial = emptyList())
    val continueWatching by MediaRepository.continueWatching.collectAsState(initial = emptyList())
    val installedAddons by MediaRepository.installedAddons.collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0F172A).copy(alpha = 0.98f),
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                val navItems = listOf(
                    "Trang chủ" to Icons.Rounded.Home,
                    "Khám phá" to Icons.Rounded.Explore,
                    "Tìm kiếm" to Icons.Rounded.Search,
                    "Addon" to Icons.Rounded.Extension
                )
                navItems.forEachIndexed { index, (label, icon) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label, fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
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
            when (selectedTab) {
                0 -> {
                    // TAB 1: Trang chủ Nuvio fluid style
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        // 1. Hero Carousel
                        item(key = "hero_carousel") {
                            HeroSlider(
                                items = heroItems,
                                onItemClick = onMediaClick
                            )
                        }

                        // 2. Continue Watching Shelf (16:9 cards)
                        if (continueWatching.isNotEmpty()) {
                            item(key = "continue_watching") {
                                Spacer(modifier = Modifier.height(18.dp))
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

                        // 3. Catalog Shelves (2:3 Posters)
                        items(
                            items = catalogRows,
                            key = { it.id }
                        ) { row ->
                            Spacer(modifier = Modifier.height(18.dp))
                            CatalogRowSection(
                                row = row,
                                onItemClick = onMediaClick
                            )
                        }
                    }
                }

                1 -> {
                    // TAB 2: Khám phá
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        item {
                            Text(
                                text = "Khám Phá Danh Mục",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        items(catalogRows, key = { "explore_${it.id}" }) { row ->
                            CatalogRowSection(
                                row = row,
                                onItemClick = onMediaClick
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }

                2 -> {
                    // TAB 3: Tìm kiếm
                    var query by remember { mutableStateOf("") }
                    var searchResults by remember { mutableStateOf<List<MediaItem>>(emptyList()) }
                    var isSearching by remember { mutableStateOf(false) }

                    LaunchedEffect(query) {
                        if (query.length >= 2) {
                            isSearching = true
                            delay(400) // Debounce
                            searchResults = MediaRepository.search(query)
                            isSearching = false
                        } else {
                            searchResults = emptyList()
                            isSearching = false
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Tìm Kiếm Phim & Phim Bộ",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            placeholder = { Text("Nhập tên phim, diễn viên...", color = Color(0xFF64748B)) },
                            leadingIcon = {
                                Icon(Icons.Rounded.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            },
                            trailingIcon = {
                                if (query.isNotBlank()) {
                                    IconButton(onClick = { query = "" }) {
                                        Icon(Icons.Rounded.Clear, contentDescription = "Xóa", tint = Color.White)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFF1E293B),
                                unfocusedContainerColor = Color(0xFF1E293B),
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color(0xFF334155)
                            ),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (isSearching) {
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                            }
                        } else if (searchResults.isNotEmpty()) {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(searchResults) { item ->
                                    PosterCard(
                                        item = item,
                                        onClick = { onMediaClick(item) },
                                        width = 110
                                    )
                                }
                            }
                        } else if (query.length >= 2) {
                            Text(
                                text = "Không tìm thấy kết quả nào cho '$query'",
                                color = Color(0xFF94A3B8),
                                modifier = Modifier.padding(top = 24.dp)
                            )
                        } else {
                            Text(
                                text = "Gợi ý: Tìm thử 'Avatar', 'Dune', 'Batman', 'Breaking Bad'...",
                                color = Color(0xFF64748B),
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }

                3 -> {
                    // TAB 4: Quản lý Addon Stremio
                    var addonUrlInput by remember { mutableStateOf("") }
                    var installStatus by remember { mutableStateOf<String?>(null) }
                    var isInstalling by remember { mutableStateOf(false) }
                    val scope = rememberCoroutineScope()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .padding(16.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        item {
                            Text(
                                text = "Quản Lý Addon Stremio",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Cài đặt Addon Stremio để mở rộng danh mục và nguồn phim chất lượng cao.",
                                fontSize = 13.sp,
                                color = Color(0xFF94A3B8)
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = addonUrlInput,
                                onValueChange = { addonUrlInput = it },
                                placeholder = { Text("Dán link manifest.json (vd: https://torrentio...)", color = Color(0xFF64748B), fontSize = 13.sp) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color(0xFF1E293B),
                                    unfocusedContainerColor = Color(0xFF1E293B),
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = Color(0xFF334155)
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    if (addonUrlInput.isNotBlank()) {
                                        isInstalling = true
                                        installStatus = null
                                        scope.launch {
                                            val ok = MediaRepository.installAddon(addonUrlInput.trim())
                                            isInstalling = false
                                            if (ok) {
                                                installStatus = "Cài đặt Addon thành công!"
                                                addonUrlInput = ""
                                            } else {
                                                installStatus = "Lỗi: Không thể kết nối hoặc đọc manifest.json"
                                            }
                                        }
                                    }
                                },
                                enabled = !isInstalling && addonUrlInput.isNotBlank(),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (isInstalling) {
                                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                } else {
                                    Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text("Cài đặt Addon mới", fontWeight = FontWeight.Bold, color = Color.White)
                            }

                            if (installStatus != null) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = installStatus!!,
                                    color = if (installStatus!!.startsWith("Cài đặt")) Color(0xFF22C55E) else Color(0xFFEF4444),
                                    fontSize = 13.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "Addon Đã Cài Đặt (${installedAddons.size})",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        items(installedAddons, key = { it.id }) { addon ->
                            Surface(
                                color = Color(0xFF1E293B),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.CheckCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "${addon.name} (v${addon.version})",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        )
                                        if (!addon.description.isNullOrBlank()) {
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = addon.description,
                                                color = Color(0xFF94A3B8),
                                                fontSize = 12.sp,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }

                                    if (addon.id != "community.cinemeta" && addon.id != "cinemeta") {
                                        IconButton(
                                            onClick = { MediaRepository.uninstallAddon(addon.id) }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.Delete,
                                                contentDescription = "Gỡ cài đặt",
                                                tint = Color(0xFFEF4444)
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
    }
}
