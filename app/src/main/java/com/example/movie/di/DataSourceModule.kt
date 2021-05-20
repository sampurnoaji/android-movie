package com.example.movie.di

import com.example.movie.data.source.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { RemoteDataSource(get()) }
}
