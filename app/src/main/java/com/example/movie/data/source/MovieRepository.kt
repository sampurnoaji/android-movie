package com.example.movie.data.source

import com.example.movie.domain.Movie
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<LoadResult<List<Movie>>>
}