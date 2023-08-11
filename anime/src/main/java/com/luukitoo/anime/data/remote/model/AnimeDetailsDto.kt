package com.luukitoo.anime.data.remote.model

import com.luukitoo.anime.domain.model.AnimeDetails

data class AnimeDetailsDto(
    val data: TopAnimeDto.AnimeDto?
) {
    fun toAnimeDetails() = AnimeDetails(
        data = data?.toAnime()
    )
}
