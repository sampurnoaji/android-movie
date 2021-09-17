package io.android.core.data.source.remote

import io.android.core.BuildConfig
import io.android.core.data.dto.NowPlayingResponse
import io.android.core.data.service.MovieService
import io.android.core.data.source.ApiHandler
import io.android.core.vo.DataResponse
import io.android.core.vo.Either

class MovieRemoteDataSource(private val service: MovieService) : ApiHandler() {
    suspend fun getNowPlaying(): Either<Exception, NowPlayingResponse> {
        return when (val response = call { service.getNowPlaying(BuildConfig.API_KEY) }) {
            is DataResponse.Success -> Either.Success(response.data)
            is DataResponse.Failure -> Either.Failure(response.cause)
        }
    }
}