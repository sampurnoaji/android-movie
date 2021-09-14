package io.android.core.di

import io.android.core.data.source.local.MovieLocalDataSource
import io.android.core.data.source.remote.MovieRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MovieRemoteDataSource(service = get()) }
    single { MovieLocalDataSource(movieDao = get()) }
}
