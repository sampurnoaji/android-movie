package com.example.movie.data.mapper.entity

import com.example.movie.abstraction.Mapper
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.domain.entity.Movie

class MoviesEntityMapper : Mapper<MovieEntity, Movie>() {
    override fun invoke(dto: MovieEntity): Movie {
        return Movie(
            adult = dto.adult ?: false,
            backdropPath = dto.backdropPath.orEmpty(),
            genreIds = emptyList(), //todo save list to db
            id = dto.id ?: 0,
            originalLanguage = dto.originalLanguage.orEmpty(),
            originalTitle = dto.originalTitle.orEmpty(),
            overview = dto.overview.orEmpty(),
            popularity = dto.popularity ?: 0f,
            posterPath = dto.posterPath.orEmpty(),
            releaseDate = dto.releaseDate.orEmpty(),
            title = dto.title.orEmpty(),
            video = dto.video ?: false,
            voteAverage = dto.voteAverage ?: 0f,
            voteCount = dto.voteCount ?: 0
        )
    }

    fun toEntity(domain: Movie): MovieEntity {
        return MovieEntity(
            adult = domain.adult,
            backdropPath = domain.backdropPath,
            id = domain.id,
            originalLanguage = domain.originalLanguage,
            originalTitle = domain.originalTitle,
            overview = domain.overview,
            popularity = domain.popularity,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            title = domain.title,
            video = domain.video,
            voteAverage = domain.voteAverage,
            voteCount = domain.voteCount
        )
    }
}