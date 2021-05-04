package com.example.movie.ui.detail.movie

import com.example.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var vm: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        vm = MovieDetailViewModel()
        vm.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        vm.setSelectedMovie(dummyMovie.id)

        val movie = vm.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.posterUrl, movie.posterUrl)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.language, movie.language)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.voteAverage, movie.voteAverage)
        assertEquals(dummyMovie.popularity, movie.popularity)
    }
}