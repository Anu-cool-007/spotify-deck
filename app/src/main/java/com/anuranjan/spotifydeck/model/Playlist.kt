package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("href") var href: String,
    @SerializedName("images") var images: List<SpotifyImage>,
    @SerializedName("name") var name: String,
    @SerializedName("primary_color") var primaryColor: String,
    @SerializedName("public") var public: Boolean,
    @SerializedName("snapshot_id") var snapshotId: String,
    @SerializedName("tracks") var tracks: Tracks,
    @SerializedName("type") var type: String,
    @SerializedName("uri") var uri: String
)
