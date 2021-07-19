package com.example.movie.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.entity.NowPlaying
import com.example.movie.domain.usecase.GetNowPlayingUseCase
import com.example.movie.utils.onError
import com.example.movie.utils.onSuccess
import io.android.momobill.vo.ViewState
import kotlinx.coroutines.launch

class MainViewModel(private val getNowPlayingUseCase: GetNowPlayingUseCase) : ViewModel() {
    
    private val _nowPlaying = MutableLiveData<ViewState<List<NowPlaying>>>()
    val nowPlaying = liveData { emitSource(_nowPlaying) }

    fun getNowPlaying() {
        _nowPlaying.value = ViewState.Loading
        viewModelScope.launch {
            getNowPlayingUseCase()
                .onSuccess { _nowPlaying.value = ViewState.Success(it) }
                .onError { _nowPlaying.value = ViewState.Error(it) }
        }
    }
}