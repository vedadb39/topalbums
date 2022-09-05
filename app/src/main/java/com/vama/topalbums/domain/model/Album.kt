package com.vama.topalbums.domain.model

data class Album(
    val id: Int,
    val name: String,
    val artist: String,
    val thumbnailImage: String,
    val releaseDate: String,
    val url: String,
    val genres: List<Genre>,
    val copyright: String
)

