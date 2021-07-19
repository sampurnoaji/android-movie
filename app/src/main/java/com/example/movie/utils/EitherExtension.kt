package com.example.movie.utils

import io.android.momobill.vo.Either

inline fun <A, B> Either<A, B>.onError(onError: (A) -> Unit): Either<A, B> {
    if (this is Either.Failure) {
        onError(this.cause)
    }
    return this
}

inline fun <A, B> Either<A, B>.onSuccess(onSuccess: (B) -> Unit): Either<A, B> {
    if (this is Either.Success) {
        onSuccess(this.data)
    }
    return this
}
