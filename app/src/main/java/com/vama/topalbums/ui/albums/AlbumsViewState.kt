package com.vama.topalbums.ui.albums

import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.ui.core.ViewState

sealed class AlbumsViewState(
    val isLoadingVisible: Boolean,
    val isEmptyViewVisible: Boolean,
    val isDataViewVisible: Boolean,
    open val albums: List<Album>
) : ViewState {

    object Loading : AlbumsViewState(
        isLoadingVisible = true,
        isEmptyViewVisible = false,
        isDataViewVisible = false,
        albums = emptyList()
    )

    data class Success(override val albums: List<Album>) : AlbumsViewState(
        isLoadingVisible = false,
        isEmptyViewVisible = false,
        isDataViewVisible = true,
        albums
    )

    object ErrorOrEmpty : AlbumsViewState(
        isLoadingVisible = false,
        isEmptyViewVisible = true,
        isDataViewVisible = false,
        albums = emptyList()
    )
}



