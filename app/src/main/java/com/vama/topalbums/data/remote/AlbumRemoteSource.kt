package com.vama.topalbums.data.remote

import com.vama.topalbums.data.remote.model.AlbumResponseFeedApiModel

interface AlbumRemoteSource {
    suspend fun getAlbums(): AlbumResponseFeedApiModel
}
