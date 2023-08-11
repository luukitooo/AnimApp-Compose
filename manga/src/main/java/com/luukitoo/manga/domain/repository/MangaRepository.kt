package com.luukitoo.manga.domain.repository

import com.luukitoo.core.util.ResultStatus
import com.luukitoo.manga.domain.model.MangaDetails
import com.luukitoo.manga.domain.model.TopManga

interface MangaRepository {

    suspend fun getTopManga(page: Int?, limit: Int?): ResultStatus<TopManga>

    suspend fun getMangaDetails(id: Long?): ResultStatus<MangaDetails>

    suspend fun searchManga(page: Int?, query: String?): ResultStatus<TopManga>
}