package com.luukitoo.animapp.presentation.screen.home.viewmodel

import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetTopAnime
import com.luukitoo.usecase.anime.GetTopAnimeParams
import com.luukitoo.usecase.manga.GetTopManga
import com.luukitoo.usecase.manga.GetTopMangaParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopAnime: GetTopAnime,
    private val getTopManga: GetTopManga
) : StatefulViewModel<HomeViewState, HomeEvent>(
    initialState = HomeViewState()
) {

    init {
        onEvent(HomeEvent.GetAnimeList)
        onEvent(HomeEvent.GetMangaList)
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetAnimeList -> getAnimeList()
            is HomeEvent.GetMangaList -> getMangaList()
            else -> Unit
        }
    }

    private fun getMangaList() = launch {
        getTopManga.execute(GetTopMangaParams()).apply {
            onSuccess { topManga ->
                updateState { copy(
                    mangaList = topManga.data?.subList(0, 10) ?: emptyList(),
                    isLoading = false
                ) }
            }
            onFailure { failure ->
                updateState { copy(
                    throwable = Disposable(failure),
                    isLoading = false
                ) }
            }
        }
    }

    private fun getAnimeList() = launch {
        getTopAnime.execute(GetTopAnimeParams()).apply {
            onSuccess { topAnime ->
                updateState { copy(
                    animeList = topAnime.data?.subList(0, 10) ?: emptyList(),
                    topFiveAnime = topAnime.data?.subList(0, 5).orEmpty(),
                    isLoading = false
                ) }
            }
            onFailure { failure ->
                updateState { copy(
                    throwable = Disposable(failure),
                    isLoading = false
                ) }
            }
        }
    }
}