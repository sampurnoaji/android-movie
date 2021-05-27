package com.example.movie.ui.list.show

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movie.R
import com.example.movie.databinding.FragmentShowsBinding
import com.example.movie.domain.entity.Show
import com.example.movie.ui.detail.show.ShowDetailActivity
import com.example.movie.utils.database.SortUtil
import com.example.movie.utils.gone
import com.example.movie.utils.visible
import com.example.movie.vo.Resource
import com.example.movie.vo.Status
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment(R.layout.fragment_shows), ShowListAdapter.ShowItemListener {

    private val binding: FragmentShowsBinding by viewBinding()
    private val vm: ShowsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.getShows(SortUtil.NEWEST).observe(viewLifecycleOwner, showsObserver)
        }

        binding.fabSortNewest.setOnClickListener {
            lifecycleScope.launch {
                vm.getShows(SortUtil.NEWEST).observe(viewLifecycleOwner, showsObserver)
            }
        }
        binding.fabSortOldest.setOnClickListener {
            lifecycleScope.launch {
                vm.getShows(SortUtil.OLDEST).observe(viewLifecycleOwner, showsObserver)
            }
        }
    }

    private val showsObserver = Observer<Resource<PagedList<Show>>> {
        when (it.status) {
            Status.LOADING -> binding.progressBar.visible()
            Status.SUCCESS -> {
                binding.progressBar.gone()

                val showListAdapter = ShowListAdapter(this@ShowsFragment)
                with(binding.rvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = showListAdapter
                }
                showListAdapter.submitList(it.data)
            }
            Status.ERROR -> {
                binding.progressBar.gone()
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onShowClicked(id: Int) {
        ShowDetailActivity.start(requireActivity(), id)
    }
}
