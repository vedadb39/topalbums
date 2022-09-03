package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import com.vama.topalbums.domain.model.Album

class AlbumsToDatabaseMapper {

    fun map(input: Album) = AlbumDatabaseModel(
        id = input.id,
        name = input.name,
        artist = input.artist,
        thumbnailImage = input.thumbnailImage
    )
}
