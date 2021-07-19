package com.example.movie.di

import org.koin.core.context.loadKoinModules

val appModule = loadKoinModules(
    listOf(
        databaseModule,
        networkModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
        mapperModule,
        viewModelModule
    )
)
