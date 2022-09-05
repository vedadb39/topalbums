package com.vama.topalbums.data

import com.vama.topalbums.data.local.AlbumLocalSource
import com.vama.topalbums.data.remote.AlbumRemoteSource
import com.vama.topalbums.domain.NetworkConnectivity
import com.vama.topalbums.domain.mapper.AlbumApiModelToAlbumMapper
import com.vama.topalbums.domain.mapper.AlbumDatabaseModelToAlbumMapper
import com.vama.topalbums.domain.mapper.AlbumsToDatabaseMapper
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.flow

class AlbumDataRepository(
    private val albumRemoteSource: AlbumRemoteSource,
    private val albumLocalSource: AlbumLocalSource,
    private val albumApiModelToAlbumMapper: AlbumApiModelToAlbumMapper,
    private val albumDatabaseModelToAlbumMapper: AlbumDatabaseModelToAlbumMapper,
    private val albumsToDatabaseMapper: AlbumsToDatabaseMapper,
    private val networkConnectivity: NetworkConnectivity
) : AlbumRepository {

    override suspend fun getAlbums() = flow {
        emit(Resource.Loading())
        val isConnectedToInternet = networkConnectivity.isConnectedToInternet()
        val localAlbums = getFromLocal()
        emit(Resource.Success(localAlbums))

        if (isConnectedToInternet.not()) {
            return@flow
        }

        try {
            val remoteAlbums = getFromRemote()
            emit(Resource.Success(remoteAlbums))
            saveToLocal(remoteAlbums)
        } catch (exception: Exception) {
            emit(Resource.Error("Couldn't get albums"))
            return@flow
        }
    }

    private suspend fun getFromLocal() = albumLocalSource.getAlbums().map { album ->
        albumDatabaseModelToAlbumMapper.map(album)
    }

    private suspend fun getFromRemote(): List<Album> {
        val albumResponse = albumRemoteSource.getAlbums()
        return albumResponse.results.map { album ->
            albumApiModelToAlbumMapper.map(album, albumResponse.copyright)
        }
    }

    private suspend fun saveToLocal(albums: List<Album>) {
        val albumsDatabase = albums.map { album ->
            albumsToDatabaseMapper.map(album)
        }
        albumLocalSource.saveAlbums(albumsDatabase)
    }

    override suspend fun getAlbumDetails(albumId: Int) =
        albumLocalSource.getAlbum(albumId)?.let { databaseAlbum ->
            val album = albumDatabaseModelToAlbumMapper.map(databaseAlbum)
            Resource.Success(album)
        } ?: Resource.Error("Item not found")

}
