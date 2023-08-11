package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import javax.inject.Inject

class SaveAnimeToFavorites @Inject constructor(
    private val repository: AnimeRepository
) : UseCase<FavoriteAnime, Unit> {

    override suspend fun execute(parameter: FavoriteAnime) {
        repository.saveAnimeToFavorites(parameter)
    }
}