package com.example.movie.ui.list.movie

import androidx.lifecycle.ViewModel
import com.example.movie.domain.MovieRepository

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    suspend fun getMovies() = repository.getMovies()
}
