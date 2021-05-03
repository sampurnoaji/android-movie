package com.example.movie.ui.list.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.Movie
import com.example.movie.databinding.ItemsMovieBinding
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster

class MovieListAdapter(
    private val movies: List<Movie>,
    private val listener: MovieItemListener
) : RecyclerView.Adapter<MovieListAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    override fun getItemCount(): Int {
        return movies.size
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
            binding.listPoster.loadPoster(movie.posterUrl)
            binding.listTitle.text = movie.title
            binding.listDate.text = movie.releaseDate.formatDate()
            binding.listOverview.text = movie.overview

            binding.root.setOnClickListener { listener.onMovieClicked(movie.id) }
        }
    }

    interface MovieItemListener {
        fun onMovieClicked(id: Int)
    }
}