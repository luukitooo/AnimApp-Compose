package com.luukitoo.manga.data.remote.service

import com.luukitoo.core.constant.NetworkConfig
import com.luukitoo.manga.data.remote.model.MangaDetailsDto
import com.luukitoo.manga.data.remote.model.TopMangaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaService {

    @GET(NetworkConfig.TopManga.ENDPOINT)
    suspend fun getTopManga(
        @Query(NetworkConfig.TopManga.Query.PAGE) page: Int?,
        @Query(NetworkConfig.TopManga.Query.LIMIT) limit: Int?,
    ): Response<TopMangaDto>

    @GET(NetworkConfig.MangaDetails.ENDPOINT)
    suspend fun getMangaDetails(
        @Path(NetworkConfig.MangaDetails.Path.ID) id: Long?
    ): Response<MangaDetailsDto>

    @GET(NetworkConfig.MangaSearch.ENDPOINT)
    suspend fun searchManga(
        @Query(NetworkConfig.MangaSearch.Query.PAGE) page: Int?,
        @Query(NetworkConfig.MangaSearch.Query.QUERY) query: String?
    ): Response<TopMangaDto>
}