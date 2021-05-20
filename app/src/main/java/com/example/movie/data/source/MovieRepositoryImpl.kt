package com.example.movie.data.source

import com.example.movie.data.mapper.response.MovieDetailResponseMapper
import com.example.movie.data.mapper.response.MoviesResponseMapper
import com.example.movie.data.mapper.response.ShowDetailResponseMapper
import com.example.movie.data.mapper.response.ShowsResponseMapper
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.Movie
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.domain.entity.Show
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val moviesResponseMapper: MoviesResponseMapper,
    private val movieDetailResponseMapper: MovieDetailResponseMapper,
    private val showsResponseMapper: ShowsResponseMapper,
    private val showDetailResponseMapper: ShowDetailResponseMapper
) : MovieRepository {

    override suspend fun getMovies(): Flow<LoadResult<List<Movie>>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getMovies()
                emit(LoadResult.Success(moviesResponseMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<LoadResult<MovieDetail>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getMovieDetail(movieId)
                emit(LoadResult.Success(movieDetailResponseMapper(result)))
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
                emit(LoadResult.Success(showsResponseMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }

    override suspend fun getShowDetail(showId: Int): Flow<LoadResult<ShowDetail>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getShowDetail(showId)
                emit(LoadResult.Success(showDetailResponseMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }
}
