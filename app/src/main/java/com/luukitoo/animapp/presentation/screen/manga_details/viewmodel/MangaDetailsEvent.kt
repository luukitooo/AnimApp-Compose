package com.luukitoo.animapp.presentation.screen.manga_details.viewmodel

sealed class MangaDetailsEvent {
    data class GetManga(val id: Long): MangaDetailsEvent()
    object CheckIfIsFavorite : MangaDetailsEvent()
    object SaveToFavorites : MangaDetailsEvent()
    object RemoveFromFavorites : MangaDetailsEvent()
}
