package com.anuranjan.spotifydeck.api

import com.google.gson.annotations.SerializedName

data class ExternalUrl (@SerializedName("spotify") var spotifyUrl : String)