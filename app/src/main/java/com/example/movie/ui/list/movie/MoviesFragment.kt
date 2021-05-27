package com.example.movie.ui.list.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movie.R
import com.example.movie.databinding.FragmentMoviesBinding
import com.example.movie.domain.entity.Movie
import com.example.movie.ui.detail.movie.MovieDetailActivity
import com.example.movie.utils.database.SortUtil
import com.example.movie.utils.gone
import com.example.movie.utils.visible
import com.example.movie.vo.Resource
import com.example.movie.vo.Status
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies), MovieListAdapter.MovieItemListener {

    private val binding: FragmentMoviesBinding by viewBinding()
    private val vm: MoviesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.getMovies(SortUtil.NEWEST).observe(viewLifecycleOwner, moviesObserver)
        }

        binding.fabSortNewest.setOnClickListener {
            lifecycleScope.launch {
                vm.getMovies(SortUtil.NEWEST).observe(viewLifecycleOwner, moviesObserver)
            }
        }
        binding.fabSortOldest.setOnClickListener {
            lifecycleScope.launch {
                vm.getMovies(SortUtil.OLDEST).observe(viewLifecycleOwner, moviesObserver)
            }
        }
    }

    private val moviesObserver = Observer<Resource<PagedList<Movie>>> {
        when (it.status) {
            Status.LOADING -> binding.progressBar.visible()
            Status.SUCCESS -> {
                binding.progressBar.gone()

                val movieListAdapter = MovieListAdapter(this@MoviesFragment)
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieListAdapter
                }
                movieListAdapter.submitList(it.data)

            }
            Status.ERROR -> {
                binding.progressBar.gone()
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMovieClicked(id: Int) {
        MovieDetailActivity.start(requireActivity(), id)
    }
}