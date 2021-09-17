package io.android.core.vo

sealed class Either<A, B> {
    class Failure<A, B>(val cause: A, val data: B? = null) : Either<A, B>()
    class Success<A, B>(val data: B) : Either<A, B>()
}
