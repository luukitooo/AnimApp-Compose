package com.luukitoo.animapp.presentation.screen.top_manga.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luukitoo.animapp.R
import com.luukitoo.animapp.component.ScoreIndicator
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme
import com.luukitoo.anime.domain.model.TopAnime
import com.luukitoo.manga.domain.model.TopManga

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopMangaListItem(
    modifier: Modifier = Modifier,
    manga: TopManga.Manga,
    onClick: (TopManga.Manga) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick.invoke(manga) },
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(15.dp)),
            model = manga.images?.jpg?.largeImageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee(
                    iterations = 50,
                    delayMillis = 1000,
                    initialDelayMillis = 2000
                ),
            text = manga.getMangaTitle(),
            maxLines = 1,
            fontSize = 20.sp
        )
        ScoreIndicator(
            score = manga.score ?: 0f,
            scoredBy = manga.scoredBy ?: 0
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview
@Composable
fun TopAnimeListItemPreview() {
    AnimAppTheme {
        Surface {
            TopMangaListItem(
                modifier = Modifier.fillMaxSize(),
                manga = TopManga.Manga(
                    title = "Attack on Titan",
                    score = 1204405f,
                    scoredBy = 231
                ),
                onClick = { }
            )
        }
    }
}