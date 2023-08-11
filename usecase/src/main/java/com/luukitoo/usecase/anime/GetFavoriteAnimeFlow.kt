package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.base.usecase.UseCaseNoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteAnimeFlow @Inject constructor(
    private val repository: AnimeRepository
): UseCaseNoParams<Flow<List<FavoriteAnime>>> {

    override suspend fun execute(): Flow<List<FavoriteAnime>> {
        return repository.getFavoriteAnimeFlow()
    }
}