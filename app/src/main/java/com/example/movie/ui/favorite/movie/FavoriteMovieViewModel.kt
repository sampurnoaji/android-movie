package com.example.movie.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.domain.MovieRepository
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    suspend fun getFavoriteMovies() = repository.getFavoriteMovies()

    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        viewModelScope.launch {
            repository.deleteFavoriteMovie(favoriteMovie)
        }
    }
}