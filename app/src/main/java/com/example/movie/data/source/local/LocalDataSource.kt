package com.example.movie.data.source.local

import androidx.paging.DataSource
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovies()
    }

    fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
}