package io.android.core.util

import io.android.core.vo.Either

inline fun <A, B> Either<A, B>.onError(onError: (A, B?) -> Unit): Either<A, B> {
    if (this is Either.Failure) {
        onError(this.cause, this.data)
    }
    return this
}

inline fun <A, B> Either<A, B>.onSuccess(onSuccess: (B) -> Unit): Either<A, B> {
    if (this is Either.Success) {
        onSuccess(this.data)
    }
    return this
}
