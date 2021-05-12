package com.example.movie.ui.list.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentMoviesBinding
import com.example.movie.ui.detail.movie.MovieDetailActivity
import com.example.movie.utils.gone
import com.example.movie.utils.visible
import com.example.movie.vo.LoadResult
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MovieListAdapter.MovieItemListener {

    private lateinit var binding: FragmentMoviesBinding
    private val vm: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getMovies()
        vm.movieResult.observe(viewLifecycleOwner) {
            when (it) {
                is LoadResult.Loading -> binding.progressBar.visible()
                is LoadResult.Success -> {
                    binding.progressBar.gone()

                    val movieListAdapter = MovieListAdapter(it.data, this)
                    with(binding.rvMovies) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieListAdapter
                    }
                }
                is LoadResult.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMovieClicked(id: Int) {
        MovieDetailActivity.start(requireActivity(), id)
    }
}