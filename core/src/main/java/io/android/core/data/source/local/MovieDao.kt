package io.android.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.android.core.data.dto.NowPlayingEntity
import io.android.core.util.DbConstant

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>)

    @Query("SELECT * FROM ${DbConstant.ENTITY_NOW_PLAYING}")
    suspend fun getNowPlaying(): List<NowPlayingEntity>

    @Query("SELECT * FROM ${DbConstant.ENTITY_NOW_PLAYING} where is_favorite = 1")
    suspend fun getFavoriteMovies(): List<NowPlayingEntity>

    @Update
    suspend fun updateFavoriteMovie(movie: NowPlayingEntity)
}