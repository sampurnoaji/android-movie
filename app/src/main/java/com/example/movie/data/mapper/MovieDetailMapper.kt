package com.example.movie.data.mapper

import com.example.movie.data.source.remote.response.MovieDetailDto
import com.example.movie.domain.MovieDetail

class MovieDetailMapper {
    operator fun invoke(movieDetailDto: MovieDetailDto): MovieDetail {
        return MovieDetail(
            id = movieDetailDto.id ?: 0,
            originalLanguage = movieDetailDto.originalLanguage.orEmpty(),
            overview = movieDetailDto.overview.orEmpty(),
            popularity = movieDetailDto.popularity ?: 0f,
            posterPath = movieDetailDto.posterPath.orEmpty(),
            releaseDate = movieDetailDto.releaseDate.orEmpty(),
            title = movieDetailDto.title.orEmpty(),
            voteAverage = movieDetailDto.voteAverage ?: 0f,
            voteCount = movieDetailDto.voteCount ?: 0
        )
    }
}
