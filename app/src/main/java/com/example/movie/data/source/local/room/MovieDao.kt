package com.example.movie.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie.data.dto.entity.NowPlayingEntity
import com.example.movie.utils.database.DbConstant

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>)

    @Query("SELECT * FROM ${DbConstant.ENTITY_NOW_PLAYING}")
    suspend fun getNowPlaying(): List<NowPlayingEntity>
}