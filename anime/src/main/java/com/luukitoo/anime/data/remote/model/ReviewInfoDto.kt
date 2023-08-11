package com.luukitoo.anime.data.remote.model

import com.google.gson.annotations.SerializedName
import com.luukitoo.anime.domain.model.ReviewInfo

data class ReviewInfoDto(
    val pagination: PaginationDto? = null,
    val data: List<ReviewDataDto>? = null
) {
    data class PaginationDto(
        @SerializedName("last_visible_page")
        val lastVisiblePage: Int? = null,
        val hasNextPage: Boolean? = null,
    )

    data class ReviewDataDto(
        @SerializedName("mal_id")
        val id: Long? = null,
        val url: String? = null,
        val type: String? = null,
        val reactions: ReactionsDto? = null,
        val date: String? = null,
        val review: String? = null,
        val score: Int? = null,
        @SerializedName("is_spoiler")
        val isSpoiler: Boolean? = null,
        val user: UserDto? = null
    ) {
        data class ReactionsDto(
            val overall: Int? = null,
            val nice: Int? = null,
            @SerializedName("love_it")
            val loveIt: Int? = null,
            val funny: Int? = null,
            val confusing: Int? = null,
            val informative: Int? = null,
            @SerializedName("well_written")
            val wellWritten: Int? = null,
            val creative: Int? = null
        )

        data class UserDto(
            val url: String? = null,
            val username: String? = null,
            val images: ImagesDto? = null
        ) {
            data class ImagesDto(
                val jpg: JPGDto? = null
            ) {
                data class JPGDto(
                    @SerializedName("image_url")
                    val imageUrl: String? = null
                )
            }
        }
    }
}

fun ReviewInfoDto.ReviewDataDto.UserDto.ImagesDto.JPGDto.toJPG() = ReviewInfo.ReviewData.User.Images.JPG(
    imageUrl = imageUrl
)

fun ReviewInfoDto.ReviewDataDto.UserDto.ImagesDto.toImages() = ReviewInfo.ReviewData.User.Images(
    jpg = jpg?.toJPG()
)

fun ReviewInfoDto.ReviewDataDto.UserDto.toUser() = ReviewInfo.ReviewData.User(
    url = url,
    username = username,
    images = images?.toImages()
)

fun ReviewInfoDto.ReviewDataDto.ReactionsDto.toReactions() = ReviewInfo.ReviewData.Reactions(
    overall = overall,
    nice = nice,
    loveIt = loveIt,
    funny = funny,
    confusing = confusing,
    informative = informative,
    wellWritten = wellWritten,
    creative = creative
)

fun ReviewInfoDto.ReviewDataDto.toReviewData() = ReviewInfo.ReviewData(
    id = id,
    url = url,
    type = type,
    reactions = reactions?.toReactions(),
    date = date,
    review = review,
    score = score,
    isSpoiler = isSpoiler,
    user = user?.toUser()
)

fun ReviewInfoDto.PaginationDto.toPagination() = ReviewInfo.Pagination(
    lastVisiblePage = lastVisiblePage,
    hasNextPage = hasNextPage
)

fun ReviewInfoDto.toReviewInfo() = ReviewInfo(
    pagination = pagination?.toPagination(),
    data = data?.map(ReviewInfoDto.ReviewDataDto::toReviewData)
)
