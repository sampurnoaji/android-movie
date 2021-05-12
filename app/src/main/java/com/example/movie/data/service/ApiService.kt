package com.example.movie.data.service

import com.example.movie.data.source.remote.response.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesDto
}
