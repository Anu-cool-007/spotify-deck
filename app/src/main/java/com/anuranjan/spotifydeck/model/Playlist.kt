package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("collaborative") var collaborative: Boolean,
    @SerializedName("description") var description: String,
    @SerializedName("external_urls") var externalUrls: ExternalUrls,
    @SerializedName("href") var href: String,
    @SerializedName("id") var id: String,
    @SerializedName("images") var images: List<SpotifyImage>,
    @SerializedName("name") var name: String,
    @SerializedName("owner") var owner: SpotifyUser,
    @SerializedName("primary_color") var primaryColor: String,
    @SerializedName("public") var public: Boolean,
    @SerializedName("snapshot_id") var snapshotId: String,
    @SerializedName("tracks") var tracks: Tracks,
    @SerializedName("type") var type: String,
    @SerializedName("uri") var uri: String
)
