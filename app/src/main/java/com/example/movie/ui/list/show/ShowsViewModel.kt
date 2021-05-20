package com.example.movie.ui.list.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.Show
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowsViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _showsResult = MutableLiveData<LoadResult<List<Show>>>()
    val showsResult: LiveData<LoadResult<List<Show>>>
        get() = _showsResult

    fun getShows() {
        viewModelScope.launch {
            repository.getShows().collect {
                _showsResult.value = it
            }
        }
    }
}
