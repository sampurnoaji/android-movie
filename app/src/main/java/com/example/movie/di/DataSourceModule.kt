package com.example.movie.di

import com.example.movie.data.source.MovieRemoteDataSource
import com.example.movie.data.source.local.LocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MovieRemoteDataSource(service = get()) }
    single { LocalDataSource(movieDao = get()) }
}
