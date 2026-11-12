package com.streamsync.app.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.streamsync.app.data.addon.AddonClient
import com.streamsync.app.data.addon.AddonManifest
import com.streamsync.app.data.addon.AddonStream
import com.streamsync.app.data.model.CatalogRow
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import com.streamsync.app.data.model.WatchProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

object MediaRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val gson = Gson()
    private var prefs: SharedPreferences? = null

    private const val CINEMETA_URL = "https://v3-cinemeta.strem.io"
    private const val PREFS_NAME = "streamsync_prefs"
    private const val KEY_ADDONS = "installed_addons"
    private const val KEY_PROGRESS = "watch_progress"

    private val _installedAddons = MutableStateFlow<List<AddonManifest>>(emptyList())
    val installedAddons: Flow<List<AddonManifest>> = _installedAddons.asStateFlow()

    private val _continueWatching = MutableStateFlow<List<WatchProgress>>(emptyList())
    val continueWatching: Flow<List<WatchProgress>> = _continueWatching.asStateFlow()

    private val _catalogRowsState = MutableStateFlow<List<CatalogRow>>(emptyList())
    val catalogRowsState: Flow<List<CatalogRow>> = _catalogRowsState.asStateFlow()

    private val _heroItemsState = MutableStateFlow<List<MediaItem>>(emptyList())
    val heroItemsState: Flow<List<MediaItem>> = _heroItemsState.asStateFlow()

    fun init(context: Context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            loadInstalledAddons()
            loadProgress()
            refreshCatalogs()
        }
    }

    private fun loadInstalledAddons() {
        val json = prefs?.getString(KEY_ADDONS, null)
        val list: List<AddonManifest> = if (!json.isNullOrBlank()) {
            try {
                val type = object : TypeToken<List<AddonManifest>>() {}.type
                gson.fromJson(json, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }

        val defaultList = if (list.none { it.id == "community.cinemeta" || it.id == "cinemeta" }) {
            val cinemeta = AddonManifest(
                id = "community.cinemeta",
                name = "Cinemeta Official",
                version = "3.0.12",
                description = "Danh mục phim điện ảnh và truyền hình chính thức từ Stremio / IMDb",
                types = listOf("movie", "series"),
                baseUrl = CINEMETA_URL
            )
            listOf(cinemeta) + list
        } else {
            list
        }

        _installedAddons.value = defaultList
    }

    private fun loadProgress() {
        val json = prefs?.getString(KEY_PROGRESS, null)
        if (!json.isNullOrBlank()) {
            try {
                val type = object : TypeToken<List<WatchProgress>>() {}.type
                val list: List<WatchProgress> = gson.fromJson(json, type) ?: emptyList()
                _continueWatching.value = list
            } catch (e: Exception) {
                _continueWatching.value = emptyList()
            }
        }
    }

    fun saveProgress(progress: WatchProgress) {
        val current = _continueWatching.value.toMutableList()
        current.removeAll { it.mediaId == progress.mediaId }
        current.add(0, progress)
        val trimmed = current.take(20)
        _continueWatching.value = trimmed
        scope.launch {
            prefs?.edit()?.putString(KEY_PROGRESS, gson.toJson(trimmed))?.apply()
        }
    }

    fun refreshCatalogs() {
        scope.launch {
            val rows = mutableListOf<CatalogRow>()
            val addons = _installedAddons.value

            for (addon in addons) {
                val base = addon.baseUrl.ifBlank { CINEMETA_URL }
                // 1. Phim Điện Ảnh Phổ Biến
                val topMovies = AddonClient.fetchCatalog(base, "movie", "top")
                if (topMovies.isNotEmpty()) {
                    rows.add(
                        CatalogRow(
                            id = "${addon.id}_movie_top",
                            title = "Phim Điện Ảnh Phổ Biến",
                            items = topMovies,
                            addonName = addon.name
                        )
                    )
                }

                // 2. Phim Bộ Thịnh Hành
                val topSeries = AddonClient.fetchCatalog(base, "series", "top")
                if (topSeries.isNotEmpty()) {
                    rows.add(
                        CatalogRow(
                            id = "${addon.id}_series_top",
                            title = "Phim Bộ Thịnh Hành",
                            items = topSeries,
                            addonName = addon.name
                        )
                    )
                }

                // 3. Phim Mới Phát Hành
                val newMovies = AddonClient.fetchCatalog(base, "movie", "year")
                if (newMovies.isNotEmpty()) {
                    rows.add(
                        CatalogRow(
                            id = "${addon.id}_movie_year",
                            title = "Phim Mới Chiếu Rạp",
                            items = newMovies,
                            addonName = addon.name
                        )
                    )
                }
            }

            // Fallback offline curated items if network fails
            if (rows.isEmpty()) {
                val fallbackItems = getFallbackItems()
                rows.add(
                    CatalogRow(
                        id = "fallback_popular",
                        title = "Phim Đề Xuất",
                        items = fallbackItems,
                        addonName = "StreamSync"
                    )
                )
            }

            _catalogRowsState.value = rows

            // Curate Hero items from first available rows
            val allItems = rows.flatMap { it.items }
            val heroes = allItems.distinctBy { it.id }.take(6)
            _heroItemsState.value = if (heroes.isNotEmpty()) heroes else getFallbackItems().take(4)
        }
    }

    fun getCatalogRows(): Flow<List<CatalogRow>> = _catalogRowsState.asStateFlow()

    fun getHeroItems(): Flow<List<MediaItem>> = _heroItemsState.asStateFlow()

    suspend fun getMeta(type: String, id: String): MediaItem? {
        val addons = _installedAddons.value
        val isImdbId = id.startsWith("tt")

        // Prioritize non-Cinemeta addons first if ID is not an IMDb tt... format
        val orderedAddons = if (!isImdbId) {
            addons.filter { it.baseUrl != CINEMETA_URL } + addons.filter { it.baseUrl == CINEMETA_URL }
        } else {
            addons
        }

        var foundMeta: MediaItem? = null
        for (addon in orderedAddons) {
            val base = addon.baseUrl.ifBlank { CINEMETA_URL }
            val res = AddonClient.fetchMeta(base, type, id)
            if (res != null) {
                if (type != "series" || res.seriesVideos.isNotEmpty()) {
                    return res
                }
                if (foundMeta == null) {
                    foundMeta = res
                }
            }
        }

        // If series found but videos list is empty, enrich from Cinemeta
        if (foundMeta != null && type == "series" && foundMeta.seriesVideos.isEmpty()) {
            val cinemetaRes = AddonClient.fetchMeta(CINEMETA_URL, type, id)
            if (cinemetaRes != null && cinemetaRes.seriesVideos.isNotEmpty()) {
                return foundMeta.copy(seriesVideos = cinemetaRes.seriesVideos)
            }
        }

        return foundMeta ?: AddonClient.fetchMeta(CINEMETA_URL, type, id)
    }

    suspend fun getStreams(type: String, id: String): List<AddonStream> {
        val streams = mutableListOf<AddonStream>()
        val addons = _installedAddons.value

        for (addon in addons) {
            val base = addon.baseUrl.ifBlank { CINEMETA_URL }
            val res = AddonClient.fetchStreams(base, type, id)
            streams.addAll(res)
        }

        // Also query community stream resolver if not already returned
        if (streams.isEmpty()) {
            val torrentioStreams = AddonClient.fetchStreams("https://torrentio.strem.fun", type, id)
            streams.addAll(torrentioStreams)
        }

        return streams
    }

    suspend fun search(query: String): List<MediaItem> {
        val base = _installedAddons.value.firstOrNull()?.baseUrl?.ifBlank { CINEMETA_URL } ?: CINEMETA_URL
        val movies = AddonClient.search(base, "movie", query)
        val series = AddonClient.search(base, "series", query)
        return (movies + series).distinctBy { it.id }
    }

    suspend fun installAddon(url: String): Boolean {
        val manifest = AddonClient.fetchManifest(url) ?: return false
        val current = _installedAddons.value.toMutableList()
        current.removeAll { it.id == manifest.id }
        current.add(manifest)
        _installedAddons.value = current
        prefs?.edit()?.putString(KEY_ADDONS, gson.toJson(current))?.apply()
        refreshCatalogs()
        return true
    }

    fun uninstallAddon(addonId: String) {
        val current = _installedAddons.value.toMutableList()
        current.removeAll { it.id == addonId }
        _installedAddons.value = current
        prefs?.edit()?.putString(KEY_ADDONS, gson.toJson(current))?.apply()
        refreshCatalogs()
    }

    private fun getFallbackItems(): List<MediaItem> = listOf(
        MediaItem(
            id = "tt15239678",
            title = "Dune: Part Two",
            type = MediaType.MOVIE,
            posterUrl = "https://image.tmdb.org/t/p/w780/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/xOMo8BRK7PfcJv9JCnx7s520DRq.jpg",
            description = "Paul Atreides hợp sức với Chani và người Fremen trên hành trình báo thù những kẻ đã hủy diệt gia tộc.",
            rating = "8.6",
            year = "2024",
            genres = listOf("Khoa học viễn tưởng", "Phiêu lưu"),
            durationMinutes = 166
        ),
        MediaItem(
            id = "tt15398776",
            title = "Oppenheimer",
            type = MediaType.MOVIE,
            posterUrl = "https://image.tmdb.org/t/p/w780/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/fm6K9vYQ79FGzOKmNavqmxsDfRo.jpg",
            description = "Câu chuyện về nhà vật lý lý thuyết J. Robert Oppenheimer và dự án Manhattan tạo ra bom nguyên tử.",
            rating = "8.9",
            year = "2023",
            genres = listOf("Tiểu sử", "Chính kịch", "Lịch sử"),
            durationMinutes = 180
        ),
        MediaItem(
            id = "tt2788316",
            title = "Shōgun",
            type = MediaType.SERIES,
            posterUrl = "https://image.tmdb.org/t/p/w780/7O4iVfOMQmdCSxhOg1WNzG1AgYT.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/56Wn2k74kZ6hI5L6n0xOsqq2n8f.jpg",
            description = "Khi một con tàu châu Âu bí ẩn mắc cạn tại ngôi làng đánh cá Nhật Bản vào thế kỷ 17, cuộc chiến quyền lực nổ ra.",
            rating = "8.7",
            year = "2024",
            genres = listOf("Hành động", "Chính kịch", "Lịch sử"),
            durationMinutes = 60
        )
    )
}
