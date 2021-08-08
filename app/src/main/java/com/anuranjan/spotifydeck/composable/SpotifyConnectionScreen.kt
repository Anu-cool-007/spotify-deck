package com.anuranjan.spotifydeck.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anuranjan.spotifydeck.R
import com.anuranjan.spotifydeck.UIState

@Composable
fun SpotifyConnectionScreen(
    authState: UIState,
    spotifyRemoteState: UIState
) {
    Surface(color = MaterialTheme.colors.background.copy(alpha = 0.7f)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    ConnectionStatusRow("Spotify Auth Token", authState)
                    ConnectionStatusRow("Spotify Remote Connection", spotifyRemoteState)
                }
            }
        }
    }
}

@Composable
private fun ConnectionStatusRow(text: String, state: UIState) {
    Row(Modifier.padding(8.dp)) {
        StatusIcon(state)
        Text(text = text, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
private fun StatusIcon(state: UIState) {
    val iconColor = when (state) {
        UIState.Disconnected -> Color.Gray
        UIState.Connected -> Color.Green
        UIState.InProgress -> Color.Yellow
        UIState.Error -> Color.Red
    }
    Icon(
        painter = painterResource(id = R.drawable.status_icon),
        contentDescription = null,
        tint = iconColor,
        modifier = Modifier.scale(0.6f)
    )
}

@Preview
@Composable
private fun SpotifyConnectionScreenPreview() {
    SpotifyConnectionScreen(
        authState = UIState.Disconnected,
        spotifyRemoteState = UIState.InProgress
    )
}