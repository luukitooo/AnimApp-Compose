package com.luukitoo.database.manga

import entity.MangaEntity
import kotlinx.coroutines.flow.Flow

interface MangaDataSource {

    fun getFavoriteMangaFlow(): Flow<List<MangaEntity>>

    suspend fun getFavoriteMangaList(): List<MangaEntity>

    suspend fun saveMangaToFavorites(
        id: Long? = null,
        title: String,
        author: String,
        imageUrl: String
    )

    suspend fun removeMangaFromFavorites(mangaId: Long)
}