package com.vama.topalbums.presentation.albumdetails

import androidx.lifecycle.viewModelScope
import com.vama.topalbums.data.Resource
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.domain.repository.AlbumRepository
import com.vama.topalbums.presentation.albumdetails.AlbumDetailsViewState.*
import com.vama.topalbums.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseViewModel<AlbumDetailsViewState>() {

    override fun initialState() = Initial

    fun onViewCreated(id: Int) {
        getAlbum(id)
    }

    private fun getAlbum(id: Int) {
        viewModelScope.launch {
            when (val result = albumRepository.getAlbumDetails(id)) {
                is Resource.Loading -> Unit
                is Resource.Success -> handleSuccessfulResult(result)
                is Resource.Error -> updateState(Error)
            }
        }
    }

    private fun handleSuccessfulResult(result: Resource<Album>) {
        result.data?.let { updateState(Success(it)) }
    }
}
