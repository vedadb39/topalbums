package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.remote.model.GenreApiModel
import com.vama.topalbums.domain.model.Genre

class GenreApiToGenreMapper {

    fun map(input: GenreApiModel) = Genre(
        genreId = input.genreId,
        name = input.name,
        url = input.url
    )
}
