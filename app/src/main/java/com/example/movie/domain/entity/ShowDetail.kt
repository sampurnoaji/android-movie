package com.example.movie.domain.entity

data class ShowDetail(
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Int
)
