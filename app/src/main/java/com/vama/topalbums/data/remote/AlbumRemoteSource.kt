package com.vama.topalbums.data.remote

import com.vama.topalbums.data.remote.model.AlbumApiModel

interface AlbumRemoteSource {
    suspend fun getAlbums(): List<AlbumApiModel>
}
