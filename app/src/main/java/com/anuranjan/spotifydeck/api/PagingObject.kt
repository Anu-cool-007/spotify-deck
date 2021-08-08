package com.anuranjan.spotifydeck.api

import com.google.gson.annotations.SerializedName

data class PagingObject<T>(
    @SerializedName("href") var href: String,
    @SerializedName("items") var items: List<T>,
    @SerializedName("limit") var limit: Int,
    @SerializedName("next") var next: String,
    @SerializedName("offset") var offset: Int,
    @SerializedName("previous") var previous: String,
    @SerializedName("total") var total: Int)
