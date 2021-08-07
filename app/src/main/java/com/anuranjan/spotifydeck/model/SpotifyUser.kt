package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class SpotifyUser(
    @SerializedName("display_name") var displayName: String,
    @SerializedName("external_urls") var externalUrls: ExternalUrls,
    @SerializedName("href") var href: String,
    @SerializedName("id") var id: String,
    @SerializedName("type") var type: String,
    @SerializedName("uri") var uri: String
)
