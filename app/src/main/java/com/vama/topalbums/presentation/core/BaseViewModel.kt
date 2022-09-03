package com.vama.topalbums.presentation.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vama.topalbums.ui.core.ViewState

abstract class BaseViewModel<VIEW_STATE : ViewState> : ViewModel() {

    private val _viewState = MutableLiveData<VIEW_STATE>().apply { value = initialState() }
    val viewState: LiveData<VIEW_STATE> get() = _viewState

    abstract fun initialState(): VIEW_STATE

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun currentViewState() = viewState.value ?: initialState()

}
