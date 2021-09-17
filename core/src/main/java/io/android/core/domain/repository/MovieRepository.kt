package io.android.core.domain.repository

import io.android.core.domain.model.NowPlaying
import io.android.core.vo.Either
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<List<NowPlaying>>
    suspend fun getFavoriteMovies(): Either<Exception, Flow<List<NowPlaying>>>
    suspend fun updateFavoriteMovie(movie: NowPlaying, newState: Boolean)
}
