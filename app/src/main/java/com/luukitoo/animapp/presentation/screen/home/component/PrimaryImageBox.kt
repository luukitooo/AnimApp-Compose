package com.luukitoo.animapp.presentation.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luukitoo.animapp.R
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@Composable
fun PrimaryImageBox(
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    placeholder: Painter? = null,
    titleStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
) {
    Box {
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = null,
            placeholder = placeholder,
            contentScale = ContentScale.Crop,
        )
        Box(
            Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    )
                )
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                style = titleStyle
            )
        }
    }
}

@Preview
@Composable
fun PrimaryImageBoxPreview() {
    AnimAppTheme {
        PrimaryImageBox(
            modifier = Modifier.fillMaxWidth(),
            url = "https://upload.wikimedia.org/wikipedia/en/thumb/4/4a/Oppenheimer_%28film%29.jpg/220px-Oppenheimer_%28film%29.jpg",
            title = "Oppenheimer",
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
    }
}