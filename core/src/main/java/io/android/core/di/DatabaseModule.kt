package io.android.core.di

import android.app.Application
import androidx.room.Room
import io.android.core.data.source.local.MovieDao
import io.android.core.data.source.local.MovieDatabase
import io.android.core.util.DbConstant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideCountriesDao(get()) }
    single { provideDatabase(androidApplication()) }
}

fun provideDatabase(application: Application): MovieDatabase {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("games".toCharArray())
    val factory = SupportFactory(passphrase)
    return Room.databaseBuilder(application, MovieDatabase::class.java, DbConstant.DATABASE)
        .fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()
}

fun provideCountriesDao(database: MovieDatabase): MovieDao {
    return database.movieDao()
}
