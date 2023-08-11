package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class SearchManga @Inject constructor(
    private val repository: MangaRepository
) : UseCase<SearchMangaParams, ResultStatus<TopManga>> {

    override suspend fun execute(parameter: SearchMangaParams): ResultStatus<TopManga> {
        return repository.searchManga(
            page = parameter.page,
            query = parameter.query
        )
    }
}

data class SearchMangaParams(
    val page: Int? = null,
    val query: String
)