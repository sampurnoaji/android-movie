package com.example.movie.ui.list.show

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
class ShowsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private var repository: MovieRepository = mock()

    private lateinit var vm: ShowsViewModel

    @Before
    fun setUp() {
        vm = ShowsViewModel(repository)
    }

    @Test
    fun getShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyShows = flow {
                emit(LoadResult.Loading)
                emit(LoadResult.Success(DummyData.shows))
            }
            Mockito.`when`(repository.getShows()).thenReturn(dummyShows)
            vm.getShows()
            Mockito.verify(repository).getShows()
            vm.showsResult.observerTest {
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
