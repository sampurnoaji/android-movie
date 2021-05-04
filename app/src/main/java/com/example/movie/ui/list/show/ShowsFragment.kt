package com.example.movie.ui.list.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentShowsBinding
import com.example.movie.ui.detail.show.ShowDetailActivity

class ShowsFragment : Fragment(), ShowListAdapter.ShowItemListener {

    private lateinit var binding: FragmentShowsBinding
    private val vm: ShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showListAdapter = ShowListAdapter(vm.getShows(), this)
        with(binding.rvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = showListAdapter
        }
    }

    override fun onShowClicked(id: Int) {
        ShowDetailActivity.start(requireActivity(), id)
    }
}