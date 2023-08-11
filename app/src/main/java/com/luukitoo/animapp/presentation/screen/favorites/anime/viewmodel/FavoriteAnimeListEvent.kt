package com.luukitoo.animapp.presentation.screen.favorites.anime.viewmodel

sealed class FavoriteAnimeListEvent {
    object GetFavoriteAnime : FavoriteAnimeListEvent()
}