package com.example.movie.ui.detail.show

import com.example.movie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ShowDetailViewModelTest {

    private lateinit var vm: ShowDetailViewModel
    private val dummyShow = DataDummy.generateShows()[0]
    private val showId = dummyShow.id

    @Before
    fun setUp() {
        vm = ShowDetailViewModel()
        vm.setSelectedShow(showId)
    }

    @Test
    fun getShow() {
        vm.setSelectedShow(dummyShow.id)

        val show = vm.getShow()
        assertNotNull(show)
        assertEquals(dummyShow.posterUrl, show.posterUrl)
        assertEquals(dummyShow.title, show.title)
        assertEquals(dummyShow.language, show.language)
        assertEquals(dummyShow.releaseDate, show.releaseDate)
        assertEquals(dummyShow.voteAverage, show.voteAverage)
        assertEquals(dummyShow.popularity, show.popularity)
    }
}