package io.android.core.vo

sealed class DataResponse<A, B> {
    class Failure<A, B>(val cause: A) : DataResponse<A, B>()
    class Success<A, B>(val data: B) : DataResponse<A, B>()
}
