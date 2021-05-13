package com.example.movie.di

import com.example.movie.data.source.MovieRepository
import com.example.movie.data.source.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}