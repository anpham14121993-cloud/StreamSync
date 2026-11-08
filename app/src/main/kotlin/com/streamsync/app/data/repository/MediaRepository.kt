package com.streamsync.app.data.repository

import com.streamsync.app.data.model.CatalogRow
import com.streamsync.app.data.model.MediaItem
import com.streamsync.app.data.model.MediaType
import com.streamsync.app.data.model.WatchProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MediaRepository {

    val heroItems = listOf(
        MediaItem(
            id = "hero-1",
            title = "Dune: Part Two",
            type = MediaType.MOVIE,
            posterUrl = "https://image.tmdb.org/t/p/w780/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/xOMo8BRK7PfcJv9JCnx7s520DRq.jpg",
            description = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            rating = "8.6",
            year = "2024",
            genres = listOf("Sci-Fi", "Adventure"),
            durationMinutes = 166
        ),
        MediaItem(
            id = "hero-2",
            title = "Oppenheimer",
            type = MediaType.MOVIE,
            posterUrl = "https://image.tmdb.org/t/p/w780/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/fm6K9vYQ79FGzOKmNavqmxsDfRo.jpg",
            description = "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
            rating = "8.9",
            year = "2023",
            genres = listOf("Biography", "Drama", "History"),
            durationMinutes = 180
        ),
        MediaItem(
            id = "hero-3",
            title = "Shōgun",
            type = MediaType.SERIES,
            posterUrl = "https://image.tmdb.org/t/p/w780/7O4iVfOMQmdCSxhOg1WnzG1AgYT.jpg",
            backdropUrl = "https://image.tmdb.org/t/p/original/y4Zz3Cq396bV4jT5yR7l4vK6B4t.jpg",
            description = "When a mysterious European ship is found marooned in a nearby fishing village, Lord Yoshii Toranaga discovers secrets that could tip the scales of power.",
            rating = "8.7",
            year = "2024",
            genres = listOf("Drama", "History", "War"),
            durationMinutes = 60
        )
    )

    val continueWatchingList = listOf(
        WatchProgress(
            id = "cw-1",
            mediaId = "cw-m1",
            title = "Shōgun",
            subtitle = "S1 E5 • Broken to the Fist",
            backdropUrl = "https://image.tmdb.org/t/p/w780/y4Zz3Cq396bV4jT5yR7l4vK6B4t.jpg",
            progressPercent = 0.65f,
            remainingMinutes = 21
        ),
        WatchProgress(
            id = "cw-2",
            mediaId = "cw-m2",
            title = "Fallout",
            subtitle = "S1 E3 • The Head",
            backdropUrl = "https://image.tmdb.org/t/p/w780/2rmK7mnchw9Xr3XdiTFSxTTvYqi.jpg",
            progressPercent = 0.40f,
            remainingMinutes = 35
        ),
        WatchProgress(
            id = "cw-3",
            mediaId = "cw-m3",
            title = "The Bear",
            subtitle = "S3 E1 • Tomorrow",
            backdropUrl = "https://image.tmdb.org/t/p/w780/m9PE2Q4Ocbj9p8f2r4sY7b8e1Gq.jpg",
            progressPercent = 0.85f,
            remainingMinutes = 6
        )
    )

    fun getCatalogRows(): Flow<List<CatalogRow>> = flow {
        val rows = listOf(
            CatalogRow(
                id = "trending",
                title = "Đang Thịnh Hành",
                items = listOf(
                    MediaItem("t-1", "Dune: Part Two", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg", rating = "8.6", year = "2024"),
                    MediaItem("t-2", "Civil War", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg", rating = "7.3", year = "2024"),
                    MediaItem("t-3", "Godzilla x Kong", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/bQ2ywAy0Scay9vdu59XZJ2jh0M.jpg", rating = "6.8", year = "2024"),
                    MediaItem("t-4", "Deadpool & Wolverine", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg", rating = "8.0", year = "2024"),
                    MediaItem("t-5", "Kingdom of the Planet of the Apes", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/gKkl37BQuKTanygYQG1pyYgLVgf.jpg", rating = "7.0", year = "2024")
                )
            ),
            CatalogRow(
                id = "top_series",
                title = "Phim Bộ Đặc Sắc",
                items = listOf(
                    MediaItem("s-1", "Shōgun", MediaType.SERIES, "https://image.tmdb.org/t/p/w500/7O4iVfOMQmdCSxhOg1WnzG1AgYT.jpg", rating = "8.7", year = "2024"),
                    MediaItem("s-2", "Fallout", MediaType.SERIES, "https://image.tmdb.org/t/p/w500/AnsZu4c057w9c58bXbN8i8X1kX.jpg", rating = "8.4", year = "2024"),
                    MediaItem("s-3", "House of the Dragon", MediaType.SERIES, "https://image.tmdb.org/t/p/w500/1X4h40fcB4WWUmIBK0auT4zRBAV.jpg", rating = "8.5", year = "2024"),
                    MediaItem("s-4", "The Boys", MediaType.SERIES, "https://image.tmdb.org/t/p/w500/2zmTngn1tYC1AvfnNDBpQI4vlBP.jpg", rating = "8.4", year = "2024"),
                    MediaItem("s-5", "The Bear", MediaType.SERIES, "https://image.tmdb.org/t/p/w500/531lkmLp0vCffw7i3vK2e8R7o.jpg", rating = "8.6", year = "2024")
                )
            ),
            CatalogRow(
                id = "action",
                title = "Hành Động & Phiêu Lưu",
                items = listOf(
                    MediaItem("a-1", "Furiosa: A Mad Max Saga", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/iADOJ8Zymht2JPMoy3R7xUMZ51.jpg", rating = "7.6", year = "2024"),
                    MediaItem("a-2", "The Beekeeper", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/A7EByudX0eOzlkQ2FIbogzyazm2.jpg", rating = "7.4", year = "2024"),
                    MediaItem("a-3", "Bad Boys: Ride or Die", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/nP6RliHjxsz4irTKsxe8FRhKZYl.jpg", rating = "7.1", year = "2024"),
                    MediaItem("a-4", "John Wick: Chapter 4", MediaType.MOVIE, "https://image.tmdb.org/t/p/w500/vZloFAK7NKnMGKEslUsZ2VoJ59m.jpg", rating = "7.8", year = "2023")
                )
            )
        )
        emit(rows)
    }
}
