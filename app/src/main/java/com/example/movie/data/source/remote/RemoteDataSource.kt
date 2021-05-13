package com.example.movie.data.source.remote

import com.example.movie.BuildConfig
import com.example.movie.data.service.ApiService
import com.example.movie.data.source.remote.response.MovieDetailDto
import com.example.movie.data.source.remote.response.MoviesDto
import com.example.movie.data.source.remote.response.ShowsDto

class RemoteDataSource(private val service: ApiService) {

    suspend fun getMovies(): MoviesDto {
        return service.getMovies(BuildConfig.API_KEY)
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailDto {
        return service.getMovieDetail(movieId, BuildConfig.API_KEY)
    }

    suspend fun getShows(): ShowsDto {
        return service.getShows(BuildConfig.API_KEY)
    }
}
