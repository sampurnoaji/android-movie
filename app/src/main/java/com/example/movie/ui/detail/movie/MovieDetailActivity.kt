package com.example.movie.ui.detail.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.utils.formatDate
import com.example.movie.utils.gone
import com.example.movie.utils.loadPoster
import com.example.movie.utils.visible
import com.example.movie.vo.LoadResult
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val vm: MovieDetailViewModel by viewModel()

    companion object {
        const val INTENT_KEY_MOVIE_ID = "movieId"

        @JvmStatic
        fun start(context: Context, movieId: Int) {
            val starter = Intent(context, MovieDetailActivity::class.java)
                .putExtra(INTENT_KEY_MOVIE_ID, movieId)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val movieId = intent.extras?.getInt(INTENT_KEY_MOVIE_ID)
        movieId?.let {
            vm.setSelectedMovie(it)
            vm.getMovieDetail()
        }
        observeMovieDetailResult()

        binding.fabAddToFavorite.setOnClickListener {
            vm.insertFavoriteMovie()
            Snackbar.make(it, getString(R.string.add_movie_to_favorite), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun observeMovieDetailResult() {
        vm.movieDetailResult.observe(this) {
            when (it) {
                is LoadResult.Loading -> binding.progressBar.visible()
                is LoadResult.Success -> {
                    binding.progressBar.gone()
                    populateMovie(it.data)
                    vm.movieDetail = it.data
                }
                is LoadResult.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun populateMovie(movie: MovieDetail) {
        with(binding) {
            detailPoster.loadPoster(movie.posterPath)
            detailTitle.text = movie.title
            detailDate.text = movie.releaseDate.formatDate()
            detailLanguage.text = movie.originalLanguage
            detailVote.text = movie.voteAverage.toString()
            detailPopularity.text = movie.popularity.toString()
            detailOverview.text = movie.overview
        }
    }
}
