package com.example.movie.data.mapper.entity

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.domain.entity.Movie

class MoviesEntityMapper : Mapper<List<MovieEntity>, List<Movie>>() {
    override operator fun invoke(dto: List<MovieEntity>): List<Movie> {
        return dto.map {
            Movie(
                adult = it.adult ?: false,
                backdropPath = it.backdropPath.orEmpty(),
                genreIds = emptyList(), //todo research to handle array data
                id = it.id ?: 0,
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                overview = it.overview.orEmpty(),
                popularity = it.popularity ?: 0f,
                posterPath = it.posterPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                title = it.title.orEmpty(),
                video = it.video ?: false,
                voteAverage = it.voteAverage ?: 0f,
                voteCount = it.voteCount ?: 0
            )
        }
    }
}