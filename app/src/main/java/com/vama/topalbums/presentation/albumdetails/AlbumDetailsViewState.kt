package com.vama.topalbums.presentation.albumdetails

import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.ui.core.ViewState

sealed class AlbumDetailsViewState(
) : ViewState {
    object Initial : AlbumDetailsViewState()
    data class Success(val selectedAlbum: Album) : AlbumDetailsViewState()
    object Error : AlbumDetailsViewState()
}
