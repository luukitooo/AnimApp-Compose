package com.luukitoo.usecase.manga

import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.manga.domain.model.FavoriteManga
import com.luukitoo.manga.domain.repository.MangaRepository
import javax.inject.Inject

class SaveMangaToFavorites @Inject constructor(
    private val repository: MangaRepository
) : UseCase<FavoriteManga, Unit> {

    override suspend fun execute(parameter: FavoriteManga) {
        repository.saveMangaToFavorites(parameter)
    }
}