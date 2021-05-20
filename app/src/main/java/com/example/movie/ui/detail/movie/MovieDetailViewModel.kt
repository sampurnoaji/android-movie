package com.example.movie.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.MovieDetail
import com.example.movie.domain.MovieRepository
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private var movieId = 0

    private val _movieDetailResult = MutableLiveData<LoadResult<MovieDetail>>()
    val movieDetailResult: LiveData<LoadResult<MovieDetail>>
        get() = _movieDetailResult

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovieDetail() {
        viewModelScope.launch {
            repository.getMovieDetail(movieId).collect {
                _movieDetailResult.value = it
            }
        }
    }
}