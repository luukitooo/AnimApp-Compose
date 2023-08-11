package com.luukitoo.animapp.presentation.screen.home.viewmodel

import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.mvi.viewmodel.ViewState

data class HomeViewState(
    val topFiveAnime: List<TopAnime.Anime> = emptyList(),
    val animeList: List<TopAnime.Anime> = emptyList(),
    val mangaList: List<TopManga.Manga> = emptyList(),
    override val isLoading: Boolean = true,
    override val throwable: Disposable<Throwable>? = null
): ViewState()
