package com.example.movie.data.mapper

import com.example.movie.data.source.remote.response.MoviesDto
import com.example.movie.domain.Movie

class MoviesMapper {
    operator fun invoke(moviesDto: MoviesDto): List<Movie> {
        return moviesDto.results?.map {
            Movie(
                adult = it?.adult ?: false,
                backdropPath = it?.backdropPath.orEmpty(),
                genreIds = it?.genreIds.orEmpty(),
                id = it?.id ?: 0,
                originalLanguage = it?.originalLanguage.orEmpty(),
                originalTitle = it?.originalTitle.orEmpty(),
                overview = it?.overview.orEmpty(),
                popularity = it?.popularity ?: 0f,
                posterPath = it?.posterPath.orEmpty(),
                releaseDate = it?.releaseDate.orEmpty(),
                title = it?.title.orEmpty(),
                video = it?.video ?: false,
                voteAverage = it?.voteAverage ?: 0f,
                voteCount = it?.voteCount ?: 0
            )
        } ?: emptyList()
    }
}
