package com.vama.topalbums.data

import com.vama.topalbums.data.Result.Error
import com.vama.topalbums.data.Result.Success
import com.vama.topalbums.data.local.AlbumLocalSource
import com.vama.topalbums.data.remote.AlbumRemoteSource
import com.vama.topalbums.domain.NetworkConnectivity
import com.vama.topalbums.domain.mapper.AlbumApiModelToAlbumMapper
import com.vama.topalbums.domain.mapper.AlbumDatabaseModelToAlbumMapper
import com.vama.topalbums.domain.mapper.AlbumsToDatabaseMapper
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.domain.model.CachePolicy
import com.vama.topalbums.domain.repository.AlbumRepository

class AlbumDataRepository(
    private val albumRemoteSource: AlbumRemoteSource,
    private val albumLocalSource: AlbumLocalSource,
    private val albumApiModelToAlbumMapper: AlbumApiModelToAlbumMapper,
    private val albumDatabaseModelToAlbumMapper: AlbumDatabaseModelToAlbumMapper,
    private val albumsToDatabaseMapper: AlbumsToDatabaseMapper,
    private val networkConnectivity: NetworkConnectivity
) : AlbumRepository {

//    override suspend fun getAlbums(cachePolicy: CachePolicy) = try {
//        val result = if (networkConnectivity.isConnectedToInternet()) {
//            if (getFromLocal().isEmpty()) {
//                getFromRemote()
//            } else {
//                getFromLocalThenUpdateFromRemote()
//            }
//        } else {
//            getFromLocal()
//        }
//        Success(result)
//    } catch (exception: Exception) {
//        Error(exception)
//    }

    override suspend fun getAlbums(cachePolicy: CachePolicy) = try {
        Success(getFromRemote())
    } catch (exception: Exception) {
        Error(exception)
    }

    private suspend fun getFromLocal() = albumLocalSource.getAlbums().map { album ->
        albumDatabaseModelToAlbumMapper.map(album)
    }

    private suspend fun getFromRemote() = albumRemoteSource.getAlbums().map { album ->
        albumApiModelToAlbumMapper.map(album)
    }.also { albums ->
        saveToLocal(albums)
    }

    private suspend fun getFromLocalThenUpdateFromRemote() =
        getFromLocal().also {
            saveToLocal(getFromRemote())
        }

    private suspend fun saveToLocal(albums: List<Album>) {
        val albumsDatabase = albums.map { album ->
            albumsToDatabaseMapper.map(album)
        }
        albumLocalSource.saveAlbums(albumsDatabase)
    }

    override suspend fun getAlbumDetails(albumId: Int): Album {
        TODO("Not yet implemented")
    }
}
