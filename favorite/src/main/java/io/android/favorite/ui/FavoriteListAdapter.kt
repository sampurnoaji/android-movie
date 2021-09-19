package io.android.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.android.core.domain.model.NowPlaying
import io.android.core.util.loadPoster
import io.android.favorite.databinding.ItemListFavoriteBinding

class FavoriteListAdapter :
    ListAdapter<NowPlaying, FavoriteListAdapter.ContentViewHolder>(DiffCallback()) {

    var onItemClick: ((NowPlaying) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    class ContentViewHolder(private val binding: ItemListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemListFavoriteBinding.inflate(inflater, viewGroup, false)
                return ContentViewHolder(binding)
            }
        }

        fun bind(nowPlaying: NowPlaying, onItemClick: ((NowPlaying) -> Unit)?) {
            with(binding) {
                imgPoster.loadPoster(nowPlaying.posterPath)
                tvTitle.text = nowPlaying.title
                tvVoteAvg.text = nowPlaying.voteAverage.toString()

                container.setOnClickListener { onItemClick?.invoke(nowPlaying) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NowPlaying>() {
        override fun areItemsTheSame(oldItem: NowPlaying, newItem: NowPlaying): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NowPlaying, newItem: NowPlaying): Boolean {
            return oldItem == newItem
        }
    }
}