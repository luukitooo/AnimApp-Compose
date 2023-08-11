package com.luukitoo.animapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luukitoo.animapp.presentation.screen.anime_details.AnimeDetailsScreen
import com.luukitoo.animapp.presentation.screen.anime_details.viewmodel.AnimeDetailsViewModel
import com.luukitoo.animapp.presentation.screen.favorites.anime.FavoriteAnimeListScreen
import com.luukitoo.animapp.presentation.screen.favorites.anime.viewmodel.FavoriteAnimeListViewModel
import com.luukitoo.animapp.presentation.screen.home.HomeScreen
import com.luukitoo.animapp.presentation.screen.home.viewmodel.HomeViewModel
import com.luukitoo.animapp.presentation.screen.manga_details.MangaDetailsScreen
import com.luukitoo.animapp.presentation.screen.manga_details.viewmodel.MangaDetailsViewModel
import com.luukitoo.animapp.presentation.screen.top_anime.TopAnimeListScreen
import com.luukitoo.animapp.presentation.screen.top_anime.viewmodel.TopAnimeListViewModel
import com.luukitoo.animapp.presentation.screen.top_manga.TopMangaListScreen
import com.luukitoo.animapp.presentation.screen.top_manga.viewmodel.TopMangaListViewModel

@Composable
fun MainNavHost() {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavDestination.Home.route
    ) {

        composable(NavDestination.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            HomeScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }

        composable(NavDestination.TopAnimeList.route) {
            val viewModel = hiltViewModel<TopAnimeListViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            TopAnimeListScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }

        composable(
            NavDestination.AnimeDetails().route,
            arguments = listOf(
                navArgument("animeId") {
                    type = NavType.LongType
                }
            )
        ) {
            val viewModel = hiltViewModel<AnimeDetailsViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            AnimeDetailsScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }

        composable(NavDestination.TopMangaList.route) {
            val viewModel = hiltViewModel<TopMangaListViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            TopMangaListScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }

        composable(
            NavDestination.MangaDetails().route,
            arguments = listOf(
                navArgument("mangaId"){
                    type = NavType.LongType
                }
            )
        ) {
            val viewModel = hiltViewModel<MangaDetailsViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            MangaDetailsScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }

        composable(NavDestination.Favorites.route) {
            val viewModel = hiltViewModel<FavoriteAnimeListViewModel>()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            FavoriteAnimeListScreen(
                viewState = state,
                onEvent = viewModel::onEvent,
                navController = navHostController
            )
        }
    }
}
