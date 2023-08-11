package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class GetTopAnime @Inject constructor(
    private val animeRepository: AnimeRepository
): UseCase<GetTopAnimeParams, ResultStatus<TopAnime>> {

    override suspend fun execute(parameter: GetTopAnimeParams): ResultStatus<TopAnime> {
        return animeRepository.getTopAnime(
            page = parameter.page,
            limit = parameter.limit
        )
    }
}

data class GetTopAnimeParams(
    val page: Int? = null,
    val limit: Int? = null
)