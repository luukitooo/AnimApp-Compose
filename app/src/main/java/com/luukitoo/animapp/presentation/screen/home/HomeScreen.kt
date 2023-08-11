package com.luukitoo.animapp.presentation.screen.home

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.luukitoo.animapp.R
import com.luukitoo.animapp.component.FullscreenLoading
import com.luukitoo.animapp.component.PagerDots
import com.luukitoo.animapp.extension.openUrl
import com.luukitoo.animapp.extension.shareText
import com.luukitoo.animapp.presentation.navigation.NavDestination
import com.luukitoo.animapp.presentation.navigation.RouteArg
import com.luukitoo.animapp.presentation.screen.home.component.PrimaryImageBox
import com.luukitoo.animapp.presentation.screen.home.component.RatingBox
import com.luukitoo.animapp.presentation.screen.home.viewmodel.HomeEvent
import com.luukitoo.animapp.presentation.screen.home.viewmodel.HomeViewState
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme
import com.luukitoo.anime.domain.model.TopAnime
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewState: HomeViewState,
    onEvent: (HomeEvent) -> Unit,
    navController: NavController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val topAnimeScrollState = rememberLazyListState()
    val topMangaScrollState = rememberLazyListState()
    val pagerState = rememberPagerState()

    if (viewState.isLoading) {
        FullscreenLoading()
        return
    }

    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.clip(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp
                    )
                ),
                actions = {
                    FilledTonalIconButton(onClick = { context.shareText(viewState.topFiveAnime[pagerState.currentPage].url) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    }
                    FilledTonalIconButton(onClick = { context.openUrl(viewState.topFiveAnime[pagerState.currentPage].trailer?.url) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null
                        )
                    }
                    FilledTonalIconButton(onClick = { context.openUrl(viewState.topFiveAnime[pagerState.currentPage].url) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_globe),
                            contentDescription = null
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(NavDestination.Favorites.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            item {
                HorizontalPager(
                    pageCount = viewState.topFiveAnime.size,
                    state = pagerState,
                    key = { viewState.topFiveAnime[it].id ?: 0 },
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = pagerState,
                        snapAnimationSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                        )
                    )
                ) { index ->
                    PrimaryImageBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        url = viewState.topFiveAnime[index].images?.jpg?.largeImageUrl ?: "",
                        title = viewState.topFiveAnime[index].title ?: "",
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    PagerDots(
                        count = viewState.topFiveAnime.size,
                        state = pagerState
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Top Anime",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp
                        )
                    )
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            coroutineScope.launch {
                                topAnimeScrollState.animateScrollToItem(viewState.animeList.lastIndex)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(8.dp),
                    state = topAnimeScrollState
                ) {
                    items(viewState.animeList) { anime ->
                        RatingBox(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            rating = anime.score.toString()
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .height(250.dp)
                                    .clickable {
                                        navController.navigate(
                                            NavDestination.AnimeDetails(
                                                animeId = RouteArg("animeId", anime.id)
                                            ).route
                                        )
                                    },
                                model = anime.images?.jpg?.largeImageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    item {
                        OutlinedButton(onClick = { navController.navigate(NavDestination.TopAnimeList.route) }) {
                            Text(text = "Show All")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Top Manga",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp
                        )
                    )
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                topMangaScrollState.animateScrollToItem(viewState.mangaList.lastIndex)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(8.dp),
                    state = topMangaScrollState
                ) {
                    items(viewState.mangaList) { manga ->
                        RatingBox(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            rating = manga.score.toString()
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .height(250.dp)
                                    .clickable {
                                        navController.navigate(
                                            NavDestination.MangaDetails(
                                                mangaId = RouteArg("mangaId", manga.id)
                                            ).route
                                        )
                                    },
                                model = manga.images?.jpg?.largeImageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    item {
                        OutlinedButton(onClick = { navController.navigate(NavDestination.TopMangaList.route) }) {
                            Text(text = "Show All")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(50.dp)) }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AnimAppTheme {
        HomeScreen(
            viewState = HomeViewState(
                isLoading = false,
                topFiveAnime = listOf(
                    TopAnime.Anime(
                        images = TopAnime.Anime.Images(
                            jpg = TopAnime.Anime.Images.JPG(
                                imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/4/4a/Oppenheimer_%28film%29.jpg/220px-Oppenheimer_%28film%29.jpg"
                            )
                        ),
                        title = "Oppenheimer"
                    )
                )
            ),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}