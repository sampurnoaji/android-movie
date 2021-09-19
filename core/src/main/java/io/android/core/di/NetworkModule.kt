package io.android.core.di

import io.android.core.BuildConfig
import io.android.core.data.service.MovieService
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideMovieService(get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val hostname = "api.themoviedb.org"
    val certificatePinner = CertificatePinner.Builder()
        .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
        .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
        .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
        .build()
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(loggingInterceptor)
    }
    return client.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)