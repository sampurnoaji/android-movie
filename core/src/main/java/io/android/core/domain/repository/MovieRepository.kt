package io.android.core.domain.repository

import io.android.core.domain.model.NowPlaying
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<List<NowPlaying>>
}
