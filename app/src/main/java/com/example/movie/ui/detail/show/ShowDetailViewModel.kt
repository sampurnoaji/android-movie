package com.example.movie.ui.detail.show

import androidx.lifecycle.ViewModel
import com.example.movie.domain.Show
import com.example.movie.utils.DataDummy

class ShowDetailViewModel : ViewModel() {

    private var showId = 0

    fun setSelectedShow(showId: Int) {
        this.showId = showId
    }

    fun getShow(): Show {
        lateinit var show: Show
        val shows = DataDummy.generateShows()
        for (it in shows) {
            if (it.id == showId) {
                show = it
            }
        }
        return show
    }
}