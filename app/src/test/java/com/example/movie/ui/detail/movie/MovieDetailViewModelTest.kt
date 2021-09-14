package com.example.movie.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movie.utils.DummyData
import com.example.movie.utils.MainCoroutineRule
import com.example.movie.vo.LoadResult
import io.android.core.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    private val dummyMovie = DummyData.movieDetail
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private var repository: MovieRepository = mock()

    private lateinit var vm: MovieDetailViewModel

    @Before
    fun setUp() {
        vm = MovieDetailViewModel(repository)
        vm.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        vm.setSelectedMovie(dummyMovie.id)
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyMovie = flow {
                emit(LoadResult.Loading)
                emit(LoadResult.Success(dummyMovie))
            }
            Mockito.`when`(repository.getMovieDetail(movieId)).thenReturn(dummyMovie)
            vm.getMovieDetail()
            Mockito.verify(repository).getMovieDetail(movieId)
            vm.movieDetailResult.observerTest {
                when (it) {
                    is LoadResult.Success -> {
                        val movie = it.data
                        assertNotNull(movie)
                        assertEquals(this@MovieDetailViewModelTest.dummyMovie, movie)
                    }
                }
            }
        }
    }
}
