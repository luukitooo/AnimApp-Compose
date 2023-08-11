package com.luukitoo.animapp.presentation.screen.top_manga.viewmodel

import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.mvi.viewmodel.ViewState

data class TopMangaListViewState(
    val searchQuery: String = "",
    val isSearchVisible: Boolean = false,
    val topMangaList: List<TopManga.Manga> = emptyList(),
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null
) : ViewState()