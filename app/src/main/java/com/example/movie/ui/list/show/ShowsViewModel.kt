package com.example.movie.ui.list.show

import androidx.lifecycle.ViewModel
import com.example.movie.domain.MovieRepository

class ShowsViewModel(private val repository: MovieRepository) : ViewModel() {

    suspend fun getShows() = repository.getShows()
}
