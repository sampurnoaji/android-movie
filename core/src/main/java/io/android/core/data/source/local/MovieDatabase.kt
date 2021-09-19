package io.android.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.android.core.data.dto.NowPlayingEntity

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