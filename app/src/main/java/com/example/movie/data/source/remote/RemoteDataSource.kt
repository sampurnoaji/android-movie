package com.example.movie.data.source.remote

import com.example.movie.BuildConfig
import com.example.movie.data.service.ApiService
import com.example.movie.data.source.remote.response.MoviesDto

class RemoteDataSource(private val service: ApiService) {

    suspend fun getMovies(): MoviesDto {
        return service.getMovies(BuildConfig.API_KEY)
    }
}
