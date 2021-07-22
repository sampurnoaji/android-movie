package com.example.movie.abstraction

import com.example.movie.utils.Either

interface NoParamsUseCase<T> {
    suspend operator fun invoke(): Either<Exception, T>
}
