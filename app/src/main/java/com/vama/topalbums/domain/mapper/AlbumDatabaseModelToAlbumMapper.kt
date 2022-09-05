package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import com.vama.topalbums.domain.model.Album

class AlbumDatabaseModelToAlbumMapper(
    private val genreDatabaseModelToGenreMapper: GenreDatabaseModelToGenreMapper
) {

    fun map(input: AlbumDatabaseModel) = Album(
        id = input.album_id,
        name = input.name,
        artist = input.artist,
        thumbnailImage = input.thumbnail_image,
        releaseDate = input.release_date,
        url = input.url,
        genres = input.genres.map { genreDatabaseModelToGenreMapper.map(it) },
        copyright = input.copyright
    )
}

