package com.example.movie.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.movie.data.source.local.entity.FavoriteMovieEntity
import com.example.movie.domain.MovieRepository
import com.example.movie.utils.MainCoroutineRule
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
class FavoriteMovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var vm: FavoriteMovieViewModel

    private var repository = mock<MovieRepository>()
    private var observer = mock<Observer<PagedList<FavoriteMovieEntity>>>()
    private var pagedList = mock<PagedList<FavoriteMovieEntity>>()
    
    @Before
    fun setUp() {
        vm = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyMovies = pagedList
            Mockito.`when`(dummyMovies.size).thenReturn(5)

            val movies = MutableLiveData<PagedList<FavoriteMovieEntity>>()
            movies.value = dummyMovies
            Mockito.`when`(repository.getFavoriteMovies()).thenReturn(movies)

            val moviesResult = vm.getFavoriteMovies().value
            Mockito.verify(repository).getFavoriteMovies()
            assertNotNull(moviesResult)
            assertEquals(5, moviesResult?.size)

            vm.getFavoriteMovies().observeForever(observer)
            Mockito.verify(observer).onChanged(dummyMovies)
        }
    }

    @Test
    fun deleteFavoriteMovie() {
    }
}