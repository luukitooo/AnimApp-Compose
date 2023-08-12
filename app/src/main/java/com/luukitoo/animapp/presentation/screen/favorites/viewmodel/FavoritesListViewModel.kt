package com.luukitoo.animapp.presentation.screen.favorites.viewmodel

import com.luukitoo.animapp.presentation.screen.favorites.helper.FavoriteItemModel
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetFavoriteAnimeFlow
import com.luukitoo.usecase.anime.GetFavoriteAnimeList
import com.luukitoo.usecase.manga.GetFavoriteMangaFlow
import com.luukitoo.usecase.manga.GetFavoriteMangaList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesListViewModel @Inject constructor(
    private val getFavoriteMangaFlow: GetFavoriteMangaFlow,
    private val getFavoriteAnimeFlow: GetFavoriteAnimeFlow,
    private val getFavoriteMangaList: GetFavoriteMangaList,
    private val getFavoriteAnimeList: GetFavoriteAnimeList
) : StatefulViewModel<FavoritesListViewState, FavoritesListEvent>(
    initialState = FavoritesListViewState()
) {

    private var favoriteMangaList: List<FavoriteItemModel> = emptyList()
    private var favoriteAnimeList: List<FavoriteItemModel> = emptyList()

    init {
        initialLoad()
        getFavoriteAnime()
        getFavoriteManga()
    }

    override fun onEvent(event: FavoritesListEvent) {
        when (event) {
            is FavoritesListEvent.SetCategory -> setCategory(event.category)
        }
    }

    private fun setCategory(category: FavoriteCategory) {
        val newList = when (category) {
            FavoriteCategory.ALL -> (favoriteAnimeList + favoriteMangaList).sortedBy { it.title }
            FavoriteCategory.ANIME -> favoriteAnimeList
            FavoriteCategory.MANGA -> favoriteMangaList
        }
        updateState {
            copy(
                selectedCategory = category,
                favoritesList = newList
            )
        }
    }

    private fun initialLoad() = launch {
        getFavoriteAnimeList.execute()
            .map(FavoriteItemModel::fromFavoriteAnime)
            .plus(
                getFavoriteMangaList.execute().map(FavoriteItemModel::fromFavoriteManga)
            ).sortedBy {
                it.title
            }.also { favoriteItemModels ->
                updateState { copy(
                    favoritesList = favoriteItemModels,
                    selectedCategory = FavoriteCategory.ALL
                ) }
            }
    }

    private fun getFavoriteManga() = launch {
        getFavoriteAnimeFlow.execute().collect { animeList ->
            favoriteAnimeList = animeList.sortedBy {
                it.title
            }.map {
                FavoriteItemModel.fromFavoriteAnime(it)
            }
        }
    }

    private fun getFavoriteAnime() = launch {
        getFavoriteMangaFlow.execute().collect { mangaList ->
            favoriteMangaList = mangaList.sortedBy {
                it.title
            }.map {
                FavoriteItemModel.fromFavoriteManga(it)
            }
        }
    }
}