package com.luukitoo.animapp.presentation.screen.top_anime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
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
import com.luukitoo.animapp.component.FullscreenLoading
import com.luukitoo.animapp.component.SearchField
import com.luukitoo.animapp.presentation.navigation.NavDestination
import com.luukitoo.animapp.presentation.navigation.RouteArg
import com.luukitoo.animapp.presentation.screen.top_anime.component.TopAnimeListItem
import com.luukitoo.animapp.presentation.screen.top_anime.viewmodel.TopAnimeListEvent
import com.luukitoo.animapp.presentation.screen.top_anime.viewmodel.TopAnimeListViewState
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAnimeListScreen(
    viewState: TopAnimeListViewState,
    onEvent: (TopAnimeListEvent) -> Unit,
    navController: NavController,
) {

    val mainScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(mainScrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Most Popular Anime") },
                scrollBehavior = mainScrollBehavior,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    FilledTonalIconButton(onClick = { onEvent(TopAnimeListEvent.ToggleSearch) }) {
                        Icon(
                            imageVector = if (viewState.isSearchVisible) {
                                Icons.Default.Close
                            } else {
                                Icons.Default.Search
                            },
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            AnimatedVisibility(visible = viewState.isSearchVisible) {
                SearchField(
                    modifier = Modifier.padding(8.dp),
                    query = viewState.searchQuery,
                    onQueryChange = {
                        onEvent(TopAnimeListEvent.UpdateSearchQuery(it))
                    },
                    onSearch = {
                        onEvent(TopAnimeListEvent.SearchAnime(it))
                    }
                )
            }
            if (!viewState.isLoading) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                ) {
                    items(viewState.topAnimeList) { anime ->
                        TopAnimeListItem(
                            modifier = Modifier.padding(8.dp),
                            anime = anime,
                            onClick = {
                                navController.navigate(
                                    NavDestination.AnimeDetails(
                                        animeId = RouteArg("animeId", anime.id)
                                    ).route
                                )
                            }
                        )
                    }
                }
            } else {
                FullscreenLoading()
            }
        }
    }
}

@Preview
@Composable
fun TopAnimeListScreenPreview() {
    AnimAppTheme {
        TopAnimeListScreen(
            viewState = TopAnimeListViewState(),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}