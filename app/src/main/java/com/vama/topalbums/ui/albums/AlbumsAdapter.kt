package com.vama.topalbums.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vama.topalbums.databinding.ItemAlbumBinding
import com.vama.topalbums.databinding.ItemAlbumBinding.inflate
import com.vama.topalbums.domain.model.Album
import com.vama.topalbums.ui.core.BaseAdapter
import javax.inject.Inject

class AlbumsAdapter @Inject constructor() : BaseAdapter<AlbumsAdapter.ViewHolder, Album>() {


    inner class ViewHolder(private val binding: ItemAlbumBinding) : BaseViewHolder(binding) {

        override fun bind(item: Album) = with(binding) {
            thumbnailImageView.layout(0, 0, 0, 0)
            Glide.with(root.context)
                .load(item.thumbnailImage)
                .centerCrop()
                .override(200)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnailImageView)
            albumNameView.text = item.name
            artistNameView.text = item.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflate(LayoutInflater.from(parent.context), parent, false)
    )
}
