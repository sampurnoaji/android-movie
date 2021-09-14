package com.example.movie.data.source

import com.example.movie.data.dto.response.NowPlayingResponse
import com.example.movie.data.mapper.NowPlayingMapper
import com.example.movie.data.source.local.LocalDataSource
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.NowPlaying
import com.example.movie.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val nowPlayingMapper: NowPlayingMapper,
    private val localDataSource: LocalDataSource
) : MovieRepository {

    override suspend fun getNowPlaying(): Flow<List<NowPlaying>> {
        return object : Nbr<List<NowPlaying>, NowPlayingResponse>() {
            override suspend fun saveCallResult(response: NowPlayingResponse) {
                localDataSource.insertNowPlaying(nowPlayingMapper.toEntity(response))
            }

            override fun shouldFetch(data: List<NowPlaying>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun loadFromDb(): List<NowPlaying> {
                return withContext(Dispatchers.IO) {
                    val result = localDataSource.getNowPlaying()
                    nowPlayingMapper.toDomain(result)
                }
            }

            override suspend fun createCall(): Either<Exception, NowPlayingResponse> {
                return movieRemoteDataSource.getNowPlaying()
            }
        }.asFlow()
    }
}
