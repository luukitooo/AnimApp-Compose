package com.luukitoo.database

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import entity.AnimeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeDataSourceImpl @Inject constructor(
    private val database: AnimAppDatabase
) : AnimeDataSource {

    override fun getFavoriteAnimeFlow(): Flow<List<AnimeEntity>> {
        return database.animeEntityQueries
            .getFavoriteAnimeList()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    override fun getFavoriteAnimeList(): List<AnimeEntity> {
        return database.animeEntityQueries
            .getFavoriteAnimeList()
            .executeAsList()
    }

    override suspend fun saveAnimeToFavorites(
        id: Long?,
        title: String,
        studio: String,
        imageUrl: String
    ) {
        database.animeEntityQueries.saveAnimeToFavorites(
            id = id,
            title = title,
            studio = studio,
            imageUrl = imageUrl
        )
    }

    override suspend fun removeAnimeFromFavorites(animeId: Long) {
        database.animeEntityQueries.removeAnimeFromFavorites(animeId)
    }
}