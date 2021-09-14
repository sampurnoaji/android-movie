package com.example.movie.ui.detail.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMovieDetailBinding
import io.android.core.domain.model.NowPlaying
import io.android.core.util.formatDate
import io.android.core.util.loadPoster
import io.android.core.util.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMovieDetailBinding::inflate)
    private val vm: MovieDetailViewModel by viewModel()

    companion object {
        private const val INTENT_KEY_NOW_PLAYING = "now-playing"

        @JvmStatic
        fun start(context: Context, nowPlaying: NowPlaying) {
            val starter = Intent(context, MovieDetailActivity::class.java)
                .putExtra(INTENT_KEY_NOW_PLAYING, nowPlaying)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val nowPlaying = intent?.getParcelableExtra<NowPlaying>(INTENT_KEY_NOW_PLAYING)
        nowPlaying?.let { showMovie(it) }
    }

    private fun showMovie(movie: NowPlaying) {
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
