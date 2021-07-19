package com.example.movie.data.source

import com.example.movie.BuildConfig
import com.example.movie.data.dto.NowPlayingResponse
import com.example.movie.data.service.MovieService
import com.example.movie.data.util.ApiClient
import com.example.movie.utils.ApiResponse
import io.android.momobill.vo.Either

class MovieRemoteDataSource(private val service: MovieService) : ApiClient() {
    suspend fun getNowPlaying(): Either<Exception, NowPlayingResponse> {
        return when (val response = call { service.getNowPlaying(BuildConfig.API_KEY) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}