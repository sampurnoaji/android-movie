package io.android.core.data.source.remote

import io.android.core.BuildConfig
import io.android.core.data.dto.NowPlayingResponse
import io.android.core.data.service.MovieService
import io.android.core.data.source.ApiClient
import io.android.core.vo.ApiResponse
import io.android.core.vo.Either

class MovieRemoteDataSource(private val service: MovieService) : ApiClient() {
    suspend fun getNowPlaying(): Either<Exception, NowPlayingResponse> {
        return when (val response = call { service.getNowPlaying(BuildConfig.API_KEY) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}