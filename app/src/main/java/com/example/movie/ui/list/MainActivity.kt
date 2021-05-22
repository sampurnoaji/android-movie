package com.example.movie.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movie.R
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.ui.favorite.FavoriteActivity
import com.example.movie.ui.list.movie.MoviesFragment
import com.example.movie.ui.list.show.ShowsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SectionsPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        adapter.addFragment(MoviesFragment(), getString(R.string.movies))
        adapter.addFragment(ShowsFragment(), getString(R.string.shows))

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.action_newest -> Toast.makeText(this, "new", Toast.LENGTH_SHORT).show()
            R.id.action_oldest -> Toast.makeText(this, "old", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}