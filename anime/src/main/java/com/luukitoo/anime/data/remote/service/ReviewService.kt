package com.luukitoo.anime.data.remote.service

import com.luukitoo.core.constant.NetworkConfig
import com.luukitoo.anime.data.remote.model.ReviewInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {

    @GET(NetworkConfig.AnimeReviews.ENDPOINT)
    suspend fun getReviewsByAnime(
        @Path(NetworkConfig.AnimeReviews.Path.ANIME_ID) animeId: Long,
        @Query(NetworkConfig.AnimeReviews.Query.PAGE) page: Int?,
        @Query(NetworkConfig.AnimeReviews.Query.SPOILER) isSpoiler: Boolean?
    ): Response<ReviewInfoDto>
}