package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class SpotifyImage(
    @SerializedName("height") var height: Int,
    @SerializedName("width") var width: Int,
    @SerializedName("url") var url: String
)
