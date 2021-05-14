package com.example.movie.data.source

import com.example.movie.BuildConfig
import com.example.movie.data.mapper.MovieDetailMapper
import com.example.movie.data.mapper.MoviesMapper
import com.example.movie.data.mapper.ShowDetailMapper
import com.example.movie.data.mapper.ShowsMapper
import com.example.movie.data.service.ApiService
import com.example.movie.data.source.remote.RemoteDataSource
import com.example.movie.domain.MovieRepository
import com.example.movie.utils.DummyData
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    val apiService = mockk<ApiService>()
    val remoteDataSource = mockk<RemoteDataSource>()
    val moviesMapper = mockk<MoviesMapper>()
    val movieDetailMapper = mockk<MovieDetailMapper>()
    val showsMapper = mockk<ShowsMapper>()
    val showDetailMapper = mockk<ShowDetailMapper>()

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remoteDataSource, moviesMapper, movieDetailMapper, showsMapper, showDetailMapper)
    }

    @Test
    fun getMovies() = runBlocking {
        apiService.stub {
            onBlocking { getMovies(BuildConfig.API_KEY) } doReturn DummyData.moviesDto
        }

        val flow = repository.getMovies()

        flow.collect {
            verify(repository.getMovies())
            assertNotNull(repository.getMovies())
            assertEquals(1, DummyData.moviesDto.results?.size)
        }
    }
}