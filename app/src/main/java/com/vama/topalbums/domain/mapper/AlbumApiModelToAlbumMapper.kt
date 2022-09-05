package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.remote.model.AlbumApiModel
import com.vama.topalbums.domain.model.Album

class AlbumApiModelToAlbumMapper(
    private val genreApiToGenreMapper: GenreApiToGenreMapper
) {

    fun map(input: AlbumApiModel, copyright: String) = Album(
        id = input.id,
        name = input.name,
        artist = input.artistName,
        thumbnailImage = input.thumbnailImage,
        genres = input.genres.map { genreApiToGenreMapper.map(it) },
        releaseDate = input.releaseDate,
        url = input.url,
        copyright = copyright
    )
}

