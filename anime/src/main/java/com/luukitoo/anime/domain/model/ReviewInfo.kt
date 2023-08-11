package com.luukitoo.anime.domain.model

data class ReviewInfo(
    val pagination: Pagination? = null,
    val data: List<ReviewData>? = null
) {
    data class Pagination(
        val lastVisiblePage: Int? = null,
        val hasNextPage: Boolean? = null,
    )

    data class ReviewData(
        val id: Long? = null,
        val url: String? = null,
        val type: String? = null,
        val reactions: Reactions? = null,
        val date: String? = null,
        val review: String? = null,
        val score: Int? = null,
        val isSpoiler: Boolean? = null,
        val user: User? = null
    ) {
        data class Reactions(
            val overall: Int? = null,
            val nice: Int? = null,
            val loveIt: Int? = null,
            val funny: Int? = null,
            val confusing: Int? = null,
            val informative: Int? = null,
            val wellWritten: Int? = null,
            val creative: Int? = null
        )

        data class User(
            val url: String? = null,
            val username: String? = null,
            val images: Images? = null
        ) {
            data class Images(
                val jpg: JPG? = null
            ) {
                data class JPG(
                    val imageUrl: String? = null
                )
            }
        }
    }
}
