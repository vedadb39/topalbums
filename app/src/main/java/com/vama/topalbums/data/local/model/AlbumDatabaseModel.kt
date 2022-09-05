package com.vama.topalbums.data.local.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class AlbumDatabaseModel : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var album_id: Int = 0
    var name: String = ""
    var artist: String = ""
    var thumbnail_image: String = ""
    var release_date: String = ""
    var url: String = ""
    var genres: RealmList<GenreDatabaseModel> = realmListOf()
    var copyright: String = ""

    constructor(
        id: Int,
        name: String,
        artist: String,
        thumbnailImage: String,
        releaseDate: String,
        url: String,
        genres: RealmList<GenreDatabaseModel>,
        copyright: String
    ) : this() {
        this.album_id = id
        this.name = name
        this.artist = artist
        this.thumbnail_image = thumbnailImage
        this.release_date = releaseDate
        this.url = url
        this.genres = genres
        this.copyright = copyright
    }

    constructor()
}
