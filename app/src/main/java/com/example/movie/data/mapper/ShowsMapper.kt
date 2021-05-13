package com.example.movie.data.mapper

import com.example.movie.data.Show
import com.example.movie.data.source.remote.response.ShowsDto

class ShowsMapper {
    operator fun invoke(showsDto: ShowsDto): List<Show> {
        return showsDto.results?.map {
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
