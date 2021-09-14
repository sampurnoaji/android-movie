package io.android.core.di

import io.android.core.data.source.MovieRepositoryImpl
import io.android.core.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieRemoteDataSource = get(),
            nowPlayingMapper = get(),
            movieLocalDataSource = get()
        )
    }
}