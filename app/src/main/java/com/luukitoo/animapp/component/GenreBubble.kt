package com.luukitoo.animapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@Composable
fun GenreBubble(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = CircleShape
            )
            .padding(
                horizontal = 12.dp,
                vertical = 2.dp
            ),
        text = text,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        fontSize = 10.sp
    )
}

@Preview
@Composable
fun GenreBubblePreview() {
    AnimAppTheme {
        GenreBubble(text = "Drama")
    }
}