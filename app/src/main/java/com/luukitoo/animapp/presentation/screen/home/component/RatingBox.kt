package com.luukitoo.animapp.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingBox(
    modifier: Modifier = Modifier,
    rating: String,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        content.invoke()
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .alpha(0.9f)
                .background(Color.Black, RoundedCornerShape(topEnd = 10.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = rating,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}