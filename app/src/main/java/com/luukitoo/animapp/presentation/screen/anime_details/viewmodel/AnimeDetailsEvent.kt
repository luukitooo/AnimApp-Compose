package com.luukitoo.animapp.presentation.screen.anime_details.viewmodel

sealed class AnimeDetailsEvent {
    data class GetDetails(val id: Long) : AnimeDetailsEvent()
    data class GetCharacters(val animeId: Long) : AnimeDetailsEvent()
    object SaveToFavorites : AnimeDetailsEvent()
    object RemoveFromFavorites : AnimeDetailsEvent()
}