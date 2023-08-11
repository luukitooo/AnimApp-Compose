package com.luukitoo.anime.data.repository

import com.luukitoo.anime.data.remote.model.toTopAnime
import com.luukitoo.anime.data.remote.service.AnimeService
import com.luukitoo.anime.domain.model.AnimeDetails
import com.luukitoo.anime.domain.model.FavoriteAnime
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.anime.domain.repository.AnimeRepository
import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.database.AnimeDataSource
import entity.AnimeEntityQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeService: AnimeService,
    private val animeDataSource: AnimeDataSource
) : AnimeRepository {

    override suspend fun getTopAnime(
        page: Int?,
        limit: Int?,
    ): ResultStatus<TopAnime> {
        return NetworkCaller.safeCall {
            animeService.getTopAnime(page, limit)
        }.mapData { topAnimeDto ->
            topAnimeDto.toTopAnime()
        }
    }

    override suspend fun getAnimeDetails(id: Long): ResultStatus<AnimeDetails> {
        return NetworkCaller.safeCall {
            animeService.getAnimeDetails(id)
        }.mapData { animeDetailsDto ->
            animeDetailsDto.toAnimeDetails()
        }
    }

    override suspend fun searchAnime(
        page: Int?,
        query: String?
    ): ResultStatus<TopAnime> {
        return NetworkCaller.safeCall {
            animeService.searchAnime(page, query)
        }.mapData { topAnimeDto ->
            topAnimeDto.toTopAnime()
        }
    }

    override fun getFavoriteAnimeFlow(): Flow<List<FavoriteAnime>> {
        return animeDataSource.getFavoriteAnimeFlow().map { animeEntities ->
            animeEntities.map { animeEntity ->
                FavoriteAnime.fromEntity(animeEntity)
            }
        }
    }

    override fun getFavoriteAnimeList(): List<FavoriteAnime> {
        return animeDataSource.getFavoriteAnimeList().map {
            FavoriteAnime.fromEntity(it)
        }
    }

    override suspend fun saveAnimeToFavorites(anime: FavoriteAnime) {
        animeDataSource.saveAnimeToFavorites(
            id = anime.id,
            title = anime.title,
            studio = anime.studio,
            imageUrl = anime.imageUrl
        )
    }

    override suspend fun removeAnimeFromFavorites(animeId: Long) {
        animeDataSource.removeAnimeFromFavorites(animeId)
    }
}