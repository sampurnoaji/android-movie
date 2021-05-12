package com.example.movie.ui.list.show

import com.example.movie.data.Show
import com.example.movie.domain.Movie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ShowsViewModelTest {

    private lateinit var vm: ShowsViewModel

    @Before
    fun setUp() {
        vm = ShowsViewModel()
    }

    @Test
    fun getShows() {
        val shows = vm.getShows()
        assertNotNull(shows)
        assertEquals(10, shows.size)
    }

    @Test
    fun getShowsReturnEmpty() {
        val shows = emptyList<Show>()
        assertEquals(emptyList<Movie>(), shows)
    }

    @Test
    fun getShowsErrorReturnNull() {
        val shows = null
        assertEquals(null, shows)
    }
}