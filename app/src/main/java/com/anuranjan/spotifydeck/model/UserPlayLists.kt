package com.anuranjan.spotifydeck.model

import com.google.gson.annotations.SerializedName

data class UserPlayLists(
    @SerializedName("href") var href: String,
    @SerializedName("items") var playlists: List<Playlist>,
    @SerializedName("limit") var limit: Int,
    @SerializedName("next") var next: String,
    @SerializedName("offset") var offset: Int,
    @SerializedName("previous") var previous: String,
    @SerializedName("total") var total: Int
)
