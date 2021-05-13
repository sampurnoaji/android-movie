package com.example.movie.di

import com.example.movie.data.source.MovieRepositoryImpl
import com.example.movie.domain.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}