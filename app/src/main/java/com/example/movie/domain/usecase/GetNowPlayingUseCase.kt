package com.example.movie.domain.usecase

import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.NowPlaying
import kotlinx.coroutines.flow.Flow

class GetNowPlayingUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): Flow<List<NowPlaying>> {
        return repository.getNowPlaying()
    }
}