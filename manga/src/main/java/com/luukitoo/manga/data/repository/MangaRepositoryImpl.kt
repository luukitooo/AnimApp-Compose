package com.luukitoo.manga.data.repository

import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.manga.data.remote.model.toTopManga
import com.luukitoo.manga.data.remote.service.MangaService
import com.luukitoo.manga.domain.model.MangaDetails
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaService: MangaService,
) : MangaRepository {

    override suspend fun getTopManga(
        page: Int?,
        limit: Int?
    ): ResultStatus<TopManga> {
        return NetworkCaller.safeCall {
            mangaService.getTopManga(page, limit)
        }.mapData { topMangaDto ->
            topMangaDto.toTopManga()
        }
    }

    override suspend fun getMangaDetails(id: Long?): ResultStatus<MangaDetails> {
        return NetworkCaller.safeCall {
            mangaService.getMangaDetails(id)
        }.mapData { mangaDetailsDto ->
            mangaDetailsDto.toMangaDetails()
        }
    }

    override suspend fun searchManga(
        page: Int?,
        query: String?
    ): ResultStatus<TopManga> {
        return NetworkCaller.safeCall {
            mangaService.searchManga(page, query)
        }.mapData { topMangaDto ->
            topMangaDto.toTopManga()
        }
    }
}