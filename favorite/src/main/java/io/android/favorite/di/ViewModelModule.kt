package io.android.favorite.di

import io.android.favorite.ui.MovieFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModel(getFavoriteMoviesUseCase = get()) }
}