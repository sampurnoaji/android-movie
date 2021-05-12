package com.example.movie.vo

sealed class LoadResult<out T> {
    object Loading : LoadResult<Nothing>()
    object Error : LoadResult<Nothing>()
    data class Success<out T>(val data: T) : LoadResult<T>()
}
