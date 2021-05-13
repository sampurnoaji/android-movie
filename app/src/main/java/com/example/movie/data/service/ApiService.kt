package com.example.movie.data.service

import com.example.movie.data.source.remote.response.MovieDetailDto
import com.example.movie.data.source.remote.response.MoviesDto
import com.example.movie.data.source.remote.response.ShowDetailDto
import com.example.movie.data.source.remote.response.ShowsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailDto

    @GET("discover/tv")
    suspend fun getShows(@Query("api_key") apiKey: String): ShowsDto

    @GET("tv/{showId}")
    suspend fun getShowDetail(
        @Path("showId") showId: Int,
        @Query("api_key") apiKey: String
    ): ShowDetailDto
}
