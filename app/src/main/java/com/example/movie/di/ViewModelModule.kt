package com.example.movie.di

import com.example.movie.ui.detail.movie.MovieDetailViewModel
import com.example.movie.ui.list.movie.MoviesViewModel
import com.example.movie.ui.list.show.ShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { ShowsViewModel(get()) }
}