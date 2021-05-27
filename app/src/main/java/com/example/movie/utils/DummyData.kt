package com.example.movie.utils

import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.data.source.remote.response.MovieDetailResponse
import com.example.movie.data.source.remote.response.MoviesResponse
import com.example.movie.data.source.remote.response.ShowDetailResponse
import com.example.movie.data.source.remote.response.ShowsResponse
import com.example.movie.domain.entity.Movie
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.domain.entity.Show
import com.example.movie.domain.entity.ShowDetail

object DummyData {
    val moviesDto = MoviesResponse(
        results = listOf(
            MoviesResponse.Result(
                id = 1
            )
        )
    )

    val movieDetailDto = MovieDetailResponse(id = 567189)

    val movies = listOf(
        Movie(
            adult = false,
            backdropPath = "",
            genreIds = emptyList(),
            id = 567189,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0f,
            posterPath = "",
            releaseDate = "",
            title = "",
            video = false,
            voteAverage = 0f,
            voteCount = 0
        )
    )

    val movieDetail = MovieDetail(
        id = 567189,
        originalLanguage = "",
        overview = "",
        popularity = 0f,
        posterPath = "",
        releaseDate = "",
        title = "",
        voteAverage = 0f,
        voteCount = 0
    )

    val moviesEntity = listOf(
        MovieEntity(
            adult = false,
            backdropPath = "",
            id = 0,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0f,
            posterPath = "",
            releaseDate = "",
            title = "",
            video = false,
            voteAverage = 0f,
            voteCount = 0
        )
    )

    val favoriteMoviesEntity = listOf(
        FavoriteMovieEntity(
            id = 0,
            title = "",
            releaseDate = "",
            posterPath = "",
            overview = ""
        )
    )

    val showsDto = ShowsResponse(
        results = listOf(
            ShowsResponse.Result(
                id = 1
            )
        )
    )

    val showDetailDto = ShowDetailResponse(id = 88396)

    val shows = listOf(
        Show(
            backdropPath = "",
            firstAirDate = "",
            genreIds = emptyList(),
            id = 88396,
            name = "",
            originCountry = emptyList(),
            originalLanguage = "",
            originalName = "",
            overview = "",
            popularity = 0f,
            posterPath = "",
            voteAverage = 0f,
            voteCount = 0
        )
    )

    val showDetail = ShowDetail(
        id = 88396,
        originalLanguage = "",
        overview = "",
        popularity = 0f,
        posterPath = "",
        firstAirDate = "",
        name = "",
        voteAverage = 0f,
        voteCount = 0
    )

    val showsEntity = listOf(
        ShowEntity(
            backdropPath = "",
            firstAirDate = "",
            id = 0,
            name = "",
            originalLanguage = "",
            originalName = "",
            overview = "",
            popularity = 0f,
            posterPath = "",
            voteAverage = 0f,
            voteCount = 0
        )
    )

    val favoriteShowsEntity = listOf(
        FavoriteShowEntity(
            id = 0,
            name = "",
            firstAirDate = "",
            posterPath = "",
            overview = ""
        )
    )
}
