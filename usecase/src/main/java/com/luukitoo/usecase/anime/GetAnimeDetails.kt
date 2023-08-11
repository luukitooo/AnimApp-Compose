package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.AnimeDetails
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class GetAnimeDetails @Inject constructor(
    private val repository: AnimeRepository
) : UseCase<GetAnimeDetailsParams, ResultStatus<AnimeDetails>> {

    override suspend fun execute(parameter: GetAnimeDetailsParams): ResultStatus<AnimeDetails> {
        return repository.getAnimeDetails(
            id = parameter.id
        )
    }
}

data class GetAnimeDetailsParams(
    val id: Long
)