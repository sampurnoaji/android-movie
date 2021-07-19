package com.example.movie.di

import com.example.movie.ui.detail.movie.MovieDetailViewModel
import com.example.movie.ui.detail.show.ShowDetailViewModel
import com.example.movie.ui.favorite.movie.FavoriteMovieViewModel
import com.example.movie.ui.favorite.show.FavoriteShowViewModel
import com.example.movie.ui.list.MainViewModel
import com.example.movie.ui.list.movie.MoviesViewModel
import com.example.movie.ui.list.show.ShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { MoviesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }

    viewModel { ShowsViewModel(get()) }
    viewModel { ShowDetailViewModel(get()) }
    viewModel { FavoriteShowViewModel(get()) }
}