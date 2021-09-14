package io.android.core.data.source.local

import io.android.core.data.dto.NowPlayingEntity

class MovieLocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>) {
        movieDao.insertNowPlaying(nowPlaying)
    }

    suspend fun getNowPlaying(): List<NowPlayingEntity> {
        return movieDao.getNowPlaying()
    }
}