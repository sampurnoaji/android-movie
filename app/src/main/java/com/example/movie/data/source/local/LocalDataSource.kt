package com.example.movie.data.source.local

import com.example.movie.data.dto.entity.NowPlayingEntity
import com.example.movie.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>) {
        movieDao.insertNowPlaying(nowPlaying)
    }

    suspend fun getNowPlaying(): List<NowPlayingEntity> {
        return movieDao.getNowPlaying()
    }
}