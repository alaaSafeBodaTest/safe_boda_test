package com.example.safebodatest.core.usecase_templates

import arrow.core.Either

interface UseCase<P, R, F> {

    fun run(params: P): Either<R, F?> = Either.Right(null)

    suspend fun runAsync(params: P): Either<R, F?> = Either.Right(null)
}