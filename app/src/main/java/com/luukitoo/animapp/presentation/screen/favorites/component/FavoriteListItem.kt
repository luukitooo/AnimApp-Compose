package com.luukitoo.animapp.presentation.screen.favorites.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FavoriteListItem(
    modifier: Modifier = Modifier,
    title: String,
    by: String,
    imageUrl: String,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.height(100.dp),
                model = imageUrl,
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    modifier = Modifier.basicMarquee(
                        iterations = Int.MAX_VALUE,
                        delayMillis = 3000
                    ),
                    text = title,
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "by $by",
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoriteListItemPreview() {
    AnimAppTheme {
        FavoriteListItem(
            title = "Attack on Titan",
            by = "MAPPA",
            imageUrl = "",
            onClick = { }
        )
    }
}