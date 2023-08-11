package com.luukitoo.manga.data.remote.model

import com.luukitoo.manga.domain.model.MangaDetails

data class MangaDetailsDto(
    val data: TopMangaDto.MangaDto? = null
) {
    fun toMangaDetails() = MangaDetails(
        data = data?.toManga()
    )
}