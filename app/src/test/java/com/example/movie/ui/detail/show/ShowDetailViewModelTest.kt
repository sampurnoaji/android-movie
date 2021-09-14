package com.example.movie.ui.detail.show

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
class ShowDetailViewModelTest {

    private val dummyShow = DummyData.showDetail
    private val showId = dummyShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private var repository: MovieRepository = mock()

    private lateinit var vm: ShowDetailViewModel

    @Before
    fun setUp() {
        vm = ShowDetailViewModel(repository)
        vm.setSelectedShow(showId)
    }

    @Test
    fun getShow() {
        vm.setSelectedShow(dummyShow.id)
        mainCoroutineRule.testDispatcher.runBlockingTest {
            val dummyShow = flow {
                emit(LoadResult.Loading)
                emit(LoadResult.Success(dummyShow))
            }
            Mockito.`when`(repository.getShowDetail(showId)).thenReturn(dummyShow)
            vm.getShowDetail()
            Mockito.verify(repository).getShowDetail(showId)
            vm.showDetailResult.observerTest {
                when (it) {
                    is LoadResult.Success -> {
                        val show = it.data
                        assertNotNull(show)
                        assertEquals(this@ShowDetailViewModelTest.dummyShow, show)
                    }
                }
            }
        }
    }
}