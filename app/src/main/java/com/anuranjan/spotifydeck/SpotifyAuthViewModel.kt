package com.anuranjan.spotifydeck

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SpotifyAuthViewModel: ViewModel() {
    var authToken by mutableStateOf("")
}