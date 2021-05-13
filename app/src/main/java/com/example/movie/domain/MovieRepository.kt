package com.example.movie.domain

import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<LoadResult<List<Movie>>>
    suspend fun getMovieDetail(movieId: Int): Flow<LoadResult<MovieDetail>>
    suspend fun getShows(): Flow<LoadResult<List<Show>>>
    suspend fun getShowDetail(showId: Int): Flow<LoadResult<ShowDetail>>
}
