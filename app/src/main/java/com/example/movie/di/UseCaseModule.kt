package com.example.movie.di

import com.example.movie.domain.usecase.GetNowPlayingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNowPlayingUseCase(repository = get()) }
}