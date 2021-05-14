package com.example.movie.data.source

import com.example.movie.data.mapper.MovieDetailMapper
import com.example.movie.data.mapper.MoviesMapper
import com.example.movie.data.mapper.ShowDetailMapper
import com.example.movie.data.mapper.ShowsMapper
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.utils.DummyData
import com.example.movie.utils.MainCoroutineRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    val moviesMapper = mock<MoviesMapper>()
    val movieDetailMapper = mock<MovieDetailMapper>()
    val showsMapper = mock<ShowsMapper>()
    val showDetailMapper = mock<ShowDetailMapper>()

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remoteDataSource, moviesMapper, movieDetailMapper, showsMapper, showDetailMapper)
    }

    @Test
    fun getMovies() = runBlocking {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummy = DummyData.moviesDto
            Mockito.`when`(remoteDataSource.getMovies()).thenReturn(dummy)
            repository.getMovies()
            Mockito.verify(remoteDataSource).getMovies()
            assertNotNull(repository.getMovies())
            assertEquals(1, dummy.results?.size)
        }
    }
}