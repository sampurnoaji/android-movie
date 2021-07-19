package com.example.movie.data.mapper

import com.example.movie.data.dto.NowPlayingResponse
import com.example.movie.domain.entity.NowPlaying

class NowPlayingMapper {
    fun toDomain(dto: NowPlayingResponse): List<NowPlaying> {
        return dto.results?.map { result ->
            NowPlaying(
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