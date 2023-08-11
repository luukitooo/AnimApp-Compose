package com.luukitoo.anime.data.repository

import com.luukitoo.anime.data.remote.model.toReviewInfo
import com.luukitoo.anime.data.remote.service.ReviewService
import com.luukitoo.anime.domain.model.ReviewInfo
import com.luukitoo.anime.domain.repository.ReviewsRepository
import com.luukitoo.core.util.NetworkCaller
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewsRepository {

    override suspend fun getReviewsByAnime(
        animeId: Long,
        page: Int,
        isSpoiler: Boolean,
    ): ResultStatus<ReviewInfo> {
        return NetworkCaller.safeCall {
            reviewService.getReviewsByAnime(animeId, page, isSpoiler)
        }.mapData { reviewInfoDto ->
            reviewInfoDto.toReviewInfo()
        }
    }
}