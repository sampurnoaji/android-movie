package com.example.movie.data.source.local

import androidx.paging.DataSource
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovies()
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

    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        movieDao.deleteFavoriteMovie(favoriteMovie)
    }



    fun getShows(): DataSource.Factory<Int, ShowEntity> {
        return movieDao.getShows()
    }

    fun insertShows(movies: List<ShowEntity>) {
        movieDao.insertShows(movies)
    }

    suspend fun insertFavoriteShow(favoriteShow: FavoriteShowEntity) {
        movieDao.insertFavoriteShow(favoriteShow)
    }

    fun getFavoriteShows(): DataSource.Factory<Int, FavoriteShowEntity> {
        return movieDao.getFavoriteShows()
    }

    suspend fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity) {
        movieDao.deleteFavoriteShow(favoriteShow)
    }
}