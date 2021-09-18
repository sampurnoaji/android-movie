package io.android.favorite.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.android.core.domain.model.NowPlaying
import io.android.core.domain.usecase.GetFavoriteMoviesUseCase
import io.android.core.vo.ViewState
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase) :
    ViewModel() {

    private val _favorites = MutableLiveData<ViewState<List<NowPlaying>>>()
    val favorites = liveData { emitSource(_favorites) }

    fun getNowPlaying() {
        _favorites.value = ViewState.Loading
        viewModelScope.launch {
//            getFavoriteMoviesUseCase()
//                .onSuccess { it.collect { data ->
//                    _favorites.value = ViewState.Success(data)
//                } }
//                .onError { cause, data ->
//                    _favorites.value = ViewState.Error(cause, data)
//                }
        }
    }
}