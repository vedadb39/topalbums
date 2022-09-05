package com.vama.topalbums.ui.albumdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vama.topalbums.databinding.ItemGenreBinding
import com.vama.topalbums.domain.model.Genre
import com.vama.topalbums.ui.core.BaseAdapter
import javax.inject.Inject

class GenresAdapter @Inject constructor() : BaseAdapter<GenresAdapter.ViewHolder, Genre>() {

    inner class ViewHolder(private val binding: ItemGenreBinding) : BaseViewHolder(binding) {

        override fun bind(item: Genre) = with(binding) {
            genreNameView.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}

