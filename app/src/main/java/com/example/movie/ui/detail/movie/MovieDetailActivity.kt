package com.example.movie.ui.detail.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.data.Movie
import com.example.movie.databinding.ActivityMovieDetailBinding
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
        binding.detailPoster.loadPoster(movie.posterUrl)
        binding.detailTitle.text = movie.title
        binding.detailDate.text = movie.releaseDate.formatDate()
        binding.detailLanguage.text = movie.language
        binding.detailVote.text = movie.voteAverage.toString()
        binding.detailPopularity.text = movie.popularity.toString()
        binding.detailOverview.text = movie.overview
    }
}