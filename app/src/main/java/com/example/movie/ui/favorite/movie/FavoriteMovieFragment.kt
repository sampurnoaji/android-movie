package com.example.movie.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.databinding.FragmentFavoriteMovieBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment(), FavoriteMovieListAdapter.MovieItemListener {

    private lateinit var binding: FragmentFavoriteMovieBinding
    private val vm: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.getFavoriteMovies().observe(viewLifecycleOwner) {
                val movieListAdapter = FavoriteMovieListAdapter(this@FavoriteMovieFragment)
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieListAdapter
                }
                movieListAdapter.submitList(it)
            }
        }
    }

    override fun onMovieClicked(id: Int) {}
    override fun onDeleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        vm.deleteFavoriteMovie(favoriteMovie)
    }
}