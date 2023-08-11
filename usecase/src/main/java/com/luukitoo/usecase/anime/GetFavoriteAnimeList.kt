package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCaseNoParams
import javax.inject.Inject

class GetFavoriteAnimeList @Inject constructor(
    private val repository: AnimeRepository
) : UseCaseNoParams<List<FavoriteAnime>> {

    override suspend fun execute(): List<FavoriteAnime> {
        return repository.getFavoriteAnimeList()
    }
}