package com.example.movie.di

import io.android.core.di.dataSourceModule
import io.android.core.di.databaseModule
import io.android.core.di.mapperModule
import io.android.core.di.networkModule
import io.android.core.di.repositoryModule
import io.android.core.di.useCaseModule
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
