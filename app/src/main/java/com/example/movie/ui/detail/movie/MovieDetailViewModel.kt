package com.example.movie.ui.detail.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.android.core.domain.model.NowPlaying
import io.android.core.domain.usecase.UpdateFavoriteMovieUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase) :
    ViewModel() {

    fun setFavoriteGame(movie: NowPlaying, newStatus: Boolean) {
        viewModelScope.launch {
            updateFavoriteMovieUseCase(movie, newStatus)
        }
    }
}