package com.anuranjan.spotifydeck

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.anuranjan.spotifydeck.composable.ShowSpotifyPlayerBtn
import com.anuranjan.spotifydeck.composable.SpotifyConnectionScreen
import com.anuranjan.spotifydeck.ui.theme.SpotifyDeckTheme
import com.anuranjan.spotifydeck.util.toLogString
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote


class MainActivity : ComponentActivity() {

    private val redirectUri = "https://www.google.com"
    private val clientId = "9b78d87bbef24fe483a20f9c9a977da8"
    private val permScopes = arrayOf("streaming")

    private val spotifyViewModel by viewModels<SpotifyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAuthTokenAndRemoteConnection()

        setContent {
            SpotifyDeckTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        floatingActionButton = {
                            if (true) {
                                ShowSpotifyPlayerBtn(
                                    "https://i.scdn.co/image/ab67616d00004851afe4947c77a4d2d9a7513d8c",
                                    true
                                )
                            }
                        }
                    ) {
                        if (spotifyViewModel.authState != UIState.Connected || spotifyViewModel.remoteConnectionState != UIState.Connected) {
                            SpotifyConnectionScreen(
                                authState = spotifyViewModel.authState,
                                spotifyRemoteState = spotifyViewModel.remoteConnectionState
                            )
                        }
                    }

                }
            }
        }
    }

    private fun getAuthTokenAndRemoteConnection() {
        spotifyViewModel.authState = UIState.InProgress
        spotifyViewModel.remoteConnectionState = UIState.InProgress

        val authTokenParams = AuthorizationRequest.Builder(
            clientId,
            AuthorizationResponse.Type.TOKEN,
            redirectUri
        ).setScopes(permScopes).build()

        val remoteConnectionParams = ConnectionParams.Builder(clientId)
            .setRedirectUri(redirectUri)
            .showAuthView(true)
            .build()

        authTokenResultListener.launch(
            AuthorizationClient.createLoginActivityIntent(
                this,
                authTokenParams
            )
        )
        SpotifyAppRemote.connect(this, remoteConnectionParams, remoteConnectListener)
    }

    private val authTokenResultListener =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val authResponse = AuthorizationClient.getResponse(Activity.RESULT_OK, data)
                Log.d("AuthResponse", authResponse.toLogString())
                when (authResponse.type) {
                    AuthorizationResponse.Type.TOKEN -> {
                        spotifyViewModel.authToken = authResponse.accessToken
                        spotifyViewModel.authState = UIState.Connected
                    }
                    else -> {
                        spotifyViewModel.authToken = authResponse.error
                        spotifyViewModel.authState = UIState.Error
                    }
                }
            }
        }

    private val remoteConnectListener = object : Connector.ConnectionListener {
        override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
            spotifyViewModel.spotifyAppRemote = spotifyAppRemote
            spotifyViewModel.remoteConnectionState = UIState.Connected
        }

        override fun onFailure(throwable: Throwable) {
            Log.e("MainActivity", throwable.message, throwable)
            spotifyViewModel.remoteConnectionState = UIState.Error
        }
    }
}
