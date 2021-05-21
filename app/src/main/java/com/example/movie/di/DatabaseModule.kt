package com.example.movie.di

import android.app.Application
import androidx.room.Room
import com.example.movie.data.source.local.room.MovieDao
import com.example.movie.data.source.local.room.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}

fun provideDatabase(application: Application): MovieDatabase {
    return Room.databaseBuilder(application, MovieDatabase::class.java, "movie-db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideCountriesDao(database: MovieDatabase): MovieDao {
    return database.movieDao()
}
