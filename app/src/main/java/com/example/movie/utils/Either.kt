package com.example.movie.utils

sealed class Either<A, B> {
    class Failure<A, B>(val cause: A) : Either<A, B>()
    class Success<A, B>(val data: B) : Either<A, B>()
}
