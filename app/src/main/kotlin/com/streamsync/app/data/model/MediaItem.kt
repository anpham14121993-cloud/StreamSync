package com.streamsync.app.data.model

data class MediaItem(
    val id: String,
    val title: String,
    val type: MediaType = MediaType.MOVIE,
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val description: String = "",
    val rating: String = "8.2",
    val year: String = "2024",
    val genres: List<String> = emptyList(),
    val durationMinutes: Int = 120,
    val streamUrl: String = ""
)

enum class MediaType {
    MOVIE,
    SERIES
}

data class CatalogRow(
    val id: String,
    val title: String,
    val items: List<MediaItem>
)

data class WatchProgress(
    val id: String,
    val mediaId: String,
    val title: String,
    val subtitle: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val progressPercent: Float = 0.5f,
    val remainingMinutes: Int = 24,
    val streamUrl: String = ""
)

data class StreamSource(
    val id: String,
    val name: String,
    val quality: String,
    val resolution: String,
    val url: String
)
