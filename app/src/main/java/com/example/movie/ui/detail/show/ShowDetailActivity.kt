package com.example.movie.ui.detail.show

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityShowDetailBinding
import com.example.movie.domain.Show
import com.example.movie.utils.formatDate
import com.example.movie.utils.loadPoster

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailBinding
    private val vm: ShowDetailViewModel by viewModels()

    companion object {
        private const val INTENT_KEY_SHOW_ID = "showId"

        @JvmStatic
        fun start(context: Context, showId: Int) {
            val starter = Intent(context, ShowDetailActivity::class.java)
                .putExtra(INTENT_KEY_SHOW_ID, showId)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        
        val showId = intent.extras?.getInt(INTENT_KEY_SHOW_ID)
        showId?.let { 
            vm.setSelectedShow(it)
            populateShow(vm.getShow())
        }
    }

    private fun populateShow(show: Show) {
        with(binding) {
            detailPoster.loadPoster(show.posterPath)
            detailTitle.text = show.name
            detailDate.text = show.firstAirDate.formatDate()
            detailLanguage.text = show.originalLanguage
            detailVote.text = show.voteAverage.toString()
            detailPopularity.text = show.popularity.toString()
            detailOverview.text = show.overview
        }
    }
}
