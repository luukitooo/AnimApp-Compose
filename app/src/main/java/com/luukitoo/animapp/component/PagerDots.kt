package com.luukitoo.animapp.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerDots(
    modifier: Modifier = Modifier,
    count: Int,
    state: PagerState
) {

    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(16.dp),
            onClick = {
                scope.launch {
                    state.animateScrollToPage(state.currentPage - 1)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        (0 until count).forEach {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (it == state.currentPage) {
                            MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.secondaryContainer
                        },
                        shape = CircleShape
                    )
            )
        }
        IconButton(
            modifier = Modifier.size(16.dp),
            onClick = {
                scope.launch {
                    state.animateScrollToPage(state.currentPage + 1)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PagerDotsPreview() {
    AnimAppTheme {
        PagerDots(
            count = 5,
            state = rememberPagerState()
        )
    }
}