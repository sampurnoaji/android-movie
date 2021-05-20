package com.example.movie.ui.list.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.Movie
import com.example.movie.domain.MovieRepository
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _moviesResult = MutableLiveData<LoadResult<List<Movie>>>()
    val movieResult: LiveData<LoadResult<List<Movie>>>
        get() = _moviesResult

    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies().collect {
                _moviesResult.value = it
            }
        }
    }
}
