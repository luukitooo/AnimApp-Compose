package com.luukitoo.anime.domain.repository

import com.luukitoo.anime.domain.model.ReviewInfo
import com.luukitoo.core.util.ResultStatus

interface ReviewsRepository {

    suspend fun getReviewsByAnime(
        animeId: Long,
        page: Int,
        isSpoiler: Boolean
    ): ResultStatus<ReviewInfo>
}