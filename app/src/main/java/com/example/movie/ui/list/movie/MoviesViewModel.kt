package com.example.movie.ui.list.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.Movie
import com.example.movie.vo.Resource

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _moviesResult = MutableLiveData<Resource<PagedList<Movie>>>()
    val movieResult: LiveData<Resource<PagedList<Movie>>>
        get() = _moviesResult

    suspend fun getMovies() = repository.getMovies()
}
