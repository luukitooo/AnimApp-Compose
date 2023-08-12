package com.luukitoo.animapp.presentation.screen.favorites.viewmodel

sealed class FavoritesListEvent {
    data class SetCategory(val category: FavoriteCategory) : FavoritesListEvent()
}