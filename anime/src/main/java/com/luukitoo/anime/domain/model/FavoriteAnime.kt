package com.luukitoo.anime.domain.model

import entity.AnimeEntity

data class FavoriteAnime(
    val id: Long? = null,
    val title: String,
    val studio: String,
    val imageUrl: String
) {

    companion object {
        fun fromEntity(entity: AnimeEntity) = FavoriteAnime(
            id = entity.id,
            title = entity.title,
            studio = entity.studio,
            imageUrl = entity.imageUrl
        )
    }
}
