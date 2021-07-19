package io.android.momobill.vo

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Error(val cause: Exception) : ViewState<Nothing>()
}
