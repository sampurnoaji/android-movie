package com.example.movie.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movie.data.mapper.EntityMapper
import com.example.movie.data.mapper.ResponseMapper
import com.example.movie.data.source.local.LocalDataSource
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.data.source.remote.response.MoviesResponse
import com.example.movie.data.source.remote.response.ShowsResponse
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.Movie
import com.example.movie.domain.entity.MovieDetail
import com.example.movie.domain.entity.Show
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.utils.AppExecutors
import com.example.movie.vo.ApiResponse
import com.example.movie.vo.LoadResult
import com.example.movie.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val entityMapper: EntityMapper,
    private val responseMapper: ResponseMapper
) : MovieRepository {

    override suspend fun getMovies(): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, MoviesResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                val pagedMovies = localDataSource.getMovies().mapByPage {
                    it.map { movie ->
                        entityMapper.moviesEntityMapper(movie)
                    }
                }
                return LivePagedListBuilder(pagedMovies, config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                val context = Dispatchers.IO + SupervisorJob()
                val result = MutableLiveData<ApiResponse<MoviesResponse>>()

                CoroutineScope(context).launch {
                    result.postValue(ApiResponse.success(remoteDataSource.getMovies()))
                }
                return result
            }

            override fun saveCallResult(data: MoviesResponse) {
                val moviesEntity = responseMapper.moviesResponseMapper.toEntity(data)
                localDataSource.insertMovies(moviesEntity)
            }
        }.asLiveData()
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<LoadResult<MovieDetail>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getMovieDetail(movieId)
                emit(LoadResult.Success(responseMapper.movieDetailResponseMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }

    override suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        localDataSource.insertFavoriteMovie(favoriteMovie)
    }

    override suspend fun getFavoriteMovies(): LiveData<PagedList<FavoriteMovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity) {
        localDataSource.deleteFavoriteMovie(favoriteMovie)
    }

    override suspend fun getShows(): LiveData<Resource<PagedList<Show>>> {
        return object : NetworkBoundResource<PagedList<Show>, ShowsResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<Show>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                val pagedShows = localDataSource.getShows().mapByPage {
                    it.map { show ->
                        entityMapper.showsEntityMapper(show)
                    }
                }
                return LivePagedListBuilder(pagedShows, config).build()
            }

            override fun shouldFetch(data: PagedList<Show>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ShowsResponse>> {
                val context = Dispatchers.IO + SupervisorJob()
                val result = MutableLiveData<ApiResponse<ShowsResponse>>()

                CoroutineScope(context).launch {
                    result.postValue(ApiResponse.success(remoteDataSource.getShows()))
                }
                return result
            }

            override fun saveCallResult(data: ShowsResponse) {
                val showsEntity = responseMapper.showsResponseMapper.toEntity(data)
                localDataSource.insertShows(showsEntity)
            }
        }.asLiveData()
    }

    override suspend fun getShowDetail(showId: Int): Flow<LoadResult<ShowDetail>> {
        return flow {
            emit(LoadResult.Loading)
            try {
                val result = remoteDataSource.getShowDetail(showId)
                emit(LoadResult.Success(responseMapper.showDetailResponseMapper(result)))
            } catch (e: Exception) {
                emit(LoadResult.Error)
            }
        }
    }

    override suspend fun getFavoriteShows(): LiveData<PagedList<FavoriteShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteShows(), config).build()
    }

    override suspend fun insertFavoriteShow(favoriteShow: FavoriteShowEntity) {
        localDataSource.insertFavoriteShow(favoriteShow)
    }

    override suspend fun deleteFavoriteShow(favoriteShow: FavoriteShowEntity) {
        localDataSource.deleteFavoriteShow(favoriteShow)
    }
}
