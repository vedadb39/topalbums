package com.vama.topalbums.data.local.model

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumDatabaseModel() : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var id: Int = 0
    var name: String = ""
    var artist: String = ""
    var thumbnail_image: String = ""

    constructor(id: Int, name: String, artist: String, thumbnailImage: String): this(){
        this.id = id
    }
}
