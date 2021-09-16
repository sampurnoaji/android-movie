package io.android.core.data.source

import io.android.core.data.dto.NowPlayingResponse
import io.android.core.data.mapper.NowPlayingMapper
import io.android.core.data.source.local.MovieLocalDataSource
import io.android.core.data.source.remote.MovieRemoteDataSource
import io.android.core.domain.model.NowPlaying
import io.android.core.domain.repository.MovieRepository
import io.android.core.vo.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val nowPlayingMapper: NowPlayingMapper,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override suspend fun getNowPlaying(): Flow<List<NowPlaying>> {
        return object : Nbr<List<NowPlaying>, NowPlayingResponse>() {
            override suspend fun saveCallResult(response: NowPlayingResponse) {
                movieLocalDataSource.insertNowPlaying(nowPlayingMapper.toEntity(response))
            }

            override fun shouldFetch(data: List<NowPlaying>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun loadFromDb(): List<NowPlaying> {
                return withContext(Dispatchers.IO) {
                    val result = movieLocalDataSource.getNowPlaying()
                    nowPlayingMapper.toDomain(result)
                }
            }

            override suspend fun createCall(): Either<Exception, NowPlayingResponse> {
                return movieRemoteDataSource.getNowPlaying()
            }
        }.asFlow()
    }

    override suspend fun getFavoriteMovies(): Flow<List<NowPlaying>> {
        return flow {
            val movies = movieLocalDataSource.getFavoriteMovies()
            emit(nowPlayingMapper.toDomain(movies))
        }
    }

    override suspend fun updateFavoriteMovie(movie: NowPlaying, newState: Boolean) {
        movieLocalDataSource.updateFavoriteMovie(nowPlayingMapper.toEntity(movie), newState)
    }
}
