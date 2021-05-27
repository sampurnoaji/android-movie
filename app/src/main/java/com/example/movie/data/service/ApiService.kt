package com.example.movie.data.service

import com.example.movie.data.source.remote.response.MovieDetailResponse
import com.example.movie.data.source.remote.response.MoviesResponse
import com.example.movie.data.source.remote.response.ShowDetailResponse
import com.example.movie.data.source.remote.response.ShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): MoviesResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("discover/tv")
    suspend fun getShows(@Query("api_key") apiKey: String): ShowsResponse

    @GET("tv/{showId}")
    suspend fun getShowDetail(
        @Path("showId") showId: Int,
        @Query("api_key") apiKey: String
    ): ShowDetailResponse
}
