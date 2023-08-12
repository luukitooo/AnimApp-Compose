package com.luukitoo.database.anime

import entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface AnimeDataSource {

    fun getFavoriteAnimeFlow(): Flow<List<AnimeEntity>>

    suspend fun getFavoriteAnimeList(): List<AnimeEntity>

    suspend fun saveAnimeToFavorites(
        id: Long? = null,
        title: String,
        studio: String,
        imageUrl: String
    )

    suspend fun removeAnimeFromFavorites(animeId: Long)
}