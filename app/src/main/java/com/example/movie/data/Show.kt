package com.example.movie.data

data class Show(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterUrl: String,
    val language: String,
    val voteAverage: Float,
    val popularity: Float
)