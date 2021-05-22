package com.example.movie.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.domain.entity.Movie
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.domain.entity.Show
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.vo.LoadResult
import com.example.movie.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): LiveData<Resource<PagedList<Movie>>>
    suspend fun getMovieById(id: Int): LiveData<Movie>
    suspend fun getMovieDetail(movieId: Int): Flow<LoadResult<MovieDetail>>
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)
    suspend fun getFavoriteMovies(): LiveData<PagedList<FavoriteMovieEntity>>
    suspend fun getShows(): Flow<LoadResult<List<Show>>>
    suspend fun getShowDetail(showId: Int): Flow<LoadResult<ShowDetail>>
}
