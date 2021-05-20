package com.example.movie.data.mapper.response

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.remote.response.MoviesResponse
import com.example.movie.domain.entity.Movie

class MoviesResponseMapper : Mapper<MoviesResponse, List<Movie>>() {
    override fun invoke(dto: MoviesResponse): List<Movie> {
        return dto.results?.map { result ->
            Movie(
                adult = result?.adult ?: false,
                backdropPath = result?.backdropPath.orEmpty(),
                genreIds = result?.genreIds?.map { it ?: 0 } ?: emptyList(),
                id = result?.id ?: 0,
                originalLanguage = result?.originalLanguage.orEmpty(),
                originalTitle = result?.originalTitle.orEmpty(),
                overview = result?.overview.orEmpty(),
                popularity = result?.popularity ?: 0f,
                posterPath = result?.posterPath.orEmpty(),
                releaseDate = result?.releaseDate.orEmpty(),
                title = result?.title.orEmpty(),
                video = result?.video ?: false,
                voteAverage = result?.voteAverage ?: 0f,
                voteCount = result?.voteCount ?: 0
            )
        } ?: emptyList()
    }
}
