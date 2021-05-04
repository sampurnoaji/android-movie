package com.example.movie.ui.list.show

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
}