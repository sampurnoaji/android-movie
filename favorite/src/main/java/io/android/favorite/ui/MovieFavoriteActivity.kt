package io.android.favorite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.ui.detail.movie.MovieDetailActivity
import io.android.core.util.gone
import io.android.core.util.viewBinding
import io.android.core.util.visible
import io.android.core.vo.ViewState
import io.android.favorite.databinding.ActivityMovieFavoriteBinding
import io.android.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MovieFavoriteActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMovieFavoriteBinding::inflate)
    private val vm by viewModel<MovieFavoriteViewModel>()

    private val favoriteListAdapter by lazy { FavoriteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()

        observeFavoritesResult()
        vm.getNowPlaying()
    }

    private fun observeFavoritesResult() {
        vm.favorites.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pgbNowPlaying.visible()
                }
                is ViewState.Success -> {
                    binding.pgbNowPlaying.gone()
                    favoriteListAdapter.submitList(it.data)
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
                this@MovieFavoriteActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = favoriteListAdapter
        }
        favoriteListAdapter.onItemClick = { favorite ->
            MovieDetailActivity.start(this, favorite)
        }
    }
}