package com.luukitoo.anime.domain.model

import com.luukitoo.core.extension.notNull

data class TopAnime(
    val pagination: Pagination? = null,
    val data: List<Anime>? = null,
) {
    data class Pagination(
        val hasNextPage: Boolean? = null,
        val currentPage: Int? = null,
    )

    data class Anime(
        val id: Long? = null,
        val url: String? = null,
        val images: Images? = null,
        val trailer: Trailer? = null,
        val title: String? = null,
        val titleEnglish: String? = null,
        val titleJapanese: String? = null,
        val episodes: Int? = null,
        val status: String? = null,
        val duration: String? = null,
        val rating: String? = null,
        val score: Float? = null,
        val scoredBy: Int? = null,
        val rank: Int? = null,
        val synopsis: String? = null,
        val year: Int? = null,
        val studios: List<Studio>? = null,
        val genres: List<Genre>? = null
    ) {
        data class Images(
            val jpg: JPG? = null
        ) {
            data class JPG(
                val imageUrl: String? = null,
                val smallImageUrl: String? = null,
                val largeImageUrl: String? = null
            )
        }

        data class Trailer(
            val youtubeId: String? = null,
            val url: String? = null
        )

        data class Studio(
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null
        )

        data class Genre(
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null
        )

        fun getAnimeTitle() = when {
            titleEnglish.notNull() -> titleEnglish!!
            title.notNull() -> title!!
            titleJapanese.notNull() -> titleJapanese!!
            else -> ""
        }
    }
}