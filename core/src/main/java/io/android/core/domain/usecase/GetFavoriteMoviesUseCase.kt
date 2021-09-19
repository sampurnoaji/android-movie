package io.android.core.domain.usecase

import io.android.core.domain.model.NowPlaying
import io.android.core.domain.repository.MovieRepository
import io.android.core.vo.Either
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): Flow<Either<Exception, List<NowPlaying>>> {
        return repository.getFavoriteMovies()
    }
}