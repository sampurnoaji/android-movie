package com.example.movie.ui.list.show

import androidx.lifecycle.ViewModel
import com.example.movie.data.Show
import com.example.movie.utils.DataDummy

class ShowsViewModel : ViewModel() {

    fun getShows(): List<Show> {
        val shows = DataDummy.generateShows().toMutableList()
        shows.reverse()
        return shows
    }
}