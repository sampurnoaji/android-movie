package com.example.movie.data.mapper

import com.example.movie.data.dto.entity.NowPlayingEntity
import com.example.movie.data.dto.response.NowPlayingResponse
import com.example.movie.domain.entity.NowPlaying

class NowPlayingMapper {
    fun toEntity(dto: NowPlayingResponse): List<NowPlayingEntity> {
        return dto.results?.map { result ->
            NowPlayingEntity(
                adult = result?.adult ?: false,
                backdropPath = result?.backdropPath.orEmpty(),
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

    fun toDomain(entities: List<NowPlayingEntity>): List<NowPlaying> {
        return entities.map { result ->
            NowPlaying(
                adult = result.adult,
                backdropPath = result.backdropPath,
                genreIds = emptyList(),
                id = result.id,
                originalLanguage = result.originalLanguage,
                originalTitle = result.originalTitle,
                overview = result.overview,
                popularity = result.popularity,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                video = result.video,
                voteAverage = result.voteAverage,
                voteCount = result.voteCount
            )
        }
    }
}