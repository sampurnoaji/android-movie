package com.example.movie.domain.entity

data class MovieDetail(
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)
