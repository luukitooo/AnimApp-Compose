package com.luukitoo.animapp.presentation.screen.manga_details.viewmodel

import com.luukitoo.core.util.Disposable
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.mvi.viewmodel.ViewState

data class MangaDetailsViewState(
    val manga: TopManga.Manga = TopManga.Manga(),
    val isFavorite: Boolean = false,
    override val isLoading: Boolean = false,
    override val throwable: Disposable<Throwable>? = null
) : ViewState()
