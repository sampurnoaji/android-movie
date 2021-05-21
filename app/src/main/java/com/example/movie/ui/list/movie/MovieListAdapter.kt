package com.example.movie.ui.list.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemsMovieBinding
import com.example.movie.domain.entity.Movie
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster

class MovieListAdapter(private val listener: MovieItemListener) :
    PagedListAdapter<Movie, MovieListAdapter.ContentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val movie = getItem(position) ?: return
        holder.bind(movie, listener)
    }

    class ContentViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(viewGroup: ViewGroup): ContentViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = ItemsMovieBinding.inflate(inflater, viewGroup, false)
                return ContentViewHolder(binding)
            }
        }

        fun bind(movie: Movie, listener: MovieItemListener) {
            binding.listPoster.loadPoster(movie.posterPath)
            binding.listTitle.text = movie.title
            binding.listDate.text = movie.releaseDate.formatDate()
            binding.listOverview.text = movie.overview

            binding.container.setOnClickListener { listener.onMovieClicked(movie.id) }
        }
    }

    interface MovieItemListener {
        fun onMovieClicked(id: Int)
    }
}