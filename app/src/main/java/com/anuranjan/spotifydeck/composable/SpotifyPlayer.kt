package com.anuranjan.spotifydeck.composable

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.anuranjan.spotifydeck.R

@Composable
fun ShowSpotifyPlayerBtn(albumArtUrl: String, isPlaying: Boolean) {
    val painter = rememberImagePainter(
        data = albumArtUrl,
        builder = {
            crossfade(true)
        }
    )
    val angle by rememberInfiniteTransition().animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing)
        )
    )

    FloatingActionButton(onClick = { /*TODO*/ }) {
        Box {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .graphicsLayer { rotationZ = angle }
            )
        }
    }
}

@Preview
@Composable
fun ShowSpotifyPlayerPreview() {
    Surface {
        ShowSpotifyPlayerBtn(
            albumArtUrl = "https://i.scdn.co/image/ab67616d00004851afe4947c77a4d2d9a7513d8c",
            isPlaying = false
        )
    }
}