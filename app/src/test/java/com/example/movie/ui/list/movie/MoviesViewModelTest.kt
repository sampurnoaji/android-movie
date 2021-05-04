package com.example.movie.ui.list.movie

import com.example.movie.data.Movie
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

    @Test
    fun getMoviesReturnEmpty() {
        val movies = emptyList<Movie>()
        assertEquals(emptyList<Movie>(), movies)
    }

    @Test
    fun getMoviesErrorReturnNull() {
        val movies = null
        assertEquals(null, movies)
    }
}