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
import com.example.movie.vo.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        CoroutineScope(Dispatchers.Main).launch {
            vm.getMovies().observe(viewLifecycleOwner) {
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
        }
    }

    override fun onMovieClicked(id: Int) {
        MovieDetailActivity.start(requireActivity(), id)
    }
}