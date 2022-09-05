package com.vama.topalbums.ui.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<VH : BaseAdapter<VH, E>.BaseViewHolder, E>(items: List<E> = emptyList()) :
    RecyclerView.Adapter<VH>() {


    var itemClickListener: (E) -> Unit = {}

    private val _items: MutableList<E> = ArrayList(items)
    var items: List<E>
        get() = _items
        set(values) {
            _items.clear()
            _items.addAll(values)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    abstract inner class BaseViewHolder(viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.root.setOnClickListener {
                itemClickListener(items[adapterPosition])
                onItemClick(items[adapterPosition])
            }
        }

        abstract fun bind(item: E)
        open fun onItemClick(item: E) {}
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        holder.bind(items[position])
    }

}
