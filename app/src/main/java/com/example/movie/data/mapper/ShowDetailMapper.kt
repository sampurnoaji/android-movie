package com.example.movie.data.mapper

import com.example.movie.data.source.remote.response.ShowDetailDto
import com.example.movie.domain.ShowDetail

class ShowDetailMapper {
    operator fun invoke(showDetailDto: ShowDetailDto): ShowDetail {
        return ShowDetail(
            id = showDetailDto.id ?: 0,
            originalLanguage = showDetailDto.originalLanguage.orEmpty(),
            overview = showDetailDto.overview.orEmpty(),
            popularity = showDetailDto.popularity ?: 0f,
            posterPath = showDetailDto.posterPath.orEmpty(),
            firstAirDate = showDetailDto.firstAirDate.orEmpty(),
            name = showDetailDto.name.orEmpty(),
            voteAverage = showDetailDto.voteAverage ?: 0f,
            voteCount = showDetailDto.voteCount ?: 0
        )
    }
}
