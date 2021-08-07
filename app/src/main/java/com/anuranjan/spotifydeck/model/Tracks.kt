package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("href") var href: String,
    @SerializedName("total") var total: Int
)
