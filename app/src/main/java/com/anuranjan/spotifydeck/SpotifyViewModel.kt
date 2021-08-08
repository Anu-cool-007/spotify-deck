package com.anuranjan.spotifydeck

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.spotify.android.appremote.api.SpotifyAppRemote

sealed class UIState {
    object Connected : UIState()
    object Disconnected : UIState()
    object InProgress : UIState()
    object Error : UIState()
}

class SpotifyViewModel : ViewModel() {
    var authToken: String by mutableStateOf("")
    var authState: UIState by mutableStateOf(UIState.Disconnected)
    var remoteConnectionState: UIState by mutableStateOf(UIState.Disconnected)
    var spotifyAppRemote: SpotifyAppRemote? by mutableStateOf(null)
}