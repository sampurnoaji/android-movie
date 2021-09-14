package com.example.movie.ui.detail.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMovieDetailBinding
import io.android.core.util.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMovieDetailBinding::inflate)
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
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }



    }
}
