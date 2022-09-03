package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import com.vama.topalbums.domain.model.Album

class AlbumDatabaseModelToAlbumMapper {

    fun map(input: AlbumDatabaseModel) = Album(
        id = input.id,
        name = input.name,
        artist = input.artist,
        thumbnailImage = input.thumbnail_image
    )
}
