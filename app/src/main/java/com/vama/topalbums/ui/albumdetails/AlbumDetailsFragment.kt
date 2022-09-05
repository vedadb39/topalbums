package com.vama.topalbums.ui.albumdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vama.topalbums.R
import com.vama.topalbums.databinding.FragmentAlbumDetailsBinding
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.presentation.albumdetails.AlbumDetailsViewModel
import com.vama.topalbums.presentation.albumdetails.AlbumDetailsViewState
import com.vama.topalbums.presentation.albumdetails.AlbumDetailsViewState.*
import com.vama.topalbums.ui.core.BaseFragment
import com.vama.topalbums.ui.core.DateFormatterHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailsFragment : BaseFragment<AlbumDetailsViewState>() {

    private var _binding: FragmentAlbumDetailsBinding? = null
    private val binding get() = _binding!!

    override val viewModel: AlbumDetailsViewModel by viewModels()
    private val navigationArguments by navArgs<AlbumDetailsFragmentArgs>()

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var dateFormatterHelper: DateFormatterHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(navigationArguments.albumId)
        setupUi()
        setupListeners()


    }

    private fun setupUi() {
        binding.genresView.adapter = genresAdapter
    }

    private fun setupListeners() {
        binding.backView.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun renderViewState(viewState: AlbumDetailsViewState) {
        when (viewState) {
            Initial -> Unit
            Error -> Unit
            is Success -> updateUi(viewState.selectedAlbum)
        }
    }

    private fun updateUi(selectedAlbum: Album) {
        with(binding) {
            Glide.with(requireContext())
                .load(selectedAlbum.thumbnailImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(thumbnailImageView1)

            artistNameView.text = selectedAlbum.artist
            albumNameView.text = selectedAlbum.name
            genresAdapter.items = selectedAlbum.genres

            releasedView.text = String.format(
                getString(
                    R.string.released_text,
                    dateFormatterHelper.getFormatterDate(selectedAlbum.releaseDate)
                )
            )
            copyrightView.text = selectedAlbum.copyright

            buttonVisitAlbum.setOnClickListener {
                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedAlbum.url))
                startActivity(urlIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
