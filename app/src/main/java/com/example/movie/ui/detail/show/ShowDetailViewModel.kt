package com.example.movie.ui.detail.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.source.local.entity.FavoriteShowEntity
import com.example.movie.domain.MovieRepository
import com.example.movie.domain.entity.ShowDetail
import com.example.movie.vo.LoadResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private var showId = 0

    private val _showDetailResult = MutableLiveData<LoadResult<ShowDetail>>()
    val showDetailResult: LiveData<LoadResult<ShowDetail>>
        get() = _showDetailResult
    
    var showDetail: ShowDetail? = null

    fun setSelectedShow(showId: Int) {
        this.showId = showId
    }

    fun getShowDetail() {
        viewModelScope.launch {
            repository.getShowDetail(showId).collect {
                _showDetailResult.value = it
            }
        }
    }

    fun insertFavoriteShow() {
        viewModelScope.launch {
            val favoriteShow = FavoriteShowEntity(
                id = showDetail?.id ?: 0,
                name = showDetail?.name,
                firstAirDate = showDetail?.firstAirDate,
                posterPath = showDetail?.posterPath,
                overview = showDetail?.overview
            )
            repository.insertFavoriteShow(favoriteShow)
        }
    }
}