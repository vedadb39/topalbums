package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.remote.model.AlbumApiModel
import com.vama.topalbums.domain.model.Album

class AlbumApiModelToAlbumMapper {

    fun map(input: AlbumApiModel) = Album(
        id = input.id,
        name = input.name,
        artist = input.artistName,
        thumbnailImage = input.thumbnailImage
    )
}

