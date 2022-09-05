package com.vama.topalbums.data.local

import com.vama.topalbums.data.local.model.AlbumDatabaseModel

interface AlbumLocalSource {
    suspend fun getAlbums(): List<AlbumDatabaseModel>
    suspend fun saveAlbums(albums: List<AlbumDatabaseModel>)
    suspend fun getAlbum(id: Int): AlbumDatabaseModel?
}
