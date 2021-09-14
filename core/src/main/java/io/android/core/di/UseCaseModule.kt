package io.android.core.di

import io.android.core.domain.usecase.GetNowPlayingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNowPlayingUseCase(repository = get()) }
}