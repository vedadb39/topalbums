package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.GenreDatabaseModel
import com.vama.topalbums.domain.model.Genre

class GenreDatabaseModelToGenreMapper {

    fun map(input: GenreDatabaseModel) = Genre(
        genreId = input.genre_id,
        name = input.name,
        url = input.url
    )
}
