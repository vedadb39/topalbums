package com.vama.topalbums.data.local

import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class AlbumLocalDataSource(
    private val realm: Realm
) : AlbumLocalSource {

    override suspend fun getAlbums(): List<AlbumDatabaseModel> {
        val albums: RealmResults<AlbumDatabaseModel> = realm.query<AlbumDatabaseModel>().find()
        return albums
    }

    override suspend fun saveAlbums(albums: List<AlbumDatabaseModel>) {
        removeAlbums()
        saveAlbumsInDatabase(albums)
    }

    override suspend fun getAlbum(id: Int): AlbumDatabaseModel? {
        val album = realm.query<AlbumDatabaseModel>("album_id == $0", id).first().find()
        println()
        return album
    }

    private suspend fun removeAlbums() {
        realm.write {
            val albums = query<AlbumDatabaseModel>().find()
            delete(albums)
        }
    }

    private suspend fun saveAlbumsInDatabase(albums: List<AlbumDatabaseModel>) {
        realm.write {
            for (album in albums) {
                copyToRealm(AlbumDatabaseModel().apply {
                    album_id = album.album_id
                    name = album.name
                    thumbnail_image = album.thumbnail_image
                    artist = album.artist
                    release_date = album.release_date
                    url = album.url
                    genres = album.genres
                    copyright = album.copyright
                })
            }
        }
    }
}
