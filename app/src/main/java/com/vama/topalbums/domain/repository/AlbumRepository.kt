package com.vama.topalbums.domain.repository

import com.vama.topalbums.data.Resource
import com.vama.topalbums.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums(): Flow<Resource<List<Album>>>
    suspend fun getAlbumDetails(albumId: Int): Resource<Album>
}
