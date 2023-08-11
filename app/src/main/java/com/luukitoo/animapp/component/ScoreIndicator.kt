package com.luukitoo.animapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@Composable
fun ScoreIndicator(score: Float, scoredBy: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "$score ($scoredBy)", fontSize = 14.sp)
        }
    }
}

@Preview
@Composable
fun ScoreIndicatorPreview() {
    AnimAppTheme {
        ScoreIndicator(score = 9.2f, scoredBy = 3221)
    }
}