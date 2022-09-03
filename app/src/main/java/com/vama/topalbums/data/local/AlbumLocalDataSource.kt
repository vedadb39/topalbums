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
        realm.writeBlocking {
            for (album in albums) {
                copyToRealm(AlbumDatabaseModel().apply {
                    name = album.name
                    thumbnail_image = album.thumbnail_image
                    artist = album.artist
                })
            }
        }
    }
}
