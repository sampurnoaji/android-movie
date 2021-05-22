package com.example.movie.ui.favorite.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.domain.MovieRepository
import kotlinx.coroutines.launch

class FavoriteShowViewModel(private val repository: MovieRepository) : ViewModel() {

    suspend fun getFavoriteShows() = repository.getFavoriteShows()

    fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity) {
        viewModelScope.launch {
            repository.deleteFavoriteShow(favoriteShow)
        }
    }
}