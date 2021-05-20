package com.example.movie.ui.detail.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private var showId = 0

    private val _showDetailResult = MutableLiveData<LoadResult<ShowDetail>>()
    val showDetailResult: LiveData<LoadResult<ShowDetail>>
        get() = _showDetailResult

    fun setSelectedShow(showId: Int) {
        this.showId = showId
    }

    fun getShowDetail() {
        viewModelScope.launch {
            repository.getShowDetail(showId).collect {
                _showDetailResult.value = it
            }
        }
    }
}