package com.example.movie.abstraction

import io.android.momobill.vo.Either

interface NoParamsUseCase<T> {
    suspend operator fun invoke(): Either<Exception, T>
}
