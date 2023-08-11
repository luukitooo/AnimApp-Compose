package com.luukitoo.anime.data.remote.service

import com.luukitoo.anime.data.remote.model.AnimeDetailsDto
import com.luukitoo.core.constant.NetworkConfig
import com.luukitoo.anime.data.remote.model.TopAnimeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET(NetworkConfig.TopAnime.ENDPOINT)
    suspend fun getTopAnime(
        @Query(NetworkConfig.TopAnime.Query.PAGE) page: Int?,
        @Query(NetworkConfig.TopAnime.Query.LIMIT) limit: Int?
    ): Response<TopAnimeDto>

    @GET(NetworkConfig.AnimeDetails.ENDPOINT)
    suspend fun getAnimeDetails(
        @Path(NetworkConfig.AnimeDetails.Path.ID) id: Long
    ): Response<AnimeDetailsDto>

    @GET(NetworkConfig.AnimeSearch.ENDPOINT)
    suspend fun searchAnime(
        @Query(NetworkConfig.AnimeSearch.Query.PAGE) page: Int?,
        @Query(NetworkConfig.AnimeSearch.Query.QUERY) query: String?
    ): Response<TopAnimeDto>
}