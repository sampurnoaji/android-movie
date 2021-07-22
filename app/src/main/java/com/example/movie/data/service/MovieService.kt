package com.example.movie.data.service

import com.example.movie.data.dto.response.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): Response<NowPlayingResponse>
}