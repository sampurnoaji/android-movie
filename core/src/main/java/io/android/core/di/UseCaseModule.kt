package io.android.core.di

import io.android.core.domain.usecase.GetFavoriteMoviesUseCase
import io.android.core.domain.usecase.GetNowPlayingUseCase
import io.android.core.domain.usecase.UpdateFavoriteMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNowPlayingUseCase(repository = get()) }
    factory { GetFavoriteMoviesUseCase(repository = get()) }
    factory { UpdateFavoriteMovieUseCase(repository = get()) }
}