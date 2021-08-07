package com.anuranjan.spotifydeck

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.anuranjan.spotifydeck.ui.theme.SpotifyDeckTheme
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse


class MainActivity : ComponentActivity() {

    private val REDIRECT_URI = "https://www.google.com"
    private val CLIENT_ID = "9b78d87bbef24fe483a20f9c9a977da8"

    private val spotifyAuthViewModel by viewModels<SpotifyAuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAuthToken()

        setContent {
            SpotifyDeckTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun getAuthToken() {
        val builder = AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            REDIRECT_URI
        )
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        val intent = AuthorizationClient.createLoginActivityIntent(this, request)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val authResponse = AuthorizationClient.getResponse(Activity.RESULT_OK, data)
            when(authResponse.type) {
                AuthorizationResponse.Type.TOKEN -> spotifyAuthViewModel.authToken = authResponse.accessToken
                else -> Log.d("MainActivity", authResponse.toString())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpotifyDeckTheme {
        Greeting("Android")
    }
}