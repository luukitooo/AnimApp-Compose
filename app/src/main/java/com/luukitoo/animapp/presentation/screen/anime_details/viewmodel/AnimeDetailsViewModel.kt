package com.luukitoo.animapp.presentation.screen.anime_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetAnimeDetails
import com.luukitoo.usecase.anime.GetAnimeDetailsParams
import com.luukitoo.usecase.anime.GetCharactersByAnime
import com.luukitoo.usecase.anime.GetCharactersByAnimeParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeDetails: GetAnimeDetails,
    private val getCharactersByAnime: GetCharactersByAnime
) : StatefulViewModel<AnimeDetailsViewState, AnimeDetailsEvent>(
    initialState = AnimeDetailsViewState()
) {

    init {
        onEvent(AnimeDetailsEvent.GetDetails(savedStateHandle["animeId"] ?: -1))
        onEvent(AnimeDetailsEvent.GetCharacters(savedStateHandle["animeId"] ?: -1))
    }

    override fun onEvent(event: AnimeDetailsEvent) {
        when (event) {
            is AnimeDetailsEvent.GetDetails -> getDetails(event.id)
            is AnimeDetailsEvent.GetCharacters -> getCharacters(event.animeId)
            is AnimeDetailsEvent.SaveToFavorites -> saveToFavorites()
            is AnimeDetailsEvent.RemoveFromFavorites -> removeFromFavorites()
        }
    }

    private fun getDetails(id: Long) = launch {
        updateState { copy(isLoading = true) }
        getAnimeDetails.execute(GetAnimeDetailsParams(id)).apply {
            onSuccess { animeDetails ->
                updateState { copy(
                    anime = animeDetails.data ?: TopAnime.Anime(),
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

    private fun getCharacters(animeId: Long) = launch {
        getCharactersByAnime.execute(GetCharactersByAnimeParams(animeId)).apply {
            onSuccess { characterInfo ->
                updateState { copy(
                    characters = characterInfo.data?.map {
                        it.character ?: CharacterInfo.CharacterData.Character()
                    } ?: emptyList(),
                ) }
            }
            onFailure { failure ->
                updateState { copy(
                    throwable = Disposable(failure)
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