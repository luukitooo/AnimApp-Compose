package com.luukitoo.animapp.presentation.screen.home.viewmodel

sealed class HomeEvent {
    object GetAnimeList: HomeEvent()
    object GetMangaList: HomeEvent()
}