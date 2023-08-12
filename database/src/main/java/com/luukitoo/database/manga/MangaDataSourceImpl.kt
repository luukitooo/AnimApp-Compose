package com.luukitoo.database.manga

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import entity.MangaEntity
import entity.MangaEntityQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaDataSourceImpl @Inject constructor(
    private val queries: MangaEntityQueries
) : MangaDataSource {

    override fun getFavoriteMangaFlow(): Flow<List<MangaEntity>> {
        return queries.getFavoriteMangaList()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
    }

    override suspend fun getFavoriteMangaList(): List<MangaEntity> {
        return queries.getFavoriteMangaList().executeAsList()
    }

    override suspend fun saveMangaToFavorites(
        id: Long?,
        title: String,
        author: String,
        imageUrl: String
    ) {
        queries.saveMangaToFavorites(
            id = id,
            title = title,
            author = author,
            imageUrl = imageUrl
        )
    }

    override suspend fun removeMangaFromFavorites(mangaId: Long) {
        queries.removeMangaFromFavorites(mangaId)
    }
}