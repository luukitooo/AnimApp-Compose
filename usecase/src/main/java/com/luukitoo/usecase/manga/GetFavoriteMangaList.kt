package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCaseNoParams
import com.luukitoo.manga.domain.model.FavoriteManga
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class GetFavoriteMangaList @Inject constructor(
    private val repository: MangaRepository
) : UseCaseNoParams<List<FavoriteManga>> {

    override suspend fun execute(): List<FavoriteManga> {
        return repository.getFavoriteMangaList()
    }
}