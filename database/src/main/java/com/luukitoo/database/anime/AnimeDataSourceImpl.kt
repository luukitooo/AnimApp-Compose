package com.luukitoo.database.anime

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import entity.AnimeEntity
import entity.AnimeEntityQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeDataSourceImpl @Inject constructor(
    private val queries: AnimeEntityQueries
) : AnimeDataSource {

    override fun getFavoriteAnimeFlow(): Flow<List<AnimeEntity>> {
        return queries.getFavoriteAnimeList()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    override suspend fun getFavoriteAnimeList(): List<AnimeEntity> {
        return queries.getFavoriteAnimeList().executeAsList()
    }

    override suspend fun saveAnimeToFavorites(
        id: Long?,
        title: String,
        studio: String,
        imageUrl: String
    ) {
        queries.saveAnimeToFavorites(
            id = id,
            title = title,
            studio = studio,
            imageUrl = imageUrl
        )
    }

    override suspend fun removeAnimeFromFavorites(animeId: Long) {
        queries.removeAnimeFromFavorites(animeId)
    }
}