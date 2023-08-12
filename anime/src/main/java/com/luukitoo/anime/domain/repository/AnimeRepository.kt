package com.luukitoo.anime.domain.repository

import com.luukitoo.anime.domain.model.AnimeDetails
import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.core.util.ResultStatus
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    suspend fun getTopAnime(
        page: Int?,
        limit: Int?
    ): ResultStatus<TopAnime>

    suspend fun getAnimeDetails(
        id: Long
    ): ResultStatus<AnimeDetails>

    suspend fun searchAnime(
        page: Int?,
        query: String?
    ): ResultStatus<TopAnime>

    fun getFavoriteAnimeFlow(): Flow<List<FavoriteAnime>>

    suspend fun getFavoriteAnimeList(): List<FavoriteAnime>

    suspend fun saveAnimeToFavorites(anime: FavoriteAnime)

    suspend fun removeAnimeFromFavorites(animeId: Long)
}