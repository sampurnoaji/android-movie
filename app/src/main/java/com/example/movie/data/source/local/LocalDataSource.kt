package com.example.movie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovies()
    }

    fun getMovieById(id: Int): LiveData<MovieEntity> {
        return movieDao.getMovieById(id)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        movieDao.insertFavoriteMovie(favoriteMovie)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieEntity> {
        return movieDao.getFavoriteMovies()
    }
}