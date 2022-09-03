package com.vama.topalbums.data.remote

import com.vama.topalbums.data.remote.model.AlbumApiModel

class AlbumRemoteDataSource(private val albumService: AlbumService) : AlbumRemoteSource {

    override suspend fun getAlbums(): List<AlbumApiModel> {
        val albums = albumService.getAlbums()
        println()
        return albums.feed.results
    }
}
