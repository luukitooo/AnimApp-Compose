package com.luukitoo.animapp.presentation.screen.manga_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.luukitoo.core.extension.notNull
import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.manga.GetFavoriteMangaList
import com.luukitoo.usecase.manga.GetMangaDetails
import com.luukitoo.usecase.manga.GetMangaDetailsParams
import com.luukitoo.usecase.manga.RemoveMangaFromFavorites
import com.luukitoo.usecase.manga.SaveMangaToFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MangaDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMangaDetails: GetMangaDetails,
    private val getFavoriteMangaList: GetFavoriteMangaList,
    private val saveMangaToFavorites: SaveMangaToFavorites,
    private val removeMangaFromFavorites: RemoveMangaFromFavorites
) : StatefulViewModel<MangaDetailsViewState, MangaDetailsEvent>(
    initialState = MangaDetailsViewState()
) {

    init {
        onEvent(MangaDetailsEvent.GetManga(savedStateHandle["mangaId"] ?: -1))
    }

    override fun onEvent(event: MangaDetailsEvent) {
        when (event) {
            is MangaDetailsEvent.GetManga -> getManga(event.id)
            is MangaDetailsEvent.CheckIfIsFavorite -> checkIfIsFavorite()
            is MangaDetailsEvent.SaveToFavorites -> saveToFavorites()
            is MangaDetailsEvent.RemoveFromFavorites -> removeFromFavorites()
        }
    }

    private fun checkIfIsFavorite() = launch {
        val favorites = getFavoriteMangaList.execute()
        updateState { copy(
            isFavorite = favorites.find {
                it.id == viewState.value.manga.id
            }.notNull()
        ) }
    }

    private fun getManga(id: Long) = launch {
        updateState { copy(isLoading = true) }
        getMangaDetails.execute(GetMangaDetailsParams(id)).apply {
            onSuccess { mangaDetails ->
                updateState { copy(
                    manga = mangaDetails.data ?: TopManga.Manga(),
                    isLoading = false
                ) }
                onEvent(MangaDetailsEvent.CheckIfIsFavorite)
            }
            onFailure { failure ->
                updateState { copy(
                    throwable = Disposable(failure),
                    isLoading = false
                ) }
            }
        }
    }

    private fun saveToFavorites() = launch {
        saveMangaToFavorites.execute(
            parameter = viewState.value.manga.toFavoriteManga()
        )
        updateState { copy(isFavorite = true) }
    }

    private fun removeFromFavorites() = launch {
        removeMangaFromFavorites.execute(
            mangaId = viewState.value.manga.id ?: -1
        )
        updateState { copy(isFavorite = false) }
    }
}