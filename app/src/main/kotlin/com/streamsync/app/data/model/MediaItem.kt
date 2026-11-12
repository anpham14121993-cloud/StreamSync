package com.streamsync.app.data.model

data class MediaItem(
    val id: String,
    val title: String,
    val type: MediaType = MediaType.MOVIE,
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val logoUrl: String = "",
    val description: String = "",
    val rating: String = "8.2",
    val year: String = "2024",
    val genres: List<String> = emptyList(),
    val durationMinutes: Int = 120,
    val streamUrl: String = "",
    val streamName: String = "",
    val season: Int? = null,
    val episode: Int? = null,
    val seriesVideos: List<SeriesVideo> = emptyList(),
    val sourceAddonId: String = ""
) {
    val backgroundUrl: String get() = backdropUrl
    val imdbRating: String get() = rating
    val releaseInfo: String get() = year
}

enum class MediaType {
    MOVIE,
    SERIES
}

data class SeriesVideo(
    val id: String,
    val title: String,
    val season: Int,
    val episode: Int,
    val released: String = "",
    val thumbnail: String = ""
)

data class CatalogRow(
    val id: String,
    val title: String,
    val items: List<MediaItem>,
    val addonName: String = ""
) {
    val name: String get() = title
    val key: String get() = id
    val hasItems: Boolean get() = items.isNotEmpty()
}

data class WatchProgress(
    val id: String,
    val mediaId: String,
    val title: String,
    val subtitle: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = "",
    val progressPercent: Float = 0.5f,
    val remainingMinutes: Int = 24,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L,
    val season: Int? = null,
    val episode: Int? = null,
    val streamUrl: String = ""
)

data class StreamSource(
    val id: String,
    val name: String,
    val quality: String,
    val resolution: String,
    val url: String
)
