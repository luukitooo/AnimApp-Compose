package com.luukitoo.animapp.presentation.screen.manga_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.luukitoo.animapp.component.ExpandableText
import com.luukitoo.animapp.component.FullscreenLoading
import com.luukitoo.animapp.component.GenreBubble
import com.luukitoo.animapp.presentation.screen.anime_details.component.YouTubePlayer
import com.luukitoo.animapp.presentation.screen.anime_details.viewmodel.AnimeDetailsEvent
import com.luukitoo.animapp.presentation.screen.manga_details.viewmodel.MangaDetailsEvent
import com.luukitoo.animapp.presentation.screen.manga_details.viewmodel.MangaDetailsViewState
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun MangaDetailsScreen(
    viewState: MangaDetailsViewState,
    onEvent: (MangaDetailsEvent) -> Unit,
    navController: NavController
) {

    val mainScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    if (viewState.isLoading) {
        FullscreenLoading()
        return
    }

    Scaffold(
        modifier = Modifier.nestedScroll(mainScrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = mainScrollBehavior,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    AnimatedVisibility(
                        visible = mainScrollBehavior.state.overlappedFraction > 0.9,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            modifier = Modifier.basicMarquee(
                                iterations = 50,
                                delayMillis = 3000,
                            ),
                            maxLines = 1,
                            text = viewState.manga.getMangaTitle()
                        )
                    }
                },
                actions = {
                    FilledTonalIconButton(
                        onClick = {
                            if (viewState.isFavorite) {
                                onEvent(MangaDetailsEvent.RemoveFromFavorites)
                            } else {
                                onEvent(MangaDetailsEvent.SaveToFavorites)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (viewState.isFavorite) {
                                Icons.Outlined.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 36.dp,
                            vertical = 12.dp
                        ),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(0.25f)
                            .clip(RoundedCornerShape(10.dp)),
                        model = viewState.manga.images?.jpg?.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column {
                        Text(
                            text = viewState.manga.getMangaTitle(),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "by ${viewState.manga.authors?.firstOrNull()?.name}",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        FlowRow(
                            modifier = Modifier.padding(top = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            viewState.manga.genres?.forEach {
                                GenreBubble(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = it.name.toString()
                                )
                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(start = 36.dp, top = 32.dp),
                    text = "Overview",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                ExpandableText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 36.dp, end = 36.dp),
                    text = viewState.manga.synopsis ?: "",
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    ),
                    minLines = 12
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 36.dp)
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Score",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Text(
                            text = viewState.manga.score.toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Scored By",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Text(
                            text = viewState.manga.scoredBy.toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview
@Composable
fun MangaDetailsScreenPreview() {
    AnimAppTheme {
        MangaDetailsScreen(
            viewState = MangaDetailsViewState(),
            onEvent = { },
            navController = rememberNavController()
        )
    }
}