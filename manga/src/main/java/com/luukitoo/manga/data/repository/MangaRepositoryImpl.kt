package com.luukitoo.manga.data.repository

import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import com.luukitoo.database.manga.MangaDataSource
import com.luukitoo.manga.data.remote.model.toTopManga
import com.luukitoo.manga.data.remote.service.MangaService
import com.luukitoo.manga.domain.model.FavoriteManga
import com.luukitoo.manga.domain.model.MangaDetails
import com.luukitoo.manga.domain.model.TopManga
import com.luukitoo.manga.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaService: MangaService,
    private val mangaDataSource: MangaDataSource
) : MangaRepository {

    override suspend fun getTopManga(
        page: Int?,
        limit: Int?
    ): ResultStatus<TopManga> {
        return NetworkCaller.safeCall {
            mangaService.getTopManga(page, limit)
        }.mapData { topMangaDto ->
            topMangaDto.toTopManga()
        }
    }

    override suspend fun getMangaDetails(id: Long?): ResultStatus<MangaDetails> {
        return NetworkCaller.safeCall {
            mangaService.getMangaDetails(id)
        }.mapData { mangaDetailsDto ->
            mangaDetailsDto.toMangaDetails()
        }
    }

    override suspend fun searchManga(
        page: Int?,
        query: String?
    ): ResultStatus<TopManga> {
        return NetworkCaller.safeCall {
            mangaService.searchManga(page, query)
        }.mapData { topMangaDto ->
            topMangaDto.toTopManga()
        }
    }

    override fun getFavoriteMangaFlow(): Flow<List<FavoriteManga>> {
        return mangaDataSource.getFavoriteMangaFlow().map { mangaEntities ->
            mangaEntities.map { mangaEntity ->
                FavoriteManga.fromEntity(mangaEntity)
            }
        }
    }

    override suspend fun getFavoriteMangaList(): List<FavoriteManga> {
        return mangaDataSource.getFavoriteMangaList().map { mangaEntity ->
            FavoriteManga.fromEntity(mangaEntity)
        }
    }

    override suspend fun saveMangaToFavorites(favoriteManga: FavoriteManga) {
        mangaDataSource.saveMangaToFavorites(
            id = favoriteManga.id,
            title = favoriteManga.title,
            author = favoriteManga.author,
            imageUrl = favoriteManga.imageUrl
        )
    }

    override suspend fun removeMangaFromFavorites(mangaId: Long) {
        mangaDataSource.removeMangaFromFavorites(mangaId)
    }
}