package com.vama.topalbums.data.remote

import com.vama.topalbums.data.remote.model.AlbumResponseApiModel
import retrofit2.http.GET

interface AlbumService {

    @GET("/api/v2/us/music/most-played/100/albums.json")
    suspend fun getAlbums(): AlbumResponseApiModel
}
