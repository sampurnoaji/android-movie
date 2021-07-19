package com.example.movie.di

import com.example.movie.data.source.MovieRepositoryImpl
import com.example.movie.domain.MovieRepository
import com.example.movie.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppExecutors() }
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieRemoteDataSource = get(),
            nowPlayingMapper = get(),
            remoteDataSource = get(),
            localDataSource = get(),
            appExecutors = get(),
            responseMapper = get(),
            entityMapper = get()
        )
    }
}