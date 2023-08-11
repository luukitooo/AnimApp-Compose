package com.luukitoo.animapp.presentation.screen.anime_details.viewmodel

import android.util.Log.d
import androidx.lifecycle.SavedStateHandle
import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.core.extension.notNull
import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.extension.launch
import com.luukitoo.mvi.viewmodel.StatefulViewModel
import com.luukitoo.usecase.anime.GetAnimeDetails
import com.luukitoo.usecase.anime.GetAnimeDetailsParams
import com.luukitoo.usecase.anime.GetCharactersByAnime
import com.luukitoo.usecase.anime.GetCharactersByAnimeParams
import com.luukitoo.usecase.anime.GetFavoriteAnimeList
import com.luukitoo.usecase.anime.RemoveAnimeFromFavorites
import com.luukitoo.usecase.anime.SaveAnimeToFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeDetails: GetAnimeDetails,
    private val getCharactersByAnime: GetCharactersByAnime,
    private val saveAnimeToFavorites: SaveAnimeToFavorites,
    private val removeAnimeFromFavorites: RemoveAnimeFromFavorites,
    private val getFavoriteAnimeList: GetFavoriteAnimeList
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
            is AnimeDetailsEvent.CheckIfIsFavorite -> checkIfIsFavorite()
            is AnimeDetailsEvent.SaveToFavorites -> saveToFavorites()
            is AnimeDetailsEvent.RemoveFromFavorites -> removeFromFavorites()
        }
    }

    private fun checkIfIsFavorite() = launch {
        val favorites = getFavoriteAnimeList.execute()
        updateState { copy(
                isFavorite = favorites
                    .find { it.id == viewState.value.anime.id }
                    .notNull()
        ) }
    }

    private fun getDetails(id: Long) = launch {
        updateState { copy(isLoading = true) }
        getAnimeDetails.execute(GetAnimeDetailsParams(id)).apply {
            onSuccess { animeDetails ->
                updateState {
                    copy(
                        anime = animeDetails.data ?: TopAnime.Anime(),
                        isLoading = false
                    )
                }
                onEvent(AnimeDetailsEvent.CheckIfIsFavorite)
            }
            onFailure { failure ->
                updateState {
                    copy(
                        throwable = Disposable(failure),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun getCharacters(animeId: Long) = launch {
        getCharactersByAnime.execute(GetCharactersByAnimeParams(animeId)).apply {
            onSuccess { characterInfo ->
                updateState {
                    copy(
                        characters = characterInfo.data?.map {
                            it.character ?: CharacterInfo.CharacterData.Character()
                        } ?: emptyList(),
                    )
                }
            }
            onFailure { failure ->
                updateState {
                    copy(
                        throwable = Disposable(failure)
                    )
                }
            }
        }
    }

    private fun saveToFavorites() = launch {
        saveAnimeToFavorites.execute(
            parameter = constructFavoriteAnime()
        )
        updateState { copy(isFavorite = true) }
    }

    private fun removeFromFavorites() = launch {
        removeAnimeFromFavorites.execute(
            animeId = viewState.value.anime.id ?: -1
        )
        updateState { copy(isFavorite = false) }
    }

    private fun constructFavoriteAnime(): FavoriteAnime {
        return with(viewState.value) {
            FavoriteAnime(
                id = anime.id,
                title = anime.getAnimeTitle(),
                studio = anime.studios?.firstOrNull()?.name.toString(),
                imageUrl = anime.images?.jpg?.imageUrl ?: ""
            )
        }
    }
}