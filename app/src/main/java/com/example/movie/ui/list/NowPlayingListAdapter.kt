package com.example.movie.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemsNowPlayingBinding
import com.example.movie.domain.entity.NowPlaying
import com.example.movie.utils.loadPoster

class NowPlayingListAdapter(private var movies: List<NowPlaying>) :
    RecyclerView.Adapter<NowPlayingListAdapter.ContentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun refreshData(movies: List<NowPlaying>) {
        this.movies = movies
        notifyDataSetChanged()
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
            binding.tvVoteAvg.text = "Vote: ${nowPlaying.voteAverage}"
        }
    }
}