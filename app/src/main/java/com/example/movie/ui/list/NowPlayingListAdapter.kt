package com.example.movie.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemsNowPlayingBinding
import io.android.core.domain.model.NowPlaying
import io.android.core.util.loadPoster

class NowPlayingListAdapter :
    ListAdapter<NowPlaying, NowPlayingListAdapter.ContentViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ContentViewHolder(private val binding: ItemsNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemsNowPlayingBinding.inflate(inflater, viewGroup, false)
                return ContentViewHolder(binding)
            }
        }

        fun bind(nowPlaying: NowPlaying) {
            binding.imgPoster.loadPoster(nowPlaying.posterPath)
            binding.tvTitle.text = nowPlaying.title
            binding.tvVoteAvg.text = nowPlaying.voteAverage.toString()
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