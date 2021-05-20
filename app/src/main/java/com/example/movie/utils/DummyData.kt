package com.example.movie.utils

import com.example.movie.data.source.remote.response.MovieDetailDto
import com.example.movie.data.source.remote.response.MoviesDto
import com.example.movie.data.source.remote.response.ShowDetailDto
import com.example.movie.data.source.remote.response.ShowsDto
import com.example.movie.domain.Movie
import com.example.movie.domain.MovieDetail
import com.example.movie.domain.Show
import com.example.movie.domain.ShowDetail

object DummyData {
    val moviesDto = MoviesDto(
        results = listOf(
            MoviesDto.Result(
                id = 1
            )
        )
    )

    val movieDetailDto = MovieDetailDto(id = 567189)

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

    val showsDto = ShowsDto(
        results = listOf(
            ShowsDto.Result(
                id = 1
            )
        )
    )

    val showDetailDto = ShowDetailDto(id = 88396)

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
}
