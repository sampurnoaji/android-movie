package com.example.movie.ui.favorite.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.databinding.FragmentFavoriteShowBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteShowFragment : Fragment(), FavoriteShowListAdapter.ShowItemListener {

    private lateinit var binding: FragmentFavoriteShowBinding
    private val vm: FavoriteShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.getFavoriteShows().observe(viewLifecycleOwner) {
                val showListAdapter = FavoriteShowListAdapter(this@FavoriteShowFragment)
                with(binding.rvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = showListAdapter
                }
                showListAdapter.submitList(it)
            }
        }
    }

    override fun onShowClicked(id: Int) {}
    override fun onDeleteFavoriteShow(favoriteShow: FavoriteShowEntity) {
        vm.deleteFavoriteShow(favoriteShow)
    }
}