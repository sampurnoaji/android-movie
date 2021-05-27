package com.example.movie.ui.favorite.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.databinding.ItemsShowBinding
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster
import com.example.movie.utils.visible

class FavoriteShowListAdapter(private val listener: ShowItemListener) :
    PagedListAdapter<FavoriteShowEntity, FavoriteShowListAdapter.ContentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteShowEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteShowEntity,
                newItem: FavoriteShowEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteShowEntity,
                newItem: FavoriteShowEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val show = getItem(position) ?: return
        holder.bind(show, listener)
    }

    class ContentViewHolder(private val binding: ItemsShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemsShowBinding.inflate(inflater, viewGroup, false)
                return ContentViewHolder(binding)
            }
        }

        fun bind(show: FavoriteShowEntity, listener: ShowItemListener) {
            binding.listPoster.loadPoster(show.posterPath ?: "")
            binding.listTitle.text = show.name
            binding.listDate.text = show.firstAirDate?.formatDate()
            binding.listOverview.text = show.overview

            binding.fabDelete.visible()
            binding.fabDelete.setOnClickListener { listener.onDeleteFavoriteShow(show) }

            binding.container.setOnClickListener { listener.onShowClicked(show.id) }
        }
    }

    interface ShowItemListener {
        fun onShowClicked(id: Int)
        fun onDeleteFavoriteShow(favoriteShow: FavoriteShowEntity)
    }
}