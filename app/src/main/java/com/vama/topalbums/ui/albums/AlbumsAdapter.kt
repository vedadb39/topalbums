package com.vama.topalbums.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vama.topalbums.R
import com.vama.topalbums.databinding.ItemAlbumBinding
import com.vama.topalbums.databinding.ItemAlbumBinding.inflate
import com.vama.topalbums.domain.model.Album
import javax.inject.Inject

class AlbumsAdapter @Inject constructor() : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private val _items: MutableList<Album> = mutableListOf()
    var items: List<Album>
        get() = _items
        set(values) {
            _items.clear()
            _items.addAll(values)
            notifyDataSetChanged()
        }

    var onItemClickListener: (Album) -> Unit = {}

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) = with(binding) {
            Glide.with(binding.root.context)
                .load(album.thumbnailImage)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.thumbnailImageView)

            binding.albumNameView.text = album.name
            binding.artistNameView.text = album.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

}
