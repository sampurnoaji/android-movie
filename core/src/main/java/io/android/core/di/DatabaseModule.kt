package io.android.core.di

import android.app.Application
import androidx.room.Room
import io.android.core.data.source.local.MovieDao
import io.android.core.data.source.local.MovieDatabase
import io.android.core.util.DbConstant
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}

fun provideDatabase(application: Application): MovieDatabase {
    return Room.databaseBuilder(application, MovieDatabase::class.java, DbConstant.DATABASE)
        .fallbackToDestructiveMigration()
        .build()
}

fun provideCountriesDao(database: MovieDatabase): MovieDao {
    return database.movieDao()
}
