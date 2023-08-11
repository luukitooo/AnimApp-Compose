package com.luukitoo.animapp.presentation.screen.top_anime.viewmodel

sealed class TopAnimeListEvent {
    data class GetTopAnime(val page: Int = 1) : TopAnimeListEvent()
    object ToggleSearch : TopAnimeListEvent()
    data class UpdateSearchQuery(val newValue: String) : TopAnimeListEvent()
    data class SearchAnime(val query: String) : TopAnimeListEvent()
}