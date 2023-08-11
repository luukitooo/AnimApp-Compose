package com.luukitoo.animapp.presentation.screen.anime_details.viewmodel

import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.characters.domain.model.CharacterInfo
import com.luukitoo.core.util.Disposable
import com.luukitoo.mvi.viewmodel.ViewState

data class AnimeDetailsViewState(
    val anime: TopAnime.Anime = TopAnime.Anime(),
    val characters: List<CharacterInfo.CharacterData.Character> = emptyList(),
    val isFavorite: Boolean = false,
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null
) : ViewState()
