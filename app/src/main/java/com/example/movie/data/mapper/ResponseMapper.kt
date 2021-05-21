package com.example.movie.data.mapper

import com.example.movie.data.mapper.response.MovieDetailResponseMapper
import com.example.movie.data.mapper.response.MoviesResponseMapper
import com.example.movie.data.mapper.response.ShowDetailResponseMapper
import com.example.movie.data.mapper.response.ShowsResponseMapper

data class ResponseMapper(
    val moviesResponseMapper: MoviesResponseMapper,
    val movieDetailResponseMapper: MovieDetailResponseMapper,
    val showsResponseMapper: ShowsResponseMapper,
    val showDetailResponseMapper: ShowDetailResponseMapper
)
