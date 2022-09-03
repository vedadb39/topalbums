package com.vama.topalbums.domain.repository

import com.vama.topalbums.data.Result
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.domain.model.CachePolicy
import com.vama.topalbums.domain.model.CachePolicy.CacheThenNetwork

interface AlbumRepository {
    suspend fun getAlbums(cachePolicy: CachePolicy = CacheThenNetwork): Result<List<Album>>
    suspend fun getAlbumDetails(albumId: Int): Album
}
