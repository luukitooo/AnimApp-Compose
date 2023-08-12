package com.luukitoo.animapp.presentation.screen.favorites

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.luukitoo.animapp.presentation.navigation.NavDestination
import com.luukitoo.animapp.presentation.navigation.RouteArg
import com.luukitoo.animapp.presentation.screen.favorites.component.FavoriteCategoriesRow
import com.luukitoo.animapp.presentation.screen.favorites.component.FavoriteListItem
import com.luukitoo.animapp.presentation.screen.favorites.helper.FavoriteType
import com.luukitoo.animapp.presentation.screen.favorites.viewmodel.FavoriteCategory
import com.luukitoo.animapp.presentation.screen.favorites.viewmodel.FavoritesListEvent
import com.luukitoo.animapp.presentation.screen.favorites.viewmodel.FavoritesListViewState
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FavoriteAnimeListScreen(
    viewState: FavoritesListViewState,
    onEvent: (FavoritesListEvent) -> Unit,
    navController: NavController
) {

    val mainScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(mainScrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Favorites") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = mainScrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                FavoriteCategoriesRow(
                    modifier = Modifier.fillMaxWidth(),
                    categories = FavoriteCategory.values(),
                    selectedCategory = viewState.selectedCategory,
                    onClick = { category ->
                        onEvent(FavoritesListEvent.SetCategory(category))
                    }
                )
            }
            items(
                key = { it.id ?: -1 },
                items = viewState.favoritesList,
            ) { favoriteItem ->
                FavoriteListItem(
                    modifier = Modifier.fillMaxWidth(),
                    favoriteItemModel = favoriteItem,
                    onClick = {
                        when (favoriteItem.type) {
                            FavoriteType.ANIME -> navController.navigate(
                                NavDestination.AnimeDetails(
                                    animeId = RouteArg("animeId", favoriteItem.id)
                                ).route
                            )
                            FavoriteType.MANGA -> navController.navigate(
                                NavDestination.MangaDetails(
                                    mangaId = RouteArg("mangaId", favoriteItem.id)
                                ).route
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoriteAnimeListScreenPreview() {
    AnimAppTheme {
        FavoriteAnimeListScreen(
            viewState = FavoritesListViewState(),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}