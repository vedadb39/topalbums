package com.vama.topalbums.data.remote.model

data class AlbumResponseApiModel(
    val feed: AlbumResponseFeedApiModel
)

data class AlbumResponseFeedApiModel(
    val results: List<AlbumApiModel>

)

