package com.luukitoo.anime.data.repository

import com.luukitoo.anime.data.remote.model.toTopAnime
import com.luukitoo.anime.data.remote.service.AnimeService
import com.luukitoo.anime.domain.model.AnimeDetails
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService,
) : AnimeRepository {

    override suspend fun getTopAnime(
        page: Int?,
        limit: Int?,
    ): ResultStatus<TopAnime> {
        return NetworkCaller.safeCall {
            animeService.getTopAnime(page, limit)
        }.mapData { topAnimeDto ->
            topAnimeDto.toTopAnime()
        }
    }

    override suspend fun getAnimeDetails(id: Long): ResultStatus<AnimeDetails> {
        return NetworkCaller.safeCall {
            animeService.getAnimeDetails(id)
        }.mapData { animeDetailsDto ->
            animeDetailsDto.toAnimeDetails()
        }
    }

    override suspend fun searchAnime(
        page: Int?,
        query: String?
    ): ResultStatus<TopAnime> {
        return NetworkCaller.safeCall {
            animeService.searchAnime(page, query)
        }.mapData { topAnimeDto ->
            topAnimeDto.toTopAnime()
        }
    }
}