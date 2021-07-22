package com.example.movie.abstraction

import com.example.movie.utils.Either

interface UseCase<Params, T> {
    suspend operator fun invoke(params: Params): Either<Exception, T>
}
