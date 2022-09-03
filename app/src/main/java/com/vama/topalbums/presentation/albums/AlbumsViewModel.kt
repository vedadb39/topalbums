package com.vama.topalbums.presentation.albums

import androidx.lifecycle.viewModelScope
import com.vama.topalbums.data.Result
import com.vama.topalbums.domain.repository.AlbumRepository
import com.vama.topalbums.presentation.core.BaseViewModel
import com.vama.topalbums.ui.albums.AlbumsViewState
import com.vama.topalbums.ui.albums.AlbumsViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseViewModel<AlbumsViewState>() {

    override fun initialState() = Loading

    fun onViewCreated() {
        viewModelScope.launch {
            when (val result = albumRepository.getAlbums()) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        updateState(ErrorOrEmpty)
                    } else {
                        updateState(Success(result.data))
                    }
                }
                is Result.Error -> updateState(ErrorOrEmpty)
            }
        }
    }
}
