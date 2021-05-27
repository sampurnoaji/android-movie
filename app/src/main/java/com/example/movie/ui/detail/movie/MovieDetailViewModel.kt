package com.example.movie.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private var movieId = 0

    private val _movieDetailResult = MutableLiveData<LoadResult<MovieDetail>>()
    val movieDetailResult: LiveData<LoadResult<MovieDetail>>
        get() = _movieDetailResult

    var movieDetail: MovieDetail? = null

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

    fun insertFavoriteMovie() {
        viewModelScope.launch {
            val favoriteMovie = FavoriteMovieEntity(
                id = movieDetail?.id ?: 0,
                title = movieDetail?.title,
                releaseDate = movieDetail?.releaseDate,
                posterPath = movieDetail?.posterPath,
                overview = movieDetail?.overview
            )
            repository.insertFavoriteMovie(favoriteMovie)
        }
    }
}