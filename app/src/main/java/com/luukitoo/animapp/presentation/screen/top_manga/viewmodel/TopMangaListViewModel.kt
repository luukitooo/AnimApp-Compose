package com.luukitoo.animapp.presentation.screen.top_manga.viewmodel

import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.manga.GetTopManga
import com.luukitoo.usecase.manga.GetTopMangaParams
import com.luukitoo.usecase.manga.SearchManga
import com.luukitoo.usecase.manga.SearchMangaParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopMangaListViewModel @Inject constructor(
    private val getTopManga: GetTopManga,
    private val searchManga: SearchManga
) : StatefulViewModel<TopMangaListViewState, TopMangaListEvent>(
    initialState = TopMangaListViewState()
) {

    init {
        onEvent(TopMangaListEvent.GetTopManga(page = 1))
    }

    override fun onEvent(event: TopMangaListEvent) {
        when (event) {
            is TopMangaListEvent.ToggleSearch -> toggleSearch()
            is TopMangaListEvent.UpdateSearchQuery -> updateSearchQuery(event.newValue)
            is TopMangaListEvent.GetTopManga -> getTopMangaList(event.page)
            is TopMangaListEvent.SearchManga -> searchManga(event.query)
        }
    }

    private fun searchManga(query: String) = launch {
        if (query.isBlank()) {
            getTopMangaList(page = 1)
            return@launch
        }
        updateState { copy(isLoading = true) }
        searchManga.execute(SearchMangaParams(query = query)).apply {
            onSuccess { topManga ->
                updateState { copy(
                    topMangaList = topManga.data ?: emptyList(),
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

    private fun getTopMangaList(page: Int) = launch {
        updateState { copy(isLoading = true) }
        getTopManga.execute(GetTopMangaParams(page = page)).apply {
            onSuccess { topManga ->
                updateState { copy(
                    topMangaList = topManga.data ?: emptyList(),
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
            getTopMangaList(page = 1)
            updateState { copy(searchQuery = "") }
        }
    }
}