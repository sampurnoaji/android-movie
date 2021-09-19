package io.android.core.domain.usecase

import io.android.core.domain.model.NowPlaying
import io.android.core.domain.repository.MovieRepository

class UpdateFavoriteMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: NowPlaying, newState: Boolean) {
        return repository.updateFavoriteMovie(movie, newState)
    }
}