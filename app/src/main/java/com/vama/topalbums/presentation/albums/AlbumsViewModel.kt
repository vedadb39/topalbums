package com.vama.topalbums.presentation.albums

import androidx.lifecycle.viewModelScope
import com.vama.topalbums.data.Resource
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.domain.repository.AlbumRepository
import com.vama.topalbums.presentation.core.BaseViewModel
import com.vama.topalbums.presentation.albums.AlbumsViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseViewModel<AlbumsViewState>() {

    override fun initialState() = Loading

    fun onViewCreated() {
        getAlbums()
    }

    fun onRetryAction() {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            albumRepository.getAlbums().collect { result ->
                when (result) {
                    is Resource.Loading -> updateState(Loading)
                    is Resource.Success -> handleSuccessfulResult(result)
                    is Resource.Error -> updateState(Error)
                }
            }
        }
    }

    private fun handleSuccessfulResult(result: Resource<List<Album>>) {
        result.data?.let { albums ->
            if (albums.isEmpty()) {
                updateState(Empty)
            } else {
                updateState(Success(albums))
            }
        }
    }
}
