package com.luukitoo.manga.domain.model

import entity.MangaEntity

data class FavoriteManga(
    val id: Long?,
    val title: String,
    val author: String,
    val imageUrl: String
) {
    companion object {
        fun fromEntity(entity: MangaEntity) = FavoriteManga(
            id = entity.id,
            title = entity.title,
            author = entity.author,
            imageUrl = entity.imageUrl
        )
    }
}
