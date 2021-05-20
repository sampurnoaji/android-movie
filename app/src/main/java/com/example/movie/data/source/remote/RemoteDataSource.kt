package com.example.movie.data.source.remote

import com.example.movie.BuildConfig
import com.example.movie.data.service.ApiService
import com.example.movie.data.source.remote.response.MovieDetailResponse
import com.example.movie.data.source.remote.response.MoviesResponse
import com.example.movie.data.source.remote.response.ShowDetailResponse
import com.example.movie.data.source.remote.response.ShowsResponse

class RemoteDataSource(private val service: ApiService) {

    suspend fun getMovies(): MoviesResponse {
        return service.getMovies(BuildConfig.API_KEY)
    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return service.getMovieDetail(movieId, BuildConfig.API_KEY)
    }

    suspend fun getShows(): ShowsResponse {
        return service.getShows(BuildConfig.API_KEY)
    }

    suspend fun getShowDetail(showId: Int): ShowDetailResponse {
        return service.getShowDetail(showId, BuildConfig.API_KEY)
    }
}
