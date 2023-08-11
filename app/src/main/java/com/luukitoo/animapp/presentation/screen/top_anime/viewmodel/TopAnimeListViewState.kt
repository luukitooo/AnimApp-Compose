package com.luukitoo.animapp.presentation.screen.top_anime.viewmodel

import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.viewmodel.ViewState

data class TopAnimeListViewState(
    val topAnimeList: List<TopAnime.Anime> = emptyList(),
    val isSearchVisible: Boolean = false,
    val searchQuery: String = "",
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null,
) : ViewState()
