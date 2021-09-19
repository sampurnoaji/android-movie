package com.example.movie.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.core.domain.model.NowPlaying
import io.android.core.domain.usecase.GetNowPlayingUseCase
import io.android.core.util.onError
import io.android.core.util.onSuccess
import io.android.core.vo.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val getNowPlayingUseCase: GetNowPlayingUseCase) : ViewModel() {

    private val _nowPlaying = MutableLiveData<ViewState<List<NowPlaying>>>()
    val nowPlaying = liveData { emitSource(_nowPlaying) }

    fun getNowPlaying() {
        _nowPlaying.value = ViewState.Loading
        viewModelScope.launch {
            getNowPlayingUseCase()
                .collect { result ->
                    result.onSuccess { _nowPlaying.value = ViewState.Success(it) }
                        .onError { cause, data ->
                            _nowPlaying.value = ViewState.Error(cause, data)
                        }
                }
        }
    }
}