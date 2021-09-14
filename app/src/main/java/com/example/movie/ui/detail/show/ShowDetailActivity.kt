package com.example.movie.ui.detail.show

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityShowDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailBinding
    private val vm: ShowDetailViewModel by viewModel()

    companion object {
        const val INTENT_KEY_SHOW_ID = "showId"

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
    }
}
