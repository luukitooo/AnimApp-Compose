package com.luukitoo.animapp.presentation.screen.favorites.anime.viewmodel

import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.viewmodel.ViewState

data class FavoriteAnimeListViewState(
    val favoriteAnimeList: List<FavoriteAnime> = emptyList(),
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null
) : ViewState()
