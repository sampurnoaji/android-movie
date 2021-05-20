package com.example.movie.di

import org.koin.core.context.loadKoinModules

val appModule = loadKoinModules(
    listOf(
        networkModule,
        dataSourceModule,
        repositoryModule,
        mapperModule,
        viewModelModule
    )
)
