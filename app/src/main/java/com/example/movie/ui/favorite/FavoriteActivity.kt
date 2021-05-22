package com.example.movie.ui.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movie.R
import com.example.movie.databinding.ActivityFavoriteBinding
import com.example.movie.ui.favorite.movie.FavoriteMovieFragment
import com.example.movie.ui.list.SectionsPagerAdapter

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SectionsPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        adapter.addFragment(FavoriteMovieFragment(), getString(R.string.movies))

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_newest -> Toast.makeText(this, "new", Toast.LENGTH_SHORT).show()
            R.id.action_oldest -> Toast.makeText(this, "old", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}