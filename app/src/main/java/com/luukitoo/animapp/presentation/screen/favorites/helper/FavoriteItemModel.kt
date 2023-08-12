package com.luukitoo.animapp.presentation.screen.favorites.helper

import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.manga.domain.model.FavoriteManga

data class FavoriteItemModel(
    val id: Long?,
    val title: String,
    val by: String,
    val imageUrl: String,
    val type: FavoriteType
) {
    companion object {

        fun fromFavoriteAnime(anime: FavoriteAnime) = FavoriteItemModel(
            id = anime.id,
            title = anime.title,
            by = anime.studio,
            imageUrl = anime.imageUrl,
            type = FavoriteType.ANIME
        )

        fun fromFavoriteManga(manga: FavoriteManga) = FavoriteItemModel(
            id = manga.id,
            title = manga.title,
            by = manga.author,
            imageUrl = manga.imageUrl,
            type = FavoriteType.MANGA
        )
    }
}

enum class FavoriteType { ANIME, MANGA }