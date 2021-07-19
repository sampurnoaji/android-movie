package com.example.movie.abstraction

import io.android.momobill.vo.Either

interface UseCase<Params, T> {
    suspend operator fun invoke(params: Params): Either<Exception, T>
}
