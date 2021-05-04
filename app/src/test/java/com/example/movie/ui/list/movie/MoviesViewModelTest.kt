package com.example.movie.ui.list.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {

    private lateinit var vm: MoviesViewModel

    @Before
    fun setUp() {
        vm = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movies = vm.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}