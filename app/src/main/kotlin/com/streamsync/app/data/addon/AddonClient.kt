package com.streamsync.app.data.addon

import com.google.gson.Gson
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import com.streamsync.app.data.model.SeriesVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

object AddonClient {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    suspend fun fetchManifest(url: String): AddonManifest? = withContext(Dispatchers.IO) {
        try {
            val normalizedUrl = if (url.endsWith("/manifest.json")) url else url.trimEnd('/') + "/manifest.json"
            val request = Request.Builder().url(normalizedUrl).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@withContext null
                val body = response.body?.string() ?: return@withContext null
                val manifest = gson.fromJson(body, AddonManifest::class.java)
                manifest?.apply {
                    baseUrl = normalizedUrl.removeSuffix("/manifest.json")
                }
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun fetchCatalog(baseUrl: String, type: String, catalogId: String): List<MediaItem> = withContext(Dispatchers.IO) {
        try {
            val cleanBase = baseUrl.trimEnd('/')
            val url = "$cleanBase/catalog/$type/$catalogId.json"
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@withContext emptyList()
                val body = response.body?.string() ?: return@withContext emptyList()
                val catalogResp = gson.fromJson(body, CatalogResponse::class.java)
                catalogResp?.metas?.map { meta ->
                    MediaItem(
                        id = meta.id,
                        title = meta.name,
                        type = if (meta.type == "series") MediaType.SERIES else MediaType.MOVIE,
                        posterUrl = meta.poster ?: "",
                        backdropUrl = meta.background ?: meta.poster ?: "",
                        logoUrl = meta.logo ?: "",
                        description = meta.description ?: "",
                        rating = meta.imdbRating ?: "8.0",
                        year = meta.releaseInfo ?: "2024",
                        genres = meta.genres ?: emptyList(),
                        durationMinutes = meta.runtime?.filter { it.isDigit() }?.toIntOrNull() ?: 120
                    )
                } ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchMeta(baseUrl: String, type: String, id: String): MediaItem? = withContext(Dispatchers.IO) {
        try {
            val cleanBase = baseUrl.trimEnd('/')
            val url = "$cleanBase/meta/$type/$id.json"
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@withContext null
                val body = response.body?.string() ?: return@withContext null
                val metaResp = gson.fromJson(body, MetaResponse::class.java)
                val meta = metaResp?.meta ?: return@withContext null
                MediaItem(
                    id = meta.id,
                    title = meta.name,
                    type = if (meta.type == "series") MediaType.SERIES else MediaType.MOVIE,
                    posterUrl = meta.poster ?: "",
                    backdropUrl = meta.background ?: meta.poster ?: "",
                    logoUrl = meta.logo ?: "",
                    description = meta.description ?: "",
                    rating = meta.imdbRating ?: "8.2",
                    year = meta.releaseInfo ?: "2024",
                    genres = meta.genres ?: emptyList(),
                    durationMinutes = meta.runtime?.filter { it.isDigit() }?.toIntOrNull() ?: 120,
                    seriesVideos = meta.videos?.map { v ->
                        SeriesVideo(
                            id = v.id,
                            title = v.displayTitle,
                            season = v.seasonNumber,
                            episode = v.episodeNumber,
                            released = v.released ?: "",
                            thumbnail = v.thumbnail ?: ""
                        )
                    } ?: emptyList()
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun fetchStreams(baseUrl: String, type: String, id: String): List<AddonStream> = withContext(Dispatchers.IO) {
        try {
            val cleanBase = baseUrl.trimEnd('/')
            val url = "$cleanBase/stream/$type/$id.json"
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@withContext emptyList()
                val body = response.body?.string() ?: return@withContext emptyList()
                val streamResp = gson.fromJson(body, StreamResponse::class.java)
                streamResp?.streams ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun search(baseUrl: String, type: String, query: String): List<MediaItem> = withContext(Dispatchers.IO) {
        try {
            val cleanBase = baseUrl.trimEnd('/')
            val encodedQuery = URLEncoder.encode(query, "UTF-8")
            val url = "$cleanBase/catalog/$type/top/search=$encodedQuery.json"
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return@withContext emptyList()
                val body = response.body?.string() ?: return@withContext emptyList()
                val catalogResp = gson.fromJson(body, CatalogResponse::class.java)
                catalogResp?.metas?.map { meta ->
                    MediaItem(
                        id = meta.id,
                        title = meta.name,
                        type = if (meta.type == "series") MediaType.SERIES else MediaType.MOVIE,
                        posterUrl = meta.poster ?: "",
                        backdropUrl = meta.background ?: meta.poster ?: "",
                        logoUrl = meta.logo ?: "",
                        description = meta.description ?: "",
                        rating = meta.imdbRating ?: "7.8",
                        year = meta.releaseInfo ?: "2024",
                        genres = meta.genres ?: emptyList(),
                        durationMinutes = 120
                    )
                } ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
