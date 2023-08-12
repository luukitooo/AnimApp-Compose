package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class RemoveMangaFromFavorites @Inject constructor(
    private val repository: MangaRepository
) : UseCase<Long, Unit> {

    override suspend fun execute(mangaId: Long) {
        repository.removeMangaFromFavorites(mangaId)
    }
}