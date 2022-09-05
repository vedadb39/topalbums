package com.vama.topalbums.data.remote

class AlbumRemoteDataSource(private val albumService: AlbumService) : AlbumRemoteSource {

    override suspend fun getAlbums() = albumService.getAlbums().feed


}
