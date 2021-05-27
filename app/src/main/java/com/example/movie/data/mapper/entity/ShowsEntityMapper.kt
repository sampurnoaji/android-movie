package com.example.movie.data.mapper.entity

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.domain.entity.Show

class ShowsEntityMapper : Mapper<ShowEntity, Show>() {

    override fun invoke(dto: ShowEntity): Show {
        return Show(
            backdropPath = dto.backdropPath.orEmpty(),
            firstAirDate = dto.firstAirDate.orEmpty(),
            genreIds = emptyList(), //todo save list to db
            id = dto.id ?: 0,
            name = dto.name.orEmpty(),
            originCountry = emptyList(), //todo save list to db
            originalLanguage = dto.originalLanguage.orEmpty(),
            originalName = dto.originalName.orEmpty(),
            overview = dto.overview.orEmpty(),
            popularity = dto.popularity ?: 0f,
            posterPath = dto.posterPath.orEmpty(),
            voteAverage = dto.voteAverage ?: 0f,
            voteCount = dto.voteCount ?: 0
        )
    }
}