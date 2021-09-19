package com.example.movie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.data.source.local.entity.MovieEntity
import com.example.movie.data.source.local.entity.ShowEntity
import com.example.movie.utils.DummyData
import com.example.movie.utils.MainCoroutineRule
import com.example.movie.utils.PagedListUtil
import io.android.core.data.source.MovieRepositoryImpl
import io.android.core.data.source.local.MovieLocalDataSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: MovieRepositoryImpl

    private val remoteDataSource = mock<RemoteDataSource>()
    private val localDataSource = mock<MovieLocalDataSource>()
    private val appExecutors = mock<AppExecutors>()
    private val entityMapper = mock<EntityMapper>()
    private val responseMapper = mock<ResponseMapper>()

    private val sort = SortUtil.NEWEST

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(
            remoteDataSource,
            localDataSource,
            appExecutors,
            entityMapper,
            responseMapper
        )
    }

    @Test
    fun getMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dataSourceFactory = mock<DataSource.Factory<Int, MovieEntity>>()
            Mockito.`when`(localDataSource.getMovies(sort)).thenReturn(dataSourceFactory)
            localDataSource.getMovies(sort)

            val moviesEntity = Resource.success(PagedListUtil.mockPagedList(DummyData.moviesEntity))
            Mockito.verify(localDataSource).getMovies(sort)
            assertNotNull(moviesEntity)
            assertEquals(DummyData.moviesDto.results?.size, moviesEntity.data?.size)
        }
    }

    @Test
    fun getShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dataSourceFactory = mock<DataSource.Factory<Int, ShowEntity>>()
            Mockito.`when`(localDataSource.getShows(sort)).thenReturn(dataSourceFactory)
            localDataSource.getShows(sort)

            val showsEntity = Resource.success(PagedListUtil.mockPagedList(DummyData.showsEntity))
            Mockito.verify(localDataSource).getShows(sort)
            assertNotNull(showsEntity)
            assertEquals(DummyData.showsDto.results?.size, showsEntity.data?.size)
        }
    }

    @Test
    fun getMovieDetail() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummy = DummyData.movieDetailDto
            val id = DummyData.movieDetailDto.id ?: 0
            Mockito.`when`(remoteDataSource.getMovieDetail(id)).thenReturn(dummy)
            val response = remoteDataSource.getMovieDetail(id)
            Mockito.verify(remoteDataSource).getMovieDetail(id)
            assertNotNull(repository.getMovieDetail(id))
            assertEquals(response.id, dummy.id)
        }
    }

    @Test
    fun getShowDetail() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummy = DummyData.showDetailDto
            val id = DummyData.showDetailDto.id ?: 0
            Mockito.`when`(remoteDataSource.getShowDetail(id)).thenReturn(dummy)
            val response = remoteDataSource.getShowDetail(id)
            Mockito.verify(remoteDataSource).getShowDetail(id)
            assertNotNull(repository.getShowDetail(id))
            assertEquals(response.id, dummy.id)
        }
    }

    @Test
    fun getFavoriteMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dataSourceFactory = mock<DataSource.Factory<Int, FavoriteMovieEntity>>()
            Mockito.`when`(localDataSource.getFavoriteMovies()).thenReturn(dataSourceFactory)
            repository.getFavoriteMovies()

            val moviesEntity = PagedListUtil.mockPagedList(DummyData.favoriteMoviesEntity)
            Mockito.verify(localDataSource).getFavoriteMovies()
            assertNotNull(moviesEntity)
            assertEquals(DummyData.favoriteMoviesEntity.size, moviesEntity.size)
        }
    }

    @Test
    fun getFavoriteShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dataSourceFactory = mock<DataSource.Factory<Int, FavoriteShowEntity>>()
            Mockito.`when`(localDataSource.getFavoriteShows()).thenReturn(dataSourceFactory)
            repository.getFavoriteShows()

            val showsEntity = PagedListUtil.mockPagedList(DummyData.favoriteShowsEntity)
            Mockito.verify(localDataSource).getFavoriteShows()
            assertNotNull(showsEntity)
            assertEquals(DummyData.favoriteShowsEntity.size, showsEntity.size)
        }
    }
}
