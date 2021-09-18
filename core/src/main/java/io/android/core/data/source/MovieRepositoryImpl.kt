package io.android.core.data.source

import io.android.core.data.dto.NowPlayingResponse
import io.android.core.data.mapper.NowPlayingMapper
import io.android.core.data.source.local.MovieLocalDataSource
import io.android.core.data.source.remote.MovieRemoteDataSource
import io.android.core.domain.model.NowPlaying
import io.android.core.domain.repository.MovieRepository
import io.android.core.vo.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val nowPlayingMapper: NowPlayingMapper,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override suspend fun getNowPlaying(): Flow<Either<Exception, List<NowPlaying>>> {
        return object : Nbr<Either<Exception, List<NowPlaying>>, NowPlayingResponse>() {
            override suspend fun saveCallResult(response: NowPlayingResponse) {
                movieLocalDataSource.insertNowPlaying(nowPlayingMapper.toEntity(response))
            }

            override fun shouldFetch(data: Either<Exception, List<NowPlaying>>?): Boolean {
                return when (data) {
                    is Either.Success -> data.data.isEmpty()
                    else -> true
                }
            }

            override suspend fun loadFromDb(): Either<Exception, List<NowPlaying>> {
                return when (val result = movieLocalDataSource.getNowPlaying()) {
                    is Either.Success -> Either.Success(nowPlayingMapper.toDomain(result.data))
                    is Either.Failure -> Either.Failure(result.cause)
                }
            }

            override suspend fun createCall(): Either<Exception, NowPlayingResponse> {
                return movieRemoteDataSource.getNowPlaying()
            }

            override fun onFetchFailed(
                cause: Exception,
                dbSource: Either<Exception, List<NowPlaying>>
            ): Either<Exception, List<NowPlaying>> {
                val data = if (dbSource is Either.Failure) dbSource.data else null
                return Either.Failure(cause, data)
            }
        }.asFlow()
    }

    override suspend fun getFavoriteMovies(): Either<Exception, Flow<List<NowPlaying>>> {
        return when (val response = movieLocalDataSource.getFavoriteMovies()) {
            is Either.Success -> {
                val data = flow { emit(nowPlayingMapper.toDomain(response.data)) }
                Either.Success(data)
            }
            is Either.Failure -> Either.Failure(response.cause)
        }
    }

    override suspend fun updateFavoriteMovie(movie: NowPlaying, newState: Boolean) {
        movieLocalDataSource.updateFavoriteMovie(nowPlayingMapper.toEntity(movie), newState)
    }
}
