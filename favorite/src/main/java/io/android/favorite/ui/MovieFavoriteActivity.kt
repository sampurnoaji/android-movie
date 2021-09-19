package io.android.favorite.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
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

    companion object {
        private const val GRID_SPAN = 2
    }

    private val binding by viewBinding(ActivityMovieFavoriteBinding::inflate)
    private val vm by viewModel<MovieFavoriteViewModel>()

    private val favoriteListAdapter by lazy { FavoriteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupRecyclerView()

        observeFavoritesResult()
    }

    override fun onResume() {
        super.onResume()
        vm.getFavorites()
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
                    if (it.data.isEmpty()) {
                        Toast.makeText(this, getString(R.string.empty_data), Toast.LENGTH_SHORT)
                            .show()
                        return@observe
                    }
                }
                is ViewState.Error -> {
                    binding.pgbNowPlaying.gone()
                }
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupRecyclerView() {
        binding.rvNowPlaying.apply {
            layoutManager = GridLayoutManager(
                this@MovieFavoriteActivity,
                GRID_SPAN
            )
            adapter = favoriteListAdapter
        }
        favoriteListAdapter.onItemClick = { favorite ->
            MovieDetailActivity.start(this, favorite)
        }
    }
}