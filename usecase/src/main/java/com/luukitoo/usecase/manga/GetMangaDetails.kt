package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.manga.domain.model.MangaDetails
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class GetMangaDetails @Inject constructor(
    private val repository: MangaRepository
) : UseCase<GetMangaDetailsParams, ResultStatus<MangaDetails>> {

    override suspend fun execute(parameter: GetMangaDetailsParams): ResultStatus<MangaDetails> {
        return repository.getMangaDetails(
            id = parameter.mangaId
        )
    }
}

data class GetMangaDetailsParams(
    val mangaId: Long?
)