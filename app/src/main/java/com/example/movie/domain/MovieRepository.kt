package com.example.movie.domain

import com.example.movie.domain.entity.NowPlaying
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<List<NowPlaying>>
}
