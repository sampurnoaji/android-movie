package com.example.movie.data.source.local

import androidx.paging.DataSource
import com.example.movie.data.dto.entity.NowPlayingEntity
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.data.source.local.room.MovieDao
import com.example.movie.utils.database.DbConstant
import com.example.movie.utils.database.SortUtil

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>) {
        movieDao.insertNowPlaying(nowPlaying)
    }

    suspend fun getNowPlaying(): List<NowPlayingEntity> {
        return movieDao.getNowPlaying()
    }



    fun getMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtil.getSortedQuery(DbConstant.ENTITY_MOVIE, "release_date", sort)
        return movieDao.getMovies(query)
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



    fun getShows(sort: String): DataSource.Factory<Int, ShowEntity> {
        val query = SortUtil.getSortedQuery(DbConstant.ENTITY_SHOW, "first_air_date", sort)
        return movieDao.getShows(query)
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