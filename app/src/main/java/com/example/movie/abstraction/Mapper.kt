package com.example.movie.abstraction

abstract class Mapper<Dto, Domain> {
    abstract operator fun invoke(dto: Dto): Domain
}