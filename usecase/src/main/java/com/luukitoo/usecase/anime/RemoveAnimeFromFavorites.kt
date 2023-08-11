package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import javax.inject.Inject

class RemoveAnimeFromFavorites @Inject constructor(
    private val repository: AnimeRepository
) : UseCase<Long, Unit> {

    override suspend fun execute(animeId: Long) {
        repository.removeAnimeFromFavorites(animeId)
    }
}