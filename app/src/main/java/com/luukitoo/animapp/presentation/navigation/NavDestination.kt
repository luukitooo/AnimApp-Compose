package com.luukitoo.animapp.presentation.navigation

sealed class NavDestination(val route: String) {

    object Home : NavDestination("home")

    object TopAnimeList : NavDestination(route = "top_anime_list")

    data class AnimeDetails(
        val animeId: RouteArg = RouteArg("animeId")
    ) : NavDestination(
        route = "anime_details/${animeId.process()}"
    )

    object TopMangaList : NavDestination(route = "top_manga_list")

    data class MangaDetails(
        val mangaId: RouteArg = RouteArg("mangaId")
    ) : NavDestination(
        route = "manga_details/${mangaId.process()}"
    )
}