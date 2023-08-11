package com.luukitoo.animapp.presentation.screen.top_anime.viewmodel

import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetTopAnime
import com.luukitoo.usecase.anime.GetTopAnimeParams
import com.luukitoo.usecase.anime.SearchAnime
import com.luukitoo.usecase.anime.SearchAnimeParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopAnimeListViewModel @Inject constructor(
    private val getTopAnime: GetTopAnime,
    private val searchAnime: SearchAnime
) : StatefulViewModel<TopAnimeListViewState, TopAnimeListEvent>(
    initialState = TopAnimeListViewState()
) {

    init {
        onEvent(TopAnimeListEvent.GetTopAnime(page = 1))
    }

    override fun onEvent(event: TopAnimeListEvent) {
        when (event) {
            is TopAnimeListEvent.GetTopAnime -> getTopAnimeList(event.page)
            is TopAnimeListEvent.ToggleSearch -> toggleSearch()
            is TopAnimeListEvent.UpdateSearchQuery -> updateSearchQuery(event.newValue)
            is TopAnimeListEvent.SearchAnime -> searchAnime(event.query)
        }
    }

    private fun searchAnime(query: String) = launch {
        if (query.isBlank()) {
            getTopAnimeList(1)
            return@launch
        }
        updateState { copy(isLoading = true) }
        searchAnime.execute(SearchAnimeParams(query = query)).apply {
            onSuccess { topAnime ->
                updateState { copy(
                    topAnimeList = topAnime.data?.sortedByDescending { it.score } ?: emptyList(),
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

    private fun updateSearchQuery(newValue: String) = updateState {
        copy(searchQuery = newValue)
    }

    private fun toggleSearch() = updateState {
        copy(isSearchVisible = !isSearchVisible)
    }.also {
        if (!viewState.value.isSearchVisible) {
            getTopAnimeList(page = 1)
            updateState { copy(searchQuery = "") }
        }
    }

    private fun getTopAnimeList(page: Int) = launch {
        updateState { copy(isLoading = true) }
        getTopAnime.execute(GetTopAnimeParams(page = page)).apply {
            onSuccess { topAnime ->
                updateState { copy(
                    topAnimeList = topAnime.data ?: emptyList(),
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