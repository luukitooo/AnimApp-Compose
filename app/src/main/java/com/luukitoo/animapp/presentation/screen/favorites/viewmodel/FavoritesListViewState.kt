package com.luukitoo.animapp.presentation.screen.favorites.viewmodel

import com.luukitoo.animapp.presentation.screen.favorites.helper.FavoriteItemModel
import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.FavoriteManga
import com.luukitoo.mvi.viewmodel.ViewState

data class FavoritesListViewState(
    val selectedCategory: FavoriteCategory = FavoriteCategory.ALL,
    val favoritesList: List<FavoriteItemModel> = emptyList(),
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null
) : ViewState()

enum class FavoriteCategory(val title: String) {
    ALL("All"),
    ANIME("Anime"),
    MANGA("Manga")
}