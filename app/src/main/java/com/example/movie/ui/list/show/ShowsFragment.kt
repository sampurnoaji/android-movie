package com.example.movie.ui.list.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentShowsBinding
import com.example.movie.ui.detail.show.ShowDetailActivity
import com.example.movie.utils.gone
import com.example.movie.utils.visible
import com.example.movie.vo.LoadResult
import org.koin.android.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment(), ShowListAdapter.ShowItemListener {

    private lateinit var binding: FragmentShowsBinding
    private val vm: ShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getShows()
        vm.showsResult.observe(viewLifecycleOwner) {
            when (it) {
                is LoadResult.Loading -> binding.progressBar.visible()
                is LoadResult.Success -> {
                    binding.progressBar.gone()

                    val showListAdapter = ShowListAdapter(it.data, this)
                    with(binding.rvShows) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = showListAdapter
                    }
                }
                is LoadResult.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onShowClicked(id: Int) {
        ShowDetailActivity.start(requireActivity(), id)
    }
}
