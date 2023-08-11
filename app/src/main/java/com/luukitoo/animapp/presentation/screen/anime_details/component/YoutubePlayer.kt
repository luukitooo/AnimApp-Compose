package com.luukitoo.animapp.presentation.screen.anime_details.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

@Composable
fun YouTubePlayer(
    modifier: Modifier = Modifier,
    videoId: String
) {
    val player = remember { YouTubePlayerInitializer() }
    val coroutineScope = rememberCoroutineScope()

    if (videoId.isNotBlank()) {
        AndroidView(
            factory = { context ->
                val playerView = YouTubePlayerView(context)
                coroutineScope.launch {
                    player.initialize(playerView, videoId)
                }
                playerView
            },
            modifier = modifier
        )
    }
}

class YouTubePlayerInitializer {
    fun initialize(playerView: YouTubePlayerView, videoId: String) {
        playerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
    }
}