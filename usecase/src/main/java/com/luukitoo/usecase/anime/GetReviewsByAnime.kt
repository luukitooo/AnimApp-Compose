package com.luukitoo.usecase.anime

import com.luukitoo.anime.domain.model.ReviewInfo
import com.luukitoo.anime.domain.repository.ReviewsRepository
import com.luukitoo.core.base.usecase.UseCase
import com.luukitoo.core.util.ResultStatus
import javax.inject.Inject

class GetReviewsByAnime @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) : UseCase<GetReviewsByAnimeParams, ResultStatus<ReviewInfo>> {

    override suspend fun execute(parameter: GetReviewsByAnimeParams): ResultStatus<ReviewInfo> {
        return reviewsRepository.getReviewsByAnime(
            animeId = parameter.animeId,
            page = parameter.page,
            isSpoiler = parameter.isSpoiler,
        )
    }
}

data class GetReviewsByAnimeParams(
    val animeId: Long,
    val page: Int,
    val isSpoiler: Boolean
)