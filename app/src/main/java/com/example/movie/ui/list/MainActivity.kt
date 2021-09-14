package com.example.movie.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.ui.detail.movie.MovieDetailActivity
import io.android.core.util.gone
import io.android.core.util.viewBinding
import io.android.core.util.visible
import io.android.core.vo.ViewState
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val vm by viewModel<MainViewModel>()

    private val nowPlayingListAdapter by lazy { NowPlayingListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()

        observeNowPlayingResult()
        vm.getNowPlaying()
    }

    private fun observeNowPlayingResult() {
        vm.nowPlaying.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbNowPlaying.visible()
                }
                is ViewState.Success -> {
                    binding.pgbNowPlaying.gone()
                    nowPlayingListAdapter.submitList(it.data)
                }
                is ViewState.Error -> {
                    binding.pgbNowPlaying.gone()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvNowPlaying.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = nowPlayingListAdapter
        }
        nowPlayingListAdapter.onItemClick = { nowPlaying ->
            MovieDetailActivity.start(this, nowPlaying)
        }
    }
}