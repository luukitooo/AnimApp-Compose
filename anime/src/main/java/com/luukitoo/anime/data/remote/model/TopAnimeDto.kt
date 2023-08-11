package com.luukitoo.anime.data.remote.model

import com.google.gson.annotations.SerializedName
import com.luukitoo.anime.domain.model.TopAnime

data class TopAnimeDto(
    val pagination: PaginationDto? = null,
    val data: List<AnimeDto>? = null,
) {
    data class PaginationDto(
        @SerializedName("has_next_page")
        val hasNextPage: Boolean? = null,
        @SerializedName("current_page")
        val currentPage: Int? = null,
    )

    data class AnimeDto(
        @SerializedName("mal_id")
        val id: Long? = null,
        val url: String? = null,
        val images: ImagesDto? = null,
        val trailer: TrailerDto? = null,
        val title: String? = null,
        @SerializedName("title_english")
        val titleEnglish: String? = null,
        @SerializedName("title_japanese")
        val titleJapanese: String? = null,
        val episodes: Int? = null,
        val status: String? = null,
        val duration: String? = null,
        val rating: String? = null,
        val score: Float? = null,
        @SerializedName("scored_by")
        val scoredBy: Int? = null,
        val rank: Int? = null,
        val synopsis: String? = null,
        val year: Int? = null,
        val studios: List<StudioDto>? = null,
        val genres: List<GenreDto>? = null
    ) {
        data class ImagesDto(
            val jpg: JPGDto? = null
        ) {
            data class JPGDto(
                @SerializedName("image_url")
                val imageUrl: String? = null,
                @SerializedName("small_image_url")
                val smallImageUrl: String? = null,
                @SerializedName("large_image_url")
                val largeImageUrl: String? = null
            )
        }

        data class TrailerDto(
            @SerializedName("youtube_id")
            val youtubeId: String? = null,
            val url: String? = null
        )

        data class StudioDto(
            @SerializedName("mal_id")
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null
        )

        data class GenreDto(
            @SerializedName("mal_id")
            val id: Long? = null,
            val type: String? = null,
            val name: String? = null
        )
    }
}

fun TopAnimeDto.AnimeDto.GenreDto.toGenre() = TopAnime.Anime.Genre(
    id = id,
    type = type,
    name = name
)

fun TopAnimeDto.AnimeDto.StudioDto.toStudio() = TopAnime.Anime.Studio(
    id = id,
    type = type,
    name = name
)

fun TopAnimeDto.AnimeDto.TrailerDto.toTrailer() = TopAnime.Anime.Trailer(
    youtubeId = youtubeId,
    url = url
)

fun TopAnimeDto.AnimeDto.ImagesDto.JPGDto.toJPG() = TopAnime.Anime.Images.JPG(
    imageUrl = imageUrl,
    smallImageUrl = smallImageUrl,
    largeImageUrl = largeImageUrl
)

fun TopAnimeDto.AnimeDto.ImagesDto.toImage() = TopAnime.Anime.Images(
    jpg = jpg?.toJPG()
)

fun TopAnimeDto.AnimeDto.toAnime() = TopAnime.Anime(
    id = id,
    url = url,
    images = images?.toImage(),
    trailer = trailer?.toTrailer(),
    title = title,
    titleEnglish = titleEnglish,
    titleJapanese = titleJapanese,
    episodes = episodes,
    status = status,
    duration = duration,
    rating = rating,
    score = score,
    scoredBy = scoredBy,
    rank = rank,
    synopsis = synopsis,
    year = year,
    studios = studios?.map(TopAnimeDto.AnimeDto.StudioDto::toStudio),
    genres = genres?.map(TopAnimeDto.AnimeDto.GenreDto::toGenre)
)

fun TopAnimeDto.PaginationDto.toPagination() = TopAnime.Pagination(
    hasNextPage = hasNextPage,
    currentPage = currentPage
)

fun TopAnimeDto.toTopAnime() = TopAnime(
    pagination = pagination?.toPagination(),
    data = data?.map(TopAnimeDto.AnimeDto::toAnime)
)