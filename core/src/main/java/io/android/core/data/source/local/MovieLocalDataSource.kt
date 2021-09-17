package io.android.core.data.source.local

import io.android.core.data.dto.NowPlayingEntity
import io.android.core.data.source.DatabaseHandler
import io.android.core.vo.DataResponse
import io.android.core.vo.Either

class MovieLocalDataSource(private val movieDao: MovieDao): DatabaseHandler() {

    suspend fun insertNowPlaying(nowPlaying: List<NowPlayingEntity>) {
        movieDao.insertNowPlaying(nowPlaying)
    }

    suspend fun getNowPlaying(): List<NowPlayingEntity> {
        return movieDao.getNowPlaying()
    }

    suspend fun getFavoriteMovies(): Either<Exception, List<NowPlayingEntity>> {
        return when (val response = load { movieDao.getFavoriteMovies() }) {
            is DataResponse.Success -> Either.Success(response.data)
            is DataResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun updateFavoriteMovie(movie: NowPlayingEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}