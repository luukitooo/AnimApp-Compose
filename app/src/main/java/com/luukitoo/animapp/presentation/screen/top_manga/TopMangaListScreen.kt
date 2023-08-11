package com.luukitoo.animapp.presentation.screen.top_manga

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.luukitoo.animapp.component.FullscreenLoading
import com.luukitoo.animapp.component.SearchField
import com.luukitoo.animapp.presentation.navigation.NavDestination
import com.luukitoo.animapp.presentation.navigation.RouteArg
import com.luukitoo.animapp.presentation.screen.top_manga.component.TopMangaListItem
import com.luukitoo.animapp.presentation.screen.top_manga.viewmodel.TopMangaListEvent
import com.luukitoo.animapp.presentation.screen.top_manga.viewmodel.TopMangaListViewState
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMangaListScreen(
    viewState: TopMangaListViewState,
    onEvent: (TopMangaListEvent) -> Unit,
    navController: NavController
) {

    val mainScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(mainScrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Most Popular Manga") },
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
                    FilledTonalIconButton(onClick = { onEvent(TopMangaListEvent.ToggleSearch) }) {
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
                        onEvent(TopMangaListEvent.UpdateSearchQuery(it))
                    },
                    onSearch = {
                        onEvent(TopMangaListEvent.SearchManga(it))
                    }
                )
            }
            if (!viewState.isLoading) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                ) {
                    items(viewState.topMangaList) { manga ->
                        TopMangaListItem(
                            modifier = Modifier.padding(8.dp),
                            manga = manga,
                            onClick = {
                                navController.navigate(
                                    NavDestination.MangaDetails(
                                        mangaId = RouteArg("mangaId", manga.id)
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

@Composable
fun TopMangaListScreenPreview() {
    AnimAppTheme {
        TopMangaListScreen(
            viewState = TopMangaListViewState(),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}