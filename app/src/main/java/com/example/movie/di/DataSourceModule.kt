package com.example.movie.di

import com.example.movie.data.source.local.LocalDataSource
import com.example.movie.data.source.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { RemoteDataSource(service = get()) }
    single { LocalDataSource(movieDao = get()) }
}
