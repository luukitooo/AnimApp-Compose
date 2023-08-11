package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class SearchAnime @Inject constructor(
    private val repository: AnimeRepository
) : UseCase<SearchAnimeParams, ResultStatus<TopAnime>> {

    override suspend fun execute(parameter: SearchAnimeParams): ResultStatus<TopAnime> {
        return repository.searchAnime(
            page = parameter.page,
            query = parameter.query
        )
    }
}

data class SearchAnimeParams(
    val page: Int? = null,
    val query: String? = null
)