package com.vama.topalbums.data.remote.model

data class AlbumResponseFeedApiModel(
    val results: List<AlbumApiModel>,
    val copyright: String
)
