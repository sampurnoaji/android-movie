package com.example.movie.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.data.dto.entity.NowPlayingEntity

@Database(
    entities = [
        NowPlayingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}