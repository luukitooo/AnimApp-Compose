package com.luukitoo.animapp.presentation.screen.favorites.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luukitoo.animapp.presentation.screen.favorites.viewmodel.FavoriteCategory
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteCategoriesRow(
    modifier: Modifier = Modifier,
    categories: Array<FavoriteCategory>,
    selectedCategory: FavoriteCategory = FavoriteCategory.ALL,
    onClick: (FavoriteCategory) -> Unit
) {

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categories) { category ->
            FilterChip(
                label = { Text(text = category.title,) },
                selected = selectedCategory == category,
                onClick = { onClick.invoke(category) },
            )
        }
    }
}

@Preview
@Composable
fun FavoriteCategoriesRowPreview() {
    AnimAppTheme {
        FavoriteCategoriesRow(
            categories = FavoriteCategory.values(),
            onClick = { }
        )
    }
}