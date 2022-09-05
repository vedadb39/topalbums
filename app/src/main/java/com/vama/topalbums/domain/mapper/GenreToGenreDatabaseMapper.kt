package com.vama.topalbums.domain.mapper

import com.vama.topalbums.data.local.model.GenreDatabaseModel
import com.vama.topalbums.domain.model.Genre

class GenreToGenreDatabaseMapper {

    fun map(input: Genre) = GenreDatabaseModel(
        genreId = input.genreId,
        name = input.name,
        url = input.url
    )
}
