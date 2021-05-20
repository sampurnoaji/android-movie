package com.example.movie.data.mapper.response

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.remote.response.ShowsResponse
import com.example.movie.domain.entity.Show

class ShowsResponseMapper : Mapper<ShowsResponse, List<Show>>() {
    override fun invoke(dto: ShowsResponse): List<Show> {
        return dto.results?.map {
            Show(
                backdropPath = it?.backdropPath.orEmpty(),
                firstAirDate = it?.firstAirDate.orEmpty(),
                genreIds = it?.genreIds.mapGenreIds(),
                id = it?.id ?: 0,
                name = it?.name.orEmpty(),
                originCountry = it?.originCountry.mapOriginCountry(),
                originalLanguage = it?.originalLanguage.orEmpty(),
                originalName = it?.originalName.orEmpty(),
                overview = it?.overview.orEmpty(),
                popularity = it?.popularity ?: 0f,
                posterPath = it?.posterPath.orEmpty(),
                voteAverage = it?.voteAverage ?: 0f,
                voteCount = it?.voteCount ?: 0
            )
        } ?: emptyList()
    }

    private fun List<Int?>?.mapGenreIds(): List<Int> {
        return this?.map {
            it ?: 0
        } ?: emptyList()
    }

    private fun List<String?>?.mapOriginCountry(): List<String> {
        return this?.map {
            it.orEmpty()
        } ?: emptyList()
    }
}
