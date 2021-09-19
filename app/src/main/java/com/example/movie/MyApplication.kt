package com.example.movie

import android.app.Application
import com.example.movie.di.viewModelModule
import io.android.core.di.dataSourceModule
import io.android.core.di.databaseModule
import io.android.core.di.mapperModule
import io.android.core.di.networkModule
import io.android.core.di.repositoryModule
import io.android.core.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    dataSourceModule,
                    mapperModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
