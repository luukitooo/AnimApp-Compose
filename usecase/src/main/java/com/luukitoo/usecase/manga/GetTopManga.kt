package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class GetTopManga @Inject constructor(
    private val mangaRepository: MangaRepository
) : UseCase<GetTopMangaParams, ResultStatus<TopManga>> {

    override suspend fun execute(parameter: GetTopMangaParams): ResultStatus<TopManga> {
        return mangaRepository.getTopManga(
            page = parameter.page,
            limit = parameter.limit
        )
    }
}

data class GetTopMangaParams(
    val page: Int? = null,
    val limit: Int? = null
)