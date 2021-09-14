package com.example.movie.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.entity.NowPlaying
import com.example.movie.domain.usecase.GetNowPlayingUseCase
import com.example.movie.utils.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val getNowPlayingUseCase: GetNowPlayingUseCase) : ViewModel() {

    private val _nowPlaying = MutableLiveData<ViewState<List<NowPlaying>>>()
    val nowPlaying = liveData { emitSource(_nowPlaying) }

    fun getNowPlaying() {
        _nowPlaying.value = ViewState.Loading
        viewModelScope.launch {
            getNowPlayingUseCase()
                .collect {
                _nowPlaying.value = ViewState.Success(it)
            }
        }
    }
}