package com.luukitoo.core.constant

object NetworkConfig {

    const val BASE_URL = "https://api.jikan.moe/v4/"

    object TopAnime {
        const val ENDPOINT = "top/anime"

        object Query {
            const val PAGE = "page"
            const val LIMIT = "limit"
        }
    }

    object AnimeDetails {
        const val ENDPOINT = "anime/{id}/full"

        object Path {
            const val ID = "id"
        }
    }

    object AnimeSearch {
        const val ENDPOINT = "anime"

        object Query {
            const val PAGE = "page"
            const val QUERY = "q"
        }
    }

    object AnimeCharacters {
        const val ENDPOINT = "anime/{id}/characters"

        object Path {
            const val ANIME_ID = "id"
        }
    }

    object AnimeReviews {
        const val ENDPOINT = "anime/{id}/reviews"

        object Path {
            const val ANIME_ID = "id"
        }

        object Query {
            const val PAGE = "page"
            const val SPOILER = "spoiler"
        }
    }

    object TopManga {
        const val ENDPOINT = "top/manga"

        object Query {
            const val PAGE = "page"
            const val LIMIT = "limit"
        }
    }

    object MangaDetails {
        const val ENDPOINT = "manga/{id}/full"

        object Path {
            const val ID = "id"
        }
    }

    object MangaSearch {
        const val ENDPOINT = "manga"

        object Query {
            const val PAGE = "page"
            const val QUERY = "q"
        }
    }
}