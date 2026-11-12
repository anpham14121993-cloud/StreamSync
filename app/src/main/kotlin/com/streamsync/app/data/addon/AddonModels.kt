package com.streamsync.app.data.addon

data class AddonManifest(
    val id: String,
    val name: String,
    val version: String,
    val description: String? = null,
    val types: List<String>? = null,
    val catalogs: List<AddonCatalogDef>? = null,
    val resources: List<Any>? = null,
    val idPrefixes: List<String>? = null,
    val logo: String? = null,
    var baseUrl: String = ""
)

data class AddonCatalogDef(
    val type: String,
    val id: String,
    val name: String? = null
)

data class CatalogResponse(
    val metas: List<AddonMeta>? = null
)

data class MetaResponse(
    val meta: AddonMeta? = null
)

data class StreamResponse(
    val streams: List<AddonStream>? = null
)

data class AddonMeta(
    val id: String,
    val type: String,
    val name: String,
    val poster: String? = null,
    val background: String? = null,
    val logo: String? = null,
    val description: String? = null,
    val releaseInfo: String? = null,
    val imdbRating: String? = null,
    val genres: List<String>? = null,
    val runtime: String? = null,
    val videos: List<AddonVideo>? = null
)

data class AddonVideo(
    val id: String = "",
    val title: String? = null,
    val name: String? = null,
    val season: Any? = 1,
    val episode: Any? = 1,
    val number: Any? = null,
    val released: String? = null,
    val thumbnail: String? = null,
    val overview: String? = null,
    val description: String? = null
) {
    val displayTitle: String
        get() = title?.takeIf { it.isNotBlank() }
            ?: name?.takeIf { it.isNotBlank() }
            ?: "Tập ${episodeNumber}"

    val seasonNumber: Int
        get() = when (val s = season) {
            is Number -> s.toInt()
            is String -> s.toIntOrNull() ?: 1
            else -> 1
        }

    val episodeNumber: Int
        get() = when (val e = episode ?: number) {
            is Number -> e.toInt()
            is String -> e.toIntOrNull() ?: 1
            else -> 1
        }
}

data class AddonStream(
    val title: String? = null,
    val name: String? = null,
    val description: String? = null,
    val url: String? = null,
    val ytId: String? = null,
    val infoHash: String? = null,
    val fileIdx: Int? = null,
    val behaviorHints: AddonBehaviorHints? = null
) {
    val displayTitle: String
        get() = when {
            !name.isNullOrBlank() && !title.isNullOrBlank() -> "[$name] $title"
            !title.isNullOrBlank() -> title
            !name.isNullOrBlank() -> name
            !description.isNullOrBlank() -> description
            else -> "Nguồn trực tuyến"
        }

    val playableUrl: String
        get() = when {
            !url.isNullOrBlank() -> url
            !ytId.isNullOrBlank() -> "https://www.youtube.com/watch?v=$ytId"
            else -> ""
        }
}

data class AddonBehaviorHints(
    val notWebReady: Boolean? = false,
    val proxyHeaders: Map<String, String>? = null
)
