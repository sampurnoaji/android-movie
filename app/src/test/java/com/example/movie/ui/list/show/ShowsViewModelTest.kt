package com.example.movie.ui.list.show

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
class ShowsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: ShowsViewModel

    private var repository = mock<MovieRepository>()
    private var observer = mock<Observer<Resource<PagedList<Show>>>>()
    private var pagedList = mock<PagedList<Show>>()

    private val sort = SortUtil.NEWEST

    @Before
    fun setUp() {
        vm = ShowsViewModel(repository)
    }

    @Test
    fun getShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyShows = Resource.success(pagedList)
            Mockito.`when`(dummyShows.data?.size).thenReturn(5)

            val shows = MutableLiveData<Resource<PagedList<Show>>>()
            shows.value = dummyShows
            Mockito.`when`(repository.getShows(sort)).thenReturn(shows)

            val showsResult = vm.getShows(sort).value?.data
            Mockito.verify(repository).getShows(sort)
            assertNotNull(showsResult)
            assertEquals(5, showsResult?.size)

            vm.getShows(sort).observeForever(observer)
            Mockito.verify(observer).onChanged(dummyShows)
        }
    }
}
