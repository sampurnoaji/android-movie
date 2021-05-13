package com.example.movie.data.service

import com.example.movie.data.source.remote.response.MoviesDto
import com.example.movie.data.source.remote.response.ShowsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesDto

    @GET("discover/tv")
    suspend fun getShows(@Query("api_key") apiKey: String): ShowsDto
}
