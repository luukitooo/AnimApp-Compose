package com.luukitoo.anime.domain.repository

import com.luukitoo.anime.domain.model.AnimeDetails
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.core.util.ResultStatus

interface AnimeRepository {

    suspend fun getTopAnime(
        page: Int?,
        limit: Int?
    ): ResultStatus<TopAnime>

    suspend fun getAnimeDetails(
        id: Long
    ): ResultStatus<AnimeDetails>

    suspend fun searchAnime(
        page: Int?,
        query: String?
    ): ResultStatus<TopAnime>
}