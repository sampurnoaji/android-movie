package com.example.movie.data.mapper

import com.example.movie.data.mapper.entity.MoviesEntityMapper
import com.example.movie.data.mapper.entity.ShowsEntityMapper

data class EntityMapper(
    val moviesEntityMapper: MoviesEntityMapper,
    val showsEntityMapper: ShowsEntityMapper
)
