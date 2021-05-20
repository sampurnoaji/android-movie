package com.example.movie.di

import com.example.movie.BuildConfig
import com.example.movie.data.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)