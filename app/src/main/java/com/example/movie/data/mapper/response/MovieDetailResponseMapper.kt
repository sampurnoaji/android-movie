package com.example.movie.data.mapper.response

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.remote.response.MovieDetailResponse
import com.example.movie.domain.entity.MovieDetail

class MovieDetailResponseMapper : Mapper<MovieDetailResponse, MovieDetail>() {
    override fun invoke(dto: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            id = dto.id ?: 0,
            originalLanguage = dto.originalLanguage.orEmpty(),
            overview = dto.overview.orEmpty(),
            popularity = dto.popularity ?: 0f,
            posterPath = dto.posterPath.orEmpty(),
            releaseDate = dto.releaseDate.orEmpty(),
            title = dto.title.orEmpty(),
            voteAverage = dto.voteAverage ?: 0f,
            voteCount = dto.voteCount ?: 0
        )
    }
}
