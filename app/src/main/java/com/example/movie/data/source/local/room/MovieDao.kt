package com.example.movie.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.movie.data.dto.entity.NowPlayingEntity
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.utils.database.DbConstant

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>)

    @Query("SELECT * FROM ${DbConstant.ENTITY_NOW_PLAYING}")
    suspend fun getNowPlaying(): List<NowPlayingEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM ${DbConstant.ENTITY_FAVORITE_MOVIE}")
    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieEntity>

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(shows: List<ShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteShow(favoriteShow: FavoriteShowEntity)

    @RawQuery(observedEntities = [ShowEntity::class])
    fun getShows(query: SupportSQLiteQuery): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT * FROM ${DbConstant.ENTITY_FAVORITE_SHOW}")
    fun getFavoriteShows(): DataSource.Factory<Int, FavoriteShowEntity>

    @Delete
    suspend fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity)
}