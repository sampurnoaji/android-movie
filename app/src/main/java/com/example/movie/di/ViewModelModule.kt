package com.example.movie.di

import com.example.movie.ui.detail.movie.MovieDetailViewModel
import com.example.movie.ui.detail.show.ShowDetailViewModel
import com.example.movie.ui.list.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { MovieDetailViewModel(get()) }

    viewModel { ShowDetailViewModel(get()) }
}