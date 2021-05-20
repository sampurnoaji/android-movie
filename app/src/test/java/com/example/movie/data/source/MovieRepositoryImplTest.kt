package com.example.movie.data.source

import com.example.movie.data.mapper.response.MovieDetailResponseMapper
import com.example.movie.data.mapper.response.MoviesResponseMapper
import com.example.movie.data.mapper.response.ShowDetailResponseMapper
import com.example.movie.data.mapper.response.ShowsResponseMapper
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.utils.DummyData
import com.example.movie.utils.MainCoroutineRule
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
    var mainCoroutineRule = MainCoroutineRule()

    val remoteDataSource = mock<RemoteDataSource>()
    val moviesMapper = mock<MoviesResponseMapper>()
    val movieDetailMapper = mock<MovieDetailResponseMapper>()
    val showsMapper = mock<ShowsResponseMapper>()
    val showDetailMapper = mock<ShowDetailResponseMapper>()

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remoteDataSource, moviesMapper, movieDetailMapper, showsMapper, showDetailMapper)
    }

    @Test
    fun getMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummy = DummyData.moviesDto
            Mockito.`when`(remoteDataSource.getMovies()).thenReturn(dummy)
            val response = remoteDataSource.getMovies()
            Mockito.verify(remoteDataSource).getMovies()
            assertNotNull(repository.getMovies())
            assertEquals(response.results?.size, dummy.results?.size)
        }
    }

    @Test
    fun getShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummy = DummyData.showsDto
            Mockito.`when`(remoteDataSource.getShows()).thenReturn(dummy)
            val response = remoteDataSource.getShows()
            Mockito.verify(remoteDataSource).getShows()
            assertNotNull(repository.getShows())
            assertEquals(response.results?.size, dummy.results?.size)
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
}
