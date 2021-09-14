package com.example.movie.ui.list.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movie.domain.MovieRepository
import com.example.movie.utils.MainCoroutineRule
import com.example.movie.utils.database.SortUtil
import com.example.movie.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: MoviesViewModel

    private var repository = mock<MovieRepository>()
    private var observer = mock<Observer<Resource<PagedList<Movie>>>>()
    private var pagedList = mock<PagedList<Movie>>()

    private val sort = SortUtil.NEWEST

    @Before
    fun setUp() {
        vm = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyMovies = Resource.success(pagedList)
            Mockito.`when`(dummyMovies.data?.size).thenReturn(5)

            val movies = MutableLiveData<Resource<PagedList<Movie>>>()
            movies.value = dummyMovies
            Mockito.`when`(repository.getMovies(sort)).thenReturn(movies)

            val moviesResult = vm.getMovies(sort).value?.data
            Mockito.verify(repository).getMovies(sort)
            assertNotNull(moviesResult)
            assertEquals(5, moviesResult?.size)

            vm.getMovies(sort).observeForever(observer)
            Mockito.verify(observer).onChanged(dummyMovies)
        }
    }
}
