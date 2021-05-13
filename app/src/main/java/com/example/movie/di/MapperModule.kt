package com.example.movie.di

import com.example.movie.data.mapper.MovieDetailMapper
import com.example.movie.data.mapper.MoviesMapper
import com.example.movie.data.mapper.ShowDetailMapper
import com.example.movie.data.mapper.ShowsMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MoviesMapper() }
    factory { MovieDetailMapper() }
    factory { ShowsMapper() }
    factory { ShowDetailMapper() }
}
