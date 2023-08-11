package com.luukitoo.animapp.presentation.screen.manga_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.manga.GetMangaDetails
import com.luukitoo.usecase.manga.GetMangaDetailsParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MangaDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMangaDetails: GetMangaDetails
) : StatefulViewModel<MangaDetailsViewState, MangaDetailsEvent>(
    initialState = MangaDetailsViewState()
) {

    init {
        onEvent(MangaDetailsEvent.GetManga(savedStateHandle["mangaId"] ?: -1))
    }

    override fun onEvent(event: MangaDetailsEvent) {
        when (event) {
            is MangaDetailsEvent.GetManga -> getManga(event.id)
            is MangaDetailsEvent.SaveToFavorites -> saveToFavorites()
            is MangaDetailsEvent.RemoveFromFavorites -> removeFromFavorites()
        }
    }

    private fun getManga(id: Long) = launch {
        updateState { copy(isLoading = true) }
        getMangaDetails.execute(GetMangaDetailsParams(id)).apply {
            onSuccess { mangaDetails ->
                updateState { copy(
                    manga = mangaDetails.data ?: TopManga.Manga(),
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

    private fun saveToFavorites() = updateState {
        copy(isFavorite = true)
    }

    private fun removeFromFavorites() = updateState {
        copy(isFavorite = false)
    }
}