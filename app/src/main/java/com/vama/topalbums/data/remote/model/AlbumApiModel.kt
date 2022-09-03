package com.vama.topalbums.data.remote.model

import com.google.gson.annotations.SerializedName

data class AlbumApiModel(
    val id: Int,
    val name: String,
    val artistName: String,
    @SerializedName("artworkUrl100")
    val thumbnailImage: String
)
