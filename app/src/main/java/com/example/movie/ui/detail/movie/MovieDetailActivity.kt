package com.example.movie.ui.detail.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.domain.Movie
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val vm: MovieDetailViewModel by viewModels()

    companion object {
        private const val INTENT_KEY_MOVIE_ID = "movieId"

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
            populateMovie(vm.getMovie())
        }
    }

    private fun populateMovie(movie: Movie) {
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