package io.android.core.vo

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error<out T>(val cause: Exception, val data: T? = null) : ViewState<T>()
}
