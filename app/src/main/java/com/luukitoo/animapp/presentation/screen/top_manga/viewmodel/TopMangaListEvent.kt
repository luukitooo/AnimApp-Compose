package com.luukitoo.animapp.presentation.screen.top_manga.viewmodel

sealed class TopMangaListEvent {
    object ToggleSearch : TopMangaListEvent()
    data class UpdateSearchQuery(val newValue: String) : TopMangaListEvent()
    data class GetTopManga(val page: Int = 1) : TopMangaListEvent()
    data class SearchManga(val query: String) : TopMangaListEvent()
}