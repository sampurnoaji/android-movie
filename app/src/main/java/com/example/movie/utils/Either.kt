package io.android.momobill.vo

sealed class Either<A, B> {
    class Failure<A, B>(val cause: A) : Either<A, B>()
    class Success<A, B>(val data: B) : Either<A, B>()
}
