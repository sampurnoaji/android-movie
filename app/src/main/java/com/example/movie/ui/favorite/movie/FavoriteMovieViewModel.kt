package com.example.movie.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.example.movie.domain.MovieRepository

class FavoriteMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    suspend fun getFavoriteMovies() = repository.getFavoriteMovies()
}