package com.example.movie.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity

@Database(
    entities = [
        MovieEntity::class,
        FavoriteMovieEntity::class,
        ShowEntity::class,
        FavoriteShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}