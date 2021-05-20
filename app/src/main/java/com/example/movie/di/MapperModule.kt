package com.example.movie.di

import com.example.movie.data.mapper.response.MovieDetailResponseMapper
import com.example.movie.data.mapper.response.MoviesResponseMapper
import com.example.movie.data.mapper.response.ShowDetailResponseMapper
import com.example.movie.data.mapper.response.ShowsResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MoviesResponseMapper() }
    factory { MovieDetailResponseMapper() }
    factory { ShowsResponseMapper() }
    factory { ShowDetailResponseMapper() }
}
