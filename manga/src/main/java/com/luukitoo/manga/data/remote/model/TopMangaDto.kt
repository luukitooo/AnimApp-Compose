package com.luukitoo.manga.data.remote.model

import com.google.gson.annotations.SerializedName
import com.luukitoo.manga.domain.model.TopManga

data class TopMangaDto(
    val pagination: PaginationDto? = null,
    val data: List<MangaDto>? = null,
) {
    data class PaginationDto(
        @SerializedName("has_next_page")
        val hasNextPage: Boolean? = null,
        @SerializedName("current_page")
        val currentPage: Int? = null,
    )

    data class MangaDto(
        @SerializedName("mal_id")
        val id: Long? = null,
        val url: String? = null,
        val images: ImagesDto? = null,
        val title: String? = null,
        @SerializedName("title_english")
        val titleEnglish: String? = null,
        @SerializedName("title_japanese")
        val titleJapanese: String? = null,
        val type: String? = null,
        val score: Float? = null,
        @SerializedName("scored_by")
        val scoredBy: Int? = null,
        val rank: Int? = null,
        val synopsis: String? = null,
        val genres: List<GenreDto>? = null,
        val authors: List<AuthorDto>? = null
    ) {
        data class ImagesDto(
            val jpg: JPGDto? = null,
        ) {
            data class JPGDto(
                @SerializedName("image_url")
                val imageUrl: String? = null,
                @SerializedName("small_image_url")
                val smallImageUrl: String? = null,
                @SerializedName("large_image_url")
                val largeImageUrl: String? = null,
            )
        }

        data class GenreDto(
            @SerializedName("mal_id")
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null,
        )

        data class AuthorDto(
            @SerializedName("mal_id")
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null,
            val url: String? = null
        )
    }
}

fun TopMangaDto.MangaDto.GenreDto.toGenre() = TopManga.Manga.Genre(
    id = id,
    type = type,
    name = name
)

fun TopMangaDto.MangaDto.ImagesDto.JPGDto.toJPG() = TopManga.Manga.Images.JPG(
    imageUrl = imageUrl,
    smallImageUrl = smallImageUrl,
    largeImageUrl = largeImageUrl
)

fun TopMangaDto.MangaDto.ImagesDto.toImages() = TopManga.Manga.Images(
    jpg = jpg?.toJPG()
)

fun TopMangaDto.MangaDto.toManga() = TopManga.Manga(
    id = id,
    url = url,
    images = images?.toImages(),
    title = title,
    titleEnglish = titleEnglish,
    titleJapanese = titleJapanese,
    type = type,
    score = score,
    scoredBy = scoredBy,
    rank = rank,
    synopsis = synopsis,
    genres = genres?.map(TopMangaDto.MangaDto.GenreDto::toGenre),
    authors = authors?.map(TopMangaDto.MangaDto.AuthorDto::toAuthor)
)

fun TopMangaDto.PaginationDto.toPagination() = TopManga.Pagination(
    hasNextPage = hasNextPage,
    currentPage = currentPage
)

fun TopMangaDto.toTopManga() = TopManga(
    pagination = pagination?.toPagination(),
    data = data?.map(TopMangaDto.MangaDto::toManga)
)

fun TopMangaDto.MangaDto.AuthorDto.toAuthor() = TopManga.Manga.Author(
    id = id,
    type = type,
    name = name,
    url = url
)