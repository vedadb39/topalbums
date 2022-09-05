package com.vama.topalbums.data.local.model

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class GenreDatabaseModel : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var genre_id: String = ""
    var name: String = ""
    var url: String = ""

    constructor(genreId: String, name: String, url: String) : this() {
        this.genre_id = genreId
        this.name = name
        this.url = url
    }

    constructor()
}
