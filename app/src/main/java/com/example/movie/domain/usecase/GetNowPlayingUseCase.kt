package com.example.movie.domain.usecase

import com.example.movie.abstraction.NoParamsUseCase
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.NowPlaying
import io.android.momobill.vo.Either

class GetNowPlayingUseCase(private val repository: MovieRepository) :
    NoParamsUseCase<List<NowPlaying>> {
    override suspend fun invoke(): Either<Exception, List<NowPlaying>> {
        return repository.getNowPlaying()
    }
}