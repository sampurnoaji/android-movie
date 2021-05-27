package com.example.movie.di

import com.example.movie.data.mapper.EntityMapper
import com.example.movie.data.mapper.ResponseMapper
import com.example.movie.data.mapper.entity.MoviesEntityMapper
import com.example.movie.data.mapper.entity.ShowsEntityMapper
import com.example.movie.data.mapper.response.MovieDetailResponseMapper
import com.example.movie.data.mapper.response.MoviesResponseMapper
import com.example.movie.data.mapper.response.ShowDetailResponseMapper
import com.example.movie.data.mapper.response.ShowsResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MoviesResponseMapper() }
    factory { MovieDetailResponseMapper() }
    factory { ShowsResponseMapper() }
    factory { ShowDetailResponseMapper() }
    factory {
        ResponseMapper(
            moviesResponseMapper = get(),
            movieDetailResponseMapper = get(),
            showsResponseMapper = get(),
            showDetailResponseMapper = get()
        )
    }

    factory { MoviesEntityMapper() }
    factory { ShowsEntityMapper() }
    factory {
        EntityMapper(
            moviesEntityMapper = get(),
            showsEntityMapper = get()
        )
    }
}
