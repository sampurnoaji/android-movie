package com.example.movie.ui.list.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.Show
import com.example.movie.databinding.ItemsShowBinding
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster

class ShowListAdapter(
    private val shows: List<Show>,
    private val listener: ShowItemListener
) : RecyclerView.Adapter<ShowListAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(shows[position], listener)
    }

    override fun getItemCount(): Int {
        return shows.size
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

        fun bind(show: Show, listener: ShowItemListener) {
            binding.listPoster.loadPoster(show.posterUrl)
            binding.listTitle.text = show.title
            binding.listDate.text = show.releaseDate.formatDate()
            binding.listOverview.text = show.overview

            binding.root.setOnClickListener { listener.onShowClicked(show.id) }
        }
    }

    interface ShowItemListener {
        fun onShowClicked(id: Int)
    }
}