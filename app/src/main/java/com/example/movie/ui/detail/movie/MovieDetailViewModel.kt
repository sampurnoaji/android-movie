package com.example.movie.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.example.movie.data.Movie
import com.example.movie.utils.DataDummy

class MovieDetailViewModel : ViewModel() {

    private var movieId = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovie(): Movie {
        lateinit var movie: Movie
        val movies = DataDummy.generateMovies()
        for (it in movies) {
            if (it.id == movieId) {
                movie = it
            }
        }
        return movie
    }
}