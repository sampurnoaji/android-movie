package io.android.core.data.service

import io.android.core.data.dto.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): Response<NowPlayingResponse>
}