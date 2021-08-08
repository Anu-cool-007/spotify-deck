package com.anuranjan.spotifydeck.util

import com.spotify.sdk.android.auth.AuthorizationResponse

fun AuthorizationResponse.toLogString(): String {
    return "AuthorizationResponse: {Code: ${this.code}, State: ${this.state}, Type: ${this.type}, Error: ${this.error}}"
}