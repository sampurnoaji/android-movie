package com.example.movie.data.source

import com.example.movie.data.mapper.MoviesMapper
import com.example.movie.data.mapper.ShowsMapper
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.domain.Movie
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.Show
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val moviesMapper: MoviesMapper,
    private val showsMapper: ShowsMapper
) : MovieRepository {

    override suspend fun getMovies(): Flow<LoadResult<List<Movie>>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getMovies()
                emit(LoadResult.Success(moviesMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }

    override suspend fun getShows(): Flow<LoadResult<List<Show>>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getShows()
                emit(LoadResult.Success(showsMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }
}
