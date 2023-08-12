package com.luukitoo.manga.domain.model

import com.google.gson.annotations.SerializedName
import com.luukitoo.core.extension.notNull

data class TopManga(
    val pagination: Pagination? = null,
    val data: List<Manga>? = null,
) {
    data class Pagination(
        val hasNextPage: Boolean? = null,
        val currentPage: Int? = null,
    )

    data class Manga(
        val id: Long? = null,
        val url: String? = null,
        val images: Images? = null,
        val title: String? = null,
        val titleEnglish: String? = null,
        val titleJapanese: String? = null,
        val type: String? = null,
        val score: Float? = null,
        val scoredBy: Int? = null,
        val rank: Int? = null,
        val synopsis: String? = null,
        val genres: List<Genre>? = null,
        val authors: List<Author>? = null
    ) {
        data class Images(
            val jpg: JPG? = null,
        ) {
            data class JPG(
                val imageUrl: String? = null,
                val smallImageUrl: String? = null,
                val largeImageUrl: String? = null,
            )
        }

        data class Genre(
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null,
        )

        data class Author(
            @SerializedName("mal_id")
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null,
            val url: String? = null
        )

        fun getMangaTitle() = when {
            titleEnglish.notNull() -> titleEnglish!!
            title.notNull() -> title!!
            titleJapanese.notNull() -> titleJapanese!!
            else -> ""
        }

        fun toFavoriteManga() = FavoriteManga(
            id = id,
            title = getMangaTitle(),
            author = authors?.firstOrNull()?.name.toString(),
            imageUrl = images?.jpg?.imageUrl.toString()
        )
    }
}