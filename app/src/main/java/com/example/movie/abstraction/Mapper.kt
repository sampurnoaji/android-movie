package com.example.movie.abstraction

abstract class Mapper<Dto, Domain> {
    abstract fun invoke(dto: Dto): Domain
}