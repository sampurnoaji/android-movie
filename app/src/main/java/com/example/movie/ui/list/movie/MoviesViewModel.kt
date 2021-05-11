package com.example.movie.ui.list.movie

import androidx.lifecycle.ViewModel
import com.example.movie.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies() = DataDummy.generateMovies()
}