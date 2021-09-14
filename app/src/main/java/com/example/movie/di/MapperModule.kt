package com.example.movie.di

import com.example.movie.data.mapper.NowPlayingMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { NowPlayingMapper() }
}
