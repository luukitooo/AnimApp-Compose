package com.luukitoo.animapp.presentation.screen.favorites.anime.viewmodel

import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetFavoriteAnimeFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteAnimeListViewModel @Inject constructor(
    private val getFavoriteAnimeList: GetFavoriteAnimeFlow
) : StatefulViewModel<FavoriteAnimeListViewState, FavoriteAnimeListEvent> (
    initialState = FavoriteAnimeListViewState()
) {

    init {
        onEvent(FavoriteAnimeListEvent.GetFavoriteAnime)
    }

    override fun onEvent(event: FavoriteAnimeListEvent) {
        when (event) {
            is FavoriteAnimeListEvent.GetFavoriteAnime -> getFavoriteAnime()
        }
    }

    private fun getFavoriteAnime() = launch {
        getFavoriteAnimeList.execute().collect { animeList ->
            updateState { copy(
                favoriteAnimeList = animeList
            ) }
        }
    }
}