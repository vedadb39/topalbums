package com.vama.topalbums.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vama.topalbums.databinding.FragmentAlbumsBinding
import com.vama.topalbums.presentation.albums.AlbumsViewModel
import com.vama.topalbums.presentation.albums.AlbumsViewState
import com.vama.topalbums.ui.albums.AlbumsFragmentDirections.Companion.actionAlbumsFragmentToAlbumDetailsFragment
import com.vama.topalbums.ui.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsFragment : BaseFragment<AlbumsViewState>() {

    private var _binding: FragmentAlbumsBinding? = null

    override val viewModel: AlbumsViewModel by viewModels()

    private val binding get() = _binding!!

    @Inject
    lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        setupUi()
    }

    private fun setupUi() {
        binding.collapsingToolbar.title = "Top 100 albums"

        with(binding.albumsView) {
            binding.toolbar
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumsAdapter
        }
        binding.emptyDataView.setOnClickListener {
            viewModel.onRetryAction()
        }
        albumsAdapter.itemClickListener = { album ->
            findNavController().navigate(actionAlbumsFragmentToAlbumDetailsFragment(album.id))
        }
    }

    override fun renderViewState(viewState: AlbumsViewState) {
        binding.albumsView.isVisible = viewState.isDataViewVisible
        binding.progressBarView.isVisible = viewState.isLoadingVisible
        binding.emptyDataView.isVisible = viewState.isEmptyViewVisible
        albumsAdapter.items = viewState.albums
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
