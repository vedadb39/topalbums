package com.vama.topalbums.ui.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vama.topalbums.presentation.core.BaseViewModel

abstract class BaseFragment<VIEW_STATE : ViewState> : Fragment() {

    abstract val viewModel: BaseViewModel<VIEW_STATE>

    open fun renderViewState(viewState: VIEW_STATE) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelInternal()
    }

    private fun observeViewModelInternal() {
        viewModel.viewState.observe(viewLifecycleOwner, RenderStateObserver())
    }

    inner class RenderStateObserver : Observer<VIEW_STATE> {
        override fun onChanged(viewState: VIEW_STATE) = renderViewState(viewState)
    }
}
