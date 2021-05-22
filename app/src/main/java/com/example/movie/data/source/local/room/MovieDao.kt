package com.example.movie.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_movie_entities")
    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieEntity>

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(shows: List<ShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteShow(favoriteShow: FavoriteShowEntity)

    @Query("SELECT * FROM show_entities")
    fun getShows(): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM favorite_show_entities")
    fun getFavoriteShows(): DataSource.Factory<Int, FavoriteShowEntity>

    @Delete
    suspend fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity)
}