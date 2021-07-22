package com.example.movie.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.domain.entity.Movie
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.domain.entity.NowPlaying
import com.example.movie.domain.entity.Show
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.vo.LoadResult
import com.example.movie.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<List<NowPlaying>>

    suspend fun getMovies(sort: String): LiveData<Resource<PagedList<Movie>>>
    suspend fun getFavoriteMovies(): LiveData<PagedList<FavoriteMovieEntity>>
    suspend fun getMovieDetail(movieId: Int): Flow<LoadResult<MovieDetail>>
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    suspend fun getShows(sort: String): LiveData<Resource<PagedList<Show>>>
    suspend fun getFavoriteShows(): LiveData<PagedList<FavoriteShowEntity>>
    suspend fun getShowDetail(showId: Int): Flow<LoadResult<ShowDetail>>
    suspend fun insertFavoriteShow(favoriteShow: FavoriteShowEntity)
    suspend fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity)
}
