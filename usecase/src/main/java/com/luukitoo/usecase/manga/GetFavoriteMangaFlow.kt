package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCaseNoParams
import com.luukitoo.manga.domain.model.FavoriteManga
import com.luukitoo.manga.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMangaFlow @Inject constructor(
    private val repository: MangaRepository
) : UseCaseNoParams<Flow<List<FavoriteManga>>> {

    override suspend fun execute(): Flow<List<FavoriteManga>> {
        return repository.getFavoriteMangaFlow()
    }
}