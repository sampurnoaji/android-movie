package com.example.movie.ui.favorite.show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.utils.MainCoroutineRule
import io.android.core.domain.repository.MovieRepository
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
class FavoriteShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: FavoriteShowViewModel

    private var repository = mock<MovieRepository>()
    private var observer = mock<Observer<PagedList<FavoriteShowEntity>>>()
    private var pagedList = mock<PagedList<FavoriteShowEntity>>()

    @Before
    fun setUp() {
        vm = FavoriteShowViewModel(repository)
    }

    @Test
    fun getFavoriteShows() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyShows = pagedList
            Mockito.`when`(dummyShows.size).thenReturn(5)

            val shows = MutableLiveData<PagedList<FavoriteShowEntity>>()
            shows.value = dummyShows
            Mockito.`when`(repository.getFavoriteShows()).thenReturn(shows)

            val showsResult = vm.getFavoriteShows().value
            Mockito.verify(repository).getFavoriteShows()
            assertNotNull(showsResult)
            assertEquals(5, showsResult?.size)

            vm.getFavoriteShows().observeForever(observer)
            Mockito.verify(observer).onChanged(dummyShows)
        }
    }

    @Test
    fun deleteFavoriteShow() {
    }
}