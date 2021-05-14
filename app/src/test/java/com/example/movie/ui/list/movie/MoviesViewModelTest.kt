package com.example.movie.ui.list.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movie.domain.MovieRepository
import com.example.movie.utils.DummyData
import com.example.movie.utils.MainCoroutineRule
import com.example.movie.utils.observerTest
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private var repository: MovieRepository = mock()

    private lateinit var vm: MoviesViewModel

    @Before
    fun setUp() {
        vm = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyMovies = flow {
                emit(LoadResult.Loading)
                emit(LoadResult.Success(DummyData.movies))
            }
            Mockito.`when`(repository.getMovies()).thenReturn(dummyMovies)
            vm.getMovies()
            Mockito.verify(repository).getMovies()
            vm.movieResult.observerTest {
                when (it) {
                    is LoadResult.Success -> {
                        assertNotNull(it.data)
                        assertEquals(1, it.data.size)
                    }
                }
            }
        }
    }
}
