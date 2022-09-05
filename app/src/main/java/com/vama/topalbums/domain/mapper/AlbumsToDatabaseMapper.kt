package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.AlbumDatabaseModel
import com.vama.topalbums.domain.model.Album
import io.realm.kotlin.ext.toRealmList

class AlbumsToDatabaseMapper(
    private val genreToGenreDatabaseMapper: GenreToGenreDatabaseMapper
) {

    fun map(input: Album) = AlbumDatabaseModel(
        id = input.id,
        name = input.name,
        artist = input.artist,
        thumbnailImage = input.thumbnailImage,
        releaseDate = input.releaseDate,
        url = input.url,
        genres = input.genres.map { genreToGenreDatabaseMapper.map(it) }.toRealmList(),
        copyright = input.copyright
    )
}

