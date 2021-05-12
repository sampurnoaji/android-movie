package com.example.movie.di

import com.example.movie.data.mapper.MoviesMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MoviesMapper() }
}
