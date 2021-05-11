package com.example.movie.ui.list.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentMoviesBinding
import com.example.movie.ui.detail.movie.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MovieListAdapter.MovieItemListener {

    private lateinit var binding: FragmentMoviesBinding
    private val vm: MoviesViewModel by viewModel()

    override

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListAdapter = MovieListAdapter(vm.getMovies(), this)
        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieListAdapter
        }
    }

    override fun onMovieClicked(id: Int) {
        MovieDetailActivity.start(requireActivity(), id)
    }
}