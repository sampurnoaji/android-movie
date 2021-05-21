package com.example.movie.data.mapper.response

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.remote.response.ShowDetailResponse
import com.example.movie.domain.entity.ShowDetail

class ShowDetailResponseMapper : Mapper<ShowDetailResponse, ShowDetail>() {
    override operator fun invoke(dto: ShowDetailResponse): ShowDetail {
        return ShowDetail(
            id = dto.id ?: 0,
            originalLanguage = dto.originalLanguage.orEmpty(),
            overview = dto.overview.orEmpty(),
            popularity = dto.popularity ?: 0f,
            posterPath = dto.posterPath.orEmpty(),
            firstAirDate = dto.firstAirDate.orEmpty(),
            name = dto.name.orEmpty(),
            voteAverage = dto.voteAverage ?: 0f,
            voteCount = dto.voteCount ?: 0
        )
    }
}
